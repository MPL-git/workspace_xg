<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
	
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />

<style type="text/css">
body {
	font-size: 13px;
	padding: 10px;
}

td input,td select {
	border: 1px solid #AECAF0;
}

.table-title {
	font-size: 14px;
	color: #333333;
	font-weight: 600;
}

.title-top {
	padding-top: 20px;
}

.marR10 {
	margin-right: 10px;
}

.marT10 {
	margin-top: 10px;
}

.marB05 {
	margin-bottom: 5px;
}

.table-title-link {
	color: #1B17EE;
}

.baseInfo tr td span,.baseInfo tr td img {
	margin-right: 20px;
}

tr.title-first td {
	background-color: #DCF9FF;
}

tr.title-second td {
	background-color: #FFFB94;
	font-size: 15px;
}

tr.title-second td span {
	margin-right: 0px;
}

tr.title-third td {
	font-size: 15px;
}

.baseInfo tr td span.amtSpan {
	margin-right: 0;
	color: #FF0000;
}

tr.title-fourth td {
	background-color: #DCF9FF;
	text-align: center;
}

.LogInfo tr td img {
	margin: 10px 20px 0 0;
}

.color01 {
	color: #FF0000;
}

.color02 {
	color: #797979;
}
</style>
<style type="text/css">
	.img-div {
		display: inline-block;
		vertical-align: middle;
	}
	.img-box {
		position: relative;
		width: 80px;
		height: 80px;
		border: 1px solid #6B6B6B;
		margin-left: 20px;
		display: inline-block;
	}
	.img-pic {
		width: 100%;
		height: 100%;
		display: block;
	}
	.img-box:hover .top-box {
		display: block;
	}
	.top-box {
		display: none;
		position: absolute;
		top: 0;
		left: 0;
		z-index: 1;
		width: 100%;
		height: 20px;
		background: rgba(0, 0, 0, .5);
	}
	.top-box span {
		position: relative;
		float: right;
		width: 20px;
		height: 20px;
		border-radius: 10px;
		background: red;
		cursor: pointer;
	}
	.top-box span:after,
	.top-box span:before {
		position: absolute;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		content: '';
		width: 16px;
		height: 4px;
		margin: auto;
		background: white;
		border-radius: 2px;
		transform: rotate(45deg);
	}
	.top-box span:before {
		transform: rotate(-45deg);
	}
	.add-box {
		position: relative;
		width: 80px;
		height: 80px;
		border: 1px solid #6B6B6B;
		margin-left: 20px;
		display: inline-block;
		vertical-align: middle;
		text-align: center;
	}
	.file-btn {
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		z-index: 1;
		opacity: 0; 
	}
	.add-span {
		height: 80px;
		line-height: 80px;
	}
