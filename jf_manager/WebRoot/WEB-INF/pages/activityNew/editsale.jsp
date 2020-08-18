<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改特卖</title>
    <link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
	  <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

.radioClass{
	margin-right: 20px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
</style>
<style type="text/css">
.middle input {
	display: block;
	width: 30px;
	margin: 2px;
}
table.l-checkboxlist-table td{
	border-style: none;
}
table.l-listbox-table td{
	border-style: none;
}
</style>
<script type="text/javascript">
	var subFlag = true;
	function ajaxFileUploadLogo(codeStr) {//上传图片
		$.ajaxFileUpload({
			url : contextPath + '/service/common/ajax_upload.shtml?fileType=7',
			secureuri : false,
			fileElementId : codeStr+"File",
			dataType : 'json',
			success : function(result, status) {
				if (result.RESULT_CODE == 0) {
					$("#"+codeStr+"Logo").attr("src", contextPath + "/file_servelt" + result.FILE_PATH);
					$("#"+codeStr).val(result.FILE_PATH);
					if($("#productWkPic").val() != '') {
						$(".productWkLinkId-div").show();
						$(".productWkPosition-div").show();
					}else {
						$(".productWkLinkId-div").hide();
						$(".productWkPosition-div").hide();
					}
					if($("#priceWkPic").val() != '') {
						$(".priceFontColor-div").show();
					}else {
						$(".priceFontColor-div").hide();
					}
				} else {
					alert(result.RESULT_MESSAGE);
				}
			},
			error : function(result, status, e) {
				alert("服务异常");
			}
		});

	}
	
	//限制上传图片大小
	function ajaxFileUploadLogoAstrict(codeStr){
		var file = document.getElementById(codeStr+"File").files[0];
		var size = file.size;
        if(size > 200*1024 ) {
            document.getElementById(codeStr+"File").setAttribute("type", "text");
            document.getElementById(codeStr+"File").setAttribute("type", "file");
        	commUtil.alertError("图片过大，请重新上传！");
            return;
        }
        var reader = new FileReader();  
        reader.onload = function(e) { 
        	var image = new Image();
        	image.onload = function() { 
   				var imgWidth = '800';
				var imgHeight = '400';
				if(codeStr == 'signPic') {
					imgWidth = '800';
					imgHeight = '400';
				}else if(codeStr == 'productWkPic') {
					imgWidth = '800';
					imgHeight = '800';
				}else if(codeStr == 'productWkPicM') {
					imgWidth = '320';
					imgHeight = '320';
				}else if(codeStr == 'priceWkPic') {
					imgWidth = '1242';
					imgHeight = '182';
				}
				if(codeStr == 'productWkPic' && (this.width > imgWidth || this.height > imgHeight ) ) {
                    document.getElementById(codeStr+"File").setAttribute("type", "text");
                    document.getElementById(codeStr+"File").setAttribute("type", "file");
					commUtil.alertError("图片像素不能大于"+imgWidth+"*"+imgHeight+"px");
					return;
				}else if(codeStr != 'productWkPic' && (this.width != imgWidth || this.height != imgHeight) ) {
					document.getElementById(codeStr+"File").setAttribute("type", "text");
					document.getElementById(codeStr+"File").setAttribute("type", "file");
					commUtil.alertError("图片像素不是"+imgWidth+"*"+imgHeight+"px");
					return;
               	}else{
               		ajaxFileUploadLogo(codeStr);
               	}       		
            };
            image.src = e.target.result;
        };
        reader.readAsDataURL(file);
    }
        
    //验证
   	$(function(){   
        var v = $("#form1").validate({
        submitHandler: function (form) {   
         		var groupName = document.getElementById("groupName");
         		var signPic = document.getElementById("signPic");
				var reg = new RegExp("^[1-9]\\d*$");
				if(!reg.test($("#productWkLinkId").val()) && $("#productWkLinkId").val()!=""){
					commUtil.alertError("主会场ID只能输入整数");
					return;
				}
         		if($.trim(groupName.value) == "") {
         			$("#groupName").ligerTip({ content: "该字段不能为空。"});
         			return;
         		}
         		if($.trim(signPic.value) == "") {
         			$.ligerDialog.question('请上传图片！');
         			return;
         		}
	          	if($("#priceWkPic").val() != '' && $("#priceFontColor").val() == '' ) {
	          		commUtil.alertError("字体色号不能为空！"); 
	          		return;
	          	}
	          	if(!/^#[a-zA-Z0-9]{6}$/.test($("#priceFontColor").val())) {
          			commUtil.alertError("请输入正确7位字体色号！"); 
          			return;
          		}
			$.ajax({
				url: "${pageContext.request.contextPath}/activityNew/isActivityAreaId.shtml",
				type: 'POST',
				dataType: 'json',
				data: $("#form1").serialize(),
				success: function (data) {
					if ("0000" == data.returnCode) {
						if(subFlag) {
							subFlag = false;
							form.submit();
						}
					}else {
						commUtil.alertError("会场不存在！！！");
						return;
					}
				},
				error: function () {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});

             }
         });           
  	}); 
	     
</script>

 </head>
  <body>
 <form method="post" id="form1" name="form1"
		action="${pageContext.request.contextPath}/activityNew/editsales.shtml"  >
		<input id="activityGroupId" name="activityGroupId" type="hidden" value="${activityGroup.id}" />
		<table class="gridtable">
			<tr>
				<td class="title">分组名称 <span class="required">*</span></td>
				<td align="left" class="l-table-edit-td"><input id="groupName" 
					name="groupName" type="text" value="${activityGroup.groupName }"
					style="float:left;width: 200px;" validate="{required:true,nameUnique:true}" />
			</tr>
			<tr>
				<td class="title">入口图彩标 <span class="required">*</span></td>
				<td>
	    			<div style="width: 160px;height: 80px;"><img id="signPicLogo" style="width: 160px;height: 80px" alt="" src="${pageContext.request.contextPath}/file_servelt${activityGroup.signPic}"></div>
	    			<div style="float: left;margin: 10px;">
	    				<input style="position:absolute; opacity:0;width: 60px;" type="file" id="signPicFile" name="file" onchange="ajaxFileUploadLogoAstrict('signPic');" />
						<a href="javascript:void(0);" >上传图片</a>
						<span style="color: gray;margin-left: 10px;">图片尺寸为800*400px，大小不超过200kb</span>
	    			</div>
	    			<input id="signPic" name="signPic" type="hidden" value="${activityGroup.signPic}">
	    		</td>
			</tr>     
			<tr>
				<td rowspan="3" class="title">商品彩标（详情页） </td>
				<td>
	    			<div style="width: 80px;height: 80px;"><img id="productWkPicLogo" style="width: 80px;height: 80px" alt="" src="${pageContext.request.contextPath}/file_servelt${activityGroup.productWkPic}"></div>
	    			<div style="float: left;margin: 10px;">
	    				<input style="position:absolute; opacity:0;width: 60px;" type="file" id="productWkPicFile" name="file" onchange="ajaxFileUploadLogoAstrict('productWkPic');" />
						<a href="javascript:void(0);" >上传图片</a>
						<span style="color: gray;margin-left: 10px;">图片尺寸不超过800*800px，大小不超过200kb</span>
	    			</div>
	    			<input id="productWkPic" name="productWkPic" type="hidden" value="${activityGroup.productWkPic}">
	    		</td>
			</tr>
			<tr>
				<td class="productWkLinkId-div" <c:if test="${empty activityGroup.productWkPic}">style="display: none;"</c:if> >
    				<span>主会场ID</span>
    				<input type="text" name="productWkLinkId" id="productWkLinkId" class="productWkLinkId" value="${activityGroup.productWkLinkId}" />
    				<span style="color: gray;">请输入主会场ID</span>
				</td>
			</tr>     
			<tr>
				<td class="productWkPosition-div" <c:if test="${empty activityGroup.productWkPic}">style="display: none;"</c:if> >
   					<span>对齐方式</span>
   					<label style="margin: 0px 10px;"><input type="radio" name="productWkPosition" id="productWkPosition" class="productWkPosition" value="1" <c:if test="${activityGroup.productWkPosition == '1' }">checked="checked"</c:if> />左上角</label>
   					<label style="margin: 0px 10px;"><input type="radio" name="productWkPosition" id="productWkPosition" class="productWkPosition" value="4" <c:if test="${activityGroup.productWkPosition == '4' }">checked="checked"</c:if> />左下角</label>
   					<label style="margin: 0px 10px;"><input type="radio" name="productWkPosition" id="productWkPosition" class="productWkPosition" value="2" <c:if test="${activityGroup.productWkPosition == '2' }">checked="checked"</c:if> />右上角</label>
   					<label style="margin: 0px 10px;"><input type="radio" name="productWkPosition" id="productWkPosition" class="productWkPosition" value="3" <c:if test="${activityGroup.productWkPosition == '3' }">checked="checked"</c:if>  <c:if test="${empty activityGroup.productWkPosition }">checked="checked"</c:if> />右下角</label>
					<!-- <p style="margin: 5px 0px;"><a href="javascript:void(0);" >查看示例</a></p> -->
				</td>
			</tr> 
			<tr>
				<td class="title">商品彩标（列表页） </td>
				<td>
	    			<div style="width: 80px;height: 80px;"><img id="productWkPicMLogo" style="width: 80px;height: 80px" alt="" src="${pageContext.request.contextPath}/file_servelt${activityGroup.productWkPicM}"></div>
	    			<div style="float: left;margin: 10px;">
	    				<input style="position:absolute; opacity:0;width: 60px;" type="file" id="productWkPicMFile" name="file" onchange="ajaxFileUploadLogoAstrict('productWkPicM');" />
						<a href="javascript:void(0);" >上传图片</a>
						<span style="color: gray;margin-left: 10px;">图片尺寸为320*320px，大小不超过200kb</span>
	    			</div>
	    			<!-- <div style="float: left;margin: 10px;">
						<a href="javascript:void(0);" >查看示例</a>
					</div> -->
	    			<input id="productWkPicM" name="productWkPicM" type="hidden" value="${activityGroup.productWkPicM}">
	    		</td>
			</tr>    
			<tr>
				<td rowspan="2" class="title">价格彩标</td>
				<td>
	    			<div style="width: 160px;height: 80px;"><img id="priceWkPicLogo" style="width: 160px;height: 80px" alt="" src="${pageContext.request.contextPath}/file_servelt${activityGroup.priceWkPic}"></div>
	    			<div style="float: left;margin: 10px;">
	    				<input style="position:absolute; opacity:0;width: 60px;" type="file" id="priceWkPicFile" name="file" onchange="ajaxFileUploadLogoAstrict('priceWkPic');" />
						<a href="javascript:void(0);" >上传图片</a>
						<span style="color: gray;margin-left: 10px;">图片尺寸为1242*182px，大小不超过200kb</span>
	    			</div>
	    			<input id="priceWkPic" name="priceWkPic" type="hidden" value="${activityGroup.priceWkPic}">
	    		</td>
			</tr>
			<tr>
				<td class="priceFontColor-div" <c:if test="${empty activityGroup.priceWkPic}">style="display: none;"</c:if> >
   					<span>字体色号</span>
    				<input type="text" name="priceFontColor" id="priceFontColor" class="priceFontColor" <c:if test="${empty activityGroup.priceWkPic }">value="#333333"</c:if><c:if test="${not empty activityGroup.priceWkPic }">value="${activityGroup.priceFontColor }"</c:if> />
    				<span style="color: gray;">请输入7位色号</span>
				</td>
			</tr>     
            <tr>
				<td class="title">操作</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<div><input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button" value="提交" /></div>
	               		<div><input name="btnCancle" type="button" id="Button2" style="float:left; margin-left: 40px" class="l-button" value="取消" onclick="frameElement.dialog.close();" /></div>
					</div>
				</td>
			</tr>           
		</table>
	</form>
</body>
</html>
