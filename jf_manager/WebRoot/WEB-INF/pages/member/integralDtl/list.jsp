<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function integralGive(accId,memberId) {
	$.ligerDialog.open({
	height: $(window).height() - 200,
	width: $(window).width() - 500,
	title: "赠送会员金币",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/member/integralGive/forMember.shtml?accId=" + accId + "&memberId=" + memberId,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}
	  var listConfig={
		      url:"/member/integralDtl/datalist.shtml",
		    
		      listGrid:{ columns: [  
			                    { display: '日志ID', name: 'id'},
			                    { display: '会员ID',  name: 'memberId'},
			                    { display: '会员昵称',  name: 'nick'},
			                    { display: '积分类型',  name: 'tallyModeName', render: function(rowdata, rowindex) {
										if (rowdata.type==22 || rowdata.type==23){
											return rowdata.tallyModeName+"(积分转盘)";
										}else{
											return rowdata.tallyModeName;
										}
									}},
			                    { display: '数额',  name: 'integral', render: function(rowdata, rowindex) {
			                    	if (rowdata.tallyMode==1){
			                    		return "<font color=\"#009900\">"+rowdata.integral+"</font>";
			                    	}else{
			                    		return "<font color=\"#CC0000\">-"+rowdata.integral+"</font>";
			                    	}
			                    }},
			                    { display: '用户剩余积分',  name: 'balance'},
			                    { display: '详细说明', name: 'remark'},
				                { display: '订单号',  name: 'orderId'},
				                { display: '时间', name: 'createDate', render: function(rowdata, rowindex) {
		    	  					var date=new Date(rowdata.createDate);
		    	  					return date.getFullYear()+"-"+("0" + (date.getMonth() + 1)).slice(-2)+"-"+("0"+date.getDate()).slice(-2)+" "+("0"+date.getHours()).slice(-2)+":"+("0"+date.getMinutes()).slice(-2)+":"+("0"+date.getSeconds()).slice(-2);
		      					}},
				                { display: "操作", name: "OPER", align: "center", render: function(rowdata, rowindex) {
									var html = [];
									html.push("<a href=\"javascript:integralGive(" + rowdata.accId + ", " + rowdata.memberId + ");\">赠送积分</a>");
								    return html.join("");
							 	}}
				               ],   
		                 showCheckbox : false,  //不设置默认为 true
		                 showRownumber:true, dataAction: 'server' 
		      } ,     						
		     container:{
		        toolBarName:"toptoolbar",
		        searchBtnName:"searchbtn",
		        fromName:"dataForm",
		        listGridName:"maingrid"
		      } 
		       
		  };
</script>

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
	<div class="search-pannel">
		<div class="search-tr"  > 
		
			<div class="search-td">
			<div class="search-td-label">会员ID：</div>
			<div class="search-td-condition" >
				<input type="text" id = "memberId" name="memberId" >
			</div>
			</div>
			 	
			<div class="search-td">
			<div class="search-td-label">会员昵称：</div>
			<div class="search-td-condition" >
				<input type="text" id = "nick" name="nick" >
			</div>
			</div>
			 
			<div class="search-td">
			<div class="search-td-label">手机号码：</div>
			<div class="search-td-condition" >
				<input type="text" id = "mobile" name="mobile" >
			</div>
			</div>

			<div class="search-td">
			<div class="search-td-label">积分类型：</div>
			<div class="search-td-condition" >
				<select id="tallyModeType" name="tallyModeType">
					<option value="0" selected="selected">请选择</option>
					<option value="2">支出</option>
					<option value="1">收入</option>
					<option value="4">支出(积分转盘)</option>
					<option value="3">收入(积分转盘)</option>
				</select>
			</div>
			</div>
			
			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
			
		</div>
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</body>


