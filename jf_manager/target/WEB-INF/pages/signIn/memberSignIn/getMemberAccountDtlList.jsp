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
 
 <script type="text/javascript">
	 
	 $(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd hh:mm:ss",
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
	 
 	 var listConfig={
	      url:"/memberSignIn/getMemberAccountDtlList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'操作时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								if (rowdata.createDate != null && rowdata.createDate != '') {
									var createDate = new Date(rowdata.createDate);
									return createDate.format("yyyy-MM-dd hh:mm:ss");
								} else {
									return "";
								}
							}},
							{display:'会员名称',name:'memberNick', align:'center', isSort:false, width:180},
							{display:'电话号码',name:'memberMobile', align:'center', isSort:false, width:180},
							{display:'类型',name:'bizTypeDesc', align:'center', isSort:false, width:180},
							{display:'本次金额',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
	 		                	var html = [];
	 		                	if(rowdata.bizType == '4' || rowdata.bizType == '7') {
	 		                		html.push(formatMoney(rowdata.freezeAmount, 2));
	 		                	}else {
									var amount = "0.00";
									if(rowdata.amount){
										if(rowdata.tallyMode == '2') {
											amount = "<span style='color:red;'>"+formatMoney(rowdata.amount, 2)+"</span>";
										}else {
											amount = formatMoney(rowdata.amount, 2);
										}
		 		                	}
									html.push(amount);
	 		                	}
								return html.join("");
			                }},
							{display:'当前余额',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
	 		                	var balance = "<span style='color:green;'>0.00</span>";
								if(rowdata.balance){
									balance = "<span style='color:green;'>"+formatMoney(rowdata.balance, 2)+"</span>";
	 		                	}
								return balance;
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
		<input type="hidden" name="accId" value="${accId }" />
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >会员名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberNick" name="memberNick" >
					</div>
				</div>
			 	<div class="search-td">
					<div class="search-td-label" >类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="bizType" name="bizType" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="bizType" items="${bizTypeList }">
								<option value="${bizType.statusValue }">
									${bizType.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">操作时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginCreateDate" name="beginCreateDate" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endCreateDate" name="endCreateDate" class="dateEditor" />
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >会员电话：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberMobile" name="memberMobile" >
					</div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
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
