
function vendorConsume(apiTypeId){
    var param = apiTypeId;
    var param1 = $("#customerId").val();
    $.ajax({
        type: "post",
        url: "/finance/find-all-vendor-record/count-result",
        data: {"apiTypeId": param,"customerId": param1},
        dataType: 'json',
        success: function (result) {
            var json = result;
            var jsondata = [];
            for (var i in json) {
                jsondata.push([i, json[i]]);
            }

            var chart = new Highcharts.Chart({
                chart: {
                    renderTo: 'container',
                    type: 'pie'
                },
                title: {
                    text: '各供应商消费统计',
                    margin: 15
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                },
                exporting: {
                    enabled: false
                },
                credits: {
                    enabled: false
                },
                series: [{
                    name: '供应商',
                    data: jsondata
                }]
            });
        }
    });
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
    console.log($.getUrlParam('companyName'));
    console.log($.getUrlParam('customerId'));
    console.log($.getUrlParam('apiTypeId'));
    console.log($.getUrlParam('apiVendorId'));
});

var href = $("#exportExcel").attr('href');
if(href) {
    href += (href.match(/\?/) ? '&' : '?') + 'companyName=' + $.getUrlParam('companyName') +
        (href.match(/\?/) ? '&' : '?') + 'customerId=' + $.getUrlParam('customerId') +
        (href.match(/\?/) ? '&' : '?') + 'apiTypeId=' + $.getUrlParam('apiTypeId') +
        (href.match(/\?/) ? '&' : '?') + 'apiVendorId=' + $.getUrlParam('apiVendorId');
    $("#exportExcel").attr('href', href);
}