<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
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
<script type="text/javascript">

$(function (){
	 $("#type").bind('change',function(){
		 var type = $(this).val();
		 if (type=='1') {
			 document.getElementById("commissionerAuditby1").style.display="";
			 document.getElementById("fwAuditby1").style.display="none";
			 document.getElementById("cf").style.display="none";
			$.ajax({
				url : "${pageContext.request.contextPath}/impeach/getchangeAuditby.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"type":type},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						var commissionerauditbyList = data.CommissionerauditbyList;
						var optionArray = [];
						optionArray.push('<option value="">请选择</option>');
						for(var i=0;i<commissionerauditbyList.length;i++){
							var commissionerAuditby = commissionerauditbyList[i].commissioner_audit_by;
							var staffname = commissionerauditbyList[i].staff_name;
							optionArray.push('<option value="'+commissionerAuditby+'">'+staffname+'</option>');
						}
						$("#auditby").html(optionArray.join(""));
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
					
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
				
		   }else if (type=='2') {
			   document.getElementById("commissionerAuditby1").style.display="none";
			  
			   document.getElementById("fwAuditby1").style.display="";
			   document.getElementById("cf").style.display="none";
			   $.ajax({
					url : "${pageContext.request.contextPath}/impeach/getchangeAuditby.shtml",
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {"type":type},
					timeout : 30000,
					success : function(data) {
						if ("0000" == data.returnCode) {
							var fwauditbyList = data.FwauditbyList;
							var optionArray = [];
							optionArray.push('<option value="">请选择</option>');
							for(var i=0;i<fwauditbyList.length;i++){
								var fwAuditby = fwauditbyList[i].fw_audit_by;
								var staffname = fwauditbyList[i].staff_name;
								optionArray.push('<option value="'+fwAuditby+'">'+staffname+'</option>');
							}
							$("#auditby").html(optionArray.join(""));
						}else{
							$.ligerDialog.error(data.returnMsg);
						}
						
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			
		}	 
		 
		});
	 
	 
	 $.metadata.setType("attr", "validate");
	    $("#form1").validate({
			errorPlacement : function(lable, element) {
	       	$(element).ligerTip({recordContent : lable.html()});
			},
			success : function(lable) {
				lable.ligerHideTip();
				lable.remove();
			},
			submitHandler : function(form) {
				var type=document.getElementById("type").value;
				var auditby = $("#auditby").val();
				var commissionerAuditby = $("#commissionerAuditby").val();
				var fwAuditby=$("#fwAuditby").val();
				
				if($.trim(type)=='' || type == null){
					$.ligerDialog.alert("变更类型不能为空！"); 
					return;
				}
				if($.trim(auditby)=='' || auditby == null) {	    
					$.ligerDialog.alert("变更前不能为空!"); 
					return;
		   		}
				if (type=='1') {
				if($.trim(commissionerAuditby)=='' || commissionerAuditby == null) {	    
					$.ligerDialog.alert("变更后不能为空"); 
					return;
		   		 }			
			   }else if (type=='2') {
				  if($.trim(fwAuditby)=='' || fwAuditby == null) {	    
					 $.ligerDialog.alert("变更后不能为空"); 
					 return;
			   	}
			}
				form.submit();
			}
		});

	
});




</script>

</head>
	<body style="margin: 10px;">
	<form method="post" id="form1" name="form1" class="form1" action="${pageContext.request.contextPath}/imPeach/changeimPeach.shtml" >
		<table class="gridtable">
			<tr>
               <td class="title">变更类型<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
	               <select id="type" name="type" style="width: 200px;">
               			<option value="">请选择</option>
						<option value="1">初审人</option>
						<option value="2">复审人</option>
               	   </select>
               </td>
			</tr>
			<tr>
               <td class="title">变更前<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
	               <select id="auditby" name="auditby" style="width: 200px;">
               			<option value="">请选择</option>
						
               	   </select>
               </td>
			</tr> 
			<tr>
               <td class="title">变更后<span style="color: red;">*</span></td>
               <td id="cf">
                   <select id="" name="" style="width: 200px;">
               			<option value="">请选择</option>
               	   </select>
               </td>
               <td id="commissionerAuditby1" colspan="2" align="left" class="l-table-edit-td" style="display:none;">
	               <select id="commissionerAuditby" name="commissionerAuditby" style="width: 200px;">
               			<option value="">请选择</option>
						 <c:forEach var="kfStaffInfos" items="${kfStaffInfos}">
							<option value="${kfStaffInfos.staffId}" >${kfStaffInfos.staffName}</option>
						</c:forEach>
               	   </select>
               </td>
               <td id="fwAuditby1" colspan="2" align="left" class="l-table-edit-td" style="display:none;">
	               <select id="fwAuditby" name="fwAuditby" style="width: 200px;">
               			<option value="">请选择</option>
						  <c:forEach var="fawuStaffInfos" items="${fawuStaffInfos}">
							<option value="${fawuStaffInfos.staffId}" >${fawuStaffInfos.staffName}</option>
						</c:forEach>
               	   </select>
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