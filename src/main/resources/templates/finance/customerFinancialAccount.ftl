
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

                    <div class="alert alert-success">

                        <button class="close" data-dismiss="alert"></button>

                        本页面与金额有关字段单位都是元<br>

                    </div>

                <#--搜索框-->

                    <div class="pull-left head-search-bottom head-search-display">

                        <label class="control-label">合作公司Id</label>

                        <div class="controls">

                            <input type="text" id="partnerId" name="partnerId" <#if partnerId??>value="${partnerId?c}"</#if> class="m-wrap medium">

                        </div>
                    </div>

                    <div class="pull-left head-search-bottom head-search-display">

                        <label class="control-label"></label>

                        <div class="controls">

                            <input type="text" id="content-company-name" name="content-company-name" <#if content??>value="${content}"</#if> class="m-wrap medium">

                        </div>
                    </div>

                    <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">

                        <form action="/finance/find-all-customer-by-dept-id" class="form-bottom find_part_customer" method="get">

                            <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                                <div class="pull-left head-search-bottom">

                                    <label class="control-label">公司状态</label>

                                    <div class="controls">

                                        <label class="checkbox">

                                            <input type="checkbox" <#if statusArray??><#list statusArray as status><#if status=="0">checked="checked"</#if></#list></#if> id="status" name="status" value="0">状态正常

                                        </label>

                                        <label class="checkbox">

                                            <input type="checkbox" <#if statusArray??><#list statusArray as status><#if status=="-1">checked="checked"</#if></#list></#if> id="status" name="status" value="-1">被禁用

                                        </label>

                                    </div>

                                </div>

                                <div class="pull-left head-search-bottom">

                                    <label class="control-label">请输入公司名称</label>

                                    <div class="controls">

                                        <div class="input-append">

                                            <input class="m-wrap" <#if content??>value="${content}" </#if> type="text" id="companyName" name="content" placeholder="请输入公司名称">

                                        </div>

                                    </div>

                                </div>

                                <div class="pull-left margin-right-20 head-search-bottom">

                                    <label class="control-label">起始日期</label>

                                    <div class="controls">

                                        <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                            <input <#if beginDate??>value="${beginDate}" </#if> id="beginDate" name="beginDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

                                        </div>

                                    </div>

                                </div>

                                <div class="pull-left head-search-bottom">

                                    <label class="control-label">结束日期</label>

                                    <div class="controls">

                                        <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                            <input <#if endDate??>value="${endDate}" </#if> id="endDate" name="endDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

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

                    </@shiro.hasPermission>

                    <@shiro.hasPermission name="customer:findAllCustomer">

                        <form action="/finance/find-all-customer" class="form-bottom find_all_customer" method="get">

                            <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                                <div class="pull-left head-search-bottom">

                                    <label>公司状态</label>

                                    <div class="controls">

                                        <label class="checkbox">

                                            <input type="checkbox" <#if statusArray??><#list statusArray as status><#if status=="0">checked="checked"</#if></#list></#if> id="status" name="status" value="0">状态正常

                                        </label>

                                        <label class="checkbox">

                                            <input type="checkbox" <#if statusArray??><#list statusArray as status><#if status=="-1">checked="checked"</#if></#list></#if> id="status" name="status" value="-1">被禁用

                                        </label>

                                    </div>

                                </div>

                                <div class="pull-left head-search-bottom">

                                    <label>请输入公司名称</label>

                                    <div class="controls">

                                        <div class="input-append">

                                            <input class="m-wrap" <#if content??>value="${content}" </#if> type="text" id="companyName" name="content" placeholder="请输入公司名称">

                                        </div>

                                    </div>

                                </div>

                                <div class="pull-left head-search-bottom">

                                    <label>请选择合作公司</label>

                                    <div class="controls">

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

                                </div>

                                <div class="pull-left margin-right-20 head-search-bottom">

                                    <label>起始日期</label>

                                    <div class="controls">

                                        <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                            <input <#if beginDate??>value="${beginDate}" </#if> id="beginDate" name="beginDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

                                        </div>

                                    </div>

                                </div>

                                <div class="pull-left head-search-bottom">

                                    <label>结束日期</label>

                                    <div class="controls">

                                        <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                            <input <#if endDate??>value="${endDate}" </#if> id="endDate" name="endDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

                                        </div>

                                    </div>

                                </div>

                                <div class="pull-left head-search-bottom">

                                    <label>&nbsp;&nbsp;</label>

                                    <div class="controls" >

                                        <div class="input-append">

                                            <button class="btn black" type="submit">搜索</button>

                                        </div>

                                    </div>

                                </div>

                            </div>

                        </form>

                    </@shiro.hasPermission>

                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption"></div>

                            <@shiro.hasPermission name="customer:findAllCustomer">
                                <@d.tools idName="exportExcel" hrefName="/finance/find-all-customer?export=true"></@d.tools>
                            </@shiro.hasPermission>

                            <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                <@d.tools idName="exportExcelByDeptId" hrefName="/finance/find-all-customer-by-dept-id?export=true"></@d.tools>
                            </@shiro.hasPermission>

                            <div class="actions">

                                <div class="btn-group">

                                    <a class="btn" href="#" data-toggle="dropdown">

                                        表格显示列

                                        <i class="icon-angle-down"></i>

                                    </a>

                                    <div id="sample_2_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">

                                        <label><input type="checkbox" checked data-column="2">公司名称</label>

                                    <#--<label><input type="checkbox" checked data-column="3">合作公司</label>-->

                                        <label><input type="checkbox" checked data-column="4">信用额度</label>

                                        <label><input type="checkbox" checked data-column="5">可用额度</label>

                                        <label><input type="checkbox" checked data-column="6">余额</label>

                                        <label><input type="checkbox" checked data-column="7">充值总额</label>

                                        <label><input type="checkbox" checked data-column="8">消费总额</label>

                                        <label><input type="checkbox" data-column="9">上周充值</label>

                                        <label><input type="checkbox" data-column="10">上周消费</label>

                                        <label><input type="checkbox" data-column="11">上月充值</label>

                                        <label><input type="checkbox" data-column="12">上月消费</label>

                                        <label><input type="checkbox" checked data-column="13">当月消费</label>

                                        <label><input type="checkbox" checked data-column="14">昨日消费</label>

                                        <label><input type="checkbox" checked data-column="15">当天消费</label>

                                        <label><input type="checkbox" checked data-column="16">操作</label>

                                    </div>

                                </div>

                            </div>

                        </div>

                        <div class="portlet-body">

                            <div class="clearfix margin-bottom-5">
                                <@shiro.hasPermission name="customer:findAllCustomer">
                                    <div class="btn-group">
                                        <button class="btn-icon black" id="previewCheckBoxCustomerFinanceCustomerId">
                                            <i class="icon-envelope"></i>预览账单
                                        </button>
                                        <button class="btn-icon black" id="sendCheckBoxCustomerFinanceCustomerId">
                                            <i class="icon-envelope"></i>发送账单
                                        </button>
                                    </div>
                                </@shiro.hasPermission>
                            </div>

                            <div class="clearfix margin-bottom-5">

                                <table class="table-condensed flip-content" style="width: 100%">
                                    <tbody>
                                    <tr>
                                        <td>上周消费总额：${lastWeekConsumeTot!'0.0'}</td>
                                        <td>上周充值总额：${lastWeekChargeTot!'0.0'}</td>
                                        <td>上月消费总额：${lastMonthConsumeTot!'0.0'}</td>
                                        <td>上月充值总额：${lastMonthChargeTot!'0.0'}</td>
                                    </tr>
                                    <tr>
                                        <td>本月消费总额：${currMonthTotleConsumeAmount!'0.0'}</td>
                                        <td>当天消费总额：${currDayTotleConsumeAmount!'0.0'}</td>
                                        <td>消费总额：${totleConsumeAmount!'0.0'}</td>
                                        <td>充值总额：${totleChargeAmount!'0.0'}</td>
                                    </tr>
                                    </tbody>
                                </table>

                            </div>

                            <div class="table-responsive">

                                <table class="table table-striped table-bordered table-hover table-condensed" id="sample_2">
                                    <thead>
                                    <tr>
                                        <th><input disabled="disabled" type="checkbox" id="allChecked" class="group-checkable" data-set="#sample_2 .checkboxes"/></th>
                                        <th>公司名称</th>
                                        <@shiro.hasPermission name="customer:findAllCustomer">
                                            <th>合作公司</th>
                                        </@shiro.hasPermission>
                                        <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                            <th style="display: none">合作公司</th>
                                        </@shiro.hasPermission>
                                        <th>信用额度</th>
                                        <th>可用额度</th>
                                        <th>余额</th>
                                        <th>充值总额(不包括今天)</th>
                                        <th>消费总额(${beginDate!'开通后'}--${endDate!'至今'})</th>
                                        <th>上周充值</th>
                                        <th>上周消费</th>
                                        <th>上月充值</th>
                                        <th>上月消费</th>
                                        <th>当月消费</th>
                                        <th>昨日消费</th>
                                        <th>当日消费</th>
                                        <th>近一周消费走势</th>
                                        <th class="table-td-none">产品类型</th>
                                        <th class="table-td-none">产品价格</th>
                                        <th class="table-td-none">总消费额</th>
                                        <th class="table-td-none">请求次数</th>
                                        <th class="table-td-none">扣费次数</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if customerFinanceList??>
                                            <#list customerFinanceList as customer>
                                            <tr>
                                                <td><input <#if customer.email?? && customer.consuTime??><#else >disabled="disabled"</#if> class="checkboxes" type="checkbox" id="checkBoxCustomerFinanceCustomerId" name="checkBoxCustomerFinanceCustomerId" value="${customer.id}"/></td>
                                                <td data-title="公司名称" <#if customer.companyStatus != 0> class="font-text-decoration"  </#if>>${customer.companyName}</td>
                                                <@shiro.hasPermission name="customer:findAllCustomer">
                                                    <td data-title="合作公司">
                                                        <#if customer.partnerId??>
                                                            <a href="/finance/find-all-customer?partnerId=${customer.partnerId?c}">${customer.partnerName!'无'}</a>
                                                        <#else >
                                                            无
                                                        </#if>
                                                    </td>
                                                </@shiro.hasPermission>
                                                <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                                    <td data-title="合作公司" style="display: none"><a href="/finance/find-all-customer-by-dept-id<#if customer.partnerId??>?partnerId=${customer.partnerId?c}</#if>">${customer.partnerName!''}</td>
                                                </@shiro.hasPermission>
                                                <td data-title="信用额度">${customer.floor!'0.0'}</td>
                                                <td data-title="可用额度">${customer.surplusFloor!'0.0'}</td>
                                                <td data-title="账号余额">${customer.balance!'0.0'}</td>
                                                <td data-title="充值总额"><a href="/finance/find-all-customer/find-all-customer-recharge-log-by-customer-id?customerId=${customer.id}&reasonId=1&companyName=${customer.companyName}" data-toggle="tooltip" data-placement="bottom" title="点击查看充值记录">${customer.chargeTotleAmount!'0.0'}</a></td>
                                                <td data-title="消费总额">${customer.consumeTotleAmount!'0.0'}</td>
                                                <td data-title="上周充值">${customer.lastWeekCharge!'0.0'}</td>
                                                <td data-title="上周消费">${customer.lastWeekConsume!'0.0'}</td>
                                                <td data-title="上月充值">${customer.lastMonthCharge!'0.0'}</td>
                                                <td data-title="上月消费">${customer.lastMonthConsume!'0.0'}</td>
                                                <td data-title="本月消费">${customer.currMonthAmount!'0.0'}</td>
                                                <td data-title="昨日消费">${customer.yesterdayConsume!'0.0'}</td>
                                                <@shiro.hasPermission name="customer:findAllCustomer">
                                                    <td data-title="当日消费"><a href="#form_modal_customer_curr_day_api_type_consume" data-toggle="modal" onclick="currDayApiTypeConsume(${customer.id})" data-toggle="tooltip" data-placement="bottom" title="点击查看当天消费情况">${customer.currDayAmount!'0.0'}</a></td>
                                                </@shiro.hasPermission>
                                                <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                                    <td data-title="当日消费">${customer.currDayAmount!'0.0'}</td>
                                                </@shiro.hasPermission>
                                                <td data-title="近一周消费走势"><a href="/finance/customer-nearly-week-thread?cid=${customer.id}&name=${customer.companyName}">查看</a></td>
                                                <td data-title="产品类型" class="table-td-none">
                                                    <#if customer.companyApiList?? && (customer.companyApiList?size>0)>
                                                        <#list customer.companyApiList as companyApi>
                                                            <#if companyApi.apiTypeId??>
                                                                <#if companyApi.enabled??>
                                                                    <#if companyApi.enabled==0>
                                                                    <span class="font-text-decoration">
                                                                    <#else>
                                                                    <span>
                                                                    </#if>
                                                                <#else >
                                                                <span class="warning">
                                                                </#if>
                                                                <#if companyApi.apiType??>
                                                                ${companyApi.apiType.name!'未知'}
                                                                    <#if companyApi.mobileOperator??>--${companyApi.mobileOperator.name!''}</#if>
                                                                </#if>
                                                            </span><br/>
                                                            <#else >
                                                                <br/>
                                                            </#if>
                                                        </#list>
                                                    </#if>
                                                </td>
                                                <td data-title="产品价格" class="table-td-none">
                                                    <#if customer.companyApiList?? && (customer.companyApiList?size>0)>
                                                        <#list customer.companyApiList as companyApi>
                                                            <#if companyApi.apiTypeId??>
                                                                <#if companyApi.price??>
                                                                    <span>${(companyApi.price/100.0)!''}</span><br/>
                                                                <#else >
                                                                    <span class="warning">未知</span><br/>
                                                                </#if >
                                                            <#else >
                                                                <br/>
                                                            </#if>
                                                        </#list>
                                                    </#if>
                                                </td>
                                                <td data-title="总消费额" class="table-td-none">
                                                    <#if customer.companyApiList?? && (customer.companyApiList?size>0)>
                                                        <#list customer.companyApiList as companyApi>
                                                            <#if companyApi.apiTypeId??>
                                                                <#if companyApi.companyApiCount?? && companyApi.companyApiCount.sumAmount??>
                                                                    <span>${(-companyApi.companyApiCount.sumAmount/100.0)!'0'}</span><br/>
                                                                <#else >
                                                                    <span>0</span></br>
                                                                </#if>
                                                            <#else >
                                                                <br/>
                                                            </#if>
                                                        </#list>
                                                    </#if>
                                                </td>
                                                <td data-title="请求次数" class="table-td-none">
                                                    <#if customer.companyApiList?? && (customer.companyApiList?size>0)>
                                                        <#list customer.companyApiList as companyApi>
                                                            <#if companyApi.apiTypeId??>
                                                                <#if companyApi.companyApiCount??>
                                                                    <span>${(companyApi.companyApiCount.countTotle)!'0'}</span><br/>
                                                                <#else >
                                                                    <span>0</span></br>
                                                                </#if>
                                                            <#else >
                                                                <br/>
                                                            </#if>
                                                        </#list>
                                                    </#if>
                                                </td>
                                                <td data-title="扣费次数" class="table-td-none">
                                                    <#if customer.companyApiList?? && (customer.companyApiList?size>0)>
                                                        <#list customer.companyApiList as companyApi>
                                                            <#if companyApi.apiTypeId??>
                                                                <#if companyApi.companyApiCount??>
                                                                    <span>${(companyApi.companyApiCount.countSuccess)!'0'}</span><br/>
                                                                <#else >
                                                                    <span>0</span></br>
                                                                </#if>
                                                            <#else >
                                                                <br/>
                                                            </#if>
                                                        </#list>
                                                    </#if>
                                                </td>
                                            </tr>
                                            </#list>
                                        </#if>

                                    </tbody>

                                </table>

                            </div>

                        <#--客户当天产品类型消费弹框-->
                            <div id="form_modal_customer_curr_day_api_type_consume" class="modal hide fade myModalCurrDayApiTypeConsume" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_customer_curr_day_api_type_consume" aria-hidden="true">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                    <h4 id="myModalLabel_customer_curr_day_api_type_consume"><span id="customer_company_name"></span>当天各产品类型消费情况</h4>
                                </div>

                                <div class="modal-body">

                                    <table class="table table-striped table-hover table-bordered table-condensed" id="simple_customer_curr_day_api_type_consume">
                                        <thead>
                                        <tr>
                                            <th>产品类型</th>
                                            <th>当前价格</th>
                                            <th>总消费额（单位：元）</th>
                                            <#--<th>请求次数</th>-->
                                            <th>扣费次数</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
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

    <script src="/js/myjs/customerleftbar.js?v=${ver}"></script>

    <script src="/js/myjs/customer-finance-account.js?v=${ver}"></script>

    <script src="/js/oldlocal/customer-finance-account.js?v=${ver}"></script>

    <script src="/js/oldlocal/customer-finance-curr-day-api-type-consume.js?v=${ver}"></script>

    <script src="/js/oldlocal/customer-finance-account-send-email.js?v=${ver}"></script>

    <script>
        jQuery(document).ready(function() {
            CustomerFinanceAccount.init();
            CustomerLeftBar.init();

        });

    </script>

    </#if>

</@layout>
