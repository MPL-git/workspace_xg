<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>公司信息</title>

<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />

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
</head>

<body>
	<div class="x_panel container-fluid">
		<div class="row content-title">
			<div class="t-title">公司信息</div>
		</div>

		<div class="x_content container-fluid sp-gl">
			<div class="row">
				<div class="col-md-12 datatable-container at-table">
					<table class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
						<tbody>
							<tr>
								<td><c:if test="${mchtInfo.settledType eq 1}">公司名称</c:if><c:if test="${mchtInfo.settledType eq 2}">名称</c:if></td>
								<td>${mchtInfo.companyName}</td>
							</tr>
							<tr>
								<td><c:if test="${mchtInfo.settledType eq 1}">公司类型</c:if><c:if test="${mchtInfo.settledType eq 2}">组成形式</c:if></td>
								<td>${mchtInfo.companyType}</td>
							</tr>
							<c:if test="${mchtInfo.settledType eq 1}">
							<tr>
								<td>统一信用代码</td>
								<td>${mchtInfo.uscc}</td>
							</tr>
							
							<tr>
								<td>注册资本</td>
								<td>${mchtInfo.regCapital}万${mchtInfo.regFeeTypeDesc}</td>
							</tr>
							</c:if>
							<tr>
								<td><c:if test="${mchtInfo.settledType eq 1}">公司住所</c:if><c:if test="${mchtInfo.settledType eq 2}">经营场所</c:if></td>
								<td>${mchtInfo.companyAddress}</td>
							</tr>
							
							<tr>
								<td><c:if test="${mchtInfo.settledType eq 1}">成立日期</c:if><c:if test="${mchtInfo.settledType eq 2}">发照时间</c:if></td>
								<td>
									<fmt:formatDate value="${mchtInfo.foundedDate}" pattern="yyyy-MM-dd"/>
								</td>
							</tr>
							
							<tr>
								<td>营业执照有效期</td>
								<td>
									<fmt:formatDate value="${mchtInfo.yearlyInspectionDate}" pattern="yyyy-MM-dd"/>
									<span style="color: red;">【到期】</span>
								</td>
							</tr>
							
							<tr>
								<td><c:if test="${mchtInfo.settledType eq 1}">营业执照经营范围</c:if><c:if test="${mchtInfo.settledType eq 2}">经营范围</c:if></td>
								<td>
									${mchtInfo.scopeOfBusiness}
								</td>
							</tr>
							
							<tr>
								<td><c:if test="${mchtInfo.settledType eq 1}">法人姓名</c:if><c:if test="${mchtInfo.settledType eq 2}">经营者姓名</c:if></td>
								<td>${mchtInfo.corporationName}</td>
							</tr>
							<tr>
								<td><c:if test="${mchtInfo.settledType eq 1}">法人身份证号</c:if><c:if test="${mchtInfo.settledType eq 2}">经营者身份证号</c:if></td>
								<td>${mchtInfo.corporationIdcardNo}</td>
							</tr>
							<tr>
								<td><c:if test="${mchtInfo.settledType eq 1}">法人身份证</c:if><c:if test="${mchtInfo.settledType eq 2}">经营者身份证</c:if></td>
								<td class="img-td">
									<ul class="td-ul" id="idCard-list">
										<li>
											<img src="${ctx}/file_servelt${mchtInfo.corporationIdcardImg1}">
										</li>
										<li>
											<img src="${ctx}/file_servelt${mchtInfo.corporationIdcardImg2}">
										</li>
									</ul>
								</td>
							</tr>
							<tr>
								<td><c:if test="${mchtInfo.settledType eq 1}">法人身份证有效期</c:if><c:if test="${mchtInfo.settledType eq 2}">身份证有效期</c:if></td>
								<td>
									<fmt:formatDate value="${mchtInfo.corporationIdcardDate}" pattern="yyyy-MM-dd"/>
								</td>
							</tr>
							<tr>
								<td><c:if test="${mchtInfo.settledType eq 1}">法人手机</c:if><c:if test="${mchtInfo.settledType eq 2}">经营者手机</c:if></td>
								<td>${mchtInfo.corporationMobile}</td>
							</tr>
							<c:if test="${mchtInfo.settledType eq 1}">
							<tr>
								<td>公司总机</td>
								<td>${mchtInfo.companyTel}</td>
							</tr>
							</c:if>
							<tr>
								<td><c:if test="${mchtInfo.settledType eq 1}">公司通讯地址</c:if><c:if test="${mchtInfo.settledType eq 2}">通讯地址</c:if></td>
								<td>${mchtInfo.contactProvinceName}${mchtInfo.contactCityName}${mchtInfo.contactCountyName}${mchtInfo.contactAddress}</td>
							</tr>
							<tr>
								<td>营业执照副本</td>
								<td class="img-td">
									<c:if test="${not empty mchtInfo.blPic}">
									<img src="${ctx}/file_servelt${mchtInfo.blPic}" onclick="viewerPic(this.src)">
									</c:if>
								</td>
							</tr>
							<c:if test="${mchtInfo.settledType eq 1}">
							<tr>
								<td>组织机构代码证</td>
								<td class="img-td">
									<c:if test="${not empty mchtInfo.occPic}">
										<img src="${ctx}/file_servelt${mchtInfo.occPic}" onclick="viewerPic(this.src)">
									</c:if>
								</td>
							</tr>
							<tr>
								<td>税务登记证</td>
								<td class="img-td">
									<c:if test="${not empty mchtInfo.trcPic}">
										<img src="${ctx}/file_servelt${mchtInfo.trcPic}" onclick="viewerPic(this.src)">
									</c:if>
								</td>
							</tr>
							<tr>
								<td>一般纳税人资格证</td>
								<td class="img-td">
									<c:if test="${not empty mchtInfo.atqPic}">
										<img src="${ctx}/file_servelt${mchtInfo.atqPic}" onclick="viewerPic(this.src)">
									</c:if>
								</td>
							</tr>
							</c:if>
							<tr>
								<td><c:if test="${mchtInfo.settledType eq 1}">银行开户许可证</c:if><c:if test="${mchtInfo.settledType eq 2}">经营者银行卡信息</c:if></td>
								<td class="img-td">
									<c:if test="${not empty mchtInfo.boaalPic}">
										<img src="${ctx}/file_servelt${mchtInfo.boaalPic}" onclick="viewerPic(this.src)">
									</c:if>
								</td>
							</tr>
							
							<tr>
								<td>主要经营品牌类型</td>
						 		<td colspan="2" class="text-left">
                         		<select style="width:210px;" name="brandType" id="brandType" validate="{required:true}" disabled>
                         		<option value="">请选择</option>
                        		<option value="0" <c:if test="${mchtInfo.brandType eq 0}">  selected = "selected" </c:if>>品牌官方</option>
                         		<option value="1" <c:if test="${mchtInfo.brandType eq 1}">  selected = "selected" </c:if>>品牌总代</option>
                         		<option value="2" <c:if test="${mchtInfo.brandType eq 2}">  selected = "selected" </c:if>>品牌代理商</option>
                         		</select>
                        		 <br><br>
                         		<textarea class="form-control" disabled rows="5" style="width: 100%;resize: vertical;" id="brandTypeDesc" name="brandTypeDesc" maxlength="256" validate="{required:true}">${mchtInfo.brandTypeDesc}</textarea>
						 		</td>
							</tr>
							<tr>
								<td>信息状态</td>
								<td style="color: red;">
									${mchtInfo.isCompanyInfPerfectDesc}

