
<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<#import "../publicPart/tools.ftl" as d>

<#import "../publicPart/publicJs.ftl" as puj>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content" xmlns="http://www.w3.org/1999/html">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">

                    <form action="/file-upload-service" method="post" enctype="multipart/form-data">
                        <input type="file" name="file" class="fileUpload" multiple>
                        <button id="px-submit" type="submit">上传</button>
                        <button id="px-clear" type="reset">取消</button>
                    </form>

                </div>

            </div>

        </div>

    </div>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

        <@puj.publicJs></@puj.publicJs>


    <script src="/js/upload/jquery.fileUploader.js"></script>

    <script src="/js/upload/jquery-ui-1.8.14.custom.min.js"></script>


    <script>

        jQuery(document).ready(function() {


            $('.fileUpload').fileUploader();

          /*  //文件上传
            $(function() {
                $("#upload").click(ajaxFileUpload);
            });

            function ajaxFileUpload() {
                var url = "/file-upload-service";
                $.ajaxFileUpload({
                    url : url,
                    fileElementId : 'file',
                    dataType : 'json',
                    type: 'post',
                    success : function(data,status) {
                        if (data == "exist") {
                            alert("该文件已经存在请勿重复上传");
                        }
                        if (data == "success") {
                            alert("文件上传成功");
                        }
                        if (data == "fail") {
                            alert("文件上传失败，请重新上传");
                        }
                    },
                    error : function() {

                    }
                });
            }
*/
        });

    </script>


    </#if>

</@layout>
