var Api = function () {

    return {

        init: function () {

            if (!jQuery().dataTable) {
                return;
            }

            var oTable = $('#sample_1').dataTable({
                "aoColumns": [
                    null,
                    null,
                    { "bVisible": false },
                    null,
                    null,
                    null,
                    { "bVisible": false },
                    { "bVisible": false },
                    null,
                    null
                    // { "bSortable": false }
                ],
                "aaSorting": [[9, 'desc']],
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

            $('#sample_1_column_toggler input[type="checkbox"]').change(function(){
                /* Get the DataTables object again - this is not a recreation, just a get of the object */
                var iCol = parseInt($(this).attr("data-column"));
                var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
                oTable.fnSetColumnVis(iCol, (bVis ? false : true));
            });

            $('#customerBalance').addClass('active');

            $('#apiRecordLog').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');

            $("#apiTypeId").change(function () {

                var param = $("#apiTypeId").val();

                $("#vendorId_chosen ").empty();
                $("#vendorId_chosen").append('<select class="medium m-wrap" id="vendorId" name="vendorId"><option value=""></option></select>');

                if (param !=null) {
                    $.ajax({
                        url: '/api/find-api-vendor-by-api-type-id',
                        data: {"apiTypeId": param},
                        type: 'post',
                        dataType: 'json',
                        success: function (data) {
                            if(data != null){
                                for (var i=0; i<data.length; i++){
                                    var op=document.createElement("option");
                                    op.value=data[i].id;
                                    op.innerHTML=data[i].name;
                                    $("#vendorId").append(op);
                                }
                            }
                        }
                    });
                }

                $("#vendorId").select2({
                    language: "zh-CN",
                    placeholder: "请选择",
                    allowClear: true
                });

            });

        }

    };

}();