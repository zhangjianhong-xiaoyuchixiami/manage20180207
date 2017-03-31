var CustomerFinanceAccountForbid = function () {

    return {

        init: function () {

            if (!jQuery().dataTable) {
                return;
            }
            var beginDate=null;
            var endDate=null;
            if ($('#beginDate').val() != null && $('#beginDate').val() != ''){
                beginDate = $('#beginDate').val();
            }else {
                beginDate = '开通后'
            }
            if ($('#endDate').val() != null && $('#endDate').val() != ''){
                endDate = $('#endDate').val();
            }else {
                endDate = '至今'
            }

            function fnFormatDetails ( oTable, nTr ,beginDate,endDate)
            {
                var aData = oTable.fnGetData( nTr );
                var sOut = '<table style="width: 100%">';
                sOut += '<tr><th colspan="5">时间范围：'+beginDate+'--'+endDate+'</th></tr>';
                sOut += '<tr>' +
                    '<th>购买产品</th>' +
                    '<th>当前价格</th>' +
                    '<th>总消费额（单位：元）</th>' +
                    '<th>请求次数</th>' +
                    '<th>成功次数</th>' +
                    '</tr>';
                sOut += '<tr>' +
                    '<td>'+aData[12]+'</td>' +
                    '<td>'+aData[13]+'</td>' +
                    '<td>'+aData[14]+'</td>' +
                    '<td>'+aData[15]+'</td>' +
                    '<td>'+aData[16]+'</td>' +
                    '</tr>';
                sOut += '</table>';
                return sOut;
            }

            var nCloneTh = document.createElement( 'th' );
            var nCloneTd = document.createElement( 'td' );
            nCloneTd.innerHTML = '<span class="row-details row-details-close"></span>';
            // nCloneTh.innerHTML = '<span class="row-details row-details-close"></span>';
            $('#sample_3 thead tr').each( function () {
                this.insertBefore( nCloneTh, this.childNodes[0] );
            } );

            $('#sample_3 tbody tr').each( function () {
                this.insertBefore(  nCloneTd.cloneNode( true ), this.childNodes[0] );
            } );

            $('#sample_3').on('click', ' tbody td .row-details', function () {
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
                    oTable.fnOpen( nTr, fnFormatDetails(oTable, nTr,beginDate,endDate), 'details' );
                }
            });

            //customerFinancialAccount表格配置
            var oTable = $('#sample_3').dataTable({
                "aoColumns": [
                    {"bSortable": false},  //0
                    null,  //1
                    null,  //2
                    null,  //3
                    null,
                    null,   //4
                    null,  //5
                    null,  //6
                    { "bVisible": false},  //7
                    { "bVisible": false},  //8
                    null,  //9
                    null,  //10
                    { "bVisible": false },  //11
                    { "bVisible": false },  //12
                    { "bVisible": false },  //13
                    { "bVisible": false },  //14
                    { "bVisible": false }   //15
                ],
                "aoColumnDefs": [
                    {
                        //充值总额
                        "aTargets": [ 4 ],
                        "sType": "html-percent"
                    },
                    {
                        //消费总额
                        "aTargets": [ 5 ],
                        "sType": "html-percent"
                    },
                    {
                        //上周充值
                        "aTargets": [ 6 ],
                        "sType": "html-percent"
                    },
                    {
                        //上周消费
                        "aTargets": [ 7 ],
                        "sType": "html-percent"
                    },
                    {
                        //上月充值
                        "aTargets": [ 8 ],
                        "sType": "html-percent"
                    },
                    {
                        //上月消费
                        "aTargets": [ 9 ],
                        "sType": "html-percent"
                    }

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

            /*表格显示列控制*/
            $('#sample_3_column_toggler input[type="checkbox"]').change(function(){
                /* Get the DataTables object again - this is not a recreation, just a get of the object */
                var iCol = parseInt($(this).attr("data-column"));
                var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
                oTable.fnSetColumnVis(iCol, (bVis ? false : true));
            });


        }

    };

}();