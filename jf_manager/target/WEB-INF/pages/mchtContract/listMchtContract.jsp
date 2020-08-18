<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
	<title>商家合同管理</title>

<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript">
      var listConfig={
      url:"/mchtContract/listMchtContractData.shtml",
      listGrid:{
		  columns: [
			  { display: '开店日期', name: 'mchtInfo.cooperateBeginDate', width:120 ,render: function(rowdata, rowindex) {
				  if(rowdata.mchtInfo.cooperateBeginDate != null && rowdata.mchtInfo.cooperateBeginDate!=""){
					  return new Date(rowdata.mchtInfo.cooperateBeginDate).format("yyyy-MM-dd");
				  }
			  }},
			  { display: '招商对接人', name: 'platformMerchantsContact.contactName',width:100 },
			  // { display: '商家序号', name: 'mchtInfo.mchtCode',width:100 },
			  { display: '商家序号',width: 100, render: function(rowdata, rowindex) {
					  var html = [];
					  html.push(rowdata.mchtInfo.mchtCode);
					  html.push("<br>");
					  if(rowdata.mchtInfo.mchtType == "2"){
						  html.push("POP");
					  }else if (rowdata.mchtInfo.mchtType == "1" && rowdata.mchtInfo.isManageSelf == "1"){
						  html.push("自营SPOP");
					  }else {
						  html.push("SPOP");
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
			  { display: "合同详情", name: "OPER4", width: 100, align: "center", render: function(rowdata, rowindex) {
				  var html = [];
				  html.push("<a href=\"javascript:viewMchtContract(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;");
				  return html.join("");
			  }},
			  { display: "商家寄件状态", name: "isMchtSendStr", width: 100, align: "center", render: function(rowdata, rowindex) {
				  var html = [];
				  html.push(rowdata.isMchtSendStr);
				  if(rowdata.mchtExpressNo != null){
					  html.push("<br/><a href=\"javascript:viewWuliu(" + rowdata.mchtExpressId + ", " + rowdata.mchtExpressNo + ");\">单号：" + rowdata.mchtExpressNo + "</a>");
				  }
				  return html.join("");
			  }},
			  { display: "合同归档状态", name: "archiveStatusStr", width: 100, align: "center", render: function(rowdata, rowindex) {
				  var html = [];
				  if(rowdata.archiveStatusStr=="未处理"){
					  html.push("<a href=\"javascript:archive(" + rowdata.id + "," + rowdata.mchtId + ");\">未处理</a>&nbsp;&nbsp;");
				  }else if(rowdata.archiveStatusStr=="不通过驳回"){
					  html.push("<a href=\"javascript:archive(" + rowdata.id + "," + rowdata.mchtId + ");\">不通过驳回</a>&nbsp;&nbsp;");
				  }else{
					  html.push(rowdata.archiveStatusStr);
				  }

				  return html.join("");
			  }},
			  { display: '内部备注',  name: 'fwInnerRemarks', width: 150, align: "center", render: function(rowdata, rowindex) {
				  if(rowdata.fwInnerRemarks == null)	return "";
				  var html = [];
				  html.push("<a href=\"javascript:editFwInnerRemarks(" + rowdata.id + ");\">" + rowdata.fwInnerRemarks + "</a>");
				  return html.join("");
			  }},
			  { display: '驳回原因',  name: 'remarks', width: 150, align: "center", render: function(rowdata, rowindex) {
				  var html = [];
				  if(rowdata.archiveStatusStr=="不通过驳回"){
					  html.push(rowdata.remarks);
				  }
				  return html.join("");
			  }},
			  { display: "合同编号", name: "contractCode", width: 100, align: "center"},
			  { display: '法务对接人', name: 'platformFawuContact.contactName',width:100 }
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
		<input type="hidden" name="cmd" value="" alt="查询或导出的命令"/>
		<input type="hidden" name="orderById" value="2" alt="按合同开始时间倒序"/>
		<div class="search-pannel">
			<div class="search-tr"  >
			
				<div class="search-td">
				<div class="search-td-label">商家序号：</div>
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
					<div class="search-td-label"  >合同编号：</div>
					<div class="search-td-condition" >
						<input type="text" name="contractCode" >
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label" style="float:left;">是否自营：</div>
					<div class="search-td-condition" >
						<select id="isManageSelf" name="isManageSelf" style="width: 150px;">
							<option value="">请选择</option>
							<option value="0">非自营</option>
							<option value="1">自营</option>
						</select>
					</div>
				</div>

			</div>

			<div class="search-tr"  >

				<div class="search-td">
					<div class="search-td-label">商家寄件状态：</div>
					<div class="search-td-condition" >
						<select name="isMchtSend" class="querysel">
							<option value="">请选择</option>
							<option value="0">未寄出</option>
							<option value="1">已寄出</option>
						</select>
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label">合同归档状态：</div>
					<div class="search-td-condition" >
						<select name="archiveStatus" class="querysel">
							<option value="">请选择</option>
							<option value="0">未处理</option>
							<option value="1">通过已归档</option>
							<option value="2">不通过驳回</option>
							<option value="4">不签约</option>
						</select>
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label">对接人：</div>
					<div class="search-td-condition">
						<select id="platContactStaffId" name="platContactStaffId">
							<c:if test="${sessionScope.USER_SESSION.isManagement==1}">
								<option value="" selected="selected">全部商家</option>
							</c:if>
							<option value="${sessionScope.USER_SESSION.staffID}" selected="selected" >我自己的商家</option>
							<c:forEach items="${sysStaffInfoCustomList }" var="sysStaffInfoCustom">
								<option value="${sysStaffInfoCustom.staffId }">${sysStaffInfoCustom.staffName }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">类目:</div>
					<div class="search-td-condition" >
						<select id="productTypeId" name="productTypeId">
							<option value="">请选择</option>
							<c:forEach items="${sysStaffProductTypeCustomList}" var="sysStaffProductTypeCustom">
								<option value="${sysStaffProductTypeCustom.productTypeId}">${sysStaffProductTypeCustom.staffName}</option>
							</c:forEach>
						</select>
				 	</div>
				</div>

				<div  class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" style="width: 100px; ">搜索</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="导出" id="export">
					</div>
				</div>

			</div>

		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

<script type="text/javascript">

	// 合同归档处理

	function archive(mchtContractId,mchtId) {
		var role107 = ${role107};
		role107 = true;
		if(role107){
			$.ligerDialog.open({
				height: $(window).height()*0.8,
				width: 640,
				title: "处理归档",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mchtContract/archive.shtml?id=" + mchtContractId+"&mchtId="+mchtId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}else{
			$.ajax({
					url : "${pageContext.request.contextPath}/mchtContract/checkFwPlatformContact.shtml",
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {
						mchtId:mchtId
					},
					timeout : 30000,
					success : function(data) {
						if ("0000" == data.returnCode) {
							$.ligerDialog.open({
								height: $(window).height()*0.8,
								width: 640,
								title: "处理归档",
								name: "INSERT_WINDOW",
								url: "${pageContext.request.contextPath}/mchtContract/archive.shtml?id=" + mchtContractId+"&mchtId="+mchtId,
								showMax: true,
								showToggle: false,
								showMin: false,
								isResize: true,
								slide: false,
								data: null
							});
						}else{
							$.ligerDialog.error(data.returnMsg);
						}
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
		}
	}

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

	// 查看商家合同详情
	function viewMchtContract(mchtContractId) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 250,
			title: "商家合同详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtContract/viewMchtContract.shtml?id=" + mchtContractId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	// 法务修改备注
	function editFwInnerRemarks(mchtContractId) {
		$.ligerDialog.open({
			height: 280,
			width: 640,
			title: "法务内部备注",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtContract/editFwInnerRemarks.shtml?id=" + mchtContractId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	function viewWuliu(expressId, expressNo) {
		$.ligerDialog.open({
			height : $(window).height(),
			width : $(window).width() * 0.35,
			title : "查看物流动态",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/resource/viewWuliu.shtml?expressId="
					+ expressId + "&expressNo=" + expressNo,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	$(function(){
		$("#closeReload .l-dialog-close").live("click", function(){
			$("#searchbtn").click();
		}); 
		
		$("#export").bind('click',function(){
			$("#dataForm").attr("action", "${pageContext.request.contextPath}/mchtContract/exportMchtContractData.shtml");
	        $("#dataForm").submit(); 
	        $("#dataForm").attr("action", "${pageContext.request.contextPath}/mchtContract/listMchtContractData.shtml");
	
			//$("#searchbtn").click();
			//location.href="${pageContext.request.contextPath}/mchtContract/export.shtml?mchtCode="+mchtCode
		});
		
	});

</script>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
