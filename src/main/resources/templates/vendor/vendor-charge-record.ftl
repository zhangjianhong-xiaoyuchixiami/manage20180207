
<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<#import "../publicPart/tools.ftl" as d>

<#import "../publicPart/publicJs.ftl" as puj>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">

                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption">${name!'无'}</div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-5">

                                <div class="pull-left label-margin-bottom label-margin-right">

                                    <label class="control-label">总额&yen;：${consumeTot!'0.0'}元</label>

                                </div>

                                <div class="pull-right tip-remark">
                                    <span class="pull-right">注：本页面与金额相关数字单位都为：元</span>
                                </div>

                            </div>

                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_2">
                                <thead>
                                <tr>
                                    <th>充值/扣费金额</th>
                                    <th>充值/扣费日期</th>
                                    <th>类型</th>
                                    <th>备注</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if logList??>
                                        <#list logList as log>
                                        <tr <#if log.reasonId == 2> class="danger" </#if>>
                                            <td data-title="充值/扣费金额">${log.amount!'0'}</td>
                                            <td data-title="充值/扣费日期">${log.chargeTime!'无'}</td>
                                            <td data-title="类型">${log.reasonName!'无'}</td>
                                            <td data-title="备注">${log.remark!'无'}</td>
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

        <@puj.publicJs></@puj.publicJs>

    <script src="/js/multi/get-url-param.js?v=${ver}"></script>

    <script src="/js/myjs/vendor-charge-record.js?v=${ver}"></script>

    <script>

        jQuery(document).ready(function () {
            vendorChargeRecord.init();
        });

    </script>

    </#if>

</@layout>
