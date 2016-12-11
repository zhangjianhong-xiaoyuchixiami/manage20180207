
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

                        修改密码

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

                        <li><a href="#">修改密码</a></li>

                    </ul>

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <!-- BEGIN PAGE CONTENT-->

            <div class="row-fluid">

                <div class="span12">

                    <div class="portlet box blue" id="form_wizard_1">

                        <div class="portlet-title">

                            <div class="caption">

                                <i class="icon-reorder"></i>

                            </div>

                            <div class="tools hidden-phone">

                                <a href="javascript:;" class="collapse"></a>

                                <a href="#portlet-config" data-toggle="modal" class="config"></a>

                                <a href="javascript:;" class="reload"></a>

                                <a href="javascript:;" class="remove"></a>

                            </div>

                        </div>

                        <div class="portlet-body form">

                            <form action="/user/updatePasswordAction" method="post" class="form-horizontal" id="submit_form" onsubmit="return validateUpdatePassword()">

                                <div class="form-wizard">

                                    <div class="tab-content">

                                        <div class="tab-pane active" id="tab1">

                                            <h3 class="block">请填写信息</h3>

                                            <div class="controls">

                                            </div>
                                            <div class="controls">

                                            </div>
                                            <#if msg??>
                                                <span><h5><font color="red">${msg}</font></h5></span>
                                            <#else>
                                                <span></span>
                                            </#if>

                                            <div class="control-group">

                                                <label class="control-label">用户名<span class="required">*</span></label>

                                                <div class="controls">

                                                    <input type="text" <#if username??>value="${username}" </#if> class="span6 m-wrap" id="username" name="username"/>

                                                    <span class="help-inline" id="usernameMsg"><#if UserMessageUsername??><font color="red">${UserMessageUsername}</font></#if></span>

                                                </div>

                                            </div>

                                            <div class="control-group">

                                                <label class="control-label">旧密码<span class="required">*</span></label>

                                                <div class="controls">

                                                    <input type="password" <#if password??>value="${username}" </#if> class="span6 m-wrap" id="password" name="password"/>

                                                    <span class="help-inline" id="passwordMsg"><#if UserMessagePassword??><font color="red">${UserMessagePassword}</font></#if></span>

                                                </div>

                                            </div>

                                            <div class="control-group">

                                                <label class="control-label">新密码<span class="required">*</span></label>

                                                <div class="controls">

                                                    <input type="password" <#if newPassword??>value="${newPassword}" </#if> class="span6 m-wrap" id="newPassword" name="newPassword"/>

                                                    <span class="help-inline" id="newPasswordMsg"><#if UserMessageNewPassword??><font color="red">${UserMessageNewPassword}</font></#if></span>

                                                    <span class="help-block">e.g：以字母开头，只能有数字、字母、下划线组成，长度为6-18个字符</span>

                                                </div>

                                            </div>

                                            <div class="control-group">

                                                <label class="control-label">再次输入新密码<span class="required">*</span></label>

                                                <div class="controls">

                                                    <input type="password" class="span6 m-wrap" id="rppassword" name="rppassword"/>

                                                    <span class="help-inline" id="rppasswordMsg"><#if UserMessageRpPassword??><font color="red">${UserMessageRpPassword}</font></#if></span>

                                                </div>

                                            </div>

                                        </div>

                                    </div>

                                    <div class="form-actions">

                                        <button type="submit" class="btn blue">提交</button>

                                        <button type="button" class="btn">取消</button>

                                    </div>

                                </div>

                            </form>

                        </div>

                    </div>

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

        $("#username").focus(function () {
            $("#usernameMsg").html("");
        });

        $("#password").focus(function () {
            $("#passwordMsg").html("");
        });

        $("#newPassword").focus(function () {
            $("#newPasswordMsg").html("");
        });

        $("#rppassword").focus(function () {
            $("#rppasswordMsg").html("");
        });
    });
</script>
</@layout>