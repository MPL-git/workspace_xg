<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/common/common-tag.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title></title>
    <link href="${pageContext.request.contextPath}/css/newSearch_form.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>

    <script type="text/javascript">

        var listConfig = {
            url: "/coupon/specialMchtStatusLogs.shtml",
            listGrid: {
                columns: [
                    {display: '更新人', name: 'updateBy', width: 200},
                    {
                        display: '更新时间', name: 'updateDate', width: 200, render: function (rows, rowindex) {
                            if (rows.updateDate != null) {
                                var date = new Date(rows.updateDate);
                                return date.getFullYear() + "-" + ("0" + (date.getMonth() + 1)).slice(-2) + "-" + ("0" + date.getDate()).slice(-2) + " " + ("0" + date.getHours()).slice(-2) + ":" + ("0" + date.getMinutes()).slice(-2) + ":" + ("0" + date.getSeconds()).slice(-2);
                            }
                        }
                    },
                    {
                        display: '操作项目', name: 'status', width: 200, render: function (rows, rowindex) {
                            if (rows.status == '1') {
                                return '添加'
                            }
                            if (rows.status == '2') {
                                return '删除'
                            }
                        }
                    },
                ],
                showCheckbox: true,  //不设置默认为 true
                showRownumber: true //不设置默认为 true
            },
            container: {
                toolBarName: "toptoolbar",
                searchBtnName: "searchbtn",
                fromName: "dataForm",
                listGridName: "maingrid"
            }
        };

    </script>

</head>

<body style="padding: 0px; overflow: hidden;">
<div class="l-loading" style="display: block" id="pageloading"></div>
<div id="toptoolbar"></div>
<form id="dataForm" runat="server">
    <div class="search-pannel">
        <input type="hidden" id="mchtId" name="mchtId" value="${mchtId}">
    </div>
    <div id="maingrid" style="margin: 0; padding: 0"></div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>
</html>
