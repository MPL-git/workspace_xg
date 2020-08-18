<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <script type="text/javascript">
 $(function(){	
 	$(".dateEditor").ligerDateEditor({
		showTime : false,
	    onChangeDate: true,
		labelWidth : 150,
		labelAlign : 'left',
		onChangeDate:function(){
		  datechangeHeader();	
		}	 
	});

	//品牌
	$("#productBrandName").ligerComboBox({
     	 width: 135,
         slide: false,
         selectBoxWidth: 450,
         selectBoxHeight: 300,
         valueField: 'id',
         textField: 'name',
         valueFieldID:'productBrandId',
         grid: getGridOptions(false),
         condition:{ fields: [{ name:'name', label:'品牌名', width:90, type:'text' }]}
    });
	
 });
 
  function datechangeHeader(){ 
	  var endPaydateDate = $("#endPaydateDate").val();
	  var endDay = new Date(endPaydateDate);
	  endDay.setDate(endDay.getDate()-1);
	  var year = endDay.getFullYear();
	  var month = endDay.getMonth() + 1;
	  var day = endDay.getDate();
	  if (month < 10) {
	      month = "0" + month;
	  }
	  if (day < 10) {
	      day = "0" + day;
	  }
	  var oneDate = year + "-" + month + "-" + day;
	  
	  var endDay = new Date(endPaydateDate);
	  endDay.setDate(endDay.getDate()-2);
	  var year = endDay.getFullYear();
	  var month = endDay.getMonth() + 1;
	  var day = endDay.getDate();
	  if (month < 10) {
	      month = "0" + month;
	  }
	  if (day < 10) {
	      day = "0" + day;
	  }
	  var twoDate = year + "-" + month + "-" + day;
	  	  
	  var endDay = new Date(endPaydateDate);
	  endDay.setDate(endDay.getDate()-3);
	  var year = endDay.getFullYear();
	  var month = endDay.getMonth() + 1;
	  var day = endDay.getDate();
	  if (month < 10) {
	      month = "0" + month;
	  }
	  if (day < 10) {
	      day = "0" + day;
	  }
	  var treeDate = year + "-" + month + "-" + day;
	    
	  var endDay = new Date(endPaydateDate);
	  endDay.setDate(endDay.getDate()-4);
	  var year = endDay.getFullYear();
	  var month = endDay.getMonth() + 1;
	  var day = endDay.getDate();
	  if (month < 10) {
	      month = "0" + month;
	  }
	  if (day < 10) {
	      day = "0" + day;
	  }
	  var fourDate = year + "-" + month + "-" + day;
	  
	  var endDay = new Date(endPaydateDate);
	  endDay.setDate(endDay.getDate()-5);
	  var year = endDay.getFullYear();
	  var month = endDay.getMonth() + 1;
	  var day = endDay.getDate();
	  if (month < 10) {
	      month = "0" + month;
	  }
	  if (day < 10) {
	      day = "0" + day;
	  }
	  var fiveDate = year + "-" + month + "-" + day;
	  
	  var endDay = new Date(endPaydateDate);
	  endDay.setDate(endDay.getDate()-6);
	  var year = endDay.getFullYear();
	  var month = endDay.getMonth() + 1;
	  var day = endDay.getDate();
	  if (month < 10) {
	      month = "0" + month;
	  }
	  if (day < 10) {
	      day = "0" + day;
	  }
	  var sexDate = year + "-" + month + "-" + day;
  
     $("table tr").find("td:eq(3)").find("span").html("<span style='font-size:12px;'>"+endPaydateDate+"</span>");
     $("table tr").find("td:eq(4)").find("span").html("<span style='font-size:12px;'>"+oneDate+"</span>");
     $("table tr").find("td:eq(5)").find("span").html("<span style='font-size:12px;'>"+twoDate+"</span>");
     $("table tr").find("td:eq(6)").find("span").html("<span style='font-size:12px;'>"+treeDate+"</span>");
     $("table tr").find("td:eq(7)").find("span").html("<span style='font-size:12px;'>"+fourDate+"</span>");
     $("table tr").find("td:eq(8)").find("span").html("<span style='font-size:12px;'>"+fiveDate+"</span>");
     $("table tr").find("td:eq(9)").find("span").html("<span style='font-size:12px;'>"+sexDate+"</span>");
     
 } 
 
 
	function getGridOptions(checkbox){
	     var options = {
	         columns: [
				{display:'ID',name:'id', align:'center', isSort:false, width:100},
				{display:'品牌',name:'name', align:'center', isSort:false, width:100}
	         ], 
	         switchPageSizeApplyComboBox: false,
		     url: '${pageContext.request.contextPath}/operateData/ShopReport/getProductBrandList.shtml',
	         pageSize: 10, 
	         checkbox: checkbox
	     };
	     return options;
	 }
	
	
 var listConfig={
     url:"/operateData/ShopReport/shopreportdata.shtml",
     listGrid:{ columns: [
						{ display: '商家序号',name:'mcht_code',width: 150},
						{ display: '店铺名称',name:'shop_name',width: 150},
						{ display: '店铺汇总',name:'',width: 150,render:function(rowdata, rowindex){
							var html = [];
						    html.push(rowdata.payAmountSum); 
							return html.join("");
						}},
						{ display: '${PaydateDateone}',name:'pay_amount_sum1',width: 150,render:function(rowdata, rowindex){
							    var html = [];
   	   		                    if(Number(rowdata.pay_amount_sum1) > Number(rowdata.pay_amount_sum2)) {
   	   		                  		html.push("<div style='color:red;'>"+rowdata.pay_amount_sum1+"</div>");
   	   		                   	}else if(Number(rowdata.pay_amount_sum1) < Number(rowdata.pay_amount_sum2)){
   	   		                  		html.push("<div style='color:green;'>"+rowdata.pay_amount_sum1+"</div>");
   	   		                    }else{
   	   		                	    html.push(rowdata.pay_amount_sum1);
   	   		                    }
   		                
							  return html.join("");
						}},
						{ display: '${PaydateDatetwo}',name:'pay_amount_sum2',width: 150,render:function(rowdata, rowindex){
							    var html = [];
	   		                    if(Number(rowdata.pay_amount_sum2) > Number(rowdata.pay_amount_sum3)) {
	   		                  		html.push("<div style='color:red;'>"+rowdata.pay_amount_sum2+"</div>");
	   		                   	}else if(Number(rowdata.pay_amount_sum2) < Number(rowdata.pay_amount_sum3)){
	   		                  		html.push("<div style='color:green;'>"+rowdata.pay_amount_sum2+"</div>");
	   		                    }else{
	   		                	    html.push(rowdata.pay_amount_sum2);
	   		                    }
	   		                  return html.join("");
						}},
						{ display: '${PaydateDatethree}',name:'pay_amount_sum3',width: 150,render:function(rowdata, rowindex){
							var html = [];
   		                    if(Number(rowdata.pay_amount_sum3) > Number(rowdata.pay_amount_sum4)) {
   		                  		html.push("<div style='color:red;'>"+rowdata.pay_amount_sum3+"</div>");
   		                   	}else if(Number(rowdata.pay_amount_sum3) < Number(rowdata.pay_amount_sum4)){
   		                  		html.push("<div style='color:green;'>"+rowdata.pay_amount_sum3+"</div>");
   		                    }else{
   		                	    html.push(rowdata.pay_amount_sum3);
   		                    }
   		                    return html.join("");
						}},
						{ display: '${PaydateDatefour}',name:'pay_amount_sum4',width: 150,render:function(rowdata, rowindex){
							var html = [];
   		                    if(Number(rowdata.pay_amount_sum4) > Number(rowdata.pay_amount_sum5)) {
   		                  		html.push("<div style='color:red;'>"+rowdata.pay_amount_sum4+"</div>");
   		                   	}else if(Number(rowdata.pay_amount_sum4) < Number(rowdata.pay_amount_sum5)){
   		                  		html.push("<div style='color:green;'>"+rowdata.pay_amount_sum4+"</div>");
   		                    }else{
   		                	    html.push(rowdata.pay_amount_sum4);
   		                    }
   		                    return html.join("");
						}},
						{ display: '${PaydateDatefive}',name:'pay_amount_sum5',width: 150,render:function(rowdata, rowindex){
							var html = [];
   		                    if(Number(rowdata.pay_amount_sum5) > Number(rowdata.pay_amount_sum6)) {
   		                  		html.push("<div style='color:red;'>"+rowdata.pay_amount_sum5+"</div>");
   		                   	}else if(Number(rowdata.pay_amount_sum5) < Number(rowdata.pay_amount_sum6)){
   		                  		html.push("<div style='color:green;'>"+rowdata.pay_amount_sum5+"</div>");
   		                    }else{
   		                	    html.push(rowdata.pay_amount_sum5);
   		                    }
   		                    return html.join("");
						}},
						{ display: '${PaydateDatesix}',name:'pay_amount_sum6',width: 150,render:function(rowdata, rowindex){
							var html = [];
   		                    if(Number(rowdata.pay_amount_sum6) > Number(rowdata.pay_amount_sum7)) {
   		                  		html.push("<div style='color:red;'>"+rowdata.pay_amount_sum6+"</div>");
   		                   	}else if(Number(rowdata.pay_amount_sum6) < Number(rowdata.pay_amount_sum7)){
   		                  		html.push("<div style='color:green;'>"+rowdata.pay_amount_sum6+"</div>");
   		                    }else{
   		                	    html.push(rowdata.pay_amount_sum6);
   		                    }
   		                    return html.join("");
						}},
						{ display: '${PaydateDateseven}',name:'pay_amount_sum7',width: 150,render:function(rowdata, rowindex){
							var html = [];  		           
   		                	html.push(rowdata.pay_amount_sum7);
   		                    return html.join("");
						}}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: true//不设置默认为 true
                 
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
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">付款日期：</div>
					<div class="l-panel-search-item">
						<input type="text" id="endPaydateDate" name="endPaydateDate" value="${endPaydateDate}" class="dateEditor"/>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >商家序号：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商家名称：</div>
					<div class="search-td-condition">
						<input type="text" id="mchtName" name="mchtName">
					</div>
				</div>
			</div>	
		
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" >品牌名称：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="productBrandName" name="productBrandName">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">对接人：</div>
					<div class="search-td-condition">
						<select id="platContactStaffId" name="platContactStaffId">
							<c:if test="${isManagement == 1}">
								<option value="" selected="selected">全部商家</option>
							</c:if>
							<option value="${staffID }" selected="selected" >我自己</option>
							<c:forEach items="${sysStaffInfoCustomList }" var="sysStaffInfoCustom">
								<option value="${sysStaffInfoCustom.staffId }">${sysStaffInfoCustom.staffName }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">一级类目：</div>
					<div class="search-td-condition">
						<select id="productTypeId" name="productTypeId" class="querysel">
							<option value="">请选择</option>
							<c:forEach var="list" items="${productTypes}">
								<option value="${list.id}">${list.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td-search-btn">
					<div id="searchbtn">搜索</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>