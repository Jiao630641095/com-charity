/**
 * Created by XWD on 2017/9/7.
 */
layui.config({
    base : "js/"
}).use(['jquery','upload'],function(){
    var upload = layui.upload,
        $ = layui.jquery;

    //上传banner图片
    var files;
    var demoListView = $('#demoList')
        ,uploadListIns = upload.render({
        elem: '#testList'
        ,url: '/banner/upload'
        ,accept: 'file'
        ,exts:'jpg|png|gif|bmp|jpeg'
        ,multiple: true
        ,auto: false
        ,bindAction: '#testListAction'
        ,choose: function(obj){
            files = obj.pushFile(); //将每次选择的文件追加到文件队列
            //读取本地文件
            obj.preview(function(index, file, result){
                var tr = $(['<tr id="upload-'+ index +'">'
                    ,'<td>'+  '<img src="'+ result +'"  class="layui-upload-img">'  +'</td>'
                    ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
                    ,'<td>等待上传</td>'
                    ,'<td>'
                    ,'<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
                    ,'<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
                    ,'</td>'
                    ,'</tr>'].join(''));

                //单个重传
                tr.find('.demo-reload').on('click', function(){
                    obj.upload(index, file);
                });

                //删除
                tr.find('.demo-delete').on('click', function(){
                    delete files[index]; //删除对应的文件
                    tr.remove();
                });

                demoListView.append(tr);
            });
        }
        ,done: function(res, index, upload){
          //  alert(res.data.src);
            if(res.code == 0){ //上传成功
                var tr = demoListView.find('tr#upload-'+ index)
                    ,tds = tr.children();
                tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                tds.eq(3).html(''); //清空操作
                delete files[index]; //删除文件队列已经上传成功的文件
                return;



            }
            this.error(index, upload);
        }
        ,error: function(index, upload){
            var tr = demoListView.find('tr#upload-'+ index)
                ,tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
            tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
        }
    });
})