<#include "layout.ftl">


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

                                <form action="/customer/insertCustomerCommon" id="form_sample_1" class="form-horizontal" method="post" onsubmit="return validateCustomer()">

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

                                            <input type="text" id="customer_Name" name="customer.name" <#if name??>value="${name}"</#if> class="span6 m-wrap"/>

                                            <span id="customer_NameMsg" class="help-inline"><#if CustomerMessageName??><font color="red">${CustomerMessageName}</font></#if></span>

                                        </div>

                                    </div>

                                    <div class="control-group">

                                        <label class="control-label">账&nbsp;&nbsp;户<span class="required">*</span></label>

                                        <div class="controls">

                                            <input type="text" id="customer_authId" name="customer.authId" <#if authId??>value="${authId}"</#if> class="span6 m-wrap"/>

                                            <span id="customer_authIdMsg" class="help-inline"><#if CustomerMessageAuthId??><font color="red">${CustomerMessageAuthId}</font></#if></span>

                                            <span class="help-block">e.g：只能有数字、字母、下划线组成</span>

                                        </div>

                                    </div>

                                    <div class="control-group">

                                        <label class="control-label">所属部门<span class="required">*</span></label>

                                        <#if deptList??>

                                            <div class="controls">

                                                <select id="dept_deptNo" name="dept.deptNo" class="medium m-wrap" tabindex="1">

                                                    <#list deptList as dept>

                                                        <option value="${dept.deptNo}">${dept.deptName}</option>

                                                    </#list>

                                                </select>

                                                <span id="dept_deptNoMsg" class="help-inline"><#if CustomerMessageDeptNo??><font color="red">${CustomerMessageDeptNo}</font></#if></span>

                                            </div>

                                        </#if>

                                    </div>

                                    <div class="form-actions">

                                        <button type="submit" class="btn blue">提交</button>

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

    </#if>

<script>
    $(document).ready(function() {
        $('#customerManage').addClass('active');

        $('#addCustomer').addClass('active');

        $('#customerManageSelect').addClass('selected');

        $('#customerManageArrow').addClass('arrow open');

        $("#customer_Name").focus(function () {
            $("#customer_NameMsg").html("");
        });

        $("#customer_authId").focus(function () {
            $("#customer_authIdMsg").html("");
        });

        $("#dept_deptNo").focus(function () {
            $("#dept_deptNoMsg").html("");
        });

        $("#customer_authId").blur(function(){
            $("#customer_authIdMsg").load("/customer/findCustomerByAuthId/"+$("#customer_authId").val(),
                    function(responseTxt){
                        if(responseTxt=="yes")
                            $("#customer_authIdMsg").html("<font color='red'>该账户已存在！</font>");
                        if(responseTxt=="no")
                            $("#customer_authIdMsg").html("");
                    });
        });
    });

</script>

</@layout>