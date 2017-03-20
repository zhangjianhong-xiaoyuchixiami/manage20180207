(function($){
    $.getUrlParam = function(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r!=null) return unescape(r[2]); return '';
    }
})(jQuery);

$(function(){
    console.log($.getUrlParam('apiTypeId'));
    console.log($.getUrlParam('vendorId'));
    console.log($.getUrlParam('apiId'));
});

var href = $("#exportExcel").attr('href');

if(href) {
    href += (href.match(/\?/) ? '&' : '?') + 'apiTypeId=' + $.getUrlParam('apiTypeId') +
        (href.match(/\?/) ? '&' : '?') + 'vendorId=' + $.getUrlParam('vendorId') +
        (href.match(/\?/) ? '&' : '?') + 'apiId=' + $.getUrlParam('apiId');
    $("#exportExcel").attr('href', href);
}
