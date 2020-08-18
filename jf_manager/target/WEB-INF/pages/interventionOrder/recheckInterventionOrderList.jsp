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
			format : "yyyy-MM-dd hh:mm:ss",
			labelAlign : 'left',
			width : 150
		});
		
		var dateArray = [];
		var defaultProductTypeIds = [];
		<c:if test="${not empty sysStaffProductTypeList }" >
			var sysStaffProductTypeList = ${sysStaffProductTypeList };
			for(var i=0;i<sysStaffProductTypeList.length;i++) {
				dateArray.push(dataBox(sysStaffProductTypeList[i].staffName, sysStaffProductTypeList[i].productTypeId));
				if(defaultProductTypeIds.length != 0) {
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
	        selectBoxWidth: 135,
	        width: 135
	    }); 
		<c:if test="${isCwOrgStatus == 1 }" >
			productType2IdsComboBox.selectValue(defaultProductTypeIds.join());
			productType2IdsComboBox.updateStyle();
		</c:if>

	});

	function dataBox(text, id){ 
		 var obj = new Object();
		 obj.text = text; 
		 obj.id = id; 
		 return obj;
	}
	
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
		url : "/interventionOrder/getInterventionOrderList.shtml?statusFlag=6",
		btnItems : [],
		listGrid : {
			columns : [{display:'申请介入编号', name:'interventionCode', align:'center', isSort:false, width:180, render: function (rowdata, rowindex) {
						var html = [];
						html.push(rowdata.interventionCode);
						if(rowdata.memberInfoStatus == 'P') {
							html.push("</br><span style='color:red;'>异常</span>");
						}
						return html.join("");
		            }},
		           	{display:'售后编号', name:'', align:'center', isSort:false, width:180, render: function (rowdata, rowindex) {
						return "<a href=\"javascript:viewAfterServiceDetail(" + rowdata.serviceOrderId + ",'" +rowdata.customerServiceTypeDesc+ "');\">"+rowdata.customerServiceOrderCode+"</a>";
	                }},
					{display:'申请人', name:'contacts', align:'center', isSort:false, width:100},
					{display:'申请原因', name:'reasonDesc', align:'center', isSort:false, width:180},
					{display:'相关商家', name:'companyName', align:'center', isSort:false, width:120},
					{display:'处理状态', name:'statusDesc', align:'center', isSort:false, width:100},
					{display:'判定内容 / 驳回理由', name:'sumOperateAudit', align:'center', isSort:false, width:200, render:function(rowdata, rowindex) {
						var html = [];
						if (rowdata.mchtResultReasonDesc != null && rowdata.mchtResultReasonDesc != '') {
							html.push("商家：" + rowdata.mchtResultReasonDesc+ "<br/>");
						}
						if (rowdata.clientResultReasonDesc != null && rowdata.clientResultReasonDesc != '') {
							html.push("买家："+ rowdata.clientResultReasonDesc);
						}
						return html.join("");
					}},
					{display:'结案备注', name:'recordOfCases', align:'center', isSort:false, width:160},
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
					}},
					{display:'处理人', name:'platformProcessorName', align:'center', isSort:false, width:100},
					{display:'操作', name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
						return "<a href=\"javascript:showInterventionOrder("+ rowdata.id + ", 5);\">查看</a>";
					}}
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
</script>
<style type="text/css">
.l-box-select .l-box-select-table td {
	font-size: 12px;
	line-height: 18px;
}
</style>
</head>
<body style="padding: 0px; overflow: hidden;">
	<!-- 	<div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">申请介入编号：</div>
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
					<div class="search-td-label">申请原因：</div>
					<div class="search-td-combobox-condition">
						<select id="reason" name="reason" style="width: 135px;">
							<option value="">请选择...</option>
							<c:forEach var="reason" items="${reasonList }">
								<option value="${reason.statusValue }">${reason.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商家名称：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="mchtName" name="mchtName">
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">商家联系电话：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="mchtPhone" name="mchtPhone">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">申请人联系电话：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="tel" name="tel">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="mchtCode" name="mchtCode">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="color: red">介入异常：</div>
					<div class="search-td-condition">
						<select id="memberStatus" name="memberStatus"
							style="width: 135px;">
							<option value="">请选择...</option>
							<option value="NP">正常</option>
							<option value="P">异常</option>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">品类：</div>
					<div style="display: inline-block;">
						<input type="text" id="productTypeId" name="productTypeId" />
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
