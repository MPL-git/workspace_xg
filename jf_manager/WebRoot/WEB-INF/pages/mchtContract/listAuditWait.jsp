<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
<title>合同待审核</title>

<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript">
function notCooperate(id,mchtId) {
	var role107 = ${role107};
	if(role107){
		$.ligerDialog.confirm("确定不签约吗？不签约后商家入驻将会关闭！", function (yes) {
			if(yes){
				$.ajax({
					url : "${pageContext.request.contextPath}/mchtContract/notCooperate.shtml?id=" + id,
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
	}else{
		$.ligerDialog.confirm("确定不签约吗？不签约后商家入驻将会关闭！", function (yes) {
			if(yes){
				$.ajax({
					url : "${pageContext.request.contextPath}/mchtContract/checkZsPlatformContact.shtml",
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {
						mchtId:mchtId
					},
					timeout : 30000,
					success : function(data) {
						if ("0000" == data.returnCode) {
							$.ajax({
								url : "${pageContext.request.contextPath}/mchtContract/notCooperate.shtml?id=" + id,
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
}

    var listConfig={
      url:"/mchtContract/listMchtContractData.shtml",
      listGrid:{
		  columns: [
				{ display: '合同生成日期', name: 'createDate', width:120 ,render: function(rowdata, rowindex) {
					if(rowdata.createDate != null && rowdata.createDate!=""){
					   return new Date(rowdata.createDate).format("yyyy-MM-dd");
					}
				}},
				{ display: '招商对接人', name: 'platformMerchantsContact.contactName',width:100 },
				// { display: '商家序号', name: 'mchtInfo.mchtCode',width:100 },
			    { display: '商家序号',width: 100, render: function(rowdata, rowindex) {
					  var html = [];
					  html.push(rowdata.mchtInfo.mchtCode);
					  html.push("<br>");
					  if(rowdata.mchtInfo.mchtType == "2"){
						  html.push("POP");
					  }else if (rowdata.mchtInfo.mchtType == "1" && rowdata.mchtInfo.isManageSelf == "1"){
						  html.push("自营SPOP");
					  }else {
						  html.push("SPOP");
					  }
					  return html.join("");
			    }},
				{ display: '入驻类型',width: 80, render: function(rowdata, rowindex) {
					console.log(rowdata.mchtInfo.settledType)
					console.log(rowdata.mchtInfo.mchtType)
	            	var html = [];
	            	if(rowdata.mchtInfo.settledType == "1"){
	            		html.push("企业公司");
	            	}else if(rowdata.mchtInfo.settledType == "2"){
	            		html.push("个体商户");
	            	}
	            	return html.join("");
	         	}},
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
			    { display: '商家上传日期', name: 'uploadDate', width:120 ,render: function(rowdata, rowindex) {
				    if(rowdata.uploadDate != null && rowdata.uploadDate!=""){
					  	return new Date(rowdata.uploadDate).format("yyyy-MM-dd");
				    }
			    }},
			    { display: '查看合同详情', name: 'uploadDate', width:120 ,render: function(rowdata, rowindex) {
					  return '<a href="javascript:viewMchtContract('+rowdata.id+');">查看</a>';
			    }},
				{ display: "线上合同状态", name: "auditStatusStr", width: 100, align: "center", render: function(rowdata, rowindex) {
				 var html = [];
				 var role107 = ${role107};
				 if(role107){
					 if(rowdata.auditStatusStr=="待审核"){
						html.push("<a href=\"javascript:audit(" + rowdata.id + ","+rowdata.mchtId+");\">已上传待审</a>&nbsp;&nbsp;");
					 }else{
						if(rowdata.auditStatus=="1"){//未上传
							html.push("<a href=\"javascript:toUpload(" + rowdata.id + ","+rowdata.mchtId+");\">未上传【上传】</a>&nbsp;&nbsp;");
						}else{
							html.push(rowdata.auditStatusStr);
						}
					 }
				 }else{
					 if (rowdata.platformFawuContact) {//法务本人及协助人可以审核
						 if(rowdata.auditStatusStr=="待审核"){
							 html.push("<a href=\"javascript:audit(" + rowdata.id + ","+rowdata.mchtId+");\">已上传待审</a>&nbsp;&nbsp;");
						 }else{
							 if(rowdata.auditStatus=="1"){//未上传
								 html.push("<a href=\"javascript:toUpload(" + rowdata.id + ","+rowdata.mchtId+");\">未上传【上传】</a>&nbsp;&nbsp;");
							 }else{
								 html.push(rowdata.auditStatusStr);
							 }
						 }
					  }else{
						 if(rowdata.auditStatusStr=="待审核"){
							 html.push("已上传待审");
						 }else{
							 if(rowdata.auditStatus=="1" && (rowdata.platformMerchantsContact || rowdata.platformFawuContact)){//未上传
								 html.push("<a href=\"javascript:toUpload(" + rowdata.id + ");\">未上传【上传】</a>&nbsp;&nbsp;");
							 }else{
								 html.push(rowdata.auditStatusStr);
							 }
						 }
					  }
				 }
					return html.join("");
				}},
			  	{ display: '线上审核日期', name: 'auditDate', width:120 ,render: function(rowdata, rowindex) {
				  	if(rowdata.auditDate != null && rowdata.auditDate!=""){
					  	return new Date(rowdata.auditDate).format("yyyy-MM-dd");
				  	}
			  	}},
			  	{ display: '法务对接人', name: 'platformFawuContact.contactName',width:100 },
			  	{ display: "生成PDF", name: "OPER4", width: 100, align: "center", render: function(rowdata, rowindex) {
				  	var html = [];
					html.push("<a href=\"${pageContext.request.contextPath}/file_servelt/" + rowdata.filePath + "\" class=\"table-title-link\">下载</a>");
				  	return html.join("");
			  	}},
			  	{ display: "操作", name: "OPER4", width: 100, align: "center", render: function(rowdata, rowindex) {
				  	var html = [];
			  		var role107 = ${role107};
			  		if(role107){
			  			html.push('<a href="javascript:;" onclick="rebuildMchtContract(' + rowdata.id + ',' + rowdata.mchtId + ');">重新生成</a>');
						html.push("<br><a href=\"javascript:notCooperate(" + rowdata.id + "," + rowdata.mchtId + ");\">不签约</a>");
			  		}else{
					  	if(rowdata.mchtInfo.status==0){
							html.push('<a href="javascript:;" onclick="rebuildMchtContract(' + rowdata.id + ',' + rowdata.mchtId + ');">重新生成</a>');
							html.push("<br><a href=\"javascript:notCooperate(" + rowdata.id + "," + rowdata.mchtId + ");\">不签约</a>");
					  	}else if(rowdata.mchtInfo.status==3){
					  		html.push("已关闭");
					  	}else if(rowdata.mchtInfo.status==1){
					  		html.push("已开通");
					  	}else if(rowdata.mchtInfo.status==2){
					  		html.push("店铺暂停");
					  	}else if(rowdata.mchtInfo.status==5){
					  		html.push("不入驻")
					  	}
			  		}
				  	return html.join("");
			  	}}
		  ],
		  showCheckbox : false,  //不设置默认为 true
		  showRownumber:true //不设置默认为 true
      }, 							
     container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      },       
	  pageSize:100 //默认条数100
  }; 
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" name="contractType" value="1" alt="新签"/>
		<%--<input type="hidden" name="auditStatus" value="2" alt="合同待审核"/>--%>
		<input type="hidden" name="orderByUploadDate" value="1" alt="按商家上传日期升序"/>
		<input type="hidden" name="orderById" value="1" alt="按合同ID升序"/>
		<div class="search-pannel">
			<div class="search-tr"  >
			
				<div class="search-td">
				<div class="search-td-label">序号：</div>
				<div class="search-td-condition">
				<input type="text" name="mcht_code" >
				</div>
				</div>

				<div class="search-td">
				<div class="search-td-label"  >名称：</div>
				<div class="search-td-condition" >
				<input type="text" name="mcht_name" >
				</div>
				</div>

				<div class="search-td">
					<div class="search-td-label">线上合同状态：</div>
					<div class="search-td-condition" >
						<select name="auditStatus" class="querysel">
							<option value="">请选择</option>
							<option value="1" <c:if test="${auditStatus=='1'}">selected="selected"</c:if>>未上传</option>
							<option value="2" <c:if test="${empty auditStatus || auditStatus=='2'}">selected="selected"</c:if>>已上传待审</option>
							<option value="3" <c:if test="${auditStatus=='3'}">selected="selected"</c:if>>通过</option>
							<option value="4" <c:if test="${auditStatus=='4'}">selected="selected"</c:if>>驳回</option>
						</select>
					</div>
				</div>
			</div>	
			<div class="search-tr"  >	
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

				<div class="search-td">
					<div class="search-td-label" style="float:left;">是否自营：</div>
					<div class="search-td-condition" >
						<select id="isManageSelf" name="isManageSelf" style="width: 150px;">
							<option value="">请选择</option>
							<option value="0">非自营</option>
							<option value="1">自营</option>
						</select>
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
//上传合同图片页面
function toUpload(mchtContractId,mchtId) {
	var role107 = ${role107};
	if(role107){
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 250,
			title: "上传合同",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtContract/toUpload.shtml?id=" + mchtContractId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}else{
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtContract/checkFwPlatformContact.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {
				mchtId:mchtId
			},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					$.ligerDialog.open({
						height: $(window).height() - 100,
						width: $(window).width() - 250,
						title: "上传合同",
						name: "INSERT_WINDOW",
						url: "${pageContext.request.contextPath}/mchtContract/toUpload.shtml?id=" + mchtContractId,
						showMax: true,
						showToggle: false,
						showMin: false,
						isResize: true,
						slide: false,
						data: null
					});
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
}

//查看商家合同详情
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
	
	// 合同审核
	function audit(mchtContractId,mchtId) {
		var role107 = ${role107};
		if(role107){
			$.ligerDialog.open({
				height: $(window).height()*0.8,
				width: $(window).width()*0.8,
				title: "处理审核",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mchtContract/audit.shtml?id=" + mchtContractId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}else{
			$.ajax({
				url : "${pageContext.request.contextPath}/mchtContract/checkFwPlatformContact.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {
					mchtId:mchtId
				},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						$.ligerDialog.open({
							height: $(window).height()*0.8,
							width: $(window).width()*0.8,
							title: "处理审核",
							name: "INSERT_WINDOW",
							url: "${pageContext.request.contextPath}/mchtContract/audit.shtml?id=" + mchtContractId,
							showMax: true,
							showToggle: false,
							showMin: false,
							isResize: true,
							slide: false,
							data: null
						});
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
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
	
	// 重新生成
	function rebuildMchtContract(mchtContractId, mchtId) {
		$.ligerDialog.confirm('你确认要重新生成合成吗？确认之后，旧的合同PDF文件将被删除，请在《入驻资质审核》中重新审核，即可生成新的合同文件。', function (yes) {
			if(yes) {
				$.ajax({
					type : 'post',
					url : "${pageContext.request.contextPath }/mchtContract/rebuildMchtContract.shtml",
					data : {mchtContractId : mchtContractId, mchtId : mchtId },
					dataType : 'json',
					success : function(data) {
						if(data.resultCode == '200') {
							$("#searchbtn").click();
						}else {
							if(data.resultMsg){
								commUtil.alertError(data.resultMsg);
							}else{
								commUtil.alertError("操作失败");
							}
						}	
					},
					error : function(e) {
						commUtil.alertError("系统异常请稍后再试");
					}
				});
			}
		});
		$(".l-dialog-content").css("padding-right", "20px");
		
	}
	
</script>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
