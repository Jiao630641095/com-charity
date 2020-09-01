layui.use(["form", "layer", "layedit", "jquery","upload"], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        upload = layui.upload,
        layedit = layui.layedit,
        $ = layui.jquery;
    //编辑器图片上传
    layedit.set({
        uploadImage: {
            url: "/tpcrental/upload" //接口url
            , type: "post" //默认post
        }
    });
    //创建一个编辑器
    var editIndex = layedit.build('news_content');

    //保存新闻
    form.on("submit(save_button)", function (data) {
        //显示、审核状态
        var rental = {
            roomnum: $(".roomnum").val(),
            floor: $("select[name='floor']").val(),
            passageway: $("select[name='passageway']").val(),
            face: $("select[name='face']").val(),
            rent: $(".rent").val(),
            area: $(".area").val(),
            info: layedit.getContent(editIndex),
            photo1:$("#photo1").attr("src"),
            photo2:$("#photo2").attr("src"),
            photo3:$("#photo3").attr("src"),
            photo4:$("#photo4").attr("src"),
            photo5:$("#photo5").attr("src")
        };
        $.post(
            "/tpcrental/addRental",
            rental,
            function (data) {
                setTimeout(function () {
                    top.layer.close(index);
                    top.layer.msg("房屋添加成功！");
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
    //图片上传
    //指定允许上传的文件类型
    var i=0;
    upload.render({
        elem: '#img'
        , url: '/tpcrental/upload'
        , accept: 'image/gif,image/jpeg,image/jpg,image/png,image/svg' //普通文件
        ,multiple: true
        ,number:5
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
          //  $('#imgUrl').attr('src', res.data.src); //图片链接（base64）
            i++;
            $('#demo2').append('<img src="'+ res.data.src+'" id="photo'+i+'" class="layui-upload-img" style="width: 104.47px;height: 104.74px">')
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