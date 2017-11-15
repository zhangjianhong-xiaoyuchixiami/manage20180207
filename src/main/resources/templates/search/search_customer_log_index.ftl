
<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<#import "../publicPart/publicJs.ftl" as puj>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content" xmlns="http://www.w3.org/1999/html">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">



                <#--<form action="/finance/rebate" id="submit_form" class="form-bottom-excel form-top" method="get">-->

                <#--<div class="clearfix margin-bottom-20 head-search-clearfix-top">-->

                <#--<div class="pull-left head-search-bottom">-->

                <#--<label class="">请选择代理人</label>-->

                <#--<div class="controls">-->

                <#--<select class="medium m-wrap" id="agency" name="agency">-->
                <#--<option value=""></option>-->
                <#--<#if agencyList??>-->
                <#--<#list agencyList as agency>-->
                <#--<option <#if agencyId?? && agencyId == agency.id>selected="selected"</#if> value="${agency.id}">${agency.name}</option>-->
                <#--</#list>-->
                <#--</#if>-->
                <#--</select>-->

                <#--</div>-->

                <#--</div>-->
                <#---->
                <#--<div class="pull-left head-search-bottom">-->

                <#--<label>&nbsp;&nbsp;</label>-->

                <#--<div class="controls" >-->

                <#--<div class="input-append">-->

                <#--<button class="btn black" id="submit" type="submit">确定</button>-->

                <#--</div>-->

                <#--</div>-->

                <#--</div>-->

                <#--</div>-->

                <#--</form>-->

                    <div class="">

                        <form class="form-search" action="#">

                            <div class="chat-form">



                                <div class="input-cont">
                                    <div class="pull-left head-search-bottom">

                                        <select class="medium m-wrap" id="pid" name="pid">
                                            <option value=""></option>
                                            <option value="">k</option>
                                            <option value="">reqId</option>
                                        </select>

                                    </div>
                                    <div class="pull-left head-search-bottom">

                                        <select class="medium m-wrap" id="pid" name="pid">
                                            <option value=""></option>
                                            <option <#if pid?? && pid == -100> selected="selected"</#if> value="-100">无</option>
                                            <#if partnerList??>
                                                <#list partnerList as partner>
                                                    <option <#if pid?? && pid==partner.id>selected="selected"</#if> value="${partner.id}">${partner.name}</option>
                                                </#list>
                                            </#if>
                                        </select>

                                    </div>

                                    <div class="pull-left head-search-bottom">
                                    <input type="text" placeholder="Search..." class="m-wrap" />
                                    </div>

                                </div>

                                <button type="button" class="btn black">Search &nbsp; <i class="m-icon-swapright m-icon-white"></i></button>

                            </div>

                        </form>

                    </div>


                </div>

            </div>

        </div>

    </div>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

        <@puj.publicJs></@puj.publicJs>

    <script src="/js/myjs/rebate/rebate.js?v=${ver}"></script>

    <script>

        jQuery(document).ready(function() {

            Rebate.init();

        });

    </script>

    </#if>

</@layout>
