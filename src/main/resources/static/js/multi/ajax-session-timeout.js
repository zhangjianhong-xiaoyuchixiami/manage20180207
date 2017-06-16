/**
 * Created by jonhn on 2017/6/16.
 */


//全局的ajax访问，处理ajax清求时sesion超时
$.ajaxSetup({
    contentType:"application/x-www-form-urlencoded;charset=utf-8",
    complete:function(XMLHttpRequest,textStatus){
        //通过XMLHttpRequest取得响应头，sessionstatus，
        var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");
        if(sessionstatus=="timeout"){
            //如果超时就处理 ，指定要跳转的页面
            //window.location.href = window.location.href;
            location.reload();
        }
    }
});
