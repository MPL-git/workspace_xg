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
var listConfig={
	     url:"",
	     listGrid:{ columns: [
							
			                ],
	                 showCheckbox : false,  //不设置默认为 true
	                 showRownumber: true //不设置默认为 true
	      } ,  							
	     container:{
	        searchBtnName:"searchbtn",
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }        
	  };
function getData(dateBegin,dateEnd,platformContactId){
	$.ajax({
		url : "${pageContext.request.contextPath}/mchtData/lawAuditSituation/data.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {"dateBegin":dateBegin,"dateEnd":dateEnd,"platformContactId":platformContactId},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				$("#listContent").html("");
				var html = [];
				html.push("1.新创建商家"+data.totalMchtInfoCount+"家；<br>");
				html.push("2.审核"+data.totalAuditCount+"个商家，"+data.hasOpenCount+"家已经开通，");
				for(var i=0;i<data.productTypeList.length;i++){
					if(i!=data.productTypeList.length-1){
						html.push(data.productTypeList[i].name+"类"+data.productTypeList[i].productTypeCount+"家，");
					}else{
						html.push(data.productTypeList[i].name+"类"+data.productTypeList[i].productTypeCount+"家；");
					}
				}
				html.push("驳回"+data.rejectAuditCount+"家。<br>");
				if(data.mchtContractList[1]){
					if(data.mchtContractList[2]){
						html.push("3.审核线上合同"+data.mchtContractList[0].total+"家，通过"+data.mchtContractList[1].acceptCount+"家，驳回"+data.mchtContractList[2].rejectCount+"家。<br>");
					}else{
						html.push("3.审核线上合同"+data.mchtContractList[0].total+"家，通过"+data.mchtContractList[1].acceptCount+"家，驳回0家。<br>");
					}
				}else{
						html.push("3.审核线上合同"+data.mchtContractList[0].total+"家，通过0家，驳回0家。<br>");
				}
				if(data.mchtContracts[1]){
					html.push("4.商家合同通过归档"+data.mchtContracts[0].acceptCount+"份，归档不通过"+data.mchtContracts[1].rejectCount+"份。");
				}else{
					html.push("4.商家合同通过归档"+data.mchtContracts[0].acceptCount+"份，归档不通过0份。");
				}
				$("#listContent").html(html.join(""));
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

$(function() {
	var notAuth = $("#notAuth").val();
	if(notAuth != 1){
		var platformContactId = $("#platformContactId").val();
		getData('${dateBegin}','${dateEnd}',platformContactId);
	}
	
	$("#searchbtn").bind('click',function(){
		if(notAuth == 1){
			commUtil.alertError("对不起，你没有权限查看。");
			return;
		}
		var dateBegin = $("#dateBegin").val();
		var dateEnd = $("#dateEnd").val();
		var platformContactId = $("#platformContactId").val();
		if(!dateBegin || !dateEnd){
			commUtil.alertError("请选择日期");
			return;
		}
		getData(dateBegin,dateEnd,platformContactId);
	});			
		
});
	
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<form id="dataForm" runat="server">
		<input type="hidden" id="notAuth" value="${notAuth}">
		<div class="search-pannel">
			<div class="search-tr">
					<div class="search-td">
					<div class="search-td-label" style="float:left;">日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="dateBegin" name="dateBegin" value="${dateBegin}"/>
						<script type="text/javascript">
							$(function() {
								$("#dateBegin").ligerDateEditor( {
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
						<input type="text" id="dateEnd" name="dateEnd" value="${dateEnd}"/>
						<script type="text/javascript">
							$(function() {
								$("#dateEnd").ligerDateEditor( {
									showTime : false,
									labelWidth : 150,
									labelAlign : 'left'
								});
							});
						</script>	
					</div>
					</div>
				<div class="search-td">
				  <div class="search-td-label">法务对接人：</div>
				    <div class="search-td-condition" >
					<select id="platformContactId" name="platformContactId" >
						<c:if test="${isManagement==1}">
							<option value="" selected="selected">全部商家</option>
							<c:forEach items="${platformContacts}" var="platformContact">
								<option value="${platformContact.id}">${platformContact.contactName}的商家</option>
							</c:forEach>
						</c:if>
						<c:if test="${isManagement==0}">
							<option value="${myContactId}" selected="selected">我对接的商家</option>
							<c:forEach items="${platformContacts}" var="platformContact">
								<option value="${platformContact.id}">${platformContact.contactName}的商家</option>
							</c:forEach>			
						</c:if>
					</select>
				</div>
				</div> 
				<div class="search-td-search-btn" >
					<div id="searchbtn" >查看</div>
				</div>
			</div>
		</div>
		<div class="table-title" id="listContent">
				
		</div>
	</form>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
