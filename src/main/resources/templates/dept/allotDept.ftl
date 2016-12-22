
<#include "../customer/layout.ftl">

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">

                    <h3 class="page-title">

                        分配部门

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="/view/successUrl">首页</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li>

                            <a href="#">客户管理</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#">Ip列表</a></li>

                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <div class="row-fluid">

                <div class="span12">

                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-cogs"></i>Table</div>

                            <div class="tools">

                                <a href="javascript:;" class="collapse"></a>

                                <a href="#portlet-config" data-toggle="modal" class="config"></a>

                                <a href="javascript:;" class="reload"></a>

                                <a href="javascript:;" class="remove"></a>

                            </div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="btn-group">

                                <button class="btn black" id="allotDeptSave">

                                    Save <i class="m-icon-swapright m-icon-white"></i>

                                </button>

                            </div>

                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th style="text-align: center; width: 100px">部门选择</th>
                                    <th style="text-align: center">部门名称</th>
                                    <th style="text-align: center; display: none" >用户Id</th>
                                    <th style="text-align: center; display: none">用户名</th>
                                </tr>
                                </thead>
                                <tbody>

                                    <#if deptList??>
                                        <#list deptList as dept>

                                        <tr>
                                            <td style="text-align: center">
                                                <input type="checkbox" name="deptId"
                                                    <#list userDeptIdList as deptIdList>
                                                        <#if deptIdList==dept.id>
                                                       checked="checked"
                                                        </#if>
                                                    </#list>
                                                       value="${dept.id}" />
                                            </td>

                                            <td>${dept.deptName}</td>
                                            <td style="display: none"><input type="text" name="userId" value="${userId}" /></td>
                                            <td style="display: none"><input type="text" name="username" value="${username}" /></td>
                                        </tr>

                                        </#list>
                                    </#if>

                                </tbody>

                            </table>


                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>

    <#--<div id="form_modal4" class="modal hide fade in" tabindex="-1" aria-labelledby="myModalLabel4" aria-hidden="false" style="display: none; margin-top: -125px;">-->

    <#--<div class="modal-header">-->

    <#--<button type="button" id="validButton" class="close" data-dismiss="modal" aria-hidden="true"></button>-->

    <#--<h3 id="myModalLabel4">提示信息</h3>-->

    <#--</div>-->

    <#--<div class="modal-body">-->

    <#--<div class="control-group">-->

    <#--<div class="controls">-->

    <#--<div class="input-append bootstrap-timepicker-component">-->

    <#--<label id="tiShiXinXi" class="control-label"></label>-->

    <#--</div>-->

    <#--</div>-->

    <#--</div>-->

    <#--</div>-->

    <#--<div class="modal-footer">-->

    <#--<button class="btn" id="validBtn" data-dismiss="modal" aria-hidden="true">确定</button>-->

    <#--</div>-->

    <#--</div>-->
    <script type="text/javascript" src="/js/myjs/allotdept.js"></script>

    <script>
        $(document).ready(function() {
            $('#userManage').addClass('active');

            $('#userList').addClass('active');

            $('#userManageSelect').addClass('selected');

            $('#userManageArrow').addClass('arrow open');

//        $('#validButton').click(function () {
//            $('#form_modal4').css('display','none');
//        });
//
//        $('#validBtn').click(function () {
//            $('#form_modal4').css('display','none');
//        });
        });
    </script>

    <#elseif section = "footer">

    </#if>

</@layout>