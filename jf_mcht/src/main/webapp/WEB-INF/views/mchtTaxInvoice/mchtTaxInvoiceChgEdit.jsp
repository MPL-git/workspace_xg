<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>税务开票信息</title>
	<!-- Bootstrap -->
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
   <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
   <link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />
<!--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/fileuploader-style.css" /> -->


</head>

<body>

  <div class="modal-dialog" role="document" style="width:700px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">税务开票信息</span>
      </div>
			<div class="modal-body">
		<form id="dataFrom">
		<input type="hidden" value="${mchtTaxInvoiceInfoChg.id}" name="id">
		<input type="hidden" value="${mchtTaxInvoiceInfoChg.mchtId}" name="mchtId">
		<input type="hidden" value="${mchtTaxInvoiceInfoChg.mchtTaxInvoiceInfoId}" name="mchtTaxInvoiceInfoId">
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">公司名称</td>
						<td colspan="2" class="text-left">
						<span>${sessionScope.mchtInfo.companyName}</span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">纳税类型</td>
						<td colspan="2" class="text-left">
						  <select  name="taxType" id="taxType" validate="{required:true}">
						   <option value="">--请选择--</option>
						   <c:forEach var="taxTypeDescItem" items="${taxTypeDescList}">
						   <option value="${taxTypeDescItem.statusValue}"   <c:if test="${mchtTaxInvoiceInfoChg.taxType==taxTypeDescItem.statusValue}">selected</c:if>>${taxTypeDescItem.statusDesc}</option>
						   </c:forEach>
                          </select>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">纳税人识别号</td>
						<td colspan="2" class="text-left">
						<input style="width:80%;" type="text" id="taxNumber" name="taxNumber" value="${mchtTaxInvoiceInfoChg.taxNumber }" validate="{required:true}">
						</td>
					</tr>
										<tr>
						<td class="editfield-label-td">地址</td>
						<td colspan="2" class="text-left">
						<input style="width:80%;" type="text" id="address" name="address" value="${mchtTaxInvoiceInfoChg.address }"  validate="{required:true}">
						</td>
					</tr>
										<tr>
						<td class="editfield-label-td">电话</td>
						<td colspan="2" class="text-left">
						<input style="width:80%;" type="text" id="tel" name="tel" value="${mchtTaxInvoiceInfoChg.tel }" validate="{required:true}">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">开户行</td>
						<td colspan="2" class="text-left">
						<input style="width:80%;" type="text" id="depositBank" name="depositBank" value="${mchtTaxInvoiceInfoChg.depositBank }" validate="{required:true}">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">帐号</td>
						<td colspan="2" class="text-left">
						<input style="width:80%;" type="text" id="accountNumber" name="accountNumber" value="${mchtTaxInvoiceInfoChg.accountNumber }" validate="{required:true}">
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">上传确认函</td>
						<td colspan="2" class="text-left">
							<c:if test='${mchtTaxInvoiceInfoChg.confirmFile!=null&&mchtTaxInvoiceInfoChg.confirmFile!=""}'>
							<div class="single_pic_picker"><input id="confirmFileFile" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${mchtTaxInvoiceInfoChg.confirmFile}"><input type="hidden"  id="confirmFile" name="confirmFile" value="${mchtTaxInvoiceInfoChg.confirmFile}"></div>
							</c:if>
							<c:if test='${mchtTaxInvoiceInfoChg.confirmFile==null||mchtTaxInvoiceInfoChg.confirmFile==""}'>
							<div class="single_pic_picker"><input id="confirmFileFile" onchange="loadImageFile(this)" type="file"><div>+</div><input type="hidden"  id="confirmFile" name="confirmFile" value=""></div>
							</c:if>
						</td>
					</tr>
					
					<c:if test="${mchtTaxInvoiceInfoChg.id!=null}">
					<tr>
						<td class="editfield-label-td">状态</td>
						<td colspan="2" class="text-left">
						<span>${auditStatusDesc }</span>
						</td>
					</tr>
					</c:if>
					<c:if test="${mchtTaxInvoiceInfoChg.id!=null&&mchtTaxInvoiceInfoChg.auditStatus=='4' }">
					<tr>
						<td class="editfield-label-td">驳回原因</td>
						<td colspan="3" class="text-left">
						<span>${mchtTaxInvoiceInfoChg.auditRemarks }</span>
						</td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		</form>
		
	<div class="modal-footer">
			<button class="btn btn-info" onclick="commitAudit();">提交审核</button>
			<button class="btn btn-info" data-dismiss="modal">取消</button>
			<a style="float:right;" href="${ctx}/static/file/税务开票资料确认函.docx">下载确认函模板</a>
      </div>
		
			</div>
	</div>
  </div>




	
	<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js"
	type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	
	
