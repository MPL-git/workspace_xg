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
	body {font-size: 12px;padding: 10px;}
	td input,td select{border:1px solid #AECAF0;}
	.table-title{font-size: 14px;color: #333333;font-weight: 600;}
	.title-top{padding-top:10px;padding-bottom:10px;}
	.marR10{margin-right:10px;}
	.marT10{margin-top:10px;}
	.marB05{margin-bottom:5px;}
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
	        	var activityGroupIds = '';
	    		$(".activity-group").each(function(){
	    			if(activityGroupIds != '' ) {
	    				activityGroupIds += ',';
	    			}
	    			activityGroupIds += $(this).attr("group");
	    		});
	    		if(activityGroupIds == '' ) {
	    			commUtil.alertError('请先选择推广渠道~');
	    			return;
	    		}
	    		$("#activityGroupIds").val(activityGroupIds);
	        	if(submitFlag){
	        		submitFlag = false;
	        		form.submit();
	        	}
	        }
	    }); 
		
	});
	
	// 添加活动分组
	function addSpreadActivityGroupSetDtl(){
		var channel = $("#channel").val();
		var activityGroupIds = '';
		$(".activity-group").each(function(){
			if(activityGroupIds != '' ) {
				activityGroupIds += ',';
			}
			activityGroupIds += $(this).attr("group");
		});
		$.ligerDialog.open({
		 	height: $(window).height()-100,
			width: $(window).width()-100,
			title: '添加活动组',
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/spreadActivityGroupSet/spreadActivityGroupManager.shtml?deviceType=${deviceType}&channel="+channel+"&activityGroupIds="+activityGroupIds,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	// 删除活动分组
	function delActivityGroupSetDtl(){
		$(".activityGroupSetDtl").each(function(){
			$(this).remove();
		});
	}
	
	// 移除活动分组
	function removeActivityGroupSetDtl(obj){
		$(obj).parent().parent().remove();
	}
	
	// 子窗口调用追加活动分组
	function childSpreadActivityGroupSetDtl(html){
		var ht = [];
		$.each(html, function(index, item){
			var str = '';
			if(item.isEffect == '1' ) {
				str = '有效';
			}else{
				str = '无效';
			}
			ht.push('<tr class="activityGroupSetDtl">');
			ht.push('<td class="activity-group" group="'+item.id+'" >'+item.activityGroup+'</td>');
			ht.push('<td>'+item.channelName+'</td>');
			ht.push('<td>'+str+'</td>');
			ht.push('<td>');
			ht.push('<a href="javascript:void(0);" onclick="removeActivityGroupSetDtl(this);" >【删除】</a>');
			ht.push('</td>');
			ht.push('</tr>');
		});
		$(".tr-first").parent().append(ht.join(""));
	}

</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/spreadActivityGroupSet/editSpreadActivityGroupSet.shtml" >
		<input type="hidden" id="id" name="id" value="${spreadActivityGroupSetCustom.id }" />
		<input type="hidden" id="channelOld" name="channelOld" value="${spreadActivityGroupSetCustom.channel }" />
		<input type="hidden" id="deviceType" name="deviceType" value="${deviceType }" />
		<input type="hidden" id="activityGroupIds" name="activityGroupIds" value="" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">推广渠道<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select id="channel" name="channel" style="width: 200px;" onchange="delActivityGroupSetDtl();" >
						<c:if test="${deviceType == '1'}">
							<c:forEach var="channelItem" items="${channelList}">
								<option value="${channelItem.statusValue}" <c:if test="${spreadActivityGroupSetCustom.channel == channel.statusValue }">selected</c:if>>${channelItem.statusDesc}</option>
							</c:forEach>
						</c:if>
						<c:if test="${deviceType == '2'}">
							<c:forEach var="list" items="${channelList}">
								<c:if test="${list.statusDesc == '今日头条'}">
									<option value="${list.statusValue}" <c:if test="${spreadActivityGroupSetCustom.channel == channel.statusValue }">selected</c:if> >${list.statusDesc}</option>
								</c:if>
							</c:forEach>
						</c:if>
					</select>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">活动组集合<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:200px;" type="text" id="name" name="name" value="${spreadActivityGroupSetCustom.name }" validate="{required:true,maxlength:50}" />
					</br><span class="activity-hint" >注意：标签名称不能超过50个字符</span>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">状态<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select id="status" name="status" style="width: 135px;">
						<option value="0">停用</option>
						<option value="1">启用</option>
					</select>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">备注</td>
				<td align="left" class="l-table-edit-td" >
					<textarea rows="5" cols="60" id="remarks" name="remarks" maxlength="100" validate="{maxlength:100}" >${spreadActivityGroupSetCustom.remarks } </textarea>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">活动分组</td>
				<td align="left" class="l-table-edit-td" >
					<input class="l-button" type="button" value="添加" onclick="addSpreadActivityGroupSetDtl();" /><br/>
					<div class="title-top">
						<table class="gridtable marT10">
				           	<tr class="tr-first">
				               <td class="title">活动组</td> 
				               <td class="title">所属推广渠道</td>
				               <td class="title">是否有效</td>
				               <td class="title">操作</td>
							</tr>
							<c:forEach var="spreadActivityGroupSetDtlCustom" items="${spreadActivityGroupSetDtlCustomList}"> 
						        <tr class="activityGroupSetDtl">
					               	<td class="activity-group" group="${spreadActivityGroupSetDtlCustom.activityGroupId }" >${spreadActivityGroupSetDtlCustom.activityGroupName }</td>
					               	<td>${spreadActivityGroupSetDtlCustom.channelName }</td>
					               	<td>${spreadActivityGroupSetDtlCustom.activityGroupIsEffect=='0'?'无效':'有效' }</td>
					               	<td>
					               		<a href="javascript:void(0);" onclick="removeActivityGroupSetDtl(this);" >【删除】</a>
					               	</td>
						        </tr>
					        </c:forEach>
				        </table>
					</div>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close();" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>