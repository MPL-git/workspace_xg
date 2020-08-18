<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/glyphicon.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:10px;padding-bottom:10px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
.title-width{width: 20%}
.marL20{margin-left:20px;}
</style>
<script type="text/javascript">
	var viewerPictures;
	$(function(){
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		
		$("[name='status']").bind("click",function() {
			 var status = $("[name='status']:checked").val();
			 if(status == '2') {
				 $(".status-table").show();
			 }else {
				 $(".status-table").hide();
			 }
		});
		
	});
	
	//查看图片
	function viewerPic(imgPath) {
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		imgPath=imgPath.replace('_S', '');
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		viewerPictures.show();
	}
	
	//查看批量图片
	function viewerPicList(interventionOrderId) {
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$.ajax({
			url: '${pageContext.request.contextPath}/interventionOrder/userInterventionProcessPicList.shtml',
			type: 'post',
			dataType: 'json',
			data: {interventionOrderId:interventionOrderId},
			success: function(data) {
				if(data == null || data.code != 200) {
					commUtil.alertError(data.msg);
				}else if(data.interventionProcessPicList != null && data.interventionProcessPicList.length > 0) {
					for(var i=0;i<data.interventionProcessPicList.length;i++) {
						$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data.interventionProcessPicList[i].pic+'" src="${pageContext.request.contextPath}/file_servelt'+data.interventionProcessPicList[i].pic+'" alt=""></li>');
					}	
					viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
						$("#viewer-pictures").hide();
					}});
					$("#viewer-pictures").show();
					viewerPictures.show();
				}else {
					commUtil.alertError('未上传凭证！');
				}
			},
			error: function() {
				commUtil.alertError('操作超时，请稍后再试！');
			}
		});
	}
	
	function submitFun(directorReason, directorRemarks, interventionOrderId) {
		if(directorReason == '' && directorRemarks == '') {
			var status = $("[name='status']:checked").val();
			var rejectReason = $("#rejectReason").val();
			var platformRemarks = $("#platformRemarks").val();
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/interventionOrder/updateInterventionOrder.shtml?statusFlag=2',
				data: {interventionOrderId:interventionOrderId, status:status, rejectReason:rejectReason, platformRemarks:platformRemarks},
				dataType: 'json',
				success: function(data) {
					if(data == null || data.code != 200) {
						commUtil.alertError(data.msg);
					}else {
						$.ligerDialog.success(data.msg, function() {
							parent.$("#searchbtn").click();
		            	 	frameElement.dialog.close();
						});	 
					}
				},
				error: function(e) {
					commUtil.alertError('操作超时，请稍后再试！');
				}
			});
		}else {
			var directorStatus = $("[name='directorStatus']:checked").val();
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/interventionOrder/updateInterventionOrder.shtml?statusFlag=3',
				data: {interventionOrderId:interventionOrderId, directorStatus:directorStatus},
				dataType: 'json',
				success: function(data) {
					if(data == null || data.code != 200) {
						commUtil.alertError(data.msg);
					}else {
						window.location.href = '${pageContext.request.contextPath}/interventionOrder/showInterventionOrderManager.shtml?interventionOrderId='+interventionOrderId+'&statusPage=2';
					}
				},
				error: function(e) {
					commUtil.alertError('操作超时，请稍后再试！');
				}
			});
		}
	}
	
	//售后详情
	function viewAfterServiceDetail(id, serviceTypeDesc) {
		$.ligerDialog.open({
	 		height: $(window).height()-50,
			width: $(window).width()-50,
	 		title: "售后详情（"+serviceTypeDesc+"）",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/order/afterService/detail.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//查看商家联系人
	function mchtContact(id) {
		$.ligerDialog.open({
			height: $(window).height()-50,
			width: $(window).width()-50,
			title: "商家联系人",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/mchtContact.shtml?contactType=4,2,6&mchtId=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//协商详情
	function interventionProcessList(interventionOrderId) {
		$.ligerDialog.open({
			height: $(window).height()-50,
			width: $(window).width()-50,
			title: "协商详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/interventionOrder/interventionProcessList.shtml?interventionOrderId=" + interventionOrderId,
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
			<span>商家信息</span>
		</div>
		<table class="gridtable marT10">
           	<tr>
               <td class="title">商家ID</td> 
               <td class="title">商家名称</td>
               <td class="title">销售模式</td>
               <td class="title">商家联系电话</td>
               <td class="title">当月被投诉数</td>
               <td class="title">总投诉数</td> 
               <td class="title">平台运营对接人</td>
			</tr>
	        <tr>
               	<td>${interventionOrderCustom.mchtCode }</td>
               	<td>${interventionOrderCustom.companyName }</td>
               	<td>${interventionOrderCustom.mchtTypeDesc }</td>
               	<td>
               		<a href="javascript:mchtContact(${interventionOrderCustom.mchtId });">查看</a>
               	</td>
               	<td>${appealOrderCountMonth }</td>
               	<td>${appealOrderCount }</td>
               	<td>${interventionOrderCustom.contactName }</td>
	        </tr>
        </table>
	</div>
	<div class="title-top">
		<div class="table-title">
			<span>介入申请信息</span>
		</div>
		<table class="gridtable marT10">
           	<tr>
               <td class="title">介入编号</td> 
               <td class="title">售后单号</td>
               <td class="title">凭证</td>
               <td class="title">介入原因</td>
               <td class="title">介入留言</td>
               <td class="title">介入联系人</td> 
               <td class="title">申请人联系电话</td>
               <td class="title">申请时间</td>
			</tr>
	        <tr>
               	<td>${interventionOrderCustom.interventionCode }</td>
               	<td>
               		<a href="javascript:viewAfterServiceDetail(${interventionOrderCustom.serviceOrderId }, '${customerServiceOrderCustom.serviceTypeDesc }');">${interventionOrderCustom.customerServiceOrderCode }</a>
               	</td>
               	<td>
               		<a href="javascript:viewerPicList(${interventionOrderCustom.id });">查看</a>
               	</td>
               	<td>${interventionOrderCustom.reasonDesc }</td>
               	<td>${interventionOrderCustom.message }</td>
               	<td>${interventionOrderCustom.contacts }</td>
               	<td>${interventionOrderCustom.tel }</td>
               	<td>
               		<fmt:formatDate value="${interventionOrderCustom.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
               	</td>
	        </tr>
        </table>
	</div>
	<div class="title-top">
		<div class="table-title">
			<span>相关商品信息</span>
		</div>
		<table class="gridtable marT10">
           	<tr>
               <td class="title">商品ID</td> 
               <td class="title">商品名称</td>
               <td class="title">醒购价</td>
               <td class="title">售后状态</td>
               <td class="title">退款金额</td>
               <td class="title">支付金额</td> 
			</tr>
	        <tr>
               	<td>${customerServiceOrderCustom.productCode }</td>
               	<td>${customerServiceOrderCustom.productName }</td>
               	<td>${customerServiceOrderCustom.salePrice }</td>
               	<td>${customerServiceOrderCustom.proStatusDesc }</td>
               	<td>${customerServiceOrderCustom.amount }</td>
               	<td>${customerServiceOrderCustom.payAmount }</td>
	        </tr>
        </table>
	</div>
	<div class="title-top">
		<div class="table-title">
			<span>协商内容</span>
			<c:if test="${not empty mapUser.userInterventionProcess or not empty mapMcht.mchtInterventionProcess }">
				<span><a href="javascript:interventionProcessList(${interventionOrderCustom.id });">【查看更多】</a></span>
			</c:if>
			<c:if test="${empty mapUser.userInterventionProcess and empty mapMcht.mchtInterventionProcess }">
				<span style="color: gray;">【查看更多】</span>
			</c:if>
		</div>
		<table class="gridtable marT10">
           	<tr>
               <td class="title title-width" >
               		<p style="margin-bottom: 10px;">客户</p>
               		<p><fmt:formatDate value="${mapUser.userInterventionProcess.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
               </td>
               <td>
               		<p style="margin-bottom: 10px;">${mapUser.userInterventionProcess.content }</p>
               		<span>
               			<c:forEach var="interventionProcessPic" items="${mapUser.interventionProcessPicList }">
               				<img src="${pageContext.request.contextPath}/file_servelt${interventionProcessPic.pic }" onclick="viewerPic(this.src);" style="width: 60px;height: 60px;" >
               			</c:forEach>
               		</span>
               </td>
			</tr>
           	<tr>
               <td class="title title-width">
               		<p style="margin-bottom: 10px;">商家</p>
               		<p><fmt:formatDate value="${mapMcht.mchtInterventionProcess.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
               </td>
               <td>
               		<p style="margin-bottom: 10px;">${mapMcht.mchtInterventionProcess.content }</p>
               		<span>
               			<c:forEach var="interventionProcessPic" items="${mapMcht.interventionProcessPicList }">
               				<img src="${pageContext.request.contextPath}/file_servelt${interventionProcessPic.pic }" onclick="viewerPic(this.src);" style="width: 60px;height: 60px;" >
               			</c:forEach>
               		</span>
               </td>
			</tr>
        </table>
	</div>
	<div class="title-top">
		<div class="table-title">
			<span>平台受理</span>
		</div>
		<div class="marT10" <c:if test="${not empty interventionOrderCustom.directorReason or not empty interventionOrderCustom.directorRemarks }">style="display: none;"</c:if> >
			<span class="marL20"><input type="radio" name="status" value="4" checked="checked" >受理介入</span>
			<span class="marL20"><input type="radio" name="status" value="2" >驳回介入</span>
		</div>
		<table class="gridtable marT10 status-table" <c:if test="${empty interventionOrderCustom.directorReason and empty interventionOrderCustom.directorRemarks }">style="display: none;"</c:if> >
           	<tr>
               <td class="title title-width">
               		<span>关闭理由<span class="required">*</span></span>
               </td>
               <td>
               		<select id="rejectReason" name="rejectReason" style="width: 50%;" <c:if test="${not empty interventionOrderCustom.directorReason or not empty interventionOrderCustom.directorRemarks }">disabled</c:if> >
						<c:forEach var="rejectReason" items="${rejectReasonList }">
							<option value="${rejectReason.statusValue }" <c:if test="${interventionOrderCustom.rejectReason == rejectReason.statusValue}">selected</c:if> > 
								${rejectReason.statusDesc }
							</option>
						</c:forEach>
					</select>
               </td>
			</tr>
           	<tr>
               <td class="title title-width">
               		<span>内部备注</span>
               </td>
               <td>
               		<textarea rows="5" cols="100" id="platformRemarks" name="platformRemarks" <c:if test="${not empty interventionOrderCustom.directorReason or not empty interventionOrderCustom.directorRemarks }">readonly</c:if> >${interventionOrderCustom.platformRemarks }</textarea>
               </td>
			</tr>
        </table>
	</div>
	<div class="title-top" <c:if test="${empty interventionOrderCustom.directorReason and empty interventionOrderCustom.directorRemarks }">style="display: none;"</c:if> >
		<div class="table-title">
			<span>关闭审核</span>
		</div>
		<div class="marT10">
			<span class="marL20"><input type="radio" name="directorStatus" value="3" disabled="disabled" >同意驳回</span>
			<span class="marL20"><input type="radio" name="directorStatus" value="1" checked="checked" >不同意驳回</span>
		</div>
		<table class="gridtable marT10">
           	<tr>
               <td class="title title-width">
               		<span>理由<span class="required">*</span></span>
               </td>
               <td>
               		<textarea rows="5" cols="100" readonly="readonly">${interventionOrderCustom.directorReason }</textarea>
               </td>
			</tr>
           	<tr>
               <td class="title title-width">
               		<span>内部备注</span>
               </td>
               <td>
               		<textarea rows="5" cols="100" readonly="readonly">${interventionOrderCustom.directorRemarks }</textarea>
               </td>
			</tr>
        </table>
	</div>
	<div class="title-top">
		<div class="table-title">
			<span>操作记录</span>
		</div>
		<table class="gridtable marT10">
           	<tr>
               <td width="30%" class="title">时间</td> 
               <td width="30%" class="title">操作人</td>
               <td width="40%" class="title">审核结果</td>
			</tr>
	        <c:forEach var="interventionOrderLogCustom" items="${interventionOrderLogCustomList }">
		        <tr>
		        	<td>
	               		<fmt:formatDate value="${interventionOrderLogCustom.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
	               	</td>
	               	<td>${interventionOrderLogCustom.staffName }</td>
	               	<td>${interventionOrderLogCustom.content }</td>
		        </tr>
	        </c:forEach>
        </table>
	</div>
	<div style="margin: 20 auto; text-align: center">
		<button style="cursor: pointer;width: 150px;height: 35px;background-color: rgba(22, 155, 213, 1);border: none;color: white;" onclick="submitFun('${interventionOrderCustom.directorReason }', '${interventionOrderCustom.directorRemarks }', '${interventionOrderCustom.id }');" >
			<c:if test="${empty interventionOrderCustom.directorReason and empty interventionOrderCustom.directorRemarks }">提交</c:if>
			<c:if test="${not empty interventionOrderCustom.directorReason or not empty interventionOrderCustom.directorRemarks }">重新受理</c:if>
		</button>
	</div>	
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
