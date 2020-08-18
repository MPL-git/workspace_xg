<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
 
 var mchtUrl = '${mchtUrl }';
 
 function subOrderReportViewList(title,rowType,date_begin,date_end,mchtId,brandId,productTypeId){
	 var pageSize = $("#pageSize").val();
	 var orderType = $("#orderType").val();
	 var activityType = $("#activityType").val();
	 var selectedProductTypeId = $("#selectedProductTypeId").val();
	 $.ligerDialog.open({
			height: $(window).height()*0.95,
			width: $(window).width()*0.95,
			title: title,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/operateData/subOrderReport/list.shtml?rowType="+rowType+"&startCreateDate="+date_begin+"&endCreateDate="+date_end+"&mchtId="+mchtId+"&brandId="+brandId+"&productTypeId="+productTypeId+"&pageSize="+pageSize+"&orderType="+orderType+"&activityType="+activityType+"&selectedProductTypeId="+selectedProductTypeId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
 }
 
 function viewerPic(imgPath){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		imgPath=imgPath.replace('_S', '');
			$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
			viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		viewerPictures.show();
	}
 
 //获取退货率
 function getReQuantityRate(paramId, rowType, payDateBegin, payDateEnd) {
	 $.ajax({
		 type: 'post',
		 url: '${pageContext.request.contextPath }/operateData/subOrderReport/selectReQuantityRate.shtml',
		 data: {paramId : paramId, rowType : rowType, payDateBegin : payDateBegin, payDateEnd : payDateEnd},
		 dataType: 'json',
		 success: function(data) {
			 if(data == null || data.code != 200){
			     commUtil.alertError(data.msg);
			 }else {
				 $("#"+paramId+"_reQuantityRate").html(data.reQuantityRate);
			 }
		 },
		 error: function(e) {
			 commUtil.alertError("系统异常请稍后再试");
		 }
	 });
 }
 
 //获取好评率
 function getGoodCommentRate(paramId, rowType, payDateBegin, payDateEnd) {
	 $.ajax({
		 type: 'post',
		 url: '${pageContext.request.contextPath }/operateData/subOrderReport/selectGoodCommentRate.shtml',
		 data: {paramId : paramId, rowType : rowType, payDateBegin : payDateBegin, payDateEnd : payDateEnd},
		 dataType: 'json',
		 success: function(data) {
			 if(data == null || data.code != 200){
			     commUtil.alertError(data.msg);
			 }else {
				 $("#"+paramId+"_goodCommentRate").html(data.reQuantityRate);
			 }
		 },
		 error: function(e) {
			 commUtil.alertError("系统异常请稍后再试");
		 }
	 });
 }
 
 var listConfig={
      url: "/operateData/subOrderReport/data.shtml",
      btnItems:[],
      listGrid:{ columns: [
                        {display:'名称',width: 300,render: function(rowdata, rowindex) {
                        	var rowType = $("#rowType").val();
                        	if(rowType == "product"){
                        		var html = [];
                                if(rowdata.ID==null){
                        		    return rowdata.NAME;
								}
	    						html.push("<img style='float:left;margin:10px;' src='${pageContext.request.contextPath}/file_servelt"+rowdata.pic+"' width='60' height='60' onclick='viewerPic(this.src)'>");
	    						html.push("<span style='display:block;text-align:left;margin-top:8px;'>"+rowdata.NAME+"<br>");
	    						html.push("<span style='color: #A1A1A1;'>ID："+ rowdata.ID+"<br></span>");
	    						html.push("<a href='"+ mchtUrl + rowdata.mcht_id +"' target='_blank'>店铺名："+ rowdata.shop_name +"</a>");
	    						html.push("</span>");
	    						return html.join("");
                        	}else{
								return rowdata.NAME;
                        	}
      					}},
                        {display:'销售数量',width: 100,name:'QUANTITY_SUM'},
                        {display:'商品金额',width: 100, name:'SALE_PRICE_SUM'},
                        {display:'商家优惠金额',width: 100,name:'MCHT_PREFERENTIAL_SUM'},
                        {display:'平台优惠金额',width: 100,name:'PLATFORM_PREFERENTIAL_SUM'},
                        {display:'实付金额',name:'PAY_AMOUNT_SUM',width: 100},
                        {display:'商品均价',width: 100,name:'AVG_NUM'},
                        {display:'商家优惠率',width: 100,name:'MCHT_RATE'},
                        {display:'平台优惠率',width: 100,name:'PLATFORM_RATE'},
                        {display:'毛利率',width: 100,name:'M_RATE'},
                        {display:'结算金额',width: 100,name:'SETTLE_AMOUNT'},
                        {display:'退货率',width: 100,name:'', render: function(rowdata, rowindex) {
                        	var html = [];
                        	var rowType = $("#rowType").val();
                        	var payDateBegin = $("#pay_date_begin").val();
                        	var payDateEnd = $("#pay_date_end").val();
                        	var flagId = "";
                        	if(rowType == "brand") {
                        		flagId = rowdata.brandId;
                        	}else if(rowType == "mcht") {
                        		flagId = rowdata.mchtId;
                        	}else if(rowType == "product") {
                        		flagId = rowdata.productId;
                        	}else if(rowType == "product_type") {
                        		flagId = rowdata.productTypeId;
                        	}
                        	if(flagId != null){
                                html.push("<span id='"+ flagId +"_reQuantityRate'>")
                                html.push("<a href=\"javascript:getReQuantityRate("+ flagId +", '"+rowType+"', '"+payDateBegin+"', '"+payDateEnd+"');\">查看</a>")
                                html.push("</span>");
                                return html.join("");
							}
      					}},
                        {display:'好评率',width: 100,name:'', render: function(rowdata, rowindex) {
                        	var html = [];
                        	var rowType = $("#rowType").val();
                        	var payDateBegin = $("#pay_date_begin").val();
                        	var payDateEnd = $("#pay_date_end").val();
                        	var flagId = "";
                        	if(rowType == "brand") {
                        		flagId = rowdata.brandId;
                        	}else if(rowType == "mcht") {
                        		flagId = rowdata.mchtId;
                        	}else if(rowType == "product") {
                        		flagId = rowdata.productId;
                        	}else if(rowType == "product_type") {
                        		flagId = rowdata.productTypeId;
                        	}

                        	if(flagId != null){
                                html.push("<span id='"+ flagId +"_goodCommentRate'>")
                                html.push("<a href=\"javascript:getGoodCommentRate("+ flagId +", '"+rowType+"', '"+payDateBegin+"', '"+payDateEnd+"');\">查看</a>")
                                html.push("</span>");
                                return html.join("");
							}

      					}},
                        {display:'操作',width: 200,render: function (rowdata, rowindex) {
                        	var rowType = $("#rowType").val();
                        	var startCreateDate = $("#pay_date_begin").val();
                        	var endCreateDate = $("#pay_date_end").val();
                        	if(rowType == "mcht"){
                        		var mchtId = rowdata.mchtId;
                        		var brandId = $("#brandId").val();
                        		var productTypeId = $("#productTypeId").val();
                        		if(!brandId){
                        			brandId = 0;
                        		}
                        		if(!productTypeId){
                        			productTypeId = 0;
                        		}

                        		if(mchtId!=null){
                                    return '<a href="javascript:;" onclick="subOrderReportViewList('+"'查看品牌'"+','+"'brand'"+','+"'"+startCreateDate+"'"+','+"'"+endCreateDate+"'"+','+mchtId+','+brandId+','+productTypeId+')">查看品牌</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="subOrderReportViewList('+"'查看商品'"+','+"'product'"+','+"'"+startCreateDate+"'"+','+"'"+endCreateDate+"'"+','+mchtId+','+brandId+','+productTypeId+')">查看商品</a>';
								}

                        	}else if(rowType == "brand"){
                        		var brandId = rowdata.brandId;
                        		var mchtId = $("#mchtId").val();
                        		var productTypeId = $("#productTypeId").val();
                        		if(!mchtId){
                        			mchtId = 0;
                        		}
                        		if(!productTypeId){
                        			productTypeId = 0;
                        		}

                        		if(brandId != null){
                                    return '<a href="javascript:;" onclick="subOrderReportViewList('+"'查看商品'"+','+"'product'"+','+"'"+startCreateDate+"'"+','+"'"+endCreateDate+"'"+','+mchtId+','+brandId+','+productTypeId+')">查看商品</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="subOrderReportViewList('+"'查看店铺'"+','+"'mcht'"+','+"'"+startCreateDate+"'"+','+"'"+endCreateDate+"'"+','+mchtId+','+brandId+','+productTypeId+')">查看店铺</a>';
								}

                        	}else if(rowType == "product_type"){
                        		var productTypeId = rowdata.productTypeId;
                        		var mchtId = $("#mchtId").val();
                        		var brandId = $("#brandId").val();
                        		if(!mchtId){
                        			mchtId = 0;
                        		}
                        		if(!brandId){
                        			brandId = 0;
                        		}
                        		//第一行是总计，不需要
                        		if (productTypeId != null){
                                    return '<a href="javascript:;" onclick="subOrderReportViewList('+"'查看商品'"+','+"'product'"+','+"'"+startCreateDate+"'"+','+"'"+endCreateDate+"'"+','+mchtId+','+brandId+','+productTypeId+')">查看商品</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="subOrderReportViewList('+"'查看品牌'"+','+"'brand'"+','+"'"+startCreateDate+"'"+','+"'"+endCreateDate+"'"+','+mchtId+','+brandId+','+productTypeId+')">查看品牌</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="subOrderReportViewList('+"'查看店铺'"+','+"'mcht'"+','+"'"+startCreateDate+"'"+','+"'"+endCreateDate+"'"+','+mchtId+','+brandId+','+productTypeId+')">查看店铺</a>';
								}
                        	}
		                }}
		         ], 
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
     container:{
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid",
        
      }        
  };
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	
	<form id="dataForm" runat="server">
		<input type="hidden" id="mchtId" name="mchtId" value="${mchtId}">
		<input type="hidden" id="brandId" name="brandId" value="${brandId}">
		<input type="hidden" id="productTypeId" name="productTypeId" value="${productTypeId}">
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td"">
					<div class="search-td-label">排行类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="rowType" name="rowType" style="width: 135px;" >
							<option value="product" <c:if test="${rowType == 'product'}">selected="selected"</c:if>>按商品</option>
							<option value="mcht" <c:if test="${rowType == 'mcht'}">selected="selected"</c:if>>按商家店铺</option>
							<option value="brand" <c:if test="${rowType == 'brand'}">selected="selected"</c:if>>按品牌</option>
							<option value="product_type" <c:if test="${rowType == 'product_type'}">selected="selected"</c:if>>按一级类目</option>
						</select>
					</div>
				</div>
				<div class="search-td" id="coDe">
					<div class="search-td-label">商品ID：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="code" name="code"/>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" id="rowTypeName">商品名称：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="name" name="name"/>
					</div>
				</div>
				<%-- <div class="search-td" style="width: 40%;">
				   <div class="search-td-label" style="float: left;width: 20%;">下单日期：</div>
				   <div class="l-panel-search-item" >
					   <input type="text" id="startCreateDate" name="startCreateDate" value="${startCreateDate}" style="width: 135px;" />
				   </div>
				   <div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
				   <div class="l-panel-search-item">
					   <input type="text" id="endCreateDate" name="endCreateDate" value="${endCreateDate}" style="width: 135px;" />
				   </div>
			   </div> --%>
				<div class="search-td">
					<div class="search-td-label">排序：</div>
					<div class="search-td-combobox-condition" >
						<select id="orderType" name="orderType" style="width: 135px;" >
							<option value="num" <c:if test="${orderType == 'num'}">selected="selected"</c:if>>按数量</option>
							<option value="payAmount" <c:if test="${orderType == 'payAmount'}">selected="selected"</c:if>>按实付金额</option>
						</select>
					</div>
				</div>	
			</div>	
			<div class="search-tr">	
				<div class="search-td">
					<div class="search-td-label">条数：</div>
					<div class="search-td-combobox-condition" >
						<select id="pageSize" name="pageSize" style="width: 135px;" >
							<option value="20" <c:if test="${pageSize == 20}">selected="selected"</c:if>>20条</option>
							<option value="30" <c:if test="${pageSize == 30}">selected="selected"</c:if>>30条</option>
							<option value="50" <c:if test="${pageSize == 50}">selected="selected"</c:if>>50条</option>
							<option value="100" <c:if test="${pageSize == 100}">selected="selected"</c:if>>100条</option>
							<option value="200" <c:if test="${pageSize == 200}">selected="selected"</c:if>>200条</option>
							<option value="300" <c:if test="${pageSize == 300}">selected="selected"</c:if>>300条</option>
							<option value="500" <c:if test="${empty pageSize || pageSize == 500}">selected="selected"</c:if>>500条</option>
						</select>
					</div>
				</div>
				<%-- <div class="search-td">
					<div class="search-td-label">排序：</div>
					<div class="search-td-combobox-condition" >
						<select id="orderType" name="orderType" style="width: 135px;" >
							<option value="num" <c:if test="${orderType == 'num'}">selected="selected"</c:if>>按数量</option>
							<option value="payAmount" <c:if test="${orderType == 'payAmount'}">selected="selected"</c:if>>按实付金额</option>
						</select>
					</div>
				</div> --%>
				<div class="search-td">
					<div class="search-td-label">栏目：</div>
					<div class="search-td-combobox-condition" >
						<select id="activityType" name="activityType" style="width: 135px;" >
							<option value="">全部</option>
							<option value="-1" <c:if test="${activityType == '-1'}">selected="selected"</c:if>>品牌特卖</option>
							<option value="0" <c:if test="${activityType == '0'}">selected="selected"</c:if>>会场</option>
							<option value="1" <c:if test="${activityType == '1'}">selected="selected"</c:if>>新用户专享</option>
							<option value="4" <c:if test="${activityType == '4'}">selected="selected"</c:if>>新用户秒杀</option>
							<option value="2" <c:if test="${activityType == '2'}">selected="selected"</c:if>>单品爆款</option>
							<option value="3" <c:if test="${activityType == '3'}">selected="selected"</c:if>>醒购秒杀</option>
							<option value="5" <c:if test="${activityType == '5'}">selected="selected"</c:if>>积分商城</option>
							<option value="6" <c:if test="${activityType == '6'}">selected="selected"</c:if>>断码清仓</option>
							<option value="7" <c:if test="${activityType == '7'}">selected="selected"</c:if>>商城销售</option>
							<option value="8" <c:if test="${activityType == '8'}">selected="selected"</c:if>>砍价免费拿</option>
							<option value="9" <c:if test="${activityType == '9'}">selected="selected"</c:if>>邀请享免单</option>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">一级类目：</div>
					<div class="search-td-combobox-condition" >
						<c:if test="${isCwOrgStatus == 1 }">
							<select name="selectedProductTypeId" id="selectedProductTypeId" disabled="disabled" style="width: 135px;" >
								<c:forEach items="${productTypes}" var="productType">
									<option value="${productType.id}" <c:if test="${selectedProductTypeId eq productType.id}">selected="selected"</c:if> <c:if test="${productTypeId eq productType.id}">selected="selected"</c:if>>${productType.name}</option>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${isCwOrgStatus == 0 }">
							<select name="selectedProductTypeId" id="selectedProductTypeId" style="width: 135px;" >
								<option value="">不限</option>
								<c:forEach items="${productTypes}" var="productType">
									<option value="${productType.id}" <c:if test="${selectedProductTypeId eq productType.id}">selected="selected"</c:if> <c:if test="${productTypeId eq productType.id}">selected="selected"</c:if>>${productType.name}</option>
								</c:forEach>
							</select>
						</c:if>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="color: red">对接人：</div>
					<div class="search-td-condition">
						<select id="platformContactId" name="platformContactId" style="width: 135px;" >
							<c:if test="${isContact==1}">
								<c:if test="${sessionScope.USER_SESSION.isManagement=='1'}">
									<option value="">全部商家</option>
								</c:if>
								<option value="${myContactId}" selected="selected">我对接的商家</option>
								<c:forEach items="${platformAssistantContacts}"
									var="platformAssistantContactList">
									<option value="${platformAssistantContactList.id}" <c:if test="${platformContactId eq platformAssistantContactList.id}">selected="selected"</c:if>>${platformAssistantContactList.contactName}的商家</option>
								</c:forEach>
							</c:if>
							<c:if test="${isContact==0}">
								<c:if test="${sessionScope.USER_SESSION.isManagement=='1'}">
								<option value="" selected="selected">全部商家</option>
								</c:if>
								<c:forEach items="${platformMchtContacts}"
									var="platformMchtContactList">
									<option value="${platformMchtContactList.id}" <c:if test="${platformContactId eq platformMchtContactList.id}">selected="selected"</c:if>>${platformMchtContactList.contactName}的商家</option>
								</c:forEach>
							</c:if>
						</select>
					</div>
				</div>
			</div>
			<div class="search-tr">
				<%-- <div class="search-td">
					<div class="search-td-label" style="color: red">对接人：</div>
					<div class="search-td-condition">
						<select id="platformContactId" name="platformContactId" style="width: 135px;" >
							<c:if test="${isContact==1}">
								<c:if test="${sessionScope.USER_SESSION.isManagement=='1'}">
									<option value="">全部商家</option>
								</c:if>
								<option value="${myContactId}">我对接的商家</option>
								<c:forEach items="${platformAssistantContacts}"
									var="platformAssistantContactList">
									<option value="${platformAssistantContactList.id}">${platformAssistantContactList.contactName}的商家</option>
								</c:forEach>
							</c:if>
							<c:if test="${isContact==0}">
								<option value="">全部商家</option>
								<c:forEach items="${platformMchtContacts}"
									var="platformMchtContactList">
									<option value="${platformMchtContactList.id}">${platformMchtContactList.contactName}的商家</option>
								</c:forEach>
							</c:if>
						</select>
					</div>
				</div> --%>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">付款日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="pay_date_begin" name="pay_date_begin" value="${pay_date_begin}" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="pay_date_end" name="pay_date_end" value="${pay_date_end}" style="width: 135px;" />
					</div>
				</div> 
				<div class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn">搜索</div>
				</div>
			</div>
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
		
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
<script type="text/javascript">
var viewerPictures;	
	$(function() {
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		
		$("#search").bind('click',function(){
			var startpayDate = $("#pay_date_begin").val();
			var endpayDate = $("#pay_date_end").val();
			var startDay = new Date(startpayDate);
			var endDay = new Date(endpayDate);
			var diffDays = (endDay - startDay) / (1000 * 60 * 60 * 24);
			if(diffDays > 31){
				commUtil.alertError("起始日期间隔不能超过31天");
				return false;
			}else{
				$("#searchbtn").click();
			}
		});
		
		$("#rowType").bind('change',function(){
			var rowTypeVal = $(this).val();
			if(rowTypeVal == "product"){
				$("#rowTypeName").text("商品名称：");
				document.getElementById("coDe").style.display="";
			}else if(rowTypeVal == "mcht"){
				$("#rowTypeName").text("店铺名称：");
				document.getElementById("coDe").style.display="none";
			}else if(rowTypeVal == "brand"){
				$("#rowTypeName").text("品牌名称：");
				document.getElementById("coDe").style.display="none";
			}else if(rowTypeVal == "product_type"){
				$("#rowTypeName").text("类目名称：");
				document.getElementById("coDe").style.display="none";
			}
		});
		
		// 日期
		$("#pay_date_begin").ligerDateEditor( {
			format: "yyyy-MM-dd hh:mm:ss",
			showTime : true,
			labelWidth : 135,
			width:135,
			labelAlign : 'left'
		});
		$("#pay_date_end").ligerDateEditor( {
			format: "yyyy-MM-dd hh:mm:ss",
			showTime : true,
			labelWidth : 135,
			width:135,
			labelAlign : 'left'
		});
		// 禁止分页
		 $("#maingrid").ligerGrid({
            usePager:false
        });
	});
	
</script> 

</html>
