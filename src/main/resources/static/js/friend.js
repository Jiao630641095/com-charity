
if("Node" in window)
Node.prototype.appendChildByName = Node.prototype.appendChildByName || function (name, attr, proto) {
    var child = document.createElement(name);
    if (attr)friend.of(attr).map(function(value,name){child.setAttribute(name, value)});
    if (proto)friend.of(proto).map(function(value,name){child[name] = value});
    this.appendChild(child);
    return child;
}

var friend = {
    sendJSON : function (url,param,callback) {
        var xmlhttp=new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState==4 && xmlhttp.status==200&&callback)
                callback(JSON.parse("["+xmlhttp.responseText+"]")[0]);
        }
        if(param){
            xmlhttp.open("POST",url,true);
            xmlhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8")
            xmlhttp.send(JSON.stringify(param));
        } else{
            xmlhttp.open("GET",url,true);
            xmlhttp.send();
        }
    }
    ,ajax : function (url,param,callback) {
        param = param || {}
        var xmlhttp=new XMLHttpRequest();
        var method="GET";
        var data="";
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState==4 && xmlhttp.status==200&&callback)
                callback(JSON.parse("["+xmlhttp.responseText+"]")[0]);
        }
        function concat(x,y) {
            return y+"="+x;
        }
        if(param.get){
            if(url.indexOf("?")==-1)url+="?";
            url+=friend.of(param.get).map(concat).join("&");
        }
        if(param.post){
            method="POST";
            data+=friend.of(param.post).map(concat).join("&");
        } else if(param.json){
            method="POST";
            xmlhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8")
            data = JSON.stringify(param.json);
        }
        xmlhttp.open(method,url,true);
        xmlhttp.send(data);
    },
    of:function (obj) {
        return new this.formap(obj);
    },
    formap:function (data){this.__data=data},
    getCookie:function(name){
        var x= new RegExp( "(^|; )"+name+"=([^;$]+)", "i");
        var result = document.cookie.match(x)
        return result&&result[2];
    },
    activeNav:function (lis,className,innerText) {
        className=className||"active";
        var href = location.href;
        friend.of(lis).map(function(x){
            var a = x.children[0];
            if(innerText&&innerText==a.innerText){
                a.className=className;
            }else if(href.indexOf(a.getAttribute("href"))>-1)
                a.className=className;
        });
    },
    q:function (cssSelector){return document.querySelector(cssSelector);} ,
    qs:function(cssSelector){return document.querySelectorAll(cssSelector);}
}
friend.loadhtml=function(attr,elementName) {
    attr=attr||"src";
    elementName=elementName||"div";/*elements =document.querySelectorAll("div[src]")*/
    var elements = document.querySelectorAll(elementName+"["+attr+"]");
    function load(url,element) {
        var xmlhttp=new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState==4){
                element.innerHTML=xmlhttp.responseText;
                    eval(element.getAttribute("onready"))
            }
        }
        xmlhttp.open("GET",url,true);
        xmlhttp.send();
    }
    function fullload(element) {
        element.setAttribute = function(name,value){
            if(name==attr)load(value,element);
            Element.prototype.setAttribute.call(element,name,value);
        }
        element.setAttribute(attr,element.getAttribute(attr));
    }
    elements&&friend.of(elements).map(fullload)
}
friend.formap.prototype.map=function (call,context) {
    var arr = [];
    if(this.__data.constructor==HTMLCollection
        || this.__data.constructor==NodeList
        || ("StaticNodeList" in window && this.__data.constructor==window.StaticNodeList)){
        for (var x=0,length=this.__data.length;x<length;x++)
            arr.push(call.call(context,this.__data[x],x,this.__data))
    }else{
        for (var x in this.__data)
            arr.push(call.call(context,this.__data[x],x,this.__data))
    }
    return arr;
}
/*div.setAttribute = function(name,value){
    console.log(name+"\t"+value);
    HTMLDivElement.prototype.setAttribute.call(this,name,value);
}*/
/*
 Object.defineProperty(div, 'src', {
 get: function() {return div.src;},
 set: function(value) {div.src = value;}
 });
 */

