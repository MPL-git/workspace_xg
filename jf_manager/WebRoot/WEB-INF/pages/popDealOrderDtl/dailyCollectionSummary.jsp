<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
.color-s0,.color-fs1{color: #0000FF;}
.color-s1,.color-fs2{color: #008000;}
.color-s4{color: #333333;}
.color-fs0{color: #000000;}
</style>
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

/* function viewMcht(productId,companyName,mchtShortName) {
	$.ligerDialog.open({
 		height: $(window).height()*0.3,
		width: $(window).width()*0.3,
		title: companyName+"("+mchtShortName+")",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/popDealOrderDtl/viewMcht.shtml?productId=" + productId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
} */

    // 查看商家信息
	function viewMchtInfo(id) {
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
	
	//导出
	function excel(){  
		var receiptDate = $("#receipt_date").val();
		if(receiptDate){
			$("#dataForm").attr("action","${pageContext.request.contextPath}/popDealOrderDtl/dailyCollectionSummaryExport.shtml");
			$("#dataForm").submit();
		}
	}

  var listConfig={

	  url:"/popDealOrderDtl/dailyCollectionSummaryList.shtml",
      listGrid:{ columns: [
		                { display: '主营类目', name: 'main_product_type'},
		                { display: '商家序号', name: 'mcht_code'},
		                { display: '公司名称', name:'company_name'},
		                { display: '店铺名称', name:'shop_name'},
		                { display: '金额', name:'sum_pay_amount'}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
     container:{
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
}
</script>

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"> 
			<div class="search-td">
				<div class="search-td-label" >商家序号：</div>
				<div class="search-td-condition" >
					<%-- <input type="text" id="mchtCode" name="mchtCode" value="${mchtCode}"> --%>
					<textarea id="mchtCode" name="mchtCode" rows="2" cols="3"></textarea>
				</div>
			</div>

			<div class="search-td">
				<div class="search-td-label">类目：</div>
				<select id="suitGroup" name="mainProductType" class="mainProductType" style="width: 150px;">
							<option value="">请选择</option>
						<c:forEach  var="productType" items="${productTypes}">
							<option value="${productType.id}">${productType.name}</option>
						</c:forEach>
					</select>
			</div>
			
			<div class="search-td">
				<div class="search-td-label" >公司名称：</div>
				<div class="search-td-condition" >
					<input type="text" id="companyName" name="companyName" value="${companyName}">
				</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">收货日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="receipt_date" name="receipt_date" value="${receipt_date}"/>
				<script type="text/javascript">
					$(function() {
						$("#receipt_date").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			</div>
			
			<div class="search-td-search-btn" style="display: inline-flex; ">
				<div style="padding-left: 10px;">
					<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="搜索" id="searchbtn">
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="导出" id="export" onclick="excel();" >
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>