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

var statusId =[];//定义一个数组
$('input[name="status"]:checked').each(function(){
    statusId.push($.trim($(this).val()));
});

console.log(statusId);

var href = $("#exportExcel").attr('href');

if(href) {
    href += (href.match(/\?/) ? '&' : '?') + 'apiTypeId=' + $.getUrlParam('apiTypeId') +
        (href.match(/\?/) ? '&' : '?') + 'vendorId=' + $.getUrlParam('vendorId') +
        (href.match(/\?/) ? '&' : '?') + 'beginDate=' + $.getUrlParam('beginDate') +
        (href.match(/\?/) ? '&' : '?') + 'endDate=' + $.getUrlParam('endDate') +
        (href.match(/\?/) ? '&' : '?') + 'status=' + statusId;
    $("#exportExcel").attr('href', href);
}
