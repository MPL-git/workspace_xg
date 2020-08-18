<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	$(function(){
		$("#autoUpDate").ligerDateEditor( {
			showTime : true,
			width: 155,
			format: "yyyy-MM-dd hh:mm"
		});
		
		var linkType="";
		<c:if test="${not empty adInfo }">
			linkType = ${adInfo.linkType};
		</c:if>
		var linkIStr = "";
		$.ajax({
			url : "${pageContext.request.contextPath}/linkType/adInfo/getLinkTypeList.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			success : function(data) {
					var linkTypeList = data.linkTypeList;
					linkIStr = '<select name="linkType" id="linkType" style="width: 160px;" onClick="radioItem(this.value, '+""+')">';
					for(var i=0;i<linkTypeList.length;i++){
						var id = linkTypeList[i].linkType;
						var name = linkTypeList[i].linkTypeName;
//							if(id != '6' && id != '7' ){
								<c:if test="${empty adInfo}">
									linkIStr+='<option  value="'+id+'" <c:if test="${id == 1}">selected</c:if>>'+name+'</option>';
		                		</c:if>
		                		<c:if test="${not empty adInfo }">
			                		if (linkType==id) {
										linkIStr+='<option value="'+id+'" selected>'+name+'</option>';
									}else{
									   linkIStr+='<option value="'+id+'">'+name+'</option>';
									}
		    					</c:if>
//							}
					}
					linkIStr+='</select>';				
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
		$("#linkTypeTd").html(linkIStr);
		
		
			var linkType = '${adInfo.linkType}';
			var linkId = '${adInfo.linkId }';
			var linkUrl = '${adInfo.linkUrl}';
			if(linkType != '') {
				if( linkType == 3 ||  linkType == 4 || linkType == 8 || linkType == 10 || linkType == 13 || linkType == 14 || linkType == 15 || linkType == 16 || linkType == 17
						|| linkType == 18 || linkType == 19 || linkType == 20 || linkType == 21 || linkType == 22 || linkType == 23 
						|| linkType == 24 || linkType == 25 || linkType == 26 || linkType == 27) {
					radioItem(linkType, linkUrl);
				}else{
					radioItem(linkType, linkId);
				}
			}else{
				radioItem('1', '');
			}
		
		
		$.metadata.setType("attr", "validate");
		var v = $("#form1").validate({
			errorPlacement: function (lable, element) {   
	        	var elementType = $(element).attr("type");
	        	if($(element).hasClass("l-text-field")){
	        		$(element).parent().ligerTip({
						content : lable.html(),width: 100
					});
	        	}else if('radio'==elementType){
	        		var radioName=$(element).attr("name");
	        		$("input[type=radio][name="+radioName+"]:last").ligerTip({
						content : lable.html(),width: 100
					});
	        	}else{
	        		$(element).ligerTip({
						content : lable.html(),width: 100
					});
	        	}
			},
			success: function (lable,element){
				lable.ligerHideTip();
				lable.remove();
			},
			submitHandler: function (form){  
				var autoUpDate = $("#autoUpDate").val();
				var pic = $("#pic").val();
				var linkType = $("input:checked[name='linkType']").val();
				var linkId = $("#linkId").val();
				var linkUrl = $("#linkUrl").val();
				if($.trim(autoUpDate) == '') {
					commUtil.alertError("上架时间不能为空！");
					return;
				}
				if($.trim(pic) == '') {
					commUtil.alertError("请上传图片！");
					return;					
				}
				if(linkType == '1' || linkType == '2' || linkType == '6' || linkType == '7' || linkType == '9' 
					|| linkType == '11' || linkType == '12' || linkType == '28' || linkType == '29') {
				var linkId = $("#linkId").val();
				var flag = linkId.match(/^-?\d+$/);
				if(linkId == '' || flag == null) {
					commUtil.alertError("ID不能为空，且必须为整数！");
					return;
				}
				updateLinkId(linkId);
			}else if(linkType == '3'){//3.商品
				var linkUrl = $("#linkUrl").val();
				if(!$.trim(linkUrl)) {
					commUtil.alertError("商品ID不能为空！");
					return;
				}
			}else{
				if(!$.trim(linkUrl)) {
					if(linkType == '4'){
						commUtil.alertError("URL不能为空！");
						return;
					}else if(linkType == '8'){
						commUtil.alertError("微淘关键字不能为空！");
						return;
					}else if(linkType == '10'){
						commUtil.alertError("商家店铺序号不能为空！");
						return;
					}
				}
				updateLinkId(linkUrl);
			}
		    	form.submit();
			}
		}); 
		
	});
   

	
	function radioItem(linkType, linkId) {
		if(!linkId){
			linkId="";
		}
		switch (linkType){
			case '1':
				$("#linkT").html('<span>会场ID<span class="required">*</span></span>');
				$("#linkI").html('<input type="text" id="linkId" name="linkId" placeholder="请输入会场ID" value="' + linkId + '" onChange="updateLinkId(this.value)" />');
				break;
			case '2':
				$("#linkT").html('<span>活动ID<span class="required">*</span></span>');
				$("#linkI").html('<input type="text" id="linkId" name="linkId" placeholder="请输入活动ID" value="' + linkId + '" onChange="updateLinkId(this.value)" />');
				break;
			case '3':
				$("#linkT").html('<span>商品ID<span class="required">*</span></span>');
				$("#linkI").html('<input type="text" id="linkId" name="linkUrl" placeholder="请输入商品ID" value="' + linkId + '" onChange="updateLinkId(this.value)" />');
				break;
			case '4':
				$("#linkT").html('<span>URL<span class="required">*</span></span>');
				$("#linkI").html('<input type="text" id="linkUrl" placeholder="请输入URL连接" name="linkUrl" value="' + linkId + '" />');
				break;
			case '5':
				$("#linkT").html('<span>URL</span>');
				$("#linkI").html('<input type="text" id="linkUrl" placeholder="无连接" name="linkUrl" value="' + linkId + '" />');
				break;
			case '6':
				$("#linkT").html('<span>频道<span class="required">*</span></span>');					
					$.ajax({
						url : "${pageContext.request.contextPath}/linkType/adInfo/getLinkTypeList.shtml",
						type : 'POST',
						dataType : 'json',
						cache : false,
						async : false,
						success : function(data) {
								var linkValueList = data.linkValueList;
								linkIStr = '<select name="linkId" id="linkId"><option value="">请选择</option>';
								for(var i=0;i<linkValueList.length;i++){
									var id = linkValueList[i].linkValue;
									var name = linkValueList[i].linkValueName;
									if (linkId==id) {
										linkIStr+='<option value="'+id+'" selected>'+name+'</option>';
									}else{
									   linkIStr+='<option value="'+id+'">'+name+'</option>';
										
									}
										
								}
								linkIStr+='</select>';				
						},
						error : function() {
							$.ligerDialog.error('操作超时，请稍后再试！');
						}
					});	
					
				$("#linkI").html(linkIStr);
				break;
				
			case '7':
				$("#linkT").html('<span>自建页面</span>');
				$("#linkI").html('<input type="text" id="linkId" placeholder="请输入自建页面ID" name="linkId" value="' + linkId + '" />');
				break;
			case '9':
				$("#linkT").html('<span>新品牌团<span class="required">*</span></span>');
				linkIStr = '<select name="linkId" id="linkId"><option value="">请选择</option>';
				<c:forEach items="${brandteamTypes}" var="brandteamType">
				var _selected="";
				var brandteamTypeId = ${brandteamType.id};
				if(linkId == brandteamTypeId){
					_selected = "selected";
				}
				linkIStr+='<option value="${brandteamType.id}" '+_selected+'>${brandteamType.name}</option>';
				</c:forEach>
				linkIStr+='</select>';
				$("#linkI").html(linkIStr);
				break;
			case '10':
				$("#linkT").html('<span>商家店铺<span class="required">*</span></span>');
				$("#linkI").html('<input type="text" id="linkUrl" name="linkUrl" placeholder="请输入商家店铺ID" value="' + linkId + '" />');
				break;
				
			case '11':
				$("#linkT").html('<span>一级分类<span class="required">*</span></span>');
				linkIStr = '<select name="linkId" id="linkId"><option value="">请选择</option>';
				<c:forEach items="${productTypes}" var="productType">
				var _selected="";
				var productTypeid = ${productType.id};
				if(linkId == productTypeid){
					_selected = "selected";
				}
				linkIStr+='<option value="${productType.id}" '+_selected+'>${productType.name}</option>';
			    </c:forEach>
				linkIStr+='</select>';
				$("#linkI").html(linkIStr);
				break;
			case '28':
				$("#linkT").html('<span>商城一级分类<span class="required">*</span></span>');
				linkIStr = '<select name="linkId" id="linkId"><option value="">请选择</option>';
				<c:forEach items="${mallCategorys}" var="mallCategory">
				var _selected="";
				var mallCategoryId = ${mallCategory.id};
				if(linkId == mallCategoryId){
					_selected = "selected";
				}
				linkIStr+='<option value="${mallCategory.id}" '+_selected+'>${mallCategory.categoryName}</option>';
			    </c:forEach>
				linkIStr+='</select>';
				$("#linkI").html(linkIStr);
				break;		
			case '29':
				$("#linkT").html('<span>优惠券ID<span class="required">*</span></span>');
				$("#linkI").html('<input type="text" id="linkId" name="linkId" placeholder="请输入优惠券ID" value="' + linkId + '" />');
				break;		
		}
	}

	
	function ajaxFileUpload() {
        $.ajaxFileUpload({
			url: contextPath + '/service/common/ajax_upload.shtml',
			secureuri: false,
			fileElementId: "logoPicFile",
			dataType: 'json',
			success: function(result, status) {
				if(result.RESULT_CODE == 0) {
					$("#logoPic").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
					$("#pic").val(result.FILE_PATH);
					$("#picName").css("color","");
					$("#picName").html(result.FILE_PATH.split("/")[result.FILE_PATH.split("/").length-1]);
				} else {
					commUtil.alertError(result.RESULT_MESSAGE);
				}
			},
			error: function(result, status, e) {
				commUtil.alertError("服务异常");
			}
		});
	}
	
	function ajaxFileUploadLogo(){
		var file = document.getElementById("logoPicFile").files[0];  
        if(!/image\/\w+/.test(file.type)){  
        	$("#picName").css("color","red");
        	$("#picName").html("图片格式不正确！"); 
            return;
        }
        var reader = new FileReader();  
        reader.onload = function(e) { 
        	var image = new Image();
        	image.onload = function() {
        		if(this.width != '188' || this.height != '94') {
        			$("#pic").val("");
        			$("#logoPic").attr("src", "");
            		$("#picName").css("color","red");
                	$("#picName").html("图片像素不是188*94PX"); 
            	}else{
            		ajaxFileUpload();
            	}
            };
            image.src = e.target.result;
        }
        reader.readAsDataURL(file);  
	}
	
	function updateLinkId(linkId) {
		if(linkId) {
			var linkType = $("select[name='linkType']").val();
			$.ajax({
				url : "${pageContext.request.contextPath}/appMng/updateLinkId.shtml",
				type : 'post',
				data : {linkId : linkId, linkType : linkType},
				dataType : 'json',
				async : false,
				success : function(data){
					 if(data == null || data.statusCode != 200) {
						 $.ligerDialog.error(data.statusCode);
						 $("#linkId").val("");
						 $("#linkUrl").val("");
					 }
				 }
				 /* error : function(e) {
					 $.ligerDialog.error('操作超时，请稍后再试！');
					 $("#linkId").val("");
				 } */
			});
		}
	}
	
</script>

<html>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/appMng/aouBrandRecommend.shtml">
		<input type="hidden" id="adInfoId" name="id" value="${id }"/>
		<input type="hidden" id="ParentId" name="ParentId" value="${getParentId}"/>
		<table class="gridtable">
			<tr>
				<td class="title">设置排序<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input type="text" value="${adInfo.seqNo }" id="seqNo" name="seqNo" validate="{digits:true,maxlength:9,required:true}" />  
				</td>
			</tr>
			
			<tr>
				<td class="title">备注名称</td>
				<td align="left" class="l-table-edit-td">
					<input type="text" id="remarks" name="remarks" value="${adInfo.remarks }" />
				</td>
			</tr>
			
			<tr>
				<td class="title">上架时间<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input type="text" id="autoUpDate" name="autoUpDate" value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${adInfo.autoUpDate }' />" />
				</td>
			</tr>
			
			<tr>
				<td class="title">图片尺寸</td>
				<td align="left" class="l-table-edit-td">LOGO尺寸：188*94PX</td>
			</tr>
			
			<tr>
               <td  class="title" width="20%">上传图片<span class="required">*</span></td>
               <td align="left" class="l-table-edit-td" >
	    			<div style="float: left;position: relative;">
	    				<input style="position: absolute;opacity:0;width:100%;" type="file" id="logoPicFile" name="file" onchange="ajaxFileUploadLogo();" />
	    				<a href="javascript:void(0);">上传图片</a>
	    			</div>
	    			<div id="picName" style="margin-left: 80px;" >${adInfo.pic }</div>
	    			<input id="pic" name="pic" type="hidden" value="${adInfo.pic }">
               </td>
           </tr>
			
			<tr>
				<td class="title">图片预览</td>
				<td align="left" class="l-table-edit-td">
					<div><img id="logoPic" alt="" src="${pageContext.request.contextPath}/file_servelt/${adInfo.pic }" ></div>
				</td>
			</tr>
			
			<tr >
				<td class="title">类型</td>
				<td align="left" class="l-table-edit-td" id="linkTypeTd">
				   <%-- <select name="linkType" id="linkType" style="width: 160px;" onClick="radioItem(this.value, '')">
					<c:forEach var="linkType" items="${linkTypeList }">
						<c:if test="${statusFlag == '5' and linkType.linkTypeValue != '6' and linkType.linkTypeValue != '7' }">
							<c:if test="${empty adInfo  }">
 							      <option  value="${linkType.linkTypeValue}" <c:if test="${linkType.linkTypeValue == 1}">selected</c:if>>${linkType.linkTypeName}</option>
                              </c:if>
							<c:if test="${not empty adInfo }">
								<option  value="${linkType.linkTypeValue}" <c:if test="${linkType.linkTypeValue ==adInfo.linkType}">selected</c:if>>${linkType.linkTypeName}</option>
							</c:if>
						</c:if>
						<c:if test="${statusFlag == '3' and linkType.linkTypeValue != '7'}">
							<c:if test="${empty adInfo  }">								
								<option  value="${linkType.linkTypeValue}" <c:if test="${linkType.linkTypeValue == 1}">selected</c:if>>${linkType.linkTypeName}</option>								
							</c:if>
							<c:if test="${not empty adInfo  }">
							    <option  value="${linkType.linkTypeValue}" <c:if test="${linkType.linkTypeValue ==adInfo.linkType}">selected</c:if>>${linkType.linkTypeName}</option>
                             </c:if>
						</c:if>
					</c:forEach>
					</select> --%>
				</td>
			</tr>
			<tr class="cType">
				<td class="title" id="linkT"></td>
				<td align="left" class="l-table-edit-td" id="linkI" ></td>
			</tr>
			
			<tr>
				<td class="title">操作</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button l-button-submit" value="提交"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
