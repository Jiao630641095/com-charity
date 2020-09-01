layui.use(["form", "layer", "jquery", "laypage", "laydate"], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        laydate = layui.laydate,
        $ = layui.jquery;

    //总页数
    var pages = $("#pages").val();
    //当前页
    var pageNum = $("#pageNum").val();
    //连续显示分页数
    var groups = 6;
    laypage.render({
        elem: "page"
        , count: pages //数据总数，从服务端得到
        ,curr: pageNum
        , jump: function (obj, first) {
            //obj包含了当前分页的所有参数，比如：
            console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
            console.log(obj.limit); //得到每页显示的条数

            //首次不执行
            if (!first) {
               window.location.href = "/admin/log/?pageNum="+obj.curr;
            }
        }
    });
})