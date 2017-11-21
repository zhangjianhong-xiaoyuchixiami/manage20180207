/**
 * Created by jonhn on 2017/3/21.
 */
var SearchCustomerLogResult = function () {

    return {

        //main function to initiate the module
        init: function () {

            function fnFormatDetails ( oTable, nTr)
            {
                var aData = oTable.fnGetData( nTr );
                var sOut = '<table style="width: 100%">';
                sOut += '<tr><th>客户名称:</th><td>'+ aData[1] +'</td></tr>';
                sOut += '<tr><th>产品类型:</th><td>'+ aData[2] +'</td></tr>';
                sOut += '<tr><th>reqId:</th><td>'+ aData[3] +'</td></tr>';
                sOut += '<tr><th>k:</th><td>'+ aData[4] +'</td></tr>';
                sOut += '<tr><th>请求数据:</th><td>'+ aData[5] +'</td></tr>';
                sOut += '<tr><th>核验结果:</th><td>'+ aData[6] +'</td></tr>';
                sOut += '<tr><th>响应结果:</th><td>'+ aData[7] +'</td></tr>';
                sOut += '<tr><th>响应时长:</th><td>'+ aData[8] +'</td></tr>';
                sOut += '<tr><th>是否扣费:</th><td>'+ aData[9] +'</td></tr>';
                sOut += '<tr><th>是否走缓存:</th><td>'+ aData[10] +'</td></tr>';
                sOut += '<tr><th>调用上游:</th><td>'+ aData[11] +'</td></tr>';
                sOut += '<tr><th>请求时间:</th><td>'+ aData[12] +'</td></tr>';
                sOut += '</table>';
                return sOut;
            }

            var nCloneTh = document.createElement( 'th' );
            var nCloneTd = document.createElement( 'td' );
            nCloneTd.innerHTML = '<span class="row-details row-details-close"></span>';
            // nCloneTh.innerHTML = '<span class="row-details row-details-close"></span>';
            $('#sample_1 thead tr').each( function () {
                this.insertBefore( nCloneTh, this.childNodes[0] );
            } );

            $('#sample_1 tbody tr').each( function () {
                this.insertBefore(  nCloneTd.cloneNode( true ), this.childNodes[0] );
            } );

            $('#sample_1').on('click', ' tbody td .row-details', function () {
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


            var oTable = $('#sample_1').dataTable({
                "aoColumns": [
                    {"bSortable": false},
                    null,
                    {"bVisible": false},
                    {"bVisible": false},
                    null,
                    {"bVisible": false},
                    null,
                    {"bVisible": false},
                    null,
                    {"bVisible": false},
                    null,
                    null,
                    null,
                    {"bSortable": false}
                ],
                "aaSorting": [[12, 'desc']],
                "bPaginate" : false,
                "bLengthChange" : false,
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

            $('#search_log').addClass('active');

            $('#search_log_select').addClass('selected');

            $('#search_log_select_arrow').addClass('arrow open');

            $('#search_customer_log').addClass('active');

        }
    };

}();