<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
	<title>合同续签中</title>

<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript">
      var listConfig={
      url:"/mchtContract/reNewContractOnlineAuditList.shtml",
      listGrid:{
		  columns: [
			  { display: '合同生成日期', name: 'createDate', width:120 ,render: function(rowdata, rowindex) {
				  if(rowdata.createDate != null && rowdata.createDate!=""){
					  return new Date(rowdata.createDate).format("yyyy-MM-dd");
				  }
			  }},
			  { display: '招商对接人', name: 'zsContact',width:100 },
			  { display: '商家序号', name: 'mchtCode',width:100 },
			  { display: '公司名称',  name: 'companyName', width: 150 },
			  { display: '主营类目', name: 'productName',width: 100 }, 
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
			  { display: '商家上传日期', name: 'uploadDate', width:120 ,render: function(rowdata, rowindex) {
				  if(rowdata.uploadDate != null && rowdata.uploadDate!=""){
					  return new Date(rowdata.uploadDate).format("yyyy-MM-dd");
				  }
			  }},
			  { display: "查看合同详情", name: "OPER4", width: 100, align: "center", render: function(rowdata, rowindex) {
				  var html = [];
				  html.push("<a href=\"javascript:viewMchtContract(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;");
				  return html.join("");
			  }},
			  { display: "线上合同状态", name: "OPER4", width: 100, align: "center", render: function(rowdata, rowindex) {
				  var html = [];
					if(rowdata.auditStatus=="1"){
						html.push("<a href=\"javascript:toUpload(" + rowdata.id + ","+rowdata.mchtId+","+rowdata.fwStaffId+","+rowdata.fwAssistantId+");\">未上传【上传】</a>&nbsp;&nbsp;");
					}else if(rowdata.auditStatus=="2"){
						html.push("<a href=\"javascript:audit(" + rowdata.id + ","+rowdata.mchtId+","+rowdata.fwStaffId+","+rowdata.fwAssistantId+");\">已上传待审</a>&nbsp;&nbsp;");
					}else{
						html.push(rowdata.auditStatusDesc);
					}
					 return html.join("");
			  }},
			  { display: '备注',  name: 'auditRemarks', width: 150,render: function(rowdata, rowindex) {
				  var html = [];
				  html.push(rowdata.auditRemarks);
				  if((rowdata.fwStaffId == ${staffID} || rowdata.fwAssistantId == ${staffID}) && rowdata.auditStatus!="1" && rowdata.auditStatus!="2"){				  
					  html.push("<br><a href=\"javascript:modifyAuditRemarks(" + rowdata.id + ");\">【修改】</a>");
				  }
				  return html.join("");
			  }},
			  { display: '线上审核日期', name: 'auditDate', width:120 ,render: function(rowdata, rowindex) {
				  if(rowdata.auditDate != null && rowdata.auditDate!=""){
					  return new Date(rowdata.auditDate).format("yyyy-MM-dd");
				  }
			  }},
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
      
   // 合同审核
  	function audit(mchtContractId,mchtId,fwStaffId,fwAssistantId) {
  		if(fwStaffId == ${staffID} || fwAssistantId == ${staffID}){
  			$.ligerDialog.open({
  				height: $(window).height()*0.8,
  				width: $(window).width()*0.8,
  				title: "处理审核",
  				name: "INSERT_WINDOW",
  				url: "${pageContext.request.contextPath}/mchtContract/audit.shtml?id=" + mchtContractId+"&&renewType=1",
  				showMax: true,
  				showToggle: false,
  				showMin: false,
  				isResize: true,
  				slide: false,
  				data: null
  			});
  		}else{
  			$.ligerDialog.error("只有该商家的法务对接人及对接人的协助人才可以操作");
  		}
  	}
      
    //上传合同图片页面
      function toUpload(mchtContractId,mchtId,fwStaffId,fwAssistantId) {   
    	 if(fwStaffId == ${staffID} || fwAssistantId == ${staffID}){
      		$.ligerDialog.open({
      			height: $(window).height() - 100,
      			width: $(window).width() - 250,
      			title: "上传合同",
      			name: "INSERT_WINDOW",
      			url: "${pageContext.request.contextPath}/mchtContract/toUpload.shtml?id=" + mchtContractId,
      			showMax: true,
      			showToggle: false,
      			showMin: false,
      			isResize: true,
      			slide: false,
      			data: null
      		});
      	}else{
      		$.ligerDialog.error("只有该商家的法务对接人及对接人的协助人才可以操作");
      	}
      }
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td" style="width:240px;">
					<div class="search-td-label">商家序号</div>
					<div class="search-td-condition"> 
						<input type="text" id="mchtCode" name="mchtCode">
					</div>
				</div>
				<div class="search-td" style="width:240px;">
					<div class="search-td-label">名称</div>
					<div class="search-td-condition">
						<input type="text" id="companyOrShop" name="companyOrShop">
					</div>
				</div>
				<div class="search-td" style="width:240px;">
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
		 		<div class="search-td" style="width:240px;">
						<div class="search-td-label">对接人：</div>
						<div class="search-td-condition">
							<select id="platContactStaffId" name="platContactStaffId">
								<c:if test="${isManagement == 1}">
									<option value="" selected="selected">全部商家</option>
								</c:if>
								<option value="${staffID}" selected="selected" >我自己的商家</option>
								<c:forEach items="${sysStaffInfoCustomList }" var="sysStaffInfoCustom">
									<option value="${sysStaffInfoCustom.staffId }">${sysStaffInfoCustom.staffName }</option>
								</c:forEach>
							</select>
						</div>
				</div>
				<div class="search-td" style="width:240px;">
					<div class="search-td-label">线上合同状态</div>
					<div class="search-td-condition">
						<select id="auditStatus" name="auditStatus">
							<option value="">请选择</option>
							<option value="1">未上传</option>
							<option value="2">已上传待审</option>
							<option value="3">通过</option>
							<option value="4">驳回</option>
						</select>
					</div>
				</div>					
					<div style="display: inline-flex;margin-bottom:5px;">
					<div id="searchbtn" class="l-button">搜索</div>
					</div>					
			</div>
		</div>		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

<script type="text/javascript">

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

	   //修改备注
    function modifyAuditRemarks(id) {
        $.ligerDialog.open({
            height: 500,
            width: 600,
            title: "修改备注",
            name: "INSERT_WINDOW",
            url: "${pageContext.request.contextPath}/mchtContract/modifyAuditRemarks.shtml?id=" + id,
            showMax: true,
            showToggle: false,
            showMin: false,
            isResize: true,
            slide: false,
            data: null,
            id: "closeReload"
        });
    }
</script>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
