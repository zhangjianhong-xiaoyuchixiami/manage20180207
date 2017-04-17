/**
 *
 */

    (function($){
        $.getUrlParam = function(name)
        {
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r!=null) return unescape(r[2]); return '';
        }
    })(jQuery);

    $(function(){
        console.log($.getUrlParam('vendorId'));
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
        href += (href.match(/\?/) ? '&' : '?') + 'vendorId=' + $.getUrlParam('vendorId') +
            (href.match(/\?/) ? '&' : '?') + 'partnerId=' + $.getUrlParam('partnerId') +
            (href.match(/\?/) ? '&' : '?') + 'beginDate=' + $.getUrlParam('beginDate') +
            (href.match(/\?/) ? '&' : '?') + 'endDate=' + $.getUrlParam('endDate') +
            (href.match(/\?/) ? '&' : '?') + 'status=' + statusId;

        $("#exportExcel").attr('href', href);
    }

    function charge(vendorId) {
        $("#apiId-controls").empty();
        $("#amount-controls").empty();
        $("#remark-controls").empty();
        $("#error-alert").empty();
        var op=document.createElement("input");
        op.value=vendorId;
        op.type="text";
        op.id="vendorIdCharge";
        op.name="vendorIdCharge";
        $("#apiId-controls").append(op);
        $("#amount-controls").append('<input type="text" id="amount" name="amount"  placeholder="（单位/元）" class="m-wrap medium"><span id="amount-message"></span><span class="help-block">说明：只能输入数字类型并且金额大于0</span>');
        $("#remark-controls").append('<textarea class="medium m-wrap" id="remark" name="remark" rows="3"></textarea><span class="help-block" style="font-size: 12px;">说明：只能输入255个字符</span>');

    }

    $("#btn-black-btn-primary").on("click",function () {
        var vendorIdCharge=$("#vendorIdCharge").val();
        var amount=$("#amount").val();
        var chargeDate=$("#chargeDate").val();
        var remark=$("#remark").attr("value");
        $.ajax({
            type: "post",
            url: "/api/find-all-vendor-record/charge",
            data: {"vendorIdCharge":vendorIdCharge,"amount":amount,"chargeDate":chargeDate,"remark":remark},
            dataType: "json",
            success: function (result) {
                if(result.amountMessage != null){
                    $("#amount-message").empty();
                    $("#amount-message").append('<span class="help-line"><font color="red">'+result.amountMessage+'</font></span>');
                    return;
                }
                if(result.errorMessage != null) {
                    $("#error-alert").empty();
                    $("#error-alert").append('<div class="alert alert-error show"><button class="close" data-dismiss="alert"></button><span>'+result.errorMessage+'</span></div>');
                    return;
                }
                if(result.successMessage != null){
                    window.location.href="/api/find-all-api-vendor-consume"
                }
            }
        });
    });