<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">    
    <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
     <script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
 <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
  <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
   
    <style type="text/css">
        body{ font-size:12px;padding:10px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>
    <script type="text/javascript">
    function  checkstaff()
    {	   var tid =$("#STAFF_CODE").val(); 
    	   var path = '<%=request.getContextPath()%>/orgMng/CHECKstaffinfo.shtml?';
			jQuery.ajax(	 {
		  		url : path,
		  		type : 'POST',
		  		dataType : 'json',
		  		cache : false,
		  		async : true,
		  		data : { id : tid },
		  		timeout : 30000,
		  		success : function(json) 
		  		{ 
		  			if(parseInt(json.rst)==0)
		  			   { 
			  			$("#labelSTAFF_CODE").text("工号已经存在"); 
		  	  		}else
		  	  			$("#labelSTAFF_CODE").text("工号可使用");
		  			
		  		},
				error : function() 
				{
			   		$("#labelSTAFF_CODE").text("工号已经存在");
				}
		  	});
	}

	$(function() { 
	   if(($("#STAFF_ID").val())!='')
		   {$("#STAFF_CODE").attr("readonly",true);
		}
	   
		$.metadata.setType("attr", "validate");		
		var v = $("#form1").validate({
	    	
			errorPlacement: function (lable, element)
			{   
	        	var elementType=$(element).attr("type");

	        	if($(element).hasClass("l-text-field")){
	        		$(element).parent().ligerTip({
						content : lable.html(),width: 100
					});
	        	}else{
	        		$(element).ligerTip({
						content : lable.html(),width: 100
					});
	        	}
			},
			
			success: function (lable,element)
			{
				lable.ligerHideTip();
				lable.remove();
			},
			submitHandler: function (form)
			{   
				form.submit();
			}
		});
	});
    </script>
<html>
	<body>
	<form method="post" id="form1" name="form1"	action="${pageContext.request.contextPath}/orgMng/save_staff.shtml" >
		<input name="ORG_ID" type="hidden"  value="${ORG_ID }" />
		<input id="STAFF_ID"name="STAFF_ID" type="hidden"  value="${STAFF_ID }" /> 
		<table  class="gridtable">
		  <c:if test="${STAFF_ID==null||STAFF_ID==''}" >
		    <tr>
               <td align="left" class="l-table-edit-td" colspan="3">
               <font color="red">温馨提示：新增人员时，初始密码为123456</font>
               </td>  
                           
           </tr>
           </c:if>
           <tr>
               <td class="title" >人员工号<span class="required">*</span></td>
               <td align="left" class="l-table-edit-td">
                 	<input id="STAFF_CODE" name="STAFF_CODE" type="text" value="${STAFF_CODE }"  onblur="javascript:checkstaff()" style="float:left;width: 200px;" 
                  validate="{required:true}"/>
              <label id="labelSTAFF_CODE" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>

               </td> 
           </tr>
            <tr>
               <td class="title">姓名<span class="required">*</span></td>
               <td align="left" class="l-table-edit-td">
                   <input id="STAFF_NAME" name="STAFF_NAME" type="text"    value="${STAFF_NAME }" style="float:left;width: 200px;" validate="{required:true}"/>
			  <label id="labelSTAFF_NAME" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
			   </td> 
           </tr>
           <tr>
               <td class="title">性别</td>
               <td align="left" class="l-table-edit-td">
					<select style="float:left;width: 200px;" id="SEX_TYPE" name="SEX_TYPE"  >
						<option value="">
							请选择
						</option>
						<c:forEach var="list" items="${SEXTYPElIST}">
							<option <c:if test="${SEX_TYPE==list.STATUS_VALUE}">selected</c:if>
								value="${list.STATUS_VALUE}">
								${list.STATUS_DESC}
							</option>
						</c:forEach>
					</select>
					<label id="labelSEX_TYPE" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
					</td> 
           </tr>
            <tr>
               <td class="title">生日</td>
               <td align="left" class="l-table-edit-td">
                    <input type="text" id="BIRTHDAY" name="BIRTHDAY"  
								value="${BIRTHDAY}"  />
							<script type="text/javascript">
							$(function() {
								$("#BIRTHDAY").ligerDateEditor( {
									showTime : false,
									labelWidth : 200,
									width:200,
									labelAlign : 'left'
								});
							});
							</script>
		          </td> 
           </tr>
                        <tr>
               <td class="title">证件类型</td>
               <td align="left" class="l-table-edit-td">
                  <select style="float:left;width: 200px;" id="CERT_TYPE" name="CERT_TYPE" >
						<option value="">
							请选择
						</option>
						<c:forEach var="list" items="${CERTTYPElist}">
							<option <c:if test="${CERT_TYPE==list.STATUS_VALUE}">selected</c:if>
								value="${list.STATUS_VALUE}">
								${list.STATUS_DESC}
							</option>
						</c:forEach>
					</select>
					<label id="labelCERT_TYPE" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
					 </td> 
           </tr>
            <tr>
               <td class="title">证件号码</td>
               <td align="left" class="l-table-edit-td">
                   <input id="CERT_NBR"name="CERT_NBR" type="text" size="16" value="${CERT_NBR }" style="float:left;width: 200px;" />
			  <label id="labelCERT_NBR" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
			   </td> 
           </tr>
           <tr>
               <td class="title">手机号码<span class="required">*</span></td>
               <td align="left" class="l-table-edit-td">
                   <input id="MOBILE_PHONE" name="MOBILE_PHONE" type="text" size="16" value="${MOBILE_PHONE }" style="float:left;width: 200px;"  validate="{required:true,digits:true,maxlength:15}"/>
			 <label id="labelMOBILE_PHONE" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
			   </td>  
           </tr>
           <tr>
               <td class="title">EMAIL<span class="required">*</span></td>
               <td align="left" class="l-table-edit-td">
				   <input id="EMAIL" name="EMAIL" type="text" size="64" value="${EMAIL }" style="float:left;width: 200px;" validate="{required:true,email:true}"/>
				   <label id="labelEMAIL" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
               </td> 
           </tr>
               <tr>
               <td class="title">其他</td>
               <td align="left" class="l-table-edit-td"> 
				  <textarea rows=5 " id="REMARK" name="REMARK" cols="50" class="text">${REMARK }</textarea>
               </td> 
           </tr> 
           <tr> 
           <Td colspan="2"> 
		<div id="btnDiv">
		<input type="button" value="取消" class="l-button l-button-test" style="float:right;" onclick="frameElement.dialog.close();" /> 
		 
		<c:if test="${STAFF_ID!=1}" >
		<input name="btnSubmit" type="submit" id="Button1" style="float:right;" class="l-button l-button-submit" value="保存" />
		</c:if>
		</div></Td>
		</tr>
		</table>
	</form>
	</body>
</html>
