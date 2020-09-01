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
    var policiesid = sessionStorage.getItem("policiesID");
    $.get(
        "/policies/getPoliciesByid",
        {policiesid:policiesid},
        function(data){
            var da = new Date(data.policiestime);
            Y = da.getFullYear() + '-';
            M = (da.getMonth()+1 < 10 ? '0'+(da.getMonth()+1) : da.getMonth()+1) + '-';
            D = (da.getDate()+1 < 10 ? '0'+da.getDate() : da.getDate());
            time=Y+M+D
            $(".title").append(data.policiestitle);
            $(".time").append("发布时间："+time+"    浏览次数："+data.looknum);
            $(".content").append(data.policiescontent);
        },
        "json"
    );


})