(function($){
    $.getUrlParam = function(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        return r?decodeURIComponent(r[2]):'';  //含有中文请注意此处的编码和解码
    }
})(jQuery);

$(function(){
    console.log($.getUrlParam('partnerId'));
    console.log($.getUrlParam('reasonId'));
});

var href = $("#exportExcel").attr('href');

if(href) {
    href += (href.match(/\?/) ? '&' : '?') + 'partnerId=' + $.getUrlParam('partnerId') +
        (href.match(/\?/) ? '&' : '?') + 'reasonId=' + $.getUrlParam('reasonId');
    $("#exportExcel").attr('href', href);
}
