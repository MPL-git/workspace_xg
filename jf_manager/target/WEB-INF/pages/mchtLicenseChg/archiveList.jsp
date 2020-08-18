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

function toArchive(id) {
	$.ligerDialog.open({
		height: 600,
		width: 800,
		title: "归档",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtLicenseChg/toArchive.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function mchtLicenseChgHandleArchive(id) {
	$.ligerDialog.open({
		height : $(window).height() - 400,
		width : $(window).width() * 0.35,
		title : "处理归档",
		name : "INSERT_WINDOW",
		url : "${pageContext.request.contextPath}/mchtLicenseChg/mchtLicenseChgHandleArchive.shtml?id="+ id ,
		showMax : true,
		showToggle : false,
		showMin : false,
		isResize : true,
		slide : false,
		data : null
	});
}

function mchtLicenseChgInnerRemarks(id) {
	$.ligerDialog.open({
		height : $(window).height() - 400,
		width : $(window).width() * 0.35,
		title : "修改备注",
		name : "INSERT_WINDOW",
		url : "${pageContext.request.contextPath}/mchtLicenseChg/mchtLicenseChgInnerRemarks.shtml?id="+ id ,
		showMax : true,
		showToggle : false,
		showMin : false,
		isResize : true,
		slide : false,
		data : null
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

//导出
function excel(){  
	$("#dataForm").attr("action","${pageContext.request.contextPath}/mchtLicenseChg/archiveDataExport.shtml");
	$("#dataForm").submit();
}

 var listConfig={
     url:"/mchtLicenseChg/archiveData.shtml",
     listGrid:{ columns: [
			            { display: '提审日期', name:'',render: function (rowdata, rowindex) {
							var createDate = new Date(rowdata.createDate);
							return createDate.format("yyyy-MM-dd hh:mm:ss");
						}},
			            { display: '商家序号', name:'mchtCode',render: function (rowdata, rowindex) {
			            	return rowdata.mchtCode;
						}},
						{ display: '公司名称', name: 'companyName',render: function(rowdata, rowindex){
		  					var html = [];
		  					 html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.mchtId + ");\">"+rowdata.companyName+"</a>"); 
		  					return html.join("");
	  					}},
			            { display: '店铺名称', name:'shopName',render: function (rowdata, rowindex) {
			            	return rowdata.shopName;
						}},
			            { display: '主营类目', name:'productTypeName',render: function (rowdata, rowindex) {
			            	return rowdata.productTypeName;
						}},
			            { display: '商家寄件状态', name:'sendAState',render: function (rowdata, rowindex) {
		  					var html = [];
		  					if(!rowdata.expressId || !rowdata.expressNo){
		  					 	html.push("未寄出"); 
		  					}else{
		  						html.push("已寄出<br><a href=\"javascript:viewWuliu(" + rowdata.expressId + ",'" + rowdata.expressNo + "');\">单号:" + rowdata.expressNo + "</a>"); 
		  					}
		  					return html.join("");
						}},
						{ display: '资料齐全状态', name:'archiveStatus',render: function (rowdata, rowindex) {
							if(rowdata.archiveStatus == 3){
								return '<span style="color:red;">【已齐全】</span><a href="javascript:;" onclick="toArchive('+rowdata.id+');">【查看】</a>';
							}else{
								return '<span style="color:red;">【未齐全】</span><a href="javascript:;" onclick="toArchive('+rowdata.id+');">【查看】</a>';
							}
						}},
						{ display: '归档处理', name:'archiveDealStatus',render: function (rowdata, rowindex) {
							var html = [];
		  					if(rowdata.archiveDealStatus == 1){
		  						 html.push("通过"); 
		  					}else if(rowdata.archiveDealStatus == 2){
		  						 html.push("<a href=\"javascript:mchtLicenseChgHandleArchive(" + rowdata.id + ");\">驳回</a>"); 
		  					}else{
		  						 html.push("<a href=\"javascript:mchtLicenseChgHandleArchive(" + rowdata.id + ");\">未处理</a>");
		  					}
		  					return html.join("");
						}},
						{ display: '内部备注', name:'archiveDealInnerRemarks',render: function (rowdata, rowindex) {
							var html = [];
		  					if(rowdata.archiveDealInnerRemarks!=null && rowdata.archiveDealInnerRemarks!=""){
		  						html.push(rowdata.archiveDealInnerRemarks);
		  						html.push("<br><a href=\"javascript:mchtLicenseChgInnerRemarks(" + rowdata.id + ");\">修改</a>");	
		  					}
		  					return html.join("");
						}},
						{ display: '驳回原因', name:'archiveDealRemarks',render: function (rowdata, rowindex) {
			            	return rowdata.archiveDealRemarks;
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
			<div class="search-td-label">归档状态:</div>
			<div class="search-td-condition" >
				<select id="archiveStatus" name="archiveStatus">
					<option value="">请选择</option>
					<option value="0">未齐全</option>
					<option value="1">已齐全</option>
				</select>
		 	</div>
			</div>
		</div>	
		<div class="search-tr">	
			<div class="search-td">
				 <div class="search-td-label">归档处理状态:</div>
				 <div class="search-td-condition" >
					 <select id="archiveDealStatus" name="archiveDealStatus" class="querysel">
						<option value="">请选择</option>
						<option value="0" selected="selected">未处理</option>
						<option value="1">通过</option>
						<option value="2">驳回</option>
					</select>
				 </div>
			 </div>
			
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
			
			<!-- <div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div> -->
			<div class="search-td-search-btn" style="display: inline-flex; ">
					<div style="padding-left: 10px;">
						<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="搜索" id="searchbtn">
					</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="导出" id="export" onclick="excel();" >
					</div>
			</div>
		</div>
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>