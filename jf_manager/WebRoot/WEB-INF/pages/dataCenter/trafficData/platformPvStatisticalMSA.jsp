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
 	.tab-1{
		width: 100%;
		height: 57px;
		background: inherit;
	    background-color: inherit;
		background-color: rgba(242, 242, 242, 1);
		box-sizing: border-box;
		border-width: 1px;
		border-style: solid;
		border-color: rgba(204, 204, 204, 1);
		border-radius: 0px;
		-moz-box-shadow: none;
		-webkit-box-shadow: none;
		box-shadow: none;
		line-height: 16px;
 	}
 	.tab-1 td:last-child{
 		width: 70%;
 	}
 	.tab-1 td:nth-child(1), .tab-1 td:nth-child(2), .tab-1 td:nth-child(3){
 		width: 10%;
 		text-align: center; 
		background: inherit;
	    background-color: inherit;
		box-sizing: border-box;
		border-width: 1px;
		border-style: solid;
		border-color: rgba(204, 204, 204, 1);
		border-radius: 0px;
		-moz-box-shadow: none;
		-webkit-box-shadow: none;
		box-shadow: none;
		font-family: 'Arial Negreta', 'Arial Normal', 'Arial';
		font-weight: 700;
		font-style: normal;
		font-size: 20px;
		color: #0099FF;
		line-height: 16px;
		cursor: pointer;
 	}
 	#dataForm{
 		background-color: rgba(242, 242, 242, 1);
 	}
 	#searchbtn{
		height: 23px;
		overflow: hidden;
		width: 60px;
		line-height: 23px;
		cursor: pointer;
		position: relative;
		text-align: center;
		border: solid 1px #A3C0E8;
		color: #333333;
		background: #E0EDFF repeat-x center;
 	}
 	.p-title{
 		background-color: rgba(242, 242, 242, 1);
 		padding: 20px 0px 0px 20px;
 	}
 	.span1{
 		font-weight: 700;
		font-style: normal;
		font-size: 16px;
		text-align: left;
 	}
 	.span2{
 		color: rgba(0, 153, 255, 1);
 	}
 	#dataShow{
 		margin: 10px 20px; 
 		background-color: rgba(0, 153, 255, 1); 
 		height: 170px;
 	}
 	.div-tab{
 		padding-top: 20px;
 	}
 	.tab-2{
 		width: 100%;
 	}
 	.tab-2 td{
 		width: 10%;
 		text-align: center; 
		background: inherit;
	    background-color: inherit;
		box-sizing: border-box;
		-moz-box-shadow: none;
		-webkit-box-shadow: none;
		box-shadow: none;
		font-family: 'Arial Negreta', 'Arial Normal', 'Arial';
		font-weight: 400;
		font-style: normal;
		font-size: 16px;
		color: white;
		line-height: 32px;
 	}
 	.tab-2 tr:nth-child(2) td{
 		font-size: 28px;
 	}
 </style>
 <script type="text/javascript">
 	var statisticsBeginDate = '${statisticsBeginDate }';
 	var statisticsEndDate = '${statisticsEndDate }';
 	var clientSource = "";
 	$(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135,
			cancelable : false
		});
		
		// 禁止分页
		$("#maingrid").ligerGrid({
	        usePager:false
	    }); 
		
		/*$(".tab-1 td:nth-child(2)").css("background-color", "rgba(255, 255, 255, 1)");
		
		$(".tab-1 td:nth-child(1)").click(function(){
			window.location.href = "${pageContext.request.contextPath}/platformPvStatistical/platformPvStatisticalSumUp.shtml";
		});
		
		$(".tab-1 td:nth-child(3)").click(function(){
			window.location.href = "${pageContext.request.contextPath}/platformPvStatistical/platformPvStatisticalProduct.shtml";
		});*/
		
		$("#searchbtn").bind('click',function(){
			if($("#statisticsBeginDate").val() == '' || $("#statisticsEndDate").val() == '' ) {
				commUtil.alertError("日期不能为空！");	
				return;
			} 
			statisticsBeginDate = $("#statisticsBeginDate").val();
			statisticsEndDate = $("#statisticsEndDate").val();
			clientSource = $("#clientSource").val();
   			$("#search").click();
     	 });
	 });
 
 	 //详情
 	 function showDtl(pageClassify, totalPvCount, totalPvCountTourist) {
		 $.ligerDialog.open({
				height: $(window).height() - 50,
				width: $(window).width() - 50,
				title: "详情",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/platformPvStatistical/platformPvStatisticalDtl.shtml?pageClassify="
					+pageClassify+"&statisticsBeginDate="+statisticsBeginDate+"&statisticsEndDate="
					+statisticsEndDate+"&clientSource="+clientSource+"&totalPvCount="+totalPvCount+"&totalPvCountTourist="+totalPvCountTourist,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
 	 
 	 //贡献下游
 	 function downstreamPage(pageClassify, pageClassifyDesc) {
		 $.ligerDialog.open({
				height: $(window).height() - 200,
				width: $(window).width() - 600,
				title: "页面路径("+pageClassifyDesc+")",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/platformPvStatistical/platformPvStatisticalFlow.shtml?pageClassify="
						+pageClassify+"&statisticsBeginDate="+statisticsBeginDate+"&statisticsEndDate="+statisticsEndDate+"&clientSource="+clientSource,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
 	
 	 var listConfig={ 
	      url:"/platformPvStatistical/platformPvStatisticalMSAList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'页面类型',name:'page_classify_desc', align:'center', isSort:false, width:180},
							{display:'访问用户数(会员)',name:'member_count', align:'center', isSort:false, width:120},
							{display:'访问用户数(非会员)',name:'member_count_tourist', align:'center', isSort:false, width:120},
							{display:'购买用户数',name:'pay_member_count', align:'center', isSort:false, width:120},
			                {display:'访问次数（会员）',name:'total_pv_count', align:'center', isSort:false, width:120},
			                {display:'访问次数（非会员）',name:'total_pv_count_tourist', align:'center', isSort:false, width:120},
			                {display:'贡献下游',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
			                	var html = [];
								html.push("<a href='javascript:;' onclick='downstreamPage("+rowdata.page_classify+",\""+rowdata.page_classify_desc+"\");'>"+rowdata.member_pv_count+"</a>");
							    return html.join("");
			                }},
			                {display:'平均停留时长（秒）',name:'stay_time_avg', align:'center', isSort:false, width:180},
							{display:'操作',name:'', align:'center', isSort:false, width:160, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href='javascript:;' onclick='showDtl("+rowdata.page_classify+","+rowdata.total_pv_count+","+rowdata.total_pv_count_tourist+");'>【详情】</a>");
							    return html.join("");
							}}
			         ], 
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      }, 							
	      container:{
	        toolBarName:"toptoolbar",
	        searchBtnName:"search",
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }        
	  };
 </script>
</head>
<body style="padding: 0px; overflow: hidden;" >
	<%--<table class="tab-1">
		<tr>
			<td>历史概况</td>
			<td>历史页面分析</td>
			<td>历史商品概括</td>
			<td></td>
		</tr>
	</table>--%>
	<p class="p-title">
		<span class="span1" >
			<span class="span2" >|</span>
			页面分析总览
		</span>
	</p>
	<form id="dataForm" runat="server" >
		<div class="search-pannel" >
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >
						终端：
					</div>
					<div class="search-td-combobox-condition" >
						<select id="clientSource" name="clientSource" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="clientSourceObj" items="${clientSourceList }" >
								<option value="${clientSourceObj.statusValue }" >
									${clientSourceObj.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">统计日：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="statisticsBeginDate" name="statisticsBeginDate" value="${statisticsBeginDate }" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="statisticsEndDate" name="statisticsEndDate" value="${statisticsEndDate }" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
					<div id="search" style="display: none;">搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0; padding: 0"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
