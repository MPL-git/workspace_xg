<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>

    <script type="text/javascript">
        // 审核/查看
        function view(id) {
            $.ligerDialog.open({
                height: 800,
                width: 1500,
                title: "投诉信息",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/propertyRightComplain/complainManageDetail.shtml?id=" + id,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }

        var listConfig = {
            url: "/propertyRightComplain/complainManageData.shtml",
            btnItems: [],
            listGrid: {
                columns: [
                    {
                        display: '申诉时间', width: 150, render: function (rowdata, rowindex) {
                            if (rowdata.createDate != null) {
                                var createDate = new Date(rowdata.createDate);
                                return createDate.format("yyyy-MM-dd hh:mm:ss");
                            }
                        }
                    },
                    {display: '商家序号', name: 'mchtCode', align: 'center', isSort: false, width: 140},
                    {display: '申诉商家', name: 'companyName', align: 'center', isSort: false, width: 140},
                    {display: '投诉ID', name: 'rightAppealId', align: 'center', isSort: false, width: 140},
                    {display: '投诉类型', name: 'appealTypeDesc', align: 'center', isSort: false, width: 140},
                    {display: '相关商品ID/店铺名称', name: 'relevantValue', align: 'center', isSort: false, width: 140},
                    {display: '处理人', name: 'staffName', align: 'center', isSort: false, width: 140},
                    {display: '转发状态', name: 'appealTypeDesc', align: 'center', isSort: false, width: 140},
                    {
                        display: '转发状态',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 180,
                        render: function (rowdata, rowindex) {
                            var html = [];
                            var status = rowdata.status;
                            if (status != null && status != '') {
                                var statusDesc = status == '1' ? '待审核' : '';
                                html.push(statusDesc);
                            }
                            return html.join("");
                        }
                    },
                    {
                        display: '操作',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 150,
                        render: function (rowdata, rowindex) {
                            var html = [];
                            var acceptStatus = rowdata.acceptStatus;
                            if (acceptStatus == '1' && rowdata.staffId == ${sessionScope.USER_SESSION.staffID}) {
                                html.push("<a href=\"javascript:view(" + rowdata.id + ");\">转发</a>");
                            }
                            return html.join("");
                        }
                    }
                ],
                showCheckbox: false,  //不设置默认为 true
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
<form id="dataForm" runat="server">
    <div class="search-pannel">
        <div class="search-tr">
            <div class="search-td">
                <div class="search-td-label">投诉类型：</div>
                <div class="search-td-combobox-condition">
                    <select id="appealType" name="appealType" style="width: 135px;">
                        <option value="">请选择...</option>
                        <c:forEach items="${appealTypeList}" var="appealType">
                            <option value="${appealType.statusValue}">${appealType.statusDesc}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">相关商品ID/店铺名称：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="relevantValue" name="relevantValue">
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">投诉ID：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="rightAppealId" name="rightAppealId">
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">商家名称：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="companyName" name="companyName">
                </div>
            </div>

            <div class="search-td-search-btn">
                <div id="searchbtn">搜索</div>
            </div>
        </div>
    </div>

    <div class="search-pannel">
        <div class="search-tr">

            <div class="search-td">
                <div class="search-td-label" style="float:left;">提审日期：</div>
                <div class="l-panel-search-item">
                    <input type="text" id="create_date_begin" name="createDateBegin"/>
                    <script type="text/javascript">
                        $(function () {
                            $("#create_date_begin").ligerDateEditor({
                                showTime: false,
                                labelWidth: 150,
                                width: 150,
                                labelAlign: 'left'
                            });
                        });
                    </script>
                </div>
                <div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
            </div>

            <div class="search-td">
                <div class="l-panel-search-item">
                    <input type="text" id="create_date_end" name="createDateEnd"/>
                    <script type="text/javascript">
                        $(function () {
                            $("#create_date_end").ligerDateEditor({
                                showTime: false,
                                labelWidth: 150,
                                width: 150,
                                labelAlign: 'left'
                            });
                        });
                    </script>
                </div>
            </div>

            <div class="search-td">
                <div class="l-panel-search-item">
                    <div class="search-td-label">处理人：</div>
                    <div class="search-td-combobox-condition">
                        <select id="staffId" name="staffId" style="width: 135px;">
                            <option value="">请选择...</option>
                            <c:forEach items="${staffList}" var="staffInfo">
                                <c:if test="${staffInfo.status == 'A'}">
                                    <option value="${staffInfo.staffId}">${staffInfo.staffName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<div id="maingrid" style="margin: 0;"></div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
