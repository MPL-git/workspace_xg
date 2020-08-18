<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery.min.js"
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
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />	

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

td input,td select{
border:1px solid #AECAF0;
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

.table-title-link{
color: #1B17EE;
font-size: 15px;
text-decoration: none;
}

.table-title{
font-size: 17px;font-weight: 600;
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
.td-pictures li{
display: inline-block;
}
td img{
width: 60px;
height: 40px;
}
</style>
<script type="text/javascript">
var viewerPictures;
var productBrandSelect;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	
	$("img").bind('click',function(){
		viewerPic($(this).attr("src"));
	});
	
	function getGridOptions(){
	     var options = {
	         columns: [
	         { display: 'ID', name: 'id', minWidth: 100, width: 100 },
	         { display: '品牌', name: 'name', minWidth: 100, width: 100 }
	         ], 
	         switchPageSizeApplyComboBox: false,
	         url: '${pageContext.request.contextPath}/productBrand/selectDatalist.shtml',
	         pageSize: 10, 
	         checkbox: false
	     };
	     return options;
	 }
	
	   var editor1;
	   KindEditor.ready(function(K) {
		 editor1 = K.create('textarea[name="content"]', {
			cssPath : '${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css',
			uploadJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/upload_json.jsp',
			fileManagerJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
			}
			
		});
		prettyPrint();
	});
	   var editor2;
	   KindEditor.ready(function(K) {
		 editor2 = K.create('textarea[name="innerRemarks"]', {
			cssPath : '${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css',
			uploadJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/upload_json.jsp',
			fileManagerJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
			}
			
		});
		prettyPrint();
	});
	
