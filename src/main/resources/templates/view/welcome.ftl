
<#include "../publicPart/layout.ftl">

<#import "../publicPart/publicJs.ftl" as puj>

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid profile">

                <div class="span12">

                    <div class="tabbable tabbable-custom tabbable-full-width">

                        <div class="row-fluid">

                            <div class="row-fluid" style="margin-top: 20px">

                                <div class="span3 responsive" data-tablet="span4" data-desktop="span3">

                                    <div class="dashboard-stat blue">

                                        <div class="details">

                                            <div class="desc">当天总消费金额</div>

                                            <div class="number">1000</div>

                                        </div>

                                    </div>

                                </div>

                                <div class="span3 responsive" data-tablet="span4" data-desktop="span3">

                                    <div class="dashboard-stat yellow">

                                        <div class="details">

                                            <div class="desc">当天总成本</div>

                                            <div class="number">100</div>

                                        </div>

                                    </div>

                                </div>

                                <div class="span3 responsive" data-tablet="span4" data-desktop="span3">

                                    <div class="dashboard-stat green">

                                        <div class="details">
                                            <div class="desc">当天毛利润</div>

                                            <div class="number">900</div>

                                        </div>

                                    </div>

                                </div>

                            </div>

                        </div>

                        <div class="row-fluid">

                            <div class="row-fluid" style="margin-top: 20px">

                                <div class="span3 responsive" data-tablet="span4" data-desktop="span3">

                                    <div class="dashboard-stat blue">

                                        <div class="details">

                                            <div class="desc">昨天总消费金额</div>

                                            <div class="number">900</div>

                                        </div>

                                    </div>

                                </div>

                                <div class="span3 responsive" data-tablet="span4" data-desktop="span3">

                                    <div class="dashboard-stat yellow">

                                        <div class="details">

                                            <div class="desc">昨天总成本</div>

                                            <div class="number">200</div>

                                        </div>

                                    </div>

                                </div>

                                <div class="span3 responsive" data-tablet="span4" data-desktop="span3">

                                    <div class="dashboard-stat green">

                                        <div class="details">
                                            <div class="desc">昨天毛利润</div>

                                            <div class="number">700</div>

                                        </div>

                                    </div>

                                </div>

                            </div>

                        </div>

                        <div class="row-fluid">

                            <div class="span3 responsive" data-tablet="span12  fix-offset" data-desktop="span3">

                                <div class="portlet box blue">

                                    <div class="portlet-title">

                                        <div class="caption">同比情况</div>

                                    </div>

                                    <div class="portlet-body">

                                        <div class="row-fluid">

                                            <div class="span4">

                                                <div class="easy-pie-chart">

                                                    <div>总消费</div>

                                                    <div class="number transactions" data-percent="55"><span>+11.11</span>%
                                                    </div>

                                                </div>

                                            </div>

                                            <div class="margin-bottom-10 visible-phone"></div>

                                            <div class="span4">

                                                <div class="easy-pie-chart">

                                                    <div>总成本</div>

                                                    <div class="number visits" data-percent="85"><span>-50</span>%</div>

                                                </div>

                                            </div>

                                            <div class="margin-bottom-10 visible-phone"></div>

                                            <div class="span4">

                                                <div class="easy-pie-chart">

                                                    <div>毛利润</div>

                                                    <div class="number bounce" data-percent="46"><span>+28.57</span>%</div>

                                                </div>

                                            </div>

                                        </div>

                                    </div>

                                </div>

                            </div>

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

    </#if>

</@layout>