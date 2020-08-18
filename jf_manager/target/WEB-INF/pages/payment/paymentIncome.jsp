<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
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


$(function(){
	$("#export").bind('click',function(){
		var pay_date_begin = $("#pay_date_begin").val();
		var pay_date_end = $("#pay_date_end").val();
		location.href="${pageContext.request.contextPath}/paymentIncome/export.shtml?pay_date_begin="+pay_date_begin+"&pay_date_end="+pay_date_end;
	});
	
});



  var listConfig={
		  
	  url:"/paymentIncome/data.shtml",
      
      listGrid:{ columns: [
                        { display: '收款日期', name:'eachDay1'},
 		                 
		                { display: '微信APP日常销售', name:'',render: function (rowdata, rowindex) {
		                	var html =[];
		                	html.push(formatMoney(rowdata.wxappAmount, 2));
			            	return html.join("");
		                }},
		                { display: '公众号日常销售', name:'', render: function (rowdata, rowindex) {
		                	var html = [];
		                	html.push(formatMoney(rowdata.wxgzhAmount, 2));
			            	return html.join("");
		                }},
		                { display: '微信APP定金', name:'',render:function(rowdata, rowindex){
		                	var html=[];
		                	html.push(formatMoney(rowdata.cdowxAmount, 2));
			            	return html.join("");
		                }},
		                { display: '公众号定金', name:'',render:function(rowdata, rowindex){
		                	var html=[];
		                	html.push(formatMoney(rowdata.cdogzhAmount, 2));
			            	return html.join("");
		                }},
		                { display: '微信APPSVIP', name:'',render:function(rowdata, rowindex){
		                	var html=[];
		                	html.push(formatMoney(rowdata.svipwxAmount, 2));
			            	return html.join("");
		                }},
		                { display: '微信公众号SVIP', name:'',render:function(rowdata, rowindex){
		                	var html=[];
		                	html.push(formatMoney(rowdata.svipgzhAmount, 2));
			            	return html.join("");
		                }},
		                { display: '支付宝定金', name:'',render:function(rowdata, rowindex){
		                	var html=[];
		                	html.push(formatMoney(rowdata.cdozfbAmount, 2));
			            	return html.join("");
		                }},
		                { display: '支付宝日常销售', name:'',render:function(rowdata, rowindex){
		                	var html=[];
		                	html.push(formatMoney(rowdata.zFbamount, 2));
			            	return html.join("");
		                }},
		                { display: '支付宝SVIP', name:'',render:function(rowdata, rowindex){
		                	var html=[];
		                	html.push(formatMoney(rowdata.svipzfbAmount, 2));
			            	return html.join("");
		                }},
			            { display: '支付宝设计付费', name:'',render:function(rowdata, rowindex){
					        var html=[];
					        html.push(formatMoney(rowdata.zfbDesignAmount, 2));
					        return html.join("");
				        }},
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true//不设置默认为 true
                 
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">收入日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="pay_date_begin" name="pay_date_begin" value="${pay_date_begin}"/>
				<script type="text/javascript">
					$(function() {
						$("#pay_date_begin").ligerDateEditor( {
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
				<input type="text" id="pay_date_end" name="pay_date_end" value="${pay_date_end}"/>
				<script type="text/javascript">
					$(function() {
						$("#pay_date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			
			<div style="display: inline-flex;">
				<div id="searchbtn" style="height: 28px;line-height: 28px;">
					搜索
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="导出" id="export">
				</div>
			</div>
		</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>