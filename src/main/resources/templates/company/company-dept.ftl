<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content" xmlns="http://www.w3.org/1999/html">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">

                    <form action="/company/find-all-company-customer-by-dept-id" class="form-bottom" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom head-search-top">

                                <label class="control-label">&nbsp;&nbsp;</label>

                                <div class="controls">

                                    <div class="input-append">

                                        <input class="m-wrap" <#if content??>value="${content}" </#if> type="text" id="companyName" name="content" placeholder="请输入公司名称">

                                        <button class="btn black" type="submit">搜索</button>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </form>

                    <div class="tabbable tabbable-custom boxless">

                        <ul class="nav nav-tabs">

                            <li class="active"><a href="#tab_1" data-toggle="tab">状态正常</a></li>

                            <li><a class="" href="#tab_2" data-toggle="tab">被禁用</a></li>

                        </ul>

                        <div class="tab-content">

                            <div class="tab-pane active" id="tab_1">

                            <#--状态正常-->
                                <div class="portlet box grey">

                                    <div class="portlet-body">

                                        <div class="table-responsive">
                                            <table class="table table-striped table-hover table-bordered table-condensed" id="companySample_1">
                                                <thead>
                                                <tr>
                                                    <th>公司名称</th>
                                                    <th>信用额度</th>
                                                    <th>可用额度</th>
                                                    <th>余额</th>
                                                    <th class="table-td-none">账号authId</th>
                                                    <th class="table-td-none">账号类型</th>
                                                    <th class="table-td-none">账号密码</th>
                                                    <th class="table-td-none">账号余额</th>
                                                    <th class="table-td-none">账号状态</th>
                                                    <th class="table-td-none">账号Ip段</th>
                                                    <th style="text-align: center;">操作</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                    <#if companyList??>
                                                        <#list companyList as company>
                                                            <#if company.companyStatus == 0>
                                                            <tr>
                                                                <td data-title="公司名称">${company.companyName!''}</td>
                                                                <td data-title="信用额度">${(-company.floor/100.0)?c}</td>
                                                                <td data-title="可用额度">${(company.surplusFloor/100.0)?c}</td>
                                                                <td data-title="余额"><#if company.companyBalance??>${(company.companyBalance/100.0)?c}<#else >0</#if></td>
                                                                <td data-title="账号authId" class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <#if customer.customerStatus.id != 0>
                                                                            <span class="font-text-decoration">
                                                                            <#else >
                                                                            <span>
                                                                            </#if>
                                                                        ${customer.authId!''}</span><br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td data-title="账号类型" class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <span>${(customer.customerType.name)!''}</span><br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td data-title="账号密码" class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <span id="table_password_${customer.id}">
                                                                                <a href="javaScript:;" onclick="showPassword(${customer.id})" data-toggle="tooltip" data-placement="auto" title="点击显示密码">显示密码</a>
                                                                            </span>
                                                                            <span id="table_content_password_${customer.id}" style="display: none">${customer.authPass!''}</span>
                                                                            <br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td data-title="账号余额" class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <span><#if customer.balance??>${(customer.balance/100.0)?c}</#if></span><br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td data-title="账号状态" class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <#if (customer.customerStatus.id)?? && customer.customerStatus.id ==0>
                                                                            <span>
                                                                            <#else >
                                                                            <span class="warning">
                                                                            </#if>
                                                                        ${(customer.customerStatus.name)!''}
                                                                        </span><br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td data-title="账号Ip段" class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <#if customer.customerType.id == 1>
                                                                                <a href="#form_modal_customer_ip_list"  onclick="showIp(${customer.id})" data-toggle="modal">Ip管理</a>
                                                                            </#if>
                                                                            <br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td data-title="操作" style="text-align: center;">
                                                                    <a href="#form_modal_company_api_status" onclick="findCompanyApi(${company.companyId})" data-toggle="modal">产品权限管理</a>
                                                                </td>
                                                            </tr>
                                                            </#if>
                                                        </#list>
                                                    </#if>
                                                </tbody>

                                            </table>
                                        </div>

                                    </div>

                                </div>

                            </div>

                            <div class="tab-pane " id="tab_2">

                            <#--禁用-->
                                <div class="portlet box grey">

                                    <div class="portlet-body">

                                        <div class="table-responsive">
                                            <table class="table table-striped table-hover table-bordered table-condensed" id="companySample_2">
                                                <thead>
                                                <tr>
                                                    <th>公司名称</th>
                                                    <th>信用额度</th>
                                                    <th>可用额度</th>
                                                    <th>余额</th>
                                                    <th class="table-td-none">账号authId</th>
                                                    <th class="table-td-none">账号类型</th>
                                                    <th class="table-td-none">账号密码</th>
                                                    <th class="table-td-none">账号余额</th>
                                                    <th class="table-td-none">账号状态</th>
                                                    <th class="table-td-none">账号Ip段</th>
                                                    <th style="text-align: center;">操作</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                    <#if companyList??>
                                                        <#list companyList as company>
                                                            <#if company.companyStatus != 0>
                                                            <tr>
                                                                <td data-title="公司名称" class="font-text-decoration">${company.companyName!''}</td>
                                                                <td data-title="信用额度">${(-company.floor/100.0)?c}</td>
                                                                <td data-title="可用额度">${(company.surplusFloor/100.0)?c}</td>
                                                                <td data-title="余额"><#if company.companyBalance??>${(company.companyBalance/100.0)?c}<#else >0</#if></td>
                                                                <td data-title="账号authId" class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <#if customer.customerStatus.id != 0>
                                                                            <span class="font-text-decoration">
                                                                            <#else >
                                                                            <span>
                                                                            </#if>
                                                                        ${customer.authId!''}</span><br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td data-title="账号类型" class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <span>${(customer.customerType.name)!''}</span><br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td data-title="账号密码" class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <span id="table_password_${customer.id}">
                                                                                <a href="javaScript:;" onclick="showPassword(${customer.id})" data-toggle="tooltip" data-placement="auto" title="点击显示密码">显示密码</a>
                                                                            </span>
                                                                            <span id="table_content_password_${customer.id}" style="display: none">${customer.authPass!''}</span>
                                                                            <br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td data-title="账号余额" class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <span><#if customer.balance??>${(customer.balance/100.0)?c}</#if></span><br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td data-title="账号状态" class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <#if (customer.customerStatus.id)?? && customer.customerStatus.id ==0>
                                                                            <span>
                                                                            <#else >
                                                                            <span class="warning">
                                                                            </#if>
                                                                        ${(customer.customerStatus.name)!''}
                                                                        </span><br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td data-title="账号Ip段" class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <#if customer.customerType.id == 1>
                                                                                <a href="#form_modal_customer_ip_list"  onclick="showIp(${customer.id})" data-toggle="modal">Ip管理</a>
                                                                            </#if>
                                                                            <br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td data-title="操作" style="text-align: center;">
                                                                    <a href="#form_modal_company_api_status" onclick="findCompanyApi(${company.companyId})" data-toggle="modal">产品权限管理</a>
                                                                </td>
                                                            </tr>
                                                            </#if>
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

                <#--修改余额-->
                    <div id="form_modal_update_balance" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_update_balance" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h3 id="myModalLabel_update_balance">请填写信息</h3>
                        </div>
                        <div class="modal-body">
                            <form action="#" class="form-horizontal">
                                <div class="control-group"></div>
                                <div class="control-group"></div>
                                <div id="error_alert_update_balance"></div>
                                <span id="update_balance_customerId" style="display: none;"></span>
                                <div id="" class="control-group">
                                    <label class="control-label">请输入金额<span class="required">*</span></label>
                                    <div class="controls">
                                        <input type="text" id="update_balance_amount" name="update_balance_amount" class="m-wrap medium" placeholder="（单位/元）">
                                        <span id="update_balance_amountMsg" class="help-line"></span>
                                        <span class="help-block">说明：只能输入数字类型并且金额大于0</span>
                                    </div>
                                </div>
                                <div id="" class="control-group">
                                    <label class="control-label">请选择理由<span class="required">*</span></label>
                                    <div class="controls">
                                        <select id="update_balance_reasonId" name="update_balance_reasonId" class="medium m-wrap" tabindex="1">
                                        </select>
                                        <span id="update_balance_reasonIdMsg" class="help-inline"></span>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                            <button class="btn black btn-primary" id="update-balance-btn-black-btn-primary" type="button">提交</button>
                        </div>
                    </div>

                <#--产品管理-->
                    <div id="form_modal_company_api_status" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_company_api_status" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h3 id="myModalLabel_company_api_status">产品权限管理</h3>
                        </div>
                    <#--产品列表-->
                        <div class="modal-body">
                            <div class="clearfix">
                                <@shiro.hasPermission name="customer:findAllCustomer">
                                    <div class="btn-group" style="margin-bottom: 10px;">
                                        <a id="simple_company_api_1_new" class="btn black">
                                            添加产品权限<i class="icon-plus"></i>
                                        </a>
                                    </div>
                                </@shiro.hasPermission>
                            </div>
                            <span id="apiType_companyId" style="display: none;">

                            </span>
                            <div id="error_alert_company_api" style="margin-bottom: -15px;margin-top: 10px;">

                            </div>
                            <div class="table-responsive">
                                <table class="table table-striped table-hover table-bordered table-condensed" width="" id="simple_company_api_1">
                                    <thead>
                                    <tr>
                                        <th style="width: 40%">产品名称</th>
                                        <th style="width: 25%">价格（单位：元）</th>
                                        <th style="width: 15%">状态</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                <#--Ip管理-->
                    <div id="form_modal_customer_ip_list" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_customer_ip_list" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h3 id="myModalLabel_customer_ip_list">客户Ip列表</h3>
                        </div>

                        <div class="modal-body">
                            <div class="clearfix">
                                <@shiro.hasPermission name="customer:findAllCustomer">
                                    <div class="btn-group" style="margin-bottom: 10px;">
                                        <a id="simple_customer_ip_1_new" class="btn black">
                                            添加Ip<i class="icon-plus"></i>
                                        </a>
                                    </div>
                                </@shiro.hasPermission>
                            </div>
                            <span id="ip_customerId" style="display: none;">

                            </span>
                            <div id="error_alert_customer_ip" style="margin-bottom: -15px;margin-top: 10px;">

                            </div>
                            <table class="table table-striped table-hover table-bordered table-condensed" id="simple_customer_ip_1">
                                <thead>
                                <tr>
                                    <th>起始Ip</th>
                                    <th>结束Ip</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>

                <#--新增客户-添加账号-批量分配权限-批量添加Ip-->
                    <div id="form_modal_from_wizard" class="modal hide fade myModalWizard" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_from_wizard" aria-hidden="true">

                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h3 id="myModalLabel_from_wizard">
                                &nbsp;
                            </h3>
                        </div>

                        <div class="modal-body">

                            <div class="row-fluid">

                                <div class="span12">

                                    <div class="portlet box blue" id="form_wizard_1">

                                        <div class="portlet-title">

                                            <div class="caption">

                                                新增客户 - <span class="step-title">第 1 步共 4 步</span>

                                            </div>

                                        </div>

                                        <div class="portlet-body form">

                                            <form action="#" class="form-horizontal" id="submit_form">

                                                <div class="form-wizard">

                                                    <div class="navbar steps">

                                                        <div class="navbar-inner">

                                                            <ul class="row-fluid">

                                                                <li class="span3">

                                                                    <a href="#tab1" data-toggle="tab" class="step active">

                                                                        <span class="number">1</span>

                                                                        <span class="desc"><i class="icon-ok"></i> 填写账号信息</span>

                                                                    </a>

                                                                </li>

                                                                <li class="span3">

                                                                    <a href="#tab2" data-toggle="tab" class="step">

                                                                        <span class="number">2</span>

                                                                        <span class="desc"><i class="icon-ok"></i> 添加产品权限</span>

                                                                    </a>

                                                                </li>

                                                                <li class="span3">

                                                                    <a href="#tab3" data-toggle="tab" class="step">

                                                                        <span class="number">3</span>

                                                                        <span class="desc"><i class="icon-ok"></i> 添加账号Ip</span>

                                                                    </a>

                                                                </li>

                                                                <li class="span3">

                                                                    <a href="#tab4" data-toggle="tab" class="step">

                                                                        <span class="number">4</span>

                                                                        <span class="desc"><i class="icon-ok"></i> 确认</span>

                                                                    </a>

                                                                </li>

                                                            </ul>

                                                        </div>

                                                    </div>

                                                    <div id="bar" class="progress progress-success progress-striped">

                                                        <div class="bar"></div>

                                                    </div>

                                                    <div class="tab-content">

                                                        <div class="alert alert-error hide">

                                                            <button class="close" data-dismiss="alert"></button>

                                                            <span id="tip">对不起，请检查你的输入！</span>

                                                        </div>

                                                        <div class="alert alert-success hide">

                                                            <button class="close" data-dismiss="alert"></button>

                                                            恭喜您，操作成功!

                                                        </div>

                                                        <div class="tab-pane active" id="tab1">

                                                            <h3 class="block">请填写账号信息</h3>

                                                            <div class="control-group">
                                                                <label class="control-label">请输入公司名称<span class="required">*</span></label>
                                                                <div class="controls">
                                                                    <input type="text" id="companyCustomerName" name="companyCustomerName" class="span6 m-wrap">
                                                                    <span id="companyNameMsg" class="help-line"></span>
                                                                </div>
                                                            </div>

                                                            <div class="control-group">
                                                                <label class="control-label">请输入账号<span class="required">*</span></label>
                                                                <div class="controls">
                                                                    <input type="text" id="authId" name="authId" class="span6 m-wrap">
                                                                    <span id="authIdMsg" class="help-line"></span>
                                                                    <span class="help-block">说明：只能有数字、字母、下划线组成</span>
                                                                </div>
                                                            </div>

                                                            <div class="control-group">
                                                                <label class="control-label">请选择合作公司</label>
                                                                <div class="controls">
                                                                    <select id="partnerId" name="partnerId" class="span6 medium" tabindex="1">
                                                                        <option value="">请选择...</option>
                                                                        <#if partnerList??>
                                                                            <#list partnerList as partner>
                                                                                <option value="${partner.id}">${partner.name}</option>
                                                                            </#list>
                                                                        </#if>
                                                                    </select>
                                                                </div>
                                                            </div>

                                                        </div>

                                                        <div class="tab-pane" id="tab2">

                                                            <h3 class="block">添加产品权限</h3>

                                                            <div class="control-group text-right">
                                                                <a href="#" id="control-group-add-api-href"><span><i class="icon-plus"></i>点击添加一栏</span></a>
                                                            </div>

                                                            <div id="control-group-add-api">

                                                                <div class="form-section" style="border-bottom: 1px solid #999;">

                                                                    <div class="control-group">

                                                                        <label class="control-label">产品类型</label>

                                                                        <div class="controls">

                                                                            <select id="add_api_type_sub" name="add_api_type_sub_1" class="span6 m-wrap">
                                                                                <option value="">请选择...</option>
                                                                                <#if apiTypeList??>
                                                                                    <#list apiTypeList as apiType>
                                                                                        <#if apiType.mobileOperatorName??>
                                                                                            <option value="${apiType.apiTypeId}-${apiType.mobileOperatorId}">${apiType.apiTypeName}--${apiType.mobileOperatorName}</option>
                                                                                        <#else >
                                                                                            <option value="${apiType.apiTypeId}">${apiType.apiTypeName}</option>
                                                                                        </#if>
                                                                                    </#list>
                                                                                </#if>
                                                                            </select>

                                                                            <span class="help-inline"></span>

                                                                        </div>

                                                                    </div>

                                                                    <div class="control-group">

                                                                        <label class="control-label">产品价格</label>

                                                                        <div class="controls">

                                                                            <input type="text" id="add_api_type_sub_price" name="add_api_type_sub_price_1" class="span6 m-wrap" placeholder="单位：元"/>

                                                                            <span class="help-inline"></span>

                                                                            <span class="help-block">说明：只能输入数字并且大于等于0</span>

                                                                        </div>

                                                                    </div>

                                                                </div>

                                                            </div>

                                                        </div>

                                                        <div class="tab-pane" id="tab3">

                                                            <h3 class="block">添加正式账号Ip</h3>

                                                            <div class="control-group text-right">
                                                                <a href="#" id="control-group-add-ip-href"><span><i class="icon-plus"></i>点击添加一栏</span></a>
                                                            </div>

                                                            <div id="control-group-add-ip">

                                                                <div class="form-section" style="border-bottom: 1px solid #999;">

                                                                    <div class="control-group">

                                                                        <label class="control-label">起始Ip</label>

                                                                        <div class="controls">

                                                                            <input type="text" class="span6 m-wrap" id="input_ipv4_begin" name="input_ipv4_begin"/>

                                                                            <span class="help-inline"></span>

                                                                            <span class="help-block">例如：192.168.111.123</span>

                                                                        </div>

                                                                    </div>

                                                                    <div class="control-group">

                                                                        <label class="control-label">终止Ip</label>

                                                                        <div class="controls">

                                                                            <input type="text" class="span6 m-wrap" id="input_ipv4_end" name="input_ipv4_end"/>

                                                                            <span class="help-inline"></span>

                                                                            <span class="help-block">例如：192.168.111.123</span>

                                                                        </div>

                                                                    </div>

                                                                </div>

                                                            </div>

                                                        </div>

                                                        <div class="tab-pane" id="tab4">

                                                            <h3 class="block">请确认要提交的信息</h3>

                                                            <h4 class="form-section">账号信息</h4>

                                                            <div class="control-group">

                                                                <label class="control-label">公司名称:</label>

                                                                <div class="controls">

                                                                    <span class="text display-value" data-display="companyCustomerName"></span>

                                                                </div>

                                                            </div>

                                                            <div class="control-group">

                                                                <label class="control-label">账号:</label>

                                                                <div class="controls">

                                                                    <span class="text display-value" data-display="authId"></span>

                                                                </div>

                                                            </div>

                                                            <div class="control-group">

                                                                <label class="control-label">合作公司:</label>

                                                                <div class="controls">

                                                                    <span class="text display-value" data-display="partnerId"></span>

                                                                </div>

                                                            </div>

                                                            <h4 class="form-section">产品权限信息</h4>

                                                            <div id="control-group-add-api-affirm">

                                                                <div class="control-group">

                                                                    <label class="control-label">产品类型:</label>

                                                                    <div class="controls">

                                                                        <span class="text display-value" data-display="add_api_type_sub_1"></span>

                                                                    </div>

                                                                </div>

                                                                <div class="control-group">

                                                                    <label class="control-label">价格:</label>

                                                                    <div class="controls">

                                                                        <span class="text display-value" data-display="add_api_type_sub_price_1"></span>

                                                                    </div>

                                                                </div>

                                                            </div>

                                                            <h4 class="form-section">Ip信息</h4>

                                                            <div id="control-group-add-ip-affirm">

                                                                <div class="control-group">

                                                                    <label class="control-label">起始Ip:</label>

                                                                    <div class="controls">

                                                                        <span class="text display-value" data-display="input_ipv4_begin"></span>

                                                                    </div>

                                                                </div>

                                                                <div class="control-group">

                                                                    <label class="control-label">终止Ip:</label>

                                                                    <div class="controls">

                                                                        <span class="text display-value" data-display="input_ipv4_end"></span>

                                                                    </div>

                                                                </div>

                                                            </div>

                                                        </div>

                                                    </div>

                                                    <div class="form-actions clearfix">

                                                        <a href="javascript:;" class="btn button-previous">

                                                            <i class="m-icon-swapleft"></i> 后退

                                                        </a>

                                                        <a href="javascript:;" class="btn blue button-next">

                                                            继续 <i class="m-icon-swapright m-icon-white"></i>

                                                        </a>

                                                        <a href="javascript:;" class="btn green button-submit">

                                                            提交 <i class="m-icon-swapright m-icon-white"></i>

                                                        </a>

                                                    </div>

                                                </div>

                                            </form>

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

    <script src="/assect/jquery-1.10.1.min.js" type="text/javascript"></script>

    <script src="/assect/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>

    <script src="/assect/bootstrap.min.js" type="text/javascript"></script>

    <script src="/assect/excanvas.min.js"></script>

    <script src="/assect/respond.min.js"></script>

    <script src="/assect/jquery.slimscroll.min.js" type="text/javascript"></script>

    <script src="/assect/jquery.blockui.min.js" type="text/javascript"></script>

    <script src="/assect/jquery.cookie.min.js" type="text/javascript"></script>

    <script src="/assect/jquery.uniform.min.js" type="text/javascript" ></script>

    <script type="text/javascript" src="/assect/jquery.validate.min.js"></script>

    <script type="text/javascript" src="/assect/additional-methods.min.js"></script>

    <script type="text/javascript" src="/assect/jquery.bootstrap.wizard.min.js"></script>

    <script type="text/javascript" src="/assect/jquery-migrate-1.2.1.min.js"></script>

    <script type="text/javascript" src="/assect/chosen.jquery.min.js"></script>

    <script type="text/javascript" src="/assect/select2.min.js"></script>

    <script type="text/javascript" src="/assect/jquery.input-ip-address-control-1.0.min.js"></script>

    <script src="/assect/app.js"></script>

    <script src="/assect/form-wizard.js"></script>

    <script>

        jQuery(document).ready(function() {

            App.init();

        });

    </script>

    <script type="text/javascript">
        var _gaq = _gaq || [];
        _gaq.push(['_setAccount', 'UA-37564768-1']);
        _gaq.push(['_setDomainName', 'keenthemes.com']);
        _gaq.push(['_setAllowLinker', true]);
        _gaq.push(['_trackPageview']);
        (function() {    var ga = document.createElement('script');
            ga.type = 'text/javascript';
            ga.async = true;
            ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';
            var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();
    </script>

    <script type="text/javascript" src="/js/former/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/former/DT_bootstrap.js"></script>

    <script src="/js/locales/bootstrap-multiselect.js"></script>

    <script src="/js/oldlocal/dept/customer-company.js"></script>

    <script src="/js/oldlocal/dept/customer-company-forbid.js"></script>

    <script src="/js/oldlocal/dept/company-api-operator.js"></script>

    <script src="/js/oldlocal/dept/company-ip-operator.js"></script>

    <script src="/js/oldlocal/company-charge-fee.js"></script>

    <script src="/js/oldlocal/company.js"></script>

    <script src="/js/oldlocal/company-add-company.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            AddCompanyAllotApiAddIp.init();
            Company.init();
            CompanyForbid.init();

            //全局的ajax访问，处理ajax清求时sesion超时
            $.ajaxSetup({
                contentType:"application/x-www-form-urlencoded;charset=utf-8",
                complete:function(XMLHttpRequest,textStatus){
                    //通过XMLHttpRequest取得响应头，sessionstatus，
                    var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");
                    if(sessionstatus=="timeout"){
                        //如果超时就处理 ，指定要跳转的页面
                        //window.location.href = window.location.href;
                        location.reload();
                    }
                }
            });

        });


        /*Api-点击添加一栏*/
        $('#control-group-add-api-href').on('click',function () {

            var add_api_type_sub = [];
            $("select[id='add_api_type_sub']").each(function(){
                add_api_type_sub.push($(this).val());
            });

            console.log(add_api_type_sub);

            var count = add_api_type_sub.length + 1 ;

            console.log(count);

            var res = $('select[name="add_api_type_sub_'+ (count-1) +'"]').val();

            console.log(res);

            if (res == null || res == "" || res == "请选择..."){
                alert("亲，请先完成当前选择后再点击添加按钮哦！");
                return;
            }

            $.ajax({
                type: "post",
                url: "/company/find-all-api-type",
                data: {"add_api_type_sub" : add_api_type_sub},
                dataType: "json",
                success: function (data) {

                    if(data != null && data.length > 0 ){
                        $('#control-group-add-api').append(
                                '<div class="form-section" style="border-bottom: 1px solid #999;">' +
                                '<div class="control-group">' +
                                '<label class="control-label">产品类型</label>' +
                                '<div class="controls">' +
                                '<select id="add_api_type_sub" name="add_api_type_sub_'+ count +'" class="span6 m-wrap">' +
                                '<option value="">请选择...</option>' +
                                '</select>' +
                                '<span class="help-inline"></span>' +
                                '</div>' +
                                '</div>' +
                                '<div class="control-group">' +
                                '<label class="control-label">产品价格</label>' +
                                '<div class="controls">' +
                                '<input type="text" id="add_api_type_sub_price" name="add_api_type_sub_price_'+ count +'" class="span6 m-wrap" placeholder="单位：元"/>' +
                                '<span class="help-inline"></span>' +
                                '<span class="help-block">说明：只能输入数字并且大于等于0</span>' +
                                '</div>' +
                                '</div>' +
                                '<div>'
                        );
                        var myContent = null;
                        for (var i = 0; i < data.length; i++){
                            if (data[i].mobileOperatorName == null){
                                myContent="<option value='"+ data[i].apiTypeId +"'>"+ data[i].apiTypeName +"</option>";
                            }else{
                                myContent="<option value='"+data[i].apiTypeId+'-'+data[i].mobileOperatorId+"'>"+ data[i].apiTypeName+'--'+data[i].mobileOperatorName +"</option>";
                            }
                            $('select[name="add_api_type_sub_'+ count +'"]').append(myContent);
                        }

                        $('#control-group-add-api-affirm').append(
                                '<div class="control-group">' +
                                '<label class="control-label">产品类型:</label>' +
                                '<div class="controls">' +
                                '<span class="text display-value" data-display="add_api_type_sub_'+ count +'"></span>' +
                                '</div>' +
                                '</div>' +
                                '<div class="control-group">' +
                                '<label class="control-label">价格:</label>' +
                                '<div class="controls">' +
                                '<span class="text display-value" data-display="add_api_type_sub_price_'+ count +'"></span>' +
                                '</div>' +
                                '</div>'
                        );

                    }else {
                        alert("亲，你已选择了所有的产品类型哦！");
                    }
                }
            });

        });

        /*Ip-点击添加一栏*/
        $('#control-group-add-ip-href').on('click',function () {

            var input_ipv4_begin = [];
            $("input[id='input_ipv4_begin']").each(function(){
                input_ipv4_begin.push($(this).val());
            });

            var count = input_ipv4_begin.length ;

            $('#control-group-add-ip').append(
                    '<div class="form-section" style="border-bottom: 1px solid #999;">' +
                    '<div class="control-group">' +
                    '<label class="control-label">起始Ip</label>'+
                    '<div class="controls">' +
                    '<input type="text" class="span6 m-wrap" id="input_ipv4_begin" name="input_ipv4_begin_'+ count +'"/>' +
                    '<span class="help-inline"></span>' +
                    '<span class="help-block">例如：192.168.111.123</span>' +
                    '</div>' +
                    '</div>' +
                    '<div class="control-group">' +
                    '<label class="control-label">终止Ip</label>' +
                    '<div class="controls">' +
                    '<input type="text" class="span6 m-wrap" id="input_ipv4_end" name="input_ipv4_end_'+ count +'"/>' +
                    '<span class="help-inline"></span>' +
                    '<span class="help-block">例如：192.168.111.123</span>' +
                    '</div>' +
                    '</div>' +
                    '</div>'
            );

            $('#control-group-add-ip-affirm').append(
                    '<div class="control-group">' +
                    '<label class="control-label">起始Ip:</label>' +
                    '<div class="controls">' +
                    '<span class="text display-value" data-display="input_ipv4_begin_'+ count +'"></span>' +
                    '</div>' +
                    '</div>' +
                    '<div class="control-group">' +
                    '<label class="control-label">终止Ip:</label>' +
                    '<div class="controls">' +
                    '<span class="text display-value" data-display="input_ipv4_end_'+ count +'"></span>' +
                    '</div>' +
                    '</div>'
            );
        });


    </script>

    <script type="text/javascript">

        (function(){
            $.extend($.fn,{
                mask: function(msg,maskDivClass){
                    this.unmask();
                    // 参数
                    var op = {
                        opacity: 0.8,
                        z: 100000,
                        bgcolor: '#ccc'
                    };
                    var original=$(document.body);
                    var position={top:0,left:0};
                    if(this[0] && this[0]!==window.document){
                        original=this;
                        position=original.position();
                    }
                    // 创建一个 Mask 层，追加到对象中
                    var maskDiv=$('<div class="maskdivgen" aria-hidden="true">&nbsp;</div>');
                    maskDiv.appendTo(original);
                    var maskWidth=original.outerWidth();
                    if(!maskWidth){
                        maskWidth=original.width();
                    }
                    var maskHeight=document.body.scrollHeight;
                    if(!maskHeight){
                        maskHeight=original.height();
                    }
                    maskDiv.css({
                        position: 'absolute',
                        top: position.top,
                        left: position.left,
                        'z-index': op.z,
                        width: maskWidth,
                        height:maskHeight,
                        'background-color': op.bgcolor,
                        opacity: 0
                    });
                    if(maskDivClass){
                        maskDiv.addClass(maskDivClass);
                    }
                    if(msg){

                        var msgDiv=$('<div style="position:absolute;padding:2px;background:#ccca;text-align: center"><div style="line-height:24px;border:#a3bad9 1px solid;background:white;padding:2px 10px 2px 10px;">'+msg+'<span class="loading"></span></div></div>');

                        //var msgDiv=$('<div style="position:absolute;"><div class="cube" style="line-height:24px;border:#a3bad9 1px solid;background:white;padding:2px 10px 2px 10px;"><div class="tg-qe-progress-bar myactive"><div class="bar"></div></div></div></div>');

                        msgDiv.appendTo(maskDiv);
                        var widthspace=(maskDiv.width()-msgDiv.width());
                        var heightspace=(maskDiv.height()-msgDiv.height());
                        msgDiv.css({
                            cursor:'wait',
                            top:(heightspace/2-2),
                            left:(widthspace/2-2)
                        });
                    }
                    maskDiv.fadeIn('fast', function(){
                        // 淡入淡出效果
                        $(this).fadeTo('slow', op.opacity);
                    });
                    return maskDiv;
                },
                unmask: function(){
                    var original=$(document.body);
                    if(this[0] && this[0]!==window.document){
                        original=$(this[0]);
                    }
                    original.find("> div.maskdivgen").fadeOut('slow',0,function(){
                        $(this).remove();
                    });
                }
            });
        })();

        function closeProgress() {
            $('#testhovertree').unmask()
        }

        function openProgress() {
            $(document).mask('正在处理,请稍后').click(function () {
                $(document).unmask()
            })
        }

    </script>

    </#if>

</@layout>