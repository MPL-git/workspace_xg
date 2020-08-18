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
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
		 });
	 });
	 
	//任务详情
	function addLabelGroup(labelGroupId, labelGroupName, updateDate) {
 		parent.appendMemberLabelGroup(labelGroupId, labelGroupName, updateDate);
		$("#"+labelGroupId).html("【已添加】");
		if($("#memberLabelGroupIds").val() == '' ) {
			$("#memberLabelGroupIds").val(labelGroupId);
		}else {
			$("#memberLabelGroupIds").val($("#memberLabelGroupIds").val()+","+labelGroupId);
		}
 	 }
	 
 	 var listConfig={
	      url:"/memberLabelGroup/getMemberLabelGroupList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'分组名称',name:'labelGroupName', align:'center', isSort:false, width:160},
							{display:'分组包含用户数',name:'statNum', align:'center', isSort:false, width:100},
							{display:'分组条件',name:'labelTypeName', align:'center', isSort:false, width:200},
							{display:'备注',name:'remarks', align:'center', isSort:false, width:200},
							{display:'创建人',name:'createStaffName', align:'center', isSort:false, width:100},
							{display:'更新时间',name:'', align:'center', isSort:false, width:160, render:function(rowdata,rowindex){
								var html = [];
								if(rowdata.updateDate != null && rowdata.updateDate != "" ){
									var updateDate = new Date(rowdata.updateDate);
									html.push(updateDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
							}},
							{display:'操作',name:'', align:'center', isSort:false, width:180, render:function(rowdata,rowindex){
								var html = [];
								var updateDate = '';
								if(rowdata.updateDate != null && rowdata.updateDate != "" ){
									updateDate = new Date(rowdata.updateDate);
									updateDate = updateDate.format("yyyy-MM-dd");
								}
								html.push("<span id="+rowdata.id+"><a href=\"javascript:addLabelGroup(" + rowdata.id + ", '" + rowdata.labelGroupName + "', '" + updateDate + "');\">【添加】</a></span>");
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
	<!-- <div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<input type="hidden" id="memberLabelGroupIds" name="memberLabelGroupIds" value="${memberLabelGroupIds }" >
			<div class="search-tr" > 
				<div class="search-td">
					<div class="search-td-label"  >分组名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="labelGroupName" name="labelGroupName" >
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">创建日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="createDateBegin" name="createDateBegin" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="createDateEnd" name="createDateEnd" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
