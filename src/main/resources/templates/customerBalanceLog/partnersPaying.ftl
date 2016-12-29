
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

                                <span class="hidden-480">请填写收款信息</span>

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

                                <label class="control-label">公司id<span class="required">*</span></label>

                                <div class="controls">

                                    <input type="text" id="authId" name="authId" class="m-wrap medium">

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

                            <div class="control-group">

                                <label class="control-label">备&nbsp;注<span class="required">*</span></label>

                                <div class="controls">

                                    <textarea class="medium m-wrap" rows="3"></textarea>

                                    <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                </div>

                            </div>



                            <div class="form-actions">
                                <button type="submit" class="btn black"><i class="icon-ok"></i> 提交</button>
                                <a href="/view/successUrl"><button type="button" class="btn">取消</button></a>
                            </div>

                        </form>


                    </div>

                </div>

            </div>

        </div>

    </div>

    <script type="text/javascript" src="/js/myjs/customerbalancelog.js"></script>

    <#elseif section = "footer">

    </#if>

</@layout>