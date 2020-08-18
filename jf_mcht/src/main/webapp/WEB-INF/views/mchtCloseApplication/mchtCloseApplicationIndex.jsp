<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/jquery.eeyellow.Timeline.css" />
<title>关店申请</title>
</head>

<body>
	<div class="x_panel container-fluid">
		<div class="row content-title">
			<div class="t-title">关店申请</div>
		</div>
		<div class="search-container container-fluid"></div>
		<div class="clearfix"></div>
		<c:if test="${empty mchtCloseApplications}">
			<div>
				申请理由：
				<textarea class="form-control"
					style="width: 100%;resize: vertical;margin-bottom: 10px;" rows="10"
					id="applyReason" name="applyReason" maxlength="256"></textarea>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" id="confirm">提交</button>
			</div>
		</c:if>
		<c:if test="${not empty mchtCloseApplications}">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="VivaTimeline">
							<dl>
								<c:if test="${mchtCloseApplication.progressStatus == '0'}">
									<c:if test="${mchtCloseApplication.applySource == '1'}">
									<dt style="background: green;">商家申请</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.createDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header" style="word-break: break-all;">${mchtCloseApplication.applyReason}</div>
										</div>
									</dd>
									</c:if>
									<c:if test="${mchtCloseApplication.applySource == '2'}">
									<dt style="background: green;">平台介入关店</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.createDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header" style="word-break: break-all;">${mchtCloseApplication.applyReason}</div>
										</div>
									</dd>
									</c:if>
									<dt style="background: blue;">招商审核</dt>
									<c:if test="${mchtCloseApplication.zsConfirmStatus!=2}">
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time">审核中</div>
									</dd>
									</c:if>
									<c:if test="${mchtCloseApplication.zsConfirmStatus==2}">
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time">招商驳回</div>
										<div class="events">
											<div class="events-header" style="word-break: break-all;">
												<span style="color: red;">驳回原因：${mchtCloseApplication.zsRejectReason}</span>
												<br>
												<a href="javascript:;" onclick="toEditApplyReason(${mchtCloseApplication.id});">修改申请理由</a>
											</div>
										</div>
									</dd>
									</c:if>
									
									<dt>运营审核</dt>
									<dt>资料确认</dt>
									<dt>售后审核</dt>
									<dt>货款结算</dt>
									<dt>签署协议</dt>
									<dt>完成</dt>
								</c:if>
								<c:if test="${mchtCloseApplication.progressStatus == '1'}">
									<c:if test="${mchtCloseApplication.applySource == '1'}">
									<dt style="background: green;">商家申请</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.createDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header" style="word-break: break-all;">${mchtCloseApplication.applyReason}</div>
										</div>
									</dd>
									</c:if>
									<c:if test="${mchtCloseApplication.applySource == '2'}">
									<dt style="background: green;">平台介入关店</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.createDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header" style="word-break: break-all;">${mchtCloseApplication.applyReason}</div>
										</div>
									</dd>
									</c:if>
									<dt style="background: green;">招商审核</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.zsConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: blue;">运营审核</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time">审核中</div>
									</dd>
									<dt>资料确认</dt>
									<dt>售后审核</dt>
									<dt>货款结算</dt>
									<dt>签署协议</dt>
									<dt>完成</dt>
								</c:if>
								<c:if test="${mchtCloseApplication.progressStatus == '2'}">
									<c:if test="${mchtCloseApplication.applySource == '1'}">
									<dt style="background: green;">商家申请</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.createDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header" style="word-break: break-all;">${mchtCloseApplication.applyReason}</div>
										</div>
									</dd>
									</c:if>
									<c:if test="${mchtCloseApplication.applySource == '2'}">
									<dt style="background: green;">平台介入关店</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.createDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header" style="word-break: break-all;">${mchtCloseApplication.applyReason}</div>
										</div>
									</dd>
									</c:if>
									<dt style="background: green;">招商审核</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.zsConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: green;">运营审核</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.commodityConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<c:if test="${empty mchtCloseApplication.fwCloseHangUpStatus}">
									<dt style="background: blue;">资料确认</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time">审核中</div>
									</dd>
									</c:if>
									<c:if test="${not empty mchtCloseApplication.fwCloseHangUpStatus}">
									<dt style="background: red;">资料确认</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.fwHangUpDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
	                                		<div class="events-header" style="color: red;">审核暂停</div>                                
			                                <div class="events-body">                                                                                            
			                                    <div class="row">
			                                        <div class="events-desc"><c:if test="${mchtCloseApplication.fwHangUpReason == 1}">您寄回的资质尚未完善，请补齐。未齐全的资质事宜请联系你的招商对接人</c:if></div>
			                                    </div>
			                                </div>                                
	                            		</div>
									</dd>
									</c:if>
									<dt>售后审核</dt>
									<dt>货款结算</dt>
									<dt>签署协议</dt>
									<dt>完成</dt>
								</c:if>
								<c:if test="${mchtCloseApplication.progressStatus == '3'}">
									<c:if test="${mchtCloseApplication.applySource == '1'}">
									<dt style="background: green;">商家申请</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.createDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header" style="word-break: break-all;">${mchtCloseApplication.applyReason}</div>
										</div>
									</dd>
									</c:if>
									<c:if test="${mchtCloseApplication.applySource == '2'}">
									<dt style="background: green;">平台介入关店</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.createDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header" style="word-break: break-all;">${mchtCloseApplication.applyReason}</div>
										</div>
									</dd>
									</c:if>
									<dt style="background: green;">招商审核</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.zsConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: green;">运营审核</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.commodityConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: green;">资料确认</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.mchtArchiveConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<c:if test="${empty mchtCloseApplication.kfCloseHangUpStatus}">
									<dt style="background: blue;">售后审核</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time">审核中</div>
									</dd>	
									</c:if>
									<c:if test="${not empty mchtCloseApplication.kfCloseHangUpStatus}">
									<dt style="background: red;">售后审核</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.kfHangUpDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
	                                		<div class="events-header" style="color: red;">审核暂停</div>                                
			                                <div class="events-body">                                                                                            
			                                    <div class="row">
			                                        <div class="events-desc">
			                                        	<c:if test="${mchtCloseApplication.kfHangUpReason == 1}">
			                                        		当前您还有订单未完成，审核暂停
			                                        	</c:if>
			                                        	<c:if test="${mchtCloseApplication.kfHangUpReason == 2}">
			                                        		当前店铺还有介入单未完成，审核暂停
			                                        	</c:if>
			                                        	<c:if test="${mchtCloseApplication.kfHangUpReason == 3}">
			                                        		当前最后一个订单的三包期未过，审核暂停
			                                        	</c:if>
			                                        </div>
			                                    </div>
			                                </div>                                
	                            		</div>
									</dd>	
									</c:if>
									<dt>货款结算</dt>
									<dt>签署协议</dt>
									<dt>完成</dt>
								</c:if>
								<c:if test="${mchtCloseApplication.progressStatus == '4'}">
									<c:if test="${mchtCloseApplication.applySource == '1'}">
									<dt style="background: green;">商家申请</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.createDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header" style="word-break: break-all;">${mchtCloseApplication.applyReason}</div>
										</div>
									</dd>
									</c:if>
									<c:if test="${mchtCloseApplication.applySource == '2'}">
									<dt style="background: green;">平台介入关店</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.createDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header" style="word-break: break-all;">${mchtCloseApplication.applyReason}</div>
										</div>
									</dd>
									</c:if>
									<dt style="background: green;">招商审核</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.zsConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: green;">运营审核</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.commodityConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: green;">资料确认</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.mchtArchiveConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: green;">售后审核</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.kfConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: blue;">货款结算</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time">审核中</div>
									</dd>
									<dt>签署协议</dt>
									<dt>完成</dt>
								</c:if>
								<c:if test="${mchtCloseApplication.progressStatus == '5'}">
									<c:if test="${mchtCloseApplication.applySource == '1'}">
									<dt style="background: green;">商家申请</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.createDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header" style="word-break: break-all;">${mchtCloseApplication.applyReason}</div>
										</div>
									</dd>
									</c:if>
									<c:if test="${mchtCloseApplication.applySource == '2'}">
									<dt style="background: green;">平台介入关店</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.createDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header" style="word-break: break-all;">${mchtCloseApplication.applyReason}</div>
										</div>
									</dd>
									</c:if>
									<dt style="background: green;">招商审核</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.zsConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: green;">运营审核</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.commodityConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: green;">资料确认</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.mchtArchiveConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: green;">售后审核</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.kfConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: green;">货款结算</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.cwConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header">货款已结清  保证金剩余 <span style="color: red;">${mchtDeposit.payAmt}</span>元</div>
										</div>
									</dd>
									<dt style="background: blue;">签署协议</dt>
								<%--修改成财务货款确认后就可以预览打印 	<c:if test="${not empty mchtCloseApplication.agreementIssueDate}">  <c:if test="${empty mchtCloseApplication.expressId}">  --%>
								<c:if test="${not empty mchtCloseApplication.filePath}">
										<dd class="pos-left clearfix" style="height: 220px;">
										<div class="circ"></div>
										<div class="time" style="width: 300px;">
										第一步:<a href="javascript:showInfo('${ctx}/mchtCloseApplication/endAgreementPreview?id=${mchtCloseApplication.id}')">【预览打印终止合作协议】</a>
										<br>第二步:<a href="javascript:;" onclick="toUploadProtocol(${mchtCloseApplication.id});">【上传协议】</a>
										<br><span style="color:gray;">备注：1、请将协议打印下来，用黑色签字笔按要求填写相关信息后加盖公章，如协议页数两页及其两页以上请盖骑缝章（保证章印清楚）；上传要求：扫描后按照页码顺序上传，保证图片清晰，内容不模糊 <br>2、线上协议通过后，您再将协议寄回平台。</span>
										
										
										<c:if test="${mchtCloseApplication.contractUploadDate != null && mchtCloseApplication.contractUploadDate != '' }"><br>   
										<c:if test="${mchtCloseApplication.contractAuditStatus == '1' }"><span style="color:red;">线上协议审核状态：待审核</span></c:if>
										<c:if test="${mchtCloseApplication.contractAuditStatus == '2' }"><span style="color:red;">线上协议审核状态：通过 </span></c:if>
										<c:if test="${mchtCloseApplication.contractAuditStatus == '3' }"><span style="color:red;">线上协议审核状态：驳回 </span><a href="javascript:;" onclick="viewSubOrder()">(查看原因)</a></c:if>
										</c:if>
													   
										<br><c:if test="${mchtCloseApplication.contractAuditStatus == '2'}">第三步:协议寄件		   
										<c:if test="${mchtCloseApplication.expressNo == null || mchtCloseApplication.expressNo == ''}"><a href="javascript:;" onclick="toSend(${mchtCloseApplication.id});">【立即寄件】</a></c:if>
										<c:if test="${not empty mchtCloseApplication.expressNo}">合作协议已寄件</c:if>
										</c:if>	
										<br>   
										<c:if test="${mchtCloseApplication.contractArchiveStatus == '1' }"><span style="color:red;">协议归档状态：通过 </span></c:if>
										<c:if test="${mchtCloseApplication.contractArchiveStatus == '2' }"><span style="color:red;">协议归档状态：驳回 </span><a href="javascript:;" onclick="viewSubOrder()">(查看原因)</a></c:if>
										</div>
									</dd>
								</c:if>	
								<%-- 	</c:if> --%>
									<dt>完成</dt>
								</c:if>
								<c:if test="${mchtCloseApplication.progressStatus == '6' || mchtCloseApplication.progressStatus == '7'}">
									<c:if test="${mchtCloseApplication.applySource == '1'}">
									<dt style="background: green;">商家申请</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.createDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header" style="word-break: break-all;">${mchtCloseApplication.applyReason}</div>
										</div>
									</dd>
									</c:if>
									<c:if test="${mchtCloseApplication.applySource == '2'}">
									<dt style="background: green;">平台介入关店</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.createDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header" style="word-break: break-all;">${mchtCloseApplication.applyReason}</div>
										</div>
									</dd>
									</c:if>
									<dt style="background: green;">招商审核</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.zsConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: green;">运营审核</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.commodityConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: green;">资料确认</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.mchtArchiveConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: green;">售后审核</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.kfConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: green;">货款结算</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.cwConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header">货款已结清  保证金剩余 <span style="color: red;">${mchtDeposit.payAmt}</span>元</div>
										</div>
									</dd>
									<dt style="background: blue;">签署协议</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.fwConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header">预计保证金退还日期：<fmt:formatDate value="${mchtCloseApplication.depositDate}" pattern="yyyy-MM-dd"/></div>
										</div>
									</dd>
									<dt>完成</dt>
								</c:if>
								<c:if test="${mchtCloseApplication.progressStatus == '8'}">
									<c:if test="${mchtCloseApplication.applySource == '1'}">
									<dt style="background: green;">商家申请</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.createDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header" style="word-break: break-all;">${mchtCloseApplication.applyReason}</div>
										</div>
									</dd>
									</c:if>
									<c:if test="${mchtCloseApplication.applySource == '2'}">
									<dt style="background: green;">平台介入关店</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.createDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header" style="word-break: break-all;">${mchtCloseApplication.applyReason}</div>
										</div>
									</dd>
									</c:if>
									<dt style="background: green;">招商审核</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.zsConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: green;">运营审核</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.commodityConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: green;">资料确认</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.mchtArchiveConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: green;">售后审核</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.kfConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: green;">货款结算</dt>
									<dd class="pos-left clearfix">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.cwConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
										<div class="events">
											<div class="events-header">货款已结清  保证金剩余 <span style="color: red;">${mchtDeposit.payAmt}</span>元</div>
										</div>
									</dd>
									<dt style="background: green;">签署协议</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.fwConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
									<dt style="background: green;">完成</dt>
									<dd class="pos-left clearfix" style="height: 65px;">
										<div class="circ"></div>
										<div class="time" style="width: 200px;"><fmt:formatDate value="${mchtCloseApplication.productConfirmDate}" pattern="yyyy-MM-dd HH:mm"/></div>
									</dd>
								</c:if>
							</dl>
						</div>
					</div>
				</div>
			</div>
		</c:if>
	</div>


	<!-- 	查看信息框 -->

	<div class="modal fade" id="viewModal" tabindex="-1" role="dialog"
		aria-labelledby="viewModalLabel" data-backdrop="static"></div>
	<!-- 	查看信息框 -->
	<div class="modal fade" id="myViewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
	 data-backdrop="static">
	</div>
		
	<!--弹出驳回原因div-->
	<div class="video_box" style="position:fixed; width:500px; height:350px; top:35%; left:35%; display: none;" id="reasonsDiv">
	    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a> 
	    	<div class="panel panel-default" style="text-align:left" >
			  	<div class="biaoti">
			        <h3 class="modal-title">
			        	驳回原因
					</h3>
			    </div>
			  	<!-- 终止合同线上审核备注 -->
			  	<c:if test="${mchtCloseApplication.contractAuditStatus == '3' }">
			  	 <p style="text-align:-webkit-auto; margin:15px ; color:#123456;font-size:14px;height:80px"  >${mchtCloseApplication.contractAuditRemarks }</p>
			  	</c:if>
			  
			   <!--合同归档备注  -->
			   <c:if test="${mchtCloseApplication.contractArchiveStatus == '2' }">
			    <p style="text-align:-webkit-auto; margin:15px ; color:#123456;font-size:14px;height:80px"  >${mchtCloseApplication.contractArchiveRemarks }</p>
			   </c:if>
			   
				<!-- 	<div class="panel panel-default"  style="float:right;width:100%;">
					<button style="float:right;margin:10px ;" onclick="closeModel();">我知道了</button>
				 	</div> -->
			 </div>
			 
	</div>
		

	<script
		src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/js/jquery.eeyellow.Timeline.js"></script>
	<script type="text/javascript">
	function toEditApplyReason(id){
		$.ajax({
			url: "${ctx}/mchtCloseApplication/toEditApplyReason.shtml?id="+id, 
			type: "GET", 
			success: function(data){
				$("#viewModal").html(data);
				$("#viewModal").modal();
			},
			error:function(){
				swal('页面不存在');
			}
		});
	}
	
	function toSend(id){
		$.ajax({
			url: "${ctx}/mchtCloseApplication/toSend.shtml?id="+id, 
			type: "GET", 
			success: function(data){
				$("#viewModal").html(data);
				$("#viewModal").modal();
			},
			error:function(){
				swal('页面不存在');
			}
		});
	}
	
	function showInfo(url){
		if(url == "")	return;
		$.ajax({
			url: url, 
			type: "GET",
			success: function(data){
				$("#viewModal").html(data);
				$("#viewModal").modal();
			},
			error:function(){
				swal('页面不存在');
			}
		});
	}
	
	function toUploadProtocol(mchtCloseId){
		$.ajax({
			url: "${ctx}/mchtCloseApplication/toUploadProtocol?mchtCloseId="+mchtCloseId, 
			type: "GET", 
			success: function(data){
				$("#viewModal").html(data);
				$("#viewModal").modal();
			},
			error:function(){
				swal('页面不存在');
			}
		});
	}
	
var submitting;	
$(document).ready(function () {
	$('.VivaTimeline').vivaTimeline({
		carousel: false
	});
	
	$("#confirm").on('click',function(){
		if(submitting){
			return false;
		}
		var applyReason = $("#applyReason").val();
		if(!applyReason){
			swal("申请理由不能为空");
			return false;
		}
		submitting = true;
		$.ajax({
	        method: 'POST',
	        url: '${ctx}/mchtCloseApplication/save',
	        data: {"applyReason":applyReason},
	        cache : false,
			async : false,
	        dataType: 'json'
	    }).done(function (result) {
	        if (result.returnCode =='0000') {
	        	submitting = false;
	        	var url = "${ctx}/mchtCloseApplication/index";
	            getContent(url);
	        }else if(result.returnCode =='4004'){
	        	submitting = false;
	        	if(result.returnMsg){
	        		swal(result.returnMsg);
	        	}else{
		        	swal("提交失败，请稍后重试");
	        	}
	        }
	    });
	});
    
});


//查看驳回原因
function viewSubOrder(_this){
	  $("#reasonsDiv").show();
	};

//关闭驳回原因
$(".video_close").on('click',function(){
		 $("#reasonsDiv").hide();
	});

</script>
</body>
</html>
