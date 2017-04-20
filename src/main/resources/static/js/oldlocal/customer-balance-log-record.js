(function($){
    $.getUrlParam = function(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        return r?decodeURIComponent(r[2]):'';  //含有中文请注意此处的编码和解码
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
    $("#exportExcel").attr('href', href);
}
