
<#include "../customer/layout.ftl">

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">
        <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
        <div id="portlet-config" class="modal hide">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"></button>
                <h3>portlet Settings</h3>
            </div>
            <div class="modal-body">
                <p>Here will be a configuration form</p>
            </div>
        </div>
        <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
        <!-- BEGIN PAGE CONTAINER-->
        <div class="container-fluid">
            <!-- BEGIN PAGE HEADER-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN STYLE CUSTOMIZER -->
                    <div class="color-panel hidden-phone">
                        <div class="color-mode-icons icon-color"></div>
                        <div class="color-mode-icons icon-color-close"></div>
                        <div class="color-mode">
                            <p>THEME COLOR</p>
                            <ul class="inline">
                                <li class="color-black current color-default" data-style="default"></li>
                                <li class="color-blue" data-style="blue"></li>
                                <li class="color-brown" data-style="brown"></li>
                                <li class="color-purple" data-style="purple"></li>
                                <li class="color-grey" data-style="grey"></li>
                                <li class="color-white color-light" data-style="light"></li>
                            </ul>
                            <label>
                                <span>Layout</span>
                                <select class="layout-option m-wrap small">
                                    <option value="fluid" selected>Fluid</option>
                                    <option value="boxed">Boxed</option>
                                </select>
                            </label>
                            <label>
                                <span>Header</span>
                                <select class="header-option m-wrap small">
                                    <option value="fixed" selected>Fixed</option>
                                    <option value="default">Default</option>
                                </select>
                            </label>
                            <label>
                                <span>Sidebar</span>
                                <select class="sidebar-option m-wrap small">
                                    <option value="fixed">Fixed</option>
                                    <option value="default" selected>Default</option>
                                </select>
                            </label>
                            <label>
                                <span>Footer</span>
                                <select class="footer-option m-wrap small">
                                    <option value="fixed">Fixed</option>
                                    <option value="default" selected>Default</option>
                                </select>
                            </label>
                        </div>
                    </div>
                    <!-- END BEGIN STYLE CUSTOMIZER -->
                    <h3 class="page-title">
                        新增管理员
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="#">首页</a>
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
                    <div class="portlet box blue">
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
                                <div class="alert alert-error hide">
                                    <button class="close" data-dismiss="alert"></button>
                                    You have some form errors. Please check below.
                                </div>
                                <div class="alert alert-success hide">
                                    <button class="close" data-dismiss="alert"></button>
                                    Your form validation is successful!
                                </div>
                                <div class="control-group">

                                </div>
                                <div class="control-group">

                                </div>
                                <div class="controls">
                                    <#if msg??>
                                        <span><h5><font color="red">${msg}</font></h5></span>
                                    <#else>
                                        <span></span>
                                    </#if>
                                </div>
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
                                    <button type="submit" class="btn blue">提交</button>
                                    <button type="button" class="btn">取消</button>
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