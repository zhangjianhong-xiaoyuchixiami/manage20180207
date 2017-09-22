var RebateDetail = function () {

    return {

        init: function () {


            $('#rebate').addClass('active');

            $('#customerBalance').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');


            $("#tid").select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $("#cyc").select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            function fnFormatDetails ( oTable, nTr)
            {
                var aData = oTable.fnGetData( nTr );
                var sOut = '<table style="width: 100%">';
                sOut += '<tr><th style="width: 12%">客户名称:</th><td>'+ aData[2] +'</td></tr>';
                sOut += '<tr><th>周期:</th><td>'+ aData[3] +'</td></tr>';
                sOut += '<tr><th>产品名称:</th><td>'+ aData[4] +'</td></tr>';
                sOut += '<tr><th>类别:</th><td>'+ aData[5] +'</td></tr>';
                sOut += '<tr><th>供应商:</th><td>'+ aData[6] +'</td></tr>';
                sOut += '<tr><th>扣费量:</th><td>'+ aData[7] +'</td></tr>';
                sOut += '<tr><th>单价:</th><td>'+ aData[8] +'</td></tr>';
                sOut += '<tr><th>售价:</th><td>'+ aData[9] +'</td></tr>';
                sOut += '<tr><th>业务回扣起始价:</th><td>'+ aData[10] +'</td></tr>';
                sOut += '<tr><th>业务回扣结算价:</th><td>'+ aData[11] +'</td></tr>';
                sOut += '<tr><th>成本:</th><td>'+ aData[12] +'</td></tr>';
                sOut += '<tr><th>销售额:</th><td>'+ aData[13] +'</td></tr>';
                sOut += '<tr><th>毛利润:</th><td>'+ aData[14] +'</td></tr>';
                sOut += '<tr><th>首次业务回扣:</th><td>'+ aData[15] +'</td></tr>';
                sOut += '<tr><th>二次业务回扣:</th><td>'+ aData[16] +'</td></tr>';
                sOut += '<tr><th>净利润:</th><td>'+ aData[17] +'</td></tr>';
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
                    {"bSortable": false},
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                ],
                "aoColumnDefs": [
                    {
                        "aTargets": [ 0 ],
                        "sType": "html-percent",
                        "sWidth": "3%"
                    },
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
                    },
                    {
                        "aTargets": [ 10 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 11 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 12 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 13 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 14 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 15 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 16 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 17 ],
                        "sType": "html-percent"
                    }
                ],
                "aaSorting": [[3, 'desc']],
                "bPaginate" : true,
                "bLengthChange" : false,
                "iDisplayLength": 10, //每页显示多少行
                "sDom": "t<'row-fluid'<'span6'i><'span6'p>>",
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

            /*表格显示列控制*/
            $('#sample_1_column_toggler input[type="checkbox"]').change(function(){
                /* Get the DataTables object again - this is not a recreation, just a get of the object */
                var iCol = parseInt($(this).attr("data-column"));
                var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
                oTable.fnSetColumnVis(iCol, (bVis ? false : true));
            });

            /*多选框*/
            jQuery('#sample_1 .group-checkable').change(function () {
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

        }

    };

}();