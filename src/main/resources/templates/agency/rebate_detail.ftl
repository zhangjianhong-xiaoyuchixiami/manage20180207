
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

                    <div class="alert alert-success">

                        <button class="close" data-dismiss="alert"></button>

                        1、本页面与金额有关字段单位都是元<br>

                    </div>

                    <form action="/finance/rebate/detail" id="submit_form" class="form-bottom-excel form-top" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <input style="display: none" id="agencyId" name="agencyId" value="${agencyId}">

                            <div class="pull-left head-search-bottom">

                                <label>&nbsp;</label>

                                <div class="controls">

                                    <label class="checkbox">

                                        <input type="checkbox" <#if cache??> checked="checked" </#if> id="cache" name="cache" value="1">包含缓存

                                    </label>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label class="">请选择产品</label>

                                <div class="controls">

                                    <select class="medium m-wrap" multiple id="tid" name="tid">
                                        <option value=""></option>
                                        <#if typeList??>
                                            <#list typeList as type>
                                                <option <#if typeArray??><#list typeArray as array><#if array?? && array == type.type_stid>selected="selected"</#if></#list></#if> value="${type.type_stid}">${type.type_stid_name}</option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label >请选择周期</label>

                                <div id="tid_choose" class="controls">

                                    <select class="medium m-wrap" multiple id="cyc" name="cyc">
                                        <option value=""></option>
                                        <#if cycleList??>
                                            <#list cycleList as cycle>
                                                <option <#if cycleArray??><#list cycleArray as array><#if array?? && array == cycle>selected="selected"</#if></#list></#if> value="${cycle}">${cycle}</option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label>&nbsp;&nbsp;</label>

                                <div class="controls" >

                                    <div class="input-append">

                                        <button class="btn black" id="submit" type="submit">确定</button>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </form>

                    <div class="row-fluid invoice">

                        <div class="row-fluid">

                            <div class="portlet-title" style="margin-bottom: -15px">

                                <div class="caption" style="margin-bottom: 0px">

                                    <div class="btn-group">

                                        <button class="btn-icon_1 red" id="del">
                                            <i class=" icon-minus-sign"></i>删除
                                        </button>

                                    </div>

                                </div>

                                <div class="actions">

                                    <div class="btn-group">

                                        <a class="btn" href="#" data-toggle="dropdown">

                                            表格显示列

                                            <i class="icon-angle-down"></i>

                                        </a>

                                        <div id="sample_1_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">

                                            <label><input type="checkbox" checked data-column="1">客户名称</label>

                                            <label><input type="checkbox" checked data-column="2">周期</label>

                                            <label><input type="checkbox" checked data-column="3">产品名称</label>

                                            <label><input type="checkbox" checked data-column="4">类别</label>

                                            <label><input type="checkbox" checked data-column="5">供应商</label>

                                            <label><input type="checkbox" checked data-column="6">扣费量</label>

                                            <label><input type="checkbox" checked data-column="7">单价</label>

                                            <label><input type="checkbox" checked data-column="8">售价</label>

                                            <label><input type="checkbox" checked data-column="9">回扣起始价</label>

                                            <label><input type="checkbox" checked data-column="10">回扣结算价</label>

                                        <#--<label><input type="checkbox" checked data-column="12">成本</label>-->

                                        <#--<label><input type="checkbox" checked data-column="13">销售额</label>-->

                                        <#--<label><input type="checkbox" checked data-column="14">毛利润</label>-->

                                        <#--<label><input type="checkbox" checked data-column="15">首次业务回扣</label>-->

                                        <#--<label><input type="checkbox" checked data-column="16">二次业务回扣</label>-->

                                        <#--<label><input type="checkbox" checked data-column="17">净利润</label>-->

                                        </div>

                                    </div>

                                </div>

                            </div>

                            <table class="table table-hover table-condensed table-layout-fixed" id="sample_1">
                                <thead>
                                <tr>
                                    <th style="width: 5%"><input type="checkbox" id="allChecked" class="group-checkable" data-set="#sample_1 .checkboxes"/></th>
                                    <th>客户名称</th>
                                    <th>周期</th>
                                    <th>产品名称</th>
                                    <th>类别</th>
                                    <th>供应商</th>
                                    <th>扣费量</th>
                                    <th style="width: 5%">单价</th>
                                    <th style="width: 5%">售价</th>
                                    <th>回扣起始价</th>
                                    <th>回扣结算价</th>
                                <#--<th>成本</th>-->
                                <#--<th>销售额</th>-->
                                <#--<th>毛利润</th>-->
                                <#--<th>首次业务回扣</th>-->
                                <#--<th>二次业务回扣</th>-->
                                <#--<th>净利润</th>-->
                                </tr>
                                </thead>
                                <tbody>
                                    <#if detailList??>
                                        <#list detailList as detail>
                                        <tr>
                                            <td style="width: 5%" data-title="多选框"><input class="checkboxes" type="checkbox" id="checkBox" name="checkBox" value="${detail.id?c}"/></td>
                                            <td class="table-td-layout-fixed">${detail.companyName!'无'}</td>
                                            <td>${detail.cycle!'无'}</td>
                                            <td>${detail.type_stid_name!'无'}</td>
                                            <td>${detail.typeName!'无'}</td>
                                            <td>${detail.vendor_partner_name!'无'}</td>
                                            <td><a href="javaScript:;" onclick="updateAmount(${detail.id?c},${detail.resultCount?c})">${detail.resultCount!'无'}</a></td>
                                            <td style="width: 5%"><a href="javaScript:;" onclick="updateCost(${detail.id?c},${detail.cost!'0'})">${detail.cost!'0'}</a></td>
                                            <td style="width: 5%"><a href="javaScript:;" onclick="updatePrice(${detail.id?c},${detail.price?c})">${detail.price!'0'}</a></td>
                                            <td><a href="javaScript:;" onclick="updateRebateBegPrice(${detail.id?c},${detail.rebateBegPrice!'0'})">${detail.rebateBegPrice!'--'}</a></td>
                                            <td><a href="javaScript:;" onclick="updateRebateEndPrice(${detail.id?c},${detail.rebateEndPrice!'0'})">${detail.rebateEndPrice!'--'}</a></td>
                                        <#--<td>${detail.costMoney!'0'}</td>-->
                                        <#--<td>${detail.priceMoney!'0'}</td>-->
                                        <#--<td>${detail.grossProfit!'0'}</td>-->
                                        <#--<td>${detail.firstRebate!'--'}</td>-->
                                        <#--<td>${detail.twiceRebate!'--'}</td>-->
                                        <#--<td>${detail.netProfit!'0'}</td>-->
                                        </tr>
                                        </#list>
                                    </#if>
                                </tbody>
                            </table>

                        </div>

                    <#--<div class="row-fluid">-->

                    <#--<div class="span4">-->

                    <#--</div>-->

                    <#--<div class="span8 invoice-block">-->

                    <#--<ul class="unstyled amounts">-->

                    <#--<li><strong>净利润总计:</strong> ￥${netProfitTot!'0'}</li>-->

                    <#--</ul>-->

                    <#--</br>-->

                    <#--</br>-->

                    <#--</br>-->

                    <#--</div>-->

                    <#--</div>-->

                    </div>

                </div>

            </div>

        </div>

    </div>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

        <@puj.publicJs></@puj.publicJs>

    <script src="/js/myjs/rebate/rebate_detail.js?v=${ver}"></script>

    <script>

        jQuery(document).ready(function() {

            RebateDetail.init();

        });

        //修改扣费量
        function updateAmount(id,amount) {
            swal({
                title: '修改扣费量',
                input: 'text',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                allowOutsideClick: true,
                inputValue: amount,
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
                    url: "/finance/rebate/detail/update-amount",
                    data: {"id": id,"amount": value},
                    dataType: "json",
                    beforeSend: function () {
                        openProgress();
                    },
                    success: function (data) {
                        closeProgress();
                        if (data != null) {
                            if (data.success != null) {
                                swal({type: 'success', title: '成功', text: "修改完成", confirmButtonText: "确定"}).then(function () {window.location.href = window.location.href ;return;});
                            }
                            if (data.fail != null) {
                                swal({type: 'error', title: '失败', text: "哎呦，修改失败了", confirmButtonText: "确定"})
                            }
                        }
                    }
                });
            },function(dismiss) {
                if (dismiss === 'cancel') {}
            });
        }

        //修改单价
        function updateCost(id,cost) {
            swal({
                title: '修改单价',
                input: 'text',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                allowOutsideClick: true,
                inputValue: cost,
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
                    url: "/finance/rebate/detail/update-cost",
                    data: {"id": id,"cost": value},
                    dataType: "json",
                    beforeSend: function () {
                        openProgress();
                    },
                    success: function (data) {
                        closeProgress();
                        if (data != null) {
                            if (data.success != null) {
                                swal({type: 'success', title: '成功', text: "修改完成", confirmButtonText: "确定"}).then(function () {window.location.href = window.location.href ;return;});
                            }
                            if (data.fail != null) {
                                swal({type: 'error', title: '失败', text: "哎呦，修改失败了", confirmButtonText: "确定"})
                            }
                        }
                    }
                });
            },function(dismiss) {
                if (dismiss === 'cancel') {}
            });
        }

        //修改售价
        function updatePrice(id,price) {
            swal({
                title: '修改售价',
                input: 'text',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                allowOutsideClick: true,
                inputValue: price,
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
                    url: "/finance/rebate/detail/update-price",
                    data: {"id": id,"price": value},
                    dataType: "json",
                    beforeSend: function () {
                        openProgress();
                    },
                    success: function (data) {
                        closeProgress();
                        if (data != null) {
                            if (data.success != null) {
                                swal({type: 'success', title: '成功', text: "修改完成", confirmButtonText: "确定"}).then(function () {window.location.href = window.location.href ;return;});
                            }
                            if (data.fail != null) {
                                swal({type: 'error', title: '失败', text: "哎呦，修改失败了", confirmButtonText: "确定"})
                            }
                        }
                    }
                });
            },function(dismiss) {
                if (dismiss === 'cancel') {}
            });
        }

        //修改业务回扣起始价
        function updateRebateBegPrice(id,price) {
            swal({
                title: '修改业务回扣起始价',
                input: 'text',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                allowOutsideClick: true,
                inputValue: price,
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
                    url: "/finance/rebate/detail/update-rebate-beg-price",
                    data: {"id": id,"price": value},
                    dataType: "json",
                    beforeSend: function () {
                        openProgress();
                    },
                    success: function (data) {
                        closeProgress();
                        if (data != null) {
                            if (data.success != null) {
                                swal({type: 'success', title: '成功', text: "修改完成", confirmButtonText: "确定"}).then(function () {window.location.href = window.location.href ;return;});
                            }
                            if (data.fail != null) {
                                swal({type: 'error', title: '失败', text: "哎呦，修改失败了", confirmButtonText: "确定"})
                            }
                        }
                    }
                });
            },function(dismiss) {
                if (dismiss === 'cancel') {}
            });
        }

        //修改业务回扣结算价
        function updateRebateEndPrice(id,price) {
            swal({
                title: '修改业务回扣结算价',
                input: 'text',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                allowOutsideClick: true,
                inputValue: price,
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
                    url: "/finance/rebate/detail/update-rebate-end-price",
                    data: {"id": id,"price": value},
                    dataType: "json",
                    beforeSend: function () {
                        openProgress();
                    },
                    success: function (data) {
                        closeProgress();
                        if (data != null) {
                            if (data.success != null) {
                                swal({type: 'success', title: '成功', text: "修改完成", confirmButtonText: "确定"}).then(function () {window.location.href = window.location.href ;return;});
                            }
                            if (data.fail != null) {
                                swal({type: 'error', title: '失败', text: "哎呦，修改失败了", confirmButtonText: "确定"})
                            }
                        }
                    }
                });
            },function(dismiss) {
                if (dismiss === 'cancel') {}
            });
        }

        //删除记录
        $("#del").on('click',function () {
            var id =[];//定义一个数组
            $('input[name="checkBox"]:checked').each(function(){
                id.push($.trim($(this).val()));
            });
            if (id == null || id == ""){
                swal({
                    title: "操作提示",
                    text: "请先选择要删除的列！",
                    type: "info",
                    confirmButtonText: "确定"
                });
            }else {
                swal({
                    title: "确定要删除吗？",   //弹出框的title
                    type: "question",    //弹出框类型
                    showCancelButton: true, //是否显示取消按钮
                    allowOutsideClick: false,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    cancelButtonText: "取消",//取消按钮文本
                    confirmButtonText: "确定"//确定按钮上面的文档
                }).then(function () {
                    $.ajax({
                        type:'post',
                        url:"/finance/rebate/detail/delete",
                        data:{"id": id},
                        dataType:"json",
                        beforeSend:function () {
                            openProgress();
                        },
                        success:function(data){
                            closeProgress();
                            if (data != null) {
                                if (data.success != null) {
                                    swal({type: 'success', title: '成功', text: "删除完成", confirmButtonText: "确定"}).then(function () {window.location.href = window.location.href ;return;});
                                }
                                if (data.fail != null) {
                                    swal({type: 'error', title: '失败', text: "哎呦，删除失败了", confirmButtonText: "确定"})
                                }
                            }
                        }
                    });
                },function(dismiss) {if (dismiss === 'cancel') {}});
            }
        });

    </script>

    </#if>

</@layout>
