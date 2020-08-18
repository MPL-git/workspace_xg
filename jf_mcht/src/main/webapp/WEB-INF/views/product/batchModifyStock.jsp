<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>修改SkU</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
	<!-- Bootstrap -->
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
      <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<!--       <link href="${pageContext.request.contextPath}/static/css/editor.dataTables.min.css" rel="stylesheet" type="text/css" /> -->
<!--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/fileuploader-style.css" /> -->
<style type="text/css">

a {
	cursor: pointer;
}

a:focus{
  color:#3C9EFF;
}


.sku_pic_picker {
height: 50px;
margin: 0 auto;
}

.sku_pic_picker div{
position:absolute;
width: 50px;
height: 50px;
line-height: 50px;
font-size: 50px;
z-index: 1000;
color: #ddd;
}
.sku_pic_picker input{
position: absolute;
width: 50px;
height: 50px;
opacity: 0;
z-index: 1002;
}

.sku_pic_picker img{
width: 50px;
height: 50px;
}
.dataTables_info{
display: none;
}

.datatable-container{
padding: 0 5px;
}

.sweet-alert button.cancel:hover {
    background-color:#8CD4F5;
}
.sweet-alert button.cancel {
    background-color: #8CD4F5;
}

.dataTable input[type='text']{
width:70%;
}

</style>


</head>

<body>
  <div class="modal-dialog" role="document" style="width:1100px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">批量修改库存</span>
      </div>
	  <div class="modal-body">
		  <div><font style="color: red">备注：修改的库存不能低于冻结的库存</font></div>
	  </div>
	  <table border="1" bordercolor="#ddd" style="width:97%;margin-left:1.5%;margin-bottom:30px;">
		  <thead>
		  	<tr role="row">
				<th width="280">图片/名称/商品ID</th>
				<th>库存</th>
			</tr>
		  	<c:forEach items="${productCustomList}" var="productCustom">
				<tr>
					<td>
						<div class="no-check">
						<div class="width-60">
						<c:if test="${productCustom.canDelete}">
							<img src="${productCustom.pic}">
						</c:if>
						<c:if test="${!productCustom.canDelete}">
							<img src="${ctx}/file_servelt${productCustom.pic}">
						</c:if>
					    </div>
						<div class="width-226">
							<p>${productCustom.name}</p>
							<p style="color: #999;margin: 0;">ID：${productCustom.code}</p>
							<p style="color: #999;margin: 5px 0 0;">货号：${productCustom.artNo}</p>
						</div>
						</div>
					</td>
					<c:if test="${productCustom.isSingleProp eq '0'}">
						<td>
							<div style="overflow-x: scroll;width: 783px;">
							<table border="1" bordercolor="#ddd" style="margin: 1.5% 0 1.5% 1.5%">
								<tr>
									<td>尺码</td>
									<c:forEach items="${productCustom.sizeValueList}" var="sizeValue">
										<td width="60">${sizeValue.propValue}</td>
									</c:forEach>
									<c:if test="${empty productCustom.sizeValueList && !empty productCustom.colorValueList}">
										<td width="60"></td>
									</c:if>
								</tr>
								<c:forEach items="${productCustom.colorValueList}" var="colorValue">
									<tr>
										<td>${colorValue.propValue}</td>
										<c:forEach items="${productCustom.sizeValueList}" var="sizeValue">
											<c:forEach items="${productCustom.productItemList}" var="productItem">
												<c:if test="${productItem.sizeValue eq sizeValue.propValue && productItem.colorValue eq colorValue.propValue}">
													<td>
														<input name="stock" style="margin-top: 1%;margin-bottom: 1%;border:0.5px solid #ddd;width: 50px;" class="priceInput" type="text" id="${productItem.id}" data-value="${productItem.lockedAmount}" value="${productItem.stock}">
														<span style="display: none;"><br><font color="red">冻结${productItem.lockedAmount}</font></span>
														<span style="display: none;"><br><font color="red">修改成功</font></span>
														<span style="display: none;"><br><font color="red">修改失败</font></span>
													</td>
												</c:if>
											</c:forEach>
										</c:forEach>

										<c:if test="${!empty productCustom.colorValueList && empty productCustom.sizeValueList}">
											<c:forEach items="${productCustom.productItemList}" var="productItem">
												<c:if test="${productItem.colorValue eq colorValue.propValue}">
													<td>
														<input name="stock" style="margin-top: 1%;margin-bottom: 1%;border:0.5px solid #ddd;width: 50px;" class="priceInput" type="text" id="${productItem.id}" data-value="${productItem.lockedAmount}" value="${productItem.stock}">
														<span style="display: none;"><br><font color="red">冻结${productItem.lockedAmount}</font></span>
														<span style="display: none;"><br><font color="red">修改成功</font></span>
														<span style="display: none;"><br><font color="red">修改失败</font></span>
													</td>
												</c:if>
											</c:forEach>
										</c:if>
									</tr>
								</c:forEach>
								<c:if test="${empty productCustom.colorValueList}">
								<tr>
									<td></td>
									<c:forEach items="${productCustom.sizeValueList}" var="sizeValue">
									<c:forEach items="${productCustom.productItemList}" var="productItem">
										<c:if test="${productItem.sizeValue eq sizeValue.propValue}">
											<td>
												<input name="stock" style="margin-top: 1%;margin-bottom: 1%;border:0.5px solid #ddd;width: 50px;" class="priceInput" type="text" id="${productItem.id}" data-value="${productItem.lockedAmount}" value="${productItem.stock}">
												<span style="display: none;"><br><font color="red">冻结${productItem.lockedAmount}</font></span>
												<span style="display: none;"><br><font color="red">修改成功</font></span>
												<span style="display: none;"><br><font color="red">修改失败</font></span>
											</td>
										</c:if>
									</c:forEach>
									</c:forEach>
								</tr>
								</c:if>
							</table>
							</div>
						</td>
					</c:if>
					<c:if test="${productCustom.isSingleProp eq '1'}">
					<td>
						<table border="1" bordercolor="#ddd" style="width:25%;margin: 1.5% 0 1.5% 1.5%">
							<tr>
								<td>尺码</td>
								<td>均码</td>
							</tr>
							<tr>
								<td>均码</td>
								<c:forEach items="${productCustom.productItemList}" var="productItem">
									<td>
										<input style="margin-top: 1%;margin-bottom: 1%;border:0.5px solid #ddd;width: 50px;" class="priceInput" type="text" id="${productItem.id}" data-value="${productItem.lockedAmount}" value="${productItem.stock}">
										<span style="display: none;"><br><font color="red">冻结${productItem.lockedAmount}</font></span>
										<span style="display: none;"><br><font color="red">修改成功</font></span>
										<span style="display: none;"><br><font color="red">修改失败</font></span>
									</td>
								</c:forEach>
							</tr>
						</table>
					<td>
					</c:if>
				</tr>
			</c:forEach>
		  </thead>
	  </table>
	</div>
  </div>

