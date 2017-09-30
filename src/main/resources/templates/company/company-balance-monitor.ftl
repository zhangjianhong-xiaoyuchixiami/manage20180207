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

                    <form action="/company/balance-monitor" class="form-bottom" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom">

                                <label>请输入公司名称</label>

                                <div class="controls">

                                    <div class="input-append">

                                        <input class="m-wrap" <#if name??>value="${name}" </#if> type="text" id="name" name="name" placeholder="请输入公司名称">

                                    </div>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label>请选择合作公司</label>

                                <div class="controls">

                                    <select class="medium m-wrap" id="pid" name="pid">
                                        <option value=""></option>
                                        <option <#if pid?? && pid == -100> selected="selected"</#if> value="-100">无</option>
                                        <#if partnerList??>
                                            <#list partnerList as partner>
                                                <option <#if pid?? && pid==partner.id>selected="selected"</#if> value="${partner.id}">${partner.name}</option>
                                            </#list>
                                        </#if>
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

                        <div class="portlet-body no-more-tables">

                            <table class="table table-striped table-hover table-bordered table-condensed table-full-width cf" id="Sample_1">
                                <thead class="cf">
                                <tr>
                                    <th><input type="checkbox" id="allChecked" class="group-checkable" data-set="#Sample_1 .checkboxes"/></th>
                                    <th>公司名称</th>
                                    <th>合作公司</th>
                                    <th>是否预付</th>
                                    <th>是否开启报警</th>
                                    <th>是否发送对方</th>
                                    <th>余额可用几天提醒</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if monitorList??>
                                        <#list monitorList as monitor>
                                        <tr>
                                            <td data-title="多选框"><input class="checkboxes" type="checkbox" id="checkBox" name="checkBox" value="${monitor.companyId?c}"/></td>
                                            <td data-title="公司名称" <#if monitor.status != 0>class="font-text-decoration"</#if>>${monitor.companyName}</td>
                                            <td data-title="合作公司">${monitor.partnerName!'无'}</td>
                                            <td data-title="是否预付"><a href="javaScript:;" onclick="isPrepay(${monitor.companyId?c})">${monitor.prepayName}</a></td>
                                            <td data-title="是否开启报警"><a href="javaScript:;" onclick="isAlarm(${monitor.companyId?c})">${monitor.alarmName}</a></td>
                                            <td data-title="是否发送对方"><a href="javaScript:;" onclick="isRemindCustomer(${monitor.companyId?c})">${monitor.remindCustomerName}</a></td>
                                            <td data-title="余额可用几天提醒"><a href="javaScript:;" onclick="ahead(${monitor.companyId?c})">${monitor.ahead}</a></td>
                                            <td data-title="操作"><a href="#form_modal_customer_email_list" onclick="queryEmail(${monitor.companyId?c})" data-toggle="modal">查看客户邮箱</a></td>
                                        </tr>
                                        </#list>
                                    </#if>
                                </tbody>
                            </table>

                        </div>

                    </div>

                <#--邮箱管理-->
                    <div id="form_modal_customer_email_list" class="modal hide fade myModal" role="dialog" aria-labelledby="myModalLabel_customer_email_list" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h3 id="myModalLabel_customer_email_list">客户邮箱列表</h3>
                        </div>

                        <div class="modal-body">
                            <div class="clearfix">
                                <div class="btn-group" style="margin-bottom: 10px;">
                                    <a id="simple_customer_email_new" class="btn black">
                                        添加<i class="icon-plus"></i>
                                    </a>
                                </div>
                            </div>
                            <span id="email_companyId" style="display: none;">

                            </span>
                            <table class="table table-striped table-hover table-bordered table-condensed" id="simple_customer_email_1">
                                <thead>
                                <tr>
                                    <th>邮箱</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
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

    <script src="/js/myjs/company-balance-monitor.js?v=${ver}"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {

            CompanyBalanceMonitor.init();

        });

        $('#pid').select2({
            language: "zh-CN",
            placeholder: "请选择",
            allowClear: true
        });

        function isPrepay(vid) {
            swal({
                title: '修改是否预付',
                input: 'select',
                inputOptions: {
                    '0': '否',
                    '1': '是'
                },
                inputPlaceholder: '请选择',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                allowOutsideClick: true,
                inputValidator: function(value) {
                    return new Promise(function(resolve, reject) {
                        if(value){
                            resolve();
                        } else {
                            reject('请至少选择一项！');
                        }
                    });
                }
            }).then(function (value) {
                $.ajax({
                    type: "post",
                    url: "/company/balance-monitor/update-prepay",
                    data: {"cid": vid, "value": value},
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
                                    title: '修改完成',
                                    confirmButtonText: "确定",
                                    html: '已修改成功'
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
            },function(dismiss) {if (dismiss === 'cancel') {}});
        }

        function isAlarm(vid) {
            swal({
                title: '修改是否报警',
                input: 'select',
                inputOptions: {
                    '0': '否',
                    '1': '是'
                },
                inputPlaceholder: '请选择',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                allowOutsideClick: true,
                inputValidator: function(value) {
                    return new Promise(function(resolve, reject) {
                        if(value){
                            resolve();
                        } else {
                            reject('请至少选择一项！');
                        }
                    });
                }
            }).then(function (value) {
                $.ajax({
                    type: "post",
                    url: "/company/balance-monitor/update-alarm",
                    data: {"cid": vid, "value": value},
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
                                    title: '修改完成',
                                    confirmButtonText: "确定",
                                    html: '已修改成功'
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
            },function(dismiss) {if (dismiss === 'cancel') {}});
        }

        function isRemindCustomer(vid) {
            swal({
                title: '修改是否通知对方',
                input: 'select',
                inputOptions: {
                    '0': '否',
                    '1': '是'
                },
                inputPlaceholder: '请选择',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                allowOutsideClick: true,
                inputValidator: function(value) {
                    return new Promise(function(resolve, reject) {
                        if(value){
                            resolve();
                        } else {
                            reject('请至少选择一项！');
                        }
                    });
                }
            }).then(function (value) {
                $.ajax({
                    type: "post",
                    url: "/company/balance-monitor/update-remind-customer",
                    data: {"cid": vid, "value": value},
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
                                    title: '修改完成',
                                    confirmButtonText: "确定",
                                    html: '已修改成功'
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
            },function(dismiss) {if (dismiss === 'cancel') {}});
        }

        function ahead(vid) {
            swal({
                title: '修改余额可用几天后通知',
                input: 'text',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                allowOutsideClick: true,
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
                    url: "/company/balance-monitor/update-ahead",
                    data: {"cid": vid, "value": value},
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
                                    title: '修改完成',
                                    confirmButtonText: "确定",
                                    html: '已修改成功'
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
            },function(dismiss) {if (dismiss === 'cancel') {}});
        }

        function queryEmail(vid) {
            $("#email_companyId").empty();
            $("#simple_customer_email_1 tbody").empty();
            $("#email_companyId").text(vid);
            $.ajax({
                type: "post",
                url: "/company/balance-monitor/query-email",
                data: {"cid": vid},
                dataType: "json",
                beforeSend:function () {
                    var myContent = "<tr>" +
                            "<td rowspan='2'>" + '正在加载，请稍后' + "<span class='loading'></span></td>" +
                            "</tr>";
                    $("#simple_customer_email_1 tbody").append(myContent);
                },
                success: function (data) {
                    $("#simple_customer_email_1 tbody").empty();
                    if (data != null && data.length > 0){
                        for (var i = 0; i < data.length; i++) {
                            var myContent = "<tr>" +
                                    "<td><a href='mailto:"+ data[i].email +"'>" + data[i].email + "</a></td>" +
                                    "<td><a href='javaScript:;' class='warning' onclick='deleteEmail("+ data[i].id +','+ vid +")'>删除</a></td>" +
                                    "</tr>";
                            $("#simple_customer_email_1 tbody").append(myContent);
                        }
                    }else {
                        var myContent = "<tr>" +
                                "<td rowspan='2'>" + '无记录' + "</td>" +
                                "</tr>";
                        $("#simple_customer_email_1 tbody").append(myContent);
                    }
                }
            });
        }

        function deleteEmail(id,vid) {
            swal({
                title: "确定要删除吗？",   //弹出框的title
                type: "warning",    //弹出框类型
                showCancelButton: true, //是否显示取消按钮
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                cancelButtonText: "取消",//取消按钮文本
                confirmButtonText: "确定删除"//确定按钮上面的文档
            }).then(function () {
                $.ajax({
                    type: "post",
                    url: "/company/balance-monitor/query-email/delete",
                    data: {"id": id, "cid": vid},
                    dataType: "json",
                    beforeSend: function () {
                        openProgress();
                    },
                    success: function (data) {
                        closeProgress();
                        if (data != null) {
                            if (data.success != null) {
                                swal(
                                        '成功',
                                        '已删除',
                                        'success'
                                );
                                queryEmail(vid);
                                return;
                            }
                            if (data.fail != null) {
                                swal(
                                        '失败',
                                        '哎呦，删除失败了',
                                        'error'
                                );
                            }
                        }
                    }
                })
            },function(dismiss) {
                if (dismiss === 'cancel') {}
            });
        }

        $("#simple_customer_email_new").on("click",function () {

            var cid =  $("#email_companyId").text();
            swal({
                title: '新增邮箱',
                input: 'text',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                cancelButtonText: "取消",
                confirmButtonText: "确定修改",
                allowOutsideClick: true,
                inputValidator: function(value) {
                    return new Promise(function(resolve, reject) {
                        var re =new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
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
                    url: "/company/balance-monitor/query-email/add",
                    data: {"cid": cid, "value": value},
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
                                    title: '添加成功',
                                    confirmButtonText: "确定",
                                    html: '已添加邮箱：' + value
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
                        }
                    }
                });
            },function(dismiss) {
                if (dismiss === 'cancel') {}
            });
        });


    </script>

    </#if>

</@layout>