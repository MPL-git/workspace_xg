<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp" %>

<html>
<head>
    <title>公司资质未完善</title>

    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
    <script type="text/javascript">
        var listConfig = {
            url: "/contractCertification/listArchiveProcessingData.shtml",
            listGrid: {
                columns: [
                    {
                        display: '开店日期', name: 'cooperateBeginDate', width: 120, render: function (rowdata, rowindex) {
                            if (rowdata.cooperateBeginDate) {
                                return new Date(rowdata.cooperateBeginDate).format("yyyy-MM-dd");
                            } else {
                                return "";
                            }
                        }
                    },
                    {display: '招商对接人', name: 'zsContact', width: 100},
                    {display: '商家序号', name: 'mchtCode', width: 100},
                    {display: '公司名称', name: 'companyName', width: 150},
                    {display: '店铺名称', name: 'shopName', width: 150},
                    {
                        display: '公司/经营信息',
                        name: 'OPER1',
                        width: 100,
                        align: 'center',
                        render: function (rowdata, rowindex) {
                            var html = [];
                            html.push("<a href=\"javascript:viewMchtInfo(" + rowdata.mchtId + ");\">查看</a>&nbsp;&nbsp;");
                            return html.join("");
                        }
                    },
                    {
                        display: '财务信息',
                        name: 'OPER2',
                        width: 100,
                        align: 'center',
                        render: function (rowdata, rowindex) {
                            var html = [];
                            html.push("<a href=\"javascript:viewFinanceInfo(" + rowdata.mchtId + ");\">查看</a>&nbsp;&nbsp;");
                            return html.join("");
                        }
                    },
                    {
                        display: '公司资质归档状态',
                        name: 'OPER4',
                        width: 100,
                        align: 'center',
                        render: function (rowdata, rowindex) {
                            var html = [];
                            var companyInfoArchiveStatus = rowdata.companyInfoArchiveStatus;
                            var archiveStatusDesc;
                            if (!companyInfoArchiveStatus || companyInfoArchiveStatus == 0) {
                                archiveStatusDesc = "<span style='color:red;'>【未齐全】</span>";
                            } else {
                                archiveStatusDesc = "<span style='color:red;'>【已归档】</span>";
                            }
                            html.push(archiveStatusDesc);
                            html.push("<a href=\"javascript:viewCompanyInfoArchive(" + rowdata.mchtId + ");\">查看</a>&nbsp;&nbsp;");
                            return html.join("");
                        }
                    },
                    {display: '法务对接人', name: 'fwContact', width: 100}
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
        }
    </script>
</head>
<body style="padding: 0px; overflow: hidden;">
<div class="l-loading" style="display: block" id="pageloading"></div>
<div id="toptoolbar"></div>
<form id="dataForm" runat="server">
    <input type="hidden" name="companyInfoArchiveStatus" value="0">
    <div class="search-pannel">
        <div class="search-tr">
            <div class="search-td">
                <div class="search-td-label">商家序号：</div>
                <div class="search-td-condition">
                    <input type="text" name="mcht_code" id="mchtCode">
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">名称：</div>
                <div class="search-td-condition">
                    <input type="text" name="mcht_name" id="mchtName">
                </div>
            </div>

            <div class="search-td-search-btn" style="display: inline-flex;">
                <div id="searchbtn" style="height: 28px;line-height: 28px;">
                  		搜索
                </div>
                <div style="padding-left: 10px;">
                    <input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="导出" id="export">
                </div>
            </div>
        </div>
    </div>

    <div id="maingrid" style="margin: 0; padding: 0"></div>
</form>

<script type="text/javascript">
    // 查看公司/经营信息
    function viewMchtInfo(id) {
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

    // 查看财务信息
    function viewFinanceInfo(id) {
        $.ligerDialog.open({
            height: $(window).height(),
            width: $(window).width() - 200,
            title: "商家财务信息",
            name: "INSERT_WINDOW",
            url: "${pageContext.request.contextPath}/mcht/mchtFinanceInfoEdit.shtml?mchtId=" + id,
            showMax: true,
            showToggle: false,
            showMin: false,
            isResize: true,
            slide: false,
            data: null
        });
    }

    // 公司资质归档状态查看
    function viewCompanyInfoArchive(mchtId) {
        $.ligerDialog.open({
            height: $(window).height() * 0.9,
            width: $(window).width() * 0.9,
            title: "公司资质归档",
            name: "INSERT_WINDOW",
            url: "${pageContext.request.contextPath}/mchtContract/viewCompanyInfoArchive.shtml?mchtId=" + mchtId + "&hideRadio=1",
            showMax: true,
            showToggle: false,
            showMin: false,
            isResize: true,
            slide: false,
            data: null,
            id: "closeReload"
        });
    }

    $(function () {
        $("#closeReload .l-dialog-close").live("click", function () {
            $("#searchbtn").click();
        });

        $("#export").bind('click', function () {
            var params = $("#dataForm").serialize();
            location.href = "${pageContext.request.contextPath}/contractCertification/companyUnfinished/export.shtml?" + + params;
        });
    });
</script>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>