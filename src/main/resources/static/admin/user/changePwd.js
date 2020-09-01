/**
 * Created by XWD on 2017/10/30.
 */
var $;
layui.config({
    base : "/static/js/"
}).use(['layer',],function(){
    var layer = parent.layer === undefined ? layui.layer : parent.layer;
    $ = layui.jquery;
    //修改密码
    $("#change").click(function(){
        var oldPwd = $("#oldPwd").val();
        var newPwd = $("#newPwd").val();
        var confirmPwd =$("#confirmPwd").val();

        if(newPwd.length <6 ){
            layer.msg("密码长度不能小于6位!");
            return false;
        }else if(!new RegExp(newPwd).test(confirmPwd)){
            layer.msg("两次输入密码不一致，请重新输入！");
            return false;
        }else{
            var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
            $.ajax({
                type: "POST",
                dataType: "text",
                url: "/admin/changePwd",
                data: {
                    "oldPwd":oldPwd,
                    "newPwd":newPwd
                },
               success:function(data){
                    if(new RegExp("yes").test(data)){
                        setTimeout(function(){
                            layer.close(index);
                            parent.location="/admin/logout";
                        },2000);
                        layer.msg("密码修改成功,请重新登录！",{time: 3000});
                    }else if(new RegExp("no").test(data)){
                        setTimeout(function(){
                            layer.close(index);
                            layer.msg("密码修改失败!");
                        },1000);
                    }else if(new RegExp("errorOldPwd").test(data)){
                        setTimeout(function(){
                            layer.close(index);
                            layer.msg("旧密码错误，请重新输入！");
                        },1000);
                    }
               }
            });
        }

    });





});