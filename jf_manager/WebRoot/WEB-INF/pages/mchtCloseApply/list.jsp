<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
<title>合同已归档</title>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript">
      var listConfig={
      url:"/mchtCloseApply/listData.shtml",
      listGrid:{
		  columns: [
				{ display: '申请日期', name: 'requestDate', width:120 ,render: function(rowdata, rowindex) {
					if(rowdata.requestDate != null && rowdata.requestDate!=""){
					   return new Date(rowdata.requestDate).format("yyyy-MM-dd");
					}
				}},
			  	{ display: "申请人类型", name: "requestTypeStr", width: 100, align: "center"},
				{ display: '招商对接人', name: 'platformMerchantsContact.contactName',width:100 },
				{ display: '招商意见', name: 'merchantsAuditStatusStr',width:100, render: function(rowdata, rowindex) {
					var html = [];
					if(rowdata.merchantsAuditStatusStr=="待审"){
						html.push("<a href=\"javascript:audit(" + rowdata.id + ");\">未处理</a>&nbsp;&nbsp;");
					}else{
						html.push(rowdata.merchantsAuditStatusStr);
					}
					return html.join("");
				}},
				{ display: '商家运营', name: 'platformOperateContact.contactName',width:100 },
				{ display: '运营意见', name: 'operateAuditStatusStr',width:100, render: function(rowdata, rowindex) {
					var html = [];
					if(rowdata.operateAuditStatusStr=="待审"){
						html.push("<a href=\"javascript:audit(" + rowdata.id + ");\">未处理</a>&nbsp;&nbsp;");
					}else{
						html.push(rowdata.operateAuditStatusStr);
					}
					return html.join("");
				}},
				{ display: '公司名称',  name: 'mchtInfo.companyName', width: 150 },
				{ display: '店铺名称',  name: 'mchtInfo.shopName', width: 150 },
				{ display: "公司/经营信息", name: "OPER1", width: 100, align: "center", render: function(rowdata, rowindex) {
					var html = [];
					html.push("<a href=\"javascript:viewMchtInfo(" + rowdata.mchtId + ");\">查看</a>&nbsp;&nbsp;");
					return html.join("");
				}},
				{ display: "财务信息", name: "OPER2", width: 100, align: "center", render: function(rowdata, rowindex) {
					var html = [];
					html.push("<a href=\"javascript:viewFinanceInfo(" + rowdata.mchtId + ");\">查看</a>&nbsp;&nbsp;");
					return html.join("");
				}},
				{ display: "联系人信息", name: "OPER3", width: 100, align: "center", render: function(rowdata, rowindex) {
					var html = [];
					html.push("<a href=\"javascript:viewMchtContact(" + rowdata.mchtId + ");\">查看</a>&nbsp;&nbsp;");
					return html.join("");
				}},
				{ display: "合作状态", name: "mchtInfo.statusStr", width: 100, align: "center"},
				{ display: "申请理由", name: "requestRemarksStr", width: 100, align: "center"},
			  	{ display: "查看", name: "OPER4", width: 100, align: "center", render: function(rowdata, rowindex) {
				  	var html = [];
				  	html.push("<a href=\"javascript:viewMchtCloseApply(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;");
				  	return html.join("");
			  	}},
		  ],
		  showCheckbox : false,  //不设置默认为 true
		  showRownumber:true //不设置默认为 true
      } , 							
     container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
       
  }
      

</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" name="orderById" value="2" alt="按ID倒序"/>
		<div class="search-pannel">
			<div class="search-tr"  >
			
				<div class="search-td">
				<div class="search-td-label">序号：</div>
				<div class="search-td-condition">
				<input type="text" name="mcht_code" >
				</div>
				</div>

				<div class="search-td">
				<div class="search-td-label"  >名称：</div>
				<div class="search-td-condition" >
				<input type="text" name="mcht_name" >
				</div>
				</div>

				<div class="search-td">
					<div class="search-td-label">招商审核状态：</div>
					<div class="search-td-condition" >
						<select name="merchantsAuditStatus" class="querysel">
							<option value="">请选择</option>
							<c:forEach var="info" items="${merchantsAuditStatusList}">
							<option value="${info.value}">${info.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label">运营审核状态：</div>
					<div class="search-td-condition" >
						<select name="operateAuditStatus" class="querysel">
							<option value="">请选择</option>
							<c:forEach var="info" items="${operateAuditStatusList}">
							<option value="${info.value}">${info.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>

			</div>

			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" >合作状态：</div>
					<div class="search-td-condition" >
						<select name="mchtInfoStatus" class="querysel">
							<option value="">请选择</option>
							<c:forEach var="info" items="${mchtInfoStatusList}">
								<option value="${info.STATUS_VALUE}">${info.STATUS_DESC}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div  class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" style="width: 100px; ">搜索</div>
				</div>
			</div>

		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

<script type="text/javascript">

	$(function(){

	})


	// 查看商家信息
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

	// 查看商家联系人
	function viewMchtContact(id){
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width(),
			title: "商家联系人",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/mchtContact.shtml?mchtId=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	// 查看关店详情
	function viewMchtCloseApply(id) {
		$.ligerDialog.open({
			height: 500,
			width: 600,
			title: "关店详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtCloseApply/preview.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	// 关店审核
	function audit(id) {
		$.ligerDialog.open({
			height: 550,
			width: 650,
			title: "关店审核",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtCloseApply/audit.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

</script>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
