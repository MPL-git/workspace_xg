<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>

    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

    <script type="text/javascript">

        function submitForm() {
            var reason = $("#reason").val();
            if (reason == null) {
                $.ligerDialog.alert("驳回理由不能为空");
                return;
            }
            var param = $("#form1").serialize();
            $.ajax({
                url: "${pageContext.request.contextPath}/order/sub/batchAuditOrRetrial.shtml",
                type: 'POST',
                dataType: 'json',
                data: param,
                success: function (data) {
                    if (data.returnCode == "0000") {
                        $.ligerDialog.success(data.returnMsg)
                        parent.location.reload();
                        frameElement.dialog.close()
                    } else {
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error: function () {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

        function show() {
            $("#reason").show();
        }
        function hide() {
            $("#reason").hide();
        }

    </script>

</head>
<body style="margin: 10px;">
<form name="form1" class="form1" action="" method="post" id="form1" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${id}">
    <input type="hidden" name="ids" value="${ids}">
    <table class="gridtable">
        <c:if test="${type==2}">
            <tr>
                <td class="title" width="20%">
                    <span style="color:red;">*</span>审核结果
                </td>
                <td align="left" class="l-table-edit-td">
                    <input type="radio" class="auditStatus" name="auditStatus" onclick="hide()" value="3" checked>审核通过
                    <input style="margin-left: 20px;" type="radio" class="auditStatus" name="auditStatus"
                           onclick="show()" value="4">审核驳回
                </td>
            </tr>
        </c:if>

        <tr height="100px">
            <c:if test="${type==2}">
                <td class="title" width="20%" required=true>驳回理由</td>
            </c:if>
            <c:if test="${type==1}">
                <td class="title" width="20%" required=true>理由</td>
            </c:if>
            <td align="left" class="l-table-edit-td">
                <select id="reason" name="reason" <c:if test="${type==2}">style="display: none"</c:if>>
                    <c:forEach var="list" items="${sysStatusLst}">
                        <option value="${list.statusValue}">${list.statusDesc}
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td class="title" width="20%">操作</td>
            <td align="left" class="l-table-edit-td">
                <input type="button" onClick="submitForm();" class="l-button l-button-submit" value="保存"/>
                <input style="margin-left: 20px;" class="l-button" type="button" value="取消"
                       onclick="frameElement.dialog.close()"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>