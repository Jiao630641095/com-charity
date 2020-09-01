layui.use(["form", "layer", "jquery", "laypage", "laydate"], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;
    //加载页面数据
    var newsData = '';
    var pid = $(".pid").val();
    var url = $(".url").val();
    $.get("/admin/project/getEventList?url=" + url + "&&id=" + pid, function (data) {
        newsData = data;
        newsList();//执行加载数据的方法
    }, "json");
    $("body").on("click", ".edit_button", function () {
        var url = $(this).data('url');//请求地址
        var index = layui.layer.open({
            title: "编辑事件",
            type: 2,
            content: url,
        })
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        })
        layui.layer.full(index);
    });
    //编辑事件
    $("body").on("click", ".edit_button", function () {
        var url = $(this).data('url');//请求地址
        var index = layui.layer.open({
            title: "编辑事件",
            type: 2,
            content: url,
            success: function (layero, index) {
                layui.layer.tips('点击此处返回事件列表', '.layui-layer-setwin .layui-layer-close', {
                    tips: 3
                });
            }
        })
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        })
        layui.layer.full(index);
    });
    //是否展示
    form.on('switch(show)', function(data){
        var id = $(this).parent().parent().children("td:first-child").children("input:first-child").val();
        var websiteUrl = $(this).data("url");
        var isShow = ""
        if(!$(this).attr("checked")){
            isShow = "1";
        }
        $.post(
            "/admin/project/saveEvent",
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
    //单个删除
    $("body").on("click", ".delete_button", function () {
        var url = $(this).data('url');  //请求地址
        layer.confirm('确定删除此信息？', {icon: 3, title: '提示信息'}, function () {
            var index = layer.msg('删除中，请稍候', {icon: 16, time: false, shade: 0.8});
            $.get(
                url,
                function (data) {
                    if (data) {
                        location.reload();
                        layer.close(index);
                    }
                }, "json"
            );
        });
    });
    //渲染数据
    function newsList(that) {
        function renderDate(data, curr) {
            var dataHtml = '';
            if (!that) {
                currData = newsData.concat().splice(curr * nums - nums, nums);
            } else {
                currData = that.concat().splice(curr * nums - nums, nums);
            }
            if (currData.length != 0) {
                for (var i = 0; i < currData.length; i++) {
                    dataHtml +=
                        '<tr>'
                        + '<td align="center"><input type="checkbox" class="boxs" name="checked"  value="' + currData[i].id + '" lay-skin="primary" lay-filter="choose"></td>'
                        + '<td align="center">' + currData[i].title + '</td>'
                        + '<td align="center"><input type="checkbox" name="show" data-url="' + currData[i].websiteUrl + '" lay-skin="switch" lay-text="是|否" lay-filter="show"' + (currData[i].isShow == 1 ? "checked" : "") + '></td>'
                        + '<td align="center">' + dateformat(currData[i].createTime) + '</td>'
                        + '<td align="center">' + dateformat(currData[i].modifyTime) + '</td>'
                        + '<td align="center">'
                        + '<a shiro:hasPermission="event:update" class="layui-btn layui-btn-mini edit_button" data-url="/admin/project/updateEvent?url=' + currData[i].websiteUrl + '&&id=' + currData[i].id + '"><i class="iconfont icon-edit"></i> 编辑</a>'
                        + '<a shiro:hasPermission="event:delete" class="layui-btn layui-btn-danger layui-btn-mini delete_button" data-url="/admin/project/deleteEvent?url=' + currData[i].websiteUrl + '&&id=' + currData[i].id + '"><i class="layui-icon delete">&#xe640;</i>删除</a>'
                        + '</td>'
                        + '</tr>'
                }
            } else {
                dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
            }
            return dataHtml;
        }

        //分页
        var nums = 13; //每页出现的数据量
        if (that) {
            newsData = that;
        }
        laypage.render({
            elem: "page",
            count: newsData.length,
            pages: Math.ceil(newsData.length / nums),
            jump: function (obj) {
                $(".event_content").html(renderDate(newsData, obj.curr));
                $('.news_list thead input[type="checkbox"]').prop("checked", false);
                form.render();
            }
        })
    }

    //格式化日期
    function dateformat(value) {
        if (value == null) {
            return "暂未修改";
        }
        var date = new Date(value);
        Y = date.getFullYear() + '-';
        M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        D = (date.getDate() + 1 < 10 ? '0' + date.getDate() : date.getDate()) + ' ';
        h = (date.getHours() + 1 < 10 ? '0' + date.getHours() : date.getHours()) + ':';
        m = (date.getMinutes() + 1 < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
        s = (date.getSeconds() + 1 < 10 ? '0' + date.getSeconds() : date.getSeconds());
        dateTime = Y + M + D + h + m + s;
        return dateTime;
    }
})
