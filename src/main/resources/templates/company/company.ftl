
<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">

                <#--搜索框-->
                    <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">

                        <form action="/company/find-all-company-customer-by-dept-id" method="get">

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

                <#--表单-->

                    <div class="portlet box grey">

                     <#--   <div class="portlet-title">

                            <div class="caption"><i class="icon-user"></i></div>

                        </div>-->

                        <div class="portlet-body no-more-tables">

                            <#--<div class="clearfix margin-bottom-5">

                                <div class="btn-group">

                                    <a class="btn black" id="add-partner" href="#form_modal1" data-toggle="modal">

                                        新增<i class="icon-plus"></i>

                                    </a>

                                </div>

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

                                                        <#if deptList??>

                                                            <#list partnerList as partner>

                                                                <option value="${partner.id}">${partner.name}</option>

                                                            </#list>

                                                        </#if>

                                                    </select>

                                                </div>

                                            </div>

                                            <div class="control-group">

                                                <label class="control-label">请选择部门<span class="required">*</span></label>

                                                <div class="controls">

                                                    <select id="deptId" name="deptId" class="medium m-wrap" tabindex="1">

                                                        <option value="">请选择...</option>

                                                        <#if deptList??>

                                                            <#list deptList as dept>

                                                                <option value="${dept.id}">${dept.deptName}</option>

                                                            </#list>

                                                        </#if>

                                                    </select>

                                                    <span id="deptIdMsg" class="help-inline"></span>

                                                </div>

                                            </div>

                                        </form>

                                    </div>

                                    <div class="modal-footer">

                                        <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                        <button class="btn black btn-primary" id="add-btn-black-btn-primary" type="button">提交</button>

                                    </div>

                                </div>

                            </div>-->

                            <table class="table table-striped table-hover table-bordered table-condensed" id="companySample_1">
                                <thead>
                                <tr>
                                    <th>公司名称</th>
                                    <@shiro.hasPermission name="customer:findAllCustomer">
                                        <th>合作公司</th>
                                    </@shiro.hasPermission>

                                    <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                        <th style="display: none">合作公司</th>
                                    </@shiro.hasPermission>

                                    <th>余额</th>
                                    <th>创建时间</th>
                                    <th>customerId</th>
                                    <th>typeId</th>
                                    <th>typeName</th>
                                    <th>authId</th>
                                    <th>authPass</th>
                                    <th>balance</th>
                                    <th>status</th>
                                    <th>statusName</th>
                                    <th>customerCreateTime</th>
                                    <#--<th>操作</th>-->
                                <#-- <th style="text-align: center; width: 10%;">操作</th>-->
                                </tr>
                                </thead>
                                <tbody>
                                    <#if companyList??>
                                        <#list companyList as company>
                                        <tr class="odd gradeX">
                                            <td>${company.companyName!''}</td>

                                            <@shiro.hasPermission name="customer:findAllCustomer">
                                                <td><a href="/company/find-all-company-customer<#if company.partnerId??>?partnerId=${company.partnerId}</#if>">${company.partnerName!''}</a></td>
                                            </@shiro.hasPermission>

                                            <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                                <td style="display: none"><a href="/company/find-all-company-customer-by-dept-id<#if company.partnerId??>?partnerId=${company.partnerId}</#if>">${company.partnerName!''}</a></td>
                                            </@shiro.hasPermission>

                                            <td><#if company.companyBalance??>${(company.companyBalance/100.0)?c}<#else >0</#if></td>
                                            <td>${company.companyCreateTime!''}</td>
                                            <td>
                                                <#if company.customerList??>
                                                    <#list company.customerList as customer>
                                                    ${customer.id!''}<br/>
                                                    </#list>
                                                </#if>
                                            </td>
                                            <td>
                                                <#if company.customerList??>
                                                    <#list company.customerList as customer>
                                                    ${(customer.customerType.id)!''}<br/>
                                                    </#list>
                                                </#if>
                                            </td>
                                            <td>
                                                <#if company.customerList??>
                                                    <#list company.customerList as customer>
                                                    ${(customer.customerType.name)!''}<br/>
                                                    </#list>
                                                </#if>
                                            </td>
                                            <td>
                                                <#if company.customerList??>
                                                    <#list company.customerList as customer>
                                                    ${customer.authId!''}<br/>
                                                    </#list>
                                                </#if>
                                            </td>
                                            <td>
                                                <#if company.customerList??>
                                                    <#list company.customerList as customer>
                                                    ${customer.authPass!''}<br/>
                                                    </#list>
                                                </#if>
                                            </td>
                                            <td>
                                                <#if company.customerList??>
                                                    <#list company.customerList as customer>
                                                        <#if customer.balance??>${(customer.balance/100.0)?c}</#if><br/>
                                                    </#list>
                                                </#if>
                                            </td>
                                            <td>
                                                <#if company.customerList??>
                                                    <#list company.customerList as customer>
                                                    ${(customer.customerStatus.id)!''}<br/>
                                                    </#list>
                                                </#if>
                                            </td>
                                            <td>
                                                <#if company.customerList??>
                                                    <#list company.customerList as customer>
                                                    ${(customer.customerStatus.name)!''}<br/>
                                                    </#list>
                                                </#if>
                                            </td>
                                            <td>
                                                <#if company.customerList??>
                                                    <#list company.customerList as customer>
                                                    ${customer.createTime!''}<br/>
                                                    </#list>
                                                </#if>
                                            </td>
                                            <#--<td>
                                                <#if company.customerList??>
                                                    <#list company.customerList as customer>
                                                        <a href="#form_modal_update_balance" id="charge_Balance" onclick="chargeBalance(${customer.id})" data-toggle="modal">充值</a>
                                                        |
                                                        <a href="#form_modal_update_balance" id="consume_Balance" onclick="consumeBalance(${customer.id})" data-toggle="modal">扣费</a><br/>
                                                    </#list>
                                                </#if>
                                            </td>-->
                                        <#--<td><a href="#form_modal_add_account" onclick="addAccount(${company.companyId})" data-toggle="modal">添加账号</a></td>-->
                                        </tr>
                                        </#list>
                                    </#if>

                                </tbody>

                            </table>

                           <#-- <div id="form_modal_add_account" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_add_account" aria-hidden="true">

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

                            </div>-->

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

    <script type="text/javascript">

        jQuery(document).ready(function() {
            Company.init();
        });

        //        $("#add-partner").click(function(){return false;});
        //
        //        $("#charge_Balance").click(function(){return false;});
        //
        //        $("#consume_Balance").click(function(){return false;});


        /*以下操作为添加账号*/
        function addAccount(companyId) {
            $("#authId-account-controls").empty();
            $("#error-alert-account").empty();
            var op=document.createElement("input");
            op.value=companyId;
            op.type="text";
            op.id="companyId";
            op.name="companyId";
            $("#authId-account-controls").append(op);
        }

        /*以下操作是给账号充值或扣费*/
        function chargeBalance(customerId) {
            $.ajax({
                type: "post",
                url: "/company/charge-customer-balance",
                dataType: "json",
                success: function (data) {
                    $("#error_alert_update_balance").empty();
                    $("#update_balance_customerId").empty();
                    $("#update_balance_amountMsg").empty();
                    var op=document.createElement("input");
                    op.value=customerId;
                    op.type="text";
                    op.id="customerId";
                    op.name="customerId";
                    $("#authId-account-controls").append(op);
                    if(data != null){
                        $("#update_balance_reasonId ").empty();
                        $("#update_balance_reasonId").append("<option value=''>请选择...</option>");
                        for (var i=0; i<data.length; i++){
                            var opSelect=document.createElement("option");
                            opSelect.value=data[i].id;
                            opSelect.innerHTML=data[i].name;
                            $("#update_balance_reasonId").append(opSelect);
                        }
                    }
                }
            });
        }

        function consumeBalance(customerId) {
            $.ajax({
                type: "post",
                url: "/company/consume-customer-balance",
                dataType: "json",
                success: function (data) {
                    $("#error_alert_update_balance").empty();
                    $("#update_balance_customerId").empty();
                    $("#update_balance_amountMsg").empty();
                    var op=document.createElement("input");
                    op.value=customerId;
                    op.type="text";
                    op.id="customerId";
                    op.name="customerId";
                    $("#authId-account-controls").append(op);
                    if(data != null){
                        $("#update_balance_reasonId ").empty();
                        $("#update_balance_reasonId").append("<option value=''>请选择...</option>");
                        for (var i=0; i<data.length; i++){
                            var opSelect=document.createElement("option");
                            opSelect.value=data[i].id;
                            opSelect.innerHTML=data[i].name;
                            $("#update_balance_reasonId").append(opSelect);
                        }
                    }
                }
            });
        }

    </script>

    </#if>


</@layout>
