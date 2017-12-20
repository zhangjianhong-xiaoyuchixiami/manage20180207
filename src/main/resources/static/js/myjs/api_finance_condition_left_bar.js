/**
 * Created by jonhn on 2017/1/16.
 */

var ApiFinanceConditionLeftBar = function () {

    return {

        init: function () {
            $(document).ready(function() {
                $('#operation_manage').addClass('active');

                $('#product_condition').addClass('active');

                $('#operation_manage_select').addClass('selected');

                $('#operation_manage_arrow').addClass('arrow open');
            });

        }
    };

}();