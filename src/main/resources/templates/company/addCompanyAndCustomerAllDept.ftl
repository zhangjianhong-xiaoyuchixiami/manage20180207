<#include "../customer/layout.ftl">


<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">

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

                            <div class="caption"><i class="icon-reorder"></i>请填写客户信息</div>

                            <div class="tools">

                                <a href="javascript:;" class="collapse"></a>

                                <a href="#portlet-config" data-toggle="modal" class="config"></a>

                                <a href="javascript:;" class="reload"></a>

                                <a href="javascript:;" class="remove"></a>

                            </div>

                        </div>

                        <div class="portlet-body form">

                            <!-- BEGIN FORM-->

                            <form action="/company/addCompanyAndCustomerAllDeptAction" id="form_sample_1" class="form-horizontal" method="post">

                                <div class="control-group"></div>

                                <div class="control-group"></div>

                                    <#if msg??>

                                        <div class="alert alert-error show">

                                            <button class="close" data-dismiss="alert"></button>

                                            ${msg}

                                        </div>

                                    </#if>

                                <div class="control-group">

                                    <label class="control-label">请输入公司名称<span class="required">*</span></label>

                                    <div class="controls">

                                        <input type="text" id="customer_Name" name="name" <#if name??>value="${name}"</#if> class="span6 m-wrap"/>

                                        <span id="customer_NameMsg" class="help-inline"><#if CompanyCustomerMessageName??><font color="red">${CompanyCustomerMessageName}</font></#if></span>

                                    </div>

                                </div>

                                <div class="control-group">

                                    <label class="control-label">请输入账号<span class="required">*</span></label>

                                    <div class="controls">

                                        <input type="text" id="customer_authId" name="authId" <#if authId??>value="${authId}"</#if> class="span6 m-wrap"/>

                                        <span id="customer_authIdMsg" class="help-inline"><#if CompanyCustomerMessageAuthId??><font color="red">${CompanyCustomerMessageAuthId}</font></#if></span>

                                        <span class="help-block">e.g：只能有数字、字母、下划线组成</span>

                                    </div>

                                </div>

                                <#if deptList??>

                                <div class="control-group">

                                    <label class="control-label">请选择部门<span class="required">*</span></label>

                                        <div class="controls">

                                            <select id="dept_id" name="deptId" class="medium m-wrap" tabindex="1">

                                                <option value="">请选择...</option>

                                                <#list deptList as dept>

                                                    <option value="${dept.id}">${dept.deptName}</option>

                                                </#list>

                                            </select>

                                            <span id="deptIdMsg" class="help-inline"><#if CompanyCustomerMessageDeptId??><font color="red">${CompanyCustomerMessageDeptId}</font></#if></span>

                                        </div>

                                </div>

                                </#if>

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
    <script src="/js/myjs/addcompanyandcustomeralldept.js" type="text/javascript"></script>
    <#elseif section = "footer">

    </#if>

</@layout>