
<#include "../customer/layout.ftl">

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">

                    <h3 class="page-title">

                    <#--客户信息-->

                    </h3>

                    <#--<ul class="breadcrumb">-->

                        <#--<li>-->

                            <#--<i class="icon-home"></i>-->

                            <#--<a href="/view/successUrl">首页</a>-->

                            <#--<i class="icon-angle-right"></i>-->

                        <#--</li>-->

                        <#--<li>-->

                            <#--<a href="#">财务管理</a>-->

                            <#--<i class="icon-angle-right"></i>-->

                        <#--</li>-->

                        <#--<li><a href="#">账号列表</a></li>-->

                    <#--</ul>-->

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <div class="row-fluid">

                <div class="span12">

                <#--搜索框-->
                    <#if deptIdList??>
                        <form action="/customer/findAllCustomerByDeptNo" method="get">

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left">

                                    <label class="control-label">账号类型</label>

                                    <div class="controls">

                                        <label class="checkbox">

                                            <input type="checkbox" <#if customerTypeIdArray??><#list customerTypeIdArray as customerTypeId><#if customerTypeId=="1">checked="checked"</#if></#list></#if> id="customerTypeId" name="customerTypeId" value="1">普通账号

                                        </label>

                                        <label class="checkbox">

                                            <input type="checkbox" <#if customerTypeIdArray??><#list customerTypeIdArray as customerTypeId><#if customerTypeId=="2">checked="checked"</#if></#list></#if> id="customerTypeId" name="customerTypeId" value="2">技术调试账号

                                        </label>

                                    </div>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">&nbsp;&nbsp;</label>

                                    <div class="controls">

                                        <div class="input-append">

                                            <input class="m-wrap" <#if content??>value="${content}" </#if> type="text" name="content" placeholder="请输入账号">

                                            <button class="btn black" type="submit">搜索</button>

                                        </div>

                                    </div>

                                </div>

                            </div>

                        </form>
                    <#else >
                        <form action="/customer/findAllCustomer" method="get">

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left">

                                    <label class="control-label">账号类型</label>

                                    <div class="controls">

                                        <label class="checkbox">

                                            <input type="checkbox" <#if customerTypeIdArray??><#list customerTypeIdArray as customerTypeId><#if customerTypeId=="1">checked="checked"</#if></#list></#if> id="customerTypeId" name="customerTypeId" value="1">普通账号

                                        </label>

                                        <label class="checkbox">

                                            <input type="checkbox" <#if customerTypeIdArray??><#list customerTypeIdArray as customerTypeId><#if customerTypeId=="2">checked="checked"</#if></#list></#if> id="customerTypeId" name="customerTypeId" value="2">技术调试账号

                                        </label>

                                    </div>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">&nbsp;&nbsp;</label>

                                    <div class="controls">

                                        <div class="input-append">

                                            <input class="m-wrap" <#if content??>value="${content}" </#if> type="text" name="content" placeholder="请输入账号">

                                            <button class="btn black" type="submit">搜索</button>

                                        </div>

                                    </div>

                                </div>

                            </div>

                        </form>
                    </#if>


                <#--表单-->
                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-cogs"></i></div>

                            <div class="tools">

                            <#--<a href="javascript:;" class="collapse"></a>-->

                                <#--<a href="#portlet-config" data-toggle="modal" class="config"></a>-->

                                <#--<a href="javascript:;" class="reload"></a>-->

                                <#--<a href="javascript:;" class="remove"></a>-->

                            </div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                <tr>

                                    <th style="text-align: center; width: 15%">账号类型</th>
                                    <th style="text-align: center; width: 20%">公司名称</th>
                                    <th style="text-align: center; width: 20%">账号</th>
                                    <th style="text-align: center; width: 15%">余额（单位/元）</th>
                                    <th style="text-align: center; width: 10%">状态</th>
                                    <th style="text-align: center; width: 20%">操作</th>
                                </tr>
                                </thead>
                                <tbody>

                                    <#if customerList??>
                                        <#list customerList as customer>
                                        <tr>
                                            <td>${customer.customerType.name}</td>
                                            <td>${customer.company.name}</td>
                                            <td><a href="/customer/findCustomerDetailInfo/${customer.authId}"><strong>${customer.authId}</strong></a></td>
                                            <td>${customer.balance/100.0}</td>
                                            <#if (customer.status)<0>
                                                <td style="color: red;">${customer.customerStatus.name}</td>
                                            <#else >
                                                <td>${customer.customerStatus.name}</td>
                                            </#if>

                                            <td style="text-align: center">
                                                <p>
                                                    <a href="/customerBalance/customerBalanceChangeView/${customer.authId}?reasonId=1" class="btn" id="gritter-light">充值</a>

                                                    <a href="/customerBalance/findAllRechargeCustomerBalanceLogByCustomerId/${customer.id}?reasonId=1" class="btn black" id="gritter-light">充值记录</a><br/>

                                                    <a href="/customerBalance/customerBalanceChangeView/${customer.authId}?reasonId=-1" class="btn" id="gritter-light">扣费</a>

                                                    <a href="/customerBalance/findAllCustomerBalanceLogByCustomerId/${customer.id}?reasonId=-1" class="btn black" id="gritter-light">消费记录</a>
                                                </p>
                                            </td>

                                        </tr>
                                        </#list>
                                    </#if>

                                </tbody>

                            </table>
                            <#if deptIdList??>

                                <#if (count>0)>
                                    <div class="row-fluid">

                                        <div class="span6">

                                            <div class="dataTables_info" id="sample_1_info">当前显示第 ${pageSize} 页 共 ${totlePage} 页</div>

                                        </div>

                                        <#if (totlePage>0)>
                                            <div class="span6" align="right">
                                                <div class="dataTables_paginate paging_bootstrap pagination">
                                                    <ul>
                                                        <#if (pageSize>1)>
                                                            <li class="next"><a href="/customer/findAllCustomerByDeptNo?pageSize=1<#if content??>&content=${content}</#if><#if customerTypeIdArray??><#list customerTypeIdArray as customerTypeId>&customerTypeId=${customerTypeId}</#list></#if>"><span class="hidden-480">首页</span></a></li>
                                                            <li class="next"><a href="/customer/findAllCustomerByDeptNo?pageSize=${pageSize-1}<#if content??>&content=${content}</#if><#if customerTypeIdArray??><#list customerTypeIdArray as customerTypeId>&customerTypeId=${customerTypeId}</#list></#if>"><span class="hidden-480">上一页</span></a></li>
                                                        </#if>
                                                        <#if (pageSize<totlePage)>
                                                            <li class="next"><a href="/customer/findAllCustomerByDeptNo?pageSize=${pageSize+1}<#if content??>&content=${content}</#if><#if customerTypeIdArray??><#list customerTypeIdArray as customerTypeId>&customerTypeId=${customerTypeId}</#list></#if>"><span class="hidden-480">下一页</span></a></li>
                                                            <li class="next"><a href="/customer/findAllCustomerByDeptNo?pageSize=${totlePage}<#if content??>&content=${content}</#if><#if customerTypeIdArray??><#list customerTypeIdArray as customerTypeId>&customerTypeId=${customerTypeId}</#list></#if>"><span class="hidden-480">尾页</span></a></li>
                                                        </#if>
                                                    </ul>
                                                </div>
                                            </div>
                                        </#if>

                                    </div>
                                </#if>
                            <#else >
                                <#if (count>0)>
                                    <div class="row-fluid">

                                        <div class="span6">

                                            <div class="dataTables_info" id="sample_1_info">当前显示第 ${pageSize} 页 共 ${totlePage} 页</div>

                                        </div>

                                        <#if (totlePage>0)>
                                            <div class="span6" align="right">
                                                <div class="dataTables_paginate paging_bootstrap pagination">
                                                    <ul>
                                                        <#if (pageSize>1)>
                                                            <li class="next"><a href="/customer/findAllCustomer?pageSize=1<#if content??>&content=${content}</#if><#if customerTypeIdArray??><#list customerTypeIdArray as customerTypeId>&customerTypeId=${customerTypeId}</#list></#if>"><span class="hidden-480">首页</span></a></li>
                                                            <li class="next"><a href="/customer/findAllCustomer?pageSize=${pageSize-1}<#if content??>&content=${content}</#if><#if customerTypeIdArray??><#list customerTypeIdArray as customerTypeId>&customerTypeId=${customerTypeId}</#list></#if>"><span class="hidden-480">上一页</span></a></li>
                                                        </#if>
                                                        <#if (pageSize<totlePage)>
                                                            <li class="next"><a href="/customer/findAllCustomer?pageSize=${pageSize+1}<#if content??>&content=${content}</#if><#if customerTypeIdArray??><#list customerTypeIdArray as customerTypeId>&customerTypeId=${customerTypeId}</#list></#if>"><span class="hidden-480">下一页</span></a></li>
                                                            <li class="next"><a href="/customer/findAllCustomer?pageSize=${totlePage}<#if content??>&content=${content}</#if><#if customerTypeIdArray??><#list customerTypeIdArray as customerTypeId>&customerTypeId=${customerTypeId}</#list></#if>"><span class="hidden-480">尾页</span></a></li>
                                                        </#if>
                                                    </ul>
                                                </div>
                                            </div>
                                        </#if>

                                    </div>
                                </#if>
                            </#if>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>

    <#elseif section = "footer">

    </#if>
<script>
    $(document).ready(function() {
        $('#customerBalance').addClass('active');

        $('#customerListBalanceLog').addClass('active');

        $('#customerBalanceSelect').addClass('selected');

        $('#customerBalanceArrow').addClass('arrow open');
    });
</script>
</@layout>
