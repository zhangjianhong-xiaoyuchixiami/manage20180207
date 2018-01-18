
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

                            <#--<h4 class="form-section"><strong>此页面可核验：手机三要素、手机在网时长、手机在网状态、手机姓名二要素、身份证姓名二要素、个人电子图像信息查询</strong></h4>-->

                                <div class="row-fluid">

                                    <div class="span12 ">

                                    <#--文本框-->
                                        <div class="row-fluid">
                                            <div class="control-group">
                                            <#--<label class="control-label">核验内容</label>-->
                                                <div class="controls">
                                                    <textarea id="valid_content" name="valid_content" class="large_1111 m-wrap" rows="6" placeholder="请输入核验内容，如果数据格式不一致，请用分隔符 “|” 分隔"></textarea>
                                                </div>
                                            </div>
                                        </div>

                                    <#--信息输入框-->
                                        <div class="row-fluid" id="valid_message_group">

                                        <#--标题-->
                                            <div id="group_title">
                                            <#--手机号-->
                                                <div class="span3 ">
                                                    <div class="controls">
                                                        <label class="control-label">手机号</label>
                                                    </div>
                                                </div>
                                            <#--身份证号-->
                                                <div class="span3 ">
                                                    <div class="controls">
                                                        <label class="control-label">身份证号</label>
                                                    </div>
                                                </div>
                                            <#--姓名-->
                                                <div class="span3 ">
                                                    <div class="controls">
                                                        <label class="control-label">姓名</label>
                                                    </div>
                                                </div>
                                            <#--银行卡号-->
                                                <div class="span3 ">
                                                    <div class="controls">
                                                        <label class="control-label">银行卡号</label>
                                                    </div>
                                                </div>
                                            </div>

                                        <#--显示第一列文本框-->
                                            <div id="group_first">
                                            <#--手机号-->
                                                <div class="span3 ">
                                                    <div class="controls">
                                                        <input type="text" id="mobile" name="mobile" class="medium m-wrap" placeholder="手机号">
                                                        <span id="mobile_msg" class="help-block"></span>
                                                    </div>
                                                </div>
                                            <#--身份证号-->
                                                <div class="span3 ">
                                                    <div class="controls">
                                                        <input type="text" id="idNo" name="idNo" class="medium m-wrap" placeholder="身份证号">
                                                        <span id="mobile_msg" class="help-block"></span>
                                                    </div>
                                                </div>
                                            <#--姓名-->
                                                <div class="span3 ">
                                                    <div class="controls">
                                                        <input type="text" id="realName" name="realName" class="medium m-wrap" placeholder="姓名">
                                                        <span id="mobile_msg" class="help-block"></span>
                                                    </div>
                                                </div>
                                            <#--银行卡号-->
                                                <div class="span3 ">
                                                    <div class="controls">
                                                        <input type="text" id="bankcard" name="bankcard" class="medium m-wrap" placeholder="银行卡号">
                                                        <span id="mobile_msg" class="help-block"></span>
                                                    </div>
                                                </div>
                                            </div>

                                        <#--显示其余列文本框-->
                                            <div id="group_body">

                                            </div>

                                        </div>

                                    <#--缓存、域名-->
                                        <div class="row-fluid" id="valid_omit_skip_address_group">
                                        <#--&lt;#&ndash;调缓存&ndash;&gt;-->
                                            <#--<div class="span4 ">-->
                                                <#--<div class="control-group">-->
                                                    <#--<label class="control-label">是否允许调缓存</label>-->
                                                    <#--<div class="controls">-->
                                                        <#--<label class="checkbox">-->
                                                            <#--<input type="radio" name="omit" checked="checked" value="true" /> 不允许-->
                                                        <#--</label>-->
                                                        <#--<label class="checkbox">-->
                                                            <#--<input type="radio" name="omit" value="false" /> 允许-->
                                                        <#--</label>-->
                                                    <#--</div>-->
                                                <#--</div>-->
                                            <#--</div>-->
                                        <#--&lt;#&ndash;存缓存&ndash;&gt;-->
                                            <#--<div class="span4 ">-->
                                                <#--<div class="control-group">-->
                                                    <#--<label class="control-label">是否允许存缓存</label>-->
                                                    <#--<div class="controls">-->
                                                        <#--<label class="checkbox">-->
                                                            <#--<input type="radio" name="skip" checked="checked" value="true" /> 不允许-->
                                                        <#--</label>-->
                                                        <#--<label class="checkbox">-->
                                                            <#--<input type="radio" name="skip" value="false" /> 允许-->
                                                        <#--</label>-->
                                                    <#--</div>-->
                                                <#--</div>-->
                                            <#--</div>-->
                                        <#--&lt;#&ndash;域名&ndash;&gt;-->
                                            <#--<div class="span4 ">-->
                                                <#--<div class="control-group">-->
                                                    <#--<label class="control-label">域名</label>-->
                                                    <#--<div class="controls">-->
                                                        <#--<label class="checkbox">-->
                                                            <#--<input type="radio" name="address" checked="checked" value="https://api.qydata.org:9000" /> api.qydata.org-->
                                                        <#--</label>-->
                                                        <#--<label class="checkbox">-->
                                                            <#--<input type="radio" name="address" value="https://apitest.qydata.org:9000" /> apitest.qydata.org-->
                                                        <#--</label>-->
                                                    <#--</div>-->
                                                <#--</div>-->
                                            <#--</div>-->
                                        </div>

                                    <#--分隔符-->
                                        <h4 class="form-section"></h4>

                                    <#--类型、指定源-->
                                        <div class="row-fluid">

                                            <#--<div style="width: 2%">-->

                                            <#--</div>-->

                                            <#--核验类型-->
                                            <div class="span4" id="valid_tid_group">

                                                <#--<div class="control-group">-->

                                                    <#--<label class="control-label">核验类型</label>-->

                                                    <#--<div id="tid_group" class="controls">-->
                                                        <#--<label class="checkbox line">-->
                                                            <#--<input type="checkbox" id="tid" name="tid" value="1" /> 手机三要素-->
                                                        <#--</label>-->
                                                        <#--<label class="checkbox line">-->
                                                            <#--<input type="checkbox" id="tid" name="tid" value="21" /> 手机三要素@高质量-->
                                                        <#--</label>-->
                                                        <#--<label class="checkbox line">-->
                                                            <#--<input type="checkbox" id="tid" name="tid" value="4" /> 手机在网时长-->
                                                        <#--</label>-->
                                                        <#--<label class="checkbox line">-->
                                                            <#--<input type="checkbox" id="tid" name="tid" value="5" /> 手机在网状态-->
                                                        <#--</label>-->
                                                        <#--<label class="checkbox line">-->
                                                            <#--<input type="checkbox" id="tid" name="tid" value="20" /> 手机姓名二要素-->
                                                        <#--</label>-->
                                                        <#--<label class="checkbox line">-->
                                                            <#--<input type="checkbox" id="tid" name="tid" value="19" /> 手机身份证二要素-->
                                                        <#--</label>-->
                                                        <#--<label class="checkbox line">-->
                                                            <#--<input type="checkbox" id="tid" name="tid" value="2" /> 身份证姓名二要素-->
                                                        <#--</label>-->
                                                        <#--<label class="checkbox line">-->
                                                            <#--<input type="checkbox" id="tid" name="tid" value="15" /> 身份证姓名二要素@高质量-->
                                                        <#--</label>-->
                                                        <#--<label class="checkbox line">-->
                                                            <#--<input type="checkbox" id="tid" name="tid" value="18" /> 个人电子图像信息查询-->
                                                        <#--</label>-->
                                                        <#--<label class="checkbox line">-->
                                                            <#--<input type="checkbox" id="tid" name="tid" value="13" /> 银行卡三要素-->
                                                        <#--</label>-->
                                                        <#--<label class="checkbox line">-->
                                                            <#--<input type="checkbox" id="tid" name="tid" value="3" /> 银行卡四要素-->
                                                        <#--</label>-->
                                                    <#--</div>-->

                                                <#--</div>-->

                                            </div>

                                        <#--指定源 1-->
                                            <div class="span4 " id="aid_group_2">

                                            </div>

                                        <#--指定源 2-->
                                            <div class="span4 " id="aid_group">

                                            </div>
                                        </div>

                                    </div>

                                </div>

                            <#--表达提交-->
                                <div class="form-actions">

                                    <button type="button" id="valid_submit" class="btn black"><i class="icon-ok"></i>提交</button>

                                    <button type="reset" class="btn">重置</button>

                                </div>

                            </form>

                        <#--分隔符-->
                            <h4 class="form-section"></h4>

                        <#--核验结果-->
                            <div id="result_body">

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

    <script src="/js/myjs/valid/data_mobile_valid.js?v=${ver}"></script>

    <script>
        jQuery(document).ready(function() {
            DataMobileValid.init();
        });




    </script>



    </#if>

</@layout>
