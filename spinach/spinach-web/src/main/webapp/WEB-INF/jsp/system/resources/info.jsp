<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<span class="l">
    <shiro:hasPermission name="sys:perm:add">
        <a href="javascript:void(0);" id="resources_add"
           class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
            添加</a>
    </shiro:hasPermission>
<shiro:hasPermission name="sys:perm:update">
    <a href="javascript:void(0);" id="resources_edit" disabled="disabled"
                   class="btn disabled  radius"><i class="Hui-iconfont Hui-iconfont-edit"></i>
    修改</a>
</shiro:hasPermission>
</span>
<form action="${path}/resources/save" method="post"
      class="form form-horizontal" id="fm">
    <input type="hidden" id="userId" value="" name="id">
    <div class="row cl">
        <label class="form-label col-3"><span class="c-red">*</span>父级菜单：</label>
        <div class="formControls col-5">
            <span class="select-box" style="width: 150px;">
           <select  name="pid" class="select" disabled="disabled">
               <option value="0"> </option>
               <c:forEach items="${pList}" var="l">
                   <option value="${l.id}">${l.name}</option>
               </c:forEach>
           </select>
                </span>
        </div>
        <div class="col-4"></div>
    </div>
    <div class="row cl">
        <label class="form-label col-3"><span class="c-red">*</span>名称：</label>
        <div class="formControls col-5">
            <input type="text" class="input-text"
                   placeholder="菜单名称"  name="name" datatype="*2-16"  disabled="disabled"
                   nullmsg="菜单名称不能为空">
        </div>
        <div class="col-4"></div>
    </div>
    <div class="row cl" id="password">
        <label class="form-label col-3"><span class="c-red">*</span>菜单类型：</label>
        <div class="formControls col-5">
            <span class="select-box" style="width: 150px;">
            <select name="type" class="select"  disabled="disabled">
                <c:forEach items="${disabledList }" var="l">
                    <option value="${l.value }">${l.label }</option>
                </c:forEach>
            </select>
                </span>
        </div>
        <div class="col-4"></div>
    </div>

    <div class="row cl">
        <label class="form-label col-3">url：</label>
        <div class="formControls col-5">
            <input type="text" name="url" datatype="*0-30" class="input-text"  disabled="disabled"/>
        </div>
    </div>
    <div class="row cl">
        <label class="form-label col-3"><span class="c-red">*</span>排序：</label>
        <div class="formControls col-5">
            <input type="text" class="input-text"  disabled="disabled"
                   name="sort" datatype="n"/>
        </div>
        <div class="col-4"></div>
    </div>
    <div class="row cl">
        <label class="form-label col-3"><span class="c-red">*</span>菜单编码：</label>
        <div class="formControls col-5">
            <input type="text" class="input-text"  disabled="disabled"
                   name="permission" datatype="*2-20"/>
        </div>
        <div class="col-4"></div>
    </div>
    <div class="row cl">
        <label class="form-label col-3">是否启用：</label>
        <div class="formControls col-5">
					<span class="select-box" style="width: 150px;">
                        <select  class="select" name="disabled" size="1" disabled="disabled">
                        <c:forEach items="${disabledList }" var="l">
                            <option value="${l.value }">${l.label }</option>
                        </c:forEach>
                    </select>
					</span>
        </div>
    </div>
    <div class="row cl">
        <label class="form-label col-3">描述：</label>
        <div class="formControls col-5">
            <textarea type="text" rows="5" name="description" datatype="*0-200" class="input-text"  disabled="disabled" ></textarea>
        </div>
        <div class="col-4"></div>
    </div>
    <div class="row cl">
        <div class="col-9 col-offset-3">
            <input class="btn btn-success radius" type="submit"
                   value="&nbsp;&nbsp;提交&nbsp;&nbsp;" disabled="disabled"> <input
                class="btn btn-default radius" type="reset"
                value="&nbsp;&nbsp;重置&nbsp;&nbsp;">
        </div>
    </div>
</form>
<script type="text/javascript">
    $(function() {
        var callback = function(data) {
            layer.msg(data.msg);
            if (data.status == "0") {
                initTree();
                $("#fm input[type='reset']").click();
            } else {
            }
        };
        $("#fm").validFrom(callback);
    });
    $("#fm input[type='reset']").click(function(){
        $("#fm input[type='hidden']").val("");
        $("#fm").find("input").not("[type='reset']").prop("disabled",true);
        $("#fm").find("select").prop("disabled",true);
        $("#fm").find("textarea").prop("disabled",true);
        $("#resources_edit").removeClass("btn-secondary").addClass("disabled").prop("disabled",true);
    });
    $("#resources_add").click(function(){
        $("#fm input[type='reset']").click();
        $("#fm").find("input").prop("disabled",false);
        $("#fm").find("select").prop("disabled",false);
        $("#fm").find("textarea").prop("disabled",false);
        $("#resources_edit").removeClass("btn-secondary").addClass("disabled").prop("disabled",true);
    });
    $("#resources_edit").click(function(){
        $("#fm").find("input").prop("disabled",false);
        $("#fm").find("select").prop("disabled",false);
        $("#fm").find("textarea").prop("disabled",false);
    });
</script>