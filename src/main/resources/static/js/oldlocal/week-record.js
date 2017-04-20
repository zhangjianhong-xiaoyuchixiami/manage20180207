/**
 * Created by jonhn on 2017/3/20.
 */
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
    console.log($.getUrlParam('years'));
    console.log($.getUrlParam('months'));
    console.log($.getUrlParam('weeks'));
    console.log($.getUrlParam('typeId'));

});

var href = $("#exportExcel").attr('href');
if(href) {
    href += (href.match(/\?/) ? '&' : '?') + 'companyName=' + $.getUrlParam('companyName') +
        (href.match(/\?/) ? '&' : '?') + 'customerId=' + $.getUrlParam('customerId') +
        (href.match(/\?/) ? '&' : '?') + 'years=' + $.getUrlParam('years') +
        (href.match(/\?/) ? '&' : '?') + 'months=' + $.getUrlParam('months') +
        (href.match(/\?/) ? '&' : '?') + 'weeks=' + $.getUrlParam('weeks') +
        (href.match(/\?/) ? '&' : '?') + 'typeId=' + $.getUrlParam('typeId');
    $("#exportExcel").attr('href', href);
}
