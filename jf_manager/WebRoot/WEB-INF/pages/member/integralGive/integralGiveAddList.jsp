<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
	
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
	
<script type="text/javascript">
	$(function() {
		$(function() {
			$(".dateEditor").ligerDateEditor( {
				showTime : false,
				labelAlign : 'left'
			});
		 });
	});
	
	//会员资料
	function viewDetail(id) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 200,
			title: "会员资料",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/member/info/detail.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//子订单详情
	function subOrderCode(id) {
		$.ligerDialog.open({
	 		height: $(window).height(),
			width: $(window).width()-50,
			title: "子订单详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/order/sub/detail.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//查看、添加、审核
	function addOrShowOrAuditIntegralGive(id, statusFlag) {
		var title = "";
		var url = "";
		if(statusFlag == 1) { //查看
			title = "查看积分赠送";
			url = "${pageContext.request.contextPath}/member/integralGive/integralGiveShow.shtml?id="+id;
		}else if(statusFlag == 2) { //添加
			title = "添加积分赠送";
			url = "${pageContext.request.contextPath}/member/integralGive/integralGiveAdd.shtml?id="+id;
		}else if(statusFlag == 3) { //审核
			title = "审核积分赠送";
			url = "${pageContext.request.contextPath}/member/integralGive/integralGiveCheck.shtml?id="+id;
		}
		$.ligerDialog.open({
	 		height: $(window).height()-50,
			width: $(window).width()-50,
			title: title,
			name: "INSERT_WINDOW",
			url: url,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	// 删除
	function deleteIntegralGive(id) {
		$.ligerDialog.confirm("是否删除？", function (yes) {
			if(yes) {
				$.ajax({
					url : "${pageContext.request.contextPath}/member/integralGive/deleteIntegralGive.shtml",
					data : {id : id},
					dataType : 'json',
					success : function(data) {
						if(data == null || data.statusCode != 200) {
							commUtil.alertError(data.message);
						}else{
							listModel.gridManager.reload();
						}
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			}
		});
	}

	var listConfig = {
		url:"/member/integralGive/integralGiveList.shtml",
		btnItems:[
	    			{text: '添加', icon: 'add', click: function() {
	    				addOrShowOrAuditIntegralGive('', 2);
	    			    return;
	    			  }
	    			}        
	    	      ],
		listGrid:{ columns: [  
                   {display:'创建日期',name:'createDate', align:'center', isSort:false, render:function(rowdata, rowindex) {
                      	if(rowdata.createDate == null || rowdata.createDate == undefined) {
							return "";
						}else{
							var createDate = new Date(rowdata.createDate);
							return createDate.format("yyyy-MM-dd");
						}
                   }},
                   {display:'审核日期',name:'updateDate', align:'center', isSort:false, render:function(rowdata, rowindex) {
                      	if(rowdata.updateDate == null || rowdata.updateDate == undefined) {
							return "";
						}else{
							var updateDate = new Date(rowdata.updateDate);
							return updateDate.format("yyyy-MM-dd");
						}
                   }},
                   {display:'会员信息',name:'groupName', align:'center', width:180, isSort:false, render:function(rowdata, rowindex) {
                   		if (rowdata.type == 2) {
                      		return rowdata.groupName;
                      	}else if(rowdata.type == 1) {
                      		return rowdata.typeDesc;
                      	}else if(rowdata.type == 3) {
                      		if(rowdata.groupId != '' && rowdata.groupId != null ) {
                      			var str = '';
	                      		var groupIds = new Array();
	                      		groupIds = rowdata.groupId.split(",");
	                      		for(var i=0;i<groupIds.length;i++) {
	                      			if(groupIds[i] != '') {
	                      				if(str == '') {
	                      					str = "<a href=\"javascript:viewDetail(" + groupIds[i] + ");\">" + groupIds[i] + "</a>";
	                      				}else {
	                      					str += "、<a href=\"javascript:viewDetail(" + groupIds[i] + ");\">" + groupIds[i] + "</a>"
	                      				}
	                      			}
	                      		}
	                      		return str;
                      		}
                      		return '';
                      	}else {
                      		if(rowdata.subOrderId != null ) {
	                      		return "<a href=\"javascript:subOrderCode(" + rowdata.subOrderId + ");\">" + rowdata.subOrderCode + "</a>";
                      		}else {
	                      		return rowdata.subOrderCode;
                      		}
                      	}
                  }},
                  {display:'赠送数量',name:'integral', align:'center', isSort:false},
                  {display:'总成本',name:'memberCountSum', align:'center', isSort:false, render:function(rowdata, rowindex) {
                	    if(rowdata.auditStatus == '1' || rowdata.auditStatus == '3') {
                	    	return rowdata.memberCount*rowdata.integral/100;
                	    }else {
	                    	return rowdata.memberCountSum*rowdata.integral/100;
                	    }
                  }},
                  {display:'赠送理由',name:'remarks', align:'center', isSort:false},
                  {display:'商家序号',name:'mchtCode', align:'center', isSort:false},
                  {display:'店铺名称',name:'shopName', align:'center', isSort:false},
                  {display:'预设承担方',name:'costBearDesc', align:'center', isSort:false},
                  {display:'保证金扣款',name:'isDepositDeductDesc', align:'center', isSort:false},
                  {display:'状态',name:'auditStatusDesc', align:'center', isSort:false},
                  {display:'操作',name:'auditOrRetrial', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
						var html = [];
						html.push("<a href=\"javascript:addOrShowOrAuditIntegralGive(" + rowdata.id + ", 1);\">查看</a>");
						if(rowdata.auditStatus == '0' || rowdata.auditStatus == '2') {
							html.push("&nbsp;&nbsp;<a href=\"javascript:deleteIntegralGive(" + rowdata.id + ");\">删除</a>");
						}
					    return html.join("");
				  }}
               ],   
               showCheckbox : false,  //不设置默认为 true
               showRownumber:true
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
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label">创建日期：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" class="dateEditor" id="startCreateDate" name="startCreateDate" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-combobox-condition" >
						<input type="text" class="dateEditor" id="endCreateDate" name="endCreateDate" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">审核日期：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" class="dateEditor" id="startUpdateDate" name="startUpdateDate" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-combobox-condition" >
						<input type="text" class="dateEditor" id="endUpdateDate" name="endUpdateDate" >
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label">审核状态：</div>
					<div class="search-td-combobox-condition" >
						<select  id="auditStatus" name="auditStatus" style="width: 135px;">
							<option value="">请选择...</option>
							<c:forEach var="auditStatus" items="${auditStatusList}">
								<option value="${auditStatus.statusValue}">${auditStatus.statusDesc}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">保证金扣款：</div>
					<div class="search-td-combobox-condition" >
						<select  id="isDepositDeduct" name="isDepositDeduct" style="width: 135px;">
							<option value="">请选择...</option>
							<c:forEach var="isDepositDeduct" items="${isDepositDeductList}">
								<option value="${isDepositDeduct.statusValue}">${isDepositDeduct.statusDesc}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">子订单号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="subOrderCode" name="subOrderCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
				<div class="search-td-search-btn">
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</body>
</html>


