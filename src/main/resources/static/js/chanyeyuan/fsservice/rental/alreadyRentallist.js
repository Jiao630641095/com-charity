layui.use(["form","layer","jquery","laypage","laydate"],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		laydate = layui.laydate,
		$ = layui.jquery;

	//加载页面数据
	var rentalData = '';
	$.get("/tpcrental/getAlreadyRentalList", function(data){
		rentalData = data;
		rentalList();//执行加载数据的方法
	},"json")

	//添加房屋
    $("body").on("click",".add_button",function(){
		var index = layui.layer.open({
			title : "添加记录",
			type : 2,
			content : "/tpcrental/goAddalready",
			success : function(){
				layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
					tips: 3
				});
			}
		})
		$(window).resize(function(){
			layui.layer.full(index);
		})
		layui.layer.full(index);
	});
	//编辑房屋信息
	$("body").on("click",".tpcrental_edit",function(){  //编辑
		var id = $(this).parent().parent().children("td:first-child").children("input:first-child").val();
		var index = layui.layer.open({
			title : "编辑记录",
			type : 2,
			content : "/tpcrental/goUpdatealready?id="+id,
			success : function(){
				layui.layer.tips('点击此处返回房屋列表', '.layui-layer-setwin .layui-layer-close', {
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
					"/tpcrental/deleteAlreadyRental",
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
			layer.msg("请选择需要删除的信息！");
		}
	});

	//条件查询
    $("body").on("click",".search_button",function(){
       var rental = {
		   name:$(".name").val(),
	   }
		$.post("/tpcrental/getAlreadyRentalListWhere",rental, function(data){
			rentalData = data;
			rentalList();//执行加载数据的方法
		},"json")
    });
    //清空
    $("body").on("click",".stick_button",function(){
		$(".name").val("");
		$.get("/tpcrental/getAlreadyRentalList", function(data){
			rentalData = data;
			rentalList();//执行加载数据的方法
		},"json")
    });
    //全选
    form.on('checkbox(allChoose)', function(data){
        var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
        child.each(function(index, item){
            item.checked = data.elem.checked;
        });
        form.render('checkbox');
    });
    //渲染数据
	function rentalList(that){
		function renderDate(data,curr){
			var dataHtml = '';
			if(!that){
				currData = rentalData.concat().splice(curr*nums-nums, nums);
			}else{
				currData = that.concat().splice(curr*nums-nums, nums);
			}
			if(currData.length != 0){
				for(var i=0;i<currData.length;i++){
				   dataHtml +=
						'<tr>'
						+'<td align="center"><input type="checkbox" class="boxs" name="checked"  value="'+currData[i].id+'" lay-skin="primary" lay-filter="choose"></td>'
						+'<td align="center">'+currData[i].qyname+'</td>'
						+'<td align="center">'+currData[i].roomnum+'</td>'
						+'<td align="center">'+currData[i].name+'</td>'
						+'<td align="center">'+currData[i].phone+'</td>'
						+'<td align="center">'+(currData[i].paytype==3?"季度付":currData[i].paytype==6?"半年付":currData[i].paytype==12?"年付":"")+'</td>'
						+'<td align="center">'+currData[i].create_time+'</td>'
						+'<td align="center">'+currData[i].pay_time+'</td>'
						+'<td align="center">'+currData[i].end_time+'</td>'
						+'<td align="center">'
						+  '<a  shiro:hasPermission="tpcrental:update3" class="layui-btn layui-btn-mini tpcrental_edit"><i class="iconfont icon-edit"></i> 编辑</a>'
						+'</td>'
						+'</tr>'

				}
			}else{
				dataHtml = '<tr><td colspan="12">暂无数据</td></tr>';
			}
			return dataHtml;
		}

		//分页
		var nums = 13; //每页出现的数据量
		if(that){
			rentalData = that;
		}
		laypage.render({
			elem : "page",
			count: rentalData.length,
			limit:13,
			//pages : Math.ceil(newsData.length/nums),
			jump : function(obj){
				$(".rental_content").html(renderDate(rentalData,obj.curr));
				$('.rental_list thead input[type="checkbox"]').prop("checked",false);
				form.render();
			}
		})
	}
	//绑定多个时间
	lay('.search_input_time').each(function(){
		laydate.render({
			elem: this
			,trigger: 'click'
		});
	});

})
