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
<style type="text/css">
 	.radioClass {
		margin-right: 20px;
	}
 	.table-edit-activity-time div,
    .table-edit-activity-time span {
		display: inline-block;
		vertical-align: middle;
    }
    .activity-time {
    	margin-left: 50px;
    }
    .activity-hint {
    	color: #6B6B6B;
    }
</style>
<script type="text/javascript">
    //判断字符是否为空
    function isEmpty(obj){
        return (typeof obj === 'undefined' || obj === null || obj === "");
    }
	var submitFlag = true;
	$(function(){
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format: "yyyy-MM-dd hh:mm:ss",
			labelAlign : 'left',
			width : 160
		});

		$("[name='sendMode']").change(function(){
			if($(this).val() == '0' ) {
				$("#send-date-div").css("display", "none");
				$("#sendDate").val("");
			}else {
				$("#send-date-div").css("display", "inline-block");
			}
		});
		
		$("#name").bind("input propertychange",function(event){	
			if($("#name").val().length == 50){
				$(".name").show();
			}else{
				$(".name").hide();
			}	
		});
		
		$("#content").bind("input propertychange",function(event){	
			if($("#content").val().length == 50){
				$(".content").show();
			}else{
				$(".content").hide();
			}	
		});
		
		$("#taskExplain").bind("input propertychange",function(event){	
			if($("#taskExplain").val().length == 100){
				$(".taskExplain").show();
			}else{
				$(".taskExplain").hide();
			}	
		});
		
		$.metadata.setType("attr", "validate");
		$("#form1").validate({
	        errorPlacement: function (lable, element) {  
	        	var elementType=$(element).attr("type");
	        	if('radio'==elementType) {
	        		var radioName=$(element).attr("name");
	        		$("input[type=radio][name="+radioName+"]:last").parent("span").ligerTip({
						content : lable.html(),width: 100
					});
	        	}else {
        			$(element).ligerTip({
						content : lable.html(),width: 100
					});
        	  	}
	        	$(".l-verify-tip-corner").css("z-index", "1001");
	        	$(".l-verify-tip-content").css("z-index", "1000");
	        },
	        success: function (lable,element) {
	            lable.ligerHideTip();
	            lable.remove();
	        },
	        submitHandler: function(form) {
	        	var sendMode = $("input[name='sendMode']:checked").val();
	        	if(sendMode == '1' ) {
	        		if($("sendDate").val() == '' ) {
		        		commUtil.alertError("定时时间不能为空！");
		        		return;
	        		}
	        		var date = new Date();
					var startDate = date.format("yyyy-MM-dd hh:mm");
	        		if(!compareTime(startDate, $("#sendDate").val())) {
						commUtil.alertError("定时时间，必须大于现在时间！");
						return;
					}
	        	}
   				var file = $("#filePath").val();
	        	if(file != '' ) {
    				var exec = (/[.]/.exec(file)) ? /[^.]+$/.exec(file.toLowerCase()) : '';  
    		        if (exec != "xls" && exec != "xlsx") {  
    		        	commUtil.alertError("文件格式不对，请上传.xls或.xlsx文件!");  
    		            return;  
    		        }  
	        	}
	        	var pic=$("#coverPic").val();
	        	if(!pic){
	        		commUtil.alertError("请上传图片!");  
		            return; 
	        	}
	        	var filePathName =$("#filePath-name").text();
	        	var sendValues =$("#sendValues").val();
	        	if(!isEmpty(sendValues)){
                    var splitArr = sendValues.split("\n");
                    for(j = 0,len=splitArr.length; j < len; j++) {
                        if(!/^[1-9]\d*$/.test(splitArr[j])){
                            commUtil.alertError("用户ID要为正整数");
                            return;
                        }
                        if(Number(splitArr[j]) >= 2147483647){
                            commUtil.alertError("用户ID输入值过大");
                            return;
                        }
                    }
				}
	        	var labelGroupIds =$("#labelGroupIds").val();
	        	if(!filePathName && !sendValues && !labelGroupIds){
	        		commUtil.alertError("用户ID为必填项!");  
		            return; 
	        	}
	        	if(submitFlag){
	        		submitFlag = false;
	        		form.submit();
	        	}
	        }
	    }); 
		
	});
	
	//删除分组
	function delMemberLabelGroup(labelGroup) {
		$(labelGroup).parent("p").remove();
		var labelGroupId=$(labelGroup).attr("placeholder");
		var labelGroupIdsArr=$("#labelGroupIds").val().split(",");
		labelGroupIdsArr.splice(labelGroupIdsArr.indexOf(labelGroupId),1);
		$("#labelGroupIds").val(labelGroupIdsArr.join(","));
	}
	
	//查看会员
	function memberView(labelGroupId) {
		$.ligerDialog.open({
 			height: $(window).height() - 50,
 			width: $(window).width() - 50,
 			title: "查看会员",
 			name: "INSERT_WINDOW",
 			url: "${pageContext.request.contextPath}/memberLabelRelation/memberLabelRelationListManager.shtml?labelGroupId="+labelGroupId,
 			showMax: true,
 			showToggle: false,
 			showMin: false,
 			isResize: true,
 			slide: false,
 			data: null
 		});
	}
	
	//选择分组
	function addMemberLabelGroup(){
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width()*0.9,
			title: "选择分组",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/task/addMemberLabelGroup.shtml",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});				
	}
	
	//下载方法
	function downLoadUserCodeExcel() {
		$.ligerDialog.confirm('是否下载该Excel？', function(yes) {
			if(yes) {
				var filePath=$("#filePath-name").text();
				location.href="${pageContext.request.contextPath}/task/downLoadUserCodeExcel.shtml?filePath="+filePath;
			}
		});
	}
	
	//导入文件
	function taskFilePathFun() {
		var file = document.getElementById("filePath").files[0];
		/* var exec = (/[.]/.exec(file.name)) ? /[^.]+$/.exec(file.name.toLowerCase()) : '';  
        if (exec != "xls" && exec != "xlsx") {  
        	commUtil.alertError("文件格式不对，请上传.xls或.xlsx文件!");  
            return;  
        }else {
        	$("#filePath-name").html(file.name);
        } */
		$("#filePath-name").html(file.name);
	}

	function compareTime(startDate, endDate) {   
		if (startDate.length > 0 && endDate.length > 0) {   
			var startDateTime = new Date(startDate).getTime();
			var endDateTime = new Date(endDate).getTime();
			if (startDateTime >= endDateTime) {   
		        return false;   
			} else {   
		    	return true;   
		    }   
		} else {   
		    return false;   
		}   
	}  
	
	//图片格式验证
	function ajaxFileUploadImg(_this) {
		var file = document.getElementById(_this).files[0]; 
		 if(!/image\/(JPEG|jpeg|JPG|jpg|png|PNG)$/.test(file.type)){  
	        	commUtil.alertError("图片格式需为jpg或png！");
	        	$("input[name='file']").attr('type','text'); 
				$("input[name='file']").attr('type','file'); 
	            return;
	        }

	    var reader = new FileReader();  
	    reader.onload = function(e) { 
	    	var image = new Image();
	    	image.onload = function() {
	   		 if(this.width != '1242' || this.height != '414'){
					commUtil.alertError("图片尺寸需为1242*414px！");
					$("input[name='file']").attr('type','text'); 
					$("input[name='file']").attr('type','file'); 
					return;
				}else{
	    			ajaxFileUploadLogo(_this);
				}
	        };
	        image.src = e.target.result;
	    }
	    reader.readAsDataURL(file);  
	}
	
	function ajaxFileUploadLogo() {
		$.ajaxFileUpload({
			url: contextPath + '/service/common/ajax_upload.shtml',
			secureuri: false,
			fileElementId: "coverPicFile",
			dataType: 'json',
			success: function(result, status) {
				if(result.RESULT_CODE == 0) {
					$("#coverPicImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
					$("#coverPic").val(result.FILE_PATH);
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
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/task/confirmTaskEdit.shtml" enctype="multipart/form-data" >
		<input type="hidden" id="id"  name="id" value="${taskCustom.id}"> 
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">文章标题<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:160px;" maxLength="50" type="text" id="name" name="name" placeholder="限制为1-50个字" value="${taskCustom.name}" validate="{required:true,maxlength:50}" />
					<span style="color:red;display:none;" class="name">最多可输入50个中文！</span>
				</td>
           	</tr>
           	<tr>
				<td class="title">封面图<span class="required">*</span></td>
				<td id="industryLicense_viewer">
					<div><img id="coverPicImg" style="width: 300px;height: 100px" alt="" src="${pageContext.request.contextPath}/file_servelt${taskCustom.coverPic}"></div>
	    			<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;width: 300px;" type="file" id="coverPicFile" name="file" data_value="industryLicense_viewer" onchange="ajaxFileUploadImg('coverPicFile');" />
	    				<a href="javascript:void(0);" style="width:30%;">上传图片(图片尺寸：1242*414px ,格式：jpg、png)</a>
	    			</div>
	    			<input id="coverPic" name="coverPic" type="hidden" value="${taskCustom.coverPic}">
				</td>
			</tr>
           	<tr>
            	<td class="title" width="20%">文章简介<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:160px;" maxLength="50" type="text" id="content" name="content" placeholder="限制为1-50个字" value="${taskCustom.content}" validate="{required:true,maxlength:50}" />
					<span style="color:red;display:none;" class="content">最多可输入50个中文！</span>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">发送方式<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<label style="margin-right: 10px;">
						<input type="radio" name="sendMode" value="0" <c:if test="${taskCustom.sendMode == '0' }">checked="checked"</c:if> <c:if test="${empty taskCustom.sendMode }">checked="checked"</c:if> />立即发送
					</label>
					<label>
						<input type="radio" name="sendMode" value="1" <c:if test="${taskCustom.sendMode == '1' }">checked="checked"</c:if> />定时发送
					</label>
					<div id="send-date-div" style="margin-left: 5px;display: inline-block;<c:if test='${empty taskCustom.sendMode or taskCustom.sendMode != 1 }'>display: none;</c:if>" >
						<input type="text" id="sendDate" name="sendDate" class="dateEditor" value="<fmt:formatDate value='${taskCustom.sendDate }' pattern='yyyy-MM-dd HH:mm:ss' />" style="width: 135px;" />
					</div>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">任务说明</td>
				<td align="left" class="l-table-edit-td" >
					<textarea rows="8" cols="60" maxLength="100" id="taskExplain" name="taskExplain" placeholder="请输入任务说明，限制为0-100个字" validate="{maxlength:100}" >${taskCustom.taskExplain }</textarea>
					<span style="color:red;display:none;" class="taskExplain">最多可输入100个中文！</span>
				</td>
           	</tr>
           	<tr>
           		<td class="title" width="20%">用户ID<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<table>
						<tr>
			            	<td class="title" width="20%">导入文件</td>
							<td align="left" class="l-table-edit-td" >
								<div style="float: left;height: 30px;">
				    			<input style="position:absolute; opacity:0;" type="file" id="filePath" name="filePath" onchange="taskFilePathFun();" />
				    				<input type="button" style="width: 60px;" value="导入文件">
				    				<span style="margin-left: 10px;" id="filePath-name" onclick="downLoadUserCodeExcel();">${taskCustom.filePath }</span>
				    			</div>
								</br></br><span style="color: red;" >注意：请上传.xls或.xlsx文件</span>
							</td>
			           	</tr>
			           	<tr>
			            	<td class="title" width="20%">手工输入</td>
							<td align="left" class="l-table-edit-td" >
								<textarea rows="8" cols="60" id="sendValues" placeholder="换行隔开" name="sendValues" >${sendValues}</textarea>
							</td>
			           	</tr>
						<tr>
			            	<td class="title" width="20%">选择分组</td>
							<td align="left" class="l-table-edit-td" >
								<input type="button" style="width: 50px;" onclick="addMemberLabelGroup();" value="添加">
								<div id="addMemberLabelGroup-div" >
									<c:forEach  var="memberLabelGroup" items="${memberLabelGroups}">
										<p style="margin: 5px 0px;">
										<a class="labelGroupId" href="javascript:void(0);" onclick="memberView(${memberLabelGroup.id});" >${memberLabelGroup.labelGroupName} <fmt:formatDate value="${memberLabelGroup.updateDate }" pattern="yyyy-MM-dd"/></a>
										<input type="button" style="margin-left:15px;color: #FFFFFF;background-color: #D9534F;border: none;width: 25px;height: 20px;border-radius: 3px;" onclick="delMemberLabelGroup(this);" placeholder="${memberLabelGroup.id}" value="-">
										</p>
									</c:forEach>
								</div>
								<input type="hidden" id="labelGroupIds"  name="labelGroupIds" value="${taskCustom.labelGroupIds}"> 
							</td>
			           	</tr>
					</table>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>