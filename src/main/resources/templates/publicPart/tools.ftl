<#macro tools idName>
<div class="actions">

    <div class="btn-group">

        <a class="btn white" href="#" data-toggle="dropdown">

            工具

            <i class="icon-angle-down"></i>

        </a>

        <ul class="dropdown-menu pull-right">

        <#--<li><a href="#"><i class="icon-print"></i> 打印</a></li>-->

            <li>
                <a id="${idName}" href="javaScript:;" ><i class="icon-share icon-black"></i> 导出Excel</a>

            </li>

        <#--<li><a href="#"><i class="icon-ban-circle"></i> Ban</a></li>-->

        <#--<li class="divider"></li>-->

        <#--<li><a href="#"><i class="i"></i> Make admin</a></li>-->

        </ul>

    </div>

</div>
</#macro>