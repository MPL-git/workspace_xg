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
var productTypeCombo;
var timestamp;
$(function(){
	var infoType=$("#infoType").val();
	/* var infoTypes =$("#infoTypes");  */
	if(infoType!=''){
		var strs= new Array(); //定义一数组 
		strs=infoType.split(","); //字符分割 
		for (i=0;i<strs.length;i++) 
		{
	
			var j=strs[i]-1;
			$("input[name='infoTypes']")[j].checked =true; 
			
		} 
	}
	
 if ($("#fileName").val()==''||$("#fileName").val()==null){
 	timestamp = (new Date()).valueOf();
 	$("#fileName").val(timestamp);
 };
	
	productTypeCombo= $("#catalogName").ligerComboBox({
          selectBoxWidth: 200,
          selectBoxHeight: 200,  
          valueField: 'id',
          textField: 'frontName',
          valueFieldID:'catalogId',
          treeLeafOnly:false,
          valueField : 'id',
          width: 160,
          tree: { url: '${pageContext.request.contextPath}/infomation/getCatalogTree.shtml', checkbox: false, ajaxType: 'get', idFieldName: 'id',textFieldName:'frontName',parentIDFieldName:'parentId',isExpand:2}
      });
});
   
   
	window.onload = function () {
		productTypeCombo.setValue("${Info.catalogId}");
   		productTypeCombo.setText("${Info.catalog}");
   	};  
   
   
  /*  var editor1;
   KindEditor.ready(function(K) {
	 editor1 = K.create('textarea[name="content"]', {
		cssPath : '${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css',
		uploadJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/upload_json.jsp',
		fileManagerJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/file_manager_json.jsp',
		allowFileManager : true,
		afterCreate : function() {
		}
		
	});
	prettyPrint();
}); */
  
function ajaxFileUploadLogo() {
	$.ajaxFileUpload({
		url: contextPath + '/service/common/ajax_upload.shtml',
		secureuri: false,
		fileElementId: "logoPicFile",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				$("#logoPic").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
				$("#pic").val(result.FILE_PATH);
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});
}  
</script>
</head>
  
  <body>
  	  <form method="post" id="form1" name="form1" 
  	 	action="${pageContext.request.contextPath}/infomation/saveinformation.shtml" style="margin: 10px" >
  	 	<input type="hidden" name="id" id="id" value="${inforId}"/>
  	 	<input id="infoType" name="infoType" type="hidden" value="${Info.infoType}"/>
  		<table class="gridtable">
		   <tr >
               <td  class="title" width="150px;">标题：</td>
               <td align="left" class="l-table-edit-td"><input type="text" name="title" id="title" value="${Info.title}"  style="width:300px;" validate="{required:true,maxlength:60}" disabled="true"/></td>
           </tr>
           
            <tr >
               <td  class="title">发布时间：</td>
               <td align="left" class="l-table-edit-td">
	               <input type="text" id="releaseTime" name="releaseTime" value="<fmt:formatDate value='${Info.releaseTime}' pattern='yyyy-MM-dd'/>" validate="{required:true}" disabled="true"/>
					<script type="text/javascript">
						/* $(function() {
							$("#releaseTime").ligerDateEditor( {
								showTime : false,
								labelWidth : 160,
								width:160,
								labelAlign : 'left'
							});
						}); */
					</script> 
               </td>
           </tr>
           
           <tr >
               <td  class="title">栏目：</td>
               <td align="left" class="l-table-edit-td">
               		<input id="catalogName" type="text" style="width:450px;" validate="{required:true}" disabled="true"/>
               </td>
           </tr>
           
            <tr >
               <td  class="title">标题图片：</td>
               <td align="left" class="l-table-edit-td" >
               		<div><img id="logoPic" style="width: 200px;height: 200px" alt="" src="${pageContext.request.contextPath}/file_servelt${Info.pic}" ></div>
	    			<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file" onchange="ajaxFileUploadLogo();" />
	    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    			</div>
	    			<input id="pic" name="pic" type="hidden" value="${Info.pic}">
               </td>
           </tr>
           
           <tr>
         	<td class="title">浏览权限</td>
			<td align="left" class="l-table-edit-td">
				<input name="infoTypes" type="checkbox" value="1" style="width: 30px;"disabled="true"/>游客
				<input name="infoTypes" type="checkbox" value="2" style="width: 30px;"disabled="true"/>会员
				<input name="infoTypes" type="checkbox" value="3" style="width: 30px;"disabled="true"/>未合作商家
				<input name="infoTypes" type="checkbox" value="4" style="width: 30px;"disabled="true"/>入驻商家
				<input name="infoTypes" type="checkbox" value="5" style="width: 30px;"disabled="true"/>平台用户 				
			</td>
			</tr>
           
             <tr >
               <td  class="title">状态：</td>
               <td align="left" class="l-table-edit-td">
               		<select id="status" name="status" style="width: 160px;" disabled="true">
						<c:forEach var="list" items="${statu}">
							<option <c:if test="${statuvalue==list.statusValue}">selected</c:if>
								 value="${list.statusValue}">${list.statusDesc}
							</option>
						</c:forEach>
					</select>
               </td>
           </tr>
           
           <tr>
				<td class="title">正文</td>
				<%-- <td align="left" class="l-table-edit-td" >
					<textarea name="content" id="NOTICE_CONTENT" style="width:150px;height:300px;visibility:hidden;" >${NOTICE_CONTENT}</textarea>
              	</td> --%>  
              	 <td align="left" class="l-table-edit-td">
					<%-- <textarea id="NOTICE_CONTENT" name="content" rows="5" cols="50" class="text" >${NOTICE_CONTENT}</textarea> --%>
					<div>${NOTICE_CONTENT}</div> 
				 </td>
				
            </tr>
             
             <tr >
               <td  class="title">文件名：</td>
               <td align="left" class="l-table-edit-td" >
               <input type="text" name="fileName" id="fileName" value="${ Info.fileName}" disabled="disabled"/>
               </td>
           </tr>
             
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