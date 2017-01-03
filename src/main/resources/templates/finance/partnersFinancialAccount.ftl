
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

                                    <label class="control-label">&nbsp;&nbsp;</label>

                                    <div class="controls">

                                        <div class="input-append">

                                            <input class="m-wrap" <#if content??>value="${content}" </#if> type="text" name="content" placeholder="请输入公司名称">

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

                                    <label class="control-label">&nbsp;&nbsp;</label>

                                    <div class="controls">

                                        <div class="input-append">

                                            <input class="m-wrap" <#if content??>value="${content}" </#if> type="text" name="content" placeholder="请输入公司名称">

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

                            <div class="clearfix">

                                <div class="btn-group">

                                    <a class="btn black" href="#form_modal1" data-toggle="modal">

                                        新增<i class="icon-plus"></i>

                                    </a>

                                </div>

                                <div id="form_modal1" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">

                                    <div class="modal-header">

                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                        <h3 id="myModalLabel1">请填写信息</h3>

                                    </div>

                                    <div class="modal-body">

                                        <form action="#" class="form-horizontal">

                                            <div class="control-group">

                                                <label class="control-label">请输入公司名称<span class="required">*</span></label>

                                                <div class="controls">

                                                    <input type="text" id="amount" name="amount" <#if amount??>value="${amount}"</#if> class="m-wrap medium">

                                                    <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                </div>

                                            </div>

                                        </form>

                                    </div>

                                    <div class="modal-footer">

                                        <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                        <button class="btn green btn-primary" data-dismiss="modal">提交</button>

                                    </div>

                                </div>

                            </div>

                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_3">

                                <thead>

                                <tr>
                                    <th style="text-align: center; width: 15%">公司名称</th>
                                    <th>周收入总额（单位：元）</th>
                                    <th>周支出总额（单位：元）</th>
                                    <th>月收入总额（单位：元）</th>
                                    <th>月支出总额（单位：元）</th>
                                    <th>收入总额（单位：元）</th>
                                    <th>支出总额（单位：元）</th>
                                    <th style="text-align: center; width: 15%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td data-title="公司名称">gmgc</td>
                                    <td data-title="周收入总额">23</td>
                                    <td data-title="周支出总额">34</td>
                                    <td data-title="月收入总额">56</td>
                                    <td data-title="月支出总额">67</td>
                                    <td data-title="收入总额">890</td>
                                    <td data-title="支出总额">567</td>
                                    <td data-title="操作" style="text-align: center">
                                        <div>
                                            <a href="#form_modal2"  data-toggle="modal">付款</a>|

                                            <div id="form_modal2" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">

                                                <div class="modal-header">

                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                                    <h3 id="myModalLabel2">请填写信息</h3>

                                                </div>

                                                <div class="modal-body">

                                                    <form action="#" class="form-horizontal">

                                                        <div class="control-group"></div>

                                                        <div class="control-group"></div>

                                                        <#if msg??>

                                                            <div class="alert alert-error show">

                                                                <button class="close" data-dismiss="alert"></button>

                                                            ${msg}

                                                            </div>

                                                        </#if>
                                                        <div class="control-group" style="display: none;">

                                                            <label class="control-label">公司id<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="authId" name="authId" class="m-wrap medium">

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="amount" name="amount" <#if amount??>value="${amount}"</#if> placeholder="（单位/元）" class="m-wrap medium">

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                                <span class="help-block">只能输入数字类型并且金额大于0</span>

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">备&nbsp;注<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <textarea class="medium m-wrap" rows="3"></textarea>

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                            </div>

                                                        </div>

                                                    </form>

                                                </div>

                                                <div class="modal-footer">

                                                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                                    <button class="btn green btn-primary" data-dismiss="modal">提交</button>

                                                </div>

                                            </div>

                                            <a href="#form_modal5"  data-toggle="modal">收款</a>|

                                            <div id="form_modal5" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel5" aria-hidden="true">

                                                <div class="modal-header">

                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                                    <h3 id="myModalLabel5">请填写信息</h3>

                                                </div>

                                                <div class="modal-body">

                                                    <form action="#" class="form-horizontal">

                                                        <div class="control-group"></div>

                                                        <div class="control-group"></div>

                                                        <#if msg??>

                                                            <div class="alert alert-error show">

                                                                <button class="close" data-dismiss="alert"></button>

                                                            ${msg}

                                                            </div>

                                                        </#if>
                                                        <div class="control-group" style="display: none;">

                                                            <label class="control-label">公司id<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="authId" name="authId" class="m-wrap medium">

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="amount" name="amount" <#if amount??>value="${amount}"</#if> placeholder="（单位/元）" class="m-wrap medium">

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                                <span class="help-block">只能输入数字类型并且金额大于0</span>

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">备&nbsp;注<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <textarea class="medium m-wrap" rows="3"></textarea>

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                            </div>

                                                        </div>

                                                    </form>

                                                </div>

                                                <div class="modal-footer">

                                                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                                    <button class="btn green btn-primary" data-dismiss="modal">提交</button>

                                                </div>

                                            </div>

                                            <a href="/finance/find-all-partners-financial-account/receipt-and-paying-record">收支明细</a>
                                        </div>

                                    </td>
                                </tr>
                                <tr>
                                    <td data-title="公司名称">未来无限</td>
                                    <td data-title="周收入总额">345</td>
                                    <td data-title="周支出总额">178</td>
                                    <td data-title="月收入总额">234</td>
                                    <td data-title="月支出总额">432</td>
                                    <td data-title="收入总额">908</td>
                                    <td data-title="支出总额">657</td>
                                    <td data-title="操作" style="text-align: center">
                                        <div>
                                            <a href="#form_modal2"  data-toggle="modal">付款</a>|

                                            <div id="form_modal2" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">

                                                <div class="modal-header">

                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                                    <h3 id="myModalLabel2">请填写信息</h3>

                                                </div>

                                                <div class="modal-body">

                                                    <form action="#" class="form-horizontal">

                                                        <div class="control-group"></div>

                                                        <div class="control-group"></div>

                                                        <#if msg??>

                                                            <div class="alert alert-error show">

                                                                <button class="close" data-dismiss="alert"></button>

                                                            ${msg}

                                                            </div>

                                                        </#if>
                                                        <div class="control-group" style="display: none;">

                                                            <label class="control-label">公司id<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="authId" name="authId" class="m-wrap medium">

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="amount" name="amount" <#if amount??>value="${amount}"</#if> placeholder="（单位/元）" class="m-wrap medium">

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                                <span class="help-block">只能输入数字类型并且金额大于0</span>

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">备&nbsp;注<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <textarea class="medium m-wrap" rows="3"></textarea>

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                            </div>

                                                        </div>

                                                    </form>

                                                </div>

                                                <div class="modal-footer">

                                                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                                    <button class="btn green btn-primary" data-dismiss="modal">提交</button>

                                                </div>

                                            </div>

                                            <a href="#form_modal5"  data-toggle="modal">收款</a>|

                                            <div id="form_modal5" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel5" aria-hidden="true">

                                                <div class="modal-header">

                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                                    <h3 id="myModalLabel5">请填写信息</h3>

                                                </div>

                                                <div class="modal-body">

                                                    <form action="#" class="form-horizontal">

                                                        <div class="control-group"></div>

                                                        <div class="control-group"></div>

                                                        <#if msg??>

                                                            <div class="alert alert-error show">

                                                                <button class="close" data-dismiss="alert"></button>

                                                            ${msg}

                                                            </div>

                                                        </#if>
                                                        <div class="control-group" style="display: none;">

                                                            <label class="control-label">公司id<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="authId" name="authId" class="m-wrap medium">

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="amount" name="amount" <#if amount??>value="${amount}"</#if> placeholder="（单位/元）" class="m-wrap medium">

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                                <span class="help-block">只能输入数字类型并且金额大于0</span>

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">备&nbsp;注<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <textarea class="medium m-wrap" rows="3"></textarea>

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                            </div>

                                                        </div>

                                                    </form>

                                                </div>

                                                <div class="modal-footer">

                                                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                                    <button class="btn green btn-primary" data-dismiss="modal">提交</button>

                                                </div>

                                            </div>

                                            <a href="/customer-balance/find-all-partners-financial-account/receipt-and-paying-record">收支明细</a>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td data-title="公司名称">厦门掌讯</td>
                                    <td data-title="周收入总额">453</td>
                                    <td data-title="周支出总额">543</td>
                                    <td data-title="月收入总额">301</td>
                                    <td data-title="月支出总额">808</td>
                                    <td data-title="收入总额">760</td>
                                    <td data-title="支出总额">735</td>
                                    <td data-title="操作" style="text-align: center">
                                        <div>
                                            <a href="#form_modal2"  data-toggle="modal">付款</a>|

                                            <div id="form_modal2" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">

                                                <div class="modal-header">

                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                                    <h3 id="myModalLabel2">请填写信息</h3>

                                                </div>

                                                <div class="modal-body">

                                                    <form action="#" class="form-horizontal">

                                                        <div class="control-group"></div>

                                                        <div class="control-group"></div>

                                                        <#if msg??>

                                                            <div class="alert alert-error show">

                                                                <button class="close" data-dismiss="alert"></button>

                                                            ${msg}

                                                            </div>

                                                        </#if>
                                                        <div class="control-group" style="display: none;">

                                                            <label class="control-label">公司id<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="authId" name="authId" class="m-wrap medium">

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="amount" name="amount" <#if amount??>value="${amount}"</#if> placeholder="（单位/元）" class="m-wrap medium">

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                                <span class="help-block">只能输入数字类型并且金额大于0</span>

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">备&nbsp;注<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <textarea class="medium m-wrap" rows="3"></textarea>

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                            </div>

                                                        </div>

                                                    </form>

                                                </div>

                                                <div class="modal-footer">

                                                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                                    <button class="btn green btn-primary" data-dismiss="modal">提交</button>

                                                </div>

                                            </div>

                                            <a href="#form_modal5"  data-toggle="modal">收款</a>|

                                            <div id="form_modal5" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel5" aria-hidden="true">

                                                <div class="modal-header">

                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                                    <h3 id="myModalLabel5">请填写信息</h3>

                                                </div>

                                                <div class="modal-body">

                                                    <form action="#" class="form-horizontal">

                                                        <div class="control-group"></div>

                                                        <div class="control-group"></div>

                                                        <#if msg??>

                                                            <div class="alert alert-error show">

                                                                <button class="close" data-dismiss="alert"></button>

                                                            ${msg}

                                                            </div>

                                                        </#if>
                                                        <div class="control-group" style="display: none;">

                                                            <label class="control-label">公司id<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="authId" name="authId" class="m-wrap medium">

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="amount" name="amount" <#if amount??>value="${amount}"</#if> placeholder="（单位/元）" class="m-wrap medium">

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                                <span class="help-block">只能输入数字类型并且金额大于0</span>

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">备&nbsp;注<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <textarea class="medium m-wrap" rows="3"></textarea>

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                            </div>

                                                        </div>

                                                    </form>

                                                </div>

                                                <div class="modal-footer">

                                                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                                    <button class="btn green btn-primary" data-dismiss="modal">提交</button>

                                                </div>

                                            </div>

                                            <a href="/customer-balance/find-all-partners-financial-account/receipt-and-paying-record">收支明细</a>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td data-title="公司名称">南京234556</td>
                                    <td data-title="周收入总额">5546</td>
                                    <td data-title="周支出总额">445</td>
                                    <td data-title="月收入总额">34</td>
                                    <td data-title="月支出总额">6767</td>
                                    <td data-title="收入总额">908</td>
                                    <td data-title="支出总额">2456</td>
                                    <td data-title="操作" style="text-align: center">
                                        <div>
                                            <a href="#form_modal2"  data-toggle="modal">付款</a>|

                                            <div id="form_modal2" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">

                                                <div class="modal-header">

                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                                    <h3 id="myModalLabel2">请填写信息</h3>

                                                </div>

                                                <div class="modal-body">

                                                    <form action="#" class="form-horizontal">

                                                        <div class="control-group"></div>

                                                        <div class="control-group"></div>

                                                        <#if msg??>

                                                            <div class="alert alert-error show">

                                                                <button class="close" data-dismiss="alert"></button>

                                                            ${msg}

                                                            </div>

                                                        </#if>
                                                        <div class="control-group" style="display: none;">

                                                            <label class="control-label">公司id<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="authId" name="authId" class="m-wrap medium">

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="amount" name="amount" <#if amount??>value="${amount}"</#if> placeholder="（单位/元）" class="m-wrap medium">

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                                <span class="help-block">只能输入数字类型并且金额大于0</span>

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">备&nbsp;注<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <textarea class="medium m-wrap" rows="3"></textarea>

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                            </div>

                                                        </div>

                                                    </form>

                                                </div>

                                                <div class="modal-footer">

                                                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                                    <button class="btn green btn-primary" data-dismiss="modal">提交</button>

                                                </div>

                                            </div>

                                            <a href="#form_modal5"  data-toggle="modal">收款</a>|

                                            <div id="form_modal5" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel5" aria-hidden="true">

                                                <div class="modal-header">

                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                                    <h3 id="myModalLabel5">请填写信息</h3>

                                                </div>

                                                <div class="modal-body">

                                                    <form action="#" class="form-horizontal">

                                                        <div class="control-group"></div>

                                                        <div class="control-group"></div>

                                                        <#if msg??>

                                                            <div class="alert alert-error show">

                                                                <button class="close" data-dismiss="alert"></button>

                                                            ${msg}

                                                            </div>

                                                        </#if>
                                                        <div class="control-group" style="display: none;">

                                                            <label class="control-label">公司id<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="authId" name="authId" class="m-wrap medium">

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="amount" name="amount" <#if amount??>value="${amount}"</#if> placeholder="（单位/元）" class="m-wrap medium">

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                                <span class="help-block">只能输入数字类型并且金额大于0</span>

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">备&nbsp;注<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <textarea class="medium m-wrap" rows="3"></textarea>

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                            </div>

                                                        </div>

                                                    </form>

                                                </div>

                                                <div class="modal-footer">

                                                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                                    <button class="btn green btn-primary" data-dismiss="modal">提交</button>

                                                </div>

                                            </div>

                                            <a href="/customer-balance/find-all-partners-financial-account/receipt-and-paying-record">收支明细</a>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td data-title="公司名称">千眼数合</td>
                                    <td data-title="周收入总额">7890</td>
                                    <td data-title="周支出总额">4545</td>
                                    <td data-title="月收入总额">5890</td>
                                    <td data-title="月支出总额">8765</td>
                                    <td data-title="收入总额">4908</td>
                                    <td data-title="支出总额">7890</td>
                                    <td data-title="操作" style="text-align: center">
                                        <div>
                                            <a href="#form_modal2"  data-toggle="modal">付款</a>|

                                            <div id="form_modal2" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">

                                                <div class="modal-header">

                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                                    <h3 id="myModalLabel2">请填写信息</h3>

                                                </div>

                                                <div class="modal-body">

                                                    <form action="#" class="form-horizontal">

                                                        <div class="control-group"></div>

                                                        <div class="control-group"></div>

                                                        <#if msg??>

                                                            <div class="alert alert-error show">

                                                                <button class="close" data-dismiss="alert"></button>

                                                            ${msg}

                                                            </div>

                                                        </#if>
                                                        <div class="control-group" style="display: none;">

                                                            <label class="control-label">公司id<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="authId" name="authId" class="m-wrap medium">

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="amount" name="amount" <#if amount??>value="${amount}"</#if> placeholder="（单位/元）" class="m-wrap medium">

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                                <span class="help-block">只能输入数字类型并且金额大于0</span>

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">备&nbsp;注<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <textarea class="medium m-wrap" rows="3"></textarea>

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                            </div>

                                                        </div>

                                                    </form>

                                                </div>

                                                <div class="modal-footer">

                                                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                                    <button class="btn green btn-primary" data-dismiss="modal">提交</button>

                                                </div>

                                            </div>

                                            <a href="#form_modal5"  data-toggle="modal">收款</a>|

                                            <div id="form_modal5" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel5" aria-hidden="true">

                                                <div class="modal-header">

                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                                    <h3 id="myModalLabel5">请填写信息</h3>

                                                </div>

                                                <div class="modal-body">

                                                    <form action="#" class="form-horizontal">

                                                        <div class="control-group"></div>

                                                        <div class="control-group"></div>

                                                        <#if msg??>

                                                            <div class="alert alert-error show">

                                                                <button class="close" data-dismiss="alert"></button>

                                                            ${msg}

                                                            </div>

                                                        </#if>
                                                        <div class="control-group" style="display: none;">

                                                            <label class="control-label">公司id<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="authId" name="authId" class="m-wrap medium">

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <input type="text" id="amount" name="amount" <#if amount??>value="${amount}"</#if> placeholder="（单位/元）" class="m-wrap medium">

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                                <span class="help-block">只能输入数字类型并且金额大于0</span>

                                                            </div>

                                                        </div>

                                                        <div class="control-group">

                                                            <label class="control-label">备&nbsp;注<span class="required">*</span></label>

                                                            <div class="controls">

                                                                <textarea class="medium m-wrap" rows="3"></textarea>

                                                                <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                            </div>

                                                        </div>

                                                    </form>

                                                </div>

                                                <div class="modal-footer">

                                                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                                    <button class="btn green btn-primary" data-dismiss="modal">提交</button>

                                                </div>

                                            </div>

                                            <a href="/customer-balance/find-all-partners-financial-account/receipt-and-paying-record">收支明细</a>
                                        </div>
                                    </td>
                                </tr>

                                </tbody>

                            </table>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>

    <script type="text/javascript" src="/js/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/DT_bootstrap.js"></script>

    <script src="/js/table-managed.js"></script>

    <script>

        jQuery(document).ready(function() {
            TableManaged.init();
        });

    </script>

    <script>
        $(document).ready(function() {
            $('#customerBalance').addClass('active');

            $('#partnersFinancialAccount').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');

        });




    </script>

    <#elseif section = "footer">

    </#if>

</@layout>
