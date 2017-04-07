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
                                                <a class="btn black" style="margin-right: 10px;" id="add-partner" href="#form_modal1" data-toggle="modal">
                                                    新增客户
                                                </a>
                                                <a class="btn red" id="batchBanCompany" style="margin-right: 10px;" href="javaScript:;">
                                                    批量禁用客户
                                                </a>

                                                <a class="btn blue" style="margin-right: 10px;" href="#form_modal_add_api" data-toggle="modal">
                                                    批量分配产品权限
                                                </a>
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
                                                                    <td data-title="合作公司"><a href="/company/find-all-company-customer<#if company.partnerId??>?partnerId=${company.partnerId}</#if>">${company.partnerName!'无'}</a></td>
                                                                </@shiro.hasPermission>
                                                                <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                                                    <td data-title="合作公司" style="display: none"><a href="/company/find-all-company-customer-by-dept-id<#if company.partnerId??>?partnerId=${company.partnerId}</#if>">${company.partnerName!'无'}</a></td>
                                                                </@shiro.hasPermission>
                                                                <td data-title="余额"><#if company.companyBalance??>${(company.companyBalance/100.0)?c}<#else >0</#if></td>
                                                                <td data-title="创建时间">${company.companyCreateTime!''}</td>
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
                                                                            <span id="table_vis_password_${customer.id}"></span>
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
                                                                                <#if customer.customerIpList?? && (customer.customerIpList?size>0)>
                                                                                    <a href="#form_modal_customer_ip_list"  onclick="showIp(${customer.id})" data-toggle="modal">点击查看Ip段</a>
                                                                                <#-- <div id="customerIpContent_${customer.id}" class="head-search-display" style="min-width: 150px; background-color: white">
                                                                                     <#list customer.customerIpList as customerIp>
                                                                                         <span>${customerIp.beginIpRaw!''}-${customerIp.endIpRaw!''}</span>
                                                                                         <span style="margin-left: 10px;"><a href="#" class="warning">删除</a></span>
                                                                                         <br/>
                                                                                     </#list>
                                                                                 </div>-->
                                                                                <#else >
                                                                                    <span class="warning">暂无Ip</span>
                                                                                </#if>
                                                                            </#if>
                                                                            <br/>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td class="table-td-none">
                                                                    <#if company.customerList??>
                                                                        <#list company.customerList as customer>
                                                                            <#if customer.customerType.id == 1>
                                                                                <a href="#form_modal_update_balance" id="charge_Balance" onclick="chargeBalance(${customer.id})" data-toggle="modal">充值</a>
                                                                                |
                                                                            <#--  <a href="#form_modal_update_balance" id="consume_Balance" onclick="consumeBalance(${customer.id})" data-toggle="modal">扣费</a>
                                                                              |-->
                                                                                <a href="#form_modal_add_ip" id="add_ip" onclick="addIp(${customer.id})" data-toggle="modal">添加Ip</a>
                                                                                |
                                                                                <#if (customer.customerStatus.id)?? && customer.customerStatus.id ==0>
                                                                                    <a href="javaScript:;" onclick="banCustomer(${customer.id})" class="warning">禁用</a><br/>
                                                                                <#else >
                                                                                    <a href="javaScript:;" onclick="unBanCustomer(${customer.id})">启用</a><br/>
                                                                                </#if>
                                                                            <#else >
                                                                                <a href="#form_modal_update_balance" id="charge_Balance" onclick="chargeBalance(${customer.id})" data-toggle="modal">充值</a>
                                                                                |
                                                                            <#-- <a href="#form_modal_update_balance" id="consume_Balance" onclick="consumeBalance(${customer.id})" data-toggle="modal">扣费</a>
                                                                             |-->
                                                                                <#if (customer.customerStatus.id)?? && customer.customerStatus.id ==0>
                                                                                    <a href="javaScript:;" onclick="banCustomer(${customer.id})" class="warning">禁用</a><br/>
                                                                                <#else >
                                                                                    <a href="javaScript:;" onclick="unBanCustomer(${customer.id})">启用</a><br/>
                                                                                </#if>
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
                                                                <td data-title="创建时间">${company.companyCreateTime!''}</td>
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
                                                                            <span id="table_vis_password_${customer.id}"></span>
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
                    <div id="form_modal1" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
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
                                <div class="btn-group">
                                    <a id="simple_company_api_1_new" class="btn black">
                                        添加产品权限<i class="icon-plus"></i>
                                    </a>
                                </div>
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
                                <div>
                                    请输入正式账号Ip段&nbsp;&nbsp;
                                    <input class="m-wrap small" id="input_ipv4_begin" name="input_ipv4_begin" type="text"/>
                                    --
                                    <input class="m-wrap small" id="input_ipv4_end" name="input_ipv4_end" type="text"/>
                                </div>
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

                </div>

            </div>

        </div>

    </div>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

    <script type="text/javascript" src="/js/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/DT_bootstrap.js"></script>

    <script src="/js/myjs/customer-company.js"></script>

    <script src="/js/myjs/customer-company-forbid.js"></script>

    <script src="/js/oldlocal/company.js"></script>

    <script src="/js/locales/bootstrap-multiselect.js"></script>

    <script src="/js/myjs/table-editable.js"></script>

    <script type="text/javascript">

        /*禁用账号*/
        function banCustomer(customerId) {
            $.ajax({
                type: "post",
                url: "/company/customer/ban",
                data: {"customerId": customerId},
                dataType: "json",
                success: function () {
                    alert("禁用成功");
                    window.location.href=window.location.href
                }
            })
        }

        /*解禁账号*/
        function unBanCustomer(customerId) {
            $.ajax({
                type: "post",
                url: "/company/customer/unban",
                data: {"customerId": customerId},
                dataType: "json",
                success: function () {
                    alert("启用成功");
                    window.location.href=window.location.href
                }
            })
        }

        var oTableEdit = null;

        /*产品权限管理*/
        function findCompanyApi(companyId) {

            $("#simple_company_api_1 tbody").empty();

            $('#simple_company_api_1').dataTable().fnClearTable();

            $('#simple_company_api_1').dataTable().fnDestroy();

            $.ajax({
                type: "post",
                url: "/company/find-company-api",
                data: {"companyId": companyId},
                dataType: "json",
                success: function (data) {

                    if (data != null){
                        for (var i = 0; i < data.length; i++) {
                            var myContent;
                            if (data[i].enabled == 0){
                                myContent = "<tr class='danger'>" +
                                        "<td style='width: 40%'>" + data[i].apiType.name + "</td>" +
                                        "<td style='width: 25%'>" + (data[i].price/100.0) + "</td>" +
                                        "<td style='width: 15%'>被禁用</td>" +
                                        "<td style='width: 20%'><a href='javaScript:;' onclick='unBanCompanyApi("+ data[i].id +','+ companyId +")'>启用</a></td>" +
                                        "</tr>"
                            }else {
                                myContent = "<tr>" +
                                        "<td style='width: 40%'>" + data[i].apiType.name + "</td>" +
                                        "<td style='width: 25%'>" + (data[i].price/100.0) + "</td>" +
                                        "<td style='width: 15%'>正在使用</td>" +
                                        "<td style='width: 20%'><a class='warning' href='javaScript:;' onclick='banCompanyApi("+ data[i].id +','+ companyId +")'>禁用</a></td>" +
                                        "</tr>"
                            }
                            $("#simple_company_api_1 tbody").append(myContent);
                        }
                    }

                    oTableEdit = $('#simple_company_api_1').dataTable({
                        "aoColumns": [
                            { "bSortable": false},
                            { "bSortable": false},
                            { "bSortable": false},
                            { "bSortable": false}
                        ],
                        "bFilter" : false,
                        "bPaginate" : false,
                        "bLengthChange" : false,
                        "sDom": "rt<'row-fluid'<'span6'il><'span6'p>>",
                        "sPaginationType": "bootstrap",
                        "oLanguage" : {  //设置语言
                            "sLengthMenu" : "每页显示 _MENU_ 条记录",
                            "sZeroRecords" : "对不起，没有匹配的数据",
                            "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
                            "sInfoEmpty" : "没有匹配的数据",
                            "sInfoFiltered" : "(数据表中共 _MAX_ 条记录)",
                            "sProcessing" : "正在加载中...",
                            "sSearch" : "全文搜索：",
                            "oPaginate" : {
                                "sFirst" : "第一页",
                                "sPrevious" : " 上一页 ",
                                "sNext" : " 下一页 ",
                                "sLast" : " 最后一页 "
                            }
                        }
                    });

                    var width =  $('#form_modal_company_api_status').width();

                    $('#simple_company_api_1').width(width-35);

                }
            })
        }

        var nEditing = null;

        /*新增*/
        $('#simple_company_api_1_new').live('click',function (e) {
            e.preventDefault();
            var aiNew = oTableEdit.fnAddData([
                '<select class="m-wrap small" id="apiTypeId_subTypeId" name="apiTypeId_subTypeId">' +
                '<option value="1">三要素</option>' +
                '<option value="2">二要素</option>' +
                '</select>',
                '<input type="text" class="m-wrap small" id="apiType_price" name="apiType_price">',
                '<select class="m-wrap small" id="apiType_enabled" name="apiType_enabled">' +
                '<option value="3">禁用</option>' +
                '<option value="4">启用</option>' +
                '</select>',
                '<a class="edit" href="">保存</a>|<a class="cancel" href="">取消</a>'
            ]);
            var nRow = oTableEdit.fnGetNodes(aiNew[0]);
            nEditing = nRow;
        });

        /*取消*/
        $('#simple_company_api_1 a.cancel').live('click', function (e) {
            e.preventDefault();
            oTableEdit.fnDeleteRow(nEditing);
        });

        /*保存*/
        $('#simple_company_api_1 a.edit').live('click', function (e) {
            e.preventDefault();
            var apiTypeId_subTypeId = $('#apiTypeId_subTypeId').val();
            alert(apiTypeId_subTypeId);
            var apiType_price = $('#apiType_price').val();
            alert(apiType_price);
            var apiType_enabled = $('#apiType_enabled').val();
            alert(apiType_enabled);

        });

        /*禁用产品权限*/
        function banCompanyApi(id,companyId) {
            $.ajax({
                type: "post",
                url: "/company/ban-api",
                data: {"id": id},
                dataType: "json",
                success: function () {
                    findCompanyApi(companyId)
                }
            })
        }

        /*启用产品权限*/
        function unBanCompanyApi(id,companyId) {
            $.ajax({
                type: "post",
                url: "/company/unban-api",
                data: {"id": id},
                dataType: "json",
                success: function () {
                    findCompanyApi(companyId)
                }
            })
        }

        /*显示Ip*/
        function showIp(customerId) {
            $("#simple_customer_ip_1 tbody").empty();
            $.ajax({
                type: "post",
                url: "/company/customer/find-ip",
                data: {"customerId": customerId},
                dataType: "json",
                success: function (data) {
                    if (data != null){
                        for (var i = 0; i < data.length; i++) {
                            var myContent = "<tr>" +
                                    "<td>" + data[i].beginIpRaw + "</td>" +
                                    "<td>" + data[i].endIpRaw + "</td>" +
                                    "<td><a href='javaScript:;' class='warning' onclick='deleteIp("+ data[i].id +','+ customerId +")'>删除</a></td>" +
                                    "</tr>"
                            $("#simple_customer_ip_1 tbody").append(myContent);
                        }
                    }
                }
            })
        }

        /*删除Ip*/
        function deleteIp(id,customerId) {
            $.ajax({
                type: "post",
                url: "/company/customer/delete-ip",
                data: {"id": id},
                dataType: "json",
                success: function () {
                    showIp(customerId)
                }
            })
        }

        /*给正式账号添加Ip*/
        function addIp(customerId) {
            $("#add_ip_customerId").empty();
            $("#error_alert_add_ip").empty();
            var op=document.createElement("input");
            op.value=customerId;
            op.type="text";
            op.id="customerId";
            op.name="customerId";
            $("#add_ip_customerId").append(op);
        }

        jQuery(document).ready(function() {
            Company.init();
            CompanyForbid.init();
            TableEditable.init();




            /*正式账号添加Ip*/
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

//            $('#control-group-add-ip').live("focus",function () {
//                $('#control-group-add-ip input').ipAddress();
//            });

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
                console.log(beginIp);
                console.log(endIp);
                //var beginIp = $("#input_ipv4_begin").val();
                //var endIp = $("#input_ipv4_end").val();
                $.ajax({
                    type: "post",
                    url: "/company/add/ip",
                    data: {"customerId" : customerId, "beginIp" : beginIp, "endIp" : endIp},
                    dataType: "json",
                    success: function () {

                    }
                });
            });


            $('#control-group-add-api-href').on('click',function () {

                $('#add-api').append(
                        "<div style='float: left'>" +
                        "<div class='control-group'>" +
                        "<label class='control-label'>产品类型</label>" +
                        "<div class='controls'>" +
                        "<select id='customer_add_api' name='customer_add_api'>" +
                        "<option value='cheese'>Cheese</option>" +
                        "<option value='tomatoes'>Tomatoes</option>" +
                        "<option value='mozarella'>Mozzarella</option>" +
                        "<option value='mushrooms'>Mushrooms</option>" +
                        "</select>" +
                        "</div>" +
                        "</div>" +
                        "<div class='control-group'>" +
                        "<label class='control-label'>产品价格</label>" +
                        "<div class='controls'>" +
                        "<input type='text' id='customer_add_api_amount' name='customer_add_api_amount' class='m-wrap medium' placeholder='单位：元'>" +
                        "</div>" +
                        "</div>" +
                        "</div>"
                );
            });

        });

    </script>


    </#if>

</@layout>