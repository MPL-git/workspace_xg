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
function picArchive(picId,archiveStatus,picType){
	var mchtContractId = $("#mchtContractId").val();
	$.ajax({ 
		url : "${pageContext.request.contextPath}/mchtContract/companyInfoPicArchiveChg.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {"mchtContractId":mchtContractId,"id":picId,"archiveStatus":archiveStatus,"picType":picType},
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
	<input type="hidden" id="mchtContractId" name="mchtContractId" value="${mchtContractId}">
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
							<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtInfoChg.corporationIdcardImg1}">
								<div>
			                       	<span><input type="radio" name="idcardImg1ArchiveStatus" <c:if test="${empty mchtInfoChg.idcardImg1ArchiveStatus || mchtInfoChg.idcardImg1ArchiveStatus == 0}">checked="checked"</c:if> value="0" onclick="picArchive(${mchtInfoChg.id},0,1);">未齐</span>
			                   		<span><input type="radio" name="idcardImg1ArchiveStatus" <c:if test="${mchtInfoChg.idcardImg1ArchiveStatus == 1}">checked="checked"</c:if> value="1" onclick="picArchive(${mchtInfoChg.id},1,1);">已归</span>
			                    </div>
						</li>
						<li>
							<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtInfoChg.corporationIdcardImg2}">
								<div>
			                       	<span><input type="radio" name="idcardImg2ArchiveStatus" <c:if test="${empty mchtInfoChg.idcardImg2ArchiveStatus || mchtInfoChg.idcardImg2ArchiveStatus == 0}">checked="checked"</c:if> value="0" onclick="picArchive(${mchtInfoChg.id},0,2);">未齐</span>
			                   		<span><input type="radio" name="idcardImg2ArchiveStatus" <c:if test="${mchtInfoChg.idcardImg2ArchiveStatus == 1}">checked="checked"</c:if> value="1" onclick="picArchive(${mchtInfoChg.id},1,2);">已归</span>
			                    </div>                
						</li>
					</ul>
				</td>
			</tr>
			<tr>
				<td  colspan="1" >营业执照副本</td>
				<td  colspan="1" >
						<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtBlPicChg.pic}">
							<div>
		                       	<span><input type="radio" name="blPicArchiveStatus" <c:if test="${empty mchtInfoChg.blPicArchiveStatus || mchtInfoChg.blPicArchiveStatus == 0}">checked="checked"</c:if> value="0" onclick="picArchive(${mchtInfoChg.id},0,3);">未齐</span>
		                   		<span><input type="radio" name="blPicArchiveStatus" <c:if test="${mchtInfoChg.blPicArchiveStatus == 1}">checked="checked"</c:if> value="1" onclick="picArchive(${mchtInfoChg.id},1,3);">已归</span>
		                    </div>
				</td>
			</tr>
			<c:if test="${not empty mchtInfoChg.occPic}">
			<tr>
				<td  colspan="1" >组织机构代码证</td>
				<td  colspan="1" >
					<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtInfoChg.occPic}">
						<div>
	                       	<span><input type="radio" name="occPicArchiveStatus" <c:if test="${empty mchtInfoChg.occPicArchiveStatus || mchtInfoChg.occPicArchiveStatus == 0}">checked="checked"</c:if> value="0" onclick="picArchive(${mchtInfoChg.id},0,4);">未齐</span>
	                   		<span><input type="radio" name="occPicArchiveStatus" <c:if test="${mchtInfoChg.occPicArchiveStatus == 1}">checked="checked"</c:if> value="1" onclick="picArchive(${mchtInfoChg.id},1,4);">已归</span>
	                    </div>
				</td>
			</tr>
			</c:if>
			<c:if test="${not empty mchtInfoChg.trcPic}">
			<tr>
				<td  colspan="1" >税务登记证</td>
				<td  colspan="1" >
					<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtInfoChg.trcPic}">
						<div>
	                       	<span><input type="radio" name="trcPicArchiveStatus" <c:if test="${empty mchtInfoChg.trcPicArchiveStatus || mchtInfoChg.trcPicArchiveStatus == 0}">checked="checked"</c:if> value="0" onclick="picArchive(${mchtInfoChg.id},0,12);">未齐</span>
	                   		<span><input type="radio" name="trcPicArchiveStatus" <c:if test="${mchtInfoChg.trcPicArchiveStatus == 1}">checked="checked"</c:if> value="1" onclick="picArchive(${mchtInfoChg.id},1,12);">已归</span>
	                    </div>
				</td>
			</tr>
			</c:if>
			<c:if test="${not empty mchtInfoChg.atqPic}">
			<tr>
				<td  colspan="1" >一般纳税人资格证</td>
				<td  colspan="1" >
					<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtInfoChg.atqPic}">
						<div>
	                       	<span><input type="radio" name="atqPicArchiveStatus" <c:if test="${empty mchtInfoChg.atqPicArchiveStatus || mchtInfoChg.atqPicArchiveStatus == 0}">checked="checked"</c:if> value="0" onclick="picArchive(${mchtInfoChg.id},0,5);">未齐</span>
	                   		<span><input type="radio" name="atqPicArchiveStatus" <c:if test="${mchtInfoChg.atqPicArchiveStatus == 1}">checked="checked"</c:if> value="1" onclick="picArchive(${mchtInfoChg.id},1,5);">已归</span>
	                    </div>
				</td>
			</tr>
			</c:if>
			<tr>
				<td  colspan="1" >银行开户许可证</td>
				<td  colspan="1" >
					<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtInfoChg.boaalPic}">
						<div>
	                       	<span><input type="radio" name="boaalPicArchiveStatus" <c:if test="${empty mchtInfoChg.boaalPicArchiveStatus || mchtInfoChg.boaalPicArchiveStatus == 0}">checked="checked"</c:if> value="0" onclick="picArchive(${mchtInfoChg.id},0,6);">未齐</span>
	                   		<span><input type="radio" name="boaalPicArchiveStatus" <c:if test="${mchtInfoChg.boaalPicArchiveStatus == 1}">checked="checked"</c:if> value="1" onclick="picArchive(${mchtInfoChg.id},1,6);">已归</span>
	                    </div>
				</td>
			</tr>
			
		</table>
	</form>
</body>
</html>
