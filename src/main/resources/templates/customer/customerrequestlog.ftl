
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

                <#--<form action="/company/find-all-company-customer" method="get">

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

                </form>-->

                <#--表单-->

                    <div class="portlet box grey">

                        <div class="portlet-body">

                            <table class="table table-striped table-hover table-bordered table-condensed" id="customerRequestLogSample_1">
                                <thead>
                                <tr>
                                    <th>公司名称</th>
                                    <th>产品类型</th>
                                    <th>请求内容</th>
                                    <th>创建时间</th>
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

    <script type="text/javascript" src="/js/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/DT_bootstrap.js"></script>

    <script type="text/javascript" src="/js/myjs/customer-request-log.js"></script>


    <script type="text/javascript">

        //初始化表格
        var table = $("#customerRequestLogSample_1").dataTable({

            "aLengthMenu": [
                [10, 15, 20, -1],
                [10, 15, 20, "全部"] // change per page values here
            ],
            /*"sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",*/
            "sDom": "rt<'row-fluid'<'span6'il><'span6'p>>",
            //语法结构
            /*
                <>
             表示一个闭合DIV
                例：<>
             = <div></div>

                <"类名称">
             表示一个带类名称的闭合DIV
                例：<"top">
             = <div class="top"></div>

                l
             - 每行显示的记录数
                f
             - 搜索框
                t
             - 表格
                i
             - 表格信息
                p
             - 分页条
                r
             - 加载时的进度条
            */

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
            "bFilter": false, //设置全文搜索框，默认true
            "bProcessing": true,// 是否显示取数据时的那个等待提示
            "bServerSide": true,//这个用来指明是通过服务端来取数据
            "sAjaxDataProp": "aaData",
            "sAjaxSource": "/customer/find-all-customer-request-log-ajax",//这个是请求的地址
            "fnServerData": retrieveData,// 获取数据的处理函数
            "aoColumns": [
                {
                    "name": "公司名称",
                    "bSortable": false
                },
                {
                    "name": "产品类型",
                    "bSortable": false
                },
                {
                    "name": "请求内容",
                    "bSortable": false
                },
                {
                    "name": "请求时间"
                }
            ],
            "aaSorting": [[3, 'desc']],
            "aoColumnDefs":[
                {"aTargets":[0],
                    "mData": function (source) {
                        var resObj = {
                            'customerId' : source.customerId
                        };
                        return resObj;
                    },
                    "mRender":function(resObj,type,full){
                        if (full.company != null){
                            return full.company.name;
                        }
                        return "";
                    }
                },
                {"aTargets":[1],
                    "mData": function (source) {
                        var resObj = {
                            'apiTypeName' : source.apiType.name
                        };
                        return resObj;
                    },
                    "mRender":function(resObj,type,full){
                        if (full.mobileOperator != null){
                            return full.apiType.name+'--'+full.mobileOperator.name;
                        }
                        return full.apiType.name;
                    }
                },
                {"aTargets":[2],
                    "mData": function (source) {
                        var resObj = {
                            'id' : source.id
                        };
                        return resObj;
                    },
                    "mRender":function(resObj,type,full){
                        var id = full.id;
                        console.log(id);
                        return "<a href='/customer/find-all-customer-request-log/content?id="+id+"'>点击显示请求内容</a>";
                    }
                },
                {"aTargets":[3],
                    "mData": function (source) {
                        var resObj = {
                            'createTime' : source.createTime.time
                        };
                        return resObj;
                    },
                    "mRender":function(resObj,type,full){
                        return new Date(full.createTime.time).toLocaleString();
                    }
                }
            ]
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

        $('#CustomerRequestLog').addClass('active');

    </script>

    </#if>

</@layout>
