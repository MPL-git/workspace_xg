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
	var allSprChnls = ${sprChnlsJson};
	var sprChnlsArray = [];
	$("#appVersion").bind('blur',function(){
		var appVersion = $(this).val();
		if(!appVersion){
			return;
		}
		var reg = /^[+]{0,1}(\d+)$/;
		if(!reg.test(appVersion)){
			return;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/appMng/version/getSprChnlByAppVersion.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"appVersion":appVersion},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					var sprChnls = data.sprChnls;
					var addSprChnlArray = [];
					if(sprChnls.length>0){
						for(var i=0;i<allSprChnls.length;i++){
							var addSprChnl = allSprChnls[i].statusValue;
							var index = $.inArray(addSprChnl,sprChnls);
						    if(index == -1){
						    	addSprChnlArray.push(allSprChnls[i]);
						    }						
						}
					}else{
						addSprChnlArray = allSprChnls;
					}
					$("tr[name='sprChnlTr']").remove();
					var html = [];
					for(var i=0;i<addSprChnlArray.length;i++){
						if(addSprChnlArray[i].statusValue!=1001 && addSprChnlArray[i].statusValue!=1002 && addSprChnlArray[i].statusValue!=9999){
							html.push('<tr name="sprChnlTr" sprChnl='+addSprChnlArray[i].statusValue+'><td style="width: 220px;">'+addSprChnlArray[i].statusDesc+'</td><td><input class=button type="file" name="file" ><input type="hidden" name="filePath"></td></tr>');
						}
					}
					$("#btnTr").before(html.join(""));
					$("input[name='file']").change(function () {
				         var filepath = $(this).val();
				         $(this).next().val(filepath);
				    });
					
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
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
				$("input[name='file']").each(function(){
					$(this).ligerHideTip();
					if (checkfile(this)==1){
						isValidateOk=false;
						return;
					}
				});
			}
			var sprChnls="";
			$("input[name='filePath']").each(function(index){
				sprChnls += $(this).closest("tr").attr("sprChnl")+",";
			});
			if(!sprChnls){
				isValidateOk=false;
				commUtil.alertError("至少需要上传一个文件");
				return;
			}
			$("#sprChnls").val(sprChnls);
	    	if(isValidateOk){
	    		commUtil.alertSuccess("正在批量添加，请稍等");
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

function checkfile(_this){
	 var flag=0;
	 var filepath = $(_this).val();
	 if($.trim(filepath)!=''){
	     var extStart = filepath.lastIndexOf(".");
	     var ext = filepath.substring(extStart, filepath.length);
	     if (ext != ".ipa" && ext != ".apk" ) {
			$(_this).ligerTip({ content: "文件限于ipa,apk格式"});
	  	 	flag= 1;
	     }
	 }else{
//	  	   flag= 1;
//	 	   $(_this).ligerTip({ content: "请上传文件！"});
	 }
	 return flag;
}
</script>       
<body>
<form method="post" id="form1" name="form1"	action="${pageContext.request.contextPath}/appMng/version/batchSave.shtml" enctype="multipart/form-data">
	<input id="chnlType" name="chnlType" type="hidden" value="${chnlType}" />
	<input id="sprChnls" name="sprChnls" type="hidden" />
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
                	<input id="appVersion" name="appVersion" type="text"  validate="{required:true,digits:true,maxlength:5}"/>
              </td> 
          </tr>
           <tr>
              <td class="title">版本号：</td>
              <td align="left" class="l-table-edit-td">
                  <input id="appVersionNo" name="appVersionNo" type="text" validate="{required:true,maxlength:32}" />
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
          
          <tr>
              <td class="title">是否生效：</td>
              <td align="left" class="l-table-edit-td">
                <select style="width: 150px;" id="isEffect" name="isEffect" validate="{required:true}">
					<option selected value="0">否</option>
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
					<option value="${list.statusValue}">${list.statusDesc}</option>
				</c:forEach>
				</select>
			</td> 
          </tr>
	   	  </c:if>
	   
          <tr>
              <td class="title">发布内容：</td>
              <td align="left" class="l-table-edit-td"> 
			  	<textarea rows=5 cols=50 id="launchContent" name="launchContent" class="text" validate="{maxlength:512}"></textarea>										
              </td> 
          </tr>
          <tr>
              <td class="title" style="width: 220px;">推广渠道</td>
              <td class="title">文件</td> 
          </tr>
           
          
          <tr id="btnTr">
		    <td colspan="2"> 
			<div id="btnDiv">
				<input name="btnSubmit" type="submit" id="Button1" style="float:right;" class="l-button l-button-submit" value="保存" />
				<input type="button" value="取消" class="l-button l-button-test" style="float:right;" onclick="frameElement.dialog.close();" /> 
			</div>
			</td>
		  </tr>
	</table>
</form>
</body>
