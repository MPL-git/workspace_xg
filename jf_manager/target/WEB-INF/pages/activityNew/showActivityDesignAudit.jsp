<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
	
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
 	.radioClass{
		margin-right: 20px;
	}
 
 </style>
<script type="text/javascript">
	var posterPic;
	var viewerPictures;
	var entryPic = '${activityNewCustom.entryPic}';
	var brandteamPic;
	$(function(){
		posterPic = new Viewer(document.getElementById('posterPic'), {});
		brandteamPic = new Viewer(document.getElementById('brandteamPic'), {});
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    		$("#viewer-pictures").hide();
    	}});
	});
	
	//审核流水表
	function activityAuditLogList(activityId) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width() - 200,
			title: "审核进度",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityAuditLog/activityAuditLogList.shtml?activityId=" + activityId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//查看驳回详情
	function seeDesignReject(activityId) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width() - 200,
			title: "查看设计驳回详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityNew/seeDesignReject.shtml?activityId=" + activityId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//合成图片 水印
	function updateActivityGroup(activityId, isSign) {
		if(isSign == '0') {
			isSign = '1';
		}else {
			isSign = '0';
		}
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/activityNew/updateActivityGroupId.shtml',
			data: {activityId : activityId, isSign : isSign},
			dataType: 'json',
			success: function(data) {
				if(data.code != '200') {
					commUtil.alertError(data.msg);
				}else {
					var strGroupName = '';
					var strHref = '';
					if(data.isSignStr == '0') {   
						strGroupName = '【加标】';
						strHref = 'javascript:updateActivityGroup('+activityId+', 0);';
					}else {
						strGroupName = '【还原】';
						strHref = 'javascript:updateActivityGroup('+activityId+', 1);';
					}
					$("#entryPicGroupName").html(strGroupName);
					$("#entryPicGroupName").attr("href", strHref);
					
					$("#entryPicImg").attr("src",contextPath + "/file_servelt"+data.entryPicStr);
					entryPic = data.entryPicStr;
				}
			},
			error: function(e) {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
	
	//合成图片 水印（后期扩展）
	function updateActivityGroupCustom(activityId, isSign) {
		if(isSign == '0') {
			isSign = '1';
		}else {
			isSign = '0';
		}
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/activityNew/updateActivityGroupIdCustom.shtml',
			data: {activityId : activityId, isSign : isSign},
			dataType: 'json',
			success: function(data) {
				if(data.code != '200') {
					commUtil.alertError(data.msg);
				}else {
					var strGroupName = '';
					var strHref = '';
					if(data.isSignStr == '0') {   
						strGroupName = '【加标】';
						strHref = 'javascript:updateActivityGroupCustom('+activityId+', 0);';
					}else {
						strGroupName = '【还原】';
						strHref = 'javascript:updateActivityGroupCustom('+activityId+', 1);';
					}
					$("#entryPicGroupNameCustom").html(strGroupName);
					$("#entryPicGroupNameCustom").attr("href", strHref);
					
					$("#entryPicImg").attr("src",contextPath + "/file_servelt"+data.entryPicStr);
					entryPic = data.entryPicStr;
				}
			},
			error: function(e) {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
	
	//图片查看
	function viewerPic(){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+entryPic+'" src="${pageContext.request.contextPath}/file_servelt'+entryPic+'" alt=""></li>');
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		$("#viewer-pictures").show();
		viewerPictures.show();
	}

</script>

</head>
	<body style="margin: 10px;">
	<form name="form1" class="form1" method="post" id="form1" enctype="multipart/form-data">
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">活动名称</td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:260px;" type="text" value="${activityNewCustom.name }" disabled="disabled" >
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">类目</td>
				<td align="left" class="l-table-edit-td" >
					<select style="width: 135px;" disabled="disabled"  >
						<option value="">请选择...</option>
						<c:forEach var="productType" items="${productTypeList }">
							<option value="${productType.id }" <c:if test="${productType.id == activityNewCustom.productTypeId }">selected</c:if>  > 
								${productType.name }
							</option>
						</c:forEach>
					</select>
					<span style="margin-left: 20px;" >
						<select style="width: 135px;" id="productTypeSecondId" disabled="disabled" name="productTypeSecondId" >
							<option value="">请选择...</option>
							<c:forEach var="productTypeSecond" items="${productTypeSecondList }">
								<option value="${productTypeSecond.id }" <c:if test="${productTypeSecond.id == activityNewCustom.productTypeSecondId }">selected</c:if>  > 
									${productTypeSecond.name }
								</option>
							</c:forEach>
						</select>
					</span>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">品牌</td>
				<td align="left" class="l-table-edit-td" >
					<select style="width: 135px;" disabled="disabled"  >
						<option value=""></option>
						<c:forEach var="productBrand" items="${productBrandList }">
							<option value="${productBrand.id }" <c:if test="${productBrand.id == activityNewCustom.productBrandId }">selected</c:if>  > 
								${productBrand.name }
							</option>
						</c:forEach>
					</select>
					<span style="margin-left: 20px;">
						品牌限制：
						<span class="radioClass"><input type="radio" disabled="disabled" name="brandLimitType" value="1" <c:if test="${activityNewCustom.brandLimitType == '1' }">checked</c:if> />品牌专场</span>
						<span class="radioClass"><input type="radio" disabled="disabled" name="brandLimitType" value="2" <c:if test="${activityNewCustom.brandLimitType == '2' }">checked</c:if> />多品牌联合</span>
					</span>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">利益点</td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:260px;" type="text" value="${activityNewCustom.benefitPoint }" disabled="disabled" >
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">期望活动时间</td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:135px;" type="text" value='<fmt:formatDate value="${activityNewCustom.expectedStartTime }" pattern="yyyy-MM-dd"/>' disabled="disabled" >
				</td>
           	</tr>
           	<tr height="220px;">
            	<td class="title" width="20%">品牌团入口图<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<div style="width: 400px;height: 200px;border: 1px solid #6B6B6B;">
						<ul class="docs-pictures clearfix td-pictures" id="brandteamPic">
							<li><img src="${pageContext.request.contextPath}/file_servelt${activityNewCustom.brandteamPic}" style="width: 400px;height: 200px;display: block;" ></li>
				 		</ul>
					</div>
			 		<span style="color: #6B6B6B;">图片的格式为：.JPG，尺寸要求：1080x335像素。</span>
				</td>
           	</tr>
           	<tr height="220px;">
            	<td class="title" width="20%">入口图</td>
				<td align="left" class="l-table-edit-td">
					<div style="width: 400px;height: 200px;border: 1px solid #6B6B6B;">
						<img id="entryPicImg" style="width: 400px;height: 200px;display: block;" src="${pageContext.request.contextPath}/file_servelt${activityNewCustom.entryPic}" onclick="viewerPic()" >
					</div>
			 		<span style="color: #6B6B6B;">图片的格式为：.JPG，尺寸要求：800x400像素。</span>
			 		<c:if test="${not empty activityNewCustom.activityAreaActivityGroupId }">
			 			<c:if test="${activityNewCustom.source == '2' }">
	 						<a href="javascript:updateActivityGroup(${activityNewCustom.id }, ${activityNewCustom.activityAreaIsSign });" id="entryPicGroupName"  >
	 							<c:if test="${activityNewCustom.activityAreaIsSign == '0' }">【加标】</c:if>
	 							<c:if test="${activityNewCustom.activityAreaIsSign != '0' }">【还原】</c:if>
	 						</a>
			 			</c:if>
			 			<c:if test="${activityNewCustom.source == '1' }">
	 						<a href="javascript:updateActivityGroupCustom(${activityNewCustom.id }, ${activityNewCustom.isSign });" id="entryPicGroupNameCustom"  >
	 							<c:if test="${activityNewCustom.isSign == '0' }">【加标】</c:if>
	 							<c:if test="${activityNewCustom.isSign != '0' }">【还原】</c:if>
	 						</a>
			 			</c:if>
	 				</c:if>
				</td>
           	</tr>
           	<tr height="220px;">
            	<td class="title" width="20%">头部海报图</td>
				<td align="left" class="l-table-edit-td">
					<div style="width: 400px;height: 200px;border: 1px solid #6B6B6B;">
						<ul class="docs-pictures clearfix td-pictures" id="posterPic">
							<li><img src="${pageContext.request.contextPath}/file_servelt${activityNewCustom.posterPic}" style="width: 400px;height: 200px;display: block;" ></li>
				 		</ul>
					</div>
			 		<span style="color: #6B6B6B;">图片的格式为：.JPG，尺寸要求：800x400像素。</span>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">促销方式</td>
				<td align="left" class="l-table-edit-td">
					<table>
						<!-- 优惠券 -->
						<c:if test="${activityNewCustom.preferentialType == 1 }">
							<c:forEach items="${cuponList }" var="cupon" varStatus="ind">
								<c:if test="${ind.index == 0 }">
									<tr>
										<td style="border:none">${activityNewCustom.preferentialTypeDesc }：</td>
										<td style="border:none">
											面额&nbsp;<input type="text" disabled="disabled" style="width: 50px;" value="${cupon.money}">元&nbsp; &nbsp;使用条件&nbsp;<input type="text" disabled="disabled" style="width: 50px;" value="${cupon.minimum}"/>元&nbsp; &nbsp;发行量&nbsp;<input type="text" disabled="disabled" style="width: 50px;" value="${cupon.grantQuantity}"/>张&nbsp;（ID：${cupon.id}）
										</td>
									</tr>
								</c:if>
								<c:if test="${ind.index != 0 }">
									<tr>
										<td style="border:none"></td>
										<td style="border:none">
											面额&nbsp;<input type="text" disabled="disabled" style="width: 50px;" value="${cupon.money}">元&nbsp; &nbsp;使用条件&nbsp;<input type="text" disabled="disabled" style="width: 50px;" value="${cupon.minimum}"/>元&nbsp; &nbsp;发行量&nbsp;<input type="text" disabled="disabled" style="width: 50px;" value="${cupon.grantQuantity}"/>张&nbsp;（ID：${cupon.id}）
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:if>
						
						<!-- 满减 -->
						<c:if test="${activityNewCustom.preferentialType == 2 }">
							<tr>
								<td style="border:none">${activityNewCustom.preferentialTypeDesc }：</td>
								<td style="border:none">
									<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" <c:if test="${fullCutCustom.ladderFlag == 0}">checked</c:if> >非阶梯</span>
									<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" <c:if test="${fullCutCustom.ladderFlag == 1}">checked</c:if> >阶梯</span>
								</td>
							</tr>
							<c:if test="${fullCutCustom.ladderFlag == 0 }"><!-- 非阶梯 -->
								<tr>
									<td style="border:none"></td>
									<td style="border:none">
										满&nbsp;<input style="width: 50px;" type="text" disabled="disabled" value="${fullCutCustom.full}"/>&nbsp;元&nbsp;&nbsp;减&nbsp;<input style="width: 50px;" type="text" disabled="disabled" value="${fullCutCustom.cut}"/>元&nbsp;&nbsp;<input type="checkbox" disabled="disabled" <c:if test="${fullCutCustom.sumFlag == 1}">checked</c:if> />累加
									</td>
								</tr>
							</c:if>
							<c:if test="${fullCutCustom.ladderFlag == 1 }"><!-- 阶梯 -->
								<c:forEach items="${fullCutList }" var="fullCut" varStatus="ind">
									<td style="border:none"></td>
									<td style="border:none">
										满&nbsp;<input style="width: 50px;" type="text" disabled="disabled" value="${fullCut.full}"/>&nbsp;元&nbsp;&nbsp;减&nbsp;<input style="width: 50px;" type="text" disabled="disabled" value="${fullCut.cut}"/>元
									</td>
								</c:forEach>
							</c:if>
						</c:if>
						
						<!-- 满赠 -->
						<c:if test="${activityNewCustom.preferentialType == 3 }">
							<tr>
								<td style="border:none">${activityNewCustom.preferentialTypeDesc }：</td>
								<td style="border:none">
									<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" <c:if test="${fullGive.type == 1}">checked</c:if> >满额赠</span>
									<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" <c:if test="${fullGive.type == 2}">checked</c:if> >买即赠</span>
								</td>
							</tr>
							<c:if test="${fullGive.type == 1}">
								<tr>
									<td style="border:none"></td>
									<td style="border:none">
										满额：&nbsp;满&nbsp;<input type="text" style="width: 50px;" disabled="disabled" value="${fullGive.minimum}"/>&nbsp;元&nbsp;&nbsp;<input type="checkbox" disabled="disabled" value="" <c:if test="${fullGive.sumFlag == 1}">checked</c:if> />累加
									</td>
								</tr>
							</c:if>
							<tr>
								<td style="border:none"></td>
								<td style="border:none">
									赠品ID:<input type="text" style="width: 50px;" disabled="disabled" value="${fullGive.productId}"/><a href="javascript:;" onclick="viewProduct(${fullGive.productId});">【${product.name}】</a>
								</td>
							</tr>
							<tr>
								<td style="border:none"></td>
								<td style="border:none">
									数量:<input type="text" style="width: 50px;" disabled="disabled" value="${fullGive.productNum}"/>
								</td>
							</tr>
						</c:if>
						
						<!-- 多买优惠 -->
						<c:if test="${activityNewCustom.preferentialType == 4 }">
							<tr>
								<td style="border:none">${activityNewCustom.preferentialTypeDesc }：</td>
								<td style="border:none">
									<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" <c:if test="${fullDiscountType == 1}">checked</c:if> >满M件减N件</span>
									<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" <c:if test="${fullDiscountType == 2}">checked</c:if> >M件N元</span>
									<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" <c:if test="${fullDiscountType == 3}">checked</c:if> >M件N折</span>
								</td>
							</tr>
							<c:if test="${fullDiscountType == 1 }">
								<td style="border:none"></td>
								<tr>
									<td style="border:none">
										&nbsp;<input type="text" disabled="disabled" style="width: 50px;" value="${fullDiscountMap.fullOfOne}"/>&nbsp;件&nbsp;&nbsp;减&nbsp;<input type="text" style="width: 50px;" disabled="disabled" value="${fullDiscountMap.fullGiftsOneName}"/>件
									</td>
								</tr>
							</c:if>
							<c:if test="${fullDiscountType == 2 }">
								<td style="border:none"></td>
								<tr>
									<td style="border:none">
										&nbsp;<input type="text" disabled="disabled" style="width: 50px;" value="${fullDiscountMap.fullOfTwo}"/>&nbsp;件&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" style="width: 50px;" disabled="disabled" value="${fullDiscountMap.fullGiftsTwoName}"/>元
									</td>
								</tr>
							</c:if>	
							<c:if test="${fullDiscountType == 3 }">
								<c:forEach items="${fullDiscountList}" var="list">
									<tr>
										<td style="border:none"></td>
										<td style="border:none">
											&nbsp;<input type="text" disabled="disabled" style="width: 50px;" value="${list.fullGiscountThree}"/>&nbsp;件&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" style="width: 50px;" disabled="disabled" value="${list.fullGiscountThreeName}"/>折
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</c:if>
					</table>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">排期</td>
				<td align="left" class="l-table-edit-td">
					<fmt:formatDate value="${activityNewCustom.activityBeginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					<c:if test="${not empty activityNewCustom.activityBeginTime or not empty activityNewCustom.activityEndTime }">到</c:if>
					<fmt:formatDate value="${activityNewCustom.activityEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">设计审核状态</td>
				<td align="left" class="l-table-edit-td">
					${activityNewCustom.designAuditStatusDesc }
					<span style="margin-left: 10px;">
						<c:if test="${activityNewCustom.designAuditStatus != '3' }">
							<a href="javascript:activityAuditLogList(${activityNewCustom.id})">【查看审核日志】</a>
						</c:if>
						<c:if test="${activityNewCustom.designAuditStatus == '3' }">
							<a href="javascript:seeDesignReject(${activityNewCustom.id})">【查看驳回详情】</a>
						</c:if>
<!-- 							<a href="javascript:seeDesignReject(${activityNewCustom.id})">【查看驳回详情】</a> -->
					</span>
				</td>
           	</tr>
           	<c:if test="${statusAudit == '3' and activityNewCustom.designAuditStatus == '3' }">
           		<tr>
	            	<td class="title" width="20%">设计驳回理由</td>
					<td align="left" class="l-table-edit-td">
						${activityNewCustom.designAuditRemarks }
					</td>
	           	</tr>
           	</c:if>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	</body>
</html>