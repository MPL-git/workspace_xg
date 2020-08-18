<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

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
	
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
$(function(){
	$("#form1").validate({
        submitHandler: function(form) {  
        	var mchtCodes = $("#mchtCodes").val();
   		    if(mchtCodes == '') {	    
   		      commUtil.alertError("推送商家的商家序号，必须检测不能为空！");
   		       return
   		    }
    		var msgType = $("#msgType").val();
    		var title = $("#title").val();
    		var content = $("#content").val();
    		if(msgType == '') {
        		commUtil.alertError("所以字段都必填,不能为空！");
        		return;
        	}
        	if(title == '') {
        		commUtil.alertError("所以字段都必填不能为空！");
        		return;
        	}
        	if(content=='') {
        	    commUtil.alertError("所以字段都必填,不能为空！");
    			return;
        	}
        	var uploading = $("#uploading").val();
        	if(uploading == 1){
        		commUtil.alertError("附近上传中，请稍后再提交！");
    			return;
        	}
        	form.submit();
        }
    }); 
	
});

//检测商家
function choiceMemberList(){
	var mchtCodes = $.trim($("#mchtCodes").val());
	if(mchtCodes == '') {
		commUtil.alertError("推送商家的商家序号不能为空！");
		return;
	}
	if(!/^[a-zA-Z0-9]+(,[a-zA-Z_0-9]+)*$/.test(mchtCodes)) {
		commUtil.alertError("请输入正确格式的推送商家的商家序号。例：P1,P2,P3");
		return;
	}
	if (mchtCodes.length>800) {
		commUtil.alertError("每次最多添加推送的商家序号100个");
		return;
	}
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 200,
		title: "商家列表",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/platformMsg/establishMailmchtInfoList.shtml?mchtCodes="+mchtCodes,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});

}

function ajaxFileUpload(obj) {
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
</script>

</head>
	<body style="margin: 10px; margin-bottom: 200px;">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/platformMsg/addestablishMail.shtml" >
		<input type="hidden" id="id" name="id" value="${platformMsgEdit.id}" />
		<input type="hidden" id="attachmentName" name="attachmentName" value="${platformMsgEdit.attachmentName}"/>
		<input type="hidden" id="attachmentPath" name="attachmentPath" value="${platformMsgEdit.attachmentPath}"/>
		<input type="hidden" id="uploading" value="0"/>
		<table class="gridtable">
		    <tr>
            	<td class="title" width="20%">类型<span class="required">*</span></td>
            	<td>
				<select id="msgType" name="msgType" style="width:100px;">
 					    <option value="">请选择...</option>
 					    <option value="A1"<c:if test="${platformMsgEdit.msgType eq 'A1' }">selected</c:if>>退款</option>
                        <option value="A2"<c:if test="${platformMsgEdit.msgType eq 'A2' }">selected</c:if>>退货</option>
                        <option value="A3"<c:if test="${platformMsgEdit.msgType eq 'A3' }">selected</c:if>>换货</option>
                        <option value="A4"<c:if test="${platformMsgEdit.msgType eq 'A4' }">selected</c:if>>投诉</option>
                        <option value="A5"<c:if test="${platformMsgEdit.msgType eq 'A5' }">selected</c:if>>违规</option>
                        <option value="A6"<c:if test="${platformMsgEdit.msgType eq 'A6' }">selected</c:if>>活动</option>
                        <option value="TZ"<c:if test="${platformMsgEdit.msgType eq 'TZ' }">selected</c:if>>通知</option>
				</select>
			  </td>
           	</tr>
			<tr>
            	<td class="title" width="20%">标题<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:335px;" type="text" id="title" name="title" value="${platformMsgEdit.title}" maxlength="30" validate="{required:true}" />
					<span style="color: #6B6B6B;">(注意：标题字符限制30字)</span>
				</td>
           	</tr>
			<tr>
				<td class="title">推送商家的商家序号<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input type="hidden" id="" value="" />
					<textarea id="mchtCodes" name="mchtCodes" rows="10" class="textarea" cols="100">${platformMsgEdit.mchtCodes}</textarea>
					<input type="button" id="choice" style="background-color: rgba(255, 153, 0, 1);width: 50px;cursor:pointer;height: 24px;border-radius: 3px;" onclick="choiceMemberList();" value="检测">
					<span style="color: #6B6B6B;">(注意：商家序号用英文逗号隔开，每次最多添加推送的商家序号100个)</span>
				</td>
			</tr>
			<tr>
				<td class="title">编辑站内信<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<textarea id="content" name="content" rows="4" cols="50" class="text" >${platformMsgEdit.content}</textarea>
				</td>
			</tr>
			<tr>
				<td class="title">附件</td>
				<td align="left" class="l-table-edit-td">
					<input style="position:absolute; opacity:0;" type="file" id="uploadFile" name="uploadFile" onchange="ajaxFileUpload(this);" />
					<input type="button" style="width: 45px;" value="上传">
					<span id="attachmentNameDesc">${platformMsgEdit.attachmentName}</span>
				</td>
			</tr>
			<tr>
            	<td class="title" width="20%">操作<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>