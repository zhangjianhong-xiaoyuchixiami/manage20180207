(function($){
    $.getUrlParam = function(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        return r?decodeURIComponent(r[2]):'';  //含有中文请注意此处的编码和解码
    }
})(jQuery);


$(function(){
    console.log($.getUrlParam('content'));
    console.log($.getUrlParam('partnerId'));
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
    href += (href.match(/\?/) ? '&' : '?') + 'partnerId=' + $.getUrlParam('partnerId') +
        (href.match(/\?/) ? '&' : '?') + 'content=' + $.getUrlParam('content') +
        (href.match(/\?/) ? '&' : '?') + 'beginDate=' + $.getUrlParam('beginDate') +
        (href.match(/\?/) ? '&' : '?') + 'endDate=' + $.getUrlParam('endDate') +
        (href.match(/\?/) ? '&' : '?') + 'status=' + statusId;

    $("#exportExcel").attr('href', href);
}

var hrefByDeptId = $("#exportExcelByDeptId").attr('href');
if(hrefByDeptId) {
    hrefByDeptId += (hrefByDeptId.match(/\?/) ? '&' : '?') + 'partnerId=' + $.getUrlParam('partnerId') +
        (hrefByDeptId.match(/\?/) ? '&' : '?') + 'content=' + $.getUrlParam('content') +
        (hrefByDeptId.match(/\?/) ? '&' : '?') + 'beginDate=' + $.getUrlParam('beginDate') +
        (hrefByDeptId.match(/\?/) ? '&' : '?') + 'endDate=' + $.getUrlParam('endDate') +
        (hrefByDeptId.match(/\?/) ? '&' : '?') + 'status=' + statusId;

    $("#exportExcelByDeptId").attr('href', hrefByDeptId);
}
