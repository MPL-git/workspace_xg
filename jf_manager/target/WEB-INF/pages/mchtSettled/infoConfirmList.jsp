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
			var date_begin = $("#date_begin").val();
			var date_end = $("#date_end").val();
			var status = $("#status").val();
			location.href="${pageContext.request.contextPath}/mchtSettled/director/export.shtml?date_begin="+date_begin+"&date_end="+date_end+"&status="+status;
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
 
//修改状态操作
function changeStatus(id,status){
	var title;
	if(status == "0"){
		title = "确认取消黑名单吗？";
	}else if(status == "2"){
		title = "确认不合作吗？";
	}else if(status == "3"){
		title = "确认加入黑名单吗？";
	}else if(status == "4"){
		title = "确认确认合作吗?";
	}
    $.ligerDialog.confirm(title, function (yes)
    {
    	if(yes){
   		   $.ajax({ //ajax提交
   				type:'POST',
   				url:'${pageContext.request.contextPath}' +"/mchtSettled/changeStatus.shtml?id="+id+"&status="+status,
   				data:"",
   				dataType:"json",
   				cache: false,
   				success: function(json){
   				  if(json==null || json.statusCode!=200){
   				     commUtil.alertError(json.message);
   				  }else{
   	                 $.ligerDialog.success("操作成功",function() {
   	                            javascript:location.reload();
   						});
   				  }
   				},
   				error: function(e){
   				 commUtil.alertError("系统异常请稍后再试");
   				}
    	    });
   		} 
   });  
}
 
