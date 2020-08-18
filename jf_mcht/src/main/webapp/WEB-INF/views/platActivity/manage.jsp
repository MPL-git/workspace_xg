<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="readonly" value="true" />
<c:if test="${empty activity || activity.status==1 || activity.status==4}">
    <c:set var="readonly" value="false" />
</c:if>
<c:set var="moreInfo" value="false" />
<c:if test="${activityArea.type==1}">
    <c:set var="moreInfo" value="true" />
</c:if>


<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>活动报名</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css"/>
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css"/>
    <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />

    <style type="text/css">
        .hidden{
            display:none;
        }
        .dv1{
            border:1px solid #cccccc;
            border-width:2px 1px 1px 1px;
            padding:10px 10px;
            margin-bottom: 10px;
            line-height: 30px;
        }
    </style>
    
    <!-- 预览活动列表 -->
<style>
	.modal-preview p,
	.modal-preview ul {
		margin: 0;
	}
	.modal-preview ul {
		overflow: hidden;
		padding: 0;
	}
	.modal-preview li {
		list-style: none;
	}
	.modal-preview {
		display: none;
		position: absolute;
		top: 50%;
		left: 50%;
		z-index: 1051;
		width: 401px;
		height: 804px;
		margin: -402px 0 0 190px;
		background: url(${pageContext.request.contextPath}/static/images/active-preview/preview-bg.png) no-repeat;
		cursor: move;
	}
	.scroll-preview {
		overflow: hidden;
		position: relative;
		width: 334px;
		height: 538px;
		margin: 158px auto 0;
		cursor: default;
	}
	.close-preview {
		position: absolute;
		top: 0;
		right: 8px;
		z-index: 3;
		width: 37px;
		height: 37px;
		background: #333 url(${pageContext.request.contextPath}/static/images/active-preview/preview-close.png) no-repeat center;
		cursor: pointer;
		border-radius: 100%;

		transition: .6s ease-out;
	}
	.close-preview:hover {
		transform: rotate(360deg);
	}

	.header-preview {
		position: relative;
		height: 167px;
	}
	.header-preview img {
		display: block;
		border: none;
		width: 100%;
	}
	.header-preview p {
		position: absolute;
		bottom: 0;
		left: 0;
		z-index: 1;
		width: 100%;
		height: 24px;
		line-height: 24px;
		color: #fff;
		font-size: 14px;
		padding-left: 6px;
		box-sizing: border-box;
		background: #737779;
	}

	.con-preview {
		overflow: hidden;
		padding: 8px 6px 0;
		background: #fafafa;
	}
	.con-preview ul {
		width: 328px;
	}
	.con-preview li {
		float: left;
		width: 154px;
		height: 226px;
		margin: 0 6px 6px 0;
		background: #fff;
		border: 1px solid #ddd;
		box-sizing: border-box;
	}

	.con-preview-top {
		position: relative;
		height: 152px;
		background: #f1f1f1;
	}
	.con-preview-top div {
		position: absolute;
		top: 7px;
		left: 0;
		left: 0;
		z-index: 1;
		width: 100%;
		height: 22px;
		padding-right: 6px;
		box-sizing: border-box;
	}
	.con-preview-top span {
		display: none;
		float: right;
		width: 22px;
		height: 100%;
		margin-left: 8px;
		border-radius: 100%;
		background: #333 url(${pageContext.request.contextPath}/static/images/active-preview/preview-edit.png) no-repeat;
		cursor: pointer;

		transition: transform .3s ease-out;
	}
	.con-preview-top span:active {
		transform: scale(1.2);
	}
	.con-preview-top span.by-bot {
		background-position: 0 -66px;
	}
	.con-preview-top span.to-bot {
		background-position: 0 -44px;
	}
	.con-preview-top span.to-top {
		background-position: 0 -22px;
	}
	.con-preview-top span.by-top {
		background-position: 0 0;
	}
	.con-preview-top span.ban {
		background-color: #999;
	}
	.con-preview-top img {
		display: block;
		width: 100%;
		border: none;
	}

	.con-preview-bot {
		padding: 8px 5px 0;
	}
	.con-preview-bot p {
		overflow: hidden;
		height: 30px;
		font: 12px/14px '微软雅黑';
		color: #333;
	}
	.con-preview-bot div {
		padding-top: 6px;
		line-height: 22px;
	}
	.con-preview-bot strong,
	.con-preview-bot s {
		float: left;
		max-width: 50px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}
	.con-preview-bot strong {
		font-size: 14px;
		font-weight: bold;
		margin-right: 4px;
		color: #f55;
	}
	.con-preview-bot s {
		font-size: 12px;
		color: #999;
	}
	.con-preview-bot em {
		float: right;
		font-style: normal;
		height: 18px;
		line-height: 18px;
		padding: 0 2px;
		font-size: 12px;
		color: #f55;
		border: 1px solid #f55;
	}

	.footer-preview {
		padding-top: 20px;
		text-align: center;
	}
	.footer-preview span {
		display: inline-block;
		vertical-align: top;
		width: 100px;
		height: 34px;
		line-height: 34px;
		margin: 0 17px;
		text-align: center;
		background: #ff5050;
		font-size: 14px;
		color: #fff;
		cursor: pointer;
	}
	.footer-preview span.hide {
		display: none;
	}

	.iScrollLoneScrollbar {
		background: rgba(0, 0, 0, .3);
	}
	.iScrollIndicator {
		border: none !important;
	}
	.preCss{
		white-space: pre-wrap;
		word-wrap: break-word;
		margin: 0 0px;
		border: 0px none white;
		background-color: white;
		padding: 0 0px;
	}
