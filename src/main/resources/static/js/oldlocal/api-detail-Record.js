(function($){
    $.getUrlParam = function(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r!=null) return unescape(r[2]); return '';
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
        (href.match(/\?/) ? '&' : '?') + 'endDate=' + $.getUrlParam('endDate');
    $("#exportExcel").attr('href', href);
}

