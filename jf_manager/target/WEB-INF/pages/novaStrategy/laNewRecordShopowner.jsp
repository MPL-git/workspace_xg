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

	 $(function() {
	  $(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
	  });
	 });
		
	//拉新记录
		function laNewRecord(id) {
			$.ligerDialog.open({
				height: $(window).height()-40,
				width:  $(window).width()-40,
				title: "拉新记录（店长ID/店长昵称）",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/theoryStatistics/laNewRecord.shtml?Id=" + id ,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}
			
		//分润记录
		function fenRunRecord(id) {
			$.ligerDialog.open({
				height: $(window).height()-40,
				width:  $(window).width()-40,
				title: "分润记录（店长ID/店长昵称）",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/theoryStatistics/fenRunRecord.shtml?Id=" + id ,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});				
		}
	
 	var listConfig={
	      url:"/salesmanManage/laNewRecordShopownerList.shtml?id="+${id},
	      listGrid:{ columns: [                    
	                        {display:'店长会员ID', name:'memberId', align:'center', isSort:false},
	                        {display:'店长会员昵称', name:'nick', align:'center', isSort:false},
	                        {display:'拉新用户', name:'laNewCount', align:'center', isSort:false},
	                        {display:'拉新分润', name:'fenrunTotal', align:'center', isSort:false, render: function (rowdata, rowindex) {
	 		                	if(rowdata.fenrunTotal){
		 		                	return formatMoney(rowdata.fenrunTotal,2);
	 		                	}else{
	 		                		return "0.00";
	 		                	}
	                        }},
	                        {display:'注册时间', name:'createDate', align:'center', isSort:false, render: function(rowdata, rowindex) {
	                        	var html = [];
	                        	if (rowdata.createDate != null && rowdata.createDate != '') {
									var createDate = new Date(rowdata.createDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
								
							}},
							{display:'升级时间', name:'payDate', align:'center', isSort:false, render: function(rowdata, rowindex) {
	                        	var html = [];
	                        	if (rowdata.payDate != null && rowdata.payDate != '') {
									var payDate = new Date(rowdata.payDate);
									html.push(payDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
								
							}},
	                        {display:'操作', name:'', align:'center', isSort:false, render: function(rowdata, rowindex) {
								var html = [];
									html.push("<a href=\"javascript:laNewRecord(" + rowdata.memberId + ");\">拉新记录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
									html.push("<a href=\"javascript:fenRunRecord(" + rowdata.memberId + ");\">分润记录</a>");
							    return html.join("");
							}}
			         ], 
	                 showCheckbox : false,  //不设置默认为 true
	                 showRownumber: true //不设置默认为 true
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
	<input type="hidden" id="pageSize" value="${pageSize}">
	<input type="hidden" id="pageNumber" value="${pageNumber}">
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td"  style="width:230px;">
					<div class="search-td-label">店长会员ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberId" name="memberId">
					</div>
				</div>
				<div class="search-td" style="width:230px;">
					<div class="search-td-label">店长会员昵称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="nick" name="nick">
					</div>
				</div>
					<div class="search-td" style="width: 30%;margin-bottom:-6px;">
						<div class="search-td-label" style="float: left;width: 20%;margin-top:2px;">升级日期</div>
						<div class="l-panel-search-item" >
							<input type="text" id="dealCompleteDateBegin" name="dealCompleteDateBegin" class="dateEditor" placeholder="请选择" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >—</div>
						<div class="l-panel-search-item">
							<input type="text" id="dealCompleteDateEnd" name="dealCompleteDateEnd" class="dateEditor" placeholder="请选择" style="width: 135px;" />
						</div>
					</div>
					<div class="search-td-search-btn" style="display: inline-flex;margin-right:220px;">
					<div id="searchbtn" class="l-button">搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