<!-- 									<c:if test="${infoUpdateCount>0}"> -->
<!-- 										<a href="javascript:mchtInfoChgApply(${mchtInfo.id})">【查看已申请更新】</a> -->
<!-- 									</c:if> -->

									<c:if test="${mchtInfo.isCompanyInfPerfect=='1'}">
										<a href="javascript:mchtInfoChgApply(${mchtInfo.id})">【申请更新】</a>
									</c:if>

									<c:if test="${mchtInfo.isCompanyInfPerfect=='0'||mchtInfo.isCompanyInfPerfect=='2'||mchtInfo.isCompanyInfPerfect=='4'}">
										<a href="javascript:mchtInfofect(${mchtProductBrand.id})">【修改】</a>
									</c:if>
								</td>
							</tr>
							
							<c:if test="${mchtInfo.isCompanyInfPerfect=='4'}">
								<tr>
									<td>驳回原因</td>
									<td style="color: red;">${mchtInfo.companyInfAuditRemarks}</td>
								</tr>
							</c:if>
							
						</tbody>
					</table>
				</div>
			</div>
	    </div>
	</div>
	
<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static"></div>
	<ul  class="docs-pictures clearfix td-ul" id="viewer-pictures" style="display: none;">
	
	</ul>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>	
<script>



var idCardViewer;
var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
		idCardViewer = new Viewer(document.getElementById('idCard-list'), {
	});
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


	var isReload=false;
	$(function(){
		$('#viewModal').on('hidden.bs.modal', function (e) {
			if(isReload){
				$.ajax({
			        url: "${ctx}/mcht/mchtInfo", 
			        type: "GET", 
			        success: function(data){
			            $("#main-content").html(data);
			            isReload=false;
			        },
				    error:function(){
				    	swal('页面不存在');
				    }
				});
			}
			});
	});
	
	
	function mchtInfofect(){
		
		$.ajax({
	        url: "${ctx}/mcht/mchtInfoPerfect?mchtId=${mchtInfo.id}", 
	        type: "GET", 
	        success: function(data){
	            $("#viewModal").html(data);
	            $("#viewModal").modal();
	        },
		    error:function(){
		    	swal('页面不存在');
		    }
		});
	}
	
	
	function mchtInfoChgApply(){
		getContent("${ctx}/mcht/mchtInfoChgApplyIndex");
	}
	
	
	
</script>
</body>
</html>
