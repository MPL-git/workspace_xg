<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

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
<style type="text/css">
 	.radioClass {
		margin-right: 20px;
	}
 	.table-edit-activity-time div,
    .table-edit-activity-time span {
		display: inline-block;
		vertical-align: middle;
    }
    .activity-time {
    	margin-left: 50px;
    }
    .activity-hint {
    	color: #6B6B6B;
    }
</style>
<style type="text/css">
	.img-div {
		display: inline-block;
		vertical-align: middle;
	}
	.img-box {
		position: relative;
		width: 160px;
		height: 80px;
		margin-left: 10px;
		display: inline-block;
	}
	.img-pic {
		width: 100%;
		height: 100%;
		display: block;
	}
	.img-box:hover .top-box {
		display: block;
	}
	.top-box {
		display: none;
		position: absolute;
		top: 0;
		left: 0;
		z-index: 1;
		width: 100%;
		height: 20px;
		background: rgba(0, 0, 0, .5);
	}
	.top-box span {
		position: relative;
		float: right;
		width: 20px;
		height: 20px;
		border-radius: 10px;
		background: red;
		cursor: pointer;
	}
	.top-box span:after,
	.top-box span:before {
		position: absolute;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		content: '';
		width: 16px;
		height: 4px;
		margin: auto;
		background: white;
		border-radius: 2px;
		transform: rotate(45deg);
	}
	.top-box span:before {
		transform: rotate(-45deg);
	}
