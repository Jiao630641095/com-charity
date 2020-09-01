layui.use(["form", "layer", "layedit", "jquery", "upload"], function () {
    var form = layui.form,
        $ = layui.jquery;
    //更新
    form.on("submit(put_button)", function (data) {
        var id = $(".id").val();
        //显示、审核状态
        var user = {
            username: $(".username").val(),
            password: $(".password").val(),
            realName: $(".realName").val(),
            sex: $("input[name='sex']:checked").val(),
            mobilePhone: $(".mobilePhone").val(),
            email: $(".email").val(),
            oldRoleId:$("#oldRoleId").val(),
            roleId:$("input[name='roleName']:checked").val()
        };
        $.ajax({
            type: "PUT",
            dataType: "json",
            url: "/admin/user/"+id,
            data: user,
            statusCode: {
                200: function () {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("修改成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    }, 1000);
                },
                404: function (data) {
                    setTimeout(function () {
                        layer.close(index);
                        layer.msg("修改失败！");
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
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        return false;
    })
})