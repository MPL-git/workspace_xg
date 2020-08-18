<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<html>
<head>
<script type="text/javascript">
	var viewerPictures;
    $(function(){
    	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    		$("#viewer-pictures").hide();
    	}});
    	
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
	function viewProductMchtInfo(mchtId,productId,mchtName) {
		$.ligerDialog.open({
			height: 400,
			width: 500,
			title: mchtName,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/product/viewProductMchtInfo.shtml?mchtId=" + mchtId+"&productId="+productId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	function lawAuditProduct(id) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width()*0.75,
			title: "法务审核",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/product/lawAuditProduct.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: false,
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
				console.log(data);
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
      url:"/product/dataList.shtml",
     
      btnItems:[],
      listGrid:{ columns: [
						{ display: '系统ID', name: 'id', width: 100}, 
			            { display: '主图', name: 'pic', width: 80,render: function(rowdata, rowindex) {
			                    var h = "";
			                      if(rowdata.pic!=null&&(rowdata.pic.indexOf("http") >= 0)){
			                       h += "<img src='"+rowdata.pic+"' width='60' height='60' onclick='viewerPic("+rowdata.id+")'>";
			                      }else{
			                       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='60' height='60' onclick='viewerPic("+rowdata.id+")'>";
			                      }
			                    return h;

						}},
			            { display: '商品ID', name: 'code', width: 100}, 
			            { display: '品牌', name: 'productBrandName', width: 100}, 
		                { display: '名称', width: 200, render: function(rowdata, rowindex) { 
							var html = [];
							html.push("<a href=\"javascript:viewProduct(" + rowdata.id + ");\">"+rowdata.name+"</a>"); 
							return html.join("");
      					}},
		                { display: '分类', name: 'productTypeName', width: 100 }, 
		                { display: '上市年份', name: 'yearOfListing', width: 100 }, 
		                { display: '性别', width: 100, name: 'suitSex', render: function(rowdata, rowindex) {
		                	var sexStr=String(rowdata.suitSex);
		                	var sexShow="";
							var html = [];
		 					if (sexStr.charAt(0)=="1"){
								sexShow+="男、";
							}
		                    if (sexStr.charAt(1)=="1"){
		                    	sexShow+="女、";
							}
		                    html.push(sexShow.replace(/[、]$/,""));
						    return html.join("");
		                  }},
		                { display: '人群', name: 'suit_group', width: 100, render: function(rowdata, rowindex) {
		                	var groupStr=String(rowdata.suitGroup);
		                	var groupShow="";
							var html = [];
		 					if (groupStr.charAt(0)=="1"){
								groupShow+="青年、";
							}
		                    if (groupStr.charAt(1)=="1"){
		                    	groupShow+="儿童、";
							}
		                    if (groupStr.charAt(2)=="1"){
		                    	groupShow+="中老年 、";
							}
		                    html.push(groupShow.replace(/[、]$/,""));
						    return html.join("");
		                  }},
		                { display: '货号', name: 'artNo', width: 100},
		                { display: '上市时间', name: 'year', width: 100, hide:${auditStatus!=null}, render: function(rowdata, rowindex) {
							var html = [];
							if (rowdata.year!=null){
								html.push(rowdata.year);
								html.push('年');
							}
							if (rowdata.seasonDesc!=null){
			                    html.push(rowdata.seasonDesc);
							}
						    return html.join("");
		                  }},
		                { display: '吊牌价', name: 'tagPrice', width: 100, render: function(rowdata, rowindex) {
							var html = [];
							
							html.push(rowdata.tagPriceMin);
							
							if(rowdata.tagPriceMin!=rowdata.tagPriceMax){
								html.push("-");
								html.push(rowdata.tagPriceMax);
							};
						    return html.join("");
					 }},
		                { display: '商城价',name: 'saleName', width: 100, render: function(rowdata, rowindex) {
							var html = [];
							
							html.push(rowdata.mallPriceMin);
							
							if(rowdata.mallPriceMin!=rowdata.mallPriceMax){
								html.push("-");
								html.push(rowdata.mallPriceMax);
							};
						    return html.join("");
					 }},
		                { display: '活动价',name: 'saleName', width: 100, render: function(rowdata, rowindex) {
							var html = [];
							
							html.push(rowdata.salePriceMin);
							
							if(rowdata.salePriceMin!=rowdata.salePriceMax){
								html.push("-");
								html.push(rowdata.salePriceMax);
							};
						    return html.join("");
					 }},
					 { display: 'SVIP折扣',name: '', width: 100, render: function(rowdata, rowindex) {
							var html = [];
							if (rowdata.svipDiscount!=null) {
								html.push(rowdata.svipDiscount);
							}else{
								html.push("-");
								
							}	
						    return html.join("");
					 }},
		                { display: '折扣',name: 'mchtName', width: 100, hide:${auditStatus!=null}, render: function(rowdata, rowindex) {
							var html = [];
							html.push(rowdata.discountMin);
							
							if(rowdata.discountMin!=rowdata.discountMax){
								html.push("-");
								html.push(rowdata.discountMax);
							};

						    return html.join("");
					 }},
 		                { display: '结算价', name: 'costPrice', width: 100, hide:${mchtType=="2" || auditStatus!=null} , render: function(rowdata, rowindex) {
							var html = [];
							if(rowdata.mchtType=='1'){
								html.push(rowdata.costPriceMin);
								if(rowdata.costPriceMin!=rowdata.costPriceMax){
									html.push("-");
									html.push(rowdata.costPriceMax);
								};
							}

						    return html.join("");
					 }},					 
					 { display: '佣金比例', name: 'popCommissionRate', width: 100,hide:${mchtType=="1" || auditStatus!=null} },
		             { display: '毛利率', name: 'profitRate', width: 100,hide:${mchtType=="2" || auditStatus!=null}, render: function(rowdata, rowindex) {
							var html = [];
							
							html.push(rowdata.profitRateMin);
							
							if(rowdata.profitRateMin!=rowdata.profitRateMax){
								html.push("-");
								html.push(rowdata.profitRateMax);
							};
							
							
						    return html.join("");
					 }},
		             { display: '类型', name: 'saleTypeDesc', width: 100},
		             { display: '状态', width: 100, render: function(rowdata, rowindex) {
		            	 var html = [];
	            	 	 if (rowdata.saleType==1){
	            	 		html.push(rowdata.statusDesc);
	            	 		if (rowdata.status=='0' && rowdata.offReason!=null && rowdata.offReason!=''){
	            	 			html.push("{"+rowdata.offReasonDesc+"}");
	            	 		}
	            	 		return html.join("<br>");
	            	 	 }
	            	 	 if (rowdata.saleType==2){
							if (rowdata.productActivityStatus==0){
								return "闲置";
							}
							if (rowdata.productActivityStatus==1){
								return "报名中";
							}
							if (rowdata.productActivityStatus==2){
								return "待开始";
							}
							if (rowdata.productActivityStatus==3){
								return "预热中";
							}
							if (rowdata.productActivityStatus==4){
								return "活动中";
							}
							if (rowdata.productActivityStatus==5){
								return "活动暂停";
							}
							if (rowdata.productActivityStatus==6){
								return "未提审";
							}
							if (rowdata.productActivityStatus==7){
								return "未通过";
							}
		            	 }
		         	 }},
		             { display: "操作", name: "OPER", width: 100, align: "center", hide:${auditStatus!=null}, render: function(rowdata, rowindex) {
						var html = [];
						html.push("<a href=\"javascript:viewProduct(" + rowdata.id + ");\">查看</a>"); 
						return html.join("");
					 }},
		             { display: "操作", name: "OPER", width: 100, align: "center", hide:${mchtType!=null}, render: function(rowdata, rowindex) {
						var html = [];
						html.push("<a href=\"javascript:lawAuditProduct(" + rowdata.id + ");\">【审核】</a>");
						return html.join("");
					 }},
/* 		             { display: "操作", name: "OPER", width: 100, align: "center", hide:${mchtType!=null || auditStatus=="1"}, render: function(rowdata, rowindex) {
						var html = [];
						html.push("<a href=\"javascript:viewProduct(" + rowdata.id + ");\">查看</a>");
						return html.join("");
					 }}, */
		             { display: '更新时间', width: 150, render: function(rowdata, rowindex) {
            	 	 	var updateDate;
            	 	    if (rowdata.updateDate!=null){
            	 			updateDate=new Date(rowdata.updateDate);
            	 	    }else{
            	 		  	updateDate=new Date(rowdata.createDate);
            	 	    }
	            	    return updateDate.format("yyyy-MM-dd hh:mm:ss");
			         }},
		             { display: '商家', name: 'companyName', width: 200, hide:${auditStatus!=null}, render: function(rowdata, rowindex) {
					 	var html = [];
						html.push("<a href=\"javascript:viewProductMchtInfo(" + rowdata.mchtId + ","+rowdata.id+",'"+rowdata.mchtName+"');\">"+rowdata.companyName+"</a>"); 
						return html.join("");
					 }},
					 { display: '商家', name: 'companyName', width: 200, hide:${mchtType!=null}},
					 { display: '审核状态', name: 'auditStatusDesc', width: 100, hide:${mchtType!=null}},
					 { display: '审核人', name: 'auditByName', width: 80, hide:${auditStatus != "23"} }
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
 
 
    var productTypeCombo;
    $(function(){
    	productTypeCombo = $("#productTypeName").ligerComboBox({
              selectBoxWidth: 200,
              selectBoxHeight: 200,  
              valueField: 'id',
              textField: 'name',
              valueFieldID: 'productType',
              treeLeafOnly: false,
              valueField: 'id',
              resize: true,
              tree: {
            	  onClick: function(note){
					  if(!productTypeCombo.treeManager.hasChildren(note.data)) {
	            		  productTypeCombo.treeManager.loadData(note.target, '${pageContext.request.contextPath}/service/prod/product_type/getProductTypeTree.shtml?parentId='+note.data.id);
					  }          		  
            	  },
            	  url: '${pageContext.request.contextPath}/service/prod/product_type/getProductTypeTree.shtml', 
            	  checkbox: false, 
            	  ajaxType: 'get', 
            	  idFieldName: 'id',
            	  textFieldName: 'name',
            	  parentIDFieldName: 'parentId',
            	  isExpand: false
              }
          });
    	
    });
    
    
    
    </script>  
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" value="${mchtType}" name="mchtType"> 
		<input type="hidden" value="${auditStatus}" name="auditStatus">
		<input type="hidden" value="${statisticsTag}" name="statisticsTag">
		<div id="topmenu"></div>
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">销售类型：</div>
					<div class="search-td-condition">
						<select id="shopStatus" name="shopStatus">
							<option value="">请选择...</option>
							<option value="1">开通商城的商家</option>
							<option value="0">未开通商城的商家</option>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">上架状态：</div>
					<div class="search-td-condition">
						<select id="status" name="status">
							<option value="">请选择</option>
							<option value="1" <c:if test="${not empty status && status == 1 }">selected</c:if>>上架</option>
							<option value="0">下架</option>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">醒购价：</div>
					<div class="search-td-condition">
						<input name="salePriceBegin" style="width:43%"> <span
							style="width:10%">--</span> <input name="salePriceEnd"
							style="width:43%">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商品类型</div>
					<div class="search-td-condition">
						<select id="saleType" name="saleType">
							<option value="">请选择...</option>
							<option value="1" <c:if test="${not empty saleType && saleType == 1 }">selected</c:if>>品牌款</option>
							<option value="2">单品款</option>
						</select>
					</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">性别：</div>
					<div class="search-td-condition">
						<select id="suitSex" name="suitSex" class="querysel">
							<option value="">请选择</option>
							<option value="10">男</option>
							<option value="01">女</option>
							<option value="11">男、女</option>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">人群：</div>
					<div class="search-td-condition">
						<select id="suitGroup" name="suitGroup" class="querysel">
							<option value="">请选择</option>
							<option value="100">青年</option>
							<option value="010">儿童</option>
							<option value="001">中老年</option>
						</select>
					</div>
				</div>
				<c:if test="${(type1Id == null || type1Id == '') && (type2Id == null || type2Id == '') }">
				<div class="search-td">
					<div class="search-td-label">分类：</div>
					<div class="search-td-combobox-condition">
						<input id="productTypeName" type="text">
					</div>
				</div>
				</c:if>
				<c:if test="${not empty type1Id}">
				<div class="search-td">
					<div class="search-td-label">分类：</div>
					<div class="search-td-combobox-condition">
						${type1Name}
						<input id="productType" name="productType" type="hidden" value="${type1Id}">
					</div>
				</div>
				</c:if>
				<c:if test="${not empty type2Id}">
				<div class="search-td">
					<div class="search-td-label">分类：</div>
					<div class="search-td-combobox-condition">
						${type2Name}
						<input id="productType" name="productType" type="hidden" value="${type2Id}">
					</div>
				</div>
				</c:if>
				<div class="search-td">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-condition">
						<input name="mchtCode"
							<c:if test="${not empty mchtCode }">readonly</c:if>
							value="${mchtCode }">
					</div>
				</div>

			</div>

			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">商家：</div>
					<div class="search-td-condition">
						<input name="mchtName">
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label">品牌：</div>
					<div class="search-td-condition">
						<input name="productBrandName"
							<c:if test="${not empty productBrandName }">readonly</c:if>
							value="${productBrandName }">
						<c:if test="${not empty productBrandId }">
							<input type="hidden" name="productBrandId" value="${productBrandId}">
						</c:if>	
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label">货号：</div>
					<div class="search-td-condition">
						<input name="artNo">
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label">商品名称：</div>
					<div class="search-td-condition">
						<input name="name">
					</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">系统ID：</div>
					<div class="search-td-condition">
						<input name="id">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商品ID：</div>
					<div class="search-td-condition">
						<input name="code">
					</div>
				</div>
				<div class="search-td" id="period" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">更新时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="update_begin" name="update_begin" value="" />
						<script type="text/javascript">
							$(function() {
								$("#update_begin").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
					<div class="l-panel-search-item" style="margin-left: 17px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="update_end" name="update_end" value=""  />
						<script type="text/javascript">
							$(function() {
								$("#update_end").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
				</div>
			</div>
			<div class="search-tr">
				<%-- <div class="search-td">
					<div class="search-td-label" style="color: red">对接人：</div>
					<div class="search-td-condition">
						<select id="platformContactId" name="platformContactId">
							<c:if test="${isContact==1}">
								<c:if test="${sessionScope.USER_SESSION.isManagement=='1'}">
									<option value="">全部商家</option>
								</c:if>
								<option value="${myContactId}">我对接的商家</option>
								<c:forEach items="${platformAssistantContacts}" var="platformAssistantContactList">
									<option value="${platformAssistantContactList.id}">${platformAssistantContactList.contactName}的商家</option>
								</c:forEach>
							</c:if>
							<c:if test="${isContact==0}">
								<option value="">全部商家</option>
								<c:forEach items="${platformMchtContacts}" var="platformMchtContactList">
									<option value="${platformMchtContactList.id}">${platformMchtContactList.contactName}的商家</option>
								</c:forEach>
							</c:if>
						</select>
					</div>
				</div> --%>
				<div class="search-td">
					<div class="search-td-label">对接人：</div>
					<div class="search-td-condition">
						<select id="platContactStaffId" name="platContactStaffId">
							<c:if test="${isManagement == 1}">
								<option value="" selected="selected">全部商家</option>
							</c:if>
							<option value="${staffID}">我自己的商家</option>
							<c:forEach items="${sysStaffInfoCustomList}" var="sysStaffInfoCustom">
								<option value="${sysStaffInfoCustom.staffId}">${sysStaffInfoCustom.staffName}的商家</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<c:if test="${mchtType == 1}">
					<div class="search-td">
						<div class="search-td-label">结算价：</div>
						<div class="search-td-condition">
							<input name="costPriceBegin" style="width:43%">
							<span style="width:10%">--</span>
							<input name="costPriceEnd" style="width:43%">
						</div>
					</div>
				</c:if>

				<div class="search-td-search-btn">
					<div id="searchbtn">搜索</div>
				</div>
			</div>
		</div>
	</form>
	<div id="toptoolbar"></div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
