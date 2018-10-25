/**
 * 项目JS主入口
 * 以依赖Layui的layer和form模板未例
 * */
layui.define(['layer', 'form'],function (exports) {
    var layer = layer.layer
    ,form = layer.form;
    //layer.msg('hello world');
    exports('index',{});//注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});
