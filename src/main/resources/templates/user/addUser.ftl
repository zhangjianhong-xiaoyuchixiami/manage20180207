
<#include "../customer/layout.ftl">

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">

                    <h3 class="page-title">
                        新增管理员
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="/view/successUrl">首页</a>
                            <span class="icon-angle-right"></span>
                        </li>
                        <li>
                            <a href="#">管理员</a>
                            <span class="icon-angle-right"></span>
                        </li>
                        <li><a href="#">新增管理员</a></li>
                    </ul>
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->

            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN VALIDATION STATES-->
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-reorder"></i>请填写管理员信息</div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse"></a>
                                <a href="#portlet-config" data-toggle="modal" class="config"></a>
                                <a href="javascript:;" class="reload"></a>
                                <a href="javascript:;" class="remove"></a>
                            </div>
                        </div>
                        <div class="portlet-body form">

                            <!-- BEGIN FORM-->
                            <form action="/user/addUserAction" method="post" id="form_sample_1" class="form-horizontal">
                                <div class="control-group"></div>

                                <div class="control-group"></div>

                                <#if msg??>

                                    <div class="alert alert-error show">

                                        <button class="close" data-dismiss="alert"></button>

                                    ${msg}

                                    </div>

                                </#if>
                                <div class="control-group">
                                    <label class="control-label">姓名<span class="required">*</span></label>
                                    <div class="controls">
                                        <input type="text" id="name" name="name" <#if name??>value="${name}" </#if> data-required="1" class="span6 m-wrap"/>

                                        <span class="help-inline" id="nameMsg"><#if UserMessageName??><font color="red">${UserMessageName}</font></#if></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">用户名<span class="required">*</span></label>
                                    <div class="controls">
                                        <input id="username" name="username" type="text" <#if username??>value="${username}" </#if> class="span6 m-wrap"/>

                                        <span class="help-inline" id="usernameMsg"><#if UserMessageUsername??><font color="red">${UserMessageUsername}</font></#if></span>

                                        <span class="help-block">e.g：只能有数字、字母、下划线组成</span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">电话<span class="required">*</span></label>
                                    <div class="controls">
                                        <input id="tel" name="tel" type="text" <#if tel??>value="${tel}" </#if> class="span6 m-wrap"/>

                                        <span class="help-inline" id="telMsg"><#if UserMessageTel??><font color="red">${UserMessageTel}</font></#if></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">账号状态<span class="required">*</span></label>
                                    <div class="controls">
                                        <select class="span6 m-wrap" name="status">

                                            <option value="0">启用</option>
                                            <option value="1">禁用</option>

                                        </select>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">管理员类型<span class="required">*</span></label>
                                    <div class="controls">
                                        <select class="span6 m-wrap" name="typeId">

                                            <option value="1">超级管理员</option>
                                            <option value="2">普通管理员</option>

                                        </select>
                                    </div>
                                </div>

                                <div class="form-actions">
                                    <button type="submit" class="btn black">提交</button>
                                    <a href="/view/successUrl"><button type="button" class="btn">取消</button></a>
                                </div>
                            </form>
                            <!-- END FORM-->
                        </div>
                    </div>
                    <!-- END VALIDATION STATES-->
                </div>
            </div>

            <!-- END PAGE CONTENT-->
        </div>
        <!-- END PAGE CONTAINER-->
    </div>


    <#elseif section = "footer">

    </#if>
<script>
    $(document).ready(function() {
        $('#userManage').addClass('active');

        $('#addUser').addClass('active');

        $('#userManageSelect').addClass('selected');

        $('#userManageArrow').addClass('arrow open');

        $("#name").focus(function () {
            $("#nameMsg").html("");
        });

        $("#username").focus(function () {
            $("#usernameMsg").html("");
        });

        $("#tel").focus(function () {
            $("#telMsg").html("");
        });
    });
</script>
</@layout>