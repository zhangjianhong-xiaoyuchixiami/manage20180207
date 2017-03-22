
<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<#import "../publicPart/tools.ftl" as d>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">

                <#--搜索框-->
                    <form action="/finance/find-all-customer/find-all-customer-recharge-log-by-customer-id" class="form-bottom" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom head-search-display">

                                <label class="control-label">客户账号Id</label>

                                <div class="controls">

                                    <input type="text" id="customerId" name="customerId" class="m-wrap medium">

                                </div>
                            </div>

                            <div class="pull-left head-search-bottom head-search-display">

                                <label class="control-label">公司名称</label>

                                <div class="controls">

                                    <input type="text" id="companyName" name="companyName" class="m-wrap medium">

                                </div>
                            </div>

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">充值理由</label>

                                <div class="controls">

                                    <label class="checkbox">

                                        <input type="checkbox" id="reasonId" name="reasonId" value="1">正常充值

                                    </label>

                                    <label class="checkbox">

                                        <input type="checkbox" id="reasonId" name="reasonId" value="2">弥补充值

                                    </label>

                                    <label class="checkbox">

                                        <input type="checkbox" id="reasonId" name="reasonId" value="3">测试充值

                                    </label>

                                </div>

                            </div>

                            <div class="pull-left margin-right-20 head-search-bottom">

                                <label class="control-label">起始日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                        <input id="beginDate" name="beginDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

                                    </div>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">结束日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                        <input id="endDate" name="endDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

                                    </div>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">&nbsp;&nbsp;</label>

                                <div class="controls" >

                                    <div class="input-append">

                                        <button class="btn black" type="submit">搜索</button>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </form>

                <#--表单-->
                    <div class="portlet box grey">

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-20">

                            </div>
                            <div class="table-responsive">
                                <table class="table table-striped table-hover table-bordered table-condensed" style="table-layout: fixed" id="sample_1">
                                    <thead>
                                    <tr>
                                        <th>标题</th>
                                        <th>类型</th>
                                        <th>请求地址</th>
                                        <th>URI</th>
                                        <th>请求方式</th>
                                        <th>提交参数</th>
                                        <th>修改前数据</th>
                                        <th>修改后数据</th>
                                        <th>异常</th>
                                        <th>开始时间</th>
                                        <th>结束时间</th>
                                        <th>操作人</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td data-title="标题" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">新增用户</td>
                                        <td data-title="类型" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">info</td>
                                        <td data-title="请求地址" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">192.168.111.113</td>
                                        <td data-title="URI" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">/add-user</td>
                                        <td data-title="请求方式" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">Post</td>
                                        <td data-title="提交参数" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">{"username":"zhang","userId":1,"balance":200}</td>
                                        <td data-title="修改前数据" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;"></td>
                                        <td data-title="修改后数据" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;"></td>
                                        <td data-title="异常" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;"></td>
                                        <td data-title="开始时间" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">2017.12.12 00:00:00</td>
                                        <td data-title="结束时间" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">2017.12.12 00:00:00</td>
                                        <td data-title="操作人" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">18945927283@163.com</td>
                                    </tr>
                                    <tr>
                                        <td data-title="标题" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">修改用户余额</td>
                                        <td data-title="类型" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">info</td>
                                        <td data-title="请求地址" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">192.168.111.113</td>
                                        <td data-title="URI" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">/add-user</td>
                                        <td data-title="请求方式" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">Post</td>
                                        <td data-title="提交参数" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">{"username":"zhang","userId":1,"balance":200}</td>
                                        <td data-title="修改前数据" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">{"username":"zhang","userId":1,"balance":100}</td>
                                        <td data-title="修改后数据" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">{"username":"zhang","userId":1,"balance":300}</td>
                                        <td data-title="异常" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;"></td>
                                        <td data-title="开始时间" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">2017.12.12 00:00:00</td>
                                        <td data-title="结束时间" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">2017.12.12 00:00:00</td>
                                        <td data-title="操作人" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">18945927283@163.com</td>
                                    </tr>
                                    <tr class="danger">
                                        <td data-title="标题" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">修改用户余额</td>
                                        <td data-title="类型" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">error</td>
                                        <td data-title="请求地址" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">192.168.111.113</td>
                                        <td data-title="URI" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">/add-user</td>
                                        <td data-title="请求方式" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">Post</td>
                                        <td data-title="提交参数" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">{"username":"zhang","userId":1,"balance":200}</td>
                                        <td data-title="修改前数据" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">{"username":"zhang","userId":1,"balance":100}</td>
                                        <td data-title="修改后数据" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">{"username":"zhang","userId":1,"balance":300}</td>
                                        <td data-title="异常" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">
                                            WebSocketAutoConfiguration.JettyWebSocketConfiguration did not match-
                                            @ConditionalOnClass did not find required class '
                                            org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocket
                                            ServerContainerInitializer' (OnClassCondition)
                                        </td>
                                        <td data-title="开始时间" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">2017.12.12 00:00:00</td>
                                        <td data-title="结束时间" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">2017.12.12 00:00:00</td>
                                        <td data-title="操作人" style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis;">18945927283@163.com</td>
                                    </tr>
                                    </tbody>
                                </table>
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

    <script type="text/javascript" src="/js/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/DT_bootstrap.js"></script>

    <script src="/js/myjs/log-message.js"></script>



    <script type="text/javascript">

        jQuery(document).ready(function() {
            LogLeftBar.init();
        });

    </script>

    </#if>

</@layout>
