<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript">
var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	
 	$("#serviceType").change( function() { 			
		var serviceType = $("#serviceType").val();
	   	$("#proStatus").empty();
	   	getProStatus(serviceType); 
 	});
	
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

function viewSubDetail(id) {
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

function getProStatus(serviceType)
{
	if(typeof(serviceType)!="undefined" && serviceType!=''){
		var path = '${pageContext.request.contextPath}/order/afterService/getProStatusList.shtml';
		jQuery.ajax( {
	  		url : path,
	  		type : 'POST',
	  		dataType : 'json',
	  		cache : false,
	  		async : false,
	  		data : { serviceType : serviceType},
	  		timeout : 30000,
	  		success : function(json) 
	  		{  
	  			var sel = $("#proStatus");
				sel.empty();
				sel.append("<option value=\"" + "\">请选择...</option>");			
				$.each(json, function(index, item) {
					sel.append("<option value=\"" + item.statusValue + "\">" + item.statusDesc + "</option>");
				});
	  		},
			error : function() 
			{
				$.ligerDialog.warn('操作超时，请稍后再试！');
			}
	  	});
	}else{
		var sel = $("#proStatus");
		sel.empty();
		sel.append("<option value=\"" + "\">请选择...</option>");
	}
}

  var listConfig={
		  
	  url:"/order/afterService/data.shtml",
      
      listGrid:{ columns: [{ display: '', width: 250, render: function(rowdata, rowindex) {
    						var html = [];
    						var createDate=new Date(rowdata.createDate);
    						html.push("售后编号："+ rowdata.orderCode);
    						html.push("申请时间："+ createDate.format("yyyy-MM-dd hh:mm:ss"));
    						html.push("子订单编号：<a href=\"javascript:viewSubDetail(" + rowdata.subOrderId + ");\">"+rowdata.subOrderCode+"</a>");
    						if(rowdata.memberInfoStatus == 'P') {
    							html.push("<span style='color:red;'>异常</span>");
    						}
    						return html.join("<br>");
    					}},
    					{ display: '商品', width: 400, render: function(rowdata, rowindex) {
    						if (rowdata.serviceType!='D'){
    							var html = [];
	    						html.push("<img style='float:left;margin:10px;' src='${pageContext.request.contextPath}/file_servelt"+rowdata.skuPic+"' width='60' height='60' onclick='viewerPic(this.src)'>");
	    						html.push("<span style='display:block;text-align:left;margin-top:8px;'>"+rowdata.productName+"<br><span style='color: #A1A1A1;'>");
	    						html.push("规格："+ rowdata.productPropDesc+"<br>");
	    						html.push("商品ID："+ rowdata.productCode+"<br></span></span>");
	    						return html.join("");
    						}
    					}},
		                { display: '商家简称', width: 180, name: 'shortName'},
		                { display: '购买数量', width: 80, name: 'productQuantity'},
		                { display: '售后数量', width: 80, name: 'quantity'},
 		                { display: '售后类型', width: 100, render: function (rowdata, rowindex) {
    						return rowdata.serviceTypeDesc+"<br>"+"<a href=\"javascript:viewAfterServiceDetail(" + rowdata.id + ",'" +rowdata.serviceTypeDesc+ "');\">详情</a>";
		                }},
 		                { display: '售后状态', width: 120, render: function (rowdata, rowindex) {
 		                	return rowdata.statusDesc+"<br>"+rowdata.proStatusDesc;
		                }},
 		                { display: '实收金额', width: 120, render: function (rowdata, rowindex) {
    						var html = [];
    						html.push(rowdata.payAmount);
    						if (rowdata.mchtPreferential!=null&&rowdata.mchtPreferential!=0){
    							html.push("<span style='color: #A1A1A1;'>商家优惠："+rowdata.mchtPreferential+"</span>");
    						}
    						if (rowdata.platformPreferential!=null&&rowdata.platformPreferential!=0){
    							html.push("<span style='color: #A1A1A1;'>平台优惠："+rowdata.platformPreferential+"</span>");
    						}
    						if (rowdata.integralPreferential!=null&&rowdata.integralPreferential!=0){
    							html.push("<span style='color: #A1A1A1;'>积分优惠："+rowdata.integralPreferential+"</span>");
    						}   						
    						return html.join("<br>");
		                }},
		                { display: '申请金额/换货', width: 100, name: 'amount'},
		                { display: '退款时间', width: 150, render: function (rowdata, rowindex) {
		                	if (rowdata.refundDate!=null){
								var refundDate=new Date(rowdata.refundDate);
								return refundDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '操作记录', width: 250, render: function(rowdata, rowindex) {
    						var html = [];
    						var logCreateDate=new Date(rowdata.logCreateDate);
    						html.push("操作人："+ rowdata.operatorTypeDesc+"<br>");
    						html.push(logCreateDate.format("yyyy-MM-dd hh:mm:ss")+"<br>");
    						html.push("<p style='overflow: hidden;text-overflow: ellipsis;white-space: nowrap;'>内容："+rowdata.logContent+"</p>");
    						return html.join("");
    					}}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
     container:{
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
};
  
function excel(){  
	$("#dataForm").attr("action","${pageContext.request.contextPath}/order/afterService/export.shtml");
	$("#dataForm").submit();
}
  
</script>
<style type="text/css">
	.l-box-select .l-box-select-table td {
		font-size: 12px;
		line-height: 18px;
	}
</style>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server" method="post">
		<div class="search-pannel">
		<input type="hidden" id="mchtId" name="mchtId" value="${mchtId}">
		<div class="search-tr"  > 
		
			<div class="search-td">
			<div class="search-td-label">商家名称：</div>
			<div class="search-td-condition">
				<input type="text" id="mchtName" name="mchtName">
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label">活动ID：</div>
			<div class="search-td-condition">
				<input type="text" id="activityId" name="activityId">
			</div>
			</div>
			 
			<div class="search-td">
			<div class="search-td-label">品牌名称：</div>
			<div class="search-td-condition">
				<input type="text" id="productBrandName" name="productBrandName">
			</div>
			</div>
			 
			<div class="search-td">
			<div class="search-td-label">商品ID：</div>
			<div class="search-td-condition">
				<input type="text" id="productId" name="productId">
			</div>
			</div>
			
		</div>
		
		<div class="search-tr"  > 
		
			<div class="search-td">
			<div class="search-td-label">子订单编号：</div>
			<div class="search-td-condition">
				<input type="text" id="subOrderCode" name="subOrderCode">
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label">收货人：</div>
			<div class="search-td-condition">
				<input type="text" id="receiverName" name="receiverName">
			</div>
			</div>
			 
			<div class="search-td">
			<div class="search-td-label">客户寄件快递单号：</div>
			<div class="search-td-condition">
				<input type="text" id="memberExpressNo" name="memberExpressNo">
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label">客户联系电话：</div>
			<div class="search-td-condition">
				<input type="text" id="contactPhone" name="contactPhone">
			</div>
			</div>
			
		</div>
		
		<div class="search-tr"  >
		
			 <div class="search-td">
			 <div class="search-td-label">售后类型：</div>
			 <div class="search-td-condition">
				<select id="serviceType" name="serviceType">
					<option value="">请选择...</option>
					<c:forEach var="list" items="${serviceTypeList}">
						<option value="${list.statusValue}" <c:if test="${serviceType eq list.statusValue}">selected="selected"</c:if>>${list.statusDesc}</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			 <div class="search-td">
			 <div class="search-td-label">进度状态：</div>
			 <div class="search-td-condition">
				<select id="proStatus" name="proStatus">
					<option value="">请选择...</option>
					<c:forEach var="list" items="${proStatusList}">
						<option value="${list.statusValue}">${list.statusDesc}</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			
			<div class="search-td">
			 <div class="search-td-label">售后状态：</div>
			 <div class="search-td-condition">
				<select id="status" name="status">
					<option value="">请选择...</option>
					<c:forEach var="list" items="${statusList}">
						<option value="${list.statusValue}">${list.statusDesc}</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			 <div class="search-td">
			<div class="search-td-label">售后编号：</div>
			<div class="search-td-condition">
				<input type="text" id="orderCode" name="orderCode">
			</div>
			</div>

		</div>
		
		<div class="search-tr"  >
			<div class="search-td">
			<div class="search-td-label" style="float: left;width: 25%;margin-top:2px;">申请时间：</div>
			<div class="l-panel-search-item">
				<input type="text" id="date_begin" name="date_begin" value="${date_begin}"/>
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
			<div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="date_end" name="date_end" value="${date_end}"/>
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
			<div class="search-td-label" style="float: left;width: 25%;margin-top:2px;">退款时间：</div>
			<div class="l-panel-search-item">
				<input type="text" id="refund_date_begin" name="refund_date_begin" value="${refund_date_begin}"/>
				<script type="text/javascript">
					$(function() {
						$("#refund_date_begin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="refund_date_end" name="refund_date_end" value="${refund_date_end}"/>
				<script type="text/javascript">
					$(function() {
						$("#refund_date_end").ligerDateEditor( {
							showTime : false,
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
			<div class="search-td-label" style="float: left;width: 30%;margin-top:2px;">操作记录日期：</div>
			<div class="l-panel-search-item">
				<input type="text" id="log_date_begin" name="log_date_begin"/>
				<script type="text/javascript">
					$(function() {
						$("#log_date_begin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="log_date_end" name="log_date_end"/>
				<script type="text/javascript">
					$(function() {
						$("#log_date_end").ligerDateEditor( {
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
			 <div class="search-td-label">等待操作：</div>
			 <div class="search-td-condition">
				<select id="waitOperator" name="waitOperator">
					<option value="">请选择...</option>
					<option value="1">等待商家操作</option>
					<option value="2">等待客户操作</option>
				</select>
		 	 </div>
			 </div>
			 
			 <%-- <div class="search-td">
			 <div class="search-td-label" style="color: red">对接人：</div>
			 <div class="search-td-condition">
				<select id="platformContactId" name="platformContactId" >
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
		</div>
		<div class="search-tr" >
		 	<div class="search-td">
				<div class="search-td-label" >品类：</div>
				<div style="display: inline-block;" >
					<input type="text" id="productTypeId" name="productTypeId" />	
				</div>
			</div>
			<div class="search-td">
				<div class="search-td-label" style="color: red">售后异常：</div>
				<div class="search-td-condition" >
					<select id="memberStatus" name="memberStatus" >
						<option value="">请选择...</option>
						<option value="NP">正常</option>
						<option value="P">异常</option>
					</select>
				</div>
			</div>

			<div class="search-td">
			<div class="search-td-label" >商家序号：</div>
			<div class="search-td-condition" >
				<input type="text" id = "mchtCode" name="mchtCode" value="${mchtCode}" >
			</div>
			</div>

			<div class="search-td">
				<div class="search-td-label" style="color: red">用户类型：</div>
				<div class="search-td-condition" >
					<select id="isSvip" name="isSvip" >
						<option value="">请选择...</option>
						<option value="1">全部用户</option>
						<option value="2">SVIP用户</option>
					</select>
				</div>
			</div>

			<div class="search-td-search-btn" style="display: inline-flex;">
				<div id="searchbtn">搜索</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 50px;height: 25px;cursor: pointer;" value="导出" id="export" onclick="excel();">
				</div>
			</div>
		</div>	
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>