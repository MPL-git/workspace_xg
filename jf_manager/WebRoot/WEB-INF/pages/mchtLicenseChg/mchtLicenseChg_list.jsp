<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
 <script type="text/javascript">
var viewerPictures; 
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
}); 

function toAudit(id) {
	$.ligerDialog.open({
		height: 600,
		width: 1300,
		title: "编辑",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtLicenseChg/toAudit.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

 var listConfig={
     url:"/mchtLicenseChg/data.shtml",
     listGrid:{ columns: [
			            { display: '提审日期', name:'',render: function (rowdata, rowindex) {
							var createDate = new Date(rowdata.createDate);
							return createDate.format("yyyy-MM-dd hh:mm:ss");
						}},
			            { display: '商家序号', name:'mchtCode',render: function (rowdata, rowindex) {
			            	return rowdata.mchtCode;
						}},
			            { display: '名称', name:'companyName',render: function (rowdata, rowindex) {
			            	return rowdata.companyName;
						}},
			            { display: '主营类目', name:'productTypeName',render: function (rowdata, rowindex) {
			            	return rowdata.productTypeName;
						}},
						{ display: '审核状态', name:'auditStatus',render: function (rowdata, rowindex) {
							if(rowdata.auditStatus == "0"){
								return '<a href="javascript:;" onclick="toAudit('+rowdata.id+');">待审</a>';
							}else if(rowdata.auditStatus == "1"){
								return "通过";
							}else if(rowdata.auditStatus == "2"){
								return "驳回";
							}
						}},
						{ display: '驳回原因', name:'licenseRejectReason',render: function (rowdata, rowindex) {
							return rowdata.licenseRejectReason;
						}},
			            { display: '法务对接人',name:'op',render: function (rowdata, rowindex) {
		            		return rowdata.fwName;
						}}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: true //不设置默认为 true
      } ,  							
     container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
  };
  
 function viewerPic(imgPath){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
			$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
			viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		viewerPictures.show();
	}
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr">
			<div class="search-td">
			<div class="search-td-label" >商家序号</div>
			<div class="search-td-condition">
				<input type="text" id="mchtCode" name="mchtCode" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >名称：</div>
			<div class="search-td-condition">
				<input type="text" id="name" name="name" >
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
				
			<div class="search-td">
			<div class="search-td-label">审核状态:</div>
			<div class="search-td-condition" >
				<select id="auditStatus" name="auditStatus">
					<option value="">请选择</option>
					<option value="0">待审</option>
					<option value="1">通过</option>
					<option value="2">驳回</option>
				</select>
		 	</div>
			</div>
		</div>	
		<div class="search-tr">	
			<div class="search-td">
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
			
			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
		</div>
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>