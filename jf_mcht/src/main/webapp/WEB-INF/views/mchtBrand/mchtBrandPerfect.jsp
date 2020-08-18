<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>添加入驻品牌</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
	<!-- Bootstrap -->
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
      <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
            <link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />
<!--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/fileuploader-style.css" /> -->
<style type="text/css">
.td-pictures li{
display: inline-block;
}
.td-pictures li img{
width: 100px;
height: 100px;
}

.video_box {
	z-index: 2222;
	display: block;
}

.black_box {
	background: #000;
	opacity: 0.6;
	left: 0;
	top: 0;
	z-index: 1111;
	position: fixed;
	height: 100%;
	width: 100%;
}
.video_close {
	position: absolute;
	top: -14px;
	right: -12px;
}

.form-group img{
	width: 100px;
	height: 100px;
}
</style>


</head>

<body>

  <div class="modal-dialog" role="document">
    <div class="modal-content" style="width: 910px;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">添加入驻品牌</span>
      </div>
		<div class="modal-body">
		<c:if test="${(not empty mchtProductBrand.id)&&(mchtProductBrand.auditStatus=='4' || mchtProductBrand.zsAuditStatus=='4')}">
			<c:if test="${mchtProductBrand.zsAuditStatus==4}">
				<span style="color:red;">对不起，您的申请被驳回，驳回原因：${mchtProductBrand.zsAuditRemarks}</span>
			</c:if>
			<c:if test="${mchtProductBrand.auditStatus==4}">
				<span style="color:red;">对不起，您的申请被驳回，驳回原因：${mchtProductBrand.auditRemarks}</span>
			</c:if>
		<br>
		<br>
		</c:if>
		<form id="dataFrom">
		<input type="hidden" value="${mchtProductBrand.id}" name="id">
		<input type="hidden" value="${brandSource}" name="brandSource">
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">品牌名称<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<input validate="{required:true}" type="text" maxlength="255" value="${mchtProductBrand.productBrandName}" name="productBrandName" id="productBrandName" onblur="checkName(this);">
							<div style="display: inline-block;color: #999999;">品牌名称与提交的商标证书名称一致</div>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">资质类型<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						  <select  name="aptitudeType" id="aptitudeType" validate="{required:true}">
						   <option value="">--请选择--</option>
						   <c:forEach var="aptitudeTypeStatus" items="${aptitudeTypeList }">
						   <option value="${aptitudeTypeStatus.statusValue}"   <c:if test="${mchtProductBrand.aptitudeType==aptitudeTypeStatus.statusValue}">selected</c:if>>${aptitudeTypeStatus.statusDesc}</option>
						   </c:forEach>
                          </select>
						</td>
					</tr>
					

					
					
					<tr>
						<td class="editfield-label-td">品牌logo<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<div style="color: #999999;">
				    		图片为200x100像素，格式.png 或.jpg，大小不超过200K，无底色，彩色标 且与商标证书上的“Logo”一致
				    		</div>
							<c:if test='${mchtProductBrand.logo!=null&&mchtProductBrand.logo!=""}'>
								<div class="single_pic_picker">
									<input id="logoFile" onchange="loadImageFile(this)" type="file">
									<img  src="${ctx}/file_servelt${mchtProductBrand.logo}">
									<input type="hidden"  id="logo" name="logo" value="${mchtProductBrand.logo}">
								</div>
							</c:if>
							<c:if test='${mchtProductBrand.logo==null||mchtProductBrand.logo==""}'>
								<div class="single_pic_picker">
									<input id="logoFile" onchange="loadImageFile(this)" type="file">
									<div>+</div>
									<input type="hidden" id="logo" name="logo" value="">
								</div>
				    		</c:if>
						</td>
					</tr>
					
					
					<tr>
						<td class="editfield-label-td">商标注册证或受理通知书 <span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<div style="display: inline-block;color: #999999;">该品牌所有商标注册证/商标受理通知书的复印件加盖公章扫描后上传</div>
							<div style="display: inline-block;color: #999999;">备注：商标如有进行变更、转让的 需要提交相应的证明</div>
							<div style="display: inline-block;color: #999999;">每个商标注册证号图片不超过100张</div>
							<div id="mchtBrandAptitudeDiv">
							<c:forEach var="mchtBrandAptitudeCustom" items="${mchtBrandAptitudeCustoms}">
							<table class="table table-bordered" name="mchtBrandAptitudeTable" mchtBrandAptitudeId="${mchtBrandAptitudeCustom.id}">
								<tbody>
									<tr>
										<td class="editfield-label-td">商标注册证号<span class="required">*</span></td>
										<td colspan="2" class="text-left">
											<input validate="{required:true}" type="text" maxlength="64" value="${mchtBrandAptitudeCustom.certificateNo}" name="certificateNo">
											<div style="display: inline-block;color: #999999;">商标注册证号必填</div>
										</td>
									</tr>
									<tr>
										<td class="editfield-label-td">本商标注册证相关文件<span class="required">*</span></td>
										<td colspan="2" class="text-left">
											<div class="pic-container">
												<ul class="docs-pictures clearfix td-pictures pictures-list" name="aptitude-pictures-list" ondrop="drop(event)" ondragover="allowDrop(event)">
													<c:forEach var="mchtBrandAptitudePic" items="${mchtBrandAptitudeCustom.mchtBrandAptitudePics}" varStatus="varStatus">
														<li id="mchtBrandAptitudePic_li_${varStatus.index }" class="pic_li" draggable="true" ondragstart="drag(event)" pic_path="${mchtBrandAptitudePic.pic}">
															<img  src="${ctx}/file_servelt${mchtBrandAptitudePic.pic}">
															<c:if test="${mchtProductBrand.auditStatus!=4}">
																<div class="pic-btn-container" style="height: 0px;">
																	<span class="glyphicon glyphicon-remove pic-remove-icon" aria-hidden="true"></span>
																</div>
															</c:if>
														</li>
													</c:forEach>
												</ul>
												<div class="filePicker">添加图片</div>
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
									<tr>
										<td colspan="2">
											<div style="float: left;display: inline-block;">
    											<a href="javascript:;" class="btn btn-info" name="delMchtBrandAptitude" onclick="delMchtBrandAptitude(this);" style="background: red;">删除</a>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
							</c:forEach>
							<c:if test="${empty mchtBrandAptitudeCustoms}">
							<table class="table table-bordered" name="mchtBrandAptitudeTable" mchtBrandAptitudeId="${mchtBrandAptitudeCustom.id}">
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
													<c:forEach var="mchtBrandAptitudePic" items="${mchtBrandAptitudeCustom.mchtBrandAptitudePics}" varStatus="varStatus">
														<li id="mchtBrandAptitudePic_li_${varStatus.index }" class="pic_li" draggable="true" ondragstart="drag(event)" pic_path="${mchtBrandAptitudePic.pic}">
															<img  src="${ctx}/file_servelt${mchtBrandAptitudePic.pic}">
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
									<tr>
										<td class="editfield-label-td">商标注册证有效期<span class="required">*</span></td>
										<td colspan="2" class="text-left">
											<input type="text" validate="{required:true}" value="<fmt:formatDate value='${mchtBrandAptitudeCustom.aptitudeExpDate}' pattern='yyyy-MM-dd'/>" id="aptitudeExpDate1" name="aptitudeExpDate" data-date-format="yyyy-mm-dd">
											<div style="display: inline-block;color: #999999;">商标注册证有效期必填</div>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div style="float: left;display: inline-block;">
    											<a href="javascript:;" class="btn btn-info" name="delMchtBrandAptitude" onclick="delMchtBrandAptitude(this);" style="background: red;">删除</a>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
							</c:if>
							</div>
							<div style="display: inline-block;"><a href="javascript:;" class="btn btn-info" id="addMchtBrandAptitude">添加</a></div>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">销售授权书<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<div style="display: inline-block;color: #999999;">该品牌所有商标注册证/商标受理通知书的复印件加盖公章扫描后上传</div>
						<div style="display: inline-block;color: #999999;">备注：所有商标授权相关的都需要提交</div>
						<div class="pic-container">
								<ul class="docs-pictures clearfix td-pictures pictures-list" id="platformAuth-pictures-list" ondrop="drop(event)" ondragover="allowDrop(event)">
								<c:forEach var="mchtPlatformAuthPic" items="${mchtPlatformAuthPics}" varStatus="varStatus">
								<li id="mchtPlatformAuthPic_li_${varStatus.index }" class="pic_li" draggable="true" ondragstart="drag(event)" pic_path="${mchtPlatformAuthPic.pic}">
								<img  src="${ctx}/file_servelt${mchtPlatformAuthPic.pic}">
								<c:if test="${mchtProductBrand.auditStatus!=4}">
								<div class="pic-btn-container" style="height: 0px;">
									<span class="glyphicon glyphicon-remove pic-remove-icon" aria-hidden="true"></span>
								</div>
								</c:if>
								</li>
								</c:forEach>
								</ul>
								<div class="filePicker">添加图片</div>
						</div>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">授权期限<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<input type="text" validate="{required:true}" value="<fmt:formatDate value='${mchtProductBrand.platformAuthExpDate}' pattern='yyyy-MM-dd'/>" id="platformAuthExpDate" name="platformAuthExpDate" data-date-format="yyyy-mm-dd">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">进货发票</td>
						<td colspan="2" class="text-left">
							<div class="pic-container">
									<ul class="docs-pictures clearfix td-pictures pictures-list" id="invoice_pictures-list" ondrop="drop(event)" ondragover="allowDrop(event)">
									<c:forEach var="invoicePic" items="${mchtBrandInvoicePics}" varStatus="varStatus">
									<li id="invoicePic_li_${varStatus.index }" class="pic_li" draggable="true" ondragstart="drag(event)" pic_path="${invoicePic.pic}">
									<img  src="${ctx}/file_servelt${invoicePic.pic}">
									<c:if test="${mchtProductBrand.auditStatus!=4}">
									<div class="pic-btn-container" style="height: 0px;">
										<span class="glyphicon glyphicon-remove pic-remove-icon" aria-hidden="true"></span>
									</div>
									</c:if>
									</li>
									</c:forEach>
									</ul>
									<div class="filePicker">添加图片</div>
							</div>
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
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="mchtBrandProductTypeCustom" items="${mchtBrandProductTypeCustoms}">
									<tr>
										<td productType1Id="${mchtBrandProductTypeCustom.productType1Id}">${mchtBrandProductTypeCustom.firstProductTypeName}</td>
										<td productType2Id="${mchtBrandProductTypeCustom.productType2Id}">${mchtBrandProductTypeCustom.secondProductTypeName}</td>
										<td productTypeIds="${mchtBrandProductTypeCustom.productTypeIds}">${mchtBrandProductTypeCustom.thirdProductTypeNames}</td>
										<td><a href="javascript:;" onclick="delMchtBrandProductType(this);">删除</a></td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							
						</td>
					</tr>	
					
					<tr>
						<td class="editfield-label-td">质检报告/检疫报告<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<div style="display: inline-block;color: #999999;">质检报告出具人必须是商标权人或入驻商家，质检报告上要体现的品牌名须与商标证书上的一致，且质检报告是最新一年的质检报告 ,每个类目图片不超过100张 <a href="javascript:;" onclick="toViewAptitude();">【查看资质要求】</a></div>
						<div class="pic-container">
								<ul class="docs-pictures clearfix td-pictures pictures-list" id="inspection-pictures-list" ondrop="drop(event)" ondragover="allowDrop(event)">
								<c:forEach var="inspectionPic" items="${mchtBrandInspectionPics}" varStatus="varStatus">
								<li id="inspectionPic_li_${varStatus.index }" class="pic_li" draggable="true" ondragstart="drag(event)" pic_path="${inspectionPic.pic}">
								<img  src="${ctx}/file_servelt${inspectionPic.pic}">
								<c:if test="${mchtProductBrand.auditStatus!=4}">
								<div class="pic-btn-container" style="height: 0px;">
									<span class="glyphicon glyphicon-remove pic-remove-icon" aria-hidden="true"></span>
								</div>
								</c:if>
								</li>
								</c:forEach>
								</ul>
								<div class="filePicker">添加图片</div>
						</div>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td" style="width: 165px;">质检报告/检疫报告有效期<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<input type="text" validate="{required:true}" value="<fmt:formatDate value='${mchtProductBrand.inspectionExpDate}' pattern='yyyy-MM-dd'/>" id="inspectionExpDate" name="inspectionExpDate" data-date-format="yyyy-mm-dd">
						</td>
					</tr>
					
					
					
					<tr>
						<td class="editfield-label-td">其它资质</td>
						<td colspan="2" class="text-left">
						<div class="pic-container">
								<ul class="docs-pictures clearfix td-pictures pictures-list" id="other-pictures-list" ondrop="drop(event)" ondragover="allowDrop(event)">
								<c:forEach var="otherPic" items="${mchtBrandOtherPics}" varStatus="varStatus">
								<li id="otherPic_li_${varStatus.index }" class="pic_li" draggable="true" ondragstart="drag(event)" pic_path="${otherPic.pic}">
								<img  src="${ctx}/file_servelt${otherPic.pic}">
								<c:if test="${mchtProductBrand.auditStatus!=4}">
								<div class="pic-btn-container" style="height: 0px;">
									<span class="glyphicon glyphicon-remove pic-remove-icon" aria-hidden="true"></span>
								</div>
								</c:if>
								</li>
								</c:forEach>
								</ul>
								<div class="filePicker">添加图片</div>
						</div>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">其它资质有效期</td>
						<td colspan="2" class="text-left">
						<input type="text" value="<fmt:formatDate value='${mchtProductBrand.otherExpDate}' pattern='yyyy-MM-dd'/>" id="otherExpDate" name="otherExpDate" data-date-format="yyyy-mm-dd">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<span style="color:#999999;">备注：上述资质文件必须全部复印后加盖公司公章，逐一扫描上传。保证上传后图片清晰可查看。</span>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		</form>
		
	  <div class="modal-footer">
		<c:if test="${empty mchtProductBrand.id || mchtProductBrand.zsAuditStatus == 0 || mchtProductBrand.zsAuditStatus == 4 || mchtProductBrand.auditStatus == 4}">
			<button class="btn btn-info" onclick="commitAudit();">提交</button>
			<button class="btn btn-info" data-dismiss="modal">取消</button>
		</c:if>	
      </div>
		
			</div>
	</div>
  </div>
