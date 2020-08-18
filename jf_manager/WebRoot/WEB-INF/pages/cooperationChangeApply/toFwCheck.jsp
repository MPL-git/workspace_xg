<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
<style type="text/css">
body {font-size: 13px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:20px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
.table-title-link{color: #1B17EE;}
.baseInfo tr td span,.baseInfo tr td img{margin-right:20px;}
tr.title-first td{background-color:#DCF9FF;}
tr.title-second td{background-color:#FFFB94;font-size:15px;}
tr.title-second td span{margin-right:0px;}
tr.title-third td{font-size:15px;}
.baseInfo tr td span.amtSpan{ margin-right:0;color: #FF0000;}
tr.title-fourth td{background-color:#DCF9FF;text-align:center;}
.LogInfo tr td img{margin:10px 20px 0 0;}
.color01{color: #FF0000;}
.color02{color: #797979;}

.video_box {
    background: #fff;
    z-index: 2222;
    display: block;
}

.black_box {
    background: #000;
    opacity: 0.6;
    left: 0;
    top: 0;
    z-index: 1111;
    position: fixed;
    height: 100%;
    width: 100%;
}
.video_close {
    position: absolute;
    top: -14px;
    right: -12px;
}
</style>
<script type="text/javascript">

    var viewerPictures;
    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="fwAuditRemarks"]', {
            cssPath : '${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css',
            uploadJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/upload_json.jsp',
            fileManagerJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/file_manager_json.jsp',
            allowFileManager : true,
            afterCreate : function() {
            }

        });
        prettyPrint();
    });

$(function(){
    viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
            $("#viewer-pictures").hide();
        }});
    changeShopType();
	
	// $("input[name='fwAuditStatus']").bind('click',function(){
	// 	var fwAuditStatus = $(this).val();
	// 	if(fwAuditStatus == 1){
	// 		$("#fwRejectReasonTr").hide();
	// 	}else if(fwAuditStatus == 2){
	// 		$("#fwRejectReasonTr").show();
	// 	}
	// });
	
	$("#confirmShopInfo").bind('click',function(){
		var shopType = $("#shopType").val();
		var businessFirms = $("#businessFirms").val();
		var brand = $("#brand").val();
		var productType = $("#productType").val();
		var shopName = $("#shopName").val();
		if(!shopType){
			commUtil.alertError("请选择店铺类型");
			return;
		}
		if(shopType == 1){
			if(!brand){
				commUtil.alertError("请输入品牌");
				return;
			}
		}else if(shopType == 2){
			if(!brand){
				commUtil.alertError("请输入品牌");
				return;
			}
			if(!productType){
				commUtil.alertError("请输入品类");
				return;
			}
		}else if(shopType == 3){
			if(!businessFirms){
				commUtil.alertError("请输入公司字号");
				return;
			}
			if(!brand){
				commUtil.alertError("请输入品牌");
				return;
			}
		}else if(shopType == 4){
			if(!businessFirms){
				commUtil.alertError("请输入公司字号");
				return;
			}
			if(!productType){
				commUtil.alertError("请输入品类");
				return;
			}
		}else if(shopType == 5){
			if(!businessFirms){
				commUtil.alertError("请输入公司字号");
				return;
			}
		}
		if(!shopName){
			commUtil.alertError("请输入店铺名");
			return;
		}
		$("#newShopName").text(shopName);
		$("#shopInfoDiv").hide();
		$(".black_box").hide();
	});
	
	$("#confirmProductType").bind('click',function(){
		var changeProductTypeId = $("#changeProductTypeId").val();
		if(!changeProductTypeId){
			commUtil.alertError("请选择类目");
			return;
		}
		$("#productTypeId").val(changeProductTypeId);
		var changeProductTypeName = $("#changeProductTypeId").find("option:selected").text();
		$("#newProductTypeName").text(changeProductTypeName);
		$("#productTypeDiv").hide();
		$(".black_box").hide();
	});
	
	$("#confirmDeposit").bind('click',function(){
		var changeDeposit = $("#changeDeposit").val();
		if(!changeDeposit){
			commUtil.alertError("请输入保证金");
			return;
		}
		$("#deposit").text(changeDeposit);
		$("#depositDiv").hide();
		$(".black_box").hide();
	});
	
	
	
	$(".video_close").bind('click',function(){
		$("#shopInfoDiv").hide();
		$(".black_box").hide();
	});
	
	$("#confirm").bind('click',function(){
		var id = $("#id").val();
		var fwAuditStatus = $("input[name='fwAuditStatus']:checked").val();
		if(!fwAuditStatus){
			commUtil.alertError("请选择法务确认结果");
			return false;
		}
        editor1.sync('fwAuditRemarks');//将内容输入到文本框
		var fwAuditRemarks = $("#fwAuditRemarks").val().trim();
		if(!fwAuditRemarks && fwAuditStatus == 2){
			commUtil.alertError("驳回理由不能为空");
			return false;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/cooperationChangeApply/fwCheck.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id,"fwAuditStatus":fwAuditStatus,"fwAuditRemarks":fwAuditRemarks},
			timeout : 30000,
			success : function(data) {
				if(data.returnCode == '0000'){
					commUtil.alertSuccess("提交成功");
					setTimeout(function(){
						parent.location.reload();
						frameElement.dialog.close();
					},1000);
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
		
	});
	
});

function editShopInfo(isView){
	$("#shopInfoDiv").show();
	$(".black_box").show();
	if(isView == 1){
		$("#opBtnDiv").hide();
	}
}

function changeShopType(){
	var shopType=$('#shopType').val();
	if(shopType=='1'||shopType=='2'){
		$('#businessFirms').attr("disabled",true);
		$('#businessFirms').val("");
	}else{
		$('#businessFirms').attr("disabled",false);
	}
	if(shopType=='4'||shopType=='5'){
		$('#brand').attr("disabled",true);
		$('#brand').val("");
	}else{
		$('#brand').attr("disabled",false);
	}
	if(shopType=='1'||shopType=='3'||shopType=='5'){
		$('#productType').attr("disabled",true);
		$('#productType').val("");
	}else{
		$('#productType').attr("disabled",false);
	}
	generateShopName();
}

function setDisableFiled(){
	var shopType=$('#shopType').val();
	if(shopType=='1'||shopType=='2'){
		$('#businessFirms').attr("disabled",true);
	}else{
		$('#businessFirms').attr("disabled",false);
	}
	if(shopType=='4'||shopType=='5'){
		$('#brand').attr("disabled",true);
	}else{
		$('#brand').attr("disabled",false);
	}
	if(shopType=='1'||shopType=='3'||shopType=='5'){
		$('#productType').attr("disabled",true);
	}else{
		$('#productType').attr("disabled",false);
	}
}

function generateShopName(){
	var shopType=$('#shopType').val();
	if(shopType=='1'){
		$('#shopName').val($('#brand').val()+"官方旗舰店");
	}
	if(shopType=='2'){
		$('#shopName').val($('#brand').val()+$('#productType').val()+"旗舰店");
	}
	if(shopType=='3'){
		$('#shopName').val($('#brand').val()+$('#businessFirms').val()+"专卖店");
	}
	if(shopType=='4'){
		$('#shopName').val($('#businessFirms').val()+$('#productType').val()+"专营店");
	}
	if(shopType=='5'){
		$('#shopName').val($('#businessFirms').val()+"官方旗舰店");
	}
}

function toPreview(id){
	$.ligerDialog.open({
		height: $(window).height()-50,
		width: $(window).width()-50,
		title: "预览查看",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/cooperationChangeApply/toPreview.shtml?id="+id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
</script>
<html>
<body>
	<div class="title-top">
	<input type="hidden" id="id" value="${id}">
	<input type="hidden" id="productTypeId" value="${cooperationChangeApply.productTypeId}">
		<div>
			<span class="table-title">公司信息</span>
		</div>
		<table class="gridtable">
          	<tr>
          		<td class="title">商家序号</td>
          		<td>${mchtInfo.mchtCode}</td>
          		<td class="title">公司名称</td>
          		<td>${mchtInfo.companyName}</td>
          	</tr>
          	<tr>
          		<td class="title">主营类目</td>
          		<td>${cooperationChangeApply.productTypeName}</td>
          		<td class="title">店铺名称</td>
          		<td>${mchtInfo.shopName}</td>
          	</tr>
          	<tr>
          		<td class="title">合同开始日期</td>
          		<td><fmt:formatDate value="${mchtInfo.agreementBeginDate}" pattern="yyyy-MM-dd"/></td>
          		<td class="title">合同结束日期</td>
          		<td><fmt:formatDate value="${mchtInfo.agreementEndDate}" pattern="yyyy-MM-dd"/></td>
          	</tr>
          	<tr>
          		<td class="title">合同归档状态</td>
          		<td>
          			<c:if test="${cooperationChangeApply.archiveStatus == 0}">
          				未处理 
          			</c:if>
          			<c:if test="${cooperationChangeApply.archiveStatus == 1}">
          				通过已归档 
          			</c:if>
          			<c:if test="${cooperationChangeApply.archiveStatus == 2}">
          				不通过驳回
          			</c:if>
          			<c:if test="${cooperationChangeApply.archiveStatus == 4}">
          				不签约
          			</c:if>
          		</td>
          		<td class="title">合同归档日期</td>
          		<td><fmt:formatDate value="${cooperationChangeApply.archiveDate}" pattern="yyyy-MM-dd"/></td>
          	</tr>
        </table>
        
        <c:if test="${not empty shopNameAuth}">
        <br>
        <div>
			<span class="table-title">店铺信息修改</span>
		</div>
		<table class="gridtable">
          	<tr>
          		<td class="title">项目</td>
          		<td>变更前</td>
          		<td>变更后</td>
          	</tr>
          	<tr>
          		<td class="title">店铺名</td>
          		<td>${cooperationChangeApply.preShopName}</td>
          		<td>
					<span id="newShopName">${cooperationChangeApply.shopName}</span><a href="javascript:;" onclick="editShopInfo(1);">【查看】</a>
				</td>

          	</tr>
        </table>
        </c:if>
        
        <c:if test="${not empty mchtProductTypeAuth}">
        <br>
        <div>
			<span class="table-title">店铺主营类目变更</span>
		</div>
		<table class="gridtable">
          	<tr>
          		<td class="title">项目</td>
          		<td>变更前</td>
          		<td>变更后</td>
          	</tr>
          	<tr>
          		<td class="title">店铺主营类目</td>
          		<td>${cooperationChangeApply.productTypeName}</td>
          		<td>
					<span id="newProductTypeName" <c:if test="${cooperationChangeApply.productTypeName ne cooperationChangeApply.newProductTypeName}">style="color: red;"</c:if>>${cooperationChangeApply.newProductTypeName}</span>
				</td>
          	</tr>
        </table>
        </c:if>
        
        <c:if test="${not empty depositAuth}">
        <br>
        <div>
			<span class="table-title">保证金变更</span>
		</div>
		<table class="gridtable">
          	<tr>
          		<td class="title">项目</td>
          		<td>变更前</td>
          		<td>变更后</td>
          	</tr>
          	<tr>
          		<td class="title">保证金变更</td>
				<td>${cooperationChangeApply.preDeposit}</td>
				<td>
					<span id="deposit" <c:if test="${cooperationChangeApply.preDeposit ne cooperationChangeApply.deposit}">style="color: red;"</c:if>>${cooperationChangeApply.deposit}</span>
				</td>
          	</tr>
        </table>
        </c:if>
        
        <c:if test="${not empty popCommissionRateAuth}">
        <br>
        <div>
			<span class="table-title">品牌技术服务费费率变更</span>
		</div>
		<table class="gridtable">
          	<tr>
          		<td class="title">项目</td>
          		<td>变更前</td>
          		<td>变更后</td>
          	</tr>
          	
          	<c:forEach items="${mchtProductBrands}" var="mchtProductBrand">
			<tr>
				<td class="title">${mchtProductBrand.productBrandName}</td>
				<td>
					<c:if test="${not empty mchtBrandRateChanges}">
					<c:forEach items="${mchtBrandRateChanges}" var="mchtBrandRateChange">
					<span  <c:if test="${mchtBrandRateChange.mchtProductBrandId eq mchtProductBrand.id}" >><fmt:formatNumber type="number" pattern="0.00" maxFractionDigits="2" value="${mchtBrandRateChange.prePopCommissionRate}"></fmt:formatNumber></c:if>
							</c:forEach>
						</c:if>
						<c:if test="${empty mchtBrandRateChanges}">
							<fmt:formatNumber type="number" pattern="0.00" maxFractionDigits="2" value="${mchtProductBrand.popCommissionRate}"></fmt:formatNumber>
						</c:if>
				</td>

				<td>
					<c:if test="${not empty mchtBrandRateChanges}">
						<input type="text" name="popCommissionRate" mchtProductBrandId="${mchtProductBrand.id}" data-type="money"
						<c:forEach items="${mchtBrandRateChanges}" var="mchtBrandRateChange">
							   <c:if test="${mchtBrandRateChange.mchtProductBrandId eq mchtProductBrand.id}">value="${mchtBrandRateChange.popCommissionRate}" </c:if>
						</c:forEach>
						>
					</c:if>
					<c:if test="${empty mchtBrandRateChanges}">
						<input type="text" name="popCommissionRate" mchtProductBrandId="${mchtProductBrand.id}" data-type="money" value='<fmt:formatNumber type="number" pattern="0.00" maxFractionDigits="2" value="${mchtProductBrand.popCommissionRate}"></fmt:formatNumber>'>
					</c:if>
				</td>
			</tr>
			</c:forEach>
        </table>
        </c:if>

<c:if test="${isView == 0}">
		<br>
        <div>
			<span class="table-title">法务审核结果</span>
		</div>
		<table class="gridtable">
			<tr>
          		<td colspan="1">合作变更函</td>
          		<td>
          			<a href="${pageContext.request.contextPath}/cooperationChangeApply/preview.shtml?id=${cooperationChangeApply.id}" target="_blank">【预览查看】</a>
          		</td>
          	</tr>
			<tr>
          		<td colspan="1">合作变更函审核状态</td>
          		<td>
          			<input type="radio" name="fwAuditStatus" value="1" <c:if test="${cooperationChangeApply.fwAuditStatus eq 1}">checked="checked"</c:if>>通过
          			<input type="radio" name="fwAuditStatus" value="2" <c:if test="${cooperationChangeApply.fwAuditStatus eq 2}">checked="checked"</c:if>>驳回
          		</td>
          	</tr>
          	<tr id="fwRejectReasonTr">
          		<td colspan="1">备注/驳回理由<span style="color: red;">*</span></td>
          		<td>
          			<textarea rows="10" cols="80" id="fwAuditRemarks" name="fwAuditRemarks" maxlength="256"></textarea>
          		</td>
          	</tr>

          	<tr>
	          	<td colspan="1">操作</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;margin-left: 20px;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
	     	</tr>

		</table>
	</div>
	</c:if>

	<br>
	<div>
		<span class="table-title">审核日志</span>
	</div>
	<table class="gridtable">
		<tr>
			<td colspan="1" class="title" style="text-align: center;">时间</td>
			<td colspan="1" class="title" style="text-align: center;">操作人</td>
			<td colspan="1" class="title" style="text-align: center;">内容</td>
			<td colspan="1" class="title" style="text-align: center;">备注/驳回原因</td>
		</tr>
		<c:forEach items="${cooperationChangeApplyLogList}" var="cooperationChangeApplyLog">
		<tr>
			<td colspan="1" style="text-align: center">
				<fmt:formatDate value="${cooperationChangeApplyLog.createDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
			</td>
			<td colspan="1" style="text-align: center">${cooperationChangeApplyLog.staffName}</td>
			<td colspan="1" style="text-align: center">
				<c:if test="${cooperationChangeApplyLog.type eq 0 && cooperationChangeApplyLog.status eq 1}">
					招商审核通过
				</c:if>
				<c:if test="${cooperationChangeApplyLog.type eq 0 && cooperationChangeApplyLog.status eq 2}">
					招商审核驳回
				</c:if>
				<c:if test="${cooperationChangeApplyLog.type eq 1 && cooperationChangeApplyLog.status eq 1}">
					法务审核通过
				</c:if>
				<c:if test="${cooperationChangeApplyLog.type eq 1 && cooperationChangeApplyLog.status eq 2}">
					法务审核驳回
				</c:if>
				<c:if test="${cooperationChangeApplyLog.type eq 2 && cooperationChangeApplyLog.status eq 1}">
					法务归档通过
				</c:if>
				<c:if test="${cooperationChangeApplyLog.type eq 2 && cooperationChangeApplyLog.status eq 2}">
					法务归档驳回
				</c:if>
			</td>
			<td colspan="1" style="text-align: center">${cooperationChangeApplyLog.remarks}</td>
		</tr>
		</c:forEach>
	</table>

		<div class="video_box" style="position:fixed; width:700px; height:250px; top:20%; left:35%; display: none;" id="shopInfoDiv">
	    <a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/close.png"></a>
	    <div class="panel panel-default" style="margin-bottom:0px;">
		    <div class="modal-header">
				<h3 class="modal-title" id="shopInfoTitle">
					店铺信息修改
				</h3>
			</div>
			<form action="" id="shopInfoForm">
		    <table class="gridtable">
				<tr>
					<td class="title">店铺类型</td>
					<td colspan="2" >
					   <select  name="shopType" id="shopType" onchange="changeShopType();" disabled="disabled">
					   	   <option value="">--请选择--</option>
						   <c:forEach var="shopTypeStatus" items="${shopTypeStatusList}">
						   		<option value="${shopTypeStatus.statusValue}"   <c:if test="${cooperationChangeApply.shopType==shopTypeStatus.statusValue}">selected</c:if>>${shopTypeStatus.statusDesc}</option>
						   </c:forEach>
	                   </select>
	                   <span style="color:#999999;">开设旗舰店如不是自主品牌，须取得品牌商的旗舰店独家授权</span>
					</td>
				</tr>
						
			    <tr>
					<td class="title">公司字号</td>
					<td colspan="2" >
						<input type="text" id="businessFirms" name="businessFirms" onkeyup="generateShopName();" value="${cooperationChangeApply.businessFirms}" >
						<span style="color:#999999;">例：厦门聚买网络科技有限公司，字号：聚买</span>
					</td>
				</tr>
			    <tr>
					<td class="title">品牌</td>
					<td colspan="2">
						<input type="text" id="brand" name="brand" onkeyup="generateShopName();" value="${cooperationChangeApply.brand}" >
						<span style="color:#999999;">填写须与商标证书名称一致</span>
					</td>
				</tr>
			    <tr>
					<td class="title">品类</td>
					<td colspan="2" class="text-left">
						<input type="text" id="productType" name="productType" onkeyup="generateShopName();" value="${cooperationChangeApply.productType}" >
						<span style="color:#999999;">只能选择一种品类，且要与商标类目一致</span>
					</td>
				</tr>
				
				<tr>
					<td class="title">店铺名</td>
					<td colspan="2" class="text-left">
						<input type="text" id="shopName" name="shopName" value="${cooperationChangeApply.shopName}">
					</td>
				</tr>
				
				<tr>
					<td class="title">合作模式</td>
					<td colspan="2">
						<span style="padding: 0 10px;"><c:if test="${mchtInfo.mchtType == 1}">SPOP</c:if><c:if test="${mchtInfo.mchtType == 2}">POP</c:if></span>
					</td>
				</tr>
				
				<tr>
					<td class="title">简要描述</td>
					<td colspan="2" >
					<c:if test="${mchtInfo.mchtType==1}">
					<div style="padding: 10px;">
					SPOP：<br>
					
					由商家定结算价和零售价；<br>
					
					商家开具增值税专用发票给平台；<br>
					
					订单发票由平台提供并邮寄给客户；<br>
					
					商家负责商品的所有服务，包括售前、发货、退换货等服务；<br>
					
					平台为商家提供技术服务，包括销售平台技术支持和流量支持；
					</div>
					</c:if>
					<c:if test="${mchtInfo.mchtType==2}">
					<div style="padding: 10px">
					          开放POP：<br>
	
						由商家自主定价销售，订单发票由商家提供并邮寄给客户；<br>
						
						商家负责商品的所有服务，包括售前、发货、退换货等服务；<br>
						
						平台为商家提供技术服务，包括销售平台技术支持和流量支持；<br>
						
					</div>
					</c:if>
					</td>
				</tr>
	        </table>
	        </form>
		</div>
</div>

<!--弹出div End-->
<div class="black_box" style="display: none;"></div>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
