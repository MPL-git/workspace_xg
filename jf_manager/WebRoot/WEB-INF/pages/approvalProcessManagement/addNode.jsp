<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
    <title>My JSP 'addmchtplatconindex.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


	<style type="text/css">
		.title{height: 30px;width: 60px;}
		.l-table-edit-td{height:30px; width: 120px;}
	</style>
	
	<script type="text/javascript">
	var approvers = "${approvers}";
	var testStaffids = [];
	var staffName;
	var dataFromValidate;
		$(function(){
			 $.metadata.setType("attr", "validate");
	         dataFromValidate =   $("#form1").validate({
	        	 rules: {
	        		 name: {
	     				 required:true,
	     				 maxlength:20,
	     		      }
	     		 },
	     		 messages: {
	     			name: {
	     			        required: "不可为空",
	     			       maxlength:"最多输入20个字",
	     			      }
	     		 },
						errorPlacement : function(lable, element) {
						 	if ($(element).attr('name') == 'name') {
								$("#name").ligerTip({
									 content : lable.html() 
								});
							} else{
								lable.appendTo(element.parent());
							} 
						},
						success : function(lable) {
							lable.ligerHideTip();
							lable.remove();
						},
						submitHandler : function(form) {
							parent.location.reload();
							form.submit();
						}
				});
			
			
			
			 function getGridOptions(checkbox){
			     var options = {
			         columns: [
			         { display: '账号', name: 'staffCode', minWidth: 100, width: 100 },
			         { display: '姓名', name: 'staffName', minWidth: 100, width: 100 }
			         ], 
			         switchPageSizeApplyComboBox: false,
			         url: '${pageContext.request.contextPath}/approvalProcessManagement/getSysStaffInfoList.shtml',
			         pageSize: 10, 
			         checkbox: checkbox
			     };
			     return options;
			 }
			 
			
			 staffName = $("#staffName").ligerComboBox({
			     	 width: 120,
			         slide: false,
			         selectBoxWidth: 280,
			         selectBoxHeight: 280,
			         valueField: 'id',
			         textField: 'staffName',
			         valueFieldID:'id',
			         grid: getGridOptions(false),
			         condition:{ fields: [{ name: 'staffName', label: '姓名',width:90,type:'text' } ]}
			     });	
			 
			 

				var approversArr = approvers.split(",");
				 for(var i=0;i<approversArr.length;i++){
					 testStaffids.push(approversArr[i]);
				 }
				$("#staffName").bind('change',function(){
					 var staffText = staffName.getText();
					var staffValue = staffName.getValue();
					 var index = $.inArray(staffValue, testStaffids);
					if(index>=0&&staffValue){
						commUtil.alertError("请不要重复添加");
					}
					if(index<0 && staffValue!=''){
						testStaffids.push(staffValue);
						$("#chaoSongrRen").append("<div id = "+staffValue+" name='staffDel'><input type='hidden' name='copiers' value="+staffValue+" /><span >"+staffText+"</span><a href=\"javascript:delCopiers(" + staffValue + ");\">删除</a></div>");
					}
				})
				
		});
		
