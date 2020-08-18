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
        $(function () {
            $(".dateEditor").ligerDateEditor({
                showTime: true,
                format: "yyyy-MM-dd hh:mm:ss",
                labelAlign: 'left',
                width: 160
            });

        });
        var submiting;

        function submitForm() {
            if (submiting) {
                return false;
            }

            console.log($("#preheatTime").val())
            if ($("#preheatTime").val().length == 0 && $("#activityBeginTime").val().length == 0 && $("#activityEndTime").val().length == 0 && $("#activityGroupId").val().length == 0) {
                commUtil.alertError("请至少选择一项进行修改！");
                return;
            }
            if (!activityTime()) {
                return;
            }

            submiting = true;
            $.ajax({
                url: "${pageContext.request.contextPath}/activityNew/saveBatchSchedule.shtml",
                type: 'POST',
                dataType: 'json',
                data: $("#form1").serialize(),
                success: function (data) {
                    submiting = false;
                    if ("0000" == data.returnCode) {
                        $.ligerDialog.success("批量排期成功。\n", function () {
                            //parent.location.reload();
                            frameElement.dialog.close();
                        });
                    } else {
                        $.ligerDialog.error(data.returnMsg);
                    }
                    //submitFlag = true;
                },
                error: function () {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

        //排期时间验证
        function activityTime() {
            var flag = true;
            var preheatTime = $("#preheatTime").val();
            console.log(preheatTime == "" || preheatTime.length != 0);
            var activityBeginTime = $("#activityBeginTime").val();
            var activityEndTime = $("#activityEndTime").val();
            if (preheatTime >= activityBeginTime && preheatTime.length != 0 && activityBeginTime.length != 0) {
                commUtil.alertError("预热时间必须小于活动开始时间！");
                flag = false;
                return flag;
            } else if (activityBeginTime >= activityEndTime && activityBeginTime.length != 0 && activityEndTime.length != 0) {
                commUtil.alertError("活动开始时间必须小于活动结束时间！");
                flag = false;
                return flag;
            } else if (new Date(activityEndTime) <= new Date && activityEndTime.length != 0) {
                commUtil.alertError("活动结束时间必须大于现在时间！");
                flag = false;
                return flag;
            } else {
                return flag;
            }
        }


    </script>

</head>
<body style="margin: 10px;">
<form name="form1" class="form1" method="post" id="form1" enctype="multipart/form-data">
    <input type="hidden" id="activityIds" name="activityIds" value="${ids}">
    <table class="gridtable">
        <tr>
            <td class="title" width="25%">预热：</td>
            <td align="left" class="l-table-edit-td table-edit-activity-time">
                <input type="text" class="dateEditor" id="preheatTime" name="preheatTime">
            </td>
        </tr>
        <tr>
            <td class="title" width="25%">活动开始时间：</td>
            <td align="left" class="l-table-edit-td table-edit-activity-time">
                <input type="text" class="dateEditor" id="activityBeginTime" name="activityBeginTime">
            </td>
        </tr>
        <tr>
            <td class="title" width="25%">活动结束时间：</td>
            <td align="left" class="l-table-edit-td table-edit-activity-time">
                <input type="text" class="dateEditor" id="activityEndTime" name="activityEndTime">
            </td>
        </tr>
        <tr>
            <td class="title" width="25%">特卖分组：</td>
            <td align="left" class="l-table-edit-td">
                <select style="width: 135px;" id="activityGroupId" name="activityGroupId">
                    <option value="">请选择...</option>
                    <c:forEach var="activityGroup" items="${activityGroupList }">
                        <option value="${activityGroup.id }">
                                ${activityGroup.id }.${activityGroup.groupName }
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td class="title" width="20%">操作</td>
            <td align="left" class="l-table-edit-td">
                <input type="button" onClick="submitForm();" class="l-button l-button-submit" value="提交"/>
                <input style="margin-left: 20px;" class="l-button" type="button" value="关闭"
                       onclick="frameElement.dialog.close()"/>
            </td>
        </tr>

    </table>
</form>
</body>
</html>