<!--品牌经营类目Div-->
<div class="video_box" style="position:fixed; width:675px; height:170px; top:35%; left:40%; display: none;" id="brandProductTypeDiv">
	<a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					添加具体经营类目
				</h3>
			</div>
			
			<form class="form-horizontal" role="form" style="margin-top: 10px;" >
				<table class="table table-bordered ">
					<tbody>
						<tr>
							<td class="editfield-label-td">选择品类<em class="ad-em">*</em></td>
							<td colspan="2" class="text-left">
							  <select class="ad-select" style="height: 134px;margin-right: 7px;" size="5" id="productType1"  onchange="$('#productTypeId').empty();getProductTypeList('productType2',$(this).val());">
	                          </select>
							  <select class="ad-select" style="height: 134px;margin-right: 7px;" size="5" id="productType2" onchange="getProductTypeList('productTypeId',$(this).val());" >
	                          </select>
							  <select class="ad-select" style="height: 134px;margin-right: 7px;" size="5" id="productTypeId" name="productTypeId" multiple="multiple">
	                          </select>
							</td>
						</tr>
					</tbody>
				</table> 
				<div class="form-group">
					<div class="col-sm-offset-5 col-md-7">
						<button type="button" class="btn btn-default" id="cancle">取消</button>
						<button type="button" class="btn btn-default" id="commit">确定</button>
					</div>
				</div>
			 </form>
		 </div>