</style>
</head>

<body>
<div class="x_panel container-fluid">
		<div class="row content-title">
			<div class="t-title">
			官方活动
			<a href="javascript:void(0);" onclick="getContent('${ctx}/plat/activity?name=${name}&pageNumber=${pageNumber}')">返回</a>
			</div>
		</div>
		
		<div class="x_content container-fluid sp-gl">
			<div class="row">
				<div class="col-md-12 datatable-container at-table">
					<table class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
						<tbody>
							<tr>
								<td>会场名称</td>
								<td>${activityArea.name}</td>
							</tr>
							<tr>
								<td>活动类型</td>
								<td><c:if test="${activityArea.type eq '1'}">品牌</c:if><c:if test="${activityArea.type eq '2'}">单品</c:if></td>
							</tr>
							<tr>
								<td>报名时间</td>
								<td>
									<fmt:formatDate value='${activityArea.enrollBeginTime}' pattern='yyyy-MM-dd HH:mm'/> 到 <fmt:formatDate value='${activityArea.enrollEndTime}' pattern='yyyy-MM-dd HH:mm'/>
								</td>
							</tr>
							<tr>
								<td>活动时间</td>
								<td>
									<fmt:formatDate value='${activityArea.activityBeginTime}' pattern='yyyy-MM-dd HH:mm'/> 到 <fmt:formatDate value='${activityArea.activityEndTime}' pattern='yyyy-MM-dd HH:mm'/>
								</td>
							</tr>
							<tr>
								<td>利益点</td>
								<td>
									<c:if test="${not empty activityArea.benefitPoint}">
										${activityArea.benefitPoint}<c:if test="${activityArea.preferentialType==1}"></c:if>
									</c:if>
								</td>
							</tr>

							<c:if test="${mchtInfo.mchtType !='1' or mchtInfo.isManageSelf == '1'}">
							<tr>
								<td>促销方式</td>
								<c:if test="${activityArea.isPreSell eq '1'}">
									<td>预付会场</td>
								</c:if>
								<c:if test="${activityArea.isPreSell ne '1'}">
									<td>${preferentialTypeDesc}
										<c:if test="${activityArea.preferentialType == '2' or activityArea.preferentialType == '4' }">
											<c:if test="${belong == '1'   }">
												<span style="color: red;">（说明：本优惠为跨店优惠，优惠金额按商品金额均分后由平台承担）</span>
											</c:if>
											<c:if test="${belong == '2'  }">
												<span style="color: red;">（说明：本优惠为跨店优惠，优惠金额按商品金额均分后由商家承担）</span>
											</c:if>
										</c:if>
										<c:if test="${activityArea.preferentialType == '1' }">
											<c:if test="${not empty couponList }">
												<c:if test="${couponList[0].belong == '1' }">
													<span style="color: red;">（说明：平台承担）</span>
												</c:if>
												<c:if test="${couponList[0].belong == '2' }">
													<span style="color: red;">（说明：本优惠为跨店优惠，优惠金额按商品金额均分后由商家承担）</span>
												</c:if>
											</c:if>
										</c:if>
									</td>
								</c:if>
							</tr>
							</c:if>

							<c:if test="${not empty activityArea.preferentialType && activityArea.preferentialType ne 0 && activityArea.isPreSell ne '1'}">
							<tr>
								<td>促销详情</td>
								<td>
									<c:if test="${not empty couponList}">
										<c:forEach var="coupon" items="${couponList}" varStatus="vs" >
                                           	 面额${coupon.money}元：使用条件${coupon.minimum}元，发行量${coupon.grantQuantity}张;&nbsp;&nbsp;
                                        </c:forEach>    
									</c:if>
									<c:if test="${not empty fullCut}">
										   <c:if test="${fullCut.ladderFlag==0}"> 非阶梯</c:if>
										   <c:if test="${fullCut.ladderFlag==1}"> 阶梯</c:if>
										   	满${fullCut.get("minimum")}元&nbsp;&nbsp;减${fullCut.get("money")}元<c:if test="${fullCut.sumFlag==1}">(累加)</c:if>
										   <c:if test="${not empty fullCut && fullCut.ladderFlag==1 &&  fullCut.get('ruleList').size()>0}">
		                                       <c:forEach var="rule" items="${fullCut.get('ruleList')}" varStatus="vs" >
		                                                                                                                                    满${rule.minimum}元&nbsp;&nbsp;减${rule.money}元&nbsp;&nbsp;
		                                       </c:forEach>
                                    	   </c:if>
									</c:if>
									<c:if test="${not empty fullGive}">
										<c:if test="${fullGive.type==1}">
											满额赠：满 ${fullGive.minimum}元 <c:if test="${fullGive.sumFlag==1}">(累加)</c:if>&nbsp;&nbsp;
											${fullGive.get('fullGiveDesc')}
										</c:if>
										<c:if test="${fullGive.type==2}">
											买即赠：${fullGive.get('fullGiveDesc')}
										</c:if>
									</c:if>
									<c:if test="${not empty fullDiscount && fullDiscount.get('ruleList').size()>0}">
                                    <c:if test="${fullDiscount.type == 1}">
                                        <c:forEach var="rule" items="${fullDiscount.get('ruleList')}" varStatus="vs" >
                                        	满${rule.minimum}件减${rule.money}件&nbsp;&nbsp;
                                        </c:forEach>
									</c:if>
									<c:if test="${fullDiscount.type == 2}">
                                        <c:forEach var="rule" items="${fullDiscount.get('ruleList')}" varStatus="vs" >
                                        	${rule.money}元任选${rule.minimum}件&nbsp;&nbsp;
                                        </c:forEach>
									</c:if>
									<c:if test="${fullDiscount.type == 3}">
                                        <c:forEach var="rule" items="${fullDiscount.get('ruleList')}" varStatus="vs" >
                                        	${rule.minimum}件${rule.money}折&nbsp;&nbsp;
                                        </c:forEach>
									</c:if>
									<c:if test="${fullDiscount.type == 4}">
                                        <c:forEach var="rule" items="${fullDiscount.get('ruleList')}" varStatus="vs" >
                                        	第${rule.minimum}件${rule.money}折&nbsp;&nbsp;
                                        </c:forEach>
									</c:if>
                                    </c:if>
								</td>
							</tr>
							</c:if>
							<tr>
								<td>活动描述</td>
								<td>
									<pre class="preCss">${activityArea.description}</pre>
								</td>
							</tr>
							<c:if test="${!limit && !isEnd}">
							<tr>
								<td>操作</td>
								<td><a href="javascript:;" onclick="edit(${activityArea.id});">【继续报名】</a></td>
							</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
	    </div>
	    
	    <c:if test="${activityArea.type eq '1'}">
			<div class="row" id="enrollActivitysDiv">
				<h4>报名管理</h4>
					<div class="col-md-12 datatable-container">
						<table id="datatable"
							class="table table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row" class="active">
									<th>特卖ID</th>
									<th>专区名称</th>
									<th>品牌</th>
									<th>商品数</th>
									<th>销量</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${activityCustoms}" var="activityCustom">
								<tr>
							      <td class="w_h_100_36">${activityCustom.id}</td>
							      <td class="w_h_280_36">${activityCustom.name}</td>
							      <td class="w_h_100_36">${activityCustom.productBrandName}</td>
							      <td class="w_h_374_36 f_f_c_l">${activityCustom.acceptProductCount}</td>
							      <td class="w_h_374_36 f_f_c_l">${activityCustom.saleQuantity}</td>
							      <td class="w_h_90_36">
							      	${activityCustom.statusDesc}
							      	<c:if test="${activityCustom.designAuditStatus == '3' }">
							      		<p class="ellipsis f55" style="margin: 0;">设计驳回</p>
							      	</c:if>
							      </td>
							      <td class="w_h_374_36 f_f_c_l">
							      		<a href="javascript:;" data-activityareaid="${activityCustom.activityAreaId}" data-activityid="${activityCustom.id}" name="toView">查看</a>
							      </td>
    							<tr>
    						</c:forEach>	
							</tbody>
						</table>
					</div>
			</div>
			</c:if>
	    
	    
	</div>

