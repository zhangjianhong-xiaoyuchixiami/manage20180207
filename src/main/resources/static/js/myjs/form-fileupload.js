var FormFileUpload = function () {


    return {
        //启动模块的主要功能
        init: function () {

            // 初始化jQuery文件上传小部件:
            $('#fileupload').fileupload({
                // 取消以下发送的跨域cookie:
                xhrFields: {withCredentials: true},
                url: ''
            });

            // Load existing files:
            // Demo settings:
            $.ajax({
                // 取消以下发送的跨域cookie:
                xhrFields: {withCredentials: true},
                url: $('#fileupload').fileupload('option', 'url'),
                dataType: 'json',
                context: $('#fileupload')[0],
                maxFileSize: 5000000,
                acceptFileTypes: /(\.|\/)(xls|xlsx)$/i,
                process: [{
                        action: 'load',
                        fileTypes: /^excel\/(xls|xlsx)$/,
                        maxFileSize: 20000000 // 20MB
                    }, {
                        action: 'resize',
                        maxWidth: 1440,
                        maxHeight: 900
                    }, {
                        action: 'save'
                    }
                ]
            }).done(function (result) {
                $(this).fileupload('option', 'done')
                    .call(this, null, {
                    result: result
                });
            });

            // 使用支持CORS支持的浏览器上传服务器状态检查:
            if ($.support.cors) {
                $.ajax({
                    url: '',
                    type: 'HEAD'
                }).fail(function () {
                    $('<span class="alert alert-error"/>')
                        .text('Upload server currently unavailable - ' +
                        new Date())
                        .appendTo('#fileupload');
                });
            }

            // initialize uniform checkboxes  
            App.initUniform('.fileupload-toggle-checkbox');
        }

    };

}();