layui.use(["form", "layer", "layedit", "jquery","upload"], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        upload = layui.upload,
        layedit = layui.layedit,
        $ = layui.jquery;
    //编辑器图片上传
    layedit.set({
        uploadImage: {
            url: "/tpcpolicies/upload" //接口url
            , type: "post" //默认post
        }
    });
    //创建一个编辑器
    var editIndex = layedit.build('news_content');

    //保存新闻
    form.on("submit(save_button)", function (data) {
        var news = {
            title: $(".title").val(),
            author: $(".author").val(),
            content: layedit.getContent(editIndex)
        };
        $.post(
            "/tpcpolicies/addPolicies",
            news,
            function (data) {
                setTimeout(function () {
                    top.layer.close(index);
                    top.layer.msg("提交审核成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                }, 1000);
            }, "json"
        );
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        return false;
    })
})