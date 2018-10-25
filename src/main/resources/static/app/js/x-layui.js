
(function ($,window) {
    "use strict";

    //ajax提交操作，带确认提示，用于ajax删除单个记录
    $("*[data-tiggle='ajax']").click(function () {
        var dataUrl = $(this).attr("data-sumbit-url");
        var dataConfirm = $(this).attr("data-confirm");

        $.confirm({
            type:'red',
            closeIcon:true,
            title:'警告',
            content:dataConfirm ? dataConfirm : '确认操作？',
            buttons: {
                '确认':{
                    btnClass:'btn-blue',
                    action:function () {
                        $.post(dataUrl,{},function (json) {
                            if(json.code == 200){
                                window.Location.reload();
                            }else{
                                $.alert({
                                    title:'提示',
                                    content:json.msg,
                                    buttons:{"好的":{btnClass:'btn-blue'}}
                                });
                            }
                        });
                    }
                },
                '取消':{}
            }
        })
    });

})(jquery.window);