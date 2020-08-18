<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="${pageContext.request.contextPath}/css/glyphicon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />

<style type="text/css">
.color-1{color: #9D999D;}
.color-2{color: #FC0000;}
.color-3{color: #EFD104;}
.color-4{color: #00FC28;}
.color-5{color: #0351F7;}
.color-6{color: #DF00FB;}
.l-box-select .l-box-select-table td {
	font-size: 12px;
	line-height: 18px;
}
</style>
<script type="text/javascript">

$(function(){
	
	var dateArray = [];
	var defaultProductTypeIds = [];
	<c:if test="${not empty sysStaffProductTypeList }" >
		var sysStaffProductTypeList = ${sysStaffProductTypeList };
		for(var i=0;i<sysStaffProductTypeList.length;i++) {
			dateArray.push(dataBox(sysStaffProductTypeList[i].staffName, sysStaffProductTypeList[i].productTypeId));
			if(defaultProductTypeIds.length != 0) {
				defaultProductTypeIds.push(";");
			}
			defaultProductTypeIds.push(sysStaffProductTypeList[i].productTypeId);
		}
	</c:if>
	var productType2IdsComboBox = $("#productTypeId").ligerComboBox({ 
		isShowCheckBox: true, 
		isMultiSelect: true, 
		emptyText: false,
        data: dateArray, 
        valueFieldID: 'productTypeIds',
        selectBoxWidth: 165.5,
        width: 165.5
    }); 
	<c:if test="${isCwOrgStatus == 1 }" >
		productType2IdsComboBox.selectValue(defaultProductTypeIds.join());
		productType2IdsComboBox.updateStyle();
	</c:if>
	
});

function dataBox(text, id){ 
	 var obj = new Object();
	 obj.text = text; 
	 obj.id = id; 
	 return obj;
}


function viewViolateOrder(id) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "商家违规详情页",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/violateOrder/view.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function viewDetail(id) {
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
		title: "子订单详情",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/order/sub/detail.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function editMchtBaseInfo(id) {
	$.ligerDialog.open({
		height: $(window).height() - 40,
		width: $(window).width() - 40,
		title: "商家基础资料",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/mchtBaseInfoEdit.shtml?mchtId=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function viewAfterServiceDetail(id, serviceTypeDesc) {
	$.ligerDialog.open({
 		height: $(window).height(),
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

  var listConfig={
	  url:"/order/subDepositOrder/data.shtml",
      listGrid:{ columns: [{ display: '预售子订单编号', width: 200, render: function(rowdata, rowindex) {
    						return rowdata.subDepositOrderCode;
    					}},
    					{ display: '类目',width: 100,render: function(rowdata, rowindex){
    						if (rowdata.productTypeName) {
    						   return rowdata.productTypeName;
							}else{
							   return "";
							}
    					}},
    					{ display: '商家序号', width: 100, render: function(rowdata, rowindex) {
    						var html = [];
    						if (rowdata.mchtCode){
    							html.push(rowdata.mchtCode);
    						}
    						return html.join("");
    					}},
    					{ display: '店铺名称', width: 200, render: function(rowdata, rowindex) {
    						var html = [];
    						if (rowdata.shopName!=null){
    							html.push(rowdata.shopName);
    						}
    						return html.join("");
    					}},
 		                { display: '品牌', width: 150, render: function (rowdata, rowindex) {
 		                	return rowdata.brandName;
		                }},
 		                { display: '商品名称', width: 180, render: function (rowdata, rowindex) {
 		                	return rowdata.productName;
		                }},
					    { display: '结算类型', width: 70,name:'mchtType', render: function (rowdata, rowindex) {
							  if(rowdata.mchtType === "1"){
								  return "SPOP";
							  }else{
								  return "POP";
							  }
					    }},
 		                { display: '数量', width: 50, render: function (rowdata, rowindex) {
 		                	return rowdata.quantity;
		                }},
		                { display: '实付金额', width: 100, name: 'payAmount'},
		                { display: '用户ID', width: 100, name: 'memberId'},
		                { display: '用户昵称', width: 100, render: function (rowdata, rowindex) {
 		                	var memberNick = rowdata.memberNick;
 		                	if(memberNick && memberNick.length > 8) {
 		                		return memberNick.substring(0, 6)+"**"+memberNick.substring(memberNick.length-1, memberNick.length);
 		                	}else {
 		                		return memberNick;
 		                	}
		                }},
		                { display: '定金状态', width: 100, name: 'statusDesc'},
		                { display: '下单时间', width: 150, render: function (rowdata, rowindex) {
		                	if (rowdata.createDate){
								var createDate=new Date(rowdata.createDate);
								return createDate.format("yyyy-MM-dd hh:mm:ss");		                		
		                	}
		                }},
		                { display: '付款方式', width: 100, name: 'paymentName'},
		                { display: '付款时间', width: 150, render: function (rowdata, rowindex) {
		                	if (rowdata.payDate){
								var payDate=new Date(rowdata.payDate);
								return payDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '交易号', width: 120, name: 'paymentNo'},
					    { display: '佣金比例', width: 60, name: 'popCommissionRate', render: function (rowdata, rowindex) {
							  if(rowdata.mchtType === "1"){
								  return "-";
							  }else{
								  return rowdata.popCommissionRate;
							  }
					    }},
		                { display: '技术服务费', width: 80, name: 'commissionAmount'},
		                { display: '结算金额', width: 100, name: 'settleAmount'},
		                { display: '尾款子订单号', width: 180, name: 'subOrderCode', render: function (rowdata, rowindex) {
		                	if(rowdata.subOrderCode){
			                	return '<a href="javascript:;" onclick="viewDetail('+rowdata.subOrderId+');">'+rowdata.subOrderCode+'</a>';
		                	}else{
		                		return "";
		                	}
		                }},
		                { display: '交易完成时间', width: 100, name: 'completeDate', render: function (rowdata, rowindex) {
		                	if (rowdata.completeDate){
								var completeDate=new Date(rowdata.completeDate);
								return completeDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '用户备注', width: 150, render: function (rowdata, rowindex) {
		                	return rowdata.memberRemarks;
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
  
  function dataBox(text, id){ 
	 var obj = new Object();
	 obj.text = text; 
	 obj.id = id; 
	 return obj;
  }
  
</script>

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" method="post" action="" >
		<div class="search-pannel">
		
		<div class="search-tr"  > 
		
			 <div class="search-td">
			 <div class="search-td-label" >订单状态：</div>
			 <div class="search-td-condition" >
				<select id="status" name="status">
					<option value="">请选择...</option>
					<c:forEach var="list" items="${statusList}">
						<option value="${list.statusValue}">${list.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			
			<div class="search-td">
			<div class="search-td-label" >商家序号：</div>
			<div class="search-td-condition" >
				<input type="text" id="mchtCode" name="mchtCode" >
			</div>
			</div>
			 
			<div class="search-td">
			<div class="search-td-label" >商家名称：</div>
			<div class="search-td-condition" >
				<input type="text" id="mchtName" name="mchtName" >
			</div>
			</div>
			 
			<div class="search-td">
			<div class="search-td-label" >订单金额：</div>
			<div class="search-td-condition" >
				<input type="text" id="amountMin" name="amountMin" style="width:60px;" onchange="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}">&nbsp;-&nbsp;<input type="text" id="amountMax" name="amountMax"  style="width:60px;" onchange="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}">
			</div>
			</div>
			
		</div>
		
		<div class="search-tr"  > 
		
			<div class="search-td">
			<div class="search-td-label" style="float: left;width: 25%;margin-top:2px;">下单时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="createDateBegin" name="createDateBegin" value="${date_begin}"/>
				<script type="text/javascript">
					$(function() {
						$("#createDateBegin").ligerDateEditor( {
							showTime : true,
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
				<input type="text" id="createDateEnd" name="createDateEnd" value="${date_end}"/>
				<script type="text/javascript">
					$(function() {
						$("#createDateEnd").ligerDateEditor( {
							showTime : true,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" style="float: left;width: 25%;margin-top:2px;">付款时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="payDateBegin" name="payDateBegin" />
				<script type="text/javascript">
					$(function() {
						$("#payDateBegin").ligerDateEditor( {
							showTime : true,
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
				<input type="text" id="payDateEnd" name="payDateEnd" />
				<script type="text/javascript">
					$(function() {
						$("#payDateEnd").ligerDateEditor( {
							showTime : true,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
		</div>	
		<div class="search-tr"  >	
			<div class="search-td">
			<div class="search-td-label" >预售母订单号：</div>
			<div class="search-td-condition" >
				<input type="text" id = "combineDepositOrderCode" name="combineDepositOrderCode" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >预售子订单号：</div>
			<div class="search-td-condition" >
				<input type="text" id = "subDepositOrderCode" name="subDepositOrderCode" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >会员ID：</div>
			<div class="search-td-condition" >
				<input type="text" id = "memberInfoId" name="memberInfoId" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >品牌：</div>
			<div class="search-td-condition" >
				<input type="text" id = "brandName" name="brandName" >
			</div>
			</div>
		</div>
		
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" >付款渠道：</div>
			<div class="search-td-condition" >
				<select id="paymentId" name="paymentId">
					<option value="">请选择...</option>
					<c:forEach items="${sysPaymentList}" var="sysPayment">
						<option value="${sysPayment.id}">${sysPayment.remarks}</option>
					</c:forEach>
				</select>
			</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label" >用户昵称：</div>
				<div class="search-td-condition" >
					<input type="text" id = "memberNick" name="memberNick" >
				</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label" >品类：</div>
				<div style="display: inline-block;" >
					<input type="text" id="productTypeId" name="productTypeId" />	
					
					<%-- <!-- isCwOrgStatus：是否为钟表类目 -->
					<select id="productTypeId" name="productTypeId" <c:if test="${isCwOrgStatus == 1 }">disabled="disabled"</c:if> >
						<c:if test="${isCwOrgStatus == 0 }">
							<option value="">请选择...</option>
						</c:if>
						<c:forEach items="${sysStaffProductTypeList}" var="sysStaffProductType">
							<option value="${sysStaffProductType.productTypeId}">${sysStaffProductType.staffName}</option>
						</c:forEach>
					</select> --%>
				
				</div>
			</div>
			
		</div>	
		<div class="search-tr"  >
			<div class="search-td">
			<div class="search-td-label"style="float: left;width: 30%;margin-top:2px;">交易完成时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="completeDateBegin" name="completeDateBegin" />
				<script type="text/javascript">
					$(function() {
						$("#completeDateBegin").ligerDateEditor( {
							showTime : true,
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
				<input type="text" id="completeDateEnd" name="completeDateEnd" />
				<script type="text/javascript">
					$(function() {
						$("#completeDateEnd").ligerDateEditor( {
							showTime : true,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
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

			<div class="search-td">
				<div class="search-td-label" style="float:left;">结算类型：</div>
				<div class="l-panel-search-item" >
					<select name="mchtType" style="width: 150px;">
						<option value="">请选择</option>
						<option value="2">POP</option>
						<option value="1">SPOP</option>
					</select>
				</div>
			</div>

			<div class="search-td-search-btn" style="display: inline-flex;">
				<div class="l-button" id="searchbtn">搜索</div>
			</div>
		 
		</div>
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>