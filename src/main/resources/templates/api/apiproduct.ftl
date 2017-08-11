
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

                    <form action="/api/api-message" class="form-bottom api_product_record" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom">

                                <label>产品类型</label>

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

                                <label>产品供应商</label>

                                <div class="controls">

                                    <select class="medium m-wrap" id="vid" name="vid">
                                        <option value=""></option>
                                        <#if apiVendorList??>
                                            <#list apiVendorList as apiVendor>
                                                <option <#if vid?? && vid==apiVendor.id>selected="selected"</#if> value="${apiVendor.id}">${apiVendor.name}<#if apiVendor.partner??>@${apiVendor.partner.name}</#if></option>
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

                                <label >价格区间</label>

                                <div class="controls">

                                    <input type="text" id="LPic" name="LPic" <#if LPic??> value="${LPic}" </#if> placeholder="最低价" class="m-wrap small">

                                    --

                                    <input type="text" id="HPic" name="HPic" <#if HPic??> value="${HPic}" </#if> placeholder="最高价" class="m-wrap small">

                                </div>

                            </div>

                        <#-- <div class="pull-left head-search-bottom">

                             <label >初始价格状态</label>

                             <div class="controls">

                                 <select class="medium m-wrap" id="statId" name="statId">
                                     <option value=""></option>
                                     <option <#if statId?? && statId== 1>selected="selected"</#if> value="1">已修改初始价格</option>
                                     <option <#if statId?? && statId== 2>selected="selected"</#if> value="2">未修改初始价格</option>
                                 </select>
                             </div>

                         </div>-->

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

                    <div class="tabbable tabbable-custom boxless">

                        <ul class="nav nav-tabs">

                            <li class="active"><a href="#tab_1" data-toggle="tab">正在使用的产品</a></li>

                            <li><a class="" href="#tab_2" data-toggle="tab">已停用的产品</a></li>

                        </ul>

                        <div class="tab-content">

                            <div class="tab-pane active" id="tab_1">

                            <#--正在使用的产品-->
                                <div class="portlet box grey">

                                    <div class="portlet-body no-more-tables">

                                        <div class="clearfix margin-bottom-5">
                                            <div class="btn-group pull-left">
                                                <button class="btn-icon red" id="batchBanApi">
                                                    <i class="icon-remove-sign"></i>禁用
                                                </button>
                                            </div>

                                            <div class="pull-right tip-remark">
                                                <span>注：用绿色标注的行是产品主通道，红色标注的行是未修改初始价格的产品</span>
                                            </div>
                                        </div>

                                        <table class="table table-striped table-bordered table-hover table-condensed" id="sample_product_1">
                                            <thead>
                                            <tr>
                                                <th><input type="checkbox" id="allChecked" class="group-checkable" data-set="#sample_product_1 .checkboxes"/></th>
                                                <th>产品类型</th>
                                                <th>合作公司</th>
                                                <th>产品供应商</th>
                                                <th>价格（单位：元）</th>
                                                <th>当前配额</th>
                                                <#--<th>预设配额</th>-->
                                                <#--<th>预设比例</th>-->
                                                <#--<th>恢复详情</th>-->
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <#if apiList??>
                                                    <#list apiList as api>
                                                        <#if api.status == 0>
                                                            <#if api.apiFake?? && api.apiFake.fakeV??>
                                                                <#if api.proxyApi.minCost==api.cost>
                                                                <tr class="success">
                                                                <#else >
                                                                <tr>
                                                                </#if>
                                                            <#else >
                                                            <tr class="danger">
                                                            </#if>
                                                            <td data-title="选择"><input class="checkboxes" type="checkbox" id="checkBoxApiIdBan" name="checkBoxApiIdBan" value="${api.id}"/></td>
                                                            <td data-title="产品类型">${api.apiType.name}
                                                                <#if (api.proxyApi.proxyApiTypeName)??>（调用：${api.proxyApi.proxyApiTypeName!''}）</#if>
                                                            </td>
                                                            <td data-title="合作公司">
                                                                <#if (api.apiVendor.partner.id)??>
                                                                    <a href="/api/api-message?pid=${(api.apiVendor.partner.id)!''}">${(api.apiVendor.partner.name)!'无'}</a>
                                                                <#else >
                                                                    无
                                                                </#if>
                                                            </td>
                                                            <td data-title="产品供应商">${api.apiVendor.name}<#if (api.apiVendor.partner)??>@${api.apiVendor.partner.name}</#if></td>
                                                            <td data-title="价格（单位：元）"><a href="javaScript:;" onclick="updateApiPrice(${api.id},${(api.cost)?c})" data-toggle="tooltip" data-placement="bottom" title="点击修改产品价格">${(api.cost/100.0)?c}</a></td>
                                                            <td data-title="当前配额"><a href="javaScript:;" onclick="updateApiCurrProb(${api.id},${(api.prob)?c})" data-toggle="tooltip" data-placement="bottom" title="点击修改当前配额">${api.prob!'0'}</a></td>
                                                            <#--<td data-title="预设配额"><a href="javaScript:;" onclick="updateApiDefProb(${api.id},${(api.defProb)?c})" data-toggle="tooltip" data-placement="bottom" title="点击修改预设配额">${api.defProb!'0'}</a></td>-->
                                                            <#--<td data-title="预设比例"><a href="javaScript:;" onclick="updateApiDefProp(${api.id},${(api.defProp)?c})" data-toggle="tooltip" data-placement="bottom" title="点击修改预设比例">${api.defProp!'0'}%</a></td>-->
                                                            <#--<td data-title="恢复详情"><a href="javaScript:;" onclick="lookDetailLog(${api.id})" >查看详情</a></td>-->
                                                        </tr>
                                                        </#if>
                                                    </#list>
                                                </#if>
                                            </tbody>
                                        </table>

                                    </div>

                                </div>

                            </div>

                            <div class="tab-pane " id="tab_2">

                            <#--已停用的产品-->
                                <div class="portlet box grey">

                                    <div class="portlet-body no-more-tables">

                                        <div class="clearfix margin-bottom-5">
                                            <div class="btn-group pull-left">
                                                <button class="btn-icon black" id="batchUnBanApi">
                                                    <i class="icon-ok-sign"></i>启用
                                                </button>
                                            </div>
                                            <#--<div class="btn-group pull-left">-->
                                                <#--<button class="btn-icon black" id="batchRecoverApi">-->
                                                    <#--<i class="icon-refresh"></i>恢复配额-->
                                                <#--</button>-->
                                            <#--</div>-->
                                            <div class="pull-right tip-remark">
                                                <span>注：红色标注的行是未修改初始价格的产品</span>
                                            </div>
                                        </div>

                                        <table class="table table-striped table-bordered table-hover table-condensed" id="sample_product_2">
                                            <thead>
                                            <tr>
                                                <th><input type="checkbox" id="allChecked" class="group-checkable" data-set="#sample_product_2 .checkboxes"/></th>
                                                <th>产品类型</th>
                                                <th>合作公司</th>
                                                <th>产品供应商</th>
                                                <th>价格（单位：元）</th>
                                                <th>当前配额</th>
                                                <#--<th>预设配额</th>-->
                                                <#--<th>预设比例</th>-->
                                                <#--<th>恢复详情</th>-->
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <#if apiList??>
                                                    <#list apiList as api>
                                                        <#if api.status != 0>
                                                            <#if api.apiFake?? && api.apiFake.fakeV??>
                                                            <tr>
                                                            <#else >
                                                            <tr class="danger">
                                                            </#if>
                                                            <td><input class="checkboxes" type="checkbox" id="checkBoxApiIdUnBan" name="checkBoxApiIdUnBan" value="${api.id}"/></td>
                                                            <td data-title="产品类型" class="font-text-decoration">${api.apiType.name}
                                                                <#if (api.proxyApi.proxyApiTypeName)??>（调用：${api.proxyApi.proxyApiTypeName!''}）</#if>
                                                            </td>
                                                            <td data-title="合作公司">
                                                                <#if (api.apiVendor.partner.id)??>
                                                                    <a href="/api/api-message?pid=${(api.apiVendor.partner.id)!''}">${(api.apiVendor.partner.name)!'无'}</a>
                                                                <#else >
                                                                    无
                                                                </#if>
                                                            </td>
                                                            <td data-title="产品供应商">${api.apiVendor.name}<#if (api.apiVendor.partner)??>@${api.apiVendor.partner.name}</#if></td>
                                                            <td data-title="价格（单位：元）"><a href="javaScript:;" onclick="updateApiPrice(${api.id},${(api.cost)?c})" data-toggle="tooltip" data-placement="bottom" title="点击修改产品价格">${(api.cost/100.0)?c}</a></td>
                                                            <td data-title="当前配额"><a href="javaScript:;" onclick="updateApiCurrProb(${api.id},${(api.prob)?c})" data-toggle="tooltip" data-placement="bottom" title="点击修改当前配额">${api.prob!'0'}</a></td>
                                                            <#--<td data-title="预设配额"><a href="javaScript:;" onclick="updateApiDefProb(${api.id},${(api.defProb)?c})" data-toggle="tooltip" data-placement="bottom" title="点击修改预设配额">${api.defProb!'0'}</a></td>-->
                                                            <#--<td data-title="预设比例"><a href="javaScript:;" onclick="updateApiDefProp(${api.id},${(api.defProp)?c})" data-toggle="tooltip" data-placement="bottom" title="点击修改预设比例">${api.defProp!'0'}%</a></td>-->
                                                            <#--<td data-title="恢复详情"><a href="javaScript:;" onclick="lookDetailLog(${api.id})" >查看详情</a></td>-->
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

            </div>

        </div>

    </div>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

        <@puj.publicJs></@puj.publicJs>

    <script src="/js/myjs/api-product.js?v=${ver}"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {

            ApiProduct.init();

        });

        $(function () {

            $("[data-toggle='tooltip']").tooltip();

        });

        function updateApiPrice(id,price) {

            var aid = id ;
            var pic = price/100.0 ;
            swal({
                title: '修改产品价格',
                input: 'text',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                cancelButtonText: "取消",
                confirmButtonText: "确定修改",
                allowOutsideClick: true,
                inputValue: pic,
                inputValidator: function(value) {
                    return new Promise(function(resolve, reject) {
                        var re =new RegExp("^(\\d+)(\\.\\d+)?$");
                        if(!re.test(value)){
                            reject('格式输入不正确！');
                        } else {
                            resolve();
                        }
                    });
                }
            }).then(function (value) {

                $.ajax({
                    type: "post",
                    url: "/api/update-price",
                    data: {"aid": aid, "pic": value},
                    dataType: "json",
                    beforeSend: function () {
                        openProgress();
                    },
                    success: function (data) {
                        closeProgress();
                        if (data != null) {
                            if (data.success != null) {
                                swal({
                                    type: 'success',
                                    title: '价格修改完成',
                                    confirmButtonText: "确定",
                                    html: '已将价格修改为：' + value
                                }).then(function () {
                                    window.location.href = window.location.href ;
                                    return;
                                });

                            }
                            if (data.fail != null) {

                                swal({
                                    type: 'error',
                                    title: '失败',
                                    text: data.fail,
                                    confirmButtonText: "确定"

                                })
                            }
                        }
                    }
                });

            },function(dismiss) {
                // dismiss的值可以是'cancel', 'overlay','close', 'timer'
                if (dismiss === 'cancel') {}
            });

        }

        function updateApiCurrProb(id,prob) {

            var aid = id ;
            var prob_w = prob ;
            swal({
                title: '修改产品当前配额',
                input: 'number',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                cancelButtonText: "取消",
                confirmButtonText: "确定修改",
                allowOutsideClick: true,
                inputValue: prob_w,
                inputValidator: function(value) {
                    return new Promise(function(resolve, reject) {
                        var re =new RegExp("^(\\d+)(\\.\\d+)?$");
                        if(!re.test(value)){
                            reject('格式输入不正确！');
                        } else {
                            resolve();
                        }
                    });
                }
            }).then(function (value) {

                $.ajax({
                    type: "post",
                    url: "/api/update-curr-prob",
                    data: {"aid": aid, "prob": value},
                    dataType: "json",
                    beforeSend: function () {
                        openProgress();
                    },
                    success: function (data) {
                        closeProgress();
                        if (data != null) {
                            if (data.success != null) {
                                swal({
                                    type: 'success',
                                    title: '当前配额修改完成',
                                    confirmButtonText: "确定",
                                    html: '已将当前配额修改为：' + value
                                }).then(function () {
                                    window.location.href = window.location.href ;
                                    return;
                                });

                            }
                            if (data.fail != null) {

                                swal({
                                    type: 'error',
                                    title: '失败',
                                    text: "哎呦，修改失败了",
                                    confirmButtonText: "确定"

                                });
                                return;
                            }
                            if (data.warning != null){
                                swal({
                                    title: "操作提示",
                                    text: data.warning,
                                    type: "warning",
                                    showCancelButton: false, //是否显示取消按钮
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: "确定"//确定按钮上面的文档
                                })
                            }
                            if (data.role_warning != null){
                                swal({
                                    title: "操作提示",
                                    text: data.role_warning,
                                    type: "warning",
                                    showCancelButton: false, //是否显示取消按钮
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: "确定"//确定按钮上面的文档
                                })
                            }
                        }
                    }
                });

            },function(dismiss) {
                // dismiss的值可以是'cancel', 'overlay','close', 'timer'
                if (dismiss === 'cancel') {}
            });

        }

        function updateApiDefProb(id,prob) {

            var aid = id ;
            var prob_w  = prob ;
            swal({
                title: '修改产品预设配额',
                input: 'number',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                cancelButtonText: "取消",
                confirmButtonText: "确定修改",
                allowOutsideClick: true,
                inputValue: prob_w,
                inputValidator: function(value) {
                    return new Promise(function(resolve, reject) {
                        var re =new RegExp("^(-?\\d+)(\\.\\d+)?$");
                        if(!re.test(value)){
                            reject('格式输入不正确！');
                        } else {
                            resolve();
                        }
                    });
                }
            }).then(function (value) {

                $.ajax({
                    type: "post",
                    url: "/api/update-def-prob",
                    data: {"aid": aid, "prob": value},
                    dataType: "json",
                    beforeSend: function () {
                        openProgress();
                    },
                    success: function (data) {
                        closeProgress();
                        if (data != null) {
                            if (data.success != null) {
                                swal({
                                    type: 'success',
                                    title: '预设配额修改完成',
                                    confirmButtonText: "确定",
                                    html: '已将预设配额修改为：' + value
                                }).then(function () {
                                    window.location.href = window.location.href ;
                                    return;
                                });

                            }
                            if (data.fail != null) {

                                swal({
                                    type: 'error',
                                    title: '失败',
                                    text: "哎呦，修改失败了",
                                    confirmButtonText: "确定"

                                });
                                return;
                            }
                            if (data.warning != null){
                                swal({
                                    title: "操作提示",
                                    text: data.warning,
                                    type: "warning",
                                    showCancelButton: false, //是否显示取消按钮
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: "确定"//确定按钮上面的文档
                                })
                            }
                            if (data.role_warning != null){
                                swal({
                                    title: "操作提示",
                                    text: data.role_warning,
                                    type: "warning",
                                    showCancelButton: false, //是否显示取消按钮
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: "确定"//确定按钮上面的文档
                                })
                            }
                        }
                    }
                });

            },function(dismiss) {
                // dismiss的值可以是'cancel', 'overlay','close', 'timer'
                if (dismiss === 'cancel') {}
            });

        }

        function updateApiDefProp(id,prop) {

            var aid = id ;
            var prop_w  = prop/100 ;
            swal({
                title: '修改产品预设比例',
                input: 'text',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                cancelButtonText: "取消",
                confirmButtonText: "确定修改",
                allowOutsideClick: true,
                inputValue: prop_w,
                inputValidator: function(value) {
                    return new Promise(function(resolve, reject) {
                        var re =new RegExp("^0.[0-9]{1,2}|0|1$");
                        if(!re.test(value)){
                            reject('格式输入不正确，区间范围：[0,1]！');
                        } else {
                            resolve();
                        }
                    });
                }
            }).then(function (value) {

                $.ajax({
                    type: "post",
                    url: "/api/update-def-prop",
                    data: {"aid": aid, "prop": value},
                    dataType: "json",
                    beforeSend: function () {
                        openProgress();
                    },
                    success: function (data) {
                        closeProgress();
                        if (data != null) {
                            if (data.success != null) {
                                swal({
                                    type: 'success',
                                    title: '预设比例修改完成',
                                    confirmButtonText: "确定",
                                    html: '已将预设比例修改为：' + value * 100 + "%"
                                }).then(function () {
                                    window.location.href = window.location.href ;
                                    return;
                                });

                            }
                            if (data.fail != null) {

                                swal({
                                    type: 'error',
                                    title: '失败',
                                    text: "哎呦，修改失败了",
                                    confirmButtonText: "确定"

                                });
                                return;
                            }
                            if (data.warning != null){
                                swal({
                                    title: "操作提示",
                                    text: data.warning,
                                    type: "warning",
                                    showCancelButton: false, //是否显示取消按钮
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: "确定"//确定按钮上面的文档
                                })
                            }
                            if (data.role_warning != null){
                                swal({
                                    title: "操作提示",
                                    text: data.role_warning,
                                    type: "warning",
                                    showCancelButton: false, //是否显示取消按钮
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: "确定"//确定按钮上面的文档
                                })
                            }
                        }
                    }
                });

            },function(dismiss) {
                // dismiss的值可以是'cancel', 'overlay','close', 'timer'
                if (dismiss === 'cancel') {}
            });

        }
        
        function lookDetailLog(id) {
            $.ajax({
                type: "post",
                url: "/api/query-detail-log",
                data: {"aid": id},
                dataType: "json",
                success: function (data) {
                    var value = "无";
                    if (data != null) {
                        if (data.statusName != null && data.createTime != null) {
                            value = data.statusName + '（恢复日期：' + data.createTime+ '）'
                        }
                    }
                    swal({
                        title: '恢复详情',
                        confirmButtonText: "确定",
                        html: value
                    })
                }
            });
        }

    </script>

    </#if>

</@layout>
