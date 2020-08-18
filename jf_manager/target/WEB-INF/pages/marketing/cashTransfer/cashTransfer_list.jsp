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
	
	$(".dateEditor").ligerDateEditor({
		showTime : true,
		format : "yyyy-MM-dd",
		labelAlign : 'left',
		width : 150
	});
	
	$("#export").bind('click', function(){
		$("#dataForm").attr("action", "${pageContext.request.contextPath}/marketing/cashTransfer/dataExport.shtml");
		$("#dataForm").submit();
	});
	
});

function changeStatus(ids, amount, alipayAccount) {
	var title = "";
	if(alipayAccount) {
		title = "确认已经将"+amount+"元转账到支付宝账号"+alipayAccount+"？";
	}else {
		title = "请确认批量转账？";
	}
	$.ligerDialog.confirm(title, function (yes) {
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/marketing/cashTransfer/changeStatus.shtml?ids=" + ids,
				secureuri : false,
				dataType : 'json',
				cache : false,
				async : false,
				success : function(data) {
					if ("0000" == data.returnCode) {
						listModel.gridManager.reload();
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
	});
}

// 驳回
function updateStatus(id) {
	$.ligerDialog.open({
		height: 400,
		width: 500,
		title: "线下提现驳回",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/marketing/cashTransfer/updateStatusManager.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

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

function toMemberCumulativeSignInList(memberId) {
	$.ligerDialog.open({
		height: $(window).height()*0.9,
		width: $(window).width()*0.9,
		title: "累签记录",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/marketing/memberCumulativeSignIn/list.shtml?memberId=" + memberId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

 var listConfig={
     url:"/marketing/cashTransfer/data.shtml",
     btnItems:[{text: '批量转账',icon: 'modify',click: function() {
		  	var data = listModel.gridManager.getSelectedRows();
        	if (data.length == 0) {
        		commUtil.alertError('请选择行！');
        	}else {
           		var str = "";                         
            	$(data).each(function() { 
            		if(this.status == '0') {
	          	  		if(str=='') {
	          		  		str = this.id ;
	          	  		}else {
	          		  		str += ","+ this.id ;
	          	  		}
            		}
            	});
            	if(str != '') {
	            	changeStatus(str, null, null); 
            	}else{
            		commUtil.alertError('请选择未转账的数据！');
            	}
        	}
		}}
		],
     listGrid:{ columns: [
			            { display: '会员ID', name:'memberId'},
			            { display: '会员名称', name:'memberNick'},
			            { display: '姓名', name:'realName'},
			            { display: '支付宝账号',name:'alipayAccount'},
			            { display: '提现类型',name:'withdrawTypeDesc'},
		                { display: '提现金额',name:'amount',align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
							return formatMoney(rowdata.amount, 2);
		                }},
		                { display: '申请时间',name:'createDate', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
							if (rowdata.applyCreateDate) {
								var applyCreateDate = new Date(rowdata.applyCreateDate);
								return applyCreateDate.format("yyyy-MM-dd hh:mm:ss");
							} else {
								return "";
							}
						}},
		                { display: '转账时间',name:'updateDate', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
							if (rowdata.updateDate && rowdata.status != '2') {
								var updateDate = new Date(rowdata.updateDate);
								return updateDate.format("yyyy-MM-dd hh:mm:ss");
							} else {
								return "";
							}
						}},
		                { display: '状态',name:'statusDesc', width:100, render:function(rowdata, rowindex) {
							var html = [];
		                	if (rowdata.status == '2') {
		                		html.push("<span style='color: red;'>"+ rowdata.statusDesc +"</span>");
		                	}else {
		                		html.push(rowdata.statusDesc);
							}
		                	return html.join("");
						}},
		                { display: '处理',render: function (rowdata, rowindex) {
		                	var html = [];
			            	if(rowdata.status == '0' && ${auditPower } ){
			            		html.push("<a href='javascript:void(0);' onclick=\"changeStatus(" + rowdata.id + "," + rowdata.amount + ",'" + rowdata.alipayAccount + "');\">转账</a>");
			            		html.push("<span style='color: blue;margin-left: 5px;margin-right: 5px;'>|</span>");
			            		html.push("<a href='javascript:void(0);' onclick='updateStatus(" + rowdata.id + ",);'>驳回</a>");
		                	}else{
		                		html.push("/");
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
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<c:if test="${auditPower }">
		<div id="toptoolbar"></div>
	</c:if>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">申请时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="createDateBegin" name="createDateBegin" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="createDateEnd" name="createDateEnd" class="dateEditor" />
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">转账时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="updateDateBegin" name="updateDateBegin" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="updateDateEnd" name="updateDateEnd" class="dateEditor" />
					</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label" >会员ID：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="memberId" name="memberId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >状态:</div>
					<div class="search-td-combobox-condition">
						<select id="status" name="status" style="width: 135px;" >
							<option value="">请选择</option>
							<option value="0">待处理</option>
							<option value="1">已转账</option>
							<option value="2">已驳回</option>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >支付宝账号：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="alipayAccount" name="alipayAccount" >
					</div>
				</div>
				<div class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" >搜索</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 50px;height: 25px;cursor: pointer;" value="导出" id="export">
					</div>
				</div>
			</div>
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>