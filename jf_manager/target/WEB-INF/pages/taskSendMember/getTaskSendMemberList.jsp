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
	 
 	 var listConfig={
	      url:"/taskSendMember/getTaskSendMemberList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'会员ID',name:'memberId', align:'center', isSort:false, width:80},
							{display:'会员昵称',name:'memberNick', align:'center', isSort:false, width:160},
							{display:'手机号',name:'mobile', align:'center', isSort:false, width:120},
							{display:'发送内容',name:'taskContent', align:'center', isSort:false, width:260},
							{display:'条数',name:'taskSendCount', align:'center', isSort:false, hide:${sendChannel == '1'}, width:80},
							{display:'成本（元）',name:'taskSendAmount', align:'center', isSort:false, hide:${sendChannel == '1'}, width:100},
							{display:'发送状态',name:'taskSendMemeberStatusDesc', align:'center', isSort:false, width:80},
							{display:'发送时间',name:'', align:'center', isSort:false, width:160, render:function(rowdata,rowindex){
								var html = [];
								if(rowdata.sendDate != null && rowdata.sendDate != "" ){
									var sendDate = new Date(rowdata.sendDate);
									html.push(sendDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
							}},
							{display:'7天内首次登录时间',name:'', align:'center', isSort:false, width:160, render:function(rowdata,rowindex){
								var html = [];
								if(rowdata.memberLoginDate != null && rowdata.memberLoginDate != "" ){
									var memberLoginDate = new Date(rowdata.memberLoginDate);
									html.push(memberLoginDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
							}},
							{display:'7天内付款订单数',name:'combineOrderCount', align:'center', isSort:false, width:100, render:function(rowdata,rowindex){
								if(rowdata.status == "3" ){
									return "";
								}else {
									return rowdata.combineOrderCount+"";
								}
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
		<input type="hidden" name="taskId" value="${taskId }" />
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" >会员ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberId" name="memberId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >会员名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberNick" name="memberNick" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >手机号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberMobile" name="memberMobile" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >发送状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach items="${taskSendMemeberList }" var="taskSendMemeber">
								<option value="${taskSendMemeber.statusValue }" >${taskSendMemeber.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="search-tr" >
				<div class="search-td">
					<div class="search-td-label"  >7天内是否登录：</div>
					<div class="search-td-combobox-condition" >
						<select id="loginStatus" name="loginStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >7天内是否下单：</div>
					<div class="search-td-combobox-condition" >
						<select id="orderStatus" name="orderStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">发送日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="startSendDate" name="startSendDate" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endSendDate" name="endSendDate" class="dateEditor" style="width: 135px;" />
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
