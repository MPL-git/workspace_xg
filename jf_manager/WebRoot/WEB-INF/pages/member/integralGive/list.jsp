<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function gridRefresh() {
    if (listModel.gridManager) {
        var gridparms = [];
        gridparms.push({ name: "staff", value: "self" });
        listModel.gridManager.loadServerData(gridparms);
    }
}

function integralGiveAudit(id) {
	$.ligerDialog.open({
	height: $(window).height()-200,
	width: $(window).width()-500,
	title: "赠送会员金币审核",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/member/integralGive/audit.shtml?id=" +id,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
	});
}

/**
 * 查看赠送会员金币审核
 */
function getIntegralGive(id) {
	$.ligerDialog.open({
		height: $(window).height()-200,
		width: $(window).width()-500,
		title: "赠送会员金币审核",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/member/integralGive/getIntegralGive.shtml?id=" +id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

	  var listConfig={
		      url:"/member/integralGive/datalist.shtml",
		    
		      listGrid:{ columns: [  
			                    { display: 'ID', name: 'id'},
			                    { display: '会员',  name: 'typeDesc', render: function(rowdata, rowindex) {
			                    	if (rowdata.type==2){
			                    		return rowdata.groupName;
			                    	}else{
			                    		return rowdata.typeDesc;
			                    	}
			                    }},
			                    { display: '赠送金币',  name: 'integral'},
			                    { display: '详细说明',  name: 'remarks'},
			                    { display: '审核状态',  name: 'auditStatusDesc'},
			                    { display: '驳回原因',  name: 'auditRemarks'},
			                    { display: '提交人<a href=\"javascript:gridRefresh();\">我提交的信息</a>',  name: 'staffName'},
				                { display: '提交时间', name: 'createDate', render: function(rowdata, rowindex) {
		    	  					var date=new Date(rowdata.createDate);
		    	  					return date.getFullYear()+"-"+("0" + (date.getMonth() + 1)).slice(-2)+"-"+("0"+date.getDate()).slice(-2)+" "+("0"+date.getHours()).slice(-2)+":"+("0"+date.getMinutes()).slice(-2)+":"+("0"+date.getSeconds()).slice(-2);
		      					}},
				                { display: "操作", name: "OPER", align: "center", render: function(rowdata, rowindex) {
									var html = [];
									if (rowdata.auditStatus==0){
										html.push("<a href=\"javascript:integralGiveAudit(" + rowdata.id + ");\">审核</a>");
									} else {
										html.push("<a href=\"javascript:getIntegralGive(" + rowdata.id + ");\">查看</a>");
									}	 
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
			<div class="search-td-label">审核状态：</div>
				<div class="search-td-condition" >
				<select  id="status" name="status">
				<option value="">请选择</option>
				<c:forEach var="statusItem" items="${auditStatus}">
					<option value="${statusItem.statusValue}">${statusItem.statusDesc}
					</option>
				</c:forEach>
				</select>
			</div>
			</div>
			 	
			<div class="search-td">
			<div class="search-td-label">提交人：</div>
			<div class="search-td-condition" >
				<input type="text" id = "staffName" name="staffName" >
			</div>
			</div>
			 
			<div class="search-td">
			<div class="search-td-label">详细说明：</div>
			<div class="search-td-condition" >
				<input type="text" id = "remarks" name="remarks" >
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


