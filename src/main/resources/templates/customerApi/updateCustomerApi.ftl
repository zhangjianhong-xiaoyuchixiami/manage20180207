
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

            <div class="row-fluid">

                <div class="span12">

                    <div class="portlet box blue tabbable">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-reorder"></i>请填写Api信息</div>

                            <div class="tools">

                                <a href="javascript:;" class="collapse"></a>

                                <a href="#portlet-config" data-toggle="modal" class="config"></a>

                                <a href="javascript:;" class="reload"></a>

                                <a href="javascript:;" class="remove"></a>

                            </div>

                        </div>

                        <div class="portlet-body form">

                            <form action="/customerApi/updateCustomerApiById" class="form-horizontal" method="post" onsubmit="return validateCustomerApi()">
                                <div class="control-group"></div>

                                <div class="control-group"></div>

                                <#if msg??>

                                    <div class="alert alert-error show">

                                        <button class="close" data-dismiss="alert"></button>

                                    ${msg}

                                    </div>

                                </#if>

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

                                        <span class="help-block">e.g：只能输入数字类型并且金额大于0</span>
                                    </div>

                                </div>

                                <div class="control-group">

                                    <label class="control-label">Api<span class="required">*</span></label>

                                    <div class="controls">

                                        <select id="apiId" name="apiId" class="medium m-wrap" tabindex="1">

                                            <#if apiList??>
                                                <#list apiList as api>

                                                    <option value="${api.id}"><#if api.apiType??>${api.apiType.name}</#if><#if api.apiMobileOperator??>---${api.apiMobileOperator.mobileOperator.name}</#if></option>

                                                </#list>
                                            </#if>

                                        </select>
                                        <#if customerApi??>

                                            <span class="help-inline">（原Api：<#if customerApi.api.apiType??>${customerApi.api.apiType.name}</#if><#if customerApi.api.apiMobileOperator??>---${customerApi.api.apiMobileOperator.mobileOperator.name}</#if>）</span>

                                        </#if>
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
                                    <button type="submit" class="btn black"><i class="icon-ok"></i> 提交</button>
                                    <a href="/customerApi/customerApiListAction/${customerApi.customerId}"><button type="button" class="btn">取消</button></a>
                                </div>

                            </form>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>

    <#elseif section = "footer">


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