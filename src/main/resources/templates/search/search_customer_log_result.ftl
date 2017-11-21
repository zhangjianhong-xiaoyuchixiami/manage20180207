
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

                    <div style="margin-top: 10px;">
                        <form id="submit_form" class="form-search" action="/search/customer-log/result" method="get">
                            <div class="chat-form">
                                <div class="input-cont">
                                    <div class="pull-left head-search-bottom">
                                        <select class="small m-wrap" id="k_reqId" name="k_reqId">
                                            <option <#if k_reqId?? && k_reqId == "k">selected="selected"</#if> value="k">k</option>
                                            <option <#if k_reqId?? && k_reqId == "reqId">selected="selected"</#if> value="reqId">reqId</option>
                                        </select>
                                    </div>
                                    <div class="pull-left head-search-bottom">
                                        <select class="medium m-wrap" id="cid" name="cid">
                                            <option value=""></option>
                                            <#if companyList??>
                                                <#list companyList as company>
                                                    <option <#if cid?? && cid == company.id>selected="selected"</#if> value="${company.id}">${company.name}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </div>
                                    <div class="pull-left head-search-bottom">
                                        <input type="text" placeholder="请输入k或reqId" <#if content??> value="${content}" </#if> id="content" name="content" class="large m-wrap" />
                                    </div>
                                </div>
                                <button type="button" id="search_submit" class="btn black">查找 &nbsp; <i class="m-icon-swapright m-icon-white"></i></button>
                            </div>
                        </form>
                    </div>

                    <div class="portlet-body">

                        <table class="table table-striped table-hover" id="sample_1">
                            <thead>
                            <tr>
                                <th>客户名称</th>
                                <th>产品类型</th>
                                <th class="table-td-none">reqId</th>
                                <th class="table-td-none">k</th>
                                <th class="table-td-none">请求数据</th>
                                <th>核验结果</th>
                                <th class="table-td-none">响应结果</th>
                                <th>响应时长</th>
                                <th class="table-td-none">是否扣费</th>
                                <th>是否走缓存</th>
                                <th>调用上游</th>
                                <th>请求时间</th>
                                <th>照片</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if logList??>
                                <#list logList as log>
                                <tr>
                                    <td>${log.cname_pname_authId!"NULL"}</td>
                                    <td>${log.type_stid_name!"NULL"}</td>
                                    <td class="table-td-none">${log.reqId!"NULL"}</td>
                                    <td class="table-td-none">${log.k!"NULL"}</td>
                                    <td class="table-td-none">${log.reqContent!"NULL"}</td>
                                    <td>${log.result!""}</td>
                                    <td class="table-td-none">${log.respContent!"NULL"}</td>
                                    <td>${log.durName!'NULL'}</td>
                                    <td class="table-td-none">${log.isCost!"NULL"}</td>
                                    <td>${log.isCache!"NULL"}</td>
                                    <td>${log.vname_pname_aname!"NULL"}</td>
                                    <td>${log.createTime!"NULL"}</td>
                                    <td><img style="width: 78px; height: 92px" src= "data:image/jpeg;base64,${log.photo!''}" alt="无照片" ></td>
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

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

        <@puj.publicJs></@puj.publicJs>

    <script src="/js/myjs/search/search_customer_log_index.js?v=${ver}"></script>
    <script src="/js/myjs/search/search_customer_log_result.js?v=${ver}"></script>
    <script>
        jQuery(document).ready(function() {
            SearchCustomerLogIndex.init();
            SearchCustomerLogResult.init();
        });
    </script>

    </#if>

</@layout>
