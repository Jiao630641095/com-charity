layui.use([ "layer",'jquery'], function(){
	var  $ = layui.jquery,
	     layer = layui.layer;
	//video背景
	$(window).resize(function(){
		if($(".video-player").width() > $(window).width()){
			$(".video-player").css({"height":$(window).height(),"width":"auto","left":-($(".video-player").width()-$(window).width())/2});
		}else{
			$(".video-player").css({"width":$(window).width(),"height":"auto","left":-($(".video-player").width()-$(window).width())/2});
		}
	}).resize();

	$(".login_btn").click(function(){
		var usernameObj = $("input[name = 'username']");
		var passwordObj = $("input[name = 'password']");
		var codeObj = $("input[name = 'code']");
		if(usernameObj.val() == ""){
			layer.msg('账号不能为空',{icon: 0});
			return false;
		}
		if(passwordObj.val() == ""){
			layer.msg('密码不能为空',{icon: 0});
			return false;
		}
		if(codeObj.val() == ""){
			layer.msg('验证码不能为空',{icon: 0});
			return false;
		}
		if(usernameObj.val() != "" && passwordObj.val() != "" && codeObj.val() != ""){
			$.ajax({
				type: "POST",
				dataType: "json",
				url: "/admin/login",
				data: {
					"username": usernameObj.val(),
					"password": passwordObj.val(),
					"code": codeObj.val().toUpperCase(),
					"timestamp": new Date().getTime()
				},
				statusCode: {
					200: function () {
						window.location.href = "/admin/index";
						layer.close(layer.msg('登陆中，请稍候', {icon: 16, time: false, shade: 0.8}));
					},
					401: function (data) {
						if (data != "") {
							var dataJson = eval('(' + data.responseText + ')');
							//踢出询问"当前账号已经登录IP: 192.168.1.126<br/>登录时间: 2017-7-28 15:52:05 "
							if (dataJson.code == 2077) {
								layer.confirm(dataJson.msg, {
									icon: 5,
									title: '提示',
									btn: ['踢出', '取消']
								}, function (index) {
									//执行踢出并登录
									onLine(url + "?tc=1");
									closeMessage(index);
								});
							}
						}
					},
					404: function (data) {
						layer.msg('账号不存在',{icon: 0});
						sadMessage(data.responseText);
					},500: function (data) {
						layer.msg('密码不正确',{icon: 0});
						sadMessage(data.responseText);
					},
					1981: function (data) {
						layer.msg('验证码错误',{icon: 0});
						refreshCaptcha();
						sadMessage(data.responseText);
					}
				}
			});
		}
	});
});


//监测键盘按下时间
document.onkeydown=function(event){
	var e = event || window.event || arguments.callee.caller.arguments[0];
	if(e && e.keyCode==27){ // 按 Esc
	}
	if(e && e.keyCode==113){ // 按 F2
	}
	if(e && e.keyCode==13){ // enter 键
		document.getElementById("Button").click();
	}
};
//刷新验证码
function refreshCaptcha(){
	$("#kaptcha").attr("src","defaultKaptcha?d="+new Date()*1);
}

//session过期。刷新页面。跳转的登陆页面即可重新在当前页面打开。并跳出iframe。
if(window != top){
	top.location.href=location.href;
}