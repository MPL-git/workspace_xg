<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/common/common-tag.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css"/>

    <style type="text/css">

    </style>

</head>
<script type="text/javascript">

    function add() {
        $("#categorys").append("<input type=\"text\" style=\"margin-left: 10px;\" onblur=\"saveCate(this)\" placeholder=\"请输入分类名称\">&nbsp;&nbsp;<a href=\"javascript:removeCate()\">删除</a>&nbsp;&nbsp;<a href=\"javascript:upSort()\">上移</a>&nbsp;&nbsp;<a href=\"javascript:downSort()\">下移</a><br><br>")
    }

    function saveCate(e,seqNo,id) {
        var decorateModuleId = $("#decorateModuleId").val();
        if (decorateModuleId == "" || decorateModuleId == null) {
            $.ligerDialog.error("模块id异常,请联系管理员")
            return;
        }
        var name = e.value;
        if (name == "" || name == null) {
            $.ligerDialog.error("分类名称不能为空")
            return;
        }
        if (id == "" || id == null){
            var data = {"decorateModuleId" : decorateModuleId,"name":name};
        } else {
            var data = {"decorateModuleId" : decorateModuleId,"name":name,"id":id};
        }
        $.ajax({
            url : "${pageContext.request.contextPath}/customPage/saveCategory.shtml",
            type : 'POST',
            dataType : 'json',
            async : false,
            data : data,
            success : function(data) {
                if(data.returnCode == '0000') {
                    javascript:location.reload();
                }else {
                    $.ligerDialog.error(data.returnMsg)
                }
            },
            error : function(e) {
                $.ligerDialog.error('操作超时，请稍后再试！');
            }
        });
    }

    function removeCate(id) {
        var decorateModuleId = $("#decorateModuleId").val();
        if (decorateModuleId == "" || decorateModuleId == null) {
            $.ligerDialog.error("模块id异常,请联系管理员")
            return;
        }
        $.ajax({
            url : "${pageContext.request.contextPath}/customPage/updateCategory.shtml?type=0",
            type : 'POST',
            dataType : 'json',
            async : false,
            data : {"id":id,"decorateModuleId":decorateModuleId},
            success : function(data) {
                if(data.returnCode == '0000') {
                    javascript:location.reload();
                }else {
                    $.ligerDialog.error(data.returnMsg)
                }
            },
            error : function(e) {
                $.ligerDialog.error('操作超时，请稍后再试！');
            }
        });
    }

    function upSort(id) {
        var decorateModuleId = $("#decorateModuleId").val();
        if (decorateModuleId == "" || decorateModuleId == null) {
            $.ligerDialog.error("模块id异常,请联系管理员")
            return;
        }
        $.ajax({
            url : "${pageContext.request.contextPath}/customPage/updateCategory.shtml?type=1",
            type : 'POST',
            dataType : 'json',
            async : false,
            data : {"id":id,"decorateModuleId":decorateModuleId},
            success : function(data) {
                if(data.returnCode == '0000') {
                    javascript:location.reload();
                }else {
                    $.ligerDialog.error(data.returnMsg)
                }
            },
            error : function(e) {
                $.ligerDialog.error('操作超时，请稍后再试！');
            }
        });
    }

    function downSort(id) {
        var decorateModuleId = $("#decorateModuleId").val();
        if (decorateModuleId == "" || decorateModuleId == null) {
            $.ligerDialog.error("模块id异常,请联系管理员")
            return;
        }
        $.ajax({
            url : "${pageContext.request.contextPath}/customPage/updateCategory.shtml?type=2",
            type : 'POST',
            dataType : 'json',
            async : false,
            data : {"id":id,"decorateModuleId":decorateModuleId},
            success : function(data) {
                if(data.returnCode == '0000') {
                    javascript:location.reload();
                }else {
                    $.ligerDialog.error(data.returnMsg)
                }
            },
            error : function(e) {
                $.ligerDialog.error('操作超时，请稍后再试！');
            }
        });
    }

</script>

<body>
<div>
    <input type="button" onclick="add()" style="margin-left: 10px;height: 18px;width: 60px" value="新增">
    <input type="hidden" id="decorateModuleId" name="decorateModuleId" value="${decorateModuleId}">
</div>
<br>
<div id="categorys">
<%--    <input type="text" style="margin-left: 10px;" onblur="saveCate(this)" placeholder="请输入分类名称" value="${productModuleType.name}">&nbsp;&nbsp;<a href="javascript:;">删除</a>&nbsp;&nbsp;<a href="javascript:;">上移</a>&nbsp;&nbsp;<a href="javascript:;">下移</a>--%>
    <c:forEach var="productModuleType" items="${productModuleTypes}">
    <input type="text" style="margin-left: 10px;" onblur="saveCate(this,${productModuleType.seqNo},${productModuleType.id})" value="${productModuleType.name}" placeholder="请输入分类名称">&nbsp;&nbsp;<a href="javascript:removeCate(${productModuleType.id})">删除</a>&nbsp;&nbsp;<c:if test="${firstId != productModuleType.id}"><a href="javascript:upSort(${productModuleType.id})">上移</a>&nbsp;&nbsp;</c:if><c:if test="${lastId != productModuleType.id}"><a href="javascript:downSort(${productModuleType.id})">下移</a></c:if><br><br>
    </c:forEach>
</div>
</body>
</html>
