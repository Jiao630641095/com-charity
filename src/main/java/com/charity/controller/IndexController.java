package com.charity.controller;

import com.charity.common.Constant;
import com.charity.common.annotation.OperationLog;
import com.charity.common.base.controller.BaseController;
import com.charity.common.util.ShiroUtil;
import com.charity.common.util.TimeFormat;
import com.charity.common.util.WebUtil;
import com.charity.entity.shiro.Permission;
import com.charity.entity.shiro.User;
import com.charity.service.PermissionService;
import com.charity.service.UserService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;

import static com.charity.common.Constant.HEAD_SESSION_STATUS_KEY;
import static com.charity.common.Constant.HEAD_SESSION_STATUS_VALUE;

/**
 * @Author：王思峰
 * @Description: 首页
 * @Date: Created in 10:34 2017/10/9
 * @Modified By:
 */
@Controller
@RequestMapping(value = {"/admin","/"})
public class IndexController extends BaseController {

    @Resource
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    @Autowired
    DefaultKaptcha defaultKaptcha;
    /**
     * 第一次请求跳转首页
     */
    @GetMapping(value = "")
    public String toIndex(){
        return "redirect:/admin/index";
    }

    /**
     *  验证码
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            log.info("验证码："+createText);
            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /**
     * 首页
     * @return
     */
    @GetMapping(value = "/admin/index")
    public String index(ModelMap modelMap){
        //从shiro的session中取user
        User user = ShiroUtil.getUserEntity();
        List<Permission> menuList = permissionService.findMenuListByUserId(user.getId());
        //通过model传到页面
        modelMap.addAttribute("menuList", menuList);
        modelMap.addAttribute("user",user);
        log.info(user.getRealName()+ TimeFormat.time()+"-----进入首页");
        return "admin/index";
    }

    /**
     * 欢迎页
     * @return
     */
    @GetMapping(value = "/admin/welcome")
    public String welcome(){
        log.info("------进入欢迎页-------");
        return "admin/welcome";
    }

    /**
     * 未授权页面
     * @return
     */
    @GetMapping(value = "/admin/403")
    public String unauthorized(){
        log.info("------没有权限-------");
        return "admin/403";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping(value = "/login")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {
        log.info("跳转到登录页面！");
        //Ajax登录超时校验,如果超时，进行前台响应提示
        if(WebUtil.isAjaxRequest(request)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader(HEAD_SESSION_STATUS_KEY , HEAD_SESSION_STATUS_VALUE);
            response.setContentType("text/hON_STATUS_KEYtml;charset=utf-8");
        }
        return "admin/login";
    }

    /**
     * 用户登陆
     * 先根据用户名查询出一条用户记录再对比密码是否正确可以防止sql注入
     * @param username  用户名
     * @param password  密码
     * @return
     */
    @PostMapping(value = "/login")
    public ResponseEntity<String> login(HttpServletRequest httpServletRequest,String username, String password, String code){
        //获取后台生成的验证码
        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
        //台生成的验证码与用户输入的验证码进行比较
        if (captchaId.equals(code)) {
            try {
                //获取当前的Subject
                Subject currentUser = ShiroUtil.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
                //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
                //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
                log.info("对用户进行登录验证..验证开始! username = {}", username);
                currentUser.login(token);
                //验证是否登录成功
                if (currentUser.isAuthenticated()) {
                    log.info("对用户进行登录验证..验证通过! username = {}", username);
                    return ResponseEntity.ok(Constant.USER_LOGIN_IN);
                }
            } catch (UnknownAccountException e) {  //账号不存在
                log.info("对用户进行登录验证..验证未通过,未知账户! username = {}", username);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constant.USER_NOT_FIND);
            } catch (IncorrectCredentialsException e) {
                log.info("对用户进行登录验证..验证未通过,错误的凭证! username = {}", username);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constant.USER_INVALID);
            } catch (LockedAccountException e) {
                log.info("对用户进行登录验证..验证未通过,账户已锁定! username = {}", username);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constant.USER_HAS_LOCK);
            } catch (ExcessiveAttemptsException eae) {
                log.info("对用户进行登录验证..验证未通过,错误次数过多! username = {}", username);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constant.USER_ERROR_MANY);
            } catch (AuthenticationException e) {
                log.info("对用户进行登录验证..验证未通过,身份验证失败! username = {}", username);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constant.USER_INVALID);
            } catch (Exception e) {
                log.error("对用户进行登录验证失败! username = {} e = {}", username, e);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constant.SYSTEM_ERRORS);
        }else{
            log.info("对用户进行登录验证..验证未通过,验证码错误! code = {}", code);
            //次status 1981状态码为自定义的
            return ResponseEntity.status(1981).body(Constant.CODE_ERROR);
        }
    }

    /**
     * 跳转到修改密码页面
     * @return
     */
    @GetMapping(value = "/changePwd")
    public String password(ModelMap modelMap){
        //获得当前登陆用户
        User user = ShiroUtil.getUserEntity();
        modelMap.addAttribute("user",user);
        log.info(user.getRealName()+ TimeFormat.time()+"跳转到修改密码页面成功!");
        return "admin/changePwd";
    }

    /**
     * 修改密码
     * @return
     */
    @OperationLog(value = "修改密码")
    @ResponseBody
    @PostMapping(value = "/password")
    public ModelMap updatePassword(String oldPassword, String newPassword){
        ModelMap messagesMap = new ModelMap();
        try {
            //获得当前登陆用户
            User user = ShiroUtil.getUserEntity();

            if(!user.getPassword().equals(SecureUtil.md5().digestHex(oldPassword))){
                log.info("修改密码失败，原始密码不正确!");
                messagesMap.put("status",FAILURE);
                messagesMap.put("message","原始密码不正确!");
                return messagesMap;
            }

            User newUser = new User();
            newUser.setId(user.getId());
            newUser.setPassword(SecureUtil.md5().digestHex(newPassword));
            userService.updateSelective(newUser);

            log.info("修改密码成功!");
            messagesMap.put("status",SUCCESS);
            messagesMap.put("message","修改密码成功!");
            return messagesMap;
        } catch (Exception e) {
            log.error("修改密码失败! e = {}", e);
            messagesMap.put("status",FAILURE);
            messagesMap.put("message","修改密码失败!");
            return messagesMap;
        }
    }
    /**
     * 修改密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changePwd")
    public String updatePwd(String newPwd,String oldPwd){
        //获得当前登陆用户
        User user = ShiroUtil.getUserEntity();
    //    log.info("user.getPassword()="+user.getPassword());
        if(!user.getPassword().equals(oldPwd)){
            log.info(user.getRealName()+ TimeFormat.time()+"修改密码失败，原始密码不正确!");
            return "errorOldPwd";
        }
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setPassword(newPwd);
        if(userService.updatePwd(newUser)){
            log.info(user.getRealName()+ TimeFormat.time()+"修改密码成功!");
            return "yes";
        }else {
            log.info(user.getRealName()+ TimeFormat.time()+"修改密码失败!");
            return "no";
        }
    }







    /**
     *退出登录
     *
     * */
    @RequestMapping(value = "/logout")
    public String logout(){
        //获得当前登陆用户
        User user = ShiroUtil.getUserEntity();
        log.info(user.getRealName()+ TimeFormat.time()+"退出了系统!");
        //获取当前的Subject
        Subject currentUser = ShiroUtil.getSubject();
        //执行退出
        currentUser.logout();
        return "redirect:/admin/login";
    }
}

