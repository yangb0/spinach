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
			tiptype : 2,
			ajaxPost : true,
			postonce : true,
			callback : function(data) {
				$.Hidemsg();
				callback(data);
			}
		});
	}
})(jQuery);

/** 全屏弹出 */
function full_add(title, url) {
	var index = layer.open({
		type : 2,
		title : title,
		content : url
	});
	layer.full(index);
}

//时间格式化
function dataFormate(fmt,date) { //author: meizz 
	var d = Number(date);
	if(d){
		d=new Date(date); 
	}else{
		return '';
	}
    var o = {
        "M+": d.getMonth() + 1, //月份 
        "d+": d.getDate(), //日 
        "h+": d.getHours(), //小时 
        "m+": d.getMinutes(), //分 
        "s+": d.getSeconds(), //秒 
        "q+": Math.floor((d.getMonth() + 3) / 3), //季度 
        "S": d.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (d.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
/**
 * 返回按钮
 */
$(document).on("click",".btn-default",function(){
	layer_close();
})

/* 弹出窗口 */
function window_add(title, url, w, h) {
	layer_show(title, url, w, h);
}
/**
 * 
 * @param formId 查询条件表单id
 * @param tmplId 模板id
 * @param showDivId 数据需要显示到的元素的id
 * @param pageDivId 分页dom id
 */
function initPage(formId, tmplId, showDivId, pageDivId) {
	var $form = $("#" + formId);
	var $showDiv = $("#" + showDivId);
	var $pageDiv = $("#" + pageDivId);
	var url = $form.attr("action");
	var tmpl = $("#"+tmplId).html();
	$.ajax({
	  dataType: "json",
	  url: url,
	  type:"post",
	  data:$form.serialize(),
	  success: function(data){
		  var totalPage = data.pagination.totalPage;
		  laytpl(tmpl).render(data, function(html) {
				$showDiv.html(html);
			});
		  data=null;
		  if(totalPage>1){//大于1页才显示分页
			  laypage({
				  cont : $pageDiv, // 容器。值支持id名、原生dom对象，jquery对象。
				  pages : totalPage, // 通过后台拿到的总页数
				  curr:  1,
				  jump : function(e,first) { // 触发分页后的回调
					  $form.find("input[name='page']").val(e.curr);
					  if(!first){
						  $.ajax({
							  dataType: "json",
							  url: url,
							  type:"post",
							  data:$form.serialize(),
							  success: function(res){
								  e.pages = e.last = res.pagination.totalPage; // 重新获取总页数，一般不用写
								  laytpl(tmpl).render(res, function(html) {
									  $showDiv.html(html);
								  });
							  }
						  });
					  }
				  }
			  });
		  	}else{
		  		$pageDiv.html('');
		  	}
	  }
	});
}