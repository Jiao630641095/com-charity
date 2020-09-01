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
    var reviewData = '';
    $.get("/tpcreview/list", function(data){
        reviewData = data;
        reviewList();//执行加载数据的方法
    },"json")



    //查看
    $("body").on("click",".tpcreview_look",function(){  //查看
        var id = $(this).parent().parent().children("td:first-child").children("input:first-child").val();
        sessionStorage.setItem("tpcreviewID",id);
        var index = layui.layer.open({
            title : "申请详情",
            type : 2,
            content : "/tpcreview/tcpReviewLook",
            success : function(){
                layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                    tips: 3
                });
            }
        })
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function(){
            layui.layer.full(index);
        })
        layui.layer.full(index);
    })

    //审核
    $("body").on("click",".check_button",function(){
        var ids = "";
        $('input[name="checked"]:checked').each(function(){
            ids+=","+ $(this).val();
        });
        ids = ids.substring(1);
        //  alert(ids);
        if(ids != ""){
            layer.confirm('确定审核选中的信息？',{icon:3, title:'提示信息'},function(index){
                var index = layer.msg('审核中，请稍候',{icon: 16,time:false,shade:0.8});
                $.post(
                    "/tpcreview/updateState",
                    {ids:ids},
                    function(data){
                        if(data){
                            setTimeout(function(){
                                layer.close(index);
                                layer.msg("审核成功");
                                location.reload();
                            },1000);
                        }
                    },"json"
                );
            })
        }else{
            layer.msg("请选择需要审核的内容");
        }
    });
    //驳回
    $("body").on("click",".bohui_button",function(){
        var id = $('input[name="checked"]:checked').val();
        if(id != "" && id != null){
            sessionStorage.setItem("reviewID",id);
            layui.layer.open({
                title : "驳回理由",
                type : 2,
                area: ['1280px', '480px'], //宽高
                content : "/tpcreview/gobohui",
                success : function(){
                    layui.layer.tips('点此处关闭', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }
            })
        }else{
            layer.msg("您啥都没选呢");
        }

    })

    //条件查询
    $("body").on("click",".search_button",function(){
        var news = {
            realName:$(".realName").val(),
        }
        $.post("/tpcreview/getReviewListWhere",news, function(data){
            reviewData = data;
            reviewList();//执行加载数据的方法
        },"json")
    });
    //清空
    $("body").on("click",".stick_button",function(){
        $(".realName").val("");
        $.get("/tpcreview/list", function(data){
            reviewData = data;
            reviewList();//执行加载数据的方法
        },"json")
    });
    //全选
    form.on('checkbox(allChoose)', function(data){
        var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
        child.each(function(index, item){
            item.checked = data.elem.checked;
        });
        form.render('checkbox');
    });
    //渲染数据
    function reviewList(that){
        function renderDate(data,curr){
            var dataHtml = '';
            if(!that){
                currData = reviewData.concat().splice(curr*nums-nums, nums);
            }else{
                currData = that.concat().splice(curr*nums-nums, nums);
            }
            if(currData.length != 0){
                for(var i=0;i<currData.length;i++){
                    dataHtml +=
                        '<tr>'
                        +'<td align="center"><input type="checkbox" class="boxs" name="checked"  value="'+currData[i].id+'" lay-skin="primary" lay-filter="choose"></td>'
                        +'<td align="center">'+currData[i].name+'</td>'
                        +'<td align="center">'+(currData[i].score/100)+'元</td>'
                        +'<td align="center">'+currData[i].realName+'</td>'
                        +'<td align="center">'+currData[i].phone+'</td>'
                        +'<td align="center">'+(currData[i].state==1?"<span class='layui-badge layui-bg-green'>已审核</span>":currData[i].state==0?"<span class='layui-bg-orange'>待审核</span>":"<span class='layui-badge'>审核未通过</span>")+'</td>'
                        +'<td align="center">'+dateformat(currData[i].createTime)+'</td>'
                        +'<td align="center">'
                        +  '<a  shiro:hasPermission="tpcreview:view" class="layui-btn layui-btn-mini tpcreview_look"><i class="layui-icon">&#xe615;</i> 查看</a>'
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
            reviewData = that;
        }
        laypage.render({
            elem : "page",
            count: reviewData.length,
            limit:13,
            //pages : Math.ceil(reviewData.length/nums),
            jump : function(obj){
                $(".review_content").html(renderDate(reviewData,obj.curr));
                $('.review_list thead input[type="checkbox"]').prop("checked",false);
                form.render();
            }
        })
    }
    //格式化日期
    function dateformat(value){
        if(value == null){
            return "暂未修改";
        }
        var date = new Date(value);
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = (date.getDate()+1 < 10 ? '0'+date.getDate() : date.getDate()) + ' ';
        h = (date.getHours()+1 < 10 ? '0'+date.getHours() : date.getHours()) + ':';
        m = (date.getMinutes()+1 < 10 ? '0'+date.getMinutes() : date.getMinutes()) + ':';
        s = (date.getSeconds()+1 < 10 ? '0'+date.getSeconds() : date.getSeconds());
        dateTime=Y+M+D+h+m+s;
        return dateTime;
    }
    //绑定多个时间
    lay('.search_input_time').each(function(){
        laydate.render({
            elem: this
            ,trigger: 'click'
        });
    });

})
