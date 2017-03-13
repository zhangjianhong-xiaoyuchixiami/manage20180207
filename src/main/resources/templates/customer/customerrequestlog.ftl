
<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">

                <#--搜索框-->

                    <form action="/company/find-all-company-customer" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom head-search-top">

                                <label class="control-label">&nbsp;&nbsp;</label>

                                <div class="controls">

                                    <div class="input-append">

                                        <input class="m-wrap" <#if content??>value="${content}" </#if> type="text" id="companyName" name="content" placeholder="请输入公司名称">

                                        <button class="btn black" type="submit">搜索</button>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </form>

                <#--表单-->

                    <div class="portlet box grey">

                        <div class="portlet-body no-more-tables">

                            <table class="table table-striped table-hover table-bordered table-condensed" id="customerRequestLogSample_1">
                                <thead>
                                <tr>
                                    <th>公司名称</th>
                                    <th>产品类型</th>
                                    <th>请求内容</th>
                                    <th>创建时间</th>
                                </tr>
                                </thead>
                            </table>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>

    <a id="test-test"></a>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

    <script type="text/javascript" src="/js/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/DT_bootstrap.js"></script>

    <script type="text/javascript">

            //初始化表格
            var table = $("#customerRequestLogSample_1").dataTable({

                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "全部"] // change per page values here
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
                "sAjaxSource": "/customer/find-all-customer-request-log",//这个是请求的地址
                "fnServerData": retrieveData ,// 获取数据的处理函数
                "aoColumns": [
                    { "mDataProp" : "customerId"},
                    { "mDataProp" : "apiTypeId"},
                    { "mDataProp" : "content","bSortable": false},
                    { "mDataProp" : "createTime"}
                ],
                "aaSorting": [[3, 'desc']]
               /* "aoColumnDefs":[
                    {"aTargets":[4],
                        "mData": function (source) {
                            var resObj = {
                                'id' : source.id,
                                'name' : source.name,
                                'username' : source.username
                            };
                            return resObj;
                        },
                        "mRender":function(resObj,type,full){
                            console.log(resObj.id);
                            return '<a href="javaScript:;">删除</a>'
                        }
                    }
                ]*/
            });

            function retrieveData( sSource,aoData, fnCallback) {
                $.ajax({
                    url : sSource,//这个就是请求地址对应sAjaxSource
                    data : {"aoData":JSON.stringify(aoData)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
                    type : 'post',
                    dataType : 'json',
                    async : false,
                    success : function(result) {
                        fnCallback(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的

                    }
                });
            }

    </script>

    </#if>


</@layout>
