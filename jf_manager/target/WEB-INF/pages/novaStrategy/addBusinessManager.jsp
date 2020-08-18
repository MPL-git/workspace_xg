<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
body {font-size: 13px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:20px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
.table-title-link{color: #1B17EE;}
.baseInfo tr td span,.baseInfo tr td img{margin-right:20px;}
tr.title-first td{background-color:#DCF9FF;}
tr.title-second td{background-color:#FFFB94;font-size:15px;}
tr.title-second td span{margin-right:0px;}
tr.title-third td{font-size:15px;}
.baseInfo tr td span.amtSpan{ margin-right:0;color: #FF0000;}
tr.title-fourth td{background-color:#DCF9FF;text-align:center;}
.LogInfo tr td img{margin:10px 20px 0 0;}
.color01{color: #FF0000;}
.color02{color: #797979;}
table.gridtable td:nth-child(5) {
	position: relative;
    padding-bottom: 25px;
}
.l-table-edit-td {
	padding-bottom: 50px;
}
</style>
<script type="text/javascript">
var submitFlag = true;
$(function(){
		$.metadata.setType("attr", "validate");
		$("#form1").validate({
	        errorPlacement: function (lable, element) {  
	        		$(element).ligerTip({
						content : lable.html(),width: 100
					});
	        	$(".l-verify-tip-corner").css("z-index", "1001");
	        	$(".l-verify-tip-content").css("z-index", "1000");
	        },
	        success: function (lable,element) {
	            lable.ligerHideTip();
	            lable.remove();
	        },
	        submitHandler: function(form) {
	        	if($('#memberIdTag').val() == ""){
	        		$.ligerDialog.warn("请绑定会员");
	        		return;
	        	}
	        	if(submitFlag){
	        		submitFlag = false;
	        		form.submit();
	        	}
	        }
	    }); 
		
		$("#name").bind("input propertychange",function(event){	
			if($("#name").val().length == 30){
				$(".name").show();
			}else{
				$(".name").hide();
			}	
		});
		
		$("#otherRemarks").bind("input propertychange",function(event){	
			if($("#otherRemarks").val().length == 200){
				$(".otherRemarks").show();
			}else{
				$(".otherRemarks").hide();
			}	
		});
});

//绑定会员
function bind(){
	$.ligerDialog.open({
		height: $(window).height(),
		width:  $(window).width()-350,
		title: "绑定会员",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/salesmanManage/binder.shtml",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});				
}
</script>
</head>
<body>
	<form method="post" id="form1" name="form1" class="form1" action="${pageContext.request.contextPath}/salesmanManage/submit.shtml" >
	<input type="hidden" id="id" name="id" value="${salesMan.id}">
	<input type="hidden" id="memberIdTag" name="memberIdTag" value="${salesMan.memberId}">
	<div class="title-top">
		<table class="gridtable">
          	<tr>
          		<td class="title">业务员会员ID<c:if test="${salesMan.id == null}"><span style="color:red;">*</span></c:if></td>
          		<c:if test="${salesMan.id == null}">
          			<td><span id="memberId"><a href="javascript:bind();">绑定会员</a></span></td>      			
          		</c:if>
          		<c:if test="${salesMan.id != null}">
          			<td>${salesMan.memberId}</td>
          		</c:if>
          	</tr>
          	<tr>
          		<td class="title">业务员会员昵称<c:if test="${salesMan.id == null}"><span style="color:red;">*</span></c:if></td>
          		<td id="nick">
          			<c:if test="${salesMan.id == null}">未绑定</c:if>
          			<c:if test="${salesMan.id != null && salesMan.nick != null}">${salesMan.nick}</c:if>
          			<c:if test="${salesMan.id != null && salesMan.nick == null}">醒购会员</c:if>
          		</td>
          	</tr>
          	<tr>
          		<td class="title">名称备注<span style="color:red;">*</span></td>
          		<td width="80%"><input style="width:299px;height:25px;" name="name" id="name" placeholder="请输入1-30个字的名称备注" value="${salesMan.name}"  validate="{required:true}" maxlength="30"/>
          			<span id="wordPrompt" class="name" style="color:red;display:none;">最多可输入30个字！</span>
          		</td>
          	</tr>
          	<tr>
          		<td class="title">其他备注</td>
          		<td><textarea rows="5" cols="30" id="otherRemarks" name="otherRemarks" placeholder="请输入0-200个字的名称备注" maxlength="200" style="width: 299px; height: 170px;">${salesMan.otherRemarks}</textarea>
          		<span style="position: relative;display: inline-block;
    vertical-align: top;">
				<div id="wordPrompt" class="otherRemarks" style="color:red;display:none;">最多可输入200字！</div>
          		</span>
          		</td>
          	</tr>
          	<tr>
          		<td class="title">操作</td>
          		<td>
					<input type="submit" class="l-button l-button-submit" id="confirm" value="保存"/> &nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="关闭" class="l-button l-button-test" onclick="frameElement.dialog.close();"/>
				</td>
          	</tr>
        </table>
	</div>
	</form>
</body>
</html>
