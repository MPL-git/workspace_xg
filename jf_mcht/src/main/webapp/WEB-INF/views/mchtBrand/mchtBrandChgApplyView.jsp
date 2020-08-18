<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
      <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
      <link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />
          <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>	

<title>完善品牌</title>
<style type="text/css">
td img{
display: inline-block;
width: 60px;
height: 40px;
}
</style>

</head>

<body>

  <div class="modal-dialog" role="document">
    <div class="modal-content" style="width: 910px;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">申请更新品牌资质查看</span>
      </div>
			<div class="modal-body">
	      <div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<c:if test="${mchtBrandChg.auditStatus=='4' }">
					<tr>
						<td class="editfield-label-td">驳回原因</td>
						<td colspan="2" class="text-left">
							<span>${mchtBrandChg.auditRemarks }</span>
						</td>
					</tr>
					</c:if>
					<tr>
						<td class="editfield-label-td">申请品牌名称</td>
						<td colspan="2" class="text-left">
						<span>${mchtProductBrand.productBrandName}</span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">资质类型</td>
						<td colspan="2" class="text-left">
						<div hight="17px">
						<input type="radio" disabled name="aptitudeType" value="1" <c:if test="${mchtProductBrand.aptitudeType == '1'}">checked</c:if>>自有商标
						<input type="radio" disabled name="aptitudeType" value="2" <c:if test="${mchtProductBrand.aptitudeType == '2'}">checked</c:if>>品牌商授权
						</div>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">品牌库品牌</td>
						<td colspan="2" class="text-left">
						<div>
						   ${mchtProductBrand.brandName}
						</div>
						</td>
					</tr>
					
					<%-- <tr>
						<td class="editfield-label-td">相应品牌ID</td>
						<td colspan="2" class="text-left">
						<span>${mchtBrandChg.productBrandId }</span>
						</td>
					</tr> --%>
					
				    <tr>
						<td class="editfield-label-td">logo图片</td>
						<td colspan="2" class="text-left">
						<img style="width: 100px;height: 50px;" src="${ctx}/file_servelt${mchtBrandChg.logo}">
						</td>
					</tr>
					
					
					
					<tr>
						<td class="editfield-label-td">商标注册证或受理通知书 <span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<div style="display: inline-block;color: #999999;">该品牌所有商标注册证/商标受理通知书的复印件加盖公章扫描后上传</div>
							<div style="display: inline-block;color: #999999;">备注：商标如有进行变更、转让的 需要提交相应的证明</div>
							<div style="display: inline-block;color: #999999;">上传的图片大小不超过500K,每个商标注册证号图片不超过100张</div>
							<div id="mchtBrandAptitudeDiv">
							<c:forEach var="mchtBrandAptitudeCustom" items="${mchtBrandAptitudeCustoms}">
							<table class="table table-bordered" mchtBrandAptitudeId="${mchtBrandAptitudeCustom.id}">
								<tbody>
									<tr>
										<td class="editfield-label-td">商标注册证号<span class="required">*</span></td>
										<td colspan="2" class="text-left">
											<input validate="{required:true}" type="text" value="${mchtBrandAptitudeCustom.certificateNo}" name="certificateNo">
											<div style="display: inline-block;color: #999999;">商标注册证号必填</div>
										</td>
									</tr>
									<tr>
										<td class="editfield-label-td">本商标注册证相关文件<span class="required">*</span></td>
										<td colspan="2" class="text-left">
											<div class="pic-container">
												<ul class="docs-pictures clearfix td-pictures pictures-list" name="aptitude-pictures-list" ondrop="drop(event)" ondragover="allowDrop(event)">
													<c:forEach var="mchtBrandAptitudePic" items="${mchtBrandAptitudeCustom.mchtBrandAptitudePicChgs}" varStatus="varStatus">
														<li id="mchtBrandAptitudePic_li_${varStatus.index }" class="pic_li" draggable="true" ondragstart="drag(event)" pic_path="${mchtBrandAptitudePic.pic}">
															<img  src="${ctx}/file_servelt${mchtBrandAptitudePic.pic}">
															<div class="pic-btn-container" style="height: 0px;">
															<span class="glyphicon glyphicon-remove pic-remove-icon" aria-hidden="true"></span>
															</div>
														</li>
													</c:forEach>
												</ul>
											</div>
										</td>
									</tr>
									<tr>
										<td class="editfield-label-td">商标注册证有效期<span class="required">*</span></td>
										<td colspan="2" class="text-left">
											<input type="text" validate="{required:true}" value="<fmt:formatDate value='${mchtBrandAptitudeCustom.aptitudeExpDate}' pattern='yyyy-MM-dd'/>" name="aptitudeExpDate" data-date-format="yyyy-mm-dd">
											<div style="display: inline-block;color: #999999;">商标注册证有效期必填</div>
										</td>
									</tr>
								</tbody>
							</table>
							</c:forEach>
							</div>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">销售授权书</td>
						<td colspan="2" class="text-left">
						        <c:forEach items="${mchtPlatformAuthPicChgs}" var="pic">
							    	<img  src="${ctx}/file_servelt${pic.pic}">
							    </c:forEach>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">授权期限</td>
						<td colspan="2" class="text-left">
						<span><fmt:formatDate value="${mchtBrandChg.platformAuthExpDate }" pattern="yyyy-MM-dd"/></span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">品牌经营类目<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<div style="display: inline-block;padding-bottom: 10px;"><a href="javascript:;" class="btn btn-info" id="addBrandProductType">添加</a></div>
							<table class="table table-bordered" id="brandProductTypeTable" <c:if test="${empty mchtBrandProductTypeCustoms}">style="display: none;"</c:if>>
								<thead>
									<tr style="background: gainsboro;">
										<th>一级类目</th>
										<th>二级类目</th>
										<th>三级类目</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="mchtBrandProductTypeCustom" items="${mchtBrandProductTypeCustoms}">
									<tr>
										<td productType1Id="${mchtBrandProductTypeCustom.productType1Id}">${mchtBrandProductTypeCustom.firstProductTypeName}</td>
										<td productType2Id="${mchtBrandProductTypeCustom.productType2Id}">${mchtBrandProductTypeCustom.secondProductTypeName}</td>
										<td productTypeIds="${mchtBrandProductTypeCustom.productTypeIds}">${mchtBrandProductTypeCustom.thirdProductTypeNames}</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">进货发票</td>
						<td colspan="2" class="text-left">
								<c:forEach items="${mchtBrandInvoicePics}" var="pic">
							    	<img  src="${ctx}/file_servelt${pic.pic}">
							    </c:forEach>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">质检报告/检疫报告</td>
						<td colspan="2" class="text-left">
								<c:forEach items="${mchtBrandInspectionPics}" var="pic">
							    	<img  src="${ctx}/file_servelt${pic.pic}">
							    </c:forEach>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">质检报告/检疫报告有效期</td>
						<td colspan="2" class="text-left">
						<span><fmt:formatDate value="${mchtBrandChg.inspectionExpDate }" pattern="yyyy-MM-dd"/></span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">其他资质</td>
						<td colspan="2" class="text-left">
								<c:forEach items="${mchtBrandOtherPics}" var="pic">
							    	<img  src="${ctx}/file_servelt${pic.pic}">
							    </c:forEach>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">其他资质有效期</td>
						<td colspan="2" class="text-left">
						<span><fmt:formatDate value="${mchtBrandChg.otherExpDate }" pattern="yyyy-MM-dd"/></span>
						</td>
					</tr>
					
					<c:if test="${mchtInfoStatus==1 && brandSource == 2}">
					<tr>
						<td class="editfield-label-td">合作协议变更申请函<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<div class="pic-container">
								<ul class="docs-pictures clearfix td-pictures pictures-list" id="agreement-pictures-list" ondrop="drop(event)" ondragover="allowDrop(event)">
								<c:forEach var="mchtBrandChangeAgreementPic" items="${mchtBrandChangeAgreementPics}" varStatus="varStatus">
								<li id="mchtBrandChangeAgreementPic_li_${varStatus.index }" class="pic_li" draggable="true" ondragstart="drag(event)" pic_path="${mchtBrandChangeAgreementPic.pic}">
								<img  src="${ctx}/file_servelt${mchtBrandChangeAgreementPic.pic}">
								<div class="pic-btn-container" style="height: 0px;">
								<span class="glyphicon glyphicon-remove pic-remove-icon" aria-hidden="true"></span>
								</div>
								</li>
								</c:forEach>
								</ul>
								<div class="filePicker">添加图片</div>
						</div>
						</td>
					</tr>
					</c:if>
					
					<tr>
						<td class="editfield-label-td">技术服务费率</td>
						<td colspan="2" class="text-left">
						<span>${mchtProductBrand.popCommissionRate}</span>
						</td>
					</tr>
					
					<%-- <tr>
						<td class="editfield-label-td">状态</td>
						<td colspan="2" class="text-left">
						<span>${auditStatusDesc }</span>
						</td>
					</tr> --%>
				</tbody>
			</table>
		</div>
		
		
			</div>
	</div>
  </div>



</body>
</html>
