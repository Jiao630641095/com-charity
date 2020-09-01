layui.use(["form","layer","jquery","laypage","laydate"],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		laydate = layui.laydate,
		$ = layui.jquery;

	//加载页面数据
	var rentalData = '';
	$.get("/tpcrental/list", function(data){
		rentalData = data;
		rentalList();//执行加载数据的方法
	},"json")

	//添加房屋
    $("body").on("click",".add_button",function(){
		var index = layui.layer.open({
			title : "添加房屋",
			type : 2,
			content : "/tpcrental/goAdd",
			success : function(){
				layui.layer.tips('点击此处返回房屋列表', '.layui-layer-setwin .layui-layer-close', {
					tips: 3
				});
			}
		})
		$(window).resize(function(){
			layui.layer.full(index);
		})
		layui.layer.full(index);
	});
	//编辑房屋
	$("body").on("click",".tpcrental_edit",function(){  //编辑
		var id = $(this).parent().parent().children("td:first-child").children("input:first-child").val();
		sessionStorage.setItem("tpcrentalID",id);
		var index = layui.layer.open({
			title : "编辑房屋",
			type : 2,
			content : "/tpcrental/goupdate",
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

	//查看
	$("body").on("click",".tpcrental_look",function(){  //查看
		var id = $(this).parent().parent().children("td:first-child").children("input:first-child").val();
		sessionStorage.setItem("tpcrentalID",id);
		var index = layui.layer.open({
			title : "房屋详情",
			type : 2,
			content : "/tpcrental/tpcRentalLook",
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
					"/tpcrental/deleteTpcRental",
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
			layer.msg("请选择需要删除的房屋");
		}
	});
	//审核
	$("body").on("click",".check_button",function(){
		var ids = "";
		$('input[name="checked"]:checked').each(function(){
			ids+=","+ $(this).val();
		});
		ids = ids.substring(1);
		//  alert(ids);
		if(ids != ""){
			layer.confirm('确定审核选中的信息？',{icon:3, title:'提示信息'},function(index){
				var index = layer.msg('审核中，请稍候',{icon: 16,time:false,shade:0.8});
				$.post(
					"/tpcrental/updateIsShow",
					{ids:ids},
					function(data){
						if(data){
							setTimeout(function(){
								layer.close(index);
								layer.msg("审核成功");
								location.reload();
							},1000);
						}
					},"json"
				);
			})
		}else{
			layer.msg("请选择需要审核的房屋");
		}
	});
	//推荐
	$("body").on("click",".isUp_button",function(){
		var ids = "";
		$('input[name="checked"]:checked').each(function(){
			ids+=","+ $(this).val();
		});
		ids = ids.substring(1);
		//  alert(ids);
		if(ids != ""){
			layer.confirm('确定推荐选中的信息？',{icon:3, title:'提示信息'},function(index){
				var index = layer.msg('推荐中，请稍候',{icon: 16,time:false,shade:0.8});
				$.post(
					"/tpcrental/updateIsUp",
					{ids:ids},
					function(data){
						if(data){
							setTimeout(function(){
								layer.close(index);
								layer.msg("推荐成功");
								location.reload();
							},1000);
						}
					},"json"
				);
			})
		}else{
			layer.msg("请选择需要推荐的房屋");
		}
	});
	//条件查询
    $("body").on("click",".search_button",function(){
       var rental = {
		   roomnum:$(".roomnum").val(),
		   floor:$(".floor").val(),
		   passageway:$(".passageway").val(),
		   rent:$(".rent").val()==""?0:$(".rent").val(),
		   torent:$(".torent").val()==""?99999:$(".torent").val(),
		   area:$(".area").val()==""?0:$(".area").val(),
		   toarea:$(".toarea").val()==""?99999:$(".toarea").val(),
		   face:$(".face").val()
	   }
		$.post("/tpcrental/getRentalListWhere",rental, function(data){
			rentalData = data;
			rentalList();//执行加载数据的方法
		},"json")
    });
    //清空
    $("body").on("click",".stick_button",function(){
		$(".roomnum").val("");
		$(".floor").val("");
		$(".passageway").val("");
		$(".rent").val("");
		$(".torent").val("");
		$(".area").val("");
		$(".toarea").val("");
		$(".face").val("");
		$.get("/tpcrental/list", function(data){
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
						+'<td align="center">'+ '<img src="'+ currData[i].photo1 +'"  class="layui-upload-img"/>'+'</td>'
						+'<td align="center">'+currData[i].roomnum+'</td>'
						+'<td align="center">'+currData[i].floor+'</td>'
						+'<td align="center">'+currData[i].passageway+'</td>'
						+'<td align="center">'+currData[i].rent+'元/㎡/天</td>'
						+'<td align="center">'+currData[i].area+'㎡</td>'
						+'<td align="center">'+currData[i].face+'</td>'
						+'<td align="center">'+(currData[i].isShow==1?"<span class='layui-badge layui-bg-green'>已审核</span>":"<span class='layui-badge'>待审核</span>")+'</td>'
						+'<td align="center">'+(currData[i].isUp==1?"<span class='layui-badge layui-bg-green'>已推荐</span>":"<span class='layui-badge'>待推荐</span>")+'</td>'
						+'<td align="center">'+dateformat(currData[i].createTime)+'</td>'
						+'<td align="center">'+dateformat(currData[i].modifyTime)+'</td>'
						+'<td align="center">'
						+  '<a  shiro:hasPermission="tpcrental:view" class="layui-btn layui-btn-mini tpcrental_look"><i class="layui-icon">&#xe615;</i> 查看</a>'
						+  '<a  shiro:hasPermission="tpcrental:update" class="layui-btn layui-btn-mini tpcrental_edit"><i class="iconfont icon-edit"></i> 编辑</a>'
						+'</td>'
						+'</tr>'
				}
			}else{
				dataHtml = '<tr><td colspan="12">暂无数据</td></tr>';
			}
			return dataHtml;
		}

		//分页
		var nums = 6; //每页出现的数据量
		if(that){
			rentalData = that;
		}
		laypage.render({
			elem : "page",
			count: rentalData.length,
			limit:6,
			//pages : Math.ceil(newsData.length/nums),
			jump : function(obj){
				$(".rental_content").html(renderDate(rentalData,obj.curr));
				$('.rental_list thead input[type="checkbox"]').prop("checked",false);
				form.render();
			}
		})
	}
    //格式化日期
    function dateformat(value){
        if(value == null){
            return "暂未修改";
        }
        var date = new Date(value);
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = (date.getDate()+1 < 10 ? '0'+date.getDate() : date.getDate()) + ' ';
        h = (date.getHours()+1 < 10 ? '0'+date.getHours() : date.getHours()) + ':';
        m = (date.getMinutes()+1 < 10 ? '0'+date.getMinutes() : date.getMinutes()) + ':';
        s = (date.getSeconds()+1 < 10 ? '0'+date.getSeconds() : date.getSeconds());
        dateTime=Y+M+D+h+m+s;
        return dateTime;
    }
	//绑定多个时间
	lay('.search_input_time').each(function(){
		laydate.render({
			elem: this
			,trigger: 'click'
		});
	});

})
