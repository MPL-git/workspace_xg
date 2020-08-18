<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

$(function (){
   var html = [];
   if ('${memberLabelList}' == '') {
    html.push("<div class='memberLabel' style='display: inline-block;margin: 5px 0px;' >");
	html.push("<span><input type='text' class='labelName' name='labelName' value='' style='width:200px;' placeholder='限制为1-50个字' validate='{maxlength:50}'></span>");
	html.push("</div>");
	html.push("<br>");
	html.push("<span style='margin-left: 5px;' class='addmemberLabel' ><input type='button' style='color: #FFFFFF;background-color:#00BFFF;border: none;width: 70px;height: 20px;border-radius: 3px;cursor:pointer;' value='+增加标签' onclick='addRelevantmemberLabel();' ></span>");
   }else {
	   var memberLabelList ;
   	<c:if test="${not empty memberLabelList }">
   	      memberLabelList = ${memberLabelList };
	</c:if>
	for(var i=0;i<memberLabelList.length;i++) {
		    html.push("<div class='memberLabel' style='display: inline-block;margin: 5px 0px;' >");
		    html.push("<input type='hidden' id='memberLabelid' name='memberLabelid' value='"+memberLabelList[i].id+"'/>");
			html.push("<span><input type='text' class='labelName' name='labelName' value='"+memberLabelList[i].labelName+"' style='width:200px;' maxlength='50' placeholder='限制为1-50个字' validate='{maxlength:50}'></span>");
			html.push("</div>");
    	if(memberLabelList.length-1 != i) {
    		html.push("<br/>");
    	}else {
    		html.push("<br/>");
    		html.push("<span style='margin-left: 5px;' class='addmemberLabel' ><input type='button' style='color: #FFFFFF;background-color:#00BFFF;border: none;width: 70px;height: 20px;border-radius: 3px;cursor:pointer;' value='+增加标签' onclick='addRelevantmemberLabel();' ></span>");
			if(i != 0) {
	    		html.push("<span style='margin-left: 10px;' class='deladdmemberLabel' ><input type='button' style='color: #FFFFFF;background-color:#00BFFF;border: none;width: 70px;height: 20px;border-radius: 3px;cursor:pointer;' value='-删除' onclick='deladdmemberLabel();' ></span>");
    		}
    	}
	}
	   
}
   $("#labelnameList").html(html.join(""));
   
   
   
   $.metadata.setType("attr", "validate");
   $("#form1").validate({
 		errorPlacement : function(lable, element) {
 			var elementType=$(element).attr("type");

         	if($(element).hasClass("l-text-field")){
         		$(element).parent().ligerTip({
 					content : lable.html(),width: 100
 				});
         	}
         	else if('radio'==elementType){
         		var radioName=$(element).attr("expiryType");
         		$("input[type=radio][name="+radioName+"]:last").ligerTip({
 					content : lable.html(),width: 100
 				});
         	}
         	else{
         		$(element).ligerTip({
 					content : lable.html(),width: 100
 				});
         	}
 		},
 		success : function(lable) {
 			lable.ligerHideTip();
 			lable.remove();
 		},
 		submitHandler : function(form) {
 			 var labelName = $("[name='labelName']").val();
 			 if($.trim(labelName)== '') {
 	    	   $.ligerDialog.alert("标签不能为空！"); 
 	     	  return;
 	     	} 
 			 
 	     	var html=[];
 	     	$(".memberLabel").each(function(index, element){
 	     		if ($(this).find("[name='labelName']").val()!=null && $(this).find("[name='labelName']").val()!="") {
 	     	    html.push($(this).find("[name='memberLabelid']").val()+"_"+$(this).find("[name='labelName']").val());
 	     	    $("#ml").val(html.join(","));	
				}
    			
    		});
 	     	
     	    formSubmit();

 		}
 	});
    
});



function addRelevantmemberLabel() {	
	var html = [];
	html.push("<br/>");
	html.push("<div class='memberLabel' style='display: inline-block;margin: 5px 0px;' >");
	html.push("<span><input type='text' class='labelName' name='labelName' value='' style='width:200px;' maxlength='50' placeholder='限制为1-50个字' validate='{maxlength:50}'></span>");
	html.push("</div>");
	$(".memberLabel:last").after(html.join(""));
	if($(".deladdmemberLabel").length == 0) {
		$(".addmemberLabel").after("<span style='margin-left: 10px;' class='deladdmemberLabel' ><input type='button' style='color: #FFFFFF;background-color:#00BFFF;border: none;width: 70px;height: 20px;border-radius: 3px;cursor:pointer;' value='-删除' onclick='deladdmemberLabel();' ></span>");
	}
	
}


function deladdmemberLabel() {
	$.ligerDialog.confirm("删除标签后，系统将同步删除标签规则和已打标签，是否确认删除？", function (yes) { 
	    if(yes){
		  $(".memberLabel:last").prev().remove();
			 $(".memberLabel:last ").remove();
			 if($(".memberLabel").length == 1) {
				$(".deladdmemberLabel").remove();
		    }
		 }
	 });
}


function formSubmit() {
	$.ajax({
		url : "${pageContext.request.contextPath}/memberLabel/addmemberLabelTyle.shtml",
		type : 'POST',
		dataType : 'json',
		data : $("#form1").serialize(),
		success : function(data) {
			if ("0000" == data.returnCode) {
				$.ligerDialog.success(data.returnMsg);
				parent.location.reload();
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

</script>

</head>
	<body style="margin: 10px;">
	<form method="post" id="form1" name="form1" class="form1" action="" >
		<input type="hidden" id="id" name="id" value="${memberLabelType.id}"/>
		<input id="ml" name="ml" type="hidden" value="" />
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">标签类型<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="labelTypeName"  name="labelTypeName" type="text" style="width:200px;" value="${memberLabelType.labelTypeName}" validate="{required:true,maxlength:50}" placeholder="限制为1-50个字"/>
				</td>
			</tr>
             
	        <tr>
				<td class="title">标签<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
				    <p style="color: red;">温馨提示:可添加多个标签。</p>
					<div id="labelnameList" style="border-style: none;" >
						
					</div>
				</td>
			</tr>
			       	
	         <tr>
				<td class="title">内部备注</td>
				<td align="left" class="l-table-edit-td">
					<textarea rows="10" cols="50" id='remarks' name="remarks"  validate="{maxlength:100}" placeholder="限制为0-100个字">${memberLabelType.remarks}</textarea>
				</td>
			</tr>       
			 <tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="保存" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>