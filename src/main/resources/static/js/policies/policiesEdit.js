layui.config({
	base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		laydate = layui.laydate,
		$ = layui.jquery;
	var policiesid = sessionStorage.getItem("policiesID");

	//编辑器图片上传路径
	layedit.set({
		uploadImage: {
			url: "/policies/uploadImage" //接口url
			,type: "post" //默认post
		}
	});

	var editIndex = "";
	//获取新闻资讯数据并进行渲染
	$.get("/policies/getPoliciesByid?policiesid="+policiesid,function(data){
		var policies = data;
		$("#policiesid").val(policies.policiesid);
		$(".title").val(policies.policiestitle);
		$("#policies_content_edit").val(policies.policiescontent);
		//创建一个编辑器
		editIndex = layedit.build('policies_content_edit',{
			content:policies.policiescontent
		});
	});

	form.on("submit(editPolicies)",function(data){
		var policies = {
			policiesid:policiesid,
			policiestitle:$(".title").val(),
			policiescontent:layedit.getContent(editIndex)
		};
		$.post(
			"/policies/updatePolicies",
			policies,
			function(data){
				setTimeout(function(){
					top.layer.close(index);
					top.layer.msg("文章修改成功！");
					layer.closeAll("iframe");
					//刷新父页面
					parent.location.reload();
				},1000);
			},"json"
		);
		//弹出loading
		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
		return false;
	})

})

