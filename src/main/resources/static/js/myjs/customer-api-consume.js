var CustomerApiConsume = function () {

    return {

        init: function () {

            if (!jQuery().dataTable) {
                return;
            }

            var table = $('#sample_5').dataTable({
                "aoColumns": [
                    null,
                    null,
                    null,
                    { "bSortable": false }
                ],
                "aaSorting": [[2, 'desc']],
                "aLengthMenu": [
                    [10, 15, 20, -1],
                    [10, 15, 20, "全部"] // change per page values here
                ],
                "iDisplayLength": 15, //每页显示多少行
                "sDom": "t<'row-fluid'<'span6'il><'span6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage" : {  //设置语言
                    "sLengthMenu" : "每页显示 _MENU_ 条记录",
                    "sZeroRecords" : "对不起，没有匹配的数据",
                    "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
                    "sInfoEmpty" : "没有匹配的数据",
                    "sInfoFiltered" : "(数据表中共 _MAX_ 条记录)",
                    "sProcessing" : "正在加载中...",
                    "sSearch" : "全文搜索：",
                    "oPaginate" : {
                        "sFirst" : "第一页",
                        "sPrevious" : " 上一页 ",
                        "sNext" : " 下一页 ",
                        "sLast" : " 最后一页 "
                    }
                },
                "bFilter" : false //设置全文搜索框，默认true

            });

            /*下拉框*/
            $("#apiTypeId").change(function () {
                var param = $("#apiTypeId").val();
                var param1 = $("#customerId").val();
                if (param !=null) {
                    $.ajax({
                        url: '/finance/find-api-vendor-by-api-type-id',
                        data: {"apiTypeId": param, "customerId": param1},
                        type: 'post',
                        dataType: 'json',
                        success: function (data) {
                            if(data != null){
                                $("#apiVendorId ").empty();
                                $("#apiVendorId").append("<option value=''>请选择...</option>");
                                for (var i=0; i<data.length; i++){
                                    var op=document.createElement("option");
                                    op.value=data[i].vendorId;
                                    op.innerHTML=data[i].vendorName;
                                    $("#apiVendorId").append(op);
                                }
                            }
                        }
                    });
                }
            });

            $('.customer_consume').change(function () {
                $(this).submit();
            });

        }

    };

}();