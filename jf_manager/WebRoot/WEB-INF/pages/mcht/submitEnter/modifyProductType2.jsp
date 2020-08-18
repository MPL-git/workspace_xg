<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript">
 	$(function(){
 		$('#brandType').val(${mchtInfo.brandType});
 			
 	 	$("#submit").click(function(){
 			$.ajax({
 				url : "${pageContext.request.contextPath}/mcht/productType2Submit.shtml",
 				type : 'POST',
 				dataType : 'json',
 				cache : false,
 				async : false,
 				data : {id:$('#id').val(),brandType:$('#brandType').val(),brandTypeDesc:$('#brandTypeDesc').val()},
 				timeout : 30000,
 				success : function(data) {
 					if (data.returnCode=="0000") {
 						$.ligerDialog.alert('提交成功！', function (yes) { 
 							$(window.parent.document).find("#mchtId").click();
 							frameElement.dialog.close();});					
 					} else {
 						$.ligerDialog.error(data.returnMsg);
 					}
 				},
 				error : function() {
 					$.ligerDialog.error('操作超时，请稍后再试！');
 				}
 			});
 	 	});
 	});
 
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
		<input id="id" name="id" type="hidden" value="${mchtInfo.id}"/>
		<div style="text-align:center;">
		<div>
                <select style="width:90%;margin-top:20px;" name="brandType" id="brandType">
                <option value="">请选择</option>
                <option value="0">品牌官方</option>
                <option value="1">品牌总代</option>
                <option value="2">品牌代理商</option>
                </select>
                <textarea style="margin-top:10px;" rows="8" cols="40" id="brandTypeDesc"  maxlength="256" name="brandTypeDesc">${mchtInfo.brandTypeDesc}</textarea>
				<input id="submit" type="button" class="l-button l-button-submit" style="margin-top:30px;" value="提交"/>
		</div>
		</div>
</body>
</html>
