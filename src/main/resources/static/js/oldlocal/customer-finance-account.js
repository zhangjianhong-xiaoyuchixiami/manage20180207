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
});

var href = $("#exportExcel").attr('href');
if(href) {
    href += (href.match(/\?/) ? '&' : '?') + 'partnerId=' + $.getUrlParam('partnerId') +
        (href.match(/\?/) ? '&' : '?') + 'content=' + $.getUrlParam(encodeURI('content'));
    $("#exportExcel"
    ).attr('href', href);
}

var hrefByDeptId = $("#exportExcelByDeptId").attr('href');
if(hrefByDeptId) {
    hrefByDeptId += (hrefByDeptId.match(/\?/) ? '&' : '?') + 'partnerId=' + $.getUrlParam('partnerId') +
        (hrefByDeptId.match(/\?/) ? '&' : '?') + 'content=' + $.getUrlParam('content');
    $("#exportExcelByDeptId"
    ).attr('href', hrefByDeptId);
}
