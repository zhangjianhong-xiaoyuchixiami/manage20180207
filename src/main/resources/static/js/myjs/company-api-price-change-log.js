var CompanyApiPriceChangeLog = function () {

    return {

        init: function () {

            if (!jQuery().dataTable) {
                return;
            }

            var form = $('#submit_form');
            var error = $('.alert-error', form);
            var success = $('.alert-success', form);

            //表单验证
            form.validate({
                doNotHideMessage: true, //this option enables to show the error/success messages on tab switch.
                errorElement: 'span', //default input error message container
                errorClass: 'validate-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    companyId: {
                        required: true
                    },
                    tid_stid: {
                        required: true
                    },
                    price: {
                        required: true,
                        number:true
                    },
                    add_date: {
                        required: true
                    }
                },
                messages: {
                    companyId:{
                        required:"请选择公司！"
                    },
                    tid_stid:{
                        required:"请选择产品！"
                    },
                    price:{
                        required: "请输入金额！",
                        number:"金额格式不正确！"
                    },
                    add_date:{
                        required:"请选择生效时间！"
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
                    success.hide();
                    error.show();
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
                }
            });


            var oTable = $('#sample_1').dataTable({
                "aoColumns": [
                    { "bSortable": false},
                    null,
                    null,
                    null,
                    null,
                    null
                ],
                "aaSorting": [[1, 'asc']],
                "aLengthMenu": [
                    [10, 15, 20, -1],
                    [10, 15, 20, "全部"] // change per page values here
                ],
                "iDisplayLength": 15, //每页显示多少行
                "sDom": "t<'row-fluid'<'span6'il><'span6'p>>",
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


            /*状态正常全选操作*/
            jQuery('#sample_1 .group-checkable').change(function () {
                var set = jQuery(this).attr("data-set");
                var checked = jQuery(this).is(":checked");
                jQuery(set).each(function () {
                    if (checked) {
                        $(this).attr("checked", true);
                    } else {
                        $(this).attr("checked", false);
                    }
                });
                jQuery.uniform.update(set);
            });


            $('#companyApiPriceChangeLog').addClass('active');

            $('#apiProduct').addClass('active');

            $('#apiProductSelect').addClass('selected');

            $('#apiProductArrow').addClass('arrow open');

            $('#cid').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $('#pid').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $('#tid').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $('#companyId').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $('#tid_stid').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $('#companyId').change(function() {
                $('#tid_stid').empty();
                $('#tid_stid').append(' <option value=""></option>');
                var companyId = $("#companyId").val();
                $.ajax({
                    type: "post",
                    url: "/api/company/query-company-api-by-company-id",
                    data: {"cid": companyId},
                    dataType: "json",
                    success:function (data) {
                        if (data != null){
                            for (var i =0; i < data.length; i++){
                                var option	= '<option value="'+data[i].id+'">'+data[i].name+'</option>';
                                $('#tid_stid').append(option);
                            }
                        }
                    }
                });
            });

            $("#add-btn-primary").on("click",function () {

                if (form.valid() == false) {
                    return false;
                }

                swal({
                    title: "确定要添加吗？",   //弹出框的title
                    type: "question",    //弹出框类型
                    showCancelButton: true, //是否显示取消按钮
                    allowOutsideClick: false,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    cancelButtonText: "取消",//取消按钮文本
                    confirmButtonText: "确定添加"//确定按钮上面的文档
                }).then(function () {

                    var companyId = $('#companyId').val();
                    var tid_stid = $('#tid_stid').val();
                    var price = $('#price').val();
                    var add_date = $('#add_date').val();
                    console.log(companyId)
                    console.log(tid_stid)
                    console.log(price)
                    console.log(add_date)

                    $.ajax({
                        type: "post",
                        url: "/api/company/add-apiType-price-change",
                        data: {"cid": companyId, "tid_stid":tid_stid,"pic": price, "date": add_date},
                        dataType: "json",
                        beforeSend:function () {
                            openProgress();
                        },
                        success: function (data) {
                            closeProgress();
                            if(data != null){
                                if (data.fail != null) {
                                    swal(
                                        '失败',
                                        '哎呦，添加失败了',
                                        'error'
                                    );
                                    return;
                                }
                                if (data.success != null) {
                                    swal({
                                        title: "成功",
                                        html: '已添加成功',
                                        type: "success",
                                        showCancelButton: false, //是否显示取消按钮
                                        confirmButtonColor: '#3085d6',
                                        confirmButtonText: "确定"//确定按钮上面的文档
                                    }).then(function () {
                                        window.location.href = window.location.href
                                    })

                                }
                            }
                        }
                    });

                },function(dismiss) {
                    // dismiss的值可以是'cancel', 'overlay','close', 'timer'
                    if (dismiss === 'cancel') {}
                });

            })

        }

    };

}();