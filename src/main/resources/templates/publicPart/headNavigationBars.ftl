<#macro navigationBars>
<div class="row-fluid">

    <div class="span12">

        <h3 class="page-title">

        <#--Title-->

        </h3>

        <ul class="breadcrumb text-right">

            <li>

                <a href="/" class="icon icon_i">首页</a>

            </li>

            <li>

                <a href="javaScript:;" class="icon icon_t" onclick=history.back()>后退</a>

            </li>

            <li>

                <a href="javaScript:;" class="icon icon_j" onclick=history.forward()>前进</a>

            </li>


            <li>

                <a href="javaScript:;" class="icon icon_n" onclick=history.go(0)>刷新</a>

            </li>

        </ul>

    </div>

</div>
</#macro>