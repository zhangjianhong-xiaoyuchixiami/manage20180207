
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
                                    <label class="control-label">邮箱<span class="required">*</span></label>
                                    <div class="controls">
                                        <input id="email" name="email" type="text" <#if email??>value="${email}" </#if> class="span6 m-wrap"/>

                                        <span class="help-inline" id="telMsg"><#if UserMessageEmail??><font color="red">${UserMessageEmail}</font></#if></span>
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


        </div>

    </div>


    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

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

    </#if>

</@layout>