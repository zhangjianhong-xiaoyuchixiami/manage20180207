var CompanyForbid = function () {

    return {

        init: function () {

            if (!jQuery().dataTable) {
                return;
            }

            function fnFormatDetails ( oTable, nTr ) {
                var aData = oTable.fnGetData( nTr );
                var sOut = '<table style="width: 100%">';
                sOut += '<tr>' +
                    '<th>账号</th>' +
                    '<th>账号类型</th>' +
                    '<th>密码</th>' +
                    '<th>余额</th>' +
                    '<th>状态</th>' +
                    '<th>Ip段</th>' +
                    '</tr>';

                sOut += '<tr>' +
                    '<td>'+aData[5]+'</td>' +
                    '<td>'+aData[6]+'</td>' +
                    '<td>'+aData[7]+'</td>' +
                    '<td>'+aData[8]+'</td>' +
                    '<td>'+aData[9]+'</td>' +
                    '<td>'+aData[10]+'</td>' +
                    '</tr>';
                sOut += '</table>';
                return sOut;
            }

            var nCloneTh = document.createElement( 'th' );
            var nCloneTd = document.createElement( 'td' );
            nCloneTd.innerHTML = '<span class="row-details row-details-close"></span>';
            // nCloneTh.innerHTML = '<span class="row-details row-details-close"></span>';
            $('#companySample_2 thead tr').each( function () {
                this.insertBefore( nCloneTh, this.childNodes[0] );
            } );

            $('#companySample_2 tbody tr').each( function () {
                this.insertBefore(  nCloneTd.cloneNode( true ), this.childNodes[0] );
            } );

            $('#companySample_2').on('click', ' tbody td .row-details', function () {
                var nTr = $(this).parents('tr')[0];
                if ( oTable.fnIsOpen(nTr) )
                {
                    /* This row is already open - close it */
                    $(this).addClass("row-details-close").removeClass("row-details-open");
                    oTable.fnClose( nTr );
                }
                else
                {
                    /* Open this row */
                    $(this).addClass("row-details-open").removeClass("row-details-close");
                    oTable.fnOpen( nTr, fnFormatDetails(oTable, nTr), 'details' );
                }
            });


            var oTable = $('#companySample_2').dataTable({
                "aoColumns": [
                    { "bSortable": false},  //0  展开符号
                    null,  //1  companyName
                    null,  //2  floor
                    null,  //3  superFloor
                    null,  //4  balance
                    { "bVisible": false},  //5 customerId
                    { "bVisible": false},  //6 typeId
                    { "bVisible": false},  //7 typeName
                    { "bVisible": false},  //8 authId
                    { "bVisible": false },  //9 authPass
                    { "bVisible": false }, //10 balance
                    { "bSortable": false}  // 11
                ],
                "aaSorting": [[4, 'desc']],
                "aLengthMenu": [
                    [10, 15, 20, -1],
                    [10, 15, 20, "全部"] // change per page values here
                ],
                "bFilter" : false, //设置全文搜索框，默认true

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
                }
            });

            /*状态正常全选操作*/
            jQuery('#companySample_2 .group-checkable').change(function () {
                var set = jQuery(this).attr("data-set");
                var checked = jQuery(this).is(":checked");
                jQuery(set).each(function () {
                    if (checked) {
                        $(this).attr("checked", true);
                    } else {
                        $(this).attr("checked", false);
                    }
                });
                jQuery.uniform.update(set);
            });

            jQuery('#companySample_2_wrapper .dataTables_filter input').addClass("m-wrap medium"); // modify table search input
            jQuery('#companySample_2_wrapper .dataTables_length select').addClass("m-wrap small"); // modify table per page dropdown



        }

    };

}();