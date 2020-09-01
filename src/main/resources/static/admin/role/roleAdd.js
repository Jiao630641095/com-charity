layui.use(["form", "layer", "layedit", "jquery", "upload"], function () {
    var form = layui.form,
        $ = layui.jquery;
    //保存
    form.on("submit(save_button)", function (data) {

        //权限字符串
        var ids = "";
        $(".layui-input-block input").each(function () {
            if($(this).attr("checked")){
                ids += "," + $(this).val();
            }
        })
        ids = ids.substring(1);
        //显示、审核状态
        var role = {
            name: $(".name").val(),
            remark: $(".remark").val(),
            ids:ids
        };
        $.post(
            "/admin/role",
            role,
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

    $("body").on("click", ".layui-form-checkbox", function () {
        var id = $(this).prev().val();//当前ID
        var pid = $(this).prev().data("url");//当前父ID
        var isCheck = $(this).prev().attr("checked");//是否选中
        if (isCheck) {
            $(this).parent().children("input:first-child").attr("checked", false);
            $(this).removeClass("layui-form-checked")
        } else if (!isCheck) {
            $(this).parent().children("input:first-child").attr("checked", true);
        }
        isCheck = $(this).prev().attr("checked");//是否选中
        check(id, pid, isCheck);
    });
    //递归
    function check(id, pid, isCheck) {
        if (isCheck) {
            $(".layui-input-block input").each(function () {
                //大哥
                if ($(this).val() == pid) {
                    $(this).attr("checked", true);
                    $(this).next().addClass("layui-form-checked")
                    var prev_pid = $(this).data("url");//当前父ID
                    $(".layui-input-block input").each(function () {
                        if ($(this).val() == prev_pid) {
                            $(this).attr("checked", true);
                            $(this).next().addClass("layui-form-checked")
                        }
                    });
                }
                //小弟
                if ($(this).data("url") == id) {
                    $(this).attr("checked", true);
                    $(this).next().addClass("layui-form-checked")
                    var next_pid = $(this).val();//当前ID当做父ID
                    $(".layui-input-block input").each(function () {
                        if ($(this).data("url") == next_pid) {
                            $(this).attr("checked", true);
                            $(this).next().addClass("layui-form-checked")
                        }
                    });
                }
            })
        } else {
            //小弟
            $(".layui-input-block input").each(function () {
                if ($(this).data("url") == id) {
                    $(this).attr("checked", false);
                    $(this).next().removeClass("layui-form-checked")
                    var next_pid = $(this).val();//当前ID当做父ID
                    $(".layui-input-block input").each(function () {
                        if ($(this).data("url") == next_pid) {
                            $(this).attr("checked", false);
                            $(this).next().removeClass("layui-form-checked")
                        }
                    });
                }
            })
        }

    }
})