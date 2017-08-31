
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

                    <form action="/finance/vendor-history-bill/detail" class="form-bottom" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <input style="display: none" id="vid" name="vid" value="${vid!''}">

                            <input style="display: none" id="name" name="name" value="${name!''}">

                            <div class="pull-left head-search-bottom">

                                <label>请选择周期</label>

                                <div class="controls">

                                    <select class="medium m-wrap" multiple id="cyc" name="cyc">
                                        <option value=""></option>
                                        <#if conTimeList??>
                                            <#list conTimeList as conTime>
                                                <option <#if cyc??><#list cyc as cyc><#if cyc?? && cyc == conTime> selected="selected"</#if></#list></#if> value="${conTime}">${conTime}</option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label>请选择产品</label>

                                <div class="controls">
                                    <select class="medium m-wrap" multiple id="aid" name="aid">
                                        <option value=""></option>
                                        <#if apiList??>
                                            <#list apiList as api>
                                                <option <#if aid??><#list aid as aid><#if aid?? && aid == api.apiId> selected="selected"</#if></#list></#if> value="${api.apiId}">${api.apiTypeName}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label>请选择状态</label>

                                <div class="controls">
                                    <select class="medium m-wrap" id="isLock" name="isLock">
                                        <option value=""></option>
                                        <option <#if isLock?? && isLock == 1> selected </#if> value="1">已锁定</option>
                                        <option <#if isLock?? && isLock == 0> selected </#if> value="0">未锁定</option>
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

                        <div class="portlet-title">

                            <div class="caption">${name!'无'}</div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-5">

                                <div class="btn-group">
                                    <button class="btn-icon black" id="add" data-target="#form_modal" data-toggle="modal">
                                        <i class="icon-plus-sign"></i>添加
                                    </button>
                                    <button class="btn-icon red" id="del">
                                        <i class=" icon-minus-sign"></i>删除
                                    </button>
                                    <button class="btn-icon black" id="lock">
                                        <i class=" icon-lock"></i>锁定
                                    </button>
                                    <button class="btn-icon red" id="unlock">
                                        <i class="  icon-unlock"></i>解除锁定
                                    </button>
                                </div>

                                <div class="pull-right tip-remark">
                                    <span class="pull-right">注：本页面与金额相关数字单位都为：元</span>
                                    </br>
                                    <span class="pull-right">消费总额&yen;：${conTot!'0.0'}元</span>
                                </div>

                            </div>

                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_2">
                                <thead>
                                <tr>
                                    <th style="width: 5%"><input type="checkbox" id="allChecked" class="group-checkable" data-set="#sample_2 .checkboxes"/></th>
                                    <th>周期</th>
                                    <th>产品名称</th>
                                    <th>单价</th>
                                    <th>扣用量</th>
                                    <th>金额</th>
                                    <th>状态</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if billDetailList??>
                                        <#list billDetailList as billDetail>
                                        <tr <#if billDetail.isLock == 1> class="success" </#if>>
                                            <td data-title="多选框"><input class="checkboxes" type="checkbox" id="checkBox" name="checkBox" value="${billDetail.id}"/></td>
                                            <td data-title="周期">${billDetail.yearMonth!'无'}</td>
                                            <td data-title="产品名称">${billDetail.apiTypeName!'无'}</td>
                                            <td data-title="单价"><a href="javaScript:;" onclick="updateCost(${billDetail.id},${billDetail.cost})" data-toggle="tooltip" data-placement="bottom" title="点击修改单价">${billDetail.cost!'0'}</a></td>
                                            <td data-title="扣用量"><a href="javaScript:;" onclick="updateAmount(${billDetail.id},${billDetail.amount?c})" data-toggle="tooltip" data-placement="bottom" title="点击修改扣费量">${billDetail.amount!'0'}</a></td>
                                            <td data-title="金额">${billDetail.consumeAmount!'0'}</td>
                                            <td data-title="状态">${billDetail.isLockName!'无'}</td>
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

                                <div id="" class="control-group">
                                    <label class="control-label">请选择产品<span class="required">*</span></label>
                                    <div class="controls">
                                        <select id="add_aid" name="add_aid" class="large m-wrap">
                                            <option value=""></option>
                                            <#if apiList??>
                                                <#list apiList as api>
                                                    <option value="${api.apiId}">${api.apiTypeName}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </div>
                                </div>

                                <div id="" class="control-group">
                                    <label class="control-label">请输入单价<span class="required">*</span></label>
                                    <div class="controls">
                                        <input type="text" id="add_cost" name="add_cost" class="medium m-wrap" placeholder="（单位/元）">
                                        <span class="help-block">说明：只能输入数字类型并且金额大于0</span>
                                    </div>
                                </div>

                                <div id="" class="control-group">
                                    <label class="control-label">请输入扣用量<span class="required">*</span></label>
                                    <div class="controls">
                                        <input type="text" id="add_amount" name="add_amount" class="medium m-wrap" placeholder="（单位/元）">
                                        <span class="help-block">说明：只能输入数字类型并且金额大于0</span>
                                    </div>
                                </div>

                                <div id="" class="control-group">
                                    <label class="control-label">请选择周期<span class="required">*</span></label>
                                    <div class="controls">
                                        <select id="add_cyc" name="add_cyc" class="large m-wrap">
                                            <option value=""></option>
                                            <#if conTimeList??>
                                                <#list conTimeList as conTime>
                                                    <option value="${conTime}">${conTime}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </div>
                                </div>

                            </form>
                        </div>
                        <div class="modal-footer">
                            <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                            <button class="btn black btn-primary" id="add_btn" type="button">提交</button>
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

    <script src="/js/myjs/vendor-history_bill_month_detail.js?v=${ver}"></script>

    <script>

        jQuery(document).ready(function () {
            VendorHistoryBillMonthDetail.init();
        });

        $('#add_aid').select2({
            language: "zh-CN",
            placeholder: "请选择",
            allowClear: true
        });

        $('#add_cyc').select2({
            language: "zh-CN",
            placeholder: "请选择",
            allowClear: true
        });

        function updateCost(id,cost) {

            $.ajax({
                type: "post",
                url: "/finance/vendor-history-bill/detail/check-lock",
                data: {"id": id},
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        if (data.lock != null && data.lock == "lock") {
                            swal({
                                title: "操作提示",
                                text: "该数据已锁定，无法修改！",
                                type: "info",
                                confirmButtonText: "确定"
                            })

                        }
                    }
                }
            });

            swal({
                title: '修改产品单价',
                html:
                '<input id="input1_cost" placeholder="单价" value="'+ cost +'" class="swal2-input">' +
                '<textarea id="input2_cost" placeholder="备注" class="swal2-textarea" rows="3"></textarea>',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                allowOutsideClick: true,
                preConfirm: function () {
                    return new Promise(function (resolve,reject) {
                        var re =new RegExp("^(-?\\d+)(\\.\\d+)?$");
                        if(!re.test($('#input1_cost').val())){
                            reject('格式输入不正确！');
                        } else {
                            resolve([
                                $('#input1_cost').val(),
                                $('#input2_cost').val()
                            ])
                        }
                    })
                }
            }).then(function (value) {
                console.log(JSON.stringify(value));
                console.log(value);

                $.ajax({
                    type: "post",
                    url: "/finance/vendor-history-bill/detail/update-cost",
                    data: {"id": id,"oldCost": cost,"data": JSON.stringify(value)},
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
                                    title: '成功',
                                    text: "单价修改完成",
                                    confirmButtonText: "确定"
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
                if (dismiss === 'cancel') {}
            });
        }
        
        function updateAmount(id,amount) {

            $.ajax({
                type: "post",
                url: "/finance/vendor-history-bill/detail/check-lock",
                data: {"id": id},
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        if (data.lock != null && data.lock == "lock") {
                            swal({
                                title: "操作提示",
                                text: "该数据已锁定，无法修改！",
                                type: "info",
                                confirmButtonText: "确定"
                            })

                        }
                    }
                }
            });

            swal({
                title: '修改产品扣费量',
                html:
                '<input id="input1_amount" placeholder="扣费量" value="'+ amount +'" class="swal2-input">' +
                '<textarea id="input2_amount" placeholder="备注" class="swal2-textarea" rows="3"></textarea>',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                allowOutsideClick: true,
                preConfirm: function () {
                    return new Promise(function (resolve,reject) {
                        var re =new RegExp("^(-?\\d+)(\\.\\d+)?$");
                        if(!re.test($('#input1_amount').val())){
                            reject('格式输入不正确！');
                        } else {
                            resolve([
                                $('#input1_amount').val(),
                                $('#input2_amount').val()
                            ])
                        }
                    })
                }
            }).then(function (value) {
                console.log(JSON.stringify(value));
                console.log(value);

                $.ajax({
                    type: "post",
                    url: "/finance/vendor-history-bill/detail/update-amount",
                    data: {"id": id,"oldAmount": amount,"data": JSON.stringify(value)},
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
                                    title: '成功',
                                    text: "扣费量修改完成",
                                    confirmButtonText: "确定"
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
                if (dismiss === 'cancel') {}
            });

        }

    </script>

    </#if>

</@layout>
