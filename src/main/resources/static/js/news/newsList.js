layui.use(["form","layer","jquery","laypage","laydate"],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		laydate = layui.laydate,
		$ = layui.jquery;
	//加载页面数据
	var newsData = '';
	$.post("/admin/news/getNewsList", function(data){
		newsData = data;
		newsList();//执行加载数据的方法
	},"json")

	//添加新闻
    $("body").on("click",".add_button",function(){
        var url = $(this).data('url');  //请求地址
		var index = layui.layer.open({
			title : "添加新闻",
			type : 2,
			content : url,
			success : function(){
				layui.layer.tips('点击此处返回新闻列表', '.layui-layer-setwin .layui-layer-close', {
					tips: 3
				});
			}
		})
		$(window).resize(function(){
			layui.layer.full(index);
		})
		layui.layer.full(index);
	});
	//编辑新闻
    $("body").on("click",".edit_button",function(){
        var url = $(this).data('url');  //请求地址
		var index = layui.layer.open({
			title : "编辑新闻",
			type : 2,
			content : url,
			success : function(){
				layui.layer.tips('点击此处返回新闻列表', '.layui-layer-setwin .layui-layer-close', {
					tips: 3
				});
			}
		})
		//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			layui.layer.full(index);
		})
		layui.layer.full(index);
	});
    //批量删除
    $("body").on("click",".deleteAll_button",function(){
		var url = $(this).data('url');  //请求地址
		alert(url)
		var ids = [];
		$('input[name="checked"]:checked').each(function(){
			ids.push({id:$(this).val(), url: $(this).data("url")});
		});
		layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(){
			var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
			$.post(
				url, {ids:ids},
				function(data){
					if(data){
						location.reload();
						layer.close(index);
					}
				},"json"
			);
		});
	});
    //单个删除
    $("body").on("click",".delete_button",function(){
        var url = $(this).data('url');  //请求地址
        layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(){
            var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
            $.get(
                url,
                function(data){
                    if(data){
                        location.reload();
                        layer.close(index);
                    }
                },"json"
            );
        });
    });
	//条件查询
    $("body").on("click",".search_button",function(){
       var news = {
		   title:$(".title").val(),
		   createTime:$(".createTime").val(),
		   modifyTime:$(".modifyTime").val(),
		   websiteID: $("select[name='websiteID']").val()
	   }
		$.post("/admin/news/getNewsList",news, function(data){
			newsData = data;
			newsList();//执行加载数据的方法
		},"json")
    });
    //清空
    $("body").on("click",".stick_button",function(){
		$(".title").val("");
		$(".createTime").val("");
		$(".modifyTime").val("");
		$.post("/admin/news/getNewsList", function(data){
			newsData = data;
			newsList();//执行加载数据的方法
		},"json")
    });
    //是否展示
    form.on('switch(isShow)', function(data){
		var id = $(this).parent().parent().children("td:first-child").children("input:first-child").val();
		var websiteUrl = $(this).data("url");
        var isShow = ""
        if(!$(this).attr("checked")){
            isShow = "1";
        }
		$.post(
			"/admin/news/save",
			{id:id,isShow:isShow,websiteUrl:websiteUrl},
			function (data) {
				setTimeout(function(){
					layer.close(index);
					layer.msg("展示状态修改成功！");
				},1000);
			}, "json"
		);
        var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
    })
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
						+'<td align="center"><input type="checkbox" class="boxs" name="checked" data-url="'+currData[i].websiteUrl+'" value="'+currData[i].id+'" lay-skin="primary" lay-filter="choose"></td>'
						+'<td align="center">'+currData[i].title+'</td>'
						+'<td align="center">'+currData[i].website+'</td>'
						+'<td align="center"><input type="checkbox" name="show" data-url="'+currData[i].websiteUrl+'" lay-skin="switch" lay-text="是|否" lay-filter="isShow"'+(currData[i].isShow==1?"checked":"")+'></td>'
						+'<td align="center">'+dateformat(currData[i].createTime)+'</td>'
						+'<td align="center">'+dateformat(currData[i].modifyTime)+'</td>'
						+'<td align="center">'
							+'<a shiro:hasPermission="news:update" class="layui-btn layui-btn-mini edit_button" data-url="/admin/news/update?url='+currData[i].websiteUrl+'&&id='+currData[i].id+'"><i class="iconfont icon-edit"></i> 编辑</a>'
							+'<a shiro:hasPermission="news:delete" class="layui-btn layui-btn-danger layui-btn-mini delete_button" data-url="/admin/news/delete?url='+currData[i].websiteUrl+'&&id='+currData[i].id+'"><i class="layui-icon delete">&#xe640;</i>删除</a>'
						+'</td>'
						+'</tr>'
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
	//绑定多个时间
	lay('.search_input_time').each(function(){
		laydate.render({
			elem: this
			,trigger: 'click'
		});
	});

})
