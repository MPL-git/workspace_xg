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
        // 查看一级分类商品
        function viewType1List(mchtCode, type1Id, type1Name) {
            $.ligerDialog.open({
                height: 800,
                width: 1500,
                title: "商品列表",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/product/productIndex.shtml?auditStatus=23&saleType=1&status=1&mchtCode=" + mchtCode +"&type1Id=" + type1Id+"&type1Name=" + encodeURI(encodeURI(type1Name)),
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }
        
     	// 查看二级分类商品
        function viewType2List(mchtCode, type2Id, type2Name) {
            $.ligerDialog.open({
                height: 800,
                width: 1500,
                title: "商品列表",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/product/productIndex.shtml?auditStatus=23&saleType=1&status=1&mchtCode=" + mchtCode +"&type2Id=" + type2Id+"&type2Name=" + encodeURI(encodeURI(type2Name)),
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }
     	
   		// 查看商品品牌商品
        function viewBrandList(mchtCode, brandName) {
            $.ligerDialog.open({
                height: 800,
                width: 1500,
                title: "商品列表",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/product/productIndex.shtml?auditStatus=23&saleType=1&status=1&mchtCode=" + mchtCode +"&productBrandName=" + encodeURI(encodeURI(brandName)),
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }

        //获取二级类目
        function productTypelist() {
            var productTypeId = $("#type1Id").val();
            if (productTypeId == '') {
                var option = [];
                option.push('<option value="">请选择...</option>');
                $("#type2Id").html(option.join(''));
                $("#type2Id").attr("disabled", "disabled");
            } else {
                $.ajax({
                    type: 'post',
                    url: '${pageContext.request.contextPath}/activityNew/productTypelist.shtml',
                    dataType: 'json',
                    data: {productTypeId:productTypeId},
                    success: function (data) {
                        if (data.code == 200) {
                            var option = [];
                            option.push('<option value="">请选择...</option>');
                            for (var i = 0; i < data.productTypeList.length; i++) {
                                option.push('<option value="' + data.productTypeList[i].id + '">' + data.productTypeList[i].name + '</option>');
                            }
                            $("#type2Id").html(option.join(''));
                            $("#type2Id").attr("disabled", "");
                        } else {
                            commUtil.alertError(data.msg);
                        }
                    },
                    error: function (e) {
                        commUtil.alertError('操作超时，请稍后再试！');
                    }
                });
            }
        }

        //查看商家
        function editMchtBaseInfo(id) {
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

        var listConfig = {
            url: "/product/mchtProductFormData.shtml",
            btnItems: [],
            listGrid: {
                columns: [
                    {display: '商家序号', name: 'mchtCode', align: 'center', isSort: false, width: 180},
                    {display: '商家名称', name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
                        return "<a href='javascript:;' onclick='editMchtBaseInfo(" + rowdata.id + ");'>"+rowdata.companyName+"</a>";
                    }},
                    {display: '店铺名称', name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
                        return "<a href=\"http://m.xgbuy.cc/webApp/xgbuy/views/index.html?redirect_url=mall/seller/index.html?fromPlatform=1&id="+ rowdata.id +"\" target=\"_blank\">"+ rowdata.shopName +"</a>";
                    }},
                    {
                        display: '商家一级类目商品数',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 180,
                        render: function (rowdata, rowindex) {
                            var html = [];
                            if (!rowdata.type1Str || rowdata.type1Str === '') {
                                return html.join("");
                            }
                            var productTypeArr = rowdata.type1Str.split(';');
                            for (var i = 0; i < productTypeArr.length; i++) {
                                var typeStr = productTypeArr[i];
                                var typeArr = typeStr.split(',');
                                var str = typeArr[1] + "(";
                                str += '<a href="javascript:void(0);" onclick="viewType1List(\'' + rowdata.mchtCode + '\',\'' + typeArr[0] + '\',\'' + typeArr[1] + '\')">';
                                str += typeArr[2] + "</a>)<br>";
                                html.push(str);
                            }
                            return html.join("");
                        }
                    },
                    {
                        display: '商家二级类目商品数',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 180,
                        render: function (rowdata, rowindex) {
                            var html = [];
                            if (!rowdata.type2Str || rowdata.type2Str === '') {
                                return html.join("");
                            }
                            var productTypeArr = rowdata.type2Str.split(';');
                            for (var i = 0; i < productTypeArr.length; i++) {
                                var typeStr = productTypeArr[i];
                                var typeArr = typeStr.split(',');
                                var str = typeArr[1] + "(";
                                str += '<a href="javascript:void(0);" onclick="viewType2List(\'' + rowdata.mchtCode + '\',\'' + typeArr[0] + '\',\'' + typeArr[1] + '\')">';
                                str += typeArr[2] + "</a>)<br>";
                                html.push(str);
                            }
                            return html.join("");
                        }
                    },
                    {
                        display: '品牌商品数',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 180,
                        render: function (rowdata, rowindex) {
                            var html = [];
                            if (!rowdata.brandStr || rowdata.brandStr === '') {
                                return html.join("");
                            }
                            var productBrandArr = rowdata.brandStr.split(';');
                            for (var i = 0; i < productBrandArr.length; i++) {
                                var brandStr = productBrandArr[i];
                                var typeArr = brandStr.split(',');
                                var str = typeArr[0] + "(";
                                str += '<a href="javascript:void(0);" onclick="viewBrandList(\'' + rowdata.mchtCode + '\',\'' + typeArr[0] + '\')">';
                                str += typeArr[1] + "</a>)<br>";
                                html.push(str);
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
<!-- <div id="toptoolbar"></div> -->
<form id="dataForm" runat="server">
    <div class="search-pannel">
        <div class="search-tr">

            <div class="search-td">
                <div class="search-td-label">商家序号：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="mchtCode" name="mchtCode">
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">店铺名称：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="shopName" name="shopName">
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">品牌：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="productBrandName" name="productBrandName">
                </div>
            </div>

        </div>
    </div>

    <div class="search-pannel">
        <div class="search-tr">
            <div class="search-td">
                <div class="search-td-label">一级类目：</div>
                <div class="search-td-combobox-condition">
                    <select id="type1Id" name="type1Id" style="width: 135px;" onchange="productTypelist();">
                        <option value="">请选择...</option>
                        <c:forEach var="productType" items="${productTypeList}">
                            <option value="${productType.id}">
                                    ${productType.name }
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">二级类目：</div>
                <div class="search-td-combobox-condition">
                    <select id="type2Id" name="type2Id" style="width: 135px;" disabled>
                        <option value="">请选择...</option>
                    </select>
                </div>
            </div>
            <div class="search-td-search-btn">
                <div id="searchbtn">搜索</div>
            </div>
        </div>
    </div>
</form>

<div id="maingrid" style="margin: 0;"></div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
