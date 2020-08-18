<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<style type="text/css">
body{ font-size:12px;padding:10px;}
.l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin-right: 20px;} 
</style>
<html>
	<head>
		<title>批量赠送会员金币</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

<script type="text/javascript">
$(document).ready(function() {
	document.getElementById('group1').style.display = 'none';
	document.getElementById('group2').style.display = 'none';
	$(".radioItem").change( 
		function() { 
			var type=$("input:radio[name='type']:checked").val();
			if (type==2)
			{
				document.getElementById('group1').style.display = '';
				document.getElementById('group2').style.display = 'none';
			}else if (type==3)
			{
				document.getElementById('group1').style.display = 'none';
				document.getElementById('group2').style.display = '';
			}else{
				document.getElementById('group1').style.display = 'none';
				document.getElementById('group2').style.display = 'none';
			}
	});
});

function save() {
	$("#tblTpye").text("");
	$("#tblGroupId1").text("");
 	$("#tblGroupId2").text("");
 	$("#tblIntegral").text("");
 	
    var type=$('input:radio[name="type"]:checked').val();
    if(type==null){
   	 $("#tblTpye").text("请选择赠送范围");
        return false;
    }
    
    var groupId1=$('input:checkbox[name="groupId1"]:checked').val();
    if( type==2 && groupId1==null)
		{$("#tblGroupId1").text("请勾选会员组"); return false;}
	 
	if( type==3 && ($("#groupId2").val())=='')
		{$("#tblGroupId2").text("会员ID不能为空"); return false;}
	 
	var re = /^[1-9]+[0-9]*]*$/;
	if (!re.test($("#integral").val()))
		{$("#tblIntegral").text("请输入正整数"); return false;}

	var groupId = "";
	if (type==2){
	    $("[name='groupId1']").each(function () {
	    	if (this.checked){
	      		groupId += this.value +",";
	        }
	    });
	}else if (type==3){
		groupId=document.getElementById("groupId2").value;
	}else{
		groupId="";
	}

    document.getElementById("groupId").value=groupId.replace(/(\,*$)/g, "");

	 $("#form1").submit();
	 } 
</script>
</head>
	<body>
		<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/member/integralGive/success.shtml" >
			<input id="groupId" name="groupId" type="hidden" value="${groupId}"/>
			<table class="gridtable" >
				<tr>
					<td class="title"  width="30%">赠送范围</td>
					<td align="left" class="l-table-edit-td">
					<c:forEach var="typeItem" items="${types}">
						<span class="radioClass"><input name="type" class="radioItem" type="radio" value="${typeItem.statusValue }" style="width: 30px;" >${typeItem.statusDesc}</span>
					</c:forEach>
					<label id="tblTpye" style="margin:0 0 0 10px;color: #FF0000"></label> 
					</td> 
				</tr>
				
				<tr id="group1">
					<td class="title"  width="30%">会员组</td>
					<td align="left" class="l-table-edit-td">
						<c:forEach var="groupItem" items="${memberGroups}">
							<input name="groupId1" type="checkbox" value="${groupItem.id }" style="width: 30px;"/>${groupItem.name }
				  		</c:forEach>
				  		<label id="tblGroupId1" style="margin:0 0 0 10px;color: #FF0000"></label>
					</td> 
				</tr>
				
				<tr id="group2">
					<td class="title"  width="30%">会员ID</td>
					<td align="left" class="l-table-edit-td">
						<textarea rows=3 id="groupId2" name="groupId2" cols="45" class="text" ></textarea>
						<label id="tblGroupId2" style="margin:0 0 0 10px;color: #FF0000">(多个用户用",""隔开)</label>
					</td> 
				</tr>

				<tr>
					<td class="title"  width="30%">赠送金币数</td>
					<td align="left" class="l-table-edit-td">
						<input type="text" id="integral" name="integral" style="float:left;width: 150px" />
					 	<label id="tblIntegral" style="float:left;margin:0 0 0 10px;color: #FF0000"></label> 
					</td> 
				</tr>

				<tr>
					<td class="title"  width="30%">详细说明</td>
					<td align="left" class="l-table-edit-td">
						<textarea rows=3 id="remarks" name="remarks" cols="45" class="text" ></textarea>
					</td> 
				</tr>
				<tr>
				<td class="title"  width="30%">操作</td> 
				<td align="left" class="l-table-edit-td">
					<input name="btnSubmit" type="button" id="Button1" class="l-button l-button-submit" value="赠送" onClick="save();" />
				</td>
				</tr>
			</table> 
			 
		</form>
	</body>
</html>
