
<#include "../customer/layout.ftl">

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

        <div id="portlet-config" class="modal hide">

            <div class="modal-header">

                <button data-dismiss="modal" class="close" type="button"></button>

                <h3>portlet Settings</h3>

            </div>

            <div class="modal-body">

                <p>Here will be a configuration form</p>

            </div>

        </div>

        <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

        <!-- BEGIN PAGE CONTAINER-->

        <div class="container-fluid">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN STYLE CUSTOMIZER -->

                    <div class="color-panel hidden-phone">

                        <div class="color-mode-icons icon-color"></div>

                        <div class="color-mode-icons icon-color-close"></div>

                        <div class="color-mode">

                            <p>THEME COLOR</p>

                            <ul class="inline">

                                <li class="color-black current color-default" data-style="default"></li>

                                <li class="color-blue" data-style="blue"></li>

                                <li class="color-brown" data-style="brown"></li>

                                <li class="color-purple" data-style="purple"></li>

                                <li class="color-grey" data-style="grey"></li>

                                <li class="color-white color-light" data-style="light"></li>

                            </ul>

                            <label>

                                <span>Layout</span>

                                <select class="layout-option m-wrap small">

                                    <option value="fluid" selected>Fluid</option>

                                    <option value="boxed">Boxed</option>

                                </select>

                            </label>

                            <label>

                                <span>Header</span>

                                <select class="header-option m-wrap small">

                                    <option value="fixed" selected>Fixed</option>

                                    <option value="default">Default</option>

                                </select>

                            </label>

                            <label>

                                <span>Sidebar</span>

                                <select class="sidebar-option m-wrap small">

                                    <option value="fixed">Fixed</option>

                                    <option value="default" selected>Default</option>

                                </select>

                            </label>

                            <label>

                                <span>Footer</span>

                                <select class="footer-option m-wrap small">

                                    <option value="fixed">Fixed</option>

                                    <option value="default" selected>Default</option>

                                </select>

                            </label>

                        </div>

                    </div>

                    <!-- END BEGIN STYLE CUSTOMIZER -->

                    <h3 class="page-title">

                        Form Layouts

                        <small>form layouts and more</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="/view/successUrl">首页</a>

                            <span class="icon-angle-right"></span>

                        </li>

                        <li>

                            <a href="#">客户管理</a>

                            <span class="icon-angle-right"></span>

                        </li>

                        <li><a href="#">添加Ip</a></li>

                    </ul>

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <!-- BEGIN PAGE CONTENT-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN SAMPLE FORM PORTLET-->

                    <div class="portlet box blue tabbable">

                        <div class="portlet-title">

                            <div class="caption">

                                <i class="icon-reorder"></i>

                                <span class="hidden-480">General Layouts</span>

                            </div>

                        </div>

                        <div class="portlet-body form">

                            <div class="tabbable portlet-tabs">

                                <ul class="nav nav-tabs">

                                    <li><a href="#" data-toggle="tab">Inline</a></li>

                                    <li><a href="#" data-toggle="tab">Grid</a></li>

                                    <li><a href="#" data-toggle="tab">Default</a></li>

                                </ul>

                                <div class="tab-content">


                                    <div class="btn-group">

                                        <button class="btn blue" onclick="add()">

                                            Add New <i class="icon-plus"></i>

                                        </button>

                                    </div>

                                    <div class="tab-pane active" id="portlet_tab1">

                                        <!-- BEGIN FORM-->

                                        <form action="/customerIp/addCustomerIpAction" class="form-horizontal" method="post">

                                            <div class="controls">
                                                <#if msg??>
                                                    <span><h5><font color="red">${msg}</font></h5></span>
                                                <#else>
                                                    <span></span>
                                                </#if>
                                            </div>

                                            <div class="control-group">

                                                <div class="controls">

                                                    <input type="hidden" id="customerId" name="customerId" value="${id}" class="m-wrap medium" />

                                                </div>

                                            </div>

                                            <div id="addClone">

                                                <div class="control-group" id="clone">

                                                    <label class="control-label">请输入Ip段<span class="required">*</span></label>

                                                    <div class="controls">

                                                        <input type="text" id="beginIp" name="beginIp" placeholder="例如：192.168.111.12" class="m-wrap medium" />

                                                        ---

                                                        <input type="text" id="endIp" name="endIp" placeholder="例如：192.168.111.112" class="m-wrap medium" />


                                                        <span class="help-inline" id="beginIpMsg"><#if CustomerMessageBeginIp??><font color="red">${CustomerMessageBeginIp}</font></#if></span>

                                                        <span class="help-inline" id="endIpMsg"><#if CustomerMessageEndIp??><font color="red">${CustomerMessageEndIp}</font></#if></span>

                                                    </div>

                                                </div>

                                            </div>

                                            <div class="form-actions">
                                                <button type="submit" class="btn blue"><i class="icon-ok"></i> 提交</button>
                                                <button type="button" class="btn">取消</button>
                                            </div>

                                        </form>

                                        <!-- END FORM-->

                                    </div>

                                </div>

                            </div>

                        </div>

                    </div>

                    <!-- END SAMPLE FORM PORTLET-->

                </div>

            </div>

            <!-- END PAGE CONTENT-->

        </div>

        <!-- END PAGE CONTAINER-->

    </div>


    <#elseif section = "footer">

    </#if>
<script>
    function add(){
        var oTr = document.getElementById("clone");
        var newTr = oTr.cloneNode(true);
        document.getElementById("addClone").appendChild(newTr);
    }
</script>
<script>
    $(document).ready(function() {
        $('#customerManage').addClass('active');

        $('#customerList').addClass('active');

        $('#customerManageSelect').addClass('selected');

        $('#customerManageArrow').addClass('arrow open');

        $("#beginIp").focus(function () {
            $("#beginIpMsg").html("");
        });

        $("#endIp").focus(function () {
            $("#endIpMsg").html("");
        });
    });
</script>

</@layout>