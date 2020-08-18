<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>查看品牌</title>
<style type="text/css">
td img{
display: inline-block;
width: 60px;
height: 40px;
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
					<tr>
						<td class="editfield-label-td">品牌名称</td>
						<td colspan="2" class="text-left">
							<span>${mchtProductBrand.productBrandName }<a href="javascript:;" onclick="downLoadBrandPic(${mchtProductBrand.id});">[下载图片]</a></span>
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
					                                        <div>
										                        <span style="color: red;"><c:if test="${mchtBrandAptitudePic.archiveStatus == 2}">未齐</c:if></span>
										                        <span><c:if test="${mchtBrandAptitudePic.archiveStatus == 3}">已归档</c:if></span>
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
						<div class="pic-container">
							<ul class="docs-pictures clearfix td-pictures pictures-list">
                                <c:forEach var="mchtPlatformAuthPic" items="${mchtPlatformAuthPics}">
                                    <li class="pic_li">
                                        <img src="${ctx}/file_servelt${mchtPlatformAuthPic.pic}">
                                        <div>
									        <span style="color: red;"><c:if test="${mchtPlatformAuthPic.archiveStatus == 2}">未齐</c:if></span>
									        <span><c:if test="${mchtPlatformAuthPic.archiveStatus == 3}">已归档</c:if></span>
                                    	</div>
                                    </li>
                                </c:forEach>
	                        </ul>
	                    </div>        
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
                                        <div>
									        <span style="color: red;"><c:if test="${mchtBrandInvoicePic.archiveStatus == 2}">未齐</c:if></span>
									        <span><c:if test="${mchtBrandInvoicePic.archiveStatus == 3}">已归档</c:if></span>
                                    	</div>
                                    </li>
                                </c:forEach>
	                        </ul>
	                    </div>	    
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">质检报告/检疫报告</td>
						<td colspan="2" class="text-left">
						<div class="pic-container">
                            <ul class="docs-pictures clearfix td-pictures pictures-list">
                                <c:forEach var="mchtBrandInspectionPic" items="${mchtBrandAptitudeCustom.mchtBrandInspectionPics}">
                                    <li class="pic_li">
                                        <img  src="${ctx}/file_servelt${mchtBrandInspectionPic.pic}">
                                        <div>
					                        <span style="color: red;"><c:if test="${mchtBrandInspectionPic.archiveStatus == 2}">未齐</c:if></span>
					                        <span><c:if test="${mchtBrandInspectionPic.archiveStatus == 3}">已归档</c:if></span>
                               						</div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">其他资质</td>
						<td colspan="2" class="text-left">
						<div class="pic-container">
                            <ul class="docs-pictures clearfix td-pictures pictures-list">
                                <c:forEach var="mchtBrandOtherPic" items="${mchtBrandAptitudeCustom.mchtBrandOtherPics}">
                                    <li class="pic_li">
                                        <img  src="${ctx}/file_servelt${mchtBrandOtherPic.pic}">
                                        <div>
					                        <span style="color: red;"><c:if test="${mchtBrandOtherPic.archiveStatus == 2}">未齐</c:if></span>
					                        <span><c:if test="${mchtBrandOtherPic.archiveStatus == 3}">已归档</c:if></span>
                               			</div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
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
  
  
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<!-- Bootstrap -->

</body>
</html>
