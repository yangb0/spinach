<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../../common/head.jsp"></jsp:include>
    <link rel="stylesheet" href="${path}/static/js/lib/zTree/zTreeStyle.css" type="text/css">
    <!--[if IE 6]>
    <script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>修改权限</title>
</head>
<body class="pos-r">
<div>
    <div class="row cl">
        <div class="col-9 col-offset-3">
            <input type="hidden" name="id" value="${id}">
            <input class="btn btn-success radius" type="button" id="editPerm_submit"
            value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            <input  class="btn btn-default radius" type="button"
                value="&nbsp;&nbsp;返回&nbsp;&nbsp;">
        </div>
    </div>

    <div class="pos-a" style="width:100%; height:80%; position: absolute;margin-top:5%;background-color:#f0f6e4; ">
            <ul id="treeDemo" class="ztree">
            </ul>
        </div>

</div>
<jsp:include page="../../common/foot.jsp"></jsp:include>
<script type="text/javascript" src="${path}/static/js/lib/zTree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
    var id = $("input[name='id']").val();
    var setting = {
        check: {
            enable: true
        },
        view: {
            showIcon: false
        },
        data: {
            key: {
                url:"myurl"//更改默认的超链接获取属性,取消超链接
            },
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "pid",
                rootPId: ""
            }
        },
        callback: {
            beforeClick: beforeClick,
            onClick: onClick
        }
    };
    function beforeClick(treeId, treeNode, clickFlag) {
        return (treeNode.click != false);
    }
    function onClick(event, treeId, treeNode, clickFlag) {
    }

    var initTree = function(){
        $.getJSON("${path}/role/rolePermission.json?id="+id,function(result){
            var t = $("#treeDemo");
            t = $.fn.zTree.init(t, setting, result.list);
        });
    }
    $("#editPerm_submit").click(function(){
        var $ztree= $.fn.zTree.getZTreeObj("treeDemo");
        var selectNodes = $ztree.getCheckedNodes(true);
        var nodes = [];
        for(var i=0;i<selectNodes.length;i++){
            var selectNode = selectNodes[i];
            nodes.push(selectNode.id);
        }
        nodes =JSON.stringify(nodes);
        nodes = nodes.substr(1,nodes.length-2);
        $.getJSON("${path}/role/savePerm",{"id":id,"rid":nodes},function(result){
            layer.msg(result.msg);
            if(result.status==0){
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
        });
    });
    $(document).ready(function(){
        initTree();
    });

</script>
</body>
</html>