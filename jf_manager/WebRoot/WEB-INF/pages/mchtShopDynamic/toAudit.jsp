<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

<style type="text/css">
	body {font-size: 12px;padding: 10px;}
	td input,td select{border:1px solid #AECAF0;}
	.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
	.radioClass{margin-right: 20px;}
	.hidden{display:none;}
</style>

<script type="text/javascript">
var submitting;
var viewerPictures; 
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	
	$("input[name='auditStatus']").bind('click',function(){
		var auditStatus = $(this).val();
		if(auditStatus == 1){
			$("#rejectionReasonTr").hide();
		}else{
			$("#rejectionReasonTr").show();
		}
	});
	
	$("#confirm").bind('click',function(){
		if(submitting){
			return;
		}
		var id = ${mchtShopDynamic.id};
		var auditStatus = $("input[name='auditStatus']:checked").val();
		var rejectionReason = $("#rejectionReason").val();
		if(!auditStatus){
			commUtil.alertError("请选择审核结果");
			return;
		}
		if(auditStatus == 2){
			if(!rejectionReason){
				commUtil.alertError("驳回理由不能为空！");
				return;
			}
		}
		submitting = true;
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtShopDynamic/audit.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id,"auditStatus":auditStatus,"rejectionReason":rejectionReason},
			timeout : 30000,
			success : function(data) {
				if(data.returnCode == '0000'){
					submitting = false;
					commUtil.alertSuccess("提交成功");
					setTimeout(function(){
						parent.location.reload();
						frameElement.dialog.close();
					},1000);
				}else{
					submitting = false;
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
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
</script>

<html>
<body>
	<form method="post" id="form1" name="form1">
		<table class="gridtable" style="width:1200px;">
			<tbody>
			<tr>
				<td class="title">动态顶部封面</td>
				<td>
					<div>
						<img style="width: 120px;height: 120px" onclick="viewerPic(this.src);" src="${pageContext.request.contextPath}/file_servelt${mchtShopDynamic.topCover}">
					</div>
				</td>
			</tr>
			<tr>
				<td class="title">动态内容</td>
				<td>
					${mchtShopDynamic.content}
				</td>
			</tr>
			<tr>
				<td class="title">绑定商品</td>
				<td>
					<table class="gridtable">
						<thead>
							<tr>
								<td>商品信息</td>
								<td>商城价</td>
								<td>活动价</td>
								<td>库存</td>
								<td>类型/状态</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${productCustomList}" var="productCustom">
							<tr>
								<td>
									<img style="float:left;margin:10px;" src="${pageContext.request.contextPath}/file_servelt${productCustom.pic}" width="60" height="60" onclick="viewerPic(this.src)">
									<span style="display:block;text-align:left;margin-top:8px;">
										${productCustom.name}<br>
										<span style="color: #A1A1A1;">
											货号：${productCustom.artNo}<br>
											ID：${productCustom.code}&nbsp;&nbsp;&nbsp;&nbsp;<a href="${mUrl}${productCustom.code}" target="_blank">【预览】</a>
										</span>
									</span>
								</td>
								<td>
									<c:if test="${productCustom.mallPriceMin eq productCustom.mallPriceMax}">
										${productCustom.mallPriceMin}
									</c:if>
									<c:if test="${productCustom.mallPriceMin ne productCustom.mallPriceMax}">
										${productCustom.mallPriceMin}——${productCustom.mallPriceMax}
									</c:if>
								</td>
								<td>
									<c:if test="${productCustom.salePriceMin eq productCustom.salePriceMax}">
										${productCustom.salePriceMin}
									</c:if>
									<c:if test="${productCustom.salePriceMin ne productCustom.salePriceMax}">
										${productCustom.salePriceMin}——${productCustom.salePriceMax}
									</c:if>
								</td>
								<td>${productCustom.stock}</td>
								<td>
									<c:if test="${productCustom.saleType eq 1}">品牌活动款</c:if>
									<c:if test="${productCustom.saleType eq 2}">单品活动款</c:if>
									<br>
									<c:if test="${productCustom.status eq 0}">下架</c:if>
									<c:if test="${productCustom.status eq 1}">上架</c:if>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td class="title">审核结果</td>
				<td>
					<input type="radio" name="auditStatus" value="1" >通过
					<input type="radio" name="auditStatus" value="2" >拒绝
				</td>
			</tr>
			<tr id="rejectionReasonTr" style="display: none;">
				<td class="title">驳回理由*</td>
				<td>
					<textarea rows="7" cols="70" id="rejectionReason" name="rejectionReason" maxlength="200"></textarea>
				</td>
			</tr>
			<tr>
	          	<td class="title">操作</td>
	          	<td>
					<input id="confirm" type="button" style="float:left;width: 70px;" class="l-button l-button-test" value="确定"/>
					<input type="button" value="取消" class="l-button l-button-test" style="float:left;margin-left: 10px;" onclick="frameElement.dialog.close();" />
				</td>
	        </tr>
			</tbody>
		</table>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>
</html>
