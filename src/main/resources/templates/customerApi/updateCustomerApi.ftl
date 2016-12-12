
<#include "../customer/layout.ftl">

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">

                    <h3 class="page-title">

                       添加Api

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="/view/successUrl">首页</a>

                            <span class="icon-angle-right"></span>

                        </li>

                        <li>

                            <a href="#">客户管理</a>

                            <span class="icon-angle-right"></span>

                        </li>

                        <li><a href="#">修改Api</a></li>

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

                                <span class="hidden-480">请填写Api信息</span>

                            </div>

                        </div>

                        <div class="portlet-body form">

                            <div class="tabbable portlet-tabs">

                                <ul class="nav nav-tabs">

                                    <li><a href="#" data-toggle="tab">Inline</a></li>

                                    <li><a href="#" data-toggle="tab">Grid</a></li>

                                    <li><a href="#" data-toggle="tab">Default</a></li>

                                </ul>

                                <div class="tab-content">

                                    <div class="tab-pane active" id="portlet_tab1">

                                        <!-- BEGIN FORM-->

                                        <form action="/customerApi/updateCustomerApiById" class="form-horizontal" method="post" onsubmit="return validateCustomerApi()">
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

                                            <div class="control-group" style="display: none">

                                                <label class="control-label">ID<span class="required">*</span></label>

                                                <div class="controls">

                                                    <input type="text" id="id" name="id" value="${customerApi.id}" class="m-wrap medium">

                                                    <span id="idMsg" class="help-inline"></span>

                                                </div>

                                            </div>

                                            <div class="control-group" style="display: none">

                                                <label class="control-label">customerId<span class="required">*</span></label>

                                                <div class="controls">

                                                    <input type="text" id="customerId" name="customerId" value="${customerApi.customerId}" class="m-wrap medium">

                                                    <span id="customerIdMsg" class="help-inline"></span>

                                                </div>

                                            </div>

                                            <div class="control-group">

                                                <label class="control-label">价&nbsp;格（/:分）<span class="required">*</span></label>

                                                <div class="controls">

                                                    <input type="text" id="price" name="price" value="${customerApi.price}" class="m-wrap medium">

                                                    <span id="priceMsg" class="help-inline"><#if CustomerMessagePrice??><font color="red">${CustomerMessagePrice}</font></font></#if></span>

                                                    <span class="help-block">e.g：只能输入数字并且金额大于0</span>
                                                </div>

                                            </div>

                                            <div class="control-group">

                                                <label class="control-label">Api<span class="required">*</span></label>

                                                <div class="controls">

                                                    <select id="apiId" name="apiId" class="medium m-wrap" tabindex="1">

                                                        <#list apiList as api>

                                                            <option value="${api.id}">${api.apiVendor.name}-${api.name}</option>

                                                        </#list>

                                                    </select>
                                                    <span class="help-inline">（原Api：${customerApi.apiVendor.name}-${customerApi.api.name}）</span>

                                                </div>

                                            </div>

                                            <div class="control-group">

                                                <label class="control-label">激活状态<span class="required">*</span></label>

                                                <div class="controls">

                                                    <select id="enabled" name="enabled" class="medium m-wrap" tabindex="1">

                                                        <option value="true">是</option>

                                                        <option value="false">否</option>

                                                    </select>

                                                    <span class="help-inline">（原状态：<#if customerApi.enabled>
                                                        已激活
                                                    </#if>
                                                        <#if !customerApi.enabled>
                                                            未激活
                                                        </#if >）
                                                    </span>

                                                </div>

                                            </div>

                                            <div class="form-actions">
                                                <button type="submit" class="btn blue"><i class="icon-ok"></i> 提交</button>
                                                <a href="/customerApi/customerApiListAction/${customerApi.customerId}"></a><button type="button" class="btn">取消</button>
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
    <script src="/js/myjs/customerapi.js" type="text/javascript" ></script>
    </#if>
<script>
    $(document).ready(function() {
        $('#customerManage').addClass('active');

        $('#customerList').addClass('active');

        $('#customerManageSelect').addClass('selected');

        $('#customerManageArrow').addClass('arrow open');
    });
</script>
</@layout>