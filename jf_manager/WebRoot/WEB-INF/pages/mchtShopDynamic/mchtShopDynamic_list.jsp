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
		height: $(window).height()*0.9,
		width: $(window).width()*0.9,
		title: "审核",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtShopDynamic/toAudit.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toView(id) {
	$.ligerDialog.open({
		height: $(window).height()*0.9,
		width: $(window).width()*0.9,
		title: "动态详情",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtShopDynamic/toView.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function updateWeight(id){
	$.ajax({ 
		url : "${pageContext.request.contextPath}/mchtShopDynamic/updateWeight.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {"id":id},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				location.reload();
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
			
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
	
}

function saveWeight(_this,id){
	$(_this).parent().find("span").remove();
	var weight = $.trim($(_this).val());
	var flag = weight.match(/^[1-9]\d*$/);
	if(weight != '' && flag != null && weight < 99999) {
		$.ajax({
			type : 'POST',
			url : "${pageContext.request.contextPath}/mchtShopDynamic/saveWeight.shtml",
			data : {id: id, weight: weight},
			dataType : 'json',
			success : function(data){
				if(data == null || data.code != 200)
					$.ligerDialog.error('操作超时，请稍后再试！');
				else{
					$(_this).parent().append("<span style='color:#009999;'>更改成功</span>");
				}
			},
			error : function(e) {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}else{
		$(_this).val("");
		$(_this).parent().append("<span style='color:red;'>请输入1~99999之间正整数</span>");
	}
}

 var listConfig={
     url:"/mchtShopDynamic/mchtShopDynamicData.shtml",
     listGrid:{ columns: [
						 {display: '权重',name:'', align:'left', isSort:false, width:100, render: function(rowdata, rowindex) {
							 return '<input value="'+rowdata.weight+'" style="width:90px;" onblur="saveWeight(this,'+rowdata.id+');">';
						 }},
			            { display: '商家序号', name:'mchtCode',render: function (rowdata, rowindex) {
			            	return rowdata.mchtCode;
						}},
			            { display: '公司名称', name:'companyName',render: function (rowdata, rowindex) {
			            	return rowdata.companyName;
						}},
			            { display: '店铺名称', name:'shopName',render: function (rowdata, rowindex) {
			            	return rowdata.shopName;
						}},
			            { display: '动态顶部封面', name:'topCover',render: function (rowdata, rowindex) {
			            	return "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.topCover+"' width='60' height='60' onclick='viewerPic(this.src)'>";
						}},
			            { display: '动态内容', name:'content',render: function (rowdata, rowindex) {
			            	return '<div style="word-break: break-all;">'+rowdata.content+'</div>';
						}},
			            { display: '绑定商品', name:'productTypeName',render: function (rowdata, rowindex) {
			            	var html = [];
			            	var productCodes = rowdata.productCodes;
			            	if(productCodes){
			            		var array = productCodes.split(",");
			            		for(var i=0;i<array.length;i++){
			            			html.push(array[i]+"<br>");
			            		}
			            	}
			            	return html.join("");
						}},
			            { display: '创建时间', name:'createDate',render: function (rowdata, rowindex) {
			            	var createDate = new Date(rowdata.createDate);
							return createDate.format("yyyy-MM-dd hh:mm:ss");
						}},
						{ display: '状态', name:'auditStatus',render: function (rowdata, rowindex) {
							var html = [];
							if(rowdata.auditStatus == "0"){
								html.push('待审核');
							}else if(rowdata.auditStatus == "1"){
								html.push('审核通过');
							}else if(rowdata.auditStatus == "2"){
								html.push('审核失败');
							}
							if(rowdata.delFlag == "1"){
								html.push('<br><span style="color:red;">已删除</span>');
							}
							return html.join();
						}},
			            { display: '操作',name:'op',render: function (rowdata, rowindex) {
			            	var html = [];
			            	html.push('<a href="javascript:;" onclick="toView('+rowdata.id+');">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;');
			            	if(rowdata.delFlag == "0"){
			            		html.push('<a href="${previewUrl}'+rowdata.id+'" target="_blank">预览</a><br>');
				            	if(rowdata.auditStatus == "0"){
									html.push('<a href="javascript:;" onclick="toAudit('+rowdata.id+');">审核</a>');
								}else if (rowdata.auditStatus != "0") {
									html.push('<a href="javascript:;" onclick="toAudit('+rowdata.id+');">重审</a>');
								}
			            	}
			            	return html.join("");
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
			<div class="search-td-label" >商家名称：</div>
			<div class="search-td-condition">
				<input type="text" id="name" name="name" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label">动态内容:</div>
			<div class="search-td-condition" >
				<input type="text" id="content" name="content" >
		 	</div>
			</div>
				
			<div class="search-td">
			<div class="search-td-label">状态:</div>
			<div class="search-td-condition" >
				<select id="auditStatus" name="auditStatus">
					<option value="">请选择</option>
					<option value="0" <c:if test="${defaultAuditStatus eq 0}">selected="selected"</c:if>>待审核</option>
					<option value="1" <c:if test="${defaultAuditStatus eq 1}">selected="selected"</c:if>>审核通过</option>
					<option value="2" <c:if test="${defaultAuditStatus eq 2}">selected="selected"</c:if>>审核失败</option>
					<!-- <option value="3" >已删除</option> -->
				</select>
		 	</div>
			</div>
		</div>	
		<div class="search-tr">	
			<div class="search-td">
			<div class="search-td-label" style="float:left;">创建日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="createDateBegin" name="createDateBegin" />
				<script type="text/javascript">
					$(function() {
						$("#createDateBegin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="createDateEnd" name="createDateEnd" />
				<script type="text/javascript">
					$(function() {
						$("#createDateEnd").ligerDateEditor({
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			
			<div class="search-td">
			 	<div class="search-td-label">主营类目：</div>
			 	<div class="search-td-condition"  >
			    	<select id="productTypeId" name="productTypeId" class="querysel">
						<option value="">请选择</option>
						<c:forEach var="list" items="${productTypes}">
								<option value="${list.id}">${list.NAME}</option>
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