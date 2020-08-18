<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body{ font-size:12px;padding:10px;}
.l-table-edit {}
.l-table-edit-td{ padding:4px;}
.l-button-submit,.l-button-test{width:80px; margin-left:10px; padding-bottom:2px;}
.l-verify-tip{ left:230px; top:120px;}
</style>

<script type="text/javascript">   
$(function () {   
	$.metadata.setType("attr", "validate");
	
	var v = $("#form1").validate({
		
        errorPlacement: function (lable, element)
        {   
        	$(element).ligerTip({
				content : lable.html(),width: 100
			});
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
<body>
<form method="post" id="form1" name="form1"	action="${pageContext.request.contextPath}/activity/singleActivityProductQuitSubmit.shtml" enctype="multipart/form-data">
	<table  class="gridtable">
           <tr>
              <td class="title">商品ID：</td>
              <td align="left" class="l-table-edit-td">
                  <input id="productId" name="productId" type="text" validate="{required:true,maxlength:8}" />
		   </td> 
          </tr>
         
          <tr> 
    		<td colspan="2">
				<div style="text-align:center;vertical-align:middle;">
					<input name="btnSubmit" type="submit" id="Button1" class="l-button l-button-submit" value="提交" />
					<input type="button" value="取消" class="l-button l-button-test" onclick="frameElement.dialog.close();" /> 
				</div>
			</td>
		  </tr>
	</table>
</form>
</body>
