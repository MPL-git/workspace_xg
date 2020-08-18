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
		var date_begin = $("#date_begin").val();
		var date_end = $("#date_end").val();
		location.href="${pageContext.request.contextPath}/preferentialCount/export.shtml?date_begin="+date_begin+"&date_end="+date_end;
	});
	
});

  var listConfig={
		  
	  url:"/preferentialCount/data.shtml",
      
      listGrid:{ columns: [{ display: "日期", name:'eachDay'},
 		                { display: '日付款单金额', render: function (rowdata, rowindex) {
 		                	if(rowdata.eachDayPayAmount){
 		                		return formatMoney(rowdata.eachDayPayAmount,2);
 		                	}
		                }},
		                { display: '日付款单优惠', render: function (rowdata, rowindex) {
		                	if(rowdata.eachDayPreferentialAmount){
 		                		return formatMoney(rowdata.eachDayPreferentialAmount,2);
 		                	}
		                }},
		                { display: '付款单优惠比例', render: function (rowdata, rowindex) {
		                	if(rowdata.eachDayPreferentialRate){
 		                		return formatMoney(rowdata.eachDayPreferentialRate,2);
 		                	}
		                }},
		                { display: '日完成单金额',render: function (rowdata, rowindex) {
		                	if(rowdata.eachDayCompletePayAmount){
 		                		return formatMoney(rowdata.eachDayCompletePayAmount,2);
 		                	}
		                }},
		                { display: '日完成单优惠',render: function (rowdata, rowindex) {
		                	if(rowdata.eachDayCompletePreferentialAmount){
 		                		return formatMoney(rowdata.eachDayCompletePreferentialAmount,2);
 		                	}
		                }},
		                { display: '完成单优惠比例', render: function (rowdata, rowindex) {
		                	if(rowdata.eachDayCompletePreferentialRate){
 		                		return formatMoney(rowdata.eachDayCompletePreferentialRate,2);
 		                	}
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
</script>

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">日期：</div>
			<div class="l-panel-search-item" >
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
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
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
			<div style="display: inline-flex;">
				<div id="searchbtn" style="height: 28px;line-height: 28px;">
					搜索
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 65px;height: 30px;cursor: pointer;" value="导出汇总表" id="export">
				</div>
			</div>
		</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>