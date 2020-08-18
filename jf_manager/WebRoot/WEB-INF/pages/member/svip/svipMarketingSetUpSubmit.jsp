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
	var submitFlag = true;
	$(function(){
		$('#year').val(${year});

		$("#beginDate").ligerDateEditor( {
			showTime : true,
			width: 158,
			format: "yyyy-MM-dd hh:mm",
			labelAlign: 'left'
		});
		$("#endDate").ligerDateEditor( {
			showTime : true,
			width: 158,
			format: "yyyy-MM-dd hh:mm",
			labelAlign: 'left'
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
				if($("#price").val() == ''){
					$.ligerDialog.error("绑定销售SVIP价格不可为空！");
					return;
				}
				var reg = /^[1-9]\d*$/;
				if(!reg.test($("#price").val())){
					$.ligerDialog.error('销售SVIP价格须为正整数！');
					return;
				}
				if($('#beginDate').val() == '' || $('#endDate').val() == ''){
					$.ligerDialog.error('活动时间不可为空！');
					return;
				}
				var beginDate = new Date($('#beginDate').val());
				var endDate = new Date($('#endDate').val());
				if(beginDate.getTime() >= endDate.getTime()){
					$.ligerDialog.error('活动开始时间须小于结束时间！');
					return;
				}
	        	if(submitFlag){
	        		submitFlag = false;
	        		form.submit();
	        	}
	        }
	    }); 
	});
</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/svipBindProduct/svipMarketingSetUpSub.shtml" >
		<input type="hidden" id="id"  name="id" value="${id}">
		<table class="gridtable">
			<tr>
				<td class="title">绑定销售SVIP年限<span style="color:red;">*</span></td>
				<td>
					<select id="year" name="year">
						<option value="1">一年</option>
						<option value="2">两年</option>
						<option value="3">三年</option>
					</select>
				</td>
			<tr>
				<td class="title">绑定销售SVIP价格<span style="color:red;">*</span></td>
				<td>
					<input type="text" id="price" name="price" value="${price}"/>
				</td>
			</tr>
			<tr>
				<td class="title">活动时间<span style="color:red;">*</span></td>
				<td>
					<div class="l-panel-search-item" style="margin-left: 0px;">
						<input type="text" id="beginDate" name="beginDate" value="<fmt:formatDate value='${beginDate}' pattern='yyyy-MM-dd HH:mm'/>" style="width: 135px;"/>
					</div>
					<div class="l-panel-search-item" style="margin-left: 5px;margin-right: 5px;" >—</div>
					<div class="l-panel-search-item">
						<input type="text" id="endDate" name="endDate" value="<fmt:formatDate value='${endDate}' pattern='yyyy-MM-dd HH:mm'/>" style="width: 135px;"/>
					</div>
				</td>
			</tr>
			</tr>
			<tr>
            	<td class="title">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" id="confirm" value="提交"/> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>