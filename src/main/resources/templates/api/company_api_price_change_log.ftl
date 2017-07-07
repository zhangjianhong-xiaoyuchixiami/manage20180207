
<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<#import "../publicPart/publicJs.ftl" as puj>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content" xmlns="http://www.w3.org/1999/html">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">

                <#--搜索框-->

                    <form action="/api/company/apiType-price-change-log" class="form-bottom" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom">

                                <label >公司名称</label>

                                <div class="controls">

                                    <select class="medium m-wrap" id="cid" name="cid">
                                        <option value=""></option>
                                        <#if companyList??>
                                            <#list companyList as company>
                                                <option <#if cid?? && cid==company.id>selected="selected"</#if> value="${company.id}">${company.name}<#if company.partner??>@${(company.partner.name)!''}</#if></option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label >合作公司</label>

                                <div class="controls">

                                    <select class="medium m-wrap" id="pid" name="pid">
                                        <option value=""></option>
                                        <#if partnerList??>
                                            <#list partnerList as partner>
                                                <option <#if pid?? && pid==partner.id>selected="selected"</#if> value="${partner.id}">${partner.name}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label >产品类型</label>

                                <div class="controls">

                                    <select class="medium m-wrap" id="tid" name="tid">
                                        <option value=""></option>
                                        <#if apiTypeList??>
                                            <#list apiTypeList as apiType>
                                                <option <#if tid?? && tid==apiType.id>selected="selected"</#if> value="${apiType.id}">${apiType.name}</option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label >价格区间</label>

                                <div class="controls">

                                    <input type="text" id="LPic" name="LPic" <#if LPic??> value="${LPic}" </#if> placeholder="最低价" class="m-wrap small">

                                    --

                                    <input type="text" id="HPic" name="HPic" <#if HPic??> value="${HPic}" </#if> placeholder="最高价" class="m-wrap small">

                                </div>

                            </div>

                            <div class="pull-left margin-right-20 head-search-bottom">

                                <label >起始日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                        <input <#if beginDate??>value="${beginDate}" </#if> id="beginDate" name="beginDate" class="m-wrap" size="16" type="text" style="width: 150px;">

                                        <span class="add-on"><i class="icon-calendar"></i></span>

                                    </div>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label >结束日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                        <input <#if endDate??>value="${endDate}" </#if> id="endDate" name="endDate" class="m-wrap " size="16" type="text" style="width: 150px;">

                                        <span class="add-on"><i class="icon-calendar"></i></span>

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

                    <div class="portlet box grey">

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-5">
                                <div class="btn-group pull-left">
                                    <button class="btn-icon black" id="batch_add" data-target="#form_modal" data-toggle="modal">
                                        <i class="icon-plus-sign"></i>添加
                                    </button>
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_1">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" id="allChecked" class="group-checkable" data-set="#sample_1 .checkboxes"/></th>
                                    <th>公司名称</th>
                                    <th>合作公司</th>
                                    <th>产品类型</th>
                                    <th>价格（单位：元）</th>
                                    <th>生效区间</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if capclList??>
                                        <#list capclList as capcl>
                                        <tr>
                                            <td data-title="选择"><input class="checkboxes" type="checkbox" id="checkBoxId" name="checkBoxId" value="${capcl.id}"/></td>
                                            <td data-title="公司名称">${(capcl.companyName)!'无'}</td>
                                            <td data-title="合作公司">${(capcl.partnerName)!'无'}</td>
                                            <td data-title="产品类型">${(capcl.apiType_stid)!'无'}</td>
                                            <td data-title="价格（单位：元）">${capcl.price/100.0}</td>
                                            <td data-title="生效区间">${(capcl.timeRange)!'无'}</td>
                                        </tr>
                                        </#list>
                                    </#if>
                                </tbody>
                            </table>

                        </div>

                    </div>

                    <div id="form_modal" class="modal hide fade myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h3 id="myModalLabel">请填写信息</h3>
                        </div>
                        <div class="modal-body">
                            <form action="#" class="form-horizontal" id="submit_form">

                                <div class="alert alert-error hide">

                                    <button class="close" data-dismiss="alert"></button>

                                    <span id="tip">对不起，请检查你的输入！</span>

                                </div>

                                <div class="alert alert-success hide">

                                    <button class="close" data-dismiss="alert"></button>

                                    恭喜您，操作成功!

                                </div>

                                <div id="" class="control-group">
                                    <label class="control-label">请选择公司<span class="required">*</span></label>
                                    <div class="controls">
                                        <select id="companyId" name="companyId" class="large m-wrap" tabindex="1">
                                            <option value=""></option>
                                            <#if companyList??>
                                                <#list companyList as company>
                                                    <option value="${company.id}">${company.name}<#if company.partner??>@${(company.partner.name)!''}</#if></option>
                                                </#list>
                                            </#if>
                                        </select>
                                        <span id="companyIdMsg" class="help-inline"></span>
                                    </div>
                                </div>

                                <div id="" class="control-group">
                                    <label class="control-label">请选择产品<span class="required">*</span></label>
                                    <div class="controls">
                                        <select id="tid_stid" name="tid_stid" class="large m-wrap" tabindex="1">
                                            <option value=""></option>
                                        </select>
                                        <span id="tid_stidMsg" class="help-inline"></span>
                                    </div>
                                </div>

                                <div id="" class="control-group">
                                    <label class="control-label">请输入金额<span class="required">*</span></label>
                                    <div class="controls">
                                        <input type="text" id="price" name="price" class="medium m-wrap" placeholder="（单位/元）">
                                        <span id="priceMsg" class="help-line"></span>
                                        <span class="help-block">说明：只能输入数字类型并且金额大于0</span>
                                    </div>
                                </div>

                                <div class="control-group">

                                    <label class="control-label">请选择生效时间</label>

                                    <div class="controls">

                                        <div class="input-append date form_datetime" data-date-viewmode="years" data-date-minviewmode="months">

                                            <input id="add_date" name="add_date" size="16" type="text" value="" readonly class="m-wrap">

                                            <span class="add-on"><i class="icon-remove"></i></span>

                                            <span class="add-on"><i class="icon-calendar"></i></span>

                                            <span id="add_dateMsg" class="help-line"></span>

                                        </div>

                                    </div>

                                </div>

                            </form>
                        </div>
                        <div class="modal-footer">
                            <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                            <button class="btn black btn-primary" id="add-btn-primary" type="button">提交</button>
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

    <script src="/js/former/bootstrap-datetimepicker.js?v=${ver}"></script>

    <script src="/js/locales/set-datatimepicker-zh-CN.js?v=${ver}"></script>

    <script src="/js/myjs/company-api-price-change-log.js?v=${ver}"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {

            CompanyApiPriceChangeLog.init();

        });

    </script>

    </#if>

</@layout>