/* 		$.validator.addMethod("staffName", function(value, element) {  
			
			var staffText = staffName.getText();
			var staffValue = staffName.getValue();
	    	if($.trim(staffValue)==''){
	    		return false;
	    	}else{
	    		return true;
	    	}
	    }, "请选择人员"); */
		
		function sava(){
				var staffValue = staffName.getValue();
				$("#staffValue").val(staffValue);
			
				var staffIds=[]; //审核人集合
				 $("input[name='copiers']").each(function(){
					 var a = $(this).val()
					 staffIds.push(a)
				 }); 
				 
				var approverType = $("#approverType").val();
			    var length = $("#zhiDing2").find("div[name='staffDel']").length;
				if(length<=0 && approverType!=0){
					commUtil.alertError("请添加审批人");
					return;
					}
				 
				var dataJson = $("#form1").serializeArray();
				dataJson.push({"name":"staffIds","value":JSON.stringify(staffIds)});
				if(dataFromValidate.form()){
		    	$.ajax({
		    		url: "${pageContext.request.contextPath}/approvalProcessManagement/saveAddNode.shtml",
		    		type : 'POST',
		    		dataType : 'json',
		    		cache : false,
		    		async : false,
		    		data : dataJson,
		    		timeout : 30000,
		    		success : function(data) {
		    			if ("0000" == data.returnCode) {
		    				commUtil.alertSuccess("提交成功");
		    				parent.location.reload();
		    				frameElement.dialog.close();
		    			}else{
		    				$.ligerDialog.error(data.returnMsg);
		    			}
		    			
		    		},
		    		error : function() {
		    			$.ligerDialog.error('操作超时，请稍后再试！');
		    		}
		    	});
				}
		    }


		
	function delCopiers(id){
		var ids=""+id;
		var a = $.inArray(ids,testStaffids)
		testStaffids.splice(a,1);
		$("#"+id).remove();
	}	

	function getTypeValue(){
		var val = $("#approverType").val();
		if(val=='0'){
			$("#zhiDing").css('display','none');
			$("#zhiDing2").css('display','none');
		}else{
			$("#zhiDing").show();
			$("#zhiDing2").show();
		}
	}

	
	
	</script>
  </head>
  
  <body>
  
  	<form method="post" id="form1" name="form1"  style="margin: 10px" >
  		<input type="hidden" name="staffValue" value="" class="staffValue" id="staffValue"/>
  		<input type="hidden" name="procedureId" id="procedureId" value="${procedureId }"/>
  		<input type="hidden" name="seq" id="seq" value="${seq }"/>
  		<input type="hidden" name="procedureNodeId" id="procedureNodeId" value="${procedureNodeId }"/>
  		<table class="gridtable">
  			<tr>
  				<td class ="title" >节点</td>
  				<td align="left" class="l-table-edit-td"><input id="name"  class="name" type="text" name="name"  value="${procedureNode.name }"  validate="{required:true}"/>
  				</td>
  			</tr>
  			
  			<tr>
  				<td class ="title" >节点类型</td>
  				<td align="left" class="l-table-edit-td">
				<input name="type" checked  type="radio" value="0"  > 正常节点
				<input name="type" type="radio" value="1" <c:if test="${procedureNode.type==1 }">checked</c:if>  > 核心节点
				<input name="type" type="radio" value="2" <c:if test="${procedureNode.type==2 }">checked</c:if> > 备用节点
  				</td>
  			</tr>
  			
  			<tr>
  				<td class ="title" >审批人类型</td>
  				<td align="left" class="l-table-edit-td">
  				<select id = "approverType" name="approverType" onchange="getTypeValue()">
  				<!-- <option value="">请选择</option> -->
  				<option value="0" <c:if test="${approverType==0 }">selected="selected"</c:if> >上级</option>
  				<option value="1"  <c:if test="${approverType==1 }">selected="selected"</c:if> >指定人员</option>
  				</select>
  				</td>
  			</tr>
  			
  			
  		
  			<tr id="zhiDing"  style="<c:if test='${approverType==0 }'>display:none</c:if>">
  				<td class ="title"  rowspan="2">审批人</td>
  				<td align="left" class="l-table-edit-td">
  				<input id="staffName"  type="text" name="staffName"  value=""  />
  				</td>
  			</tr>
  			
  			<tr id="zhiDing2"  style="<c:if test='${approverType==0 }'>display:none</c:if>">
  			<td align="left" class="l-table-edit-td">
  				<c:forEach var="sysStaffInfoCustom" items="${sysStaffInfoCustomList}">
	  				<div id = "${sysStaffInfoCustom.staffId }" name="staffDel">
	  				<input type="hidden" name="copiers" value="${sysStaffInfoCustom.staffId }" />
	  				<span >${sysStaffInfoCustom.staffName }</span>
	  				<a href="javascript:delCopiers(${sysStaffInfoCustom.staffId });">删除</a>
	  				</div>
  				</c:forEach>
  			<span id = "chaoSongrRen"></span>
  			</td>
  			</tr>
  			
  			 
  			<tr>
  				<td class="title">操作</td>
  				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit"   style="float:left;margin-right:30px;" class="l-button l-button-submit" value="提交" onclick="sava();"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
  				</td>
  			</tr>
  		</table>
  	</form>
  </body>
</html>
