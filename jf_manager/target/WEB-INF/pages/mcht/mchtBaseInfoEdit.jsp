<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	
<link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>	

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

td input,td select{
border:1px solid #AECAF0;
}
.radioClass{
	margin-right: 20px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}

.table-title-link{
color: #1B17EE;
font-size: 15px;
text-decoration: none;
}

.table-title{
font-size: 17px;font-weight: 600;
}
</style>
<style type="text/css">
.middle input {
	display: block;
	width: 30px;
	margin: 2px;
}
table.l-checkboxlist-table td{
	border-style: none;
}
table.l-listbox-table td{
	border-style: none;
}
.td-pictures li{
display: inline-block;
}
td img{
width: 60px;
height: 40px;
}
</style>
<script type="text/javascript">
var viewerPictures;
var idcarPicViewer;
var blPicViewer;
$(function(){
	
	$('#brandType').val(${mchtInfo.brandType});
	
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	idcarPicViewer = new Viewer(document.getElementById('idcarPicViewer'), {});
	blPicViewer = new Viewer(document.getElementById('blPicViewer'), {});
	Date.prototype.format = function(fmt)   
	{ //author: meizz   
	  var o = {   
	    "M+" : this.getMonth()+1,                 //月份   
	    "d+" : this.getDate(),                    //日   
	    "h+" : this.getHours(),                   //小时   
	    "m+" : this.getMinutes(),                 //分   
	    "s+" : this.getSeconds(),                 //秒   
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
	    "S"  : this.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;   
	} ;
});


function mchtInfoChgList(mchtId) {
	$.ligerDialog.open({
	height: $(window).height() - 40,
	width: $(window).width() - 40,
	title: "公司信息",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/mchtInfoChgList.shtml?mchtId=" +mchtId,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}

function mchtTaxInvoiceInfoEdit(id) {
	$.ligerDialog.open({
	height: $(window).height()-200,
	width: $(window).width()-500,
	title: "税务开票信息修改与审核",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/mchtTaxInvoiceInfoEdit.shtml?id=" +id,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}	
function changeShopNameAuditStatus(id) {
	$.ligerDialog.open({
	height: $(window).height()-200,
	width: $(window).width()-500,
	title: "设置店铺名",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/changeShopNameAuditStatus.shtml?id=" +id,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}


function changeStatus(id,status) {
	$.ligerDialog.open({
	height: 450,
	width: 400,
	title: "合作状态修改",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/changeMchtStatus.shtml?mchtId=" + id+"&status="+status,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}

function changeMchtUser(mchtUserId) {
	$.ligerDialog.open({
	height: 350,
	width: 400,
	title: "用户信息修改",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/changeMchtUser.shtml?mchtUserId=" + mchtUserId,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}
function totalAudit(mchtId) {
	$.ligerDialog.open({
	height: 550,
	width: 600,
	title: "审核",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/totalAudit.shtml?mchtId=" + mchtId,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}

function changeMchtBaseInfo(mchtId) {
	$.ligerDialog.open({
	height: 470,
	width: 1000,
	title: "商家合作信息修改",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/changeMchtBaseInfo.shtml?mchtId=" + mchtId,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}

function changeMchtCompanyInfo(mchtId) {
	$.ligerDialog.open({
	height: $(window).height() - 40,
	width: 1000,
	title: "商家资质公司信息修改与审核",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/changeMchtCompanyInfo.shtml?mchtId=" + mchtId,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}

function changeIsCompanyInfPerfect(mchtId) {
	$.ligerDialog.open({
	height: 300,
	width: 650,
	title: "审核状态",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/changeIsCompanyInfPerfect.shtml?mchtId=" + mchtId,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}


function addMchtProductType(mchtId) {
	$.ligerDialog.open({
	height: 250,
	width: 500,
	title: "添加商家经营品类",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/editMchtProductType.shtml?mchtId=" + mchtId,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}
function editMchtProductType(mchtProductTypeId) {
	$.ligerDialog.open({
	height: 250,
	width: 500,
	title: "审核",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/editMchtProductType.shtml?mchtProductTypeId=" + mchtProductTypeId,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}
function delMchtProductType(mchtProductTypeId,productTypeName) {
	var title="是否删除品类:"+productTypeName;
	$.ligerDialog.confirm(title, function (yes) { 
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/mcht/mchtProductTypeDelSubmit.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {mchtProductTypeId:mchtProductTypeId},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						$("#maingrid").ligerGetGridManager().reload();
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
		});
	
	
}


function editMchtProductBrand(mchtProductBrandId, view) {
	$.ligerDialog.open({
	height: $(window).height() - 40,
	width: 1000,
	title: "品牌信息",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/editMchtProductBrand.shtml?view="+ view +"&mchtProductBrandId=" + mchtProductBrandId,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}

function auditMchtProductBrand(mchtProductBrandId) {
	$.ligerDialog.open({
	height: $(window).height() - 40,
	width: 1500,
	title: "入驻品牌",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/auditMchtProductBrand.shtml?mchtProductBrandId=" + mchtProductBrandId,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}

function addMchtProductBrand(mchtId) {
	$.ligerDialog.open({
	height: $(window).height() - 40,
	width: 1000,
	title: "修改商家授权品牌",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/editMchtProductBrand.shtml?mchtId=" + mchtId,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}

function mchtBrandChgList(mchtProductBrandId) {
	$.ligerDialog.open({
		height: $(window).height()-40,
		width: $(window).width()-40,
		title: "品牌信息更新查询",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/mchtBrandChgList.shtml?mchtProductBrandId=" + mchtProductBrandId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}


function viewerPic(imgPath){
	
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
	viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	$("#viewer-pictures").show();
	viewerPictures.show();
	
}

//修改或查看特批
function editOrShowMchtProductBrandApproved(mchtProductBrandId, statusPage) {
	$.ligerDialog.open({
		height: 400,
		width: 500,
		title: "招商标记",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/editOrShowMchtProductBrandApproved.shtml?mchtProductBrandId=" + mchtProductBrandId +"&statusPage=" + statusPage,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

//撤销特批
function updateMchtProductBrandApproved(mchtProductBrandId) {
	$.ligerDialog.confirm("撤销后将不能更改，请确认是否更改", function (yes) { 
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/mcht/updateMchtProductBrandApproved.shtml",
				type : 'post',
				dataType : 'json',
				data : {mchtProductBrandId : mchtProductBrandId, isSpeciallyApproved : '0'},
				success : function(data) {
					if(data.returnCode == '0000') 
						maingridBrandReload();
					else 
						$.ligerDialog.error(data.returnMsg);
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
	});
}

//品牌初始化
function brandInitialization(mchtProductBrandId) {
	$.ligerDialog.confirm("是否将品牌初始化", function (yes) {
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/mcht/brandInitialization.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {mchtProductBrandId:mchtProductBrandId},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("初始化成功");
						javascript:location.reload();
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
	});
}


function maingridBrandReload() {
	$("#maingridBrand").ligerGetGridManager().reload();
}


function viewerMchtPic(mchtProductBrandId,picType){
	
	var url;
	if(picType==1){
		url="${pageContext.request.contextPath}/mcht/getMchtBrandPic.shtml";
	}
	if(picType==2){
		url="${pageContext.request.contextPath}/mcht/getPlatfromAuthPic.shtml";
	}
	
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
	$.ajax({
		url : url,
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {mchtProductBrandId:mchtProductBrandId},
		timeout : 30000,
		success : function(data) {
			if(data&&data.length>0){
				for (var i=0;i<data.length;i++)
				{
					$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" alt=""></li>');
				}
				viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
					$("#viewer-pictures").hide();
				}});
				$("#viewer-pictures").show();
				viewerPictures.show();
			}
			
			
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
	
}

	  $(function(){
			var brandGrid = $("#maingridBrand").ligerGrid({
              columns: [
				{ display: '申请品牌名称', name: 'productBrandName',render: function(rowdata, rowindex) {
		       	  	return rowdata.productBrandName+'<a href="javascript:;" onclick="editMchtProductBrand('+rowdata.id+',0);">【查看】</a>';
			 	}},          
              	{ display: '品牌库logo', name: 'logo',render: function(rowdata, rowindex) {
					if(rowdata.productBrandId){
	            	  	return "<div style='padding:3px;'><img src='${pageContext.request.contextPath}/file_servelt"+rowdata.brandLogo+"'></div>";
					}else{
						return "";
					}
  			 	} },
              	{ display: '品牌库名称', name: 'brandName' ,render: function(rowdata, rowindex) {
	          	  	if(rowdata.productBrandId){
		       	  		return rowdata.brandName+'<a href="javascript:;" onclick="editMchtProductBrand('+rowdata.id+',0);">【查看】</a>';
		       	  	}else if(rowdata.mchtStatus == 1 && !rowdata.productBrandId ){
						return '<a href="javascript:;" onclick="brandInitialization('+rowdata.id+');">【品牌初始化】</a>';
		       	  	}else {
						return "";
					}
			 	} },
              	{display: '招商确认', name: 'zsAuditStatus' ,render: function(rowdata, rowindex) {
	          	  	if(!rowdata.zsAuditStatus || rowdata.zsAuditStatus == '0'){
	          	  		return "未提交审核";
	          	  	}else if(rowdata.zsAuditStatus == '1'){
	          	  		<c:if test="${sessionScope.USER_SESSION.staffID==1}">
	          	  			return '<a href="javascript:;" onclick="editMchtProductBrand('+rowdata.id+',2);">待审</a>';
	          	  		</c:if>
	          	  		<c:if test="${sessionScope.USER_SESSION.staffID ne 1}">
      	  					return '待审';
      	  				</c:if>
	          	  	}else if(rowdata.zsAuditStatus == '2'){
        	  			return "审核通过";
	          	  	}else if(rowdata.zsAuditStatus == '4'){
		          	  	<c:if test="${sessionScope.USER_SESSION.staffID==1}">
		          	  		return '<a href="javascript:;" onclick="editMchtProductBrand('+rowdata.id+',2);">驳回</a>';
		          	  	</c:if>
		          	  	<c:if test="${sessionScope.USER_SESSION.staffID ne 1}">
  	  						return '驳回';
  	  					</c:if>
	          	  	}else if(rowdata.zsAuditStatus == '5'){
		          	  	<c:if test="${sessionScope.USER_SESSION.staffID==1}">
		          	  		return '<a href="javascript:;" onclick="editMchtProductBrand('+rowdata.id+',2);">不签约</a>';
		          	  	</c:if>
		          	  	<c:if test="${sessionScope.USER_SESSION.staffID ne 1}">
  							return '不签约';
  						</c:if>
	          	  	}else if(rowdata.zsAuditStatus == '6'){
		          	  	<c:if test="${sessionScope.USER_SESSION.staffID==1}">
		          	  		return '<a href="javascript:;" onclick="editMchtProductBrand('+rowdata.id+',2);">黑名单</a>';
		          	  	</c:if>
		          	  	<c:if test="${sessionScope.USER_SESSION.staffID ne 1}">
							return '黑名单';
						</c:if>
	          	  	}
			 	}},
              	{ display: '${mchtInfo.mchtType=="2"?"技术服务费率":"定价模式"}', name: 'id', render: function(rowdata, rowindex) {
	  					var html = [];
	  					if(${mchtInfo.mchtType=="2"}){
	  						html.push(rowdata.popCommissionRate);
	  					}else{
	  						html.push("<a href=\"javascript:viewerPriceModelDesc();\">"+rowdata.priceModelStatusDesc+"</a>");
	  					}
	  				    return html.join("");
  			 		}  
              	},
              { display: '资质类型', name: 'aptitudeTypeDesc'}, 
              { display: '授权有效期', name: 'platformAuthExpDate',render: function(rowdata, rowindex){
            	  var platformAuthExpDate=new Date(rowdata.platformAuthExpDate);
            	  return platformAuthExpDate.format("yyyy-MM-dd");
              }},
              { display: '法务审核状态', name: 'auditStatusDesc', render: function(rowdata, rowindex) {
					var html = [];
					if((rowdata.auditStatus=='2' || rowdata.auditStatus=='1') && ${sessionScope.USER_SESSION.staffID==1} ){
						if(rowdata.zsAuditStatus == 2){
	 				    	html.push("<a href=\"javascript:auditMchtProductBrand(" + rowdata.id + ");\">【"+rowdata.auditStatusDesc+"】</a>");
						}else{
							html.push(rowdata.auditStatusDesc);	
						}
//				    	html.push("<a href=\"javascript:auditMchtProductBrandStatus(" + rowdata.id + "," + rowdata.auditStatus + ");\">【"+rowdata.auditStatusDesc+"】</a>");
// 					}else if(rowdata.auditStatus=='1'){
// 						html.push("<a href=\"javascript:updateAuditStatus(" + rowdata.id + ");\">【"+rowdata.auditStatusDesc+"】</a>");
					}else{
						html.push(rowdata.auditStatusDesc);	
					}
				    return html.join("");
			 } },
              { display: '运营状态', name: 'statusDesc', render: function(rowdata, rowindex) {
					var html = [];
					html.push(rowdata.statusDesc);
					if(${sessionScope.USER_SESSION.staffID=='1'}){
				    	html.push("<a href=\"javascript:editMchtProductBrand(" + rowdata.id + ",3);\">【修改】</a>&nbsp;&nbsp;");
					}
				    return html.join("");
			 }},
			 { display: '招商标记', name: 'statusDesc', render: function(rowdata, rowindex) {
					var html = [];
					html.push(rowdata.isSpeciallyApprovedDesc);
					var zsMarked = ${zsMarked};
					if(rowdata.isSpeciallyApproved) {
						if(zsMarked == 1){
							if(rowdata.isSpeciallyApproved == '0'){
								
								html.push("<a href=\"javascript:editOrShowMchtProductBrandApproved(" + rowdata.id + ", 1);\">【修改】</a>&nbsp;&nbsp;");
							} 
							
							if(rowdata.isSpeciallyApproved == '1' && ${sessionScope.USER_SESSION.staffID != fwMchtPlatformContact.staffId}){
								
								html.push("<a href=\"javascript:editOrShowMchtProductBrandApproved(" + rowdata.id + ", 2);\">【查看】</a>&nbsp;&nbsp;");
							}
						}
					}
				    return html.join("");
			 }},
			 { display: '是否参与特卖', name: 'isActivity', render: function(rowdata, rowindex) {
					var html = [];
					var isActivityStr = rowdata.isActivity == 1 ? '是': '否';
					html.push(isActivityStr);
					if(${sessionScope.USER_SESSION.staffID==yyMchtPlatformContact.staffId} || ${sessionScope.USER_SESSION.staffID==assistantId}){
				    	html.push("<a href=\"javascript:toEditIsActivity(" + rowdata.id + ");\">【修改】</a>&nbsp;&nbsp;");
					}
				    return html.join("");
			 }},
			 { display: '操作', name: 'op', render: function(rowdata, rowindex) {
					var html = [];
				    if(${sessionScope.USER_SESSION.staffID=='1' || sessionScope.USER_SESSION.staffID==yyMchtPlatformContact.staffId}) {
						html.push("<a href=\"javascript:mchtBrandChgList(" + rowdata.id + ");\">【查看更新记录】</a>");
						html.push("</br><a href=\"javascript:singleProductActivityClose("+rowdata.mchtId+","+rowdata.productBrandId+");\">【关闭单品活动（" + rowdata.singleProductActivityCount + "）】</a>");
						html.push("</br><a href=\"javascript:activityClose("+rowdata.mchtId+","+rowdata.productBrandId+");\">【关闭品牌活动（" + rowdata.activityCount + "）】</a>");
						html.push("</br><a href=\"javascript:productClose("+rowdata.mchtId+","+rowdata.productBrandId+");\">【商品全部下架（" + rowdata.productCount + "）】</a>");
					}
				    return html.join("");
			  }}
              ],  pageSize:5,pageSizeOptions:[5,10,20,50,100],
              url:'${pageContext.request.contextPath}/mcht/mchtProductBrandData.shtml?mchtId=${mchtInfo.id}',
              width: '100%',
              onAfterShowData: function() { 
					$(".l-grid-row-cell-inner").css("height", "auto");
					var i = 0;
// 					$("tr",".l-grid2","#"+listModel.container.listGridName+"").each(function() {
// 						$($("tr",".l-grid1","#"+listModel.container.listGridName+"")[i]).css("height",$(this).height());
// 						i++;
// 					});
					}
          });
			var productTypeGrid = $("#maingrid").ligerGrid({
              columns: [
		                { display: '一级类目', name: 'productTypeName'},
		                { display: '二级类目', name: 'id', render: function(rowdata, rowindex) {
							return "不限";
					 }},
		                { display: '三级类目', name: 'id', render: function(rowdata, rowindex) {
							return "不限";
						 }},
		                { display: '状态',  name: 'statusDesc' }, 
		                { display: "操作", name: "OPER", align: "center", render: function(rowdata, rowindex) {
							var html = [];
							if(rowdata.status!='2' && ${sessionScope.USER_SESSION.staffID==fwMchtPlatformContact.staffId}){
						    html.push("<a href=\"javascript:editMchtProductType(" + rowdata.id + ");\">审核</a>&nbsp;&nbsp;");
							}
							
// 						    html.push("<a href=\"javascript:delMchtProductType(" + rowdata.id + ",\'"+rowdata.productTypeName+"\');\">删除</a>&nbsp;&nbsp;");
						    return html.join("");
					 }
	             }
		                
              ],  pageSize:5,pageSizeOptions:[5,10,20,50,100],
              url:'${pageContext.request.contextPath}/mcht/mchtProductTypeData.shtml?mchtId=${mchtInfo.id}',
              width: '100%',height:'250'
          });
			
			
			var logGrid = $("#maingridLog").ligerGrid({
	              columns: [
	              { display: '时间', name: 'platformAuthExpDate',render: function(rowdata, rowindex){
	            	  var createDate=new Date(rowdata.createDate);
	            	  return createDate.format("yyyy-MM-dd hh:mm:ss");
	              }},
	              { display: '操作人', render: function(rowdata, rowindex){
	            	  if(rowdata.creatorType == "1"){
	            		  return rowdata.creatorName;
	            	  }else if(rowdata.creatorType == "2"){
	            		  return "商家";
	            	  }else{
	            		  return "";
	            	  }
	              }},
	                  { display: '状态类型', name: 'logType' },
					  {display:'名称',name:'logName'},
	                  {display: '变更前', name:'beforeChange', render: function(rowdata, rowindex){
							  if(rowdata.beforeChange == null){
								  return "";
							  }else {
							  	return rowdata.beforeChange;
							  }
						  }
	                  },
					  {display: '变更后', name:'afterChange',render: function(rowdata, rowindex){
							  if(rowdata.afterChange == null){
								  return "";
							  }else {
								  return rowdata.afterChange;
							  }
						  }
					  },
	              { display: '备注/驳回原因', name: 'remarks'}
	              ],  pageSize:5,pageSizeOptions:[5,10,20,50,100],
	              url:'${pageContext.request.contextPath}/mcht/mchtInfoChangeLogData.shtml?mchtId=${mchtInfo.id}',
	              width: '100%',
	              fixedCellHeight: false
	          });
			
			

	  });

	  function viewerPriceModelDesc(){
          var manager = $("#maingridBrand").ligerGetGridManager();
          var row = manager.getSelectedRow();
          if (!row) { alert('请选择行'); return; }
          $.ligerDialog.question(row.priceModelDesc.replace(/\n/g,"<br>"));
	  }

	  function auditMchtBankAccount(mchtBankAccountId) {
	    	 
  		$.ligerDialog.open({
  			height: 600,
  			width: 800,
	    		title: "商家银行帐号审核",
	    		name: "INSERT_WINDOW",
	    		url: "${pageContext.request.contextPath}/mcht/mchtBankAccountAudit.shtml?id=" + mchtBankAccountId,
	    		showMax: true,
	    		showToggle: false,
	    		showMin: false,
	    		isResize: true,
	    		slide: false,
	    		data: null,
	    		id:"audit_dialog"
  		});
  	}
	  
	  
	// 合同审核
	function contractAudit(mchtContractId) {
		$.ligerDialog.open({
			height: 300,
			width: 500,
			title: "处理审核",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtContract/audit.shtml?id=" + mchtContractId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	// 查看合同详情
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
	
	// 合同归档处理
	function contractArchive(mchtContractId) {
		$.ligerDialog.open({
			height: 480,
			width: 640,
			title: "处理归档",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtContract/archive.shtml?id=" + mchtContractId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	// 查看合同扫描件
	function viewContractPic2(mchtContractId) {
		$.ligerDialog.open({
			height: 700,
			width: 800,
			title: "查看合同",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtContract/viewContractPicPage.shtml?id=" + mchtContractId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//修改店铺名
	function toEditShopName(mchtId) {
		$.ligerDialog.open({
			height: 600,
			width: 600,
			title: "修改店铺名",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/toEditShopName.shtml?mchtId=" + mchtId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//修改主营类目
	function toEditMchtProductType(mchtProductTypeId) {
		$.ligerDialog.open({
			height: 300,
			width: 500,
			title: "修改主营类目",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/editMchtProductType.shtml?mchtProductTypeId=" + mchtProductTypeId+"&canEdit=1",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	function updateAuditStatus(mchtProductBrandId) {
		$.ajax({
			url : "${pageContext.request.contextPath}/mcht/updateAuditStatus.shtml?",
			type : 'POST',
			dataType : 'json',
			data : {mchtProductBrandId : mchtProductBrandId},
			cache : false,
			async : false,
			timeout : 30000,
			success : function(data) {
				if(data.code == '200') {
					$("#maingridBrand").ligerGetGridManager().loadData();
				}else {
					$.ligerDialog.error('系统错误！');
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
	
	function auditMchtProductBrandStatus(mchtProductBrandId, auditStatus) {
		if(auditStatus == '1') {
			updateAuditStatus(mchtProductBrandId);
		}
		auditMchtProductBrand(mchtProductBrandId);
	}
	
	/* function toEditMchtName(id) {
		$.ligerDialog.open({
			height: $(window).height()*0.3,
			width: $(window).width()*0.3,
			title: "修改商家简称",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/toEditMchtName.shtml?mchtId=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	} */
		
	function toZsConfirm(id) {
		$.ligerDialog.open({
			height: $(window).height()*0.8,
			width: $(window).width()*0.5,
			title: "招商确认",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/toZsConfirm.shtml?mchtId=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//查看入驻资料
	function viewMchtSettled(mchtId) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: "查看入驻信息",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/viewMchtSettled.shtml?mchtId="+mchtId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//关闭单品活动
	function singleProductActivityClose(mchtId, productBrandId) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: "商家单品活动",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/singleProductActivityCloseManager.shtml?mchtId="+mchtId+"&productBrandId="+productBrandId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//关闭品牌活动
	function activityClose(mchtId, productBrandId) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: "商家品牌特卖活动",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/activityCloseManager.shtml?mchtId="+mchtId+"&productBrandId="+productBrandId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//商品全部下架
	function productClose(mchtId, productBrandId) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: "查看商品",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/productCloseManager.shtml?mchtId="+mchtId+"&productBrandId="+productBrandId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//查看店铺
	function showMchtShop(mchtId, mchtType) {
	  $.ligerDialog.open({
		  	height: $(window).height() - 50,
			width: $(window).width() - 100,
			title: "商品列表",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/showMchtShopManager.shtml?mchtId="+mchtId+"&mchtType="+mchtType,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
	   });
	}
	
	//修改是否参与特卖
	function toEditIsActivity(mchtProductBrandId) {
		$.ligerDialog.open({
			height: 200,
			width: 400,
			title: "是否参与特卖",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/toEditIsActivity.shtml?mchtProductBrandId=" + mchtProductBrandId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	function toEditCompanyType(id){
		$.ligerDialog.open({
			height : 250,
			width : 400,
			title : "修改公司类型",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/mcht/toEditCompanyType.shtml?id="+id,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	function toEditCompanyAddress(id){
		$.ligerDialog.open({
			height : 250,
			width : 400,
			title : "修改住所地址",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/mcht/toEditCompanyAddress.shtml?id="+id,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	function toEditFoundedDate(id){
		$.ligerDialog.open({
			height : 350,
			width : 400,
			title : "修改发照时间",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/mcht/toEditFoundedDate.shtml?id="+id,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	function toEditYearlyInspectionDate(id){
		$.ligerDialog.open({
			height : 350,
			width : 400,
			title : "修改营业执照有效期",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/mcht/toEditYearlyInspectionDate.shtml?id="+id,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	function toEditCorporationIdcardDate(id){
		$.ligerDialog.open({
			height : 350,
			width : 400,
			title : "修改法人身份证有效期",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/mcht/toEditCorporationIdcardDate.shtml?id="+id,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	function toEditScopeOfBusiness(id){
		$.ligerDialog.open({
			height : 500,
			width : 800,
			title : "修改经营范围",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/mcht/toEditScopeOfBusiness.shtml?id="+id,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	function toEditZsTotalAuditStatus(id){
		$.ligerDialog.open({
			height : 350,
			width : 500,
			title : "修改招商总审状态",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/mcht/toEditZsTotalAuditStatus.shtml?id="+id,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	function updateMchtLicenseStatus(mchtId) {
		$.ligerDialog.open({
			height : 500,
			width : 600,
			title : "修改行业执照",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/mcht/mchtLicenseStatusManager.shtml?mchtId="+mchtId,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	 function modify(id) {
			$.ligerDialog.open({
			height: 340,
			width: 300,
			title: "修改主营经营品牌类型",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/modifyProductType2.shtml?id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	 
	 //修改主要经营品牌类型成功重新加载数据
	 function freshen(id){
			$.ajax({
				url : "${pageContext.request.contextPath}/mcht/freshenFwAudit.shtml?id="+id,
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				timeout : 30000,
				success : function(data) {
					var mchtInfo = data.mchtInfo;
					$('#brandType').val(mchtInfo.brandType);
					$('#brandTypeDesc').val(mchtInfo.brandTypeDesc);
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});		 
	 }
</script>

<html>
<body>

<!-- 商家合作信息 -->
<input type="hidden" id="mchtId" value="${mchtId}" onclick="freshen(${mchtId});">
		<div><span class="table-title" >商家合作信息</span>
		</div>
		<br>
		<table class="gridtable">
			<input type="hidden" id="zsManagement" value="${zsManagement}">
			<tr>
				<td  colspan="1" class="title">商家类型、序号 </td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<c:if test="${mchtInfo.isManageSelf == 1 && mchtInfo.mchtType == 1 }">
				 自营
				</c:if>
				${mchtInfo.mchtTypeDesc }、${mchtInfo.mchtCode }
				</td>
				<td  colspan="1" class="title">招商入驻资料</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<a href="javascript:;" onclick="viewMchtSettled(${mchtInfo.id});">【查看入驻资料】</a>|招商总审状态 :
					<c:if test="${mchtInfo.zsTotalAuditStatus == 4}">
						未提审
					</c:if>
					<c:if test="${mchtInfo.zsTotalAuditStatus == 0}">
						待审
					</c:if>
					<c:if test="${mchtInfo.zsTotalAuditStatus == 2}">
						通过
					</c:if>
					<c:if test="${mchtInfo.zsTotalAuditStatus == 3}">
						驳回
					</c:if>
					<c:if test="${mchtInfo.zsTotalAuditStatus == 5}">
						不签约
					</c:if>
					<c:if test="${mchtInfo.zsTotalAuditStatus == 6}">
						黑名单
					</c:if>
					<c:if test="${sessionScope.USER_SESSION.staffID==1}">
						<a href="javascript:;" onclick="toEditZsTotalAuditStatus(${mchtInfo.id});">【修改】</a>
					</c:if>
					
				</td>
				
				<%-- <td  colspan="1" class="title">商家简称</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.shortName }
					<c:if test="${sessionScope.USER_SESSION.staffID==zsMchtPlatformContact.staffId}">
						<a href="javascript:;" onclick="toEditMchtName(${mchtInfo.id});">【修改】</a>
					</c:if>
				</td> --%>
				
				<td  colspan="1" class="title">公司名称</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.companyName }
					<c:if test="${mchtInfo.shopStatus == '1' }">
						<a href="http://m.xgbuy.cc/webApp/xgbuy/views/index.html?redirect_url=mall/seller/index.html?id=${mchtInfo.id }" target="_blank" >【查看店铺】</a>
					</c:if>
					<c:if test="${mchtInfo.shopStatus == '0' }">
						<a href="javascript:showMchtShop(${mchtInfo.id }, ${mchtInfo.mchtType });">【查看店铺】</a>
					</c:if>
				</td>	
				
			</tr>
			<tr>
				<td  colspan="1" class="title">资质总审核状态</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				
				<c:choose>
				   <c:when test="${sessionScope.USER_SESSION.staffID==1}"> 
					<a class="table-title-link" href="javascript:totalAudit(${mchtInfo.id })" >【${mchtInfo.totalAuditStatusDesc }】</a>
				   </c:when>
				   <c:otherwise>
				  	${mchtInfo.totalAuditStatusDesc }
				   </c:otherwise>
				</c:choose>	
				</td>
				<td  colspan="1" class="title">线上合同状态</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtContractAuditStatusDesc}
				<c:if test="${mchtContract.auditStatus=='2'  && sessionScope.USER_SESSION.staffID==fwMchtPlatformContact.staffId}">
				<a class="table-title-link" href="javascript:contractAudit(${mchtContract.id})" >【审核】</a>
				</c:if>
				<c:if test="${not empty mchtContract && not empty mchtContract.id}">
				<a class="table-title-link" href="javascript:viewContractPic2(${mchtContract.id })" >【查看详情】</a>
				</c:if>
				</td>
				<td  colspan="1" class="title">合同归档状态</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${mchtContract.contractType=='2'}">
						【续签】
					</c:if>
						${mchtContractArchiveStatusDesc}
					<c:if test="${((mchtInfo.totalAuditStatus == '4' || mchtInfo.totalAuditStatus == '3') && not empty mchtContract.id && not empty mchtContract.archiveStatus && mchtContract.archiveStatus != '1') && sessionScope.USER_SESSION.staffID == fwMchtPlatformContact.staffId}">
						<a class="table-title-link" href="javascript:contractArchive(${mchtContract.id})" >【操作】</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">最新合同编号</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtContract.contractCode }
				</td>
				<td  colspan="1" class="title">最新合同开始</td>
			<td  colspan="3" align="left" class="l-table-edit-td">
				<fmt:formatDate value="${mchtContract.beginDate}" pattern="yyyy-MM-dd"/>
				
				</td>
					<td  colspan="1" class="title">最新合同到期</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				
				<fmt:formatDate value="${mchtContract.endDate}" pattern="yyyy-MM-dd"/>
				</td>
				
			</tr>
			
			
			<tr>
				<td  colspan="1" class="title">缴费状态</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				</td>
				<td  colspan="1" class="title">开店日期</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<fmt:formatDate value="${mchtInfo.cooperateBeginDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td  colspan="1" class="title">合作状态</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.statusDesc}
					<c:if test="${sessionScope.USER_SESSION.staffID=='1'||(((mchtInfo.totalAuditStatus=='2'&&(mchtContract.archiveStatus=='1'||mchtContract.auditStatus=='3')&&mchtInfo.status!='0')||(mchtContract.contractType=='2'&&(mchtContract.archiveStatus=='1'||mchtContract.archiveStatus=='3'))||isContractExpired)&&sessionScope.USER_SESSION.staffID==yyMchtPlatformContact.staffId)}">
						<c:if test="${sessionScope.USER_SESSION.staffID=='1' || mchtInfo.financeConfirmStatus == 1}">
							<a class="table-title-link" href="javascript:changeStatus(${mchtInfo.id},'${mchtInfo.status}')" >【修改】</a>
						</c:if>
					</c:if>
				</td>
				
			</tr>
			
			
			
			<tr>

			<tr>
				<td  colspan="1" class="title">用户名</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtUser.userCode}
				<c:if test="${sessionScope.USER_SESSION.staffID==yyMchtPlatformContact.staffId}">
				<a class="table-title-link" href="javascript:changeMchtUser(${mchtUser.id})" >【修改】</a>
				</c:if>
			</td>	
					<td  colspan="1" class="title">用户手机号</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtUser.mobile }
					</td>	
					<td  colspan="1" class="title">用户邮箱</td>
					<td  colspan="7" align="left" class="l-table-edit-td">
					${mchtUser.email }
					</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">法务对接人</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${fwMchtPlatformContact.contactName}
				</td>	
					<td  colspan="1" class="title">招商对接人</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
					${zsMchtPlatformContact.contactName}
					<c:if test="${empty mchtInfo.zsAuditStatus || mchtInfo.zsAuditStatus == '0'}">
				   <!-- <a href="javascript:;" onclick="toZsConfirm(${mchtInfo.id});">【未确认】</a> -->
						【未确认】
					</c:if>
					<c:if test="${mchtInfo.zsAuditStatus == '1'}">
				   <!-- <a href="javascript:;" onclick="toZsConfirm(${mchtInfo.id});">【已确认】</a> -->
						【已确认】
					</c:if>
					</td>	
					<td  colspan="1" class="title">商家运营</td>
					<td  colspan="7" align="left" class="l-table-edit-td">
						${yyMchtPlatformContact.contactName}
					</td>
			</tr>

		</table>
		
		
		
		<!-- 商家资质公司信息  -->
		<c:if test="${mchtInfo.settledType eq 1}">
		<br>
		<br>
		<div><span class="table-title" >公司信息</span></div>
		<br>
		<table class="gridtable">
			<tr>
				<td  colspan="1" class="title">入驻类型</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${mchtInfo.settledType eq 1}">企业公司</c:if>
					<c:if test="${mchtInfo.settledType eq 2}">个体商户</c:if>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">公司名称 </td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.companyName }
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">公司类型</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.companyType}
					<c:if test="${not empty mchtFwContactId || sessionScope.USER_SESSION.staffID==1}">
						<a href="javascript:;" onclick="toEditCompanyType(${mchtInfo.id});">【修改】</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">统一信用代码</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.uscc }
				</td>
				
			</tr>
			<tr>
				<td  colspan="1" class="title">注册资本</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.regCapital}万元（${mchtInfo.regFeeTypeDesc}）
				</td>
				
			</tr>
		
		    <tr>
			   <td  colspan="1" class="title">公司住所</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.companyAddress}
					<c:if test="${not empty mchtFwContactId || sessionScope.USER_SESSION.staffID==1}">
						<a href="javascript:;" onclick="toEditCompanyAddress(${mchtInfo.id});">【修改】</a>
					</c:if>
				<c:if test="${not empty mchtFwContactId || sessionScope.USER_SESSION.staffID==1}">
						<a href="javascript:;" onclick="toEditcompanyAddress(${mchtInfo.id});">【修改】</a>
				</c:if>
				</td>
			</tr>
		
			<tr>
		
			<td  colspan="1" class="title">成立日期</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<fmt:formatDate value="${mchtInfo.foundedDate }" pattern="yyyy-MM-dd"/>
				
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">营业执照有效期</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<fmt:formatDate value="${mchtInfo.yearlyInspectionDate}" pattern="yyyy-MM-dd"/>
					<c:if test='${yearlyInspectionInvalid==1}'>
						<span style="color: red;font-size: 15px;">【到期】</span>
					</c:if>
					<c:if test="${not empty mchtFwContactId || sessionScope.USER_SESSION.staffID==1}">
						<c:if test="${empty mchtInfo.yearlyInspectionDate}"><a href="javascript:;" onclick="toEditYearlyInspectionDate(${mchtInfo.id});">【添加有效期】</a></c:if>
						<c:if test="${not empty mchtInfo.yearlyInspectionDate}"><a href="javascript:;" onclick="toEditYearlyInspectionDate(${mchtInfo.id});">【修改有效期】</a></c:if>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">经营范围</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<textarea rows="13" cols="83" maxlength="1024">${mchtInfo.scopeOfBusiness}</textarea>
					<c:if test="${not empty mchtFwContactId || sessionScope.USER_SESSION.staffID==1}">
						<c:if test="${empty mchtInfo.scopeOfBusiness}"><a href="javascript:;" onclick="toEditScopeOfBusiness(${mchtInfo.id});">【添加经营范围】</a></c:if>
						<c:if test="${not empty mchtInfo.scopeOfBusiness}"><a href="javascript:;" onclick="toEditScopeOfBusiness(${mchtInfo.id});">【修改经营范围】</a></c:if>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">法人姓名</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.corporationName }
				</td>
			</tr>
			<tr>
			<td  colspan="1" class="title">法人身份证号</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.corporationIdcardNo}
			</td>	
							
				
			</tr>
			<tr>
				
					<td  colspan="1" class="title">身份证正背面</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
					 <ul class="docs-pictures clearfix td-pictures" id="idcarPicViewer">
				 
				 <li><img  src="${pageContext.request.contextPath}/file_servelt${mchtInfo.corporationIdcardImg1}" alt=""></li>
				 <li><img  src="${pageContext.request.contextPath}/file_servelt${mchtInfo.corporationIdcardImg2}" alt=""></li>
          			</ul>
					</td>	
				
			</tr>
			
			<tr>
				<td  colspan="1" class="title">法人身份证有效期</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<fmt:formatDate value="${mchtInfo.corporationIdcardDate}" pattern="yyyy-MM-dd"/>
					<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set> 
					<c:if test='${not empty mchtInfo.corporationIdcardDate && mchtInfo.corporationIdcardDate.time < nowDate}'>
						<span style="color: red;font-size: 15px;">【到期】</span>
					</c:if>
					<c:if test="${not empty mchtFwContactId || sessionScope.USER_SESSION.staffID==1}">
						<c:if test="${empty mchtInfo.corporationIdcardDate}"><a href="javascript:;" onclick="toEditCorporationIdcardDate(${mchtInfo.id});">【添加有效期】</a></c:if>
						<c:if test="${not empty mchtInfo.corporationIdcardDate}"><a href="javascript:;" onclick="toEditCorporationIdcardDate(${mchtInfo.id});">【修改有效期】</a></c:if>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">法人手机</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.corporationMobile }
					</td>
			</tr>
	
			
			<tr>
				<td  colspan="1" class="title">公司总机</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.companyTel }
					</td>
			</tr>

			<tr>
				<td  colspan="1" class="title">公司通讯地址</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.contactProvinceName}${mchtInfo.contactCityName}${mchtInfo.contactCountyName}${mchtInfo.contactAddress}
				</td>
			</tr>
			
			
			
			<tr>
					<td  colspan="1" class="title">营业执照副本</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
					 <ul class="docs-pictures clearfix td-pictures" id="blPicViewer">
					 <li>
					<img  src="${pageContext.request.contextPath}/file_servelt${mchtInfo.blPic}" alt="">
					</li>
					<c:if test="${not empty mchtBlPics}">
					<c:forEach var="mchtBlPic" items="${mchtBlPics}">
					<li>
					<img  src="${pageContext.request.contextPath}/file_servelt${mchtBlPic.pic}" alt="">
					</li>
					</c:forEach>
					</c:if>
					</ul>
					</td>	
				
			</tr>
			<tr>
				<td colspan="1" class="title">主营经营品牌类型</td>
				<td colspan="2" align="left" class="l-table-edit-td">
                <select style="width:210px;" name="brandType" id="brandType" disabled>
                <option value=""></option>
                <option value="0">品牌官方</option>
                <option value="1">品牌总代</option>
                <option value="2">品牌代理商</option>
                </select>
                <c:if test="${role431}">
                <a href="javascript:modify('${mchtInfo.id}')">【修改】</a>
                </c:if>
                <br><br>
                <textarea disabled rows="5" cols="70" id="brandTypeDesc" name="brandTypeDesc">${mchtInfo.brandTypeDesc}</textarea>
				</td>
			</tr>
			<tr>
					<td  colspan="1" class="title">组织机构代码证</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
					<img onclick="viewerPic(this.src)" src="${pageContext.request.contextPath}/file_servelt${mchtInfo.occPic}" alt="">
					</td>	
				
			</tr>
		<!-- 	
			<tr>
					<td  colspan="1" class="title">税务登记证</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
					<img onclick="viewerPic(this.src)" src="${pageContext.request.contextPath}/file_servelt${mchtInfo.trcPic}" alt="">
					</td>	
				
			</tr>
		-->	
			<tr>
					<td  colspan="1" class="title">一般纳税人资格证</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
					<img onclick="viewerPic(this.src)" src="${pageContext.request.contextPath}/file_servelt${mchtInfo.atqPic}" alt="">
					</td>	
				
			</tr>
			<tr>
					<td  colspan="1" class="title">银行开户许可证</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
					<img onclick="viewerPic(this.src)" src="${pageContext.request.contextPath}/file_servelt${mchtInfo.boaalPic}" alt="">
					</td>	
				
			</tr>
			
			
			
			<tr>
				<td  colspan="1" class="title">审核状态</td>
				<td  colspan="3" align="left" class="l-table-edit-td"> 
				<c:choose>
					<c:when test="${sessionScope.USER_SESSION.staffID==1} && ${mchtInfo.isCompanyInfPerfect=='2'||mchtInfo.isCompanyInfPerfect=='3'}">
						<a class="table-title-link" href="javascript:changeIsCompanyInfPerfect(${mchtInfo.id })" >【${mchtInfo.isCompanyInfPerfectDesc }】</a>
					</c:when>
					<c:otherwise>		
							${mchtInfo.isCompanyInfPerfectDesc}					
					</c:otherwise>
				</c:choose>
					<a href="javascript:;" onclick="mchtInfoChgList(${mchtInfo.id})">【查看更新记录】</a>
				</td>
			</tr>
			
			<c:if test="${mchtInfo.isCompanyInfPerfect =='4'}">
			<tr>
				<td  colspan="1" class="title">驳回原因</td>
				<td  colspan="3" align="left" class="l-table-edit-td"> 
				<span style="color: red;">${mchtInfo.companyInfAuditRemarks}</span>
				</td>
			</tr>
			</c:if>
			<c:if test="${not empty zsContactId || not empty fwContactId || not empty generalOffice}">
			<tr>
				<td  colspan="1" class="title">内部备注</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.companyInfAuditInnerRemarks}
				</td> 
			</tr>
			</c:if>
			<tr>
				<td  colspan="1" class="title">银行帐号状态</td>
				<td  colspan="3" align="left" class="l-table-edit-td"> 
				
			<c:choose>
			   <c:when test="${(mchtBankAccount.status=='0'||mchtBankAccount.status=='1')  && sessionScope.USER_SESSION.staffID==1}"> 
				<a class="table-title-link" href="javascript:auditMchtBankAccount('${mchtBankAccount.id}')" >【${mchtBankAccount.statusDesc }】</a>   
			   </c:when>
			   <c:otherwise>
			  	${mchtBankAccount.statusDesc }
			   </c:otherwise>
			</c:choose>	
				</td>
			</tr>
			
		</table>
		</c:if>
		
		<c:if test="${mchtInfo.settledType eq 2}">
		<br>
		<br>
		<div><span class="table-title" >公司信息</span></div>
		<br>
		<table class="gridtable">
			<tr>
				<td  colspan="1" class="title">入驻类型</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${mchtInfo.settledType eq 1}">企业公司</c:if>
					<c:if test="${mchtInfo.settledType eq 2}">个体商户</c:if>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">名称 </td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.companyName }
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">组成形式</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.companyType}
					<c:if test="${not empty mchtFwContactId || sessionScope.USER_SESSION.staffID==1}">
						<a href="javascript:;" onclick="toEditCompanyType(${mchtInfo.id});">【修改】</a>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">营业执照注册号</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.uscc}
				</td>
				
			</tr>
			
			<tr>
			<td  colspan="1" class="title">经营场所</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.companyAddress}
<<<<<<< .working
					<c:if test="${not empty mchtFwContactId || sessionScope.USER_SESSION.staffID==1}">
						<a href="javascript:;" onclick="toEditcompanyAddress(${mchtInfo.id});">【修改】</a>
					</c:if>
=======
					<c:if test="${not empty mchtFwContactId || sessionScope.USER_SESSION.staffID==1}">
						<a href="javascript:;" onclick="toEditCompanyAddress(${mchtInfo.id});">【修改】</a>
					</c:if>
>>>>>>> .merge-right.r22326
				</td>
			</tr>
		
			<tr>
		
			<td  colspan="1" class="title">发照时间</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<fmt:formatDate value="${mchtInfo.foundedDate }" pattern="yyyy-MM-dd"/>
					<c:if test="${not empty mchtFwContactId || sessionScope.USER_SESSION.staffID==1}">
						<c:if test="${empty mchtInfo.foundedDate}"><a href="javascript:;" onclick="toEditFoundedDate(${mchtInfo.id});">【添加】</a></c:if>
						<c:if test="${not empty mchtInfo.foundedDate}"><a href="javascript:;" onclick="toEditFoundedDate(${mchtInfo.id});">【修改】</a></c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">营业执照有效期</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<fmt:formatDate value="${mchtInfo.yearlyInspectionDate}" pattern="yyyy-MM-dd"/>
					<c:if test='${yearlyInspectionInvalid==1}'>
						<span style="color: red;font-size: 15px;">【到期】</span>
					</c:if>
					<c:if test="${not empty mchtFwContactId || sessionScope.USER_SESSION.staffID==1}">
						<c:if test="${empty mchtInfo.yearlyInspectionDate}"><a href="javascript:;" onclick="toEditYearlyInspectionDate(${mchtInfo.id});">【添加有效期】</a></c:if>
						<c:if test="${not empty mchtInfo.yearlyInspectionDate}"><a href="javascript:;" onclick="toEditYearlyInspectionDate(${mchtInfo.id});">【修改有效期】</a></c:if>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">经营范围</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<textarea rows="13" cols="83" maxlength="1024">${mchtInfo.scopeOfBusiness}</textarea>
					<c:if test="${not empty mchtFwContactId || sessionScope.USER_SESSION.staffID==1}">
						<c:if test="${empty mchtInfo.scopeOfBusiness}"><a href="javascript:;" onclick="toEditScopeOfBusiness(${mchtInfo.id});">【添加经营范围】</a></c:if>
						<c:if test="${not empty mchtInfo.scopeOfBusiness}"><a href="javascript:;" onclick="toEditScopeOfBusiness(${mchtInfo.id});">【修改经营范围】</a></c:if>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">经营者姓名</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.corporationName }
				</td>
			</tr>
			<tr>
			<td  colspan="1" class="title">经营者身份证号</td>
			<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.corporationIdcardNo}
			</td>	
							
			</tr>
			<tr>
				<td  colspan="1" class="title">经营者身份证正背面</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<ul class="docs-pictures clearfix td-pictures" id="idcarPicViewer">
					 <li><img  src="${pageContext.request.contextPath}/file_servelt${mchtInfo.corporationIdcardImg1}" alt=""></li>
					 <li><img  src="${pageContext.request.contextPath}/file_servelt${mchtInfo.corporationIdcardImg2}" alt=""></li>
	          		</ul>
				</td>	
			</tr>
			
			<tr>
				<td  colspan="1" class="title">经营者身份证有效期</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<fmt:formatDate value="${mchtInfo.corporationIdcardDate}" pattern="yyyy-MM-dd"/>
					<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set> 
					<c:if test='${not empty mchtInfo.corporationIdcardDate && mchtInfo.corporationIdcardDate.time < nowDate}'>
						<span style="color: red;font-size: 15px;">【到期】</span>
					</c:if>
					<c:if test="${not empty mchtFwContactId || sessionScope.USER_SESSION.staffID==1}">
						<c:if test="${empty mchtInfo.corporationIdcardDate}"><a href="javascript:;" onclick="toEditCorporationIdcardDate(${mchtInfo.id});">【添加有效期】</a></c:if>
						<c:if test="${not empty mchtInfo.corporationIdcardDate}"><a href="javascript:;" onclick="toEditCorporationIdcardDate(${mchtInfo.id});">【修改有效期】</a></c:if>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">经营者手机</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.corporationMobile }
				</td>
			</tr>
	
			<tr>
				<td  colspan="1" class="title">通讯地址</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.contactProvinceName}${mchtInfo.contactCityName}${mchtInfo.contactCountyName}${mchtInfo.contactAddress}
				</td>
			</tr>
			
			
			
			<tr>
					<td  colspan="1" class="title">营业执照副本</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
					 <ul class="docs-pictures clearfix td-pictures" id="blPicViewer">
					 <li>
					<img  src="${pageContext.request.contextPath}/file_servelt${mchtInfo.blPic}" alt="">
					</li>
					<c:if test="${not empty mchtBlPics}">
					<c:forEach var="mchtBlPic" items="${mchtBlPics}">
					<li>
					<img  src="${pageContext.request.contextPath}/file_servelt${mchtBlPic.pic}" alt="">
					</li>
					</c:forEach>
					</c:if>
					</ul>
					</td>	
				
			</tr>
			<tr>
					<td  colspan="1" class="title">经营者银行卡信息</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
					<img onclick="viewerPic(this.src)" src="${pageContext.request.contextPath}/file_servelt${mchtInfo.boaalPic}" alt="">
					</td>	
				
			</tr>
			
			<tr>
				<td colspan="1" class="title">主营经营品牌类型</td>
				<td colspan="2" align="left" class="l-table-edit-td">
                <select style="width:210px;" name="brandType" id="brandType" disabled>
                <option value=""></option>
                <option value="0">品牌官方</option>
                <option value="1">品牌总代</option>
                <option value="2">品牌代理商</option>
                </select>
                <c:if test="${role431}">
                <a href="javascript:modify('${mchtInfo.id}')">【修改】</a>
                </c:if>
                <br><br>
                <textarea disabled rows="5" cols="70" id="brandTypeDesc" name="brandTypeDesc">${mchtInfo.brandTypeDesc}</textarea>
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">审核状态</td>
				<td  colspan="3" align="left" class="l-table-edit-td"> 
				<c:choose>
					<c:when test="${sessionScope.USER_SESSION.staffID==1} && ${mchtInfo.isCompanyInfPerfect=='2'||mchtInfo.isCompanyInfPerfect=='3'}">
						<a class="table-title-link" href="javascript:changeIsCompanyInfPerfect(${mchtInfo.id })" >【${mchtInfo.isCompanyInfPerfectDesc }】</a>
					</c:when>
					<c:otherwise>			
							${mchtInfo.isCompanyInfPerfectDesc}					
					</c:otherwise>
				</c:choose>
				<a href="javascript:;" onclick="mchtInfoChgList(${mchtInfo.id})">【查看更新记录】</a>
				</td>
			</tr>
			
			<c:if test="${mchtInfo.isCompanyInfPerfect =='4'}">
			<tr>
				<td  colspan="1" class="title">驳回原因</td>
				<td  colspan="3" align="left" class="l-table-edit-td"> 
				<span style="color: red;">${mchtInfo.companyInfAuditRemarks}</span>
				</td>
			</tr>
			</c:if>
			<c:if test="${not empty zsContactId || not empty fwContactId || not empty generalOffice}">
			<tr>
				<td  colspan="1" class="title">内部备注</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.companyInfAuditInnerRemarks}
				</td> 
			</tr>
			</c:if>
			<tr>
				<td  colspan="1" class="title">银行帐号状态</td>
				<td  colspan="3" align="left" class="l-table-edit-td"> 
				
			<c:choose>
			   <c:when test="${(mchtBankAccount.status=='0'||mchtBankAccount.status=='1')  && sessionScope.USER_SESSION.staffID==1}"> 
				<a class="table-title-link" href="javascript:auditMchtBankAccount('${mchtBankAccount.id}')" >【${mchtBankAccount.statusDesc }】</a>   
			   </c:when>
			   <c:otherwise>
			  	${mchtBankAccount.statusDesc }
			   </c:otherwise>
			</c:choose>	
				</td>
			</tr>
			
		</table>
		</c:if>
		
		<!-- 店铺信息 -->
		<div><span class="table-title" >店铺名信息</span>
		</div>
		<br>
		<table class="gridtable">
			<tr>
				<td  colspan="1" class="title">合作模式 </td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.mchtTypeDesc }
				</td>
				<td  colspan="1" class="title">店铺类型</td>
				<td  colspan="3" align="left" class="l-table-edit-td">${mchtInfo.shopTypeDesc }</td>
				<td  colspan="1" class="title">店铺名</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.shopName }
					<c:if test="${sessionScope.USER_SESSION.staffID==1}">
						<a href="javascript:;" onclick="toEditShopName(${mchtInfo.id})">【修改】</a>
					</c:if>
				</td>	
				
			</tr>
			<tr>
				<td  colspan="1" class="title">公司字号</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.businessFirms}
				</td>
				<td  colspan="1" class="title">品牌</td> 
			<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.brand}
				
				</td>
				<td  colspan="1" class="title">品类</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.productType}
				</td>
				
			</tr>
			
			
			<tr>
				<td  colspan="1" class="title">状态</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:choose>
					   <c:when test="${(mchtInfo.shopNameAuditStatus=='2'||mchtInfo.shopNameAuditStatus=='1' || mchtInfo.shopNameAuditStatus=='4')  && sessionScope.USER_SESSION.staffID==1}"> 
						<a class="table-title-link" href="javascript:changeShopNameAuditStatus(${mchtInfo.id})" >【${mchtInfo.shopNameAuditStatusDesc}】</a>   
					   </c:when>
					   <c:otherwise>
						${mchtInfo.shopNameAuditStatusDesc}
					   </c:otherwise>
					</c:choose>	
				</td>
				<td  colspan="1" class="title">驳回原因</td>
				<td  colspan="7" align="left" class="l-table-edit-td"> 
					${mchtInfo.shopNameAuditRemarks}
				</td>
			</tr>
			<c:if test="${not empty zsContactId || not empty fwContactId || not empty generalOffice}">
			<tr>
				<td  colspan="1" class="title">内部备注</td>
				<td  colspan="11" align="left" class="l-table-edit-td">
					${mchtInfo.shopNameAuditInnerRemarks}
				</td> 
			</tr>
			</c:if>
		</table>
		
		<br>
		<br>
		<div><span class="table-title" >品类信息及经营许可证</span></div>
		<br>
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">主营类目</td>
				<td colspan="1" >
					${productTypeName}
					<c:if test="${mchtProductType.status == '0'}">[申请中]</c:if>
					<c:if test="${mchtProductType.status == '1'}">[正常]</c:if>
					<c:if test="${mchtProductType.status == '2'}">[暂停]</c:if>
					<c:if test="${mchtProductType.status == '3'}">[关闭]</c:if>
					<c:if test="${sessionScope.USER_SESSION.staffID=='1'}">
						<a href="javascript:;" onclick="toEditMchtProductType(${mchtProductType.id});">【修改】</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">商品主要经营类目</td>
				<td colspan="1" >
					${productType2Name}
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">行业经营许可证</td>
				<td colspan="1" >
					<img src="${pageContext.request.contextPath}/file_servelt${mchtInfo.businessLicensePic}" style="margin:10px; width:120px; height:80px;" onclick="viewerPic(this.src)">
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">状态</td>
				<td colspan="1" >
					<c:if test="${mchtInfo.licenseIsMust == '0' }">[非必需]</c:if>
					<c:if test="${mchtInfo.licenseIsMust == '1' }">[必需]</c:if>
					${mchtInfo.licenseStatusDesc }
					<c:if test="${sessionScope.USER_SESSION.staffID == '1' }">
						<a href="javascript:void(0);" onclick="updateMchtLicenseStatus(${mchtInfo.id });" >【修改】</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">驳回原因</td>
				<td colspan="1" >
					${mchtInfo.licenseRejectReason }
				</td>
			</tr>
		</table>
		
	
	<br>
		<br>
	<div><span class="table-title" >品牌信息</span></div>
		<br>
	<form id="dataFormBrand" runat="server">
		<div id="maingridBrand" style="margin: 0; padding: 0"></div>
	</form>
	
	<br>
	<br>
	<div><span class="table-title" >商家审核日志</span></div>
		<br>
	<form id="dataFormLog" runat="server">
		<div id="maingridLog" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
		
</body>
</html>
