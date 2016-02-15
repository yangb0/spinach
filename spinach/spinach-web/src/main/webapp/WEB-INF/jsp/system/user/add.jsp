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
		<form action="${path}/user/save" method="post"
			class="form form-horizontal" id="fm">
			<input type="hidden" id="userId" value="${id}" name="id"> <input
				type="hidden" value="${userType}" name="userType">
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>用户名：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${a.username }"
						placeholder="用户名" id="user-name" name="username" datatype="*2-16"
						nullmsg="用户名不能为空">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>昵称：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${a.nickName }"
						placeholder="昵称" id="user-name" name="nickName" datatype="*2-16"
						nullmsg="昵称不能为空">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl" id="password">
				<label class="form-label col-3"><span class="c-red">*</span>初始密码：</label>
				<div class="formControls col-5">
					<input type="text" autocomplete="off" name="password"
						value="123456" class="input-text" datatype="*6-20"
						nullmsg="密码不能为空"> <span>默认密码为123456</span>
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>手机：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${a.mobile }"
						placeholder="" id="user-tel" name="mobile" datatype="m"
						nullmsg="手机不能为空">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>邮箱：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" placeholder="@" name="email"
						id="email" datatype="e" nullmsg="请输入邮箱！" value="${a.email }">
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
			if ($("#userId").val() != 0) {
				$("#password").remove();
			}
			var callback = function(data) {
				layer.msg(data.msg);
				if (data.status == "0") {
					var index = parent.layer.getFrameIndex(window.name);
					parent.initPage("listForm", "demo", "view", "page");
					parent.layer.close(index);
				}
			};
			$("#fm").validFrom(callback);
		});
	</script>
</body>
</html>