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
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'),
		{
			hide : function() {
				$("#viewer-pictures").hide();
			}
		});
		$("#viewer-pictures").show();
		viewerPictures.show();
	}


	function updateProductItem(value) {
		   var productid=$("input:[name='id']").val();
		   var cooauditStatus="";
		   if (value=='1') {
			   cooauditStatus=1;
		   }else if (value=='2') {
			   cooauditStatus=2;
		  } 
		   var flag = true;
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
					data : {mallPrice : mallPrice,salePrice : salePrice,cooauditStatus:cooauditStatus,productid:productid},
					success : function(data) {
						if ("0000" == data.returnCode) {
							commUtil.alertSuccess("修改成功！");
							parent.location.reload();
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

</script>
<html>
<body>
	<form method="post" id="form1" name="form1">
		<input type="hidden" name="id" value="${productCustom.id}">
		<input type="hidden" name="mallPrice" value="">
		<input type="hidden" name="salePrice" value="">
		<table class="gridtable">
			<tr>
				<td class="title" width="140px;">SKU图</td>
				<c:forEach var="productProp" items="${productProps}">
					<td class="title">${productProp.name}</td>
				</c:forEach>
				<td class="title">吊牌价</td>
				<td class="title">商城价</td>
				<td class="title">活动价</td>
				<td class="title">结算价</td>
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
					     <input id="${productItem.id}-mallPrice" class="mallPrice" mall-price-id="${productItem.id}" type="text" value="${productItem.mallPrice}" style="width: 100px;" />
					     <c:if test="${ind.index == 0}">
								<br/><a href="javascript:void(0);" onclick="allChangeMallPrice(${productItem.id});">批量</a>
						 </c:if>
					</td>
					<td class="l-table-edit-td center-align">					
						 <input id="${productItem.id}-salePrice" class="salePrice" sale-price-id="${productItem.id}" type="text" value="${productItem.salePrice}" style="width: 100px;" />
						 <c:if test="${ind.index == 0}">
						  <br/><a href="javascript:void(0);" onclick="allChangeSalePrice(${productItem.id});">批量</a>
						 </c:if>
					</td>
					
				   <td id="${productItem.id}-costPrice" class="l-table-edit-td center-align">${productItem.costPrice}</td>
					<td class="l-table-edit-td center-align">${productItem.lockedAmount}</td>
					<td class="l-table-edit-td center-align">${productItem.stock}</td>
					<td class="l-table-edit-td center-align">${productItem.sku}</td>
				</tr>
			</c:forEach>
		</table>
		<br/>		 
			  
			  <div align="left" class="l-table-edit-td" >
					<input name="btnSubmit"  type="button" class="l-button l-button-submit" value="保存" style="margin-left: 418px;" onclick="updateProductItem(1);"/> 
					<input style="margin-left: 420;"  type="button"  class="l-button l-button-submit" value="保存并通过" onclick="updateProductItem(2);" />
			  </div>
		<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures"
			style="display: none;">
		</ul>
	</form>
</body>
</html>
