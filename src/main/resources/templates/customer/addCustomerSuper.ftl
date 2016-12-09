<#include "layout.ftl">

<@layout ; section>
    <#if section = "head">



    <#elseif section = "content" >

    <div class="page-content">

        <div id="portlet-config" class="modal hide">

            <div class="modal-header">

                <button data-dismiss="modal" class="close" type="button"></button>

                <h3>portlet Settings</h3>

            </div>

            <div class="modal-body">

                <p>Here will be a configuration form</p>

            </div>

        </div>

        <div class="container-fluid">

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

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        新增客户

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

                        <li><a href="#">新增客户</a></li>

                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN VALIDATION STATES-->

                    <div class="portlet box blue">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-reorder"></i>Basic Validation</div>

                            <div class="tools">

                                <a href="javascript:;" class="collapse"></a>

                                <a href="#portlet-config" data-toggle="modal" class="config"></a>

                                <a href="javascript:;" class="reload"></a>

                                <a href="javascript:;" class="remove"></a>

                            </div>

                        </div>

                        <div class="portlet-body form">

                            <!-- BEGIN FORM-->

                                <form action="/customer/insertCustomer" id="form_sample_1" class="form-horizontal" method="post" onsubmit="return validateCustomer()">

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

                                        <label class="control-label">公司名称<span class="required">*</span></label>

                                        <div class="controls">

                                            <input type="text" id="customer.name" name="customer.name" class="span6 m-wrap"/>

                                            <span id="customer.nameMsg" class="help-block"></span>

                                        </div>

                                    </div>

                                    <div class="control-group">

                                        <label class="control-label">账&nbsp;&nbsp;户<span class="required">*</span></label>

                                        <div class="controls">

                                            <input type="text" id="customer.authId" name="customer.authId" class="span6 m-wrap"/>

                                            <span id="customer.authIdMsg" class="help-block"></span>

                                        </div>

                                    </div>

                                    <div class="control-group">

                                        <label class="control-label">所属部门<span class="required">*</span></label>

                                        <#if deptList??>

                                            <div class="controls">

                                                <select id="dept.deptNo" name="dept.deptNo" class="medium m-wrap" tabindex="1">

                                                    <#list deptList as dept>

                                                        <option value="${dept.deptNo}">${dept.deptName}</option>

                                                    </#list>

                                                </select>

                                            </div>

                                        </#if>

                                    </div>

                                    <div class="form-actions">

                                        <button type="submit" id="SubmitAddCustomer" class="btn blue">提交</button>

                                        <button type="reset" class="btn">重置</button>

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

    </#if>


<script>
    $(document).ready(function() {
        $('#customerManage').addClass('active');

        $('#addCustomer').addClass('active');

        $('#customerManageSelect').addClass('selected');

        $('#customerManageArrow').addClass('arrow open');
    });
</script>
</@layout>