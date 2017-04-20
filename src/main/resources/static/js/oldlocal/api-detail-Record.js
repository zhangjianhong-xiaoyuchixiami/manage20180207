(function($){
    $.getUrlParam = function(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        return r?decodeURIComponent(r[2]):'';  //含有中文请注意此处的编码和解码
    }
})(jQuery);

$(function(){
    console.log($.getUrlParam('apiId'));
    console.log($.getUrlParam('beginDate'));
    console.log($.getUrlParam('endDate'));
});

var href = $("#exportExcel").attr('href');

if(href) {
    href += (href.match(/\?/) ? '&' : '?') + 'apiId=' + $.getUrlParam('apiId') +
        (href.match(/\?/) ? '&' : '?') + 'beginDate=' + $.getUrlParam('beginDate') +
        (href.match(/\?/) ? '&' : '?') + 'endDate=' + $.getUrlParam('endDate') +
        (href.match(/\?/) ? '&' : '?') + 'apiTypeName=' + $.getUrlParam('apiTypeName') +
        (href.match(/\?/) ? '&' : '?') + 'vendorName=' + $.getUrlParam('vendorName');
    $("#exportExcel").attr('href', href);
}

