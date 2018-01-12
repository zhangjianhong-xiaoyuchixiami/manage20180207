

function currentConsumerIncomeCondition(typeId, subTypeId) {
    // $("#currConsumerIncomeCondition").empty();
    /*加载消费数据*/
    $.ajax({
        type: "post",
        url: "/operation/get-curr-customer-income-condition",
        data:{"typeId":typeId,"subTypeId":subTypeId},
        dataType: "json",
        beforeSend:function () {
            var myContent = "<tr>" +
                "<td rowspan='4'>" + '正在加载，请稍后' + "<span class='loading'></span></td>" +
                "</tr>";
            $("#currConsumerIncomeCondition tbody").append(myContent);
        },
        success:function (result) {
            $("#currConsumerIncomeCondition tbody").empty();

            if (result != null ){

                if (result.length > 0) {
                    for (var i = 0; i < result.length; i++) {
                        var myContent;

                        if (result[0].subTypeName != null){

                            $('#myModalLabel_1').text(result[0].apiTypeName + "--" + result[0].subTypeName)
                        }else{

                            $('#myModalLabel_1').html(result[0].apiTypeName )
                        }

                        if (result[i].price == null) {
                            myContent = "<tr>" +
                                "<td>" + result[i].customerName+"</td>" +
                                "<td>" + result[i].consumeAccount + "</td>" +
                                "<td>" + '未知' + "</td>" +
                                "<td>" + result[i].amount + "</td>" +
                                "</tr>";
                        } else {
                            myContent = "<tr>" +
                                "<td>" + result[i].customerName+"</td>" +
                                "<td>" + result[i].consumeAccount + "</td>" +
                                "<td>" + result[i].price + "</td>" +
                                "<td>" + result[i].amount + "</td>" +
                                "</tr>";
                        }
                        $("#currConsumerIncomeCondition tbody").append(myContent);
                    }
                }else {
                    var myContent = "<tr>" +
                        "<td rowspan='4'>" + '无消费记录' + "</td>" +
                        "</tr>";
                    $("#currConsumerIncomeCondition tbody").append(myContent);
                }
            }else {
                var myContent = "<tr>" +
                    "<td rowspan='4'colspan=\"4\">" + '无消费记录' + "</td>" +
                    "</tr>";
                $("#currConsumerIncomeCondition tbody").append(myContent);
            }

        }
    });
}

