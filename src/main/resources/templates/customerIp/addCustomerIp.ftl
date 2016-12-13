
<#include "../customer/layout.ftl">

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">

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
                                                <@shiro.hasRole name="backAdmin">
                                                    <a href="/customer/findAllCustomer"><button type="button" class="btn">取消</button></a>
                                                </@shiro.hasRole>
                                                <@shiro.hasRole name="sell">
                                                    <a href="/customer/findAllCustomerByDeptNo"><button type="button" class="btn">取消</button></a>
                                                </@shiro.hasRole>
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