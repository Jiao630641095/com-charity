/**
 * Created by XWD on 2017/9/25.
 */
layui.config({
    base : "js/"
}).use(['form','layer','jquery','laypage','upload'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        $ = layui.jquery;

    //加载页面数据
    var standardData = '';
    $.get("/standard/list", function(data){
        var newArray = [];
        standardData = data;
        if(window.sessionStorage.getItem("addStandard")){
            var addStandard = window.sessionStorage.getItem("addStandard");
            standardData = JSON.parse(addStandard).concat(standardData);
        }

        //执行加载数据的方法
        standardList();
    })

    //添加法规
    $(".standardAdd_btn").click(function(){
        var index = layui.layer.open({
            title : "添加法规",
            type : 2,
            content : "/standard/standardAdd",
            success : function(layero, index){
                layui.layer.tips('点击此处返回法规列表', '.layui-layer-setwin .layui-layer-close', {
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
                    "/standard/deleteStandard",
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
            layer.msg("请选择需要删除的法规");
        }
    })

    //全选
    form.on('checkbox(allChoose)', function(data){
        var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="state"])');
        child.each(function(index, item){
            item.checked = data.elem.checked;
        });
        form.render('checkbox');
    });

    //通过判断法规是否全部选中来确定全选按钮是否选中
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

    //是否展示
    form.on('switch(isShow)', function(data){
        var standardid = $(this).parent().parent().children("td:first-child").children("input:first-child").val();
        var isShow = "";
        if(!$(this).attr("checked")){
            isShow = "1";
        }
        var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
        $.post(
            "/standard/isShow",
            {standardid:standardid,state:isShow},
            function(data){
                if(data){
                    setTimeout(function(){
                        layer.close(index);
                        layer.msg("展示状态修改成功！");
                        location.reload();
                    },1000);
                }
            },"json"
        );



    })

    //操作
    $("body").on("click",".standard_edit",function(){  //编辑
        var id = $(this).parent().parent().children("td:first-child").children("input:first-child").val();
        sessionStorage.setItem("standardID",id);
        var index = layui.layer.open({
            title : "编辑法规",
            type : 2,
            content : "/standard/standardEdit",
            success : function(layero, index){
                layui.layer.tips('点击此处返回法规列表', '.layui-layer-setwin .layui-layer-close', {
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
    $("body").on("click",".standard_look",function(){  //查看
        var id = $(this).parent().parent().children("td:first-child").children("input:first-child").val();
        sessionStorage.setItem("standardID",id);
        var index = layui.layer.open({
            title : "查看政策法规",
            type : 2,
            content : "/standard/standardLook",
            success : function(layero, index){
                layui.layer.tips('点击此处返回法规列表', '.layui-layer-setwin .layui-layer-close', {
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
    $("body").on("click",".standard_del",function(){  //删除
        var id = $(this).parent().parent().children("td:first-child").children("input:first-child").val();
        layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(){
            var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
            $.post(
                "/standard/deleteStandard",
                {ids:id},
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
        });
    })

    function standardList(that){
        //渲染数据
        function renderDate(data,curr){
            var dataHtml = '';
            if(!that){
                currData = standardData.concat().splice(curr*nums-nums, nums);
            }else{
                currData = that.concat().splice(curr*nums-nums, nums);
            }
            if(currData.length != 0){
                for(var i=0;i<currData.length;i++){
                    var date = new Date(currData[i].standardtime);
                    Y = date.getFullYear() + '-';
                    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
                    D = (date.getDate()+1 < 10 ? '0'+date.getDate() : date.getDate()) + ' ';
                    h = (date.getHours()+1 < 10 ? '0'+date.getHours() : date.getHours()) + ':';
                    m = (date.getMinutes()+1 < 10 ? '0'+date.getMinutes() : date.getMinutes()) + ':';
                    s = (date.getSeconds()+1 < 10 ? '0'+date.getSeconds() : date.getSeconds());
                    time=Y+M+D+h+m+s;
                    dataHtml += '<tr>'
                        +'<td><input type="checkbox" class="boxs" name="checked"  value="'+currData[i].standardid+'" lay-skin="primary" lay-filter="choose"/></td>'
                        +'<td>'+currData[i].standardtitle+'</td>'
                        +'<td><input type="checkbox" name="state" lay-skin="switch" lay-text="是|否" lay-filter="isShow"'+(currData[i].state?"checked":"")+'/></td>'
                        +'<td>'+time+'</td>'
                        +'<td>'
                        +  '<a shiro:hasPermission="standard:view" class="layui-btn layui-btn-mini standard_look"><i class="layui-icon">&#xe615;</i> 查看</a>'
                        +  '<a shiro:hasPermission="standard:update" class="layui-btn layui-btn-mini standard_edit"><i class="iconfont icon-edit"></i> 编辑</a>'
                        +  '<a shiro:hasPermission="standard:delete" class="layui-btn layui-btn-danger layui-btn-mini standard_del"><i class="layui-icon delete">&#xe640;</i> 删除</a>'
                        +'</td>'
                        +'</tr>';
                }
            }else{
                dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
            }
            return dataHtml;
        }


        //分页
        var nums = 10; //每页出现的数据量
        if(that){
            standardData = that;
        }
        laypage.render({
            elem : "page",
            count: standardData.length,
            limit : 10,
            jump : function(obj){
                $(".standard_content").html(renderDate(standardData,obj.curr));
                $('.standard_list thead input[type="checkbox"]').prop("checked",false);
                form.render();
            }
        })
    }



})

