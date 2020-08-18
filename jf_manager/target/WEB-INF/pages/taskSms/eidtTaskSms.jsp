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
		
		$("#taskSendChannel").change(function(){
			if($(this).val() == '' ) {
				$("#price-p").css("display", "none");
				$("#price-span").html("");
			}else {
				var price = $(this).find("option:selected").attr("price-value");
				$("#price-span").html("（单价："+price+"元/条）");
				$("#price-p").css("display", "");
				$("#price").val(price);
			}
		});
		$("[name='taskSendMode']").change(function(){
			if($(this).val() == '0' ) {
				$("#send-date-div").css("display", "none");
				$("#taskSendDate").val("");
			}else {
				$("#send-date-div").css("display", "inline-block");
			}
		});
		$("[name='isGiveCoupon']").change(function(){
			if($(this).val() == '0' ) {
				$("#coupon-id-div").css("display", "none");
				$("#couponId").val("");
			}else {
				$("#coupon-id-div").css("display", "inline-block");
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
				var couponId = $("#couponId").val();
	        	var taskSendMode = $("input[name='taskSendMode']:checked").val();
				var startDate = new Date().format("yyyy-MM-dd hh:mm:ss");
	        	if(taskSendMode == '1' ) {
	        		if($("#taskSendDate").val() == '' ) {
		        		commUtil.alertError("定时时间不能为空！");
		        		return;
	        		}
	        		if(!compareTime(startDate, $("#taskSendDate").val())) {
						commUtil.alertError("定时时间，必须大于现在时间！");
						return;
					}
					startDate =  $("#taskSendDate").val();
	        	}
				var isGiveCoupon = $("input[name='isGiveCoupon']:checked").val();
				var flag = true;
	        	if(isGiveCoupon == '1'){
					$.ajax({
						url : "${pageContext.request.contextPath}/taskSms/checkCouponId.shtml",
						type : 'POST',
						dataType : 'json',
						cache : false,
						async : false,
						data : {"couponId":couponId, "date":startDate},
						timeout : 30000,
						success : function(data) {
							if ("0000" != data.returnCode) {
								commUtil.alertError(data.returnMsg);
								flag = false;
								return false;
							}
						},
						error : function() {
							$.ligerDialog.error('操作超时，请稍后再试！');
						}
					});
				}
				if( !flag ){
					return;
				}

	        	var taskSendType = $("input[name='taskSendType']:checked").val();
	        	if(taskSendType != '2' ) {
					var file = $("#taskFilePath").val();
					if(file != '' ) {
						var exec = (/[.]/.exec(file)) ? /[^.]+$/.exec(file.toLowerCase()) : '';
						if (exec != "xls" && exec != "xlsx") {
							commUtil.alertError("文件格式不对，请上传.xls或.xlsx文件!");
							return;
						}
					}
				}
                var taskSendType = $("input[name='taskSendType']:checked").val();
	        	var sendValues = $("#taskSendValues").val();
                if( !isEmpty(sendValues) && taskSendType == "0"){
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

		<c:forEach items="${taskSendChannelList }" var="taskSendChannel">
			<c:if test="${taskSendChannel.statusValue != '1' }">
				$("#price").val(${taskSendChannel.remark });
			</c:if>
		</c:forEach>

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
	
	function taskContentFun() {
		var taskContent = $("#taskContent").val().trim();
		var len = 4;
		if(taskContent.length == 0 ) {
			len = 4;
		}else {
			len += Number(taskContent.length);
		}
		if(taskContent.length <= 64 ) {
			$("#taskContent-span").html("温馨提示：（含签名）共"+len+"个字，预计分1段");
		}else if(taskContent.length > 64 && taskContent.length <= 132) {
			$("#taskContent-span").html("温馨提示：（含签名）共"+len+"个字，预计分2段");
		}else {
			$("#taskContent-span").html("温馨提示：（含签名）共"+len+"个字，预计分3段");
		}
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

	function updateTaskSendType(val) {
		if(val == '2' ) {
			$("#task-file-path-tr").hide();
			$("#task-send-values-tr").hide();
			$("#label-group-id-tr").hide();
		}else {
			$("#task-file-path-tr").show();
			$("#task-send-values-tr").show();
			$("#label-group-id-tr").show();
		}
	}
	function checkCouponId(){
		let couponId = $("#couponId").val();
		let taskSendMode = $("input[name='taskSendMode']:checked").val();
		let date = new Date().format("yyyy-MM-dd hh:mm:ss");
		if(taskSendMode == '1' ) {
			if($("#taskSendDate").val() == '' ) {
				commUtil.alertError("定时时间不能为空！");
				return;
			}
			if(!compareTime(date, $("#taskSendDate").val())) {
				commUtil.alertError("定时时间，必须大于现在时间！");
				return;
			}
			date = $("#taskSendDate").val();
		}
		if(couponId){
			$.ajax({
				url : "${pageContext.request.contextPath}/taskSms/checkCouponId.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"couponId":couponId, "date":date},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("优惠券ID可用");
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			commUtil.alertError("请输入优惠券ID");
			return ;
		}
	}
	
</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/taskSms/eidtTaskSms.shtml" enctype="multipart/form-data" >
		<input type="hidden" id="id" name="id" value="${taskSmsCustom.id }" />
		<input type="hidden" id="fileName" name="fileName" value="${taskSmsCustom.taskFilePath }" />
		<input type="hidden" id="price" name="price" value="" />
		<input type="hidden" id="taskLabelGroupIds" name="taskLabelGroupIds" value="" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%"> 发送渠道<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select id="taskSendChannel" name="taskSendChannel" style="width: 135px;" validate="{required:true}" >
						<option value="">请选择...</option>
						<c:forEach items="${taskSendChannelList }" var="taskSendChannel">
							<c:if test="${taskSendChannel.statusValue != '1' }">
								<option value="${taskSendChannel.statusValue }" price-value="${taskSendChannel.remark }" <c:if test="${taskSendChannel.statusValue == taskSmsCustom.taskSendChannel }">selected</c:if> >${taskSendChannel.statusDesc }</option>
							</c:if>
						</c:forEach>
					</select>
					<c:if test="${empty taskSmsCustom.taskSendChannel }">
						<p id="price-p" style="display: none;" ><span style="color: red;" id="price-span" ></span></p>
					</c:if>
					<c:if test="${not empty taskSmsCustom.taskSendChannel }">
						<c:forEach items="${taskSendChannelList }" var="taskSendChannel">
							<c:if test="${taskSendChannel.statusValue == taskSmsCustom.taskSendChannel }">
								<p id="price-p" ><span style="color: red;" id="price-span" >（单价：${taskSendChannel.remark }元/条）</span></p>
							</c:if>
						</c:forEach>
					</c:if>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">任务名称<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:435px;" type="text" id="taskName" name="taskName" value="${taskSmsCustom.taskName }" validate="{required:true,maxlength:50}" placeholder="限制为1-50个字" />
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">短信内容<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<span>（信息签名：......【醒购】）</span></br>
					<textarea rows="8" cols="60" id="taskContent" name="taskContent" placeholder="请输入发送内容，限制为1-200字" validate="{required:true,maxlength:200}" onchange="taskContentFun();" >${taskSmsCustom.taskContent }</textarea>
					</br><span style="color: red;" id="taskContent-span" >
						<c:if test="${fn:length(taskSmsCustom.taskContent) >= '0' and fn:length(taskSmsCustom.taskContent) <= '64'}">温馨提示：（含签名）共${fn:length(taskSmsCustom.taskContent) + 4 }个字，预计分1段</c:if>
						<c:if test="${fn:length(taskSmsCustom.taskContent) > '64' and fn:length(taskSmsCustom.taskContent) <= '132'}">温馨提示：（含签名）共${fn:length(taskSmsCustom.taskContent) + 4 }个字，预计分2段</c:if>
						<c:if test="${fn:length(taskSmsCustom.taskContent) > '132'}">温馨提示：（含签名）共${fn:length(taskSmsCustom.taskContent) + 4 }个字，预计分3段</c:if>
					</span>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">发送方式<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<label style="margin-right: 10px;">
						<input type="radio" name="taskSendMode" value="0" <c:if test="${taskSmsCustom.taskSendMode == '0' }">checked="checked"</c:if> <c:if test="${empty taskSmsCustom.taskSendMode }">checked="checked"</c:if> />立即发送
					</label>
					<label>
						<input type="radio" name="taskSendMode" value="1" <c:if test="${taskSmsCustom.taskSendMode == '1' }">checked="checked"</c:if> />定时发送
					</label>
					<div id="send-date-div" style="margin-left: 5px;display: inline-block;<c:if test='${empty taskSmsCustom.taskSendMode or taskSmsCustom.taskSendMode != 1 }'>display: none;</c:if>" >
						<input type="text" id="taskSendDate" name="taskSendDate" class="dateEditor" value="<fmt:formatDate value='${taskSmsCustom.taskSendDate }' pattern='yyyy-MM-dd HH:mm:ss' />" style="width: 135px;" />
					</div>
				</td>
           	</tr>
			<tr>
				<td class="title" width="20%">间隔发送条数<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select id="taskSendCount" name="taskSendCount" style="width: 135px;" >
						<option value="100000" <c:if test="${taskSmsCustom.taskSendCount == 100000 }">selected</c:if> >10万</option>
						<option value="200000" <c:if test="${taskSmsCustom.taskSendCount == 200000 }">selected</c:if> >20万</option>
						<option value="300000" <c:if test="${taskSmsCustom.taskSendCount == 300000 }">selected</c:if> >30万</option>
						<option value="400000" <c:if test="${taskSmsCustom.taskSendCount == 400000 }">selected</c:if> >40万</option>
						<option value="500000" <c:if test="${taskSmsCustom.taskSendCount == 500000 }">selected</c:if> >50万</option>
					</select>
				</td>
			</tr>
           	<tr>
            	<td class="title" width="20%">任务说明</td>
				<td align="left" class="l-table-edit-td" >
					<textarea rows="8" cols="60" id="taskTaskExplain" name="taskTaskExplain" placeholder="请输入任务说明，限制为0-100个字" validate="{maxlength:100}" >${taskSmsCustom.taskTaskExplain }</textarea>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">发送类型<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<label style="margin-right: 10px;">
						<input type="radio" name="taskSendType" value="0" onchange="updateTaskSendType(this.value);" <c:if test="${taskSmsCustom.taskSendType == '0' }">checked="checked"</c:if> <c:if test="${empty taskSmsCustom.taskSendType }">checked="checked"</c:if> />用户ID
					</label>
					<label style="margin-right: 10px;">
						<input type="radio" name="taskSendType" value="1" onchange="updateTaskSendType(this.value);" <c:if test="${taskSmsCustom.taskSendType == '1' }">checked="checked"</c:if> />手机号
					</label>
					<label>
						<input type="radio" name="taskSendType" value="2" disabled onchange="updateTaskSendType(this.value);" <c:if test="${taskSmsCustom.taskSendType == '2' }">checked="checked"</c:if> />全平台用户
					</label>
				</td>
           	</tr>
           	<tr id="task-file-path-tr" <c:if test="${taskSmsCustom.taskSendType == '2' }">style="display: none;"</c:if> >
            	<td class="title" width="20%">导入文件</td>
				<td align="left" class="l-table-edit-td" >
					<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="taskFilePath" name="taskFilePath" onchange="taskFilePathFun();" />
	    				<input type="button" style="width: 60px;" value="导入文件">
	    				<span style="margin-left: 10px;" id="taskFilePath-name" >${taskSmsCustom.taskFilePath }</span>
	    			</div>
					</br></br><span style="color: red;" >注意：请上传.xls或.xlsx文件</span>
				</td>
           	</tr>
           	<tr id="task-send-values-tr" <c:if test="${taskSmsCustom.taskSendType == '2' }">style="display: none;"</c:if> >
            	<td class="title" width="20%">手工输入</td>
				<td align="left" class="l-table-edit-td" >
					<textarea rows="8" cols="60" id="taskSendValues" name="taskSendValues" placeholder="用换行隔开" >${taskSmsCustom.taskSendValues }</textarea>
				</td>
           	</tr>
			<tr id="label-group-id-tr" <c:if test="${taskSmsCustom.taskSendType == '2' }">style="display: none;"</c:if> >
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
				<td class="title" width="20%">是否赠送优惠券<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<label style="margin-right: 10px;">
						<input type="radio" name="isGiveCoupon" value="0" <c:if test="${taskSmsCustom.isGiveCoupon != '1' }">checked="checked"</c:if> />否
					</label>
					<label>
						<input type="radio" name="isGiveCoupon" value="1" <c:if test="${taskSmsCustom.isGiveCoupon == '1' }">checked="checked"</c:if> />是
					</label>
					<div id="coupon-id-div" style="margin-left: 5px;display: inline-block;<c:if test="${ taskSmsCustom.isGiveCoupon != '1' }">display: none;</c:if>" >
						<input type="text" id="couponId" name="couponId" value="${taskSmsCustom.couponId}" style="width: 135px;" onchange="checkCouponId(this)"/>
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