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
		if(dateFun()) {
			var date_begin = $("#date_begin").val();
			var date_end = $("#date_end").val();
			location.href="${pageContext.request.contextPath}/preferentialCount/integralDailyStatistics/export.shtml?date_begin="+date_begin+"&date_end="+date_end;
	  	}
	});
	
});

  var listConfig={
		  
	  url:"/preferentialCount/integralDailyStatistics/data.shtml",
      
      listGrid:{ columns: [{ display: '日期', name:'statisticDate', align:'center', isSort:false, width:100},
 		                { display: '期初余额', align:'center', isSort:false, width:120, render: function (rowdata, rowindex) {
 		                	if(rowdata.beginIntegral){
 		                		return formatMoney(rowdata.beginIntegral,2);
 		                	}
		                }},
		                {display:'购物赠送（+）', name:'integralType1', align:'center', isSort:false, width:100},
		                {display:'手机认证（+）', name:'integralType2', align:'center', isSort:false, width:100},
		                {display:'完善资料（+）', name:'integralType3', align:'center', isSort:false, width:100},
		                {display:'购物抵扣（-）', name:'integralType4', align:'center', isSort:false, width:100},
		                {display:'积分兑换（-）', name:'integralType5', align:'center', isSort:false, width:100},
		                {display:'系统赠送（+）', name:'integralType6', align:'center', isSort:false, width:100},
		                {display:'抵扣返还（+）', name:'integralType7', align:'center', isSort:false, width:100},
		                {display:'售后补偿（+）', name:'integralType8', align:'center', isSort:false, width:150},
		                {display:'输入邀请码（+）', name:'integralType17', align:'center', isSort:false, width:130},
		                {display:'查看新星攻略（+）', name:'integralType18', align:'center', isSort:false, width:130},
		                {display:'参与游戏（-）', name:'integralType9', align:'center', isSort:false, width:100},
		                {display:'参与游戏（+）', name:'integralType10', align:'center', isSort:false, width:100},
		                {display:'积分超时失效（-）', name:'invalidIntegral', align:'center', isSort:false, width:120},
		                {display:'其它赠送', name:'otherGive', align:'center', isSort:false, width:100},
		                {display:'其它消费', name:'otherUse', align:'center', isSort:false, width:100},
		                { display: '期末余额', align:'center', isSort:false, width:120, render: function (rowdata, rowindex) {
		                	if(rowdata.endIntegral){
 		                		return formatMoney(rowdata.endIntegral,2);
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
  
	function subFun() {
	  	if(dateFun()) {
	  		$("#searchbtn").click();
	  	}
	}
  
  	function dateFun() {
  		var dateBegin = $("#date_begin").val();
		var dateEnd = $("#date_end").val();
		var date = '${date_end }'
		if(dateBegin == '' || dateEnd == '') {
			commUtil.alertError("日期不能为空！");
			return false;
		}else if(dateBegin > date || dateEnd > date) {
			commUtil.alertError("日期必须小于当天！");
			return false;
		}else if(dateBegin > dateEnd) {
			commUtil.alertError("开始日期必须小于等于结束日期！");
			return false;
		}else{
			var dateBeginTime = new Date(dateBegin).getTime();
			var dateEndTime = new Date(dateEnd).getTime();
			var num = 31*24*60*60*1000;
			if((dateEndTime - dateBeginTime) > num) {
				commUtil.alertError("开始日期与结束日期，最多相差31天！");
				return false;
			}
		}
		return true;
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
				<div id="searchbtn" style="height: 28px;line-height: 28px; display: none;"></div>
				<input type="button" style="width: 65px;height: 30px;cursor: pointer; background-color: #c1dbfa; border: solid 1px #A3C0E8; color: #333333;" value="搜索" onclick="subFun();">
				<input type="button" style="width: 70px;height: 30px;cursor: pointer; margin-left: 20px;" value="导出汇总表" id="export">
			</div>
		</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>