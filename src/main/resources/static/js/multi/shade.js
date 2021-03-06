/**
 * Created by jonhn on 2017/6/16.
 */


(function(){
    $.extend($.fn,{
        mask: function(msg,maskDivClass){
            this.unmask();
            // 参数
            var op = {
                opacity: 0.8,
                z: 1055,
                bgcolor: '#ccc'
            };
            var original=$(document.body);
            var position={top:0,left:0};
            if(this[0] && this[0]!==window.document){
                original=this;
                position=original.position();
            }
            // 创建一个 Mask 层，追加到对象中
            var maskDiv=$('<div class="maskdivgen" aria-hidden="true">&nbsp;</div>');
            maskDiv.appendTo(original);
            var maskWidth=original.outerWidth();
            if(!maskWidth){
                maskWidth=original.width();
            }
            var maskHeight=document.body.scrollHeight;
            if(!maskHeight){
                maskHeight=original.height();
            }
            maskDiv.css({
                position: 'absolute',
                top: position.top,
                left: position.left,
                'z-index': op.z,
                width: maskWidth,
                height:maskHeight,
                'background-color': op.bgcolor,
                opacity: 0
            });
            if(maskDivClass){
                maskDiv.addClass(maskDivClass);
            }
            if(msg){

                var msgDiv=$('<div style="position:absolute;padding:2px;background:#ccca;text-align: center"><div style="line-height:24px;border:#a3bad9 1px solid;background:white;padding:2px 10px 2px 10px;">'+msg+'<span class="loading"></span></div></div>');

                //var msgDiv=$('<div style="position:absolute;"><div class="cube" style="line-height:24px;border:#a3bad9 1px solid;background:white;padding:2px 10px 2px 10px;"><div class="tg-qe-progress-bar myactive"><div class="bar"></div></div></div></div>');

                msgDiv.appendTo(maskDiv);
                var widthspace=(maskDiv.width()-msgDiv.width());
                var heightspace=(maskDiv.height()-msgDiv.height());
                msgDiv.css({
                    cursor:'wait',
                    top:(heightspace/2-2),
                    left:(widthspace/2-2)
                });
            }
            maskDiv.fadeIn('fast', function(){
                // 淡入淡出效果
                $(this).fadeTo('slow', op.opacity);
            });
            return maskDiv;
        },
        unmask: function(){
            var original=$(document.body);
            if(this[0] && this[0]!==window.document){
                original=$(this[0]);
            }
            original.find("> div.maskdivgen").fadeOut('slow',0,function(){
                $(this).remove();
            });
        }
    });
})();

function closeProgress() {
    $(document).unmask()
}

function openProgress() {
    $(document).mask('正在处理,请稍后').click(function () {
        $(document).unmask()
    })
}