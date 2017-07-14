var ExtraExcel = function () {

    return {

        init: function () {

           /* var form = $('#submit_form');

            //表单验证
            form.validate({
                //doNotHideMessage: true, //this option enables to show the error/success messages on tab switch.
                errorElement: 'span', //default input error message container
                errorClass: 'validate-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                    pid: {
                        required: true
                    },
                    wid: {
                        required: true
                    },
                    beginDate: {
                        required: true
                    },
                    endDate: {
                        required: true
                    }
                },
                messages: {
                    pid:{
                        required:"请选择合作公司！"
                    },
                    wid:{
                        required:"请选择统计方式！"
                    },
                    beginDate:{
                        required: "请选择起始日期！"
                    },
                    endDate:{
                        required:"请选择结束日期！"
                    }
                },

                errorPlacement: function (error, element) { // render error placement for each input type
                    if (element.attr("name") == "gender") { // for uniform radio buttons, insert the after the given container
                        error.addClass("no-left-padding").insertAfter("#form_gender_error");
                    } else if (element.attr("name") == "payment[]") { // for uniform radio buttons, insert the after the given container
                        error.addClass("no-left-padding").insertAfter("#form_payment_error");
                    } else {
                        error.insertAfter(element); // for other inputs, just perform default behavoir
                    }
                },

                invalidHandler: function (event, validator) { //display error alert on form submit
                    App.scrollTo(error, -200);
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.help-inline').removeClass('ok'); // display OK icon
                    $(element)
                        .closest('.control-group').removeClass('success').addClass('error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change dony by hightlight
                    $(element)
                        .closest('.control-group').removeClass('error'); // set error class to the control group
                },

                success: function (label) {
                    if (label.attr("for") == "gender" || label.attr("for") == "payment[]") { // for checkboxes and radip buttons, no need to show OK icon
                        label
                            .closest('.control-group').removeClass('error').addClass('success');
                        label.remove(); // remove error label here
                    } else { // display success icon for other inputs
                        label
                        //.addClass('valid ok') // mark the current input as valid and display OK icon
                            .closest('.control-group').removeClass('error').addClass('success'); // set success class to the control group
                    }
                },
                submitHandler: function (form) {
                    if(form.valid() == false)
                        return false;
                    form.submit();
                }
            });*/

            var oTable1 = $('#sample_1').dataTable({
                "aoColumns": [
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                ],
                "aaSorting": [[5, 'asc']],
                "bPaginate" : true,
                "bLengthChange" : false,
                "iDisplayLength": 15, //每页显示多少行
                "sDom": "t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage" : {  //设置语言
                    "sLengthMenu" : "每页显示 _MENU_ 条记录",
                    "sZeroRecords" : "对不起，没有匹配的数据",
                    "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
                    "sInfoEmpty" : "没有匹配的数据",
                    "sInfoFiltered" : "(数据表中共 _MAX_ 条记录)",
                    "sProcessing" : "正在加载中...",
                    "sSearch" : "全文搜索：",
                    "oPaginate" : {
                        "sFirst" : "第一页",
                        "sPrevious" : " 上一页 ",
                        "sNext" : " 下一页 ",
                        "sLast" : " 最后一页 "
                    }
                },
                "bFilter" : false //设置全文搜索框，默认true
            });

            var oTable2 = $('#sample_2').dataTable({
                "aoColumns": [
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                ],
                "aaSorting": [[5, 'asc']],
                "bPaginate" : true,
                "bLengthChange" : false,
                "iDisplayLength": 15, //每页显示多少行
                "sDom": "t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage" : {  //设置语言
                    "sLengthMenu" : "每页显示 _MENU_ 条记录",
                    "sZeroRecords" : "对不起，没有匹配的数据",
                    "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
                    "sInfoEmpty" : "没有匹配的数据",
                    "sInfoFiltered" : "(数据表中共 _MAX_ 条记录)",
                    "sProcessing" : "正在加载中...",
                    "sSearch" : "全文搜索：",
                    "oPaginate" : {
                        "sFirst" : "第一页",
                        "sPrevious" : " 上一页 ",
                        "sNext" : " 下一页 ",
                        "sLast" : " 最后一页 "
                    }
                },
                "bFilter" : false //设置全文搜索框，默认true
            });

            var oTable3 = $('#sample_3').dataTable({
                "aoColumns": [
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                ],
                "aaSorting": [[5, 'desc']],
                "bPaginate" : true,
                "bLengthChange" : false,
                "iDisplayLength": 15, //每页显示多少行
                "sDom": "t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage" : {  //设置语言
                    "sLengthMenu" : "每页显示 _MENU_ 条记录",
                    "sZeroRecords" : "对不起，没有匹配的数据",
                    "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
                    "sInfoEmpty" : "没有匹配的数据",
                    "sInfoFiltered" : "(数据表中共 _MAX_ 条记录)",
                    "sProcessing" : "正在加载中...",
                    "sSearch" : "全文搜索：",
                    "oPaginate" : {
                        "sFirst" : "第一页",
                        "sPrevious" : " 上一页 ",
                        "sNext" : " 下一页 ",
                        "sLast" : " 最后一页 "
                    }
                },
                "bFilter" : false //设置全文搜索框，默认true
            });

            $('#extra-account-partner').addClass('active');

            $('#customerBalance').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');

            $("#pid").select2({

                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true

            });

            $("#wid").select2({

                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true

            });

            $("#cid").select2({

                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true

            });

            $("#pid").change(function () {

                var param = $("#pid").val();

                $("#cid_choose ").empty();
                $("#cid_choose").append('<select class="medium m-wrap" multiple id="cid" name="cid"><option value=""></option></select>');

                if (param !=null) {
                    $.ajax({
                        url: '/excel/find-customer-by-pid',
                        data: {"pid": param},
                        type: 'post',
                        dataType: 'json',
                        success: function (data) {
                            if(data != null){
                                for (var i=0; i<data.length; i++){
                                    var op=document.createElement("option");
                                    op.value=data[i].id;
                                    op.innerHTML=data[i].name;
                                    $("#cid").append(op);
                                }
                            }
                        }
                    });
                }

                $("#cid").select2({

                    language: "zh-CN",
                    placeholder: "请选择",
                    allowClear: true

                });

            });

            (function($){
                $.getUrlParam = function(name)
                {
                    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
                    var r = window.location.search.substr(1).match(reg);
                    return r?decodeURIComponent(r[2]):'';  //含有中文请注意此处的编码和解码
                }
            })(jQuery);
            var cid =[];//定义一个数组
            cid.push($('#cid').val());
            console.log(cid);
            $(function(){
                console.log($.getUrlParam('pid'));
                console.log($.getUrlParam('wid'));
                console.log($.getUrlParam('beginDate'));
                console.log($.getUrlParam('endDate'));
            });
            var href = $("#exportExcel").attr('href');
            if(href) {
                href += (href.match(/\?/) ? '&' : '?') + 'pid=' + $.getUrlParam('pid') +
                    (href.match(/\?/) ? '&' : '?') + 'wid=' + $.getUrlParam('wid') +
                    (href.match(/\?/) ? '&' : '?') + 'cid=' + cid +
                    (href.match(/\?/) ? '&' : '?') + 'beginDate=' + $.getUrlParam('beginDate') +
                    (href.match(/\?/) ? '&' : '?') + 'endDate=' + $.getUrlParam('endDate');

                $("#exportExcel").attr('href', href);
            }

        }

    };

}();