<!-- 预览活动列表 -->
<div class="modal-preview" id="modal_preview">
	<div class="scroll-preview" id="scroll_preview">
		<div class="main-preview" id="main_preview">
			<div class="header-preview">
				<img src="" alt="" id="header_preview_img">
				<p id="header_preview_txt"></p>
			</div>

			<div class="con-preview" id="con_preview"></div>
		</div>
	</div>

	<div class="footer-preview">
		<span class="btn-preview" id="order_edit">编辑排序</span>
		<span class="btn-preview hide" id="order_post">提交排序</span>
		<span class="btn-preview hide" id="order_cancer">取消排序</span>
	</div>

	<div class="close-preview" id="close_preview"></div>


	<style>
		/*弹出提示*/
		.alert-preview {
			display: none;
			position: absolute;
			top: 0;
			left: 0;
			z-index: 2;
			width: 100%;
			height: 100%;
			color: #333;
			font-size: 14px;
		}

		/*温馨提示*/
		.alert-preview-con {
			display: none;
			overflow: hidden;
			position: absolute;
			top: 50%;
			left: 50%;
			width: 277px;
			height: 133px;
			margin: -66.5px 0 0 -138.5px;
			border-radius: 8px;
			background: #fff;
			box-shadow: 0 0 6px #ddd;
			cursor: default;
		}
		.alert-preview-body {
			height: 96px;
			text-align: center;
		}
		.alert-preview-body h4 {
			font-size: 20px;
			line-height: 36px;
			margin: 0;
			padding-top: 12px;
			font-weight: bold;
		}
		.alert-preview-body p {
			line-height: 30px;
		}
		.alert-preview-footer {
			height: 36px;
			line-height: 36px;
			text-align: center;
			border-top: 1px solid #ddd;
		}
		.alert-preview-footer span {
			float: left;
			width: 50%;
			height: 100%;
			box-sizing: border-box;
			cursor: pointer;
		}
		.alert-preview-footer span:first-child {
			border-right: 1px solid #ddd;
		}

		#alert_preview_delay {
			height: 60px;
		}
	</style>
	<div class="alert-preview" id="alert_preview">
		<!-- 编辑-取消与确认 -->
		<div class="alert-preview-con" id="alert_preview_confirm">
			<div class="alert-preview-body">
				<h4>温馨提示</h4>
				<p>是否取消排序</p>
			</div>
			<div class="alert-preview-footer">
				<span class="yes" id="alert_preview_yes">确认</span>
				<span class="no" id="alert_preview_no">取消</span>
			</div>
		</div>

		<!-- 编辑-成功或失败 -->
		<div class="alert-preview-con" id="alert_preview_delay">
			<div class="alert-preview-body">
				<h4>提交成功</h4>
			</div>
		</div>
	</div>
