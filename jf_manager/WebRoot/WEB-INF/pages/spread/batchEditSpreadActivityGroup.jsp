<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>
    <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	
 
<script type="text/javascript">
//    var dataFromValidate;
	
// 	$(function (){ 
// 	    $.metadata.setType("attr", "validate");    
	    
// 	           dataFromValidate = $("#form1").validate({
// 	       		errorPlacement: function (lable, element)
// 	    		{   
// 	            	var elementType=$(element).attr("type");

// 	            	if($(element).hasClass("l-text-field")){
// 	            		$(element).parent().ligerTip({
// 	    					content : lable.html(),width: 100
// 	    				});
// 	            	}else{
// 	            		$(element).ligerTip({
// 	    					content : lable.html(),width: 100
// 	    				});
// 	            	}
// 	    		},
	    		
// 	    		success: function (lable,element)
// 	    		{
// 	    			lable.ligerHideTip();
// 	    			lable.remove();
// 	    		}
// 	            });
// 	  }); 
	
	
	function commitSave(){
		  
		    if($("#channel").val()==''&&$("#isEffect").val()==''){
		    	alert("请至少选择一个修改项。");
		    	return;
		    }
			$("#form1").submit();
	}
	
	
	
	
  </script>
 </head>
<html>
<body>
	<form method="post" id="form1" name="form1"
		action="${pageContext.request.contextPath}/spread/batchEditSpreadActivityGroupSave.shtml"  >
		<input id="deviceType" name="deviceType" type="hidden" value="${deviceType}" />
		<input id="spreadActivityGroupIds" name="spreadActivityGroupIds" type="hidden" value="${spreadActivityGroupIds}" />
		<table class="gridtable">
		
			<tr>
               <td class="title"  style="text-align: center;" >推广渠道*</td>  
	               <td class="statu_value" colspan="5" height="40px">
						<select style="width:163px;" id="channel" name="channel" class="querysel">
						<option value="">--请选择--</option>
							<c:if test="${deviceType == '1'}">
								<c:forEach var="channelItem" items="${channelList}">
									<option value="${channelItem.statusValue}" <c:if test="${channelItem.statusValue==spreadActivityGroup.channel}">selected="selected"</c:if>>${channelItem.statusDesc}</option>
								</c:forEach>
							</c:if>
							<c:if test="${deviceType == '2'}">
								<c:forEach var="list" items="${channelList}">
									<c:if test="${list.statusDesc == '今日头条'}">
										<option value="${list.statusValue}" <c:if test="${channelItem.statusValue==spreadActivityGroup.channel}">selected="selected"</c:if> >${list.statusDesc}</option>
									</c:if>
								</c:forEach>
							</c:if>
						</select>
				   </td> 
            </tr>
			
			<tr>
               <td class="title"  style="text-align: center;" >状态*</td>  
	               <td class="statu_value" colspan="5" height="40px">
						<select name="isEffect"  id = "isEffect">
						<option value="">--请选择--</option>
						<option value="1">启用</option>
						<option value="0">禁用</option>
						</select>
				   </td> 
            </tr>
	            
            
            
            
            
            <tr>
				<td class="title">操作</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<div><input name="btnSubmit" type="button" id="Button1" onclick="commitSave();" style="float:left;" class="l-button"  value="提交"  /></div>
	               		<div><input name="btnCancle" type="button" id="Button2" style="float:left; margin-left: 40px" class="l-button" value="取消" onclick="frameElement.dialog.close();" /></div>
					</div>
				</td>
			</tr>
            

		</table>
	</form>
</body>
</html>
