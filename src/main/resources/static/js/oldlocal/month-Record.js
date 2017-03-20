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
    console.log($.getUrlParam('years'));
    console.log($.getUrlParam('months'));
    console.log($.getUrlParam('typeId'));

});

var href = $("#exportExcel").attr('href');
if(href) {
    href += (href.match(/\?/) ? '&' : '?') + 'companyName=' + $.getUrlParam('companyName') +
        (href.match(/\?/) ? '&' : '?') + 'customerId=' + $.getUrlParam('customerId') +
        (href.match(/\?/) ? '&' : '?') + 'years=' + $.getUrlParam('years') +
        (href.match(/\?/) ? '&' : '?') + 'months=' + $.getUrlParam('months') +
        (href.match(/\?/) ? '&' : '?') + 'typeId=' + $.getUrlParam('typeId');
    $("#exportExcel").attr('href', href);
}
