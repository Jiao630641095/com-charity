//一般直接写在一个js文件中
var $,tab;
layui.config({
    base: "/static/js/"
}).use(["layer", 'form',"jquery", "element"], function () {
    var $ = layui.jquery,
        form = layui.form,
        layer = layui.layer,
        element = layui.element;   //Tab的切换功能，切换事件监听等，需要依赖element模块;
       // 新增一个Tab项
        $("body").on("click", ".layui-this", function () {
            var id = $(this).children("a").data('id');
            var href = $(this).children("a").data('href');
            var title = $(this).children("a").children("cite").text();
            if(href != null && href !=''){
                addTab(title,href,id);
            }
        });

    //删除tab
    $("body").on("click",".top_tab li i.layui-tab-close",function(){
        element.tabDelete("bodyTab",$(this).parent("li").attr("lay-id")).init();
    });
    //刷新当前页面.
    $(".refresh").click(function(){//此处添加禁止连续点击刷新一是为了降低服务器压力，另外一个就是为了防止超快点击造成chrome本身的一些js文件的报错(不过貌似这个问题还是存在，不过概率小了很多)
        if($(this).hasClass("refreshThis")){
            $(this).removeClass("refreshThis");
            var url = $(".layui-show").find("iframe").attr("src");
            $(".layui-show").find("iframe").attr("src",url);
            setTimeout(function(){
                $(".refresh").addClass("refreshThis");
            },2000)
        }else{
            layer.msg("您点击的速度超过了服务器的响应速度，还是等两秒再刷新吧！");
        }

    });
    //关闭其他
    $(".closePageOther").click(function(){
        if($("#top_tabs li").length>2){
            var id = $('.layui-tab-title li.layui-this').attr('lay-id');
            $('.layui-tab-title li').each(function () {
                var id1 = $(this).attr('lay-id');
                if (id1 != id && id1 !== undefined && id1 != '') {
                    element.tabDelete("bodyTab", id1);
                }
            });
        }else{
            layer.msg('没有可关闭的窗口',{icon: 0});
        }

    });
    //关闭全部
    $(".closePageAll").click(function(){
        if($('.layui-tab-title li').length>1){
            $('.layui-tab-title li i.layui-tab-close').trigger('click');

        }else{
            layer.msg('没有可关闭的窗口',{icon: 0});
        }
    });

    //登出
    $("#signOut").click(function(){
        layer.confirm('确定要退出?', {icon: 3, title:'提示'}, function(){
            location.href="/admin/logout"
        });
    });

    //修改密码
    $("#changePwd").click(function(){
        var title=$(this).find("cite").text();
        var href ="/admin/changePwd";
        var id = new Date().getTime();
        addTab(title,href,id);
    });

    //个人资料
    $("#userInfo").click(function(){
        var title=$(this).find("cite").text();
        var href ="/admin/user/userinfo";
        var id = new Date().getTime();
        addTab(title,href,id);
    });

    //隐藏左侧导航
    $(".hideMenu").click(function(){
        var sideWidth = $('#larry-side').width();
        if(sideWidth === 200) {
            $('#larry-body').animate({
                left: '0'
            });
            $('#larry-footer').animate({
                left: '0'
            });
            $('#larry-side').animate({
                width: '0'
            });
        } else {
            $('#larry-body').animate({
                left: '200px'
            });
            $('#larry-footer').animate({
                left: '200px'
            });
            $('#larry-side').animate({
                width: '200px'
            });
        }
    });
    //全屏
    $('.admin-side-full').on('click', function () {
        var docElm = document.documentElement;
        //W3C
        if (docElm.requestFullscreen) {
            docElm.requestFullscreen();
        }
        //FireFox
        else if (docElm.mozRequestFullScreen) {
            docElm.mozRequestFullScreen();
        }
        //Chrome等
        else if (docElm.webkitRequestFullScreen) {
            docElm.webkitRequestFullScreen();
        }
        //IE11
        else if (elem.msRequestFullscreen) {
            elem.msRequestFullscreen();
        }
        layer.msg('按Esc即可退出全屏');
    });

    //添加新tab   公共方法
    function  addTab(title,href,id){
        var tabIndex = -1;
        $(".layui-tab-title.top_tab li").each(function(){  //判断tab 是否被打开
            if($(this).find("cite").text() == title){
                tabIndex=tabIndex+1;
            }
        });
        if(tabIndex<0){ //没打开就新添加一个tab
            element.tabAdd('bodyTab', {
                title: '<cite>'+title+'</cite>'+'<i class="layui-icon layui-unselect layui-tab-close">&#x1006;</i>'
                , content: '<iframe src="' +href+ '"></iframe>'
                , id: id //实际使用一般是规定好的id，这里以时间戳模拟下
            });
            $("#top_tabs li").removeClass("layui-this");
            $("#top_tabs li:last-child").addClass("layui-this");
            $(".clildFrame div").removeClass("layui-show");
            $(".clildFrame div:last-child").addClass("layui-show");
        }else { //如果打开了 就切换到打开的tab
            var layId;
            $(".layui-tab-title.top_tab li").each(function(){
                if($(this).find("cite").text() == title){
                    layId = $(this).attr("lay-id");
                }
            });
            element.tabChange('bodyTab', layId);
        }
    }

    //更换皮肤
    function skins(){
        var skin = window.sessionStorage.getItem("skin");
        if(skin){  //如果更换过皮肤
            if(window.sessionStorage.getItem("skinValue") != "自定义"){
                $("body").addClass(window.sessionStorage.getItem("skin"));
            }else{
                $(".layui-layout-admin .layui-header").css("background-color",skin.split(',')[0]);
                $(".layui-bg-black").css("background-color",skin.split(',')[1]);
                $(".hideMenu").css("background-color",skin.split(',')[2]);
            }
        }
    }
    skins();
    $(".changeSkin").click(function(){
        layer.open({
            title : "更换皮肤",
            area : ["310px","280px"],
            type : "1",
            content : '<div class="skins_box">'+
            '<form class="layui-form">'+
            '<div class="layui-form-item">'+
            '<input type="radio" name="skin" value="默认" title="默认" lay-filter="default" checked="">'+
            '<input type="radio" name="skin" value="橙色" title="橙色" lay-filter="orange">'+
            '<input type="radio" name="skin" value="蓝色" title="蓝色" lay-filter="blue">'+
            '<input type="radio" name="skin" value="自定义" title="自定义" lay-filter="custom">'+
            '<div class="skinCustom">'+
            '<input type="text" class="layui-input topColor" name="topSkin" placeholder="顶部颜色" />'+
            '<input type="text" class="layui-input leftColor" name="leftSkin" placeholder="左侧颜色" />'+
            '<input type="text" class="layui-input menuColor" name="btnSkin" placeholder="顶部菜单按钮" />'+
            '</div>'+
            '</div>'+
            '<div class="layui-form-item skinBtn">'+
            '<a href="javascript:;" class="layui-btn layui-btn-small layui-btn-normal" lay-submit="" lay-filter="changeSkin">确定更换</a>'+
            '<a href="javascript:;" class="layui-btn layui-btn-small layui-btn-primary" lay-submit="" lay-filter="noChangeSkin">我再想想</a>'+
            '</div>'+
            '</form>'+
            '</div>',
            success : function(index, layero){
                var skin = window.sessionStorage.getItem("skin");
                if(window.sessionStorage.getItem("skinValue")){
                    $(".skins_box input[value="+window.sessionStorage.getItem("skinValue")+"]").attr("checked","checked");
                };
                if($(".skins_box input[value=自定义]").attr("checked")){
                    $(".skinCustom").css("visibility","inherit");
                    $(".topColor").val(skin.split(',')[0]);
                    $(".leftColor").val(skin.split(',')[1]);
                    $(".menuColor").val(skin.split(',')[2]);
                };
                form.render();
                $(".skins_box").removeClass("layui-hide");
                $(".skins_box .layui-form-radio").on("click",function(){
                    var skinColor;
                    if($(this).find("span").text() == "橙色"){
                        skinColor = "orange";
                    }else if($(this).find("span").text() == "蓝色"){
                        skinColor = "blue";
                    }else if($(this).find("span").text() == "默认"){
                        skinColor = "";
                    }
                    if($(this).find("span").text() != "自定义"){
                        $(".topColor,.leftColor,.menuColor").val('');
                        $("body").removeAttr("class").addClass("main_body "+skinColor+"");
                        $(".skinCustom").removeAttr("style");
                        $(".layui-bg-black,.hideMenu,.layui-layout-admin .layui-header").removeAttr("style");
                    }else{
                        $(".skinCustom").css("visibility","inherit");
                    }
                })
                var skinStr,skinColor;
                $(".topColor").blur(function(){
                    $(".layui-layout-admin .layui-header").css("background-color",$(this).val());
                })
                $(".leftColor").blur(function(){
                    $(".layui-bg-black").css("background-color",$(this).val());
                })
                $(".menuColor").blur(function(){
                    $(".hideMenu").css("background-color",$(this).val());
                })

                form.on("submit(changeSkin)",function(data){
                    if(data.field.skin != "自定义"){
                        if(data.field.skin == "橙色"){
                            skinColor = "orange";
                        }else if(data.field.skin == "蓝色"){
                            skinColor = "blue";
                        }else if(data.field.skin == "默认"){
                            skinColor = "";
                        }
                        window.sessionStorage.setItem("skin",skinColor);
                    }else{
                        skinStr = $(".topColor").val()+','+$(".leftColor").val()+','+$(".menuColor").val();
                        window.sessionStorage.setItem("skin",skinStr);
                        $("body").removeAttr("class").addClass("main_body");
                    }
                    window.sessionStorage.setItem("skinValue",data.field.skin);
                    layer.closeAll("page");
                });
                form.on("submit(noChangeSkin)",function(){
                    $("body").removeAttr("class").addClass("main_body "+window.sessionStorage.getItem("skin")+"");
                    $(".layui-bg-black,.hideMenu,.layui-layout-admin .layui-header").removeAttr("style");
                    skins();
                    layer.closeAll("page");
                });
            },
            cancel : function(){
                $("body").removeAttr("class").addClass("main_body "+window.sessionStorage.getItem("skin")+"");
                $(".layui-bg-black,.hideMenu,.layui-layout-admin .layui-header").removeAttr("style");
                skins();
            }
        })
    })

});

