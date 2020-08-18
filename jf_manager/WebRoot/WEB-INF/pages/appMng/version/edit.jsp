<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body{ font-size:12px;padding:10px;}
.l-table-edit {}
.l-table-edit-td{ padding:4px;}
.l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
.l-verify-tip{ left:230px; top:120px;}
</style>

<script type="text/javascript">   
$(function () {
     $("#imageFile").change(function () {
         var filepath = $("input[name='imageFile']").val();
         $("#fileName").val(filepath);
     });
     
	$.metadata.setType("attr", "validate");
	
	var v = $("#form1").validate({
		
        errorPlacement: function (lable, element)
        {   
        	if($(element).hasClass("l-text-field")){
        		$(element).parent().ligerTip({
					content : lable.html(),width: 100
				});
        	}else{
        		$(element).ligerTip({
					content : lable.html(),width: 100
				});
        	}
        },
		
		success: function (lable,element)
		{
			lable.ligerHideTip();
			lable.remove();
		},
		submitHandler: function (form)
		{   
			var isValidateOk=true;
			if(${chnlType != 3}){
				$("#imageFile").ligerHideTip();
				if (checkfile()==1){
					isValidateOk=false;
				}
			}
			
	    	if(isValidateOk){
	    		form.submit();
	    	}else{
				$("html,body").animate({scrollTop:$("body").offset().top},0);
	    	}
		}
	});
});

function  checkAppVersion(){
  var flag=0;
   var appVersion =$("#appVersion").val(); 
   if(appVersion !="") {
   		var id=$.trim($("#id").val());
		jQuery.ajax({
			url : "${pageContext.request.contextPath}/appMng/version/check.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : { id : id,appVersion:appVersion},
			timeout : 30000,
			success : function(data){
				   if (data.returnCode!="0000") {
		    			$("#appVersion").ligerTip({ content: "版本已经存在"});
			  			flag= 1;
		  	  	   }
		  		},
				error : function() 
				{
					$.ligerDialog.error('操作超时，请稍后再试！');
			   		flag= 1;
				}
		  	});   
	   }
	  return flag;
}

function  checkAppVersionNo(){
  var flag=0;
   var appVersionNo =$("#appVersionNo").val(); 
   if(appVersionNo !="") {
   		var id=$.trim($("#id").val());
		jQuery.ajax({
			url : "${pageContext.request.contextPath}/appMng/version/checkNo.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : { id : id,appVersionNo:appVersionNo},
			timeout : 30000,
			success : function(data){
				if (data.returnCode!="0000") {
			  			$("#appVersionNo").ligerTip({ content: "版本号已经存在"});
			  			flag= 1;
		  	  	   }
		  		},
				error : function() 
				{
					$.ligerDialog.error('操作超时，请稍后再试！');
			   		flag= 1;
				}
		  	});   
	   }
	  return flag;
}

