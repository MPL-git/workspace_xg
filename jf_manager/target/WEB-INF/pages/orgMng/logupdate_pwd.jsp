<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>  

<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>


<style type="text/css">
    body{ font-size:12px;padding:10px;}
    .l-table-edit {}
    .l-table-edit-td{ padding:4px;}
    .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    .l-verify-tip{ left:230px; top:120px;}
</style>
   <script type="text/javascript">

var alert = function (content)
    {
     $.ligerDialog.alert(content);
    };
 $(function ()  { 
   // editPage.initEditPage(); 
    $.metadata.setType("attr", "validate");
            var v = $("form").validate({
                debug: true,
                errorPlacement: function (lable, element)
                {
                    if (element.hasClass("l-textarea"))
                    {
                        element.ligerTip({ content: lable.html(), target: element[0] }); 
                    }
                    else if (element.hasClass("l-text-field"))
                    {
                        element.parent().ligerTip({ content: lable.html(), target: element[0] });
                    }
                    else
                    {
                        lable.appendTo(element.parents("td:first").next("td"));
                    }
                },
                success: function (lable)
                {
                    lable.ligerHideTip();
                    lable.remove();
                },
                submitHandler: function ()
                {
                    $("form .l-text,.l-textarea").ligerHideTip();
                    var postForm=$("#postForm");
                    //commUtil.toDoAjaxForm(postForm);
                     var dform=$("#postForm");
				   //提交
				   $.ajax({
						type:'POST',
						url:dform.attr("action"),
						data:dform.serializeArray(),
						dataType:"json",
						cache: false,
						success: function(json){
						  if(json==null || json.statusCode!=200){
						    commUtil.alertError(json.message);
						  }else{
							  $.ligerDialog.waitting('修改成功,请重新登录,稍等...');
							  setTimeout(function () {javascript:window.location="${pageContext.request.contextPath}/logOut.shtml";},2000);
						  }
						},
						error: function(e){
						   commUtil.alertError("系统异常请稍后再试");
						}
					});
                }
            });
            $("form").ligerForm();
  });         
    </script>
<html>
	<body> 
<form id="postForm" method="post" action="${pageContext.request.contextPath}/orgMng/logsaveupdate_pwd.shtml">
      <table cellpadding="0" cellspacing="0" class="l-table-edit" align="center">
       <tr>
        <td align="left" class="l-table-edit-td" colspan="2">
               <font color="red">温馨提示：您的密码为初始密码123456，请先修改密码</font>
               </td>  
       </tr>
		 <tr>
		       
               <td align="right" class="l-table-edit-td">旧密码：</td>
               <td align="left" class="l-table-edit-td">
               <input  type="hidden" name="STAFF_ID"  id="STAFF_ID"  value="${STAFF_ID }" />
               
				  <input  type="password" name="OLD_PWD"  ltype="text"  validate="{required:true,maxlength:32}"  />
               </td>
               <td align="left"></td>
           </tr>
           <tr>
               <td align="right" class="l-table-edit-td">新密码：</td>
               <td align="left" class="l-table-edit-td">
                  <input name="NEW_PWD" type="password" ltype="text"  validate="{required:true,maxlength:32}" />
               </td>
               <td align="left"></td>
           </tr>
           <tr>
               <td align="right" class="l-table-edit-td">重复新密码：</td>
               <td align="left" class="l-table-edit-td">
				  <input name="RE_NEW_PWD" type="password"  ltype="text"  validate="{required:true,maxlength:32}" />
               </td>
               <td align="left"></td>
           </tr>
           <tr>
           <td colspan="2">
           <input type="submit" value="保存" id="subBtn" class="l-button l-button-submit"/> 
	    <input type="button" value="返回" class="l-button l-button-test"  onclick="javascript:history.back(-1);" /> 
	
           </td>
           </tr>
		</table>  
		   
    </form>
</body>
</html>
