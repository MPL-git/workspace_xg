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
	
	function submitFun() {
		var isInitiateViolate = $("[name='isInitiateViolate']:checked").val();
		if(isInitiateViolate == '1') {
			editManuallyViolateOrder();
		}else {
			updateIsInitiateViolate('subFlag');
		}
	}
	//子框口调用方法
	function childFun() {
		updateIsInitiateViolate('');
	}
	function updateIsInitiateViolate(flag) {
		var isInitiateViolate = $("[name='isInitiateViolate']:checked").val();
		var interventionOrderId = '${interventionOrderCustom.id }';
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/interventionOrder/updateInterventionOrder.shtml?statusFlag=8',
			data: {interventionOrderId:interventionOrderId, isInitiateViolate:isInitiateViolate},
			dataType: 'json',
			success: function(data) {
				if(data == null || data.code != 200) {
					commUtil.alertError(data.msg);
				}else {
					if(flag == '') {
						$.ligerDialog.success('操作成功！', function() {
							parent.updateIsInitiateViolateFun(interventionOrderId, data.updateDate, data.isInitiateViolate);
		            	 	frameElement.dialog.close();
						});
					}else {
						$.ligerDialog.success(data.msg, function() {
							parent.updateIsInitiateViolateFun(interventionOrderId, data.updateDate, data.isInitiateViolate);
		            	 	frameElement.dialog.close();
						});	 
					}
				}
			},
			error: function(e) {
				commUtil.alertError('操作超时，请稍后再试！');
			}
		});
	}
	function editManuallyViolateOrder() {
		$.ligerDialog.open({
	 		height: $(window).height()-50,
			width: $(window).width()-50,
	 		title: "添加人工发起违规",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/violateOrder/editManuallyViolateOrder.shtml",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
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
	function interventionProcessList(interventionOrderId, processType) {
		var title = "协商详情";
		if(processType != '') {
			title = "商家处理凭证详情";
		}
		$.ligerDialog.open({
			height: $(window).height()-50,
			width: $(window).width()-50,
			title: title,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/interventionOrder/interventionProcessList.shtml?interventionOrderId=" + interventionOrderId + "&processType=" + processType,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	
	//创建工单
	function addWork(id) {
		 $.ligerDialog.open({
				height: 600,
				width: 950,
				title: "创建工单",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/interventionOrder/addinterventionOrderWork.shtml?id="+id,
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
			<span><a href="javascript:addWork(${interventionOrderCustom.id});">【创建工单】</a></span>
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
				<span><a href="javascript:interventionProcessList(${interventionOrderCustom.id }, '');">【查看更多】</a></span>
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
			<span>商家申诉</span>
		</div>
		<table class="gridtable marT10">
           	<tr>
               <td class="title title-width" >
               		<span>申诉说明</span>
               </td>
               <td>
               		<span>${mapMchtAppeal.mchtAppealInterventionProcess.content }</span>
               </td>
			</tr>
           	<tr>
               <td class="title title-width">
               		<span>上传凭证</span>
               </td>
               <td>
           			<c:forEach var="interventionProcessPic" items="${mapMchtAppeal.interventionProcessPicList }">
           				<img src="${pageContext.request.contextPath}/file_servelt${interventionProcessPic.pic }" onclick="viewerPic(this.src);" style="width: 60px;height: 60px;" >
           			</c:forEach>
               </td>
			</tr>
        </table>
	</div>
	<c:if test="${not empty interventionOrderCustom.winType }">
		<div class="title-top">
			<div class="table-title">
				<span>平台判定</span>
			</div>
			<table class="gridtable marT10 status-table">
	           	<tr>
	               <td class="title title-width">
	               		<span>买家</span>
	               </td>
	               <td>
	               		<select id="clientResultReason" name="clientResultReason" style="width: 50%;" disabled="disabled">
							<c:forEach var="clientResultReason" items="${clientResultReasonList }">
								<option value="${clientResultReason.statusValue }" <c:if test="${interventionOrderCustom.clientResultReason == clientResultReason.statusValue}">selected</c:if> > 
									${clientResultReason.statusDesc }
								</option>
							</c:forEach>
						</select>
	               </td>
				</tr>
	           	<tr>
	               <td class="title title-width">
	               		<span>商家</span>
	               </td>
	               <td>
	               		<select id="mchtResultReason" name="mchtResultReason" style="width: 50%;" disabled="disabled">
							<c:forEach var="mchtResultReason" items="${mchtResultReasonList }">
								<option value="${mchtResultReason.statusValue }" <c:if test="${interventionOrderCustom.mchtResultReason == mchtResultReason.statusValue}">selected</c:if> > 
									${mchtResultReason.statusDesc }
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
	               		<textarea rows="5" cols="100" id="platformRemarks" name="platformRemarks" readonly="readonly" >${interventionOrderCustom.platformRemarks }</textarea>
	               </td>
				</tr>
	        </table>
		</div>
	</c:if>
	<c:if test="${fn:length(listMap) > 0 }">
		<div class="title-top">
			<div class="table-title">
				<span>商家处理凭证</span>
				<span><a href="javascript:interventionProcessList(${interventionOrderCustom.id }, '3');">【查看更多】</a></span>
			</div>
			<table class="gridtable marT10">
	           	<c:forEach var="map" items="${listMap }">
		           	<tr>
		               <td class="title title-width">
		               		<p style="margin-bottom: 10px;">商家</p>
		               		<p><fmt:formatDate value="${map.interventionProcess.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
		               </td>
		               <td>
		               		<p style="margin-bottom: 10px;">${map.interventionProcess.content }</p>
		               		<span>
		               			<c:forEach var="interventionProcessPic" items="${map.interventionProcessPicList }">
		               				<img src="${pageContext.request.contextPath}/file_servelt${interventionProcessPic.pic }" onclick="viewerPic(this.src);" style="width: 60px;height: 60px;" >
		               			</c:forEach>
		               		</span>
		               </td>
					</tr>
	           	</c:forEach>
	        </table>
		</div>
	</c:if>
	<div class="title-top">
		<div class="table-title">
			<span>结案记录</span>
		</div>
		<table class="gridtable marT10 status-table">
           	<tr>
               <td class="title title-width">
               		<span id="recordOfCases-span">结案记录</span>
               </td>
               <td>
               		<textarea rows="5" cols="100" id="recordOfCases" name="recordOfCases" readonly="readonly" >${interventionOrderCustom.recordOfCases }</textarea>
               </td>
			</tr>
        </table>
	</div>
	<div class="title-top">
		<div class="table-title">
			<span>是否发起违规</span>
		</div>
		<div class="marT10">
			<span class="marL20"><input type="radio" name="isInitiateViolate" value="1" checked="checked" >是</span>
			<span class="marL20"><input type="radio" name="isInitiateViolate" value="0" >否</span>
		</div>
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
		<button style="cursor: pointer;width: 150px;height: 35px;background-color: rgba(22, 155, 213, 1);border: none;color: white;" onclick="submitFun();" >
			提交
		</button>
	</div>	
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