function yesterdayConsumerIncomeCondition(typeId, subTypeId) {

    // $("#yestConsumerIncomeCondition").empty();
    /*加载消费数据*/
    $.ajax({
        type: "post",
        url: "/operation/get-yest-customer-income-condition",
        data: {"typeId": typeId, "subTypeId": subTypeId},
        dataType: "json",
        beforeSend: function () {
            var myContent = "<tr>" +
                "<td rowspan='4'>" + '正在加载，请稍后' + "<span class='loading'></span></td>" +
                "</tr>";
            $("#yestConsumerIncomeCondition tbody").append(myContent);
        },
        success: function (result) {

            $("#yestConsumerIncomeCondition tbody").empty();

            if (result != null ) {

                if (result.length > 0) {
                    for (var i = 0; i < result.length; i++) {
                        var myContent;

                        if (result[i].price == null) {
                            myContent = "<tr>" +
                                "<td>" + result[i].customerName + "</td>" +
                                "<td>" + result[i].consumeAccount + "</td>" +
                                "<td>" + '未知' + "</td>" +
                                "<td>" + result[i].amount + "</td>" +
                                "</tr>";
                        } else {
                            myContent = "<tr>" +
                                "<td>" + result[i].customerName + "</td>" +
                                "<td>" + result[i].consumeAccount + "</td>" +
                                "<td>" + result[i].price + "</td>" +
                                "<td>" + result[i].amount + "</td>" +
                                "</tr>";
                        }
                        $("#yestConsumerIncomeCondition tbody").append(myContent);
                    }
                } else {
                    var myContent = "<tr>" +
                        "<td rowspan='4'>" + '无消费记录' + "</td>" +
                        "</tr>";
                    $("#yestConsumerIncomeCondition tbody").append(myContent);
                }
            } else {
                var myContent = "<tr>" +
                    "<td rowspan='4'colspan=\"4\">" + '无消费记录' + "</td>" +
                    "</tr>";
                $("#yestConsumerIncomeCondition tbody").append(myContent);
            }

        }
    });
}
function currentVendorCostCondition(typeId, subTypeId) {

    // $("#currVendorCostCondition").empty();
    /*加载消费数据*/
    $.ajax({
        type: "post",
        url: "/operation/get-curr-vendor-cost-condition",
        data: {"typeId": typeId, "subTypeId": subTypeId},
        dataType: "json",
        beforeSend: function () {
            var myContent = "<tr>" +
                "<td rowspan='4'>" + '正在加载，请稍后' + "<span class='loading'></span></td>" +
                "</tr>";
            $("#currVendorCostCondition tbody").append(myContent);
        },
        success: function (result) {

            $("#currVendorCostCondition tbody").empty();

            if (result != null ) {

                if (result.length > 0) {
                    for (var i = 0; i < result.length; i++) {
                        var myContent;

                        if (result[i].cost == null) {
                            myContent = "<tr>" +
                                "<td>" + result[i].vendorName + "</td>" +
                                "<td>" + result[i].consumeAccount + "</td>" +
                                "<td>" + '未知' + "</td>" +
                                "<td>" + result[i].amount + "</td>" +
                                "</tr>";
                        } else {
                            myContent = "<tr>" +
                                "<td>" + result[i].vendorName + "</td>" +
                                "<td>" + result[i].consumeAccount + "</td>" +
                                "<td>" + result[i].cost + "</td>" +
                                "<td>" + result[i].amount + "</td>" +
                                "</tr>";
                        }
                        $("#currVendorCostCondition tbody").append(myContent);
                    }
                } else {
                    var myContent = "<tr>" +
                        "<td rowspan='4'>" + '无消费记录' + "</td>" +
                        "</tr>";
                    $("#currVendorCostCondition tbody").append(myContent);
                }
            } else {
                var myContent = "<tr>" +
                    "<td rowspan='4'colspan=\"4\">" + '无消费记录' + "</td>" +
                    "</tr>";
                $("#currVendorCostCondition tbody").append(myContent);
            }

        }
    });
}

function yesterdayVendorCostCondition(typeId, subTypeId) {

    // $("#yestConsumerIncomeCondition").empty();
    /*加载消费数据*/
    $.ajax({
        type: "post",
        url: "/operation/get-yest-vendor-cost-condition",
        data: {"typeId": typeId, "subTypeId": subTypeId},
        dataType: "json",
        beforeSend: function () {
            var myContent = "<tr>" +
                "<td rowspan='4'>" + '正在加载，请稍后' + "<span class='loading'></span></td>" +
                "</tr>";
            $("#yestVendorCostCondition tbody").append(myContent);
        },
        success: function (result) {

            $("#yestVendorCostCondition tbody").empty();

            if (result != null) {

                if (result.length > 0) {
                    for (var i = 0; i < result.length; i++) {
                        var myContent;

                        if (result[i].cost == null) {
                            myContent = "<tr>" +
                                "<td>" + result[i].vendorName + "</td>" +
                                "<td>" + result[i].consumeAccount + "</td>" +
                                "<td>" + '未知' + "</td>" +
                                "<td>" + result[i].amount + "</td>" +
                                "</tr>";
                        } else {
                            myContent = "<tr>" +
                                "<td>" + result[i].vendorName + "</td>" +
                                "<td>" + result[i].consumeAccount + "</td>" +
                                "<td>" + result[i].cost + "</td>" +
                                "<td>" + result[i].amount + "</td>" +
                                "</tr>";
                        }
                        $("#yestVendorCostCondition tbody").append(myContent);
                    }
                } else {
                    var myContent = "<tr>" +
                        "<td rowspan='4'>" + '无消费记录' + "</td>" +
                        "</tr>";
                    $("#yestVendorCostCondition tbody").append(myContent);
                }
            } else {
                var myContent = "<tr>" +
                    "<td rowspan='4'colspan=\"4\">" + '无消费记录' + "</td>" +
                    "</tr>";
                $("#yestVendorCostCondition tbody").append(myContent);
            }

        }
    });
}




