/**
 * Created by XWD on 2017/9/26.
 */
//获取政策详细信息
layui.config({
    base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function() {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;
    var id = sessionStorage.getItem("tpcrentalID");
    $.get(
        "/tpcrental/getRentalByid",
        {id:id},
        function(data){
            var da = new Date(data.createTime);
            Y = da.getFullYear() + '-';
            M = (da.getMonth()+1 < 10 ? '0'+(da.getMonth()+1) : da.getMonth()+1) + '-';
            D = (da.getDate()+1 < 10 ? '0'+da.getDate() : da.getDate());
            time=Y+M+D
            $(".roomnum").append(data.roomnum);
            $(".time").append("发布时间："+time+"    浏览次数："+data.looknum+"次");
            $(".floor").append("楼层："+data.floor);
            $(".passageway").append("通道："+data.passageway+"通道");
            $(".rent").append("租金："+data.rent+"元/㎡/天");
            $(".area").append("面积：约"+data.area+"㎡");
            $(".face").append("朝向："+data.face);
            $(".info").append("详细信息："+data.info);
        },
        "json"
    );


})