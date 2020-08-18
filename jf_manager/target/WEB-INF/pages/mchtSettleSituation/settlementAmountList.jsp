<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
	 $(function() {
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left'
		});
		
	 });
	 
	 function formatMoney(s, n) {
	    n = n > 0 && n <= 20 ? n : 2;
	    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	    var l = s.split(".")[0].split("").reverse(),
	    r = s.split(".")[1];
	    t = "";
	    for(i = 0; i < l.length; i ++ )
	    {
	       t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "" : "");
	    }
	    return t.split("").reverse().join("") + "." + r;
	 }
	 
 	 var listConfig={
	      url:"/mchtSettleSituation/settlementAmountList.shtml",
 		  btnItems:[],
	      listGrid:{ columns: [
							{display:'商家序号',name:'', align:'center', isSort:false, width:200, render:function(rowdata, rowindex) {
								return rowdata.mcht_code+"-"+rowdata.mcht_id;
	                        }},
							{display:'公司名称',name:'company_name', align:'center', isSort:false, width:200},
							{display:'店铺名称',name:'shop_name', align:'center', isSort:false, width:200},
							{display:'已经算金额',name:'', align:'center', isSort:false, width:200, render:function(rowdata, rowindex) {
								if(rowdata.close_settle_amount){
									return formatMoney(rowdata.close_settle_amount, 2);	                		
			                	}else{
			                		return "0.00";
			                	}
	                        }},
							{display:'未结算金额',name:'', align:'center', isSort:false, width:200, render:function(rowdata, rowindex) {
								if(rowdata.open_settle_amount){
									return formatMoney(rowdata.open_settle_amount, 2);	                		
			                	}else{
			                		return "0.00";
			                	}
	                        }},
							{display:'销售总应付金额',name:'', align:'center', isSort:false, width:200, render:function(rowdata, rowindex) {
								if(rowdata.sum_settle_amount){
									return formatMoney(rowdata.sum_settle_amount, 2);	                		
			                	}else{
			                		return "0.00";
			                	}
	                        }},
							{display:'直赔单',name:'', align:'center', isSort:false, width:200, render:function(rowdata, rowindex) {
								if(rowdata.sum_customer_amount){
									return formatMoney(rowdata.sum_customer_amount, 2);	                		
			                	}else{
			                		return "0.00";
			                	}
	                        }},
							{display:'是否异常',name:'', align:'center', isSort:false, width:120, render:function(rowdata, rowindex) {
								var close_settle_amount = "0.00";
								var open_settle_amount = "0.00";
								var sum_settle_amount = "0.00";
								var sum_customer_amount = "0.00";
								if(rowdata.close_settle_amount){
									close_settle_amount = formatMoney(rowdata.close_settle_amount, 2);	                		
			                	}
								if(rowdata.open_settle_amount){
									open_settle_amount = formatMoney(rowdata.open_settle_amount, 2);	                		
			                	}
								if(rowdata.sum_settle_amount){
									sum_settle_amount = formatMoney(rowdata.sum_settle_amount, 2);	                		
			                	}
								if(rowdata.sum_customer_amount){
									sum_customer_amount = formatMoney(rowdata.sum_customer_amount, 2);	                		
			                	}
								if(Number(formatMoney(Number(close_settle_amount) + Number(open_settle_amount) + Number(sum_customer_amount), 2)) != Number(sum_settle_amount) ){
									return "<span style='color: red;'>异常</span>";	                		
			                	}else{
			                		return "";
			                	}
	                        }} 
			         ], 
	                 showCheckbox :false,  //不设置默认为 true
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
<!-- 	<div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >商家名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtName" name="mchtName" >
					</div>
				</div>
				<div class="search-td">
				<div class="search-td-label" >主营类目：</div>
				<div class="search-td-condition" >
					<div class="search-td-combobox-condition" >
						<c:if test="${isCwOrgStatus == 1 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" disabled="disabled">
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${isCwOrgStatus == 0 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;">
								<option value="">请选择...</option>
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
						</c:if>
				 	 </div>
				</div>
			</div>
			<div class="search-td">
			<div class="search-td-label">品牌：</div>
			<div  class="search-td-condition">
				<input name="productBrandName">
			</div>
		    </div>
		    <div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >
						<input type="checkbox" id="abnormityFlag" name="abnormityFlag" />
					</div>
					<div class="search-td-combobox-condition" >
						查看异常商家
					</div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			 </div>	
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
