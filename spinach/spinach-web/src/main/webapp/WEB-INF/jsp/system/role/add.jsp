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
		<form action="${path}/role/save" method="post"
			class="form form-horizontal" id="fm">
			<input type="hidden" id="userId" value="${id}" name="id"> <input
				type="hidden" value="${userType}" name="userType">
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>角色名：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${role.name }"
						placeholder="角色名" id="user-name" name="name" datatype="*2-16"
						nullmsg="用户名不能为空">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>角色编码：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${role.roleCode}"
						placeholder="角色编码" id="user-name" name="roleCode" datatype="*2-16"
						nullmsg="昵称不能为空">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl" id="password">
				<label class="form-label col-3"><span class="c-red">*</span>角色描述：</label>
				<div class="formControls col-5">
					<input type="text" name="description"
						value="${role.description }" class="input-text" />
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>排序：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${role.sort }"
						 name="sort" datatype="n"/>
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">是否启用：</label>
				<div class="formControls col-5">
					<span class="select-box" style="width: 150px;"> <select
						class="select" name="disabled" size="1">
							<c:forEach items="${list }" var="l">
								<option value="${l.value }"
									${l.value eq a.disabled ? 'selected' : '' }>${l.label }</option>
							</c:forEach>
					</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<div class="col-9 col-offset-3">
					<input class="btn btn-success radius" type="submit"
						value="&nbsp;&nbsp;提交&nbsp;&nbsp;"> <input
						class="btn btn-default radius" type="button"
						value="&nbsp;&nbsp;返回&nbsp;&nbsp;">
				</div>
			</div>
		</form>

	</div>
	<jsp:include page="../../common/foot.jsp"></jsp:include>

	<script type="text/javascript">
		$(function() {
			var callback = function(data) {
				if (data.status == "0") {
					var index = parent.layer.getFrameIndex(window.name);
					parent.initPage("listForm", "demo", "view", "page");
					parent.layer.close(index);
				} else {
					layer.msg(data.msg);
				}
			};
			$("#fm").validFrom(callback);
		});
	</script>
</body>
</html>