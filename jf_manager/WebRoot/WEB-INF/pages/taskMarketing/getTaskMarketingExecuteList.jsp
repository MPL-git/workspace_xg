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
	 function viewTaskMarketing(taskMarketingId) {
 		$.ligerDialog.open({
 			height: $(window).height() - 100,
 			width: $(window).width() - 100,
 			title: "任务详情",
 			name: "INSERT_WINDOW",
 			url: "${pageContext.request.contextPath}/taskMarketing/viewTaskMarketing.shtml?taskMarketingId="+taskMarketingId,
 			showMax: true,
 			showToggle: false,
 			showMin: false,
 			isResize: true,
 			slide: false,
 			data: null
 		});
 	 }
	 
	 //执行任务
	 function auditOrApproveTask(taskMarketingId) {
 		$.ligerDialog.open({
 			height: $(window).height() - 100,
 			width: $(window).width() - 100,
 			title: "执行",
 			name: "INSERT_WINDOW",
 			url: "${pageContext.request.contextPath}/taskMarketing/auditOrApproveTaskManager.shtml?taskMarketingId="+taskMarketingId,
 			showMax: true,
 			showToggle: false,
 			showMin: false,
 			isResize: true,
 			slide: false,
 			data: null
 		});
 	 }
	 
	 //提交审核 或 取消
	 function auditTask(taskId, taskStatus) {
		 var msg = '是否确定提交审核？';
		 if(taskStatus == 7 ) {
			 msg = '是否确定取消任务？';
		 }
		 $.ligerDialog.confirm(msg, function (status){  
             if(status==true){
            	 $.ajax({
        			 type: 'post',
        			 url: '${pageContext.request.contextPath }/taskMarketing/auditTask.shtml',
        			 data: {taskId : taskId, status : taskStatus},
        			 dataType: 'json',
        			 success: function(data) {
        				 if(data == null || data.statusCode != 200){
        				     commUtil.alertError(data.message);
        				 }else {
        					 $("#searchbtn").click();
        				 }
        			 },
        			 error: function(e) {
        				 commUtil.alertError("系统异常请稍后再试");
        			 }
        		 });
             }
         });
	 }
	 
 	 var listConfig={
	      url:"/taskMarketing/getTaskMarketingList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'任务名称',name:'', align:'center', isSort:false, width:160, render:function(rowdata,rowindex) {
								var html = [];
								html.push("<a href=\"javascript:viewTaskMarketing(" + rowdata.id + ");\">" + rowdata.taskName + "</a>");
								return html.join("");
							}},
							{display:'发送内容',name:'taskContent', align:'center', isSort:false, width:260},
							{display:'发送用户数',name:'sendMemberCount', align:'center', isSort:false, width:100},
							{display:'创建人',name:'createStaffName', align:'center', isSort:false, width:100},
							{display:'审核人',name:'auditStaffName', align:'center', isSort:false, width:100},
							{display:'审批人',name:'approvalStaffName', align:'center', isSort:false, width:100},
							{display:'创建时间',name:'', align:'center', isSort:false, width:160, render:function(rowdata,rowindex){
								var html = [];
								if(rowdata.createDate != null && rowdata.createDate != "" ){
									var createDate = new Date(rowdata.createDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
							}},
							{display:'任务状态',name:'taskStatusDesc', align:'center', isSort:false, width:100},
							{display:'操作',name:'', align:'center', isSort:false, width:180, render:function(rowdata,rowindex){
								var html = [];
								html.push("<a href=\"javascript:auditOrApproveTask(" + rowdata.id + ");\">【执行】</a>");
								html.push("<a href=\"javascript:auditTask(" + rowdata.taskId + ", 7);\">【取消】</a>");
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
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >任务名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="taskName" name="taskName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >任务状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="taskStatus" name="taskStatus" style="width: 135px;" >
							<c:forEach items="${taskStatusList }" var="taskStatus">
								<c:if test="${taskStatus.statusValue == '3' }">
									<option value="${taskStatus.statusValue }" >${taskStatus.statusDesc }</option>
								</c:if> 
							</c:forEach>
						</select>
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
