<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>

 <ul id="pagination_"></ul>
</div>
<input type="hidden" id="totalPage__" value="${pagination.totalPage }">
<script type="text/javascript">
$(function(){
	var _serialize=$("#fm").serialize();
	$('#pagination_').twbsPagination({
		totalPages : $("#totalPage__").val(),
		visiblePages:10,
		first:'首页',
		prev:'上一页',
		next:'下一页',
		last:'尾页',
		href:'?page={{number}}&'+_serialize
	});
});
</script>