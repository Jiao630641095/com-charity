layui.use(["layer", "jquery", "laypage"], function () {
    var layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;

    //总页数
    var pages = $("#pages").val();
    //当前页
    var pageNum = $("#pageNum").val();
    //连续显示分页数
    var groups = 6;
    laypage.render({
        elem: "page"
        , count: pages //数据总数，从服务端得到
        , curr: pageNum
        , jump: function (obj, first) {
            //obj包含了当前分页的所有参数，比如：
            console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
            console.log(obj.limit); //得到每页显示的条数

            //首次不执行
            if (!first) {
                window.location.href = "/admin/role/?pageNum=" + obj.curr;
            }
        }
    });
    //添加角色
    $("body").on("click", ".add_button", function () {
        var url = $(this).data('url');  //请求地址
        var index = layui.layer.open({
            title: "添加角色",
            type: 2,
            content: url,
            success: function () {
                layui.layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
                    tips: 3
                });
            }
        })
        $(window).resize(function () {
            layui.layer.full(index);
        })
        layui.layer.full(index);
    });
    //修改角色
    $("body").on("click", ".edit_button", function () {
        var id = $(this).parent().parent().children("td:first-child").children("input:first-child").val();
        var url = $(this).data('url');  //请求地址
        var index = layui.layer.open({
            title: "修改角色",
            type: 2,
            content: url + "/" + id,
            success: function () {
                layui.layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
                    tips: 3
                });
            }
        })
        $(window).resize(function () {
            layui.layer.full(index);
        })
        layui.layer.full(index);
    });
    //授权
    $("body").on("click", ".permission_button", function () {
        var id = $(this).parent().parent().children("td:first-child").children("input:first-child").val();
        var url = $(this).data('url');  //请求地址
        var index = layui.layer.open({
            title: "角色授权",
            type: 2,
            content: url  + id,
            success: function () {
                layui.layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
                    tips: 3
                });
            }
        })
        $(window).resize(function () {
            layui.layer.full(index);
        })
        layui.layer.full(index);
    });
    //删除用户
    //此处请求后台程序，下方是成功后的前台处理……
    $("body").on("click", ".delete_button", function () {
        var id = $(this).parent().parent().children("td:first-child").children("input:first-child").val();
        var url = $(this).data('url');  //请求地址
        layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function() {
            var index = layer.msg('删除中，请稍候', {icon: 16, time: false, shade: 0.8});
            $.ajax({
                type: "DELETE",
                dataType: "json",
                url: url + id,
                data: {
                    "timestamp": new Date().getTime()
                },
                statusCode: {
                    200: function (data) {
                        setTimeout(function () {
                            layer.close(index);
                            layer.msg("删除成功！");
                            location.reload();
                        }, 1000);
                    },
                    404: function (data) {
                        setTimeout(function () {
                            layer.close(index);
                            layer.msg("删除失败！");
                            location.reload();
                        }, 1000);
                    },
                    500: function () {
                        setTimeout(function () {
                            layer.close(index);
                            layer.msg("系统错误！");
                            location.reload();
                        }, 1000);
                    }
                }
            });
        });
    })
})