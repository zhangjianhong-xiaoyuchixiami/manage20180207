var ApiVendorRecord = function () {

    return {

        init: function () {

            if (!jQuery().dataTable) {
                return;
            }

            function fnFormatDetailsVendor ( oTable, nTr ) {
                var aData = oTable.fnGetData( nTr );
                var sOut = '<table style="width: 100%">';
                sOut += '<tr><th colspan="4">时间范围：开通后--至昨天</th></tr>';
                sOut += '<tr>' +
                    '<th>供应类型</th>' +
                    '<th>当前价格</th>' +
                    '<th>总消费额</th>' +
                    // '<th>调用成功次数</th>' +
                    '<th>扣费次数</th>' +
                    '</tr>';
                sOut += '<tr>' +
                    '<td>'+aData[10]+'</td>' +
                    '<td>'+aData[11]+'</td>' +
                    '<td>'+aData[12]+'</td>' +
                    // '<td>'+aData[13]+'</td>' +
                    '<td>'+aData[13]+'</td>' +
                    '</tr>';
                sOut += '</table>';
                return sOut;
            }


            var nCloneThV = document.createElement( 'th' );
            var nCloneTdV = document.createElement( 'td' );
            nCloneTdV.innerHTML = '<span class="row-details row-details-close"></span>';
            // nCloneTh.innerHTML = '<span class="row-details row-details-close"></span>';
            $('#sample_11 thead tr').each( function () {
                this.insertBefore( nCloneThV, this.childNodes[0] );
            } );

            $('#sample_11 tbody tr').each( function () {
                this.insertBefore(  nCloneTdV.cloneNode( true ), this.childNodes[0] );
            } );

            $('#sample_11').on('click', ' tbody td .row-details', function () {
                var nTr = $(this).parents('tr')[0];
                if ( oTable2.fnIsOpen(nTr) )
                {
                    /* This row is already open - close it */
                    $(this).addClass("row-details-close").removeClass("row-details-open");
                    oTable2.fnClose( nTr );
                }
                else
                {
                    /* Open this row */
                    $(this).addClass("row-details-open").removeClass("row-details-close");
                    oTable2.fnOpen( nTr, fnFormatDetailsVendor(oTable2, nTr), 'details' );
                }
            });

            var oTable2 = $('#sample_11').dataTable({
                "aoColumns": [
                    { "bSortable": false },
                    null,
                    null,
                    null,
                    null,
                    null,
                    { "bVisible": false },
                    { "bVisible": false },
                    null,
                    null,
                    { "bVisible": false },
                    { "bVisible": false },
                    { "bVisible": false },
                    // { "bVisible": false },
                    { "bVisible": false }
                ],
                "aoColumnDefs": [
                    {
                        "aTargets": [ 1 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 2 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 3 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 4 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 5 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 6 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 7 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 8 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 9 ],
                        "sType": "html-percent"
                    }
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

            $('#sample_11_column_toggler input[type="checkbox"]').change(function(){
                /* Get the DataTables object again - this is not a recreation, just a get of the object */
                var iCol = parseInt($(this).attr("data-column"));
                var bVis = oTable2.fnSettings().aoColumns[iCol].bVisible;
                oTable2.fnSetColumnVis(iCol, (bVis ? false : true));
            });

            $('#customerBalance').addClass('active');

            $('#apiVendorRecordLog').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');



        }

    };

}();