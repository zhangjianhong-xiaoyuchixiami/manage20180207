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

                            <a href="index.html">首页</a>

                            <span class="icon-angle-right"></span>

                        </li>

                        <li>

                            <a href="#">客户管理</a>

                            <span class="icon-angle-right"></span>

                        </li>

                        <li><a href="#">新增客户</a></li>

                    </ul>

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

                            <form action="#" id="form_sample_1" class="form-horizontal">

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

                                        <input type="text" name="name" data-required="1" class="span6 m-wrap"/>

                                    </div>

                                </div>

                                <#if deptList??>

                                    <div class="control-group">

                                        <label class="control-label">请选择所属部门<span class="required">*</span></label>

                                        <div class="controls">

                                            <select class="span6 m-wrap" name="deptId">

                                                <option value="">请选择...</option>

                                                <#list deptList as dept>

                                                    <option value="${dept.id}">${dept.deptName}</option>

                                                </#list>

                                            </select>

                                        </div>

                                    </div>

                                </#if>

                                <div class="form-actions">

                                    <button type="submit" class="btn blue">确定</button>

                                    <button type="button" class="btn">取消</button>

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

</@layout>