</style>
<script type="text/javascript">
var viewerPictures;
var appealPlatformRemarksPicTd;
var appealOrderLogDetail;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewerPictures'), {});
	appealPlatformRemarksPicTd = new Viewer(document.getElementById('appealPlatformRemarksPicTd'), {});
	appealOrderLogDetail = new Viewer(document.getElementById('appealOrderLogDetail'), {});
	
	$("#savePlatformRemarks").bind('click',function(){
		var platformRemarks = $.trim($("#platformRemarks").val());
		var appealOrderId = $("#appealOrderId").val();
		var picNameA = $("#picNameA").val()==undefined?"":$("#picNameA").val();
		var picNameB = $("#picNameB").val()==undefined?"":$("#picNameB").val();
		var picNameC = $("#picNameC").val()==undefined?"":$("#picNameC").val();
		if(platformRemarks == ""){
			commUtil.alertError("备注不能为空");
			return false;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/appealOrder/savePlatformRemarks.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {id:appealOrderId, platformRemarks:platformRemarks, picNameA:picNameA, picNameB:picNameB, picNameC:picNameC},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					$("#savePlatformRemarksTr").hide();
					$("#platformRemarks").attr("readonly",true);
					commUtil.alertSuccess("备注成功！");
					var html = "";
					if(picNameA != "") {
						html += "<div style='position: relative;width: 80px;height: 80px;border: 1px solid #6B6B6B;margin-left: 20px;display: inline-block;'>"
									+"<img alt='' src='${pageContext.request.contextPath}/file_servelt"+ picNameA +"' style='width: 100%;height: 100%;display: block;' >"
									+"</div>";
					}
					if(picNameB != "") {
						html += "<div style='position: relative;width: 80px;height: 80px;border: 1px solid #6B6B6B;margin-left: 20px;display: inline-block;'>"
							+"<img alt='' src='${pageContext.request.contextPath}/file_servelt"+ picNameB +"' style='width: 100%;height: 100%;display: block;' >"
							+"</div>";
					}
					if(picNameC != "") {
						html += "<div style='position: relative;width: 80px;height: 80px;border: 1px solid #6B6B6B;margin-left: 20px;display: inline-block;'>"
							+"<img alt='' src='${pageContext.request.contextPath}/file_servelt"+ picNameC +"'style='width: 100%;height: 100%;display: block;' >"
							+"</div>";
					}
					$("#appealPlatformRemarksPicTd").html(html);
					appealPlatformRemarksPicTd.destroy();
					appealPlatformRemarksPicTd = new Viewer(document.getElementById('appealPlatformRemarksPicTd'), {});
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
				
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
	
	$("#savePlatformProcessor").bind('click',function(){
		var appealOrderId = $("#appealOrderId").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/appealOrder/savePlatformProcessor.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":appealOrderId},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					$("#savePlatformProcessorTd").hide();
					$("#saveAppealLogTd").show();
					$("#logPicTr").show();
					commUtil.alertSuccess("领取成功");
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
				
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
	
	$("#saveAppealLog").bind('click',function(){
		var appealOrderId = $("#appealOrderId").val();
		var serviceStatus = $("input[name='serviceStatus']:checked").val();
		var content = $.trim($("#content").val());
		var status = $("input[name='status']:checked").val();
		var userType = "2";
		if(serviceStatus == '1') {
			if(content == '') {
				commUtil.alertError("回复内容不能为空！");
				return;
			}
			if($("input[name='status']:checked").length == 0) {
				commUtil.alertError("请选择待回复方！");
				return;
			}
		}
		var logPicNameA = $("#logPicNameA").val()==undefined?"":$("#logPicNameA").val();
		var logPicNameB = $("#logPicNameB").val()==undefined?"":$("#logPicNameB").val();
		var logPicNameC = $("#logPicNameC").val()==undefined?"":$("#logPicNameC").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/appealOrder/saveAppealLog.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {appealOrderId:appealOrderId, serviceStatus:serviceStatus, content:content, status:status, userType:userType, logPicNameA:logPicNameA, logPicNameB:logPicNameB, logPicNameC:logPicNameC},
			timeout : 30000,
			success : function(data) {
				if(data.code == 200) {
					parent.childFrameFun(data.map.appealOrder.id, data.map.appealOrder.serviceStatus);
					if(data.map.appealOrder.serviceStatus == 2) {
						$("#serviceStatusDiv").hide();
					}else {
						$("#content").val("");
						$("input[name='status']").each(function(){
							$(this).attr("checked",false);
						});
						var html = "<tr><td>"+data.map.appealLog.userName+"<br>"+data.appealLogCreateDate+"</td><td colspan='3'>"+data.map.appealLog.content+"<br>";
						var str = "";
						for(var i=0;i<data.map.appealLogPicList.length;i++) {
							str += "<img src='${pageContext.request.contextPath}/file_servelt"+data.map.appealLogPicList[i].pic+"' width='80' height='80'>";
						}
						html += str+"</td></tr>";
						$("#appealOrderLogDetail").append(html);
						$(".img-logPicImg").html("");
						$("#add-logPicImg").show();
						appealOrderLogDetail.destroy();
						appealOrderLogDetail = new Viewer(document.getElementById('appealOrderLogDetail'), {});
					}
					commUtil.alertSuccess(data.msg);
				}else {
					commUtil.alertError(data.msg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
	
	
});

function viewSubDetail(id) {
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
		title: "子订单详情",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/order/sub/detail.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function viewWuliu(subOrderId) {
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()*0.35,
		title: "查看物流动态",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/order/afterService/viewWuliu.shtml?subOrderId=" + subOrderId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function viewDetail(id) {
	$.ligerDialog.open({
 		height: $(window).height()-50,
		width: $(window).width()-100,
		title: "子订单详情",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/order/sub/detail.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

//图片上传
function ajaxFileUploadPicFile(statusImg) {
    $.ajaxFileUpload({
    	url: '${pageContext.request.contextPath}/service/common/ajax_upload.shtml?fileType=1',
		secureuri: false,
		fileElementId: statusImg,
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				if(statusImg == 'picImg') {
					addPicImg(result.FILE_PATH);
				}else if(statusImg == 'logPicImg') {
					addLogPicImg(result.FILE_PATH);
				}
			} else {
				commUtil.alertError(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			commUtil.alertError("服务异常！");
		}
	});
}

function serviceStatusFun(val) {
	if(val == '1') {
		$("#contentTr").show();
		$("#logPicTr").show();
		$("#statusTr").show();
	}
	if(val == '2') {
		$("#contentTr").hide();
		$("#logPicTr").hide();
		$("#statusTr").hide();
	}
}

function addPicImg(pic) {
	if($(".picName").length == 2) {
		$("#add-picImg").hide();
	}
	if($("[name='picNameA']").length == 0) {
		var html = $(".img-picImg").html();
		var str = "<div class='img-box' >"
			+"<img class='img-pic' alt='' src='${pageContext.request.contextPath}/file_servelt"+ pic +"' >"
			+"<div class='top-box'><span class='top-delete' onclick='delPicImg(this)'></span></div>"
			+"<input type='hidden' class='picName' id='picNameA' name='picNameA' value='"+ pic +"'>"
			+"</div>";
		html += str;
		$(".img-picImg").html(html);
	}else if($("[name='picNameB']").length == 0) {
		var html = $(".img-picImg").html();
		var str = "<div class='img-box' >"
			+"<img class='img-pic' alt='' src='${pageContext.request.contextPath}/file_servelt"+ pic +"' >"
			+"<div class='top-box'><span class='top-delete' onclick='delPicImg(this)'></span></div>"
			+"<input type='hidden' class='picName' id='picNameB' name='picNameB' value='"+ pic +"'>"
			+"</div>";
		html += str;
		$(".img-picImg").html(html);
	}else if($("[name='picNameC']").length == 0) {
		var html = $(".img-picImg").html();
		var str = "<div class='img-box' >"
			+"<img class='img-pic' alt='' src='${pageContext.request.contextPath}/file_servelt"+ pic +"' >"
			+"<div class='top-box'><span class='top-delete' onclick='delPicImg(this)'></span></div>"
			+"<input type='hidden' class='picName' id='picNameC' name='picNameC' value='"+ pic +"'>"
			+"</div>";
		html += str;
		$(".img-picImg").html(html);
	}
}
function delPicImg(obj) {
	$(obj).parent().parent().remove();
	if($(".picName").length == 2) {
		$("#add-picImg").show();
	}
}
function addLogPicImg(pic) {
	if($(".logPicName").length == 2) {
		$("#add-logPicImg").hide();
	}
	if($("[name='logPicNameA']").length == 0) {
		var html = $(".img-logPicImg").html();
		var str = "<div class='img-box' >"
			+"<img class='img-pic' alt='' src='${pageContext.request.contextPath}/file_servelt"+ pic +"' >"
			+"<div class='top-box'><span class='top-delete' onclick='delLogPicImg(this)'></span></div>"
			+"<input type='hidden' class='logPicName' id='logPicNameA' name='logPicNameA' value='"+ pic +"'>"
			+"</div>";
		html += str;
		$(".img-logPicImg").html(html);
	}else if($("[name='logPicNameB']").length == 0) {
		var html = $(".img-logPicImg").html();
		var str = "<div class='img-box' >"
			+"<img class='img-pic' alt='' src='${pageContext.request.contextPath}/file_servelt"+ pic +"' >"
			+"<div class='top-box'><span class='top-delete' onclick='delLogPicImg(this)'></span></div>"
			+"<input type='hidden' class='logPicName' id='logPicNameB' name='logPicNameB' value='"+ pic +"'>"
			+"</div>";
		html += str;
		$(".img-logPicImg").html(html);
	}else if($("[name='logPicNameC']").length == 0) {
		var html = $(".img-logPicImg").html();
		var str = "<div class='img-box' >"
			+"<img class='img-pic' alt='' src='${pageContext.request.contextPath}/file_servelt"+ pic +"' >"
			+"<div class='top-box'><span class='top-delete' onclick='delLogPicImg(this)'></span></div>"
			+"<input type='hidden' class='logPicName' id='logPicNameC' name='logPicNameC' value='"+ pic +"'>"
			+"</div>";
		html += str;
		$(".img-logPicImg").html(html);
	}
}
function delLogPicImg(obj) {
	$(obj).parent().parent().remove();
	if($(".logPicName").length == 2) {
		$("#add-logPicImg").show();
	}
}


//创建工单
function addWork(id) {
	 $.ligerDialog.open({
			height: 600,
			width: 950,
			title: "创建工单",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/appealOrder/addappealOrderWork.shtml?id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
	  });
 }

</script>
<html>
<body>
	<div class="title-top">
		<div class="table-title">
			<span class="marR10">订单信息</span>
		</div>
		<table class="gridtable marT10">
          	<tr>
              <td class="title">订单状态</td><td>${subOrderCustom.statusDesc}</td>
              <td class="title">取消原因</td><td>${subOrderCustom.cancelReason}</td>
              <td class="title">取消时间</td><td><fmt:formatDate value="${subOrderCustom.cancelDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
              <td class="title">子订单编号</td><td>${subOrderCustom.subOrderCode}</td>
		</tr>
		<tr>
              <td class="title">订单金额</td><td>${subOrderCustom.amount}</td> 
              <td class="title">客户付款渠道</td><td>${subOrderCustom.paymentName}</td>
              <td class="title">订单渠道</td><td>${subOrderCustom.sourceClientDesc}</td>
              <td class="title">母订单号</td><td>${subOrderCustom.combineOrderCode}</td>
		</tr> 
        <tr>
              <td class="title">下单时间</td><td><fmt:formatDate value="${subOrderCustom.orderCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
              <td class="title">付款时间</td><td><fmt:formatDate value="${subOrderCustom.orderPayDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
              <td class="title">发货时间</td><td id="deliveryDate"><fmt:formatDate value="${subOrderCustom.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
              <td class="title">收货时间</td><td><fmt:formatDate value="${subOrderCustom.completeDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
		<tr>
              <td class="title">收货人</td><td>${subOrderCustom.receiverName}</td>
              <td class="title">联系电话</td><td id="receiverPhone"></td>
              <td class="title">用户ID</td><td>${subOrderCustom.memberId}</td> 
              <td class="title">用户昵称</td><td>${subOrderCustom.memberNick}</td>
		</tr> 
        <tr>
              <td class="title">快递名称</td><td>${subOrderCustom.expressName}</td> 
              <td class="title">快递单号</td>
              <td>
              		<a href="javascript:;" onclick="viewWuliu(${subOrderCustom.id});">${subOrderCustom.expressNo}</a>
              </td>
              <td class="title">收货地址</td><td colspan="3">${subOrderCustom.receiverAddress}</td>
        </tr>
        <tr>
              <td class="title">客户备注</td><td colspan="7">${subOrderCustom.combineOrderRemarks}</td>
        </tr>
        </table>
	</div>

	<div class="title-top">
		<input type="hidden" id="appealOrderId" value="${appealOrder.id}">
		<div class="table-title">
			<span class="marR10">商家信息</span>
		</div>
		<table class="gridtable baseInfo">
			<tr class="title-first">
				<td>商家</td>
				<td>${mchtInfo.companyName}</td>
				<td>商家序号</td>
				<td>${mchtInfo.mchtCode}</td>
				<td>对接运营</td>
				<td>${contactName}</td>
			</tr>
			<tr class="title-second">
				<td>商家入驻时间</td>
				<td><fmt:formatDate value="${mchtInfo.cooperateBeginDate}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>30天内违规次数</td>
				<td>${thirtyDaysViolateCount}</td>
				<td>总违规次数</td>
				<td>${totalViolateCount}</td>
			</tr>
		</table>
	</div>

	<div class="title-top">
		<div class="table-title">
			<span class="marR10">投诉信息</span>
		</div>
		<table class="gridtable marT10 baseInfo">
			<tr class="title-first">
				<td>投诉编号</td>
				<td>${appealOrder.orderCode}</td>
				<td>订单编号</td>
				<td>
					<a href="javascript:viewDetail(${subOrderCustom.id });">${subOrderCustom.subOrderCode}</a>
				</td>
				<td>投诉类型</td>
				<td>${appealTypeDesc}</td>
			</tr>
			<tr class="title-second">
				<td>投诉人</td>
				<td>${appealOrder.userName}</td>
				<td>状态</td>
				<td>${statusDesc}</td>
				<td>所属活动</td>
				<td>${belongActivity}</td>
			</tr>
			<tr>
				<td>投诉时间</td>
				<td><fmt:formatDate value="${appealOrder.createDate}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>最后更新时间</td>
				<td><fmt:formatDate value="${appealOrder.updateDate}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>责任方</td>
				<td>${liabilityDesc}</td>
			</tr>
		</table>
	</div>

	<div class="title-top">
		<div class="table-title">
			<span class="marR10">商品信息</span>
		</div>
		<table class="gridtable">
			<tr class="title-first">
				<td>商品信息</td>
				<td>货号</td>
				<td>商品价格</td>
				<td>优惠金额</td>
				<td>实际付款金额</td>
			</tr>
			<tr>
				<td id="viewerPictures"><img style="float:left;margin:8px;"
					src="${pageContext.request.contextPath}/file_servelt${orderDtl.skuPic}"
					width="80" height="80"> <span
					style="display:block;text-align:left;margin-top:8px;"> <span
						style="display:block;margin-bottom:4px;">${orderDtl.brandName}<br></span>
						<span style="display:block;margin-bottom:4px;">${orderDtl.productName}<br></span>
						<span style="display:block;margin-bottom:4px;">${orderDtl.artNo}<br></span>
				</span></td>
				<td>${orderDtl.artNo}</td>
				<td>${orderDtl.salePrice}</td>
				<td><span style="display:block;text-align:left;margin-top:8px;">
						<c:forEach items="${orderPreferentialInfos}"
							var="orderPreferentialInfo">
							<span style="display:block;margin-bottom:4px;"> <c:if
									test="${orderPreferentialInfo.preferentialType eq '1'}">
	           					优惠券
	           				</c:if> <c:if
									test="${orderPreferentialInfo.preferentialType eq '2'}">
	           					满减
	           				</c:if> <c:if
									test="${orderPreferentialInfo.preferentialType eq '3'}">
	           					满赠
	           				</c:if> <c:if
									test="${orderPreferentialInfo.preferentialType eq '4'}">
	           					对买多折
	           				</c:if> <c:if
									test="${orderPreferentialInfo.preferentialType eq '5'}">
	           					积分优惠
	           				</c:if> :${orderPreferentialInfo.preferentialAmount}元<br>
							</span>
						</c:forEach>
				</span></td>
				<td>${orderDtl.payAmount}元</td>
			</tr>
		</table>
	</div>

	<div class="title-top">
		<div class="table-title">
			<span class="marR10">客服备注 </span>
			<c:if test="${woRks}">
			<span><a href="javascript:addWork(${appealOrder.id});">【创建工单】</a></span>
			</c:if>
		</div>
		
		<table class="gridtable marT10">
          	<tr>
	            <td class="title" style="width: 25%">备注</td>
	            <td>
	            	<textarea rows="5" cols="100" id="platformRemarks" name="platformRemarks"
						<c:if test="${not empty appealOrder.platformRemarks}">readonly="readonly"</c:if>>${appealOrder.platformRemarks}</textarea>
	            </td>
			</tr>
          	<tr>
	            <td class="title" style="width: 25%">备注凭证</br>最多3张</td>
	            <td id="appealPlatformRemarksPicTd">
	            	<c:if test="${not empty appealOrder.platformRemarks or appealOrder.platformProcessor ne staffId }">
	            		<c:forEach items="${appealPlatformRemarksPicList }" var="appealPlatformRemarksPic" >
	            			<div style="position: relative;width: 80px;height: 80px;border: 1px solid #6B6B6B;margin-left: 20px;display: inline-block;">
	            				<img alt="" src="${pageContext.request.contextPath}/file_servelt${appealPlatformRemarksPic.pic}" style="width: 100%;height: 100%;display: block;" >
	            			</div>
	            		</c:forEach>
	            	</c:if>
	            	<c:if test="${empty appealOrder.platformRemarks and appealOrder.platformProcessor eq staffId }">
            			<div class="img-div img-picImg" >
	           			
		            	</div>
	           			<div id="add-picImg" class="add-box" >
	           				<input id="picImg" class="file-btn" type="file" name="file" onchange="ajaxFileUploadPicFile('picImg');" />
							<span class="add-span" >+</span>
						</div>
	            	</c:if>
				</td>
			</tr>
          	<tr id="savePlatformRemarksTr" style="<c:if test="${not empty appealOrder.platformRemarks or appealOrder.platformProcessor ne staffId}">display:none;</c:if>">
	            <td class="title" style="width: 25%">操作</td>
	            <td>
					<input type="button" style="cursor: pointer;width: 120px;height: 35px;background-color: rgba(22, 155, 213, 1);border: none;"
						value="提交" id="savePlatformRemarks" />
	            </td>
			</tr>
        </table>
	</div>

	<div class="title-top">
		<div class="table-title">
			<span class="marR10">处理过程 </span>
		</div>
		<table class="gridtable marT10 baseInfo" id="appealOrderLogDetail">
			<tr class="title-first">
				<td>发起人</td>
				<td>内容</td>
			</tr>
			<c:forEach items="${appealLogCustoms}" var="appealLogCustom">
				<tr>
					<td>${appealLogCustom.userName}<br> <fmt:formatDate
							value="${appealLogCustom.createDate}"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td colspan="3">${appealLogCustom.content}<br> <c:if
							test="${not empty appealLogCustom.pics}">
							<c:forEach items="${appealLogCustom.pics}" var="pic">
								<img src="${pageContext.request.contextPath}/file_servelt${pic}"
									width='80' height='80'>
							</c:forEach>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div class="title-top" id="serviceStatusDiv" <c:if test="${appealOrder.platformProcessor ne staffId or appealOrder.serviceStatus eq 2}">style="display: none;"</c:if> >
		<div class="table-title">
			<span class="marR10">客服回复 </span>
		</div>
		
		<table class="gridtable marT10">
          	<tr>
	            <td class="title" style="width: 25%">是否关闭投诉单</td>
	            <td>
	            	<span style="padding-left: 20px;">
	            		<input type="radio" name="serviceStatus" onclick="serviceStatusFun(this.value);" value="1" checked="checked" >否
	            	</span>
	            	<span style="padding-left: 20px;">
	            		<input type="radio" name="serviceStatus" onclick="serviceStatusFun(this.value);" value="2">是
	            	</span>
	            </td>
			</tr>
          	<tr id="contentTr">
	            <td class="title" style="width: 25%">回复内容</td>
	            <td>
					<textarea rows="5" cols="100" id="content" name="content"></textarea>
	            </td>
			</tr>
          	<tr id="logPicTr" style="<c:if test="${empty appealOrder.platformProcessor}">display:none;</c:if>" >
	            <td class="title" style="width: 25%">回复的凭证</br>最多3张</td>
	            <td>
	            	<div class="img-div img-logPicImg" >
	           			
	            	</div>
           			<div id="add-logPicImg" class="add-box" >
           				<input id="logPicImg" class="file-btn" type="file" name="file" onchange="ajaxFileUploadPicFile('logPicImg');" />
						<span class="add-span" >+</span>
					</div>
	            </td>
			</tr>
          	<tr id="statusTr">
	            <td class="title" style="width: 25%">待回复方</td>
	            <td>
	            	<span style="padding-left: 20px;">
	            		<input type="radio" name="status" value="2">待商家回复 
	            	</span>
	            	<span style="padding-left: 20px;">
	            		<input type="radio" name="status" value="1">待客户回复
	            	</span>
	            </td>
			</tr>
          	<tr>
	            <td class="title" style="width: 25%">操作</td>
	            <td id="savePlatformProcessorTd" style="<c:if test="${not empty appealOrder.platformProcessor}">display:none;</c:if>">
					<input type="button" style="cursor: pointer;width: 120px;height: 35px;background-color: rgba(22, 155, 213, 1);border: none;"
						value="领取" id="savePlatformProcessor" />
	            </td>
	            <td id="saveAppealLogTd" style="<c:if test="${empty appealOrder.platformProcessor}">display:none;</c:if>">
					<input type="button" style="cursor: pointer;width: 120px;height: 35px;background-color: rgba(22, 155, 213, 1);border: none;"
						value="提交" id="saveAppealLog" />
	            </td>
			</tr>
        </table>
	</div>
</body>
</html>
