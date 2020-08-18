<%@ page language="java" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="${pageContext.request.contextPath}/css/glyphicon.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css"/>

<style type="text/css">
    .color-1 {
        color: #9D999D;
    }

    .color-2 {
        color: #FC0000;
    }

    .color-3 {
        color: #EFD104;
    }

    .color-4 {
        color: #00FC28;
    }

    .color-5 {
        color: #0351F7;
    }

    .color-6 {
        color: #DF00FB;
    }

    .l-box-select .l-box-select-table td {
        font-size: 12px;
        line-height: 18px;
    }
</style>
<script type="text/javascript">
    var viewerPictures;
    $(function () {
        viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {
            hide: function () {
                $("#viewer-pictures").hide();
            }
        });

        $("#serviceType").change(function () {
            var serviceType = $("#serviceType").val();
            $("#proStatus").empty();
            getProStatus(serviceType);
        });

        var dateArray = [];
        var defaultProductTypeIds = [];
        <c:if test="${not empty sysStaffProductTypeList }" >
        var sysStaffProductTypeList = ${sysStaffProductTypeList };
        for (var i = 0; i < sysStaffProductTypeList.length; i++) {
            dateArray.push(dataBox(sysStaffProductTypeList[i].staffName, sysStaffProductTypeList[i].productTypeId));
            if (defaultProductTypeIds.length != 0) {
                defaultProductTypeIds.push(";");
            }
            defaultProductTypeIds.push(sysStaffProductTypeList[i].productTypeId);
        }
        </c:if>
        var productType2IdsComboBox = $("#productTypeId").ligerComboBox({
            isShowCheckBox: true,
            isMultiSelect: true,
            emptyText: false,
            data: dateArray,
            valueFieldID: 'productTypeIds',
            selectBoxWidth: 165.5,
            width: 165.5
        });
        <c:if test="${isCwOrgStatus == 1 }" >
        productType2IdsComboBox.selectValue(defaultProductTypeIds.join());
        productType2IdsComboBox.updateStyle();
        </c:if>

    });

    $(function () {

        $("#wuliu").bind('click', function () {
            if ($(this).attr("checked")) {
                $(this).val(1);
            } else {
                $(this).val(0);
            }
        });

        var dateArray = [];
        var defaultProductTypeIds = [];
        <c:if test="${not empty sysStaffProductTypeList }" >
        var sysStaffProductTypeList = ${sysStaffProductTypeList };
        for (var i = 0; i < sysStaffProductTypeList.length; i++) {
            dateArray.push(dataBox(sysStaffProductTypeList[i].staffName, sysStaffProductTypeList[i].productTypeId));
            if (defaultProductTypeIds.length != 0) {
                defaultProductTypeIds.push(";");
            }
            defaultProductTypeIds.push(sysStaffProductTypeList[i].productTypeId);
        }
        </c:if>
        var productType2IdsComboBox = $("#productTypeId").ligerComboBox({
            isShowCheckBox: true,
            isMultiSelect: true,
            emptyText: false,
            data: dateArray,
            valueFieldID: 'productTypeIds',
            selectBoxWidth: 165.5,
            width: 165.5
        });
        <c:if test="${isCwOrgStatus == 1 }" >
        productType2IdsComboBox.selectValue(defaultProductTypeIds.join());
        productType2IdsComboBox.updateStyle();
        </c:if>

    });

    function dataBox(text, id) {
        var obj = new Object();
        obj.text = text;
        obj.id = id;
        return obj;
    }


    function viewViolateOrder(id) {
        $.ligerDialog.open({
            height: $(window).height() - 100,
            width: $(window).width() - 400,
            title: "商家违规详情页",
            name: "INSERT_WINDOW",
            url: "${pageContext.request.contextPath}/violateOrder/view.shtml?id=" + id,
            showMax: true,
            showToggle: false,
            showMin: false,
            isResize: true,
            slide: false,
            data: null
        });
    }

    function viewDetail(id) {
        $.ligerDialog.open({
            height: $(window).height(),
            width: $(window).width() - 50,
            title: "子订单详情",
            name: "INSERT_WINDOW",
            url: "${pageContext.request.contextPath}/order/sub/detail.shtml?id=" + id,
            showMax: true,
            showToggle: false,
            showMin: false,
            isResize: true,
            slide: false,
            data: null
        });
    }

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

    function viewAfterServiceDetail(id, serviceTypeDesc) {
        $.ligerDialog.open({
            height: $(window).height(),
            width: $(window).width() - 50,
            title: "售后详情（" + serviceTypeDesc + "）",
            name: "INSERT_WINDOW",
            url: "${pageContext.request.contextPath}/order/afterService/detail.shtml?id=" + id,
            showMax: true,
            showToggle: false,
            showMin: false,
            isResize: true,
            slide: false,
            data: null
        });
    }

    //批量审核
    function batchAuditOrRetrialPage(ids, type, title) {
        // var auditStatus = $("#auditStatus").val();
        // if (auditStatus == '2'){
        $.ligerDialog.open({
            height: 300,
            width: 600,
            title: title,
            name: "INSERT_WINDOW",
            url: "${pageContext.request.contextPath}/order/sub/batchAuditOrRetrialPage.shtml?&ids=" + ids + "&type=" + type,
            showMax: true,
            showToggle: false,
            showMin: false,
            isResize: true,
            slide: false,
            data: null
        });
        // } else {
        //     $.ligerDialog.alert("不是待审状态不能批量审核")
        //     return;
        // }
    }

    //商品详情
    function viewProduct(id) {
        $.ligerDialog.open({
            height: $(window).height() - 40,
            width: 1200,
            title: "商品信息",
            name: "INSERT_WINDOW",
            url: "${pageContext.request.contextPath}/product/viewProduct.shtml?id=" + id,
            showMax: true,
            showToggle: false,
            showMin: false,
            isResize: true,
            slide: false,
            data: null
        });
    }

    //审核通过
    function pass(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/order/sub/auditPass.shtml?id=" + id,
            type: 'POST',
            dataType: 'json',
            cache: false,
            async: false,
            success: function (data) {
                if (data.returnCode == "0000") {
                    $.ligerDialog.success(data.returnMsg)
                        javascript:location.reload();
                } else {
                    $.ligerDialog.error(data.returnMsg);
                }
            },
            error: function () {
                $.ligerDialog.error('操作超时，请稍后再试！');
            }
        });
    }

    function viewerPic(imgPath) {
        $("#viewer-pictures").empty();
        viewerPictures.destroy();
        imgPath = imgPath.replace('_S', '');
        $("#viewer-pictures").append('<li><img data-original="' + imgPath + '" src="' + imgPath + '" alt=""></li>');
        viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {
            hide: function () {
                $("#viewer-pictures").hide();
            }
        });
        viewerPictures.show();
    }


    var listConfig = {
        url: "/order/sub/orderReviewData.shtml",
        btnItems: [
            {
                text: '批量审核', icon: 'add', click: function () {
                    var data = listModel.gridManager.getSelectedRows();
                    if (data.length == 0) {
                        $.ligerDialog.alert('请选择行！');
                    } else {
                        var str = "";
                        $(data).each(function () {
                            if (str == '') {
                                str = this.id;
                            } else {
                                str += "," + this.id;
                            }
                        });
                        batchAuditOrRetrialPage(str, "2", "批量审核或驳回");
                    }
                    return;
                }
            }
        ],
        listGrid: {
            columns: [{
                display: '子订单编号', width: 200, render: function (rowdata, rowindex) {
                    var html = [];
                    html.push("<a href=\"javascript:viewDetail(" + rowdata.id + ");\">" + rowdata.subOrderCode + "</a>");
                    if (rowdata.memberInfoStatus == 'P') {
                        html.push("</br><span style='color:red;'>异常</span>");
                    }
                    return html.join("");
                }
            },
                {
                    display: '店铺名称', width: 200, render: function (rowdata, rowindex) {
                        var html = [];
                        if (rowdata.shopName != null) {
                            html.push(rowdata.shopName);
                        }
                        return html.join("");
                    }
                },
                {
                    display: '商品名称', width: 200, render: function (rowdata, rowindex) {
                        var html = [];
                        html.push(rowdata.productName + "<br>");
                        html.push("商品ID: ");
                        html.push("<a href=\"javascript:viewProduct(" + rowdata.productid + ");\">" + rowdata.productCode + "</a>");
                        return html.join("");
                    }
                },
                {display: '实付金额', width: 100, name: 'payAmount'},
                {
                    display: '收货人', width: 100, name: 'receiverName', render: function (rowdata, rowindex) {
                        var html = [];
                        if (rowdata.receiverName != null) {
                            html.push(rowdata.receiverName);
                            if (rowdata.suborderCount != "1" && rowdata.auditStatus != '1') {
                                html.push("<br>");
                                html.push("(");
                                html.push('<span style="color: red">' + rowdata.suborderCount + '</span>');
                                html.push(")");
                            }

                        }
                        return html.join("");
                    }
                },
                {
                    display: '认证图片', width: 100, render: function (rowdata, rowindex) {
                        var html = [];
                        if (rowdata.pic != null) {
                            html.push("<img style='float:left;margin:10px;' src='${pageContext.request.contextPath}/file_servelt" + rowdata.pic + "' width='60' height='60' onclick='viewerPic(this.src)'>");
                        }
                        return html.join("");
                    }
                },
                {display: '订单状态', width: 100, name: 'statusDesc'},
                {
                    display: '下单时间', width: 150, render: function (rowdata, rowindex) {
                        if (rowdata.orderCreateDate != null) {
                            var orderCreateDate = new Date(rowdata.orderCreateDate);
                            return orderCreateDate.format("yyyy-MM-dd hh:mm:ss");
                        }
                    }
                },
                /*{ display: '付款时间', width: 150, render: function (rowdata, rowindex) {
                    if (rowdata.orderPayDate!=null){
                        var orderPayDate=new Date(rowdata.orderPayDate);
                        return orderPayDate.format("yyyy-MM-dd hh:mm:ss");
                    }
                }},*/
                {
                    display: '审核状态', width: 100, render: function (rowdata, rowindex) {
                        if (rowdata.auditStatus == '1') {
                            return "无需审核";
                        }
                        if (rowdata.auditStatus == '2') {
                            return "待审";
                        }
                        if (rowdata.auditStatus == '3') {
                            return "通过";
                        }
                        if (rowdata.auditStatus == '4') {
                            return "不通过";
                        }
                        return "";
                    }
                },
                {
                    display: '审核通过时间', width: 150, render: function (rowdata, rowindex) {
                        if (rowdata.auditDate != null) {
                            var orderPayDate = new Date(rowdata.auditDate);
                            return orderPayDate.format("yyyy-MM-dd hh:mm:ss");
                        }
                    }
                },
                {
                    display: '操作', width: 150, render: function (rowdata, rowindex) {
                        var html = [];
                        if (rowdata.auditStatus == 2 && rowdata.memberStatus != null && rowdata.pic != null && rowdata.status==1) {
                            html.push("<a href=\"javascript:pass(" + rowdata.id + ");\">通过</a>");
                            html.push(" | ");
                            html.push("<a href=\"javascript:batchAuditOrRetrialPage(" + rowdata.id + ",1,'驳回');\">驳回</a>");
                        }
					
                        return html.join("");
                    }
                }
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
    }

    function subFunction(statusExcel) {
        /* var status = $("[name='status']").val(); */
        /* var orderPayDateBegin = $("[name='orderPayDateBegin']").val();
        var orderPayDateEnd = $("[name='orderPayDateEnd']").val(); */
        /* var orderCreateDateBegin = $("[name='orderCreateDateBegin']").val();
        var orderCreateDateEnd = $("[name='orderCreateDateEnd']").val(); */
        /*		if(statusExcel == '1') {
                    listModel.ligerGrid.url = '
        ${pageContext.request.contextPath}/order/sub/data.shtml';
			listModel.initdataPage();
		}else if(statusExcel == '2') {*/
        /* if (!orderCreateDateBegin || !orderCreateDateEnd) {
            commUtil.alertError("导出时，请选择下单时间！");
        } else { */
            $("#dataForm").attr("action", "${pageContext.request.contextPath}/order/sub/orderReviewExport.shtml");
            $("#dataForm").submit();
        /* } */
    }

    function dataBox(text, id) {
        var obj = new Object();
        obj.text = text;
        obj.id = id;
        return obj;
    }


</script>

<body style="padding: 0px; overflow: hidden;">
<div class="l-loading" style="display: block" id="pageloading"></div>
<div id="toptoolbar"></div>
<form id="dataForm" method="post" action="">
    <div class="search-pannel">

        <div class="search-tr">

            <div class="search-td">
                <div class="search-td-label">订单状态：</div>
                <div class="search-td-condition">
                    <select id="status" name="status">
                        <option value="">请选择...</option>
                        <c:forEach var="list" items="${statusList}">
                            <option value="${list.statusValue}" <c:if test="${list.statusValue eq '1'}">selected</c:if>>${list.statusDesc}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">商家序号：</div>
                <div class="search-td-condition">
                    <input type="text" id="mchtCode" name="mchtCode">
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">商家名称：</div>
                <div class="search-td-condition">
                    <input type="text" id="mchtName" name="mchtName">
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">订单金额：</div>
                <div class="search-td-condition">
                    <input type="text" id="amountMin" name="amountMin" style="width:60px;"
                           onchange="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}">&nbsp;-&nbsp;<input
                        type="text" id="amountMax" name="amountMax" style="width:60px;"
                        onchange="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}">
                </div>
            </div>

        </div>

        <div class="search-tr">

            <div class="search-td">
                <div class="search-td-label" style="float: left;width: 25%;margin-top:2px;">下单时间：</div>
                <div class="l-panel-search-item">
                    <input type="text" id="orderCreateDateBegin" name="orderCreateDateBegin" value=""/>
                    <script type="text/javascript">
                        $(function () {
                            $("#orderCreateDateBegin").ligerDateEditor({
                                showTime: true,
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
                    <input type="text" id="orderCreateDateEnd" name="orderCreateDateEnd" value=""/>
                    <script type="text/javascript">
                        $(function () {
                            $("#orderCreateDateEnd").ligerDateEditor({
                                showTime: true,
                                labelWidth: 150,
                                width: 150,
                                labelAlign: 'left'
                            });
                        });
                    </script>
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label" style="float: left;width: 25%;margin-top:2px;">付款时间：</div>
                <div class="l-panel-search-item">
                    <input type="text" id="orderPayDateBegin" name="orderPayDateBegin" value="${date_begin}"/>
                    <script type="text/javascript">
                        $(function () {
                            $("#orderPayDateBegin").ligerDateEditor({
                                showTime: true,
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
                    <input type="text" id="orderPayDateEnd" name="orderPayDateEnd"  value="${date_end}"/>
                    <script type="text/javascript">
                        $(function () {
                            $("#orderPayDateEnd").ligerDateEditor({
                                showTime: true,
                                labelWidth: 150,
                                width: 150,
                                labelAlign: 'left'
                            });
                        });
                    </script>
                </div>
            </div>
        </div>
        <div class="search-tr">
            <div class="search-td">
                <div class="search-td-label" style="float: left;width: 25%;margin-top:2px;">发货时间：</div>
                <div class="l-panel-search-item">
                    <input type="text" id="deliveryDateBegin" name="deliveryDateBegin"/>
                    <script type="text/javascript">
                        $(function () {
                            $("#deliveryDateBegin").ligerDateEditor({
                                showTime: true,
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
                    <input type="text" id="deliveryDateEnd" name="deliveryDateEnd"/>
                    <script type="text/javascript">
                        $(function () {
                            $("#deliveryDateEnd").ligerDateEditor({
                                showTime: true,
                                labelWidth: 150,
                                width: 150,
                                labelAlign: 'left'
                            });
                        });
                    </script>
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">订单号：</div>
                <div class="search-td-condition">
                    <input type="text" id="subOrderCode" name="subOrderCode">
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">收货电话：</div>
                <div class="search-td-condition">
                    <input type="text" id="phone" name="phone">
                </div>
            </div>
        </div>

        <div class="search-tr">
            <div class="search-td">
                <div class="search-td-label">会员ID：</div>
                <div class="search-td-condition">
                    <input type="text" id="memberInfoId" name="memberInfoId">
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">审核状态：</div>
                <div class="search-td-combobox-condition">
                    <select id="auditStatus" name="auditStatus" style="width: 135px;">
                        <option value="">请选择</option>
                        <option value="2" selected="selected">待审</option>
                        <option value="3">通过</option>
                        <option value="4">不通过</option>
                    </select>
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label" style="float: left;width: 30%;margin-top:2px;">审核通过时间：</div>
                <div class="l-panel-search-item">
                    <input type="text" id="auditDateBegin" name="auditDateBegin"/>
                    <script type="text/javascript">
                        $(function () {
                            $("#auditDateBegin").ligerDateEditor({
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
                    <input type="text" id="auditDateEnd" name="auditDateEnd"/>
                    <script type="text/javascript">
                        $(function () {
                            $("#auditDateEnd").ligerDateEditor({
                                showTime: false,
                                labelWidth: 150,
                                width: 150,
                                labelAlign: 'left'
                            });
                        });
                    </script>
                </div>
            </div>

            <%--<div class="search-td">
                <div class="search-td-label" >品牌：</div>
                <div class="search-td-condition" >
                    <input type="text" id = "brandName" name="brandName" >
                </div>
                </div>

                <div class="search-td">
                <div class="search-td-label" >货号：</div>
                <div class="search-td-condition" >
                    <input type="text" id = "artNo" name="artNo" >
                </div>
                </div>

                <div class="search-td">
                <div class="search-td-label" >付款渠道：</div>
                <div class="search-td-condition" >
                    <select id="paymentId" name="paymentId">
                        <option value="">请选择...</option>
                        <c:forEach items="${sysPaymentList}" var="sysPayment">
                            <option value="${sysPayment.id}">${sysPayment.remarks}</option>
                        </c:forEach>
                    </select>
                </div>
                </div>--%>

        </div>
        <div class="search-tr">
            <div class="search-td">
                <div class="search-td-label">用户昵称：</div>
                <div class="search-td-condition">
                    <input type="text" id="memberNick" name="memberNick">
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">收货人：</div>
                <div class="search-td-condition">
                    <input type="text" id="receiverName" name="receiverName">
                </div>
            </div>

            <%--<div class="search-td">
                <div class="search-td-label" >品类：</div>
                <div style="display: inline-block;" >
                    <input type="text" id="productTypeId" name="productTypeId" />

                    &lt;%&ndash; <!-- isCwOrgStatus：是否为钟表类目 -->
                    <select id="productTypeId" name="productTypeId" <c:if test="${isCwOrgStatus == 1 }">disabled="disabled"</c:if> >
                        <c:if test="${isCwOrgStatus == 0 }">
                            <option value="">请选择...</option>
                        </c:if>
                        <c:forEach items="${sysStaffProductTypeList}" var="sysStaffProductType">
                            <option value="${sysStaffProductType.productTypeId}">${sysStaffProductType.staffName}</option>
                        </c:forEach>
                    </select> &ndash;%&gt;

                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label" >物流信息：<input type="checkbox" id = "wuliu" name="wuliu" ></div>
                <div class="search-td-condition" >
                    只看无物流信息
                </div>
            </div>--%>
        </div>
        <div class="search-tr">
            <div class="search-td">
                <div class="search-td-label" style="color: red">订单异常：</div>
                <div class="search-td-condition">
                    <select id="memberStatus" name="memberStatus">
                        <option value="">请选择...</option>
                        <option value="NP">正常</option>
                        <option value="P">异常</option>
                    </select>
                </div>
            </div>
            <%-- <div class="search-td">
                <div class="search-td-label" style="color: red">对接人：</div>
                <div class="search-td-condition" >
                    <select id="platformContactId" name="platformContactId" >
                        <c:if test="${isContact==1}">
                            <c:if test="${sessionScope.USER_SESSION.isManagement=='1'}">
                                <option value="">全部商家</option>
                            </c:if>
                            <option value="${myContactId}">我对接的商家</option>
                            <c:forEach items="${platformAssistantContacts}" var="platformAssistantContactList">
                                <option value="${platformAssistantContactList.id}">${platformAssistantContactList.contactName}的商家</option>
                            </c:forEach>
                        </c:if>

                        <c:if test="${isContact==0}">
                            <option value="">全部商家</option>
                            <c:forEach items="${platformMchtContacts}" var="platformMchtContactList">
                                <option value="${platformMchtContactList.id}">${platformMchtContactList.contactName}的商家</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>
            </div> --%>
            <div class="search-td">
                <div class="search-td-label" style="color: red;">对接人：</div>
                <div class="search-td-condition">
                    <select id="platContactStaffId" name="platContactStaffId">
                        <%-- <c:if test="${isManagement == 1}"> --%>
                            <option value="" selected="selected">全部商家</option>
                        <%-- </c:if> --%>
                        <option value="${staffID}">我自己的商家</option>
                        <c:forEach items="${sysStaffInfoCustomList}" var="sysStaffInfoCustom">
                            <option value="${sysStaffInfoCustom.staffId}">${sysStaffInfoCustom.staffName}的商家</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">认证图片：</div>
                <div class="search-td-combobox-condition">
                    <select id="checkPic" name="checkPic" style="width: 135px;">
                        <option value="0">请选择</option>
                        <option value="1" selected="selected">已上传</option>
                        <option value="2">未上传</option>
                    </select>
                </div>
            </div>

            <%--<div class="search-td">
                        <div class="search-td-label"  >推广类型：</div>
                        <div class="search-td-combobox-condition" >
                            <select id="promotionType" name="promotionType" style="width: 135px;" >
                                <option value="">请选择</option>
                                <option value="1">推广分润</option>
                                <option value="0">无</option>
                            </select>
                        </div>
            </div>--%>

            <div class="search-td-search-btn" style="display: inline-flex;">
                <%--<div class="l-button" onclick="subFunction('1');" >搜索</div>--%>
                <div id="searchbtn">搜索</div>
                <%--				<c:if test="${statusPage == '2' }">--%>
                <div style="padding-left: 10px;">
                    <input class="l-button" type="button" style="width: 50px;height: 25px;cursor: pointer;" value="导出"
                           onclick="subFunction()" ; id="export">
                </div>
                <%--				</c:if>--%>
            </div>

        </div>
    </div>

    <div id="maingrid" style="margin: 0; padding: 0"></div>
</form>
<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>