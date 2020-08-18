<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
</style>
<script type="text/javascript">
$(function (){
    $("#form1").ligerForm();
});
//验证
$(function(){   
     var v = $("#form1").validate({
     submitHandler: function (form)
          {   
          	var isValidateOk=true;
          	var dealopinion = $.trim($("#dealOpinion").val());
      		var dealOpinion = document.getElementById("dealOpinion"); 	
      		var dealStatus = $("input[name='dealStatus']:checked").val();
             if ($.trim(dealStatus)=="2") {
            	           	 
          		if($.trim(dealOpinion.value)==""){
          		   $.ligerDialog.question('处理跟进/处理意见未填写，请填写后重新提交！');
      			  isValidateOk=false;
      		    }
          		if (dealopinion!="") {
          		if(!/^[\u4E00-\u9FA5]+$/.test(dealopinion)){
                    $("#dealOpinion").ligerTip({ content: "请输入中文!"});
                    isValidateOk=false;
                 } 			
			   }
				
          	  if(isValidateOk){
          		form.submit();
          	}
		  }
             
            if ($.trim(dealStatus)=="3") {	
		       form.submit();
			}
          		
          }
      });           
});

//不需要处理部分隐藏
function setNoVisibility(){  
    document.getElementById("deal").style.display = "none";  
    document.getElementById("related").style.display = "none";
} 
function setVisibility(){  
    document.getElementById("deal").style.display = "";  
    document.getElementById("related").style.display = ""; 
}  


</script>

<html>
<body>
	<form method="post" id="form1" name="form1"
		action="${pageContext.request.contextPath}/mcht/mchtfeedbackedits.shtml">
		<input id="id" name="id" type="hidden" value="${mchtFeedbacklist.id}" />
		<table class="gridtable">						
			<tr>
				<td class="title">处理跟进*</td>
				<td align="left" class="l-table-edit-td">
                <input type="radio" id="dealStatus" name="dealStatus" value="2"  onclick="setVisibility()"<c:if test="${mchtFeedbacklist.dealStatus=='1'}">checked="checked"</c:if>>已处理
                <input type="radio" id="dealStatus" name="dealStatus" value="3" onclick="setNoVisibility()"<c:if test="${mchtFeedbacklist.dealStatus=='3'}">checked="checked"</c:if>>不需要处理
                </td>
			</tr>
	
			 <tr id="deal">
				<td class="title">处理意见(必填)*</td>
				<td align="left" class="l-table-edit-td">
					<textarea id="dealOpinion" name="dealOpinion" rows="4" cols="45" class="text" maxlength="200" validate="{required:true,nameUnique:true}">${mchtFeedbacklist.dealOpinion}</textarea>
				</td>
			</tr>
			<tr id="related">
				<td class="title">相关订单(非必填)</td>
				<td align="left" class="l-table-edit-td">
					<input id="relatedOrder" name="relatedOrder" value="${mchtFeedbacklist.relatedOrder}" type="text" style="width:200px;" validate="{required:true,nameUnique:true}"/>
			</tr>
		  
            <tr>
				<td class="title">操作</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<div><input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button" value="提交" /></div>
					</div>
				</td>
			</tr>           
		</table>
	</form>
</body>
</html>