<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
<jsp:include page="../../common/head.jsp"></jsp:include>
</head>
<body>
<div class="pd-20">
	<form action="${path}/dict/save" method="post" class="form form-horizontal" id="fm">
	<input type="hidden" id="id" value="${dict.id}" name="id">
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>字段：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="${dict.targetColumn }" placeholder="对应表字段"  name="targetColumn" datatype="*2-16" nullmsg="请输入表字段">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>显示内容：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="${dict.label }" placeholder="" id="dict-name" name="label" datatype="*2-16" nullmsg="请输入显示内容">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>值：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="${dict.value }" placeholder="" id="dict-tel" name="value"  datatype="n" nullmsg="请输入对应值">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">描述：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  name="descrption"  value="${dict.descrption }">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">备注：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  name="remark" value="${dict.remark }">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">是否启用：</label>
			<div class="formControls col-5"> <span class="select-box" style="width:150px;">
			 <select class="select" name="disabled" size="1">
				<c:forEach items="${list }" var="l">
					<option value="${l.value }"
						${l.value eq dict.disabled ? 'selected' : '' }>${l.label }
					</option>
				</c:forEach>
				</select>
				</span> 
				</div>
		</div>
		<div class="row cl">
			<div class="col-9 col-offset-3">
				<input class="btn btn-success radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				<input class="btn btn-default radius" type="button" value="&nbsp;&nbsp;返回&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</div>
<jsp:include page="../../common/foot.jsp"></jsp:include>
 
<script type="text/javascript">
$(function(){
	var callback = function(data) {
		layer.msg(data.msg);
		if (data.status == "0") {
			var index = parent.layer.getFrameIndex(window.name);
			parent.initPage("listForm","demo", "view", "page");
			parent.layer.close(index);
		}
	};
	$("#fm").validFrom(callback);
});
</script>
</body>
</html>