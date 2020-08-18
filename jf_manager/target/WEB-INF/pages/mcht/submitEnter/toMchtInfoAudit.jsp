<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
var idCardPic_viewer;
var businessLicense_viewer;
var managerIdCard_viewer;
var managerBusinessLicense_viewer;
var industryLicense_viewer;
var contact_viewer;
var boaalPic_viewer;
var idcardImg_viewer;
var idcardImgAB0;
var idcardImgAB1;
var idcardImgAB2;
var idcardImgAB3;
var idcardImgAB4;

<c:forEach var="mchtProductBrandCustom" items="${mchtProductBrandCustoms}" varStatus="index">
	<c:forEach var="mchtBrandAptitudeCustom" items="${mchtProductBrandCustom.mchtBrandAptitudeCustoms}" varStatus="idx">
		var aptitudePic_viewer${index.index}${idx.index};
		var mchtBrandInspectionPic_viewer${index.index};
		var mchtBrandOtherPics_viewer${index.index};
		var platformAuthPic_viewer${index.index};
		var mchtBrandInvoicePic_viewer${index.index};
	</c:forEach>
</c:forEach>

$(function(){
	$('#brandType').val(${mchtInfo.brandType});
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	
	<c:forEach var="mchtProductBrandCustom" items="${mchtProductBrandCustoms}" varStatus="index">
		mchtBrandInspectionPic_viewer${index.index} = new Viewer(document.getElementById('mchtBrandInspectionPic_viewer${index.index}'), {});
		mchtBrandOtherPics_viewer${index.index} = new Viewer(document.getElementById('mchtBrandOtherPics_viewer${index.index}'), {});
		platformAuthPic_viewer${index.index} = new Viewer(document.getElementById('platformAuthPic_viewer${index.index}'), {});
		mchtBrandInvoicePic_viewer${index.index} = new Viewer(document.getElementById('mchtBrandInvoicePic_viewer${index.index}'), {});
	<c:forEach var="mchtBrandAptitudeCustom" items="${mchtProductBrandCustom.mchtBrandAptitudeCustoms}" varStatus="idx">
		aptitudePic_viewer${index.index}${idx.index} = new Viewer(document.getElementById('aptitudePic_viewer${index.index}${idx.index}'), {});
	</c:forEach>
	</c:forEach>
	
	 if(document.getElementById('managerIdCard_viewer') != null){
		managerIdCard_viewer = new Viewer(document.getElementById('managerIdCard_viewer'), {});
	}
	if(document.getElementById('managerBusinessLicense_viewer') != null){
		managerBusinessLicense_viewer = new Viewer(document.getElementById('managerBusinessLicense_viewer'), {});
	}
	if(document.getElementById('idcardImg') != null){
		idcardImg_viewer = new Viewer(document.getElementById('idcardImg'), {});
	}
	if(document.getElementById('idCardPic_viewer') != null){
		idCardPic_viewer = new Viewer(document.getElementById('idCardPic_viewer'), {});
	}
	if(document.getElementById('businessLicense_viewer') != null){
		idCardPic_viewer = new Viewer(document.getElementById('businessLicense_viewer'), {});
	}
	if(document.getElementById('industryLicense_viewer') != null){
		industryLicense_viewer = new Viewer(document.getElementById('industryLicense_viewer'), {});
	}
	if(document.getElementById('boaalPic_viewer') != null){
		boaalPic_viewer = new Viewer(document.getElementById('boaalPic_viewer'), {});
	}
	if(document.getElementById('idcardImgAB0') != null){
		idcardImgAB0 = new Viewer(document.getElementById('idcardImgAB0'), {});
	}
	if(document.getElementById('idcardImgAB1') != null){
		idcardImgAB1 = new Viewer(document.getElementById('idcardImgAB1'), {});
	}
	if(document.getElementById('idcardImgAB2') != null){
		idcardImgAB2 = new Viewer(document.getElementById('idcardImgAB2'), {});
	}
	if(document.getElementById('idcardImgAB3') != null){
		idcardImgAB3 = new Viewer(document.getElementById('idcardImgAB3'), {});
	}
	if(document.getElementById('idcardImgAB4') != null){
		idcardImgAB4 = new Viewer(document.getElementById('idcardImgAB4'), {});
	}
	
	$('#brandType').val(${mchtInfo.brandType});
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
		var isCompanyInfPerfect = $("input[name='isCompanyInfPerfect']:checked").val();
		if (!isCompanyInfPerfect) {
			commUtil.alertError("请选择公司信息审核结果");
			return;
		}
		var companyInfAuditRemarks=$("#companyInfAuditRemarks").val();
		if(isCompanyInfPerfect == 4){
			if(!companyInfAuditRemarks){
				commUtil.alertError("公司信息审核驳回时，驳回原因不能为空");
				return;
			}
		}
		var contractDeposit = $("#contractDeposit").val();
		if (!contractDeposit) {
			commUtil.alertError("店铺保证金不能为空");
			return;
		}


		var tag = true;

		if(${mchtInfo.mchtType eq '2' ||  (mchtInfo.mchtType eq '1'   &&  mchtInfo.isManageSelf eq '0'  ) } ) {


			//商家对接人信息审核
			$("table[name='mchtContactTable']").each(function (index) {
				if (!$("input[name='contactAuditStatus" + index + "']:checked").val()) {
					commUtil.alertError("请选择" + $(this).find('td').eq(1).text() + "信息审核的审核结果");
					return tag = false;
				}
				if ($(totalAuditStatus == 2) && $("input[name='contactAuditStatus" + index + "']:checked").val() == 2 && $('#rejectReasons' + index).val() == "") {
					commUtil.alertError($(this).find('td').eq(1).text() + "信息审核的驳回原因不可为空");
					return tag = false;
				}
			});
			if (!tag) {
				return;
			}


			$("table[name='mchtContactTable']").each(function (index) {
				if (!$("input[name='contactAuditStatus" + index + "']:checked").val()) {
					commUtil.alertError("请选择" + $(this).find('td').eq(1).text() + "信息审核的审核结果");
					return tag = false;
				}
				if ($(totalAuditStatus == 2) && $("input[name='contactAuditStatus" + index + "']:checked").val() == 2 && $('#rejectReasons' + index).val() == "") {
					commUtil.alertError($(this).find('td').eq(1).text() + "信息审核的驳回原因不可为空");
					return tag = false;
				}

			});
			if (!tag) {
				return;
			}
		}

			$("table[name='mchtContactTable']").each(function (index) {
				var mchtContactPictures1 = [];
				var lis = $('#mchtContactPictures1' + index).find("li");
				if ($.trim($(this).find("td").eq(1).text()) == "店铺总负责人") {
					lis.each(function (index, item) {
						var path = $("img", item).attr("path");
						var def = ($(".def", item).length == 1 ? "1" : "0");
						var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
						mchtContactPictures1.push(pic);
					});

					if(${mchtInfo.mchtType eq '2' ||  (mchtInfo.mchtType eq '1'   &&  mchtInfo.isManageSelf eq '0' ) } ) {
						if (mchtContactPictures1.length < 2) {
						commUtil.alertError("请上传" + $(this).find('td').eq(1).text() + "身份证正反面");
						return tag = false;
						}
					};
				}
				;
			});
			if (!tag) {
				return;
			}



		
		var totalAuditStatus = $("input[name='totalAuditStatus']:checked").val();
		if (!totalAuditStatus) {
			commUtil.alertError("请选择商家信息资质总审核的审核结果");
			return;
		}
		if(totalAuditStatus == 2){//总审通过
			if(isCompanyInfPerfect == 4){
				commUtil.alertError("公司信息审核结果驳回，商家信息资质总审核无法通过");
				return;
			}
			
			$("table[name='mchtContactTable']").each(function(index){
				if($("input[name='contactAuditStatus"+index+"']:checked").val()==2){
					commUtil.alertError($(this).find('td').eq(1).text()+"审核结果驳回，商家信息资质总审核无法通过");
					return tag = false;
				}
			});
			if(!tag){
				return;
			}
				
			var i=0;
			$("table[name='mchtProductBrandTable']").each(function(){
				var mchtProductBrandAuditStatus = $(this).find("input[type='radio']:checked").val();
				if(mchtProductBrandAuditStatus && mchtProductBrandAuditStatus == 3){
					return;
				}else{
					i++;
				}
			});
			<c:if test="${mchtInfo.settledType eq 1}">
			if(i == $("table[name='mchtProductBrandTable']").length && $("table[name='mchtProductBrandTable']").length>0){
				/* commUtil.alertError("至少需要一个品牌审核通过，商家信息资质总审核无法通过");
				return; */
			}
			</c:if>
		}
		
		var totalAuditRemarks=$("#totalAuditRemarks").val();
		if(totalAuditStatus == 3){//总审驳回
			if(!totalAuditRemarks){
				commUtil.alertError("商家信息资质总审核驳回时，驳回原因不能为空");
				return;
			}
		}
		

		
		var agreementBeginDate = $("#agreementBeginDate").val();
		var agreementEndDate = $("#agreementEndDate").val();
		if(!agreementBeginDate && totalAuditStatus == 2){
			commUtil.alertError("请选择合同开始日期");
			return;
		}
		if(!agreementEndDate && totalAuditStatus == 2){
			commUtil.alertError("请选择合同结束日期");
			return;
		}
		
		var mchtBlPics = [];
		var lis = $("#mchtBlPic").find("li");
		lis.each(function(index, item) {
			var path = $("img", item).attr("path");
			var def = ($(".def", item).length == 1 ? "1" : "0");
			var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
			mchtBlPics.push(pic);
		});
		if(mchtBlPics.length > 0){
			$("#mchtBlPics").val(JSON.stringify(mchtBlPics));
		}else{
			commUtil.alertError("请上传营业执照副本");
			return;
		}
			
	/*	if($("#mchtBoaalPicPicstures").find("li").length != 1){
			<c:if test="${mchtInfo.settledType eq 1}">
			commUtil.alertError("请上传银行开户许可证");
			</c:if>
			<c:if test="${mchtInfo.settledType eq 2}">
			commUtil.alertError("请上传经营者银行卡信息");
			</c:if>
			return;
		}*/



			//身份证相关
			var mchtBoaalPicPicstures = $("#mchtBoaalPicPicstures img").attr("path");
			var mchtIdcardImgs = [];
			var lis = $("#mchtIdcardImg").find("li");
			lis.each(function (index, item) {
				var path = $("img", item).attr("path");
				var def = ($(".def", item).length == 1 ? "1" : "0");
				var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
				mchtIdcardImgs.push(pic);
			});
		//自营SPOP不需要审核
		if(${mchtInfo.mchtType eq '1' && mchtInfo.isManageSelf eq '1'} ) {
			if (mchtIdcardImgs.length > 0) {
				$("#mchtIdcardImgs").val(JSON.stringify(mchtIdcardImgs));
			}
		}else {
			if (mchtIdcardImgs.length > 0) {
				$("#mchtIdcardImgs").val(JSON.stringify(mchtIdcardImgs));
			} else {
				commUtil.alertError("请上传身份证正反面");
				return;
			}
		}

			var mchtContactPictures11 = "";
			if (document.getElementById('mchtContactPictures2') != null) {
				var mchtContactPictures2 = [];
				var lis = $("#mchtContactPictures2").find("li");
				lis.each(function (index, item) {
					var path = $("img", item).attr("path");
					var def = ($(".def", item).length == 1 ? "1" : "0");
					var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
					mchtContactPictures2.push(pic);
				});
				mchtContactPictures11 = JSON.stringify(mchtContactPictures2);

				if(${mchtInfo.mchtType eq '2' ||  (mchtInfo.mchtType eq '1'   &&  mchtInfo.isManageSelf eq '0'  ) } ) {
					if (mchtContactPictures2.length < 0) {
					commUtil.alertError("请上传店铺总负责人手持身份证正反面");
					return;
				  }
				}
			}



		var companyInfAuditInnerRemarks=$("#companyInfAuditInnerRemarks").val();
		var depositContent=$("#depositContent").val();
		var mchtProductBrandArray = [];
		var mchtType = "${mchtInfo.mchtType}";
		var mchtProductBrandChecked = true;
		var mchtProductBrandCheckedMsg = "";
		var mchtContactList = [];
		var mchtContacts = "";
		// debugger;
		$("table[name='mchtContactTable']").each(function(index){
			// debugger;
			var id = $("input[name='contactAuditStatus"+index+"']:checked").attr("id");
			var contactAuditStatus = $("input[name='contactAuditStatus"+index+"']:checked").val();
			if (contactAuditStatus =='1' ||contactAuditStatus =='2'){
			var rejectReasons = $("#rejectReasons"+index).val();
			var innerRemarks = $("#innerRemarks"+index).val();
			
			var mchtContactPictures1 = [];
			var lis = $('#mchtContactPictures1'+index).find("li");
			lis.each(function(index, item) {
				var path = $("img", item).attr("path");
				mchtContactPictures1.push(path);
			});
			var mchtContactPicturesAB = JSON.stringify(mchtContactPictures1);
			var mchtContact = {id:id,contactAuditStatus:contactAuditStatus,rejectReasons:rejectReasons,innerRemarks:innerRemarks,mchtContactPicturesAB:mchtContactPicturesAB};
			mchtContactList.push(mchtContact);
			}
		});
		mchtContacts = JSON.stringify(mchtContactList);
		$("input[name='productBrandName']").each(function(index){
			var mchtProductBrandObj = {};
			var mchtProductBrandId = $(this).attr("mchtProductBrandId");
			mchtProductBrandObj.id = mchtProductBrandId;
			var productBrandName = $(this).val();
			if(!productBrandName){
				mchtProductBrandChecked = false;
				mchtProductBrandCheckedMsg = "申请品牌名称不能为空";
				return false;
			}
			mchtProductBrandObj.productBrandName = productBrandName;
			
			/*if(mchtType == "2"){*/
				var popCommissionRate = $(this).closest("table").find("input[name='popCommissionRate']").val();
				if(!popCommissionRate){
					mchtProductBrandChecked = false;
					mchtProductBrandCheckedMsg = "品牌的技术服务费率不能为空";
					return false;
				}
			if (parseInt(popCommissionRate) < 0) {
				mchtProductBrandChecked = false;
				mchtProductBrandCheckedMsg = "品牌的技术服务费率不能小于0";
				return false;
			}
			if (parseInt(popCommissionRate) >= 1) {
				mchtProductBrandChecked = false;
				mchtProductBrandCheckedMsg = "品牌的技术服务费率不能大于等于1";
				return false;
			}
			mchtProductBrandObj.popCommissionRate = popCommissionRate;
			/*}else{//TODO SPOP
				var priceModel = $(this).closest("table").find("select[name='priceModel']").val();
				if(!priceModel){
					mchtProductBrandChecked = false;
					mchtProductBrandCheckedMsg = "品牌的SPOP定价方式不能为空";
					return false;
				}
				mchtProductBrandObj.priceModel = priceModel;
			}*/
			var auditStatus = $(this).closest("table").find("input[name='auditStatus"+index+"']:checked").val();
			if(!auditStatus){
				mchtProductBrandChecked = false;
				mchtProductBrandCheckedMsg = "品牌的法务确认结果未审核";
				return false;
			}
			mchtProductBrandObj.auditStatus = auditStatus;
			
			var productBrandId = $(this).closest("tr").next().next().find("input[name='productBrandId"+index+"']").val();
			if(!productBrandId && auditStatus == 3){//审核通过才必须选择品牌库品牌
				mchtProductBrandChecked = false;
				mchtProductBrandCheckedMsg = "请选择品牌库品牌";
				return false;
			}
			mchtProductBrandObj.productBrandId = productBrandId;
			
			//TODO 备注处理
			var $lastTr = $(this).closest("table").find("tr").last();
			var auditRemarks = $lastTr.prev().find("textarea").eq(0).val();
			var auditInnerRemarks = $lastTr.find("textarea").eq(0).val();
			if(auditStatus == 4){
				if(!auditRemarks){
					mchtProductBrandChecked = false;
					mchtProductBrandCheckedMsg = "品牌审核驳回时，驳回原因不能为空。";
					return false;
				}
			}
			
			var mchtPlatformAuthPictures = [];
			var lis = $("#mchtPlatformAuthPictures"+index).find("li");
			lis.each(function(index, item) {
				var path = $("img", item).attr("path");
				var def = ($(".def", item).length == 1 ? "1" : "0");
				var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
				mchtPlatformAuthPictures.push(pic);
			});
			mchtProductBrandObj.mchtPlatformAuthPics = JSON.stringify(mchtPlatformAuthPictures);
			
			var mchtBrandInvoicePictures = [];
			var lis = $("#mchtBrandInvoicePictures"+index).find("li");
			lis.each(function(index, item) {
				var path = $("img", item).attr("path");
				var def = ($(".def", item).length == 1 ? "1" : "0");
				var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
				mchtBrandInvoicePictures.push(pic);
			});
			mchtProductBrandObj.mchtBrandInvoicePics = JSON.stringify(mchtBrandInvoicePictures);
			
			var mchtBrandInspectionPictures = [];
			var lis = $("#mchtBrandInspectionPictures"+index).find("li");
			lis.each(function(index, item) {
				var path = $("img", item).attr("path");
				var def = ($(".def", item).length == 1 ? "1" : "0");
				var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
				mchtBrandInspectionPictures.push(pic);
			});
			mchtProductBrandObj.mchtBrandInspectionPics = JSON.stringify(mchtBrandInspectionPictures);
			
			var mchtBrandOtherPictures = [];
			var lis = $("#mchtBrandOtherPictures"+index).find("li");
			lis.each(function(index, item) {
				var path = $("img", item).attr("path");
				var def = ($(".def", item).length == 1 ? "1" : "0");
				var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
				mchtBrandOtherPictures.push(pic);
			});
			mchtProductBrandObj.mchtBrandOtherPics = JSON.stringify(mchtBrandOtherPictures);
			//TODO 商标注册证或受理通知书
			var mchtBrandAptitudeArray = [];
			$(this).closest("tr").next().next().next().find("table").each(function(idx){
				var mchtBrandAptitudeId = $(this).attr("mchtBrandAptitudeId");
				var mchtBrandAptitudePictures = [];
				$(this).find("ul").find("li").each(function(index, item) {
					var path = $("img", item).attr("path");
					var def = ($(".def", item).length == 1 ? "1" : "0");
					var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
					mchtBrandAptitudePictures.push(pic);
				});
				mchtBrandAptitudeArray.push({
					"mchtBrandAptitudeId":mchtBrandAptitudeId,
					"mchtBrandAptitudePics":JSON.stringify(mchtBrandAptitudePictures)
				});
			});
			mchtProductBrandObj.mchtBrandAptitudes = JSON.stringify(mchtBrandAptitudeArray);
			
			mchtProductBrandObj.auditRemarks=auditRemarks;
			mchtProductBrandObj.auditInnerRemarks=auditInnerRemarks;
			mchtProductBrandArray.push(mchtProductBrandObj);
		});
		var mchtProductBrandJsonStr = JSON.stringify(mchtProductBrandArray);
		if(!mchtProductBrandChecked){
			commUtil.alertError(mchtProductBrandCheckedMsg);
			return;
		}
		var licenseIsMust = $("input[name='licenseIsMust']:checked").val();
		var businessLicensePic = $("#businessLicensePic").val();
		var licenseStatus = $("input[name='licenseStatus']:checked").val();
		var licenseRejectReason = $("#licenseRejectReason").val();
		submitting = true;
		$.ajax({
			url : "${pageContext.request.contextPath}/mcht/mchtInfoAudit.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"mchtId":$("#mchtId").val(),
					"isCompanyInfPerfect":isCompanyInfPerfect,
					"companyInfAuditRemarks":companyInfAuditRemarks,
					"companyInfAuditInnerRemarks":companyInfAuditInnerRemarks,
					"agreementBeginDate":agreementBeginDate,
					"agreementEndDate":agreementEndDate,
					"contractDeposit":contractDeposit,
					"depositContent":depositContent,
					"totalAuditStatus":totalAuditStatus,
					"totalAuditRemarks":totalAuditRemarks,
					"mchtProductBrandJsonStr":mchtProductBrandJsonStr,
					"mchtBlPics":$("#mchtBlPics").val(),
					"mchtIdcardImgs":$("#mchtIdcardImgs").val(),
					"licenseIsMust":licenseIsMust,
					"businessLicensePic":businessLicensePic,
					"licenseStatus":licenseStatus,
					"licenseRejectReason":licenseRejectReason,
					"mchtContacts":mchtContacts,
					"mchtContactPictures11":mchtContactPictures11,
					"mchtBoaalPicPicstures":mchtBoaalPicPicstures,
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
		$
				.ajax({
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

	function toZsConfirm(id) {
		$.ligerDialog
				.open({
					height : $(window).height() * 0.8,
					width : $(window).width() * 0.5,
					title : "招商确认",
					name : "INSERT_WINDOW",
					url : "${pageContext.request.contextPath}/mcht/toZsConfirm.shtml?mchtId="
							+ id,
					showMax : true,
					showToggle : false,
					showMin : false,
					isResize : true,
					slide : false,
					data : null
				});
	}

	function addBrand(id) {
		$.ligerDialog
				.open({
					height : $(window).height() - 40,
					width : $(window).width() - 40,
					title : "品牌修改",
					name : "INSERT_WINDOW",
					url : "${pageContext.request.contextPath}/service/prod/product_brand/add2BrandFromMchtBrand.shtml?id="
							+ id,
					showMax : true,
					showToggle : false,
					showMin : false,
					isResize : true,
					slide : false,
					data : null
				});
	}

	//图片格式验证
	function ajaxFileUploadImg(_this,type) {
		var file = document.getElementById(_this.id).files[0]; 
	    var reader = new FileReader();  
	    reader.onload = function(e) { 
	    	var image = new Image();
	    	image.onload = function() {
	        	ajaxFileUpload(_this,type);
	        };
	        image.src = e.target.result;
	    }
	    reader.readAsDataURL(file);  
	}

	function ajaxFileUpload(_this,type) {
		var id = $(_this).attr("id");
		var data_value = $(_this).attr("data_value");
		$.ajaxFileUpload({
			url:"${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
			secureuri: false,
			fileElementId: id,
			dataType: 'json',
			success: function(result, status) {
			//判断是否是联系人图片上传
			if(type == '1'){
				if(result.RESULT_CODE == 0) {
					$("#mcht"+id).append('<li><p><img src="${pageContext.request.contextPath}/file_servelt'+result.FILE_PATH+'" path="'+result.FILE_PATH+'"></p><a href="javascript:void(0);" class="del">删除</a></li>');
					$(".del").live('click',function(){
						$(this).closest("li").remove();
					});
					
					<c:forEach var="mchtProductBrandCustom" items="${mchtProductBrandCustoms}" varStatus="index">
					<c:forEach var="mchtBrandAptitudeCustom" items="${mchtProductBrandCustom.mchtBrandAptitudeCustoms}" varStatus="idx">
						if(data_value == 'aptitudePic_viewer${index.index}${idx.index}'){
							aptitudePic_viewer${index.index}${idx.index}.destroy();
							aptitudePic_viewer${index.index}${idx.index} = new Viewer(document.getElementById('aptitudePic_viewer${index.index}${idx.index}'), {});
						}
						if(data_value == 'mchtBrandInspectionPic_viewer${index.index}'){
							mchtBrandInspectionPic_viewer${index.index}.destroy();
							mchtBrandInspectionPic_viewer${index.index} = new Viewer(document.getElementById('mchtBrandInspectionPic_viewer${index.index}'), {});
						}
						if(data_value == 'mchtBrandOtherPics_viewer${index.index}'){
							mchtBrandOtherPics_viewer${index.index}.destroy();
							mchtBrandOtherPics_viewer${index.index} = new Viewer(document.getElementById('mchtBrandOtherPics_viewer${index.index}'), {});
						}
						if(data_value == 'platformAuthPic_viewer${index.index}'){
							platformAuthPic_viewer${index.index}.destroy();
							platformAuthPic_viewer${index.index} = new Viewer(document.getElementById('platformAuthPic_viewer${index.index}'), {});
						}
						if(data_value == 'mchtBrandInvoicePic_viewer${index.index}'){
							mchtBrandInvoicePic_viewer${index.index}.destroy();
							mchtBrandInvoicePic_viewer${index.index} = new Viewer(document.getElementById('mchtBrandInvoicePic_viewer${index.index}'), {});
						}
					</c:forEach>
					</c:forEach>
					
					if(data_value == 'idCardPic_viewer'){
						idCardPic_viewer.destroy();
						idCardPic_viewer = new Viewer(document.getElementById('idCardPic_viewer'), {});
					}else if(data_value == 'businessLicense_viewer'){
						businessLicense_viewer.destroy();
						businessLicense_viewer = new Viewer(document.getElementById('businessLicense_viewer'), {});
					} else if(data_value == 'managerIdCard_viewer'){
						managerIdCard_viewer.destroy();
						managerIdCard_viewer = new Viewer(document.getElementById('managerIdCard_viewer'), {}); 
					} else if(data_value == 'managerBusinessLicense_viewer'){
						managerBusinessLicense_viewer.destroy();
						managerBusinessLicense_viewer = new Viewer(document.getElementById('managerBusinessLicense_viewer'), {}); 
					} else if(data_value == 'industryLicense_viewer'){
						industryLicense_viewer.destroy();
						industryLicense_viewer = new Viewer(document.getElementById('industryLicense_viewer'), {});
					}else if(data_value== 'idcardImgAB0'){
						idcardImgAB0.destroy();
						idcardImgAB0 = new Viewer(document.getElementById('idcardImgAB0'), {});
					}else if(data_value== 'idcardImgAB1'){
						idcardImgAB1.destroy();
						idcardImgAB1 = new Viewer(document.getElementById('idcardImgAB1'), {});
					}else if(data_value== 'idcardImgAB2'){
						idcardImgAB2.destroy();
						idcardImgAB2 = new Viewer(document.getElementById('idcardImgAB2'), {});
					}else if(data_value== 'idcardImgAB3'){
						idcardImgAB3.destroy();
						idcardImgAB3 = new Viewer(document.getElementById('idcardImgAB3'), {});
					}else if(data_value== 'idcardImgAB4'){
						idcardImgAB3.destroy();
						idcardImgAB3 = new Viewer(document.getElementById('idcardImgAB4'), {});
					}
				}else {
					alert(result.RESULT_MESSAGE);
				}
			}
				if(type == '2'){
					if(result.RESULT_CODE == 0) {
						if(id == "ContactPictures2" || id == "BoaalPicPicstures"){//如果是手持身份证，只能上传一张
							$("#mcht"+id).html('<li><p><img src="${pageContext.request.contextPath}/file_servelt'+result.FILE_PATH+'" path="'+result.FILE_PATH+'"></p><a href="javascript:void(0);" class="del">删除</a></li>');	
						}else if(id != "ContactPictures2"){//如果是身份证正反面，只能上传两张
							if($("#mcht"+id).children('li').length<2){
								$("#mcht"+id).append('<li><p><img src="${pageContext.request.contextPath}/file_servelt'+result.FILE_PATH+'" path="'+result.FILE_PATH+'"></p><a href="javascript:void(0);" class="del">删除</a></li>');	
							}else{
								$("#mcht"+id).children('li').eq(1).remove();
								$("#mcht"+id).append('<li><p><img src="${pageContext.request.contextPath}/file_servelt'+result.FILE_PATH+'" path="'+result.FILE_PATH+'"</p><a href="javascript:void(0);" class="del">删除</a></li>');	
							}
						}
						
							$(".del").live('click',function(){
								$(this).closest("li").remove();
							});
					} else {
						alert(result.RESULT_MESSAGE);
					}
				}
			},
			error: function(result, status, e) {
				alert("服务异常");
			}
		});
	}

	function toEditCompanyType(id){
		$.ligerDialog.open({
			height : 250,
			width : 400,
			title : "修改公司类型",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/mcht/toEditCompanyType.shtml?id="+id,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	function toEditYearlyInspectionDate(id){
		$.ligerDialog.open({
			height : 350,
			width : 400,
			title : "修改营业执照有效期",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/mcht/toEditYearlyInspectionDate.shtml?id="+id,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	function toEditScopeOfBusiness(id){
		$.ligerDialog.open({
			height : 500,
			width : 800,
			title : "修改经营范围",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/mcht/toEditScopeOfBusiness.shtml?id="+id,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	function toEditDate(id,type){
		$.ligerDialog.open({
			height: 400,
			width: 600,
			title: "修改有效期",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/toEditDate.shtml?id="+ id +"&type=" + type,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
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
	
	 function modify(id) {
			$.ligerDialog.open({
			height: 340,
			width: 300,
			title: "修改主营经营品牌类型",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/modifyProductType2.shtml?id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	 
	 //修改主要经营品牌类型成功重新加载数据
	 function freshen(id){
			$.ajax({
				url : "${pageContext.request.contextPath}/mcht/freshenFwAudit.shtml?id="+id,
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				timeout : 30000,
				success : function(data) {
					var mchtInfo = data.mchtInfo;
					$('#brandType').val(mchtInfo.brandType);
					$('#brandTypeDesc').val(mchtInfo.brandTypeDesc);
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});		 
	 }
	 
	//公司住所
	 function toEditCompanyAddress(id,name){
	 	$.ligerDialog.open({
	 		height : 280,
	 		width : 800,
	 		title : name,
	 		name : "INSERT_WINDOW",
	 		url : "${pageContext.request.contextPath}/mcht/toEditCompanyAddress.shtml?id="+id,
	 		showMax : true,
	 		showToggle : false,
	 		showMin : false,
	 		isResize : true,
	 		slide : false,
	 		data : null
	 	});
	 }
	
	//修改发照时间
	 function toEditFoundedDate(id,name){
	 	$.ligerDialog.open({
	 		height : 280,
	 		width : 800,
	 		title : name,
	 		name : "INSERT_WINDOW",
	 		url : "${pageContext.request.contextPath}/mcht/toEditFoundedDate.shtml?id="+id,
	 		showMax : true,
	 		showToggle : false,
	 		showMin : false,
	 		isResize : true,
	 		slide : false,
	 		data : null
	 	});
	 }
	
	//法人身份证有效期
	 function toEditCorporationIdcardDate(id,name){
	 	$.ligerDialog.open({
	 		height : 210,
	 		width : 800,
	 		title : name,
	 		name : "INSERT_WINDOW",
	 		url : "${pageContext.request.contextPath}/mcht/toEditCorporationIdcardDate.shtml?id="+id,
	 		showMax : true,
	 		showToggle : false,
	 		showMin : false,
	 		isResize : true,
	 		slide : false,
	 		data : null
	 	});
	 }
</script>

<html>
<body>
		<input type="hidden" id="mchtId" value="${mchtId}" onclick="freshen(${mchtId});">
		<!-- 公司信息审核  -->
		<c:if test="${mchtInfo.settledType eq 1}">
		<br>
		<br>
		<div><span class="table-title" >公司信息审核</span></div>
		<br>
		<table class="gridtable" style="width:800px;">
			<tr>
				<td  colspan="1" class="title" style="width: 200px;">入驻类型</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${mchtInfo.settledType eq 1}">
						企业公司
					</c:if>
					<c:if test="${mchtInfo.settledType eq 2}">
						个体商户
					</c:if>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title" style="width: 200px;">公司名称</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.companyName}
				</td>
			</tr>
			<tr>
			<tr>
				<td  colspan="1" class="title" style="width: 200px;">店铺名称</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.shopName}
				</td>
				</tr>
			<tr>
			
				<td  colspan="1" class="title">公司类型</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.companyType}
					<c:if test="${not empty mchtFwContactId}">
						<a href="javascript:;" onclick="toEditCompanyType(${mchtInfo.id});">【修改】</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">营业执照注册号</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.uscc}
				</td>
				
			</tr>
			<tr>
				<td  colspan="1" class="title">注册资本</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.regCapital}万元（${mchtInfo.regFeeTypeDesc}）
				</td>
				
			</tr>
		
		   <tr>
			<td  colspan="1" class="title">公司住所</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<%-- ${mchtInfo.companyAddress}
					<c:if test="${not empty mchtFwContactId || sessionScope.USER_SESSION.staffID==1}">
						<a href="javascript:;" onclick="toEditcompanyAddress(${mchtInfo.id});">【修改】</a>
				    </c:if>	 --%>			
					${mchtInfo.companyAddress}<a href="javascript:;" onclick="toEditCompanyAddress(${mchtInfo.id},'修改住所地址');">【修改】</a>				
				</td>
			</tr>
		
			<tr>
		
			<td  colspan="1" class="title">成立时间</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<fmt:formatDate value="${mchtInfo.foundedDate}" pattern="yyyy-MM-dd"/><a href="javascript:;" onclick="toEditFoundedDate(${mchtInfo.id},'修改成立时间');">【修改】</a>				
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">营业执照有效期</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<fmt:formatDate value="${mchtInfo.yearlyInspectionDate}" pattern="yyyy-MM-dd"/>
					<c:if test='${yearlyInspectionInvalid==1}'>
						<span style="color: red;font-size: 15px;">【到期】</span>
					</c:if>
					<c:if test="${empty mchtInfo.yearlyInspectionDate && not empty mchtFwContactId}"><a href="javascript:;" onclick="toEditYearlyInspectionDate(${mchtInfo.id});">【添加有效期】</a></c:if>
					<c:if test="${not empty mchtInfo.yearlyInspectionDate && not empty mchtFwContactId}"><a href="javascript:;" onclick="toEditYearlyInspectionDate(${mchtInfo.id});">【修改有效期】</a></c:if>
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">经营范围</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${empty mchtInfo.scopeOfBusiness && not empty mchtFwContactId}"><a href="javascript:;" onclick="toEditScopeOfBusiness(${mchtInfo.id});">【添加经营范围】</a></c:if>
					<c:if test="${not empty mchtInfo.scopeOfBusiness && not empty mchtFwContactId}">${mchtInfo.scopeOfBusiness}<a href="javascript:;" onclick="toEditScopeOfBusiness(${mchtInfo.id});">【修改经营范围】</a></c:if>
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">法人姓名</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.corporationName}
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">法人身份证号</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.corporationIdcardNo}
				</td>	
			</tr>
			<tr>
				<td  colspan="1" class="title">身份证正背面</td>
				<td  colspan="3" align="left" class="l-table-edit-td" id="idCardPic_viewer">
					<t:imageList name="mchtIdcardImg" list="${idcardImgList}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
					<div style="float: left;height: 105px;margin: 10px;">
						<input style="position:absolute; opacity:0;width: 110px;" type="file" id="IdcardImg" data_value="idCardPic_viewer" name="file" onchange="ajaxFileUploadImg(this,1);" /> 
						<input type="button" style="width: 70px;" value="上传图片" /> 
					</div>
					<input type="hidden" id="mchtIdcardImgs" name="mchtIdcardImgs" >
				</td>	
			</tr>
			<tr>
				<td  colspan="1" class="title">法人身份证有效期</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<fmt:formatDate value="${mchtInfo.corporationIdcardDate}" pattern="yyyy-MM-dd"/><a href="javascript:;" onclick="toEditCorporationIdcardDate(${mchtInfo.id},'修改法人身份证有效期');">【修改】</a>	
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">法人手机</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.corporationMobile }
					</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">公司总机</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.companyTel}
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">公司通讯地址</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.contactProvinceName}${mchtInfo.contactCityName}${mchtInfo.contactCountyName}${mchtInfo.contactAddress}
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">营业执照副本</td>
				<td  colspan="3" align="left" class="l-table-edit-td" id="businessLicense_viewer">
					<t:imageList name="mchtBlPic" list="${blPicList}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
					<div style="float: left;height: 105px;margin: 10px;">
						<input style="position:absolute; opacity:0;width: 110px;" type="file" id="BlPic" name="file" data_value="businessLicense_viewer" onchange="ajaxFileUploadImg(this,1);" /> 
						<input type="button" style="width: 70px;" value="上传图片" /> 
					</div>
					<input type="hidden" id="mchtBlPics" name="mchtBlPics">
				</td>	
				
			</tr>
			<tr>
				<td colspan="1" class="title">主营经营品牌类型</td>
				<td colspan="2" align="left" class="l-table-edit-td">
                <select style="width:210px;" name="brandType" id="brandType" disabled>
                <option value=""></option>
                <option value="0">品牌官方</option>
                <option value="1">品牌总代</option>
                <option value="2">品牌代理商</option>
                </select>
                <c:if test="${role43}">
                <a href="javascript:modify('${mchtInfo.id}')">【修改】</a>
                </c:if>
                <br><br>
                <textarea disabled rows="5" cols="70" id="brandTypeDesc" name="brandTypeDesc">${mchtInfo.brandTypeDesc}</textarea>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">组织机构代码证</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<img onclick="viewerPic(this.src)" src="${pageContext.request.contextPath}/file_servelt${mchtInfo.occPic}" alt="">
				</td>	
			</tr>
			<tr>
				<td  colspan="1" class="title">税务登记证</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<img onclick="viewerPic(this.src)" src="${pageContext.request.contextPath}/file_servelt${mchtInfo.trcPic}" alt="">
				</td>	
			</tr>
			<tr>
				<td  colspan="1" class="title">一般纳税人资格证</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<img onclick="viewerPic(this.src)" src="${pageContext.request.contextPath}/file_servelt${mchtInfo.atqPic}" alt="">
				</td>	
			</tr>
			<tr>
				<td  colspan="1" class="title">银行开户许可证</td>
				<td  colspan="1" align="left" class="l-table-edit-td" id="boaalPic_viewer">
					<t:imageList name="mchtBoaalPicPicstures" list="${mchtBoaalPicPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
						<div style="float: left;height: 105px;margin: 10px;">
							 <input style="position:absolute; opacity:0;width: 110px;" type="file" id="BoaalPicPicstures" name="file" onchange="ajaxFileUploadImg(this,2);" /> 
							 <input type="button" style="width: 70px;" value="上传图片" /> 
						</div>
				</td>	
			</tr>
			<tr>
				<td  colspan="1" class="title">审核结果</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<input type="radio" name="isCompanyInfPerfect" value="1" <c:if test="${mchtInfo.isCompanyInfPerfect == 1}">checked="checked"</c:if>>通过 
					<input type="radio" name="isCompanyInfPerfect" value="4" <c:if test="${mchtInfo.isCompanyInfPerfect == 4}">checked="checked"</c:if>>驳回
				</td>	
				
			</tr>
			<tr>
				<td  colspan="1" class="title">备注及驳回原因</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<textarea rows="7" cols="70" id="companyInfAuditRemarks" name="companyInfAuditRemarks" >${mchtInfo.companyInfAuditRemarks }</textarea>
				</td>
			</tr>
		</table>
		</c:if>
		
		<c:if test="${mchtInfo.settledType eq 2}">
		<br>
		<br>
		<div><span class="table-title" >公司信息审核</span></div>
		<br>
		<table class="gridtable" style="width:800px;">
			<tr>
				<td  colspan="1" class="title" style="width: 200px;">入驻类型</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${mchtInfo.settledType eq 1}">
						企业公司
					</c:if>
					<c:if test="${mchtInfo.settledType eq 2}">
						个体商户
					</c:if>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title" style="width: 200px;">名称</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.companyName}
				</td>
			</tr>
			<tr>
			<tr>
				<td  colspan="1" class="title" style="width: 200px;">店铺名称</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.shopName}
				</td>
				</tr>
			<tr>
			
				<td  colspan="1" class="title">组成形式</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.companyType}
					<c:if test="${not empty mchtFwContactId}">
						<a href="javascript:;" onclick="toEditCompanyType(${mchtInfo.id});">【修改】</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">营业执照注册号</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.uscc}
				</td>
				
			</tr>
			<tr>
			<td  colspan="1" class="title">经营场所</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.companyAddress}<a href="javascript:;" onclick="toEditCompanyAddress(${mchtInfo.id},'修改经营住所');">【修改】</a>				
				</td>
			</tr>
		
			<tr>
		
			<td  colspan="1" class="title">发照时间</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<fmt:formatDate value="${mchtInfo.foundedDate}" pattern="yyyy-MM-dd"/><a href="javascript:;" onclick="toEditFoundedDate(${mchtInfo.id},'修改发照时间');">【修改】</a>				
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">营业执照有效期</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<fmt:formatDate value="${mchtInfo.yearlyInspectionDate}" pattern="yyyy-MM-dd"/>
					<c:if test='${yearlyInspectionInvalid==1}'>
						<span style="color: red;font-size: 15px;">【到期】</span>
					</c:if>
					<c:if test="${empty mchtInfo.yearlyInspectionDate && not empty mchtFwContactId}"><a href="javascript:;" onclick="toEditYearlyInspectionDate(${mchtInfo.id});">【添加有效期】</a></c:if>
					<c:if test="${not empty mchtInfo.yearlyInspectionDate && not empty mchtFwContactId}"><a href="javascript:;" onclick="toEditYearlyInspectionDate(${mchtInfo.id});">【修改有效期】</a></c:if>
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">经营范围</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${empty mchtInfo.scopeOfBusiness && not empty mchtFwContactId}"><a href="javascript:;" onclick="toEditScopeOfBusiness(${mchtInfo.id});">【添加经营范围】</a></c:if>
					<c:if test="${not empty mchtInfo.scopeOfBusiness && not empty mchtFwContactId}">${mchtInfo.scopeOfBusiness}<a href="javascript:;" onclick="toEditScopeOfBusiness(${mchtInfo.id});">【修改经营范围】</a></c:if>
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">经营者姓名</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.corporationName}
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">经营者身份证号</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.corporationIdcardNo}
				</td>	
			</tr>
			<tr>
				<td  colspan="1" class="title">经营者身份证正背面</td>
				<td  colspan="3" align="left" class="l-table-edit-td" id="managerIdCard_viewer">
					<t:imageList name="mchtIdcardImg" list="${idcardImgList}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt"/>
					<div style="float: left;height: 105px;margin: 10px;">
						<input style="position:absolute; opacity:0;width: 110px;" type="file" id="IdcardImg" name="file" data_value="managerIdCard_viewer"  onchange="ajaxFileUploadImg(this,1);" /> 
						<input type="button" style="width: 70px;" value="上传图片" /> 
					</div>
					<input type="hidden" id="mchtIdcardImgs" name="mchtIdcardImgs" >
				</td>	
			</tr>
			<tr>
				<td  colspan="1" class="title">经营者身份证有效期</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<fmt:formatDate value="${mchtInfo.corporationIdcardDate}" pattern="yyyy-MM-dd"/><a href="javascript:;" onclick="toEditCorporationIdcardDate(${mchtInfo.id},'修改身份证有效期');">【修改】</a>	
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">经营者手机</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.corporationMobile }
					</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">通讯地址</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.contactProvinceName}${mchtInfo.contactCityName}${mchtInfo.contactCountyName}${mchtInfo.contactAddress}
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">营业执照副本</td>
				<td  colspan="3" align="left" class="l-table-edit-td" id="managerBusinessLicense_viewer">
					<t:imageList name="mchtBlPic" list="${blPicList}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
					<div style="float: left;height: 105px;margin: 10px;">
						<input style="position:absolute; opacity:0;width: 110px;" type="file" id="BlPic" name="file" data_value="managerBusinessLicense_viewer" onchange="ajaxFileUploadImg(this,1);" /> 
						<input type="button" style="width: 70px;" value="上传图片" /> 
					</div>
					<input type="hidden" id="mchtBlPics" name="mchtBlPics">
				</td>	
				
			</tr>
			<tr>
				<td  colspan="1" class="title">经营者银行卡信息</td>
				<td  colspan="1" align="left" class="l-table-edit-td" id="boaalPic_viewer">
					<t:imageList name="mchtBoaalPicPicstures" list="${mchtBoaalPicPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
						<div style="float: left;height: 105px;margin: 10px;">
							 <input style="position:absolute; opacity:0;width: 110px;" type="file" id="BoaalPicPicstures" name="file" onchange="ajaxFileUploadImg(this,2);" /> 
							 <input type="button" style="width: 70px;" value="上传图片" /> 
						</div>
				</td>	
			</tr>
			<tr>
				<td colspan="1" class="title">主要经营品牌类型</td>
				<td colspan="2" align="left" class="l-table-edit-td">
                <select style="width:210px;" name="brandType" id="brandType" disabled>
                <option value=""></option>
                <option value="0">品牌官方</option>
                <option value="1">品牌总代</option>
                <option value="2">品牌代理商</option>
                </select>
                <c:if test="${role43}">
                <a href="javascript:modify('${mchtInfo.id}')">【修改】</a>
                </c:if>
                <br><br>
                <textarea disabled rows="5" cols="70" id="brandTypeDesc" name="brandTypeDesc">${mchtInfo.brandTypeDesc}</textarea>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">审核结果</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<input type="radio" name="isCompanyInfPerfect" value="1" <c:if test="${mchtInfo.isCompanyInfPerfect == 1}">checked="checked"</c:if>>通过 
					<input type="radio" name="isCompanyInfPerfect" value="4" <c:if test="${mchtInfo.isCompanyInfPerfect == 4}">checked="checked"</c:if>>驳回
				</td>	
				
			</tr>
			<tr>
				<td  colspan="1" class="title">备注及驳回原因</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<textarea rows="7" cols="70" id="companyInfAuditRemarks" name="companyInfAuditRemarks" >${mchtInfo.companyInfAuditRemarks }</textarea>
				</td>
			</tr>
		</table>
		</c:if>
		
		<br>
		<br>
		<div><span class="table-title" >经营许可证</span></div>
		<br>
		<table class="gridtable" style="width:1200px;">
			<tbody>
			<tr>
				<td class="title">是否必须</td>
				<td>
					<input type="radio" name="licenseIsMust" value="1" <c:if test="${mchtInfo.licenseIsMust == 1}">checked="checked"</c:if>>是
					<input type="radio" name="licenseIsMust" value="0" <c:if test="${mchtInfo.licenseIsMust == 0}">checked="checked"</c:if>>否
				</td>
			</tr>
			<tr>
				<td class="title">行业执照</td>
				<td id="industryLicense_viewer">
					<div><img id="businessLicensePicImg" style="width: 300px;height: 150px" alt="" src="${pageContext.request.contextPath}/file_servelt${mchtInfo.businessLicensePic}"></div>
	    			<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="businessLicensePicFile" name="file" data_value="industryLicense_viewer" onchange="ajaxFileUploadLogo();" />
	    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    			</div>
	    			<input id="businessLicensePic" name="businessLicensePic" type="hidden" value="${mchtInfo.businessLicensePic}">
				</td>
			</tr>
			<tr>
				<td class="title">审核状态</td>
				<td>
					<input type="radio" name="licenseStatus" value="2" <c:if test="${mchtInfo.licenseStatus == 2}">checked="checked"</c:if>>通过
					<input type="radio" name="licenseStatus" value="3" <c:if test="${mchtInfo.licenseStatus == 3}">checked="checked"</c:if>>驳回
					<input type="radio" name="licenseStatus" value="1" <c:if test="${mchtInfo.licenseStatus == 1}">checked="checked"</c:if>>待审
					<input type="radio" name="licenseStatus" value="0" <c:if test="${mchtInfo.licenseStatus == 0}">checked="checked"</c:if>>未申请
				</td>
			</tr>
			<tr>
				<td class="title">备注及驳回原因</td>
				<td>
					<textarea rows="7" cols="70" id="licenseRejectReason" name="licenseRejectReason">${mchtInfo.licenseRejectReason}</textarea>
				</td>
			</tr>
			</tbody>
		</table>
		
		<br>
		<br>
		<div><span class="table-title" >品牌黑名单</span></div>
		<br>
		<table class="gridtable" style="width:1200px;">
			<tbody>
			<tr>
				<td class="title">商家提审时间</td>
				<td class="title">审核时间</td>
				<td class="title">品牌名称</td>
				<td class="title">品牌状态</td>
				<td class="title">内部备注</td>
			</tr>
			<c:forEach var="mchtProductBrand" items="${blackMchtProductBrands}">
			<tr>
				<td  colspan="1">
					<fmt:formatDate value="${mchtProductBrand.commitAuditDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<fmt:formatDate value="${mchtProductBrand.auditDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					${mchtProductBrand.productBrandName}
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<c:if test="${mchtProductBrand.auditStatus == '5'}">
						不签约
					</c:if>
					<c:if test="${mchtProductBrand.auditStatus == '6'}">
						黑名单
					</c:if>
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					${mchtProductBrand.auditInnerRemarks}
				</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		<br>
		<br>
		<div><span class="table-title" >品牌审核</span></div>
		<br>
		<c:forEach var="mchtProductBrandCustom" items="${mchtProductBrandCustoms}" varStatus="index">
		<table class="gridtable" name="mchtProductBrandTable">
			<tbody>
				<tr>
					<td  colspan="1" class="title" style="width:200px;">申请品牌名称*</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<input name="productBrandName" mchtProductBrandId="${mchtProductBrandCustom.id}" value="${mchtProductBrandCustom.productBrandName}">
						<a class="table-title-link" href="javascript:addBrand(${mchtProductBrandCustom.id})" >添加到品牌库</a>
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">资质类型</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<c:if test="${mchtProductBrandCustom.aptitudeType == '1'}">
							自有商标
						</c:if>
						<c:if test="${mchtProductBrandCustom.aptitudeType == '2'}">
							品牌商授权
						</c:if>
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">品牌库品牌 </td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<input type="hidden" name="productBrandId${index.index}" id="productBrandId${index.index}" value="${mchtProductBrandCustom.productBrandId}"/>
						<input type="text" name="brandName" id="brandName${index.index}" value="${mchtProductBrandCustom.brandName}"/>
					</td>	
				</tr>
				<tr>
					<td  colspan="1" class="title">LOGO图片</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<img alt="" onclick="viewerPic(this.src)" src="${pageContext.request.contextPath}/file_servelt${mchtProductBrandCustom.logo}">
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">商标注册证或受理通知书</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
					<c:forEach var="mchtBrandAptitudeCustom" items="${mchtProductBrandCustom.mchtBrandAptitudeCustoms}" varStatus="idx">
						<table class="gridtable" style="margin-top: 10px;" mchtBrandAptitudeId="${mchtBrandAptitudeCustom.id}">
							<tbody>
								<tr>
									<td  colspan="1" class="title" style="width:150px;">商标注册证号</td>
									<td  colspan="3" align="left" class="l-table-edit-td">
										${mchtBrandAptitudeCustom.certificateNo}
									</td>
								</tr>
								<tr>
									<td  colspan="1" class="title">本商标注册证相关文件</td>
									<td  colspan="3" align="left" class="l-table-edit-td" id="aptitudePic_viewer${index.index}${idx.index}">
										<t:imageList name="mchtBrandAptitudePicstures${index.index}${idx.index}" list="${mchtBrandAptitudeCustom.mchtBrandAptitudePics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
										<div style="float: left;height: 105px;margin: 10px;">
							    			<input style="position:absolute; opacity:0;width: 110px;" type="file" id="BrandAptitudePicstures${index.index}${idx.index}" data_value="aptitudePic_viewer${index.index}${idx.index}" name="file" onchange="ajaxFileUploadImg(this,1);" /> 
											<input type="button" style="width: 70px;" value="上传图片" /> 
							    		</div>
									</td>
								</tr>
								<tr>
									<td  colspan="1" class="title">商家注册证有效期</td>
									<td  colspan="3" align="left" class="l-table-edit-td">
										<fmt:formatDate value="${mchtBrandAptitudeCustom.aptitudeExpDate}" pattern="yyyy-MM-dd"/>
										&nbsp;&nbsp;<a href="javascript:;" onclick="toEditDate(${mchtBrandAptitudeCustom.id},0);">【修改有效期】</a>
									</td>
								</tr>
							</tbody>
						</table>
					</c:forEach>	
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">销售授权书</td>
					<td  colspan="3" align="left" class="l-table-edit-td" id="platformAuthPic_viewer${index.index}">
						<t:imageList name="mchtPlatformAuthPictures${index.index}" list="${mchtProductBrandCustom.mchtPlatformAuthPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
						<div style="float: left;height: 105px;margin: 10px;">
							<input style="position:absolute; opacity:0;width: 110px;" type="file" id="PlatformAuthPictures${index.index}" data_value="platformAuthPic_viewer${index.index}" name="file" onchange="ajaxFileUploadImg(this,1);" /> 
							<input type="button" style="width: 70px;" value="上传图片" /> 
						</div>
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">授权期限</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<fmt:formatDate value="${mchtProductBrandCustom.platformAuthExpDate}" pattern="yyyy-MM-dd"/>
						&nbsp;&nbsp;<a href="javascript:;" onclick="toEditDate(${mchtProductBrandCustom.id},1);">【修改有效期】</a>
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">进货发票</td>
					<td  colspan="3" align="left" class="l-table-edit-td" id="mchtBrandInvoicePic_viewer${index.index}">
						<t:imageList name="mchtBrandInvoicePictures${index.index}" list="${mchtProductBrandCustom.mchtBrandInvoicePics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
						<div style="float: left;height: 105px;margin: 10px;">
							<input style="position:absolute; opacity:0;width: 110px;" type="file" id="BrandInvoicePictures${index.index}" data_value="mchtBrandInvoicePic_viewer${index.index}" name="file" onchange="ajaxFileUploadImg(this,1);" /> 
							<input type="button" style="width: 70px;" value="上传图片" /> 
						</div>
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">质检报告/检疫报告</td>
					<td  colspan="3" align="left" class="l-table-edit-td" id="mchtBrandInspectionPic_viewer${index.index}">
						<t:imageList name="mchtBrandInspectionPictures${index.index}" list="${mchtProductBrandCustom.mchtBrandInspectionPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt"/>
						
						<div style="float: left;height: 105px;margin: 10px;">
							<input style="position:absolute; opacity:0;width: 110px;" type="file" id="BrandInspectionPictures${index.index}" data_value="mchtBrandInspectionPic_viewer${index.index}" name="file" onchange="ajaxFileUploadImg(this,1);" /> 
							<input type="button" style="width: 70px;" value="上传图片" /> 
						</div>
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">质检报告/检疫报告有效期</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<fmt:formatDate value="${mchtProductBrandCustom.inspectionExpDate}" pattern="yyyy-MM-dd"/>
						&nbsp;&nbsp;<a href="javascript:;" onclick="toEditDate(${mchtProductBrandCustom.id},2);">【修改有效期】</a>
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">其他资质</td>
					<td  colspan="3" align="left" class="l-table-edit-td" id="mchtBrandOtherPics_viewer${index.index}">
						<t:imageList name="mchtBrandOtherPictures${index.index}" list="${mchtProductBrandCustom.mchtBrandOtherPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
						<div style="float: left;height: 105px;margin: 10px;">
							<input style="position:absolute; opacity:0;width: 110px;" type="file" id="BrandOtherPictures${index.index}" data_value="mchtBrandOtherPics_viewer${index.index}" name="file" onchange="ajaxFileUploadImg(this,1);" /> 
							<input type="button" style="width: 70px;" value="上传图片" /> 
						</div>
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">其他资质有效期</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<fmt:formatDate value="${mchtProductBrandCustom.otherExpDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">品牌经营的类目</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<table class="gridtable">
							<tbody>
								<tr>
									<td class="title">一级类目</td>
									<td class="title">二级类目</td>
									<td class="title">三级类目</td>
								</tr>
								<c:forEach var="mchtBrandProductTypeCustom" items="${mchtProductBrandCustom.mchtBrandProductTypeCustoms}">
									<tr>
										<td>${mchtBrandProductTypeCustom.firstProductTypeName}</td>
										<td>${mchtBrandProductTypeCustom.secondProductTypeName}</td>
										<td>${mchtBrandProductTypeCustom.thirdProductTypeNames}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
				<%--<c:if test="${mchtInfo.mchtType == 2}">--%>
				<tr>
					<td  colspan="1" class="title">技术服务费率</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<input name="popCommissionRate" value="${mchtProductBrandCustom.popCommissionRate}" readonly="readonly">
					</td>
				</tr>
				<%--</c:if>
				<c:if test="${mchtInfo.mchtType == 1}">
				<tr>
					<td  colspan="1" class="title">SPOP定价方式 *</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<select name="priceModel" disabled="disabled">
							<option value="">选择定价方式</option>
							<option value="1" <c:if test="${mchtProductBrandCustom.priceModel == '1'}">selected="selected"</c:if>>吊牌价*比例</option>
							<option value="2" <c:if test="${mchtProductBrandCustom.priceModel == '2'}">selected="selected"</c:if>>代理价</option>
							<option value="3" <c:if test="${mchtProductBrandCustom.priceModel == '3'}">selected="selected"</c:if>>售价*比例</option>
						</select>
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">定价说明</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<textarea rows="" cols=""></textarea>
					</td>
				</tr>
				</c:if>--%>
				<tr>
					<td  colspan="1" class="title">法务确认结果 *</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<input type="radio" name="auditStatus${index.index}" <c:if test="${mchtProductBrandCustom.auditStatus == 3}">checked="checked"</c:if> value="3">通过
						<input type="radio" name="auditStatus${index.index}" <c:if test="${mchtProductBrandCustom.auditStatus == 4}">checked="checked"</c:if> value="4">驳回
						<input type="radio" name="auditStatus${index.index}" <c:if test="${mchtProductBrandCustom.auditStatus == 5}">checked="checked"</c:if> value="5">不签约
						<input type="radio" name="auditStatus${index.index}" <c:if test="${mchtProductBrandCustom.auditStatus == 6}">checked="checked"</c:if> value="6">黑名单
					</td>
				</tr>
				<tr>
					<td colspan="1" class="title">备注/驳回原因</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<textarea rows="7" cols="70" >${mchtProductBrandCustom.auditRemarks }</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="1" class="title">内部备注</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<textarea rows="7" cols="70" >${mchtProductBrandCustom.auditInnerRemarks}</textarea>
					</td>
				</tr>
			</tbody>
		</table>
		</c:forEach>
	<br>
	<br>
	<div><span class="table-title" >商家对接人信息审核</span></div>
	<c:forEach var="mchtContact" items="${mchtContactList}" varStatus="index">
		<table class="gridtable" name="mchtContactTable" id="mchtContactTable" style="width:800px;">
			<tbody>
				<tr>
					<td  colspan="1" class="title">对接人类型</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${mchtContact.contactType eq 1}">
						店铺总负责人
					</c:if>
					<c:if test="${mchtContact.contactType eq 2}">
						营运负责人
					</c:if>
					<c:if test="${mchtContact.contactType eq 3}">
						订单对接人
					</c:if>
					<c:if test="${mchtContact.contactType eq 4}">
						售后对接人
					</c:if>	
					<c:if test="${mchtContact.contactType eq 6}">
						客服对接人
					</c:if>
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">姓名</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						${mchtContact.contactName}
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">手机号码</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						${mchtContact.mobile}
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">座机号</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						${mchtContact.tel}
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">QQ</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						${mchtContact.qq}
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">邮箱</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						${mchtContact.email}
					</td>
				</tr>
				<c:if test="${mchtContact.contactType eq 1}">
				<tr>
					<td  colspan="1" class="title">身份证正反面</td>
					<td  colspan="3" align="left" class="l-table-edit-td" id="idcardImgAB${index.index}">
					<c:forEach var="item" items="${totalIdCardImgList}" varStatus="position">
					 <c:if test="${position.index == index.index}">
						<t:imageList name="mchtContactPictures1${index.index}" list="${item}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
					 </c:if>
					</c:forEach>
						<div style="float: left;height: 105px;margin: 10px;">
							<input style="position:absolute; opacity:0;width: 110px;" type="file" id="ContactPictures1${index.index}" name="file" data_value="idcardImgAB${index.index}" onchange="ajaxFileUploadImg(this,2);" /> 
							<input type="button" style="width: 70px;" value="上传图片" /> 
						</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${mchtContact.contactType eq 1}">
				<tr>
					<td  colspan="1" class="title">店铺负责人手持身份证</td>
					<td  colspan="3" align="left" class="l-table-edit-td" id="idcardImg" >
						<t:imageList name="mchtContactPictures2" list="${contactIdcardImg}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
						<div style="float: left;height: 105px;margin: 10px;">
							<input style="position:absolute; opacity:0;width: 110px;" type="file" id="ContactPictures2" name="file" onchange="ajaxFileUploadImg(this,2);" /> 
							<input type="button" style="width: 70px;" value="上传图片" /> 
						</div>
					</td>
				</tr>
				</c:if>
				<tr>
					<td  colspan="1" class="title">地址</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						${mchtContact.countyName}${mchtContact.provinceName}${mchtContact.cityName}${mchtContact.address}
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">审核结果 </td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<input type="radio"  id="${mchtContact.id}" name="contactAuditStatus${index.index}" <c:if test="${mchtContact.auditStatus == 1}">checked="checked"</c:if> value="1">通过
						<input type="radio"  id="${mchtContact.id}" name="contactAuditStatus${index.index}" <c:if test="${mchtContact.auditStatus == 2}">checked="checked"</c:if> value="2">驳回
					</td>
				</tr>
				<tr>
					<td colspan="1" class="title">驳回原因</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<textarea rows="7" cols="70" id="rejectReasons${index.index}" name="rejectReasons${index.index}">${mchtContact.rejectReasons}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="1" class="title">内部备注</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<textarea rows="7" cols="70" id="innerRemarks${index.index}" name="innerRemarks${index.index}">${mchtContact.innerRemarks}</textarea>
					</td>
				</tr>
			</tbody>
			<br>
		</table>
	</c:forEach>
	<br>
	<br>
	<div><span class="table-title">商家信息资质总审核</span></div>
	<br>
	<table class="gridtable">
		<tr>
			<td  colspan="1" class="title" style="width:200px;">审核结果</td>
			<td  colspan="3" align="left" class="l-table-edit-td">
				<input name="totalAuditStatus" type="radio" value="2" <c:if test="${mchtInfo.totalAuditStatus =='2'}">checked="checked"</c:if>>通过
				<input name="totalAuditStatus" type="radio" value="3" <c:if test="${mchtInfo.totalAuditStatus =='3'}">checked="checked"</c:if>>驳回
				<input name="totalAuditStatus" type="radio" value="5" <c:if test="${mchtInfo.totalAuditStatus =='5'}">checked="checked"</c:if>>不签约
				<input name="totalAuditStatus" type="radio" value="6" <c:if test="${mchtInfo.totalAuditStatus =='6'}">checked="checked"</c:if>>黑名单
			</td>
		</tr>
		<tr>
			<td  colspan="1" class="title">店铺保证金</td>
			<td  colspan="3" align="left" class="l-table-edit-td">
				<input type="text" id="contractDeposit" name="contractDeposit" value="${mchtInfo.contractDeposit}">
			</td>
		</tr>
		<tr>
			<td  colspan="1" class="title">保证金内容</td>
			<td  colspan="3" align="left" class="l-table-edit-td">
				<textarea rows="7" cols="70" id="depositContent" name="depositContent"></textarea>
			</td>
		</tr>
		<tr>
			<td  colspan="1" class="title">合同开始日期</td>
			<td  colspan="3" align="left" class="l-table-edit-td">
				<input id="agreementBeginDate" name="agreementBeginDate" type="text" style="width:400px;"/>
				<script type="text/javascript">
					$(function() {
						$("#agreementBeginDate").ligerDateEditor({
								showTime : false,
								labelWidth : 150,
								width:150,
								labelAlign : 'left'
						});
					});
				</script>
			</td>
		</tr>
		<tr>
			<td  colspan="1" class="title">合同结束日期</td>
			<td  colspan="3" align="left" class="l-table-edit-td">
				<input id="agreementEndDate" name="agreementEndDate" type="text" style="width:400px;"/>
				<script type="text/javascript">
					$(function() {
						$("#agreementEndDate").ligerDateEditor({
								showTime : false,
								labelWidth : 150,
								width:150,
								labelAlign : 'left'
						});
					});
				</script>
			</td>
		</tr>
		<tr>
			<td  colspan="1" class="title">备注/驳回内容</td>
			<td  colspan="3" align="left" class="l-table-edit-td">
				<textarea rows="7" cols="70" id="totalAuditRemarks" name="totalAuditRemarks">${mchtInfo.totalAuditRemarks }</textarea>
			</td>
		</tr>
		<tr>
			<td  colspan="1" class="title">内部备注</td>
			<td  colspan="3" align="left" class="l-table-edit-td">
				<textarea rows="7" cols="70" name="companyInfAuditInnerRemarks" id="companyInfAuditInnerRemarks">${mchtInfo.companyInfAuditInnerRemarks }</textarea>
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
