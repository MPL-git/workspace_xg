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
var totalCount = "";
var combineAmount = "";
var wxAmount = "";
var zfbAmount = "";
var gzhAmount = "";
var wxCount = "";
var zfbCount = "";
var gzhCount = "";

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

});

  var listConfig={
		  
	  url:"/resourceLocationManagement/getHourProductDataStatisticsList.shtml?pagetype="+${pagetype},
      
      listGrid:{ columns: [
			          { display: "统计时间", name:'name'},
					  { display: '访客数（会员）',name:'totalVisitorCount'},
					  { display: "访客数（非会员）", name:'totalVisitorCountTourist'},
					  { display: '浏览量（会员）', name:'totalPvCount'},
					  { display: '浏览量（非会员）', name:'totalPvCountTourist'},
					  { display: '加购件数', name:'shoppingCartCount'},
					  { display: '加购转化', name:'addProductRate'},
					  { display: '提交订单件数',name:'subProductCount'},
					  { display: '下单转化', name:'submitOrderRate'},
					  { display: '付款件数', name:'payProductCount'},
					  { display: '付款转化', name:'paymentRate'}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true, //不设置默认为 true
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
		<div class="search-tr"  >
			<div class="search-td" style="margin-bottom:-5px;">
			<div class="search-td-label" style="float:left;">统计日期：</div>
			<div class="l-panel-search-item" >
				<script type="text/javascript">
					$(function() {
						$("#begin_date").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
				<input type="text" id="begin_date" name="begin_date" value="${begin_date}"/>
			</div>
			</div>
			<div style="display: inline-flex;">
				<div id="searchbtn" style="height: 28px;line-height: 28px;">
					搜索
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