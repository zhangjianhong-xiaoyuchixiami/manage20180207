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

                    <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">

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

                    </@shiro.hasPermission>

                    <@shiro.hasPermission name="customer:findAllCustomer">

                        <form action="/company/find-all-company-customer" method="get">

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left" style="margin-bottom: -20px;margin-top: -25px;">

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

                    </@shiro.hasPermission>

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

                                        <div class="clearfix margin-bottom-5">
                                            <div class="btn-group">
                                                <a class="btn black" style="margin-right: 10px;" id="add-partner" href="#form_modal_from_wizard" data-toggle="modal">
                                                    新增客户
                                                </a>
                                                <a class="btn red" id="batchBanCompany" style="margin-right: 10px;" href="javaScript:;">
                                                    批量禁用客户
                                                </a>

                                            <#-- <a class="btn blue" style="margin-right: 10px;" href="#form_modal_add_api" data-toggle="modal">
                                                 批量分配产品权限
                                             </a>-->
                                            </div>

                                        </div>

                                        <div class="table-responsive">
                                            <table class="table table-striped table-hover table-bordered table-condensed" id="companySample_1">
                                                <thead>
                                                <tr>
                                                    <th><input type="checkbox" id="allChecked" class="group-checkable" data-set="#companySample_1 .checkboxes"/></th>
                                                    <th>公司名称</th>
                                                    <th>信用额度</th>
                                                    <th>剩余信用额度</th>
                                                    <@shiro.hasPermission name="customer:findAllCustomer">
                                                        <th>合作公司</th>
                                                    </@shiro.hasPermission>

                                                    <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                                        <th style="display: none">合作公司</th>
                                                    </@shiro.hasPermission>

                                                    <th>余额</th>
                                                    <th>创建时间</th>
                                                    <th class="table-td-none">typeName</th>
                                                    <th class="table-td-none">authId</th>
                                                    <th class="table-td-none">authPass</th>
                                                    <th class="table-td-none">balance</th>
                                                    <th class="table-td-none">statusName</th>
                                                    <th class="table-td-none">Ip段</th>
                                                    <th class="table-td-none">操作</th>
                                                    <th style="text-align: center; width: 10%;">操作</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                    <#if companyList??>
                                                        <#list companyList as company>
                                                            <#if company.companyStatus == 0>
                                                            <tr>
                                                                <td><input class="checkboxes" type="checkbox" id="checkBoxCompanyId" name="checkBoxCompanyId" value="${company.companyId}"/></td>
                                                                <td data-title="公司名称">${company.companyName!''}</td>
                                                                <td data-title="信用额度">${(-company.floor/100.0)?c}</td>
                                                                <td data-title="剩余信用额度">${(company.surplusFloor/100.0)?c}</td>
                                                                <@shiro.hasPermission name="customer:findAllCustomer">
                                                                    <td data-title="合作公司">
                                                                        <#if company.partnerId??>
                                                                            <a href="/company/find-all-company-customer?partnerId=${company.partnerId}">${company.partnerName!'无'}</a>
                                                                        <#else >
                                                                            无
                                                                        </#if>
                                                                    </td>
                                                                </@shiro.hasPermission>
                                                                <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                                                    <td data-title="合作公司" style="display: none"><a href="/company/find-all-company-customer-by-dept-id<#if company.partnerId??>?partnerId=${company.partnerId}</#if>">${company.partnerName!'无'}</a></td>
                                                                </@shiro.hasPermission>
                                                                <td data-title="余额"><#if company.companyBalance??>${(company.companyBalance/100.0)?c}<#else >0</#if></td>
                                                                <td data-title="创建时间">${company.companyCreateTime?date}</td>
                                                                <td class="table-td-none">
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
                                                                <td class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <span>${(customer.customerType.name)!''}</span><br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td class="table-td-none">
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
                                                                <td class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <span><#if customer.balance??>${(customer.balance/100.0)?c}</#if></span><br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td class="table-td-none">
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
                                                                <td class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <#if customer.customerType.id == 1>
                                                                            <#-- <#if customer.customerIpList?? && (customer.customerIpList?size>0)>-->
                                                                                <a href="#form_modal_customer_ip_list"  onclick="showIp(${customer.id})" data-toggle="modal">Ip管理</a>
                                                                            <#--<#else >
                                                                                <span class="warning">暂无Ip</span>
                                                                            </#if>-->
                                                                            </#if>
                                                                            <br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <a href="#form_modal_update_balance" id="charge_Balance" onclick="chargeBalance(${customer.id})" data-toggle="modal">充值</a>
                                                                            |
                                                                            <#if (customer.customerStatus.id)?? && customer.customerStatus.id ==0>
                                                                                <a href="javaScript:;" onclick="banCustomer('${customer.authId}')" class="warning">禁用</a><br/>
                                                                            <#else >
                                                                                <a href="javaScript:;" onclick="unBanCustomer('${customer.authId}')">启用</a><br/>
                                                                            </#if>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td style="text-align: center;">
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
                                                    <th>剩余信用额度</th>
                                                    <@shiro.hasPermission name="customer:findAllCustomer">
                                                        <th>合作公司</th>
                                                    </@shiro.hasPermission>

                                                    <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                                        <th style="display: none">合作公司</th>
                                                    </@shiro.hasPermission>

                                                    <th>余额</th>
                                                    <th>创建时间</th>
                                                    <th class="table-td-none">typeName</th>
                                                    <th class="table-td-none">authId</th>
                                                    <th class="table-td-none">authPass</th>
                                                    <th class="table-td-none">balance</th>
                                                    <th class="table-td-none">statusName</th>
                                                    <th class="table-td-none">Ip段</th>
                                                    <th class="table-td-none">操作</th>
                                                    <th style="text-align: center; width: 10%;">操作</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                    <#if companyList??>
                                                        <#list companyList as company>
                                                            <#if company.companyStatus != 0>
                                                            <tr>
                                                                <td data-title="公司名称" class="font-text-decoration">${company.companyName!''}</td>
                                                                <td data-title="信用额度">${(-company.floor/100.0)?c}</td>
                                                                <td data-title="剩余信用额度">${(company.surplusFloor/100.0)?c}</td>
                                                                <@shiro.hasPermission name="customer:findAllCustomer">
                                                                    <td data-title="合作公司"><a href="/company/find-all-company-customer<#if company.partnerId??>?partnerId=${company.partnerId}</#if>">${company.partnerName!'无'}</a></td>
                                                                </@shiro.hasPermission>
                                                                <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                                                    <td data-title="合作公司" style="display: none"><a href="/company/find-all-company-customer-by-dept-id<#if company.partnerId??>?partnerId=${company.partnerId}</#if>">${company.partnerName!'无'}</a></td>
                                                                </@shiro.hasPermission>
                                                                <td data-title="余额"><#if company.companyBalance??>${(company.companyBalance/100.0)?c}<#else >0</#if></td>
                                                                <td data-title="创建时间">${company.companyCreateTime?date}</td>
                                                                <td class="table-td-none">
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
                                                                <td class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <span>${(customer.customerType.name)!''}</span><br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td class="table-td-none">
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
                                                                <td class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <span><#if customer.balance??>${(customer.balance/100.0)?c}</#if></span><br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <span> ${(customer.customerStatus.name)!''}</span><br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <#if customer.customerType.id == 1>
                                                                                <a href="javaScript:;" id="customerIpBar_${customer.id}" onclick="showIp(${customer.id})">点击查看Ip段</a>
                                                                                <div id="customerIpContent_${customer.id}" class="head-search-display" style="min-width: 150px; background-color: white">
                                                                                    <#if customer.customerIpList?? && (customer.customerIpList?size>0)>
                                                                                        <#list customer.customerIpList as customerIp>
                                                                                            <span>${customerIp.beginIpRaw!''}-${customerIp.endIpRaw!''}</span><br/>
                                                                                        </#list>
                                                                                    <#else >
                                                                                        <span>无</span>
                                                                                    </#if>
                                                                                </div>
                                                                            </#if>
                                                                            <br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <a href="#form_modal_update_balance" id="charge_Balance" onclick="chargeBalance(${customer.id})" data-toggle="modal">充值</a>
                                                                            |
                                                                            <a href="#form_modal_update_balance" id="consume_Balance" onclick="consumeBalance(${customer.id})" data-toggle="modal">扣费</a><br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td><a href="#form_modal_add_account" onclick="addAccount(${company.companyId})" data-toggle="modal">添加账号</a></td>
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

                <#--新增公司-->
                <#--  <div id="form_modal1" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
                      <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                          <h3 id="myModalLabel1">请填写信息</h3>
                      </div>
                      <div class="modal-body">
                          <form action="#" class="form-horizontal">
                              <div class="control-group"></div>
                              <div class="control-group"></div>
                              <div id="error-alert"></div>
                              <div class="control-group">
                                  <label class="control-label">请输入公司名称<span class="required">*</span></label>
                                  <div class="controls">
                                      <input type="text" id="companyCustomerName" name="companyCustomerName" class="m-wrap medium">
                                      <span id="companyNameMsg" class="help-line"></span>
                                  </div>
                              </div>
                              <div class="control-group">
                                  <label class="control-label">请输入账号<span class="required">*</span></label>
                                  <div class="controls">
                                      <input type="text" id="authId" name="authId" class="m-wrap medium">
                                      <span id="authIdMsg" class="help-line"></span>
                                      <span class="help-block">说明：只能有数字、字母、下划线组成</span>
                                  </div>
                              </div>
                              <div class="control-group">
                                  <label class="control-label">请选择合作公司<span class="required">*</span></label>
                                  <div class="controls">
                                      <select id="partnerId" name="partnerId" class="medium m-wrap" tabindex="1">
                                          <option value="">请选择...</option>
                                          <#if partnerList??>
                                              <#list partnerList as partner>
                                                  <option value="${partner.id}">${partner.name}</option>
                                              </#list>
                                          </#if>
                                      </select>
                                  </div>
                              </div>
                          </form>
                      </div>
                      <div class="modal-footer">
                          <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                          <button class="btn black btn-primary" id="add-btn-black-btn-primary" type="button">提交</button>
                      </div>
                  </div>
