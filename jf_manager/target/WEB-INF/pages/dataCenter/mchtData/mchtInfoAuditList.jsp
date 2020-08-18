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
	$(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
		});

	});

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
		url : "/mchtData/mchtInfoAuditList.shtml",
		btnItems : [],
		listGrid : {
			columns : [{display:'创建日期', name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
						var html = [];
						if (rowdata.create_date != null && rowdata.create_date != '') {
							var create_date = new Date(rowdata.create_date);
							html.push(create_date.format("yyyy-MM-dd"));
						}
						return html.join("");
					}},
		           	{display:'招商对接人', name:'zs_contact_name', align:'center', isSort:false, width:100},
		           	{display:'商家序号', name:'mcht_code', align:'center', isSort:false, width:80},
		           	{display:'公司名称', name:'', align:'center', isSort:false, width:200, render:function(rowdata, rowindex) {
		           		var html = [];
					    html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.id + ");\">"+ rowdata.company_name +"</a><br>"); 
					    return html.join("");
		           	}},
		           	{display:'合作状态', name:'mcht_status_desc', align:'center', isSort:false, width:80},
		           	{display:'店铺名称', name:'shop_name', align:'center', isSort:false, width:200},
		           	{display:'主营类目', name:'mcht_product_type_name', align:'center', isSort:false, width:100},
		           	{display:'品牌', name:'product_brand_name_audit_status', align:'center', isSort:false, width:300},
		           	{display:'联系人', name:'mcht_settled_contact_name', align:'center', isSort:false, width:100},
		           	{display:'联系电话', name:'mcht_settled_phone', align:'center', isSort:false, width:100},
		           	{display:'商家是否提交', name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
		           		var html = [];
		           		if(rowdata.total_audit_status == '4') { 
		           			html.push("否");
		           		}else {
		           			html.push("是");
		           		}
		           		return html.join("");
		           	}},
		           	{display:'招商是否确认', name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
		           		var html = [];
		           		if(rowdata.zs_audit_status == '1') { 
		           			html.push("是");
		           		}else if(rowdata.zs_audit_status == '0') {
		           			html.push("否");
		           		}
		           		return html.join("");
		           	}},
		           	{display:'法务总审状态', name:'mcht_total_audit_status_desc', align:'center', isSort:false, width:100},
		           	{display:'驳回原因', name:'total_audit_remarks', align:'center', isSort:false, width:100},
		           	{display:'线上合同', name:'mcht_contract_audit_status_desc', align:'center', isSort:false, width:100},
		           	{display:'财务确认', name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
		           		var html = [];
		           		if(rowdata.finance_confirm_status == '1') { 
		           			html.push("已确认");
		           		}else if(rowdata.finance_confirm_status == '0') {
		           			html.push("未确认");
		           		}
		           		return html.join("");
		           	}},
		           	{display:'应缴保证金', name:'mcht_deposit_total_amt', align:'center', isSort:false, width:80},
		           	{display:'已缴保证金', name:'mcht_deposit_pay_amt', align:'center', isSort:false, width:80},
		           	{display:'保证金类型', name:'mcht_deposit_type_desc', align:'center', isSort:false, width:80},
		           	{display:'开通日期', name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
						var html = [];
						if (rowdata.cooperate_begin_date != null && rowdata.cooperate_begin_date != '') {
							var cooperate_begin_date = new Date(rowdata.cooperate_begin_date);
							html.push(cooperate_begin_date.format("yyyy-MM-dd"));
						}
						return html.join("");
					}},
		           	{display:'最新合同到期日期', name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
						var html = [];
						if (rowdata.agreement_end_date != null && rowdata.agreement_end_date != '') {
							var agreement_end_date = new Date(rowdata.agreement_end_date);
							html.push(agreement_end_date.format("yyyy-MM-dd"));
						}
						return html.join("");
					}},
		           	{display:'商家合同寄出', name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
		           		var html = [];
		           		if(rowdata.mcht_contract_is_mcht_send == '1') { 
		           			html.push("是");
		           		}else if(rowdata.mcht_contract_is_mcht_send == '0') {
		           			html.push("否");
		           		}
		           		return html.join("");
		           	}},
		           	{display:'合同归档', name:'mcht_contract_archive_status_desc', align:'center', isSort:false, width:100},
		           	{display:'平台合同寄出', name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
		           		var html = [];
		           		if(rowdata.mcht_contract_is_platform_send == '1') { 
		           			html.push("是");
		           		}else if(rowdata.mcht_contract_is_platform_send == '0') {
		           			html.push("否");
		           		}
		           		return html.join("");
		           	}},
		           	{display:'合同备注驳回原因', name:'mcht_contract_remarks', align:'center', isSort:false, width:200},
		           	{display:'商品数', name:'mcht_product_sum', align:'center', isSort:false, width:80},
		           	{display:'近月销售额', name:'order_dtl_pay_amount_sum', align:'center', isSort:false, width:80},
		           	{display:'近月特卖数', name:'activity_area_count', align:'center', isSort:false, width:80}
				],
			showCheckbox : false, //不设置默认为 true
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
	
	function subFunction() {
		$("#dataForm").attr("action","${pageContext.request.contextPath}/mchtData/mchtInfoAuditExport.shtml");
		$("#dataForm").submit();
	}
	
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<!-- 	<div id="toptoolbar"></div> -->
	<form id="dataForm" method="post" action="" >
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="mchtCode" name="mchtCode" value="${mchtCode}">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">公司名称：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="companyName" name="companyName">
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
					<div class="search-td-label">联系人：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="mchtSettledContactName" name="mchtSettledContactName">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">合作状态：</div>
					<div class="search-td-combobox-condition">
						<select id="mchtStatus" name="mchtStatus" style="width: 135px;">
							<option value="">请选择...</option>
							<c:forEach var="mchtStatus" items="${mchtStatusList }">
								<option value="${mchtStatus.statusValue }">${mchtStatus.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">主营类目：</div>
					<div class="search-td-combobox-condition">
						<c:if test="${isCwOrgStatus == 1 }">
							<select id="productTypeId" name="productTypeId" disabled="disabled" style="width: 135px;">
								<c:forEach items="${productTypeList}" var="productType">
									<option value="${productType.id}">${productType.name}</option>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${isCwOrgStatus == 0 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;">
								<option value="">请选择</option>
								<c:forEach items="${productTypeList}" var="productType">
									<option value="${productType.id}">${productType.name}</option>
								</c:forEach>
							</select>
						</c:if>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">法务总审状态：</div>
					<div class="search-td-combobox-condition">
						<select id="mchtTotalAuditStatus" name="mchtTotalAuditStatus" style="width: 135px;">
							<option value="">请选择...</option>
							<c:forEach var="mchtTotalAuditStatus" items="${mchtTotalAuditStatusList }">
								<option value="${mchtTotalAuditStatus.statusValue }">${mchtTotalAuditStatus.statusDesc }</option>
							</c:forEach>
							<option value="999">已提交</option>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">线上合同：</div>
					<div class="search-td-combobox-condition">
						<select id="mchtContractAuditStatus" name="mchtContractAuditStatus" style="width: 135px;">
							<option value="">请选择...</option>
							<c:forEach var="mchtContractAuditStatus" items="${mchtContractAuditStatusList }">
								<option value="${mchtContractAuditStatus.statusValue }">${mchtContractAuditStatus.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">财务确认：</div>
					<div class="search-td-combobox-condition">
						<select id="financeConfirmStatus" name="financeConfirmStatus" style="width: 135px;">
							<option value="">请选择...</option>
							<option value="0">未确认</option>
							<option value="1">已确认</option>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商家合同寄出：</div>
					<div class="search-td-combobox-condition">
						<select id="mchtContractIsMchtSend" name="mchtContractIsMchtSend" style="width: 135px;">
							<option value="">请选择...</option>
							<option value="0">未寄出</option>
							<option value="1">已寄出</option>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">平台合同寄出：</div>
					<div class="search-td-combobox-condition">
						<select id="mchtContractIsPlatformSend" name="mchtContractIsPlatformSend" style="width: 135px;">
							<option value="">请选择...</option>
							<option value="0">未寄出</option>
							<option value="1">已寄出</option>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">创建日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginCreateDate" name="beginCreateDate" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endCreateDate" name="endCreateDate" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">开通日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginCooperateBeginDate" name="beginCooperateBeginDate" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endCooperateBeginDate" name="endCooperateBeginDate" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">最新合同到期日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginAgreementEndDate" name="beginAgreementEndDate" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endAgreementEndDate" name="endAgreementEndDate" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">合同归档：</div>
					<div class="search-td-combobox-condition">
						<select id="mchtContractArchiveStatus" name="mchtContractArchiveStatus" style="width: 135px;">
							<option value="">请选择...</option>
							<c:forEach var="mchtContractArchiveStatus" items="${mchtContractArchiveStatusList }">
								<option value="${mchtContractArchiveStatus.statusValue }">${mchtContractArchiveStatus.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">保证金类型：</div>
					<div class="search-td-combobox-condition">
						<select id="mchtDepositType" name="mchtDepositType" style="width: 135px;">
							<option value="">请选择...</option>
							<c:forEach var="mchtDepositType" items="${mchtDepositTypeList }">
								<option value="${mchtDepositType.statusValue }">${mchtDepositType.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label" style="color: red;">对接人：</div>
					<div class="search-td-combobox-condition">
						<select id="platformContactId" name="platformContactId" >
							<c:if test="${isContact == 1}">
								<c:if test="${sessionScope.USER_SESSION.isManagement == '1' }">
									<option value="">全部商家</option>
								</c:if>
								<option value="${myContactId }">我对接的商家</option>
								<c:forEach var="platformAssistantContact" items="${platformAssistantContactList }" >
									<option value="${platformAssistantContact.id }">${platformAssistantContact.contactName }的商家</option>
								</c:forEach>
								<c:if test="${sessionScope.USER_SESSION.isManagement == '1' }">
									<c:forEach var="platformMchtContact" items="${platformMchtContactList }" > 
										<option value="${platformMchtContact.id }">${platformMchtContact.contactName }的商家</option>
									</c:forEach>
								</c:if>
							</c:if>
							<c:if test="${isContact == 0}">
								<option value="">全部商家</option>
								<c:forEach var="platformMchtContact" items="${platformMchtContactList }" > 
									<option value="${platformMchtContact.id }">${platformMchtContact.contactName }的商家</option>
								</c:forEach>			
							</c:if>
						</select>
					</div>
				</div>
				<div class="search-td-search-btn" style="display: inline-flex;" >
					<div id="searchbtn">搜索</div>
					<c:if test="${not empty role87}">
						<div style="padding-left: 10px;">
							<input type="button" style="width: 50px;height: 25px;cursor: pointer;" value="导出" onclick="subFunction();" id="export">
						</div>	
					</c:if>
				</div>
			</div>
		</div>
	</form>

	<div id="maingrid" style="margin: 0;"></div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
