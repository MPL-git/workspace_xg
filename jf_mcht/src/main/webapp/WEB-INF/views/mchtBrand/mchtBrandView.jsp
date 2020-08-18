<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>查看品牌</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/validate.jf.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/viewer.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/webuploader.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/imageUploader.css"/>

<style type="text/css">
    .td-pictures li{
        display: inline-block;
    }
    .td-pictures li img{
        width: 40px;
        height: 40px;
    }

    .td_title{
        font-weight: normal;
        background-color: #cccccc;
        height:22px;
        border-bottom: 0 !important;
    }

    .text-right{
        width:150px;
    }
</style>
</head>

<body>
<!--查看品牌 -->
  <div class="modal-dialog" role="document">
    <div class="modal-content" style="width: 910px;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">查看品牌</span>
      </div>
      <div class="modal-body">
      
      <div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<c:if test="${mchtProductBrand.auditStatus=='4' }">
					<tr>
						<td class="editfield-label-td">驳回原因</td>
						<td colspan="2" class="text-left">
						<span>${mchtProductBrand.auditRemarks }</span>
						</td>
					</tr>
					</c:if>
					<tr>
						<td class="editfield-label-td">品牌名称</td>
						<td colspan="2" class="text-left">
						<span>${mchtProductBrand.productBrandName }</span>
						</td>
					</tr>
					
<!-- 					<tr> -->
<!-- 						<td class="editfield-label-td">相应品牌ID</td> -->
<!-- 						<td colspan="2" class="text-left"> -->
<!-- 						<span>${mchtProductBrand.productBrandId }</span> -->
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr>
						<td class="editfield-label-td">资质类型</td>
						<td colspan="2" class="text-left">
						<span>${mchtProductBrand.aptitudeTypeDesc }</span>
						</td>
					</tr>
					
					
				    <tr>
						<td class="editfield-label-td">logo图片</td>
						<td colspan="2" class="text-left">
						<img style="width: 100px;height: 50px;" src="${ctx}/file_servelt${mchtProductBrand.logo}">
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
					                            <ul class="docs-pictures clearfix td-pictures pictures-list">
					                                <c:forEach var="mchtBrandAptitudePic" items="${mchtBrandAptitudeCustom.mchtBrandAptitudePics}">
					                                    <li class="pic_li">
					                                        <img  src="${ctx}/file_servelt${mchtBrandAptitudePic.pic}">
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
							<div class="pic-container">
	                            <ul class="docs-pictures clearfix td-pictures pictures-list">
	                                <c:forEach var="mchtPlatformAuthPic" items="${mchtPlatformAuthPics}">
	                                    <li class="pic_li">
	                                        <img src="${ctx}/file_servelt${mchtPlatformAuthPic.pic}">
	                                    </li>
	                                </c:forEach>
	                            </ul>
                            </div>    
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">授权有效期</td>
						<td colspan="2" class="text-left">
						<span><fmt:formatDate value="${mchtProductBrand.platformAuthExpDate }" pattern="yyyy-MM-dd"/></span>
						</td>
					</tr>
					
					
					<tr>
						<td class="editfield-label-td">进货发票</td>
						<td colspan="2" class="text-left">
							<div class="pic-container">
	                            <ul class="docs-pictures clearfix td-pictures pictures-list">
	                                <c:forEach var="mchtBrandInvoicePic" items="${mchtBrandInvoicePics}">
	                                    <li class="pic_li">
	                                        <img src="${ctx}/file_servelt${mchtBrandInvoicePic.pic}">
	                                    </li>
	                                </c:forEach>
	                            </ul>
                            </div>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">品牌经营类目<span class="required">*</span></td>
						<td colspan="2" class="text-left">
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
										<td>${mchtBrandProductTypeCustom.firstProductTypeName}</td>
										<td>${mchtBrandProductTypeCustom.secondProductTypeName}</td>
										<td>${mchtBrandProductTypeCustom.thirdProductTypeNames}</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">质检报告/检疫报告</td>
						<td colspan="2" class="text-left">
							<div class="pic-container">
	                            <ul class="docs-pictures clearfix td-pictures pictures-list">
	                                <c:forEach var="mchtBrandInspectionPic" items="${mchtBrandInspectionPics}">
	                                    <li class="pic_li">
	                                        <img src="${ctx}/file_servelt${mchtBrandInspectionPic.pic}">
	                                    </li>
	                                </c:forEach>
	                            </ul>
                            </div>    
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">质检报告/检疫报告有效期</td>
						<td colspan="2" class="text-left">
						<span><fmt:formatDate value="${mchtProductBrand.inspectionExpDate }" pattern="yyyy-MM-dd"/></span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">其他资质</td>
						<td colspan="2" class="text-left">
							<div class="pic-container">
	                            <ul class="docs-pictures clearfix td-pictures pictures-list">
	                                <c:forEach var="mchtBrandOtherPic" items="${mchtBrandOtherPics}">
	                                    <li class="pic_li">
	                                        <img src="${ctx}/file_servelt${mchtBrandOtherPic.pic}">
	                                    </li>
	                                </c:forEach>
	                            </ul>
                            </div>	    
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">其他资质有效期</td>
						<td colspan="2" class="text-left">
						<span><fmt:formatDate value="${mchtProductBrand.otherExpDate }" pattern="yyyy-MM-dd"/></span>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
      
      
    </div>
    </div>
  </div>
  
  <script type="text/javascript">
  
	function brandPerfect(){
		
		$.ajax({
	        url: "${ctx}/mcht/mchtBrandPerfect?mchtBrandId=${mchtProductBrand.id}", 
	        type: "GET", 
	        success: function(data){
	            $("#viewModal").html(data);
	        },
		    error:function(){
		    	swal('页面不存在');
		    }
		});
	}
	function brandChgApply(){
		
		$.ajax({
	        url: "${ctx}/mcht/mchtBrandChgApply?mchtBrandId=${mchtProductBrand.id}", 
	        type: "GET", 
	        success: function(data){
	            $("#viewModal").html(data);
	        },
		    error:function(){
		    	swal('页面不存在');
		    }
		});
	}

  
  </script>
  <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/js/imageUpload.js" ></script>
  
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<!-- Bootstrap -->

</body>
</html>
