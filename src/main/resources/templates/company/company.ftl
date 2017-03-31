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
                                                <a class="btn red" style="margin-right: 10px;" href="#">
                                                    批量禁用客户
                                                </a>

                                                <a class="btn blue" style="margin-right: 10px;" href="#form_modal_add_api" data-toggle="modal">
                                                    批量分配产品权限
                                                </a>
                                            </div>
                                        <#-- <p>
                                             <button type="button" class="btn"><font><font>默认</font></font></button>
                                             <button type="button" class="btn red"><font><font>主</font></font></button>
                                             <button type="button" class="btn blue"><font><font>信息</font></font></button>
                                             <button type="button" class="btn green"><font><font>成功</font></font></button>
                                         </p>-->
                                        </div>

                                        <div class="table-responsive">
                                            <table class="table table-striped table-hover table-bordered table-condensed" id="companySample_1">
                                                <thead>
                                                <tr>
                                                    <th><input type="checkbox"/></th>
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
                                                                <td><input type="checkbox"/></td>
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
                                                                                    <a href="javaScript:;" id="customerIpBar_${customer.id}" onclick="showIp(${customer.id})">点击查看Ip段</a>
                                                                                    <div id="customerIpContent_${customer.id}" class="head-search-display" style="min-width: 150px; background-color: white">

                                                                                    <#list customer.customerIpList as customerIp>
                                                                                         <span>${customerIp.beginIpRaw!''}-${customerIp.endIpRaw!''}</span>
                                                                                         <span style="margin-left: 10px;"><a href="#" class="warning">删除</a></span>
                                                                                         <br/>
                                                                                     </#list>
                                                                                    </div>
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
                                                                                <a href="#form_modal_add_ip" id="add_ip" onclick="consumeBalance(${customer.id})" data-toggle="modal">添加Ip</a>
                                                                                |
                                                                                <#if (customer.customerStatus.id)?? && customer.customerStatus.id ==0>
                                                                                    <a href="#" class="warning">禁用</a><br/>
                                                                                <#else >
                                                                                    <a href="#">启用</a><br/>
                                                                                </#if>
                                                                            <#else >
                                                                                <a href="#form_modal_update_balance" id="charge_Balance" onclick="chargeBalance(${customer.id})" data-toggle="modal">充值</a>
                                                                                |
                                                                               <#-- <a href="#form_modal_update_balance" id="consume_Balance" onclick="consumeBalance(${customer.id})" data-toggle="modal">扣费</a>
                                                                                |-->
                                                                                <#if (customer.customerStatus.id)?? && customer.customerStatus.id ==0>
                                                                                    <a href="#" class="warning">禁用</a><br/>
                                                                                <#else >
                                                                                    <a href="#">启用</a><br/>
                                                                                </#if>
                                                                            </#if>
                                                                        </#list>
                                                                    </#if>
                                                                </td>
                                                                <td>
                                                                    <a href="#form_modal_add_api" onclick="addAccount(${company.companyId})" data-toggle="modal">分配产品权限</a>|
                                                                    <a href="#form_modal_company_api_status" data-toggle="modal">管理产品状态</a>
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

                <#--管理产品状态-->
                    <div id="form_modal_company_api_status" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_company_api_status" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h3 id="myModalLabel_company_api_status">管理产品状态</h3>
                        </div>
                        <div class="modal-body">
                            <div class="control-group text-center">
                                <div class="controls">
                                    <select multiple="multiple" id="my_multi_select">

                                        <option value="1,1">张建宏-1</option>

                                        <option value="2,2">张建宏-2</option>

                                        <option value="3,3">张建宏-3</option>

                                        <option value="4">张建宏-4</option>

                                        <option value="5">张建宏-5</option>

                                        <option value="6">张建宏-6</option>

                                        <option value="7">张建宏-7</option>

                                        <option value="8">张建宏-8</option>

                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                            <button class="btn black btn-primary" id="company-api-status-btn-black-btn-primary" type="button">提交</button>
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
                            <div class="control-group text-center" id="control-group-add-ip">
                                <div>
                                    请输入正式账号Ip段&nbsp;&nbsp;
                                    <input class="m-wrap small" name="input_ipv4_begin" type="text"/>
                                    --
                                    <input class="m-wrap small" name="input_ipv4_end" type="text"/>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                            <button class="btn black btn-primary" id="add-ip-btn-black-btn-primary" type="button">提交</button>
                        </div>
                    </div>

                <#--添加Api-->
                    <div id="form_modal_add_api" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabell_add_api" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h3 id="myModalLabell_add_api">分配产品权限</h3>
                        </div>
                        <div class="modal-body">
                            <div class="control-group text-right">
                                <a href="#" id="control-group-add-api-href"><span><i class="icon-plus"></i>点击添加一栏</span></a>
                            </div>
                            <div id="add-api">
                                <div style="float: left">
                                    <div class="control-group">
                                        <label class="control-label">产品类型</label>
                                        <div class="controls">
                                        <#--<select data-placeholder="请选择" class="chosen" multiple="multiple" tabindex="6">
                                            <option value=""></option>
                                            <option>三要素--联通</option>
                                            <option>三要素--移动</option>
                                            <option>三要素--电信</option>
                                            <option>二要素--联通</option>
                                            <option>二要素--移动</option>
                                            <option>二要素--电信</option>
                                            <option>银行卡三要素</option>
                                        </select>-->

                                            <select class="multiselect" id="multiselect" multiple="multiple">
                                                <option value="cheese">Cheese</option>
                                                <option value="tomatoes">Tomatoes</option>
                                                <option value="mozarella">Mozzarella</option>
                                                <option value="mushrooms">Mushrooms</option>
                                                <option value="pepperoni">Pepperoni</option>
                                                <option value="onions">Onions</option>
                                            </select>

                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">产品价格</label>
                                        <div class="controls">
                                            <input type="text" id="price" name="price" class="m-wrap medium">
                                            <span id="priceMsg" class="help-inline"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                            <button class="btn black btn-primary" id="add-ip-btn-black-btn-primary" type="button">提交</button>
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

    <script type="text/javascript">
        jQuery(document).ready(function() {
            Company.init();
            CompanyForbid.init();

            /*管理产品状态*/
            $('#my_multi_select').multiSelect({
                selectableHeader: "<div>正在使用的产品</div>",
                selectionHeader: "<div>被禁用的产品</div>"
                //selectableOptgroup: true
                /*selectableFooter: "<input type='text' class='search-input' autocomplete='off' placeholder='输入产品名称'>",*/
                /*selectionFooter: "<input type='text' class='search-input' autocomplete='off' placeholder='输入产品名称'>",*/

            });

            $('#company-api-status-btn-black-btn-primary').on('click',function () {
                var value = $('#my_multi_select').val();
                console.log(value);

            });

            /*添加Ip*/
            $('#control-group-add-ip-href').on('click',function () {
                $('#control-group-add-ip').append(
                        "<div>" +
                        "请输入正式账号Ip段&nbsp;&nbsp;" +
                        "<input class='m-wrap small' name='input_ipv4_begin' type='text'/>" +
                        "-- " +
                        "<input class='m-wrap small' name='input_ipv4_end' type='text'/>"+
                        "</div>"
                );
            });

            $('#control-group-add-ip').live("focus",function () {
                $('#control-group-add-ip input').ipAddress();
            });

            $('#add-ip-btn-black-btn-primary').on('click',function () {
                var begin = [];
                $("input[name='input_ipv4_begin']").each(function(){
                    begin.push($.trim($(this).val()));
                });
                var end = [];
                $("input[name='input_ipv4_end']").each(function(){
                    end.push($.trim($(this).val()));
                });
                console.log(begin);
                console.log(end);
            });

            /* $('#control-group-add-api-href').on('click',function () {

                 $('#add-api').append(
                         "<div style='float: left'>" +
                         "<div class='control-group'>" +
                         "<label class='control-label'>产品类型</label>" +
                         "<div class='controls'>" +
                         "<select data-placeholder='请选择' class='chosen' multiple='multiple'>" +
                         "</select>" +
                         "</div>" +
                         "</div>" +
                         "<div class='control-group'>" +
                         "<label class='control-label'>产品价格</label>" +
                         "<div class='controls'>" +
                         "<input type='text' class='m-wrap medium'>" +
                         "</div>" +
                         "</div>" +
                         "</div>"
                 );
             });*/

            $('#multiselect').multiselect();

        });

    </script>


    </#if>

</@layout>