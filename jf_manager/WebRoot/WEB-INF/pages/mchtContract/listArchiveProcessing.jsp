<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
<title>商家资料归档审核</title>

<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript">
      var listConfig={
      url:"/mchtContract/listArchiveProcessingData.shtml",
      listGrid:{
		  columns: [
			  { display: '开店日期', name: 'cooperateBeginDate', width:120 ,render: function(rowdata, rowindex) {
				  if(rowdata.cooperateBeginDate){
					  return new Date(rowdata.cooperateBeginDate).format("yyyy-MM-dd");
				  }else{
					  return "";
				  }
			  }},
			  { display: '招商对接人', name: 'zsContact',width:100 },
			  { display: '商家序号', name: 'mchtCode',width:100 },
			  { display: '入驻类型',width: 80, render: function(rowdata, rowindex) {
	            	var html = [];
	            	if(rowdata.settledType == "1"){
	            		html.push("企业公司");
	            	}else if(rowdata.settledType == "2"){
	            		html.push("个体商户");
	            	}
	            	return html.join("");
	          }},
			  { display: '公司名称',  name: 'companyName', width: 150 },
			  { display: '店铺名',  name: 'shopName', width: 150 },
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
			  { display: "合同详情", name: "OPER4", width: 100, align: "center", render: function(rowdata, rowindex) {
				  var html = [];
				  html.push("<a href=\"javascript:viewMchtContract(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;");
				  return html.join("");
			  }},
			  { display: "公司资质归档状态", name: "OPER4", width: 100, align: "center", render: function(rowdata, rowindex) {
				  var html = [];
				  var companyInfoArchiveStatus = rowdata.companyInfoArchiveStatus;
				  var archiveStatusDesc;
				  if(!companyInfoArchiveStatus || companyInfoArchiveStatus == 0){
					  archiveStatusDesc="<span style='color:red;'>【未齐全】</span>";
				  }else{
					  archiveStatusDesc="<span style='color:red;'>【已归档】</span>";
				  }
				  html.push(archiveStatusDesc);
				  html.push("<a href=\"javascript:viewCompanyInfoArchive(" + rowdata.mchtId + ");\">查看</a>&nbsp;&nbsp;");
				  return html.join("");
			  }},
			  { display: "品牌资质归档状态", name: "OPER4", width: 100, align: "center", render: function(rowdata, rowindex) {
				  return rowdata.mchtBrandArchiveStatusHtml;
			  }},
			  { display: "经营许可证归档", name: "licenseArchiveStatus", width: 100, align: "center", render: function(rowdata, rowindex) {
				  var html = [];
				  if(rowdata.businessLicensePic){
					  var licenseArchiveStatus = rowdata.licenseArchiveStatus;
					  if(licenseArchiveStatus == 1){
						  html.push('<span style="color:red;">【已齐全】</span>');
					  }else{
						  html.push('<span style="color:red;">【未齐全】</span>');
					  }
					  html.push('<a href="javascript:;" onclick="toLicenseArchiveStatus('+rowdata.mchtId+');">【查看】</a>');
				  }
				  return html.join("");
			  }},
			  { display: "商家寄件状态", name: "isMchtSend", width: 100, align: "center", render: function(rowdata, rowindex) {
				  if(!rowdata.isMchtSend || rowdata.isMchtSend == 0){
					  return "未寄出";
				  }else{
					  return "已寄出";
				  }
			  }},
			  { display: "合同归档状态", name: "archiveStatusDesc", width: 100, align: "center", render: function(rowdata, rowindex) {
				  var html = [];
				  var role107 = ${role107};
				  if(role107){
					  html.push("<a href=\"javascript:archive(" + rowdata.id + ","+rowdata.mchtId+");\">"+rowdata.archiveStatusDesc+"</a>");
				  }else{
					  if(rowdata.archiveStatus == '0' || rowdata.archiveStatus == '2'){
						  html.push("<a href=\"javascript:archive(" + rowdata.id + ","+rowdata.mchtId+");\">"+rowdata.archiveStatusDesc+"</a>");
					  }else{
						  html.push(rowdata.archiveStatusDesc);
					  }
				  }
				  return html.join("");
			  }},
			  { display: "合同编号", name: "contractCode", width: 100, align: "center"},
			  { display: '法务对接人', name: 'fwContact',width:100 }
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
		<input type="hidden" name="contractType" value="1" alt="新签"/>
<!-- 	<input type="hidden" name="status" value="3" alt="合同已审核通过"/> -->
		<input type="hidden" name="mchtInfoStatusIn" value="[0,1,2]" alt="商家合作状态!=关闭"/>
<!-- 	<input type="hidden" name="archiveStatusIn" value="[0,2,4]" alt="合同不等于已归档"/> -->
		<input type="hidden" name="orderByAuditDate" value="1" alt="按合同审核时间升序"/>
		<input type="hidden" name="orderById" value="2" alt="按合同ID降序"/>
		<input type="hidden" name="notArchive" value="-1" alt="公司资质归档状态,品牌资质归档状态,合同归档状态任意一个未归档"/>
		<div class="search-pannel">
			<div class="search-tr"  >
			
				<div class="search-td">
				<div class="search-td-label">商家序号：</div>
				<div class="search-td-condition">
				<input type="text" name="mcht_code" id="mchtCode">
				</div>
				</div>

				<div class="search-td">
				<div class="search-td-label"  >名称：</div>
				<div class="search-td-condition" >
				<input type="text" name="mcht_name" id="mchtName">
				</div>
				</div>

				<div class="search-td">
					<div class="search-td-label"  >合同编号：</div>
					<div class="search-td-condition" >
						<input type="text" name="contractCode" id="contractCode">
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label">商家寄件状态：</div>
					<div class="search-td-condition" >
						<select name="isMchtSend" class="querysel" id="isMchtSend">
							<option value="">请选择</option>
							<option value="0">未寄出</option>
							<option value="1">已寄出</option>
						</select>
					</div>
				</div>
				
			</div>

			<div class="search-tr"  >

				<div class="search-td">
					<div class="search-td-label">合同归档状态：</div>
					<div class="search-td-condition" >
						<select name="archiveStatus" class="querysel" id="archiveStatus">
							<option value="">请选择</option>
							<option value="0" <c:if test="${archiveStatus == '0'}">selected="selected"</c:if>>未处理</option>
							<option value="1">通过已归档</option>
							<option value="2">不通过驳回</option>
							<option value="4">不签约</option>
						</select>
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">公司资质归档状态：</div>
					<div class="search-td-condition" >
						<select name="companyInfoArchiveStatus" class="querysel" id="companyInfoArchiveStatus">
							<option value="">请选择</option>
							<option value="0">未齐全</option>
							<option value="1">已归档</option>
						</select>
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">品牌资质归档状态：</div>
					<div class="search-td-condition" >
						<select name="mchtBrandArchiveStatus" class="querysel" id="mchtBrandArchiveStatus">
							<option value="">请选择</option>
							<option value="0">未齐全</option>
							<option value="1">已归档</option>
						</select>
					</div>
				</div>
			</div>	
			<div class="search-tr"  >
				<div class="search-td">
				<div class="search-td-label" style="float:left;">入驻类型：</div>
				<div class="search-td-condition" >
					<select id="settledType" name="settledType" style="width: 150px;">
						<option value="">请选择</option>
						<option value="1">企业公司</option>
						<option value="2">个体商户</option>
					</select>
			 	 </div>
			 	 </div>
			 	 
				<div class="search-td">
				<div class="search-td-label" style="float:left;">合作状态：</div>
				<div class="search-td-condition" >
					<select id="mchtInfoStatus" name="mchtInfoStatus" style="width: 150px;">
						<option value="">请选择</option>
						<c:forEach items="${mchtInfoStatusList}" var="mchtInfoStatus">
							<c:if test="${mchtInfoStatus.statusValue ne 5}">
								<option value="${mchtInfoStatus.statusValue}">${mchtInfoStatus.statusDesc}</option>
							</c:if>
						</c:forEach>
					</select>
			 	 </div>
			 	 </div>
					
				<div class="search-td">
					<div class="search-td-label" >
						<input type="checkbox" class="liger-checkbox" name="is_my_fawu" value="1" id="isMyFawu"/>
					</div>
					<div class="search-td-condition" style="text-align: left;">
						只看本人法务对接
					</div>
				</div>

				<div class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" style="height: 28px;line-height: 28px;">
						搜索
					</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="导出" id="export">
					</div>
				</div>
				
			</div>

		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

<script type="text/javascript">

	//资质归档情况
	function viewMchtInfoArchive(mchtId) {
		$.ligerDialog.open({
			height: $(window).height()*0.9,
			width: $(window).width()*0.9,
			title: "资质归档情况",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtContract/mchtInfoArchive.shtml?mchtId=" + mchtId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//公司资质归档状态查看
	function viewCompanyInfoArchive(mchtId) {
		$.ligerDialog.open({
			height: $(window).height()*0.9,
			width: $(window).width()*0.9,
			title: "公司资质归档",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtContract/viewCompanyInfoArchive.shtml?mchtId=" + mchtId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null,
			id: "closeReload"
		});
	}
	
	//品牌资质归档状态查看
	function viewMchtProductBrandPics(id) {
		$.ligerDialog.open({
			height: $(window).height()*0.9,
			width: $(window).width()*0.9,
			title: "品牌资质归档",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/viewMchtProductBrandPics.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null,
			id: "closeReload"
		});
	}
	
	// 合同归档处理
	function archive(mchtContractId,mchtId) {
		var role107 = ${role107};
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
	
	function toLicenseArchiveStatus(mchtId){
		$.ligerDialog.open({
			height: 400,
			width: 600,
			title: "经营许可证归档状态",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtContract/toLicenseArchiveStatus.shtml?mchtId=" + mchtId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
$(function(){
	$("#closeReload .l-dialog-close").live("click", function(){
		$("#searchbtn").click();
	}); 
	
	$("#export").bind('click',function(){
		var mchtCode = $("#mchtCode").val();
		var mchtName = $("#mchtName").val();
		var contractCode = $("#contractCode").val();
		var isMchtSend = $("#isMchtSend").val();
		var archiveStatus = $("#archiveStatus").val();
		var isMyFawu = $("input[name='is_my_fawu']:checked").val();
		if(isMyFawu){
			isMyFawu = 1;
		}else{
			isMyFawu = "";
		}
		location.href="${pageContext.request.contextPath}/mchtContract/export.shtml?mchtCode="+mchtCode+"&mchtName="+mchtName+"&contractCode="+contractCode+"&isMchtSend="+isMchtSend+"&archiveStatus="+archiveStatus+"&isMyFawu="+isMyFawu;
	});
	
	
});
</script>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>