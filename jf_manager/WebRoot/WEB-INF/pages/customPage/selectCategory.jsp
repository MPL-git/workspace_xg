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
    $(function () {
        if (${productModuleTypes == null}){
            $.ligerDialog.error("请设置添加类目后再选择类目")
            setTimeout(function () {
                frameElement.dialog.close();
            },1000)
        }
    })

    function saveSelectCategory() {
        var decorateModuleId = $("#decorateModuleId").val();
        var categorys = $("#categorys").val();
        var productIds = $("#productIds").val();
        var moduleItemId = $("#moduleItemId").val();
        if (decorateModuleId == "" || decorateModuleId == null) {
            $.ligerDialog.error("模块id异常,请联系管理员")
            return;
        }
        if (categorys == "" || categorys == null) {
            $.ligerDialog.error("请选择类目,如果已经选中,请联系管理员")
            return;
        }
        $.ajax({
            url : "${pageContext.request.contextPath}/customPage/saveSelectCategory.shtml",
            type : 'POST',
            dataType : 'json',
            async : false,
            data : {"decorateModuleId":decorateModuleId,"categorys":categorys,"productIds":productIds,"moduleItemId":moduleItemId},
            success : function(data) {
                if(data.returnCode == '0000') {
                    commUtil.alertSuccess("添加成功");
                    setTimeout(function () {
                        parent.location.reload();
                        frameElement.dialog.close();
                    },1000)
                }else {
                    $.ligerDialog.error(data.returnMsg)
                    commUtil.alertError("添加商品成功,设置类目失败,请在商品模块重设类目或者联系管理员");
                }
            },
            error : function(e) {
                commUtil.alertError("添加商品成功,设置类目失败,请在商品模块重设类目或者联系管理员");
            }
        });
    }

</script>

<body>
<div>
    <input type="hidden" id="decorateModuleId" name="decorateModuleId" value="${decorateModuleId}">
    <input type="hidden" id="productIds" name="productIds" value="${productIds}">
    <input type="hidden" id="moduleItemId" name="moduleItemId" value="${moduleItemId}">
</div>
<br>
<div>

    <select id="categorys" name="categorys">
        <c:forEach items="${productModuleTypes}" var="productModuleType"><option value="${productModuleType.id}">${productModuleType.name}</option></c:forEach>
    </select>
    <br>
    <br>
    <input type="button" style="margin-left: 50px" onclick="saveSelectCategory()" value="保存">
</div>
</body>
</html>
