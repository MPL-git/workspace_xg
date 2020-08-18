<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
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

<script type="text/javascript">

$(function() {
	$(".dateEditor").ligerDateEditor({
		showTime : true,
		format : "yyyy-MM-dd hh:mm",
		labelAlign : 'left',
		width : 200
	});
	
 });

 
$(function(){
	$("#form1").validate({
        submitHandler: function(form) {  
        	var memberIds = $.trim($("#memberIds").val());
   		    if(memberIds == '') {	    
   		      commUtil.alertError("会员ID不能为空！");
   		       return
   		    }
    		var mchtCodes = $.trim($("#mchtCodes").val());
    		var mchtProductBrandIds = $.trim($("#mchtProductBrandIds").val());
    		var subOrderCode =$.trim($("#subOrderCode").val());
    		var memberSituation =$.trim($("#memberSituation").val());
    		var mchtAppealedCount =$.trim($("#mchtAppealedCount").val());
    		var reg=/^[0-9]*$/;
    		if(mchtCodes == '') {
        		commUtil.alertError("商家序号不能为空！");
        		return;
        	}
        	if(mchtProductBrandIds == '') {
        		commUtil.alertError("被投诉品牌不能为空！");
        		return;
        	}
        	if(subOrderCode=='') {
        	    commUtil.alertError("相关子订单号不能为空！");
    			return;
        	}
        	if(memberSituation=='') {
        	    commUtil.alertError("会员截止目前工商投诉/举报情况不能为空！");
    			return;
        	}
        	if(mchtAppealedCount=='' || !reg.test(mchtAppealedCount)) {
        	    commUtil.alertError("商家被投诉次数不能为空或请输入整数！");
    			return;
        	}
        	form.submit();
        }
    }); 
	
});

