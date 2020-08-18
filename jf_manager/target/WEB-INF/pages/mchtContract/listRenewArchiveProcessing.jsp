<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
	<title>合同续签中</title>

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
			  { display: '开店日期', name: 'createDate', width:120 ,render: function(rowdata, rowindex) {
				  if(rowdata.createDate != null && rowdata.createDate!=""){
					  return new Date(rowdata.createDate).format("yyyy-MM-dd");
				  }
			  }},
			  { display: '招商对接人', name: 'platformMerchantsContact.contactName',width:100 },
			  { display: '商家序号', name: 'mchtInfo.mchtCode',width:100 },
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
			  { display: "合同详情", name: "OPER4", width: 100, align: "center", render: function(rowdata, rowindex) {
				  var html = [];
				  html.push("<a href=\"javascript:viewMchtContract(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;");
				  return html.join("");
			  }},
			  { display: "商家寄件状态", name: "isMchtSendStr", width: 100, align: "center"},
			  { display: "合同归档状态", name: "archiveStatusStr", width: 100, align: "center", render: function(rowdata, rowindex) {
				  var html = [];
				  if(rowdata.archiveStatusStr=="未处理"){
					  html.push("<a href=\"javascript:archive(" + rowdata.id + ");\">未处理</a>&nbsp;&nbsp;");
				  }else if(rowdata.archiveStatusStr=="不通过驳回"){
					  html.push("<a href=\"javascript:archive(" + rowdata.id + ");\">不通过驳回</a>&nbsp;&nbsp;");
				  }else{
					  html.push(rowdata.archiveStatusStr);
				  }

				  return html.join("");
			  }},
			  {
                  display: '法务备注',
                  name: 'fwInnerRemarks',
                  width: 100,
                  align: 'center',
                  render: function (rowdata, rowindex) {
                  	var html = [];
                  	html.push(rowdata.fwInnerRemarks);
                  	if(rowdata.fwInnerRemarks != null && rowdata.fwInnerRemarks != ""){
                  		 html.push("<br>");
                  		 html.push("<a href=\"javascript:modifyFwInnerRemarks(" + rowdata.id + ");\">【修改】</a>");
                  	}             
                  	return html.join("");
                  }
             },
			  { display: "合同编号", name: "contractCode", width: 100, align: "center"},
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
      
   	$(function() {
 		$("#export").bind('click',function(){
 			var mcht_code = $("#mcht_code").val();
 			var mcht_name = $("#mcht_name").val();
 			var contractCode = $("#contractCode").val();
 		    var isMchtSend = $("#isMchtSend").val();
 			var archiveStatus = $("#archiveStatus").val();
 			var is_my_fawu;
 			if($('input[name="is_my_fawu"]:checked').val() == undefined){
 				is_my_fawu = "";
 			}else{
 				is_my_fawu = $('input[name="is_my_fawu"]:checked').val();
 			}
 			var contractType = $("#contractType").val();
 			var archiveStatusIn = $("#archiveStatusIn").val();
 			var orderByBeginDate = $("#orderByBeginDate").val();
 			location.href="${pageContext.request.contextPath}/mchtContract/exports.shtml?mcht_code="+mcht_code+"&mcht_name="+mcht_name+"&contractCode="+contractCode+"&isMchtSend="+isMchtSend+"&archiveStatus="+archiveStatus+"&is_my_fawu="+is_my_fawu+"&contractType="+contractType+"&archiveStatusIn="+archiveStatusIn+"&orderByBeginDate="+orderByBeginDate;
 		});
	});
      
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" name="contractType" id="contractType" value="2" alt="续签"/>
		<input type="hidden" name="archiveStatusIn" id = "archiveStatusIn" value="[0,2,4]" alt="合同不等于已归档"/>
		<input type="hidden" name="orderByBeginDate" id = "orderByBeginDate" value="2" alt="按合同开始时间倒序"/>
		<div class="search-pannel">
			<div class="search-tr"  >
			
				<div class="search-td">
				<div class="search-td-label">序号：</div>
				<div class="search-td-condition">
				<input type="text" name="mcht_code" id="mcht_code">
				</div>
				</div>

				<div class="search-td">
				<div class="search-td-label"  >名称：</div>
				<div class="search-td-condition" >
				<input type="text" name="mcht_name" id="mcht_name">
				</div>
				</div>

				<div class="search-td">
					<div class="search-td-label"  >合同编号：</div>
					<div class="search-td-condition" >
						<input type="text" name="contractCode" id="contractCode">
					</div>
				</div>

			</div>

			<div class="search-tr"  >

				<div class="search-td">
					<div class="search-td-label">商家寄件状态：</div>
					<div class="search-td-condition" >
						<select name="isMchtSend" id="isMchtSend" class="querysel">
							<option value="">请选择</option>
							<option value="0">未寄出</option>
							<option value="1">已寄出</option>
						</select>
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label">合同归档状态：</div>
					<div class="search-td-condition" >
						<select name="archiveStatus" id="archiveStatus" class="querysel">
							<option value="">请选择</option>
							<option value="0">未处理</option>
							<option value="1">通过已归档</option>
							<option value="2">不通过驳回</option>
							<option value="4">不签约</option>
						</select>
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label" >
						<input type="checkbox" class="liger-checkbox" name="is_my_fawu" id="is_my_fawu" value="1" />
					</div>
					<div class="search-td-condition" style="text-align: left;">
						只看本人法务对接
					</div>
				</div>

				<div  class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" style="width: 100px; ">搜索</div>
					<div style="display: inline-flex;width: 62px;margin-left:15px;">
						<div id="export" class="l-button">导出</div>		
					</div>	
				</div>

			</div>

		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

<script type="text/javascript">

	// 合同归档处理
	function archive(mchtContractId) {
		$.ligerDialog.open({
			height: 480,
			width: 640,
			title: "处理归档",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtContract/archive.shtml?id=" + mchtContractId,
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

	// 查看商家合同详情
	function viewMchtContract(mchtContractId) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 250,
			title: "商家合同详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtContract/viewMchtContract.shtml?id=" + mchtContractId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

    //修改法务备注
    function modifyFwInnerRemarks(id) {
        $.ligerDialog.open({
            height: 500,
            width: 600,
            title: "修改备注",
            name: "INSERT_WINDOW",
            url: "${pageContext.request.contextPath}/mchtContract/modifyFwInnerRemarks.shtml?id=" + id,
            showMax: true,
            showToggle: false,
            showMin: false,
            isResize: true,
            slide: false,
            data: null,
            id: "closeReload"
        });
    }
</script>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