<div class="modal fade" id="viewModal1" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel1" data-backdrop="static">
</div>


	
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js"
	type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>	




<script type="text/javascript">
	$(function(){

	})

	$(".priceInput").blur(function () {
		var tag = true;
		var _this = $(this);
		_this.next().hide();
		_this.next().next().hide();
		_this.next().next().next().hide();
		var reg = new RegExp("^[0-9]*$");
		if(!reg.test($(_this).val())){
			swal("请输入数字");
			return;
		}
		if($(_this).val()>100000){
			swal("库存不可大于十万");
			return;
		}
		//冻结库存 > 输入值时，输入框下方呈现冻结数同时 将输入数字更改冻结数
		$.ajax({
			url : "${ctx}/product/getLockedAmount.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"productItemId":$(this).attr("id")},
			timeout : 30000,
			success : function(data) {
				if (data.returnCode=="0000") {
					if(data.returnData> $(_this).val() && $(_this).val()!=''){
						$(_this).val(data.returnData);
						$(_this).next().html("<br><font color=\"red\">冻结"+data.returnData+"</font>");
						$(_this).next().show();
						tag = false;
					}
					$.ajax({
						url : "${ctx}/product/changeStock.shtml",
						type : 'POST',
						dataType : 'json',
						cache : false,
						async : false,
						data : {"productItemId":$(_this).attr("id"),"stock":$(_this).val()},
						timeout : 30000,
						success : function(data) {
							if (data.returnCode=="0000" && data.returnData == true && tag == true) {
								_this.next().next().show();
							} else if(data.returnCode=="4004"){
								_this.next().next().next().show();
							}
							$(".close").click(function () {
								$("#viewModal").modal('hide');
								table.ajax.reload(null, false);
							})
						},
						error : function() {
							swal({
								title: "请求失败！",
								type: "error",
								timer: 1500,
								confirmButtonText: "确定"
							});
						}
					});
				}
			},
			error : function() {
				swal({
					title: "提交失败！",
					type: "error",
					timer: 1500,
					confirmButtonText: "确定"
				});
			}
		});
	})

</script>

</body>
</html>
