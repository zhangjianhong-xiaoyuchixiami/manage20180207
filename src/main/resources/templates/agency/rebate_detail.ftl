
<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<#import "../publicPart/publicJs.ftl" as puj>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">

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

                            <input style="display: none" id="agencyId" name="agencyId" value="${agencyId?c}">

                            <input style="display: none" id="name" name="name" value="${name!'无'}">

                            <div class="pull-left head-search-bottom">

                                <label class="">请选择代理客户</label>

                                <div class="controls" id="cid_chosen">

                                    <select class="medium m-wrap" multiple id="cid" name="cid">
                                        <option value=""></option>
                                        <#if companyList??>
                                            <#list companyList as company>
                                                <option <#if companyArray??><#list companyArray as array><#if array?? && array == company.companyId>selected="selected"</#if></#list></#if> value="${company.companyId}">${company.companyName}</option>
                                            </#list>
                                        </#if>
                                    </select>

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

                    <div class="row-fluid">

                        <table class="table-bordered table-striped table-condensed flip-content">
                            <thead class="flip-content">
                            <tr>
                                <td style="width: 10%;"><h4>代理人</h4></td>
                                <td style="width: 20%;"><h4>返佣规则</h4></td>
                                <td style="width: 70%;" colspan="2"><h4>付款明细</h4></td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="table table-bordered">
                                <td rowspan="4">${name!'无'}</td>
                                <td rowspan="4">${rule!'无'}</td>
                                <td>对方调用我方源（不包含缓存，按真实成本价计算）：${costRealOur!'0.0'}</strong></td>
                                <td>对方调用我方源（不包含缓存，按返佣起始价计算）：${costVirtualOur!'0.0'}</strong></td>
                            </tr>
                            <tr>
                                <td>我方调用对方源（不包含缓存）：${costOpposite!'0.0'}</strong></td>
                                <td>首次业务回扣：${firstRebate!'0.0'}</></td>
                            </tr>
                            <tr>
                                <td>二次业务回扣：${secondaryRebate!'0.0'}</strong></td>
                                <td>调用缓存：${costCache!'0.0'}</strong></td>
                            </tr>
                            <tr>
                                <td>缓存分成：${cacheRebate!'0.0'}</td>
                                <td>净利润：${netProfit!'0.0'}</td>
                            </tr>
                            </tbody>
                        </table>

                    </div>

                    <hr/>

                    <div class="tabbable tabbable-custom boxless">

                        <ul class="nav nav-tabs">

                            <li class="active"><a href="#tab_1" data-toggle="tab">对方调用我方源（不包含缓存）</a></li>

                            <li><a href="#tab_2" data-toggle="tab">我方调用对方源（不包含缓存）</a></li>

                            <li><a href="#tab_3" data-toggle="tab">缓存调用</a></li>

                        </ul>

                        <div class="tab-content">

                            <div class="tab-pane active" id="tab_1">

                                <div class="portlet-title">

                                    <div class="caption">

                                        <div class="btn-group">

                                            <button class="btn-icon_1 red" id="del">
                                                <i class=" icon-minus-sign"></i>删除
                                            </button>

                                        </div>

                                    </div>

                                </div>

                                <table class="table table-hover table-condensed" id="sample_1">
                                    <thead>
                                    <tr>
                                        <th style="width: 5%"><input type="checkbox" class="group-checkable" data-set="#sample_1 .checkboxes"/></th>
                                        <th>公司名称</th>
                                        <th>周期</th>
                                        <th>产品名称</th>
                                        <th>供应商</th>
                                        <th>扣费量</th>
                                        <th>单价</th>
                                        <th>售价</th>
                                        <th>回扣起始价</th>
                                        <th>回扣结算价</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if detailList??>
                                            <#list detailList as detail>
                                            <tr>
                                                <td style="width: 5%" data-title="多选框"><input class="checkboxes" type="checkbox" id="checkBox" name="checkBox" value="${detail.id?c}"/></td>
                                                <td>${detail.companyName!'无'}</td>
                                                <td>${detail.cycle!'无'}</td>
                                                <td>${detail.type_stid_name!'无'}</td>
                                                <td>${detail.vendorName!'无'}</td>
                                                <td><a href="javaScript:;" onclick="updateAmount(${detail.id?c},${detail.costCount?c})">${detail.costCount!'0'}</a></td>
                                                <td><a href="javaScript:;" onclick="updateCost(${detail.id?c},${detail.cost!'0'})">${detail.cost!'0'}</a></td>
                                                <td><a href="javaScript:;" onclick="updatePrice(${detail.id?c},${detail.price!'0'})">${detail.price!'0'}</a></td>
                                                <td><a href="javaScript:;" onclick="updateRebateBegPrice(${detail.id?c},${detail.rebateBegPrice!'0'})">${detail.rebateBegPrice!'--'}</a></td>
                                                <td><a href="javaScript:;" onclick="updateRebateEndPrice(${detail.id?c},${detail.rebateEndPrice!'0'})">${detail.rebateEndPrice!'--'}</a></td>
                                            </tr>
                                            </#list>
                                        </#if>
                                    </tbody>
                                </table>

                            </div>

                            <div class="tab-pane " id="tab_2">

                                <table class="table table-hover table-condensed" id="sample_2">
                                    <thead>
                                    <tr>
                                        <th>周期</th>
                                        <th>产品名称</th>
                                        <th>供应商</th>
                                        <th>扣费量</th>
                                        <th>单价</th>
                                        <th>金额</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if detailListOppo??>
                                            <#list detailListOppo as detail>
                                            <tr>
                                                <td>${detail.cycle!'无'}</td>
                                                <td>${detail.type_stid_name!'无'}</td>
                                                <td>${detail.vendorName!'无'}</td>
                                                <td>${detail.costCount!'0'}</td>
                                                <td>${detail.cost!'0'}</td>
                                                <td>${detail.costMoney!'0'}</td>
                                            </tr>
                                            </#list>
                                        </#if>
                                    </tbody>
                                </table>

                            </div>

                            <div class="tab-pane " id="tab_3">

                                <div class="portlet-title">

                                    <div class="caption">

                                        <div class="btn-group">

                                            <button class="btn-icon_1 red" id="del_cache">
                                                <i class=" icon-minus-sign"></i>删除
                                            </button>

                                        </div>

                                    </div>

                                </div>

                                <table class="table table-hover table-condensed" id="sample_3">
                                    <thead>
                                    <tr>
                                        <th style="width: 5%"><input type="checkbox" class="group-checkable" data-set="#sample_3 .checkboxes"/></th>
                                        <th>公司名称</th>
                                        <th>周期</th>
                                        <th>产品名称</th>
                                        <th>扣费量</th>
                                        <th>售价</th>
                                        <th>金额</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if detailListCache??>
                                            <#list detailListCache as detail>
                                            <tr>
                                                <td style="width: 5%" data-title="多选框"><input class="checkboxes" type="checkbox" id="checkBox_cache" name="checkBox_cache" value="${detail.id?c}"/></td>
                                                <td>${detail.companyName!'无'}</td>
                                                <td>${detail.cycle!'无'}</td>
                                                <td>${detail.type_stid_name!'无'}</td>
                                                <td>${detail.costCount!'0'}</td>
                                                <td><a href="javaScript:;" onclick="updateCachePrice(${detail.id?c},${detail.price!'0'})">${detail.price!'0'}</a></td>
                                                <td>${detail.priceMoney!'0'}</td>
                                            </tr>
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

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

        <@puj.publicJs></@puj.publicJs>

    <script src="/js/myjs/rebate/rebate_detail.js?v=${ver}"></script>

    <script src="/js/myjs/rebate/rebate_detail_cache.js?v=${ver}"></script>

    <script src="/js/myjs/rebate/rebate_detail_opposite.js?v=${ver}"></script>

    <script>

        jQuery(document).ready(function() {

            RebateDetail.init();
            RebateDetailCache.init();
            RebateDetailOpposite.init();

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
                    url: "/finance/rebate/detail/update-rebate-begin-price",
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

        //修改缓存售价
        function updateCachePrice(id,price) {
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
                    url: "/finance/rebate/detail/update-cache-price",
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

        //删除缓存记录
        $("#del_cache").on('click',function () {
            var id =[];//定义一个数组
            $('input[name="checkBox_cache"]:checked').each(function(){
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
                        url:"/finance/rebate/detail/delete-cache",
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