-->
                <#--添加账号-->
                    <div id="form_modal_add_account" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_add_account" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h3 id="myModalLabel_add_account">请填写信息</h3>
                        </div>
                        <div class="modal-body">
                            <form action="#" class="form-horizontal">
                                <div class="control-group"></div>
                                <div class="control-group"></div>
                                <div id="error-alert-account"></div>
                                <div id="authId-account-controls" class="controls" style="display: none;"></div>
                                <div class="control-group">
                                    <label class="control-label">请输入账号<span class="required">*</span></label>
                                    <div class="controls">
                                        <input type="text" id="authId-account" name="authId-account" class="m-wrap medium">
                                        <span id="authId-accountMsg" class="help-line"></span>
                                        <span class="help-block">说明：只能有数字、字母、下划线组成</span>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                            <button class="btn black btn-primary" id="add-account-btn-black-btn-primary" type="button">提交</button>
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
                                <div id="update_balance_customerId" class="controls" style="display: none;"></div>
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
                                <div class="btn-group" style="margin-bottom: 10px;">
                                    <a id="simple_company_api_1_new" class="btn black">
                                        添加产品权限<i class="icon-plus"></i>
                                    </a>
                                </div>
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
                                        <th style="width: 20%">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                <#--添加Ip-->
                    <div id="form_modal_add_ip" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_add_ip" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h3 id="myModalLabel_add_ip">添加Ip</h3>
                        </div>
                        <div class="modal-body">
                            <div class="control-group text-right">
                                <a href="#" id="control-group-add-ip-href"><span><i class="icon-plus"></i>点击添加一栏</span></a>
                            </div>
                            <div class="control-group"></div>
                            <div class="control-group"></div>
                            <div id="error_alert_add_ip"></div>
                            <div id="add_ip_customerId" class="controls" style="display: none;"></div>
                            <div class="control-group text-center" id="control-group-add-ip">

                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                            <button class="btn black btn-primary" id="add-ip-btn-black-btn-primary" type="button">提交</button>
                        </div>
                    </div>

                <#--批量添加产品-->
                    <div id="form_modal_add_api" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabell_add_api" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h3 id="myModalLabell_add_api">分配产品权限</h3>
                        </div>
                        <div class="modal-body">


                        </div>
                        <div class="modal-footer">
                            <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                            <button class="btn black btn-primary" id="add-ip-btn-black-btn-primary" type="button">提交</button>
                        </div>
                    </div>

                <#--Ip列表-->
                    <div id="form_modal_customer_ip_list" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_customer_ip_list" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h3 id="myModalLabel_customer_ip_list">客户Ip列表</h3>
                        </div>

                        <div class="modal-body">
                            <div class="clearfix">
                                <div class="btn-group" style="margin-bottom: 10px;">
                                    <a id="simple_customer_ip_1_new" class="btn black">
                                        添加Ip<i class="icon-plus"></i>
                                    </a>
                                </div>
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
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>

                <#--表单向导-->
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

                                                            对不起，请检查你的输入！

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

                                                            <div class="control-group">

                                                                <label class="control-label">Fullname<span class="required">*</span></label>

                                                                <div class="controls">

                                                                    <input type="text" class="span6 m-wrap" name="fullname"/>

                                                                    <span class="help-inline">Provide your fullname</span>

                                                                </div>

                                                            </div>

                                                            <div class="control-group">

                                                                <label class="control-label">Fullname<span class="required">*</span></label>

                                                                <div class="controls">

                                                                    <input type="text" class="span6 m-wrap" name="fullname"/>

                                                                    <span class="help-inline">Provide your fullname</span>

                                                                </div>

                                                            </div>

                                                            <div class="control-group">

                                                                <label class="control-label">Fullname<span class="required">*</span></label>

                                                                <div class="controls">

                                                                    <input type="text" class="span6 m-wrap" name="fullname"/>

                                                                    <span class="help-inline">Provide your fullname</span>

                                                                </div>

                                                            </div>

                                                        </div>

                                                        <div class="tab-pane" id="tab3">

                                                            <h3 class="block">添加正式账号Ip</h3>

                                                            <div class="control-group">

                                                                <label class="control-label">Card Holder Name<span class="required">*</span></label>

                                                                <div class="controls">

                                                                    <input type="text" class="span6 m-wrap" name="card_name" />

                                                                    <span class="help-inline"></span>

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

                                                                <label class="control-label">部门:</label>

                                                                <div class="controls">

                                                                    <span class="text display-value" data-display="partnerId"></span>

                                                                </div>

                                                            </div>

                                                            <h4 class="form-section">产品权限信息</h4>

                                                            <div class="control-group">

                                                                <label class="control-label">Fullname:</label>

                                                                <div class="controls">

                                                                    <span class="text display-value" data-display="fullname"></span>

                                                                </div>

                                                            </div>

                                                            <div class="control-group">

                                                                <label class="control-label">Gender:</label>

                                                                <div class="controls">

                                                                    <span class="text display-value" data-display="gender"></span>

                                                                </div>

                                                            </div>

                                                            <h4 class="form-section">Ip信息</h4>

                                                            <div class="control-group">

                                                                <label class="control-label">Card Holder Name:</label>

                                                                <div class="controls">

                                                                    <span class="text display-value" data-display="card_name"></span>

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

            FormWizard.init();

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

    <script src="/js/myjs/customer-company.js"></script>

    <script src="/js/myjs/customer-company-forbid.js"></script>

    <script src="/js/oldlocal/company-api-operator.js"></script>

    <script src="/js/oldlocal/company-ip-operator.js"></script>

    <script src="/js/oldlocal/company-charge-fee.js"></script>

    <script src="/js/oldlocal/company.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            Company.init();
            CompanyForbid.init();
        });

        /*以下操作是新增公司-获得焦点清空文本框*/
        $("#companyCustomerName").focus(function () {
            $("#companyNameMsg").html("");
        });

        /*以下操作是新增公司-获得焦点清空文本框*/
        $("#authId").focus(function () {
            $("#authIdMsg").html("");
        });

        /*以下操作是新增公司-获得焦点清空文本框*/
        $("#deptId").focus(function () {
            $("#deptIdMsg").html("");
        });

        /*以下操作是新增公司-失去焦点验证authId*/
        /*$("#authId").blur(function(){
            $("#authIdMsg").load("/customer/findCustomerByAuthId/"+$("#authId").val(),
                    function(responseTxt){
                        if(responseTxt=="yes")
                            $("#authIdMsg").html("<font color='red'>该账号已被使用，请重新输入！</font>");
                        if(responseTxt=="no")
                            $("#authIdMsg").html("");
                    });
        });*/

        /*以下操作是新增公司-点击提交按钮提交数据*/
        $("#add-btn-black-btn-primary").on("click",function () {
            var companyCustomerName=$("#companyCustomerName").val();
            var authId=$("#authId").val();
            var partnerId=$("#partnerId").val();
            var deptId=$("#deptId").val();
            $.ajax({
                type: "post",
                url: "/company/add-company-customer",
                data: {"companyName":companyCustomerName,"authId":authId,"partnerId":partnerId,"deptId":deptId},
                dataType: "json",
                success: function (result) {
                    if(result.companyNameMessage != null){
                        $("#companyNameMsg").empty();
                        $("#companyNameMsg").html('<font color="red">'+result.companyNameMessage+'</font>');
                        return;
                    }
                    if(result.authIdMessage != null){
                        $("#authIdMsg").empty();
                        $("#authIdMsg").html('<font color="red">'+result.authIdMessage+'</font>');
                        return;
                    }
                    if(result.deptMessage != null){
                        $("#deptIdMsg").empty();
                        $("#deptIdMsg").html('<font color="red">'+result.deptMessage+'</font>');
                        return;
                    }
                    if(result.errorMessage != null) {
                        $("#error-alert").empty();
                        $("#error-alert").append('<div class="alert alert-error show"><button class="close" data-dismiss="alert"></button><span>'+result.errorMessage+'</span></div>')
                        return;
                    }
                    if (result.successMessage != null){
                        alert("操作成功");
                        window.location.href=window.location.href
                    }
                }
            });
        });





        /*给正式账号添加Ip-绑定账号Id*/
        function addIp(customerId) {
            $("#add_ip_customerId").empty();
            $("#error_alert_add_ip").empty();
            var op=document.createElement("input");
            op.value=customerId;
            op.type="text";
            op.id="customerId";
            op.name="customerId";
            $("#add_ip_customerId").append(op);
            $('#control-group-add-ip').empty();
            $('#control-group-add-ip').append(
                    "<div>" +
                    "请输入正式账号Ip段&nbsp;&nbsp;" +
                    "<input class='m-wrap small' id='input_ipv4_begin' name='input_ipv4_begin' type='text'/>" +
                    "-- " +
                    "<input class='m-wrap small' id='input_ipv4_end' name='input_ipv4_end' type='text'/>"+
                    "</div>"
            );
        }

        /*正式账号添加Ip-点击添加一栏*/
        $('#control-group-add-ip-href').on('click',function () {
            $('#control-group-add-ip').append(
                    "<div>" +
                    "请输入正式账号Ip段&nbsp;&nbsp;" +
                    "<input class='m-wrap small' id='input_ipv4_begin' name='input_ipv4_begin' type='text'/>" +
                    "-- " +
                    "<input class='m-wrap small' id='input_ipv4_end' name='input_ipv4_end' type='text'/>"+
                    "</div>"
            );
        });

        /*提交Ip*/
        $('#add-ip-btn-black-btn-primary').on('click',function () {
            var customerId=$("#customerId").val();
            var beginIp = [];
            $("input[name='input_ipv4_begin']").each(function(){
                beginIp.push($.trim($(this).val()));
            });
            var endIp = [];
            $("input[name='input_ipv4_end']").each(function(){
                endIp.push($.trim($(this).val()));
            });
            console.log(customerId);
            console.log(beginIp);
            console.log(endIp);

            $.ajax({
                type: "post",
                url: "/company/customer/add/ip",
                data: {"customerId" : customerId, "beginIp" : beginIp, "endIp" : endIp},
                dataType: "json",
                success: function (data) {
                    if (data != null){
                        if (data.success != null){
                            window.location.href=window.location.href;
                            alert("添加成功！");
                            return;
                        }
                        if (data.fail != null){
                            $("#error_alert_add_ip").empty();
                            $("#error_alert_add_ip").append(
                                    '<div class="alert alert-error show">' +
                                    '<button class="close" data-dismiss="alert"></button>' +
                                    '<span>'+data.fail+'</span>' +
                                    '</div>');
                            return;
                        }
                    }
                }
            });
        });

    </script>

    </#if>

</@layout>