function subOrderLlist(){
	var memberIds = $.trim($("#memberIds").val());
	$.ligerDialog.open({
		height: $(window).height(),
		width: $(window).width()-50,
		title: "查看子订单",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/businessCirclesAppealOrder/subOrderList.shtml?memberIds="+memberIds,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});

}


function customerServiceOrder(){
	var memberIds = $.trim($("#memberIds").val());
	$.ligerDialog.open({
		height: $(window).height(),
		width: $(window).width()-50,
		title: "查看售后单",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/businessCirclesAppealOrder/customerServiceOrderList.shtml?memberIds="+memberIds,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});

}


function interventionOrder(){
	var memberIds = $.trim($("#memberIds").val());
	$.ligerDialog.open({
		height: $(window).height(),
		width: $(window).width()-50,
		title: "查看介入单",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/businessCirclesAppealOrder/interventionOrderList.shtml?memberIds="+memberIds,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});

}

function appealOrder(){
	var memberIds = $.trim($("#memberIds").val());
	$.ligerDialog.open({
		height: $(window).height(),
		width: $(window).width()-50,
		title: "查看投诉单",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/businessCirclesAppealOrder/appealOrderList.shtml?memberIds="+memberIds,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});

}


//检测会员信息
function MemberidList(){
	var memberIds = $.trim($("#memberIds").val());
	if(memberIds == '') {
		commUtil.alertError("会员ID不能为空！");
		return;
	}
	$.ligerDialog.open({
		height: $(window).height(),
		width: $(window).width()-50,
		title: "会员检测",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/businessCirclesAppealOrder/memberIdlist.shtml?memberIds="+memberIds,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	
	$.ajax({//获取会员相关的子订单
		type: 'post',
		url: '${pageContext.request.contextPath}/businessCirclesAppealOrder/orderList.shtml',
		data: {memberIds : memberIds},
		dataType: 'json',
		success: function(data) {
			if(data == null || data.code != 200){
		    	commUtil.alertError(data.msg);
		  	}else {
		  		var html=[]; 
		  		html.push(data.subOrderCustomscuont);
		  		$(".memberinfo-div").html(html.join(""));
		  		document.getElementById("suborder").style.display='';
		  		var html=[];
		  		html.push(data.customerServiceOrdercount);
		  		$(".customer_service_order-div").html(html.join(""));
		  		document.getElementById("customerserviceorder").style.display='';
		  		var html=[];
		  		html.push(data.interventionOrdercount);
		  		$(".interventionOrdercount-div").html(html.join(""));
		  		document.getElementById("interventionOrder").style.display='';
		  		var html=[];
		  		html.push(data.appealOrdercount);
		  		$(".appealOrdercount-div").html(html.join(""));
		  		document.getElementById("appealOrder").style.display='';
          		  
		  	}
		},
		error: function(e){
		 	commUtil.alertError("系统异常请稍后再试！");
		}
	});
	

}



//检测商家信息
function MchtidList(){
	var mchtCodes = $.trim($("#mchtCodes").val());
	if(mchtCodes == '') {
		commUtil.alertError("商家信息不能为空！");
		return;
	}
	$.ligerDialog.open({
		height: $(window).height(),
		width: $(window).width()-50,
		title: "商家序号",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/businessCirclesAppealOrder/mchtIdsList.shtml?mchtCodes="+mchtCodes,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	
	$.ajax({
		type: 'post',
		url: '${pageContext.request.contextPath}/businessCirclesAppealOrder/productBrandList.shtml',
		data: {mchtCodes : mchtCodes},
		dataType: 'json',
		success: function(data) {
			if(data == null || data.code != 200){
		    	commUtil.alertError("查无该商家被投诉品牌！");
		  	}else {
		  		
		  		var productBrandCustoms = data.productBrandCustoms;
		  		if (productBrandCustoms!=null && productBrandCustoms!='') {
		  				var dateArray = [];
				  		var defaultProductbrands = [];
		  			for(var i=0;i<productBrandCustoms.length;i++) {
		  				dateArray.push(dataBox(productBrandCustoms[i].name, productBrandCustoms[i].id));
		  				if(defaultProductbrands.length != 0) {
		  					defaultProductbrands.push(";");
		  				}
		  				defaultProductbrands.push(productBrandCustoms[i].id);
		  			}
		  		var productbrandsComboBox = $("#mchtProductBrandIds").ligerComboBox({ 
		  			isShowCheckBox: true, 
		  			isMultiSelect: true, 
		  			emptyText: false,
		  	        data: dateArray, 
		  	        valueFieldID: 'productBrandIds',
		  	        selectBoxWidth: 165.5,
		  	        width: 165.5
		  	    }); 
		  		  		
		  		document.getElementById("mchtProductBrandIds").style.display='';
			  }		
          		  
		  	}
		},
		error: function(e){
		 	commUtil.alertError("系统异常请稍后再试！");
		}
	});

}

function dataBox(text, id){ 
	 var obj = new Object();
	 obj.text = text; 
	 obj.id = id; 
	 return obj;
}
</script>

</head>
	<body style="margin: 10px; margin-bottom: 200px;">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/businessCirclesAppealOrder/addMakupformationbusinessCirclesAppealOrder.shtml" >
		<input type="hidden" id="id" name="id" value="${businessCirclesAppealOrder.id}" />
		<table class="gridtable">
			<tr>
				<td class="title">会员ID<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<textarea id="memberIds" name="memberIds" rows="5" class="textarea" cols="50">${businessCirclesAppealOrder.memberIds}</textarea>
					<input type="button" id="choice" style="background-color: rgba(135,206,250);width: 50px;cursor:pointer;height: 24px;border-radius: 3px;" onclick="MemberidList();" value="检测">
				</td>
			</tr>
			<tr>
				<td class="title">商家序号<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<textarea id="mchtCodes" name="mchtCodes" rows="5" class="textarea" cols="50">${MchtCodeGroup}</textarea>
					<input type="button" id="choice" style="background-color: rgba(135,206,250);width: 50px;cursor:pointer;height: 24px;border-radius: 3px;" onclick="MchtidList();" value="检测">
				</td>
			</tr>
			<tr>
				<td class="title">被投诉的品牌<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input type="text" style="display: none;" id="mchtProductBrandIds" name="mchtProductBrandIds" />
				</td>
			</tr>
			<tr>
				<td class="title">会员相关单号</td>
				<td align="left" class="l-table-edit-td">
					<div id="suborder" style="display: none;"><a href='javascript:;' onclick="subOrderLlist()">子订单(<span class="memberinfo-div"></span>)</a></div>
					<div id="customerserviceorder" style="display: none;"><a href='javascript:;' onclick="customerServiceOrder()">售后单(<span class="customer_service_order-div"></span>)</a></div>
					<div id="interventionOrder" style="display: none;"><a href='javascript:;' onclick="interventionOrder()">介入单(<span class="interventionOrdercount-div"></span>)</a></div>
					<div id="appealOrder" style="display: none;"><a href='javascript:;' onclick="appealOrder()">投诉单(<span class="appealOrdercount-div"></span>)</a></div>
				</td>
			</tr>
			<tr>
				<td class="title">相关子订单<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input style="width:360px;" type="text" id="subOrderCode" name="subOrderCode" value="${businessCirclesAppealOrder.subOrderCode}" />
				</td>
			</tr>
			<tr>
				<td class="title">签收时间</td>
				<td align="left" class="l-table-edit-td">
					 <input type="text" class="dateEditor" id="signForDate" name="signForDate" value="<fmt:formatDate value="${businessCirclesAppealOrder.signForDate}" pattern="yyyy-MM-dd"/>">
				</td>
			</tr>
			<tr>
				<td class="title">会员截止目前工商投诉/举报情况<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<textarea id="memberSituation" name="memberSituation" rows="4" cols="50" class="text" >${businessCirclesAppealOrder.memberSituation}</textarea>
				</td>
			</tr>
			<tr>
				<td class="title">商家被投诉次数<span class="required">*</span></td>
				<td align="left">
					<input type="text" style="width: 50px;" id="mchtAppealedCount" name="mchtAppealedCount" value="${businessCirclesAppealOrder.mchtAppealedCount}" />&nbsp次
				</td>
			</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>