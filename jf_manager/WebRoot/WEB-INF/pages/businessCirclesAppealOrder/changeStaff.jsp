<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="/common/common-tag.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
      rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
        type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
        type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
      rel="stylesheet" type="text/css"/>
<script type="text/javascript">
	// 领取
    function changeStaff(id) {
    	var customerServiceHandler = $('#customerServiceHandler option:selected').val();
    	var id = $('#id').val();
        $.ajax({
            url : "${pageContext.request.contextPath}/businessCirclesAppealOrder/changeStaffId.shtml?",
            type : 'POST',
            dataType : 'json',
            data : {
            	id : id,
            	customerServiceHandler: customerServiceHandler
           	},
            cache : false,
            async : false,
            timeout : 30000,
            success : function(data) {
                if (data.code == '200') {
                    $.ligerDialog.success("操作成功",function() {
                    	parent.$("#searchbtn").click();
    					frameElement.dialog.close();
					});
                } else {
                    $.ligerDialog.error(data.message || '系统错误！');
                }
            },
            error : function() {
                $.ligerDialog.error('操作超时，请稍后再试！');
            }
        });
    }
</script>
<body>
<form method="post" id="form1" name="form1">
     <table class="gridtable">
     <input type="hidden" value="${id}" id="id">
		<tr>
             <td colspan="1" class="title" style="text-align: center;">处理人</td>
             <td colspan="1" class="title" style="text-align: center;">操作</td>
       </tr>
       <tr>
           <td colspan="1">
           		<select id="customerServiceHandler" name="customerServiceHandler" style="width: 135px;">
                  <option value="">请选择...</option>
                  <c:forEach items="${staffList}" var="staffInfo">
                      <option value="${staffInfo.customer_service_handler}">${staffInfo.staff_name}</option>
                  </c:forEach>
               	</select>
          	</td>
          	<td>
          		<button type="button" class="l-button l-button-submit" onclick="changeStaff()">提交</button>
          	</td>
       </tr>
     </table>
</form>
</body>
</html>
