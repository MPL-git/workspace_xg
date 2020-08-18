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
        $(function() {
            $("#search").bind('click',function(){
                var reg = new RegExp("^[1-9]\\d*$");
                if(!reg.test($("#beginProductCount").val()) && !$("#beginProductCount").val() == ""){
                    commUtil.alertError("商家销量输入错误，请重新输入");
                    return false;
                }
                if (!reg.test($("#endProductCount").val()) && !$("#endProductCount").val() == ""){
                    commUtil.alertError("商家销量输入错误，请重新输入");
                    return false;
                }
                    $("#searchbtn").click();
            });

        });


        //根据一级类目联动二级类目
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
            url: "/mchtData/commoditySaleStatisticsList.shtml",
            btnItems: [],
            listGrid: {
                columns: [
                    {display: '商家序号', name: 'mchtCode', align: 'center', isSort: false, width: 180},
                    {display: '公司名称', name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
                        // return "<a href='javascript:;' onclick='editMchtBaseInfo(" + rowdata.id + ");'>"+rowdata.companyName+"</a>";
                            return rowdata.companyName;
                        }},
                    {display: '店铺名称', name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
                        // return "<a href=\"http://m.xgbuy.cc/webApp/xgbuy/views/index.html?redirect_url=mall/seller/index.html?fromPlatform=1&id="+ rowdata.id +"\" target=\"_blank\">"+ rowdata.shopName +"</a>";
                            return rowdata.shopName;
                        }},
                    {
                        display: '近三个月商品销量',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 180,
                        render: function (rowdata, rowindex) {
                            var html = [];
                            if (!rowdata.saleCount || rowdata.saleCount === '') {
                                return html.join("");
                            }else {
                                var str = rowdata.saleCount+ "<br>"
                                html.push(str)
                            }
                            return html.join("");
                        }
                    },
                    {
                        display: '近三个月一级类目商品销量',
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
                                str += typeArr[2] + ")<br>";
                                html.push(str);
                            }
                            return html.join("");
                        }
                    },
                    {
                        display: '近三个月二级类目商品销量',
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
                                str += typeArr[2] + ")<br>";
                                html.push(str);
                            }
                            return html.join("");
                        }
                    },

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
                <div class="search-td-label">商家名称：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="shopName" name="shopName">
                </div>
            </div>

            <div class="search-td" style="width: 40%;">
                <div class="search-td-label" style="float: left;width: 20%;">近三个月商品销量：</div>
                <div class="l-panel-search-item" >
                    <input type="text" id="beginProductCount" name="beginProductCount" />
                </div>
                <div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
                <div class="l-panel-search-item">
                    <input type="text" id="endProductCount" name="endProductCount" />
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
                 <%-- <div id="searchbtn">搜索</div>--%>
                 <div id="search" class="l-button">搜索</div>
                 <input type="hidden" id="searchbtn">
            </div>
        </div>
    </div>
</form>

<div id="maingrid" style="margin: 0;"></div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
