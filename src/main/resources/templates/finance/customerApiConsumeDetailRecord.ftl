
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

                <#--表单-->
                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-user"></i><#if companyName??>${companyName}</#if><#if apiTypeName??>--${apiTypeName}</#if></div>

                            <div class="tools">

                            <#--<a href="javascript:;" class="collapse"></a>-->

                                <#--<a href="#portlet-config" data-toggle="modal" class="config"></a>-->

                                <#--<a href="javascript:;" class="reload"></a>-->

                                <#--<a href="javascript:;" class="remove"></a>-->

                            </div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">共计&yen;：<#if totleAmount??><span>${totleAmount/100.0}元&nbsp;&nbsp;&nbsp;</span><#else ><span>0元&nbsp;&nbsp;&nbsp;</span></#if></label>

                                </div>

                            </div>

                            <table class="table table-striped table-hover table-bordered table-condensed" id="sample_7">
                                <thead>
                                <tr>
                                    <th style="width: 35%">产品供应商</th>
                                    <th style="width: 45%">产品名称</th>
                                    <th style="width: 20%">金额（单位/元）</th>
                                </tr>
                                </thead>
                                <tbody>
                               <#if customerApiVendorList??>
                                   <#list customerApiVendorList as customerApiVendor>
                                   <tr>
                                       <td><#if customerApiVendor.vendorName??>${customerApiVendor.vendorName}</#if></td>
                                       <td><#if customerApiVendor.apiName??>${customerApiVendor.apiName}</#if></td>
                                       <td><#if customerApiVendor.totlePrice??>${customerApiVendor.totlePrice/100.0}<#else >0</#if></td>
                                   </tr>
                                   </#list>
                               </#if>
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

    <script type="text/javascript" src="/js/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/DT_bootstrap.js"></script>

    <script src="/js/table-managed.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            TableManaged.init();
        });
    </script>
    <script>
        $(document).ready(function() {
            $('#customerBalance').addClass('active');

            $('#customerListBalanceLog').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');
        });
    </script>
    </#if>

</@layout>
