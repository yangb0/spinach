<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>

<meta charset="utf-8">
<title>登录(Login)</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->
<link rel="stylesheet" href="${path}/static/css/reset.css">
<link rel="stylesheet" href="${path}/static/css/supersized.css">
<link rel="stylesheet" href="${path}/static/css/login.css">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
<style type="text/css">
.Huialert-danger, .Huialert-error { #
	fff: #b94a48;
	background-color: #f37b1d;
	border-color: #e56c0c
}

.Huialert-danger h4, .Huialert-error h4 {
	color: #b94a48
}
</style>
</head>

<body>

	<div class="page-container">
		<h1>登录(Login)</h1>
		<form action="${path}/login.json" method="post" id="demoform">
			<div class="Huialert Huialert-error" style="display: none;">
				<i class="icon-remove"></i>错误状态提示
			</div>
			<input type="text" name="username" class="username"
				placeholder="请输入您的用户名！" value="demo">
			<input type="password" name="password" class="password"
				placeholder="请输入您的用户密码！" value="123456">
			<!--                 <input type="Captcha" class="Captcha" name="Captcha" placeholder="请输入验证码！"> -->
			<button type="submit" class="submit_button">登录</button>
			<div class="error">
				<span>+</span>
			</div>
		</form>
	</div>

	<!-- Javascript -->
	<script src="${path}/static/js/jquery/jquery-1.8.1.min.js"></script>
	<script src="${path}/static/js/jquery/jquery.form.js"></script>
	<script src="${path}/static/js/supersized.3.2.7.min.js"></script>
	<script src="${path}/static/js/supersized-init.js"></script>
	<script type="text/javascript">
		if (top.location !== self.location) {
			alert("登陆超时,请重新登录!");
			top.location=self.location;
		}
	</script>
	<script type="text/javascript">

		$('.page-container form .username, .page-container form .password')
				.blur(
						function() {
							$(".page-container form").parent().find('.error')
									.fadeOut('fast');
							$(".Huialert-error").hide();
						});
		$(".page-container form ").ajaxForm(
				{
					beforeSerialize : function() {
						var username = $(".page-container form").find(
								'.username').val();
						var password = $(".page-container form").find(
								'.password').val();
						if (username == '') {
							$(".page-container form").find('.error').css("top",
									"27px").fadeOut('fast', function() {
							});
							$(".page-container form").find('.error').fadeIn(
									'fast',
									function() {
										$(".page-container form").parent()
												.find('.username').focus();
									});
							return false;
						}

						if (password == '') {
							$(".page-container form").find('.error').css("top",
									"96px").fadeOut('fast', function() {
							});
							$(".page-container form").find('.error').fadeIn(
									'fast',
									function() {
										$(".page-container form").parent()
												.find('.password').focus();
									});
							return false;
						}
						$(".page-container form input[type='submit']").prop("disabled",true);//点击后禁用,避免重复提交
					},
					data : $(".page-container form ").serialize(),
					success : function(result) {
						var result = eval(result);
						if (result && result.status == 0) {
							window.location.href = "${path}/";
						} else {
							$(".Huialert-error").html(result.msg).show();
							$(".page-container form input[type='submit']").prop("disabled",false);
						}
					}
				});
	</script>
</body>

</html>

