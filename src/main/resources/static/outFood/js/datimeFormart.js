

/*  Created by JiaoZhiPeng on 2018/4/21.*/

function getLocalTime(nS) {
    var time = new Date(nS);
    var y = time.getFullYear();//年
    var m = time.getMonth() + 1;//月
    var d = time.getDate();//日
    var h = time.getHours();//时
    var mm = time.getMinutes();//分
    var s = time.getSeconds();//秒
    if (m<10){
        m = "0"+m;
    }
    if (d<10){
        d = "0"+d;
    }
    if (h<10){
        h = "0"+h;
    }
    if (mm<10){
        mm = "0"+mm;
    }
    if (s<10){
        s = "0"+s;
    }
    return (y+"-"+m+"-"+d+" "+h+":"+mm+":"+s)
}


function getUrlParms (name) {
    let tstr = window.location.href;
    let index = tstr.indexOf('?')
    let str = tstr.substring(index + 1);
    let arr = str.split('&');
    let result = {};
    arr.forEach((item) => {
        let a = item.split('=');
        result[a[0]] = a[1];
    })
    return result[name];
}



