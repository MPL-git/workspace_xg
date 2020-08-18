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

var zfbAmount = "";
var zfbCount = "";

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
		location.href="${pageContext.request.contextPath}/designReceivables/count/export.shtml?pay_date_begin="+pay_date_begin+"&pay_date_end="+pay_date_end;
	});
	$(".dateEditor").ligerDateEditor( {
		showTime : false,
		labelAlign : 'left'
	});
	
});

function designReceivablesDtl(payDate,paymentId) {
	$.ligerDialog.open({
 		height: $(window).height()-100,
		width: $(window).width()-100,
		title: "设计收款明细",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/designReceivablesDtl/list.shtml?paymentId=" + paymentId+"&payDate="+payDate,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}


function checkSearchCondition(){
	zfbAmount = "";
	zfbCount = "";
    return true;
 }

  var listConfig={
		  
	  url:"/designReceivables/count/data.shtml",
      
      listGrid:{ columns: [{ display: "收款日期", name:'eachDay'},

					 	 { display: '支付宝笔数',name:'', render: function (rowdata, rowindex) {
							  var html = [];
							  if(zfbCount != ''){
								  if(Number(zfbCount) > Number(rowdata.zfbCount) ) {
									  html.push("<a href='javascript:;' style='color:green;' onclick='designReceivablesDtl(\""+ rowdata.eachDay +"\", 2);'>"+ rowdata.zfbCount +"</span>");
								  }else if(Number(zfbCount) < Number(rowdata.zfbCount) ) {
									  html.push("<a href='javascript:;' style='color:red;' onclick='designReceivablesDtl(\""+ rowdata.eachDay +"\", 2);'>"+ rowdata.zfbCount +"</span>");
								  }else {
									  html.push("<a href='javascript:;' onclick='designReceivablesDtl(\""+ rowdata.eachDay +"\", 2);'>"+ rowdata.zfbCount +"</a>");
								  }
							  }else {
								  html.push("<a href='javascript:;' onclick='designReceivablesDtl(\""+ rowdata.eachDay +"\", 2);'>"+ rowdata.zfbCount +"</a>");
							  }
							  return html.join("");
						  }},

		                { display: '支付宝金额',render: function (rowdata, rowindex) {
		                	var html = [];
		                	if(zfbAmount != ''){
		                		if(Number(zfbAmount) > Number(formatMoney(rowdata.zfbAmount, 2)) ) {
		                			html.push("<a href='javascript:;' style='color:green;' onclick='designReceivablesDtl(\""+ rowdata.eachDay +"\", 2);'>"+ formatMoney(rowdata.zfbAmount, 2) +"</a>");
			            		}else if(Number(zfbAmount) < Number(formatMoney(rowdata.zfbAmount, 2)) ) {
			            			html.push("<a href='javascript:;' style='color:red;' onclick='designReceivablesDtl(\""+ rowdata.eachDay +"\", 2);'>"+ formatMoney(rowdata.zfbAmount, 2) +"</a>");
			            		}else {
			            			html.push("<a href='javascript:;' onclick='designReceivablesDtl(\""+ rowdata.eachDay +"\", 2);'>"+ formatMoney(rowdata.zfbAmount, 2) +"</a>");
			            		}
		                	}else {
		                		html.push("<a href='javascript:;' onclick='designReceivablesDtl(\""+ rowdata.eachDay +"\", 2);'>"+ formatMoney(rowdata.zfbAmount, 2) +"</a>");
		                	}
							zfbAmount = rowdata.zfbAmount+"";
							zfbCount = rowdata.zfbCount+"";
			            	return html.join("");
		                }},

		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true, //不设置默认为 true
                 beforeSearch: checkSearchCondition
      } , 							
     container:{
        // toolBarName:"toptoolbar",
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
			<div class="search-td-label" style="float: left;width: 20%;margin-top:2px;">日期：</div>
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
	<script type="text/javascript">
	$(function() {
		// 禁止分页
		 $("#maingrid").ligerGrid({
            usePager:false
        });
	});
</script>
</body>