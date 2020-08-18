<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript">
 function gridRefresh() {
	    if (listModel.gridManager) {
	        var gridparms = [];
	        gridparms.push({ name: "staff", value: "self" });
	        listModel.gridManager.loadServerData(gridparms);
	    }
	}
 
 function viewCoupon(id) {
		$.ligerDialog.open({
		height: $(window).height()-200,
		width: $(window).width()-600,
		title: "查看优惠劵",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/marketing/coupon/view.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}

 function editCoupon(id) {
		$.ligerDialog.open({
		height: $(window).height()-200,
		width: $(window).width()-600,
		title: "管理优惠劵",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/marketing/coupon/edit.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
 
 function couponDtl(id) {
		$.ligerDialog.open({
		height: $(window).height()-200,
		width: $(window).width()-400,
		title: "优惠劵明细",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/marketing/coupon/detail.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}

  var listConfig={
	  
      url:"/marketing/coupon/data.shtml",
    
      btnItems:[{ text: '创建优惠劵', icon: 'add', dtype:'win',  click: itemclick, url: "/marketing/coupon/add.shtml", seqId:"", width: 800, height: 600}],
      
      listGrid:{ columns: [  { display: '优惠劵ID', name: 'id', width: 100}, 
                        { display: '优惠劵名称', name: 'name', width: 200},
 		                { display: '面额/使用条件', render: function (rowdata, rowindex) {
							var html="<div id='usingDetail"+rowdata.id+"'>";
							if (rowdata.preferentialType=='1') {
								html += rowdata.money+"元 / 满"+rowdata.minimum+"元 ";
							}else {
								var discount=rowdata.discount*10;
								discounts  = discount.toFixed(1);
								if (rowdata.conditionType=='1' && rowdata.maxDiscountMoney!=null) {
									html += discounts+"折 /无条件/折扣上限"+rowdata.maxDiscountMoney;
								}else if (rowdata.conditionType=='1' && rowdata.maxDiscountMoney==null) {
									html += discounts+"折 /无条件/折扣不设上限";
								}else if (rowdata.conditionType=='2' && rowdata.maxDiscountMoney!=null) {
									html += discounts+"折 /满"+rowdata.minimum+"元可用/折扣上限"+rowdata.maxDiscountMoney;
								}else if (rowdata.conditionType=='2' && rowdata.maxDiscountMoney==null) {
									html += discounts+"折 /满"+rowdata.minimum+"元可用/折扣不设上限";
								}else if (rowdata.conditionType=='3' && rowdata.maxDiscountMoney!=null){
									html += discounts+"折 /满"+rowdata.minicount+"件可用/折扣上限"+rowdata.maxDiscountMoney;
								}else if (rowdata.conditionType=='3' && rowdata.maxDiscountMoney==null) {
									html += discounts+"折 /满"+rowdata.minicount+"件可用/折扣不设上限";
								}
							}
							html += "</div>";
							return html;
		                }},
		                { display: '使用量/领取量/发行量', width: 150, render: function (rowdata, rowindex) {
							return rowdata.useQuantity+" / "+rowdata.recQuantity+" / "+rowdata.grantQuantity;
		                }},
		                { display: '领取时间', width: 150, render: function (rowdata, rowindex) {
		                	var html = [];
		                    if (rowdata.recBeginDate!=null){
		                    	var beginDate=new Date(rowdata.recBeginDate);
		                    	html.push("起 "+ beginDate.format("yyyy-MM-dd hh:mm"));
		                    }
		                    if (rowdata.recEndDate!=null){
		                    	var endDate=new Date(rowdata.recEndDate);
		                    	html.push("止 "+endDate.format("yyyy-MM-dd hh:mm"));
		                    }
							return html.join("<br>");
		                }},
		                { display: '使用时间类型',  name: 'expiryTypeDesc'}, 
		                { display: '类目',  name: '',render:function(rowdata, rowindex){
		                	if (rowdata.couponType==2) {
								return rowdata.productTypeName;
							}
		                }}, 
		                /* { display: '是否叠加使用品类券', width: 100,render: function (rowdata, rowindex) {
		                	if(rowdata.canSuperpose == "0"){
		                		return "否";
		                	}else if(rowdata.canSuperpose == "1"){
		                		return "是";
		                	}else{
		                		return "";
		                	}
		                }},  */
		                { display: '使用时间', width: 150 , render: function (rowdata, rowindex) {
		                	if (rowdata.expiryType==2){
		                		return rowdata.expiryDays+"天";
		                	}else{
		                		if (rowdata.expiryBeginDate!=null){
		                		var beginDate=new Date(rowdata.expiryBeginDate);
								var endDate=new Date(rowdata.expiryEndDate);
								return "起 "+ beginDate.format("yyyy-MM-dd hh:mm")+"<br>止 "+endDate.format("yyyy-MM-dd hh:mm");
		                		}
		                	}
		                }},
		                { display: '领取对象', render: function (rowdata, rowindex) {
		                	if (rowdata.minMemberGroupName!=null){
		                		return rowdata.minMemberGroupName+"及以上";
		                	}
		                }},
		                { display: '领取方式', name: 'recTypeDesc'},
			            { display: '创建时间', width: 150, render: function(rowdata, rowindex) {
			            	var createDate=new Date(rowdata.createDate);
			            	return createDate.format("yyyy-MM-dd hh:mm:ss");
			         	}},
			         	{ display: '创建人<a href=\"javascript:gridRefresh();\">我的</a>',  name: 'staffName'},
		                { display: '状态', render: function(rowdata, rowindex) {
		                	if (rowdata.status==0){
		                		return "<span style='color:#CC0000'>"+rowdata.statusDesc+"</span>";
		                	}else{
		                		return rowdata.statusDesc;
		                	}
		                }},
		                { display: '是否失效', render: function(rowdata, rowindex) {
		                	if (rowdata.secondNum>0){
		                		return "已失效";
		                	}else{
		                		return "未失效";
		                	}
		                }},
		                { display: "操作", name: "OPER", align: "center", hide:${roleType!=1}, render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:editCoupon(" + rowdata.id + ");\">管理</a>&nbsp;&nbsp;"); 
						    html.push("<a href=\"javascript:couponDtl(" + rowdata.id + ");\">明细</a>");
						    return html.join("");
						}},
		                { display: "操作", name: "OPER", align: "center", hide:${roleType!=2}, render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:viewCoupon(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;"); 
						    html.push("<a href=\"javascript:couponDtl(" + rowdata.id + ");\">明细</a>");
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

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<c:if test="${roleType!=1}">
	<div id="toptoolbar"></div>
	</c:if>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		
		<div class="search-tr"  > 
		     
		    <div class="search-td">
			<div class="search-td-label" >优惠劵ID：</div>
			<div class="search-td-condition" >
				<input type="text" id = "id" name="id" >
			</div>
			</div>
		     
			<div class="search-td">
			<div class="search-td-label" >优惠劵名称：</div>
			<div class="search-td-condition" >
				<input type="text" id = "name" name="name" >
			</div>
			</div>
			
			 <div class="search-td">
			 <div class="search-td-label" >状态:</div>
			 <div class="search-td-condition" >
				<select id="status" name="status">
					<option value="">请选择</option>
					<c:forEach var="list" items="${couponStatus}">
						<option value="${list.statusValue}">${list.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			 <div class="search-td">
			 <div class="search-td-label" >是否失效:</div>
			 <div class="search-td-condition" >
				<select id="expiryFlag" name="expiryFlag">
					<option value="">请选择</option>
					<option value="1">未失效
					<option value="2">已失效
					</option>
				</select>
		 	 </div>
			 </div>
			
		</div>
		
		<div class="search-tr"  > 
		
			<div class="search-td">
			<div class="search-td-label" style="float:left;">创建时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="date_begin" name="date_begin" />
				<script type="text/javascript">
					$(function() {
						$("#date_begin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="date_end" name="date_end" />
				<script type="text/javascript">
					$(function() {
						$("#date_end").ligerDateEditor( {
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
			 <div class="search-td-label" >类目：</div>
			 <div class="search-td-condition" >
				<select id="typeIds" name="typeIds">
					<option value="">请选择</option>
					<c:forEach items="${productTypes}" var="productType">
						<option value="${productType.id}">${productType.name}</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			 <div class="search-td">
			 <div class="search-td-label" >是否支持红包雨:</div>
			 <div class="search-td-condition" >
				<select id="isSupportCouponRain" name="isSupportCouponRain">
					<option value="">请选择</option>
					<option value="0">否
					<option value="1">是
					</option>
				</select>
		 	 </div>
			 </div>
			
			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
			
		</div>
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>