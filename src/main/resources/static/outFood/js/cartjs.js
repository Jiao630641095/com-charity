//购物车列表
function getCartList() {
    $.getJSON("/canteen/getCart.action",function (data) {
        $('.popover-body').empty();
        if (data.code == 4000){
            if(data.cart != null){
                var list = data.cart;
                var div = '<div class="mui-scroll-wrapper"><div class="popover-scroll mui-scroll">';
                var sum = 0;
                var amt = 0;
                for (key in list){
                    var name = '';
                    var price =0;
                    var cid = key;
                    var num = list[key];
                    $.each(commodityListMsg, function (index, val) {
                        if (val.id == cid ){
                            name = val.name;
                            price = val.price;
                            return false;
                        }
                    })
                    sum += num;
                    amt += Number(num*price);
                    amt = parseFloat(amt);
                    div +=['<div class="popover-item mui-text-center mui-clearfix">',
                        ' <div class="mui-pull-left mui-col-xs-4">'+name+'</div>',
                        '<div class="mui-pull-left mui-col-xs-2">￥'+price+'</div>',
                        '<div class="mui-pull-left mui-col-xs-2"><i class="foodNum">'+num+'</i>份</div>',
                        '<div class="mui-pull-left mui-col-xs-2 reduceNum mui-icon mui-icon-minus" style="cursor: pointer;" atte="'+cid+'"></div>',
                        '<div class="mui-pull-right mui-col-xs-2 sumNum mui-icon mui-icon-plus" style="cursor: pointer;"  atte="'+cid+'"></div>',
                        '</div>'].join("");
                };
	            div += '</div></div>';
                amt=parseInt(amt*100)/100;
                div += ['<div class="popover-item mui-text-center mui-clearfix">',
                    '<div class="mui-pull-left mui-col-xs-4 red-c">合计</div>',
                    '<div class="mui-pull-left mui-col-xs-2 red-c">￥'+amt+'</div>',
                    '<div class="mui-pull-left mui-col-xs-2 red-c"><i class="foodNum" id="cartSum">'+sum+'</i>份</div>',
                    '</div>'].join("");
                $(".popover-body").html(div);
                $(".circle").show();
                $(".circle").html(sum);
                (function($) {
                    $('.mui-scroll-wrapper').scroll({
                        indicators: true //是否显示滚动条
                    });
                })(mui)
            }else{
                $('.circle').hide();
            }
        }
    })
}

//添加购物车
$(document).on("click",".add-icon",function () {
    //当前时间是否在8:00-10:00
    var nowDate = new Date();
        var nowHours = nowDate.getHours();
        var nowMin = nowDate.getMinutes();
        var nowTime = nowHours + ":" + nowMin;
        var strb = '9:00'.split (":");
        if (strb.length != 2) {
            return false;
        }
        var stre = '12:00'.split (":");
        if (stre.length != 2) {
            return false;
        }
        var strn = nowTime.split (":");
        if (stre.length != 2) {
            return false;
        }
        var b = new Date ();
        var e = new Date ();
        var n = new Date ();
        b.setHours (strb[0]);
        b.setMinutes (strb[1]);
        e.setHours (stre[0]);
        e.setMinutes (stre[1]);
        n.setHours (strn[0]);
        n.setMinutes (strn[1]);
        if (n.getTime () - b.getTime () > 0 && n.getTime () - e.getTime () < 0) {
            var id = $(this).attr("atte");
            clickBtn($(this));
            $.getJSON("/canteen/setCart.action",{id:id,num:1},function (data) {
                if (data.code == 4000) {
                    getCartList();
                }
            });
    } else {
        mui("#popoverTime").popover('show');
        return false;
    }
})


//菜单列表
function getCommodityList() {

    $.getJSON("/canteen/commodityList.action",function (data) {
        $("commodityTypeOne").empty();
        $("commodityTypeTwo").empty();
        if (data.code == 4000){
            if(data.list != null){
                var list = data.list;
                commodityListMsg = data.list;
                getCartList()
                var divOne = "";
                var divTwo = "";
                $.each(list, function (index, val) {
                    var div =['<li class="mui-table-view-cell mui-media mui-disabled">',
                        '	<a>',
                        '	<img class="mui-media-object mui-pull-left" src="'+val.picture+'">',
                        '	<div class="mui-media-body">',
                        '	<div style="margin:10px 0;">'+val.name+'</div>',
                        '	<div>￥'+val.price+'/份</div>',
                        '	</div>',
                        '	</a>',
                        '	<img src="images/add_icon.jpg" class="add-icon" atte="'+val.id+'" style="width: 25px;cursor: pointer;" />',
                        '</li>'].join("");;
                    if (val.type==1){
                        divOne += div;
                    }
                    if (val.type==2){
                        divTwo += div;
                    }
                });
                $("#commodityTypeOne").html(divOne);
                $("#commodityTypeTwo").html(divTwo);
            }
        }
    })
}






//		减数量
$(document).on('click','.reduceNum',function(){
    var id = $(this).attr("atte");
    $.getJSON("/canteen/setCart.action",{id:id,num:-1},function (data) {
        if (data.code == 4000)
            getCartList();
    })
    /* var num = $(this).siblings().find('.foodNum').html();
     var sumNum = $('.red-c').find('.foodNum').html();
     if(num - 1 == 0){
         $(this).parents('.popover-item').remove();
         $('.red-c').find('.foodNum').html(parseInt(sumNum) - 1);
     }else{
         $(this).siblings().find('.foodNum').html(num - 1);
         $('.red-c').find('.foodNum').html(parseInt(sumNum) - 1);
     }*/
})


//		加数量
$(document).on('click','.sumNum',function(){
    var id = $(this).attr("atte");
    $.getJSON("/canteen/setCart.action",{id:id,num:1},function (data) {
        if (data.code == 4000)
            getCartList();
    })
    /* var num = $(this).siblings().find('.foodNum').html();
     var sumNum = $('.red-c').find('.foodNum').html();
     $(this).siblings().find('.foodNum').html(parseInt(num) + 1);
     $('.red-c').find('.foodNum').html(parseInt(sumNum) + 1);*/
})

//飞入购物车
var totalBtn1 = 0;
function clickBtn(obj) {
    var img = obj.siblings('a').find('img');
    //var btn = $(this).attr('class');
    console.log(img)

    var flyImg = img.clone().css({
        'opacity':'0.6'
    });
    $('.wrapper').append(flyImg);
    flyImg.css({
        'z-index':999,
        'border':'3px solid #fff',
        'position': 'absolute',
        'height' : img.height() + 'px',
        'width' : img.width() + 'px',
        'top' : img.offset().top +'px',
        'left' : img.offset().left + 'px'
    })
    flyImg.animate({
        'width' : 50 + 'px',
        'height' : 50 + 'px',
        'border-radius' : 100 + '%'
    },function(){
        var t;
        t = $('.shopCar').offset().top;
        totalBtn1 ++;
        if(totalBtn1>99){
            totalBtn1 = 99
        }
        flyImg.animate({
            'top':t,
            'left':($(document).width()-$('.shopCar').width()) + 'px',
            'height':0 +'px',
            'width' :0+'px',
        },1500,function(){
            flyImg.remove();
        })
    });
}