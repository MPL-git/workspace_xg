<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
  <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript">
 
	 function toViewSupplyList(id,companyName){
		$.ligerDialog.open({
			height: $(window).height()*0.8,
			width: $(window).width()*0.6,
			title: "修改"+companyName+"的供应商",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtShopManager/toViewSupplyList.shtml?mchtId=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
	 function shopStatusLogList(id){
		$.ligerDialog.open({
			height: $(window).height()*0.8,
			width: $(window).width()*0.5,
			title: "修改店铺状态",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtShopManager/shopStatusLogList.shtml?mchtId=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
		
		function changeSupplyChainStatus(id,supplyChainStatus) {
			var title;
			if (!supplyChainStatus || supplyChainStatus=="0"){
				title="您确认要将供应链状态改为：开通";
			}else if(supplyChainStatus=="1"){
				title="您确认要将供应链状态改为：关闭";
			}
			$.ligerDialog.confirm(title+"？", function (yes) {
				if(yes){
					$.ajax({
						url : "${pageContext.request.contextPath}/mchtShopManager/changeSupplyChainStatus.shtml?mchtId=" + id,
						secureuri : false,
						dataType : 'json',
						cache : false,
						async : false,
						success : function(data) {
							if ("0000" == data.returnCode) {
								listModel.gridManager.reload();
							}else{
								$.ligerDialog.error(data.returnMsg);
							}
						},
						error : function() {
							$.ligerDialog.error('操作超时，请稍后再试！');
						}
					});
				}
			});
		}
		
		function changeStatus(id,shopStatus) {
			var title;
			if (!shopStatus || shopStatus=="0"){
				title="您确认要将店铺状态改为：开通";
			}else if(shopStatus=="1"){
				title="您确认要将店铺状态改为：关闭";
			}
			$.ligerDialog.confirm(title+"？", function (yes) {
				if(yes){
					$.ajax({
						url : "${pageContext.request.contextPath}/mchtShopManager/changeMchtShopStatus.shtml?mchtId=" + id,
						secureuri : false,
						dataType : 'json',
						cache : false,
						async : false,
						success : function(data) {
							if ("0000" == data.returnCode) {
								listModel.gridManager.reload();
							}else{
								$.ligerDialog.error(data.returnMsg);
							}
						},
						error : function() {
							$.ligerDialog.error('操作超时，请稍后再试！');
						}
					});
				}
			});
		}
		
		function changeActivityAuthStatus(id,activityAuthStatus) {
			var title;
			if (activityAuthStatus=="0"){
				title="您确认要将商家活动开通状态改为：开通";
			}else if(activityAuthStatus=="1"){
				title="您确认要将商家活动开通状态改为：关闭";
			}
			$.ligerDialog.confirm(title+"？", function (yes) {
				if(yes){
					$.ajax({
						url : "${pageContext.request.contextPath}/mchtShopManager/changeMchtShopStatus.shtml?mchtId=" + id+"&type=1",
						secureuri : false,
						dataType : 'json',
						cache : false,
						async : false,
						success : function(data) {
							if ("0000" == data.returnCode) {
								listModel.gridManager.reload();
							}else{
								$.ligerDialog.error(data.returnMsg);
							}
						},
						error : function() {
							$.ligerDialog.error('操作超时，请稍后再试！');
						}
					});
				}
			});
		}
		
		// 优化后开通关闭店铺活动操作 
		function changeActivityStatus(id,activityAuthStatus) {
			var title = '';
			var isClose = 0;
			if (activityAuthStatus=="0"){
				title="开通活动";
			}else if(activityAuthStatus=="1"){
				title="关闭活动";
				isClose = 1;
			}
			
			$.ligerDialog.open({
				height: $(window).height()*0.6,
				width: $(window).width()*0.8,
				title: title,
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mchtShopManager/toEditActivityStatus.shtml?mchtId=" + id + "&isClose=" + isClose,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}
		
 
      var listConfig={
	  
      url:"/mchtShopManager/data.shtml",
	  btnItems:[{ text: '开通店铺', icon: 'add', dtype:'win',  click: itemclick, url:'/mchtShopManager/toAddMchtShop.shtml', seqId:"", width : 400, height:300},
	            { text: '开通活动权限', icon: 'add', dtype:'win',  click: itemclick, url:'/mchtShopManager/toAddActivityAuth.shtml', seqId:"", width : 400, height:300}
	  		   ],
      listGrid:{ columns: [ 
		                { display: '商家序号', name: 'mcht_code',width:100 }, 
		                { display: '公司名称',  name: 'company_name', width: 150 }, 
		                { display: '店铺名', name:'shop_name',width: 200},
		                { display: '主营类目', name: 'mcht_product_type', width:120},
		                { display: '品牌', name: 'mcht_product_brand', width:180},
		                { display: 'DSR', name: '', width: 100 ,render: function(rowdata, rowindex) {
   		                	var productScore = rowdata.avg_product_score == null || rowdata.avg_product_score == ''? 5: rowdata.avg_product_score;
   		                	var serveScore = rowdata.avg_serve_score == null || rowdata.avg_serve_score == ''? 10: rowdata.avg_serve_score;
   		                	return ((productScore + serveScore)/3).toFixed(2);
 		                }},
 		               	{ display: '总营业额', name: 'allPayAmount', width:180},
 		               	{ display: '总销量', name: 'allQuantity', width:180},
		                { display: '全部商品', name: 'total_product_count', width:80},
		                { display: '店铺上架中商品', name: 'up_product_count', width:100},
		              	{ display: '店铺状态', name: 'shop_status_desc', width: 100 },
		              	{ display: '活动开通状态', name: 'activity_auth_status_desc', width: 100 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.activity_auth_status == "0"){
    	   		                return "关闭";
    		                }else{
    		                	return "开通";
    		                }
 		                }},
		              	{ display: '供应链状态', name: 'supply_chain_status_desc', width: 100 ,render: function(rowdata, rowindex) {
   		                	if(!rowdata.supply_chain_status || rowdata.supply_chain_status == "0"){
    	   		                return "关闭";
    		                }else if(rowdata.supply_chain_status == "1"){
    		                	return '已开通<a href="javascript:;" onclick="toViewSupplyList('+rowdata.id+','+"'"+rowdata.company_name+"'"+');">【查看】</a>';
    		                }
 		                }},
		              	{ display: '日期记录', name: 'status_date', width:120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.last_open_date){
    	   		                return new Date(rowdata.last_open_date).format("yyyy-MM-dd")+'<a href="javascript:;" onclick="shopStatusLogList('+rowdata.id+');">记录</a>';
    		                }else{
    		                	return "";
    		                }
 		                }},
 		                { display: '活动优惠', name: 'shop_status_desc', width: 200 ,render: function(rowdata, rowindex) {
							var fullCutRules = rowdata.full_cut_rules;
							if(fullCutRules){
								var fullCutRuleArray = fullCutRules.split(";");
								var ruleStr = "";
								for(var i=0;i<fullCutRuleArray.length;i++){
									var fullCutRule = fullCutRuleArray[i];//例：10,5|20,8|30,10
									var tmpArray = fullCutRule.split("|");
									for(var j=0;j<tmpArray.length;j++){
										var eachRuleArray = tmpArray[j].split(",");//例：10,5
										ruleStr += eachRuleArray[0]+"-"+eachRuleArray[1]+",";
									}
								}
								return ruleStr;
							}else{
								return "";
							}
 		                }}, 
 		                { display: '操作', name: 'fw_contact_name',width:180, render: function(rowdata, rowindex) {
							var html = [];
							if(!rowdata.shop_status || rowdata.shop_status=="0"){
							    html.push("<a href=\"javascript:changeStatus(" + rowdata.id + ","+rowdata.shop_status+");\">开通店铺</a>&nbsp;&nbsp;");
							}else if(rowdata.shop_status=="1"){
							    html.push("<a href=\"javascript:changeStatus(" + rowdata.id + ","+rowdata.shop_status+");\">关闭店铺</a>&nbsp;&nbsp;");
							}
							if(rowdata.activity_auth_status=="0"){
							    html.push("<a href=\"javascript:changeActivityStatus(" + rowdata.id + ","+rowdata.activity_auth_status+");\">开通活动</a>&nbsp;&nbsp;");
							}else if(rowdata.activity_auth_status=="1"){
							    html.push("<a href=\"javascript:changeActivityStatus(" + rowdata.id + ","+rowdata.activity_auth_status+");\">关闭活动</a>&nbsp;&nbsp;");
							}
							if(!rowdata.supply_chain_status || rowdata.supply_chain_status=="0"){
							    html.push("<a href=\"javascript:changeSupplyChainStatus(" + rowdata.id + ","+rowdata.supply_chain_status+");\">开通供应链</a>&nbsp;&nbsp;");
							}else if(rowdata.supply_chain_status=="1"){
							    html.push("<a href=\"javascript:changeSupplyChainStatus(" + rowdata.id + ","+rowdata.supply_chain_status+");\">关闭供应链</a>&nbsp;&nbsp;");
							}
							html.push("<a href=\"javascript:toView(" + rowdata.id + ");\">浏览</a>");																								                		
						    return html.join("");
		              	}}
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
       
  }
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
				<div class="search-td-label">商家序号：</div>
				<div class="search-td-condition">
				<input type="text" id ="mchtCode" name="mchtCode" >
				</div>
				</div>
			
				<div class="search-td">
				<div class="search-td-label">商家名称：</div>
				<div class="search-td-condition" >
				<input type="text" id ="name" name="name" >
				</div>
				</div>
	
			 	<div class="search-td">
				 	<div class="search-td-label">主营类目：</div>
				 	<div class="search-td-condition"  >
				    	<select id="productTypeId" name="productTypeId" class="querysel">
							<option value="">请选择</option>
							<c:forEach var="list" items="${productTypes}">
									<option value="${list.id}">${list.NAME}</option>
							</c:forEach>
						</select>
					</div>
			 	</div>
			 	
			 	<div class="search-td">
				 	<div class="search-td-label">店铺开通状态：</div>
				 	<div class="search-td-condition"  >
				    	<select id="shopStatus" name="shopStatus" class="querysel">
							<option value="">请选择</option>
							<option value="0">关闭</option>
							<option value="1" selected="selected">开通</option>
						</select>
					</div>
			 	</div>
			 </div>	
			 	
			 <div class="search-tr"  >
			 	<div class="search-td">
				 	<div class="search-td-label">活动开通状态：</div>
				 	<div class="search-td-condition"  >
				    	<select id="activityAuthStatus" name="activityAuthStatus" class="querysel">
							<option value="" selected="selected">请选择</option>
							<option value="0">关闭</option>
							<option value="1">开通</option>
						</select>
					</div>
			 	</div>
			 	
			 	<div class="search-td">
					<div class="search-td-label" style="color: red;">对接人：</div>
					<div class="search-td-condition">
						<select id="platContactStaffId" name="platContactStaffId">
							<c:if test="${isManagement == 1}">
								<option value="" selected="selected">全部商家</option>
							</c:if>
							<option value="${staffID}" selected="selected" >我自己的商家</option>
							<c:forEach items="${sysStaffInfoCustomList}" var="sysStaffInfoCustom">
								<option value="${sysStaffInfoCustom.staffId}">${sysStaffInfoCustom.staffName}的商家</option>
							</c:forEach>
						</select>
					</div>
				</div> 
			 	
			 	<div  class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" style="width: 100px; ">搜索</div> 
				</div>
			</div>
			
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
