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
var logoPic_viewer; 
var viewerPictures;
var logoPic_viewer; 
var viewerPictures;
var dataFromValidate;
var mchtBrandInvoicePic_viewer;



		var editor1;
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
		});



		
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
		
		
		
		
		//限制上传附件大小
		function ajaxFileUploadattachment(obj) {
			var file = obj.files[0];
		    var reader = new FileReader();  
		    reader.onload = function(e) { 
		    	var filesize = file.size;
		    		if(filesize>1024*1024*10) {
		    			commUtil.alertError("附件总大小不超过10M!");
		        	}else {
		    			ajaxFileUploads(obj);
		    		}
		    },
		    reader.readAsDataURL(file);
		}

		
		function ajaxFileUploads(obj) {
		    var file = obj.files[0];
			var fileName = file.name;
			$("#attachmentName").val(fileName);
			$("#attachmentNameDesc").text(fileName);
		    var reader = new FileReader();
		    $("#uploading").val(1);
		    reader.onload = function(e) {
		    	$.ajaxFileUpload({
		    		url: '${pageContext.request.contextPath}/service/common/fileUpload.shtml?fileType=8',
		    		secureuri: false,
		    		fileElementId: "uploadFile",
		    		dataType: 'json',
		    		success: function(result, status) {
		    			if(result.RESULT_CODE == 0){
		    			   var filePath = result.FILE_PATH;
			               $("#attachmentPath").val(filePath);
			               $("#uploading").val(0);
			               commUtil.alertSuccess("文件上传成功");
		   			  	}else{
		   			  		alert(result.RESULT_MESSAGE);
		   			  	}
		    		},
		    		error: function(e) {
		    			alert("服务异常");
		    		}
		    	});
		    };
		    reader.readAsDataURL(file);  
		}



	
		//获取节点和抄送人
			function getNodeAndCopies(){
		    var procedureId =	$("#procedureId").val();
			$.ajax({
				url : "${pageContext.request.contextPath}/approvalProcessManagement/getNodeAndCopies.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {procedureId:procedureId},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						$("#copies").html("");
						$("#applicationNode").html("");
						var staffNameList = data.staffNameList;//抄送人
						var procedureNodeList = data.procedureNodeList;//节点
						/*审批人  */
						for(var i = 0;i<staffNameList.length;i++){
							var staffName=staffNameList[i].staffName;
							$("#copies").append("&nbsp;<span>"+staffName+",</span> ");
						}
						/*节点  */
						for(var i = 0;i<procedureNodeList.length;i++){
							var procedureNodeName = procedureNodeList[i].name;
							var procedureNodeType = procedureNodeList[i].type; 
							if(procedureNodeType==0||procedureNodeType==1){
								$("#applicationNode").append("&nbsp;<input type='checkbox' checked='checked' disabled>"+procedureNodeName+"");
							}else{
								$("#applicationNode").append("&nbsp;<input type='checkbox'  disabled>"+procedureNodeName+"");
							};
						}
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
		
		//图片格式验证
		function ajaxFileUploadImg(_this) {
			var file = document.getElementById(_this.id).files[0]; 
		    if(!/image\/(PNG|png|JPG|jpg|JPEG|jpeg|gif|GIF)$/.test(file.type)){  
		    	commUtil.alertError("图片格式不正确！");
		        return;
		    }
		    var reader = new FileReader();  
		    reader.onload = function(e) { 
		    	var image = new Image();
		    	image.onload = function() {
		        	ajaxFileUpload(_this);
		        };
		        image.src = e.target.result;
		    }
		    reader.readAsDataURL(file);  
		}

		function ajaxFileUpload(_this) {
			var id = $(_this).attr("id");
			var data_value = $(_this).attr("data_value");
			$.ajaxFileUpload({
				url:"${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
				secureuri: false,
				fileElementId: id,
				dataType: 'json',
				success: function(result, status) {
					if(result.RESULT_CODE == 0) {
						$("#aaa").append('<li><p><img src="${pageContext.request.contextPath}/file_servelt'+result.FILE_PATH+'" path="'+result.FILE_PATH+'"></p><a href="javascript:void(0);" class="del">删除</a></li>');
						$(".del").live('click',function(){
							$(this).closest("li").remove();
						});
		
						//对viewer进行重新赋值
							mchtBrandInvoicePic_viewer.destroy();
							mchtBrandInvoicePic_viewer = new Viewer(document.getElementById('mchtBrandInvoicePic_viewer'), {});
							
					} else {
						alert(result.RESULT_MESSAGE);
					}
				},
				error: function(result, status, e) {
					alert("服务异常");
				}
			});
		}


	 //保存
	 function save(){
		//被选中的备选
	 	var standbys=[];
	 	$("input[name='standby']:checked").each(function(){
	 	  var value =  $(this).val();
	 		if(value!=null){
	 			standbys.push(value)
	 		}
	 	})
	 	
	 	editor1.sync();
		var dataJson = $("#form1").serializeArray();
		dataJson.push({"name":"standbys","value":JSON.stringify(standbys)});
		var approvalRemark = $("#approvalRemark").val();
		if(approvalRemark.length>200){
			commUtil.alertError("审核备注不能超过200个字");
			return;
		}
		
		
		if(dataFromValidate.form()){
		$.ajax({
			url : "${pageContext.request.contextPath}/approvalProcessManagement/saveApproval.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : dataJson,
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess("提交成功");
					parent.location.reload();
					frameElement.dialog.close();
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
				
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
		}
	 }
	 
	 
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
	 

</script>

</head>
	<body style="margin: 10px;">
	<form method="post" id="form1" name="form1"  >
		<input type="hidden" id="attachmentName" name="attachmentName" value=""/>
		<input type="hidden" id="attachmentPath" name="attachmentPath" value=""/>
		<input type="hidden" id="approvalApplicationId" name="approvalApplicationId" value="${approvalApplication.id}"/>
		<input type="hidden" id="nodeApproverId" name="nodeApproverId" value="${nodeApproverCustom.id}"/>
		<input type="hidden" id="uploading" value="0"/>
		<input type="hidden" id="id" name="id" value="${customerServiceOrderCustom.id}"/>
		<table class="gridtable">
				<tr>
	            	<td class="title" width="20%">流程名称:<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						 <span>${approvalApplicationCustom.procedureName }</span>
					</td>
	           	</tr>
	           	
	           	<tr>	       
					<td class="title" width="20%" style="margin-left: 20px;">标题名称:<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" width="20%" >
						 <span>${approvalApplicationCustom.name }</span>
					</td>
					
	           	</tr>
	           	
	           	<tr >	       
					<td  class="title"width="20%"  style="margin-left: 20px;">创建人:<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
							 <span>${nodeApproverCustom.createName }</span>
					</td>
					
					
	           	</tr>
	           		           	 <tr >	       
					<td class="title" width="20%" style="margin-left: 20px;">创建时间:<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						 <span><fmt:formatDate value="${nodeApproverCustom.createDate }" pattern="yyyy-MM-dd HH:mm"/></span>
					</td>
	           	</tr> 
	           	
	           	
	      	<tr>
				<td class="title">内容</td>
				<td align="left" class="l-table-edit-td">
					<div> ${approvalApplicationCustom.content }</div>
				</td>
			</tr>
	           	
	           	
			<tr>
				<td  colspan="1" class="title">图片</td>
				<td  colspan="3" align="left" class="l-table-edit-td" id="mchtBrandInvoicePic_viewer">
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
				<td align="left" class="l-table-edit-td" >
					<span>${approvalApplicationCustom.linkUrl }</span>
				</td>
	         </tr>
	           	
	           	
	           		           	
	         <tr>
				<td class="title">附件</td>
				<td align="left" class="l-table-edit-td">
					<!-- <input style="position:absolute; opacity:0;" type="file" id="uploadFile" name="uploadFile" onchange="ajaxFileUploadattachment(this);" />
					<input type="button" style="width: 45px;" value="上传"><br> -->
						<span id="attachmentNameDesc">${approvalApplicationCustom.enclosureName }</span>  &nbsp;&nbsp;&nbsp;&nbsp;
						
					<c:if test="${approvalApplicationCustom.enclosureName != null && approvalApplicationCustom.enclosureName != ''  }">	
					<a  href="javascript:downLoadFile();" id="fileDown"  attachmentname="${approvalApplicationCustom.enclosureName}" attachmentpath="${approvalApplicationCustom.filePath}" >下载</a>
					</c:if>
				</td>
			</tr>
	           	
	           	
	           	
	       	 <tr>
				<td class="title">审批节点</td>
				<td align="left" class="l-table-edit-td" id="applicationNode">
				<c:forEach var="auditNode" items="${auditNodeList }">
				&nbsp;<input type='checkbox'  checked disabled>${auditNode.name }
				</c:forEach>
				</td>
			</tr>
			
			<tr>
				<td class="title">审核结果</td>
				<td align="left" class="l-table-edit-td">
				<input name="auditNode"  checked  type="radio" value="1" > 通过    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="auditNode"   type="radio" value="2" > 驳回
				</td>
			</tr>
			
			
			<tr>
				<td class="title">审核备注</td>
				<td align="left" class="l-table-edit-td">
					<textarea name="approvalRemark" id="approvalRemark"  style="width:750px;height:100px;resize:none;"></textarea>
				</td>
			</tr>
			
			     	
	      	<tr>
				<td class="title">审批图片</td>
				<td align="left" class="l-table-edit-td">
					<textarea name="content" id="NOTICE_CONTENT" style="width:150px;height:300px;visibility:hidden;">${approvalApplication.content}</textarea>
				</td>
			</tr>

		<c:if test="${approvalApplicationNode.type==1 }">
			<tr>
				<td class="title">备用节点</td>
				<td align="left" class="l-table-edit-td">
				<c:forEach var="standbyNode" items="${standbyNodeList }">
				&nbsp;<input type='checkbox' name="standby" value="${standbyNode.id }"  <c:if test="${standbyNode.needApproval==1}">checked</c:if> >${standbyNode.name }
				</c:forEach>
				</td>
			</tr>
		</c:if>
         
			 <tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input  class="l-button l-button-submit" value="提交"  onclick="save()"/> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
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