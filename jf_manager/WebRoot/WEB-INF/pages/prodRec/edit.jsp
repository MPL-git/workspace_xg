<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
 <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
	<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
 
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
		 function onSubmit() {
			var ret = {RESULT_CODE: 1};  //默认为错误 
			var id = $.trim($("#CATALOG_ID").val());
			var name = $.trim($("#CATALOG_NAME").val()); 
			var desc = $.trim($("#CATALOG_DESC").val());
			if(name == "") {
				ret["RESULT_MESSAGE"] = "栏目名称不能为空!";
				return ret;
			}
			
			if(desc == "") {
				ret["RESULT_MESSAGE"] = "栏目描述不能为空!";
				return ret;
			}
			window.parent.$("#pageloading").show();
			$.ajax({
				type: "POST",
				async: false,
				url: "${pageContext.request.contextPath}/prodRec/saveedit.shtml",
				data: {CATALOG_ID: id,CATALOG_NAME:name, CATALOG_DESC: desc},
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
		 
</script>
	</head> 
<body>
		 
			<table   class="gridtable">
				<tr>
					<td  class="title">
						栏目名称：
					</td>
					<td align="left" class="l-table-edit-td">
						<input id="CATALOG_ID" name="CATALOG_ID"type="hidden"
							value="${CATALOG_ID}" /> 
				 <input id="CATALOG_NAME" name="CATALOG_NAME" type="text" size="30" value="${CATALOG_NAME}" /> 
					</td> 
				</tr>
				 
				<tr>
					<td  class="title">
						栏目描述：
					</td>
					<td align="left" class="l-table-edit-td">
						<input id="CATALOG_DESC" name="CATALOG_DESC"  type="text" size="30" value="${CATALOG_DESC}" />
					</td> 
				</tr>
  
			<tr> 
			</table> 
	</body>
</html> 