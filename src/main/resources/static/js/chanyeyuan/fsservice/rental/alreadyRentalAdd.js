layui.use(["form", "layer", "jquery","laydate"], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laydate = layui.laydate,
        $ = layui.jquery;

    //保存记录
    form.on("submit(save_button)", function (data) {
        var alreadyRental = {
            id:0,
            rid: $("select[name='rid']").val(),
            roomnum:$("#roomnum option:selected").text(),
            qyname:$(".qyname").val(),
            name:$(".name").val(),
            phone:$(".phone").val(),
            paytype:$("select[name='paytype']").val(),
            create_time:$(".create_time").val(),
            pay_time: $(".pay_time").val(),
            end_time:$(".end_time").val()
        };
        $.post("/tpcrental/addAlreadyRental",alreadyRental,function (data) {
                if(data){
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("添加记录成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    }, 1000);
                }else{
                    top.layer.close(index);
                    top.layer.msg("添加记录失败！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                }

            }, "json"
        );
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        return false;
    })
    //绑定多个时间
    lay('.search_input_time').each(function(){
        laydate.render({
            elem: this
            ,trigger: 'click'
        });
    });
})