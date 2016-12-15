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

                            <form action="/customer/insertCustomerSuper" id="form_sample_1" class="form-horizontal" method="post">

                                <div class="control-group"></div>

                                <div class="control-group"></div>

                                    <#if msg??>

                                        <div class="alert alert-error show">

                                            <button class="close" data-dismiss="alert"></button>

                                            对不起，操作失败，请检查你的输入！

                                        </div>

                                    </#if>

                                <div class="control-group">

                                    <label class="control-label">请输入公司名称<span class="required">*</span></label>

                                    <div class="controls">

                                        <input type="text" id="customer_Name" name="name" <#if name??>value="${name}"</#if> class="span6 m-wrap"/>

                                        <span id="customer_NameMsg" class="help-inline"><#if CustomerMessageName??><font color="red">${CustomerMessageName}</font></#if></span>

                                    </div>

                                </div>

                                <div class="control-group">

                                    <label class="control-label">请输入账号<span class="required">*</span></label>

                                    <div class="controls">

                                        <input type="text" id="customer_authId" name="authId" <#if authId??>value="${authId}"</#if> class="span6 m-wrap"/>

                                        <span id="customer_authIdMsg" class="help-inline"><#if CustomerMessageAuthId??><font color="red">${CustomerMessageAuthId}</font></#if></span>

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

                                        </div>

                                </div>

                                </#if>

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

            $("#dept_id").focus(function () {
                $("#dept_IdMsg").html("");
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

    <#elseif section = "footer">

    </#if>



</@layout>