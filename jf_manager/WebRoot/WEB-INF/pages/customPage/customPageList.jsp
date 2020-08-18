<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
function edit(id) {
	$.ligerDialog.open({
		height: $(window).height()*0.5,
		width: $(window).width()*0.5,
		title: "编辑",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/customPage/editCustomPage.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function changeStatus(id,status) {
	var title;
	if(status == "0"){
		title = "确认上架吗？";
	}else{
		title = "确认下架吗？";
	}
	$.ligerDialog.confirm("是否"+title+"？", function (yes) {
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/customPage/changeStatus.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"id":id},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						listModel.gridManager.reload();
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

function previewDecorateInfo(id) {
	$.ligerDialog.open({
		height: $(window).height()*0.9,
		width: $(window).width()*0.5,
		title: "预览",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/customPage/previewDecorateInfo.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function designDecorateArea(decorateInfoId) {
	$.ligerDialog.open({
		height: $(window).height()*0.9,
		width: $(window).width()*0.8,
		title: "装修",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/customPage/designDecorateArea.shtml?decorateInfoId=" + decorateInfoId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function delDecorateArea(id) {
	$.ligerDialog.confirm("是否删除？", function (yes)
	{
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/customPage/delDecorateArea.shtml?id=" + id,
				secureuri : false,
				dataType : 'json',
				cache : false,
				async : false,
				success : function(data) {
					if ("0000" == data.returnCode) {
						//location.reload();
						listModel.gridManager.reload();
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

 var listConfig={
	  
     url:"/customPage/data.shtml",
     btnItems:[{ text: '添加', icon: 'add', dtype:'win',  click: itemclick, url: "/customPage/editCustomPage.shtml", seqId:"", width: 600, height: 400}],
     listGrid:{ columns: [
						{ display: 'ID', name:'id'},
			            { display: '类型', render: function (rowdata, rowindex) {
			            	if(rowdata.pageType == "1"){
								return rowdata.pageTypeDesc;		                		
			            	}else if(rowdata.pageType == "2"){
								return '<span style="color:red;">'+rowdata.pageTypeDesc+'</span>';		                		
			            	}else if(rowdata.pageType == "3"){
			            		return '<span style="color:#5500FF;">'+rowdata.pageTypeDesc+'</span>';
			            	}else if(rowdata.pageType == "4"){
			            		return '<span style="color:blue;">'+rowdata.pageTypeDesc+'</span>';
			            	}
						}},
			            { display: '页面名称',name:'pageName'},
			            { display: '备注',name:'remarks'},
			            { display: '创建时间',render: function (rowdata, rowindex) {
							return new Date(rowdata.createDate).format("yyyy-MM-dd hh:mm:ss");		                		
						}},
		                { display: '上架状态',name:'statusDesc'},
		                { display: '主题信息操作', render: function (rowdata, rowindex) {
		                	var html = '<a href="javascript:;" onclick="edit('+rowdata.id+')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;';
		                	if(rowdata.status == "0"){
		                		html+='<a href="javascript:;" onclick="changeStatus('+rowdata.id+','+rowdata.status+')">上架</a>&nbsp;&nbsp;&nbsp;&nbsp;';
		                	}else if(rowdata.status == "1"){
		                		html+='<a href="javascript:;" onclick="changeStatus('+rowdata.id+','+rowdata.status+')">下架</a>&nbsp;&nbsp;&nbsp;&nbsp;';
		                	}
		                	html+='<a href="javascript:;" onclick="previewDecorateInfo('+rowdata.id+')">预览</a>';
							return html;		                		
						}},
		                { display: '装修操作', render: function (rowdata, rowindex) {
		                	var html = '<a href="javascript:;" onclick="designDecorateArea('+rowdata.decorateInfoId+')">装修</a>';
		                	if(rowdata.status == "0"){
								html+='&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="delDecorateArea('+rowdata.id+')">删除</a>';
							}
							return html;
						}},
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
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">类型：</div>
			<div class="l-panel-search-item" >
				<select id="pageType" name="pageType" style="width: 150px;">
					<option value="">不限</option>
					<c:forEach var="pageType" items="${pageTypeList}">
						<option value="${pageType.statusValue}">${pageType.statusDesc}</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			<div class="search-td">
			<div class="search-td-label" >名称：</div>
			<div class="search-td-condition">
				<input type="text" id="pageName" name="pageName">
			</div>
			</div>
			 
			<div class="search-td">
			<div class="search-td-label" >备注：</div>
			<div class="search-td-condition">
				<input type="text" id="remarks" name="remarks">
			</div>
			</div> 
			 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">上架状态：</div>
			<div class="l-panel-search-item" >
				<select id="status" name="status">
					<option value="">不限</option>
					<c:forEach var="status" items="${statusList}">
						<option value="${status.statusValue}">${status.statusDesc}</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			
			<div class="search-td-search-btn">
				<div id="searchbtn">搜索</div>
			</div>
			
		</div>
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>