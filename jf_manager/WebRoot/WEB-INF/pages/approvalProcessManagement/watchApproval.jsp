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
	<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>


    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />

    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


<style>
.upload_image_list {
    font: 12px/1.5 tahoma,arial,'Hiragino Sans GB','宋体',sans-serif;
}

.newTd{
	background-color: rgb(255, 255, 255);
	border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: rgb(102, 102, 102);
}
</style>
<script type="text/javascript">
var mchtBrandInvoicePic_viewer;
		
		$(function(){
			mchtBrandInvoicePic_viewer = new Viewer(document.getElementById('mchtBrandInvoicePic_viewer'), {});
			 $.metadata.setType("attr", "validate");
			 dataFromValidate =  $("#form1").validate({
					errorPlacement : function(lable, element) {
			      	$(element).ligerTip({recordContent : lable.html()});
					},
					success : function(lable) {
						lable.ligerHideTip();
						lable.remove();
					}
			   })
		})
		

		//附加下载
		function  downLoadFile(){
	   		 var attachmentPath = $("#fileDown").attr("attachmentpath");
	   		 var attachmentName = $("#fileDown").attr("attachmentname");
	   		 $.ajax({
	   				type: 'post',
	   				url: '${pageContext.request.contextPath}/imPeach/checkFileExists.shtml',
	   				data: {attachmentPath:attachmentPath},
	   				dataType: 'json',
	   				success: function(data){
	   					if(data == null || data.code != 200){
	   				    	commUtil.alertError(data.msg);
	   				  	}else{
	   				  		location.href="${pageContext.request.contextPath}/imPeach/downLoadAttachment.shtml?fileName="+attachmentName+"&filePath="+attachmentPath;
	   				  	}
	   				},
	   				error: function(e){
	   				 	commUtil.alertError("系统异常请稍后再试！");
	   				}
	   			});
		}
	 
		function auditView(LogId){
			 $.ligerDialog.open({
					height: 800,
					width: 900,
					title: "查看",
					name: "INSERT_WINDOW",
					url: "${pageContext.request.contextPath}/approvalProcessManagement/auditView.shtml?LogId="+LogId,
					showMax: true,
					showToggle: false,
					showMin: false,
					isResize: true,
					slide: false,
					data: null
			});
		}

</script>

