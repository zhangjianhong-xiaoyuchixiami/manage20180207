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
    console.log($.getUrlParam('beginDate'));
    console.log($.getUrlParam('endDate'));
});

var href = $("#exportExcel").attr('href');

if(href) {
    href += (href.match(/\?/) ? '&' : '?') + 'apiTypeId=' + $.getUrlParam('apiTypeId') +
        (href.match(/\?/) ? '&' : '?') + 'vendorId=' + $.getUrlParam('vendorId') +
        (href.match(/\?/) ? '&' : '?') + 'beginDate=' + $.getUrlParam('beginDate') +
        (href.match(/\?/) ? '&' : '?') + 'endDate=' + $.getUrlParam('endDate');
    $("#exportExcel").attr('href', href);
}
