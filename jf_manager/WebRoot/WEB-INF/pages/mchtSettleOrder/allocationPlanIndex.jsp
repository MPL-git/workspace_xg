<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">

	 
 function formatMoney(s, n)
 {
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
function exportExcel() {
	var mchtCode = $("#mchtCode").val();
	var name = $("#name").val();
	location.href="${pageContext.request.contextPath}/mchtSettleSituation/exportCurrent.shtml?mchtCode="+mchtCode+"&name="+name;	
}
 
 function getLastDay(year,month)     
 {     
  var new_year = year;  //取当前的年份     
  var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）     
  if(month>12)      //如果当前大于12月，则年份转到下一年     
  {     
  new_month -=12;    //月份减     
  new_year++;      //年份增     
  }     
  var new_date = new Date(new_year,new_month,1);        //取当年当月中的第一天     
  return (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月最后一天日期     
 }   
 
 var listConfig={
     url:"/mchtSettleOrder/allocationPlanList.shtml",
     listGrid:{ columns: [
			            { display: '商家序号',name:'', render: function (rowdata, rowindex) {
			            	return rowdata.mchtCode;	
		                }},
		                { display: '开通日期', render: function (rowdata, rowindex) {
		                	var html = [];
                        	if (rowdata.cooperateBeginDate != null && rowdata.cooperateBeginDate != '') {
								var cooperateBeginDate = new Date(rowdata.cooperateBeginDate);
								html.push(cooperateBeginDate.format("yyyy-MM-dd hh:mm:ss"));
							}
							return html.join("");
		                }},
		                { display: '商家公司名称', name:'companyName'},
		                { display: '店铺名称', name:'shopName'},
		                { display: '类目',name:'openProductType'},
		                { display: '品牌',name:'openProductBrand'},
		                { display: '结算类型',name:'typeDesc',render: function (rowdata, rowindex) {
		                	var text = "";
		                	if(!rowdata.popId){
		                		text = "未填写";
		                	}else{
		                		text = rowdata.typeDesc;
		                	}
							return "<a href=\"javascript:edit(" + rowdata.popId +","+rowdata.id+");\">"+text+"</a>";
		                }},
		                { display: '共几期未付',name:'unpaidCount'},
		                { display: '未付总金额',render: function (rowdata, rowindex) {
		                	if(rowdata.unpaidAmount){
								return rowdata.unpaidAmount;	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '押几期',name:'periods'},
		                { display: '超时几期',name:'exceedPeriods',render: function (rowdata, rowindex) {
		                	if(rowdata.popId && rowdata.unpaidCount-rowdata.periods > 0){
		                		return rowdata.unpaidCount-rowdata.periods;
		                	} else if(rowdata.popId && rowdata.unpaidCount-rowdata.periods < 0){
		                		return "0";
		                	}           	
		                }},
		                { display: '超时金额',render: function (rowdata, rowindex) {
		                	if(rowdata.exceedAmount && rowdata.popId != null && rowdata.popId != ""){
								return rowdata.exceedAmount;	                		
		                	}else if(!rowdata.popId){
		                		return "";
		                	}else{
		                		return "0.00";
		                	}
		                }}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: true //不设置默认为 true
      },  							
     container:{
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
  };
 
	//编辑
	function edit(id,mchtId) {
		$.ligerDialog.open({
			height: 250,
			width: 600,
			title: "修改类型",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtSettleOrder/audit.shtml?id=" + id+"&mchtId="+mchtId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  >
			<div class="search-td">
			<div class="search-td-label" >商家序号：</div>
			<div class="search-td-condition" >
				<input type="text" id="mchtCode" name="mchtCode" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >商家名称：</div>
			<div class="search-td-condition" >
				<input type="text" id="companyOrShop" name="companyOrShop" >
			</div>
			</div>
			<div class="search-td">
				<div class="search-td-label" >类目：</div>
				<div class="search-td-condition" >
					<div class="search-td-combobox-condition" >
					<select id="productTypeId" name="productTypeId" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="productType" items="${productTypes}">
						<option value="${productType.id}">${productType.name}
						</option>
					</c:forEach>
					</select>
				 	 </div>
				</div>
			</div>
		 	<div class="search-td">
			<div class="search-td-label">结算类型：</div>
			<div  class="search-td-condition">
				<select id="type" name="type" style="width: 135px;">
					<option value="">请选择...</option>
					<option value="1">T+1</option>
					<option value="2">暂停</option>
					<option value="3">周期</option>
					<option value="4">重点</option>
					<option value="5">大商家</option>
				</select>
			</div>
		    </div>
			 <div  style="display: inline-flex;">
				<div style="padding-left: 10px;">
					<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="搜索" id="searchbtn">
				</div>
			 </div>
		</div>
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>