<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link
	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script
	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/common/js/uploadImageList.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/css/upload_image_list.css"
	rel="stylesheet" type="text/css" />
<script
	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js"
	type="text/javascript">
</script>
<link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

.radioClass {
	margin-right: 20px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}

.auditFix {
	position: fixed;
	bottom: 0;
	left: 0;
	width: 100%;
	_position: absolute;
}
</style>
<style type="text/css">
.middle input {
	display: block;
	width: 30px;
	margin: 2px;
}

table.l-checkboxlist-table td {
	border-style: none;
}

table.l-listbox-table td {
	border-style: none;
}

.td-pictures li {
	display: inline-block;
}

.td-pictures li img {
	width: 100px;
	height: 100px;
}

.table-title {
	font-size: 17px;
	font-weight: 600;
}

.center-align {
	text-align: center;
}
</style>
<script type="text/javascript">
	var viewerPictures;
	$(function() {
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'),
				{
					hide : function() {
						$("#viewer-pictures").hide();
					}
				});
	});

	function viewerPic(imgPath) {
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		imgPath = imgPath.replace('_S', '');
		$("#viewer-pictures")
				.append(
						'<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'),
				{
					hide : function() {
						$("#viewer-pictures").hide();
					}
				});
		$("#viewer-pictures").show();
		viewerPictures.show();
	}

	function submit_fun() {
		var flag = true;
		if('${productCustom.mchtType}' == '1' ) {
			var mallPrice = '';
			$(".mallPrice").each(function(){
				if(!/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test($(this).val()) ) {
					commUtil.alertError("请输入正确格式的<span style='color: red;'>商城价</span>！");
					flag = false;
					return false;
				}
				if(Number($(this).val()) > Number($("#"+$(this).attr("mall-price-id")+"-tagPrice").html()) ) {
					commUtil.alertError("<span style='color: red;'>商城价</span>不能大于<span style='color: red;'>吊牌价</span>！");
					flag = false;
					return false;
				}
				if(mallPrice == '' ) {
					mallPrice = $(this).attr("mall-price-id")+","+$(this).val();
				}else {
					mallPrice += "-"+$(this).attr("mall-price-id")+","+$(this).val();
				}
			});
			if(!flag) {
				return;
			}
			$("[name='mallPrice']").val(mallPrice);
			var salePrice = '';
			$(".salePrice").each(function(){
				if(!/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test($(this).val()) ) {
					commUtil.alertError("请输入正确格式的<span style='color: red;'>活动价</span>！");
					flag = false;
					return false;
				}
				if(Number($(this).val()) > Number($("#"+$(this).attr("sale-price-id")+"-mallPrice").val()) ) {
					commUtil.alertError("<span style='color: red;'>活动价</span>不能大于<span style='color: red;'>商城价</span>！");
					flag = false;
					return false;
				}
				if(Number($(this).val()) < Number($("#"+$(this).attr("sale-price-id")+"-costPrice").html()) ) {
					commUtil.alertError("<span style='color: red;'>活动价</span>不能小于<span style='color: red;'>结算价</span>！");
					flag = false;
					return false;
				}
				if(salePrice == '' ) {
					salePrice = $(this).attr("sale-price-id")+","+$(this).val();
				}else {
					salePrice += "-"+$(this).attr("sale-price-id")+","+$(this).val();
				}
			});
			$("[name='salePrice']").val(salePrice);
		}
		if(flag) {
			$.ajax({
				url : "${pageContext.request.contextPath}/product/lawAuditSubmit.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : $("#form1").serialize(),
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						parent.$("#searchbtn").click();
						frameElement.dialog.close();
					} else {
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
	}

	function allChangeMallPrice(productItemId) {
		var mallPrice = $("#"+productItemId+"-mallPrice").val();
		if(!/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test(mallPrice) ) {
			commUtil.alertError("请输入正确格式的<span style='color: red;'>商城价</span>！");
			return;
		}
		$(".mallPrice").each(function(){
			$(this).val(mallPrice);
		});
	}
	function allChangeSalePrice(productItemId) {
		var salePrice = $("#"+productItemId+"-salePrice").val();
		if(!/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test(salePrice) ) {
			commUtil.alertError("请输入正确格式的<span style='color: red;'>活动价</span>！");
			return;
		}
		$(".salePrice").each(function(){
			$(this).val(salePrice);
		});
	}

	function updateProductItem() {
		var flag = true;
		if('${productCustom.mchtType}' == '1' ) {
			var mallPrice = '';
			$(".mallPrice").each(function(){
				if(!/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test($(this).val()) ) {
					commUtil.alertError("请输入正确格式的<span style='color: red;'>商城价</span>！");
					flag = false;
					return false;
				}
				if(Number($(this).val()) > Number($("#"+$(this).attr("mall-price-id")+"-tagPrice").html()) ) {
					commUtil.alertError("<span style='color: red;'>商城价</span>不能大于<span style='color: red;'>吊牌价</span>！");
					flag = false;
					return false;
				}
				if(mallPrice == '' ) {
					mallPrice = $(this).attr("mall-price-id")+","+$(this).val();
				}else {
					mallPrice += "-"+$(this).attr("mall-price-id")+","+$(this).val();
				}
			});
			if(!flag) {
				return;
			}
			var salePrice = '';
			$(".salePrice").each(function(){
				if(!/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test($(this).val()) ) {
					commUtil.alertError("请输入正确格式的<span style='color: red;'>活动价</span>！");
					flag = false;
					return false;
				}
				if(Number($(this).val()) > Number($("#"+$(this).attr("sale-price-id")+"-mallPrice").val()) ) {
					commUtil.alertError("<span style='color: red;'>活动价</span>不能大于<span style='color: red;'>商城价</span>！");
					flag = false;
					return false;
				}
				if(Number($(this).val()) < Number($("#"+$(this).attr("sale-price-id")+"-costPrice").html()) ) {
					commUtil.alertError("<span style='color: red;'>活动价</span>不能小于<span style='color: red;'>结算价</span>！");
					flag = false;
					return false;
				}
				if(salePrice == '' ) {
					salePrice = $(this).attr("sale-price-id")+","+$(this).val();
				}else {
					salePrice += "-"+$(this).attr("sale-price-id")+","+$(this).val();
				}
			});
			if(mallPrice == '' || salePrice == '' ) {
				flag = false;
			}
			if(flag) {
				$.ajax({
					url : "${pageContext.request.contextPath}/product/updateProductItem.shtml",
					type : 'POST',
					dataType : 'json',
					data : {mallPrice : mallPrice,salePrice : salePrice},
					success : function(data) {
						if ("0000" == data.returnCode) {
							commUtil.alertSuccess("修改成功！");
						} else {
							$.ligerDialog.error(data.returnMsg);
						}
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			}
		}
	}


</script>
<html>
<body>
	<form method="post" id="form1" name="form1">
		<input type="hidden" name="id" value="${productCustom.id}">
		<input type="hidden" name="mallPrice" value="">
		<input type="hidden" name="salePrice" value="">
		<table class="gridtable">
			<tr>
				<td class="title" width="140px;">商品名称</td>
				<td align="left" class="l-table-edit-td">${productCustom.name}
				</td>
			</tr>
			<tr>
				<td class="title">商品货号</td>
				<td align="left" class="l-table-edit-td">
					${productCustom.artNo}</td>
			</tr>
		</table>
		<br/>
		<div>
			<span class="table-title">商品SKU信息</span>
		</div>
		<table class="gridtable">
			<tr>
				<td class="title" width="140px;">SKU图</td>
				<c:forEach var="productProp" items="${productProps}">
					<td class="title">${productProp.name}</td>
				</c:forEach>
				<td class="title">吊牌价</td>
				<td class="title">商城价</td>
				<td class="title">活动价</td>
				<c:if test="${productCustom.mchtType == 1}">
					<td class="title">结算价</td>
				</c:if>
				<td class="title">冻结数量</td>
				<td class="title">库存数量</td>
				<td class="title">SKU商家编码</td>
			</tr>
			<c:forEach var="productItem" items="${productItems}" varStatus="ind" >
				<tr>
					<td class="l-table-edit-td center-align">
						<img alt="" style="display: inline-block;width: 60px;height: 60px;" src="${pageContext.request.contextPath}/file_servelt${productItem.pic}" onclick='viewerPic(this.src)'>
					</td>
					<c:forEach var="productProp" items="${productProps}">
						<td class="l-table-edit-td center-align">${productItem.propValuesMap[productProp.id]}</td>
					</c:forEach>
					<td id="${productItem.id}-tagPrice" class="l-table-edit-td center-align">${productItem.tagPrice}</td>
					<td class="l-table-edit-td center-align">
						<c:if test="${productCustom.mchtType == '1'}">
							<c:if test="${flag == '1' }">
								<input id="${productItem.id}-mallPrice" class="mallPrice" mall-price-id="${productItem.id}" type="text" value="${productItem.mallPrice}" style="width: 100px;" />
								<c:if test="${ind.index == 0}">
									<br/><a href="javascript:void(0);" onclick="allChangeMallPrice(${productItem.id});">全改</a>
								</c:if>
							</c:if>
							<c:if test="${flag != '1' }">
								${productItem.mallPrice}
							</c:if>
						</c:if>
						<c:if test="${productCustom.mchtType == '2'}">
							${productItem.mallPrice}
						</c:if>
					</td>
					<td class="l-table-edit-td center-align">
						<c:if test="${productCustom.mchtType == '1'}">
							<c:if test="${flag == '1' }">
								<input id="${productItem.id}-salePrice" class="salePrice" sale-price-id="${productItem.id}" type="text" value="${productItem.salePrice}" style="width: 100px;" />
								<c:if test="${ind.index == 0}">
									<br/><a href="javascript:void(0);" onclick="allChangeSalePrice(${productItem.id});">全改</a>
								</c:if>
							</c:if>
							<c:if test="${flag != '1' }">
								${productItem.salePrice}
							</c:if>
						</c:if>
						<c:if test="${productCustom.mchtType == '2'}">
							${productItem.salePrice}
						</c:if>
					</td>
					<c:if test="${productCustom.mchtType == 1}">
						<td id="${productItem.id}-costPrice" class="l-table-edit-td center-align">${productItem.costPrice}</td>
					</c:if>
					<td class="l-table-edit-td center-align">${productItem.lockedAmount}</td>
					<td class="l-table-edit-td center-align">${productItem.stock}</td>
					<td class="l-table-edit-td center-align">${productItem.sku}</td>
				</tr>
			</c:forEach>
		</table>
		<br/>
		<div>
			<span class="table-title">前端预览</span>
		</div>
		<div style="width: 100%;height: 10000px;">
			<iframe src="${mUrl }${productCustom.code }" frameborder="0" scrolling="auto" style="width: 100%;height: 100%;"></iframe>
		</div>
		<div style="height: 110px;"></div>
		<br/>
		<c:if test="${flag != '1'}">
			<table class="gridtable auditFix">
				<tr>
					<td class="title" width="140px;">驳回原因</td>
					<td align="left" class="l-table-edit-td">
						<textarea rows="5" id="auditRemarks" name="auditRemarks" class="text" style="width:100%;height:100%;">${productCustom.auditRemarks}</textarea>
					</td>
				</tr>
				<tr>
					<td class="title">审核状态</td>
					<td align="left" class="l-table-edit-td">
						<c:forEach var="statusItem" items="${auditStatusList }">
							<c:if test="${statusItem.statusValue != 0}">
								<span class="radioClass">
									<input class="radioItem" type="radio" value="${statusItem.statusValue }" name="auditStatus"
										   <c:if test="${productCustom.auditStatus==statusItem.statusValue}">checked="checked"</c:if>>${statusItem.statusDesc}</span>
							</c:if>
						</c:forEach>
						<input name="btnSubmit" id="Button1" style="float:right" class="l-button l-button-submit" value="提交" onclick="submit_fun();" />
					</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${flag == '1'}">
			<div class="auditFix">
				<input name="btnSubmit" id="Button1" style="float:right;margin-bottom: 10px;margin-right: 30px;" class="l-button l-button-submit" value="确认修改" onclick="updateProductItem();" />
			</div>
		</c:if>
		<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures"
			style="display: none;">
		</ul>
	</form>
</body>
</html>
