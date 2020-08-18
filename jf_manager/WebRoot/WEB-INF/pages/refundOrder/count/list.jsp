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
		var success_date_begin = $("#success_date_begin").val();
		var success_date_end = $("#success_date_end").val();
		location.href="${pageContext.request.contextPath}/refundOrder/count/export.shtml?success_date_begin="+success_date_begin+"&success_date_end="+success_date_end;
	});

	$(".dateEditor").ligerDateEditor( {
		showTime : false,
		labelAlign : 'left',
		labelwidth: 120,
		width:120
	});
	
});

function refundList(successDate,paymentId) {
	$.ligerDialog.open({
 		height: $(window).height()-100,
		width: $(window).width()-100,
		title: "退款任务",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/refundOrder/list.shtml?paymentId=" + paymentId+"&successDate="+successDate,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toLockDate(lockDate) {
	$.ligerDialog.open({
 		height: $(window).height()*0.45,
		width: $(window).width()*0.30,
		title: "锁住退款日期操作",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/refundOrder/count/toLockDate.shtml?lockDate="+lockDate,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

  var listConfig={
		  
	  url:"/refundOrder/count/data.shtml",
      
      listGrid:{ columns: [{ display: "退款日期", name:'eachDay'},
 		                { display: '笔数', render: function (rowdata, rowindex) {
 		                	var count = Number(rowdata.wxCount) + Number(rowdata.zfbCount) + Number(rowdata.gzhCount) + Number(rowdata.otherCount);
			            	if(count != rowdata.totalCount){
								return rowdata.totalCount+"<span style='color:red;'>[异常]</span>";	                		
		                	}else{
				            	return ""+rowdata.totalCount;
		                	}
		                }},
		                { display: '退款金额', render: function (rowdata, rowindex) {
		                	var amount = Number(rowdata.wxAmount) + Number(rowdata.zfbAmount) + Number(rowdata.gzhAmount) + Number(rowdata.otherAmount);
		                	if(parseFloat(amount).toFixed(2)!=rowdata.refundAmount){
		                		return formatMoney(rowdata.refundAmount,2)+"<span style='color:red;'>[异常]</span>";
		                	}else{
				            	if(rowdata.refundAmount){
									return formatMoney(rowdata.refundAmount,2);	                		
			                	}
		                	}
		                }},
		                { display: '微信APP/H5金额', render: function (rowdata, rowindex) {
			            	if(rowdata.wxAmount){
								return '<a href="javascript:;" onclick="refundList('+"'"+rowdata.eachDay+"'"+',1);">'+formatMoney(rowdata.wxAmount,2)+'</a>';	                		
		                	}
		                }},
		                { display: '支付宝金额',render: function (rowdata, rowindex) {
			            	if(rowdata.zfbAmount){
								return '<a href="javascript:;" onclick="refundList('+"'"+rowdata.eachDay+"'"+',2);">'+formatMoney(rowdata.zfbAmount,2)+'</a>';	                		
		                	}
		                }},
		                { display: '微信公众号/小程序金额', render: function (rowdata, rowindex) {
			            	if(rowdata.gzhAmount){
								return '<a href="javascript:;" onclick="refundList('+"'"+rowdata.eachDay+"'"+',3);">'+formatMoney(rowdata.gzhAmount,2)+'</a>';	                		
		                	}
		                }},
		                { display: '其他金额', render: function (rowdata, rowindex) {
		                	if(rowdata.otherAmount){
								return '<a href="javascript:;" onclick="refundList('+"'"+rowdata.eachDay+"'"+',-1);">'+formatMoney(rowdata.otherAmount,2)+'</a>';	                		
		                	}
		                }},
		                { display: '微信APP/H5笔数',name:'wxCount'},
		                { display: '支付宝笔数',name:'zfbCount'},
		                { display: '微信公众号/小程序笔数',name:'gzhCount'},
		                { display: '其他笔数',name:'otherCount'},
		                { display: '已确认金额',render: function (rowdata, rowindex) {
			            	if(rowdata.confirmAmount){
								return formatMoney(rowdata.confirmAmount,2);	                		
		                	}
		                }},
		                { display: '锁住日期', render: function (rowdata, rowindex) {
		                	if(rowdata.totalCount){
				            	if(rowdata.lockDate){
									return "已锁";	                		
			                	}else{
			                		if(rowdata.registerAmount || rowdata.noDealAmount){
				                		return '<a href="javascript:;">未锁</a>';
			                		}else{
				                		return '<a href="javascript:;" onclick="toLockDate('+"'"+rowdata.eachDay+"'"+');">未锁[锁定]</a>';
			                		}
			                	}
		                	}else{
		                		return "";
		                	}
		                }},
		                { display: '已登记金额', render: function (rowdata, rowindex) {
			            	if(rowdata.registerAmount){
								return formatMoney(rowdata.registerAmount,2);	                		
		                	}
		                }},
		                { display: '未处理金额',render: function (rowdata, rowindex) {
			            	if(rowdata.noDealAmount){
								return formatMoney(rowdata.noDealAmount,2);	                		
		                	}
		                }},
		                { display: '异常金额',render: function (rowdata, rowindex) {
			            	if(rowdata.unusualAmount){
								return formatMoney(rowdata.unusualAmount,2);	                		
		                	}
		                }}
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
<%--		<div class="search-tr"  > --%>
<%--			<div class="search-td">--%>
<%--			<div class="search-td-label" style="float:left;">退款日期：</div>--%>
<%--			<div class="l-panel-search-item" >--%>
<%--				<input type="text" id="success_date_begin" name="success_date_begin" value="${success_date_begin}"/>--%>
<%--				<script type="text/javascript">--%>
<%--					$(function() {--%>
<%--						$("#success_date_begin").ligerDateEditor( {--%>
<%--							showTime : false,--%>
<%--							labelWidth : 150,--%>
<%--							width:150,--%>
<%--							labelAlign : 'left'--%>
<%--						});--%>
<%--					});--%>
<%--				</script>--%>
<%--			</div>--%>
<%--			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>--%>
<%--			</div>--%>
<%--			--%>
<%--			<div class="search-td">--%>
<%--			<div class="l-panel-search-item">--%>
<%--				<input type="text" id="success_date_end" name="success_date_end" value="${success_date_end}"/>--%>
<%--				<script type="text/javascript">--%>
<%--					$(function() {--%>
<%--						$("#success_date_end").ligerDateEditor( {--%>
<%--							showTime : false,--%>
<%--							labelWidth : 150,--%>
<%--							width:150,--%>
<%--							labelAlign : 'left'--%>
<%--						});--%>
<%--					});--%>
<%--				</script>	--%>
<%--			</div>--%>
<%--			</div>--%>
<%--			--%>
<%--			<div style="display: inline-flex;">--%>
<%--				<div id="searchbtn" style="height: 28px;line-height: 28px;">--%>
<%--					统计--%>
<%--				</div>--%>
<%--				<div style="padding-left: 10px;">--%>
<%--					<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="导出" id="export">--%>
<%--				</div>--%>
<%--			</div>--%>
<%--		</div>--%>
	<div class="search-tr">
		<div class="search-td" style="width: 40%;">
			<div class="search-td-label" style="float: left;width: 20%;">退款日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="success_date_begin" name="success_date_begin" class="dateEditor" value="${success_date_begin}" style="width: 135px;" />
			</div>
			<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
			<div class="l-panel-search-item">
				<input type="text" id="success_date_end" name="success_date_end" class="dateEditor" value="${success_date_end}" style="width: 135px;" />
			</div>
		</div>
		<div class="search-td-search-btn" style="display: inline-flex;" >
			<div id="searchbtn" >统计</div>
			<div style="padding-left: 10px;">
				<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="导出" id="export" >
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