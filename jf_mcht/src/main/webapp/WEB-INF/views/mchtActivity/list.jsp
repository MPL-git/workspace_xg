<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>品牌特卖活动管理</title>

	<style type="text/css">
		.color-red{
			color: #FC0000;
		}
		.color-green{
			color: #00FC28;
		}
		.color-orange{
			color: orange;
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
</style>
</head>

<body>
<div class="x_panel container-fluid py-tm">
	<input type="hidden" id="mchtInfoStatus">
	<div class="row content-title">
		<div class="t-title">
			品牌特卖活动管理
			<a href='javascript:void(0);' onclick="edit();" >创建活动</a>
		</div>
	</div>
	<div class="search-container container-fluid">
		<input type="hidden" name="pageNumber" id="pageNumber" value="${pageNumber}">
		<form class="form-horizontal" id="searchForm">
			<div class="form-group">
				<label for="name" class="col-md-1 control-label search-lable">活动名称：</label>
				<div class="col-md-2 search-condition">
					<input class="form-control nameWidth200" type="text"  id="name" name="name" value="${name}">
				</div>
				<label for="productBrandId" class="col-md-1 control-label search-lable">品牌：</label>
				<div class="col-md-2 search-condition">
					<select class="form-control" name="productBrandId" id="productBrandId">
						<option value="">--请选择--</option>
						<c:forEach var="brand" items="${brandList}">
							<option value="${brand.id}" <c:if test="${productBrandId eq brand.id}">selected="selected"</c:if>>${brand.name}</option>
						</c:forEach>
					</select>
				</div>
				<label for="status" class="col-md-1 control-label search-lable">状态：</label>
				<div class="col-md-2 search-condition">
					<select class="form-control" name="status" id="status">
						<option value="">--请选择--</option>
						<c:forEach var="status" items="${statusList}">
							<option value="${status.value}" <c:if test="${selectedStatus eq status.value}">selected="selected"</c:if>>${status.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-3 text-center search-btn">
					<button type="button"  class="btn btn-info" id="btn-query">搜索</button>
				</div>
			</div>
		</form>

	</div>
	<div class="clearfix"></div>
	<div class="x_content container-fluid">
		<div class="row">
			<div class="col-md-12 datatable-container">
				<table id="datatable" class="table table-striped table-bordered dataTable no-footer" role="grid" aria-describedby="datatable_info">
					<thead>
					<tr role="row">
						<th>特卖ID</th>
						<th>活动名称/利益点</th>
						<th>商品数</th>
						<th>销量</th>
						<th>排期</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>


<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
	 data-backdrop="static">
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

<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
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

<script>
// 拖动预览活动列表
new Draggabilly(modal_preview);

// 预览活动列表
function viewAndSort(activityId){
	$.ajax({
		url: '${ctx}/mcht/activity/getProducts', 
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
		


	function edit(id) {
		var mchtInfoStatus = $("#mchtInfoStatus").val();
		if(mchtInfoStatus == "2"){
			swal("对不起，合作状态暂停，不能提报活动");
			return false;
		}
		var url = "${ctx}/mcht/activity/edit";
		if(id){
			url += "?id=" + id;
		}
		// $.ajax({
		//     url: url,
		//     type: "GET",
		//     success: function (data) {
		//         $("#viewModal").html(data);
		//         $("#viewModal").modal();
		//     },
		//     error: function () {
		//         swal('页面不存在');
		//     }
		// });
		getContent(url);
	}
	
	function view(id) {
		var mchtInfoStatus = $("#mchtInfoStatus").val();
		if(mchtInfoStatus == "2"){
			swal("对不起，合作状态暂停，不能提报活动");
			return false;
		}
		var url = "${ctx}/mcht/activity/view";
		if(id){
			url += "?id=" + id;
		}
		var name = $("#name").val();
		var productBrandId = $("#productBrandId").val();
		var status = $("#status").val();
		var pageNumber = $("li[class='paginate_button active']").find("a").first().text();
		if(name){
			url+="&name="+encodeURI(encodeURI(name));
		}
		if(productBrandId){
			url+="&productBrandId="+productBrandId;
		}
		if(status){
			url+="&status="+status;
		}
		if(pageNumber){
			url+="&pageNumber="+pageNumber;
		}
		getContent(url);
	}

	function del(id){
		swal({
			title: "确定要删除该活动吗?",
			type: "warning",
			showCancelButton: true,
			confirmButtonText: "确定",
			cancelButtonText: "取消",
			closeOnConfirm: false
		},
		function(){
			var ids=[];
			ids.push(id);
			var postData= {"ids":JSON.stringify(ids)};
			$.ajax({
				url : "${ctx}/mcht/activity/delete",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : postData,
				timeout : 30000,
				success : function(result) {
					if (result.success) {
						table.ajax.reload();
					} else {
						swal({
							title: result.returnMsg,
							type: "error",
							confirmButtonText: "确定",
							closeOnConfirm: true
						});
					}

				},
				error : function() {
					swal({
						title: "处理失败！",
						type: "error",
						timer: 1500,
						confirmButtonText: "确定"
					});
				}
			});
		});
	}

	var table,
		status_val;
	$(document).ready(function () {
		var isInit = true;
		var pageNumber;
		table = $('#datatable').dataTable({
			"ajax": function (data, callback, settings) {
				pageNumber = $("#pageNumber").val();
				var param = $("#searchForm").serializeArray();
				param.push({"name": "pageSize", "value": data.length});
				if (data.start > 0) {
					param.push({"name": "pageNumber", "value": data.start / data.length + 1});
				} else if(isInit && pageNumber && parseInt(pageNumber)>0){
					param.push({"name": "pageNumber", "value": pageNumber});
				} else {
					param.push({"name": "pageNumber", "value": 1});
				}
				status_val = param[2].value;

				$.ajax({
					method: 'POST',
					url: '${ctx}/mcht/activity/list',
					data: param,
					dataType: 'json'
				}).done(function (result) {
					if (result.success) {
						var returnData = {};
						returnData.recordsTotal = result.data.page.totalRow;
						returnData.recordsFiltered = result.data.page.totalRow; // 后台不实现过滤功能，每次查询均视作全部结果
						returnData.data = result.data.page.list;
						callback(returnData);
						var mchtInfoStatus = result.data.mchtInfoStatus;
						$("#mchtInfoStatus").val(mchtInfoStatus);
					}
				});
			},
			"language": {"url": '${ctx}/static/cn.json'},
			"autoWidth": true,   // 禁用自动调整列宽
			"stripeClasses": ['odd', 'even'], // 为奇偶行加上样式，兼容不支持CSS伪类的场合
			"order": [[1, 'asc']],          // 取消默认排序查询,否则复选框一列会出现小箭头
			"processing": true,  // 隐藏加载提示,自行处理
			"serverSide": true,   // 启用服务器端分页
			"searching": false,   // 禁用原生搜索
			"bSort": false,
			"bServerSide": true,
			"columns": [
				{"data": "activity.id","width":80},
				{"data": "name", "className" : "text-left", "width":260,"render": function (data, a, b) {
					return '<span style="word-break: break-all;">'+data+'</span></br><span class="ellipsis f55" style="word-break: break-all;margin: 0;">' + b.benefitPoint + '</span>';
				}},
				{"data": "activity.productCount","width":80},
				{"data": "activity.saleQuantity"},
				// {"data": "benefitPoint"},
				{"data": "activity.submitTime", "className" : "text-left","width":160, "render": function (data, type, row, meta) {
					if(row.activityBeginTime && row.activityEndTime){
						return '<p style="margin: 0;">' + row.activityBeginTime + '</p><p style="margin: 0;color:red;">' + row.activityEndTime + '</p>';
					}else{
						return "";
					}
				}},
				{"data": "statusStr", "width":100,"render": function(data, type, row, meta){
					var html = [];
					if(data=="活动中"){
						html.push('<span class="color-red">', data, '</span>');
					}else if(data=="审核中"){
						html.push('<span class="color-green">', data, '</span>');
					}else if(data=="已驳回"){
						html.push('<span class="color-orange">', data, '</span>');
					}else{
						html.push(data);
					}
					if(row.activity.designAuditStatus == '3') {
						html.push('<p class="ellipsis f55" style="margin: 0;">设计驳回</p>');
					}
					return html.join("");
				}},
				{
					"data": "activity.id","width":60,"render": function (data, type, row, meta) {
						var html = [];
						html.push('<a class="table-opr-btn" href="javascript:void(0);" onclick="view(', "'"+row.id+"'", ');" >查看</a>');
						return html.join("");
				}}
			],
		      
		       "initComplete": function(settings, json) {
		           //这里在初始化完成后修改一下显示的页数
		           if(isInit&& parseInt(pageNumber)!=0){
		    		   isInit = false;
		    		   table.page(parseInt(pageNumber)-1).draw(false);
		    	   }
		       }
		}).api();
		
		$('#btn-query').on('click', function (event) {
			$("#pageNumber").val(1);
			table.ajax.reload();
		});
		
		$("#name").keydown(function(e){
	    	if(e.keyCode==13){
	    		table.ajax.reload();
	            return false;
	    	}
	    });
		
		$(".paginate_button").on('click',function(){
			$("#pageNumber").val($(this).find("a").text());
		});
	});

	function listProduct(activityId, status) {
		var url = "${ctx}/mcht/activity/listProductPage?activityId=" + activityId + "&status=" + status;
		getContent(url);
	}
</script>
</body>
</html>
