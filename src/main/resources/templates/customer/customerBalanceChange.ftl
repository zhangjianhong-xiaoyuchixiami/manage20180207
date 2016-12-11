
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

                      余额变更

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

                                <span class="hidden-480">请填写余额变更信息</span>

                            </div>

                        </div>

                        <div class="portlet-body form">

                            <div class="tabbable portlet-tabs">

                                <ul class="nav nav-tabs">

                                    <li><a href="#" data-toggle="tab">Inline</a></li>

                                    <li><a href="#" data-toggle="tab">Grid</a></li>

                                    <li><a href="#portlet_tab1" data-toggle="tab">Default</a></li>

                                </ul>

                                <div class="tab-content">

                                    <div class="tab-pane active" id="portlet_tab1">

                                        <!-- BEGIN FORM-->

                                        <form action="/customerBalance/customerBalanceChangeAction" class="form-horizontal" method="post" >

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

                                                    <input type="text" id="authId" name="authId" <#if authId??>value="${authId}"</#if> class="m-wrap medium">

                                                    <span class="help-inline" id="authIdMsg"><#if CustomerMessageAuthId??><font color="red">${CustomerMessageAuthId}</font></#if></span>

                                                    <span class="help-block">e.g：只能有数字、字母、下划线组成</span>

                                                </div>

                                            </div>

                                            <div class="control-group">

                                                <label class="control-label">金&nbsp;额（/：分）<span class="required">*</span></label>

                                                <div class="controls">

                                                    <input type="text" id="amount" name="amount" <#if amount??>value="${amount}"</#if> class="m-wrap medium">

                                                    <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                    <span class="help-block">e.g：只能输入数字并且金额大于0</span>

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

                                                    <span class="help-inline" id="reasonIdMsg"><#if CustomerMessageReasonId??><font color="red">${CustomerMessageReasonId}</font></#if></span>

                                                </div>

                                            </div>

                                            <div class="form-actions">
                                                <button type="submit" class="btn blue"><i class="icon-ok"></i> 提交</button>
                                                <button type="button" class="btn">取消</button>
                                            </div>

                                        </form>


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

    </#if>
<script>
    $(document).ready(function() {
        $('#customerBalance').addClass('active');

        $('#changeCustomerBalance').addClass('active');

        $('#customerBalanceSelect').addClass('selected');

        $('#customerBalanceArrow').addClass('arrow open');

        $("#authId").focus(function () {
            $("#authIdMsg").html("");
        });

        $("#amount").focus(function () {
            $("#amountMsg").html("");
        });

        $("#reasonId").focus(function () {
            $("#reasonIdMsg").html("");
        });

        $("#authId").blur(function(){
            $("#authIdMsg").load("/customer/findCustomerByAuthId/"+$("#authId").val(),
                    function(responseTxt){
                        if(responseTxt=="yes")
                            $("#authIdMsg").html("");
                        if(responseTxt=="no")
                            $("#authIdMsg").html("<font color='red'>该账户不存在！</font>");
                    });
        });
    });
</script>
</@layout>