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
  $.metadata.setType("attr", "validate");
  $("#form1").validate({
		errorPlacement : function(lable, element) {
     	$(element).ligerTip({recordContent : lable.html()});
		},
		success : function(lable) {
			lable.ligerHideTip();
			lable.remove();
		},
		submitHandler : function(form) {
		var type = $("#type").val();
	    var scene=$("#scene").val();
	    var description=$("#description").val();
	    var needLimit = $("input[name='needLimit']:checked").val();
	    var limitMemberAction = $("input[name='limitMemberAction']:checked").val();
	    var caseCloseDesc = $("select[name='caseCloseDesc']").val(); 
	    var caseCloseDesc1 = $("select[name='caseCloseDesc1']").val(); 
   		/* var commissionerInnerRemarks=$("#commissionerInnerRemarks").val(); */
   		
   		var filePathList=$("#filePathList").val();
   		if($.trim(type)== '') {
    		   $.ligerDialog.alert("举报类型不能为空！"); 
     		   return;
     	 }
   		if($.trim(scene)== '') {
   		   $.ligerDialog.alert("举报场景不能为空！"); 
    		   return;
    	}
   		$(".subOrde").each(function(index, element){
			if($(this).find("[name='orde']").val() == '') {
				$.ligerDialog.alert("相关订单不能为空！");
				return;
			}
			if(!/^[A-Za-z0-9]+$/.test($(this).find("[name='orde']").val()) ) {
				$.ligerDialog.alert("只能数字和字母！");
				return;
			} 
			
		});
       	if($.trim(description) == '') {
       		$.ligerDialog.alert("举报说明不能为空！");
       		return;
       	}
     
       	if($.trim(needLimit)=='') {
       		$.ligerDialog.alert("限制行为不能为空！");
   			return;
       	}
       	if ($.trim(needLimit)=='1' && $.trim(limitMemberAction)=='') {
       		$.ligerDialog.alert("限制用户行为选择不能为空！");
   			return;
		}
       	if ($.trim(needLimit)==1 && $.trim(caseCloseDesc)=='' ) {
       		$.ligerDialog.alert("结案说明不能为空！");
   			return;
		}
       	if ($.trim(needLimit)==0 && $.trim(caseCloseDesc1)=='') {
       		$.ligerDialog.alert("结案说明不能为空！");
   			return;
		}
       	/* if ($.trim(commissionerInnerRemarks)=='') {
       		$.ligerDialog.alert("内部备注不能为空！");
   			return;
		} */
       	var uploading = $("#uploading").val();
    	if(uploading == 1){
    		commUtil.alertError("举报文件上传中，请稍后再提交！");
			return;
    	}
    	
    	if (filePathList=='') {
        	$.ligerDialog.warn("请上传补充举证材料");
            return;
		}
    	
    	  formSubmit();

		}
	});
  
  $("#type").bind('change',function(){
		var type = $(this).val();
		$.ajax({
			url : "${pageContext.request.contextPath}/impeach/getimpeachMember.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"type":type},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					var sceneList = data.sceneList;
					var optionArray = [];
					optionArray.push('<option value="">请选择</option>');
					for(var i=0;i<sceneList.length;i++){
						var statusValue = sceneList[i].statusValue;
						var statusDesc = sceneList[i].statusDesc;
						optionArray.push('<option value="'+statusValue+'">'+statusDesc+'</option>');
					}
					$("#scene").html(optionArray.join(""));
				}else {
					var html=[];
					html.push('<option value="">请选择</option>');
					$("#scene").html(html.join(""));
				}
				
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
  
    var html = [];
    html.push("<div class='subOrde' style='display: inline-block;margin: 5px 0px;' >");
	html.push("<span><input type='text' class='orde' name='orde' value='' style='width:200px;'></span>");
	html.push("</div>");
	html.push("<span style='margin-left: 5px;' class='addsubOrde' ><input type='button' style='color: #FFFFFF;background-color: #FF8000;border: none;width: 25px;height: 20px;border-radius: 3px;cursor:pointer;' value='+' onclick='addRelevantSuborde();' ></span>");
	$("#subOrdeList").html(html.join(""));
	
});


function addRelevantSuborde() {
	var tmkFlag = false;
	$(".subOrde").each(function(index, element){
		if($(this).find("[name='orde']").val() == '' || !/^[A-Za-z0-9]+$/.test($(this).find("[name='orde']").val()) || $(".subOrde").length>=50) {
			tmkFlag = true;
			return false;
		}
	});
	
	if(tmkFlag) {
		$.ligerDialog.question("必须填写正确的订单号,才能新增下一条且只能增加50条！");
		return;
	} 
	var html = [];
	html.push("<br/>");
	html.push("<div class='subOrde' style='display: inline-block;margin: 5px 0px;' >");
	html.push("<span><input type='text' class='orde' name='orde' value='' style='width:200px;'></span>");
	html.push("</div>");
	$(".subOrde:last").after(html.join(""));
	if($(".delSubOrde").length == 0) {
		$(".addsubOrde").after("<span style='margin-left: 10px;' class='delSubOrde' ><input type='button' style='color: #FFFFFF;background-color: #FF8000;border: none;width: 25px;height: 20px;border-radius: 3px;cursor:pointer;' value='-' onclick='delSubOrde();' ></span>");
	}
	
}


function delSubOrde() {
	$(".subOrde:last").prev().remove();
	$(".subOrde:last ").remove();
	if($(".subOrde").length == 1) {
		$(".delSubOrde").remove();
	}
}

function need(value){
	if (value=='1') {
		document.getElementById("limitMemberAction").style.display='';
		document.getElementById("caseCloseDesc1").style.display="none";
		document.getElementById("caseCloseDesc").style.display='';
	}else if (value=='0') {
		document.getElementById("limitMemberAction").style.display="none";
		document.getElementById("caseCloseDesc").style.display="none";
		document.getElementById("caseCloseDesc1").style.display='';
	}
	
}





var fileS=[];
function ajaxFileUpload(obj) {
	/* var oFile = obj.files[0]; */
	if (obj.files.length === 0) {
			return;
	 }
    var file = obj.files[0];
    var fileName = file.name;
    var rFilter = ["jpg","bmp","png","gif","JPG","BMP","PNG","GIF","mp3","wav","wma","ogg","ape","acc","MP3","WAV","WMA","OGG","APE","ACC","avi","mp4","mov","rm","wma","mkv","rmvb","AVI","MP4","MOV","RM","WMA","MKV","RMVB","doc","docx","xls","xlsx","ppt","pptx","pdf","rar","zip","DOC","DOCX","XLS","XLSX","PPT","PPTX","PDF","RAR","ZIP"];
  /*   var filesize = file.size;
	var fileName = file.name;
	$("#filePathName").val(fileName);
	$("#filePathList").text(fileName);
    if (filesize>1024*1024*300) {
    	commUtil.alertError("视频大小不超过300M");
    	return false;
	} */
	var suffix = file.name.substring(file.name.lastIndexOf(".")+1);
	if($.inArray(suffix,rFilter)==-1){
		commUtil.alertError("文件格式不正确！");
		return false;
	}
	if(suffix=="MP4"||suffix=="mp4"||suffix=="avi"||suffix=="AVI"){
		if(file.size>100*1024*1024){
			commUtil.alertError("视频大小不能超过100M");
	        return false;
	    } 
	}
	var totalSize=0;
	 for(var i=0;i<fileS.length;i++){
		totalSize += fileS[i].size*1;
	}
	
	if(Number(totalSize)+Number(file.size)>400*1024*1024){
		commUtil.alertError("文件总大小不能超过400M");
		return false;
	}; 
	fileS.push(file);
	
    var reader = new FileReader();
    $("#uploading").val(1);
    reader.onload = function(e) {
    	$.ajaxFileUpload({
    		url: '${pageContext.request.contextPath}/service/common/fileUpload.shtml?fileType=8',
    		secureuri: false,
    		fileElementId: "uploadFile",
    		dataType: 'json',
    		success: function(result, status) {
    			if(result.RESULT_CODE == 0){
    			   var filePath = result.FILE_PATH;
    			   var html=[];
    			    html.push("<li>");
    				html.push("<p>");
    				html.push(fileName);
    			    html.push("&nbsp;"+"已上传"+"&nbsp;&nbsp;&nbsp;&nbsp;"+"<a href=\"javascript:void(0);\" onclick='delfilePath();' style='color: red;'>删除</a>"+"<br><br>");
    				html.push("</p>");
    				html.push("</li>");
    			   $("#filePath1").append(html.join(""));
    			   
    			   var html1=[];
    			   html1.push("<li>");
    			   html1.push(fileName);
    			   html1.push(filePath);
    			   html1.push("</li>");
    			   $("#filePath2").append(html1.join(""));
    			   var pictures = [];
    			   var lis = $("#filePath2").find("li");
    			   lis.each(function(index, item) {
    	    			pictures.push($(this).text());
    	    		});

    			   $("#filePathList").val(pictures);
    					    		     			   	
	               $("#uploading").val(0);
	               commUtil.alertSuccess("举报文件上传成功");
   			  	}else{
   			  		alert(result.RESULT_MESSAGE);
   			  	}
    		},
    		error: function(e) {
    			alert("服务异常");
    		}
    	});
    };
    reader.readAsDataURL(file);  
}


function delfilePath() {
	   if ($("#filePath1 li").length>1) {
	       $("#filePath1 li:last").prev().remove();
	   }else if ($("#filePath1 li").length==1) {
		    $("#filePath1 li").remove(); 	
	  }  
}



function formSubmit() {
	$.ajax({
		url : "${pageContext.request.contextPath}/imPeach/editimPeach.shtml",
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
	<form method="post" id="form1" name="form1" class="form1" action="${pageContext.request.contextPath}/imPeach/editimPeach.shtml" >
		<input type="hidden" id="id" name="id" value="" />
		<input type="hidden" id="filePathList" name="filePathList" value=""/>
		<input type="hidden" id="uploading" value="0"/>
		<table class="gridtable">
			<tr>
               <td class="title">举报类型<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
	               <select id="type" name="type" style="width: 200px;">
               			<option value="">请选择</option>
						<c:forEach var="impeachListMemberTypeList" items="${impeachListMemberTypeList}">
							<option value="${impeachListMemberTypeList.statusValue}" >${impeachListMemberTypeList.statusDesc}</option>
						</c:forEach>
               	   </select>
               </td>
			</tr> 
             <tr>
               <td class="title">举报场景<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<select id="scene" name="scene" style="width: 200px;">
               			<option value="">请选择</option>
               		</select>
               </td>
	        </tr>
	        <tr>
				<td class="title">相关订单<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<div id="subOrdeList" style="border-style: none;" >
						
					</div>
				</td>
			</tr>
	        <tr>
               <td class="title">举报说明<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<textarea rows="10" cols="100" id="description" name="description" maxlength="1024"></textarea>
               </td>
	        </tr>
	      <tr>
				<td class="title">举报凭证<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input style="position:absolute; opacity:0;" type="file" id="uploadFile" name="uploadFile" onchange="ajaxFileUpload(this);" />
					<input type="button" style="width: 80px;height: 25px;" value="上传凭证"><span style="color: #C0C0C0;font-size:13px">(请上传举报凭证，可上传图片、压缩包、视频文件。视频大小不超过100M)</span>
					<br>
					<br>
					<ul id=filePath1 style="color: red;"></ul>
					<ul id=filePath2 style="display: none;"></ul>					
				</td>
			</tr>
	        <tr>
               <td class="title">是否需限制行为<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="radio" name="needLimit" value="1" onchange="need(1)">是
               		&nbsp;&nbsp;&nbsp;
               		<input type="radio" name="needLimit" value="0" onchange="need(0)">否
               </td>
	        </tr>
	         <tr id="limitMemberAction">
               <td class="title">限制用户行为选择<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="checkbox" name="limitMemberAction" value="1" >&nbsp;限制评价
               		&nbsp;&nbsp;&nbsp;
               		<input type="checkbox" name="limitMemberAction" value="2" >&nbsp;限制下单
               		&nbsp;&nbsp;&nbsp;
               		<input type="checkbox" name="limitMemberAction" value="3" >&nbsp;限制登入
               </td>
	        </tr>
	        <tr id="caseCloseDesc">
               <td class="title">结案说明<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<select id="caseCloseDesc" name="caseCloseDesc" style="width: 300px;">
               			<option value="">请选择</option>
               			<c:forEach var="caseCloselist" items="${caseCloselist}">
               			    <c:if test="${caseCloselist.statusValue ne '4'}">
							<option value="${caseCloselist.statusValue}" >${caseCloselist.statusDesc}</option>
							</c:if>
						</c:forEach>
               		</select>
               </td>
	        </tr>
	        <tr id="caseCloseDesc1" style="display:none;">
               <td class="title">结案说明<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<select id="caseCloseDesc1" name="caseCloseDesc1" style="width: 300px;">
               			<option value="">请选择</option>
               			<c:forEach var="caseCloselist" items="${caseCloselist}">
               			     <c:if test="${caseCloselist.statusValue=='4'}">
							<option value="${caseCloselist.statusValue}" >${caseCloselist.statusDesc}</option>
							</c:if>
						</c:forEach>
               		</select>
               </td>
	        </tr> 	
	         <tr>
				<td class="title">内部备注</td>
				<td align="left" class="l-table-edit-td">
					<textarea rows="10" cols="100" id='commissionerInnerRemarks' name="commissionerInnerRemarks" maxlength="1024"></textarea>
				</td>
			</tr>       
			 <tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>