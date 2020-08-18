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
    function saveColor() {
        var decorateModuleId = $("#decorateModuleId").val();
        if (decorateModuleId == "" || decorateModuleId == null) {
            $.ligerDialog.error("模块id异常,请联系管理员")
            return;
        }
        {
        var bgColor = $("#bgColor").val();
        if (bgColor == "" || bgColor == null) {
            $.ligerDialog.error("请输入背景颜色")
            return;
        }
        if (bgColor.substring(0,1)!= '#'){
            $.ligerDialog.error("请输入正确的背景颜色代码")
            return;
        }
        var fieldFontColor = $("#fieldFontColor").val();
        if (fieldFontColor == "" || fieldFontColor == null) {
            $.ligerDialog.error("请输入栏位字体颜色")
            return;
        }
        if (fieldFontColor.substring(0,1)!= '#'){
            $.ligerDialog.error("请输入正确的栏位字体颜色代码")
            return;
        }
        var fieldBgColor = $("#fieldBgColor").val();
        if (fieldBgColor == "" || fieldBgColor == null) {
            $.ligerDialog.error("请输入栏位背景颜色")
            return;
        }
        if (fieldBgColor.substring(0,1)!= '#'){
            $.ligerDialog.error("请输入正确的栏位背景颜色代码")
            return;
        }
        var fieldSelectFontColor = $("#fieldSelectFontColor").val();
        if (fieldSelectFontColor == "" || fieldSelectFontColor == null) {
            $.ligerDialog.error("请输入选中字体颜色")
            return;
        }
        if (fieldSelectFontColor.substring(0,1)!= '#'){
            $.ligerDialog.error("请输入正确的选中字体颜色代码")
            return;
        }
        }
        $.ajax({
            url : "${pageContext.request.contextPath}/customPage/saveColor.shtml",
            type : 'POST',
            dataType : 'json',
            async : false,
            data : {"decorateModuleId":decorateModuleId,"bgColor":bgColor,"fieldFontColor":fieldFontColor,"fieldBgColor":fieldBgColor,"fieldSelectFontColor":fieldSelectFontColor},
            success : function(data) {
                if(data.returnCode == '0000') {
                    $.ligerDialog.success("保存成功")
                    setTimeout(function () {
                        javascript:location.reload();
                    },1000)
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
    <input type="hidden" id="decorateModuleId" name="decorateModuleId" value="${decorateModuleId}">
</div>
<%--<br>--%>
<div id="categorys">
    背景颜色<input type="text" id="bgColor" name="bgColor" style="margin-left: 34px;" value="${decorateModule.bgColor}" placeholder="请输入颜色代码" maxlength="8"><br><br>
    栏位字体颜色<input type="text" id="fieldFontColor" name="fieldFontColor" style="margin-left: 10px;" value="${decorateModule.fieldFontColor}" placeholder="请输入颜色代码" maxlength="8"><br><br>
    栏位背景颜色<input type="text" id="fieldBgColor" name="fieldBgColor" style="margin-left: 10px;" value="${decorateModule.fieldBgColor}" placeholder="请输入颜色代码" maxlength="8"><br><br>
    选中字体颜色<input type="text" id="fieldSelectFontColor" name="fieldSelectFontColor" style="margin-left: 10px;" value="${decorateModule.fieldSelectFontColor}" placeholder="请输入颜色代码" maxlength="8"><br><br>
    <input type="button" style="margin-left: 80px;" onclick="saveColor()" value="保存">
</div>
</body>
</html>