</head>
	<body style="margin: 10px;">
		<table class="gridtable">
				<tr>
	            	<td class="title" width="20%">流程名称:<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td"  colspan="3" >
						 <span>${approvalApplicationCustom.procedureName }</span>
					</td>
	           	</tr>
	           	
	           	<tr>	       
					<td class="title" width="20%" style="margin-left: 20px;">标题名称:<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" width="20%"   colspan="3">
						 <span>${approvalApplicationCustom.name }</span>
					</td>
					
	           	</tr>
	           	
	           	<tr >	       
					<td  class="title"width="20%"  style="margin-left: 20px;">创建人:<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td">
							 <span>${approvalApplicationCustom.createName }</span>
					</td>
					<td class="title" width="20%" style="margin-left: 20px;">创建时间:<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
					<span><fmt:formatDate value="${approvalApplicationCustom.createDate }" pattern="yyyy-MM-dd HH:mm"/></span>
					</td>
	           	</tr>
	   
	      	<tr>
				<td class="title">内容</td>
				<td align="left" class="l-table-edit-td"  colspan="3">
					<div> ${approvalApplicationCustom.content }</div>
				</td>
			</tr>
	           	
	           	
			<tr>
				<td  colspan="1" class="title">图片</td>
				<td  colspan="3" align="left" class="l-table-edit-td" id="mchtBrandInvoicePic_viewer"  colspan="3">
			    	<t:imageList name="mchtBrandInvoicePictures" list="${mchtBrandInvoicePics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
			    	
			    	<ul id="aaa" name="mchtPlatformAuthPictures" class="upload_image_list">
			    	<c:forEach var="approvalApplication" items="${approvalApplicationPicList}">
			    	<li><p><img src="${pageContext.request.contextPath}/file_servelt${approvalApplication.pic}" path="${approvalApplication.pic }"></p></li>
			    	</c:forEach>
			    	</ul>
				
				</td>
			</tr>
	           	
	           		           	
	         <tr>
	            <td class="title" width="20%">链接</td>
				<td align="left" class="l-table-edit-td"  colspan="3">
					<span>${approvalApplicationCustom.linkUrl }</span>
				</td>
	         </tr>
	           	
	           	
	           		           	
	         <tr>
				<td class="title">附件</td>
				<td align="left" class="l-table-edit-td"  colspan="3">
					<!-- <input style="position:absolute; opacity:0;" type="file" id="uploadFile" name="uploadFile" onchange="ajaxFileUploadattachment(this);" />
					<input type="button" style="width: 45px;" value="上传"><br> -->
						<span id="attachmentNameDesc">${approvalApplicationCustom.enclosureName }</span>  &nbsp;&nbsp;&nbsp;&nbsp;
					<c:if test="${approvalApplicationCustom.enclosureName != null && approvalApplicationCustom.enclosureName != '' }">
					<a  href="javascript:downLoadFile();" id="fileDown"  attachmentname="${approvalApplicationCustom.enclosureName}" attachmentpath="${approvalApplicationCustom.filePath}" >下载</a>
					</c:if>	
				</td>
			</tr>
	           	
	           	
	           	
	       	 <tr>
				<td class="title">审批节点</td>
				<td align="left" class="l-table-edit-td" id="applicationNode"  colspan="3">
				<c:forEach var="auditNode" items="${auditNodeList }">
				${auditNode.name }
				
				<c:if test="${auditNode.approvalStatus == 0 && auditNode.seqNo != auditNode.maxSeq}">
				<img src="${pageContext.request.contextPath}/images/approvalApplication/dengdai.png">
				<!--箭头  -->
				<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/approvalApplication/jiantou.png"></a>
				</c:if>
				
				<c:if test="${auditNode.approvalStatus == 0 && auditNode.seqNo == auditNode.maxSeq}">
				<img src="${pageContext.request.contextPath}/images/approvalApplication/dengdai.png">
				</c:if>
				
				<c:if test="${auditNode.approvalStatus == 1 && auditNode.seqNo != auditNode.maxSeq }">
				<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/approvalApplication/gouxuan.png"></a>
				<!--箭头  -->
				<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/approvalApplication/jiantou.png"></a>
				</c:if>
				
				<c:if test="${auditNode.approvalStatus == 1 && auditNode.seqNo == auditNode.maxSeq}">
				<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/approvalApplication/wanchen.png"></a>
				</c:if>
				
				
				
				</c:forEach>
				</td>
			</tr>
		</table>

		<br/>
		<div>
			<span class="table-title" >审核记录</span>
		</div>
		<table class="gridtable">
			<tr align="center">
				<td class="title">操作人</td>
				<td class="title">所属部门</td>
				<td class="title">操作时间</td>
				<td class="title">操作</td>
				<td class="title">审批节点</td>
				<td class="title" width="20%">审批备注</td>
				<td class="title">审批图片</td>
			</tr>
			<c:forEach items="${approvalApplicationLogs}" var="approvalApplicationLog">
			<tr align="center">
					<td>${approvalApplicationLog.createName }</td>
					<td>${approvalApplicationLog.department }</td>
				<td><fmt:formatDate value="${approvalApplicationLog.createDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td>${approvalApplicationLog.operation }</td>
				<td>${approvalApplicationLog.approvalNode }</td>
				<td width="20%">${approvalApplicationLog.approvalRemarks }</td>
				<td>
					<c:if test="${not  empty approvalApplicationLog.approvalPic && tag !=''}">
					<a  name='${approvalApplicationLog.operation }'  href="javascript:auditView(${approvalApplicationLog.id});" >查看</a>
					</c:if>
				</td>
			</tr>
			</c:forEach>
		</table>

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