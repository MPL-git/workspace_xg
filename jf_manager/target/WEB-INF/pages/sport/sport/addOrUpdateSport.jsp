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
	        	var homeTeam = $("#homeTeam").val();
	        	var awayTeam = $("#awayTeam").val();
	        	var homeRate = $("#homeRate").val();
	        	var awaysRate = $("#awaysRate").val();
	        	var drawRate = $("#drawRate").val();
	        	var beginTime = new Date($("#beginTime").val()).getTime();
	        	if(homeTeam == awayTeam) {
	        		commUtil.alertError("主场队伍和客场队伍不能相同！");
	        		return;
	        	}
				if(!(/(^[2-9]\d*(\.[0-9]{0,2})?$)|(^[1-9]\d+(\.[0-9]{0,2})?$)|(^1\.[0-9]?([1-9]{1})$)|(^1\.[1-9]{1}([0-9]?)$)/).test(homeRate) 
	        			|| !(/(^[2-9]\d*(\.[0-9]{0,2})?$)|(^[1-9]\d+(\.[0-9]{0,2})?$)|(^1\.[0-9]?([1-9]{1})$)|(^1\.[1-9]{1}([0-9]?)$)/).test(awaysRate) 
	        			|| !(/(^[2-9]\d*(\.[0-9]{0,2})?$)|(^[1-9]\d+(\.[0-9]{0,2})?$)|(^1\.[0-9]?([1-9]{1})$)|(^1\.[1-9]{1}([0-9]?)$)/).test(drawRate)) {
					commUtil.alertError("比例，必须是一个大于1最多保留两位小数！");
					return;
				}
	        	var nowTime = new Date().getTime();
	        	if(beginTime < nowTime) {
	        		commUtil.alertError("比赛开始时间必须大于当前时间！");
	        		return;
	        	}
	        	$("#homeTeamName").val($("#homeTeam").find("option:selected").attr("teamName"));
	        	$("#awayTeamName").val($("#awayTeam").find("option:selected").attr("teamName"));
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
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/sport/addOrUpdateSport.shtml" >
		<input type="hidden" id="sportId" name="sportId" value="${sport.id }" />
		<input type="hidden" id="homeTeamName" name="homeTeamName" value="" />
		<input type="hidden" id="awayTeamName" name="awayTeamName" value="" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">比赛名称<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:160px;" type="text" id="sportName" name="sportName" value="${sport.sportName }" validate="{required:true,maxlength:20}" />
					</br><span class="activity-hint" >注意：队伍名称不能超过20个字符</span>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">主场队伍<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select id="homeTeam" name="homeTeam" style="width: 160px;" validate="{required:true}" >
						<c:forEach var="sportTeam" items="${sportTeamList }" >
							<option value="${sportTeam.id }" teamName="${sportTeam.name }" <c:if test="${sport.homeTeam == sportTeam.id }">selected</c:if> >
								${sportTeam.name }
							</option>
						</c:forEach>
					</select>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">客场队伍<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select id="awayTeam" name="awayTeam" style="width: 160px;" validate="{required:true}" >
						<c:forEach var="sportTeam" items="${sportTeamList }">
							<option value="${sportTeam.id }" teamName="${sportTeam.name }" <c:if test="${sport.awayTeam == sportTeam.id }">selected</c:if> >
								${sportTeam.name }
							</option>
						</c:forEach>
					</select>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">主场比例<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:120px;" type="text" id="homeRate" name="homeRate" value="${sport.homeRate }" validate="{required:true,maxlength:6,max:9999}" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">客场比例<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:120px;" type="text" id="awaysRate" name="awaysRate" value="${sport.awaysRate }" validate="{required:true,maxlength:6,max:9999}" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">平局比例<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:120px;" type="text" id="drawRate" name="drawRate" value="${sport.drawRate }" validate="{required:true,maxlength:6,max:9999}" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">比赛开始时间<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input type="text" id="beginTime" name="beginTime" class="dateEditor" value="<fmt:formatDate value='${sport.beginTime }' pattern='yyyy-MM-dd HH:mm:ss' />" validate="{required:true}" />
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