/**
 * Created by XWD on 2018/1/30.
 */
layui.use(["form","layer","jquery","laypage","laydate"],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        laydate = layui.laydate,
        $ = layui.jquery;
    //加载页面数据
    var rentalData = '';
    $.get("/tpcrental/getExpireRentalList", function(data){
        rentalData = data;
        reviewList();//执行加载数据的方法
    },"json")


    //短信提醒
    $("body").on("click",".send_sms",function(){
        var expireRental = {
            roomnum : $("#roomnum").text(),
            name : $("#name").text(),
            phone : $("#phone").text(),
            pay_date : $("#pay_date").text()
        };
        layer.msg("正在开发中……");
       // if(expireRental.phone != "" && expireRental.phone != null){
       //     $.post(
       //         "/tpcrental/sendsms",
       //         expireRental,
       //         function(data){
       //             if(data){
       //                 setTimeout(function(){
       //                     layer.msg("发送成功!");
       //                 },1000);
       //             }
       //         },"json"
       //     );
       // }else{
       //     layer.msg("没有可发送短信的手机号！");
       // }
    });
    //已支付
    $("body").on("click",".update_paydate",function(){
        var id = $(this).parent().parent().children("td:first-child").children("input:first-child").val();
        var expireRental = {
            id : id,
            pay_time: $("#pay_date").text()
        };
        layer.confirm('确定收到付款？',{icon:3, title:'提示信息'},function(index){
            var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
            $.post(
                "/tpcrental/updatePayDate",
                expireRental,
                function(data){
                    if(data){
                        setTimeout(function(){
                            layer.close(index);
                            layer.msg("更新成功!");
                            location.reload();
                        },1000);
                    }
                },"json"
            );
        })

    });


    //条件查询
    $("body").on("click",".search_button",function(){
        var news = {
            name:$(".name").val(),
        }
        $.post("/tpcrental/getExpireRentalListWhere",news, function(data){
            rentalData = data;
            reviewList();//执行加载数据的方法
        },"json")
    });
    //清空
    $("body").on("click",".stick_button",function(){
        $(".name").val("");
        $.get("/tpcrental/getExpireRentalList", function(data){
            rentalData = data;
            reviewList();//执行加载数据的方法
        },"json")
    });
    //渲染数据
    function reviewList(that){
        function renderDate(data,curr){
            var dataHtml = '';
            if(!that){
                currData = rentalData.concat().splice(curr*nums-nums, nums);
            }else{
                currData = that.concat().splice(curr*nums-nums, nums);
            }
            if(currData.length != 0){
                for(var i=0;i<currData.length;i++){
                    dataHtml +=
                        '<tr>'
                        +'<td align="center"><input type="checkbox" class="boxs" name="checked"  value="'+currData[i].id+'" lay-skin="primary" lay-filter="choose"></td>'
                        +'<td align="center">'+currData[i].qyname+'</td>'
                        +'<td align="center" id="roomnum">'+currData[i].roomnum+'</td>'
                        +'<td align="center" id="name">'+currData[i].name+'</td>'
                        +'<td align="center" id="phone">'+currData[i].phone+'</td>'
                        +'<td align="center">'+(currData[i].paytype==3?"季度付":currData[i].paytype==6?"半年付":currData[i].paytype==12?"年付":"")+'</td>'
                        +'<td align="center" id="pay_date">'+currData[i].pay_date+'</td>'
                        +'<td align="center">'
                        +  '<a class="layui-btn layui-btn-mini update_paydate">已支付</a>'
                        +  '<a class="layui-btn layui-btn-mini send_sms">短信提醒</a>'
                        +'</td>'
                        +'</tr>'
                }
            }else{
                dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
            }
            return dataHtml;
        }

        //分页
        var nums = 13; //每页出现的数据量
        if(that){
            rentalData = that;
        }
        laypage.render({
            elem : "page",
            count: rentalData.length,
            limit:13,
            //pages : Math.ceil(rentalData.length/nums),
            jump : function(obj){
                $(".review_content").html(renderDate(rentalData,obj.curr));
                $('.review_list thead input[type="checkbox"]').prop("checked",false);
                form.render();
            }
        })
    }

})
