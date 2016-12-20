
<#include "../customer/layout.ftl">

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">

                    <h3 class="page-title">

                       分配角色

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

                        <li><a href="#">角色管理</a></li>

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

                                <button class="btn black" id="allotRoleSave">

                                    Save <i class="m-icon-swapright m-icon-white"></i>

                                </button>

                            </div>

                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th style="text-align: center; width: 100px" >角色选择</th>
                                    <th style="text-align: center">角色编号</th>
                                    <th style="text-align: center">角色名称</th>
                                    <th style="text-align: center; display: none">用户名</th>
                                </tr>
                                </thead>
                                <tbody>

                                    <#if roleList??>
                                        <#list roleList as role>

                                        <tr>
                                            <td style="text-align: center">
                                                <input type="checkbox" name="roleId"  <#list userRoleList as userRoleIdList>
                                                       <#if userRoleIdList.roleId==role.id>checked="checked"</#if>

                                                </#list> value="${role.id}" />
                                            </td>
                                            <td>${role.id}</td>
                                            <td>${role.name}</td>
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

                        <#--<label class="control-label">请输入用户名！</label>-->

                    <#--</div>-->

                <#--</div>-->

            <#--</div>-->

        <#--</div>-->

        <#--<div class="modal-footer">-->

            <#--<button class="btn" id="validBtn" data-dismiss="modal" aria-hidden="true">关闭</button>-->

        <#--</div>-->

    <#--</div>-->

    <#elseif section = "footer">

    </#if>
<script>
    $(document).ready(function() {
        $('#userManage').addClass('active');

        $('#userList').addClass('active');

        $('#userManageSelect').addClass('selected');

        $('#userManageArrow').addClass('arrow open');
//
//        $('#validButton').click(function () {
//            $('#form_modal4').css('display','none');
//        });
//
//        $('#validBtn').click(function () {
//            $('#form_modal4').css('display','none');
//        });
    });
</script>
</@layout>