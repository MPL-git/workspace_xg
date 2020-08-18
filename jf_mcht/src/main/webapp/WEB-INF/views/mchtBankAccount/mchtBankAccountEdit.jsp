<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>编辑银行帐号</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
	<!-- Bootstrap -->
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
   <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
   <link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />
<!--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/fileuploader-style.css" /> -->
<style type="text/css">
.td-pictures li{
display: inline-block;
}
.td-pictures li img{
width: 100px;
height: 100px;
}
</style>


</head>

<body>

  <div class="modal-dialog" role="document" style="width:600px;">
    <div class="modal-content">
    	<input type="hidden" id="isReload" value="${isReload}">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">
	        <c:if test="${not empty mchtBankAccount.id}">编辑银行帐号</c:if>
	        <c:if test="${empty mchtBankAccount.id}">添加银行帐号</c:if>
        </span>
      </div>
			<div class="modal-body">
		<c:if test='${mchtBankAccount.status=="3"}'>
		<span style="color:red;">对不起，您的申请被驳回，驳回原因：${mchtBankAccount.auditRemarks}</span>
		<br>
		<br>
		</c:if>
			
		<form id="dataFrom">
		<input type="hidden" value="${mchtBankAccount.id}" name="id">
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td>公司名称</td>
						<td style="text-align: left;">${mchtInfo.companyName}</td>
					</tr>
					<tr>
						<td>商家序号</td>
						<td style="text-align: left;">${mchtInfo.mchtCode}</td>
					</tr>
					<tr>
						<td class="editfield-label-td">选择类型</td>
						<td colspan="2" class="text-left">
						<c:if test="${mchtInfo.settledType eq 1}">
							<c:if test="${mchtBankAccount.mchtId != '2' and mchtBankAccount.mchtId != '324' and mchtBankAccount.mchtId != '8' }">
								<input style="vertical-align: middle;" <c:if test="${mchtBankAccount.accType=='2'}"> checked="checked" </c:if> type="radio" name="accType" value="2" onchange="changeAccType();" validate="{required:true}" >
								<span>对公账户 </span>
							</c:if>
							<c:if test="${mchtBankAccount.mchtId == '2' or mchtBankAccount.mchtId == '324' or mchtBankAccount.mchtId == '8' }">
								<input <c:if test="${mchtBankAccount.accType=='2'}"> checked="checked" </c:if> type="radio" name="accType" value="2" onchange="changeAccType();" >
								<span style="vertical-align: top;">对公账户 </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input <c:if test="${mchtBankAccount.accType=='1'}"> checked="checked" </c:if> type="radio" name="accType" value="1" onchange="changeAccType();">
								<span style="vertical-align: top;">对私账户</span>
							</c:if>
						</c:if>
						<c:if test="${mchtInfo.settledType eq 2}">
							<input <c:if test="${mchtBankAccount.accType=='2'}"> checked="checked" </c:if> type="radio" name="accType" value="2" onchange="changeAccType();" >
							<span style="vertical-align: top;">对公账户 </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input <c:if test="${mchtBankAccount.accType=='1'}"> checked="checked" </c:if> type="radio" name="accType" value="1" onchange="changeAccType();">
							<span style="vertical-align: top;">对私账户</span>
						</c:if>	
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">账户名称</td>
						<td colspan="2" class="text-left">
							<input type="text" id="accName" name="accName" value="" validate="{required:true}"
								<c:if test="${mchtBankAccount.accType=='2'}">readonly="readonly" style="background-color: #E3E3E3;"</c:if>>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">开户银行</td>
						<td colspan="2" class="text-left">
						  <select  name="bankCode" id="bankCode" validate="{required:true}">
						   <option value="">--请选择--</option>
						   <c:forEach var="bank" items="${banks}">
						   <option value="${bank.id}"   <c:if test="${mchtBankAccount.bankCode==bank.id}">selected</c:if>>${bank.name}</option>
						   </c:forEach>
                          </select>
						</td>
					</tr>
					
					
				    <tr>
						<td class="editfield-label-td">开户支行名称</td>
						<td colspan="2" class="text-left">
						<input type="text" id="depositBank" name="depositBank" value="${mchtBankAccount.depositBank}" validate="{required:true}">&nbsp;&nbsp;例：农业银行厦门金山支行
						</td>
					</tr>
				    <tr>
						<td class="editfield-label-td">银行账号</td>
						<td colspan="2" class="text-left">
						<input type="text" id="accNumber" name="accNumber" value="${mchtBankAccount.accNumber}" validate="{required:true,digits:true}">&nbsp;&nbsp;例：440708105********
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		</form>
		
	<div class="modal-footer">
	
			<button class="btn btn-info" onclick="commitSave();" <c:if test="${mchtBankAccount.status == 1}">disabled="disabled"</c:if>>提交</button>
			<button class="btn btn-info" data-dismiss="modal">取消</button>
      </div>
		
			</div>
	</div>
  </div>




	
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js"
	type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<!--     <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/fileUpload.js"></script> -->
    <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>	
	
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
        },
        errorPlacement: function(error, element) {  
        	error.appendTo($(element).closest('td'));
        }
	});
	
	changeAccType();
    
});


function changeAccType(){
	var accType = $("input[name=accType]:checked").val();
	<c:if test="${mchtInfo.settledType == 1}">
	if(accType == "2"){
		$("#accName").val("${mchtInfo.companyName}");
		$("#accName").attr("readonly", true);
		$("#accName").css("background-color","#E3E3E3");
	}else{
		$("#accName").attr("readonly", false);
		$("#accName").css("background-color","");
		$("#accName").val("${mchtBankAccount.accName}");
	}
	</c:if>
	<c:if test="${mchtInfo.settledType == 2}">
	if(accType == "2"){
		$("#accName").val("${mchtInfo.companyName}");
		$("#accName").attr("readonly", true);
		$("#accName").css("background-color","#E3E3E3");
	}else{
		$("#accName").val("${mchtInfo.corporationName}");
		$("#accName").attr("readonly", true);
		$("#accName").css("background-color","#E3E3E3");
	}
	</c:if>
}


function commitSave(){
	if(dataFromValidate.form()){
		var dataJson = $("#dataFrom").serializeArray();
		$.ajax({
			url : "${ctx}/mchtBankAccount/mchtBankAccountSave",
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
					var isReload = $("#isReload").val();
					if(isReload){
						location.reload();
					}
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
