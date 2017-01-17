
<#include "../customer/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN VALIDATION STATES-->
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-reorder"></i>请填写部门信息</div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse"></a>
                                <a href="#portlet-config" data-toggle="modal" class="config"></a>
                                <a href="javascript:;" class="reload"></a>
                                <a href="javascript:;" class="remove"></a>
                            </div>
                        </div>
                        <div class="portlet-body form">

                            <!-- BEGIN FORM-->
                            <form action="/dept/addDeptAction" method="post" id="form_sample_1" class="form-horizontal">

                                <div class="control-group"></div>

                                <div class="control-group"></div>

                                <#if msg??>

                                    <div class="alert alert-error show">

                                        <button class="close" data-dismiss="alert"></button>

                                    ${msg}

                                    </div>

                                </#if>
                                <div class="control-group">
                                    <label class="control-label">部门名称<span class="required">*</span></label>
                                    <div class="controls">
                                        <input type="text" id="deptName" name="deptName" <#if deptName??>value="${deptName}" </#if>data-required="1" class="span6 m-wrap"/>

                                        <span class="help-inline" id="deptNameMsg"><#if DeptMessageDeptName??><font color="red">${DeptMessageDeptName}</font></#if></span>
                                    </div>
                                </div>

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


    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

    <script>
        $(document).ready(function() {
            $('#deptManage').addClass('active');

            $('#addDept').addClass('active');

            $('#deptManageSelect').addClass('selected');

            $('#deptManageArrow').addClass('arrow open');

            $("#deptName").focus(function () {
                $("#deptNameMsg").html("");
            });

            $("#deptNo").focus(function () {
                $("#deptNoMsg").html("");
            });
        });
    </script>

    </#if>

</@layout>