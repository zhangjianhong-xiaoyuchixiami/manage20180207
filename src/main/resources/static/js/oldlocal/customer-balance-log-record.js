(function($){
    $.getUrlParam = function(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r!=null) return unescape(r[2]); return '';
    }
})(jQuery);

$(function(){
    console.log($.getUrlParam('companyName'));
    console.log($.getUrlParam('customerId'));
    console.log($.getUrlParam('beginDate'));
    console.log($.getUrlParam('endDate'));
});
var reasonId =[];//定义一个数组
$('input[name="reasonId"]:checked').each(function(){
    reasonId.push($.trim($(this).val()));
});

var href = $("#exportExcel").attr('href');

if(href) {
    href += (href.match(/\?/) ? '&' : '?') + 'companyName=' + $.getUrlParam('companyName') +
        (href.match(/\?/) ? '&' : '?') + 'customerId=' + $.getUrlParam('customerId') +
        (href.match(/\?/) ? '&' : '?') + 'beginDate=' + $.getUrlParam('beginDate')+
        (href.match(/\?/) ? '&' : '?') + 'endDate=' + $.getUrlParam('endDate')+
        (href.match(/\?/) ? '&' : '?') + 'reasonId=' + reasonId;
    $("#exportExcel"
    ).attr('href', href);
}
