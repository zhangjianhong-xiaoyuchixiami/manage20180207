
<#include "../customer/layout.ftl">
<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid profile">

                <div class="span12">

                    <div class="tabbable tabbable-custom tabbable-full-width">

                        <div style="background: url('/image/welcomeback1.jpg') no-repeat; font-size: 36px; text-align: center; width: auto; height: 700px; border: 1px solid white">

                            <div style="margin-top: 120px; border: 1px solid white; color: darkgrey;">
                                千言数合欢迎您
                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>

    <#elseif section = "footer">

    </#if>
</@layout>