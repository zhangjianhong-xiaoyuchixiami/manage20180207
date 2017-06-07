
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

                                                <label class="control-label">部门:</label>

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

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

    </#if>

</@layout>
