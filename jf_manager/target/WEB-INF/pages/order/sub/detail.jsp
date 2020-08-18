<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/css/glyphicon.css"
	  rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/star.css"
	  rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/css/form.css"
	  rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/glyphicon.css"
	  rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css"
	  rel="stylesheet" type="text/css" />
<script
		src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js"
		type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/viewer.min.js"
		type="text/javascript"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<style type="text/css">
	body {font-size: 12px;padding: 10px;}
	td input,td select{border:1px solid #AECAF0;}
	.table-title{font-size: 14px;color: #333333;font-weight: 600;}
	.title-top{padding-top:10px;padding-bottom:10px;}
	.marR10{margin-right:10px;}
	.marT10{margin-top:10px;}
	.marB05{margin-bottom:5px;}
	.table-title-link{color: #1B17EE;}
	.color-1{color: #9D999D;}
	.color-2{color: #FC0000;}
	.color-3{color: #EFD104;}
	.color-4{color: #00FC28;}
	.color-5{color: #0351F7;}
	.color-6{color: #DF00FB;}
</style>
<script type="text/javascript">
	// 商品详情信息
	function viewProduct(id) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width()-200,
			title: "商品信息",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/product/viewProduct.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	var viewerPictures;
	var commentAjaxFlag = true;
	var viewerFlag = true;
	$(function() {
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'),
				{
					hide : function() {
						$("#viewer-pictures").hide();
					}
				});

		receiverPhoneStr();

		$("a[name='downLoadSubOrderAttachment']")
				.bind(
						'click',
						function() {
							var subOrderAttachmentId = $(this).attr(
									"subOrderAttachmentId");
							var fileName = $(this).attr("fileName");
							var filePath = $(this).attr("filePath");
							$
									.ajax({
										type : 'post',
										url : '${pageContext.request.contextPath}/order/sub/checkFileExists.shtml',
										data : {
											"subOrderAttachmentId" : subOrderAttachmentId
										},
										dataType : 'json',
										success : function(data) {
											if (data == null
													|| data.code != 200) {
												commUtil.alertError(data.msg);
											} else {
												location.href = "${pageContext.request.contextPath}/order/sub/downLoadSubOrderAttachment.shtml?fileName="
														+ fileName
														+ "&filePath="
														+ filePath;
											}
										},
										error : function(e) {
											commUtil.alertError("系统异常请稍后再试！");
										}
									});
						});

	});

	//图片格式验证
	function ajaxFileUpload(obj) {
		var file = obj.files[0];
		var fileName = file.name;
		var rFilter = [ "jpg", "bmp", "png", "gif", "JPG", "BMP", "PNG", "GIF",
			"mp3", "wav", "wma", "ogg", "ape", "acc", "MP3", "WAV", "WMA",
			"OGG", "APE", "ACC", "avi", "mp4", "mov", "rm", "wma", "mkv",
			"rmvb", "AVI", "MP4", "MOV", "RM", "WMA", "MKV", "RMVB", "doc",
			"docx", "xls", "xlsx", "ppt", "pptx", "pdf", "rar", "zip",
			"DOC", "DOCX", "XLS", "XLSX", "PPT", "PPTX", "PDF", "RAR",
			"ZIP" ];
		var suffix = file.name.substring(fileName.lastIndexOf(".") + 1);
		if ($.inArray(suffix, rFilter) == -1) {
			commUtil.alertError("文件格式不正确！");
			return;
		}
		var reader = new FileReader();
		reader.onload = function(e) {
			$
					.ajaxFileUpload({
						url : '${pageContext.request.contextPath}/service/common/fileUpload.shtml?fileType=8',
						secureuri : false,
						fileElementId : "uploadFile",
						dataType : 'json',
						success : function(result, status) {
							if (result.RESULT_CODE == 0) {
								var filePath = result.FILE_PATH;
								var subOrderId = ${subOrderCustom.id};
								$
										.ajax({
											type : 'post',
											url : '${pageContext.request.contextPath}/order/sub/saveSubOrderAttachment.shtml',
											data : {
												"id" : subOrderId,
												"filePath" : filePath,
												"fileName" : fileName
											},
											dataType : 'json',
											success : function(json) {
												if (json == null
														|| json.code != 200) {
													commUtil
															.alertError("上传失败，请稍后重试");
												} else {
													commUtil
															.alertSuccess("上传成功");
													location.reload();
												}
											},
											error : function(e) {
												commUtil
														.alertError("系统异常请稍后再试！");
											}
										});
							} else {
								alert(result.RESULT_MESSAGE);
							}
						},
						error : function(e) {
							alert("服务异常");
						}
					});
		};
		reader.readAsDataURL(file);
	}

	function receiverPhoneStr() {
		var receiverPhone = '${subOrderCustom.receiverPhone}';
		var deliveryDateStr = $("#deliveryDate").html();
		if (receiverPhone != '' && deliveryDateStr != '') {
			var date = new Date();
			new Date(date.setMonth((new Date().getMonth() - 3)));
			date.format("yyyy-MM-dd hh:mm:ss");
			var deliveryDate = new Date(deliveryDateStr);
			deliveryDate.format("yyyy-MM-dd hh:mm:ss");
			if (deliveryDate.getTime() < date.getTime()) {
				var receiverPhoneStr = receiverPhone.substring(0, 6) + "**"
						+ receiverPhone.substring(receiverPhone.length - 1);
				$("#receiverPhone").html(receiverPhoneStr);
			} else {
				$("#receiverPhone").html(receiverPhone);
			}
		} else {
			$("#receiverPhone").html(receiverPhone);
		}
	}

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
		viewerPictures.show();
	}

	//放大图片
	function viewerPics(commentId, src, flag) {
		var url = "${pageContext.request.contextPath}/comment/getCommentPicList.shtml";
		if (flag == 'mcht') {
			url = "${pageContext.request.contextPath}/comment/getCommentMchtPicList.shtml";
		}
		viewerFlag = true;
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$
				.ajax({
					url : url,
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {
						commentId : commentId
					},
					timeout : 30000,
					success : function(data) {
						if (data && data.length > 0) {
							var ind = 0;
							for (var i = 0; i < data.length; i++) {
								if (data[i].pic == src) {
									ind = i;
								}
								$("#viewer-pictures")
										.append(
												'<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" alt=""></li>');
							}
							viewerPictures = new Viewer(document
									.getElementById('viewer-pictures'), {
								url : 'data-original',
								hide : function() {
									$("#viewer-pictures").hide();
								},
								viewed : function() {
									if (viewerFlag) {
										viewerPictures.view(ind);
										viewerFlag = false;
									}
								}
							});
							$("#viewer-pictures").show();
							viewerPictures.show();
						}
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});

	}

	function remarkEdit(subOrderId) {
		$.ligerDialog
				.open({
					height : 260,
					width : 350,
					title : "订单备注",
					name : "INSERT_WINDOW",
					url : "${pageContext.request.contextPath}/order/sub/editRemark.shtml?subOrderId="
							+ subOrderId,
					showMax : true,
					showToggle : false,
					showMin : false,
					isResize : true,
					slide : false,
					data : null
				});
	}

	function addOrUpdateOrderAbnormal(subOrderId) {
		$.ligerDialog
				.open({
					height : 500,
					width : 600,
					title : "异常订单标识",
					name : "INSERT_WINDOW",
					url : "${pageContext.request.contextPath}/order/afterService/addOrUpdateOrderAbnormalManager.shtml?subOrderId="
							+ subOrderId,
					showMax : true,
					showToggle : false,
					showMin : false,
					isResize : true,
					slide : false,
					data : null
				});
	}

	function updateOrderAbnormalLog(orderAbnormalLogId, subOrderId) {
		$.ligerDialog
				.open({
					height : 310,
					width : 550,
					title : "跟单操作",
					name : "INSERT_WINDOW",
					url : "${pageContext.request.contextPath}/order/sub/updateOrderAbnormalLogManager.shtml?orderAbnormalLogId="
							+ orderAbnormalLogId + "&subOrderId=" + subOrderId,
					showMax : true,
					showToggle : false,
					showMin : false,
					isResize : true,
					slide : false,
					data : null
				});
	}

	function mchtRemarkEdit(subOrderId) {
		$.ligerDialog
				.open({
					height : 260,
					width : 350,
					title : "商家备注",
					name : "INSERT_WINDOW",
					url : "${pageContext.request.contextPath}/order/sub/editRemark.shtml?subOrderId="
							+ subOrderId + "&mchtRemark=1",
					showMax : true,
					showToggle : false,
					showMin : false,
					isResize : true,
					slide : false,
					data : null
				});
	}

	function addComplain(orderDtlId) {
		$.ligerDialog
				.open({
					height : $(window).height() - 200,
					width : 650,
					title : "发起投诉",
					name : "INSERT_WINDOW",
					url : "${pageContext.request.contextPath}/order/complain/add.shtml?orderDtlId="
							+ orderDtlId,
					showMax : true,
					showToggle : false,
					showMin : false,
					isResize : true,
					slide : false,
					data : null
				});
	}

	function viewAfterServiceDetail(id, serviceTypeDesc) {
		$.ligerDialog
				.open({
					height : $(window).height(),
					width : $(window).width() - 50,
					title : "售后详情（" + serviceTypeDesc + "）",
					name : "INSERT_WINDOW",
					url : "${pageContext.request.contextPath}/order/afterService/detail.shtml?id="
							+ id,
					showMax : true,
					showToggle : false,
					showMin : false,
					isResize : true,
					slide : false,
					data : null
				});

		// 	location.href = "${pageContext.request.contextPath}/order/afterService/detail.shtml?id=" + id;
	}

	function viewWuliu(subOrderId) {
		$.ligerDialog
				.open({
					height : $(window).height(),
					width : $(window).width() * 0.35,
					title : "查看物流动态",
					name : "INSERT_WINDOW",
					url : "${pageContext.request.contextPath}/order/afterService/viewWuliu.shtml?subOrderId="
							+ subOrderId,
					showMax : true,
					showToggle : false,
					showMin : false,
					isResize : true,
					slide : false,
					data : null
				});
	}

	function addRefundOrder(orderDtlId, subOrderStatus, payAmount) {
		$.ligerDialog
				.open({
					height : '600',
					width : '600',
					title : "售后申请",
					name : "INSERT_WINDOW",
					url : "${pageContext.request.contextPath}/order/complain/addRefundOrderManager.shtml?orderDtlId="
							+ orderDtlId
							+ "&subOrderStatus="
							+ subOrderStatus
							+ "&payAmount=" + payAmount,
					showMax : true,
					showToggle : false,
					showMin : false,
					isResize : true,
					slide : false,
					data : null
				});
	}

	function addCustomerServiceOrder(customerServiceOrderId, subOrderStatus,
									 payAmount) {
		$.ligerDialog
				.open({
					height : '600',
					width : '600',
					title : "售后申请",
					name : "INSERT_WINDOW",
					url : "${pageContext.request.contextPath}/order/afterService/addCustomerServiceOrder.shtml?customerServiceOrderId="
							+ customerServiceOrderId
							+ "&subOrderStatus="
							+ subOrderStatus + "&payAmount=" + payAmount,
					showMax : true,
					showToggle : false,
					showMin : false,
					isResize : true,
					slide : false,
					data : null
				});
	}

	//查看商家联系人
	function mchtContact(id) {
		$.ligerDialog
				.open({
					height : $(window).height() - 50,
					width : $(window).width() - 100,
					title : "商家联系人",
					name : "INSERT_WINDOW",
					url : "${pageContext.request.contextPath}/mcht/mchtContact.shtml?contactType=4,6,3&mchtId="
							+ id,
					showMax : true,
					showToggle : false,
					showMin : false,
					isResize : true,
					slide : false,
					data : null
				});
	}

	function addShamFollowBy(id) {
		$
				.ajax({
					type : 'post',
					url : '${pageContext.request.contextPath}/order/sub/addShamFollowBy.shtml',
					data : {
						id : id
					},
					dataType : 'json',
					success : function(data) {
						if (data == null || data.code != 200) {
							commUtil.alertError(data.msg);
						} else {
							$(".follow-name")
									.html("订单跟单员：" + data.followByName);
						}
					},
					error : function(e) {
						commUtil.alertError("系统异常请稍后再试！");
					}
				});

	}

	// 评论信息展开
	function commentDown(subOrderId) {
		if (commentAjaxFlag) {
			$
					.ajax({
						type : 'post',
						url : '${pageContext.request.contextPath}/comment/viewCommentList.shtml',
						data : {
							subOrderId : subOrderId
						},
						dataType : 'json',
						success : function(data) {
							if (data == null || data.code != 200) {
								commUtil.alertError(data.msg);
							} else {
								if (data.shopScore != null) {
									$(".mcht-score").attr(
											"style",
											"width:" + data.shopScore.mchtScore
											* 24 + "px;");
									$(".wuliu-score").attr(
											"style",
											"width:"
											+ data.shopScore.wuliuScore
											* 24 + "px;");
									$(".comment-create").html(data.createDate);
									var html = [];
									$
											.each(
													data.listMap,
													function(index, element) {
														html
																.push("<hr style='border-top:2px dotted #ddd; margin: 10px 0px 10px 0px;' />");
														html
																.push("<div><table style='font-size: 12px;width: 100%;'><tr><td width='25%'>");
														html
																.push("<div style='overflow: hidden;'>");
														html
																.push("<img style='float: left;margin: 10px;' src='${pageContext.request.contextPath}/file_servelt"+element.commentCustom.skuPic+"' width='80' height='80'>");
														html
																.push("<p style='padding-top: 15px;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;width: calc(100%-100px);'>"
																		+ element.commentCustom.brandName
																		+ "&nbsp;&nbsp;"
																		+ element.commentCustom.productName
																		+ "&nbsp;&nbsp;"
																		+ element.commentCustom.artNo
																		+ "</p>");
														html
																.push("<div style='margin-top: 20px;color: gray;'>");
														html
																.push("<p>规格："
																		+ element.commentCustom.productPropDesc
																		+ "</p><p>SKU码："
																		+ element.commentCustom.sku
																		+ "</p></div></div>");
														html
																.push("<div><div style='float: left;margin: 5px 10px 5px 0px;'>商品描述</div>");
														html
																.push("<div class='starBox' style='margin: 5px 0px 5px 0px;'>");
														html
																.push("<ul class='star' >");
														html
																.push("<li><a href='javascript:void(0)' class='one-star' style='cursor: default;'>1</a></li>");
														html
																.push("<li><a href='javascript:void(0)' class='two-star' style='cursor: default;'>2</a></li>");
														html
																.push("<li><a href='javascript:void(0)' class='three-star' style='cursor: default;'>3</a></li>");
														html
																.push("<li><a href='javascript:void(0)' class='four-star' style='cursor: default;'>4</a></li>");
														html
																.push("<li><a href='javascript:void(0)' class='five-star' style='cursor: default;'>5</a></li>");
														html.push("</ul>");
														html
																.push("<div class='current-rating' style='width: "+element.commentCustom.productScore*24+"px;'></div></div>");
														html
																.push("<div style='clear:both; height: 0; line-height: 0; font-size: 0'></div></div></td>");
														html
																.push("<td width='75%'><div style='overflow: hidden;'><div style='display: inline-block;width: 65%;float: left;'>");
														html
																.push("<div style='margin: 0px 20px 0px 20px;'>"
																		+ element.commentCustom.content
																		+ "</div></div>");
														html
																.push("<div style='display: inline-block;width: 35%;'>");
														if (element.commentPicList != null) {
															$
																	.each(
																			element.commentPicList,
																			function(
																					ind,
																					ele) {
																				html
																						.push("<img style='margin: 5px 5px 0px 0px;' src='${pageContext.request.contextPath}/file_servelt"
																								+ ele.pic
																								+ "' onclick='viewerPics("
																								+ element.commentCustom.id
																								+ ", \""
																								+ ele.pic
																								+ "\")' width='60' height='60'>");
																			});
														}
														html
																.push("</div></div>");
														if (element.appendCommentCustom != null) {
															html
																	.push("<hr style='border-top:2px dotted #ddd; margin: 10px 0px 10px 20px;' />");
															html
																	.push("<div style='overflow: hidden;'>");
															html
																	.push("<div style='display: inline-block;width: 65%;float: left;'>");
															if (element.betweenDays == 0) {
																html
																		.push("<div style='margin: 0px 20px 0px 20px;'><span style='color: red;'>当天追评</span></br>");
															} else {
																html
																		.push("<div style='margin: 0px 20px 0px 20px;'><span style='color: red;'>"
																				+ element.betweenDays
																				+ "天后追评</span></br>");
															}
															html
																	.push(element.appendCommentCustom.content
																			+ "</div></div>");
															html
																	.push("<div style='display: inline-block;width: 35%;'>");
															html.push("");
															if (element.appendCommentPicList != null) {
																$
																		.each(
																				element.appendCommentPicList,
																				function(
																						ind,
																						ele) {
																					html
																							.push("<img style='margin: 5px 5px 0px 0px;' src='${pageContext.request.contextPath}/file_servelt"
																									+ ele.pic
																									+ "' onclick='viewerPics("
																									+ element.commentCustom.id
																									+ ", \""
																									+ ele.pic
																									+ "\")' width='60' height='60'>");
																				});
															}
															html
																	.push("</div></div>");
														}
														if (element.commentCustom.mchtReplyDay != null) {
															html
																	.push("<hr style='border-top:2px dotted #ddd; margin: 10px 0px 10px 20px;' />");
															html
																	.push("<div style='overflow: hidden;'>");
															html
																	.push("<div style='display: inline-block;width: 65%;float: left;'>");
															if (element.commentCustom.mchtReplyDay == 0) {
																html
																		.push("<div style='margin: 0px 20px 0px 20px;'><span style='color: red;'>当天回复</span></br>");
															} else {
																html
																		.push("<div style='margin: 0px 20px 0px 20px;'><span style='color: red;'>"
																				+ element.commentCustom.mchtReplyDay
																				+ "天后回复</span></br>");
															}
															html
																	.push("<span style='color: gray;'>"
																			+ element.commentCustom.mchtReply
																			+ "</span></div></div>");
															html
																	.push("<div style='display: inline-block;width: 35%;'>");
															html.push("");
															if (element.commentCustom.mchtCommentPic) {
																var mchtCommentPics = element.commentCustom.mchtCommentPic
																		.split(",");
																for (var i = 0; i < mchtCommentPics.length; i++) {
																	html
																			.push("<img style='margin: 5px 5px 0px 0px;' src='${pageContext.request.contextPath}/file_servelt/"
																					+ mchtCommentPics[i]
																					+ "' onclick='viewerPics("
																					+ element.commentCustom.id
																					+ ", \""
																					+ mchtCommentPics[i]
																					+ "\", \"mcht\")' width='60' height='60'>");
																}
															}
															html
																	.push("</div></div>");
														}
														html
																.push("</td></tr></table></div>");
														$(
																".comment-content-div")
																.html(
																		html
																				.join(""));
													});
									$(".comment-div").show();
									commentAjaxFlag = false;
								}
								$(".comment-down").hide();
								$(".comment-up").show();
							}
						},
						error : function(e) {
							commUtil.alertError("系统异常请稍后再试！");
						}
					});
		} else {
			$(".comment-div").show();
			$(".comment-down").hide();
			$(".comment-up").show();
		}
	}
	// 评论信息收起
	function commentUp() {
		$(".comment-down").show();
		$(".comment-up").hide();
		$(".comment-div").hide();
	}

	//介入单查看
	function showInterventionOrder(interventionOrderId, statusPage) {
		$.ligerDialog
				.open({
					height : $(window).height() - 100,
					width : $(window).width() - 200,
					title : "查看详情",
					name : "INSERT_WINDOW",
					url : "${pageContext.request.contextPath}/interventionOrder/showInterventionOrderManager.shtml?interventionOrderId="
							+ interventionOrderId + "&statusPage=" + statusPage,
					showMax : true,
					showToggle : false,
					showMin : false,
					isResize : true,
					slide : false,
					data : null
				});
	}

	function viewAppealOrder(id) {
		$.ligerDialog
				.open({
					height : $(window).height() - 50,
					width : $(window).width() - 100,
					title : "投诉详情页",
					name : "INSERT_WINDOW",
					url : "${pageContext.request.contextPath}/appealOrder/view.shtml?id="
							+ id,
					showMax : true,
					showToggle : false,
					showMin : false,
					isResize : true,
					slide : false,
					data : null
				});
	}

	//创建工单
	function addWork(id) {
		$.ligerDialog
				.open({
					height : 600,
					width : 950,
					title : "创建工单",
					name : "INSERT_WINDOW",
					url : "${pageContext.request.contextPath}/order/sub/addOrderWork.shtml?id="
							+ id,
					showMax : true,
					showToggle : false,
					showMin : false,
					isResize : true,
					slide : false,
					data : null
				});
	}
</script>
<html>
<body>
<div class="title-top">
	<div class="table-title">
		<span>订单信息</span>
		<c:if test="${woRkCustoms}">
			<span><a href="javascript:addWork(${subOrderCustom.id});">【创建工单】</a></span>
		</c:if>
	</div>
	<table class="gridtable marT10">
		<tr>
			<td class="title" style="width: 80px;">订单状态</td>
			<td>${subOrderCustom.statusDesc}<c:if
					test="${subOrderCustom.isUserDel eq '1' }">
				<span style="color: red;">(已删除)</span>
				</c:if></td>
			<td class="title" style="width: 80px;">取消原因</td>
			<td>${subOrderCustom.cancelReason}</td>
			<td class="title" style="width: 80px;">取消时间</td>
			<td><fmt:formatDate value="${subOrderCustom.cancelDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td class="title" style="width: 80px;">子订单编号</td>
			<td>${subOrderCustom.subOrderCode}（${orderTypeDesc}）<c:if test="${promotionType==1}"><span style="color: red;">(推广分润)</span></c:if></td>
		</tr>
		<tr>
			<td class="title" style="width: 80px;">订单金额</td>
			<c:if test="${subOrderCustom.orderType eq '7'}">
				<td>0</td>
			</c:if>
			<c:if test="${subOrderCustom.orderType ne '7'}">
				<td>${subOrderCustom.amount}</td>
			</c:if>
			<td class="title" style="width: 80px;">客户付款渠道</td>
			<c:if test="${subOrderCustom.orderType eq '7'}">
				<td>积分转盘</td>
			</c:if>
			<c:if test="${subOrderCustom.orderType ne '7'}">
				<td>${subOrderCustom.paymentName}</td>
			</c:if>
			<td class="title" style="width: 80px;">订单渠道</td>
			<td>${subOrderCustom.sourceClientDesc}</td>
			<td class="title" style="width: 80px;">母订单号</td>
			<td>${subOrderCustom.combineOrderCode}</td>
		</tr>
		<tr>
			<td class="title" style="width: 80px;">下单时间</td>
			<td><fmt:formatDate value="${subOrderCustom.orderCreateDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td class="title" style="width: 80px;">付款时间</td>
			<td><fmt:formatDate value="${subOrderCustom.orderPayDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td class="title" style="width: 80px;">发货时间</td>
			<td id="deliveryDate">
				<span style="color: red">承诺时间：</span><fmt:formatDate value="${subOrderCustom.deliveryLastDate}"
																	 pattern="yyyy-MM-dd HH:mm:ss" />  <br>
				<span style="color: red">实际时间：</span><fmt:formatDate value="${subOrderCustom.deliveryDate}"
																	 pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
			<td class="title" style="width: 80px;">收货时间</td>
			<td><fmt:formatDate value="${subOrderCustom.completeDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		<tr>
			<td class="title" style="width: 80px;">商家序号</td>
			<td>${subOrderCustom.mchtCode}</td>
			<td class="title" style="width: 80px;">店铺名称</td>
			<td>${subOrderCustom.shopName}</td>
			<td class="title" style="width: 80px;">商家公司名</td>
			<td><c:if test="${subOrderCustom.companyName != '' }">
				<a href="javascript:mchtContact(${subOrderCustom.mchtId});">${subOrderCustom.companyName}</a>
			</c:if></td>
			<td class="title" style="width: 80px;">商家类型</td>
			<td>${subOrderCustom.isManageSelfDesc}${subOrderCustom.mchtTypeDesc}</td>
		</tr>
		<tr>
			<td class="title" style="width: 80px;">活动ID</td>
			<td style="word-break: break-all;">${subOrderCustom.activityIdGroup}</td>
			<td class="title" style="width: 80px;">会场ID</td>
			<td style="word-break: break-all;">${subOrderCustom.activityAreaIdGroup}</td>
			<td class="title" style="width: 80px;">收货人</td>
			<td>${subOrderCustom.receiverName}</td>
			<td class="title" style="width: 80px;">联系电话</td>
			<td id="receiverPhone"></td>
		</tr>
		<tr>
			<td class="title" style="width: 80px;">快递名称</td>
			<td>${subOrderCustom.expressName}</td>
			<td class="title" style="width: 80px;">快递单号</td>
			<td><a href="javascript:;"
				   onclick="viewWuliu(${subOrderCustom.id});">${subOrderCustom.expressNo}</a>
			</td>
			<td class="title" style="width: 80px;">收货地址</td>
			<td colspan="3">${subOrderCustom.receiverAddress}</td>
		</tr>
		<tr>
			<td class="title" style="width: 80px;">用户ID</td>
			<td>${subOrderCustom.memberId}<c:if
					test="${memberCouponCustom}">
				<span style="color: red;">(SVIP)</span>
				</c:if></td>
			<td class="title" style="width: 80px;">用户昵称</td>
			<td>${subOrderCustom.memberNick}</td>
			<td class="title" style="width: 80px;">客户备注</td>
			<td colspan="3">${subOrderCustom.combineOrderRemarks}</td>
		</tr>
	</table>
</div>

<div class="title-top">
	<div class="table-title">
		<span>商品信息</span>
	</div>
	<table class="gridtable marT10">
		<tr>
			<td class="title">SKU图</td>
			<td class="title">品牌</td>
			<td class="title">名称</td>
			<td class="title">货号</td>
			<td class="title">商品来源</td>
			<td class="title">规格</td>
			<td class="title">吊牌价</td>
			<td class="title">醒购价</td>
			<td class="title">数量</td>
			<td class="title">商家优惠</td>
			<td class="title">订单运费</td>
			<td class="title">平台优惠</td>
			<td class="title">积分优惠</td>
			<td class="title">实付金额</td>
			<c:if test="${subOrderCustom.mchtType==1}">
				<td class="title">结算价</td>
			</c:if>
			<%-- <c:if test="${subOrderCustom.mchtType==2}">
           <td class="title">佣金比例</td>
           <td class="title">技术服务费</td>
           <td class="title">结算金额</td>
           </c:if> --%>
			<td class="title">是否SVIP购买</td>
			<td class="title">SVIP折扣</td>
			<td class="title">推广分润</td>
			<td class="title">售后状态</td>
			<td class="title">介入单</td>
			<td class="title">投诉</td>
		</tr>
		<c:forEach var="orderDtl" items="${orderDtlCustoms}">
			<tr>
				<td><img alt=""
						 src="${pageContext.request.contextPath}/file_servelt${orderDtl.skuPic}"
						 onclick="viewerPic(this.src)" style="width:60px;height:60px;"></td>
				<td>${orderDtl.productBrandName}</td>
				<td>${orderDtl.productName}<br />
					商品ID：${orderDtl.productCode}<br /> <c:if
							test="${(sessionScope.USER_SESSION.staffID == '1' or role89 == '89') and not empty orderDtl.orderProductSnapshotId }">
						<a
								href="${pageContext.request.contextPath}/order/combine/showDetail.shtml?orderDtlId=${orderDtl.id}"
								target="_blank">【快照详情】</a>
					</c:if> <br>快递：${orderDtl.orderDtlExpressName}&nbsp;&nbsp;快递单号：${orderDtl.dtlExpressNo}
				</td>
				<td>${orderDtl.productArtNo}</td>

				<c:if test="${subOrderCustom.orderType eq '7'}">
					<td>积分转盘</td>
				</c:if>
				<c:if test="${subOrderCustom.orderType ne '7'}">
					<td>
						<c:choose>
							<c:when
									test="${not empty orderDtl.activityId  and not empty orderDtl.activityAreaId}">
								<c:if test="${orderDtl.activityAreaSource == '1'}">
									${orderDtl.activityAreaName}
								</c:if>
								<c:if test="${orderDtl.activityAreaSource == '2'}">
									品牌特卖
								</c:if>
							</c:when>
							<c:when test="${not empty orderDtl.singleProductActivityId}">
								<c:if test="${orderDtl.singleActivityName == '1'}">
									新用户专享
								</c:if>
								<c:if test="${orderDtl.singleActivityName == '2'}">
									单品疯折
								</c:if>
								<c:if test="${orderDtl.singleActivityName == '3'}">
									限时秒杀
								</c:if>
								<c:if test="${orderDtl.singleActivityName == '4'}">
									新用户秒杀
								</c:if>
								<c:if test="${orderDtl.singleActivityName == '5'}">
									积分商城
								</c:if>
								<c:if test="${orderDtl.singleActivityName == '6'}">
									断码清仓
								</c:if>
								<c:if test="${orderDtl.singleActivityName == '7'}">
									砍价免费拿
								</c:if>
								<c:if test="${orderDtl.singleActivityName == '8'}">
									邀请享免单
								</c:if>
								<c:if test="${orderDtl.singleActivityName == '9'}">
									优惠券
								</c:if>
								<c:if test="${orderDtl.singleActivityName == '10'}">
									助力减价
								</c:if>
							</c:when>
							<c:otherwise>
								商城
							</c:otherwise>
						</c:choose></td>
				</c:if>
				<td>${orderDtl.productPropDesc}</td>
				<td>${orderDtl.tagPrice}</td>
				<td>${orderDtl.salePrice}</td>
				<td>${orderDtl.quantity}</td>
				<td>${orderDtl.mchtPreferential}</td>
				<td>${orderDtl.freight}</td>
				<td>${orderDtl.platformPreferential}</td>
				<td>${orderDtl.integralPreferential}</td>
				<td>${orderDtl.payAmount}</td>
				<c:if test="${subOrderCustom.mchtType==1}">
					<%--<td>${orderDtl.commissionMoney}</td>--%>
					<td>${orderDtl.settlePrice}</td>
				</c:if>
				<td style="text-align:center;"><c:if
						test="${orderDtl.isSvipBuy=='0' || empty orderDtl.isSvipBuy}">否</c:if>
					<c:if test="${orderDtl.isSvipBuy=='1'}">是</c:if></td>
				<td><c:if test="${empty orderDtl.svipDiscount}">
					<span style="margin-left:30px;">-</span>
				</c:if> <fmt:formatNumber value="${orderDtl.svipDiscount}"
										  pattern="#.####"></fmt:formatNumber></td>
				<td><c:if test="${empty orderDtl.distributionAmount}">
					<span style="margin-left:30px;">-</span>
				</c:if>

						${orderDtl.distributionAmount}
				</td>
					<%-- <c:if test="${subOrderCustom.mchtType==2}">
			<c:forEach var="orderDtl" items="${orderDtlCustoms}"> 
	        <tr>
               	<td><img alt="" src="${pageContext.request.contextPath}/file_servelt${orderDtl.skuPic}" onclick="viewerPic(this.src)" style="width:60px;height:60px;"></td>
               	<td>${orderDtl.productBrandName}</td>
               	<td>
               		${orderDtl.productName}<br/>
               		商品ID：${orderDtl.productCode}<br/>
               		<c:if test="${(sessionScope.USER_SESSION.staffID == '1' or role89 == '89') and not empty orderDtl.orderProductSnapshotId }">
               			<a href="${pageContext.request.contextPath}/order/combine/showDetail.shtml?orderDtlId=${orderDtl.id}" target="_blank" >【快照详情】</a>
               		</c:if>
               		<br>快递：${orderDtl.orderDtlExpressName}&nbsp;&nbsp;快递单号：${orderDtl.dtlExpressNo}
               	</td>
               	<td>${orderDtl.productArtNo}</td>
               	<td>${orderDtl.productPropDesc}</td>
               	<td>${orderDtl.tagPrice}</td>
               	<td>${orderDtl.salePrice}</td>
               	<td>${orderDtl.quantity}</td>
               	<td>${orderDtl.mchtPreferential}</td>
               	<td>${orderDtl.freight}</td>
               	<td>${orderDtl.platformPreferential}</td>
               	<td>${orderDtl.integralPreferential}</td>
               	<td>${orderDtl.payAmount}</td>
               	<c:if test="${subOrderCustom.mchtType==1}">
               	<td>${orderDtl.commissionMoney}</td>
               	</c:if>
               	<td style="text-align:center;"><c:if test="${orderDtl.isSvipBuy=='0' || empty orderDtl.isSvipBuy}">否</c:if><c:if test="${orderDtl.isSvipBuy=='1'}">是</c:if></td>
               	<td><c:if test="${empty orderDtl.svipDiscount}"><span style="margin-left:30px;">-</span></c:if><fmt:formatNumber value="${orderDtl.svipDiscount}" pattern="#.####" ></fmt:formatNumber></td>
               	<%-- <c:if test="${subOrderCustom.mchtType==2}">
			<c:forEach var="orderDtl" items="${orderDtlCustoms}"> 
	        <tr>
               	<td><img alt="" src="${pageContext.request.contextPath}/file_servelt${orderDtl.skuPic}" onclick="viewerPic(this.src)" style="width:60px;height:60px;"></td>
               	<td>${orderDtl.productBrandName}</td>
               	<td>
               		${orderDtl.productName}<br/>
               		商品ID：<a href="javascript:viewProduct(${orderDtl.productId });">${orderDtl.productCode}</a><br/>
               		<c:if test="${(sessionScope.USER_SESSION.staffID == '1' or role89 == '89') and not empty orderDtl.orderProductSnapshotId }">
               			<a href="${pageContext.request.contextPath}/order/combine/showDetail.shtml?orderDtlId=${orderDtl.id}" target="_blank" >【快照详情】</a>
               		</c:if>
               		<br>快递：${orderDtl.orderDtlExpressName}&nbsp;&nbsp;快递单号：${orderDtl.dtlExpressNo}
               	</td>
               	<td>${orderDtl.productArtNo}</td>
               	<td>${orderDtl.productPropDesc}</td>
               	<td>${orderDtl.tagPrice}</td>
               	<td>${orderDtl.salePrice}</td>
               	<td>${orderDtl.quantity}</td>
               	<td>${orderDtl.mchtPreferential}</td>
               	<td>${orderDtl.freight}</td>
               	<td>${orderDtl.platformPreferential}</td>
               	<td>${orderDtl.integralPreferential}</td>
               	<td>${orderDtl.payAmount}</td>
               	<c:if test="${subOrderCustom.mchtType==1}">
               	<td>${orderDtl.commissionMoney}</td>
               	</c:if>
               	<td style="text-align:center;"><c:if test="${orderDtl.isSvipBuy=='0' || empty orderDtl.isSvipBuy}">否</c:if><c:if test="${orderDtl.isSvipBuy=='1'}">是</c:if></td>
               	<td><c:if test="${empty orderDtl.svipDiscount}"><span style="margin-left:30px;">-</span></c:if><fmt:formatNumber value="${orderDtl.svipDiscount}" pattern="#.####" ></fmt:formatNumber></td>
               	<%-- <c:if test="${subOrderCustom.mchtType==2}">
               	<td>${orderDtl.popCommissionRate}</td>
               	<td>${orderDtl.commissionAmount}</td>
               	<td>${orderDtl.settleAmount}</td>
               	</c:if> --%>
				<td><c:choose>
					<c:when test="${empty orderDtl.serviceTypeDesc}">
						<c:if
								test="${orderDtl.subOrderStatus == '1' or orderDtl.subOrderStatus == '2' or orderDtl.subOrderStatus == '6' }">
							<c:if test="${empty orderDtl.singleProductActivityType}">
								<c:if test="${orderDtl.subOrderStatus != '6' }">
									<a
											href="javascript:addRefundOrder(${orderDtl.id }, ${orderDtl.subOrderStatus }, ${orderDtl.payAmount });">
										【发起】 </a>
								</c:if>
								<c:if
										test="${orderDtl.subOrderStatus == '6' and receiptDateTime <= receiptEndDateTime }">
									<a
											href="javascript:addRefundOrder(${orderDtl.id }, ${orderDtl.subOrderStatus }, ${orderDtl.payAmount });">
										【发起】 </a>
								</c:if>
							</c:if>
							<c:if
									test="${not empty orderDtl.singleProductActivityType && orderDtl.singleProductActivityType!='7' && orderDtl.singleProductActivityType!='8'}">
								<c:if test="${orderDtl.subOrderStatus != '6' }">
									<a
											href="javascript:addRefundOrder(${orderDtl.id }, ${orderDtl.subOrderStatus }, ${orderDtl.payAmount });">
										【发起】 </a>
								</c:if>
								<c:if
										test="${orderDtl.subOrderStatus == '6' and receiptDateTime <= receiptEndDateTime }">
									<a
											href="javascript:addRefundOrder(${orderDtl.id }, ${orderDtl.subOrderStatus }, ${orderDtl.payAmount });">
										【发起】 </a>
								</c:if>
							</c:if>
						</c:if>
						<c:if
								test="${orderDtl.subOrderStatus != '1' and orderDtl.subOrderStatus != '2' and orderDtl.subOrderStatus != '6' }">
							<span style="margin-left:30px;">无</span>
						</c:if>
					</c:when>
					<c:otherwise>
						<a
								href="javascript:viewAfterServiceDetail(${orderDtl.customerServiceId }, '${orderDtl.serviceTypeDesc }');">
								${orderDtl.serviceTypeDesc}<br>${orderDtl.serviceStatusDesc}
						</a>
						<c:if test="${subOrderCustom.status ne '3' }">
							<c:if
									test="${orderDtl.serviceStatusDesc eq '已撤销' || orderDtl.serviceProStatus eq 'A3' || orderDtl.serviceProStatus eq 'B3' || orderDtl.serviceProStatus eq 'C3' || orderDtl.serviceProStatus eq 'C6'}">
								<c:if test="${empty orderDtl.singleProductActivityType}">
									<a
											href="javascript:addCustomerServiceOrder(${orderDtl.customerServiceId}, ${orderDtl.subOrderStatus }, ${orderDtl.payAmount});">
										<br>【再次发起】
									</a>
								</c:if>
								<c:if
										test="${not empty orderDtl.singleProductActivityType && orderDtl.singleProductActivityType!='7' && orderDtl.singleProductActivityType!='8'}">
									<a
											href="javascript:addCustomerServiceOrder(${orderDtl.customerServiceId}, ${orderDtl.subOrderStatus }, ${orderDtl.payAmount});">
										<br>【再次发起】
									</a>
								</c:if>
							</c:if>
						</c:if>
					</c:otherwise>
				</c:choose></td>
					<%-- <td><c:if test="${not empty orderDtl.distributionAmount}">${orderDtl.distributionAmount}</c:if></td> --%>
				<td><c:if test="${empty orderDtl.interventionOrderId}">
					<span style="margin-left:30px;">无</span>
				</c:if> <c:if test="${not empty orderDtl.interventionOrderId}">
					<a
							href="javascript:showInterventionOrder(${orderDtl.interventionOrderId}, 1);">【查看】</a>
				</c:if></td>
				<td><c:if test="${!empty orderDtl.appealOrderId}">
					<a href="javascript:;"
					   onclick="viewAppealOrder(${orderDtl.appealOrderId});">${orderDtl.appealStatusDesc}</a>
				</c:if> <c:if
						test="${empty orderDtl.appealOrderId and subOrderCustom.status != '6'}">
					<a href="javascript:addComplain(${orderDtl.id})"
					   class="table-title-link">【发起】</a>
				</c:if></td>
			</tr>
		</c:forEach>
	</table>
</div>

<div class="title-top">
	<div class="table-title">
		<span>订单优惠</span>
	</div>
	<table class="gridtable marT10">
		<tr>
			<td class="title" style="width: 80px;">优惠归类</td>
			<td class="title" style="width: 80px;">使用范围</td>
			<td class="title" style="width: 80px;">优惠类型</td>
			<td class="title" style="width: 80px;">优惠内容</td>
			<td class="title" style="width: 80px;">优惠金额</td>
			<td class="title" style="width: 80px;">商品优惠金额</td>
		</tr>
		<c:forEach var="orderPreferentialInfo"
				   items="${orderPreferentialInfoCustoms}">
			<tr>
				<td>${orderPreferentialInfo.belongDesc}</td>
				<td>${orderPreferentialInfo.rangDesc} </td>
				<td>${orderPreferentialInfo.preferentialTypeDesc}  <c:if test="${orderPreferentialInfo.preferentialType==1 }">(${orderPreferentialInfo.preferentialId })</c:if>  </td>
				<td>${orderPreferentialInfo.preferentialContent}</td>
				<td>${orderPreferentialInfo.totalAmout}</td>
				<td style="word-break: break-all;">${orderPreferentialInfo.contentProduct}</td>
			</tr>
		</c:forEach>
	</table>
</div>

<c:if
		test="${subOrderCustom.status == '3' or subOrderCustom.status == '6' }">
	<div class="title-top">
		<div class="table-title">
			<span class="marR10">客户评价</span> <a
				href="javascript:commentDown(${subOrderCustom.id});"
				class="table-title-link glyphicon glyphicon-chevron-down comment-down">展开</a>
			<a href="javascript:commentUp();"
			   class="table-title-link glyphicon glyphicon-chevron-up comment-up"
			   style="display: none;">收起</a>
		</div>
		<div class="comment-div"
			 style="border: 1px solid black; padding: 10px; display: none;">
			<div style="width: 100%;">
				<div style="margin-bottom: 10px;">
					<span class="glyphicon glyphicon-home"></span> <span
						style="margin-left: 2px;">店铺评分</span>
				</div>
				<div>
					<div style="float: left;margin: 5px 10px 5px 0px;">卖家服务</div>
					<div class="starBox" style="margin: 5px 0px 5px 0px;">
						<ul class="star">
							<li><a href="javascript:void(0)" class="one-star"
								   style="cursor: default;">1</a></li>
							<li><a href="javascript:void(0)" class="two-stars"
								   style="cursor: default;">2</a></li>
							<li><a href="javascript:void(0)" class="three-stars"
								   style="cursor: default;">3</a></li>
							<li><a href="javascript:void(0)" class="four-stars"
								   style="cursor: default;">4</a></li>
							<li><a href="javascript:void(0)" class="five-stars"
								   style="cursor: default;">5</a></li>
						</ul>
						<div class="current-rating mcht-score"></div>
					</div>
					<div style="clear:both; height: 0; line-height: 0; font-size: 0"></div>
				</div>
				<div>
					<div style="float: left;margin: 5px 10px 5px 0px;">物流服务</div>
					<div class="starBox" style="margin: 5px 0px 5px 0px;">
						<ul class="star">
							<li><a href="javascript:void(0)" class="one-star"
								   style="cursor: default;">1</a></li>
							<li><a href="javascript:void(0)" class="two-stars"
								   style="cursor: default;">2</a></li>
							<li><a href="javascript:void(0)" class="three-stars"
								   style="cursor: default;">3</a></li>
							<li><a href="javascript:void(0)" class="four-stars"
								   style="cursor: default;">4</a></li>
							<li><a href="javascript:void(0)" class="five-stars"
								   style="cursor: default;">5</a></li>
						</ul>
						<div class="current-rating wuliu-score"></div>
					</div>
					<div style="clear:both; height: 0; line-height: 0; font-size: 0"></div>
				</div>
				<div>
					<div style="float: left;margin: 5px 10px 5px 0px;">评价时间</div>
					<div class="starBox comment-create"
						 style="margin: 5px 0px 5px 0px;"></div>
					<div style="clear:both; height: 0; line-height: 0; font-size: 0"></div>
				</div>
			</div>
			<div class="comment-content-div"></div>
		</div>
	</div>
</c:if>

<div class="title-top">
	<div class="table-title">
		<span class="marR10">商家备注</span> <a
			href="javascript:mchtRemarkEdit(${subOrderCustom.id})"
			class="table-title-link">【修改】</a>
	</div>
	<table class="gridtable marT10">
		<tr>
			<td class="title">备注内容</td>
		</tr>
		<tr>
			<td><span style="padding:0 10px;font-size:20px;"
					  class="glyphicon glyphicon-flag color-${subOrderCustom.remarksColor}"
					  aria-hidden='true'></span> ${subOrderCustom.remarks}</td>
		</tr>
	</table>
</div>

<div class="title-top">
	<div class="table-title">
		<span class="marR10">商家备注日志</span>
	</div>
	<table class="gridtable marT10">
		<tr>
			<td class="title" width="200px;">时间</td>
			<td class="title" width="200px;">备注人</td>
			<td class="title">备注内容</td>
		</tr>
		<c:forEach var="mchtRemarksLog" items="${mchtRemarksLogs}">
			<tr>
				<td><fmt:formatDate value="${mchtRemarksLog.createDate}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><c:if test="${mchtRemarksLog.operatorType == '1'}">
					${mchtRemarksLog.mchtUserCode}
				</c:if> <c:if test="${mchtRemarksLog.operatorType == '2'}">
					${mchtRemarksLog.staffName}
				</c:if></td>
				<td><c:if test="${not empty mchtRemarksLog.remarksColor}">
							<span style="padding:0 10px;font-size:20px;"
								  class="glyphicon glyphicon-flag color-${mchtRemarksLog.remarksColor}"
								  aria-hidden='true'></span>
				</c:if> ${mchtRemarksLog.remarks}</td>
			</tr>
		</c:forEach>
	</table>
</div>

<div class="title-top">
	<div class="table-title">
		<span class="marR10">订单备注</span> <a
			href="javascript:remarkEdit(${subOrderCustom.id})"
			class="table-title-link">【添加】</a>
		<c:if test="${not empty afterSaleGroupRole }">
			<a href="javascript:addOrUpdateOrderAbnormal(${subOrderCustom.id});"
			   class="table-title-link">【标记异常订单】</a>
			<c:forEach var="orderAbnormalLogCustom"
					   items="${orderAbnormalLogCustomList }">
					<span style="margin-left: 20px;"> <c:if
							test="${orderAbnormalLogCustom.abnormalType eq '3'}">
						已超时【发货跟单：<c:if
							test="${empty orderAbnormalLogCustom.followStatus || orderAbnormalLogCustom.followStatus eq '1'}">
								<a
										href="javascript:updateOrderAbnormalLog(${orderAbnormalLogCustom.id }, ${subOrderCustom.id});">未处理</a>
					</c:if>
						<c:if
								test="${not empty orderAbnormalLogCustom.followStatus && orderAbnormalLogCustom.followStatus ne '1'}">
							${orderAbnormalLogCustom.followStatusDesc}
						</c:if>
						<c:if test="${not empty orderAbnormalLogCustom.followByName}">
							（${orderAbnormalLogCustom.followByName}）
						</c:if>】
					</c:if> <c:if test="${orderAbnormalLogCustom.abnormalType eq '4'}">
						虚假发货 【虚发跟单：<c:if
							test="${empty orderAbnormalLogCustom.followStatus || orderAbnormalLogCustom.followStatus eq '1'}">
								<a
										href="javascript:updateOrderAbnormalLog(${orderAbnormalLogCustom.id }, ${subOrderCustom.id});">未处理</a>
					</c:if>
						<c:if
								test="${not empty orderAbnormalLogCustom.followStatus && orderAbnormalLogCustom.followStatus ne '1'}">
							${orderAbnormalLogCustom.followStatusDesc}
						</c:if>
						<c:if test="${not empty orderAbnormalLogCustom.followByName}">
							（${orderAbnormalLogCustom.followByName}）
						</c:if>】
					</c:if> <c:if test="${orderAbnormalLogCustom.abnormalType eq '1'}">
						缺货【跟单：<c:if
							test="${empty orderAbnormalLogCustom.followStatus || orderAbnormalLogCustom.followStatus eq '1'}">
								<a
										href="javascript:updateOrderAbnormalLog(${orderAbnormalLogCustom.id }, ${subOrderCustom.id});">未处理</a>
					</c:if>
						<c:if
								test="${not empty orderAbnormalLogCustom.followStatus && orderAbnormalLogCustom.followStatus ne '1'}">
							${orderAbnormalLogCustom.followStatusDesc}
						</c:if>
						<c:if test="${not empty orderAbnormalLogCustom.followByName}">
							（${orderAbnormalLogCustom.followByName}）
						</c:if>】
					</c:if> <c:if test="${orderAbnormalLogCustom.abnormalType eq '2'}">
						其他【跟单：<c:if
							test="${empty orderAbnormalLogCustom.followStatus || orderAbnormalLogCustom.followStatus eq '1'}">
								<a
										href="javascript:updateOrderAbnormalLog(${orderAbnormalLogCustom.id }, ${subOrderCustom.id});">未处理</a>
					</c:if>
						<c:if
								test="${not empty orderAbnormalLogCustom.followStatus && orderAbnormalLogCustom.followStatus ne '1'}">
							${orderAbnormalLogCustom.followStatusDesc}
						</c:if>
						<c:if test="${not empty orderAbnormalLogCustom.followByName}">
							（${orderAbnormalLogCustom.followByName}）
						</c:if>】
					</c:if>
					</span>
			</c:forEach>
			<span style="margin-left: 20px;" class="follow-name"> 订单跟单员：<c:if
					test="${not empty subOrderCustom.followBy }">${subOrderCustom.followByName }</c:if>
					<c:if test="${empty subOrderCustom.followBy }">
						<a href="javascript:;"
						   onclick="addShamFollowBy(${subOrderCustom.id});">领取</a>
					</c:if>
				</span>
		</c:if>
	</div>
	<table class="gridtable marT10">
		<tr>
			<td class="title" width="200px;">时间</td>
			<td class="title" width="200px;">备注人</td>
			<td class="title">备注内容</td>
		</tr>
		<c:forEach var="remarksLog" items="${remarksLogCustoms}">
			<tr>
				<td><fmt:formatDate value="${remarksLog.createDate}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${remarksLog.staffName}</td>
				<td><span style="padding:0 10px;font-size:20px;"
						  class="glyphicon glyphicon-flag color-${remarksLog.remarksColor}"
						  aria-hidden='true'></span> ${remarksLog.remarks}</td>
			</tr>
		</c:forEach>
	</table>
</div>

<div class="title-top">
	<div class="table-title">
		<span>订单状态日志</span>
	</div>
	<table class="gridtable marT10">
		<tr>
			<td class="title" width="200px;">时间</td>
			<td class="title" width="200px;">订单状态</td>
			<td class="title">备注内容</td>
		</tr>
		<c:forEach var="orderLog" items="${orderLogCustoms}">
			<tr>
				<td><fmt:formatDate value="${orderLog.createDate}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${orderLog.statusDesc}</td>
				<td>${orderLog.remarks}</td>
			</tr>
		</c:forEach>
	</table>
</div>

<div class="title-top">
	<div class="table-title">
		<span style="float: left;">附件管理</span>
		<div style="margin-left: 65px;">
			<input style="position:absolute; opacity:0;" type="file"
				   id="uploadFile" name="uploadFile" onchange="ajaxFileUpload(this);" />
			<input type="button" style="width: 45px;" value="上传">
		</div>
	</div>
	<table class="gridtable marT10">
		<tr>
			<td class="title" width="200px;">时间</td>
			<td class="title" width="200px;">上传人</td>
			<td class="title">上传附件</td>
		</tr>
		<c:forEach var="subOrderAttachmentCustom"
				   items="${subOrderAttachmentCustoms}">
			<tr>
				<td><fmt:formatDate
						value="${subOrderAttachmentCustom.createDate}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><c:if
						test="${subOrderAttachmentCustom.creatorType == '1'}">
					${subOrderAttachmentCustom.sysCreatorName}
				</c:if> <c:if
						test="${subOrderAttachmentCustom.creatorType == '2'}">
					${subOrderAttachmentCustom.mchtCreatorName}
				</c:if> （${subOrderAttachmentCustom.creatorTypeDesc}）</td>
				<td><a href="javascript:;" name="downLoadSubOrderAttachment"
					   subOrderAttachmentId="${subOrderAttachmentCustom.id}"
					   fileName="${subOrderAttachmentCustom.name}"
					   filePath="${subOrderAttachmentCustom.attachmentPath}">${subOrderAttachmentCustom.name}</a></td>
			</tr>
		</c:forEach>
	</table>
</div>

<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures"
	style="display: none;"></ul>
</body>
</html>