</div>

<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
     data-backdrop="static">
</div>

<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/imageUpload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.serializejson.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/iscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/tmpl.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/draggabilly.pkgd.min.js"></script>

<!-- 预览活动列表 -->
<script id="tpl_data" type="text/html">
	<ul>
	{% for (var i = 0; i < o.list.length; i++) { %}
		<li data-id="{%= o.list[i].id %}" data-activityproductid="{%= o.list[i].activityProductId %}">
			<div class="con-preview-top">
				<div>
					<span class="by-bot" title="移到最低" data-id=""></span>
					<span class="to-bot" title="向下移" data-id=""></span>
					<span class="to-top" title="向上移" data-id=""></span>
					<span class="by-top" title="移到最顶" data-id=""></span>
				</div>
				<img src="${ctx}/file_servelt{%= o.list[i].img %}" alt="">
			</div>
			<div class="con-preview-bot">
				<p title="{%= o.list[i].name %}">{%= o.list[i].name %}</p>
				<div>
					<strong title="¥{%= o.list[i].price %}">¥{%= o.list[i].price %}</strong>
					<s title="¥{%= o.list[i].cost %}">¥{%= o.list[i].cost %}</s>
					<em>{%= o.list[i].discount %}折</em>
				</div>
			</div>
		</li>
	{% } %}
	</ul>
