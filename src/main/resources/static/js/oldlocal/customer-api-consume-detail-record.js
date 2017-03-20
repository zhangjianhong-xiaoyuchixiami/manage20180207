(function($){
    $.getUrlParam = function(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r!=null) return unescape(r[2]); return '';
    }
})(jQuery);

var reasonId =[];//定义一个数组

$('input[name="reasonId"]:checked').each(function(){
    reasonId.push($.trim($(this).val()));
});

$(function(){
    console.log($.getUrlParam('companyName'));
    console.log($.getUrlParam('customerId'));
    console.log($.getUrlParam('apiTypeId'));
    console.log($.getUrlParam('apiTypeName'));
});

var href = $("#exportExcel").attr('href');
if(href) {
    href += (href.match(/\?/) ? '&' : '?') + 'companyName=' + $.getUrlParam('companyName') +
        (href.match(/\?/) ? '&' : '?') + 'customerId=' + $.getUrlParam('customerId') +
        (href.match(/\?/) ? '&' : '?') + 'apiTypeId=' + $.getUrlParam('apiTypeId') +
        (href.match(/\?/) ? '&' : '?') + 'apiTypeName=' + $.getUrlParam('apiTypeName') +
        (href.match(/\?/) ? '&' : '?') + 'reasonId=' + reasonId;
    $("#exportExcel").attr('href', href);
}
