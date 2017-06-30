<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<#import "../publicPart/publicJs.ftl" as puj>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content" xmlns="http://www.w3.org/1999/html">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">

                    <div class="portlet box grey">

                        <div class="portlet-body no-more-tables">

                            <table class="table table-striped table-hover table-bordered table-condensed table-full-width cf" id="companySample_1">
                                <thead class="cf">
                                <tr>
                                    <th>公司名称</th>
                                    <th class="numeric">信用额度</th>
                                    <th class="numeric">可用额度</th>
                                    <th class="numeric">余额</th>
                                    <th>账号authId</th>
                                    <th class="numeric">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td data-title="公司名称">北京千眼数合科技</td>
                                    <td data-title="信用额度" class="numeric">1000</td>
                                    <td data-title="可用额度" class="numeric">2000</td>
                                    <td data-title="余额" class="numeric">1000</td>
                                    <td data-title="账号authId">
                                        <div>
                                            <table id="companySample_1_detail">
                                                <tr>
                                                    <th>编号</th>
                                                    <th>姓名</th>
                                                    <th>年龄</th>
                                                </tr>
                                                <tr>
                                                    <td>1</td>
                                                    <td>小徐</td>
                                                    <td>20</td>
                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td>小张</td>
                                                    <td>18</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </td>
                                    <td data-title="操作" class="numeric">
                                        <a>产品权限管理</a>
                                        &nbsp;|&nbsp;
                                        <a>Ip管理</a>
                                        &nbsp;|&nbsp;
                                        <a>充值</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td data-title="公司名称">北京千眼数合科技</td>
                                    <td data-title="信用额度" class="numeric">1000</td>
                                    <td data-title="可用额度" class="numeric">2000</td>
                                    <td data-title="余额" class="numeric">1000</td>
                                    <td data-title="账号authId">
                                        <div>
                                            <table id="companySample_1_detail">
                                                <tr>
                                                    <th>编号</th>
                                                    <th>姓名</th>
                                                    <th>年龄</th>
                                                </tr>
                                                <tr>
                                                    <td>1</td>
                                                    <td>小徐</td>
                                                    <td>20</td>
                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td>小张</td>
                                                    <td>18</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </td>
                                    <td data-title="操作" class="numeric">
                                        <a>产品权限管理</a>
                                        &nbsp;|&nbsp;
                                        <a>Ip管理</a>
                                        &nbsp;|&nbsp;
                                        <a>充值</a>
                                    </td>
                                </tr>
                                </tbody>

                            </table>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

        <@puj.publicJs></@puj.publicJs>

    <script src="/js/former/jquery.dataTables.js" type="text/javascript" ></script>

    <script src="/js/former/DT_bootstrap.js" type="text/javascript" ></script>


    <script type="text/javascript">

        jQuery(document).ready(function() {

            function fnFormatDetails ( oTable, nTr ) {
                var aData = oTable.fnGetData( nTr );
                var sOut = aData[5];
                return sOut;
            }

            var nCloneTh = document.createElement( 'th' );
            var nCloneTd = document.createElement( 'td' );
            nCloneTd.innerHTML = '<span class="row-details row-details-close"></span>';
            //nCloneTh.innerHTML = '<span class="row-details row-details-close"></span>';

            $('#companySample_1 thead tr').each( function () {
                this.insertBefore( nCloneTh, this.childNodes[0] );
            } );

            $('#companySample_1 tbody tr').each( function () {

                this.insertBefore(  nCloneTd.cloneNode( true ), this.childNodes[0] );

               $("#companySample_1_detail")

            } );

            $('#companySample_1').on('click', ' tbody td .row-details', function () {
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


            var oTable = $('#companySample_1').dataTable({
                "aoColumns": [
                    { "bSortable": false},  //0  展开符号
                    null,  //2  companyName
                    null,  //3  floor
                    null,  //4  superFloor
                    null,  //5  balance
                    { "bSortable": false},  //6 authId
                    { "bSortable": false}  // 7 公司操作
                ],
                "aaSorting": [[6, 'desc']],
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

        });

    </script>


    </#if>

</@layout>