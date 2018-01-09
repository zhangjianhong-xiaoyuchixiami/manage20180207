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

                    <div class="portlet box grey">

                        <div class="portlet-body form">

                            <form id="submit_form" action="/data/mobile/valid/result" class="horizontal-form" method="get">

                                <h4 class="form-section"><strong>此页面可核验：手机三要素、手机在网时长、手机在网状态、手机姓名二要素、身份证姓名二要素、个人电子图像信息查询</strong></h4>

                                <div class="row-fluid">

                                    <div class="span12 ">

                                        <div class="row-fluid">

                                            <div class="control-group">

                                                <label class="control-label">产品类型</label>

                                                <div class="controls">

                                                    <select  class="m-wrap span6" id="tid" name="tid">
                                                        <option value=""></option>
                                                        <option value="/mobile/verify/3f">手机三要素</option>
                                                        <option value="/mobile/query/duration">手机在网时长</option>
                                                        <option value="/mobile/query/status">手机在网状态</option>
                                                        <option value="/mobile/verify/2f-name">手机姓名二要素</option>
                                                        <option value="/id/verify/2f">身份证姓名二要素</option>
                                                        <option value="/id/query/photo">个人电子图像信息查询</option>
                                                    </select>

                                                </div>

                                            </div>

                                        </div>

                                        <h4 class="form-section"></h4>

                                    <#--手机号-->
                                        <div class="row-fluid">

                                            <div class="span3 ">

                                                <div class="control-group">

                                                    <label class="control-label" for="firstName">手机号</label>

                                                    <div class="controls">

                                                        <input type="text" id="mobile" name="mobile" class="medium m-wrap" placeholder="请输入手机号">

                                                        <span id="mobile_msg" class="help-block"></span>

                                                    </div>

                                                </div>

                                            </div>

                                            <div class="span3 ">

                                                <div class="control-group">

                                                    <label class="control-label" for="firstName">指定接口</label>

                                                    <div class="controls">

                                                        <select  class="medium m-wrap" id="aid" name="aid">

                                                            <option value=""></option>

                                                        </select>

                                                    </div>

                                                </div>

                                            </div>

                                        </div>

                                    <#--姓名-->
                                        <div class="row-fluid">

                                            <div class="span3 ">

                                                <div class="control-group">

                                                    <label class="control-label" for="firstName">姓名</label>

                                                    <div class="controls">

                                                        <input type="text" id="realName" name="realName" class="medium m-wrap" placeholder="请输入姓名">

                                                    </div>

                                                </div>

                                            </div>

                                            <div class="span3 ">

                                                <div class="control-group">

                                                    <label class="control-label" for="firstName">是否允许调缓存</label>

                                                    <div class="controls">

                                                        <select  class="medium m-wrap" id="omit" name="omit">

                                                            <option value="true">不允许</option>

                                                            <option value="false">允许</option>

                                                        </select>

                                                    </div>

                                                </div>

                                            </div>

                                        </div>

                                    <#--身份证号-->
                                        <div class="row-fluid">

                                            <div class="span3 ">

                                                <div class="control-group">

                                                    <label class="control-label" for="firstName">身份证号</label>

                                                    <div class="controls">

                                                        <input type="text" id="idNo" name="idNo" class="medium m-wrap" placeholder="请输入身份证号">

                                                        <span id="idNo_msg" class="help-block"></span>

                                                    </div>

                                                </div>

                                            </div>

                                            <div class="span3 ">

                                                <div class="control-group">

                                                    <label class="control-label" for="firstName">是否允许存缓存</label>

                                                    <div class="controls">

                                                        <select  class="medium m-wrap" id="skip" name="skip">

                                                            <option value="true">不允许</option>

                                                            <option value="false">允许</option>

                                                        </select>

                                                    </div>

                                                </div>

                                            </div>

                                        </div>

                                    </div>

                                </div>

                                <div class="form-actions">

                                    <button type="button" id="valid_submit" class="btn black"><i class="icon-ok"></i>提交</button>

                                    <button type="reset" class="btn">重置</button>

                                </div>

                            </form>

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

    <script src="/js/myjs/valid/data_mobile_valid.js?v=${ver}"></script>

    <script>
        jQuery(document).ready(function() {
            DataMobileValid.init();
        });
    </script>

    </#if>

</@layout>