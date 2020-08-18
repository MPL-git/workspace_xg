<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>

<style type="text/css">
	body{ font-size:12px;padding:10px;}
	.table-1{padding: 30px 0px; border-collapse: separate;}
	.select-1{width: 150px;}
	.table-1 tr{height: 40px;}
	.table-1 tr .td-2{padding-left: 3px;}
</style>

<html>
<body>
	<form method="post" id="form-1" action="${pageContext.request.contextPath}/mcht/updatePlatformContactList.shtml">
		<input id="ids" name="ids" type="hidden" value="${ids }"/>
		<div>
			<table align="center" class="table-1">
				<tbody>
		            <tr>
		                <td class="td-1" >选择类型：</td>
		                <td class="td-2" >
			                <select name="contactType" id="contactType" class="select-1" onChange="getPlatformContactList();" >
							    <c:forEach var="contactType" items="${contactTypeList }" >
							    	<option value="${contactType.statusValue }">${contactType.statusDesc }</option>
							    </c:forEach>
							</select>
		                </td>
		            </tr>
		            <tr>
		                <td class="td-1" >选择人员：</td>
		                <td class="td-2" >
			                <select name="platformContactId" id="platformContactId" class="select-1" >
							    <option value="">请选择...</option>
							    <c:forEach var="platformContact" items="${platformContactCustomList }">
							    	<option value="${platformContact.id }">${platformContact.contactName }</option>
							    </c:forEach>
							    <option value="status">取消此对接人</option>
							</select>
		                </td>
		            </tr>
		    	</tbody>
			</table>
		</div>
		<div align="center">
			<input type="button" class="l-button l-button-submit" value="确认更改" onClick="save();"/>
		</div>
	</form>
</body>

<script type="text/javascript">

	function save() {
		if($("#platformContactId").val() == "") {
			$.ligerDialog.warn("请选择对接人员！");
			return false;
		}else{
			$("#form-1").submit();
		}
	}
	
	function getPlatformContactList() {
		var contactType = $("#contactType").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/mcht/getPlatformContactList.shtml",
			data : {contactType : contactType},
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				if(data.statusCode != 200) {
					commUtil.alertError(data.message);
				}else {
					var str = "<option value=''>请选择...</option>";
					$.each(data.platformContactList, function(index, val){
						str += "<option value='" + val.id + "'>" + val.contactName + "</option>";
					});
					str += "<option value='status'>取消此对接人</option>";
					$("#platformContactId").html(str);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
	
</script>

</html>
