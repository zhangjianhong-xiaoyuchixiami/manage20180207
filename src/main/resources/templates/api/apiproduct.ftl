
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

                                <label class="control-label">产品类型</label>

                                <div class="controls">

                                    <select class="medium m-wrap chosen" data-placeholder="请选择..." tabindex="1" id="apiTypeId" name="apiTypeId">
                                        <option value=""></option>
                                        <#if apiTypeList??>
                                            <#list apiTypeList as apiType>
                                                <option <#if apiTypeId?? && apiTypeId==apiType.id>selected="selected"</#if> value="${apiType.id}">${apiType.name}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">产品供应商</label>

                                <div class="controls">

                                    <select class="medium m-wrap chosen" data-placeholder="请选择..." tabindex="1" id="vendorId" name="vendorId">
                                        <option value=""></option>
                                        <#if apiVendorList??>
                                            <#list apiVendorList as apiVendor>
                                                <option <#if vendorId?? && vendorId==apiVendor.id>selected="selected"</#if> value="${apiVendor.id}">${apiVendor.name}<#if apiVendor.partner??>@${apiVendor.partner.name}</#if></option>
                                            </#list>
                                        </#if>
                                    </select>
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
                                                <span>注：用绿色标注的行是产品主通道</span>
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
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <#if apiList??>
                                                    <#list apiList as api>
                                                        <#if api.status == 0>
                                                            <#if api.proxyApi.minCost==api.cost>
                                                            <tr class="success">
                                                            <#else >
                                                            <tr>
                                                            </#if>
                                                            <td data-title="选择"><input class="checkboxes" type="checkbox" id="checkBoxApiIdBan" name="checkBoxApiIdBan" value="${api.id}"/></td>
                                                            <td data-title="产品类型">${api.apiType.name}
                                                                <#if (api.mobileOperatorList?size>0)>--
                                                                    <#list api.mobileOperatorList as mobileOperator>
                                                                    ${mobileOperator.name}<#if (api.mobileOperatorList?size>1)>,</#if>
                                                                    </#list>
                                                                </#if>
                                                                <#if (api.proxyApi.proxyApiTypeName)??>（调用：${api.proxyApi.proxyApiTypeName!''}）</#if>
                                                            </td>
                                                            <td data-title="合作公司">
                                                                <#if (api.apiVendor.partner.id)??>
                                                                    <a href="/api/api-message?partnerId=${(api.apiVendor.partner.id)!''}">${(api.apiVendor.partner.name)!'无'}</a>
                                                                <#else >
                                                                    无
                                                                </#if>
                                                            </td>
                                                            <td data-title="产品供应商">${api.apiVendor.name}<#if (api.apiVendor.partner)??>@${api.apiVendor.partner.name}</#if></td>
                                                            <td data-title="价格（单位：元）"><#--<a href="javaScript:;" onclick="updateApiPrice(${api.id},${(api.cost)?c})">-->${(api.cost/100.0)?c}<#--</a>--></td>
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
                                        </div>

                                        <table class="table table-striped table-bordered table-hover table-condensed" id="sample_product_2">
                                            <thead>
                                            <tr>
                                                <th><input type="checkbox" id="allChecked" class="group-checkable" data-set="#sample_product_2 .checkboxes"/></th>
                                                <th>产品类型</th>
                                                <th>合作公司</th>
                                                <th>产品供应商</th>
                                                <th>价格（单位：元）</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <#if apiList??>
                                                    <#list apiList as api>
                                                        <#if api.status != 0>
                                                        <tr>
                                                            <td><input class="checkboxes" type="checkbox" id="checkBoxApiIdUnBan" name="checkBoxApiIdUnBan" value="${api.id}"/></td>
                                                            <td data-title="产品类型" class="font-text-decoration">${api.apiType.name}
                                                                <#if (api.mobileOperatorList?size>0)>--
                                                                    <#list api.mobileOperatorList as mobileOperator>
                                                                    ${mobileOperator.name}<#if (api.mobileOperatorList?size>1)>,</#if>
                                                                    </#list>
                                                                </#if>
                                                                <#if (api.proxyApi.proxyApiTypeName)??>（调用：${api.proxyApi.proxyApiTypeName!''}）</#if>
                                                            </td>
                                                            <td data-title="合作公司">
                                                                <#if (api.apiVendor.partner.id)??>
                                                                    <a href="/api/api-message?partnerId=${(api.apiVendor.partner.id)!''}">${(api.apiVendor.partner.name)!'无'}</a>
                                                                <#else >
                                                                    无
                                                                </#if>
                                                            </td>
                                                            <td data-title="产品供应商">${api.apiVendor.name}<#if (api.apiVendor.partner)??>@${api.apiVendor.partner.name}</#if></td>
                                                            <td data-title="价格（单位：元）"><#--<a href="javaScript:;" onclick="updateApiPrice(${api.id},${(api.cost)?c})">-->${(api.cost/100.0)?c}<#--</a>--></td>
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

    <script type="text/javascript" src="/js/former/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/former/DT_bootstrap.js"></script>

    <script src="/js/sweetalert/sweetalert2.min.js"></script>

    <script src="/js/sweetalert/core.js"></script>

    <script src="/js/myjs/api-product.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {

            ApiProduct.init();

            /*状态正常批量禁用Api操作*/
            $("#batchBanApi").on('click',function () {

                var apiId =[];//定义一个数组
                $('input[name="checkBoxApiIdBan"]:checked').each(function(){
                    apiId.push($.trim($(this).val()));
                });

                if (apiId == null || apiId == ""){
                    swal({
                        title: "操作提示",
                        text: "请先选择要禁用的产品！",
                        type: "info",
                        confirmButtonText: "确定"
                    });
                }else {

                    swal({
                        title: "确定要禁用吗？",   //弹出框的title
                        type: "question",    //弹出框类型
                        showCancelButton: true, //是否显示取消按钮
                        allowOutsideClick: false,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        cancelButtonText: "取消",//取消按钮文本
                        confirmButtonText: "确定禁用"//确定按钮上面的文档
                    }).then(function () {

                        $.ajax({
                            type:'post',
                            url:"/api/ban",
                            data:{"apiId": apiId},
                            dataType:'json',
                            beforeSend:function () {
                                openProgress();
                            },
                            success:function(data){
                                closeProgress();
                                if (data != null){
                                    if (data.fail != null){
                                        swal({
                                            title: "操作提示",
                                            text: data.fail,
                                            type: "error",
                                            showCancelButton: false,
                                            confirmButtonColor: '#3085d6',
                                            confirmButtonText: "确定"
                                        }).then(function () {
                                            location.reload();
                                            return;
                                        })
                                    }
                                    if (data.success != null){
                                        swal({
                                            title: "操作提示",
                                            text: "禁用成功",
                                            type: "success",
                                            showCancelButton: false, //是否显示取消按钮
                                            confirmButtonColor: '#3085d6',
                                            confirmButtonText: "确定"//确定按钮上面的文档
                                        }).then(function () {
                                            location.reload();
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
            });

            /*状态禁用批量启用Api操作*/
            $("#batchUnBanApi").on('click',function () {

                var apiId =[];//定义一个数组
                $('input[name="checkBoxApiIdUnBan"]:checked').each(function(){
                    apiId.push($.trim($(this).val()));
                });

                if (apiId == null || apiId == ""){
                    swal({
                        title: "操作提示",
                        text: "请先选择要启用的产品！",
                        type: "info",
                        confirmButtonText: "确定"
                    });
                }else {

                    swal({
                        title: "确定要启用吗？",   //弹出框的title
                        type: "question",    //弹出框类型
                        showCancelButton: true, //是否显示取消按钮
                        allowOutsideClick: false,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        cancelButtonText: "取消",//取消按钮文本
                        confirmButtonText: "确定启用"//确定按钮上面的文档
                    }).then(function () {

                        $.ajax({
                            type:'post',
                            url:"/api/unban",
                            data:{"apiId": apiId},
                            dataType:'json',
                            beforeSend:function () {
                                openProgress();
                            },
                            success:function(data){
                                closeProgress();
                                if (data != null){
                                    if (data.fail != null){
                                        swal({
                                            title: "操作提示",
                                            text: data.fail,
                                            type: "error",
                                            showCancelButton: false,
                                            confirmButtonColor: '#3085d6',
                                            confirmButtonText: "确定"
                                        }).then(function () {
                                            location.reload();
                                            return;
                                        })
                                    }
                                    if (data.success != null){
                                        swal({
                                            title: "操作提示",
                                            text: "启用成功",
                                            type: "success",
                                            showCancelButton: false, //是否显示取消按钮
                                            confirmButtonColor: '#3085d6',
                                            confirmButtonText: "确定"//确定按钮上面的文档
                                        }).then(function () {
                                            location.reload();
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
            });

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
                    url: "/api/update-price",
                    data: {"aid": aid, "pic": pic},
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
                                    text: "哎呦，修改失败了",
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

    </script>

    </#if>

</@layout>
