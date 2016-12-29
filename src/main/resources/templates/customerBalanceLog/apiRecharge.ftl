
<#include "../customer/layout.ftl">

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">

                    <h3 class="page-title">

                    <#--余额变更-->

                    </h3>

                <#--<ul class="breadcrumb">-->

                <#--<li>-->

                <#--<i class="icon-home"></i>-->

                <#--<a href="/view/successUrl">首页</a>-->

                <#--<span class="icon-angle-right"></span>-->

                <#--</li>-->

                <#--<li>-->

                <#--<a href="#">财务管理</a>-->

                <#--<span class="icon-angle-right"></span>-->

                <#--</li>-->

                <#--<li><a href="#">余额变更</a></li>-->

                <#--</ul>-->

                </div>

            </div>

            <div class="row-fluid">

                <div class="span12">

                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption">

                                <i class="icon-reorder"></i>

                                <span class="hidden-480">请输入金额</span>

                            </div>

                            <div class="tools">

                            <#--<a href="javascript:;" class="collapse"></a>-->

                                <#--<a href="#portlet-config" data-toggle="modal" class="config"></a>-->

                                <#--<a href="javascript:;" class="reload"></a>-->

                                <#--<a href="javascript:;" class="remove"></a>-->

                            </div>

                        </div>

                        <form action="/customerBalance/customerBalanceChangeAction" class="form-horizontal" method="post" >

                            <div class="control-group"></div>

                            <div class="control-group"></div>

                            <#if msg??>

                                <div class="alert alert-error show">

                                    <button class="close" data-dismiss="alert"></button>

                                ${msg}

                                </div>

                            </#if>
                            <div class="control-group" style="display: none;">

                                <label class="control-label">账&nbsp;&nbsp;户<span class="required">*</span></label>

                                <div class="controls">

                                    <input type="text" id="authId" name="authId" <#if authId??>value="${authId}"</#if> class="m-wrap medium">

                                    <span class="help-inline" id="authIdMsg"><#if CustomerMessageAuthId??><font color="red">${CustomerMessageAuthId}</font></#if></span>

                                    <span class="help-block">只能有数字、字母、下划线组成</span>

                                </div>

                            </div>

                            <div class="control-group">

                                <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                <div class="controls">

                                    <input type="text" id="amount" name="amount" <#if amount??>value="${amount}"</#if> placeholder="（单位/元）" class="m-wrap medium">

                                    <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                    <span class="help-block">只能输入数字类型并且金额大于0</span>

                                </div>

                            </div>

                            <#--<div class="control-group">-->

                                <#--<label class="control-label">原&nbsp;&nbsp;因<span class="required">*</span></label>-->

                                <#--<div class="controls">-->

                                    <#--<select id="reasonId" name="reasonId" class="medium m-wrap" tabindex="1">-->

                                        <#--<#list customerBalanceModifyReasonList as reasonList>-->

                                            <#--<option value="${reasonList.id}">${reasonList.name}</option>-->

                                        <#--</#list>-->

                                    <#--</select>-->

                                    <#--<span class="help-inline" id="reasonIdMsg"><#if CustomerMessageReasonId??><font color="red">${CustomerMessageReasonId}</font></#if></span>-->

                                <#--</div>-->

                            <#--</div>-->

                            <div class="form-actions">
                                <button type="submit" class="btn black"><i class="icon-ok"></i> 提交</button>
                                <a href="/view/successUrl"><button type="button" class="btn">取消</button></a>
                            </div>

                        </form>


                        <#--<div class="control-group" style="display: block;">-->

                            <#--<label class="control-label"></label>-->

                            <#--<div class="controls">-->

                                <#--<a id="tipInfo" class="btn blue" href="#form_modal3" data-toggle="modal">-->

                                    <#--点击-->

                                <#--</a>-->

                            <#--</div>-->

                        <#--</div>-->

                        <#--<div id="form_modal3" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="true">-->

                            <#--<div class="modal-header">-->

                                <#--<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>-->

                                <#--<h3 id="myModalLabel3">提示信息</h3>-->

                            <#--</div>-->

                            <#--<div class="modal-body">-->

                                <#--<#if success??>${success}</#if>-->

                            <#--</div>-->

                            <#--<div class="modal-footer">-->

                                <#--<button class="btn" data-dismiss="modal" aria-hidden="true">确定</button>-->

                            <#--</div>-->

                        <#--</div>-->


                    </div>

                </div>

            </div>

        </div>

    </div>

        <#if success??>
            <#if success == "yes">
            <script>
//                $(document).ready(function() {
//                    $('tipInfo').click();
//                });
                alert("操作成功")
            </script>
            <#else >
            <script>
//                $(document).ready(function() {
//                    $('tipInfo').click();
//                });
                alert("操作失败")
            </script>
            </#if>
        </#if>

    <script type="text/javascript" src="/js/myjs/customerbalancelog.js"></script>

    <script>
//        $(document).ready(function() {
//
//            $('#validButton').click(function () {
//                $('#form_modal4').css('display','none');
//            });
//
//            $('#validBtn').click(function () {
//                $('#form_modal4').css('display','none');
//            });
//        });
    </script>


    <#elseif section = "footer">

    </#if>

</@layout>