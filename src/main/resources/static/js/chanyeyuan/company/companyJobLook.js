/**
 * Created by XWD on 2018/3/7.
 */
//获取详细信息
layui.config({
    base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function() {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;
    var id = sessionStorage.getItem("tpccompanyID");
    $.get(
        "/tpccompany/getCompanyJobById",
        {id:id},
        function(data){
            var da = new Date(data.create_time);
            Y = da.getFullYear() + '-';
            M = (da.getMonth()+1 < 10 ? '0'+(da.getMonth()+1) : da.getMonth()+1) + '-';
            D = (da.getDate()+1 < 10 ? '0'+da.getDate() : da.getDate());
            time=Y+M+D
            $(".name").append("招聘职位："+data.name);
            $(".time").append("发布时间："+time);
            $(".size").append("招聘人数："+data.size);
            $(".education").append("学历要求："+(data.education==0?"不限":data.education==1?"初中":data.education==2?"高中":data.education==3?"大专":data.education==4?"本科":data.education==5?"硕士":"博士"));
            $(".hideSalary").append("薪资待遇："+(data.hideSalary==1?"面议":data.minSalary+"-"+data.maxSalary+"元/月"));
            $(".age").append("工作经验："+(data.age==0?"无":data.age+"年"));
            $(".info").append("详细信息："+data.info);
        },
        "json"
    );


})