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
        <span class="modal-title" id="exampleModalLabel">查看寄件内容</span>
      </div>
			<div class="modal-body">
	      <div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">项目</td>
						<td colspan="2" class="text-left">
							<span>资质文件如是复印件需全部加盖公章。且保证文件内容清晰、章印清楚</span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">品牌名称</td>
						<td colspan="2" class="text-left">
							<span>${productBrandName}<a href="javascript:;" onclick="downLoadBrandPic(${mchtProductBrandId});">【下载图片】</a></span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">商标注册证或受理通知书 <span class="required">*</span></td>
						<td colspan="2" class="text-left">
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
						<td class="editfield-label-td">其他资质</td>
						<td colspan="2" class="text-left">
								<c:forEach items="${mchtBrandOtherPics}" var="pic">
							    	<img  src="${ctx}/file_servelt${pic.pic}">
							    </c:forEach>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		
			</div>
	</div>
  </div>

<script type="text/javascript">
	function downLoadBrandPic(mchtProductBrandId){
		location.href="${ctx}/mcht/contract/downLoadBrandPic?mchtProductBrandId="+mchtProductBrandId;
	}

</script>


</body>
</html>
