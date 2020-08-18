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
	        	var status = $("input[name='status']:checked").val();
        		var remarks = $("#remarks").val();
        		if(status == '5' || status == '6' ) {
        			if(remarks == '') {
	        			commUtil.alertError("请填写驳回理由！");
	        			return
        			}else if(remarks.length > 100) {
        				commUtil.alertError("驳回理由，限制为1-100个字！");
        				return
        			}
        		}
	        	if(submitFlag){
	        		$.ligerDialog.confirm("是否确定执行？", function (yes) {
	        			if (yes) {
	        			   submitFlag = false;
	    	        	   form.submit();					
						}       			
	        		});
	        	}else {
					commUtil.alertError("正在处理中，请耐心等待！");
				}
	        }
	    }); 
		
	});
	
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
	
	//审核
	function updateStatus(val) {
		if(val == '2') {
			$("#remarks-tr").attr("style", "display: none;");
			$("#remarks").html("");
		}else {
			$("#remarks-tr").attr("style", "");
		}
	}
	
	//下载文件
	function downloadFile(taskFilePath) {
		$.ligerDialog.confirm("请确认下载文件！", function (status){  
            if(status==true){
           		window.location.href="${pageContext.request.contextPath}/file_servelt"+taskFilePath;
            }
        });
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
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/taskMarketing/auditOrApproveTask.shtml" >
		<input type="hidden" id="taskId" name="taskId" value="${taskMarketingCustom.taskId }" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">任务名称<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:160px;" type="text" id="taskName" name="taskName" value="${taskMarketingCustom.taskName }" placeholder="限制为1-50个字" disabled="disabled" />
				</td>
           	</tr>
           	<tr>
           		<td class="title" width="20%">目标平台<span class="required">*</span></td>
           		<td align="left" class="l-table-edit-td" >
           			<label>
           				<input type="checkbox" id="iosContext-0" name="iosContext" value="0" disabled="disabled" <c:if test="${taskMarketingCustom.iosContext == '1' }">checked="checked"</c:if> onchange="contextFun(this.checked, 0);" >iOS生产环境
           			</label>
           			<label style="margin-left: 20px;">
           				<input type="checkbox" id="androidContext-0" name="androidContext" value="0" disabled="disabled" <c:if test="${taskMarketingCustom.androidContext == '1' }">checked="checked"</c:if> onchange="contextFun(this.checked, 1);" >Android
           			</label>
           			<label style="margin-left: 20px;">
           				<input type="checkbox" id="iosContext-1" name="iosContext" value="1" disabled="disabled" <c:if test="${taskMarketingCustom.iosContext == '0' }">checked="checked"</c:if> onchange="contextFun(this.checked, 2);" >iOS测试环境
           			</label>
           		</td>
           	</tr>
           	<tr id="iosTitle-tr" style="<c:if test="${empty taskMarketingCustom.iosContext }">display: none;</c:if>" >
           		<td class="title" width="20%">iOS标题<span class="required">*</span></td>
           		<td align="left" class="l-table-edit-td" >
					<input style="width:435px;" type="text" id="iosTitle" name="iosTitle" disabled="disabled" value="${taskMarketingCustom.iosTitle }" placeholder="限制为1-50个字" />
				</td>
           	</tr>
           	<tr id="androidTitle-tr" style="<c:if test="${empty taskMarketingCustom.androidContext }">display: none;</c:if>" >
           		<td class="title" width="20%">安卓标题<span class="required">*</span></td>
           		<td align="left" class="l-table-edit-td" >
					<input style="width:435px;" type="text" id="androidTitle" name="androidTitle" disabled="disabled" value="${taskMarketingCustom.androidTitle }" placeholder="限制为1-50个字" />
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">消息内容<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<textarea rows="8" cols="60" id="taskContent" name="taskContent" placeholder="请输入发送内容" disabled="disabled" onchange="taskContentFun();" >${taskMarketingCustom.taskContent }</textarea>
					</br><span style="color: red;" id="taskContent-span" >温馨提示：共${fn:length(taskMarketingCustom.taskContent) }个字</span>
				</td>
           	</tr>
           	<tr>
           		<td class="title" width="20%">链接栏目<span class="required">*</span></td>
           		<td align="left" class="l-table-edit-td" >
           			<select id="linkType" name="linkType" style="width: 135px;" disabled="disabled" onchange="linkTypeFun(this.value);" >
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
						ID/remark地址&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="linkUrl" name="linkUrl" disabled="disabled" value="${taskMarketingCustom.linkUrl }" style="width: 400px;" />
					</span>
           		</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">发送方式<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<label style="margin-right: 10px;">
						<input type="radio" name="taskSendMode" value="0" disabled="disabled" <c:if test="${taskMarketingCustom.taskSendMode == '0' }">checked="checked"</c:if> <c:if test="${empty taskMarketingCustom.taskSendMode }">checked="checked"</c:if> />立即发送
					</label>
					<label>
						<input type="radio" name="taskSendMode" value="1" disabled="disabled" <c:if test="${taskMarketingCustom.taskSendMode == '1' }">checked="checked"</c:if> />定时发送
					</label>
					<div id="send-date-div" style="margin-left: 5px;display: inline-block;<c:if test='${empty taskMarketingCustom.taskSendMode or taskMarketingCustom.taskSendMode != 1 }'>display: none;</c:if>" >
						<input type="text" id="taskSendDate" name="taskSendDate" disabled="disabled" value="<fmt:formatDate value='${taskMarketingCustom.taskSendDate }' pattern='yyyy-MM-dd HH:mm:ss' />" style="width: 135px;" />
					</div>
				</td>
           	</tr>
           	<tr>
           		<td class="title" width="20%">定速推送</td>
           		<td align="left" class="l-table-edit-td" >
           			<label>
           				<input type="checkbox" id="bigPushDurationStatus" name="bigPushDurationStatus" value="1" disabled="disabled" <c:if test="${not empty taskMarketingCustom.bigPushDuration }">checked="checked"</c:if> onchange="bigPushDurationFun(this.checked);" >定速推送
           			</label>
           			<span id="bigPushDuration-span" style="margin-left: 20px; <c:if test="${empty taskMarketingCustom.bigPushDuration }">display: none;</c:if>" >推送将分布在<input type="text" id="bigPushDuration" name="bigPushDuration" disabled="disabled" value="${taskMarketingCustom.bigPushDuration }" placeholder="1~1400分钟" style="width: 100px;" />分钟内完成</span>
           		</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">任务说明</td>
				<td align="left" class="l-table-edit-td" >
					<textarea rows="8" cols="60" id="taskTaskExplain" name="taskTaskExplain" placeholder="请输入任务说明，限制为0-100个字" disabled="disabled" >${taskMarketingCustom.taskTaskExplain } </textarea>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">导入文件</td>
				<td align="left" class="l-table-edit-td" >
					<c:if test="${not empty taskMarketingCustom.taskFilePath }">
						<a href="javascript:void(0);" onclick="downloadFile('${taskMarketingCustom.taskFilePath }');" >${taskMarketingCustom.taskFilePath }</a>
					</c:if>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">手工输入</td>
				<td align="left" class="l-table-edit-td" >
					<textarea rows="8" cols="60" id="taskSendValues" name="taskSendValues" placeholder="用换行隔开" disabled="disabled" >${taskMarketingCustom.taskSendValues } </textarea>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">选择分组</td>
				<td align="left" class="l-table-edit-td" >
					<div id="addMemberLabelGroup-div" >
						<c:forEach items="${memberLabelGroupList }" var="memberLabelGroup">
							<p style="margin: 5px 0px;display: flex;">
								<a href="javascript:void(0);" onclick="memberView(${memberLabelGroup.id });" >${memberLabelGroup.labelGroupName }&nbsp;<fmt:formatDate value="${memberLabelGroup.updateDate }" pattern="yyyy-MM-dd"/></a>
							</p>
						</c:forEach>
					</div>
				</td>
           	</tr>
           	<tr>
       			<td class="title" width="20%">创建人</td>
       			<td align="left" class="l-table-edit-td" >${taskMarketingCustom.createStaffName }</td>
       		</tr>	
      		<tr>
       			<td class="title" width="20%">创建时间</td>
       			<td align="left" class="l-table-edit-td" >
       				<fmt:formatDate value='${taskMarketingCustom.createDate }' pattern='yyyy-MM-dd HH:mm:ss' />
       			</td>
       		</tr>
           	<c:forEach items="${taskLogCustomList }" var="taskLogCustom">
           		<c:if test="${taskLogCustom.operatorType == '1' }">
           			<tr>
	           			<td class="title" width="20%">审核信息</td>
	           			<td align="left" class="l-table-edit-td" >
	           				<table class="gridtable marT10">
					           	<tr>
					               <td class="title">审核结果</td> 
					               <td class="title">驳回理由</td>
					               <td class="title">审核人</td>
					               <td class="title">审核时间</td>
								</tr>
						        <tr>
					               	<td>${taskLogCustom.statusDesc }</td>
					               	<td>${taskLogCustom.remarks }</td>
					               	<td>${taskLogCustom.createName }</td>
					               	<td><fmt:formatDate value='${taskLogCustom.createDate }' pattern='yyyy-MM-dd HH:mm:ss' /></td>
						        </tr>
					        </table>
	           			</td>
	           		</tr>
           		</c:if>
           		<c:if test="${taskLogCustom.operatorType == '2' }">
           			<tr>
	           			<td class="title" width="20%">审批信息</td>
	           			<td align="left" class="l-table-edit-td" >
	           				<table class="gridtable marT10">
					           	<tr>
					               <td class="title">审批结果</td> 
					               <td class="title">驳回理由</td>
					               <td class="title">审批人</td>
					               <td class="title">审批时间</td>
								</tr>
						        <tr>
					               	<td>${taskLogCustom.statusDesc }</td>
					               	<td>${taskLogCustom.remarks }</td>
					               	<td>${taskLogCustom.createName }</td>
					               	<td><fmt:formatDate value='${taskLogCustom.createDate }' pattern='yyyy-MM-dd HH:mm:ss' /></td>
						        </tr>
					        </table>
	           			</td>
	           		</tr>
           		</c:if>
           		<c:if test="${taskLogCustom.operatorType == '3' }">
           			<tr>
	           			<td class="title" width="20%">执行信息</td>
	           			<td align="left" class="l-table-edit-td" >
	           				<table class="gridtable marT10">
					           	<tr>
					               <td class="title">执行结果</td> 
					               <td class="title">执行人</td>
					               <td class="title">执行时间</td>
								</tr>
						        <tr>
					               	<td>${taskLogCustom.statusDesc }</td>
					               	<td>${taskLogCustom.createName }</td>
					               	<td><fmt:formatDate value='${taskLogCustom.createDate }' pattern='yyyy-MM-dd HH:mm:ss' /></td>
						        </tr>
					        </table>
	           			</td>
	           		</tr>
           		</c:if>
           		<c:if test="${taskLogCustom.operatorType == '4' }">
           			<tr>
	           			<td class="title" width="20%">取消人</td>
	           			<td align="left" class="l-table-edit-td" >${taskLogCustom.createName }</td>
	           		</tr>	
           			<tr>
	           			<td class="title" width="20%">取消时间</td>
	           			<td align="left" class="l-table-edit-td" >
	           				<fmt:formatDate value='${taskLogCustom.createDate }' pattern='yyyy-MM-dd HH:mm:ss' />
	           			</td>
	           		</tr>	
           		</c:if>
           	</c:forEach>
           	
           	<c:if test="${taskMarketingCustom.taskStatus == '1' }">
           		<tr>
	           		<td class="title" width="20%">审核结果<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<label style="margin-right: 10px;">
							<input type="radio" name="status" value="2" checked="checked" onchange="updateStatus(this.value);" />通过
						</label>
						<label>
							<input type="radio" name="status" value="5" onchange="updateStatus(this.value);" />驳回
						</label>
					</td>
	           	</tr>
	           	<tr id="remarks-tr" style="display: none;">
	           		<td class="title" width="20%">驳回理由<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<textarea rows="8" cols="60" id="remarks" name="remarks" ></textarea>
					</td>
	           	</tr>
           	</c:if>
           	<c:if test="${taskMarketingCustom.taskStatus == '2' }">
           		<tr>
	           		<td class="title" width="20%">审批结果<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<label style="margin-right: 10px;">
							<input type="radio" name="status" value="3" checked="checked" onchange="updateStatus(this.value);" />通过
						</label>
						<label>
							<input type="radio" name="status" value="6" onchange="updateStatus(this.value);" />驳回
						</label>
					</td>
	           	</tr>
	           	<tr id="remarks-tr" style="display: none;">
	           		<td class="title" width="20%">驳回理由<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<textarea rows="8" cols="60" id="remarks" name="remarks" ></textarea>
					</td>
	           	</tr>
           	</c:if>
           	<c:if test="${taskMarketingCustom.taskStatus == '3' }">
           		<tr>
	           		<td class="title" width="20%">执行结果<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<label style="margin-right: 10px;">
							<input type="radio" name="status" value="4" checked="checked" />立即执行
						</label>
					</td>
	           	</tr>
           	</c:if>
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