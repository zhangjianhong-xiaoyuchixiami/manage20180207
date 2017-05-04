
<#include "../publicPart/layout.ftl">

<#import "../publicPart/publicJs.ftl" as puj>

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">

                    <form action="/test/list-view" class="form-bottom" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom head-search-top">

                                <label class="control-label">&nbsp;&nbsp;</label>

                                <div class="controls">

                                    <div class="input-append">

                                        <input class="m-wrap" <#if userId??>value="${userId}" </#if> type="text" id="userId" name="userId" placeholder="请输入用户Id">

                                        <button class="btn black" type="submit">搜索</button>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </form>

                    <div class="portlet box light-grey">

                        <div class="portlet-body">

                            <table class="table table-striped table-bordered table-hover" id="sample_5">

                                <thead>

                                <tr>

                                    <th>id</th>

                                    <th>邮箱</th>

                                    <th>状态</th>

                                    <th>类型</th>

                                </tr>

                                </thead>

                                <tbody>

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

    <script type="text/javascript" src="/js/former/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/former/DT_bootstrap.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function () {

            //初始化表格
            var table = $("#sample_5").dataTable({

                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "全部"] // change per page values here
                ],
                "sDom": "<'span12 text-center'r>t<'row-fluid'<'span6'il><'span6'p>>",
                "iDisplayLength": 15, //每页显示多少行
                "sPaginationType": "bootstrap",
                "oLanguage" : {  //设置语言
                    "sLengthMenu" : "每页显示 _MENU_ 条记录",
                    "sZeroRecords" : "对不起，没有匹配的数据",
                    "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
                    "sInfoEmpty" : "没有匹配的数据",
                    "sInfoFiltered" : "(数据表中共 _MAX_ 条记录)",
                    "sProcessing" : "正在加载中...",
                    "oPaginate" : {
                        "sFirst" : "第一页",
                        "sPrevious" : " 上一页 ",
                        "sNext" : " 下一页 ",
                        "sLast" : " 最后一页 "
                    }
                },
                "bFilter" : false, //设置全文搜索框，默认true
                "bProcessing": true ,// 是否显示取数据时的那个等待提示
                "bServerSide": true,//这个用来指明是通过服务端来取数据
                "sAjaxDataProp" : "aaData",
                "sAjaxSource": "/test/list-ajax",//这个是请求的地址
                "fnServerData": retrieveData ,// 获取数据的处理函数
                "aoColumns": [
                    { "mDataProp" : "id"},
                    { "mDataProp" : "email"},
                    { "mDataProp" : "status"},
                    { "mDataProp" : "typeId","bSortable": false}
                ],
                "aaSorting": [[1, 'desc']]
            });

            function retrieveData( sSource,aaData, fnCallback) {

                var userId = $('#userId').val();

                $.ajax({
                    url : sSource,//这个就是请求地址对应sAjaxSource
                    data : {"aaData":JSON.stringify(aaData),"userId":userId},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
                    type : 'post',
                    dataType : 'json',
                    async : false,
                    success : function(result) {
                        fnCallback(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
                    }
                });
            }

        });

    </script>


    </#if>

</@layout>
