<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<style type="text/css">
	.color-s0,.color-fs1{color: #0000FF;}
	.color-s1,.color-fs2{color: #008000;}
	.color-s4{color: #333333;}
	.color-fs0{color: #000000;}
</style>
<script type="text/javascript">

	$(function(){
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left',
			labelwidth: 120,
			width:120
		});

	});

	function viewDetail(id) {
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width()-50,
			title: "母订单详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/order/combine/detail.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	function batchPassInvoice(ids) {
		$.ligerDialog.confirm("是否要批量处理？", function (yes) {
			if (yes) {
				$.ajax({
					url : "${pageContext.request.contextPath}/order/batchAuditCombineOrderInvoice.shtml?ids=" + ids,
					secureuri : false,
					dataType : 'json',
					cache : false,
					async : false,
					success : function(data) {
						if ("0000" == data.returnCode) {
							$("#searchbtn").click();
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

	function passInvoice(status, id) {
		$.ligerDialog.confirm("是否要处理？", function (yes) {
			if (yes) {
				$.ajax({
					url : "${pageContext.request.contextPath}/order/auditCombineOrderInvoice.shtml?id=" + id + "&status=" + status,
					secureuri : false,
					dataType : 'json',
					cache : false,
					async : false,
					success : function(data) {
						if ("0000" == data.returnCode) {
							$("#searchbtn").click();
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

	function submitRejectInvoice() {
		if(!$("#rejectReason").val().trim()){
			$.ligerDialog.error('驳回原因必填！');
			return;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/order/auditCombineOrderInvoice.shtml?id=" + $("#id").val() + "&status=2&rejectReason=" + $("#rejectReason").val().trim(),
			secureuri : false,
			dataType : 'json',
			cache : false,
			async : false,
			success : function(data) {
				if ("0000" == data.returnCode) {
					$("#searchbtn").click();
					$.ligerDialog.hide();
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}

	function rejectInvoice(id) {
		$("#id").val(id);
		$("#rejectReason").val("");
		$.ligerDialog.open({
			target: $("#rejectInvoiceModel"),
			title: '开票驳回',
			width: 600,
			height: 220,
			isResize: true,
			modal: true
		});
	}

	var listConfig={
		url:"/order/combineOrderInvoiceList.shtml",
		btnItems:[
			{text: '批量处理', icon: 'modify', click: function() {
					var data = listModel.gridManager.getSelectedRows();
					if (data.length == 0)
						$.ligerDialog.alert('请选择行');
					else
					{
						var str = "";
						$(data).each(function ()
						{
							if(str==''){
								str = this.id ;
							}else{
								str += ","+ this.id ;
							}
						});
						batchPassInvoice(str);
					};

					return;
				}},
		],
		listGrid:{ columns: [
				{ display: "公司名称/抬头名称", width: 200, name: 'company'},
				{ display: "税号", width: 150, name: 'dutyParagraph'},
				{ display: "单位地址", width: 200, name: 'address'},
				{ display: "电话号码", width: 150, name: 'phone'},
				{ display: "开户银行", width: 200, name: 'bank'},
				{ display: "银行账号", width: 200, name: 'bankAccount'},
				{ display: "金额", width: 100, name: 'orderPayAmount'},
				{ display: "邮箱", width: 100, name: 'email'},
				{ display: '发票类型', width: 100, render: function (rowdata, rowindex) {
						var type=rowdata.type;
						if (type){
							if(type == '1'){
								return '专票';
							}else if(type == '2'){
								return '电子发票';
							}else if(type == '3'){
								return '纸质普票';
							}
						}else {
							return '';
						}
					}},
				{ display: "相关订单", width: 160, render: function (rowdata, rowindex) {
						if (rowdata.combineOrderId){
							return '<a href="javascript:viewDetail('+ rowdata.combineOrderId +');">'+ rowdata.combineOrderCode +'</a>';
						}
					}},
				{ display: '申请时间', width: 150, render: function (rowdata, rowindex) {
						if (rowdata.createDate!=null){
							return new Date(rowdata.createDate).format("yyyy-MM-dd hh:mm:ss");
						}
					}},
				{ display: '申请状态', width: 120, render: function (rowdata, rowindex) {
						var status=rowdata.status;
						if (status){
							if(status == '0'){
								return '申请开票中';
							}else if(status == '1'){
								return '已开票';
							}else if(status == '2'){
								return '已驳回';
							}
						}else {
							return '';
						}
					}},
				{ display: '操作', width: 220, render:function(rowdata,rowindex){
						var status=rowdata.status;
						if (status){
							if(status == '0'){
								return '<a href="javascript:passInvoice(1,'+ rowdata.id +');">处理完成</a> | <a href="javascript:rejectInvoice('+ rowdata.id +');">驳回</a>';
							}else{
								return new Date(rowdata.statusDate).format("yyyy-MM-dd hh:mm:ss");
							}
						}else {
							return '';
						}
					}}
			],
			showCheckbox : true,  //不设置默认为 true
			showRownumber:true //不设置默认为 true
		} ,
		container:{
			toolBarName:"toptoolbar",
			searchBtnName:"searchbtn",
			fromName:"dataForm",
			listGridName:"maingrid"
		}
	}
</script>

<body style="padding: 0px; overflow: hidden;">
<div id="toptoolbar"></div>
<form id="dataForm" runat="server">
	<div class="search-pannel">
		<div class="search-tr"  >
			<div class="search-td">
				<div class="search-td-label" >抬头名称：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id="company" name="company" >
				</div>
			</div>
			<div class="search-td">
				<div class="search-td-label" >税号：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id="dutyParagraph" name="dutyParagraph" >
				</div>
			</div>
			<div class="search-td">
				<div class="search-td-label" >状态：</div>
				<div class="search-td-combobox-condition" >
					<select id="status" name="status" style="width: 135px;" >
						<option value="">请选择</option>
						<option value="0">申请开票中</option>
						<option value="1">已开票</option>
						<option value="2">已驳回</option>
					</select>
				</div>
			</div>
			<div class="search-td">
				<div class="search-td-label" >发票类型：</div>
				<div class="search-td-combobox-condition" >
					<select id="type" name="type" style="width: 135px;" >
						<option value="">请选择</option>
						<option value="1">专票</option>
						<option value="2">电子发票</option>
						<option value="3">纸质普票</option>
					</select>
				</div>
			</div>
		</div>

		<div class="search-tr"  >
			<div class="search-td" style="width: 40%;">
				<div class="search-td-label" style="float: left;width: 20%;">申请日期：</div>
				<div class="l-panel-search-item" >
					<input type="text" id="create_date_begin" name="createDateBegin" class="dateEditor" value="${today}" style="width: 135px;" />
				</div>
				<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
				<div class="l-panel-search-item">
					<input type="text" id="create_date_end" name="createDateEnd" class="dateEditor" value="${today}" style="width: 135px;" />
				</div>
			</div>
			<div class="search-td" style="width: 40%;">
				<div class="search-td-label" style="float: left;width: 20%;">处理日期：</div>
				<div class="l-panel-search-item" >
					<input type="text" id="status_date_begin" name="statusDateBegin" class="dateEditor" value="" style="width: 135px;" />
				</div>
				<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
				<div class="l-panel-search-item">
					<input type="text" id="status_date_end" name="statusDateEnd" class="dateEditor" value="" style="width: 135px;" />
				</div>
			</div>
			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
		</div>

		<div id="maingrid" style="margin: 0; padding: 0"></div>
</form>

<%--驳回弹窗--%>
<div id="rejectInvoiceModel" style="text-align:center">
	<input type="hidden" id="id" name="id" value="">
	<table class="gridtable">
		<tr>
			<td colspan="1" class="title">驳回原因<span class="required">*</span></td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<textarea id="rejectReason" name="rejectReason" rows="5" cols="50" class="text" maxlength="100"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="1" class="title">操作</td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="提交" onclick="submitRejectInvoice()">
			</td>
		</tr>
	</table>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>