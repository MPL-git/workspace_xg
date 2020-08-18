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
            url: "/coupon/specialMchtList.shtml",
            btnItems: [
                {
                    text: '添加商家',
                    icon: 'add',
                    id: 'add',
                    dtype: 'win',
                    click: itemclick,
                    url: "/coupon/addSpecialMchtPage.shtml",
                    seqId: "",
                    width: 600,
                    height: 300
                }, {
                    text: '批量添加',
                    icon: 'add',
                    id: 'add',
                    click: function () {
                        var data = listModel.gridManager.getSelectedRows();
                        if (data.length == 0) {
                            $.ligerDialog.alert('请选择行');
                        } else {
                            var str = "";
                            var num = 0;
                            $(data).each(function () {
                                if (str == '') {
                                    str = this.mchtId;
                                    num++;
                                } else {
                                    str += "," + this.mchtId;
                                    num++;
                                }
                            });
                            batchAdd(str, num);
                        }
                        return;
                    }
                }, {
                    text: '批量删除',
                    icon: 'delete',
                    id: 'delete',
                    click: function () {
                        var data = listModel.gridManager.getSelectedRows();
                        if (data.length == 0) {
                            $.ligerDialog.alert('请选择行');
                        } else {
                            var str = "";
                            var num = 0;
                            $(data).each(function () {
                                if (str == '') {
                                    str = this.id;
                                    num++;
                                } else {
                                    str += "," + this.id;
                                    num++;
                                }
                            });
                            batchDel(str, num);
                        }
                        return;
                    }
                },
            ],
            listGrid: {
                columns: [
                    {display: '商家序号', name: 'mchtCode', width: 150},
                    {
                        display: '公司名称', name: 'companyName', width: 200, render: function (rows, rowindex) {
                            var html = [];
                            html.push("<a href=\"javascript:getMchtBaseInfo(" + rows.mchtId + ");\">" + rows.companyName + "</a>");
                            return html.join("");
                        }
                    },
                    {display: '商家名称', name: 'shopName', width: 200},
                    {display: '主营类目', name: 'productType', width: 150},
                    {
                        display: '商家状态', name: 'status', width: 100, render: function (rows, rowindex) {
                            if (rows.status == '1') {
                                return '正常'
                            } else {
                                return '状态异常'
                            }
                        }
                    },
                    {display: '最低基数服务费率', name: 'commissionRate', width: 150},
                    {
                        display: '更新时间', name: 'updateDate', width: 200, render: function (rows, rowindex) {
                            if (rows.updateDate != null) {
                                var date = new Date(rows.updateDate);
                                return date.getFullYear() + "-" + ("0" + (date.getMonth() + 1)).slice(-2) + "-" + ("0" + date.getDate()).slice(-2) + " " + ("0" + date.getHours()).slice(-2) + ":" + ("0" + date.getMinutes()).slice(-2) + ":" + ("0" + date.getSeconds()).slice(-2);
                            }
                        }
                    },
                    {
                        display: '更新人', name: 'updateBy', width: 200, render: function (rows, rowindex) {
                            if (rows.updateBy != null) {
                                return rows.updateBy
                            }
                        }
                    },
                    {
                        display: '管理',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 180,
                        render: function (rows, rowindex) {
                            var html = [];
                            if (rows.type === "added") {
                                html.push("<a id=" + "A" + rows.mchtId + " href=\"javascript:add(" + rows.mchtId + ");\" >添加</a>&nbsp&nbsp");
                                html.push("<a id=" + "B" + rows.mchtId + " style=\"display:none;\" href=\"javascript:add(" + rows.mchtId + ");\" >已添加</a>&nbsp&nbsp");
                            } else {
                                html.push("<a id=" + "A" + rows.mchtId + " href=\"javascript:del(" + rows.id + "," + rows.mchtId + ");\" >删除</a>&nbsp&nbsp");
                                html.push("<a id=" + "B" + rows.mchtId + " style=\"display:none;\" href=\"javascript:del(" + rows.id + "," + rows.mchtId + ");\" >已删除</a>&nbsp&nbsp");
                            }
                            html.push("<a href=\"javascript:log(" + rows.mchtId + ");\">日志</a>");
                            return html.join("");
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

        function getMchtBaseInfo(id) {
            $.ligerDialog.open({
                height: $(window).height() - 40,
                width: $(window).width() - 40,
                title: "商家基础资料",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/mcht/mchtBaseInfoEdit.shtml?mchtId=" + id,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }

        function batchAdd(ids, num) {
            if (confirm("选中" + num + "个商家,是否添加")) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/coupon/batchAddSpecialMchtPage.shtml",
                    type: 'POST',
                    dataType: 'json',
                    data: {"ids": ids},
                    success: function (data) {
                        if ("0000" == data.returnCode) {
                            listModel.ligerGrid.url = '${pageContext.request.contextPath}/coupon/specialMchtList.shtml?type=added';
                            listModel.initdataPage();
                            $.ligerDialog.success("添加成功")
                        } else {
                            $.ligerDialog.error(data.returnMsg);
                            if (data.idSuccess != null || data.idError != null) {
                                $.ligerDialog.error(data.mchtIdSuccess + "已经添加成功," + data.mchtIdError + "添加失败.");
                            }
                        }
                    },
                    error: function () {
                        $.ligerDialog.error('操作超时，请稍后再试！');
                    }
                });
            }
        }

        function batchDel(ids, num) {
            if (confirm("选中" + num + "个商家,是否删除")) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/couPon/batchDelSpecialMcht.shtml",
                    type: 'POST',
                    dataType: 'json',
                    data: {"ids": ids},
                    success: function (data) {
                        if ("0000" == data.returnCode) {
                            listModel.ligerGrid.url = '${pageContext.request.contextPath}/coupon/specialMchtList.shtml';
                            listModel.initdataPage();
                            $.ligerDialog.success("删除成功")
                        } else {
                            $.ligerDialog.error(data.returnMsg);
                            if (data.idSuccess != null || data.idError != null) {
                                $.ligerDialog.error(data.idSuccess + "已经删除成功," + data.idError + "删除失败.");
                            }
                        }
                    },
                    error: function () {
                        $.ligerDialog.error('操作超时，请稍后再试！');
                    }
                });
            }
        }

        function searchNotAdded() {
            listModel.ligerGrid.url = '${pageContext.request.contextPath}/coupon/specialMchtList.shtml?type=added';
            listModel.initdataPage();
        }

        function searchAdded() {
            listModel.ligerGrid.url = '${pageContext.request.contextPath}/coupon/specialMchtList.shtml';
            listModel.initdataPage();
        }

        function add(mchtId) {
            var a = "#A" + mchtId;
            var b = "#B" + mchtId;
            $.ajax({
                url: "${pageContext.request.contextPath}/couPon/addSpecialMcht.shtml",
                type: 'POST',
                dataType: 'json',
                data: {"mchtId": mchtId},
                success: function (data) {
                    if ("0000" == data.returnCode) {
                        $(a).hide();
                        $(b).show();
                        // $.ligerDialog.success("添加成功")
                    } else {
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error: function () {
                    $.ligerDialog.error('系统繁忙，请稍后再试！');
                }
            });
        }

        function del(id, mchtId) {
            var a = "#A" + mchtId;
            var b = "#B" + mchtId;
            $.ajax({
                url: "${pageContext.request.contextPath}/couPon/delSpecialMcht.shtml",
                type: 'POST',
                dataType: 'json',
                data: {"id": id},
                success: function (data) {
                    if ("0000" == data.returnCode) {
                        $(a).hide();
                        $(b).show();
                        // $.ligerDialog.success("删除成功")
                    } else {
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error: function () {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

        function log(mchtId) {
            $.ligerDialog.open({
                height: $(window).height() - 300,
                width: $(window).width() - 600,
                title: "日志",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/coupon/specialMchtStatusLogPage.shtml?mchtId=" + mchtId,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }

    </script>

</head>

<body style="padding: 0px; overflow: hidden;">
<div class="l-loading" style="display: block" id="pageloading"></div>
<div id="toptoolbar"></div>
<form id="dataForm" runat="server">
    <div class="search-pannel">
        <div class="search-tr">
            <div class="search-td">
                <div class="search-td-label" style="float:left;">商家序号</div>
                <div class="l-panel-search-item">
                    <input type="text" id="mchtCode" name="mchtCode">
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label" style="float:left;">名称</div>
                <div class="l-panel-search-item">
                    <input type="text" id="name" name="name">
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label" style="float:left;">主营类目</div>
                <div class="l-panel-search-item">
                    <select id="productType" name="productType">
                        <option value="0">==请选择==</option>
                        <c:forEach items="${productTypeList}" var="productType" varStatus="vs">
                            <option value="${productType.id}"> ${productType.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label" style="float:left;">技术服务费率低于等于</div>
                <div class="l-panel-search-item">
                    <input type="text" id="fee" name="fee">例:0.2
                </div>
            </div>
            <div class="search-td-export-btn">
                <div style="padding-left: 10px;">
                    <input type="button" style="width: 80px;height: 25px;cursor: pointer;" onclick="searchNotAdded()"
                           value="查看未添加">
                </div>
            </div>
            <div class="search-td-export-btn">
                <div style="padding-left: 10px;">
                    <input type="button" style="width: 80px;height: 25px;cursor: pointer;" onclick="searchAdded()"
                           value="查看已添加">
                </div>
            </div>
            <%--            <div class="search-td-search-btn">--%>
            <%--                <div id="searchbtn" onclick="searchAdded()">查看已添加</div>--%>
            <%--            </div>--%>
        </div>
    </div>
    <div id="maingrid" style="margin: 0; padding: 0"></div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>
</html>
