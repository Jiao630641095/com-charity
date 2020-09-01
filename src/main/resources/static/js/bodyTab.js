
layui.define(["element","jquery"],function(exports){
	var element = layui.element,
		$ = layui.jquery,
		Tab = function(){
			this.tabConfig = {
				closed : true,
				openTabNum : 10,  //最大可打开窗口数量
				tabFilter : "bodyTab",  //添加窗口的filter
				url : undefined  //获取菜单json地址
			}
		};
	//参数设置
	Tab.prototype.set = function(option) {
		var _this = this;
		$.extend(true, _this.tabConfig, option);
		return _this;
	};


	var bodyTab = new Tab();
	exports("bodyTab",function(option){
		return bodyTab.set(option);
	});
})
