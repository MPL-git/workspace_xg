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
	  
	  $("#export").bind('click',function(){
		  var memberId = $("#memberId").val();
		  var nick = $("#nick").val();
		  var status = $("#status").val();
		  var createDateBegin = $("#createDateBegin").val();
		  var createDateEnd = $("#createDateEnd").val();
		  location.href="${pageContext.request.contextPath}/newStart/export.shtml?memberId="+memberId+"&nick="+nick+"&status="+status+"&nick="+nick+"&createDateBegin="+createDateBegin+"&createDateEnd="+createDateEnd;
	  });

	  $("#checkAll").live('click',function(){
			 if($(this).attr("checked")){
				 $("input[name='withdrawOrderId']").each(function(){
					 $(this).attr("checked",true);
				 });
			 }else{
				 $("input[name='withdrawOrderId']").each(function(){
					 $(this).attr("checked",false);
				 });
			 }
		 });
		 $("input[name='withdrawOrderId']").live('click',function(){
			 if($(this).attr("checked")){
				 var selectedLength = $("input[name='withdrawOrderId']:checked").length;
				 var length = $("input[name='withdrawOrderId']").length;
				 if(selectedLength == length){
					 $("#checkAll").attr("checked",true);
				 }else{
					 $("#checkAll").attr("checked",false);
				 }
			 }else{
				 $("#checkAll").attr("checked",false);
			 }
		 });
		
	 });
		
	//审核(type:1审核 2重审)
	function audit(ids,type,yy) {
		$.ligerDialog.open({
			height: 600,
			width: 600,
			title: "审核",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/newStart/audit.shtml?ids=" + ids+"&type="+type+"&yy="+yy,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});				
	}
	
	//查看详情
	function detail(id,pageType) {
		$.ligerDialog.open({
			height: $(window).height()-40,
			width:  $(window).width()-300,
			title: "查看详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/theoryStatistics/detail.shtml?Id=" + id+"&pageType="+pageType,
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
			title: "分润记录",
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
	      url:"/newStart/getNewStartList.shtml",
	      btnItems:[{text: '批量审核', icon: 'modify', click: function() {
   			  var data = listModel.gridManager.getSelectedRows();
              if (data.length == 0)
            	  $.ligerDialog.alert('请选择行');
              else
              {
              	if(${cwStatus eq '1'}){
					$.ligerDialog.alert('当前用户无操作权限');
              		return;
			  	}
				  var str = "";
                  $(data).each(function ()
                  {
                	  if(str==''){
                		  str = this.id ;
                	  }else{
                		  str += ","+ this.id ;
                	  }
                  });
                  audit(str,1,1);
              }
              return;
  			}},
  			{ line:true },
  		  ],
	      listGrid:{ columns: [                    
	                        {display:'会员ID', name:'memberId', align:'center', isSort:false},
	                        {display:'会员昵称', name:'nick', align:'center', isSort:false},
	                        {display:'提现前余额', name:'novaBalance', align:'center', isSort:false, render: function (rowdata, rowindex) {
	 		                	if(rowdata.novaBalance){
		 		                	return formatMoney(rowdata.novaBalance,2);
	 		                	}else{
	 		                		return "0.00";
	 		                	}
	                        }},
	                        {display:'提现金额', name:'amount', align:'center', isSort:false, render: function (rowdata, rowindex) {
	 		                	if(rowdata.amount){
		 		                	return formatMoney(rowdata.amount,2);
	 		                	}else{
	 		                		return "0.00";
	 		                	}
	                        }},
	                        {display:'账户名', name:'realName', align:'center', isSort:false},
	                        {display:'银行卡号', name:'alipayAccount', align:'center', isSort:false},
	                        {display:'开户行', name:'branchName', align:'center', isSort:false},
	                        {display:'省份/城市', name:'branchName', align:'center', isSort:false, render: function (rowdata, rowindex) {       	
	 		                		return rowdata.provinceAreaName+rowdata.cityAreaName;
	                        }},
	                        {display:'申请时间', name:'createDate', align:'center', isSort:false, render: function(rowdata, rowindex) {
	                        	var html = [];
	                        	if (rowdata.createDate != null && rowdata.createDate != '') {
									var createDate = new Date(rowdata.createDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
								
							}},
	                        {display:'审核状态', name:'status', align:'center', isSort:false}, 
	                        {display:'操作', name:'', align:'center', isSort:false, render: function(rowdata, rowindex) {
								var html = [];
									html.push("<a href=\"javascript:fenRunRecord(" + rowdata.memberId + ");\">分润记录</a>&nbsp;&nbsp;&nbsp;");
									html.push("<a href=\"javascript:detail(" + rowdata.id + ",1"+");\">查看详情</a>&nbsp;&nbsp;&nbsp;");
									if(rowdata.status== "待财审" && ${cwStatus} != '1'){
										html.push("<a href=\"javascript:audit(" + rowdata.id + ",1,1"+");\">财审</a>");
									}
									if(rowdata.status=="财审不通过" && ${cwStatus} != '1'){
										html.push("<a href=\"javascript:audit(" + rowdata.id + ",2,1"+");\">重审</a>");
									}					
							    return html.join("");
							}}
			         ], 
	                 showCheckbox : true,  //不设置默认为 true
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
					<div class="search-td-label">会员ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberId" name="memberId">
					</div>
				</div>
				<div class="search-td" style="width:230px;">
					<div class="search-td-label">会员昵称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="nick" name="nick">
					</div>
				</div>
				<div class="search-td" style="width:230px;">
					<div class="search-td-label">审核状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;">
							<option value="">请选择</option>
							<option value="1">申请中</option>
							<option value="2" selected="selected">待财审</option>
							<option value="8">财审通过</option>						
							<option value="3">转账中</option>
							<option value="4">提现成功</option>
							<option value="5">审核不通过</option>
							<option value="6">财审不通过</option>
							<option value="7">提现失败</option>
						</select>
					</div>
				</div>
					<div class="search-td" style="width: 30%;margin-bottom:-6px;">
						<div class="search-td-label" style="float: left;width: 20%;margin-top:2px;">申请日期</div>
						<div class="l-panel-search-item" >
							<input type="text" id="createDateBegin" name="createDateBegin" class="dateEditor" placeholder="请选择" style="width: 135px;" />
						</div>
						<%--<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >--</div>--%>
						<div class="l-panel-search-item">
							<input type="text" id="createDateEnd" name="createDateEnd" class="dateEditor" placeholder="请选择" style="width: 135px;" />
						</div>
					</div>
					<div class="search-td-search-btn">
					<div id="searchbtn" class="l-button" style="float: left;">搜索</div>
						<div style="float: left; padding-left: 10px;">
							<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="导出Excel" id="export">
						</div>
					<%--<div id="export" class="l-button" style="float: left; padding-left: 10px;">导出Excel</div>--%>
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
