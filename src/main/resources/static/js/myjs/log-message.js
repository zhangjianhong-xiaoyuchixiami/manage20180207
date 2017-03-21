/**
 * Created by jonhn on 2017/3/21.
 */
var LogLeftBar = function () {

    return {

        //main function to initiate the module
        init: function () {
            $(document).ready(function() {
                $('#logManage').addClass('active');

                $('#logMessage').addClass('active');

                $('#customerBalanceSelect').addClass('selected');

                $('#customerBalanceArrow').addClass('arrow open');
            });

        }
    };

}();