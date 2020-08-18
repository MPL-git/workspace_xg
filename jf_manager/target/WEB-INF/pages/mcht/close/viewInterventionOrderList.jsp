<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript">
	//查看
	function showInterventionOrder(interventionOrderId, statusPage) {
		$.ligerDialog.open({
			height : $(window).height() - 100,
			width : $(window).width() - 200,
			title : "查看详情",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/interventionOrder/showInterventionOrderManager.shtml?interventionOrderId="
					+ interventionOrderId + "&statusPage=" + statusPage,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}

	//售后详情
	function viewAfterServiceDetail(id, serviceTypeDesc) {
		$.ligerDialog.open({
	 		height: $(window).height(),
			width: $(window).width()-50,
	 		title: "售后详情（"+serviceTypeDesc+"）",
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
	
	var listConfig = {
		url : "/mcht/close/viewInterventionOrderData.shtml",
		listGrid : {
			columns : [{display:'介入单编号', name:'interventionCode', align:'center', isSort:false, width:180, render: function (rowdata, rowindex) {
						var html = [];
						html.push('<a href="javascript:;" onclick="showInterventionOrder('+rowdata.id+',1);">'+rowdata.interventionCode+'</a>');
						if(rowdata.memberInfoStatus == 'P') {
							html.push("</br><span style='color:red;'>异常</span>");
						}
						return html.join("");
		            }},
		            {display:'售后编号', name:'', align:'center', isSort:false, width:180, render: function (rowdata, rowindex) {
						return "<a href=\"javascript:viewAfterServiceDetail(" + rowdata.serviceOrderId + ",'" +rowdata.customerServiceTypeDesc+ "');\">"+rowdata.customerServiceOrderCode+"</a>";
	                }},
					{display:'处理状态', name:'statusDesc', align:'center', isSort:false, width:100},
					{display:'处理人', name:'platformProcessorName', align:'center', isSort:false, width:100},
					{display:'申请时间', name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
						if (rowdata.createDate != null && rowdata.createDate != '') {
							var createDate = new Date(rowdata.createDate);
							return createDate.format("yyyy-MM-dd hh:mm:ss");
						} else {
							return "";
						}
					}},
					{display:'最后更新时间', name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
						if (rowdata.updateDate != null && rowdata.updateDate != '') {
							var updateDate = new Date(rowdata.updateDate);
							return updateDate.format("yyyy-MM-dd hh:mm:ss");
						} else {
							return "";
						}
					}}
				],
			showCheckbox : true, //不设置默认为 true
			showRownumber : true
		//不设置默认为 true
		},
		container : {
			toolBarName : "toptoolbar",
			searchBtnName : "searchbtn",
			fromName : "dataForm",
			listGridName : "maingrid"
		}
	};
</script>
<style type="text/css">
	.l-box-select .l-box-select-table td {
		font-size: 12px;
		line-height: 18px;
	}
</style>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
	<input type="hidden" name="mchtId" value="${mchtId}">
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label" style="width: 100px;">申请介入编号：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="interventionCode" name="interventionCode">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">售后单号：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="customerServiceOrderCode" name="customerServiceOrderCode">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="color: red">处理状态：</div>
					<div class="search-td-condition" >
						<select id="status" name="status" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach items="${statusList}" var="status">
								<option value="${status.statusValue}">${status.statusDesc}</option>
							</c:forEach>
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
