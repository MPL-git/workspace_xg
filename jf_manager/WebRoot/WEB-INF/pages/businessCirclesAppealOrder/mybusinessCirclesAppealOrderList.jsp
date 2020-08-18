<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js"></script>
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
    <%-- 自定义JS --%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>

    <link href="${pageContext.request.contextPath}/css/glyphicon.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/star.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript">
        $(function () {
            $(".dateEditor").ligerDateEditor( {
                showTime : false,
                labelAlign : 'left',
                labelwidth: 120,
                width:120
            });

        });


        function businessCirclesAppealOrderPic(id) {
            $.ligerDialog.open({
                height: $(window).height() - 40,
                width: 1200,
                title: "工商投诉原件",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/businessCirclesAppealOrder/businessCirclesAppealOrderPic.shtml?id=" + id,
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
                height: $(window).height() - 50,
                width: $(window).width() - 100,
                title: "会员资料",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/member/info/detail.shtml?id=" + id,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }


        function customerServiceOrderCount(id) {
            $.ligerDialog.open({
                height: $(window).height(),
                width: $(window).width() - 50,
                title: "查看售后单",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/businessCirclesAppealOrder/customerServiceOrderCountdata.shtml?id=" + id,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });

        }

        function interventionOrderCount(id) {
            $.ligerDialog.open({
                height: $(window).height(),
                width: $(window).width() - 50,
                title: "查看介入单",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/businessCirclesAppealOrder/interventionOrderCountdata.shtml?id=" + id,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });

        }


        function appealOrderCount(id) {
            $.ligerDialog.open({
                height: $(window).height(),
                width: $(window).width() - 50,
                title: "查看投诉单",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/businessCirclesAppealOrder/appealOrderCountdata.shtml?id=" + id,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });

        }


        function subOrderCustomscuont(id) {
            $.ligerDialog.open({
                height: $(window).height(),
                width: $(window).width() - 50,
                title: "查看子订单",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/businessCirclesAppealOrder/subOrderListdata.shtml?id=" + id,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });

        }


        function kfRecords(id, status) {
            $.ligerDialog.open({
                height: $(window).height(),
                width: $(window).width() - 50,
                title: "客服记录",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/businessCirclesAppealOrder/kfRecordsdata.shtml?id=" + id + "&status=" + status,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });

        }


        var listConfig = {
            url: "/businessCirclesAppealOrder/businessCirclesAppealOrderdata.shtml",
            btnItems: [{
                text: '创建工商投诉',
                icon: 'add',
                id: 'add',
                dtype: 'win',
                click: itemclick,
                url: "/businessCirclesAppealOrder/addbusinessCirclesAppealOrder.shtml",
                seqId: "",
                width: 950,
                height: 600
            }],
            listGrid: {
                columns: [
                    {display: '工商投诉编号', name: 'id', align: 'center', isSort: false, width: 100},
                    {display: '创建人', name: 'createbyName', align: 'center', isSort: false, width: 100},
                    {
                        display: '消费者投诉时间',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 180,
                        render: function (rowdata, rowindex) {
                            var html = [];
                            if (rowdata.consumerAppealDate != null && rowdata.consumerAppealDate != '') {
                                var consumerAppealDate = new Date(rowdata.consumerAppealDate);
                                html.push(consumerAppealDate.format("yyyy-MM-dd"));
                            }
                            return html.join("");
                        }
                    },

                    {display: '登记编号', name: 'registrationNumber', align: 'center', isSort: false, width: 200},
                    {display: '投诉人', name: 'appealName', align: 'center', isSort: false, width: 100},
                    {display: '投诉人联系电话', name: 'appealMobile', align: 'center', isSort: false, width: 200},
                    {
                        display: '投诉单类型',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 150,
                        render: function (rowdata, rowindex) {
                            if (rowdata.appealOrderType == '1') {
                                return "投诉单";
                            }
                            if (rowdata.appealOrderType == '2') {
                                return "举报单";
                            }

                        }
                    },
                    {display: '被投诉类型', name: 'typesOfComplaints', align: 'center', isSort: false, width: 100},
                    {display: '消费者投诉具体内容', name: 'consumerAppealContent', align: 'center', isSort: false, width: 300},
                    {
                        display: '工商投诉原件',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 100,
                        render: function (rowdata, rowindex) {
                            return "<a href=\"javascript:businessCirclesAppealOrderPic(" + rowdata.id + ");\">查看</a>";
                        }
                    },
                    {
                        display: '投诉单状态',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 100,
                        render: function (rowdata, rowindex) {
                            if (rowdata.appealStatus == '0') {
                                return "待跟进";
                            }
                            if (rowdata.appealStatus == '1') {
                                return "客服处理中";
                            }
                            if (rowdata.appealStatus == '2') {
                                return "待法务处理";
                            }
                            if (rowdata.appealStatus == '3') {
                                return "内部结案移交工商";
                            }
                            if (rowdata.appealStatus == '4') {
                                return "未结案移交工商";
                            }
                            if (rowdata.appealStatus == '5') {
                                return "驳回跟进";
                            }

                        }
                    },
                    {
                        display: '会员ID',
                        name: 'memberIds',
                        align: 'center',
                        isSort: false,
                        width: 100,
                        render: function (rowdata, rowindex) {
                            var html = [];
                            if (rowdata.memberIds != null && rowdata.memberIds != '') {
                                var memberId = rowdata.memberIds.split(",");
                                for (var i = 0; i < memberId.length; i++) {
                                    html.push("<a href=\"javascript:viewDetail(" + memberId[i] + ");\">" + memberId[i] + "<br></a>");
                                }
                                return html.join("");

                            } else {
                                return "";
                            }

                        }
                    },
                    {
                        display: '商家信息',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 200,
                        render: function (rowdata, rowindex) {
                            var mchtInformation = rowdata.mchtInformation;
                            if (mchtInformation != null) {
                                return mchtInformation.replace(/,/g, "<br>");
                            }
                        }
                    },
                    {display: '商家被投诉次数', name: 'mchtAppealedCount', align: 'center', isSort: false, width: 100},
                    {
                        display: '被投诉的品牌',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 200,
                        render: function (rowdata, rowindex) {
                            var productbrandName = rowdata.productbrandName;
                            if (productbrandName != null) {
                                return productbrandName.replace(/,/g, "<br>");
                            }
                        }
                    },
                    {
                        display: '相关类型单号',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 200,
                        render: function (rowdata, rowindex) {
                            var html = [];
                            if (rowdata.customerServiceOrderCount > 0 || rowdata.interventionOrderCount > 0 || rowdata.appealOrderCount > 0 || rowdata.subOrderCustomscuont > 0) {
                                html.push("<a href=\"javascript:subOrderCustomscuont(" + rowdata.id + ");\">子订单(<span>" + rowdata.subOrderCustomscuont + "</span>)</a><br>");
                                html.push("<a href=\"javascript:customerServiceOrderCount(" + rowdata.id + ");\">售后单(<span>" + rowdata.customerServiceOrderCount + "</span>)</a><br>");
                                html.push("<a href=\"javascript:interventionOrderCount(" + rowdata.id + ");\">介入单(<span>" + rowdata.interventionOrderCount + "</span>)</a><br>");
                                html.push("<a href=\"javascript:appealOrderCount(" + rowdata.id + ");\">投诉单(<span>" + rowdata.appealOrderCount + "</span>)</a><br>");
                            } else {
                                return "";
                            }
                            return html.join("");
                        }
                    },
                    {display: '相关子订单', name: 'subOrderCode', align: 'center', isSort: false, width: 200},
                    {
                        display: '签收时间',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 180,
                        render: function (rowdata, rowindex) {
                            var html = [];
                            if (rowdata.signForDate != null && rowdata.signForDate != '') {
                                var signForDate = new Date(rowdata.signForDate);
                                html.push(signForDate.format("yyyy-MM-dd"));
                            }
                            return html.join("");
                        }
                    },
                    {
                        display: '客服处理人',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 200,
                        render: function (rowdata, rowindex) {
                            var html = [];
                            if (rowdata.customerServiceHandler != null && rowdata.customerServiceHandler != '') {
                                html.push(rowdata.customerservicehandlerName + "<br>");

                            }
                            if (rowdata.customerServiceResultDate != null && rowdata.customerServiceResultDate != '') {
                                var customerServiceResultDate = new Date(rowdata.customerServiceResultDate);
                                html.push(customerServiceResultDate.format("yyyy-MM-dd hh:mm"));
                            }
                            return html.join("");

                        }
                    },
                    {
                        display: '客服记录',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 200,
                        render: function (rowdata, rowindex) {
                            var html = [];
                            if (rowdata.customerServiceRecordsid != null && rowdata.customerServiceRecordsid != '') {
                                html.push("<a href=\"javascript:kfRecords(" + rowdata.id + ",'0');\">查看</a><br>");

                            } else {
                                return "";
                            }
                            return html.join("");
                        }
                    },
                    {display: '客服处理结果', name: 'customerServiceResult', align: 'center', isSort: false, width: 200},
                    {display: '法务处理结果', name: 'fwResult', align: 'center', isSort: false, width: 200},
                    {display: '工商处理结果', name: 'businessCirclesResult', align: 'center', isSort: false, width: 200}

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

        //导出
        function subFunction(statusExcel) {
            $("#dataForm").attr("action", "${pageContext.request.contextPath}/businessCirclesAppealOrder/businessCirclesAppealOrderExport.shtml");
            $("#dataForm").submit();

        }

    </script>
</head>
<body style="padding: 0px; overflow: hidden;">
<div id="toptoolbar"></div>
<form id="dataForm" runat="server">
    <div class="search-pannel">
        <div class="search-tr">
            <%--<div class="search-td">
               <div class="search-td-label" style="float:left;"  >投诉日期：</div>
               <div class="l-panel-search-item" >
                    <input type="text" class="dateEditor" id="startconsumerAppealDate" name="startconsumerAppealDate" >
                </div>
                <div class="l-panel-search-item" >&nbsp;&nbsp;&nbsp;&nbsp;至：</div>
             </div>
             <div class="search-td">
                  <div class="l-panel-search-item" >
                       <input type="text" class="dateEditor" id="endconsumerAppealDate" name="endconsumerAppealDate" >
                  </div>
             </div>--%>
            <div class="search-td" style="width: 30%;margin-bottom:-6px;">
                <div class="search-td-label" style="float: left;width: 20%;margin-top:2px;">投诉日期</div>
                <div class="l-panel-search-item">
                    <input type="text" id="startconsumerAppealDate" name="startconsumerAppealDate" class="dateEditor"
                           placeholder="请选择" style="width: 135px;"/>
                </div>
                <div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;">至</div>
                <div class="l-panel-search-item">
                    <input type="text" id="endconsumerAppealDate" name="endconsumerAppealDate" class="dateEditor"
                           placeholder="请选择" style="width: 135px;"/>
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">投诉单类型：</div>
                <div class="search-td-condition">
                    <select id="appealOrderType" name="appealOrderType" class="querysel">
                        <option value=''>请选择...</option>
                        <option value='1'>投诉单</option>
                        <option value='2'>举报单</option>
                    </select>
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">投诉单状态：</div>
                <div class="search-td-condition">
                    <select id="appealStatus" name="appealStatus" class="querysel">
                        <option value="">请选择...</option>
                        <option value="0">待跟进</option>
                        <option value="1">客服处理中</option>
                        <option value="2">待法务处理</option>
                        <option value="3">内部结案移交工商</option>
                        <option value="4">未结案移交工商</option>
                        <option value="5">驳回跟进</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="search-tr">
            <div class="search-td">
                <div class="search-td-label">会员ID：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="memberIds" name="memberIds">
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">公司名称：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="companyName" name="companyName">
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">客服处理人：</div>
                <div class="search-td-condition">
                    <select id="staffId" name="staffId" class="querysel">
                        <option value="">请选择...</option>
                        <c:forEach var="staffidList" items="${staffidList}">
                            <option value="${staffidList.customer_service_handler}">${staffidList.staff_name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">子订单号：</div>
                <div class="search-td-condition">
                    <input type="text" id="subOrderCode" name="subOrderCode">
                </div>
            </div>
            <div class="search-td-search-btn" style="display: inline-flex;">
                <div id="searchbtn">搜索</div>
                <div style="padding-left: 10px;">
                    <input type="button" style="width: 50px;height: 25px;cursor: pointer;" value="导出"
                           onclick="subFunction();" id="export">
                </div>
            </div>

        </div>
    </div>
    <div id="maingrid" style="margin: 0; padding: 0"></div>
</form>
<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">

</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
