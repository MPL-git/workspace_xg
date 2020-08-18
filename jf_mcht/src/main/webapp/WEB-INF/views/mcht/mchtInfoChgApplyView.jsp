<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
      <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
      <link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />
          <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>	
<title>申请修改公司信息</title>
<style type="text/css">
.td-ul {
margin: 0;
padding: 0;
}
.td-ul li{
display: inline-block;
}
.img-td img{
display: inline-block;
width: 60px;
height: 40px;
margin:10px 10px 10px 0;
}
.td-ul li img{
width: 60px;
height: 40px;
}
</style>
<script type="text/javascript">

var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
});

function viewerPic(imgPath){
	
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
	viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	viewerPictures.show();
	
}
</script>

</head>

<body>

  <div class="modal-dialog" role="document" style="width:800px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">查看信息</span>
      </div>
			<div class="modal-body">
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">公司名称</td>
						<td colspan="2" class="text-left">
						<span>${mchtInfoChg.companyName}</span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">公司类型</td>
						<td colspan="2" class="text-left">
						<span>${mchtInfoChg.companyType}</span>
						</td>
					</tr>
					
					
				    <tr>
						<td class="editfield-label-td">统一信用代码</td>
						<td colspan="2" class="text-left">
						<span>${mchtInfoChg.uscc}</span>
						</td>
					</tr>
					
					
					
					<tr>
						<td class="editfield-label-td">注册资本</td>
						<td colspan="2" class="text-left">
						<span>${mchtInfoChg.regCapital}（万）${mchtInfoChg.regFeeTypeDesc}</span>
						
						</td>
					</tr>
					
					
					<tr>
						<td class="editfield-label-td">公司住所</td>
						<td colspan="2" class="text-left">
						<span>${mchtInfoChg.companyAddress}</span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">成立日期</td>
						<td colspan="2" class="text-left">
						<span><fmt:formatDate value='${mchtInfoChg.foundedDate}' pattern='yyyy-MM-dd'/></span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">营业执照有效期</td>
						<td colspan="2" class="text-left">
						<span><fmt:formatDate value='${mchtInfoChg.yearlyInspectionDate}' pattern='yyyy-MM-dd'/></span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">营业执照经营范围</td>
						<td colspan="2" class="text-left">
							${mchtInfoChg.scopeOfBusiness}
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">法人姓名</td>
						<td colspan="2" class="text-left">
						<span>${mchtInfoChg.corporationName}</span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">法人身份证号</td>
						<td colspan="2" class="text-left">
						<span>${mchtInfoChg.corporationIdcardNo}</span>
						</td>
					</tr>
					
					
					
					
					<tr>
						<td class="editfield-label-td">法人身份证</td>
						<td colspan="2" class="img-td text-left">	
								<img  src="${ctx}/file_servelt${mchtInfoChg.corporationIdcardImg1}" onclick="viewerPic(this.src)">
								<img  src="${ctx}/file_servelt${mchtInfoChg.corporationIdcardImg2}" onclick="viewerPic(this.src)">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">法人身份证有效期</td>
						<td colspan="2" class="text-left">
						<span><fmt:formatDate value='${mchtInfoChg.corporationIdcardDate}' pattern='yyyy-MM-dd'/></span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">法人手机</td>
						<td colspan="2" class="text-left">
						<span>${mchtInfoChg.corporationMobile}</span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">公司总机</td>
						<td colspan="2" class="text-left">
						<span>${mchtInfoChg.companyTel}</span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">公司通讯地址</td>
						<td colspan="2" class="text-left">
						
						<span>${mchtInfoChg.contactProvinceName}${mchtInfoChg.contactCityName}${mchtInfoChg.contactCountyName}${mchtInfoChg.contactAddress}</span>
						
						</td>
					</tr>
					
							<tr>
								<td>营业执照副本</td>
								<td class="img-td text-left">
									<c:if test="${not empty mchtInfoChg.blPic}">
									<img src="${ctx}/file_servelt${mchtInfoChg.blPic}" onclick="viewerPic(this.src)">
									</c:if>
								</td>
							</tr>
							<tr>
								<td>组织机构代码证</td>
								<td class="img-td text-left">
									<c:if test="${not empty mchtInfoChg.occPic}">
									<img src="${ctx}/file_servelt${mchtInfoChg.occPic}" onclick="viewerPic(this.src)">
									</c:if>
								</td>
							</tr>
							<tr>
								<td>税务登记证</td>
								<td class="img-td text-left">
									<c:if test="${not empty mchtInfoChg.trcPic}">
									<img src="${ctx}/file_servelt${mchtInfoChg.trcPic}" onclick="viewerPic(this.src)">
									</c:if>
								</td>
							</tr>
							<tr>
								<td>一般纳税人资格证</td>
								<td class="img-td text-left">
									<c:if test="${not empty mchtInfoChg.atqPic}">
									<img src="${ctx}/file_servelt${mchtInfoChg.atqPic}" onclick="viewerPic(this.src)">
									</c:if>
								</td>
							</tr>
							<tr>
								<td>银行开户许可证</td>
								<td class="img-td text-left">
									<c:if test="${not empty mchtInfoChg.boaalPic}">
									<img src="${ctx}/file_servelt${mchtInfoChg.boaalPic}" onclick="viewerPic(this.src)">
									</c:if>
								</td>
							</tr>
							<tr>
								<td>主要经营品牌类型</td>
						 		<td colspan="2" class="text-left">
                         		<select style="width:210px;" name="brandType" id="brandType" validate="{required:true}" disabled>
                         		<option value="">请选择</option>
                        		<option value="0" <c:if test="${mchtInfoChg.brandType eq 0}">  selected = "selected" </c:if>>品牌官方</option>
                         		<option value="1" <c:if test="${mchtInfoChg.brandType eq 1}">  selected = "selected" </c:if>>品牌总代</option>
                         		<option value="2" <c:if test="${mchtInfoChg.brandType eq 2}">  selected = "selected" </c:if>>品牌代理商</option>
                         		</select>
                        		 <br><br>
                         		<textarea class="form-control" disabled rows="5" style="width: 100%;resize: vertical;" id="brandTypeDesc" name="brandTypeDesc" maxlength="256" validate="{required:true}">${mchtInfoChg.brandTypeDesc}</textarea>
						 		</td>
							</tr>
							<tr>
								<td class="editfield-label-td">审核状态</td>
								<td colspan="2" class="text-left">
								<span>${mchtInfoChg.statusDesc}</span>
								</td>
							</tr>
							<tr>
								<td class="editfield-label-td">驳回原因</td>
								<td colspan="2" class="text-left">
								<span>${mchtInfoChg.auditRemarks}</span>
								</td>
							</tr>
				</tbody>
			</table>
		</div>
		
			</div>
	</div>
  </div>
  	<ul  class="docs-pictures clearfix td-ul" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>
</html>
