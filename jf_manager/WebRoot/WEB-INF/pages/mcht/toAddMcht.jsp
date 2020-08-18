<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	
</script>

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

td input,td select{
border:1px solid #AECAF0;
}
.radioClass{
	margin-right: 20px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
</style>
<style type="text/css">
.middle input {
	display: block;
	width: 30px;
	margin: 2px;
}
table.l-checkboxlist-table td{
	border-style: none;
}
table.l-listbox-table td{
	border-style: none;
}
</style>
<script type="text/javascript">
var dataFromValidate;

$(function ()  {
	
	$("#cooperateBeginDate").ligerDateEditor( {
		showTime : false,
		width:158
	});
	$("#agreementBeginDate").ligerDateEditor( {
		showTime : false,
		width:158
	});
	$("#agreementEndDate").ligerDateEditor( {
		showTime : false,
		width:158
	});
	$.metadata.setType("attr", "validate");
	
	dataFromValidate = $("#form1").validate({
	            	
	            	rules:{
	            		
	            		"mchtUser.password": {
	            	        required: true,
	            	        minlength: 6
	            	      },
	            	      "confirmPassword": {
	            	        required: true,
	            	        minlength: 6,
	            	        equalTo: "#password"
	            	      }
	            	},
	            	
	            	messages:{
	            		password: {
	            	        required: "请输入密码",
	            	        minlength: "密码长度不能小于 5 个字母"
	            	      },
	            	      confirmPassword: {
	            	        required: "请输入密码",
	            	        minlength: "密码长度不能小于 5 个字母",
	            	        equalTo: "两次密码输入不一致"
	            	      }
	            	},
	            	
	                errorPlacement: function (lable, element)
	                {   console.log(lable[0].innerText);
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
	                }
	            });
	            
	            
	  });    

	 function commitSave(){
		 if(dataFromValidate.form()){
				var dataJson = $("#form1").serializeArray();
				$.ajax({
					url : "${pageContext.request.contextPath}/mcht/validateMchtInfo.shtml",
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : dataJson,
					timeout : 30000,
					success : function(data) {
						if (data.returnCode=="0000") {
						$("#form1").submit();
						} else {
							alert(data.returnMsg);
						}
						
					},
					error : function() {
						alert("提交失败，请重新提交");
					}
				});
	 	  }
	    	
	    }      


</script>

<html>
<body>
	<form method="post" id="form1" name="form1"
		action="${pageContext.request.contextPath}/mcht/mchtSave.shtml">
		<table class="gridtable">
			<tr>

				<td  colspan="1" class="title">商家简称 <span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td"><input id="shortName" validate="{required:true}"
					name="mchtInfo.shortName" type="text" value=""
					/></td>
					
				<td  colspan="1" class="title">公司名称<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td"><input id="companyName" validate="{required:true}"
					name="mchtInfo.companyName" type="text" value=""
					/></td>
			</tr>
			<tr>
				<td  colspan="1" class="title">商家类型 <span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td"> 
	
				
				<select style="width: 160px;" id="mchtType" name="mchtInfo.mchtType" validate="{selectRequired:true}">
					<option value="">请选择</option>
					<c:forEach var="statusItem" items="${mchtTypeStatus}">
						<option value="${statusItem.statusValue}">${statusItem.statusDesc}
						</option>
					</c:forEach>
				</select>
<!-- 				<div style="display:inline;"> -->
<!-- 				<input  type="checkbox" value="1" name="mchtInfo.isManageSelf"><span>是否自营</span>  -->
<!-- 				</div> -->
				</td>
				
				<td  colspan="1" class="title">合作开始日期<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<input type="text" id="cooperateBeginDate" name="mchtInfo.cooperateBeginDate"  style="float:left;" validate="{required:true}"/>
				</td>
			</tr>
			<tr>
			<td  colspan="1" class="title">店铺名 <span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td"><input id="shopName" validate="{required:true}"
					name="mchtInfo.shopName" type="text" value=""
					style="float:left;" /></td>
					
				<td  colspan="1" class="title">店铺目录<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td"><input id="shopCatalog" validate="{required:true}"
					name="mchtInfo.shopCatalog" type="text" value=""
					style="float:left;" /></td>
			</tr>
					<tr>
			<td  colspan="1" class="title">最新合同开始 <span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<input type="text" id="agreementBeginDate" name="mchtInfo.agreementBeginDate"  style="float:left;" validate="{required:true}"/>
				</td>
					
				<td  colspan="1" class="title">最新合同到期<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<input type="text" id="agreementEndDate" name="mchtInfo.agreementEndDate"  style="float:left;" validate="{required:true}"/>
				</td>
			</tr>
					<tr>
			<td  colspan="1" class="title">合作状态<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					
				<select style="width: 160px;" id="status" name="mchtInfo.status" validate="{selectRequired:true}">
					<option value="">请选择</option>
					<c:forEach var="statusItem" items="${mchtInfoStatus}">
						<option value="${statusItem.statusValue}">${statusItem.statusDesc}
						</option>
					</c:forEach>
				</select>
				</td>
					<td  colspan="1" class="title">用户名 <span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<input id="userCode" validate="{required:true}"
					name="mchtUser.userCode" type="text" value=""
					style="float:left;" />
			</td>		
			
			</tr>
					<tr>
	
					
				<td  colspan="1" class="title">初始密码<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td"><input id="password"
					name="mchtUser.password" type="password" value=""
					style="float:left;" /></td>
					
				<td  colspan="1" class="title">确认密码<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td"><input id="confirmPassword"
					name="confirmPassword" type="password" value=""
					style="float:left;" /></td>
			</tr>
					<tr>
			
				<td  colspan="1" class="title">对接人类型 <span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">				
				<select style="width: 162px;" id="status" name="mchtContact.contactType" validate="{selectRequired:true}">
					<option value="">请选择</option>
					<c:forEach var="statusItem" items="${mchtContactTypes}">
						<option value="${statusItem.statusValue}">${statusItem.statusDesc}
						</option>
					</c:forEach>
				</select></td>
				<td  colspan="1" class="title">对接人姓名<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td"><input id="contactName" validate="{required:true}"
					name="mchtContact.contactName" type="text" value=""
					style="float:left;" /></td>
			
			</tr>
					<tr>
			
						<td  colspan="1" class="title">用户手机号 <span class="required">*</span></td>
					<td  colspan="3" align="left" class="l-table-edit-td">
					<input id="mobile" 	name="mchtUser.mobile" type="text" value=""
					style="float:left;" validate="{required:true,mobile:true}" /></td>	
				<td  colspan="1" class="title">用户邮箱<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td"><input id="email" 
					name="mchtUser.email" type="text" value="" validate="{required:true,email:true}"
					style="float:left;" /></td>
			</tr>
			<tr>
				<td  class="title">操作</td>
				<td colspan="7" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<c:if test="${STAFF_ID!=1}">
							<input name="btnSubmit" type="button" id="Button1" onclick="commitSave();"
								style="float:left;" class="l-button l-button-submit" value="提交"
								 />
						</c:if>
						<input type="button" value="取消" class="l-button l-button-test"
							style="float:left;" onclick="frameElement.dialog.close();" />

					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
