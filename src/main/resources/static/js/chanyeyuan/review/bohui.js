layui.config({
	base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		laydate = layui.laydate,
		$ = layui.jquery;
	var id = sessionStorage.getItem("reviewID");

	//编辑器图片上传路径
	layedit.set({
		uploadImage: {
			url: "/tpcnews/upload" //接口url
			,type: "post" //默认post
		}
	});

	var editIndex = "";
	//获取新闻资讯数据并进行渲染
	$.get("/tpcreview/getReviewByid?id="+id,function(data){
		var tpcreview = data;
		$("#id").val(tpcreview.id);
		$(".name").val(tpcreview.name);
		//创建一个编辑器
		editIndex = layedit.build('news_content_edit',{
			content:tpcreview.comment
		});
	});

	form.on("submit(editTpcNews)",function(data){
		var tpcreview = {
			id:id,
			comment:layedit.getContent(editIndex),
		};
		$.post(
			"/tpcreview/updateBohui",
			tpcreview,
			function(data){
				setTimeout(function(){
					top.layer.close(index);
					top.layer.msg("驳回成功！");
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

