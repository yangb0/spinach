/**
 * 自定义jquery函数
 */
(function($) {
	// 从json对象中获取值填入form表单中
	$.fn.jsonData = function(data) {
		var $name = $(this).find("[name]");
		$name.each(function() {
			var name = $(this).attr("name");
			$(this).val(data[name]);
		});
	};
	// 表单验证提交 配合validation.js使用
	$.fn.validFrom = function(callback) {
		$(this).Validform({
			tiptype : 3,
			ajaxPost : true,
			postonce : true,
			callback : function(data) {
				$.Hidemsg();
				callback(data);
			}
		});
	}
})(jQuery);
