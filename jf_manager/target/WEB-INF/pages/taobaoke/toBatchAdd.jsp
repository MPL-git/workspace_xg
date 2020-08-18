<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
 <script type="text/javascript">
 $(function(){
	
	 $("#productType1Id").bind('change',function(){
			var productType1Id = $(this).val();
			if(productType1Id){
				$("#productType2Id").empty();
				$("#productTypeId").empty();
				$.ajax({
					url : "${pageContext.request.contextPath}/taobaoke/getProductTypesByParentId.shtml?parentId="+productType1Id,
					secureuri : false,
					dataType : 'json',
					cache : false,
					async : false,
					success : function(data) {
						if ("0000" == data.returnCode) {
							var productTypeList = data.productTypeList;
							var html=[];
							html.push('<option value="">请选择</option>');
							for(var i=0;i<productTypeList.length;i++){
								html.push('<option value="'+productTypeList[i].id+'">'+productTypeList[i].name+'</option>');
							}
							$("#productType2Id").append(html.join(""));
							$("#productTypeId").append('<option value="">请选择</option>');
						}else{
							$.ligerDialog.error(data.returnMsg);
						}
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			}else{
				$("#productType2Id").empty();
				$("#productType2Id").append('<option value="">请选择</option>');
				$("#productTypeId").empty();
				$("#productTypeId").append('<option value="">请选择</option>');
			}
		});
		
		$("#productType2Id").bind('change',function(){
			var productType2Id = $(this).val();
			if(productType2Id){
				$("#productTypeId").empty();
				$.ajax({
					url : "${pageContext.request.contextPath}/taobaoke/getProductTypesByParentId.shtml?parentId="+productType2Id,
					secureuri : false,
					dataType : 'json',
					cache : false,
					async : false,
					success : function(data) {
						if ("0000" == data.returnCode) {
							var productTypeList = data.productTypeList;
							var html=[];
							html.push('<option value="">请选择</option>');
							for(var i=0;i<productTypeList.length;i++){
								html.push('<option value="'+productTypeList[i].id+'">'+productTypeList[i].name+'</option>');
							}
							$("#productTypeId").append(html.join(""));
						}else{
							$.ligerDialog.error(data.returnMsg);
						}
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			}else{
				$("#productTypeId").empty();
				$("#productTypeId").append('<option value="">请选择</option>');
			}
		});
	 
	 $("#toList").bind('click',function(){
		 var productTypeId = $("#productTypeId").val();
		 var num = $("#num").val();
		 var isCoupon = $("#isCoupon").val();
		 var source = $("#source").val();
		 var keyword = $("#keyword").val();
		 var start_price = $("#start_price").val();
		 var end_price = $("#end_price").val();
		 var start_tk_rate = $("#start_tk_rate").val();
		 var end_tk_rate = $("#end_tk_rate").val();
		 var wetaoChannel=$("#wetaoChannel").val();
		 if(!productTypeId){
			 commUtil.alertError("三级分类不能为空，请先选择一二级分类，在选择三级分类");
			 return;
		 }
		 if(!num){
			 commUtil.alertError("请选择抓取数量");
			 return;
		 }
		 var reg = /^(100|[1-9]\d|\d)(.\d{1,2})$/;
		 if(start_tk_rate){
			 if(!reg.test(start_tk_rate) && !testNumber(start_tk_rate)){
				commUtil.alertError("佣金比例只能输入1-100之间的数,最多保留2位小数");
			 	return;
			 }
		 }
		 if(end_tk_rate){
			 if(!reg.test(end_tk_rate) && !testNumber(end_tk_rate)){
				commUtil.alertError("佣金比例只能输入1-100之间的数,最多保留2位小数");
			 	return;
			 }
		 }
		 if(start_tk_rate && end_tk_rate){
			 if(Number(start_tk_rate)>Number(end_tk_rate)){
				 commUtil.alertError("佣金比例区间输入有误");
				 return;
			 }
		 }
		 $.ligerDialog.open({
				height: $(window).height()*0.9,
				width: $(window).width()*0.9,
				title: "商品确认",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/taobaoke/getProductList.shtml?productTypeId=" + productTypeId+"&num="+num+"&isCoupon="+isCoupon+"&source="+source+"&keyword="+encodeURI(encodeURI(keyword))+"&start_price="+start_price+"&end_price="+end_price+"&start_tk_rate="+start_tk_rate+"&end_tk_rate="+end_tk_rate+"&wetaoChannel="+wetaoChannel,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
	 });
	 
	 
 }); 
</script>
<style type="text/css">
	.l-box-select .l-box-select-table td {
		font-size: 12px;
		line-height: 18px;
	}
</style>
</head>
<body style="padding: 0px; overflow: hidden;">
	<form runat="server" method="post">
		<div class="search-pannel">
		<div class="search-tr">
			<div class="search-td">
				<div class="search-td-label" style="float:left;">一级分类：</div>
				<div class="l-panel-search-item" >
				<select id="productType1Id" name="productType1Id" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach items="${productTypes}" var="productType">
						<option value="${productType.id}">${productType.name}</option>
					</c:forEach>
				</select>
		 	 	</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label" style="float:left;">二级分类：</div>
				<div class="l-panel-search-item" >
				<select id="productType2Id" name="productType2Id" style="width: 150px;">
					<option value="">请选择</option>
				</select>
		 	 	</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label" style="float:left;">三级分类：</div>
				<div class="l-panel-search-item" >
				<select id="productTypeId" name="productTypeId" style="width: 150px;">
					<option value="">请选择</option>
				</select>
		 	 	</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label">其他关键词：</div>
				<div class="search-td-combobox-condition">
					<input id="keyword" name="keyword" type="text">
				</div>
			</div>
		</div>	 
		<div class="search-tr">
			<div class="search-td">
				<div class="search-td-label">抓取数量：</div>
				<div class="search-td-combobox-condition" >
					<select id="num" name="num" style="width: 70px;">
						<option value="">请选择</option>
						<option value="100" selected="selected">100</option>
						<!-- <option value="200">200</option>
						<option value="500">500</option>
						<option value="1000">1000</option> -->
					</select>
				</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">有无优惠券：</div>
			<div class="l-panel-search-item" >
				<select id="isCoupon" name="isCoupon">
					<option value="">请选择</option>
					<option value="0">不限制</option>
					<option value="1" selected="selected">有优惠券</option>
				</select>
		 	 </div>
			 </div>
				 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">类型：</div>
			<div class="l-panel-search-item" >
				<select id="source" name="source">
					<option value="">请选择</option>
					<option value="1">淘宝</option>
					<option value="2" selected="selected">天猫</option>
				</select>
		 	 </div>
			 </div>
			
			<div class="search-td">
				<div class="search-td-label">商品价格区间：</div>
				<div class="search-td-condition">
					<input id="start_price" name="start_price" style="width:43%"> <span
							style="width:10%">--</span> <input id="end_price" name="end_price"
							style="width:43%">
				</div>
			</div>
		</div>
		<div class="search-tr">
			<div class="search-td">
				<div class="search-td-label">佣金比例区间：</div>
				<div class="search-td-condition">
					<input id="start_tk_rate" name="start_tk_rate" style="width:43%"> <span
							style="width:10%">--</span> <input name="end_tk_rate" id="end_tk_rate"
							style="width:43%">
				</div>
			</div>
			<div class="search-td">
				<div class="search-td-label">是否包邮：</div>
				<div class="search-td-condition">
					<select id="need_free_shipment" name="need_free_shipment">
						<option value="">请选择</option>
						<option value="0">不限制</option>
						<option value="1">包邮</option>
					</select>
				</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label">所属频道：</div>
				<div class="search-td-condition">
				<select id="wetaoChannel" name="wetaoChannel" style="width:150px;" >
					<option value="">请选择</option>
					<c:forEach var="wetaoChannel" items="${wetaoChannels}">
						<option value="${wetaoChannel.id}">${wetaoChannel.channelName}</option>
					</c:forEach> 
				</select>
				</div>
			</div>
			
			
			
			<div class="search-td-search-btn">
				<input type="button" style="width: 80px;height: 25px;cursor: pointer;" value="开始抓取" id="toList">
			</div>
		</div>	
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
</body>