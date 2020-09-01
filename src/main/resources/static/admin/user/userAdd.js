layui.use(["form", "layer", "layedit", "jquery", "upload"], function () {
    var form = layui.form,
        $ = layui.jquery;

    //保存
    form.on("submit(save_button)", function (data) {
        //显示、审核状态
        var user = {
            username: $(".username").val(),
            password: $(".password").val(),
            roleName: $("input[name='roleName']:checked").val(),
            realName: $(".realName").val(),
            sex: $("input[name='sex']:checked").val(),
            mobilePhone: $(".mobilePhone").val(),
            email: $(".email").val(),
        };
        $.post(
            "/admin/user",
            user,
            function (data) {
                setTimeout(function () {
                    top.layer.close(index);
                    top.layer.msg("添加成功！");
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