layui.config({
	base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		laydate = layui.laydate,
		$ = layui.jquery;
	var standardid = sessionStorage.getItem("standardID");
	//编辑器图片上传路径
	layedit.set({
		uploadImage: {
			url: "/standard/uploadImage" //接口url
			,type: "post" //默认post
		}
	});

	var editIndex = "";
	//获取新闻资讯数据并进行渲染
	$.get("/standard/getStandardByid?standardid="+standardid,function(data){
		var standard = data;
		$("#standardid").val(standard.standardid);
		$(".title").val(standard.standardtitle);
		$("#standard_content_edit").val(standard.standardcontent);
		//创建一个编辑器
		editIndex = layedit.build('standard_content_edit',{
			content:standard.standardcontent
		});
	});

	form.on("submit(editStandard)",function(data){
		var standard = {
			standardid:standardid,
			standardtitle:$(".title").val(),
			standardcontent:layedit.getContent(editIndex)
		};
		$.post(
			"/standard/updateStandard",
			standard,
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

