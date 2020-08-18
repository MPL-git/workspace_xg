<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" ></script>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>

<style type="text/css">

</style>

 <script type="text/javascript">
	 $(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 150
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
	 
	 //订单信息
	 function viewCommentList(mchtId, avgProductScore, avgMchtScore, avgWuliuScore) {
		 var tabid = "ligerui1" + new Date().getTime();
         var title = "评价信息";
         var url = "/comment/mchtCommentInfoManager.shtml?mchtId="+mchtId
        		 		+"&avgProductScore="+avgProductScore
        		 		+"&avgMchtScore="+avgMchtScore
        		 		+"&avgWuliuScore="+avgWuliuScore;
         parent.addTab(tabid, title, url);
	 }
	 
	 //入驻品牌 
	 function editMchtProductBrand(mchtProductBrandId) {
			$.ligerDialog.open({
			height: $(window).height() - 40,
			width: 1000,
			title: "入驻品牌",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/editMchtProductBrand.shtml?view=0&mchtProductBrandId=" + mchtProductBrandId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
 	 var listConfig={ 
	      url:"/comment/getMchtCommentList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'商家序号', name:'mcht_code', align:'center', isSort:false, width:100},   
							{display:'公司名称', name:'company_name', align:'center', isSort:false, width:120}, 
							{display:'店铺名称', name:'shop_name', align:'center', isSort:false, width:180}, 
							{display:'主营类目', name:'producttype_name', align:'center', isSort:false, width:120},
							{display:'品牌/资质/开通', name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
			                	var mchtProductBrandCustom = rowdata.mcht_product_brand_custom;
			                	var html = [];
			                	if(mchtProductBrandCustom != null && mchtProductBrandCustom != ''){
			                		var mchtProductBrandCustomAry = mchtProductBrandCustom.split(",");
									for (var i = 0; i < mchtProductBrandCustomAry.length; i++) {
										if(mchtProductBrandCustomAry[i] != null && mchtProductBrandCustomAry[i] != '') {
											var mchtProductBrandCustomStr = mchtProductBrandCustomAry[i].split("|");
											if(mchtProductBrandCustomStr.length > 0) {
												html.push("<span style='color:red;'>["+mchtProductBrandCustomStr[1]+"]</span>");
												html.push("<span style='color:green;'>["+mchtProductBrandCustomStr[2]+"]</span>");
												if(mchtProductBrandCustomStr[3] != '') {
													html.push("<a href=\"javascript:editMchtProductBrand(" + mchtProductBrandCustomStr[0] + ");\">"+mchtProductBrandCustomStr[3]+"</a>");
												}else {
													html.push("<a href=\"javascript:editMchtProductBrand(" + mchtProductBrandCustomStr[0] + ");\">"+mchtProductBrandCustomStr[4]+"</a>");
												}
												html.push("<br/>");
											}
										}
									}
			                	}
								return html.join("");
			                }},
			                {display:'销量', name:'sum_quantity', align:'center', isSort:false, width:100},
			                {display:'商品描述', name:'', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
			                	var html = [];
			                	if(rowdata.avg_product_score != null) {
			                		html.push(rowdata.avg_product_score+"分");
			                	}else {
			                		html.push("--");
			                	}
			                	return html.join("");
			                }},
			                {display:'卖家服务', name:'', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
			                	var html = [];
			                	if(rowdata.avg_mcht_score != null) {
			                		html.push(rowdata.avg_mcht_score+"分");
			                	}else {
			                		html.push("--");
			                	}
			                	return html.join("");
			                }},
			                {display:'物流服务', name:'', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
			                	var html = [];
			                	if(rowdata.avg_wuliu_score != null) {
			                		html.push(rowdata.avg_wuliu_score+"分");
			                	}else {
			                		html.push("--");
			                	}
			                	return html.join("");
			                }},
			                {display:'开店时间', name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
    	   		                var html = [];
	   		                	if(rowdata.cooperate_begin_date != null && rowdata.cooperate_begin_date != "" ) {
	   		                		var cooperateBeginDate = new Date(rowdata.cooperate_begin_date);
	    	   		                var cooperateBeginDateStr = cooperateBeginDate.format("yyyy-MM-dd hh:mm:ss");
	    	   		             	var today = new Date().format("yyyy-MM-dd");
	    	   		                if(cooperateBeginDateStr.substring(0,10) == today){
	    	   		                	html.push("<span style='color:red;'>"+cooperateBeginDateStr+"</span>");
	    	   		                }else{
	    	   		                	html.push(cooperateBeginDateStr);
	    	   		                }
	    		                }
								return html.join("");
	 		                }},
							{display:'操作', name:'', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.avg_product_score == null && rowdata.avg_mcht_score == null && rowdata.avg_wuliu_score == null) {
									html.push("<a href='javascript:;' style='color: gray;' >【评价信息】</a>");
								}else {
									html.push("<a href='javascript:;' onclick='viewCommentList("+rowdata.id+", "+rowdata.avg_product_score+", "+rowdata.avg_mcht_score+", "+rowdata.avg_wuliu_score+")'>【评价信息】</a>");
								}
							    return html.join("");
							}}
			         ], 
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      }, 							
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
	<!-- <div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server" >
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" >商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >商家名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtName" name="mchtName" >
					</div>
				</div>
				<div class="search-td">
				 	<div class="search-td-label">主营类目：</div>
				 	<div class="search-td-condition"  >
			    		<select id="productTypeId" name="productTypeId" style="width: 135px;" >
							<option value="">请选择</option>
							<c:forEach var="list" items="${productTypes}">
								<option value="${list.id}">${list.name}</option>
							</c:forEach>
						</select>
					</div>
			 	</div>
			  	<div class="search-td">
					<div class="search-td-label" >品牌：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="bandName" name="bandName" >
					</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">商品描述评分：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="avgProductScoreBegin"
							name="avgProductScoreBegin" style="width: 43%;" /> <span
							style="width:10%">--</span> <input type="text"
							id="avgProductScoreEnd" name="avgProductScoreEnd"
							style="width: 43%;" />
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">卖家服务评分：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="avgMchtScoreBegin" name="avgMchtScoreBegin"
							style="width: 43%;" /> <span style="width:10%">--</span> <input
							type="text" id="avgMchtScoreEnd" name="avgMchtScoreEnd"
							style="width: 43%;" />
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">物流服务评分：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="avgWuliuScoreBegin"
							name="avgWuliuScoreBegin" style="width: 43%;" /> <span
							style="width:10%">--</span> <input type="text"
							id="avgWuliuScoreEnd" name="avgWuliuScoreEnd" style="width: 43%;" />
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
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
