layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;
	//加载类型数据
	$.get("/adai/typeList", function(data){
		for(var i=0;i<data.length;i++){
			$("#type").append(
				"<option value='"+data[i].id+"'>"+data[i].name+"</option>"
			)
		}
	});
	//条件查询
	function getNetWork(value){
		alert(value);
	}

	//加载页面数据
	var newsData = '';
	$.get("/adai/list", function(data){
		var newArray = [];
		newsData = data;
		if(window.sessionStorage.getItem("addNews")){
			var addNews = window.sessionStorage.getItem("addNews");
			newsData = JSON.parse(addNews).concat(newsData);
		}
		//执行加载数据的方法
		newsList();
	})

	//添加文章
	$(".newsAdd_btn").click(function(){
		var index = layui.layer.open({
			title:"添加爱心网络",
			type : 2,
			content : "/adai/gonetworkAdd",
			success : function(layero, index){
				layui.layer.tips('点击此处返回网络列表', '.layui-layer-setwin .layui-layer-close', {
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

	//批量删除
	var ids = "";
	$(".batchDel").click(function(){
		$('input[name="checked"]:checked').each(function(){
			ids+=","+ $(this).val();
		});
		ids = ids.substring(1);
		//  alert(ids);
		if(ids != ""){
			layer.confirm('确定删除选中的信息？',{icon:3, title:'提示信息'},function(index){
				var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
				$.post(
					"/adai/deleteNetwork",
					{ids:ids},
					function(data){
						if(data){
							setTimeout(function(){
								layer.close(index);
								layer.msg("删除成功");
								location.reload();
							},1000);
						}
					},"json"
				);
			})
		}else{
			layer.msg("请选择需要删除的爱心网络");
		}
	})

	//全选
	form.on('checkbox(allChoose)', function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		child.each(function(index, item){
			item.checked = data.elem.checked;
		});
		form.render('checkbox');
	});

	//通过判断文章是否全部选中来确定全选按钮是否选中
	form.on("checkbox(choose)",function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		var childChecked = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"]):checked')
		if(childChecked.length == child.length){
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = true;
		}else{
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = false;
		}
		form.render('checkbox');
	})


	//操作
	$("body").on("click",".network_edit",function(){  //编辑
		var id = $(this).parent().parent().children("td:first-child").children("input:first-child").val();
		sessionStorage.setItem("netWorkID",id);
		var index = layui.layer.open({
			title : "编辑爱心网络信息",
			type : 2,
			content : "/adai/gonetworkEdit",
			success : function(layero, index){
				layui.layer.tips('点击此处返回爱心网络列表', '.layui-layer-setwin .layui-layer-close', {
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

	//单个删除
	$("body").on("click",".network_del",function(){  //删除
		var id = $(this).parent().parent().children("td:first-child").children("input:first-child").val();
		//alert(id);
		layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(){
			var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});

			$.post(
				"/adai/deleteNetwork",
				{ids:id},
				function(data){
					if(data){
						location.reload();
						layer.close(index);
					}
				},"json"
			);
		});
	})

	function newsList(that){
		//渲染数据
		function renderDate(data,curr){
			var dataHtml = '';
			if(!that){
				currData = newsData.concat().splice(curr*nums-nums, nums);
			}else{
				currData = that.concat().splice(curr*nums-nums, nums);
			}
			if(currData.length != 0){
				for(var i=0;i<currData.length;i++){//
					dataHtml += '<tr>'
			    	+'<td><input type="checkbox" class="boxs" name="checked"  value="'+currData[i].id+'" lay-skin="primary" lay-filter="choose"/></td>'
			    	+'<td>'+currData[i].name+'</td>'
			    	+'<td><img src="'+ currData[i].logo +'" style="width: 30px;height: 30px"  class="layui-upload-img"/></td>'
			    	+'<td>'+currData[i].type+'</td>'
			    	+'<td><a href="'+currData[i].link+'" style="color:#1E9FFF;" target="_blank">'+currData[i].link+'</a></td>'
			    	+'<td>'
					+  '<a shiro:hasPermission="network:update" class="layui-btn layui-btn-mini network_edit"><i class="iconfont icon-edit" onclick="editNetWork"></i> 编辑</a>'
					+  '<a shiro:hasPermission="network:delete" class="layui-btn layui-btn-danger layui-btn-mini network_del"><i class="layui-icon delete">&#xe640;</i> 删除</a>'
			        +'</td>'
			    	+'</tr>';
				}
			}else{
				dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
			}
		    return dataHtml;
		}

		//分页
		var nums = 13; //每页出现的数据量
		if(that){
			newsData = that;
		}
		laypage.render({
		    elem : "page",
			count: newsData.length,
			pages : Math.ceil(newsData.length/nums),
			jump : function(obj){
				$(".news_content").html(renderDate(newsData,obj.curr));
				$('.news_list thead input[type="checkbox"]').prop("checked",false);
		    	form.render();
			}
		})
	}
})
