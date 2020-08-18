<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String htmlData = request.getParameter("NOTICE_CONTENT") != null ? request.getParameter("NOTICE_CONTENT") : "";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
   
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">


//添加流程节点
function addNode() {
	var seq = 1;
	$("table[name='nodeCount']").each(function(){
		seq+=1;
	})
	
	var procedureId = $("#procedureId").val();
	
	 $.ligerDialog.open({
			height: 600,
			width: 600,
			title: "添加节点",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/approvalProcessManagement/addNode.shtml?procedureId="+procedureId+"&seq="+seq,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
	});
}


//上移
	function moveUp(procedureNodeId){
		var prevId = $("a[id="+procedureNodeId+"]").closest("tr[name='nodeTr']").prev("tr").attr("id");
		if(procedureNodeId && prevId){
			$.ajax({
				url : "${pageContext.request.contextPath}/approvalProcessManagement/moveUp.shtml?prevId="+prevId+"&procedureNodeId="+procedureNodeId,
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {prevId:prevId,procedureNodeId:procedureNodeId},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("操作成功");
						location.reload();
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			
		}
		
	}

//下移
	function moveDown(procedureNodeId){
		var nextId = $("a[id="+procedureNodeId+"]").closest("tr[name='nodeTr']").next("tr").attr("id");
		if(procedureNodeId && nextId){
			$.ajax({
				url : "${pageContext.request.contextPath}/approvalProcessManagement/moveDown.shtml?nextId="+nextId+"&procedureNodeId="+procedureNodeId,
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {procedureNodeId:procedureNodeId,nextId:nextId},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("操作成功");
						location.reload();
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			
		}
	}
	
	
//修改流程节点
	function edit(procedureNodeId) {
	var seq = 1;
	$("table[name='nodeCount']").each(function(){
		seq+=1;
	})
	var procedureId = $("#procedureId").val();
	
	 $.ligerDialog.open({
			height: 600,
			width: 600,
			title: "修改节点",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/approvalProcessManagement/addNode.shtml?procedureNodeId="+procedureNodeId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
	});
}


//删除节点 
	function del(procedureNodeId){
	    var title="删除";
	    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
	    if (yes) {
		$.ajax({
			url : "${pageContext.request.contextPath}/approvalProcessManagement/delNode.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {procedureNodeId:procedureNodeId},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess("操作成功");
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
	 })
  }

</script>
</head>
  
  <body>
  	  <form method="post" id="form1" name="form1"  style="margin: 10px" >
  	 	<input type="hidden" name="procedureId" id="procedureId" value="${procedureId}"/>
  		<table class="gridtable">
		   <tr >
               <td  class="title" width="150px;">名称：</td>
               <td align="left" class="l-table-edit-td">
              <span>${procedureCustom.name }</span>
               </td>
           </tr>
           
           <c:if test="${procedureCustom.status==0}">
           <tr >
               <td  class="title">操作</td>
               <td align="left" class="l-table-edit-td">
               		 <input type="button"  style="margin-right: 50px;width: 90px;" class="l-button l-button-submit" value="添加流程节点"  onclick="addNode()" /> 
               </td>
           </tr>
           </c:if>
           
 
         
         <c:forEach var="procedureNode" items="${procedureNodeList}" varStatus="i">
         <tr name="nodeTr" id="${procedureNode.id}"> <td  class="title">第${i.count}步 </td>
        	<td>
        		<table class="gridtable" name="nodeCount">
        			   <tr>
			               <td  class="title" width="150px;">节点：</td>
			               <td align="left" class="l-table-edit-td">
			              	<span>${ procedureNode.name}</span>
			               </td>
			           </tr>
			           
			           <tr >
			               <td  class="title" width="150px;">节点类型：</td>
			               <td align="left" class="l-table-edit-td">
			               <c:if test="${ procedureNode.type==0}">
			               <span>正常节点</span>
			           	   </c:if>
			              	
			               <c:if test="${ procedureNode.type==1}">
			               <span>核心节点</span>
			               </c:if>
			              	
			                <c:if test="${ procedureNode.type==2}">
			              	<span>备用节点</span>
			              	</c:if>
			               </td>
			           </tr>
			           
			           <tr>
			               <td  class="title" width="150px;">审批人类型：</td>
			               <td align="left" class="l-table-edit-td">
			               <c:if test="${ procedureNode.approverType==0}">
			               <span>上级</span>
			               </c:if>
			              	
			                <c:if test="${ procedureNode.approverType==1}">
			              	<span>指定人员</span>
			              	</c:if>
			               </td>
			           </tr>
			           
			            <tr><td  class="title">审批人:</td>
				         <td>
				          <c:if test="${ procedureNode.approverType==1}">
				          
				         <c:forEach var="staffname" items="${procedureNode.staffNameList }">
				         <span>${staffname}</span>,
				         </c:forEach>
				         
				         </c:if>
				         </td>
				        </tr>
				        
				        
				         <tr>
			               <td  class="title" width="150px;">操作：</td>
			               <td align="left" class="l-table-edit-td">
			               <c:if test="${i.index!=0}">
			              	<a href="javascript:moveUp(${procedureNode.id});" name="moveUp"  id="${procedureNode.id}">上移</a>
			               </c:if>
			               
			                <c:if test="${!i.last}">
			              	<a href="javascript:moveDown(${procedureNode.id});" name="moveDown"  id="${procedureNode.id}">下移</a>
			              	  </c:if>
			              	<a href="javascript:edit(${procedureNode.id});">修改</a>
			              	
			              	<a href="javascript:del(${procedureNode.id});">删除</a>
			               </td>
			           </tr>
        		
        		</table>
        	
        	</td>
         </tr>
         </c:forEach>
          

		     <tr><td  class="title">抄送人</td>
	         <td>
	         <c:forEach var="sysStaffInfoCustom" items="${sysStaffInfoCustomList }">
	         <span>${sysStaffInfoCustom.staffName}</span>,
	         </c:forEach>
	         </td>
	         </tr>


			<tr>
  			   <td class="title">操作：</td>   
	           <td align="left" class="l-table-edit-td" >
	           <input type="button" value="关闭窗口" class="l-button l-button-submit" onclick="frameElement.dialog.close()"/>
	       </td></tr>
  		</table>
  		</form>
  </body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>
