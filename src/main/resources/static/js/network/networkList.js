layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage','table'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
        table = layui.table,
		$ = layui.jquery;
	//加载类型数据

	//条件查询
	function getNetWork(value){
		alert(value);
	}


	//添加文章
	$(".newsAdd_btn").click(function(){
		var index = layui.layer.open({
			title:"添加二维码",
			type : 2,
			content : "/adai/gonetworkAdd",
			success : function(layero, index){
				layui.layer.tips('点击此处返回二维码列表', '.layui-layer-setwin .layui-layer-close', {
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
		 alert(ids);
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

    //第一个实例
    function renderTable(where){
        tableIns =  table.render({
            elem: '#orderList'
            ,url: '/adai/list'+where
            ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                ,groups: 5 //只显示 1 个连续页码
                ,limit:10
                ,page:1
                ,first: false //不显示首页
                ,last: false //不显示尾页

            }
            ,cols: [[ //表头
                {field: 'id', title: '序号', width:160, sort: true, fixed: 'left'}
                ,{field: 'name', title: '项目名', width:160}
                ,{field: 'word', title: '项目说明', width:160}
                ,{field: 'image', title: '二维码',templet:'#image', width:160}
                ,{field: 'url', title: '跳转网址', width:160}
                ,{field: 'status', title: '打开方式', templet: "#status", width:160}
                ,{field: 'createTime', title: '创建时间', templet: "#createTime", width:160}
                ,{field: '',title:'操作',fixed: 'right', align:'center', toolbar: '#barDemo', width:300}
            ]]
        })
    }

    var where = '';
    renderTable(where);

    //监听性别操作
    form.on('switch(changeStatus)', function(obj){
        var status = 0;
        if (obj.elem.checked){
            status =1;
        }
        $.post(
            "/adai/changeStutus",
            {id:this.value,status:status},
            function (data) {
            	if (data){
                    layer.tips("展示状态修改为" + status == 1?"展示":"隐藏");
                    renderTable(where);
                } else {
            		layer.alert("修改失败")
				}
            }, "json"
        );
    });



//监听工具条
table.on('tool(test)', function(obj){
    var data = obj.data;
    var id = data.id;
    var orderState = Number(data.orderState) + 1;
    if(obj.event === 'update'){
        commodityChange(id)
    } else if(obj.event === 'delete'){
        layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(){
            var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
            $.post(
                "/adai/delete",
                {id:id},
                function (data) {
                    top.layer.msg("删除成功！");
                    location.reload();
                }, "json"
            );
        });

    } else if(obj.event === 'download'){
        location.href = "/adai/download/"+ id;
    }else if(obj.event === 'details'){
        showOrderDetails(id);
    }
});
})
function showOrderDetails(id) {
    layer.open({
        type: 2,
        title: "订单详情",
        area: ['1080px', '750px'],
        content: ['toShowOrderDetailsByOrderId/' + id, 'no'],
    });
}

function commodityChange(id) {
    layer.open({
        type: 2,
        title: "订单详情",
        area: ['1080px', '750px'],
        content: ['gonetworkEdit/' + id, 'no'],
    });
}
