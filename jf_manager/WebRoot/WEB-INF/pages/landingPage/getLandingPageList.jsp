<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
			 showTime: true,
			 format: "yyyy-MM-dd",
			 labelAlign: 'left',
			 width: 135
		 });
	 })

	//查看地址
	function viewAddress(id) {
		$.ligerDialog.open({
			height: 250,
			width: 600,
			title: "设置",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/landingPage/viewAddress.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});				
	}
	
	//添加、修改落地页
	function addOrModify(id) {
		$.ligerDialog.open({
			height: 750,
			width:  1100,
			title: "修改落地页",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/landingPage/addOrModify.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});				
	}
	
 	var listConfig={
	      url:"/landingPage/getLandingPageListData.shtml",
		  btnItems:[{ text: '添加落地页', icon: 'add', dtype:'win',  click: itemclick, url:"/landingPage/addOrModify.shtml", seqId:"", width : 1100, height:750}],
	      listGrid:{ columns: [                    
	                        {display:'落地页ID', name:'id', align:'center', isSort:false},
	                        {display:'落地页名称', name:'name', align:'center', isSort:false},
	                        {display:'落地页界面', name:'url', align:'center', isSort:false, render: function (rowdata, rowindex) {
	                        	return '<a href="${mUrl}?id='+rowdata.id+'" target="_blank">预览</a>';
	                        }},
	                        {display:'参数', name:'param', align:'center', isSort:false, render: function (rowdata, rowindex) {
	                        	return "<a href=\"javascript:viewAddress(" + rowdata.id + ");\">查看地址</a>";
	                        }},
	                        {display:'备注', name:'remarks', align:'center', isSort:false},
	                        {display:'创建人', name:'createBy', align:'center', isSort:false},
	                        {display:'创建时间', name:'createDate', align:'center', isSort:false, render: function (rowdata, rowindex) {
									var html = [];
									if (rowdata.createDate != null && rowdata.createDate != '') {
										var createDate = new Date(rowdata.createDate);
										html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
									}
									return html.join("");
	                        }},
	                        {display:'操作', name:'', align:'center', isSort:false, render: function(rowdata, rowindex) {
								return "<a href=\"javascript:addOrModify(" + rowdata.id + ");\">修改</a>";
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
 
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<input type="hidden" id="pageSize" value="${pageSize}">
	<input type="hidden" id="pageNumber" value="${pageNumber}">
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td"  style="width:230px;">
					<div class="search-td-label">落地页ID</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="id" name="id">
					</div>
				</div>
				<div class="search-td" style="width:230px;">
					<div class="search-td-label">落地页名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="name" name="name">
					</div>
				</div>
					<div class="search-td" style="width: 30%;margin-bottom:-6px;">
						<div class="search-td-label" style="float: left;width: 20%;margin-top:2px;">创建日期</div>
						<div class="l-panel-search-item" >
							<input type="text" id="createDateBegin" name="createDateBegin" class="dateEditor" placeholder="请选择" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 5px;margin-right: 5px;" >—</div>
						<div class="l-panel-search-item">
							<input type="text" id="createDateEnd" name="createDateEnd" class="dateEditor" placeholder="请选择" style="width: 135px;" />
						</div>
					</div>
					<div class="search-td-search-btn">
					<div id="searchbtn" class="l-button" style="float: left;">搜索</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
