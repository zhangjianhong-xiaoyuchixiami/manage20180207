
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

                <#--搜索框-->
                    <form action="/finance/find-all-customer/find-all-customer-recharge-log-by-customer-id" class="form-bottom" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">类型</label>

                                <div class="controls">

                                    <label class="checkbox">

                                        <input type="checkbox" id="typeId" name="typeId" value="1">入库

                                    </label>

                                    <label class="checkbox">

                                        <input type="checkbox" id="typeId" name="typeId" value="2">错误

                                    </label>

                                </div>

                            </div>

                            <div class="pull-left margin-right-20 head-search-bottom">

                                <label class="control-label">标题</label>

                                <div class="controls">

                                    <input id="title" name="title" class="m-wrap small-big" type="text">

                                </div>

                            </div>
                            <div class="pull-left margin-right-20 head-search-bottom">

                                <label class="control-label">请求地址</label>

                                <div class="controls">

                                    <input id="address" name="address" class="m-wrap small-big" type="text">

                                </div>

                            </div>
                            <div class="pull-left margin-right-20 head-search-bottom">

                                <label class="control-label">请求方式</label>

                                <div class="controls">

                                    <input id="reqWay" name="reqWay" class="m-wrap small-big" type="text">

                                </div>

                            </div>

                            <div class="pull-left margin-right-20 head-search-bottom">

                                <label class="control-label">操作人</label>

                                <div class="controls">

                                    <input id="operator" name="operator" class="m-wrap small-big" type="text">

                                </div>

                            </div>

                            <div class="pull-left margin-right-20 head-search-bottom">

                                <label class="control-label">操作时间</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                        <input id="operDate" name="operDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

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

                        <div class="portlet-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-hover table-bordered table-condensed table-layout-fixed" id="sample_1">
                                    <thead>
                                    <tr>
                                        <th>标题</th>
                                        <th>类型</th>
                                        <th>请求地址</th>
                                        <th>URI</th>
                                        <th>请求方式</th>
                                        <th>提交参数</th>
                                       <#-- <th>操作前数据</th>
                                        <th>操作后数据</th>-->
                                        <th>异常</th>
                                      <#--  <th>操作开始时间</th>
                                        <th>请求超时</th>-->
                                        <th>操作人</th>
                                        <th>操作时间</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if logList??>
                                            <#list  logList as log>
                                                <#if log.typeId==2>
                                                <tr class="danger">
                                                <#else >
                                                <tr>
                                                </#if>
                                                <td class="table-td-layout-fixed">${log.title!'无'}</td>
                                                <td class="table-td-layout-fixed">${(log.logType.name)!'无'}</td>
                                                <td class="table-td-layout-fixed">${log.remoteAddr!'无'}</td>
                                                <td class="table-td-layout-fixed">${log.requestUri!'无'}</td>
                                                <td class="table-td-layout-fixed">${log.method!'无'}</td>
                                                <td class="table-td-layout-fixed">${log.params!'无'}</td>
                                           <#--     <td class="table-td-layout-fixed">${log.operationBeforData!'无'}</td>
                                                <td class="table-td-layout-fixed">${log.operationAfterData!'无'}</td>-->
                                                <td class="table-td-layout-fixed">${log.error!'无'}</td>
                                              <#--  <td class="table-td-layout-fixed">${(log.beginTime?datetime)!'无'}</td>-->
                                             <#--   <td class="table-td-layout-fixed">${log.timeOut!'无'}</td>-->
                                                <td class="table-td-layout-fixed">${(log.user.email)!'无'}</td>
                                                <td class="table-td-layout-fixed">${(log.createTime?datetime)!'无'}</td>
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
    </div>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

        <@puj.publicJs></@puj.publicJs>

    <script type="text/javascript" src="/js/former/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/former/DT_bootstrap.js"></script>

    <script src="/js/myjs/log-message.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            LogLeftBar.init();
        });

    </script>

    </#if>

</@layout>
