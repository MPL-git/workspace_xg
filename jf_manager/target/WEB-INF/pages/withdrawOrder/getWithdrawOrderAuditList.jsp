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
 
 <style type="text/css">
 	#day, #month, #year{
 		width: 60px;
 		height: 25px;
 		line-height: 25px;
 		cursor: pointer;
 	}
 	#day, #month{
 		margin-right: 20px;
 	}
 		
 </style>
 <script type="text/javascript">
	 $(function() {
		 $(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
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
	 
	 //签到记录
	 function showMemberAccountDtl(memberAccountId) {
		 $.ligerDialog.open({
				height: $(window).height() - 50,
				width: $(window).width() - 300,
				title: "签到记录",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/memberSignIn/memberAccountDtlManager.shtml?accId="+memberAccountId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 //导出
	 function auditExcel(){
		 $("#dataForm").attr("action","${pageContext.request.contextPath}/withdrawOrder/exportWithdrawOrderAuditList.shtml");
		 $("#dataForm").submit();
	 }
	 
	 //批量审核
	 function auditWithdrawOrder(ids) {
		 var idSt = ids.split(",");
 		 if(idSt.length == 1) {
 			var idStr = idSt[0].split("-");
 			if(idStr[1] != '1') {
 				commUtil.alertError("只有申请中状态的才可审核！");
 				return;
 			}
 		 }
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "审核",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/withdrawOrder/auditWithdrawOrderManager.shtml?ids="+ids,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
 	 var listConfig={
	      url:"/withdrawOrder/getWithdrawOrderAuditList.shtml",
	      btnItems:[
					{text: '批量审核', icon: 'modify', click: function() {
						  var data = listModel.gridManager.getSelectedRows();
					    if (data.length == 0) {
					  	  $.ligerDialog.alert('请选择行');
					    }else {
					       var str = "";
					        $(data).each(function () {    
					      	  if(str==''){
					      		  str = this.id + "-" + this.status;
					      	  }else{
					      		  str += ","+ this.id + "-" + this.status;
					      	  }
					        });                                                      
					        auditWithdrawOrder(str);
					    }; 
					    return;
					}}
	               ],
	      listGrid:{ columns: [
							{display:'会员ID',name:'memberId', align:'center', isSort:false, width:80},
							{display:'会员名称',name:'memberNick', align:'center', isSort:false, width:100},
							{display:'电话号码',name:'memberMobile', align:'center', isSort:false, width:120},
							{display:'剩余可提现金额',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								return formatMoney(rowdata.memberAccountBalance, 2);
			                }},
			                {display:'已提现金额',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
								return formatMoney(rowdata.amountSum, 2);
			                }},
			                {display:'提现金额',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
								return formatMoney(rowdata.amount, 2);
			                }},
			                {display:'提现类型',name:'withdrawTypeDesc', align:'center', isSort:false, width:120},
			                {display:'提现名称',name:'withdrawCnfName', align:'center', isSort:false, width:180},
							{display:'申请时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								if (rowdata.createDate != null && rowdata.createDate != '') {
									var createDate = new Date(rowdata.createDate);
									return createDate.format("yyyy-MM-dd hh:mm:ss");
								} else {
									return "";
								}
							}},
							{display:'状态',name:'statusDesc', align:'center', isSort:false, width:120, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.withdrawMethod == 1 && (rowdata.status == 1 || rowdata.status == 2 || rowdata.status == 4 )) {
									html.push("<span style='color:red;'>"+rowdata.statusDesc+"(线下提现)</span>");
								}else {
									html.push(rowdata.statusDesc);
								}
								return html.join("");
			                }},
							{display:'是否有消费',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
								if(rowdata.combineOrderCount > 0) {
									return "是";
								}else {
									return "否";
								}
			                }},
							{display:'提现次数',name:'withdrawOrderCount', align:'center', isSort:false, width:100},
							{display:'操作',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								html.push("<a href='javascript:;' onclick='showMemberAccountDtl("+rowdata.accId+");'>【签到记录】</a>");
							    if(rowdata.status == '1') {
							    	html.push("<a href='javascript:;' onclick='auditWithdrawOrder(\""+rowdata.id+"-"+rowdata.status+"\");'>【审核】</a>");
							    }
								return html.join("");
			                }}
	      				],
	                 showCheckbox :true,  //不设置默认为 true
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
	<form id="dataForm" runat="server" method="post" >
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" >会员ID：</div>
					<div class="search-td-combobox-condition" >
				 		<input type="text" id="memberId" name="memberId" />
				 	</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginCreateDate" name="beginCreateDate" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endCreateDate" name="endCreateDate" class="dateEditor" />
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >是否消费：</div>
					<div class="search-td-combobox-condition" >
						<select id="expenseFlag" name="expenseFlag" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
				 	 </div>
				</div>
			</div>
			<div class="search-tr" >
				<div class="search-td">
					<div class="search-td-label" >是否有提现：</div>
					<div class="search-td-combobox-condition" >
						<select id="withdrawOrderFlag" name="withdrawOrderFlag" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >审核状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;" >
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
					<div class="search-td-label" >提现类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="withdrawType" name="withdrawType" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="withdrawType" items="${withdrawTypeList }">
								<option value="${withdrawType.statusValue }">
									${withdrawType.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >提现名称：</div>
					<div class="search-td-combobox-condition" >
						<select id="withdrawCnfId" name="withdrawCnfId" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="withdrawCnf" items="${withdrawCnfList }">
								<option value="${withdrawCnf.id }">
									${withdrawCnf.name }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
<%--				<div class="search-td-search-btn" >--%>
<%--					<div id="searchbtn" >搜索</div>--%>
<%--				</div>--%>
				<div class="search-td-search-btn" style="display: inline-flex;" >
					<div id="searchbtn" >搜索</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="导出" id="export" onclick="auditExcel();">
					</div>
				</div>
			</div>
		</div>
	</form>
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
