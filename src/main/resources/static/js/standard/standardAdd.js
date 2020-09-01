layui.config({
	base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		laydate = layui.laydate,
		$ = layui.jquery;
	//编辑器图片上传
	layedit.set({
		uploadImage: {
			url: "/standard/uploadImage" //接口url
			,type: "post" //默认post
		}
	});
	//创建一个编辑器
 	var editIndex = layedit.build('news_content');
 	form.on("submit(addStandard)",function(data){
		var standard = {
			standardtitle:$(".title").val(),
			standardcontent:layedit.getContent(editIndex)
		};
		$.post(
			"/standard/addStandard",
			standard,
			function(data){
				setTimeout(function(){
					top.layer.close(index);
					top.layer.msg("文章添加成功！");
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
