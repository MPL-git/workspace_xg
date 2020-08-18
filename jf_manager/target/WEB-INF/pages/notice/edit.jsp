<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("NOTICE_CONTENT") != null ? request.getParameter("NOTICE_CONTENT") : "";
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
	<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
	
 <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
    <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

    <style type="text/css">
        body{ font-size:12px;padding:10px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>
    <script type="text/javascript">
    function save(){
    	 var i=0;
			 $("#labelNOTICE_TITLE").text(""); 
			 $("#labelNOTICE_LEVEL").text(""); 
			 $("#labelBGN_DATE").text(""); 
			 $("#labelEND_DATE").text("");  
			 $("#labelNOTICE_CONTENT").text("");  
			 if (($("#NOTICE_TITLE").val())=='')
				 {$("#labelNOTICE_TITLE").text("请填写信息");i=1 ;}
			if (($("#NOTICE_LEVEL").val())=='')
				 {$("#labelNOTICE_LEVEL").text("请选择信息");i=1 ;}  
			 if (($("#BGN_DATE").val())=='')
				 {$("#labelBGN_DATE").text("请填写信息");i=1 ;}
			if (($("#END_DATE").val())=='')
				 {$("#labelEND_DATE").text("请选择信息");i=1 ;} /* 
			if (($("#NOTICE_CONTENT").val())=='')
				 {$("#labelNOTICE_CONTENT").text("请选择信息");i=1 ;}  */
	 	if(i==1)
	 		{return;}
				 editor1.sync(); 
       $("#form1").submit();
       }
    var editor1;
    KindEditor.ready(function(K) {
		 editor1 = K.create('textarea[name="NOTICE_CONTENT"]', {
			cssPath : '${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css',
			uploadJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/upload_json.jsp',
			fileManagerJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
			}
			
		});
		prettyPrint();
	});
		</script>
<html>
	<body> 
	<form name="form1" id="form1" method="post" action="${pageContext.request.contextPath}/notice/success.shtml">
			<table  class="gridtable">
				<tr>
					<td  class="title" width="20%">
						公告标题
					</td>
					<td align="left" class="l-table-edit-td">
						<input id="NOTICE_ID" name="NOTICE_ID" type="hidden"
							value="${NOTICE_ID}" />
						<input name="TYPE" type="hidden" value="${TYPE}" />
						<input ID="NOTICE_TITLE" name="NOTICE_TITLE" type="text" value="${NOTICE_TITLE}"
							 style="float:left;width: 300px;"   />
						 <label id="labelNOTICE_TITLE" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
					</td> 
				</tr>
				<tr>
					<td class="title" width="20%">
						公告级别
					</td>
					<td align="left" class="l-table-edit-td">
						<select  style="float:left;width: 300px;" id="NOTICE_LEVEL"  
							name="NOTICE_LEVEL" class="querysel">
							<option value="">
								请选择
							</option>
							<c:forEach var="list" items="${NoticeLevel}">
								<option
									<c:if test="${NOTICE_LEVEL==list.STATUS_VALUE}">selected</c:if>
									value="${list.STATUS_VALUE}">
									${list.STATUS_DESC}
								</option>
							</c:forEach>
						</select>
						<label id="labelNOTICE_LEVEL" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
					</td>
</tr>
					<tr>
						<td class="title" width="20%">
							开始时间
						</td>
						<td align="left" class="l-table-edit-td">
							<input type="text" id="BGN_DATE" name="BGN_DATE"  	value="${BGN_DATE}" style="float:left;"/>
							<script type="text/javascript">
							$(function() {
								$("#BGN_DATE").ligerDateEditor( {
									showTime : false,
									labelWidth : 300,
									width:300,
									labelAlign : 'left'
								});
							});
							</script>
							<label id="labelBGN_DATE" style="float:left;margin:0 0 0 10px;color: #FF0000">
						</td> 
					</tr>
					<tr>
						<td class="title" width="20%">
							结束时间
						</td>
						<td align="left" class="l-table-edit-td">
							<input type="text" id="END_DATE" name="END_DATE" 
								 style="float:left;" value="${END_DATE}" />
							<script type="text/javascript"> 
								$(function() {
								$("#END_DATE").ligerDateEditor( {
									showTime : false,
									labelWidth : 300,
									width:300,
									labelAlign : 'left'
								});
							});
						</script>
						<label id="labelEND_DATE" style="float:left;margin:0 0 0 10px;color: #FF0000">
						</td> 
					</tr>
					<tr>
						<td class="title" width="20%">
							公告内容 
						</td>
						<td align="left" class="l-table-edit-td">
							<textarea name="NOTICE_CONTENT" id="NOTICE_CONTENT" style="width:150px;height:300px;visibility:hidden;"><%=htmlspecialchars(htmlData)%>${NOTICE_CONTENT}</textarea>
               <label id="labelNOTICE_CONTENT" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
              </td>  
					</tr>
		 <tr> 
		 <td  colspan="2"> 
	<input type="button" value="取消" class="l-button l-button-test" style="float:right;" onclick="frameElement.dialog.close();" /> 
					<input name="btnSubmit" type="button" id="Button1" style="float:right;" class="l-button l-button-submit" value="保存" onClick="save();" />
		
			</td>
			</tr>  
			</table>
		</form>
	</body>
</html> 
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>