</div>
<!--弹出div End-->

<!--查看资质要求Div-->
<div class="video_box" style="position:fixed; width:675px; height:170px; top:35%; left:40%; display: none;" id="viewAptitudeDiv">
	<a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					查看资质要求
				</h3>
			</div>
			<div class="form-group">
				${brandAptitude}
			</div>
		 </div>
</div>
<!--弹出div End-->
<div class="black_box" style="display: none;" id="blackBox"></div>


	
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js"
	type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<!--     <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/fileUpload.js"></script> -->
    <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>	
    <script src="${pageContext.request.contextPath}/static/js/imageUpload.js" type="text/javascript"></script>	
	
<script type="text/javascript">
function checkName(_this){
	var name = $(_this).val();
	var id = "${mchtProductBrand.id}";
	$.ajax({
		url : "${ctx}/mcht/mchtBrand/checkName",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {name:name,id:id},
		timeout : 30000,
		success : function(data) {
			if (data.returnCode=="0000") {
				
			} else {
				$(_this).next().text(data.returnMsg);
			}
			
		},
		error : function() {
			swal({
				  title: "请求失败！",
				  type: "error",
				  confirmButtonText: "确定"
				});
		}
	});
}

var dataFromValidate;
var imageUploader;
$(function(){
	imageUploader=new ImageUploader();
	
	$.metadata.setType("attr", "validate");

	dataFromValidate =$("#dataFrom").validate({
        highlight : function(element) {  
        	$(element).addClass('error');
            $(element).closest('tr').find("td").first().addClass('has-error');  
        },
        success : function(label) {  
            label.closest('tr').find("td").first().removeClass('has-error');  
        }
	});

    $('#aptitudeExpDate1').datetimepicker(
    		{
    			minView: "month", //选择日期后，不会再跳转去选择时分秒
				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                autoclose:true //选择日期后自动关闭
    	    }
    );
    $('#platformAuthExpDate').datetimepicker(
    		{
    			minView: "month", //选择日期后，不会再跳转去选择时分秒
				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                autoclose:true //选择日期后自动关闭
    	    }
    );
    $('#inspectionExpDate').datetimepicker(
    		{
    			minView: "month", //选择日期后，不会再跳转去选择时分秒
				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                autoclose:true //选择日期后自动关闭
    	    }
    );
    $('#otherExpDate').datetimepicker(
    		{
    			minView: "month", //选择日期后，不会再跳转去选择时分秒
				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                autoclose:true, //选择日期后自动关闭
                pickerPosition: "top-right"
    	    }
    );
    
    $("#addMchtBrandAptitude").on('click',function(){
    	var length = $("table[name='mchtBrandAptitudeTable']").length;
    	length++;
    	var buttonId="mchtBrandAptitudeTable"+length;
    	var aptitudeExpDateId="aptitudeExpDate"+length;
    	var html=[];
    	html.push('<table class="table table-bordered" name="mchtBrandAptitudeTable" mchtBrandAptitudeId="0">');
    	html.push('<tbody>');
    	html.push('<tr>');
    	html.push('<td class="editfield-label-td">商标注册证号<span class="required">*</span></td>');
    	html.push('<td colspan="2" class="text-left">');
    	html.push('<input validate="{required:true}" type="text" value="" name="certificateNo">');
    	html.push('<div style="display: inline-block;color: #999999;">商标注册证号必填</div>');
    	html.push('</td>');
    	html.push('</tr>');
    	html.push('<tr>');
    	html.push('<td class="editfield-label-td">本商标注册证相关文件<span class="required">*</span></td>');
    	html.push('<td colspan="2" class="text-left">');
    	html.push('<div class="pic-container">');
    	html.push('<ul class="docs-pictures clearfix td-pictures pictures-list" name="aptitude-pictures-list" ondrop="drop(event)" ondragover="allowDrop(event)">');
    	html.push('</ul>');
    	html.push('<div class="filePicker" id="'+buttonId+'">添加图片</div>');
    	html.push('</div>');
    	html.push('</td>');
    	html.push('</tr>');
    	html.push('<tr>');
    	html.push('<td class="editfield-label-td">商标注册证有效期<span class="required">*</span></td>');
    	html.push('<td colspan="2" class="text-left">');
    	html.push('<input type="text" validate="{required:true}" value="" id="'+aptitudeExpDateId+'" name="aptitudeExpDate" data-date-format="yyyy-mm-dd">');
    	html.push('<div style="display: inline-block;color: #999999;">商标注册证有效期必填</div>');
    	html.push('</td>');
    	html.push('</tr>');
    	html.push('<tr>');
    	html.push('<td colspan="2">');
    	html.push('<div style="float: left;display: inline-block;">');
    	html.push('<a href="javascript:;" class="btn btn-info" name="delMchtBrandAptitude" onclick="delMchtBrandAptitude(this);" style="background: red;">删除</a>');
    	html.push('</div>');
    	html.push('</td>');
    	html.push('</tr>');
    	html.push('</tbody>');
    	html.push('</table>');
		$("#mchtBrandAptitudeDiv").append(html.join(""));
    	imageUploader.uploader.addButton({
    	    id: '#'+buttonId,
    	});
    	
    	$('#'+aptitudeExpDateId).datetimepicker(
        		{
        			minView: "month", //选择日期后，不会再跳转去选择时分秒
    				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
                    language: 'zh-CN', //汉化
                    autoclose:true, //选择日期后自动关闭
                    pickerPosition: "top-right"
        	    }
        );
    });
    
    $("#addBrandProductType").on('click',function(){
    	$("#brandProductTypeDiv").show();
    	$("#blackBox").show();
    });
    
    $(".video_close").on('click',function(){
    	$("#brandProductTypeDiv").hide();
    	$("#viewAptitudeDiv").hide();
    	$("#blackBox").hide();
    });
    
    $("#cancle").on('click',function(){
    	$("#brandProductTypeDiv").hide();
    	$("#viewAptitudeDiv").hide();
    	$("#blackBox").hide();
    });
    
    getProductTypeList("productType1",1);
    
    $("#commit").on('click',function(){
    	var productType1Id = $("#productType1").val();
    	var existsProductType1Id = $("#brandProductTypeTable").find("tbody").find("tr").first().find("td").eq(0).attr("productType1Id");
    	if(existsProductType1Id && productType1Id != existsProductType1Id){
    		swal({
				  title: "只能选择一种一级类目!",
				  type: "error",
				  confirmButtonText: "确定"
				});
    		return;
    	}
    	var productType1Name = $("#productType1").find("option:selected").text();
    	var productType2Id = $("#productType2").val();
    	var productType2Name = $("#productType2").find("option:selected").text();
    	var productTypeIds = "";
    	var productTypeNames = "";
    	var existsProductTypeIds = "";
    	$("#brandProductTypeTable").find("tbody").find("tr").each(function(){
    		existsProductTypeIds+=$(this).find("td").eq(2).attr("producttypeIds")+",";
    	});
    	var hasExists = false;
    	$("#productTypeId").find("option:selected").each(function(index){
    		var productTypeId = $(this).val();
    		var idx = existsProductTypeIds.indexOf(productTypeId);
    		if(idx == -1){
	    		if(index!=$("#productTypeId").find("option:selected").length-1){
	    			productTypeIds+= productTypeId+",";
	    			productTypeNames+= $(this).text()+"<br>";
	    		}else{
	    			productTypeIds+= productTypeId;
	    			productTypeNames+= $(this).text();
	    		}
    		}else{
    			hasExists = true;    			
    			return;
    		}
    	});
    	if(hasExists){
    		swal({
				  title: "三级类目已存在，请重新选择",
				  type: "error",
				  confirmButtonText: "确定"
				});
			return;
    	}
    	if(!productTypeIds){
    		swal({
				  title: "请选择三级类目!",
				  type: "error",
				  confirmButtonText: "确定"
				});
  			return;
    	}
    	var existsProductType2Ids = [];
    	$("#brandProductTypeTable").find("tbody").find("tr").each(function(){
    		existsProductType2Ids.push($(this).find("td").eq(1).attr("productType2Id"));
    	});
    	var idx = $.inArray(productType2Id,existsProductType2Ids);
    	if(idx == -1){
	    	var html = '<tr><td productType1Id="'+productType1Id+'">'+productType1Name+'</td><td productType2Id="'+productType2Id+'">'+productType2Name+'</td><td productTypeIds="'+productTypeIds+'">'+productTypeNames+'</td><td><a href="javascript:;" onclick="delMchtBrandProductType(this);">删除</a></td></tr>';
	    	$("#brandProductTypeTable").find("tbody").append(html);
    	}else{
    		var $td = $("#brandProductTypeTable").find("tbody").find("tr").eq(idx).find("td").eq(2);
    		$td.attr("productTypeIds",$td.attr("productTypeIds")+","+productTypeIds);
    		$td.append("<br>"+productTypeNames);
    	}
    	$("#brandProductTypeDiv").hide();
    	$("#blackBox").hide();
    	$("#brandProductTypeTable").show();
    });
});

