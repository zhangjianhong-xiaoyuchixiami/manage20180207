
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
                                            <select class="small m-wrap" id="tid" name="tid">
                                                <option value=""></option>
                                                <option value="">k</option>
                                                <option value="">reqId</option>
                                            </select>
                                        </div>
                                        <div class="pull-left head-search-bottom">
                                            <select class="medium m-wrap" id="cid" name="cid">
                                                <option value=""></option>
                                                <option value="">1</option>
                                                <option value="">1</option>
                                                <option value="">1</option>
                                            </select>
                                        </div>
                                        <div class="pull-left head-search-bottom">
                                            <input type="text" placeholder="请输入搜索内容" id="content" name="content" class="large m-wrap" />
                                        </div>
                                    </div>
                                    <button type="submit" id="search_submit" class="btn black">查找 &nbsp; <i class="m-icon-swapright m-icon-white"></i></button>
                                </div>
                        </form>
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
    <script>
        jQuery(document).ready(function() {
            SearchCustomerLogIndex.init();
        });
    </script>

    </#if>

</@layout>
