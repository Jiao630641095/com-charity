layui.use(["form","layer","jquery","laypage","laydate"],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		laydate = layui.laydate,
		$ = layui.jquery;
	//加载页面数据
	var foodData = '';
	$.get("/tpctodayfood/list", function(data){
		foodData = data;
		foodList();//执行加载数据的方法
	},"json")

	//添加菜品
    $("body").on("click",".add_button",function(){
		var index = layui.layer.open({
			title : "添加菜品",
			type : 2,
			content : "/tpctodayfood/goAdd",
			success : function(){
				layui.layer.tips('点击此处返回菜品列表', '.layui-layer-setwin .layui-layer-close', {
					tips: 3
				});
			}
		})
		$(window).resize(function(){
			layui.layer.full(index);
		})
		layui.layer.full(index);
	});
	//编辑菜品
	$("body").on("click",".food_edit",function(){  //编辑
		var id = $(this).parent().parent().children("td:first-child").children("input:first-child").val();
		sessionStorage.setItem("tpcfoodID",id);
		var index = layui.layer.open({
			title : "编辑菜品",
			type : 2,
			content : "/tpctodayfood/goupdate?id="+id,
			success : function(){
				layui.layer.tips('点击此处返回菜品列表', '.layui-layer-setwin .layui-layer-close', {
					tips: 3
				});
			}
		})
		//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			layui.layer.full(index);
		})
		layui.layer.full(index);
	})


    //删除
    $("body").on("click",".delete_button",function(){
		var ids = "";
		$('input[name="checked"]:checked').each(function(){
			ids+=","+ $(this).val();
		});
		ids = ids.substring(1);
		//  alert(ids);
		if(ids != ""){
			layer.confirm('确定删除选中的信息？',{icon:3, title:'提示信息'},function(index){
				var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
				$.post(
					"/tpctodayfood/deleteTpcFood",
					{ids:ids},
					function(data){
						if(data){
							setTimeout(function(){
								layer.close(index);
								layer.msg("删除成功!");
								location.reload();
							},1000);
						}
					},"json"
				);
			})
		}else{
			layer.msg("请选择需要删除的菜品");
		}
	});
	//条件查询
    $("body").on("click",".search_button",function(){
       var food = {
		   name:$(".name").val()
	   }
		$.post("/tpctodayfood/getFoodListWhere",food, function(data){
			foodData = data;
			foodList();//执行加载数据的方法
		},"json")
    });
    //重置
    $("body").on("click",".stick_button",function(){
		$(".name").val("");
		$.get("/tpctodayfood/list", function(data){
			foodData = data;
			foodList();//执行加载数据的方法
		},"json")
    });
    //全选
    form.on('checkbox(allChoose)', function(data){
        var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="status"])');
        child.each(function(index, item){
            item.checked = data.elem.checked;
        });
        form.render('checkbox');
    });
	//是否展示
	form.on('switch(status)', function(data){
		var id = $(this).parent().parent().children("td:first-child").children("input:first-child").val();
		$.post(
			"/tpctodayfood/updateStatus",
			{id:id},
			function (data) {
				setTimeout(function(){
					layer.close(index);
					layer.msg("展示状态修改成功！");
				},1000);
			}, "json"
		);
		var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
	})

    //渲染数据
	function foodList(that){
		function renderDate(data,curr){
			var dataHtml = '';
			if(!that){
				currData = foodData.concat().splice(curr*nums-nums, nums);
			}else{
				currData = that.concat().splice(curr*nums-nums, nums);
			}
			if(currData.length != 0){
				for(var i=0;i<currData.length;i++){
                    dataHtml +=
                        '<tr>'
						+'<td align="center"><input type="checkbox" class="boxs" name="checked"  value="'+currData[i].id+'" lay-skin="primary" lay-filter="choose"></td>'
						+'<td align="center">'+ '<img src="'+ currData[i].photo +'"  class="layui-upload-img"/>'
						+'<td align="center">'+currData[i].name+'</td>'
						+'<td align="center">'+currData[i].price+'元</td>'
						+'<td align="center">'+currData[i].unit+'</td>'
						+'<td align="center"><input type="checkbox" name="status" lay-skin="switch" lay-text="是|否" lay-filter="status"'+(currData[i].status==1?"checked":"")+'></td>'
						+'<td align="center">'
						+  '<a  shiro:hasPermission="tpctodayfood:update" class="layui-btn layui-btn-mini food_edit"><i class="iconfont icon-edit"></i> 编辑</a>'
						+'</td>'
						+'</tr>'
				}
			}else{
				dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
			}
			return dataHtml;
		}

		//分页
		var nums = 7; //每页出现的数据量
		if(that){
			foodData = that;
		}
		laypage.render({
			elem : "page",
			count: foodData.length,
			limit:7,
			//pages : Math.ceil(foodData.length/nums),
			jump : function(obj){
				$(".news_content").html(renderDate(foodData,obj.curr));
				$('.news_list thead input[type="checkbox"]').prop("checked",false);
				form.render();
			}
		})
	}


})
