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
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left'
		});
		
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
	 });
		
	function viewerPic(pic) {
		imgPath=$(pic).attr("src");
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$("#viewer-pictures")
				.append(
						'<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'),
				{
					hide : function() {
						$("#viewer-pictures").hide();
					}
				});
		$("#viewer-pictures").hide();
		viewerPictures.show();

	}
	 
	//编辑
	function edit(id) {
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width()*0.9,
			title: "编辑",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/task/taskEdit.shtml?Id=" + id ,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//详情
	function details(id) {
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width()*0.9,
			title: "详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/task/taskDetails.shtml?Id=" + id ,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//装修
	function design(decorateInfoId,taskId) {
		$.ligerDialog.open({
			height: $(window).height()*0.9,
			width: $(window).width()*0.9,
			title: "装修",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/task/design.shtml?decorateInfoId=" + decorateInfoId + "&taskId=" + taskId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null,
			closeRefresh: true
		});
	}
		
	 
	//添加任务
 	function addTask() {
 		$.ligerDialog.open({
 			height: $(window).height() - 100,
 			width: $(window).width() - 200,
 			title: "添加",
 			name: "INSERT_WINDOW",
 			url: "${pageContext.request.contextPath}/task/taskEdit.shtml",
 			showMax: true,
 			showToggle: false,
 			showMin: false,
 			isResize: true,
 			slide: false,
 			data: null
 		});
 	 }
	
	//删除
	function deleteTask(id) {
		$.ligerDialog.confirm('是否确定删除？', function(yes) {
			if(yes) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/task/deleteTask.shtml",
					 data : {id : id},
					 dataType : 'json',
					 success : function(data){
						 if(data == null || data.statusCode != 200)
							 commUtil.alertError(data.message);
						 else{
							 $("#searchbtn").click();
						 }
					 },
					 error : function(e) {
						 commUtil.alertError("系统异常请稍后再试");
					 }
				 });
			}
		});
	}
		
	//提审
	function arraigned(id) {
		$.ligerDialog.confirm('是否确定提审？', function(yes) {
			if(yes) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/task/arraigned.shtml",
					 data : {id : id},
					 dataType : 'json',
					 success : function(data){
						 if(data == null || data.statusCode != 200)
							 commUtil.alertError(data.message);
						 else{
							 $("#searchbtn").click();
						 }
					 },
					 error : function(e) {
						 commUtil.alertError("系统异常请稍后再试");
					 }
				 });
			}
		});
	}

	
 	var listConfig={
	      url:"/task/getTaskList.shtml",
	      btnItems:[{ text: '添加', icon:'add', id:'add', dtype:'win', click:addTask }],
	      listGrid:{ columns: [
	                        {display:'标题', name:'name', align:'center', isSort:false, width:180},
	                        {display:'封面', name:'coverPic', align:'center', isSort:false, width:180,render: function(rowdata, rowindex) {
			                    var h = "";
			                      if(rowdata.coverPic!=null&&(rowdata.coverPic.indexOf("http") >= 0)){
			                       h += "<img src='"+rowdata.coverPic+"' width='180' height='60' onclick='viewerPic("+rowdata.id+")'>";
			                      }else{
			                       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.coverPic+"' width='180' height='60' onclick='viewerPic(this)'>";
			                      }
			                    return h;
							}},
	                        {display:'简介', name:'content', align:'center', isSort:false, width:180},
	                        {display:'状态', name:'status', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
	                        	var html = "";
	                        	if (rowdata.status == "0") {
	                        		html = "待提审";
								}else if(rowdata.status == "1"){
									html = "待运营审核";
								}else if(rowdata.status == "2"){
									html = "待法务审核";
								}else if(rowdata.status == "3"){
									html = "法务审核通过";
								}else if(rowdata.status == "4"){
									html = "已执行";
								}else if(rowdata.status == "5"){
									html = "运营审核失败";
								}else if(rowdata.status == "6"){
									html = "法务审核失败";
								}else if(rowdata.status == "7"){
									html = "已取消";
								}
								return html;
							}},
							{display:'创建人', name:'createName', align:'center', isSort:false, width:180},
	                        {display:'创建时间', name:'createDate', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
	                        	var html = [];
	                        	if (rowdata.createDate != null && rowdata.createDate != '') {
									var createDate = new Date(rowdata.createDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
								
							}},
	                        {display:'操作', name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.status == "0"){
									html.push("<a href=\"javascript:edit(" + rowdata.id + ");\">编辑</a>&nbsp;&nbsp;");
									html.push("<a href=\"javascript:arraigned(" + rowdata.id + ");\">提审</a>&nbsp;&nbsp;");
									html.push("<a href=\"javascript:details(" + rowdata.id + ");\">详情</a>&nbsp;&nbsp;");
									html.push("<a href=\"javascript:deleteTask(" + rowdata.id + ");\">删除</a>");
								}else if(rowdata.status == "1" || rowdata.status == "2"){
									html.push("<a href=\"javascript:details(" + rowdata.id + ");\">详情</a>&nbsp;&nbsp;");
									html.push("<a href=\"javascript:deleteTask(" + rowdata.id + ");\">删除</a>");
								}else if(rowdata.status == "3" || rowdata.status == "4"){
									html.push("<a href=\"javascript:details(" + rowdata.id + ");\">详情</a>");
								}else if(rowdata.status == "5" || rowdata.status == "6"){
									html.push("<a href=\"javascript:edit(" + rowdata.id + ");\">编辑</a>&nbsp;&nbsp;");
									html.push("<a href=\"javascript:details(" + rowdata.id + ");\">详情</a>&nbsp;&nbsp;");
									html.push("<a href=\"javascript:deleteTask(" + rowdata.id + ");\">删除</a>");
								}
							    return html.join("");
							}},
	                        {display:'装修操作', name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.status == "0" || rowdata.status == "5" || rowdata.status == "6"){
									html.push("<a href=\"javascript:design(" + rowdata.decorateInfoId +","+ rowdata.id +");\">装修</a>&nbsp;&nbsp;");
								}
								html.push('<a href="${previewUrl}'+rowdata.taskActivitySelectionId+'" target="_blank">预览</a>');
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
 
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >标题：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="name" name="name">
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label"  >状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 70%;">
							<option value="">请选择</option>
							<option value="0" selected>待提审</option>
							<option value="1">待运营审核</option>
							<option value="2">待法务审核</option>
							<option value="3">法务审核通过</option>
							<option value="4">已执行</option>
							<option value="6">法务审核失败</option>
							<option value="5">运营审核失败</option>
						</select>
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label" style="float:left;width: 20%;">创建日期：</div>
					<div class="l-panel-search-item">
						<input type="text" id="create_date_begin" name="create_date_begin" value="${dateBegin}"/>
						<script type="text/javascript">
							$(function() {
								$("#create_date_begin").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
					<div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
				</div>
				<div class="search-td">
					<div class="l-panel-search-item">
						<input type="text" id="create_date_end" name="create_date_end" value="${dateEnd}"/>
						<script type="text/javascript">
							$(function() {
								$("#create_date_end").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
				</div>
				
				<div class="search-td-search-btn" >
					<div id="searchbtn" class="l-button">搜索</div>
				</div>
			</div>	
		</div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