$("input[name='brandName']").each(function(index){
	var eachProductBrandSelect = $(this).ligerComboBox({
				width : 150,
				slide : false,
				selectBoxWidth : 450,
				selectBoxHeight : 300,
				valueField : 'id',
				textField : 'name',
				valueFieldID : 'productBrandId'+index,
				grid : getGridOptions(),
				condition : {
					fields : [ {
						name : 'name',
						label : '品牌名',
						width : 90,
						type : 'text'
					} ]
				}
			});
//	productBrandSelect.setValue("${productBrand.id}");
//	productBrandSelect.setText("${productBrand.name}");
});

	var submitting;
	$("#confirm").bind('click',function(){
		editor1.sync();
		editor2.sync();
		var mchtIdcardImgs = [];
		var lis = $("#mchtIdcardImg").find("li");
		lis.each(function(index, item) {
			var path = $("img", item).attr("path");
			var def = ($(".def", item).length == 1 ? "1" : "0");
			var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
			mchtIdcardImgs.push(pic);
		});
		if(mchtIdcardImgs.length > 0){
			$("#mchtIdcardImgs").val(JSON.stringify(mchtIdcardImgs));
		}else if(${mchtContact.contactType eq '1'}){
			commUtil.alertError("请上传身份证正反面");
			return;
		}
		
		
		if(${mchtContact.contactType eq '1'}){
		var mchtPrincipalidcardImg = [];
		var lis = $("#mchtBlPic").find("li");
		lis.each(function(index, item) {
			var path = $("img", item).attr("path");
			var def = ($(".def", item).length == 1 ? "1" : "0");
			var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
			mchtPrincipalidcardImg.push(pic);
		});
		if(mchtPrincipalidcardImg.length > 0){
			$("#mchtPrincipalidcardImg").val(JSON.stringify(mchtPrincipalidcardImg));
		}else{
			commUtil.alertError("请上传店铺负责人手持身份证");
			return;
		}
		}
		
		var auditStatus = $("input[name='auditStatus']:checked").val();
		var rejectReasons = $("#rejectReasons").val();
		var innerRemarks = $("#innerRemarks").val();
		if(rejectReasons.length>256){
			commUtil.alertError("驳回原因过长");
			return;
		}
		if(innerRemarks.length>256){
			commUtil.alertError("内部备注过长");
			return;
		}
		
		submitting = true;
		$.ajax({
			url : "${pageContext.request.contextPath}/mcht/mchtContactAudit.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":$("#id").val(),
					"auditStatus":auditStatus,
					"rejectReasons":rejectReasons,
					"innerRemarks":innerRemarks,
					"mchtPrincipalidcardImg":$("#mchtPrincipalidcardImg").val(),
					"mchtIdcardImgs":$("#mchtIdcardImgs").val(),
					
				},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					submitting = false;
					commUtil.alertSuccess("操作成功");
					setTimeout(function(){
						parent.location.reload();
						frameElement.dialog.close();
					},1000);
				}else{
					submitting = false;
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				submitting = false;
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
		
	});
});

	function viewerPic(imgPath) {

		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$("#viewer-pictures")
				.append(
						'<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'),
				{
					hide : function() {
						$("#viewer-pictures").hide();
					}
				});
		$("#viewer-pictures").show();
		viewerPictures.show();

	}

	function viewerMchtPic(mchtProductBrandId, picType) {

		var url;
		if (picType == 1) {
			url = "${pageContext.request.contextPath}/mcht/getMchtBrandPic.shtml";
		}
		if (picType == 2) {
			url = "${pageContext.request.contextPath}/mcht/getPlatfromAuthPic.shtml";
		}

		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$.ajax({
					url : url,
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {
						mchtProductBrandId : mchtProductBrandId
					},
					timeout : 30000,
					success : function(data) {
						console.log(data);
						if (data && data.length > 0) {
							for (var i = 0; i < data.length; i++) {
								$("#viewer-pictures")
										.append(
												'<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" alt=""></li>');
							}
							viewerPictures = new Viewer(document
									.getElementById('viewer-pictures'), {
								hide : function() {
									$("#viewer-pictures").hide();
								}
							});
							$("#viewer-pictures").show();
							viewerPictures.show();
						}

					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});

	}




	//图片格式验证
	function ajaxFileUploadImg(_this) {
		var file = document.getElementById(_this.id).files[0]; 
	    var reader = new FileReader();  
	    reader.onload = function(e) { 
	    	var image = new Image();
	    	image.onload = function() {
	        	ajaxFileUpload(_this);
	        };
	        image.src = e.target.result;
	    }
	    reader.readAsDataURL(file);  
	}

	function ajaxFileUpload(_this) {
		var id = $(_this).attr("id");
		$.ajaxFileUpload({
			url:"${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
			secureuri: false,
			fileElementId: id,
			dataType: 'json',
			success: function(result, status) {
				if(result.RESULT_CODE == 0) {
					$("#mcht"+id).append('<li><p><img src="${pageContext.request.contextPath}/file_servelt'+result.FILE_PATH+'" path="'+result.FILE_PATH+'" onclick="viewerPic(this.src)"></p><a href="javascript:void(0);" class="del">删除</a></li>');
					$(".del").live('click',function(){
						$(this).closest("li").remove();
					});
				} else {
					alert(result.RESULT_MESSAGE);
				}
			},
			error: function(result, status, e) {
				alert("服务异常");
			}
		});
	}
	


	
	function ajaxFileUploadLogo() {
		$.ajaxFileUpload({
			url: contextPath + '/service/common/ajax_upload.shtml',
			secureuri: false,
			fileElementId: "logoPicFile",
			dataType: 'json',
			success: function(result, status) {
				if(result.RESULT_CODE == 0) {
					$("#logoPic").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
					$("#pic").val(result.FILE_PATH);
				} else {
					alert(result.RESULT_MESSAGE);
				}
			},
			error: function(result, status, e) {
				alert("服务异常");
			}
		});
		
	}
	
	function ajaxFileUploadLogo() {
		$.ajaxFileUpload({
			url: contextPath + '/service/common/ajax_upload.shtml',
			secureuri: false,
			fileElementId: "businessLicensePicFile",
			dataType: 'json',
			success: function(result, status) {
				if(result.RESULT_CODE == 0) {
					$("#businessLicensePicImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
					$("#businessLicensePic").val(result.FILE_PATH);
				} else {
					alert(result.RESULT_MESSAGE);
				}
			},
			error: function(result, status, e) {
				alert("服务异常");
			}
		});
		
	}
</script>
<html>
<body>
		<input type="hidden" id="id" value="${mchtContact.id}">
		<table class="gridtable" style="width:800px;">
			<tr>
				<td  colspan="1" class="title" style="width: 200px;">对接人类型</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${mchtContact.contactType eq 1}">
						电商总负责人
					</c:if>
					<c:if test="${mchtContact.contactType eq 2}">
						运营对接人
					</c:if>
					<c:if test="${mchtContact.contactType eq 3}">
						订单对接人
					</c:if>
					<c:if test="${mchtContact.contactType eq 4}">
						售后对接人
					</c:if>
					<c:if test="${mchtContact.contactType eq 5}">
						财务对接人
					</c:if>
					<c:if test="${mchtContact.contactType eq 6}">
						客服对接人
					</c:if>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title" style="width: 200px;">姓名</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtContact.contactName}
				</td>
			</tr>
			<tr>
			<tr>
				<td  colspan="1" class="title" style="width: 200px;">手机号码</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtContact.mobile}
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title" style="width: 200px;">座机号</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtContact.tel}
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title" style="width: 200px;">QQ</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtContact.qq}
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title" style="width: 200px;">邮箱</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtContact.email}
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">身份证正反面</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${idcardImgList ne null}">
					<t:imageList name="mchtIdcardImg" list="${idcardImgList}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
					</c:if>
					<div style="float: left;height: 105px;margin: 10px;">
						<input style="position:absolute; opacity:0;width: 110px;" type="file" id="IdcardImg" name="file" onchange="ajaxFileUploadImg(this);" /> 
						<input type="button" style="width: 70px;" value="上传图片" /> 
					</div>
					<input type="hidden" id="mchtIdcardImgs" name="mchtIdcardImgs" >
				</td>	
			</tr>
			
			<c:if test="${mchtContact.contactType eq 1}">
			<tr>
				<td  colspan="1" class="title">店铺负责人手持身份证</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${handHeldIdCardList ne null}">
					<t:imageList name="mchtBlPic" list="${handHeldIdCardList}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
					</c:if>
					<div style="float: left;height: 105px;margin: 10px;">
						<input style="position:absolute; opacity:0;width: 110px;" type="file" id="BlPic" name="file" onchange="ajaxFileUploadImg(this);" /> 
						<input type="button" style="width: 70px;" value="上传图片" /> 
					</div>
					<input type="hidden" id="mchtPrincipalidcardImg" name="mchtPrincipalidcardImg">
				</td>	
			</tr>
			</c:if>
			
			<tr>
				<td  colspan="1" class="title">地址</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtContact.provinceName}${mchtContact.cityName}${mchtContact.countyName}${mchtContact.address}
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">审核结果</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<input type="radio" name="auditStatus" value="1" checked="checked">通过 
					<input type="radio" name="auditStatus" value="2" <c:if test="${mchtContact.auditStatus == 2}">checked="checked"</c:if>>驳回
				</td>	
				
			</tr>
			
			<tr>
				<td  colspan="1" class="title">驳回原因</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<textarea name="content" id="rejectReasons" style="width:150px;height:300px;visibility:hidden;">${mchtContact.rejectReasons}</textarea>
					<%-- <textarea rows="7" cols="70" id="rejectReasons" name="rejectReasons" >${mchtContact.rejectReasons}</textarea> --%>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">内部备注</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<textarea name="innerRemarks" id="innerRemarks" style="width:150px;height:300px;visibility:hidden;">${mchtContact.innerRemarks}</textarea>
					<%-- <textarea rows="7" cols="70" id="innerRemarks" name="innerRemarks" >${mchtContact.innerRemarks}</textarea> --%>
				</td>
			</tr>
			<tr>
	        <td class="title">操作</td>
	        <td>
	        	<div id="btnDiv">
					<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
					<input type="button" value="取消" class="l-button l-button-test" style="float:left;margin-left: 20px;" onclick="frameElement.dialog.close();" />
				</div>
			</td>
	    </tr>
		</table>

	
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
		
</body>
</html>
