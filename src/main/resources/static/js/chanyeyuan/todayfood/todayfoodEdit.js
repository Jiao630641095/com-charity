layui.config({
	base : "js/"
}).use(['form','layer','jquery','layedit','laydate',"upload"],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		upload = layui.upload,
		$ = layui.jquery;

	var id = sessionStorage.getItem("tpcfoodID");


	//获取菜品数据并进行渲染
	$.get("/tpctodayfood/getFoodByid?id="+id,function(data){
		var food = data;
		$("#id").val(food.id);
		$(".name").val(food.name);
		$("#imgUrl").attr("src",food.photo);
		$(".price").val(food.price);
		var x = document.getElementById("unit"); //获取select对象
		var y = x.nextSibling.children[1]
		for(var z in y.children)
			if(y.children[z].getAttribute("lay-value")==food.unit)
				y.children[z].click();
	});

	form.on("submit(editTpcfood)",function(data){
		var food = {
			id:id,
			name:$(".name").val(),
			photo: $("#imgUrl").attr("src"),
			price:$(".price").val(),
			unit:$("select[name='unit']").val()
		};
		$.post(
			"/tpctodayfood/updateTpcTodayFood",
			food,
			function(data){
				setTimeout(function(){
					top.layer.close(index);
					top.layer.msg("菜品修改成功！");
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
	//图片上传
	//指定允许上传的文件类型
	upload.render({
		elem: '#img'
		, url: '/tpctodayfood/upload'
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
			$('#imgUrl').attr('src', res.data.src); //图片链接（base64）
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

