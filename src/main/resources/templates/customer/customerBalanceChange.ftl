
<#include "layout.ftl">

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

                        Form Layouts

                        <small>form layouts and more</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="/view/successUrl">首页</a>

                            <span class="icon-angle-right"></span>

                        </li>

                        <li>

                            <a href="#">财务管理</a>

                            <span class="icon-angle-right"></span>

                        </li>

                        <li><a href="#">余额变更</a></li>

                    </ul>

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <!-- BEGIN PAGE CONTENT-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN SAMPLE FORM PORTLET-->

                    <div class="portlet box blue tabbable">

                        <div class="portlet-title">

                            <div class="caption">

                                <i class="icon-reorder"></i>

                                <span class="hidden-480">General Layouts</span>

                            </div>

                        </div>

                        <div class="portlet-body form">

                            <div class="tabbable portlet-tabs">

                                <ul class="nav nav-tabs">

                                    <li><a href="#" data-toggle="tab">Inline</a></li>

                                    <li><a href="#" data-toggle="tab">Grid</a></li>

                                    <li class="active"><a href="#portlet_tab1" data-toggle="tab">Default</a></li>

                                </ul>

                                <div class="tab-content">

                                    <div class="tab-pane active" id="portlet_tab1">

                                        <!-- BEGIN FORM-->
                                        <@shiro.Roles name="backAdmin">
                                            <form action="/customerBalance/customerBalanceChangeAction" class="form-horizontal" method="post" onsubmit="return validateCustomerBalanceLog()">

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

                                                    <label class="control-label">账&nbsp;&nbsp;户<span class="required">*</span></label>

                                                    <div class="controls">

                                                        <input type="text" id="authId" name="authId" class="m-wrap medium">

                                                        <span class="help-inline" id="authIdMsg"></span>

                                                    </div>

                                                </div>

                                                <div class="control-group">

                                                    <label class="control-label">金&nbsp;&nbsp;额<span class="required">*</span></label>

                                                    <div class="controls">

                                                        <input type="text" id="amount" name="amount" class="m-wrap medium">

                                                        <span class="help-inline" id="amountMsg"></span>

                                                    </div>

                                                </div>

                                                <div class="control-group">

                                                    <label class="control-label">原&nbsp;&nbsp;因<span class="required">*</span></label>

                                                    <div class="controls">

                                                        <select id="reasonId" name="reasonId" class="medium m-wrap" tabindex="1">

                                                            <#list customerBalanceModifyReasonList as reasonList>

                                                                <option value="${reasonList.id}">${reasonList.name}</option>

                                                            </#list>

                                                        </select>

                                                    </div>

                                                </div>

                                                <div class="form-actions">
                                                    <button type="submit" class="btn blue"><i class="icon-ok"></i> Save</button>
                                                    <button type="button" class="btn">Cancel</button>
                                                </div>

                                            </form>
                                        </@shiro.Roles>

                                        <@shiro.Roles name="sell">
                                            <form action="/customerBalance/customerBalanceChangeActionCommon" class="form-horizontal" method="post" onsubmit="return validateCustomerBalanceLog()">

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

                                                    <label class="control-label">账&nbsp;&nbsp;户<span class="required">*</span></label>

                                                    <div class="controls">

                                                        <input type="text" id="authId" name="authId" class="m-wrap medium">

                                                        <span class="help-inline" id="authIdMsg"></span>

                                                    </div>

                                                </div>

                                                <div class="control-group">

                                                    <label class="control-label">金&nbsp;&nbsp;额<span class="required">*</span></label>

                                                    <div class="controls">

                                                        <input type="text" id="amount" name="amount" class="m-wrap medium">

                                                        <span class="help-inline" id="amountMsg"></span>

                                                    </div>

                                                </div>

                                                <div class="control-group">

                                                    <label class="control-label">原&nbsp;&nbsp;因<span class="required">*</span></label>

                                                    <div class="controls">

                                                        <select id="reasonId" name="reasonId" class="medium m-wrap" tabindex="1">

                                                            <#list customerBalanceModifyReasonList as reasonList>

                                                                <option value="${reasonList.id}">${reasonList.name}</option>

                                                            </#list>

                                                        </select>

                                                    </div>

                                                </div>

                                                <div class="form-actions">
                                                    <button type="submit" class="btn blue"><i class="icon-ok"></i> Save</button>
                                                    <button type="button" class="btn">Cancel</button>
                                                </div>

                                            </form>
                                        </@shiro.Roles>
                                        <!-- END FORM-->

                                    </div>

                                </div>

                            </div>

                        </div>

                    </div>

                    <!-- END SAMPLE FORM PORTLET-->

                </div>

            </div>

            <!-- END PAGE CONTENT-->

        </div>

        <!-- END PAGE CONTAINER-->

    </div>


    <#elseif section = "footer">
    <script src="/js/myjs/customerbalancelog.js" type="text/javascript" ></script>
    </#if>
<script>
    $(document).ready(function() {
        $('#customerBalance').addClass('active');

        $('#changeCustomerBalance').addClass('active');

        $('#customerBalanceSelect').addClass('selected');

        $('#customerBalanceArrow').addClass('arrow open');
    });
</script>
</@layout>