<script type="text/javascript">



var dataFromValidate;
$(function(){
	
	$.metadata.setType("attr", "validate");

	dataFromValidate =$("#dataFrom").validate({
        highlight : function(element) {  
        	$(element).addClass('error');
            $(element).closest('tr').find("td").first().addClass('has-error');  
        },
        success : function(label) {  
            label.closest('tr').find("td").first().removeClass('has-error');  
        }
	});
});





function commitSave(){

	if(dataFromValidate.form()){
		var dataJson = $("#dataFrom").serializeArray();
		$.ajax({
			url : "${ctx}/mchtTaxInvoice/mchtTaxInvoiceChgCommitSave",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : dataJson,
			timeout : 30000,
			success : function(data) {
				if (data.returnCode=="0000") {
					$("#viewModal").modal('hide');
					swal({
						  title: "保存成功!",
						  type: "success",
						  confirmButtonText: "确定"
						  
						});
					$("#infoChgApply").remove();
					 table.ajax.reload();
				} else {
					swal({
						  title: data.returnMsg,
						  type: "error",
						  confirmButtonText: "确定"
						});
				}
				
			},
			error : function() {
				swal({
					  title: "提交失败！",
					  type: "error",
					  timer: 1500,
					  confirmButtonText: "确定"
					});
			}
		});
	}
	
	
}

function commitAudit(){
	if(dataFromValidate.form()){
		
		var $confirmFile=$("#confirmFile").parent().children("img");
		
		if($confirmFile.length<=0){
				swal({
					  title: '请上传确认函',
					  type: "error",
					  confirmButtonText: "确定"
					});
		return;
		}
		
		if($confirmFile.length>0&&$confirmFile.attr("src")!=""&&$confirmFile.attr("src").indexOf("data:image")==0){//有修改
			uploadImage("confirmFileFile",$("#confirmFile"));
		}
		
		var dataJson = $("#dataFrom").serializeArray();
		$.ajax({
			url : "${ctx}/mchtTaxInvoice/mchtTaxInvoiceChgCommitAudit",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : dataJson,
			timeout : 30000,
			success : function(data) {
				if (data.returnCode=="0000") {
					$("#viewModal").modal('hide');
					swal({
						  title: "提交成功!",
						  type: "success",
						  confirmButtonText: "确定"
						  
						});
					$("#infoChgApply").remove();
					 table.ajax.reload();
				} else {
					swal({
						  title: data.returnMsg,
						  type: "error",
						  confirmButtonText: "确定"
						});
				}
				
			},
			error : function() {
				swal({
					  title: "提交失败！",
					  type: "error",
					  timer: 1500,
					  confirmButtonText: "确定"
					});
			}
		});
	}
}



function loadImageFile(obj) {
	if (obj.files.length === 0) {
		return;
	}
	var oFile = obj.files[0];
	var rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
	if (!rFilter.test(oFile.type)) {
		swal("请选择图片文件");
		return;
	}
    if($(obj).parent().children("img").length<=0){
    	$('<img>').appendTo( $(obj).parent() );;
    }
	var oFReader = new FileReader();
	oFReader.onload = function(oFREvent) {
		$(obj).parent().children("img").attr("src",oFREvent.target.result);
		$(obj).parent().children("div").remove();
	};
	oFReader.readAsDataURL(oFile);
}

//上传图片
function uploadImage(fileElementId,$valueFiled) {
	var oFile = document.getElementById(fileElementId).files[0];
	var formData = new FormData();
	formData.append("file",oFile);
	$.ajax({ 
		url : "${ctx}/common/fileUpload", 
		type : 'POST', 
		data : formData, 
		async: false,
		// 告诉jQuery不要去处理发送的数据
		processData : false, 
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,
		beforeSend:function(){
			console.log("图片片上传中，请稍候");
		},
		success : function(data) {
            if (data.returnCode=="0000") {
                $valueFiled.val(data.returnData);
            } else {
                swal({
                    title: "图片上传失败！",
                    type: "error",
                    confirmButtonText: "确定"
                });
            }
		}, 
		error : function(responseStr) { 
			swal("图片上传失败");
		} 
		});


}




</script>
</body>
</html>
