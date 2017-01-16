/**
 * Created by jonhn on 2017/1/16.
 */


/*下拉框*/
jQuery(document).ready(function() {

    $("#years").change(function () {
        var param = $("#years").val();
        var param1 = $("#customerId").val();
        var param2 = $("#typeId").val();

        if (param !=null) {
            $.ajax({
                url: '/finance/find-company-customer-week-uplink-months-by-customer-id',
                data: {"years": param, "customerId": param1, "typeId": param2},
                type: 'post',
                dataType: 'json',
                success: function (data) {
                    if(data != null){
                        $("#months ").empty();
                        $("#months").append("<option value=''>请选择...</option>");
                        for (var i=0; i<data.length; i++){
                            var op=document.createElement("option");
                            op.value=data[i];
                            op.innerHTML='第'+data[i]+'月';
                            $("#months").append(op);
                        }
                    }
                }
            });
        }
    });

    $("#months").change(function () {
        var param = $("#years").val();
        var param1 = $("#customerId").val();
        var param2 = $("#typeId").val();
        var param3 = $("#months").val();
        if (param !=null && param2 != null) {
            $.ajax({
                url: '/finance/find-company-customer-weeks-by-customer-id',
                data: {"years": param, "customerId": param1, "typeId": param2, "months": param3},
                type: 'post',
                dataType: 'json',
                success: function (data) {
                    if(data != null){
                        $("#weeks ").empty();
                        $("#weeks").append("<option value=''>请选择...</option>");
                        for (var i=0; i<data.length; i++){
                            var op=document.createElement("option");
                            op.value=data[i];
                            op.innerHTML='第'+data[i]+'周';
                            $("#weeks").append(op);
                        }
                    }
                }
            });
        }
    });
});

