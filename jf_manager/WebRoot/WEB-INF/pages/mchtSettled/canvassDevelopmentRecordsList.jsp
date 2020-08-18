<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
 $(function(){
	 $("#export").bind('click',function(){
			var platContactStaffId = $("#platContactStaffId").val();
			var mchtSettledApplyStatus = $("#mchtSettledApplyStatus").val();
			var memberId = $("#memberId").val();
			var memberMobile = $("#memberMobile").val();
			var memberNick = $("#memberNick").val();
			var memberCreateDate = $("#memberCreateDate").val();
			console.log(memberCreateDate)
					console.log(memberMobile)
							console.log(memberNick)
			var memberEndDate = $("#memberEndDate").val();
			
			location.href="${pageContext.request.contextPath}/mchtSettled/detailsOfInvitingBusiness.shtml?platContactStaffId="+platContactStaffId+"&mchtSettledApplyStatus="+mchtSettledApplyStatus+"&memberId="+memberId+"&memberMobile="+memberMobile+"&memberNick="+memberNick+"&memberCreateDate="+memberCreateDate+"&memberEndDate="+memberEndDate;
		});
 }); 
 //批量或单个分配
function allot(ids) {
	$.ligerDialog.open({
 		height: 500,
		width: 300, 
		title: "招商对接人分配",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettled/allot/list.shtml?ids=" + ids,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
 

 
function viewMchtSettled(id) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "入驻申请审核进度",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettled/view.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}


	//查看申请进度
	function applicationProgress(mchtSettledApplyId){
		$.ligerDialog.open({
			height: 200,
			width: 400, 
			title: "入驻申请审核进度",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtSettled/statusConfirmation.shtml?mchtSettledApplyId=" + mchtSettledApplyId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
		
	}
	
	//查看申请进度
	function entryProgress(_this){
		var mchtCode = $(_this).attr("id");
		$.ligerDialog.open({
			height: 800,
			width: 1400, 
			title: "查看入驻信息",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtData/mchtInfoAudit.shtml?mchtCode="+mchtCode,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
		
	}


	
 var listConfig={
	  
     url:"/mchtSettled/canvassDevelopmentRecordsData.shtml", 
     listGrid:{ columns: [
						{ display: '会员ID', name:'id'},
			            { display: '会员昵称', width: 120, name:'nick'},
			            { display: '会员联系电话', width: 120, name:'mobile'},
			            { display: '会员注册时间', render: function(rowdata, rowindex) {
			            	var memCreateDate=new Date(rowdata.createDate);
			            	return memCreateDate.format("yyyy-MM-dd");
			         	}},
		                { display: '是否申请入驻', render: function(rowdata, rowindex) {
			            	var html = [];
			            	if(rowdata.mchtSettledApplyId==null ||rowdata.mchtSettledApplyId=='' ){
		                		html.push("否"); 
		                	}else{
		                		html.push("是"); 
		                	}
			            	return html.join("");
			         	}},
			            { display: '入驻申请审核进度', render: function(rowdata, rowindex) {
			             	var html = [];
			            	if(rowdata.mchtSettledApplyId==null ||rowdata.mchtSettledApplyId=='' ){
		                		html.push(""); 
		                	}else{
		                
		                		var mchtCompanyList = rowdata.mchtCompany;
		                		var mchtSettledApplyIdList =  rowdata.mchtSettledApplyId;
		                		var mchtCompanyArr =  mchtCompanyList.split(",");
		                		var mchtSettledApplyIdArr = mchtSettledApplyIdList.split(",");
		                		for(var i=0;i<mchtCompanyArr.length;i++){
		                			html.push(mchtCompanyArr[i]);
		                			html.push('<a href="javascript:applicationProgress(' +mchtSettledApplyIdArr[i]+' );">【查看申请进度】</a><br>')
		                		}
		           /*      		
		                		html.push(rowdata.mchtCompany);
			            		html.push('<a href="javascript:applicationProgress(' +rowdata.mchtSettledApplyId+' );">【查看申请进度】</a>') */
		                	}
			            	return html.join(""); 
			         	}},
		                { display: '商家提交入驻进度', render: function(rowdata, rowindex) {
		                	var html = [];
		                	if(rowdata.mchtCode==null ||rowdata.mchtCode=='' ){
		                		html.push(""); 
		                	}else{
		                		var mchtCodeList = rowdata.mchtCode;
		                		var mchtCompanyNotNullList=rowdata.mchtCompanyNotNull
		                		var mchtCodeArr = mchtCodeList.split(",");
		                		var mchtCompanyNotNullArr = mchtCompanyNotNullList.split(",");
		                		for(var i =0 ; i< mchtCompanyNotNullArr.length ;i++){
		                			html.push(mchtCompanyNotNullArr[i]);
		                			html.push('<a id='+mchtCodeArr[i]+' href="javascript:entryProgress('+mchtCodeArr[i]+');">【查看入驻进度】</a><input style="display:none" value="'+mchtCodeArr[i]+'" /><br>')
		                		}

		                	}
			            	return html.join("");
			         	}},
		            
		                { display: '所属专员',render:function(rowdata, rowindex){
		            		var html = [];
			            	if (rowdata.zsName==null || rowdata.zsName==''){
			            		html.push(""); 
			            	}else{
			            		var zsNameList =  rowdata.zsName;
		                		var zsNameArr =  zsNameList.split(",");
		                		for(var i =0 ; i< zsNameArr.length ;i++){
		                			html.push(zsNameArr[i]+"<br>");
		                		}
			            		
			            	}
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
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label" >会员ID：</div>
					<div class="search-td-condition">
					<input name = "memberId" id="memberId" >
					</div>
				</div>
					
					
				<div class="search-td">
				<div class="search-td-label" style="float:left;">会员联系电话：</div>
				<div class="search-td-condition" >
				<input name = "memberMobile" id="memberMobile" >
			 	 </div>
				 </div>
					
				<div class="search-td">
				<div class="search-td-label" style="float:left;">会员昵称：</div>
				<div class="search-td-condition" >
				<input name = "memberNick"  id = "memberNick">
			 	 </div>
				 </div>
				 
			<div class="search-td">
					<div class="search-td-label" >对接人：</div>
					<div class="search-td-condition">
						<select id="platContactStaffId" name="platContactStaffId">
							<c:if test="${isManagement == 1}">
								<option value="" selected="selected">全部员工</option>
							</c:if>
							<option value="${staffID}" selected="selected" >我自己</option>
							<c:forEach items="${sysStaffInfoCustomList}" var="sysStaffInfoCustom">
								<option value="${sysStaffInfoCustom.staffId}">${sysStaffInfoCustom.staffName}</option>
							</c:forEach>
						</select>
					</div>
				</div>

			</div>
		
		
		
		
		

			<div class="search-tr">		
					
				<div class="search-td">
				<div class="search-td-label" style="float:left;">已注册成商家：</div>
				<div class="search-td-condition" >
					<select id="mchtSettledApplyStatus" name="mchtSettledApplyStatus" style="width: 150px;">
						<option value="">请选择</option>
						<option value="1">是</option>
						<option value="2">否</option>
					</select>
			 	 </div>
				 </div>
				 
				 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">注册时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="memberCreateDate" name="memberCreateDate" />
				<script type="text/javascript">
					$(function() {
						$("#memberCreateDate").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							formate:"yyyy-MM-dd",
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="memberEndDate" name="memberEndDate" />
				<script type="text/javascript">
					$(function() {
						$("#memberEndDate").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							formate:"yyyy-MM-dd",
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
					
				<div class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn">搜索</div>
					<div style="padding-left: 10px;">
						<input type="button"
							style="width: 50px;height: 25px;cursor: pointer;" value="导出"
							id="export">
					</div>
				</div>

			</div>

		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>