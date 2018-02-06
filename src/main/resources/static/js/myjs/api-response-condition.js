var ApiResponseCondition = function () {

    return {

        init: function () {

            $('#operation_manage').addClass('active');

            $('#product_condition_response').addClass('active');

            $('#operation_manage_select').addClass('selected');

            $('#operation_manage_arrow').addClass('arrow open');


            function fnFormatDetails ( oTable, nTr )
            {
                var aData = oTable.fnGetData( nTr );
                var sOut = '<table style="width: 100%">';
                sOut += '<tr><th colspan="2">时间范围：今日凌晨--当前</th></tr>';
                sOut +=
                    '<tr><th style="width: 15%">通道：</th><td >'+aData[1]+'</td></tr>' +
                    '<tr><th >供应商：</th><td >'+aData[2]+'</td></tr>' +
                    '<tr><th >今日请求数：</th><td >'+aData[3]+'</td></tr>' +
                    '<tr><th >今日失败数</th><td >'+aData[4]+'</td></tr>' +
                    '<tr><th >今日失败率：</th><td >'+aData[5]+'</td></tr>' +
                    '<tr><th >今日成功数</th><td >'+aData[6]+'</td></tr>' +
                    '<tr><th >[0～500)ms：</th><td >'+aData[7]+'</td></tr>' +
                    '<tr><th >[500～1000)ms：</th><td >'+aData[8]+'</td></tr>' +
                    '<tr><th >[1000～1500)ms：</th><td >'+aData[9]+'</td></tr>' +
                    '<tr><th >[1500～2000)ms：</th><td >'+aData[10]+'</td></tr>' +
                    '<tr><th >[2000～2500)ms：</th><td >'+aData[11]+'</td></tr>' +
                    '<tr><th >[2500～3000)ms：</th><td >'+aData[12]+'</td></tr>' +
                    '<tr><th >[3000～+∞)ms：</th><td >'+aData[13]+'</td></tr>' +
                    '<tr><th >标签：</th><td >'+aData[14]+'</td></tr>';
                sOut += '</table>';
                return sOut;
            }

            var nCloneTh = document.createElement( 'th' );
            var nCloneTd = document.createElement( 'td' );
            nCloneTd.innerHTML = '<span class="row-details row-details-close"></span>';
            $('#sample_2 thead tr').each( function () {
                this.insertBefore( nCloneTh, this.childNodes[0] );
            } );

            $('#sample_2 tbody tr').each( function () {
                this.insertBefore(  nCloneTd.cloneNode( true ), this.childNodes[0] );
            } );

            $('#sample_2').on('click', ' tbody td .row-details', function () {
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

            //customerFinancialAccount表格配置
            var oTable = $('#sample_2').dataTable({
                "aoColumns": [
                    {
                        "bSortable": false
                    },  //展开符号 0
                    null,  //产品名1
                    null,  //供应商名 2
                    null,  //请求总数 3
                    null,  //失败数 6
                    null,  //失败率 4
                    {"bVisible": false},  //成功数 7
                    null,  //0-500ms 5
                    {"bVisible": false},  //0-500ms 8
                    {"bVisible": false},  //0-500ms 9
                    {"bVisible": false},  //0-500ms 10
                    {"bVisible": false},  //0-500ms 11
                    {"bVisible": false},  //0-500ms 12
                    {"bVisible": false},  //0-500ms 13
                    {"bVisible": false},  //0-500ms 14
                    {"bSortable": false},  //走势 15
                    {
                        "bSortable": false
                    } //加标签 16
                ],
                "aoColumnDefs": [
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
                    }
                ],
                "aaSorting": [[3, 'desc']],
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

            // /*表格显示列控制*/
            // $('#sample_2_column_toggler input[type="checkbox"]').change(function(){
            //     /* Get the DataTables object again - this is not a recreation, just a get of the object */
            //     var iCol = parseInt($(this).attr("data-column"));
            //     var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
            //     oTable.fnSetColumnVis(iCol, (bVis ? false : true));
            // });

        }

    };

}();