<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <script type="text/javascript">
	 $(function() {
		$(".dateEditor").ligerDateEditor( {
			showTime : true,
			format: "yyyy-MM-dd hh:mm:ss",
			labelAlign : 'left',
		});
		
		$("#export").bind('click', function(){
			var batchNum = $("#batchNum").val();
			var channel = $("#channel").val();
			var code = $("#code").val();
			var isExchange = $("#isExchange").val();
			var memberCouponStatus = $("#memberCouponStatus").val();
			var createDateStart = $("#createDateStart").val();
			var createDateEnd = $("#createDateEnd").val();
			location.href="${pageContext.request.contextPath}/couponExchangeCode/exportCouponExchangeCodeList.shtml?couponId=${couponId}&batchNum="+batchNum
					+"&channel="+channel+"&code="+code+"&isExchange="+isExchange+"&memberCouponStatus="+memberCouponStatus
					+"&createDateStart="+createDateStart+"&createDateEnd="+createDateEnd;
		});
		
	 });
	 
 	 var listConfig={
	      url:"/couponExchangeCode/getCouponExchangeCodeList.shtml?couponId=${couponId}",
	      btnItems:[{ text: '游离码作废', icon: 'delete', dtype:'win',  click: itemclick, url:'/couponExchangeCode/couponExchangeCodeManager.shtml?couponId=${couponId}&statusPage=2', seqId:"", width : 700, height:400}],
	      listGrid:{ columns: [
							{display:'游离码ID',name:'id', align:'center', isSort:false, width:80},
							{display:'渠道',name:'channelDesc', align:'channelDesc', isSort:false, width:180},
	                        {display:'号码',name:'code', align:'center', isSort:false, width:180},
	                        {display:'是否兑换',name:'isExchangeDesc', align:'center', isSort:false, width:100},
	                        {display:'是否使用',name:'memberCouponStatusDesc', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
	                        	if(rowdata.memberCouponId == null) {
	                        		return "未使用";
	                        	}else {
	                        		return rowdata.memberCouponStatusDesc;
	                        	}
	                        }},
	                        {display:'兑换条件',name:'exchangeLimitDesc', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
	                        	if(rowdata.exchangeLimit == null) {
	                        		return "";
	                        	}else {
	                        		return rowdata.exchangeLimitDesc;
	                        	}
	                        }},
	                        {display:'生成批次',name:'batchNum', align:'center', isSort:false, width:180},
	                        {display:'生成时间',name:'createDate', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	var html = [];
								if(rowdata.createDate != null && rowdata.createDate != "") {
									var createDate = new Date(rowdata.createDate);
									if(new Date(rowdata.createDate) <= new Date) {
										html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
									}
								}
							    return html.join("");
	                        }},
	                        {display:'备注',name:'remarks', align:'center', isSort:false, width:180}
			         ], 
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      } , 							
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label" >生成批次：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="batchNum" name="batchNum" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >渠道：</div>
					<div class="search-td-combobox-condition" >
						<select id="channel" name="channel" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="channel" items="${channelList }">
								<option value="${channel.statusValue }">
									${channel.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >号码：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="code" name="code" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >是否兑换：</div>
					<div class="search-td-combobox-condition" >
						<select id="isExchange" name="isExchange" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="isExchange" items="${isExchangeList }">
								<option value="${isExchange.statusValue }">
									${isExchange.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >是否使用：</div>
					<div class="search-td-combobox-condition" >
						<select id="memberCouponStatus" name="memberCouponStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="status" items="${statusList }">
								<option value="${status.statusValue }">
									${status.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >兑换条件：</div>
					<div class="search-td-combobox-condition" >
						<select id="exchangeLimit" name="exchangeLimit" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="exchangeLimit" items="${exchangeLimitList }">
								<option value="${exchangeLimit.statusValue }">
									${exchangeLimit.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="float:left;">生成时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" class="dateEditor" id="createDateStart" name="createDateStart" />
					</div>
					<div class="l-panel-search-item" ><span style="margin-left: 10px;">到</span></div>
				</div>
				<div class="search-td">
					<div class="l-panel-search-item">
						<input type="text" class="dateEditor" id="createDateEnd" name="createDateEnd" />
					</div>
				</div>
				<div class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" >搜索</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 50px;height: 25px;cursor: pointer;" value="导出" id="export">
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
