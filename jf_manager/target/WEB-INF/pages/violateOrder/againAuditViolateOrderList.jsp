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
	 
		$("#violateType").bind('change',function(){
			var violateType = $(this).val();
			$.ajax({
				url : "${pageContext.request.contextPath}/violateOrder/getViolateActions.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"violateType":violateType},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						var violateActions = data.violateActions;
						var optionArray = [];
						optionArray.push('<option value="">请选择</option>');
						for(var i=0;i<violateActions.length;i++){
							var statusValue = violateActions[i].statusValue;
							var statusDesc = violateActions[i].statusDesc;
							optionArray.push('<option value="'+statusValue+'">'+statusDesc+'</option>');
						}
						$("#violateAction").html(optionArray.join(""));
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
					
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		});
		
		var dateArray = [];
		<c:if test="${not empty sysStaffProductTypeList }" >
			var sysStaffProductTypeList = ${sysStaffProductTypeList };
			for(var i=0;i<sysStaffProductTypeList.length;i++) {
				dateArray.push(dataBox(sysStaffProductTypeList[i].staffName, sysStaffProductTypeList[i].productTypeId));
			}
		</c:if>
		var productType2IdsComboBox = $("#productTypeId").ligerComboBox({ 
			isShowCheckBox: true, 
			isMultiSelect: true, 
			emptyText: false,
	        data: dateArray, 
	        valueFieldID: 'productTypeIds',
	        selectBoxWidth: 135,
	        width: 135
	    }); 
		
 }); 
 
 function dataBox(text, id){ 
	 var obj = new Object();
	 obj.text = text; 
	 obj.id = id; 
	 return obj;
 }
 
 function viewViolateOrder(id) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "商家违规详情页",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/violateOrder/view.shtml?id=" + id+"&role="+"1",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
 }
 
 // 违规复审
 function againAuditManager(id) {
	 $.ligerDialog.open({
		height: 500,
		width: 600,
		title: "违规复审",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/violateOrder/againAuditManager.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
 }
	
 var listConfig={
	 btnItems:[],
     url:"/violateOrder/againAuditViolateOrderList.shtml",
     listGrid:{ columns: [
						{ display:'创建时间', width:120, render:function(rowdata, rowindex) {
							if(rowdata.createDate) {
								var createDate = new Date(rowdata.createDate);
								return createDate.format("yyyy-MM-dd hh:mm:ss");		                		
							}
						}},
						{ display:'来源/违规单编号', width:160, render:function(rowdata, rowindex) {
							return rowdata.orderSourceDesc+'<br><a href="javascript:;" onclick="viewViolateOrder('+rowdata.id+');">'+rowdata.orderCode+'</a>';	                	
		                }},
		                { display:'商家', width:120, render:function (rowdata, rowindex) {
							return rowdata.mchtCode+"<br>"+rowdata.shopName+"<br>"+rowdata.companyName;		                	
		                }},
		                { display:'子订单号', width:'160', name:'subOrderCode'},
		                { display:'违规行为', width:150, render:function(rowdata, rowindex) {
							return "【"+rowdata.violateTypeDesc+"】"+rowdata.violateActionDesc;		                	
		                }},
		                { display:'处罚金额（元）', width:100, name:'money'},
		                { display:'申诉状态', width:100, name:'statusDesc'},
		                { display:'商家申诉时间', width:120, render:function(rowdata, rowindex) {
							if(rowdata.complainTime) {
								var complainTime = new Date(rowdata.complainTime);
								return complainTime.format("yyyy-MM-dd hh:mm:ss");		                		
							}
						}},
		                { display: '平台处理人', width:100, name:'staffName'},
		                { display: '创建人', width:100, name:'createName'},
		                { display: '复审状态', width:100, name:'againAuditStatusDesc'},
		                { display: '复审备注', width:160, name:'againAuditRemarks'},
		                { display: '操作', width:80, render:function(rowdata, rowindex) {
							var html = [];
							if((rowdata.againAuditStatus == null || rowdata.againAuditStatus == '0') && ${auditPower }) {
								html.push("<a href=\"javascript:againAuditManager("+rowdata.id+");\">【复审】</a>");
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
	<form id="dataForm" runat="server" method="post">
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" >来源：</div>
					<div class="search-td-combobox-condition" >
						<select id="orderSource" name="orderSource" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="orderSource" items="${orderSourceList }">
								<option value="${orderSource.statusValue }">${orderSource.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >违规编号：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="orderCode" name="orderCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >商家序号：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >商家名称：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="name" name="name" >
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" >子订单号：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="subOrderCode" name="subOrderCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >违规类型：</div>
					<div class="search-td-combobox-condition">
						<select id="violateType" name="violateType" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="violateType" items="${violateTypeList }" >
								<option value="${violateType.statusValue }">${violateType.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >违规行为：</div>
					<div class="search-td-combobox-condition">
						<select id="violateAction" name="violateAction" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="violateAction" items="${violateActionList }">
								<option value="${violateAction.statusValue }">${violateAction.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >创建人：</div>
					<div class="search-td-combobox-condition">
						<select id="createBy" name="createBy" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="createByInfo" items="${createByInfos }">
								<option value="${createByInfo.createBy }">${createByInfo.staffName }</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" >申诉状态：</div>
					<div class="search-td-combobox-condition">
						<select id="status" name="status" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="4">超期未申诉</option>
							<option value="5">申诉失败</option>
							<option value="6">申诉成功</option>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="color: red;">对接人：</div>
					<div class="search-td-condition">
						<select id="platContactStaffId" name="platContactStaffId">
							<c:if test="${sessionScope.USER_SESSION.isManagement == '1' }">
								<option value="" selected="selected">全部商家</option>
							</c:if>
							<option value="${sessionScope.USER_SESSION.staffID }" selected="selected" >我自己的商家</option>
							<c:forEach items="${sysStaffInfoCustomList }" var="sysStaffInfoCustom">
								<option value="${sysStaffInfoCustom.staffId }">${sysStaffInfoCustom.staffName }的商家</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >复审状态：</div>
					<div class="search-td-combobox-condition">
						<select id="againAuditStatus" name="againAuditStatus" style="width: 135px;" >
							<c:forEach var="againAuditStatus" items="${againAuditStatusList }">
								<option value="${againAuditStatus.statusValue }">${againAuditStatus.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">品类：</div>
					<!-- <div style="display: inline-block;"> -->
					<div class="search-td-combobox-condition">
						<input type="text" id="productTypeId" name="productTypeId" />
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">创建日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginCreateDate" name="beginCreateDate" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endCreateDate" name="endCreateDate" class="dateEditor" />
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">申诉日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginComplainDate" name="beginComplainDate" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endComplainDate" name="endComplainDate" class="dateEditor" />
					</div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>