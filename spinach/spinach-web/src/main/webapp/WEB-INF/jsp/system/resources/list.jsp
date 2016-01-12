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
    <title>菜单列表</title>
</head>
<body class="pos-r">

<div >
    <div>
        <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>系统管理 <span class="c-gray en">&gt;</span> 菜单列表 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    </div>
    <div class="pos-a" style="width:150px;left:0; height:100%; border-right:1px solid #e5e5e5; background-color:#f5f5f5">
        <ul id="treeDemo" class="ztree">
        </ul>
    </div>
    <div style="margin-left: 150px;">
    <div class="pd-20">
        <jsp:include page="info.jsp"/>
    </div>
    </div>
</div>
<jsp:include page="../../common/foot.jsp"></jsp:include>
<script type="text/javascript" src="${path}/static/js/lib/zTree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
    var setting = {
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
        $("#fm").jsonData(treeNode);
        $("#fm").find("input").not("[type='reset']").prop("disabled",true);
        $("#fm").find("select").prop("disabled",true);
        $("#resources_edit").removeClass("disabled").addClass("btn-secondary").prop("disabled",false);
    }

    var initTree = function(){
        $.getJSON("${path}/resources/list.json",function(result){
            var t = $("#treeDemo");
            t = $.fn.zTree.init(t, setting, result.list);
        });
    }
    $(document).ready(function(){
       initTree();
    });

</script>
</body>
</html>