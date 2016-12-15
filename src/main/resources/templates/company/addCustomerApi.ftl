
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

                        <li><a href="#">添加Api</a></li>

                    </ul>

                </div>

            </div>

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

                                    <li><a href="#"" data-toggle="tab">Default</a></li>

                                </ul>

                                <div class="tab-content">

                                    <div class="btn-group">

                                        <button class="btn blue" onclick="addCustomerApi()">

                                            Add New <i class="icon-plus"></i>

                                        </button>

                                    </div>

                                    <div class="tab-pane active" id="portlet_tab1">

                                        <!-- BEGIN FORM-->

                                        <form action="/customerApi/addCustomerApiAction" class="form-horizontal" method="post">

                                            <#if msg??>

                                                <div class="alert alert-error show">

                                                    <button class="close" data-dismiss="alert"></button>

                                                    对不起，操作失败，请检查你的输入！

                                                </div>

                                            </#if>

                                            <div class="control-group" style="display: none">

                                                <label class="control-label">客户ID<span class="required">*</span></label>

                                                <div class="controls">

                                                    <input type="text" id="companyId" name="companyId" value="${companyId}" class="m-wrap medium">

                                                    <span id="customerIdMsg" class="help-inline"></span>

                                                </div>

                                            </div>

                                            <div id="addCloneCustomerApi">

                                                <div id="cloneCustomerApi">

                                                    <div class="control-group">

                                                        <div class="controls">

                                                        </div>

                                                    </div>

                                                    <div class="control-group">

                                                        <label class="control-label">价&nbsp;格<span class="required">*</span></label>

                                                        <div class="controls">

                                                            <input type="text" id="price" name="price" <#if price??>value="${price}" </#if> placeholder="（单位：分）" class="m-wrap medium">

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

                                                            <span id="apiIdMsg" class="help-inline"></span>

                                                        </div>

                                                    </div>

                                                    <div class="control-group">

                                                        <label class="control-label">激活状态<span class="required">*</span></label>

                                                        <div class="controls">

                                                            <select id="enabled" name="enabled" class="medium m-wrap" tabindex="1">

                                                                <option value="true">是</option>

                                                                <option value="false">否</option>

                                                            </select>

                                                            <span id="enabledMsg" class="help-inline"></span>

                                                        </div>

                                                    </div>

                                                </div>
                                            </div>

                                    </div>

                                </div>

                                <div class="form-actions">
                                    <button type="submit" class="btn blue"><i class="icon-ok"></i> 提交</button>
                                    <@shiro.hasRole name="backAdmin">
                                        <a href="/customer/findAllCustomer"><button type="button" class="btn">取消</button></a>
                                    </@shiro.hasRole>
                                    <@shiro.hasRole name="sell">
                                        <a href="/customer/findAllCustomerByDeptNo"><button type="button" class="btn">取消</button></a>
                                    </@shiro.hasRole>
                                </div>

                                </form>

                                <!-- END FORM-->

                            </div>

                        </div>

                    </div>

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
    function addCustomerApi(){
        var oTr = document.getElementById("cloneCustomerApi");
        var newTr = oTr.cloneNode(true);
        document.getElementById("addCloneCustomerApi").appendChild(newTr);
    }
</script>
<script>
    $(document).ready(function() {
        $('#customerManage').addClass('active');

        $('#customerList').addClass('active');

        $('#customerManageSelect').addClass('selected');

        $('#customerManageArrow').addClass('arrow open');

        $("#price").focus(function () {
            $("#priceMsg").html("");
        });
    });
</script>

</@layout>