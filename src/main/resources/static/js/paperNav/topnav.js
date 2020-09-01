/**
 * Created by XWD on 2017/8/31.
 */
layui.config({
    base: "../css/"
}).use(['laytpl', 'layer','treegrid'], function () {
    var laytpl = layui.laytpl,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        treegrid = layui.treegrid;
    treegrid.config.render = function (viewid, data) {
        var view = document.getElementById(viewid).innerHTML;
        return laytpl(view).render(data) || '';
    };

    var id = 100001;
    var tree1;
    $.getJSON("/nav/getNavs",{},function (rows) {
        tree1=treegrid.createNew({
            elem: 'demo',
            view: 'view',
            data: { rows: rows },
            parentid: 'pid',
            singleSelect: true
        });
        tree1.build();
    });


    $('.layui-btn').on('click', function () {
        switch ($(this).attr('lay-filter')) {
            case 'add': {
                var row = tree1.getRow();
                if(row == null){
                    layer.open({
                        type: 1
                        ,id: 'layerDemo'//防止重复弹出
                        ,content: '<div style="padding: 20px 100px;">'+ "您还没有选中任何信息" +'</div>'
                        ,btn: '我知道了'
                        ,btnAlign: 'c' //按钮居中
                        ,shade: 0 //不显示遮罩
                        ,yes: function(){
                            layer.closeAll();
                        }
                    })
                }else{
                    var r = {
                        id:id,
                        pid: row.pid,
                        title: '请在这里编辑导航名，记得保存，否则无效',
                        url: '请在这里编辑导航路径，记得保存，否则无效'
                    };
                    id++;
                    tree1.insertNode(r, row.id);
                }
            } break;
            case 'addChild': {
                var row = tree1.getRow();
                if(row == null){
                    layer.open({
                        type: 1
                        ,id: 'layerDemo'//防止重复弹出
                        ,content: '<div style="padding: 20px 100px;">'+ "您还没有选中任何信息" +'</div>'
                        ,btn: '我知道了'
                        ,btnAlign: 'c' //按钮居中
                        ,shade: 0 //不显示遮罩
                        ,yes: function(){
                            layer.closeAll();
                        }
                    })
                }else{
                    if(row.pid != 0){
                        layer.open({
                            type: 1
                            ,id: 'layerDemo'//防止重复弹出
                            ,content: '<div style="padding: 20px 100px;">'+ "二级导航不能添加子导航！" +'</div>'
                            ,btn: '我知道了'
                            ,btnAlign: 'c' //按钮居中
                            ,shade: 0 //不显示遮罩
                            ,yes: function(){
                                layer.closeAll();
                            }
                        })
                    }else{
                        var rs = [{
                            id:id,
                            pid: row.id,
                            title: '请在这里编辑导航名，记得保存，否则无效',
                            url: '请在这里编辑导路径，记得保存，否则无效'
                        }];
                        id += 1;
                        tree1.insertChild(rs, row.id);
                    }

                }
            } break;
            case 'delete': {
                var row = tree1.getRow();

                if(row == null){
                    layer.open({
                        type: 1
                        ,id: 'layerDemo'//防止重复弹出
                        ,content: '<div style="padding: 20px 100px;">'+ "您还没有选中任何信息" +'</div>'
                        ,btn: '我知道了'
                        ,btnAlign: 'c' //按钮居中
                        ,shade: 0 //不显示遮罩
                        ,yes: function(){
                            layer.closeAll();
                        }
                    })
                }else{
                    if(row.pid == 0){//一级导航不能删除
                        layer.open({
                            type: 1
                            ,id: 'layerDemo'//防止重复弹出
                            ,content: '<div style="padding: 20px 100px;">'+ "一级导航不能删除" +'</div>'
                            ,btn: '我知道了'
                            ,btnAlign: 'c' //按钮居中
                            ,shade: 0 //不显示遮罩
                            ,yes: function(){
                                layer.closeAll();
                            }
                        })
                    }else{
                        layer.confirm('确定删除选中的信息？',{icon:3, title:'提示信息'},function(index) {
                            $.ajax({
                                url: "/nav/deleteNav",
                                type: "post",
                                data: {
                                    id: row.id,
                                },
                                dataType: "json",
                                success: function (data) {
                                    if (data) {
                                        top.layer.close(index);
                                        top.layer.msg("删除成功！");
                                        setTimeout(function () {
                                            layer.closeAll("iframe");
                                            //刷新父页面
                                            location.reload();
                                        }, 1000);
                                    } else {
                                        top.layer.close(index);
                                        top.layer.msg("删除失败！");
                                    }
                                }
                            });
                            var index = top.layer.msg('删除中，请稍候', {icon: 16, time: false, shade: 0.8});
                        })
                    }

                }
            } break;
            case 'edit': {
                var row = tree1.getRow();
                if(row == null){
                    layer.open({
                        type: 1
                        ,id: 'layerDemo'//防止重复弹出
                        ,content: '<div style="padding: 20px 100px;">'+ "您还没有选中任何信息" +'</div>'
                        ,btn: '我知道了'
                        ,btnAlign: 'c' //按钮居中
                        ,shade: 0 //不显示遮罩
                        ,yes: function(){
                            layer.closeAll();
                        }
                    })
                }else{

                    tree1.editNode(row.id);
                }

            } break;
            case 'endEdit': {  //保存
                var row = tree1.getRow();

                    $.ajax({
                        url:"/nav/saveOrUpateNavs",
                        type:"post",
                        data:{
                            id:row.id,
                            title:$("input[name='title']").val(),
                            url:$("input[name='url']").val(),
                            pid:row.pid
                        },
                        dataType:"json",
                        success:function(data){
                            if(data == 0){//  1 添加成功 0 失败
                                top.layer.close(index);
                                top.layer.msg("添加失败！");
                            }else if(data ==1){
                                top.layer.close(index);
                                top.layer.msg("添加成功！");
                                setTimeout(function(){
                                    layer.closeAll("iframe");
                                    //刷新父页面
                                   location.reload();
                                },1000);
                            }else if(data ==2){//2修改成功 3失败
                                top.layer.close(index);
                                top.layer.msg("修改成功！");
                                setTimeout(function(){
                                    layer.closeAll("iframe");
                                    //刷新父页面
                                    location.reload();
                                },1000);
                            }else if(data ==3){
                                top.layer.close(index);
                                top.layer.msg("修改失败！");
                            }else if(data ==4){//返回4 提示用户编辑新添加的导航
                                top.layer.close(index);
                                top.layer.msg("请先编辑新添加的导航！");
                            }
                        }
                    });
                    //弹出loading
                    var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});


            } break;
        }
    });
});