function checkfile(){
	 var flag=0;
	 var filepath = $("#fileName").val();
	 if($.trim(filepath)!=''){
	     var extStart = filepath.lastIndexOf(".");
	     var ext = filepath.substring(extStart, filepath.length);
	     if (ext != ".ipa" && ext != ".apk" ) {
			$("#imageFile").ligerTip({ content: "文件限于ipa,apk格式"});
	  	 	flag= 1;
	     }
	 }else{
	  flag= 1;
	 	   $("#imageFile").ligerTip({ content: "请上传文件！"});
	 }
	 return flag;
}
</script>       
<body>
<form method="post" id="form1" name="form1"	action="${pageContext.request.contextPath}/appMng/version/saveSubmit.shtml" enctype="multipart/form-data">
	<input id="id" name="id" type="hidden" value="${version.id}" />
	<input id="chnlType" name="chnlType" type="hidden" value="${chnlType}" />
	<c:if test="${chnlType==1}">
		<input id="sprChnl" name="sprChnl" type="hidden" value="1001" />
	</c:if>
	<c:if test="${chnlType==3}">
		<input id="sprChnl" name="sprChnl" type="hidden" value="1002" />
		<input id="isMust" name="isMust" type="hidden" value="0" />
	</c:if>
	<table  class="gridtable">
          <tr>
              <td class="title" >版本：</td>
              <td align="left" class="l-table-edit-td">
                	<input id="appVersion" name="appVersion" type="text" value="${version.appVersion}"  validate="{required:true,digits:true,maxlength:5}"/>
              </td> 
          </tr>
           <tr>
              <td class="title">版本号：</td>
              <td align="left" class="l-table-edit-td">
                  <input id="appVersionNo" name="appVersionNo" type="text" value="${version.appVersionNo }" validate="{required:true,maxlength:32}" />
		   </td> 
          </tr>
          
           <tr>
              <td class="title">APP类型</td>
              <td align="left" class="l-table-edit-td">
                  <select style="width: 150px;" id="appType" name="appType" validate="{required:true}">
					<c:if test="${chnlType==1 || chnlType==2}"><option selected="selected" value="2">安卓</option></c:if>
					<c:if test="${chnlType==3}"><option selected="selected" value="1">IOS</option></c:if>
				</select>
		   </td> 
          </tr>
          
          <c:if test="${chnlType==2}">
          <tr>
              <td class="title">推广渠道</td>
              <td align="left" class="l-table-edit-td">
                  <select style="width: 250px;" id="sprChnl" name="sprChnl" validate="{required:true}">
					<option value="">请选择</option>
					<c:forEach var="list" items="${sprChnls}">
						<c:if test="${list.statusValue!=1001 and list.statusValue!=1002 and list.statusValue!=9999}">	
							<option <c:if test="${version.sprChnl==list.statusValue}">selected</c:if> value="${list.statusValue}">${list.statusDesc}</option>
						</c:if>
				</c:forEach>
				</select>
		   </td> 
          </tr>
          </c:if>
          
          <tr>
              <td class="title">是否生效：</td>
              <td align="left" class="l-table-edit-td">
                <select style="width: 150px;" id="isEffect" name="isEffect" validate="{required:true}">
					<option value="">请选择</option>
					<c:forEach var="list" items="${isEffectList}">
					<option <c:if test="${version.isEffect==list.statusValue}">selected</c:if> value="${list.statusValue}">${list.statusDesc}</option>
				</c:forEach>
				</select>
		   </td>  
          </tr>
          
          <c:if test="${chnlType!=3}">
          <tr>
              <td class="title">是否必须：</td>
              <td align="left" class="l-table-edit-td">
				 <select style="width: 150px;" id="isMust" name="isMust" validate="{required:true}">
					<option value="">请选择</option>
					<c:forEach var="list" items="${isMustList}">
					<option <c:if test="${version.isMust==list.statusValue}">selected</c:if> value="${list.statusValue}">${list.statusDesc}</option>
				</c:forEach>
				</select>
			</td> 
          </tr>
          <tr>
          <tr>
			<td class="title" >文件</td> 
	        <td>
	        	<input class=button type='file' id="imageFile" name='imageFile' value="${version.packageUrl}">
	        </td> 
	   </tr>
	     <tr>
			<td  class="title" >文件名</td> 
	        <td>
	        	<div id="fileNameDiv">${version.packageUrl}<input type='hidden' id="fileName" value="${version.packageUrl}"></input></div> 
	        </td> 
	   </tr>
	   </c:if>
	   
          <tr>
              <td class="title">发布内容：</td>
              <td align="left" class="l-table-edit-td"> 
			  <textarea rows=5 cols=50 id="launchContent" name="launchContent" class="text" validate="{maxlength:512}">${version.launchContent}</textarea>										
              </td> 
          </tr> 
          <tr>
          
          <c:if test="${chnlType==3}">
          <tr>
              <td class="title" >提醒版本：</td>
              <td align="left" class="l-table-edit-td">
                	<input id="targetVersion" name="targetVersion" type="text" value="${version.targetVersion}"  validate="{required:true,digits:true,maxlength:5}"/>
              </td> 
          </tr>
          </c:if>
          
    <Td colspan="2"> 
	<div id="btnDiv">
		<input type="button" value="取消" class="l-button l-button-test" style="float:right;" onclick="frameElement.dialog.close();" /> 
		<input name="btnSubmit" type="submit" id="Button1" style="float:right;" class="l-button l-button-submit" value="保存" />
	</div>
	</Td>
	</tr>
	</table>
</form>
</body>
