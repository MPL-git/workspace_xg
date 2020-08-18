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
$(function ()  {
	
	
	
	            
});    

function picsArchive(mchtProductBrandId,id,picType){
	var mchtId = $("#mchtId").val();
	$.ajax({
		url : "${pageContext.request.contextPath}/mchtContract/picsArchive.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {"mchtId":mchtId,"mchtProductBrandId":mchtProductBrandId,"id":id,"picType":picType},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				$.ligerDialog.success("操作成功!");
				location.reload();
//				frameElement.dialog.close();
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
			
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

function picArchive(picId,archiveStatus,picType){
	var mchtId = $("#mchtId").val();
	$.ajax({ 
		url : "${pageContext.request.contextPath}/mchtContract/picArchive.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {"mchtId":mchtId,"id":picId,"archiveStatus":archiveStatus,"picType":picType},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				$.ligerDialog.success("操作成功!");
				location.reload();
//				parent.location.reload();
//				frameElement.dialog.close();
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

<html>
<body>
	<form method="post" id="form1" name="form1">
	<input type=hidden type="hidden" name="id" value="${mchtProductBrand.id}">
	<input type="hidden" id="mchtId" name="mchtId" value="${mchtId}">
		<div>公司资质信息</div>
		<table class="gridtable">
			<tr>
				<td  colspan="1" class="title">项目</td>
				<td  colspan="1" class="title">资质文件如是复印件需全部加盖公章。且保证文件内容清晰、章印清楚</td>
			</tr>
			<tr>
				<td  colspan="1" >法人身份证</td>
				<td  colspan="1" >
					<ul  class="docs-pictures clearfix td-pictures" >
						<li>
							<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtInfo.corporationIdcardImg1}">
							<div>
		                       	<span><input type="radio" name="idcardImg1ArchiveStatus" <c:if test="${empty mchtInfo.idcardImg1ArchiveStatus || mchtInfo.idcardImg1ArchiveStatus == 0}">checked="checked"</c:if> value="0" onclick="picArchive(${mchtInfo.id},0,1);">未齐</span>
		                   		<span><input type="radio" name="idcardImg1ArchiveStatus" <c:if test="${mchtInfo.idcardImg1ArchiveStatus == 1}">checked="checked"</c:if> value="1" onclick="picArchive(${mchtInfo.id},1,1);">已归</span>
		                    </div>
						</li>
						<li>
							<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtInfo.corporationIdcardImg2}">
							<div>
		                       	<span><input type="radio" name="idcardImg2ArchiveStatus" <c:if test="${empty mchtInfo.idcardImg2ArchiveStatus || mchtInfo.idcardImg2ArchiveStatus == 0}">checked="checked"</c:if> value="0" onclick="picArchive(${mchtInfo.id},0,2);">未齐</span>
		                   		<span><input type="radio" name="idcardImg2ArchiveStatus" <c:if test="${mchtInfo.idcardImg2ArchiveStatus == 1}">checked="checked"</c:if> value="1" onclick="picArchive(${mchtInfo.id},1,2);">已归</span>
		                    </div>
						</li>
					</ul>
				</td>
			</tr>
			<tr>
				<td  colspan="1" >营业执照副本</td>
				<td  colspan="1" >
					<c:forEach var="mchtBlPic" items="${mchtBlPics}" varStatus="status">
						<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtBlPic.pic}">
						<div>
	                       	<span><input type="radio" name="blPicArchiveStatus${status.index}" <c:if test="${empty mchtBlPic.blPicArchiveStatus || mchtBlPic.blPicArchiveStatus == 0}">checked="checked"</c:if> value="0" onclick="picArchive(${mchtBlPic.id},0,3);">未齐</span>
	                   		<span><input type="radio" name="blPicArchiveStatus${status.index}" <c:if test="${mchtBlPic.blPicArchiveStatus == 1}">checked="checked"</c:if> value="1" onclick="picArchive(${mchtBlPic.id},1,3);">已归</span>
	                    </div>
					</c:forEach>
				</td>
			</tr>
			<c:if test="${not empty mchtInfo.occPic}">
			<tr>
				<td  colspan="1" >组织机构代码证</td>
				<td  colspan="1" >
					<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtInfo.occPic}">
					<div>
                       	<span><input type="radio" name="occPicArchiveStatus" <c:if test="${empty mchtInfo.occPicArchiveStatus || mchtInfo.occPicArchiveStatus == 0}">checked="checked"</c:if> value="0" onclick="picArchive(${mchtInfo.id},0,4);">未齐</span>
                   		<span><input type="radio" name="occPicArchiveStatus" <c:if test="${mchtInfo.occPicArchiveStatus == 1}">checked="checked"</c:if> value="1" onclick="picArchive(${mchtInfo.id},1,4);">已归</span>
                    </div>
				</td>
			</tr>
			</c:if>
			<c:if test="${not empty mchtInfo.trcPic}">
			<tr>
				<td  colspan="1" >税务登记证</td>
				<td  colspan="1" >
					<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtInfo.trcPic}">
					<div>
                       	<span><input type="radio" name="trcPicArchiveStatus" <c:if test="${empty mchtInfo.trcPicArchiveStatus || mchtInfo.trcPicArchiveStatus == 0}">checked="checked"</c:if> value="0" onclick="picArchive(${mchtInfo.id},0,12);">未齐</span>
                   		<span><input type="radio" name="trcPicArchiveStatus" <c:if test="${mchtInfo.trcPicArchiveStatus == 1}">checked="checked"</c:if> value="1" onclick="picArchive(${mchtInfo.id},1,12);">已归</span>
                    </div>
				</td>
			</tr>
			</c:if>
			<c:if test="${not empty mchtInfo.atqPic}">
			<tr>
				<td  colspan="1" >一般纳税人资格证</td>
				<td  colspan="1" >
					<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtInfo.atqPic}">
					<div>
                       	<span><input type="radio" name="atqPicArchiveStatus" <c:if test="${empty mchtInfo.atqPicArchiveStatus || mchtInfo.atqPicArchiveStatus == 0}">checked="checked"</c:if> value="0" onclick="picArchive(${mchtInfo.id},0,5);">未齐</span>
                   		<span><input type="radio" name="atqPicArchiveStatus" <c:if test="${mchtInfo.atqPicArchiveStatus == 1}">checked="checked"</c:if> value="1" onclick="picArchive(${mchtInfo.id},1,5);">已归</span>
                    </div>
				</td>
			</tr>
			</c:if>
			<tr>
				<td  colspan="1" >银行开户许可证</td>
				<td  colspan="1" >
					<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtInfo.boaalPic}">
					<div>
                       	<span><input type="radio" name="boaalPicArchiveStatus" <c:if test="${empty mchtInfo.boaalPicArchiveStatus || mchtInfo.boaalPicArchiveStatus == 0}">checked="checked"</c:if> value="0" onclick="picArchive(${mchtInfo.id},0,6);">未齐</span>
                   		<span><input type="radio" name="boaalPicArchiveStatus" <c:if test="${mchtInfo.boaalPicArchiveStatus == 1}">checked="checked"</c:if> value="1" onclick="picArchive(${mchtInfo.id},1,6);">已归</span>
                    </div>
				</td>
			</tr>
			
		</table>
		<br>
		<div>品牌资质归档</div>
		<table class="gridtable">
			<tr>
				<td  colspan="1" class="title">项目</td>
				<td  colspan="1" class="title">资质文件如是复印件需全部加盖公章。且保证文件内容清晰、章印清楚</td>
			</tr>
			<c:forEach var="mchtProductBrand" items="${mchtProductBrandCustoms}" varStatus="idx">
			<tr>
				<td  colspan="1" >品牌名称 </td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<span>${mchtProductBrand.productBrandName}</span>
				</td>	
			</tr>
			
			<tr>
				<td  colspan="1" >商标注册证或受理通知书</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<c:forEach var="mchtBrandAptitudeCustom" items="${mchtProductBrand.mchtBrandAptitudeCustoms}" varStatus="index">
					<table class="gridtable" style="margin-top: 10px;" name="mchtBrandAptitudeTable" mchtBrandAptitudeId="${mchtBrandAptitudeCustom.id}">
						<tbody>
							<tr>
								<td  colspan="1" style="width:150px;">商标注册证号</td>
								<td  colspan="3" align="left" class="l-table-edit-td">
									${mchtBrandAptitudeCustom.certificateNo}
								</td>
							</tr>
							<tr>
								<td  colspan="1">本商标注册证相关文件</td>
								<td  colspan="3" align="left" class="l-table-edit-td">
									<c:if test="${not empty mchtBrandAptitudeCustom.mchtBrandAptitudePicList}">
										<span><a href="javascript:;" onclick="picsArchive(${mchtProductBrand.id},${mchtBrandAptitudeCustom.id},7);">全部已归档</a></span>
									</c:if>
									<c:forEach var="mchtBrandAptitudePic" items="${mchtBrandAptitudeCustom.mchtBrandAptitudePicList}" varStatus="status">
										<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtBrandAptitudePic.pic}">
										<div>
					                       	<span><input type="radio" name="mchtBrandAptitudePicArchiveStatus${idx.index}${index.index}${status.index}" <c:if test="${empty mchtBrandAptitudePic.archiveStatus || mchtBrandAptitudePic.archiveStatus == 0}">checked="checked"</c:if> value="0" onclick="picArchive(${mchtBrandAptitudePic.id},0,7);">未齐</span>
					                   		<span><input type="radio" name="mchtBrandAptitudePicArchiveStatus${idx.index}${index.index}${status.index}" <c:if test="${mchtBrandAptitudePic.archiveStatus == 1}">checked="checked"</c:if> value="1" onclick="picArchive(${mchtBrandAptitudePic.id},1,7);">已归</span>
		                    			</div>
									</c:forEach>
								</td>
							</tr>
						</tbody>
					</table>
				</c:forEach>	
				</td>
			</tr>				
			
			<tr>
				<td  colspan="1" >销售授权书</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${not empty mchtProductBrand.mchtPlatformAuthPicList}">
						<span><a href="javascript:;" onclick="picsArchive(${mchtProductBrand.id},${mchtProductBrand.id},8);">全部已归档</a></span>
					</c:if>
					<c:forEach var="mchtPlatformAuthPic" items="${mchtProductBrand.mchtPlatformAuthPicList}" varStatus="status">
						<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtPlatformAuthPic.pic}">
						<div>
					        <span><input type="radio" name="mchtPlatformAuthPicArchiveStatus${idx.index}${status.index}" <c:if test="${empty mchtPlatformAuthPic.archiveStatus || mchtPlatformAuthPic.archiveStatus == 0}">checked="checked"</c:if> value="0" onclick="picArchive(${mchtPlatformAuthPic.id},0,8);">未齐</span>
					        <span><input type="radio" name="mchtPlatformAuthPicArchiveStatus${idx.index}${status.index}" <c:if test="${mchtPlatformAuthPic.archiveStatus == 1}">checked="checked"</c:if> value="1" onclick="picArchive(${mchtPlatformAuthPic.id},1,8);">已归</span>
		                </div>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td  colspan="1" >进货发票</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${not empty mchtProductBrand.mchtBrandInvoicePicList}">
						<span><a href="javascript:;" onclick="picsArchive(${mchtProductBrand.id},${mchtProductBrand.id},9);">全部已归档</a></span>
					</c:if>
					<c:forEach var="mchtBrandInvoicePic" items="${mchtProductBrand.mchtBrandInvoicePicList}" varStatus="status">
						<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtBrandInvoicePic.pic}">
						<div>
					        <span><input type="radio" name="mchtBrandInvoicePicArchiveStatus${idx.index}${status.index}" <c:if test="${empty mchtBrandInvoicePic.archiveStatus || mchtBrandInvoicePic.archiveStatus == 0}">checked="checked"</c:if> value="0" onclick="picArchive(${mchtBrandInvoicePic.id},0,9);">未齐</span>
					        <span><input type="radio" name="mchtBrandInvoicePicArchiveStatus${idx.index}${status.index}" <c:if test="${mchtBrandInvoicePic.archiveStatus == 1}">checked="checked"</c:if> value="1" onclick="picArchive(${mchtBrandInvoicePic.id},1,9);">已归</span>
		                </div>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td  colspan="1" >质检报告/检疫报告</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${not empty mchtProductBrand.mchtBrandInspectionPicList}">
						<span><a href="javascript:;" onclick="picsArchive(${mchtProductBrand.id},${mchtProductBrand.id},10);">全部已归档</a></span>
					</c:if>
					<c:forEach var="mchtBrandInspectionPic" items="${mchtProductBrand.mchtBrandInspectionPicList}" varStatus="status">
						<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtBrandInspectionPic.pic}">
						<div>
					        <span><input type="radio" name="mchtBrandInspectionPicArchiveStatus${idx.index}${status.index}" <c:if test="${empty mchtBrandInspectionPic.archiveStatus || mchtBrandInspectionPic.archiveStatus == 0}">checked="checked"</c:if> value="0" onclick="picArchive(${mchtBrandInspectionPic.id},0,10);">未齐</span>
					        <span><input type="radio" name="mchtBrandInspectionPicArchiveStatus${idx.index}${status.index}" <c:if test="${mchtBrandInspectionPic.archiveStatus == 1}">checked="checked"</c:if> value="1" onclick="picArchive(${mchtBrandInspectionPic.id},1,10);">已归</span>
		                </div>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td  colspan="1" >其他资质</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${not empty mchtProductBrand.mchtBrandOtherPicList}">
						<span><a href="javascript:;" onclick="picsArchive(${mchtProductBrand.id},${mchtProductBrand.id},11);">全部已归档</a></span>
					</c:if>
					<c:forEach var="mchtBrandOtherPic" items="${mchtProductBrand.mchtBrandOtherPicList}" varStatus="status">
						<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtBrandOtherPic.pic}">
						<div>
					        <span><input type="radio" name="mchtBrandOtherPicArchiveStatus${idx.index}${status.index}" <c:if test="${empty mchtBrandOtherPic.archiveStatus || mchtBrandOtherPic.archiveStatus == 0}">checked="checked"</c:if> value="0" onclick="picArchive(${mchtBrandOtherPic.id},0,11);">未齐</span>
					        <span><input type="radio" name="mchtBrandOtherPicArchiveStatus${idx.index}${status.index}" <c:if test="${mchtBrandOtherPic.archiveStatus == 1}">checked="checked"</c:if> value="1" onclick="picArchive(${mchtBrandOtherPic.id},1,11);">已归</span>
		                </div>
					</c:forEach>
				</td>
			</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>
