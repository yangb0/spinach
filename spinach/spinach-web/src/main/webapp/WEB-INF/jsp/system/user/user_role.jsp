<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML>
<html>
<jsp:include page="../../common/head.jsp"></jsp:include>
</head>
<body>
<div class="pd-20">
    <form action="${path}/user/saveRole.json" method="post"
          class="form form-horizontal" id="fm">
        <input type="hidden" id="accountId" value="${account.id}" name="accountId">

        <div class="row cl">
            <label class="form-label col-3">用户名：</label>

            <div class="formControls col-5">
                <input type="text" class="input-text" value="${account.username }"
                       id="user-name" readonly="readonly">
            </div>
            <div class="col-4"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>角色：</label>

            <div class="formControls col-5">
                <c:forEach items="${roList}" var="r">
                    <p>
                        <input type="checkbox" value="${r.id}"  ${fn:contains(userRoIds,r.id ) ? 'checked' : ''} name="roleIds">${r.name}
                    </p>
                </c:forEach>
            </div>
            <div class="col-4"></div>
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
    $(function () {
        var callback = function (data) {
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