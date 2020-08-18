<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<%@include file="/common/common-tag.jsp"%>
 <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
	<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
   
    <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
   <script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
 
<style type="text/css">
  body{ font-size:12px;padding:10px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
</style>
<html>
<head>
<title> 分类</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javascript"> 
		 var falg=true;
           
		 function onSubmit() { 
			var ret = {RESULT_CODE: 1};  //默认为错误
			var org_id = $.trim($("#ORG_ID").val());
			var partent_id = $.trim($("#PARENT_ID").val());
			var org_name = $.trim($("#ORG_NAME").val()); 
			var addr = $.trim($("#ADDR").val()); 
			var duty_staff_id = $.trim($("#DUTY_STAFF_ID").val()); 
			var duty_staff_name = $.trim($("#DUTY_STAFF_NAME").val()); 
			var link_phone = $.trim($("#LINK_PHONE").val()); 
			var modify_phone = $.trim($("#MOBILE_PHONE").val()); 
			var org_desc = $.trim($("#ORG_DESC").val());  
			var i=0;
			 $("#lb_org_name").text("");  
			 $("#lb_phone").text(""); 
			 $("#lb_link_phone").text(""); 
			 $("#lb_org_addr").text(""); 
			 $("#lb_staff_id").text("");
			 $("#lb_staff_name").text("");
			 if(!falg){
                 i=1;
                 $.ligerDialog.warn("请输入正确负责人ID");
             }
			 if (($("#ORG_NAME").val())=='')
				 {$("#lb_org_name").text("请填写信息");i=1 ;}
			if (($("#ADDR").val())=='')
				 {$("#lb_org_addr").text("请填写信息");i=1  ;}
			if (($("#DUTY_STAFF_ID").val())=='')
			 {$("#lb_staff_id").text("请填写信息");i=1  ;}
			if (($("#DUTY_STAFF_NAME").val())=='')
				 {$("#lb_staff_name").text("请填写信息");i=1  ;}
			if (($("#LINK_PHONE").val())=='')
				 {$("#lb_link_phone").text("请填写信息");i=1  ;}
			if (!verify.mobilePhone($("#MOBILE_PHONE").val()))
				 {$("#lb_phone").text("无效的手机号码");return ;} 
			if(i==1 )
				{return;}
			 window.parent.$("#pageloading").show();
			$.ajax({
				type: "POST",
				async: false,
				url: "${pageContext.request.contextPath}/orgMng/save_org.shtml",
				data: {ORG_ID: org_id,PARENT_ID: partent_id, ORG_NAME: org_name, ADDR: addr, DUTY_STAFF_ID: duty_staff_id, DUTY_STAFF_NAME: duty_staff_name
					, LINK_PHONE: link_phone, MOBILE_PHONE: modify_phone, ORG_DESC: org_desc },
				dataType: 'json',
				success: function(data) { 
					ret = data;
				},
				error: function() {
					ret["RESULT_MESSAGE"] = "服务异常!";
				},
				complete: function() {
					window.parent.$("#pageloading").hide();
				}
			});
			
			return ret;
		}
		 
		 
		function getdutyStaffName(){
			//获取原负责人姓名
			var yuanId =  $("#yuanId").val();
			//获取新id的联系电话
			var staffId = $("#DUTY_STAFF_ID").val();
			var staffName = $("#DUTY_STAFF_NAME").val();
			
			$.ajax({
				type: "POST",
				async: false,
				url: "${pageContext.request.contextPath}/orgMng/getdutyStaffName.shtml",
				data: {yuanId: yuanId,staffId: staffId,staffName:staffName },
				dataType: 'json',
				success: function(data) { 
					if(data.code == 200){
                        falg = true;
						$("#DUTY_STAFF_NAME").val(data.staffName);
						$("#LINK_PHONE").val(data.mobilePhone);
						$("#MOBILE_PHONE").val(data.mobilePhone);
					}else if(data.code == 9999){
                        falg = false;
                    }
				},
				error: function() {
					ret["RESULT_MESSAGE"] = "服务异常!";
				},
				complete: function() {
					window.parent.$("#pageloading").hide();
				}
			});

			
			
		} 
		 
</script>
</head>
	<body>
	<form id="postForm" name="postForm" method="post" action="${pageContext.request.contextPath}/orgMng/save_org.shtml" >
		<input type="hidden" id="yuanId" name="yuanId" value="${DUTY_STAFF_ID}"/>
		<input type="hidden" id="yuanName" name="yuanName" value="${DUTY_STAFF_NAME}"/>
		<table class="gridtable" >
		   <c:if test="${ORG_ID!=null&&ORG_ID!=''&&ORG_ID==1}" >
		    <tr>
               <td class="title" colspan="2" align="right">
               <font color="red" >温馨提示：部门管理根节点不能操作</font>
               </td>          
           </tr>
           </c:if>
           <tr>
               <td class="title">部门名称</td>
               <td align="left" class="l-table-edit-td" colspan="3">
                 	<input id="ORG_NAME" name="ORG_NAME" type="text" value="${ORG_NAME }"style="width: 350px" />  <label id="lb_org_name" style="color: #FF0000">  </label>            
					<input id="ORG_ID" name="ORG_ID" type="hidden"  value="${ORG_ID }" />
					<input id="PARENT_ID" name="PARENT_ID" type="hidden"  value="${PARENT_ID}" />
               </td> 
           </tr>
           <tr>
               <td class="title">地址</td>
               <td align="left" class="l-table-edit-td" colspan="3">
					<input id="ADDR"  name="ADDR" type="text"   value="${ADDR }"  style="width: 350px" validate="{required:true}" /><label id="lb_org_addr" style="color: #FF0000">  </label>    
               </td> 
           </tr>
            
            <tr>
              <td  class="title">负责人ID</td>
               <td align="left" class="l-table-edit-td" >
					<input id="DUTY_STAFF_ID"  name="DUTY_STAFF_ID" type="text" style="width: 150px" value="${DUTY_STAFF_ID }" onchange="getdutyStaffName()"  validate="{required:true}" /><label id="lb_staff_id" style="color: #FF0000">  </label>    
               </td> 
               
               <td class="title">原负责人</td>
               <td align="left" class="l-table-edit-td" >
					<input id="DUTY_STAFF_NAME"  name="DUTY_STAFF_NAME" type="text" style="width: 150px" value="${DUTY_STAFF_NAME }"  validate="{required:true}" /><label id="lb_staff_name" style="color: #FF0000">  </label>    
               </td> 
           </tr>

           
            <tr>
               <td class="title">联系电话</td>
               <td align="left" class="l-table-edit-td" colspan="3">
					<input id="LINK_PHONE" name="LINK_PHONE" type="text" style="width: 350px" value="${LINK_PHONE }"   /><label id="lb_link_phone" style="color: #FF0000">  </label>
               </td> 
           </tr>
           
            <tr>
               <td class="title">手机号码</td>
               <td align="left" class="l-table-edit-td" colspan="3">
					<input id="MOBILE_PHONE" name="MOBILE_PHONE" type="text"  style="width: 350px" value="${MOBILE_PHONE }"  /><label id="lb_phone" style="color: #FF0000">  </label>
               </td> 
           </tr> 
           
            <tr>
               <td class="title">其他</td>
               <td align="left" class="l-table-edit-td" colspan="3"> 
				  <textarea rows=5 id="ORG_DESC" name="ORG_DESC" style="width: 500px" class="text">${ORG_DESC }</textarea>
               </td> 
           </tr>  
		</table> 
	</form>
	</body>
</html>