function delMchtBrandProductType(_this){
	$(_this).closest("tr").remove();
}

function delMchtBrandAptitude(_this){
	$(_this).closest("table").remove();
}

function getProductTypeList(selectId,parentId){
	var sel = $("#"+selectId);
	if(parentId==""){
		sel.empty();
		return;
	}
	$.ajax({
		url : "${ctx}/mcht/getProductTypes",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {parentId:parentId},
		timeout : 30000,
		success : function(data) {
			if (data.returnCode=="0000") {
				sel.empty();
				$.each(data.returnData, function(index, item) {
					sel.append("<option value=\"" + item.id + "\">" + item.name + "</option>");
				});
			} else {
				swal({
					  title: data.returnMsg,
					  type: "error",
					  confirmButtonText: "确定"
					});
			}
			
		},
		error : function() {
			swal({
				  title: "请求失败！",
				  type: "error",
				  confirmButtonText: "确定"
				});
		}
	});
	
}

function commitAudit(){
	if(dataFromValidate.form()){
		var $logo=$("#logo").parent().children("img");
		if($logo.length <= 0){
				swal({
					  title: '请上传品牌logo',
					  type: "error",
					  confirmButtonText: "确定"
					});
			return;
		}
		if($logo.length > 0 && $logo.attr("src") != "" && $logo.attr("src").indexOf("data:image") == 0){//有修改
			uploadImage("logoFile",$("#logo"));
		}
		show_waitMe();
		
		var uploadAptitudePictures = true;
		$("ul[name='aptitude-pictures-list']").each(function(){
			if($(this).children("li").length == 0){
				swal({
					  title: '请上传商标注册证或受理通知书',
					  type: "error",
					  confirmButtonText: "确定"
					});
				hide_waitMe();
				uploadAptitudePictures = false;
				return;
			}
			
			if($(this).children("li").length > 100){
				swal({
					  title: '请上传商标注册证或受理通知书，最多100张图片',
					  type: "error",
					  confirmButtonText: "确定"
					});
				hide_waitMe();
				uploadAptitudePictures = false;
				return;
			}
		});
		
		if(!uploadAptitudePictures){
			return;
		}
		
		if($("#platformAuth-pictures-list").children("li").length == 0) {
			swal({
				  title: '请上传销售授权书',
				  type: "error",
				  confirmButtonText: "确定"
				});
			hide_waitMe();
			return;
		}
		if($("#platformAuth-pictures-list").children("li").length > 15) {
			swal({
				  title: '请上传销售授权书，最多15张图片',
				  type: "error",
				  confirmButtonText: "确定"
				});
			hide_waitMe();
			return;
		}
		if($("#invoice_pictures-list").children("li").length > 10) {
			swal({
				  title: '请上传进货发票，最多10张图片',
				  type: "error",
				  confirmButtonText: "确定"
				});
			hide_waitMe();
			return;
		}
		if($("#inspection-pictures-list").children("li").length == 0) {
			swal({
				  title: '请上传质检报告/检疫报告',
				  type: "error",
				  confirmButtonText: "确定"
				});
			hide_waitMe();
			return;
		}
		if($("#inspection-pictures-list").children("li").length > 100) {
			swal({
				  title: '请上传质检报告/检疫报告，最多100张图片',
				  type: "error",
				  confirmButtonText: "确定"
				});
			hide_waitMe();
			return;
		}
		if($("#other-pictures-list").children("li").length > 50) {
			swal({
				  title: '请上传其它资质，最多50张图片',
				  type: "error",
				  confirmButtonText: "确定"
				});
			hide_waitMe();
			return;
		}
		
		if($("#brandProductTypeTable").find("tbody").find("tr").length == 0){
			swal({
				  title: '请添加品牌经营类目',
				  type: "error",
				  confirmButtonText: "确定"
				});
			hide_waitMe();
			return;
		}
		
		imageUploader.upload(function(){
			var mchtBrandAptitudeArray = [];
			var aptitudeChecked = true;
			$("ul[name='aptitude-pictures-list']").each(function(){
				var $tr = $(this).closest("tr");
				var eachCertificateNo = $tr.prev().find("input[name='certificateNo']").first().val();
				var eachAptitudeExpDate = $tr.next().find("input[name='aptitudeExpDate']").first().val();
				if(!eachCertificateNo){
					aptitudeChecked = false;
					swal("商标注册证号不能为空");
					return;
				}
				if(!eachAptitudeExpDate){
					aptitudeChecked = false;
					swal("商标注册证有效期不能为空");
					return;
				}
				var aptitudePicArray = [];
				$(this).children("li").each(function(index,element){
					var pic = {};
			    	pic.pic = $(element).attr("pic_path");
			    	aptitudePicArray.push(pic);
				});
				if(aptitudePicArray.length == 0){
					aptitudeChecked = false;
					swal("请上传商标注册证相关文件");
					return;
				}
				var eachMchtBrandAptitudeId = $(this).closest("table").attr("mchtBrandAptitudeId");
				var obj ={
					"eachMchtBrandAptitudeId":eachMchtBrandAptitudeId,
					"eachCertificateNo":eachCertificateNo,
					"eachAptitudePics":JSON.stringify(aptitudePicArray),
					"eachAptitudeExpDate":eachAptitudeExpDate
				};
				mchtBrandAptitudeArray.push(obj);
			});
			if(!aptitudeChecked){
				return;
			}
			var mchtBrandProductTypeArray = [];
			$("#brandProductTypeTable").find("tbody").find("tr").each(function(){
				var productType1Id = $(this).find("td").eq(0).attr("productType1Id");
				var productType2Id = $(this).find("td").eq(1).attr("productType2Id");
				var productTypeIds = $(this).find("td").eq(2).attr("productTypeIds");
				mchtBrandProductTypeArray.push({
		    		"productTypeId":productType1Id,
		    		"tLevel":1
		    	});
				mchtBrandProductTypeArray.push({
		    		"productTypeId":productType2Id,
		    		"tLevel":2
		    	});
				mchtBrandProductTypeArray.push({
		       		"productTypeId":productTypeIds,
		       		"tLevel":3
		       	});
			});
			
			var platformAuthPics = [];
			$("#platformAuth-pictures-list").children("li").each(function(index,element){
				var pic = {};
		    	pic.pic = $(element).attr("pic_path");
		    	platformAuthPics.push(pic);
			});
			if(platformAuthPics.length == 0){
				swal("请上传销售授权书");
				return;
			}
			var invoicePics = []; 
			$("#invoice_pictures-list").children("li").each(function(index,element){
				var pic = {};
		    	pic.pic = $(element).attr("pic_path");
		    	invoicePics.push(pic);
			});
			var inspectionPics = []; 
			$("#inspection-pictures-list").children("li").each(function(index,element){
				var pic = {};
		    	pic.pic = $(element).attr("pic_path");
		    	inspectionPics.push(pic);
			});
			if(inspectionPics.length == 0){
				swal("请上传质检报告/检疫报告");
				return;
			}
			var otherPics = [];
			$("#other-pictures-list").children("li").each(function(index,element){
				var pic = {};
		    	pic.pic = $(element).attr("pic_path");
		    	otherPics.push(pic);
			});
			
// 			var mchtBrandChangeAgreementPics = [];
// 			$("#agreement-pictures-list").children("li").each(function(index,element){
// 				var pic = {};
// 		    	pic.pic = $(element).attr("pic_path");
// 		    	mchtBrandChangeAgreementPics.push(pic);
// 			}); 
			
			var dataJson = $("#dataFrom").serializeArray();
			dataJson.push({"name":"mchtBrandProductTypeJsonStr","value":JSON.stringify(mchtBrandProductTypeArray)});
			dataJson.push({"name":"mchtBrandAptitudeJsonStr","value":JSON.stringify(mchtBrandAptitudeArray)});
			dataJson.push({"name":"platformAuthPics","value":JSON.stringify(platformAuthPics)});
			dataJson.push({"name":"invoicePics","value":JSON.stringify(invoicePics)});
			dataJson.push({"name":"inspectionPics","value":JSON.stringify(inspectionPics)});
			dataJson.push({"name":"otherPics","value":JSON.stringify(otherPics)});
//			dataJson.push({"name":"mchtBrandChangeAgreementPics","value":JSON.stringify(mchtBrandChangeAgreementPics)});

			$.ajax({
				url : "${ctx}/mcht/mchtBrandPerfectCommitAudit",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : dataJson,
				timeout : 30000,
				success : function(data) {
					hide_waitMe();
					if (data.returnCode=="0000") {
						$("#viewModal").modal('hide');
						swal({
							  title: "提交成功!",
							  type: "success",
							  confirmButtonText: "确定"
							  
							});
						window.parent.location.reload();
					} else {
						swal({
							  title: data.returnMsg,
							  type: "error",
							  confirmButtonText: "确定"
							});
					}
				},
				error : function() {
					hide_waitMe();
					swal({
						  title: "提交失败！",
						  type: "error",
						  timer: 1500,
						  confirmButtonText: "确定"
						});
				}
			});
		});

	}
}


