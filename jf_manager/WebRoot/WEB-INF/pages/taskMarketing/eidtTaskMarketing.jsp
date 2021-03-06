<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
	var submitFlag = true;
	$(function(){
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format: "yyyy-MM-dd hh:mm:ss",
			labelAlign : 'left',
			width : 160
		});
		
		$("[name='taskSendMode']").change(function(){
			if($(this).val() == '0' ) {
				$("#send-date-div").css("display", "none");
				$("#taskSendDate").val("");
			}else {
				$("#send-date-div").css("display", "inline-block");
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
	        	var taskSendMode = $("input[name='taskSendMode']:checked").val();
	        	if(taskSendMode == '1' ) {
	        		if($("#taskSendDate").val() == '' ) {
		        		commUtil.alertError("定时时间不能为空！");
		        		return;
	        		}
	        		var date = new Date();
					var startDate = date.format("yyyy-MM-dd hh:mm");
	        		if(!compareTime(startDate, $("#taskSendDate").val())) {
						commUtil.alertError("定时时间，必须大于现在时间！");
						return;
					}
	        	}
   				var file = $("#taskFilePath").val();
	        	if(file != '' ) {
    				var exec = (/[.]/.exec(file)) ? /[^.]+$/.exec(file.toLowerCase()) : '';  
    		        if (exec != "xls" && exec != "xlsx") {  
    		        	commUtil.alertError("文件格式不对，请上传.xls或.xlsx文件！");  
    		            return;  
    		        }  
	        	}
	        	var iosContext = $("input[name='iosContext']:checked").val();
	        	var androidContext = $("input[name='androidContext']:checked").val();
	        	var bigPushDurationStatus = $("input[name='bigPushDurationStatus']:checked").val();
	        	var iosTitle = $("#iosTitle").val();
	        	var androidTitle = $("#androidTitle").val();
	        	var bigPushDuration = $("#bigPushDuration").val();
	        	var linkType = $("#linkType").val();
	        	if(iosContext == undefined && androidContext == undefined ) {
	        		commUtil.alertError("请选择目标平台!");
	        		return;
	        	}else {
	        		if(iosContext != undefined && iosTitle == '' ) {
	        			commUtil.alertError("请填写iOS标题！");
	        			return;
	        		}
	        		if(androidContext != undefined && androidTitle == '' ) {
	        			commUtil.alertError("请填写安卓标题！");
	        			return;
	        		}
	        	}
	        	if(linkType == '1' || linkType == '2' || linkType == '4' || linkType == '5' || linkType == '6' 
	        			|| linkType == '7' || linkType == '107' || linkType == '108' ) {
	    			if($("#linkUrl").val() == '' ) {
	    				commUtil.alertError("请填写ID/remark地址!");
		        		return;
	    			}
	    		}
	        	
	        	console.log(bigPushDurationStatus);
	        	
	        	if(bigPushDurationStatus != undefined && bigPushDuration == '' ) {
	        		commUtil.alertError("请填写定速推送分钟！");
        			return;
	        	}else if(bigPushDurationStatus != undefined  && /^[1-9]\d*$/.test(bigPushDuration) ) {
	        		if(bigPushDuration > 1400 ) {
		        		commUtil.alertError("请填写定速推送1~1400分钟！");
	        			return;
	        		}
	        	}else if(bigPushDurationStatus != undefined  && !/^[1-9]\d*$/.test(bigPushDuration) ){
	        		commUtil.alertError("填写定速推送格式不正确！");
        			return;
	        	}
	        	if(submitFlag){
		        	var taskLabelGroupIds = '';
		        	$(".labelGroupId").each(function(){
	        			if(taskLabelGroupIds != '' ) {
		        			taskLabelGroupIds += ',';
		        		}
		        		taskLabelGroupIds += $(this).attr("label-group-id");
		        	});
		        	$("#taskLabelGroupIds").val(taskLabelGroupIds);
	        		submitFlag = false;
	        		form.submit();
	        	}else {
					commUtil.alertError("正在处理中，请耐心等待！");
				}
	        }
	    }); 
		
	});
	
	//添加分组
	function addMemberLabelGroup() {
		var memberLabelGroupIds = '';
    	$(".labelGroupId").each(function(){
    		if(memberLabelGroupIds != '' ) {
    			memberLabelGroupIds += ',';
    		}
    		memberLabelGroupIds += $(this).attr("label-group-id");
    	});
		$.ligerDialog.open({
 			height: $(window).height() - 50,
 			width: $(window).width() - 50,
 			title: "选择分组",
 			name: "INSERT_WINDOW",
 			url: "${pageContext.request.contextPath}/memberLabelGroup/memberLabelGroupListManager.shtml?memberLabelGroupIds="+memberLabelGroupIds,
 			showMax: true,
 			showToggle: false,
 			showMin: false,
 			isResize: true,
 			slide: false,
 			data: null
 		});
	}
	
	function appendMemberLabelGroup(labelGroupId, labelGroupName, updateDate) {
		var html = [];
		html.push('<p style="margin: 5px 0px;display: flex;">');
		html.push('<a href="javascript:void(0);" onclick="memberView('+labelGroupId+');" >'+labelGroupName+'&nbsp;'+updateDate+'</a>');
		html.push('<input type="button" class="labelGroupId" label-group-id="'+labelGroupId+'" style="margin-left:20px;color: #FFFFFF;background-color: #D9534F;border: none;width: 25px;height: 20px;border-radius: 3px;" onclick="delMemberLabelGroup(this);" value="-">');
		html.push('</p>');
		$("#addMemberLabelGroup-div").append(html.join(""));
	}
	
	//删除分组
	function delMemberLabelGroup(labelGroup) {
		$(labelGroup).parent("p").remove();
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
	
	//导入文件
	function taskFilePathFun() {
		var file = document.getElementById("taskFilePath").files[0];
		var exec = (/[.]/.exec(file.name)) ? /[^.]+$/.exec(file.name.toLowerCase()) : '';  
        if (exec != "xls" && exec != "xlsx") {  
        	commUtil.alertError("文件格式不对，请上传.xls或.xlsx文件!");  
            return;  
        }else {
        	$("#taskFilePath-name").html(file.name);
        }
	}
	
	//目标平台
	function contextFun(checked, context) {
		if(context == '0' ) {
			if(checked ) {
				if($("#iosContext-1")[0].checked ) {
					$("#iosContext-1").click();
				}
				$("#iosTitle-tr").show();
			}else {
				if(!$("#iosContext-1")[0].checked ) {
					$("#iosTitle-tr").hide();
					$("#iosTitle").val("");
				}
			}
		}else if(context == '1'){
			if(checked ){
				$("#androidTitle-tr").show();
			}else {
				$("#androidTitle-tr").hide();
				$("#androidTitle").val("");
			}
		}else if(context == '2'){
			if(checked ) {
				if($("#iosContext-0")[0].checked ) {
					$("#iosContext-0").click();
				}
				$("#iosTitle-tr").show();
			}else {
				if(!$("#iosContext-0")[0].checked ) {
					$("#iosTitle-tr").hide();
					$("#iosTitle").val("");
				}
			}
		}
	}
	
	//定速推送
	function bigPushDurationFun(checked) {
		if(checked ) {
			$("#bigPushDuration-span").show();
		}else {
			$("#bigPushDuration-span").hide();
			$("#bigPushDuration").val("");
		}
	}

	//链接栏目
	function linkTypeFun(val) {
		$("#linkUrl").val("");
		if(val == '3' || val == '101' || val == '102' || val == '103' || val == '104' || val == '105' || val == '106' || val == '109' ) {
			$("#linkUrl-span").hide();
		}else {
			$("#linkUrl-span").show();
		}
	}
	
	function taskContentFun() {
		var taskContent = $("#taskContent").val();
		$("#taskContent-span").html("温馨提示：共"+taskContent.length+"个字");
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
	
</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/taskMarketing/eidtTaskMarketing.shtml" enctype="multipart/form-data" >
		<input type="hidden" id="id" name="id" value="${taskMarketingCustom.id }" />
		<input type="hidden" id="fileName" name="fileName" value="${taskMarketingCustom.taskFilePath }" />
		<input type="hidden" id="taskLabelGroupIds" name="taskLabelGroupIds" value="" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">任务名称<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:160px;" type="text" id="taskName" name="taskName" value="${taskMarketingCustom.taskName }" validate="{required:true,maxlength:50}" placeholder="限制为1-50个字" />
				</td>
           	</tr>
           	<tr>
           		<td class="title" width="20%">目标平台<span class="required">*</span></td>
           		<td align="left" class="l-table-edit-td" >
           			<label>
           				<input type="checkbox" id="iosContext-0" name="iosContext" value="1" <c:if test="${taskMarketingCustom.iosContext == '1' }">checked="checked"</c:if> onchange="contextFun(this.checked, 0);" >iOS生产环境
           			</label>
           			<label style="margin-left: 20px;">
           				<input type="checkbox" id="androidContext-0" name="androidContext" value="1" <c:if test="${taskMarketingCustom.androidContext == '1' }">checked="checked"</c:if> onchange="contextFun(this.checked, 1);" >Android
           			</label>
           			<label style="margin-left: 20px;">
           				<input type="checkbox" id="iosContext-1" name="iosContext" value="0" <c:if test="${taskMarketingCustom.iosContext == '0' }">checked="checked"</c:if> onchange="contextFun(this.checked, 2);" >iOS测试环境
           			</label>
           		</td>
           	</tr>
           	<tr id="iosTitle-tr" style="<c:if test="${empty taskMarketingCustom.iosContext }">display: none;</c:if>" >
           		<td class="title" width="20%">iOS标题<span class="required">*</span></td>
           		<td align="left" class="l-table-edit-td" >
					<input style="width:435px;" type="text" id="iosTitle" name="iosTitle" value="${taskMarketingCustom.iosTitle }" validate="{maxlength:50}" placeholder="限制为1-50个字" />
				</td>
           	</tr>
           	<tr id="androidTitle-tr" style="<c:if test="${empty taskMarketingCustom.androidContext}">display: none;</c:if>" >
           		<td class="title" width="20%">安卓标题<span class="required">*</span></td>
           		<td align="left" class="l-table-edit-td" >
					<input style="width:435px;" type="text" id="androidTitle" name="androidTitle" value="${taskMarketingCustom.androidTitle }" validate="{maxlength:50}" placeholder="限制为1-50个字" />
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">消息内容<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<textarea rows="8" cols="60" id="taskContent" name="taskContent" placeholder="请输入发送内容" validate="{required:true,maxlength:200}" onchange="taskContentFun();" >${taskMarketingCustom.taskContent }</textarea>
					</br><span style="color: red;" id="taskContent-span" >温馨提示：共${fn:length(taskMarketingCustom.taskContent) }个字</span>
				</td>
           	</tr>
           	<tr>
           		<td class="title" width="20%">链接栏目<span class="required">*</span></td>
           		<td align="left" class="l-table-edit-td" >
           			<select id="linkType" name="linkType" style="width: 135px;" validate="{required:true}" onchange="linkTypeFun(this.value);" >
						<option value="">请选择...</option>
						<c:forEach items="${taskMarketingLinkTypeList }" var="taskMarketingLinkType">
							<option value="${taskMarketingLinkType.statusValue }" price-value="${taskMarketingLinkType.remark }" <c:if test="${taskMarketingLinkType.statusValue == taskMarketingCustom.linkType }">selected</c:if> >${taskMarketingLinkType.statusDesc }</option>
						</c:forEach>
					</select>
					<span id="linkUrl-span" style="margin-left: 30px; 
						<c:if test="${empty taskMarketingCustom.linkType || taskMarketingCustom.linkType == '3' || taskMarketingCustom.linkType == '101' 
						|| taskMarketingCustom.linkType == '102' || taskMarketingCustom.linkType == '103' || taskMarketingCustom.linkType == '104' 
						|| taskMarketingCustom.linkType == '105' || taskMarketingCustom.linkType == '106' || taskMarketingCustom.linkType == '109' }">display: none;</c:if>
					" >
						ID/remark地址&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="linkUrl" name="linkUrl" value="${taskMarketingCustom.linkUrl }" style="width: 400px;" />
					</span>
           		</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">发送方式<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<label style="margin-right: 10px;">
						<input type="radio" name="taskSendMode" value="0" <c:if test="${taskMarketingCustom.taskSendMode == '0' }">checked="checked"</c:if> <c:if test="${empty taskMarketingCustom.taskSendMode }">checked="checked"</c:if> />立即发送
					</label>
					<label>
						<input type="radio" name="taskSendMode" value="1" <c:if test="${taskMarketingCustom.taskSendMode == '1' }">checked="checked"</c:if> />定时发送
					</label>
					<div id="send-date-div" style="margin-left: 5px;display: inline-block;<c:if test='${empty taskMarketingCustom.taskSendMode or taskMarketingCustom.taskSendMode != 1 }'>display: none;</c:if>" >
						<input type="text" id="taskSendDate" name="taskSendDate" class="dateEditor" value="<fmt:formatDate value='${taskMarketingCustom.taskSendDate }' pattern='yyyy-MM-dd HH:mm:ss' />" style="width: 135px;" />
					</div>
				</td>
           	</tr>
           	<tr>
           		<td class="title" width="20%">定速推送</td>
           		<td align="left" class="l-table-edit-td" >
           			<label>
           				<input type="checkbox" id="bigPushDurationStatus" name="bigPushDurationStatus" value="1" <c:if test="${not empty taskMarketingCustom.bigPushDuration }">checked="checked"</c:if> onchange="bigPushDurationFun(this.checked);" >定速推送
           			</label>
           			<span id="bigPushDuration-span" style="margin-left: 20px; <c:if test="${empty taskMarketingCustom.bigPushDuration }">display: none;</c:if>" >推送将分布在<input type="text" id="bigPushDuration" name="bigPushDuration" value="${taskMarketingCustom.bigPushDuration }" placeholder="1~1400分钟" style="width: 100px;" />分钟内完成</span>
           		</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">任务说明</td>
				<td align="left" class="l-table-edit-td" >
					<textarea rows="8" cols="60" id="taskTaskExplain" name="taskTaskExplain" placeholder="请输入任务说明，限制为0-100个字" validate="{maxlength:100}" >${taskMarketingCustom.taskTaskExplain }</textarea>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">导入文件</td>
				<td align="left" class="l-table-edit-td" >
					<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="taskFilePath" name="taskFilePath" onchange="taskFilePathFun();" />
	    				<input type="button" style="width: 60px;" value="导入文件">
	    				<span style="margin-left: 10px;" id="taskFilePath-name" >${taskMarketingCustom.taskFilePath }</span>
	    			</div>
					</br></br><span style="color: red;" >注意：请上传.xls或.xlsx文件</span>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">手工输入</td>
				<td align="left" class="l-table-edit-td" >
					<textarea rows="8" cols="60" id="taskSendValues" name="taskSendValues" placeholder="用换行隔开" >${taskMarketingCustom.taskSendValues }</textarea>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">选择分组</td>
				<td align="left" class="l-table-edit-td" >
					<input type="button" style="width: 50px;" onclick="addMemberLabelGroup();" value="添加">
					<div id="addMemberLabelGroup-div" >
						<c:forEach items="${memberLabelGroupList }" var="memberLabelGroup">
							<p style="margin: 5px 0px;display: flex;">
								<a href="javascript:void(0);" onclick="memberView(${memberLabelGroup.id });" >${memberLabelGroup.labelGroupName }&nbsp;<fmt:formatDate value="${memberLabelGroup.updateDate }" pattern="yyyy-MM-dd"/></a>
								<input type="button" class="labelGroupId" label-group-id="${memberLabelGroup.id }" style="margin-left:20px;color: #FFFFFF;background-color: #D9534F;border: none;width: 25px;height: 20px;border-radius: 3px;" onclick="delMemberLabelGroup(this);" value="-">
							</p>
						</c:forEach>
					</div>
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