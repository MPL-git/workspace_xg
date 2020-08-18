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
	
	// 查看商家信息
		function viewMchtInfo(id) {
			$.ligerDialog.open({
				height: $(window).height() - 40,
				width: $(window).width() - 40,
				title: "商家基础资料",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mcht/mchtBaseInfoEdit.shtml?mchtId=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}
	
	// 查看商家联系人
		function viewMchtContact(id){
			$.ligerDialog.open({
				height: $(window).height(),
				width: $(window).width(),
				title: "商家联系人",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mcht/mchtContact.shtml?mchtId=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}
		
		function updatemchtexpres(id,contractType,name) {
			$.ligerDialog.open({
				height: 450,
				width: 550,
				title: name,
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mchtContract/mchtContractExprelist.shtml?Id=" + id+"&contractType="+contractType+"&isPlatformSend="+isPlatformSend ,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}
 	var listConfig={
	      url:"/mchtContract/contractReturnList.shtml",
	      listGrid:{ columns: [                    
	                        {display:'商家序号', name:'mchtCode', align:'center', isSort:false},
	                        {display:'招商对接人', name:'platfromContactName', align:'center', isSort:false},
	                        {display:'合同类型', name:'contractType', align:'center', render: function(rowdata, rowindex) {
	        			  		if(rowdata.contractType == "1"){
	        			  			return '合作合同';
	        			  		}
	        			  		if(rowdata.contractType == "2"){
	        			  			return '终止合同';
	        			  		}
	        			  	}},
	                        {display:'公司名称', name:'companyName', align:'center', isSort:false}, 
	                        {display:'店铺名称', name:'shopName', align:'center', isSort:false}, 
	                    	{ display: "查看信息", name: "OPER5", align: "center", render: function(rowdata, rowindex) {
	        			  		var html = [];
	        					html.push("<a href=\"javascript:viewMchtInfo(" + rowdata.mchtId + ");\">公司信息</a><br>");
	        					html.push("<a href=\"javascript:viewMchtContact(" + rowdata.mchtId + ");\">联系人信息</a><br>");
	        					return html.join("");
	        			  	}},
	        			  	{ display: '回寄地址信息', name: "returnAdress", render: function(rowdata, rowindex) {
	        			  		var html = [];
	        					html.push("收  件  人："+rowdata.contactName+"<br>");
	        					html.push("联系电话："+rowdata.mobile+"<br>");
	        					if(rowdata.address == null || rowdata.address == "null"){
	        						html.push("收件地址：<br>");
	        					}else{
	        						html.push("收件地址："+rowdata.address+"<br>");
	        					}
	        					
	        					return html.join("");
	        			  	}},
	        			  	{ display: '平台回寄状态', name: "returnAdress", align: "center", render: function(rowdata, rowindex) {
	        			  		var html = [];
	        			  		if(rowdata.isPlatformSend == "0"){
	        			  			html.push("<a href=\"javascript:updatemchtexpres(" + rowdata.id + ","+rowdata.contractType+",'填写寄回单号'"+");\">未寄回</a><br>");
	        			  		}else if(rowdata.isPlatformSend == "1"){
	        			  			html.push("已寄回<br>");
	        			  			html.push(rowdata.expressName+"："+rowdata.platformExpressNo+"<a href=\"javascript:updatemchtexpres(" + rowdata.id + "," + rowdata.contractType+",'修改寄回单号'"+");\">【修改】</a><br>");
	        			  		}        			  		
	        					return html.join("");
	        			  	}},
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
					<div class="search-td-label">商家序号</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode">
					</div>
				</div>
				<div class="search-td" style="width:230px;">
					<div class="search-td-label">名称</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="name" name="name">
					</div>
				</div>
				<div class="search-td" style="width:230px;">
					<div class="search-td-label">平台寄回状态</div>
					<div class="search-td-combobox-condition" >
						<select id="isPlatformSend" name="isPlatformSend" style="width: 135px;">
							<option value="">请选择</option>
							<option value="0" selected="selected">未寄回</option>
							<option value="1">已寄回</option>						
						</select>
					</div>
				</div>
				<div class="search-td" style="width:230px;">
					<div class="search-td-label">合同类型</div>
					<div class="search-td-combobox-condition" >
						<select id="contractType" name="contractType" style="width: 135px;">
							<option value="">请选择</option>
							<option value="1">合作合同</option>
							<option value="2">终止合同</option>						
						</select>
					</div>
				</div>
					<div class="search-td-search-btn" style="display: inline-flex;margin-right:400px;">
					<div id="searchbtn" class="l-button">搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
