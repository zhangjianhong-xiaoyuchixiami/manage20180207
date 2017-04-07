var Company = function () {

    return {

        init: function () {

            if (!jQuery().dataTable) {
                return;
            }

            function fnFormatDetails ( oTable, nTr ) {
                var aData = oTable.fnGetData( nTr );
                var sOut = '<table style="width: 100%">';
                sOut += '<tr>' +
                    '<th>账号</th>' +
                    '<th>账号类型</th>' +
                    '<th>密码</th>' +
                    '<th>余额</th>' +
                    '<th>状态</th>' +
                    '<th>Ip段</th>' +
                    '<th>操作</th>' +
                    '</tr>';

                sOut += '<tr>' +
                    '<td>'+aData[8]+'</td>' +
                    '<td>'+aData[9]+'</td>' +
                    '<td>'+aData[10]+'</td>' +
                    '<td>'+aData[11]+'</td>' +
                    '<td>'+aData[12]+'</td>' +
                    '<td>'+aData[13]+'</td>' +
                    '<td>'+aData[14]+'</td>' +
                    '</tr>';
                sOut += '</table>';
                return sOut;
            }

            var nCloneTh = document.createElement( 'th' );
            var nCloneTd = document.createElement( 'td' );
            nCloneTd.innerHTML = '<span class="row-details row-details-close"></span>';
            // nCloneTh.innerHTML = '<span class="row-details row-details-close"></span>';
            $('#companySample_1 thead tr').each( function () {
                this.insertBefore( nCloneTh, this.childNodes[0] );
            } );

            $('#companySample_1 tbody tr').each( function () {
                this.insertBefore(  nCloneTd.cloneNode( true ), this.childNodes[0] );
            } );

            $('#companySample_1').on('click', ' tbody td .row-details', function () {
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


            var oTable = $('#companySample_1').dataTable({
                "aoColumns": [
                    { "bSortable": false},  //0  展开符号
                    { "bSortable": false},  //0  展开符号
                    null,  //1  companyName
                    null,  //2
                    null,
                    null, //3  partnerName
                    null,  //4  companyBalance
                    null,  //5  companyCreateTime
                    { "bVisible": false},  //6 customerId
                    { "bVisible": false},  //7 typeId
                    { "bVisible": false},  //8 typeName
                    { "bVisible": false},  //9 authId
                    { "bVisible": false },  //10 authPass
                    { "bVisible": false }, //11 balance
                    { "bVisible": false },  //12 操作
                    { "bSortable": false}  // 13
                ],
                "aaSorting": [[4, 'desc']],
                "aLengthMenu": [
                    [10, 15, 20, -1],
                    [10, 15, 20, "全部"] // change per page values here
                ],
                "bFilter" : false, //设置全文搜索框，默认true

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
                }
            });

            /*以下操作是新增公司*/
            $("#companyCustomerName").focus(function () {
                $("#companyNameMsg").html("");
            });

            $("#authId").focus(function () {
                $("#authIdMsg").html("");
            });

            $("#deptId").focus(function () {
                $("#deptIdMsg").html("");
            });

            $("#authId").blur(function(){
                $("#authIdMsg").load("/customer/findCustomerByAuthId/"+$("#authId").val(),
                    function(responseTxt){
                        if(responseTxt=="yes")
                            $("#authIdMsg").html("<font color='red'>该账号已被使用，请重新输入！</font>");
                        if(responseTxt=="no")
                            $("#authIdMsg").html("");
                    });
            });

            $("#add-btn-black-btn-primary").on("click",function () {
                var companyCustomerName=$("#companyCustomerName").val();
                var authId=$("#authId").val();
                var partnerId=$("#partnerId").val();
                var deptId=$("#deptId").val();
                $.ajax({
                    type: "post",
                    url: "/company/add-company-customer",
                    data: {"companyName":companyCustomerName,"authId":authId,"partnerId":partnerId,"deptId":deptId},
                    dataType: "json",
                    success: function (result) {
                        if(result.companyNameMessage != null){
                            $("#companyNameMsg").empty();
                            $("#companyNameMsg").html('<font color="red">'+result.companyNameMessage+'</font>');
                            return;
                        }
                        if(result.authIdMessage != null){
                            $("#authIdMsg").empty();
                            $("#authIdMsg").html('<font color="red">'+result.authIdMessage+'</font>');
                            return;
                        }
                        if(result.deptMessage != null){
                            $("#deptIdMsg").empty();
                            $("#deptIdMsg").html('<font color="red">'+result.deptMessage+'</font>');
                            return;
                        }
                        if(result.errorMessage != null) {
                            $("#error-alert").empty();
                            $("#error-alert").append('<div class="alert alert-error show"><button class="close" data-dismiss="alert"></button><span>'+result.errorMessage+'</span></div>')
                            return;
                        }
                        if (result.successMessage != null){
                            alert("操作成功");
                            window.location.href=window.location.href
                        }
                    }
                });
            });


            /*以下操作为添加账号*/
            $("#authId-account").focus(function () {
                $("#authId-accountMsg").html("");
            });

            $("#authId-account").blur(function(){
                $("#authId-accountMsg").load("/customer/findCustomerByAuthId/"+$("#authId-account").val(),
                    function(responseTxt){
                        if(responseTxt=="yes")
                            $("#authId-accountMsg").html("<font color='red'>该账号已被使用，请重新输入！</font>");
                        if(responseTxt=="no")
                            $("#authId-accountMsg").html("");
                    });
            });

            $("#add-account-btn-black-btn-primary").on("click",function () {
                var companyId=$("#companyId").val();
                var authId=$("#authId-account").val();
                $.ajax({
                    type: "post",
                    url: "/company/add-customer-account",
                    data: {"companyId":companyId,"authId":authId},
                    dataType: "json",
                    success: function (result) {
                        if(result.authIdMessage != null){
                            $("#authId-accountMsg").empty();
                            $("#authId-accountMsg").html('<font color="red">'+result.authIdMessage+'</font>');
                            return;
                        }
                        if(result.errorMessage != null) {
                            $("#error-alert-account").empty();
                            $("#error-alert-account").append('<div class="alert alert-error show"><button class="close" data-dismiss="alert"></button><span>'+result.errorMessage+'</span></div>')
                            return;
                        }
                        if (result.successMessage != null){
                            alert("操作成功");
                            window.location.href=window.location.href
                        }
                    }
                });
            });


            /*以下操作是给账号充值或扣费*/
            $("#update_balance_amount").focus(function () {
                $("#update_balance_amountMsg").html("");
            });

            $("#update_balance_reasonId").focus(function () {
                $("#update_balance_reasonIdMsg").html("");
            });

            $("#update-balance-btn-black-btn-primary").on("click",function () {
                var customerId=$("#customerId").val();
                var amount=$("#update_balance_amount").val();
                var reason=$("#update_balance_reasonId").val();
                $.ajax({
                    type: "post",
                    url: "/company/update-customer-balance",
                    data: {"customerId":customerId,"amount":amount,"reason":reason},
                    dataType: "json",
                    success: function (result) {
                        if(result.amountMessage != null){
                            $("#update_balance_amountMsg").empty();
                            $("#update_balance_amountMsg").html('<font color="red">'+result.amountMessage+'</font>');
                            return;
                        }
                        if(result.reasonMessage != null){
                            $("#update_balance_reasonIdMsg").empty();
                            $("#update_balance_reasonIdMsg").html('<font color="red">'+result.reasonMessage+'</font>');
                            return;
                        }
                        if(result.errorMessage != null) {
                            $("#error_alert_update_balance").empty();
                            $("#error_alert_update_balance").append('<div class="alert alert-error show"><button class="close" data-dismiss="alert"></button><span>'+result.errorMessage+'</span></div>')
                            return;
                        }
                        if (result.successMessage != null){
                            alert("操作成功");
                            window.location.href=window.location.href
                        }
                    }
                });
            });

            /*状态正常全选操作*/
            jQuery('#companySample_1 .group-checkable').change(function () {
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

            jQuery('#companySample_1_wrapper .dataTables_filter input').addClass("m-wrap medium"); // modify table search input
            jQuery('#companySample_1_wrapper .dataTables_length select').addClass("m-wrap small"); // modify table per page dropdown

            /*状态正常禁用公司操作*/
            $("#batchBanCompany").on('click',function () {

                var companyId =[];//定义一个数组
                $('input[name="checkBoxCompanyId"]:checked').each(function(){
                    companyId.push($.trim($(this).val()));
                });

                if (companyId == null || companyId == ""){
                    alert("请先选择要禁用的公司")
                }else {
                    $.ajax({
                        type:'post',
                        url:"/company//ban",
                        data:{"companyId": companyId},
                        dataType:'json',
                        success:function(data){
                            if(data!=null && data.result=="ok"){
                                alert("操作成功");
                                window.location.href=window.location.href;
                            }else{
                                alert("操作失败");
                                window.location.href=window.location.href;
                            }
                        }
                    });
                }
            });


            /*左侧导航*/
            $('#customerManage').addClass('active');

            $('#customerList').addClass('active');

            $('#customerManageSelect').addClass('selected');

            $('#customerManageArrow').addClass('arrow open');

        }

    };

}();