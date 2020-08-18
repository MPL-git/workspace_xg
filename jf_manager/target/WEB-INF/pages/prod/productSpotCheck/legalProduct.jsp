<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp" %>

<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
    <link href="${pageContext.request.contextPath}/css/newSearch_form.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript">
        var listConfig = {

            url: "/product/productSpotCheckData.shtml",

            listGrid: {
                columns: [{display: '商家序号', name: 'mchtCode', width: 200},
                    {display: '商家名称', name: 'name', width: 180, width: 300},
                    {display: '店铺名称', name: 'shopName', width: 180, width: 300},
                    {display: '主营类目', name: 'productType', width: 150},
                    {display: '商管运营', name: 'yy_contact_name', width: 150},
                    {display: '总SKU数', name: 'skuTotal', width: 150},
                    {display: '法务对接人', name: 'totalAuditBy', width: 150},
                ],
                showCheckbox: false,  //不设置默认为 true
                showRownumber: true //不设置默认为 true
            },
            container: {
                toolBarName: "toptoolbar",
                searchBtnName: "searchbtn",
                fromName: "dataForm",
                listGridName: "maingrid"
            },

        }
        function serach() {
            var productType = $("#productType").val();
            var flag = true;
            if (productType === "0") {
                flag = false;
                commUtil.alertError("请先选择主营类目");
                return flag;
            }
            var mchtSalesRank = $("#mchtSalesRank").val();
            if (mchtSalesRank == "" || mchtSalesRank > 50 || mchtSalesRank < 1) {
                flag = false;
                commUtil.alertError("请正确填写商家总销量排行前（X）名，但不能超过50");
                return flag;
            }
        }

        function spotCheckExport() {
            var productType = $("#productType").val();
            var flag = true;
            if (productType === "0") {
                flag = false;
                commUtil.alertError("请先选择主营类目");
                return flag;
            }
            var mchtSalesRank = $("#mchtSalesRank").val();
            if (mchtSalesRank == "" || mchtSalesRank > 50 || mchtSalesRank < 1) {
                flag = false;
                commUtil.alertError("请正确填写商家总销量排行前（X）名，但不能超过50");
                return flag;
            }

            if (flag) {
                $("#dataForm").attr("action", "${pageContext.request.contextPath}/product/spotCheck/spotCheckExport.shtml");
                $("#dataForm").submit();
            }
        }

    </script>
</head>
<body style="padding: 0px; overflow: hidden;">
<div class="l-loading" style="display: block" id="pageloading"></div>
<form id="dataForm" method="post" action=""><%--runat="server"--%>
    <div class="search-pannel">
        <div class="search-tr">

            <div class="search-td">
                <div class="search-td-label">主营类目：</div>
                <div class="search-td-condition">
                    <select id="productType" name="productType">
                        <option value="0">==请选择==</option>
                        <c:forEach items="${productTypeList}" var="productType" varStatus="vs">
                            <option value="${productType.id}"> ${productType.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">商家总销量排行：</div>
                <div class="search-td-condition">
                    <input type="text" placeholder="请输入1到50的数字" id="mchtSalesRank" name="mchtSalesRank" onkeyup="value=value.replace(/[^(\d)]/g,'')"/>
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label" style="color: red">对接人：</div>
                <div class="search-td-condition">
                    <select id="platContactStaffId" name="platContactStaffId">
                        <c:if test="${isManagement == 1}">
                            <option value="" selected="selected">全部商家</option>
                        </c:if>
                        <option value="${staffID }" selected="selected" >我自己</option>
                        <c:forEach items="${sysStaffInfoCustomList }" var="sysStaffInfoCustom">
                            <option value="${sysStaffInfoCustom.staffId }">${sysStaffInfoCustom.staffName }</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">商家合作状态：</div>
                <div class="search-td-condition">
                    <select id="mchtStatus" name="mchtStatus">
                        <option value="1">正常</option>
                    </select>
                </div>
            </div>
            <div class="search-td-export-btn">
                <%--                    <div onclick="spotCheckExport()"id="export">导出</div>--%>
                <div style="padding-left: 10px;">
                    <input type="button" style="width: 50px;height: 25px;cursor: pointer;" value="导出"
                           onclick="spotCheckExport()" ; id="export">
                </div>
            </div>
            <div class="search-td-search-btn">
                <div id="searchbtn" onclick="serach()">搜索</div>
            </div>


        </div>
    </div>

    <div id="maingrid" style="margin: 0; padding: 0"></div>
</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