function confirmCooperate(id,status) {
	$.ligerDialog.open({
		height: $(window).height()*0.8,
		width: $(window).width()*0.3,
		title: "公司信息确认",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettled/confirmCooperate.shtml?id=" + id+"&status="+status,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toNotCooperate(id,status) {
	$.ligerDialog.open({
		height: $(window).height()*0.6,
		width: $(window).width()*0.5,
		title: "备注不合作原因",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettled/toNotCooperate.shtml?id=" + id+"&status="+status,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function joinInBlacklist(id,status) {
	$.ligerDialog.open({
		height: $(window).height()*0.3,
		width: $(window).width()*0.3,
		title: "加入黑名单",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettled/joinInBlacklist.shtml?id=" + id+"&status="+status,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function cancelTheBlacklist(id,status) {
	$.ligerDialog.open({
		height: $(window).height()*0.4,
		width: $(window).width()*0.4,
		title: "取消黑名单",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettled/cancelTheBlacklist.shtml?id=" + id+"&status="+status,
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
		title: "查看入驻信息",
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
	
 var listConfig={
	  
     url:"/mchtSettled/infoConfirm/data.shtml",
     listGrid:{ columns: [
			            { display: '入驻申请时间', width: 100, render: function(rowdata, rowindex) {
			            	var createDate=new Date(rowdata.createDate);
			            	return createDate.format("yyyy-MM-dd");
			         	}},
			            { display: '来源', width: 80,render: function(rowdata, rowindex) {
			            	if(rowdata.clientType == "1"){
			            		return "PC端";
			            	}else if(rowdata.clientType == "2"){
			            		return "招商H5";
			            	}else{
			            		return "PC端";
			            	}
			         	}},
			         	{ display: '入驻类型', width: 80,render: function(rowdata, rowindex) {
			            	var html = [];
			            	if(rowdata.settledType == "1"){
			            		html.push("企业公司");
			            	}else if(rowdata.settledType == "2"){
			            		html.push("个体商户");
			            	}
			            	return html.join("");
			         	}},
		                { display: '公司', render: function(rowdata, rowindex) {
		                	return '<a href="javascript:viewMchtSettled('+rowdata.id+');" >'+rowdata.companyName+'</a>';
			         	}},
			            { display: '注册资本', render: function(rowdata, rowindex) {
			            	if (rowdata.regCapital==null){
			            		return "/";
			            	}else{
			            		return rowdata.regCapital+"万"+rowdata.regFeeTypeDesc;
			            	}
			         	}},
		                { display: '主营类目', name: 'productTypeMain'},
		                { display: '品牌', name: 'productBrandMain'},
		                { display: '联系人(职务)',  name: '',render:function(rowdata, rowindex){
		                   var html=[];
		                   if (rowdata.contactName!=null) {
							   html.push(rowdata.contactName);
							  if (rowdata.contactJob!=null) {
							   html.push("(");
							   html.push(rowdata.contactJob);
							   html.push(")");					   								
							}
						}
		                   return html.join("");
		                }},
		                { display: '联系电话',  name: 'phone'},
		                { display: '专员确认状态', render: function(rowdata, rowindex) {
			            	if (rowdata.status=="4"){
			            		return "<span style='color:blue;'>"+rowdata.statusDesc+"</span>";
			            	}else{
			            		return rowdata.statusDesc;
			            	}
			         	}},
 		                { display: '主管确认状态', render: function(rowdata, rowindex) {
			            	if (rowdata.status=="1"){
			            		return "<span style='color:blue;'>"+rowdata.depositConfirmStatusDesc+"</span>";
			            	}else{
			            		return rowdata.depositConfirmStatusDesc;
			            	}
			         	}}, 
 		                { display: '保证金方式', render: function(rowdata, rowindex) {
			            	if (rowdata.contractDeposit && rowdata.depositType){
			            		return "保证金："+rowdata.contractDeposit+"元<br>"+rowdata.depositTypeDesc;
			            	}else if(rowdata.depositType){
			            		return rowdata.depositTypeDesc;
			            	}else{
			            		return "";
			            	}
			         	}}, 
		                { display: '商品情况', name: 'productSituation'},
		                { display: '商家其他渠道链接', name: 'otherChannelLink'},
		                { display: '物流配送', name: 'logistics'},
		                { display: '团队情况', name: 'teamSituation'},
		                { display: '公司概况', name: 'companySituation'},
		                { display: '操作', width: 150,name: "OPER", align: "center", render: function(rowdata, rowindex) {
		                	var role107 = ${role107};
		                	if(role107){
							    	return "<a href=\"javascript:confirmCooperate(" + rowdata.id + ",4);\">【确认合作】</a><a href=\"javascript:toNotCooperate(" + rowdata.id + ",2);\">【不合作】</a><br><a href=\"javascript:joinInBlacklist(" + rowdata.id + ",3);\">【加入黑名单】</a><a href=\"javascript:cancelTheBlacklist(" + rowdata.id + ",0);\">【取消黑名单】</a>";
		                	}else{
			                	if(rowdata.status == "0"){//未确认
							    	return "<a href=\"javascript:confirmCooperate(" + rowdata.id + ",4);\">【确认合作】</a><a href=\"javascript:toNotCooperate(" + rowdata.id + ",2);\">【不合作】</a><br><a href=\"javascript:joinInBlacklist(" + rowdata.id + ",3);\">【加入黑名单】</a>";
							    }else if(rowdata.status == "3"){//黑名单
							    	return "<a href=\"javascript:cancelTheBlacklist(" + rowdata.id + ",0);\">【取消黑名单】</a>";
							    }
		                	}
						}}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: true //不设置默认为 true
      } ,  							
     container:{
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
  };
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		
		<div class="search-tr"  > 
		
			<div class="search-td">
			<div class="search-td-label" style="float:left;">入驻申请日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="date_begin" name="date_begin" />
				<script type="text/javascript">
					$(function() {
						$("#date_begin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="date_end" name="date_end" />
				<script type="text/javascript">
					$(function() {
						$("#date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >公司名称：</div>
			<div class="search-td-condition" >
				<input type="text" id="companyName" name="companyName" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">来源：</div>
			<div class="search-td-condition" >
				<select id="sourceType" name="sourceType" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="list" items="${sourceTypes}">
						<option value="${list.statusValue}">${list.statusDesc}
						</option>
					</c:forEach>
					<option value="-1">PC端</option>
				</select>
		 	 </div>
			 </div>
		</div>
		
		
		<div class="search-tr"  >
			<div class="search-td">
			<div class="search-td-label" style="float:left;">专员确认状态：</div>
			<div class="search-td-condition" >
				<select id="status" name="status" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="list" items="${applyStatus}">
						<option value="${list.statusValue}" <c:if test="${list.statusValue eq status}">selected="selected"</c:if>>${list.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	</div>
			</div>
			 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">主管确认状态：</div>
			<div class="search-td-condition" >
				<select id="depositConfirmStatus" name="depositConfirmStatus" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="list" items="${depositConfirmStatusList}">
						<option value="${list.statusValue}">${list.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			<c:if test="${isManagement eq '1' && OrgId25 eq '25'}"> 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">对接人：</div>
			<div class="search-td-condition" >
				<select id="platformContactId" name="platformContactId" style="width: 150px;">
					<option value="-1">全部商家</option>
					<c:forEach var="platformContactCustom" items="${platformContactCustoms}">
						<option value="${platformContactCustom.id}">${platformContactCustom.staffName}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 </c:if>
			 
			 <div class="search-td">
				<div class="search-td-label" style="float:left;">入驻类型：</div>
				<div class="search-td-condition" >
					<select id="settledType" name="settledType" style="width: 150px;">
						<option value="">请选择</option>
						<option value="1">企业公司</option>
						<option value="2">个体商户</option>
					</select>
			 	 </div>
			 </div>
			 			
			<div class="search-td-search-btn" style="display: inline-flex;">
				<div id="searchbtn" >搜索</div>
				<!-- 
				<div style="padding-left: 10px;">
					<input type="button" style="width: 50px;height: 25px;cursor: pointer;" value="导出" id="export">
				</div>
				 -->
			</div>
			
		</div>
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>