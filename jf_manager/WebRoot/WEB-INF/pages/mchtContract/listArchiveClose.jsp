<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
<title>不签约待关闭</title>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript">
      var listConfig={
      url:"/mchtContract/listMchtContractData.shtml",
      listGrid:{
		  columns: [
				{ display: '创建日期', name: 'createDate', width:120 ,render: function(rowdata, rowindex) {
					if(rowdata.createDate != null && rowdata.createDate!=""){
					   return new Date(rowdata.createDate).format("yyyy-MM-dd");
					}
				}},
				{ display: '招商对接人', name: 'platformMerchantsContact.contactName',width:100 },
				{ display: '模式', name: 'mchtInfo.mchtTypeStr',width:100 },
				{ display: '商家序号', name: 'mchtInfo.mchtCode',width:100 },
				//{ display: '商家简称',  name: 'mchtInfo.shortName', width: 150 },
				{ display: '公司名称',  name: 'mchtInfo.companyName', width: 150 },
				{ display: '店铺名',  name: 'mchtInfo.shopName', width: 150 },
				{ display: "公司/经营信息", name: "OPER1", width: 100, align: "center", render: function(rowdata, rowindex) {
					var html = [];
					html.push("<a href=\"javascript:viewMchtInfo(" + rowdata.mchtId + ");\">查看</a>&nbsp;&nbsp;");
					return html.join("");
				}},
				{ display: "财务信息", name: "OPER2", width: 100, align: "center", render: function(rowdata, rowindex) {
					var html = [];
					html.push("<a href=\"javascript:viewFinanceInfo(" + rowdata.mchtId + ");\">查看</a>&nbsp;&nbsp;");
					return html.join("");
				}},
				{ display: "联系人信息", name: "OPER3", width: 100, align: "center", render: function(rowdata, rowindex) {
					var html = [];
					html.push("<a href=\"javascript:viewMchtContact(" + rowdata.mchtId + ");\">查看</a>&nbsp;&nbsp;");
					return html.join("");
				}},
				{ display: "资质总状态", name: "mchtInfo.totalAuditStatusStr", width: 100, align: "center"},
				{ display: "合作状态", name: "mchtInfo.statusStr", width: 100, align: "center"},
			  	{ display: "招商操作", name: "OPER4", width: 100, align: "center", render: function(rowdata, rowindex) {
				  	var html = [];
					if(rowdata.mchtInfo.statusStr=="入驻中"){
						html.push("<a href=\"javascript:closeShop(" + rowdata.mchtId + ");\">直接关闭</a>&nbsp;&nbsp;");
					}else{
						html.push("<a href=\"javascript:requestCloseShop(" + rowdata.mchtId + ");\">申请关店</a>&nbsp;&nbsp;");
					}

				  	return html.join("");
			  	}},
			  	{ display: '保证金余额', name: 'mchtDeposit.totalAmt',width:100 },
			  	{ display: '法务对接人', name: 'platformFawuContact.contactName',width:100 }
		  ],
		  showCheckbox : false,  //不设置默认为 true
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
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" name="archiveStatus" value="4" alt="合同归档状态=不签约"/>
		<input type="hidden" name="mchtInfoStatusIn" value="[0,1,2]" alt="商家合作状态!=关闭"/>
		<input type="hidden" name="orderById" value="2"/>
		<div class="search-pannel">
			<div class="search-tr"  >
			
				<div class="search-td">
				<div class="search-td-label">序号：</div>
				<div class="search-td-condition">
				<input type="text" name="mcht_code" >
				</div>
				</div>

				<div class="search-td">
					<div class="search-td-label">模式：</div>
					<div class="search-td-condition" >
						<select name="mcht_type" class="querysel">
							<option value="">请选择</option>
							<c:forEach var="list" items="${mcht_type}">
								<option value="${list.STATUS_VALUE}">${list.STATUS_DESC}
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
		
				<div class="search-td">
				<div class="search-td-label"  >名称：</div>
				<div class="search-td-condition" >
				<input type="text" name="mcht_name" >
				</div>
				</div>

			</div>

			<div class="search-tr"  >

				<div class="search-td">
					<div class="search-td-label" >
						<input type="checkbox" class="liger-checkbox" name="is_my_merchants" value="1" />
					</div>
					<div class="search-td-condition" style="text-align: left;">
						只看本人招商对接
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label" >
						<input type="checkbox" class="liger-checkbox" name="is_my_fawu" value="1" />
					</div>
					<div class="search-td-condition" style="text-align: left;">
						只看本人法务对接
					</div>
				</div>

				<div  class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" style="width: 100px; ">搜索</div>
				</div>

			</div>

		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

<script type="text/javascript">

	function closeShop(mchtId){
		$.ligerDialog.confirm('你是否确认关闭该商家？关闭后，如果要重新入驻，需向上级申请。', function (yes) {
			if(yes){
				$.ajax({
					url : "${pageContext.request.contextPath}/mcht/closeCommit.shtml?mchtId=" + mchtId,
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {
						mchtId:mchtId
					},
					timeout : 30000,
					success : function(result) {
						console.log(result);
						if (result.success) {
							$.ligerDialog.success("关闭成功!");
							$("#searchbtn").click();
						}else{
							$.ligerDialog.error(result.message);
						}
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});

			}

		});
	}

	function requestCloseShop(mchtId) {
		$.ligerDialog.open({
			height: 480,
			width: 640,
			title: "申请关店",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtCloseApply/request.shtml?mchtId=" + mchtId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}


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

	// 查看财务信息
	function viewFinanceInfo(id) {
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width() - 200,
			title: "商家财务信息",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/mchtFinanceInfoEdit.shtml?mchtId=" + id,
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
</script>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
