/*创建收款付款*/
function paymentReceipt(partnerId,reasonId) {
    $("#partnerId-controls").empty();
    $("#reasonId-controls").empty();
    $("#amount-controls").empty();
    $("#remark-controls").empty();
    $("#error-alert").empty();
    var opPartnerId=document.createElement("input");
    opPartnerId.value=partnerId;
    opPartnerId.type="text";
    opPartnerId.id="partnerId";
    opPartnerId.name="partnerId";
    $("#partnerId-controls").append(opPartnerId);
    var opReasonId=document.createElement("input");
    opReasonId.value=reasonId;
    opReasonId.type="text";
    opReasonId.id="reasonId";
    opReasonId.name="reasonId";
    $("#reasonId-controls").append(opReasonId);
    $("#amount-controls").append('<input type="text" id="amount-flag" name="amount"  placeholder="（单位/元）" class="m-wrap medium"><span id="amount-message"></span><span class="help-block">说明：只能输入数字类型并且金额大于0</span>');
    $("#remark-controls").append('<textarea class="medium m-wrap" id="remark" name="remark" rows="3"></textarea><span class="help-block" style="font-size: 12px;">说明：只能输入255个字符</span>');

}

(function($){
    $.getUrlParam = function(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r!=null) return unescape(r[2]); return '';
    }
})(jQuery);

$(function(){
    console.log($.getUrlParam('partnerName'));
});

var href = $("#exportExcel").attr('href');

if(href) {
    href += (href.match(/\?/) ? '&' : '?') + 'partnerName=' + $.getUrlParam('partnerName');
    $("#exportExcel").attr('href', href);
}
