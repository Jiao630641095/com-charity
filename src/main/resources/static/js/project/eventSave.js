layui.use(["form", "layer", "layedit", "jquery","upload","laydate"], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;

    //编辑器图片上传
    layedit.set({
        uploadImage: {
            url: "/admin/project/upload" //接口url
            , type: "post" //默认post
        }
    });
    //创建一个编辑器
    var editIndex = layedit.build('event_content');

    //保存
    form.on("submit(save_button)",function(){
        //显示、审核状态
        var event = {
            id:$(".id").val(),
            pid:$(".pid").val(),
            websiteUrl:$(".url").val(),
            title:$(".title").val(),
            illustration:$("#illustration").attr("src"),
            contents:layedit.getContent(editIndex)
        };
        $.post(
            "/admin/project/saveEvent",
            event,
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
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        return false;
    })
    //图片上传
    //指定允许上传的文件类型
    upload.render({
        elem: '#img'
        , url: '/admin/news/upload'
        , accept: 'image/gif,image/jpeg,image/jpg,image/png,image/svg' //普通文件
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {

            });
        }
        , done: function(res){
            //如果上传失败
            if(res.code > 0){
                return layer.msg('上传失败');
            }
            //上传成功
            $('#illustration').attr('src', res.data.src); //图片链接（base64）
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });
})