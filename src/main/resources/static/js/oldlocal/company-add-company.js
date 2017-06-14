var AddCompanyAllotApiAddIp = function () {


    return {
        //main function to initiate the module
        init: function () {
            if (!jQuery().bootstrapWizard) {
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
                    companyCustomerName: {
                        required: true
                    },
                    authId: {
                        required: true,
                        authId: true,
                        remote: {
                            url: "/company/findCustomerByAuthId",
                            type: "post",
                            dataType: "json",
                            data: {
                                authId: function(){
                                    return $("#authId").val();
                                }
                            },
                            dataFilter: function (data,type) {
                                var json = $.parseJSON(data);
                                if (json.success == "true") {
                                    return true ;
                                }else if (json.fail == "false"){
                                    return false;
                                }
                            }
                        }
                    },
                    partnerId: {

                    },
                    add_api_type_sub_1: {

                    },
                    add_api_type_sub_price_1: {
                        number: true
                    },
                    add_api_type_sub_2: {

                    },
                    add_api_type_sub_price_2: {
                        number: true
                    },
                    add_api_type_sub_3: {

                    },
                    add_api_type_sub_price_3: {
                        number: true
                    },
                    add_api_type_sub_4: {

                    },
                    add_api_type_sub_price_4: {
                        number: true
                    },
                    add_api_type_sub_5: {

                    },
                    add_api_type_sub_price_5: {
                        number: true
                    },
                    add_api_type_sub_6: {

                    },
                    add_api_type_sub_price_6: {
                        number: true
                    },
                    add_api_type_sub_7: {

                    },
                    add_api_type_sub_price_7: {
                        number: true
                    },
                    add_api_type_sub_8: {

                    },
                    add_api_type_sub_price_8: {
                        number: true
                    },
                    add_api_type_sub_9: {

                    },
                    add_api_type_sub_price_9: {
                        number: true
                    },
                    add_api_type_sub_10: {

                    },
                    add_api_type_sub_price_10: {
                        number: true
                    },
                    add_api_type_sub_11: {

                    },
                    add_api_type_sub_price_11: {
                        number: true
                    },
                    add_api_type_sub_12: {

                    },
                    add_api_type_sub_price_12: {
                        number: true
                    },
                    add_api_type_sub_13: {

                    },
                    add_api_type_sub_price_13: {
                        number: true
                    },
                    add_api_type_sub_14: {

                    },
                    add_api_type_sub_price_14: {
                        number: true
                    },
                    add_api_type_sub_15: {

                    },
                    add_api_type_sub_price_15: {
                        number: true
                    },
                    input_ipv4_begin: {
                        ipVali:true
                    },
                    input_ipv4_end: {
                        ipVali:true
                    },
                    input_ipv4_begin_1: {
                        ipVali:true
                    },
                    input_ipv4_end_1: {
                        ipVali:true
                    },
                    input_ipv4_begin_2: {
                        ipVali:true
                    },
                    input_ipv4_end_2: {
                        ipVali:true
                    },
                    input_ipv4_begin_3: {
                        ipVali:true
                    },
                    input_ipv4_end_3: {
                        ipVali:true
                    },
                    input_ipv4_begin_4: {
                        ipVali:true
                    },
                    input_ipv4_end_4: {
                        ipVali:true
                    },
                    input_ipv4_begin_5: {
                        ipVali:true
                    },
                    input_ipv4_end_5: {
                        ipVali:true
                    },
                    input_ipv4_begin_6: {
                        ipVali:true
                    },
                    input_ipv4_end_6: {
                        ipVali:true
                    },
                    input_ipv4_begin_7: {
                        ipVali:true
                    },
                    input_ipv4_end_7: {
                        ipVali:true
                    },
                    input_ipv4_begin_8: {
                        ipVali:true
                    },
                    input_ipv4_end_8: {
                        ipVali:true
                    },
                    input_ipv4_begin_9: {
                        ipVali:true
                    },
                    input_ipv4_end_9: {
                        ipVali:true
                    },
                    input_ipv4_begin_10: {
                        ipVali:true
                    },
                    input_ipv4_end_10: {
                        ipVali:true
                    },
                    input_ipv4_begin_11: {
                        ipVali:true
                    },
                    input_ipv4_end_11: {
                        ipVali:true
                    },
                    input_ipv4_begin_12: {
                        ipVali:true
                    },
                    input_ipv4_end_12: {
                        ipVali:true
                    },
                    input_ipv4_begin_13: {
                        ipVali:true
                    },
                    input_ipv4_end_13: {
                        ipVali:true
                    },
                    input_ipv4_begin_14: {
                        ipVali:true
                    },
                    input_ipv4_end_14: {
                        ipVali:true
                    },
                    input_ipv4_begin_15: {
                        ipVali:true
                    },
                    input_ipv4_end_15: {
                        ipVali:true
                    }
                },

                messages: {
                    companyCustomerName:"请输入公司名称！",
                    authId:{
                        required: "请输入账号！",
                        remote:"该账号已被使用，请重新输入账号！"
                    },
                    add_api_type_sub_price_1: "请输入数字！",
                    add_api_type_sub_price_2: "请输入数字！",
                    add_api_type_sub_price_3: "请输入数字！",
                    add_api_type_sub_price_4: "请输入数字！",
                    add_api_type_sub_price_5: "请输入数字！",
                    add_api_type_sub_price_6: "请输入数字！",
                    add_api_type_sub_price_7: "请输入数字！",
                    add_api_type_sub_price_8: "请输入数字！",
                    add_api_type_sub_price_9: "请输入数字！",
                    add_api_type_sub_price_10: "请输入数字！",
                    add_api_type_sub_price_11: "请输入数字！",
                    add_api_type_sub_price_12: "请输入数字！",
                    add_api_type_sub_price_13: "请输入数字！",
                    add_api_type_sub_price_14: "请输入数字！",
                    add_api_type_sub_price_15: "请输入数字！"
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
                            .addClass('valid ok') // mark the current input as valid and display OK icon
                            .closest('.control-group').removeClass('error').addClass('success'); // set success class to the control group
                    }
                },

                submitHandler: function (form) {
                    success.show();
                    error.hide();
                    //add here some ajax code to submit your form or just call form.submit() if you want to submit the form without ajax
                }

            });

            //验证Ip
            jQuery.validator.addMethod("ipVali",function(value,element){
                var ip = /^(?:(?:2[0-4][0-9]\.)|(?:25[0-5]\.)|(?:1[0-9][0-9]\.)|(?:[1-9][0-9]\.)|(?:[0-9]\.)){3}(?:(?:2[0-5][0-5])|(?:25[0-5])|(?:1[0-9][0-9])|(?:[1-9][0-9])|(?:[0-9]))$/;
                return this.optional(element) || (ip.test(value));
            },"请输入正确的Ip格式，例如：192.168.111.123");

            //验证账号
            jQuery.validator.addMethod("authId",function(value,element){
                var authId = /^[a-zA-Z0-9-_]+$/;
                return this.optional(element) || (authId.test(value));
            },"只能有数字、字母、下划线组成！");

            //确认页面取值
            var displayConfirm = function() {
                $('.display-value', form).each(function(){
                    var input = $('[name="'+$(this).attr("data-display")+'"]', form);
                    if (input.is(":text") || input.is("textarea")) {
                        $(this).html(input.val());
                    } else if (input.is("select")) {
                        $(this).html(input.find('option:selected').text());
                    } else if (input.is(":radio") && input.is(":checked")) {
                        $(this).html(input.attr("data-title"));
                    } else if ($(this).attr("data-display") == 'card_expiry') {
                        $(this).html($('[name="card_expiry_mm"]', form).val() + '/' + $('[name="card_expiry_yyyy"]', form).val());
                    } else if ($(this).attr("data-display") == 'payment') {
                        var payment = [];
                        $('[name="payment[]"]').each(function(){
                            payment.push($(this).attr('data-title'));
                        });
                        $(this).html(payment.join("<br>"));
                    }
                });
            }

            // 进度条-步数标题栏
            $('#form_wizard_1').bootstrapWizard({
                'nextSelector': '.button-next',
                'previousSelector': '.button-previous',
                onTabClick: function (tab, navigation, index) {
                    return false;
                },
                onNext: function (tab, navigation, index) {
                    success.hide();
                    error.hide();

                    if (form.valid() == false) {
                        return false;
                    }

                    var total = navigation.find('li').length;
                    var current = index + 1;
                    // set wizard title
                    $('.step-title', $('#form_wizard_1')).text('第 ' + (index + 1) + ' 步共 ' + total + '步');
                    // set done steps
                    jQuery('li', $('#form_wizard_1')).removeClass("done");
                    var li_list = navigation.find('li');
                    for (var i = 0; i < index; i++) {
                        jQuery(li_list[i]).addClass("done");
                    }

                    if (current == 1) {
                        $('#form_wizard_1').find('.button-previous').hide();
                    } else {
                        $('#form_wizard_1').find('.button-previous').show();
                    }

                    if (current >= total) {
                        $('#form_wizard_1').find('.button-next').hide();
                        $('#form_wizard_1').find('.button-submit').show();
                        displayConfirm();
                    } else {
                        $('#form_wizard_1').find('.button-next').show();
                        $('#form_wizard_1').find('.button-submit').hide();
                    }
                    App.scrollTo($('.page-title'));
                },
                onPrevious: function (tab, navigation, index) {
                    success.hide();
                    error.hide();

                    var total = navigation.find('li').length;
                    var current = index + 1;
                    // set wizard title
                    $('.step-title', $('#form_wizard_1')).text('第 ' + (index + 1) + ' 步共 ' + total + '步');
                    // set done steps
                    jQuery('li', $('#form_wizard_1')).removeClass("done");
                    var li_list = navigation.find('li');
                    for (var i = 0; i < index; i++) {
                        jQuery(li_list[i]).addClass("done");
                    }

                    if (current == 1) {
                        $('#form_wizard_1').find('.button-previous').hide();
                    } else {
                        $('#form_wizard_1').find('.button-previous').show();
                    }

                    if (current >= total) {
                        $('#form_wizard_1').find('.button-next').hide();
                        $('#form_wizard_1').find('.button-submit').show();
                    } else {
                        $('#form_wizard_1').find('.button-next').show();
                        $('#form_wizard_1').find('.button-submit').hide();
                    }

                    App.scrollTo($('.page-title'));
                },
                onTabShow: function (tab, navigation, index) {
                    var total = navigation.find('li').length;
                    var current = index + 1;
                    var $percent = (current / total) * 100;
                    $('#form_wizard_1').find('.bar').css({
                        width: $percent + '%'
                    });
                }
            });

            //隐藏后退按钮
            $('#form_wizard_1').find('.button-previous').hide();

            //表单提交
            $('#form_wizard_1 .button-submit').on("click",function () {

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

                    var companyName = $('#companyCustomerName').val();
                    var authId = $('#authId').val();
                    var partnerId = $('#partnerId').val();

                    var add_api_type_sub = [] ;
                    $("select[id='add_api_type_sub']").each(function(){
                        add_api_type_sub.push($.trim($(this).val()));
                    });
                    var add_api_type_sub_price = [] ;
                    $("input[id='add_api_type_sub_price']").each(function(){
                        add_api_type_sub_price.push($.trim($(this).val()));
                    });
                    var beginIp = [];
                    $("input[id='input_ipv4_begin']").each(function(){
                        beginIp.push($.trim($(this).val()));
                    });
                    var endIp = [];
                    $("input[id='input_ipv4_end']").each(function(){
                        endIp.push($.trim($(this).val()));
                    });

                    $.ajax({
                        type: "post",
                        url: "/company/add-company-customer",
                        data: {
                            "companyName" : companyName,
                            "authId" : authId,
                            "partnerId" : partnerId,
                            "add_api_type_sub" : add_api_type_sub,
                            "add_api_type_sub_price" : add_api_type_sub_price,
                            "beginIp" : beginIp,
                            "endIp" : endIp
                        },
                        dataType: "json",
                        beforeSend:function () {
                            openProgress();
                        },
                        success: function (data) {
                            closeProgress();
                            if (data != null) {
                                if (data.error != null) {
                                    swal({
                                        title: "失败",
                                        text: "哎呦，添加失败了",
                                        type: "error",
                                        showCancelButton: false,
                                        confirmButtonColor: '#3085d6',
                                        confirmButtonText: "确定"
                                    }).then(function () {
                                        return;
                                    })
                                }
                                if (data.success != null){
                                    swal({
                                        title: "成功",
                                        text: "添加成功",
                                        type: "success",
                                        showCancelButton: false, //是否显示取消按钮
                                        confirmButtonColor: '#3085d6',
                                        confirmButtonText: "确定"//确定按钮上面的文档
                                    }).then(function () {
                                        location.reload();
                                    })
                                }
                            }
                        }
                    })

                },function(dismiss) {
                    // dismiss的值可以是'cancel', 'overlay','close', 'timer'
                    if (dismiss === 'cancel') {}
                });

            }).hide();

        }

    };

}();