</script>



<script type="text/javascript">
function edit(id) {
    var url = "${ctx}/plat/activity/edit?id=" + id;
    getContent(url);
//    $.ajax({
//        url: url,
//        type: "GET",
//        success: function (data) {
//            $("#viewModal").html(data);
//            $("#viewModal").modal();
//        },
//        error: function () {
//            swal('页面不存在');
//        }
//   });
}

//拖动预览活动列表
new Draggabilly(modal_preview);

// 预览活动列表
function viewAndSort(activityId){
	$.ajax({
		url: '${ctx}/plat/activity/getProducts', 
		type: "GET",
		data : {activityId:activityId},
		async: false, 
		success: function (data) {
			viewAndSortSucsess(data);
		},
		error: function (data) {
			swal(data);
		}
	});
	
	// 关闭
	$('#close_preview').click(function () {
		$('#modal_preview').fadeOut();
	});
}

function viewAndSortSucsess(data) {
	// 赋值
	$('#con_preview').html(tmpl('tpl_data', data));

	$('#header_preview_img').attr('src', "${ctx}/file_servelt"+data.posterPic);
	$('#header_preview_txt').text($('#benefitPoint').val());

	$('#con_preview li:first-child .by-top').addClass('ban');
	$('#con_preview li:first-child .to-top').addClass('ban');
	$('#con_preview li:last-child .by-bot').addClass('ban');
	$('#con_preview li:last-child .to-bot').addClass('ban');

	// 重置
	$('#alert_preview').hide();
	$('#order_edit').removeClass('hide').siblings().addClass('hide');
	$('.alert-preview-con').hide();

	// 显示
	$('#modal_preview').stop().fadeIn();

	// 生成滚动条
	new IScroll('#scroll_preview', {
		mouseWheel: true,
		scrollbars: true
	});

	// 编辑排序
	$('#order_edit').click(function () {
		$(this).addClass('hide').siblings().removeClass('hide');
		$('.con-preview-top span').show();
	});

	 // 提交排序
	 var order_post_loading = false;
	$('#order_post').click(function () {
		if (order_post_loading) {
			return;
		}
		order_post_loading = true;
		// 请求返回结果
		var activityProductIds="";
        $("#con_preview").find("ul").find("li").each(function(){
        	activityProductIds+=$(this).data("activityproductid")+",";
        });
        if($.trim(activityProductIds)!=""){
        	activityProductIds=activityProductIds.substring(0, activityProductIds.length-1);
        }
        $.ajax({
        	url: '${ctx}/mcht/activity/saveSort', 
            type: "GET",
            data : {activityProductIds:activityProductIds},
            async: false, 
            success: function (data) {
				$('#alert_preview_delay h4').html('提交成功');
				$('#order_edit').removeClass('hide').siblings().addClass('hide');
				$('.con-preview-top span').hide();
				$('#alert_preview_delay').show().siblings().hide();
				$('#alert_preview').stop(true).fadeIn(100).delay(1000).fadeOut(100);
				order_post_loading = false;
            },
            error : function() {
				$('#alert_preview_delay h4').html('提交失败');
				$('#alert_preview_delay').show().siblings().hide();
				$('#alert_preview').stop(true).fadeIn(100).delay(1000).fadeOut(100);
				order_post_loading = false;
			}
        });
	});

	 // 取消排序
	$('#order_cancer').click(function () {
		$('#alert_preview_confirm').show().siblings().hide();;
		$('#alert_preview').stop().fadeIn();
	});

	// 取消-取消提示
	$('#alert_preview_no').click(function () {
		$('#alert_preview').hide();
	});

	// 确认-取消提示
	$('#alert_preview_yes').click(function () {
		viewAndSortSucsess(data);
	});

	// 移至最低
	$('.by-bot').click(function () {
		var that = $(this);

		if (that.hasClass('ban')) {
			return;
		}

		that.addClass('ban').siblings().removeClass('ban');
		that.siblings('.to-bot').addClass('ban');

		$('#con_preview li:last-child span').removeClass('ban');

		if (that.closest('li').index() == 0) {
			$('#con_preview li:nth-child(2) .by-top').addClass('ban');
			$('#con_preview li:nth-child(2) .to-top').addClass('ban');
		}

		$('#con_preview ul').append(that.closest('li'));
	});

	// 下移
	$('.to-bot').click(function () {
		var that = $(this);
		
		if (that.hasClass('ban')) {
			return;
		}

		if (that.closest('li').index() == 0) {
			that.removeClass('ban').siblings().removeClass('ban');

			$('#con_preview li:nth-child(2) span').removeClass('ban');
			$('#con_preview li:nth-child(2) .by-top').addClass('ban');
			$('#con_preview li:nth-child(2) .to-top').addClass('ban');

			if (that.closest('ul').find('li').length == 2) {
				$('#con_preview li:first-child .by-bot').addClass('ban');
				$('#con_preview li:first-child .to-bot').addClass('ban');
			}
		} else if (that.closest('li').index() == that.closest('ul').find('li').length - 2) {
			that.addClass('ban').siblings('.by-bot').addClass('ban');
			$('#con_preview li:last-child span').removeClass('ban');
		}

		$('#con_preview li').eq(that.closest('li').index() + 1).after(that.closest('li'));
	});

	// 上移
	$('.to-top').click(function () {
		var that = $(this);
		
		if (that.hasClass('ban')) {
			return;
		}

		if (that.closest('li').index() == 1) {
			$('#con_preview li:first-child span').removeClass('ban');

			if (that.closest('ul').find('li').length == 2) {
				$('#con_preview li:first-child .by-bot').addClass('ban');
				$('#con_preview li:first-child .to-bot').addClass('ban');
			}

			that.addClass('ban').siblings().removeClass('ban').siblings('.by-top').addClass('ban');
		} else if (that.closest('li').index() == that.closest('ul').find('li').length - 1) {
			$('#con_preview li:last-child span').removeClass('ban');

			$('#con_preview li').eq(that.closest('li').index() - 1).find('.by-bot').addClass('ban');
			$('#con_preview li').eq(that.closest('li').index() - 1).find('.to-bot').addClass('ban');
		}

		$('#con_preview li').eq(that.closest('li').index() - 1).before(that.closest('li'));
	});

	// 移至最顶
	$('.by-top').click(function () {
		var that = $(this);
		
		if (that.hasClass('ban')) {
			return;
		}

		that.addClass('ban').siblings().removeClass('ban');
		that.siblings('.to-top').addClass('ban');

		$('#con_preview li:first-child span').removeClass('ban');

		if (that.closest('li').index() == that.closest('ul').find('li').length - 1) {
			$('#con_preview li:nth-last-child(2) .by-bot').addClass('ban');
			$('#con_preview li:nth-last-child(2) .to-bot').addClass('ban');
		}

		$('#con_preview ul').prepend(that.closest('li'));
	});
}

    var dataFromValidate;
    $(function () {

        <c:if test="${readonly}">
        disableForm("dataFrom", true);
        </c:if>

        $.metadata.setType("attr", "validate");
        dataFromValidate = $("#dataFrom").validate({
            highlight: function (element) {
                $(element).addClass('error');
                $(element).closest('tr').find("td").first().addClass('has-error');
            },
            success: function (label) {
                label.closest('tr').find("td").first().removeClass('has-error');
            },
            errorPlacement: function (error, element) {
                error.appendTo($(element).closest('td'));
            }
        });
		
        $("a[name='toView']").on('click',function(){
        	var id = $(this).data("activityareaid");
        	var activityId = $(this).data("activityid");
        	var url = "${ctx}/plat/activity/viewActivity?id=" + id+"&activityId="+activityId;
//            $.ajax({
//                url: url,
//                type: "GET",
//                success: function (data) {
//                   $("#viewModal").html(data);
//                    $("#viewModal").modal();
//               },
//                error: function () {
//                	swal('页面不存在');
//                }
//            });
        	getContent(url);
        });
        
    });


    function commitSave() {
        enableForm("dataFrom");
        if (dataFromValidate.form()) {

            <c:if test="${moreInfo}">

                var entryPic = $("input[name='activity.entryPic']");
                var entryPics = entryPic.parent().children("img");
                if(entryPics.length<=0){
                    swal({
                        title: '请上传入口图',
                        type: "error",
                        confirmButtonText: "确定"
                    });
                    return;
                }
                if("${activity.entryPic}"=="" || entryPics.attr("src").indexOf("${activity.entryPic}") < 0){//有修改
                	uploadImg("activity.entryPicFile", entryPic);
                }

                var posterPic = $("input[name='activity.posterPic']");
                var posterPics = posterPic.parent().children("img");
                if(posterPics.length<=0){
                    swal({
                        title: '请上传头部海报',
                        type: "error",
                        confirmButtonText: "确定"
                    });
                    return;
                }
                if("${activity.posterPic}"=="" || posterPics.attr("src").indexOf("${activity.posterPic}") < 0){//有修改
                	uploadImg("activity.posterPicFile", posterPic);
                }

            </c:if>


            var dataJson = $("#dataFrom").serializeArray()
            dataJson.push({"name":"json", "value":JSON.stringify($("#dataFrom").find(':input').filter(function () {
                return $.trim(this.value).length > 0
            }).serializeJSON())});

            $.ajax({
                url: "${ctx}/plat/activity/save",
                type: 'POST',
                dataType: 'json',
                cache: false,
                async: false,
                data: dataJson,
                timeout: 30000,
                success: function (result) {
                    if (result.success) {
                        //$("#viewModal").modal('hide');
                        //table.ajax.reload();
//                        swal({
//                            title: "提交成功!",
//                            type: "success",
//                            confirmButtonText: "确定"
//
//                        });
                        listProduct(result.data.activity.id, 1);
                    } else {
                        swal({
                            title: result.returnMsg,
                            type: "error",
                            confirmButtonText: "确定"
                        });
                    }

                },
                error: function () {
                    swal({
                        title: "提交失败！",
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
            });
        }

    }

    //禁用form表单中所有的input[文本框、复选框、单选框],select[下拉选],多行文本框[textarea]
    function disableForm(formId) {
        $("#"+formId+ " :text").attr("disabled","disabled");
        $("#"+formId+ " textarea").attr("disabled","disabled");
        $("#"+formId+ " select").attr("disabled","disabled");
        $("#"+formId+ " :radio").attr("disabled","disabled");
        $("#"+formId+ " :checkbox").attr("disabled","disabled");

        <c:if test="${not empty auth}">
            <c:if test="${auth.activityNameFlag==1}">
            $("input[name='activity.name']").removeAttr("disabled");
            </c:if>
            <c:if test="${auth.activityTypeFlag==1}">
            $("select[name='activity.productTypeId']").removeAttr("disabled");
            </c:if>
            <c:if test="${auth.activityBrandFlag==1}">
            $("select[name='activity.productBrandId']").removeAttr("disabled");
            </c:if>
            <c:if test="${auth.activityEntryFlag==1}">
            $("input[name='activity.entryPic']").removeAttr("disabled");
            </c:if>
            <c:if test="${auth.activityPosterFlag==1}">
            $("input[name='activity.posterPic']").removeAttr("disabled");
            </c:if>
        </c:if>
    }

    function enableForm(id) {
        $("#"+id+ " :text").removeAttr("disabled");
        $("#"+id+ " textarea").removeAttr("disabled");
        $("#"+id+ " select").removeAttr("disabled");
        $("#"+id+ " :radio").removeAttr("disabled");
        $("#"+id+ " :checkbox").removeAttr("disabled");
    }

    function listProduct(activityId, status) {
        $('#viewModal').on('hidden.bs.modal', function (e) {
            var url = "${ctx}/plat/activity/listProductPage?activityId=" + activityId + "&status=" + status;
            getContent(url);
        })

        $("#viewModal").modal('hide');
    }


    //上传图片
    function uploadImg($img,$valueFiled) {
		var oFile = document.getElementById($img).files[0];
    	var formData = new FormData();
    	formData.append("file",oFile);
    	$.ajax({ 
    		url : "${ctx}/common/fileUpload?fileType=3", 
    		type : 'POST', 
    		data : formData, 
    		async: false,
    		// 告诉jQuery不要去处理发送的数据
    		processData : false, 
    		// 告诉jQuery不要去设置Content-Type请求头
    		contentType : false,
    		beforeSend:function(){
    			console.log("图片片上传中，请稍候");
    		},
    		success : function(data) {
                if (data.returnCode=="0000") {
                    $valueFiled.val(data.returnData);
                } else {
                    swal({
                        title: "图片上传失败！",
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
    		}, 
    		error : function(responseStr) { 
    			swal("图片上传失败");
    		} 
    		});


    }


    function loadImageFile(obj) {
        if (obj.files.length === 0) {
            return;
        }
        var oFile = obj.files[0];
        if(oFile.size>100*1024){
        	swal("图片大小不能超过100K");
            return;
        }
        var rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
        if (!rFilter.test(oFile.type)) {
        	swal("请选择图片文件");
            return;
        }
        if($(obj).parent().children("img").length<=0){
            $('<img>').appendTo( $(obj).parent() );;
        }
        var oFReader = new FileReader();
        oFReader.onload = function(oFREvent) {
            var img=new Image();
            img.src=oFREvent.target.result;
            img.onload=function(){
                if(img.width!=800||img.height!=400){
                	swal("请选择尺寸为800*400的图片");
                    return;
                }else{
                    $(obj).parent().children("img").attr("src",oFREvent.target.result);
                    $(obj).parent().children("div").remove();
                }
            }
        };
        oFReader.readAsDataURL(oFile);
    }

</script>
</body>
</html>
