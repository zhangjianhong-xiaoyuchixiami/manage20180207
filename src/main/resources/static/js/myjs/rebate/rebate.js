var Rebate = function () {

    return {

        init: function () {


            $('#rebate').addClass('active');

            $('#customerBalance').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');

            $("#agency").select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $("#cid").select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $("#cyc").select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $("#rate").select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $("#cache").select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $("#cid").change(function () {

                var param = $("#cid").val();

                $("#tid_choose ").empty();
                $("#tid_choose").append('<select class="medium m-wrap" multiple id="tid" name="tid"><option value=""></option></select>');

                if (param !=null) {
                    $.ajax({
                        url: '/excel/find-type-by-cid',
                        data: {"cid": param},
                        type: 'post',
                        dataType: 'json',
                        success: function (data) {
                            if(data != null){
                                for (var i=0; i<data.length; i++){
                                    var op=document.createElement("option");
                                    op.value=data[i].id;
                                    op.innerHTML=data[i].name;
                                    $("#tid").append(op);
                                }
                            }
                        }
                    });
                }

                $("#tid").select2({

                    language: "zh-CN",
                    placeholder: "请选择",
                    allowClear: true

                });

            });

            var oTable = $('#sample_1').dataTable({
                "aoColumns": [
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                ],
                "aaSorting": [[0, 'asc']],
                "bPaginate" : true,
                "bLengthChange" : false,
                "iDisplayLength": 15, //每页显示多少行
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

            var tid =[];//定义一个数组
            tid.push($('#tid').val());

            var href = $("#exportExcel").attr('href');

            if(href) {
                href += (href.match(/\?/) ? '&' : '?') + 'cid=' + $.getUrlParam('cid') +
                    (href.match(/\?/) ? '&' : '?') + 'wid=' + $.getUrlParam('wid') +
                    (href.match(/\?/) ? '&' : '?') + 'tid=' + tid +
                    (href.match(/\?/) ? '&' : '?') + 'beginDate=' + $.getUrlParam('beginDate') +
                    (href.match(/\?/) ? '&' : '?') + 'endDate=' + $.getUrlParam('endDate');

                $("#exportExcel").attr('href', href);
            }

        }

    };

}();