<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<div class="menu_dropdown bk_2">
	<c:forEach items="${resources }" var="r">
		<c:if test="${r.pid==0 }">
			<dl >
				<dt>
					<i class="Hui-iconfont">${r.icon }</i> ${r.name }<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<c:forEach items="${resources }" var="rr">
							<c:if test="${rr.pid==r.id }">
								<li><a _href="${path }${rr.url }" href="javascript:void(0)">${rr.name }</a></li>
							</c:if>
						</c:forEach>
					</ul>
				</dd>
			</dl>
		</c:if>
	</c:forEach>

</div>