function loadImageFile(obj) {
	if (obj.files.length === 0) {
		return;
	}
	var oFile = obj.files[0];
// 	var rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
	var rFilter = /^(?:image\/jpeg|image\/png)$/i;
	if (!rFilter.test(oFile.type)) {
		swal("图片格式不正确！");
		return;
	}
	var reader = new FileReader();  
    reader.onload = function(e) { 
    	var image = new Image();
    	image.onload = function() {
	    	var imgWidth = '200';
	    	var imgHeight = '100';
	    	if(this.width != imgWidth || this.height != imgHeight) {
	    		swal("图片像素不是"+imgWidth+"*"+imgHeight+"px");
	    		return;
	    	}else {
	    		if($(obj).parent().children("img").length<=0){
	    	    	$('<img>').appendTo( $(obj).parent() );;
	    	    }
    			$(obj).parent().children("img").attr("src",e.target.result);
    			$(obj).parent().children("div").remove();
	    	}
        };
        image.src = e.target.result;
    }
    reader.readAsDataURL(oFile); 
}

//上传图片
function uploadImage(fileElementId,$valueFiled) {
	var oFile = document.getElementById(fileElementId).files[0];
	var formData = new FormData();
	formData.append("file",oFile);
	$.ajax({ 
		url : "${ctx}/common/fileUpload", 
		type : 'POST', 
		data : formData, 
		async: false,
		// 告诉jQuery不要去处理发送的数据
		processData : false, 
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,
		beforeSend:function(){
			console.log("图片片上传中，请稍候");
		},
		success : function(data) {
            if (data.returnCode=="0000") {
                $valueFiled.val(data.returnData);
            } else {
                swal({
                    title: "图片上传失败！",
                    type: "error",
                    confirmButtonText: "确定"
                });
            }
		}, 
		error : function(responseStr) { 
			swal("图片上传失败");
		} 
		});
}
function toViewAptitude(){
	$("#viewAptitudeDiv").show();
	$("#blackBox").show();
}
</script>
</body>
</html>