</style>
<script type="text/javascript">
	var submitFlag = true;
	var viewerPictures;
	var v;
	$(function(){
		
		
		var linkType = '${mallCategoryCustom.adLinkType }';
		var linkValue = '${mallCategoryCustom.adLinkValue }';

		$.ajax({
			url : "${pageContext.request.contextPath}/linkType/adInfo/getLinkTypeList.shtml?showType=${showType}",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : "",
			timeout : 30000,
			success : function(data) {
				var linkTypeLists = data.linkTypeList
				for(var i=0;i<linkTypeLists.length;i++){
					var id=linkTypeLists[i].linkType
					var name=linkTypeLists[i].linkTypeName
					if(id==linkType){
						$("#adLinkType").append('<option value="'+id+'" selected >'+name+'</option>')
					}else{
						$("#adLinkType").append('<option value="'+id+'" >'+name+'</option>')
					}
					
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
		
		
		
		 $("#adLinkType").change( function() { 
			 var linkType = $("#adLinkType").val();
		 	radioItem(linkType, "");	
		 })

		if(linkType != '') {
				radioItem(linkType, linkValue);
		}else{
				radioItem('1', '');
			
		}
		

		
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format: "yyyy-MM-dd hh:mm:ss",
			labelAlign : 'left',
			width : 160
		});
		
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		
		$.metadata.setType("attr", "validate");
		v = $("#form1").validate({
	        errorPlacement: function (lable, element) {  
	        	var elementType=$(element).attr("type");
	        	if('radio'==elementType) {
	        		var radioName=$(element).attr("name");
	        		$("input[type=radio][name="+radioName+"]:last").parent("span").ligerTip({
						content : lable.html(),width: 100
					});
	        	}else {
	        		$(element).ligerTip({
						content : lable.html(),width: 100
					});
	        	}
	        	$(".l-verify-tip-corner").css("z-index", "1001");
	        	$(".l-verify-tip-content").css("z-index", "1000");
	        },
	        success: function (lable,element) {
	            lable.ligerHideTip();
	            lable.remove();
	        	}
	        
	    }); 
		
	});

	

	function radioItem(linkType, linkValue) {
		switch (linkType) {
			case '1':
				$("#show1-1").html('会场ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="adLinkValue" name="adLinkValue" placeholder="请输入会场ID"  value="' + linkValue + '" validate="{digits:true,maxlength:9}"/>');
				break;
			case '2':
				$("#show1-1").html('活动ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="adLinkValue" name="adLinkValue" placeholder="请输入活动ID"  value="' + linkValue + '" validate="{digits:true,maxlength:9}"/>');
				break;
			case '3':
				$("#show1-1").html('商品ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="adLinkValue" name="adLinkValue" placeholder="请输入商品ID"  value="' + linkValue + '" validate="{digits:true,maxlength:12}"/>');
				break;
			case '4':
				$("#show1-1").html('URL<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="adLinkValue" name="adLinkValue" placeholder="请输入URL连接"  value="' + linkValue + '" validate="{digits:true,maxlength:120}"/>');
				break;
			case '5':
				$("#show1-1").html('URL');
				$("#show1-2").html('<input type="text" id="adLinkValue" name="adLinkValue" placeholder="无连接"    value="' + linkValue + '" />');
				break;
			case '6':
				if(linkValue == '')
					linkValue = 1;
				$("#show1-1").html('<span>频道<span class="required">*</span></span>');
				var linkIStr = '<select id="adLinkValue" name="adLinkValue" style="width: 160px;">';
				$.ajax({
					url : "${pageContext.request.contextPath}/linkType/adInfo/getLinkTypeList.shtml?showType="+14,
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : "",
					timeout : 30000,
					success : function(data) {
						var linkValueList = data.linkValueList
						for(var i=0;i<linkValueList.length;i++){
							var id=linkValueList[i].linkValue
							var name=linkValueList[i].linkValueName
							if(id==linkValue){
								linkIStr += '<option  value="'+id+'"   selected>'+name+'</option>'
							}else{
								linkIStr += '<option  value="'+id+'"  >'+name+'</option>'
							}
							
						}
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
				
				linkIStr += '</select>'
				$("#show1-2").html(linkIStr);
				break;
			case '7':
				$("#show1-1").html('自建页面ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="adLinkValue" name="adLinkValue" value="' + linkValue + '" placeholder="请输入自建页面ID" validate="{digits:true,maxlength:9}"/>');
				break;
			case '8':
				$("#show1-1").html('淘宝优选关键字<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="adLinkValue" name="adLinkValue"  value="' + linkValue + '"   placeholder="请输入淘宝优选关键字" validate="{digits:true,maxlength:9}"/>');
			  	break;
			case '9':
				$("#show1-1").html('<span>新品牌团<span class="required">*</span></span>');
				linkIStr = '<select name="adLinkValue" id="adLinkValue" style="width: 160px;"><option value="">请选择</option>';
				<c:forEach items="${brandteamTypes}" var="brandteamType">
				var _selected="";
				var brandteamTypeId = ${brandteamType.id};
				if(linkValue == brandteamTypeId){
					_selected = "selected";
				}
				linkIStr+='<option value="${brandteamType.id}"  '+_selected+'>${brandteamType.name}</option>';
				</c:forEach>
				linkIStr+='</select>';
				$("#show1-2").html(linkIStr);
				break;
			case '10':
				$("#show1-1").html('商家店铺<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="adLinkValue" name="adLinkValue"  placeholder="请输入商家序号" value="' + linkValue + '" validate="{maxlength:12}" />');
			  	break;
			case '11':
				
				$("#show1-1").html('<span>一级分类<span class="required">*</span></span>');
				linkIStr = '<select name="adLinkValue" id="adLinkValue" style="width: 160px;"><option value="">请选择</option>';
				<c:forEach items="${productTypes}" var="productType">
				var _selected="";
				var productTypeid = ${productType.id};
				if(linkValue == productTypeid){
					_selected = "selected";
				}
				linkIStr+='<option value="${productType.id}" '+_selected+'>${productType.name}</option>';
			    </c:forEach>
				linkIStr+='</select>';
				$("#show1-2").html(linkIStr);
				break;
			case '28':
				$("#show1-1").html('<span>商城一级分类<span class="required">*</span></span>');
				linkIStr = '<select name="adLinkValue" id="adLinkValue" style="width: 160px;"><option value="">请选择</option>';
				<c:forEach items="${mallCategorys}" var="mallCategory">
				var _selected="";
				var productTypeid = ${mallCategory.id};
				if(linkValue == productTypeid){
					_selected = "selected";
				}
				linkIStr+='<option value="${mallCategory.id}" style="width:" 160px" '+_selected+'>${mallCategory.categoryName}</option>';
			    </c:forEach>
				linkIStr+='</select>';
				$("#show1-2").html(linkIStr);
			  	break;
			case '29':
				$("#show1-1").html('优惠券ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="adLinkValue" name="adLinkValue" value="' + linkValue + '" placeholder="请输入优惠券ID" validate="{maxlength:120}" style="width:300px;"/>');
			  	break;
		}
	}
	
	
	
	//限制上传图片大小
	function ajaxFileUploadLogoAstrict(codeStr){
		var file = document.getElementById(codeStr+"File").files[0];  
        var reader = new FileReader();  
        reader.onload = function(e) { 
        	var image = new Image();
        	image.onload = function() { 
   				var imgWidth = '800';
				var imgHeight = '400';
				if(codeStr == 'adPic') {
					imgWidth = '1020';
					imgHeight = '360';
				}
				
				if(codeStr == 'adPic' && (this.width != imgWidth || this.height != imgHeight ) ) {
					commUtil.alertError("图片像素不是"+imgWidth+"*"+imgHeight+"像素！"); 
				}else{
               		ajaxFileUploadLogo(codeStr);
               	}       		
            };
            image.src = e.target.result;
        };
        reader.readAsDataURL(file);
    }
	
	//上传图片
	function ajaxFileUploadLogo(codeStr) {//上传图片
		$.ajaxFileUpload({
			url : contextPath + '/service/common/ajax_upload.shtml?fileType=9',
			secureuri : false,
			fileElementId : codeStr+"File",
			dataType : 'json',
			success : function(result, status) {
				if (result.RESULT_CODE == 0) {
					$("#"+codeStr+"Logo").attr("src", contextPath + "/file_servelt" + result.FILE_PATH);
					$("#"+codeStr).val(result.FILE_PATH);
					if($("#adPic").val() != '') {
						$("#del").attr("class", "top-box");
						$(".img-box").attr("style", "border: 1px solid #6B6B6B;");
						$(".adLinkType-tr").show();
						$(".adLinkValue-tr").show();
					}else {
						$(".adLinkType-tr").hide();
						$(".adLinkValue-tr").hide();
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
	
	//放大图片
	function viewerPic(imgPath){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		imgPath=imgPath.replace('_S', '');
			$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
			viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		viewerPictures.show();
	}
	
/* 	function updateAdLinkTypeStr(adLinkType) {
		if(adLinkType == '1') {
			$(".ad-link-type").html("会场ID");
		}else if(adLinkType == '2') {
			$(".ad-link-type").html("活动ID");
		}else if(adLinkType == '3') {
			$(".ad-link-type").html("商品ID");
		}else if(adLinkType == '4') {
			$(".ad-link-type").html("自定义页面");
		}else if(adLinkType == '5') {
			$(".ad-link-type").html("URL链接");
		}else if(adLinkType == '7') {
			$(".ad-link-type").html("商家店铺");
		}else {
			$(".ad-link-type").html("无链接");
		}
	} */
	
	//删除广告图
	function delLogPicImg(obj) {
		$(".adLinkType-tr").hide();
		$(".adLinkValue-tr").hide();
		var adPic = $("#adPic").val();
		$("#adPicLogo").attr("src", "");
		$("#del").attr("class", "");
		$(".img-box").attr("style", "border: 1px solid white;");
		$("#adPic").val("");
		$("#adPicFile").val("");
	}
	
	
	//验证商品ID
	function productCodeFun(code) {
		var flag = false;
		$.ajax({
			 type : 'POST',
			 url : "${pageContext.request.contextPath}/mallCategory/productCodeFun.shtml",
			 data : {productCode : code},
			 dataType : 'json',
			 async : false,
			 success : function(data){
				 if(data == null || data.code != 200)
					 commUtil.alertError(data.msg);
				 else{
					 if(data.productId != null && data.productId != '') {
						 $("#adLinkValueProductId").val(data.productId);
						 flag = true;
					 }
				 }
			 },
			 error : function(e) {
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
		return flag;
	}
	
	//店铺ID
	function mchtCodeFun(code) {
		var flag = false;
		$.ajax({
			 type : 'POST',
			 url : "${pageContext.request.contextPath}/mallCategory/mchtCodeFun.shtml",
			 data : {mchtCode : code},
			 dataType : 'json',
			 async : false,
			 success : function(data){
				 if(data == null || data.code != 200)
					 commUtil.alertError(data.msg);
				 else{
					 if(data.mchtId != null && data.mchtId != '') {
						 $("#adLinkValueMchtId").val(data.mchtId);
						 flag = true;
					 }
				 }
			 },
			 error : function(e) {
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
		return flag;
	}
	
	
	function commitSave(){
       	if($("#adPic").val() != '' && $("#adLinkType").val() != '' && $("#adLinkType").val() == '6' && $("#adLinkValue").val() == '' ) {
       		commUtil.alertError($(".ad-link-type").html()+"不能为空！"); 
       		return;
       	}
    	if($("#adPic").val() != '' && $("#adLinkType").val() != '' && $("#adLinkType").val() == '11' && $("#adLinkValue").val() == '' ) {
       		commUtil.alertError($(".ad-link-type").html()+"不能为空！"); 
       		return;
       	}
    	if($("#adPic").val() != '' && $("#adLinkType").val() != '' && $("#adLinkType").val() == '28' && $("#adLinkValue").val() == '' ) {
       		commUtil.alertError($(".ad-link-type").html()+"不能为空！"); 
       		return;
       	}
    	if($("#adPic").val() == '' ) {
       		commUtil.alertError("请上传图片！"); 
       		return;
       	}
   

	if(v.form()){
		var dataJson = $("#form1").serializeArray();
		
		$.ajax({
			method: 'POST',
			url: '${pageContext.request.contextPath}/mallCategory/addOrUpdateMallCategory.shtml',
			data: dataJson,
			dataType: 'json',
			cache : false,
			async : false,
			timeout : 30000,
			success:function(data){
				if(data.returnCode == '0000'){
					commUtil.alertSuccess("保存成功");
					setTimeout(function(){
						parent.location.reload();
						frameElement.dialog.close();
					},1000);
				}else{
					commUtil.alertError(data.returnMsg); 
				}
			},
			error:function(){
				commUtil.alertError("请求失败"); 
			}
		});	
	
	}
	
	
};
	
</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/mallCategory/addOrUpdateMallCategory.shtml" >
		<input type="hidden" id="id" name="id" value="${mallCategoryCustom.id }" >
		<input type="hidden" id="adLinkValueProductId" name="adLinkValueProductId" value="${mallCategoryCustom.adLinkValue }" >
		<input type="hidden" id="adLinkValueMchtId" name="adLinkValueMchtId" value="${mallCategoryCustom.adLinkValue }" >
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">一级分类名称<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:160px;" type="text" id="categoryName" name="categoryName" value="${mallCategoryCustom.categoryName }" validate="{required:true,minlength:2,maxlength:15}" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">绑定类目<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select style="width: 160px;" id="productType1Ids" name="productType1Ids" >
						<c:forEach var="productType" items="${productTypeList }" >
							<option value="${productType.id }" <c:if test="${mallCategoryCustom.productType1Ids == productType.id }">selected</c:if> >${productType.name }</option>
						</c:forEach>
					</select>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">广告图</td>
            	<div class="top-box"><span class="top-delete" onclick="delLogPicImg(this)"></span></div>
				<td align="left" class="l-table-edit-td" >
					<%-- <div style="width: 160px;height: 80px;"><img id="adPicLogo" style="width: 160px;height: 80px" alt="" src="${pageContext.request.contextPath}/file_servelt${mallCategoryCustom.adPic }" onclick="viewerPic(this.src);" ></div> --%>
					<div class="img-div img-logPicImg" >
						<div class="img-box" <c:if test="${not empty mallCategoryCustom.adPic }">style="border: 1px solid #6B6B6B;"</c:if><c:if test="${empty mallCategoryCustom.adPic }">style="border: 1px solid white;"</c:if> >
							<img id="adPicLogo" class="img-pic" alt="" src="${pageContext.request.contextPath}/file_servelt${mallCategoryCustom.adPic }" >
							<div <c:if test="${not empty mallCategoryCustom.adPic }">class="top-box"</c:if> id="del" >
								<span class="top-delete" onclick="delLogPicImg(this)"></span>
							</div>
						</div>
					</div>
	    			<div style="margin: 10px;" >
	    				<input style="position:absolute; opacity:0;width: 60px;" type="file" id="adPicFile" name="file" onchange="ajaxFileUploadLogoAstrict('adPic');" />
						<a href="javascript:void(0);" >上传图片</a>
	    			</div>
	    			<input id="adPic" name="adPic" type="hidden" value="${mallCategoryCustom.adPic }" >
				</td>
           	</tr>
			<tr class="adLinkType-tr" <c:if test="${empty mallCategoryCustom.adPic }">style="display: none;"</c:if> >
            	<td class="title" width="20%">活动类型</td>
				<td align="left" class="l-table-edit-td" >
				<!-- 	<select style="width: 160px;" id="adLinkType" name="adLinkType" onchange="updateAdLinkTypeStr(this.value);" > -->
					<select style="width: 160px;" id="adLinkType" name="adLinkType" >
						<%-- <option value="">请选择...</option>
						<c:forEach var="status" items="${statusList }" >
							<option value="${status.statusValue }" <c:if test="${mallCategoryCustom.adLinkType == status.statusValue }">selected</c:if> >${status.statusDesc }</option>
						</c:forEach> --%>
					</select>
				</td>
           	</tr>
			<tr class="adLinkValue-tr" <c:if test="${empty mallCategoryCustom.adPic }">style="display: none;"</c:if> >
            	<td class="title ad-link-type" id="show1-1" width="20%">
            		<%-- <c:if test="${empty mallCategoryCustom.adLinkType }">无连接</c:if>
            		<c:if test="${not empty mallCategoryCustom.adLinkType }">
	            		<c:forEach var="status" items="${statusList }" >
							<c:if test="${mallCategoryCustom.adLinkType == status.statusValue }">${status.statusDesc }<c:if test="${status.statusValue == '1' or status.statusValue == '2' or status.statusValue == '3' }">ID</c:if></c:if>
						</c:forEach>
            		</c:if> --%>
            	</td>
				<td align="left" id="show1-2" class="l-table-edit-td" >
					<%-- <c:if test="${mallCategoryCustom.adLinkType == '3' }">
						<input style="width:160px;" type="text" id="adLinkValue" name="adLinkValue" value="${mallCategoryCustom.productCode }"  />
					</c:if>
					<c:if test="${mallCategoryCustom.adLinkType == '7' }">
						<input style="width:160px;" type="text" id="adLinkValue" name="adLinkValue" value="${mallCategoryCustom.mchtCode }"  />
					</c:if>
					<c:if test="${mallCategoryCustom.adLinkType != '3' and mallCategoryCustom.adLinkType != '7' }">
						<input style="width:160px;" type="text" id="adLinkValue" name="adLinkValue" value="${mallCategoryCustom.adLinkValue }"  />
					</c:if> --%>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
				<input name="btnSubmit"  id="Button1" style="float:left;" class="l-button l-button-submit" value="提交" onclick="commitSave();"/>
					<!-- <input type="submit" class="l-button l-button-submit" value="提交" /> --> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
		<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
	</body>
</html>