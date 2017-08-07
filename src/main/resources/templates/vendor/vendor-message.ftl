
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

                    <form action="/vendor/all-vendor" class="form-bottom" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom">

                                <label>请选择供应商</label>

                                <div class="controls">

                                    <select class="medium m-wrap" multiple id="vid" name="vid">
                                        <option value=""></option>
                                        <#if vendorExtList??>
                                            <#list vendorExtList as vendor>
                                                <option <#if vid??><#list vid as vid><#if vid?? && vid == vendor.vendorId> selected="selected"</#if></#list></#if> value="${vendor.vendorId}">${vendor.vendorName}</option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label>请选择合作公司</label>

                                <div class="controls">
                                    <select class="medium m-wrap" multiple id="pid" name="pid">
                                        <option value=""></option>
                                        <#if partnerList??>
                                            <#list partnerList as partnerId>
                                                <option <#if pid??><#list pid as pid><#if pid?? && pid == partnerId.id> selected="selected"</#if></#list></#if> value="${partnerId.id}">${partnerId.name}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label>是否预付</label>

                                <div class="controls">
                                    <select class="medium m-wrap" id="preId" name="preId">
                                        <option value=""></option>
                                        <option <#if preId?? && preId == 0>selected="selected"</#if> value="0">否</option>
                                        <option <#if preId?? && preId == 1>selected="selected"</#if> value="1">是</option>
                                    </select>
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

                        <div class="portlet-body">

                            <div class="clearfix margin-bottom-5">

                                <div class="pull-right tip-remark">
                                    <span class="pull-right">注：本页面与金额相关数字单位都为：元</span>
                                </div>

                            </div>

                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_2">
                                <thead>
                                <tr>
                                    <th style="width: 5%"><input type="checkbox" id="allChecked" class="group-checkable" data-set="#sample_2 .checkboxes"/></th>
                                    <th>供应商</th>
                                    <th>合作公司</th>
                                    <th>是否预付</th>
                                    <th>充值金额</th>
                                    <th>消费金额</th>
                                    <th>余额</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if vendorExtList??>
                                        <#list vendorExtList as vendor>
                                        <tr>
                                            <td data-title="操作"><input class="checkboxes" type="checkbox" id="checkBox" name="checkBox" value="${vendor.vendorId}"/></td>
                                            <td>${vendor.vendorName!'无'}</td>
                                            <td>${vendor.partnerName!'无'}</td>
                                            <td href="javaScript:;" onclick=""><a>${vendor.isPrepayName!'否'}</a></td>
                                            <td><a href="/vendor/all-vendor/charge-record?vid=${vendor.vendorId}">${vendor.balance!'0'}</a></td>
                                            <td>${vendor.totleCost!'0'}</td>
                                            <td>${vendor.remaining!'0'}</td>
                                            <td><a href="#form_modal" data-toggle="modal" onclick="">充值</a></td>
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

                            <form action="#" class="form-horizontal">

                                <div class="control-group"></div>

                                <div class="control-group"></div>

                                <input style="display: none" id="charge_vid" name="charge_vid" value="">

                                <div class="control-group">

                                    <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                    <div class="controls">
                                        <input type="text" id="amount" name="amount"  placeholder="（单位/元）" class="m-wrap medium">
                                        <span class="help-block">说明：只能输入数字类型并且金额大于0</span>
                                    </div>

                                </div>

                                <div class="control-group">

                                    <label class="control-label">充值日期<span class="required">*</span></label>

                                    <div class="controls">

                                        <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                            <input id="date" name="date" class="m-wrap m-ctrl-medium date-picker" size="16" type="text"><span class="add-on"><i class="icon-calendar"></i></span>

                                        </div>

                                    </div>

                                </div>

                                <div class="control-group">

                                    <label class="control-label">备&nbsp;注<span class="required">*</span></label>

                                    <div class="controls">
                                        <textarea class="medium m-wrap" id="remark" name="remark" rows="3"></textarea>
                                        <span class="help-block">说明：只能输入255个字符</span>
                                    </div>

                                </div>

                            </form>

                        </div>

                        <div class="modal-footer">

                            <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                            <button class="btn black btn-primary" id="btn-black-btn-primary" type="button">提交</button>

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

    <script src="/js/multi/get-url-param.js?v=${ver}"></script>

    <script src="/js/myjs/vendor-message.js?v=${ver}"></script>

    <script>

        jQuery(document).ready(function () {
            vendorMessage.init();
        });

        $("#vid").select2({
            language: "zh-CN",
            placeholder: "请选择",
            allowClear: true
        });

        $("#pid").select2({
            language: "zh-CN",
            placeholder: "请选择",
            allowClear: true
        });

        $("#preId").select2({
            language: "zh-CN",
            placeholder: "请选择",
            allowClear: true
        });

        function charge(var vid) {
            $("#charge_vid").val(vid)
        }
        
        function isPrepay(var vid) {
            
        }

    </script>

    </#if>

</@layout>
