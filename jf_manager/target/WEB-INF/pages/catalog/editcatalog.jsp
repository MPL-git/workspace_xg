<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
	
	
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	 
	<script type="text/javascript">
		var catalogFlag=true;
		var oldCatalog;
		$(function ()  { 
			oldCatalog = $("#catalog").val(); 
			$("#catalog").blur(function(){
				var catalog = $("#catalog").val(); 
				catalog = catalog.replace(" ","");
				catalog = $.trim(catalog);
				var reg=/^[a-zA-Z]+$/;
				if (catalog != "") {
					if(!reg.test(catalog)){
						$("#catalog").ligerTip({content : "栏目目录只允许为英文!"});
						catalogFlag=false;
					}else{
						if (catalogIsHased(catalog)) {
							$("#catalog").ligerTip({content : "有重复，请更换。"});
							catalogFlag=false;
						}else{
							$("#catalog").ligerTip({content : "没有重复，可使用。"});
							catalogFlag=true;
						}
					}
				}
			});
			
			 $.metadata.setType("attr", "validate");
	          $("#form1").validate({
						errorPlacement : function(lable, element) {
							$(element).ligerTip({
								content : lable.html()
							});
						},
						success : function(lable) {
							lable.ligerHideTip();
							lable.remove();
						},
						submitHandler : function(form) {
							form.submit();
						}
				});

		});  
		
		//判断栏目目录的规则
		function catalogIsHased(catalog) {
			var isHased = false;
			if (catalog!=oldCatalog){
				$.ajax({
					url : "${pageContext.request.contextPath}/catalog/checkCatalog.shtml",
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {name : catalog,oldName:oldCatalog},
					timeout : 30000,
					success : function(data) {
						if ("1" == data.hased) {
							isHased = true;
						}
					},
					error : function() {
						alert('操作超时，请稍后再试！');
					}
				});
			}
			return isHased;
		}
		
		
		
		//后端名称
		$.validator.addMethod("backName", function(value, element) {  
			var backName=document.getElementById("backName");
			if($.trim(backName.value)!=''){
				return true;
			}else{
				return false;
			}
	    }, "请输入后端栏目名称");
		
		//前端名称
		$.validator.addMethod("frontName", function(value, element) {  
			var frontName=document.getElementById("frontName");
			if($.trim(frontName.value)!=''){
				return true;
			}else{
				return false;
			}
	    }, "请输入前端栏目名称");
		
		//栏目目录
		$.validator.addMethod("catalog", function(value, element) {  
			var catalog=document.getElementById("catalog");
			if($.trim(catalog.value)!=''&& catalogFlag){
				return true;
			}else{
				return false;
			}
	    }, "请输入栏目目录名称");
		
		//栏目排序
		$.validator.addMethod("seqNo", function(value, element) {  
			var seqNo=document.getElementById("seqNo");
			var reg = new RegExp("^[0-9]*$");  
			if($.trim(seqNo.value)!='' && reg.test(seqNo.value)){
				return true;
			}else{
				return false;
			}
	    }, "请输入正确的栏目序号，为纯数字");
		
		//状态
		$.validator.addMethod("status", function(value, element) {  
			var status=document.getElementById("status");
			if($.trim(status.value)!=''){
				return true;
			}else{
				return false;
			}
	    }, "请选择状态");     
</script>

</head>
	<body style="padding:50px">
	<form name="form1" class="form1" action="${pageContext.request.contextPath}/catalog/addcatalog.shtml" method="post" id="form1" enctype="multipart/form-data">
		<input name="id" type="hidden"  value="${catalog.id}" />
		<c:if test="${ todo=='add'}">
			<input name="parentId" type="hidden"  value="${parent_id}" />
			<input name="method" type="hidden"  value="add" />
		</c:if>
		<c:if test="${ todo!='add'}">
			<input name="parentId" type="hidden"  value="${catalog.parent_id}" />
			<input name="method" type="hidden"  value="update" />
		</c:if>
		<table  class="gridtable"  >
		 	<tr >
               <td class="title" width="20%">上级栏目:*</td>
               <c:if test="${ parentname==''}">
					<td align="left" class="l-table-edit-td" ></td>
				</c:if>
				<c:if test="${ parentname!=''}">
					<td align="left" class="l-table-edit-td" >
					<c:if test="${todo=='add'}">${parentname}</c:if>
					<c:if test="${todo!='add'}">${catalog.parent_name}</c:if>
					</td>
				</c:if>
           </tr>
		   <tr >
               <td  class="title" width="20%">后端栏目名称:</td>
               <td align="left" class="l-table-edit-td" ><input type="text" name="backName" id="backName" class="backName" value="${catalog.back_name}"/></td>
           </tr>
           
           <tr >
               <td  class="title" width="20%">前端栏目名称:</td>
               <td align="left" class="l-table-edit-td" ><input type="text" name="frontName" id="frontName" class="frontName" value="${catalog.front_name}"/></td>
           </tr>
            
           <tr>
               <td   class="title" width="20%">栏目目录</td>
               <td align="left" class="l-table-edit-td">
                  <input name="catalog" type="text" class="catalog" id="catalog"  value="${catalog.catalog}"/>
               </td>
           </tr>
           
           <tr>
               <td   class="title" width="20%">栏目排序</td>
               <td align="left" class="l-table-edit-td">
                  <input name="seqNo" type="text" id="seqNo" class="seqNo" value="${catalog.seq_no}"/>
               </td>
           </tr>
           
           <tr>
               <td  class="title"width="20%">状态*</td>
               <td align="left" class="l-table-edit-td">
			     <select  class="status"  style="float:left;width: 160px;" id="status" name="status">
				    <c:forEach var="list" items="${status}">
						<option value="${list.statusValue}" <c:if test="${list.statusValue==catalog.status}">selected="selected"</c:if>>${list.statusDesc}</option>
					</c:forEach>
				  </select>
				  <label id="labelStatus" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
			   </td>
			</tr>

           <tr>            
           <td colspan="2"> 
            <input type="button" value="取消" style="float:right;" class="l-button l-button-submit" onclick="frameElement.dialog.close()"/>
			<input name="btnSubmit" type="submit" id="Button1" style="float:right; margin-left: 50px;" class="l-button l-button-submit" value="保存"  /> 
       </td></tr>
		</table> 
		
	</form>
	</body>
</html>