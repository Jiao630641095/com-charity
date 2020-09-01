/**
 * Created by XWD on 2017/9/7.
 */
layui.config({
    base : "js/"
}).use(['form','layer','jquery','upload'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        upload = layui.upload,
        $ = layui.jquery;

    var id = sessionStorage.getItem("bannerID");
    //回显数据
    //获取banner数据并进行渲染
    $.get("/banner/getbannerByid?id="+id,function(data){
        $("#div_img").append('<img src="'+data.imgurl+'" style="width: 500px;height: 300px;"/>');
        $("#addressurl").val(data.addressurl);
    });

    //监听提交
    form.on('submit(banner_edit)', function(data){
        var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
        $.post(
            "/banner/updateBanner",
            {id:id,addressurl:$("#addressurl").val()},
            function(data){
                if(data){
                    setTimeout(function(){
                        layer.close(index);
                        layer.msg("修改成功！");
                        parent.location.reload();
                    },1000);
                }else{
                    setTimeout(function(){
                        layer.close(index);
                        layer.msg("修改失败！");
                    },1000);
                }
            },"json"
        );
        return false;
    });

});
