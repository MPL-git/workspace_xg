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
    		        	commUtil.alertError("文件格式不对，请上传.xls或.xlsx文件!");  
    		            return;  
    		        }  
	        	}
	        	if(submitFlag){
	        		submitFlag = false;
	        		form.submit();
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
	
</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="" enctype="multipart/form-data" >
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%"> 发送渠道<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select id="taskSendChannel" name="taskSendChannel" style="width: 135px;" disabled="disabled" >
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
					<span>${taskSmsCustom.taskName }</span>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">短信内容<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<span>（信息签名：......【醒购】）</span></br>
					<div style="width: 450px;margin-top: 5px;">${taskSmsCustom.taskContent }</div>
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
						<input type="radio" name="taskSendMode" value="0" disabled="disabled" <c:if test="${taskSmsCustom.taskSendMode == '0' }">checked="checked"</c:if> <c:if test="${empty taskSmsCustom.taskSendMode }">checked="checked"</c:if> />立即发送
					</label>
					<label>
						<input type="radio" name="taskSendMode" value="1" disabled="disabled" <c:if test="${taskSmsCustom.taskSendMode == '1' }">checked="checked"</c:if> />定时发送
					</label>
					<div id="send-date-div" style="margin-left: 5px;display: inline-block;<c:if test='${empty taskSmsCustom.taskSendMode or taskSmsCustom.taskSendMode != 1 }'>display: none;</c:if>" >
						<input type="text" id="taskSendDate" name="taskSendDate" disabled="disabled" value="<fmt:formatDate value='${taskSmsCustom.taskSendDate }' pattern='yyyy-MM-dd HH:mm:ss' />" style="width: 135px;" />
					</div>
				</td>
           	</tr>
			<tr>
				<td class="title" width="20%">间隔发送条数<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select id="taskSendCount" name="taskSendCount" style="width: 135px;" disabled="disabled" >
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
					<div style="width: 450px;">${taskSmsCustom.taskTaskExplain }</div>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">发送类型<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<label style="margin-right: 10px;">
						<input type="radio" name="taskSendType" value="0" disabled="disabled" <c:if test="${taskSmsCustom.taskSendType == '0' }">checked="checked"</c:if> <c:if test="${empty taskSmsCustom.taskSendType }">checked="checked"</c:if> />用户ID
					</label>
					<label style="margin-right: 10px;">
						<input type="radio" name="taskSendType" value="1" disabled="disabled" <c:if test="${taskSmsCustom.taskSendType == '1' }">checked="checked"</c:if> />手机号
					</label>
					<label>
						<input type="radio" name="taskSendType" value="2" disabled="disabled" <c:if test="${taskSmsCustom.taskSendType == '2' }">checked="checked"</c:if> />全平台用户
					</label>
				</td>
           	</tr>
			<c:if test="${taskSmsCustom.taskSendType != '2' }">
				<tr>
					<td class="title" width="20%">导入文件</td>
					<td align="left" class="l-table-edit-td" >
						<c:if test="${not empty taskSmsCustom.taskFilePath }">
							<a href="javascript:void(0);" onclick="downloadFile('${taskSmsCustom.taskFilePath }');" >${taskSmsCustom.taskFilePath }</a>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="title" width="20%">手工输入</td>
					<td align="left" class="l-table-edit-td" >
						<textarea rows="8" cols="60" id="taskSendValues" name="taskSendValues" placeholder="用换行隔开" disabled="disabled" >${taskSmsCustom.taskSendValues } </textarea>
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
							<c:if test="${not empty taskSmsCustom.sendMemberCount }">
								<span style="color: red;">（共${taskSmsCustom.sendMemberCount }个发送用户，预估成本：${taskSmsCustom.totalSendAmount }元）</span>
							</c:if>
						</div>
					</td>
				</tr>
			</c:if>
           	<tr>
       			<td class="title" width="20%">创建人</td>
       			<td align="left" class="l-table-edit-td" >${taskSmsCustom.createStaffName }</td>
       		</tr>	
      		<tr>
       			<td class="title" width="20%">创建时间</td>
       			<td align="left" class="l-table-edit-td" >
       				<fmt:formatDate value='${taskSmsCustom.createDate }' pattern='yyyy-MM-dd HH:mm:ss' />
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
           	<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>