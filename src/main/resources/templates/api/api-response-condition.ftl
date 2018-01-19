
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

                    <div class="portlet box grey">

                        <div class="portlet-body">

                            <div class="table-responsive">

                                <table class="table table-bordered table-hover table-condensed table-layout-fixed" id="sample_2">
                                    <thead>
                                        <tr>
                                            <th>产品名称</th>
                                            <th>供应商名称</th>
                                            <th>今日请求数</th>
                                            <th>请求失败率</th>
                                            <th>0—500ms</th>
                                            <th class="table-td-none">请求成功数</th>
                                            <th class="table-td-none">请求失败数</th>
                                            <th class="table-td-none">0—500ms</th>
                                            <th class="table-td-none">500—1000ms</th>
                                            <th class="table-td-none">1000—1500ms</th>
                                            <th class="table-td-none">1500—2000ms</th>
                                            <th class="table-td-none">2000—2500ms</th>
                                            <th class="table-td-none">2500—3000ms</th>
                                            <th class="table-td-none">3000ms—+∞</th>
                                            <th class="table-td-none">产品标签</th>
                                            <th>响应时间走势</th>
                                            <th>标签</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <#if apiResponseConditions??>
                                            <#list apiResponseConditions as condition>
                                                <tr>
                                                    <td data-title="产品名称" >
                                                        <#if condition.apiName??>
                                                        (apiId:${condition.apiId})
                                                        ${condition.apiName!'未知'}
                                                        </#if>
                                                    </td>
                                                    <td data-title="供应商名称">${condition.vendorName}</td>
                                                    <td data-title="今日请求数">${condition.totalCount}</td>
                                                    <td data-title="请求失败率">${condition.failPercent}</td>
                                                    <td data-title="0—500ms">${condition.count1}</td>
                                                    <td data-title="请求成功数" class="table-td-none">${condition.successCount}</td>
                                                    <td data-title="请求失败数" class="table-td-none">${condition.failCount}</td>
                                                    <td data-title="0—500ms" class="table-td-none">${condition.count1}</td>
                                                    <td data-title="500—1000ms" class="table-td-none">${condition.count2}</td>
                                                    <td data-title="1000—1500ms" class="table-td-none">${condition.count3}</td>
                                                    <td data-title="1500—2000ms" class="table-td-none">${condition.count4}</td>
                                                    <td data-title="2000—2500ms" class="table-td-none">${condition.count5}</td>
                                                    <td data-title="2500—3000ms" class="table-td-none">${condition.count6}</td>
                                                    <td data-title="3000ms—+∞" class="table-td-none">${condition.count7}</td>
                                                    <td data-title="产品标签" class="table-td-none"> ${condition.apiTag! '暂无标签'}</td>
                                                    <td data-title="响应时间走势"><a href="/api-response-condition/api-response-time-trends?apiId=${condition.apiId}&vendorName=${condition.vendorName}&apiName=${condition.apiName}"  data-toggle="modal" data-toggle="tooltip" data-placement="bottom" title="响应时间走势">查看</a></td>
                                                    <td data-title="操作" class="table-td-layout-fixed">
                                                        <a href="#" onclick="showApiTags(${condition.apiId})" data-toggle="modal" data-toggle="tooltip" data-placement="bottom" title="给此产品添加标签">
                                                            ${condition.apiTag! '点击添加标签'}
                                                        </a>
                                                    </td>

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

    <script src="/js/myjs/api-response-condition.js?v=${ver}"></script>

    <script src="/js/myjs/api_finance_condition_left_bar.js?v=${ver}"></script>

    <script>
        jQuery(document).ready(function() {
            ApiResponseCondition.init();
        });

    </script>

    <script>

        /*显示标签*/
        function showApiTags(apiId) {

            $.ajax({
                type: "post",
                url: "/api-response-condition/show-api-tags",
                data: {"apiId": apiId},
                dataType: "json",

                success: function (data) {
                    var tag = data.apiTag;
                    if (typeof(tag)=="undefined"){

                        tag = "";
                    }
                    swal({
                        title: '修改产品标签',
                        input: 'textarea',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        cancelButtonText: "取消",
                        confirmButtonText: "提交",
                        allowOutsideClick: true,
                        inputValue:tag,


                    }).then(function (value) {

                        $.ajax({
                            type: "post",
                            url: "/api-response-condition/submitApiTag",
                            data: {"apiId": apiId, "apiTag": value},
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
                                            title: '产品标签修改完成',
                                            confirmButtonText: "确定",
                                            html: '已将产品标签修改为：' + value
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
                        if (dismiss === 'cancel') {}
                    });
                }
            })
        }

    </script>

</#if>

</@layout>
