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
	        	resultFun();
	        	var homeScore = $("#homeScore").val();
	    		var awaysScore = $("#awaysScore").val();
	    		var result = $("input[name='result']:checked").val();
	    		var teamName = "";
	    		if(result == '1') {
	    			teamName = "${sportCustom.homeTeamName }胜";
	    		}else if(result == '3') {
	    			teamName = "比赛平局";
	    		}else if(result == '2') {
	    			teamName = "${sportCustom.awayTeamName }胜";
	    		}
	    		var str = "您选择的是<span style='color:red;'>"+homeScore+":"+awaysScore+teamName+"</span>，请确认您的结算结果！";
	    		$.ligerDialog.confirm(str, function(yes) {
					 if(yes) {
						if(submitFlag){
			        		submitFlag = false;
			        		form.submit();
			        	}
					 }
				});
	    		$(".l-dialog-btn-inner:even").html("取消");
				$(".l-dialog-btn-inner:odd").html("确认");
	        }
	    }); 
		
	});

	function resultFun() {
		var homeScore = $("#homeScore").val();
		var awaysScore = $("#awaysScore").val();
		var g = /^[0-9]\d*$/;
		if(homeScore != '' && awaysScore != '' && g.test(homeScore) && g.test(awaysScore)) {
			if(homeScore > awaysScore) {
				$("[name='result']:eq(0)").attr("checked",true);
			}else if(homeScore == awaysScore) {
				$("[name='result']:eq(1)").attr("checked",true);
			}else if(homeScore < awaysScore) {
				$("[name='result']:eq(2)").attr("checked",true);
			}
		}
	}
	
</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/sport/updateAuditStatus.shtml" >
		<input type="hidden" id="sportId" name="sportId" value="${sportCustom.id }" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">${sportCustom.homeTeamName }得分<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:160px;" type="text" id="homeScore" name="homeScore" value="" onchange="resultFun();" validate="{required:true,digits:true}" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">${sportCustom.awayTeamName }得分<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:160px;" type="text" id="awaysScore" name="awaysScore" value="" onchange="resultFun();" validate="{required:true,digits:true}" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">比赛结果<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<span style="margin-left: 10px;"><label><input type="radio" name="result" value="1" />${sportCustom.homeTeamName }胜</label></span>
					<span style="margin-left: 20px;"><label><input type="radio" name="result" value="3" />比赛平局</label></span>
					<span style="margin-left: 20px;"><label><input type="radio" name="result" value="2" />${sportCustom.awayTeamName }胜</label></span>
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