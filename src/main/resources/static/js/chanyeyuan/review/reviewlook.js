/**
 * Created by XWD on 2017/9/26.
 */
//获取政策法规详细信息
layui.config({
    base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function() {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;
    var id = sessionStorage.getItem("tpcreviewID");
    $.get(
        "/tpcreview/getReviewByid",
        {id:id},
        function(data){
            var da = new Date(data.createTime);
            Y = da.getFullYear() + '-';
            M = (da.getMonth()+1 < 10 ? '0'+(da.getMonth()+1) : da.getMonth()+1) + '-';
            D = (da.getDate()+1 < 10 ? '0'+da.getDate() : da.getDate());
            time=Y+M+D
            $(".name").append(data.name);
            $(".time").append("申请人："+data.realName+"     申请时间："+time);
            $(".content").append("<h3>租金："+(data.score/100)+"元</h3><h3>身份证：</h3><img src='"+data.idCardUrl+"'/><h3><br>营业执照：</h3><img src='"+data.licenseUrl+"'/><br>备注："+data.comment);
        },
        "json"
    );


})