function yesterdayHourConsumerIncomeCondition(typeId, subTypeId) {

    // $("#yestConsumerIncomeCondition").empty();
    /*加载消费数据*/
    $.ajax({
        type: "post",
        url: "/operation/get-yest-hour-customer-income-condition",
        data: {"typeId": typeId, "subTypeId": subTypeId},
        dataType: "json",
        beforeSend: function () {
            var myContent = "<tr>" +
                "<td rowspan='4'>" + '正在加载，请稍后' + "<span class='loading'></span></td>" +
                "</tr>";
            $("#yestHourConsumerIncomeCondition tbody").append(myContent);
        },
        success: function (result) {

            $("#yestHourConsumerIncomeCondition tbody").empty();

            if (result != null ) {

                if (result.length > 0) {
                    for (var i = 0; i < result.length; i++) {
                        var myContent;

                        if (result[i].price == null) {
                            myContent = "<tr>" +
                                "<td>" + result[i].customerName + "</td>" +
                                "<td>" + result[i].consumeAccount + "</td>" +
                                "<td>" + '未知' + "</td>" +
                                "<td>" + result[i].amount + "</td>" +
                                "</tr>";
                        } else {
                            myContent = "<tr>" +
                                "<td>" + result[i].customerName + "</td>" +
                                "<td>" + result[i].consumeAccount + "</td>" +
                                "<td>" + result[i].price + "</td>" +
                                "<td>" + result[i].amount + "</td>" +
                                "</tr>";
                        }
                        $("#yestHourConsumerIncomeCondition tbody").append(myContent);
                    }
                } else {
                    var myContent = "<tr>" +
                        "<td rowspan='4'>" + '无消费记录' + "</td>" +
                        "</tr>";
                    $("#yestHourConsumerIncomeCondition tbody").append(myContent);
                }
            } else {
                var myContent = "<tr>" +
                    "<td rowspan='4'colspan=\"4\">" + '无消费记录' + "</td>" +
                    "</tr>";
                $("#yestHourConsumerIncomeCondition tbody").append(myContent);
            }

        }
    });
}





function yesterdayHourVendorCostCondition(typeId, subTypeId) {

    // $("#yestConsumerIncomeCondition").empty();
    /*加载消费数据*/
    $.ajax({
        type: "post",
        url: "/operation/get-yest-hour-vendor-cost-condition",
        data: {"typeId": typeId, "subTypeId": subTypeId},
        dataType: "json",
        beforeSend: function () {
            var myContent = "<tr>" +
                "<td rowspan='4'>" + '正在加载，请稍后' + "<span class='loading'></span></td>" +
                "</tr>";
            $("#yestHourVendorCostCondition tbody").append(myContent);
        },
        success: function (result) {

            $("#yestHourVendorCostCondition tbody").empty();

            if (result != null) {

                if (result.length > 0) {
                    for (var i = 0; i < result.length; i++) {
                        var myContent;

                        if (result[i].cost == null) {
                            myContent = "<tr>" +
                                "<td>" + result[i].vendorName + "</td>" +
                                "<td>" + result[i].consumeAccount + "</td>" +
                                "<td>" + '未知' + "</td>" +
                                "<td>" + result[i].amount + "</td>" +
                                "</tr>";
                        } else {
                            myContent = "<tr>" +
                                "<td>" + result[i].vendorName + "</td>" +
                                "<td>" + result[i].consumeAccount + "</td>" +
                                "<td>" + result[i].cost + "</td>" +
                                "<td>" + result[i].amount + "</td>" +
                                "</tr>";
                        }
                        $("#yestHourVendorCostCondition tbody").append(myContent);
                    }
                } else {
                    var myContent = "<tr>" +
                        "<td rowspan='4'>" + '无消费记录' + "</td>" +
                        "</tr>";
                    $("#yestHourVendorCostCondition tbody").append(myContent);
                }
            } else {
                var myContent = "<tr>" +
                    "<td rowspan='4'colspan=\"4\">" + '无消费记录' + "</td>" +
                    "</tr>";
                $("#yestHourVendorCostCondition tbody").append(myContent);
            }

        }
    });
}

