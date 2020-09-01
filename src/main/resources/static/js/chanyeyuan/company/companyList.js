layui.use(["form","layer","jquery","laypage","laydate"],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		laydate = layui.laydate,
		$ = layui.jquery;

	//加载页面数据
	var rentalData = '';
	$.get("/tpccompany/list", function(data){
		rentalData = data;
		rentalList();//执行加载数据的方法
	},"json")


	//查看
	$("body").on("click",".tpcrental_look",function(){  //查看
		var id = $(this).parent().parent().children("td:first-child").children("input:first-child").val();
		sessionStorage.setItem("tpccompanyID",id);
		var index = layui.layer.open({
			title : "招聘信息详情",
			type : 2,
			content : "/tpccompany/tpcCompanyJobLook",
			success : function(){
				layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
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





	//审核、驳回
	$("body").on("click",".check_button",function(){
		var ids = "";
		$('input[name="checked"]:checked').each(function(){
			ids+=","+ $(this).val();
		});
		ids = ids.substring(1);
		if(ids != ""){
			layer.confirm('确定操作选中的信息？',{icon:3, title:'提示信息'},function(index){
				var index = layer.msg('操作中，请稍候',{icon: 16,time:false,shade:0.8});
				$.post(
					"/tpccompany/updateIsShow",
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
			layer.msg("您还什么都没选呢");
		}
	});
	//推荐、还原
	$("body").on("click",".isUp_button",function(){
		var ids = "";
		$('input[name="checked"]:checked').each(function(){
			ids+=","+ $(this).val();
		});
		ids = ids.substring(1);
		//  alert(ids);
		if(ids != ""){
			layer.confirm('确定操作选中的信息？',{icon:3, title:'提示信息'},function(index){
				var index = layer.msg('操作中，请稍候',{icon: 16,time:false,shade:0.8});
				$.post(
					"/tpccompany/updateIsUp",
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
			layer.msg("您还什么都没选呢");
		}
	});
	//条件查询
    $("body").on("click",".search_button",function(){
       var rental = {
		   cname:$(".cname").val(),
	   }
		$.post("/tpccompany/getListWhere",rental, function(data){
			rentalData = data;
			rentalList();//执行加载数据的方法
		},"json")
    });
    //清空
    $("body").on("click",".stick_button",function(){
		$(".cname").val("");
		$.get("/tpccompany/list", function(data){
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
						+'<td align="center">'+currData[i].cname+'</td>'
						+'<td align="center">'+currData[i].name+'</td>'
						+'<td align="center">'+currData[i].size+'人</td>'
						+'<td align="center">'+(currData[i].age==0?"无":currData[i].age+"年")+'</td>'
						+'<td align="center">'+(currData[i].education==0?"不限":currData[i].education==1?"初中":currData[i].education==2?"高中":currData[i].education==3?"大专":currData[i].education==4?"本科":currData[i].education==5?"硕士":"博士")+'</td>'
						+'<td align="center">'+(currData[i].hideSalary==1?"面议":currData[i].minSalary+"-"+currData[i].maxSalary+"元/月")+'</td>'
						+'<td align="center">'+dateformat(currData[i].create_time)+'</td>'
						+'<td align="center">'+(currData[i].isUp==1?"<span class='layui-badge layui-bg-green'>已推荐</span>":"<span class='layui-badge'>待推荐</span>")+'</td>'
						+'<td align="center">'+(currData[i].isShow==1?"<span class='layui-badge layui-bg-green'>已审核</span>":"<span class='layui-badge'>待审核</span>")+'</td>'
						+'<td align="center">'
						+  '<a  shiro:hasPermission="tpccompany:view" class="layui-btn layui-btn-mini tpcrental_look"><i class="layui-icon">&#xe615;</i> 查看</a>'
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

})
