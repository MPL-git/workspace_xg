<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
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
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	

<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
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



.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}
 .l-text-readonly{
 border-bottom: none !important;
 }
 td img{
 width: 60px;
 height: 40px;
 }
 td ul li{
	display: inline-block;
 }
</style>
<script type="text/javascript">
Date.prototype.format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
} ;


var logoPic_viewer; 
var platformAuthPic_viewer; 
var mchtBrandInvoicePic_viewer; 
var mchtBrandInspectionPic_viewer; 
var mchtBrandOtherPics_viewer; 


$(function ()  {
	
	logoPic_viewer = new Viewer(document.getElementById('logoPic_viewer'), {});
	platformAuthPic_viewer = new Viewer(document.getElementById('platformAuthPic_viewer'), {});
	mchtBrandInvoicePic_viewer = new Viewer(document.getElementById('mchtBrandInvoicePic_viewer'), {});
	mchtBrandInspectionPic_viewer = new Viewer(document.getElementById('mchtBrandInspectionPic_viewer'), {});
	mchtBrandOtherPics_viewer = new Viewer(document.getElementById('mchtBrandOtherPics_viewer'), {});
	
	if('${view }' == "1") {
		$.metadata.setType("attr", "validate");
		
	    //当运营状态=正常时，但审核状态！=通过时。提时：对不起，审核状态未通过，运营状态不能为正常。
	    $.validator.addMethod("statusCheck", function(value, element) {  
	    	if(!$("input[type=radio][name='auditStatus'][value=3]").attr("checked")&&$("#status").val()=="1"){
	 			return false;
	 		}else{
	 			return true;
	 		}
	    }, "审核状态未通过，运营状态不能为正常");
	    
	    //驳回原因必填
	    $.validator.addMethod("auditRemarksRequired", function(value, element) {  
	    	if($("input[type=radio][name='auditStatus'][value=4]").attr("checked")&&$.trim($("#auditRemarks").val())==""){
	 			return false;
	 		}else{
	 			return true;
	 		}
	    }, "请注明驳回原因");
	    
	    //非未完善状态必填
	    $.validator.addMethod("perfectRequired", function(value, element) { 
	    	
	    	if(($("input[type=radio][name='auditStatus'][value=1]").attr("checked")||$("input[type=radio][name='auditStatus'][value=2]").attr("checked")||$("input[type=radio][name='auditStatus'][value=3]").attr("checked")||$("input[type=radio][name='auditStatus'][value=4]").attr("checked"))&&$(element).attr("type")=="radio"){
	  		   var radioName=$(element).attr("name");
			   var hasSelect=false;
			   $($("input[type=radio][name="+radioName+"]")).each(function(){
				    if($(this).attr("checked")){
				    	hasSelect=true;
				    }
				  });
			   return hasSelect;
	    	}
	    	
	    	if(($("input[type=radio][name='auditStatus'][value=1]").attr("checked")||$("input[type=radio][name='auditStatus'][value=2]").attr("checked")||$("input[type=radio][name='auditStatus'][value=3]").attr("checked")||$("input[type=radio][name='auditStatus'][value=4]").attr("checked"))&&value==""){
	    		return false;
	    	}else{
	    		return true;
	    	}
	    	
	    }, "该字段不能为空");
		
	    var v = $("#form1").validate({
	    	
	        errorPlacement: function (lable, element)
	        {  
	        	var elementType=$(element).attr("type");
	
	        	if($(element).hasClass("l-text-field")){
	        		$(element).parent().ligerTip({
						content : lable.html(),width: 100
					});
	        	}
	        	else if('radio'==elementType){
	        		var radioName=$(element).attr("name");
	        		$("input[type=radio][name="+radioName+"]:last").ligerTip({
						content : lable.html(),width: 100
					});
	        	}else if('hidden'==elementType){
	        		$(element).closest('td').find("div").ligerTip({
						content : lable.html(),width: 100
					});  
	        	}
	        	
	        	else{
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
	        	mchtProductBrandSaveSubmit();
	        }
	    });   
	}
	
	            
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

var productBrandSelect;
$(function(){

	var isEidt= ${!empty mchtProductBrand.id};
	 productBrandSelect = $("#productBrandName").ligerComboBox({
	     	 width: 150,
	         slide: false,
	         selectBoxWidth: 450,
	         selectBoxHeight: 300,
	         valueField: 'id',
	         textField: 'name',
	         valueFieldID:'productBrandId',
	         readonly:isEidt,
	         grid: getGridOptions(),
	         condition:{ fields: [{ name: 'name', label: '品牌名',width:90,type:'text'}
	                             ]}
	     });
	 
	 productBrandSelect.setValue('${productBrand.id}');
	 productBrandSelect.setText("${productBrand.name}");
	
		
});


function aptitudePicUpload() {
	$.ajaxFileUpload({
		url : contextPath + '/service/common/ajax_upload.shtml',
		secureuri : false,
		fileElementId : "mchtBrandAptitudePic",
		dataType : 'json',
		success : function(result, status) {
			if (result.RESULT_CODE == 0) {
				uploadImageList.addImage(contextPath + "/file_servelt",
						result.FILE_PATH,"aptitudePics");
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error : function(result, status, e) {
			alert("服务异常");
		}
	});

}

function platformAuthPicUpload() {
	$.ajaxFileUpload({
		url : contextPath + '/service/common/ajax_upload.shtml',
		secureuri : false,
		fileElementId : "mchtPlatformAuthPic",
		dataType : 'json',
		success : function(result, status) {
			if (result.RESULT_CODE == 0) {
				uploadImageList.addImage(contextPath + "/file_servelt",
						result.FILE_PATH,"platformAuthPics");
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error : function(result, status, e) {
			alert("服务异常");
		}
	});

}
</script>

<html>
<body>
	<form method="post" id="form1" name="form1">
		<table class="gridtable">
			<tr>
				<td  colspan="1" class="title">品牌名称 </td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<span>${mchtBrandChg.productBrandName}</span>
				</td>	
			</tr>
			<tr>
				<td  colspan="1" class="title">资质类型</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<span>
					<c:if test="${mchtBrandChg.aptitudeType == 1}">自有商标</c:if>
					<c:if test="${mchtBrandChg.aptitudeType == 2}">品牌商授权</c:if>
				</span>
			</td>
			</tr>
			
			<tr>
			
				<td  colspan="1" class="title">LOGO图片</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<ul  class="docs-pictures clearfix td-pictures" id="logoPic_viewer" >
					<li>
						<img  src="${pageContext.request.contextPath}/file_servelt${mchtBrandChg.logo}">
					</li>
				</ul>
			</td>
			</tr>
			
			<tr>
					<td  colspan="1" class="title">商标注册证或受理通知书</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
					<c:forEach var="mchtBrandAptitudeChgCustom" items="${mchtBrandAptitudeChgCustoms}">
						<table class="gridtable" style="margin-top: 10px;" name="mchtBrandAptitudeTable">
							<tbody>
								<tr>
									<td  colspan="1" class="title" style="width:150px;">商标注册证号</td>
									<td  colspan="3" align="left" class="l-table-edit-td">
										${mchtBrandAptitudeChgCustom.certificateNo}
									</td>
								</tr>
								<tr>
									<td  colspan="1" class="title">本商标注册证相关文件</td>
									<td  colspan="3" align="left" class="l-table-edit-td">
										<ul  class="docs-pictures clearfix td-pictures" id="logoPic_viewer" >
										<c:forEach var="mchtBrandAptitudePicChg" items="${mchtBrandAptitudeChgCustom.mchtBrandAptitudePicChgs}">
											<li>
												<img  src="${pageContext.request.contextPath}/file_servelt${mchtBrandAptitudePicChg.pic}">
											</li>
										</c:forEach>
										</ul>
									</td>
								</tr>
								<tr>
									<td  colspan="1" class="title">商家注册证有效期</td>
									<td  colspan="3" align="left" class="l-table-edit-td">
										<fmt:formatDate value="${mchtBrandAptitudeChgCustom.aptitudeExpDate}" pattern="yyyy-MM-dd"/>
									</td>
								</tr>
							</tbody>
						</table>
					</c:forEach>	
					</td>
				</tr>				
			
			<tr>
				<td  colspan="1" class="title">销售授权书</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<ul  class="docs-pictures clearfix td-pictures" id="platformAuthPic_viewer" >
						<c:forEach var="mchtPlatformAuthPicChg" items="${mchtPlatformAuthPicChgs}">
							<li>
								<img  src="${pageContext.request.contextPath}/file_servelt${mchtPlatformAuthPicChg.pic}">
							</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">授权有效期</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<fmt:formatDate value="${mchtBrandChg.platformAuthExpDate}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">进货发票</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<ul  class="docs-pictures clearfix td-pictures" id="mchtBrandInvoicePic_viewer" >
						<c:forEach var="mchtBrandInvoicePicChg" items="${mchtBrandInvoicePicChgs}">
							<li>
								<img  src="${pageContext.request.contextPath}/file_servelt${mchtBrandInvoicePicChg.pic}">
							</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">质检报告/检疫报告</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<ul  class="docs-pictures clearfix td-pictures" id="mchtBrandInspectionPic_viewer" >
						<c:forEach var="mchtBrandInspectionPicChg" items="${mchtBrandInspectionPicChgs}">
							<li>
								<img  src="${pageContext.request.contextPath}/file_servelt${mchtBrandInspectionPicChg.pic}">
							</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">报告有效期</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<fmt:formatDate value="${mchtBrandChg.inspectionExpDate}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">其他资质<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<ul  class="docs-pictures clearfix td-pictures" id="mchtBrandOtherPics_viewer" >
						<c:forEach var="mchtBrandOtherPicChg" items="${mchtBrandOtherPicChgs}">
							<li>
								<img  src="${pageContext.request.contextPath}/file_servelt${mchtBrandOtherPicChg.pic}">
							</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">其他资质有效期</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<fmt:formatDate value="${mchtBrandChg.otherExpDate}" pattern="yyyy-MM-dd"/>
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
							<c:forEach var="mchtBrandChgProductTypeCustom" items="${mchtBrandChgProductTypeCustoms}">
								<tr>
									<td>${mchtBrandChgProductTypeCustom.firstProductTypeName}</td>
									<td>${mchtBrandChgProductTypeCustom.secondProductTypeName}</td>
									<td>${mchtBrandChgProductTypeCustom.thirdProductTypeNames}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>			
			
			<tr>
				<c:if test='${mchtInfo.mchtType=="2"}'>
					<td  colspan="1" class="title">技术服务费<span class="required">*</span></td>
				</c:if>
				<c:if test='${mchtInfo.mchtType=="1"}'>
					<td  colspan="1" class="title">定价方式<span class="required">*</span></td>
				</c:if>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test='${mchtInfo.mchtType=="2"}'>
						<input id="popCommissionRate" validate="{required:true,number:true}"
							name="popCommissionRate" type="text" value="${mchtBrandChg.popCommissionRate }"
							style="float:left;" <c:if test="${sessionScope.USER_SESSION.staffID!=1}">readonly="readonly"</c:if> />
					</c:if>
					<c:if test='${mchtInfo.mchtType=="1"}'>
						<select style="width: 160px;" id="priceModel" name="priceModel" validate="{required:true}">
							<option value="">请选择</option>
							<c:forEach var="statusItem" items="${priceModelStatusDesc}">
								<option  <c:if test="${mchtBrandChg.priceModel==statusItem.statusValue}">selected</c:if>
								value="${statusItem.statusValue}">${statusItem.statusDesc}
								</option>
							</c:forEach>
						</select>
						<br>
						<br>
						<textarea rows="3" style="width:90%;" name="priceModelDesc" validate="{required:true}">${mchtBrandChg.priceModelDesc}</textarea>
					</c:if>
				</td>	
			 </tr>
			 
			 <tr>
				<td  colspan="1" class="title">法务审核结果 </td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<input type="radio" name="auditStatus" value="3" <c:if test="${mchtBrandChg.auditStatus == 3}">checked="checked"</c:if>>通过
					<input type="radio" name="auditStatus" value="4" <c:if test="${mchtBrandChg.auditStatus == 4}">checked="checked"</c:if>>驳回
					<input type="radio" name="auditStatus" value="5" <c:if test="${mchtBrandChg.auditStatus == 5}">checked="checked"</c:if>>不签约
					<input type="radio" name="auditStatus" value="6" <c:if test="${mchtBrandChg.auditStatus == 6}">checked="checked"</c:if>>黑名单	
				</td>
			</tr>
			
			<tr>
			<td  colspan="1" class="title">备注/驳回原因</td>
				<td  colspan="7" align="left" class="l-table-edit-td">
					<textarea rows=2 id="auditRemarks" name="auditRemarks" cols="50" class="text" >${mchtBrandChg.auditRemarks}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">内部备注</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<textarea rows="" cols="">${mchtBrandChg.auditInnerRemarks}</textarea>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
