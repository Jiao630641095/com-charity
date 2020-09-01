layui.config({
	base : "js/"
}).use(['form','layer','jquery','upload'],function(){
	var form = layui.form,
		upload = layui.upload,
		$ = layui.jquery;
	var id = sessionStorage.getItem("netWorkID");

	//加载类型数据
	$.get(
		"/network/typeList",
		"",
		function (data) {
			console.log(data)
			for(var i in data ){
				console.log(data[i].id);
				console.log($("select[name='quiznews1']"));
				$("#type").append("<option value='"+data[i].id+"' selected=''>"+data[i].name+"</option>");
			}
			form.render("select","test1");
		},
		"json"
	);


	//获取新闻资讯数据并进行渲染
	$.get("/network/list?id="+id,function(data){
		var network = data[0];
		$("#id").val(network.id);
		$(".name").val(network.name);
		$(".link").val(network.link);
		$("#logo").attr('src',network.logo); //图片链接（base64）
		console.log(network.tid)
		$("#type").val(network.tid);
		form.render("select","test1");
		$("#type option").each(function(){
			if(this.value == network.tid){
				$(this).attr("selected",true)
			}
		})
	});
	//更新数据
	form.on("submit(addNetWork)",function(data){//$(".layui-input-block html body").val();
		var pid;
		var quiznews1 = $("select[name='quiznews1']").val();
		var quiznews2 = $("select[name='quiznews2']").val();
		if(quiznews2==null||quiznews2<=0){
			pid = quiznews1;
		}else{
			pid = quiznews2;
		}
		var netWork = {
			id:$("#id").val(),
			name:$(".name").val(),
			link:$(".link").val(),
			tid:$("#type").val(),
			logo:$("#logo").attr('src')
		};
		$.post(
			"/network/edit",
			netWork,
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
	//logo上传
	upload.render({
		elem: '#img'
		, url: '/network/upload'
		, accept: 'image/gif,image/jpeg,image/jpg,image/png,image/svg' //普通文件
		, before: function (obj) {
			//预读本地文件示例，不支持ie8
			obj.preview(function (index, file, result) {

			});
		}
		, done: function(res){
			//如果上传失败
			if(res.code > 0){
				return layer.msg('上传失败');
			}
			//上传成功
			$('#logo').attr('src', res.data.src); //图片链接（base64）
		}
		,error: function(){
			//演示失败状态，并实现重传
			var demoText = $('#demoText');
			demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
			demoText.find('.demo-reload').on('click', function(){
				uploadInst.upload();
			});
		}
	});
})
