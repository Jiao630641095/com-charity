layui.use(["form","layer","jquery","laypage","laydate"],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		laydate = layui.laydate,
		$ = layui.jquery;

	//加载页面数据
	var newsData = '';
	$.get("/tpctrain/list", function(data){
		newsData = data;
		newsList();//执行加载数据的方法
	},"json")

   //授权、夺权功能
	$("body").on("click",".check_button",function(){
		var ids = "";
		$('input[name="checked"]:checked').each(function(){
			ids+=","+ $(this).val();
		});
		ids = ids.substring(1);
		if(ids != ""){
			layer.confirm('确定对选中的信息进行操作？',{icon:3, title:'提示信息'},function(index){
				var index = layer.msg('操作中，请稍候',{icon: 16,time:false,shade:0.8});
				$.post(
					"/tpctrain/updateIsShow",
					{ids:ids},
					function(data){
						if(data){
							setTimeout(function(){
								layer.close(index);
								layer.msg("操作成功");
								location.reload();
							},1000);
						}
					},"json"
				);
			})
		}else{
			layer.msg("请选择需要操作的信息");
		}
	});
	//条件查询
    $("body").on("click",".search_button",function(){
       var news = {
		   realName:$(".realName").val(),
	   }
		$.post("/tpctrain/getListWhere",news, function(data){
			newsData = data;
			newsList();//执行加载数据的方法
		},"json")
    });
    //清空
    $("body").on("click",".stick_button",function(){
		$(".realName").val("");
		$.get("/tpctrain/list", function(data){
			newsData = data;
			newsList();//执行加载数据的方法
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
	function newsList(that){
		function renderDate(data,curr){
			var dataHtml = '';
			if(!that){
				currData = newsData.concat().splice(curr*nums-nums, nums);
			}else{
				currData = that.concat().splice(curr*nums-nums, nums);
			}
			if(currData.length != 0){
				for(var i=0;i<currData.length;i++){
                    dataHtml +=
                        '<tr>'
						+'<td align="center"><input type="checkbox" class="boxs" name="checked"  value="'+currData[i].id+'" lay-skin="primary" lay-filter="choose"></td>'
						+'<td align="center">'+currData[i].title+'</td>'
						+'<td align="center">'+currData[i].realName+'</td>'
						+'<td align="center">'+currData[i].phone+'</td>'
						+'<td align="center">'+currData[i].email+'</td>'
						+'<td align="center">'+currData[i].num+'</td>'
						+'<td align="center">'+(currData[i].isShow==1?"<span class='layui-badge layui-bg-green'>已审核</span>":"<span class='layui-badge'>待审核</span>")+'</td>'
						+'<td align="center">'+dateformat(currData[i].create_time)+'</td>'
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
			limit:13,
			//pages : Math.ceil(newsData.length/nums),
			jump : function(obj){
				$(".news_content").html(renderDate(newsData,obj.curr));
				$('.news_list thead input[type="checkbox"]').prop("checked",false);
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

})
