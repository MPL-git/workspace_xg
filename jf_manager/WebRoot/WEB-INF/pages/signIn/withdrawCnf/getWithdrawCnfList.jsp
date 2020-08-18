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
	 
	 //启用
	 function updateIsEffect(withdrawCnfId, isEffect, withdrawCnfName) {
		 var isEffectDesc = "";
		 if(isEffect == '0') {
			 isEffectDesc = "是否确认启用";
		 }else if(isEffect == '1') {
			 isEffectDesc = "是否确认停用";
		 }
		 $.ligerDialog.confirm(isEffectDesc+"<span style='color: red;'>"+withdrawCnfName+"</span>提现方式？", function(yes) {
			 if(yes) {
				 $.ajax({
					 type: 'post',
					 url: '${pageContext.request.contextPath }/withdrawCnf/updateIsEffect.shtml',
					 data: {withdrawCnfId : withdrawCnfId, isEffect : isEffect},
					 dataType: 'json',
					 success: function(data) {
						 if(data == null || data.code != 200){
						     commUtil.alertError(data.msg);
						 }else {
							 $("#searchbtn").click();
						 }
					 },
					 error: function(e) {
						 commUtil.alertError("系统异常请稍后再试！");
					 }
				 });
			 }
		 });
	 }
	 
	 //编辑
	 function updateWithdrawCnf(withdrawCnfId) {
		 $.ligerDialog.open({
			height: 400,
			width: 500,
			title: "修改提现方式",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/withdrawCnf/addWithdrawCnfManager.shtml?withdrawCnfId="+withdrawCnfId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
 	 var listConfig={
	      url:"/withdrawCnf/getWithdrawCnfList.shtml",
	      btnItems:[{ text:'新增提现方式', icon:'add', dtype:'win', click:itemclick, url:'/withdrawCnf/addWithdrawCnfManager.shtml', seqId:'', width:500, height:400}],
	      listGrid:{ columns: [
							{display:'提现名称',name:'name', align:'center', isSort:false, width:180},
							{display:'类型',name:'withdrawTypeDesc', align:'center', isSort:false, width:120},
							{display:'金额',name:'amount', align:'center', isSort:false, width:100},
							{display:'是否启用',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
	 		                	var html = [];
	 		                	if(rowdata.isEffect == '0') {
	 		                		html.push("否");
	 		                	}else if(rowdata.isEffect == '1') {
	 		                		html.push("是");
	 		                	}
	 		                	return html.join("");
			                }},
			                {display:'操作人员',name:'createName', align:'center', isSort:false, width:100},
							{display:'操作',name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.isEffect == '0') {
									html.push("<a href='javascript:;' onclick='updateIsEffect("+rowdata.id+", "+rowdata.isEffect+" ,\""+rowdata.name+"\");'>【启用】</a>");
								}else if(rowdata.isEffect == '1'){
									html.push("<a href='javascript:;' onclick='updateIsEffect("+rowdata.id+", "+rowdata.isEffect+" ,\""+rowdata.name+"\");'>【停用】</a>");
								}
								html.push("<a href='javascript:;' onclick='updateWithdrawCnf("+rowdata.id+");'>【编辑】</a></br>");
							    return html.join("");
							}}
			         ], 
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
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
	<form id="dataForm" runat="server" >
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >提现名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="name" name="name" >
					</div>
				</div>
			 	<div class="search-td">
					<div class="search-td-label" >类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="withdrawType" name="withdrawType" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="withdrawType" items="${withdrawTypeList }">
								<option value="${withdrawType.statusValue }">
									${withdrawType.statusDesc }
								</option>
							</c:forEach>
						</select>
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
