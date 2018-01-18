/**
 * Created by jonhn on 2017/3/21.
 */
var DataMobileValid = function () {

    return {

        init: function () {

            $('#pro_valid').addClass('active');

            $('#pro_valid_select').addClass('selected');

            $('#pro_valid_arrow').addClass('arrow open');

            $('#data_mobile_valid').addClass('active');

            $(function () {

                //添加-缓存、域名html
                $('#valid_omit_skip_address_group').append(
                    "<div class='span4 '>" +
                    "<div class='control-group'>" +
                    "<label class='control-label'>是否允许调缓存</label>" +
                    "<div class='controls'>" +
                    "<label class='checkbox'>" +
                    "<input type='radio' name='omit' checked='checked' value='true' /> 不允许" +
                    "</label>" +
                    "<label class='checkbox'>" +
                    "<input type='radio' name='omit' value='false' /> 允许" +
                    "</label>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +

                    "<div class='span4 '>" +
                    "<div class='control-group'>" +
                    "<label class='control-label'>是否允许存缓存</label>" +
                    "<div class='controls'>" +
                    "<label class='checkbox'>" +
                    "<input type='radio' name='skip' checked='checked' value='true' /> 不允许" +
                    "</label>" +
                    "<label class='checkbox'>" +
                    "<input type='radio' name='skip' value='false' /> 允许" +
                    "</label>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +

                    "<div class='span4 '>" +
                    "<div class='control-group'>" +
                    "<label class='control-label'>域名</label>" +
                    "<div class='controls'>" +
                    "<label class='checkbox'>" +
                    "<input type='radio' name='address' checked='checked' value='https://api.qydata.org:9000' /> api.qydata.org" +
                    "</label>" +
                    "<label class='checkbox'>" +
                    "<input type='radio' name='address' value='https://apitest.qydata.org:9000' /> apitest.qydata.org" +
                    "</label>" +
                    "</div>" +
                    "</div>" +
                    "</div>"
                );

                //添加核验类型html
                $('#valid_tid_group').append(
                    "<div class='control-group'>" +
                    "<label class='control-label'>核验类型</label>" +
                    "<div id='tid_group' class='controls'>" +
                    "<label class='control-label'>" +
                    "<input type='checkbox' id='tid' name='tid' value='1' /> 手机三要素" +
                    "</label>" +
                    "<label class='control-label'>" +
                    "<input type='checkbox' id='tid' name='tid' value='21' /> 手机三要素@高质量" +
                    "</label>" +
                    "<label class='control-label'>" +
                    "<input type='checkbox' id='tid' name='tid' value='4' /> 手机在网时长" +
                    "</label>" +
                    "<label class='control-label'>" +
                    "<input type='checkbox' id='tid' name='tid' value='5' /> 手机在网状态" +
                    "</label>" +
                    "<label class='control-label'>" +
                    "<input type='checkbox' id='tid' name='tid' value='20' /> 手机姓名二要素" +
                    "</label>" +
                    "<label class='control-label'>" +
                    "<input type='checkbox' id='tid' name='tid' value='19' /> 手机身份证二要素" +
                    "</label>" +
                    "<label class='control-label'>" +
                    "<input type='checkbox' id='tid' name='tid' value='2' /> 身份证姓名二要素" +
                    "</label>" +
                    "<label class='control-label'>" +
                    "<input type='checkbox' id='tid' name='tid' value='15' /> 身份证姓名二要素@高质量" +
                    "</label>" +
                    "<label class='control-label'>" +
                    "<input type='checkbox' id='tid' name='tid' value='18' /> 个人电子图像信息查询" +
                    "</label>" +
                    "<label class='control-label'>" +
                    "<input type='checkbox' id='tid' name='tid' value='13' /> 银行卡三要素" +
                    "</label>" +
                    "<label class='control-label'>" +
                    "<input type='checkbox' id='tid' name='tid' value='3' /> 银行卡四要素" +
                    "</label>" +
                    "</div>" +
                    "</div>"
                );

            });

            //根据核验内容填充文本框
            $('#valid_content').on('blur',function () {
                var valid_content = $("textarea[name='valid_content']").val();
                $.ajax({
                    type: "post",
                    url: "/data/mobile/valid_content",
                    data: {"valid_content" : valid_content},
                    dataType: "json",
                    success: function (data) {
                        if (data != null && data.length > 0) {
                            $('#group_first').remove();
                            $('#group_body').empty();
                            for (var i = 0; i < data.length; i++) {
                                var mobile = data[i].mobile;
                                if(typeof(mobile)=="undefined"){
                                    mobile = "";
                                }
                                var idNo = data[i].idNo;
                                if(typeof(idNo)=="undefined"){
                                    idNo = "";
                                }
                                var name = data[i].name;
                                if(typeof(name)=="undefined"){
                                    name = "";
                                }
                                var bankcard = data[i].bankcard;
                                if(typeof(bankcard)=="undefined"){
                                    bankcard = "";
                                }
                                var myContent =
                                    "<div>" +
                                    "<div class='span3 '>" +
                                    "<div class='controls'>" +
                                    "<input type='text' id='mobile' name='mobile' value='" + mobile + "' class='medium m-wrap' placeholder='手机号'>" +
                                    "<span id='mobile_msg' class='help-block'></span>" +
                                    "</div>" +
                                    "</div>" +
                                    "<div class='span3 '>" +
                                    "<div class='controls'>" +
                                    "<input type='text' id='idNo' name='idNo' value='" + idNo + "' class='medium m-wrap' placeholder='身份证号'>" +
                                    "<span id='mobile_msg' class='help-block'></span>" +
                                    "</div>" +
                                    "</div>" +
                                    "<div class='span3 '>" +
                                    "<div class='controls'>" +
                                    "<input type='text' id='realName' name='realName' value='" + name + "' class='medium m-wrap' placeholder='姓名'>" +
                                    "<span id='mobile_msg' class='help-block'></span>" +
                                    "</div>" +
                                    "</div>" +
                                    "<div class='span3 '>" +
                                    "<div class='controls'>" +
                                    "<input type='text' id='bankcard' name='bankcard' value='" + bankcard + "' class='medium m-wrap' placeholder='银行卡号'>" +
                                    "<span id='mobile_msg' class='help-block'></span>" +
                                    "</div>" +
                                    "</div>" +
                                    "</div>";
                                $("#group_body").append(myContent);
                            }
                        }
                    }
                });
            });

            //根据产品类型查找数据源
            $('#valid_tid_group').on('change',function () {

                var tid = [];//定义一个数组
                $('input[name="tid"]:checked').each(function(){
                    tid.push($.trim($(this).val()));
                });

                $.ajax({
                    type: "post",
                    url: "/data/mobile/aid",
                    data: {"tid" : tid},
                    dataType: "json",
                    success: function (data) {
                        //指定产品
                        var aid_checked = [];//定义一个数组
                        $('input[name="point_aid"]:checked').each(function(){
                            aid_checked.push($.trim($(this).val()));
                        });

                        $("#aid_group").empty();
                        $("#aid_group_2").empty();

                        if (data != null && data.length > 0) {
                            var myTitle =
                                "<div class='control-group'>" +
                                "<label class='control-label'>&nbsp;&nbsp;</label>" +
                                "<div class='controls' id='aid_group_1'>" +
                                "</div>" +
                                "</div>";
                            $("#aid_group").append(myTitle);
                            var myTitle2 =
                                "<div class='control-group'>" +
                                "<label class='control-label'>指定数据源</label>" +
                                "<div class='controls' id='aid_group_2'>" +
                                "</div>" +
                                "</div>";
                            $("#aid_group_2").append(myTitle2);
                            for (var i = 0; i < data.length; i++) {
                                var isAid = null;
                                //console.log("aid:" + aid_checked);
                                if (aid_checked != null){
                                    for (var j = 0; j < aid_checked.length; j++) {
                                        //   console.log(data[i].aid + "----" + aid_checked[j]);
                                        if (data[i].aid == aid_checked[j]){
                                            isAid = "checked='checked'";
                                            break;
                                        }
                                    }
                                }
                                //   console.log("isAid:" + isAid);
                                var aid = data[i].aid;
                                if (typeof(aid) == "undefined") {
                                    aid = "";
                                }
                                var name = data[i].name;
                                if (typeof(name) == "undefined") {
                                    name = "";
                                }
                                var myContent =
                                    "<label class='checkbox line'>" +
                                    "<input id='point_aid' "+ isAid +" name='point_aid' type='checkbox' value='" + aid + "' />" + name +
                                    "</label>";
                                if (i < (data.length)/2){
                                    $("#aid_group_2").append(myContent);
                                }else {
                                    $("#aid_group_1").append(myContent);
                                }
                            }
                        }
                    }
                });
            });

            /*表单提交*/
            $('#valid_submit').on("click",function () {
                //产品类型
                var tid = [];//定义一个数组
                $('input[name="tid"]:checked').each(function(){
                    tid.push($.trim($(this).val()));
                });
                if (tid == null || tid == ""){
                    swal({
                        title: "操作提示",
                        text: "请选择要核验的类型！",
                        type: "info",
                        confirmButtonText: "确定"
                    });
                    return
                }else {
                    //指定产品
                    var aid = [];//定义一个数组
                    $('input[name="point_aid"]:checked').each(function(){
                        aid.push($.trim($(this).val()));
                    });
                    //手机号
                    var mobile = [];//定义一个数组
                    $('input[name="mobile"]').each(function(){
                        mobile.push($.trim($(this).val()));
                    });
                    //身份证号
                    var idNo = [];//定义一个数组
                    $('input[name="idNo"]').each(function(){
                        idNo.push($.trim($(this).val()));
                    });
                    //姓名
                    var realName = [];//定义一个数组
                    $('input[name="realName"]').each(function(){
                        realName.push($.trim($(this).val()));
                    });
                    //银行卡号
                    var bankcard = [];//定义一个数组
                    $('input[name="bankcard"]').each(function(){
                        bankcard.push($.trim($(this).val()));
                    });
                    //调换存
                    var omit = $('input[name="omit"]:checked').val();
                    //存缓存
                    var skip = $('input[name="skip"]:checked').val();
                    //域名
                    var address = $('input[name="address"]:checked').val();
                    console.log(tid);
                    console.log(aid);
                    console.log(mobile);
                    console.log(idNo);
                    console.log(realName);
                    console.log(bankcard);
                    console.log(omit);
                    console.log(skip);
                    console.log(address);

                    $.ajax({
                        type: "post",
                        url: "/data/mobile/valid/result",
                        data: {
                            "tid" : tid,
                            "aid" : aid,
                            "mobile" : mobile,
                            "idNo" : idNo,
                            "realName" : realName,
                            "bankcard" : bankcard,
                            "omit" : omit,
                            "skip" : skip,
                            "address" : address
                        },
                        dataType: "json",
                        beforeSend:function () {
                            openProgress();
                        },
                        success: function (data) {
                            closeProgress();
                            $("#result_body").empty();

                            $("#result_body").append("<table class='table table-striped table-hover table-layout-fixed' id='sample_1'>" +
                                "<thead>" +
                                "<tr>" +
                                "<th style='width: 20%'>产品类型</th>" +
                                "<th style='width: 15%'>数据源</th>" +
                                "<th style='width: 10%'>核验结果</th>" +
                                "<th style='width: 20%'>请求数据</th>" +
                                "<th style='width: 20%'>响应结果</th>" +
                                "<th style='width: 10%'>照片</th>" +
                                "</tr>" +
                                "</thead>" +
                                "<tbody>" +
                                "</tbody>" +
                                "</table>");

                            //var width =  $('#sample_1').width();
                            //$('#sample_1').width($('#sample_1').width());

                            if (data != null && data.length > 0 ){
                                for (var i = 0; i < data.length; i++) {
                                    var apiType = data[i].apiType;
                                    if(typeof(apiType)=="undefined"){
                                        apiType = "";
                                    }
                                    var dataSource = data[i].dataSource;
                                    if(typeof(dataSource)=="undefined"){
                                        dataSource = "默认渠道";
                                    }
                                    var result = data[i].result;
                                    if(typeof(result)=="undefined"){
                                        result = "";
                                    }
                                    var requestBody = data[i].requestBody;
                                    if(typeof(requestBody)=="undefined"){
                                        requestBody = "";
                                    }
                                    var responseBody = data[i].responseBody;
                                    if(typeof(responseBody)=="undefined"){
                                        responseBody = "";
                                    }
                                    var photo = data[i].photo;
                                    if(typeof(photo)=="undefined"){
                                        photo = "";
                                    }
                                    var myContent =
                                        "<tr>" +
                                        "<td>" + apiType + "</td>" +
                                        "<td>" + dataSource + "</td>" +
                                        "<td>" + result + "</td>" +
                                        "<td class='table-td-layout-fixed'>" + requestBody + "</td>" +
                                        "<td class='table-td-layout-fixed'>" + responseBody + "</td>" +
                                        "<td><img style='width: 78px; height: 92px' src= 'data:image/jpeg;base64," + photo + "' alt='无照片' ></td>" +
                                        "</tr>";
                                    $("#sample_1 tbody").append(myContent);
                                }
                            }

                            function fnFormatDetails ( oTable, nTr)
                            {
                                var aData = oTable.fnGetData( nTr );
                                var sOut = '<table style="width: 100%">';
                                sOut += '<tr><th style="width: 10%">产品类型:</th><td>'+ aData[1] +'</td></tr>';
                                sOut += '<tr><th>数据源:</th><td>'+ aData[2] +'</td></tr>';
                                sOut += '<tr><th>核验结果:</th><td>'+ aData[3] +'</td></tr>';
                                sOut += '<tr><th>请求数据:</th><td>'+ aData[4] +'</td></tr>';
                                sOut += '<tr><th>响应结果:</th><td>'+ aData[5] +'</td></tr>';
                                sOut += '<tr><th>照片:</th><td>'+ aData[6] +'</td></tr>';
                                sOut += '</table>';
                                return sOut;
                            }

                            var nCloneTh = document.createElement( 'th' );
                            var nCloneTd = document.createElement( 'td' );
                            nCloneTd.innerHTML = '<span class="row-details row-details-close"></span>';
                            // nCloneTh.innerHTML = '<span class="row-details row-details-close"></span>';
                            $('#sample_1 thead tr').each( function () {
                                this.insertBefore( nCloneTh, this.childNodes[0] );
                            } );

                            $('#sample_1 tbody tr').each( function () {
                                this.insertBefore(  nCloneTd.cloneNode( true ), this.childNodes[0] );
                            } );

                            $('#sample_1').on('click', ' tbody td .row-details', function () {
                                var nTr = $(this).parents('tr')[0];
                                if ( oTable.fnIsOpen(nTr) )
                                {
                                    /* This row is already open - close it */
                                    $(this).addClass("row-details-close").removeClass("row-details-open");
                                    oTable.fnClose( nTr );
                                }
                                else
                                {
                                    /* Open this row */
                                    $(this).addClass("row-details-open").removeClass("row-details-close");
                                    oTable.fnOpen( nTr, fnFormatDetails(oTable, nTr), 'details' );
                                }
                            });

                            var oTable = $('#sample_1').dataTable({
                                "aoColumns": [
                                    { "bSortable": false},
                                    { "bSortable": false},
                                    { "bSortable": false},
                                    { "bSortable": false},
                                    { "bSortable": false},
                                    { "bSortable": false},
                                    { "bSortable": false}
                                ],
                                "bFilter" : false,
                                "bPaginate" : false,
                                "bLengthChange" : false,
                                "sDom": "rt<'row-fluid'<'span6'il><'span6'p>>",
                                "sPaginationType": "bootstrap",
                                "oLanguage" : {  //设置语言
                                    "sZeroRecords" : "对不起，没有匹配的数据",
                                    "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
                                    "sInfoEmpty" : "没有匹配的数据"
                                }
                            });
                        }
                    });

                }
                // $('#submit_form').submit();
            });

        }
    };

}();