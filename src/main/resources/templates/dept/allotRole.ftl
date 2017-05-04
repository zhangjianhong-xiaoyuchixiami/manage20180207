
<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

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

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-cogs"></i>Table</div>

                            <div class="tools">

                                <a href="javascript:;" class="collapse"></a>

                                <a href="#portlet-config" data-toggle="modal" class="config"></a>

                                <a href="javascript:;" class="reload"></a>

                                <a href="javascript:;" class="remove"></a>

                            </div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="btn-group">

                                <button class="btn black" id="allotRoleSave">

                                    Save <i class="m-icon-swapright m-icon-white"></i>

                                </button>

                            </div>

                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th style="text-align: center; width: 100px" >角色选择</th>
                                    <th>角色编号</th>
                                    <th>角色名称</th>
                                    <th style="display: none">用户Id</th>
                                </tr>
                                </thead>
                                <tbody>

                                    <#if roleList??>
                                        <#list roleList as role>

                                        <tr>
                                            <td>
                                                <input type="checkbox" name="roleId"  <#list userRoleList as userRoleIdList>
                                                       <#if userRoleIdList.roleId==role.id>checked="checked"</#if>
                                                </#list> value="${role.id}" />
                                            </td>
                                            <td>${role.id}</td>
                                            <td>${role.name}</td>
                                            <td style="display: none"><input type="text" name="userId" value="${userId}" /></td>
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

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

        <@puj.publicJs></@puj.publicJs>

    <script type="text/javascript" src="/js/myjs/allotrole.js"></script>

    <script>

        $(document).ready(function(){

            AllotRole.init();

        });

    </script>


    </#if>

</@layout>