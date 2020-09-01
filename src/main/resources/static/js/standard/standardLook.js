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
    var standardid = sessionStorage.getItem("standardID");
    $.get(
        "/standard/getStandardByid",
        {standardid:standardid},
        function(data){
            var da = new Date(data.standardtime);
            Y = da.getFullYear() + '-';
            M = (da.getMonth()+1 < 10 ? '0'+(da.getMonth()+1) : da.getMonth()+1) + '-';
            D = (da.getDate()+1 < 10 ? '0'+da.getDate() : da.getDate());
            time=Y+M+D
            $(".title").append(data.standardtitle);
            $(".time").append("发布时间："+time+"    浏览次数："+data.looknum);
            $(".content").append(data.standardcontent);
        },
        "json"
    );


})