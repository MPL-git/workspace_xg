<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

<html>
<head>
<script type="text/javascript">

	var viewerPictures;

	$(function(){
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
				$("#viewer-pictures").hide();
			}});

		$(".l-dialog-close").live("click", function(){
			$("#searchbtn").click();
		});

	});

	function viewProduct(id) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: 1200,
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

	function viewerPic(productId){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$.ajax({
			url : "${pageContext.request.contextPath}/product/getPoductPic.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {productId:productId},
			timeout : 30000,
			success : function(data) {

				if(data&&data.length>0){
					for (var i=0;i<data.length;i++)
					{	if(data[i].pic.indexOf("http") >= 0){
						$("#viewer-pictures").append('<li><img data-original="'+data[i].pic+'" src="'+data[i].pic+'" alt=""></li>');
					}else{
						$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" alt=""></li>');
					}
					}
					viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
							$("#viewer-pictures").hide();
						}});
					$("#viewer-pictures").show();
					viewerPictures.show();
				}


			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});

	}

 var listConfig={
      url:"/resourceLocationManagement/memberLotteryList.shtml",
      btnItems:[],
		listGrid:{ columns: [
				{ display: '品牌/商品ID', width: 150, hide:${type != 2},render: function (rowdata, rowindex) {
						return rowdata.productBrandName+"<br>"+rowdata.couponProductCode;
					}},
				{ display: '面额',  name: 'couponAmount', width: 150, hide:${type != 2}},
				{ display: '会员ID',  name: 'memberId', width: 150, hide:${type == 3}},
				{ display: '会员昵称',  name: 'memberNick', width: 150},
				{ display: '领劵时间', width: 150, hide:${type != 2},render: function (rowdata, rowindex) {
						if(rowdata.createDate){
							var createDate = new Date(rowdata.createDate);
							return createDate.format("yyyy-MM-dd");
						}else{
							return "";
						}
					}},
				{ display: '使用时间', width: 150, hide:${type != 2},render: function (rowdata, rowindex) {
						if(rowdata.useDate){
							var useDate = new Date(rowdata.useDate);
							return useDate.format("yyyy-MM-dd");
						}else{
							return "";
						}
					}},
				{ display: '优惠券状态', width: 150, hide:${type != 2},render: function (rowdata, rowindex) {
						if(rowdata.useStatus == '0'){
							return '未使用';
						}else if(rowdata.useStatus == '1'){
							return "已使用";
						}else{
							return "";
						}
					}},
				{ display: '订单号',  name: 'combineOrderCode', width: 150, hide:${type != 2}},
				{ display: '会员手机号',  name: 'memberMobile', width: 150, hide:${type != 3}},
				{ display: '消耗积分',  name: 'consumeIntegral', width: 150, hide:${not empty type}},
				{ display: '抽奖时间', width: 150, hide:${not empty type && type != 3 },render: function (rowdata, rowindex) {
						if(rowdata.createDate){
							var createDate = new Date(rowdata.createDate);
							return createDate.format("yyyy-MM-dd hh:mm:ss");
						}else{
							return "";
						}
					}},
				{ display: '收货人',  name: 'receiverName', width: 150, hide:${type != 3}},
				{ display: '收货电话',  name: 'receiverPhone', width: 150, hide:${type != 3}},
				{ display: '收货地址',  name: 'receiverAddress', width: 150, hide:${type != 3}},
				{ display: '抽中类型', name: '', align:'center', width: 150, hide:${not empty type}, render: function(rowdata, rowindex) {
					if(rowdata.type == 1){
						return "积分";
					}else if(rowdata.type == 2){
						return "优惠券";
					}else if(rowdata.type == 3){
						return "商品";
					}else if(rowdata.type == 4){
						return "谢谢参与";
					}
				}},
				{ display: '详情', name: '', align:'center', width: 330, hide:${not empty type}, render: function(rowdata, rowindex) {
						if(rowdata.type == 1){
							return rowdata.integral;
						}else if(rowdata.type == 2){
							var html=[]
							html.push("面额:"+rowdata.couponAmount+"<br>");
							html.push("品牌:"+rowdata.productBrandName+"<br>");
							html.push("商品ID:"+rowdata.couponProductCode+"<br>");
							return html.join("");
						}else if(rowdata.type == 3){
							var html=[];
							var h = "";
							if(rowdata.productPic!=null&&(rowdata.productPic.indexOf("http") >= 0)){
								h += "<div class='no-check' style='display:  inline-flex; margin:  20px;'><img src='"+rowdata.productPic+"' width='100' height='100' onclick='viewerPic("+rowdata.productId+")'>";
							}else{
								h += "<div class='no-check' style='display:  inline-flex; margin:  20px;'><img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.productPic+"' width='100' height='100' onclick='viewerPic("+rowdata.productId+")'>";
							}
							html.push(h);
							html.push("<div class='width-226'><p class='ellipsis'>"+rowdata.productName+"</p><p>商品货号:"+rowdata.productArtNo+"</p><p>商品ID:"+rowdata.productCode+"</p></div>")
							html.push("<div>")
							return html.join("");
						}else if(rowdata.type == 4){
							return "谢谢参与";
						}
				}},
		          ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
     container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        

  };

</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div id="topmenu"></div>
		<div class="search-pannel">
			<div class="search-tr">
				<c:if test="${not empty type}">
					<input type="hidden" id="type" name="type" value="${type}">
					<input type="hidden" id="integralProduct" name="integralProduct" value="${integralProduct}">
					<input type="hidden" id="couponId" name="couponId" value="${couponId}">
					<c:if test="${type eq 2}">
						<div class="search-td">
							<div class="search-td-label">会员ID：</div>
							<div class="search-td-condition">
								<input name="memberId" value="${memberId }">
							</div>
						</div>
						<div class="search-td">
							<div class="search-td-label">用户昵称：</div>
							<div class="search-td-condition">
								<input name="memberNick" value="${memberNick }">
							</div>
						</div>
						<div class="search-td">
							<div class="search-td-label">状态：</div>
							<div class="search-td-condition">
								<select id="useStatus" name="useStatus" >
									<option value="">请选择</option>
									<option value="0">未使用</option>
									<option value="1">已使用</option>
								</select>
							</div>
						</div>
					</c:if>
					<c:if test="${type eq 3}">
					<div class="search-td">
						<div class="search-td-label">手机号：</div>
						<div class="search-td-condition">
							<input name="memberMobile" value="${memberMobile }">
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">用户昵称：</div>
						<div class="search-td-condition">
							<input name="memberNick" value="${memberNick }">
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">收货人：</div>
						<div class="search-td-condition">
							<input name="receiverName" value="${receiverName }">
						</div>
					</div>
					</c:if>
				</c:if>
				<c:if test="${empty type}">
					<div class="search-td">
						<div class="search-td-label" style="float:left;">抽奖日期：</div>
						<div class="l-panel-search-item" >
							<input type="text" id="createDate" name="createDate" />
							<script type="text/javascript">
								$(function() {
									$("#createDate").ligerDateEditor( {
										showTime : false,
										labelWidth : 150,
										width:150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">会员ID：</div>
						<div class="search-td-condition">
							<input name="memberId" value="${memberId }">
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">会员昵称：</div>
						<div class="search-td-condition">
							<input name="memberNick" value="${memberNick }">
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">抽中类型：</div>
						<div class="search-td-condition">
							<select id="type" name="type" >
								<option value="">请选择</option>
								<option value="1">积分</option>
								<option value="2">优惠券</option>
								<option value="3">商品</option>
								<option value="4">谢谢惠顾</option>
							</select>
						</div>
					</div>
				</c:if>

				<div class="search-td-search-btn">
					<div id="searchbtn">搜索</div>
				</div>
			</div>

		</div>
	</form>

	   <div id="maingrid" style="margin: 0; padding: 0"></div>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
