
<#include "../customer/layout.ftl">

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">

                    <a id="test-test" href="javascript:;">
                        Test
                    </a>

                    <div class="portlet box light-grey">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-globe"></i>Managed Table</div>

                            <div class="tools">

                                <a href="javascript:;" class="collapse"></a>

                                <a href="#portlet-config" data-toggle="modal" class="config"></a>

                                <a href="javascript:;" class="reload"></a>

                                <a href="javascript:;" class="remove"></a>

                            </div>

                        </div>

                        <div class="portlet-body">

                            <table class="table table-striped table-bordered table-hover" id="sample_5">

                                <thead>

                                <tr>

                                    <th>id</th>

                                    <th>姓名</th>

                                    <th>用户名</th>

                                    <th>电话</th>

                                    <th>操作</th>

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

    <script type="text/javascript">

        $("#test-test").on("click",function (){

            //初始化表格
            var table = $("#sample_5").dataTable({

                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "全部"] // change per page values here
                ],
                "iDisplayLength": 15, //每页显示多少行
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
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
                "sAjaxSource": "/test/list",//这个是请求的地址
                "fnServerData": retrieveData ,// 获取数据的处理函数
                "aoColumns": [
                    { "mDataProp" : "id"},
                    { "mDataProp" : "name"},
                    { "mDataProp" : "username"},
                    { "mDataProp" : "tel","bSortable": false},
                    { "bSortable": false}
                ],
                "aaSorting": [[1, 'desc']],
                "aoColumnDefs":[
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

        });

    </script>


    </#if>

</@layout>
