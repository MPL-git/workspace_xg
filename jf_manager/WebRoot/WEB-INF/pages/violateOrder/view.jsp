<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/imageUploader.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/imageUpload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/utils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/clipboard.min.js"></script>

<style type="text/css">
body {font-size: 13px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:20px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
.table-title-link{color: #1B17EE;}
.baseInfo tr td span,.baseInfo tr td img{margin-right:20px;}
tr.title-first td{background-color:#DCF9FF;}
tr.title-second td{background-color:#FFFB94;font-size:15px;}
tr.title-second td span{margin-right:0px;}
tr.title-third td{font-size:15px;}
.baseInfo tr td span.amtSpan{ margin-right:0;color: #FF0000;}
tr.title-fourth td{background-color:#DCF9FF;text-align:center;}
.LogInfo tr td img{margin:10px 20px 0 0;}
.color01{color: #FF0000;}
.color02{color: #797979;}
</style>

<script type="text/javascript">
	var viewerPictures;
	$(function(){
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
				$("#viewer-pictures").hide();
			}});
		$("#saveProcesBy").bind('click',function(){
			var violateComplainOrderId = $("#violateComplainOrderId").val();
			$.ajax({
				url : "${pageContext.request.contextPath}/violateOrder/saveProcesBy.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"id":violateComplainOrderId},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						$("#saveProcesByDiv").hide();
						$("#saveComplainOrderStatusDiv").show();
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


	});

	function toCheck(id) {
		$.ligerDialog.open({
			height: $(window).height()-100,
			width: $(window).width()-400,
			title: "审核",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/violateOrder/toCheck.shtml?violateComplainOrderId=" + id,
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

	//物流（新增）
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

    $(document).ready(function () {
        // 复制快递单号
        var clipboard = new Clipboard('.copyExpressNo');
        clipboard.on('success', function (e) {
            $.ligerDialog.success("复制成功！");
        });
    });
	
    
	//挂起的事件
    function toChoice(id) {
        $.ligerDialog.open({
            height: $(window).height()-100,
            width: $(window).width()-400,
            title: "挂起原因",
            name: "INSERT_WINDOW",
            url: "${pageContext.request.contextPath}/violateOrder/toChoice.shtml?violateComplainOrderId=" + id,
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
		<input type="hidden" id="role" name="role" value="${role}">
	<input type="hidden" id="violateOrderId" value="${violateOrder.id}">
	<input type="hidden" id="violateComplainOrderId" value="${violateComplainOrder.id}">
		<div class="table-title">
				<span class="marR10">
					商家信息：
				</span>
		</div>
		<table class="gridtable">
		<tr>
			<td class="title" style="width: 161px;">商家</td>
			<td >${mchtInfo.companyName}</td>
			<td class="title">商家序号</td>
			<td >${mchtInfo.mchtCode}</td>
			<td class="title">对接运营</td>
			<td >${contactName}</td>
		</tr>
		<tr>
			<td class="title">店铺名称</td>
			<td >${mchtInfo.shopName}</td>
			<td class="title">30天内违规次数</td>
			<td >${thirtyDaysViolateCount}</td>
			<td class="title">总违规次数</td>
			<td >${totalViolateCount}</td>
		 </tr>
		</table>	
		<div class="table-title">
			<span class="marR10">
				违规信息：
			</span>
		</div>
		<table class="gridtable">
			<tr>
				<td class="title" style="width: 161px;">违规编号</td>
				<td>${violateOrder.orderCode}</td>
				<td class="title">子订单号</td>
				<td><a href="javascript:;" onclick="viewDetail(${violateOrder.subOrderId});">${subOrderCode}</a></td>
				<td class="title">申诉状态</td>
				<td ><span style="color: red;">${statusDesc}</span></td>
			</tr>
			<tr>
				<td class="title">违规时间</td>
				<td><fmt:formatDate value="${violateOrder.violateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td class="title">创建人员</td>
				<td>
					<c:if test="${not empty staffName}">
						${staffName}
					</c:if>
					<c:if test="${empty staffName && violateOrder.orderSource==1}">
						系统
					</c:if>
				</td>
				<td class="title">来源/状态</td>
				<td >${orderSourceDesc}【<span style="color: red;">${auditStatusDesc}</span>】</td>
			</tr>
			<tr>
				<td class="title">违规类型</td>
				<td >${violateTypeDesc}</td>
				<td class="title">违规行为</td>
				<td >${violateActionDesc}</td>
				<td class="title">处罚金额</td>
				<td >
					${violateOrder.money}元
					<span style="color: red;">
					<c:if test="${violateOrder.jifenStatus eq '0'}">，需发放积分${violateOrder.jifenIntegral}个</c:if>
					<c:if test="${violateOrder.jifenStatus eq '1'}">，已发放积分${violateOrder.jifenIntegral}个</c:if>
					<c:if test="${violateOrder.jifenStatus eq '2'}">，不用发放积分</c:if>
					</span>
				</td>
			</tr>
			<c:if test="${not empty subOrderCode && subOrderCode ne ''}">
				<tr>
					<td class="title">快递名称</td>
					<td >${subOrderCustom.expressName}</td>
					<td class="title">快递单号</td>
					<td><a href="javascript:;" onclick="viewWuliu(${subOrderCustom.id});">${subOrderCustom.expressNo}</a>
						<span><c:if test="${not empty subOrderCustom.expressNo && subOrderCustom.expressNo ne ''}"><a href="javascript:void(0);" class="copyExpressNo" data-clipboard-action="copy" data-clipboard-text=${subOrderCustom.expressNo} >【复制】</a></c:if></span>
					</td>
					<td class="title">快递官网</td>
					<td >
						<c:if test="${subOrderCustom.expressName eq '顺丰'}">https://www.sf-express.com/cn/sc/</c:if>
						<c:if test="${subOrderCustom.expressName eq '邮政'}">http://www.chinapost.com.cn/</c:if>
						<c:if test="${subOrderCustom.expressName eq 'EMS'}">http://www.ems.com.cn/</c:if>
						<span>
						<c:if test="${subOrderCustom.expressName eq '顺丰'}"><a href="https://www.sf-express.com/cn/sc/" target="_blank">【打开】</a></c:if>
						<c:if test="${subOrderCustom.expressName eq '邮政'}"><a href="http://www.chinapost.com.cn/" target="_blank">【打开】</a></c:if>
						<c:if test="${subOrderCustom.expressName eq 'EMS'}"><a href="http://www.ems.com.cn/" target="_blank">【打开】</a></c:if>
					</span>
					</td>
				</tr>
			</c:if>
		</table>
		<table class="gridtable">
			<tr>
				<td class="title" colspan="1" style="width: 161px;">违规详情</td>
				<td colspan="5">${violateOrder.content}</td>
			</tr>
			<tr>
				<td class="title" colspan="1">处罚方式</td>
				<td colspan="5">${violateOrder.punishMeans}</td>
			</tr>
			<tr>
				<td class="title" colspan="1">内部附件</td>
				<td colspan="5"><a href="${pageContext.request.contextPath}/file_servelt${violateOrder.enclosure}">${enclosureName}</a></td>
			</tr>
			<tr>
				<td class="title" colspan="1">内部备注</td>
				<td colspan="5">${violateOrder.platformRemarks}</td>
			</tr>
			<tr>
				<td class="title" colspan="1">内部凭证图片</td>
				<td colspan="5">
					<div class="pic-container">
						<ul class="docs-pictures clearfix td-pictures pictures-list">
					<c:forEach items="${violatePlatformRemarksPics}" var="violatePlatformRemarksPic">
						<li class="pic_li">
							<img src="${pageContext.request.contextPath}/file_servelt${violatePlatformRemarksPic.pic}">
						</li>
					</c:forEach>
						</ul>
					</div>
				</td>
			</tr>
			<c:if test="${violateOrder.auditStatus eq '2'}">
			<tr>
				<td class="title" colspan="1">主管驳回原因</td>
				<td colspan="5">${violateOrder.auditRemarks}</td>
			</tr>
			</c:if>
			
			<tr id="rejectTr">
				<td class="title" colspan="1">申诉截止时间</td>
				<td colspan="2"><fmt:formatDate value="${violateOrder.complainEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td class="title" colspan="1" style="width: 161px;">申诉资料状态</td>
				<td colspan="2">
					<c:if test="${violateOrder.complainInfoStatus eq '2'}">待审核</c:if>
					<c:if test="${violateOrder.complainInfoStatus eq '1'}">待补充</c:if>
				</td>
			</tr>

			<c:if test="${violateOrder.suspendedStatus eq '2'}">
				<tr id="suspendStatus">
					<td class="title" colspan="1">是否挂起</td>
					<td colspan="5">是</td>
				</tr>
			</c:if>

			<c:if test="${violateOrder.suspendedStatus eq '2'}">
				<tr id="suspendedReason">
					<td class="title" colspan="1">挂起原因</td>
					<td colspan="5">${violateOrder.suspendedReason}</td>
				</tr>
			</c:if>


		</table>
		<c:if test="${violateOrder.status ne '2'}">
		<c:if test="${not empty violateComplainOrderCustoms}">
		<div class="table-title">
			<span class="marR10">
				商家申诉：
			</span>
		</div>
		</c:if>
		</c:if>
		<c:forEach var="violateComplainOrderCustom" items="${violateComplainOrderCustoms}">
			<table class="gridtable marT10 LogInfo">
	          	<tr class="title-fourth">
	          		<td>申诉时间</td>
	          		<td style="text-align: left;"><fmt:formatDate value="${violateComplainOrderCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	          	</tr>
	          	<tr class="title-fourth">
	          		<td>联系方式</td>
	          		<td style="text-align: left;">电话：${violateComplainOrderCustom.phone}  邮箱：${violateComplainOrderCustom.email}</td>
	          	</tr>
	          	<tr class="title-fourth">
	          		<td>申诉内容</td>
	          		<td colspan="3" style="text-align: left;">${violateComplainOrderCustom.content}</td>
	          	</tr>
	          	<tr class="title-fourth">
	          		<td>上传资料</td>
	          		<td colspan="3" style="text-align: left;">
	          			<c:if test="${not empty violateComplainOrderCustom.mchtComplainOrderPics}">
							<div class="pic-container">
								<ul class="docs-pictures clearfix td-pictures pictures-list">
									<c:forEach var="mchtPic" items="${violateComplainOrderCustom.mchtComplainOrderPics}">
										<li class="pic_li">
											<img src="${pageContext.request.contextPath}/file_servelt${mchtPic.pic}">
										</li>
									</c:forEach>
								</ul>
							</div>
	          			</c:if>
	          		</td>
	          	</tr>
	          	<c:if test="${not empty violateComplainOrder.procesBy}">
	          	<tr class="title-fourth">
		        	<td>申诉审核人</td>
		        	<td style="text-align: left;">${violateComplainOrderCustom.staffName}</td>
	          	</tr>
	          	</c:if>
	          	<tr class="title-fourth">
		        	<td>审核状态</td>
		        	<td style="text-align: left;">
		        		<c:if test="${violateComplainOrderCustom.status eq '0' && role ne '2'}">
		        			<a href="javascript:;" onclick="toCheck(${violateComplainOrderCustom.id});">【待审核】</a>
		        		</c:if>
						<c:if test="${violateComplainOrderCustom.status eq '0' && role eq '2'}">
							待审核
						</c:if>
		        		<c:if test="${violateComplainOrderCustom.status eq '1'}">
		        			通过
		        		</c:if>
		        		<c:if test="${violateComplainOrderCustom.status eq '2'}">
		        			驳回
		        		</c:if>

						<c:if test="${role ne '1' && role ne '2'}">
							<span>
                            <c:if test="${violateComplainOrderCustom.status eq '0' && violateOrder.suspendedStatus eq '1'}">
								<a href="javascript:;" onclick="toChoice(${violateComplainOrderCustom.id});">【是否挂起】</a>
							</c:if>
							<c:if test="${violateOrder.suspendedStatus eq '2'}">
								已挂起
							</c:if>
                        </span>
						</c:if>

		        	</td>
	          	</tr>
	          	<c:if test="${violateComplainOrderCustom.status eq '1'}">
		          	<tr class="title-fourth">
			        	<td>内部备注</td>
			        	<td style="text-align: left;">
			        		${violateComplainOrderCustom.processInnerRemarks }
			        	</td>
		          	</tr>
	          	</c:if>
	          	<c:if test="${violateComplainOrderCustom.status eq '2'}">
		          	<tr class="title-fourth">
			        	<td>驳回原因</td>
			        	<td style="text-align: left;">
			        	${violateComplainOrderCustom.remarks}<br>
			        	<c:if test="${not empty violateComplainOrderCustom.platFormComplainOrderPics}">
							<div class="pic-container">
								<ul class="docs-pictures clearfix td-pictures pictures-list">
									<c:forEach var="platFormPic" items="${violateComplainOrderCustom.platFormComplainOrderPics}">
										<li class="pic_li">
											<img src="${pageContext.request.contextPath}/file_servelt${platFormPic.pic}">
										</li>
									</c:forEach>
								</ul>
							</div>
		          		</c:if>
			        	</td>
		          	</tr>
	          	</c:if>
	        </table>
		</c:forEach>
	</div>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">

	</ul>

</body>
</html>
