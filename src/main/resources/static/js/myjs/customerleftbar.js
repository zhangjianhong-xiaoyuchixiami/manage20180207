/**
 * Created by jonhn on 2017/1/16.
 */

var CustomerLeftBar = function () {

    return {

        //main function to initiate the module
        init: function () {
            $(document).ready(function() {
                $('#customerBalance').addClass('active');

                $('#customerListBalanceLog').addClass('active');

                $('#customerBalanceSelect').addClass('selected');

                $('#customerBalanceArrow').addClass('arrow open');
            });

        }
    };

}();