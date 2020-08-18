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
	 $(".dateEditor").ligerDateEditor( {
		 showTime : false,
		 labelAlign : 'left',
		 labelwidth: 120,
		 width:120
	 });

	var dateArray = [];
	var defaultProductTypeIds = [];
	<c:if test="${not empty sysStaffProductTypeList }" >
		var sysStaffProductTypeList = ${sysStaffProductTypeList };
		for(var i=0;i<sysStaffProductTypeList.length;i++) {
			dateArray.push(dataBox(sysStaffProductTypeList[i].staffName, sysStaffProductTypeList[i].productTypeId));
			if(defaultProductTypeIds.length != 0) {
				defaultProductTypeIds.push(";");
			}
			defaultProductTypeIds.push(sysStaffProductTypeList[i].productTypeId);
		}
	</c:if>
	var productType2IdsComboBox = $("#productTypeId").ligerComboBox({ 
		isShowCheckBox: true, 
		isMultiSelect: true, 
		emptyText: false,
        data: dateArray, 
        valueFieldID: 'productTypeIds',
        selectBoxWidth: 165.5,
        width: 165.5
    }); 
	<c:if test="${isCwOrgStatus == 1 }" >
		productType2IdsComboBox.selectValue(defaultProductTypeIds.join());
		productType2IdsComboBox.updateStyle();
	</c:if>
	
});

function dataBox(text, id){ 
	 var obj = new Object();
	 obj.text = text; 
	 obj.id = id; 
	 return obj;
}
 
function savePlatformProcessor(id,input){
	$.ajax({
		url : "${pageContext.request.contextPath}/appealOrder/savePlatformProcessor.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {"id":id},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				var $div = $(input).parent();
				$div.empty();
				$div.text(data.staffName);
				commUtil.alertSuccess("领取成功");
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
			
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
} 
 
function viewAppealOrder(id) {
	$.ligerDialog.open({
		height: $(window).height() - 50,
		width: $(window).width() - 100,
		title: "投诉详情页",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/appealOrder/view.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function childFrameFun(appealOrderId, serviceStatus) {
	if(appealOrderId != '' && serviceStatus != '') {
		if(serviceStatus == '1') {
			$("#serviceStatus-"+appealOrderId).html("处理中");
			$("#serviceStatus-"+appealOrderId).attr("style", "color:green;");
		}else if(serviceStatus == '2') {
			$("#serviceStatus-"+appealOrderId).html("已结束");
			$("#serviceStatus-"+appealOrderId).attr("style", "");
		}else {
			$("#serviceStatus-"+appealOrderId).html("待介入");
			$("#serviceStatus-"+appealOrderId).attr("style", "color:red;");
		}
	}
}
	
 var listConfig={
	  
     url:"/appealOrder/pendingData.shtml",
     listGrid:{ columns: [
						{ display: '投诉编号', width: 160, render: function(rowdata, rowindex) {
							var html = [];
							html.push(rowdata.orderCode); 
							if(rowdata.memberInfoStatus == 'P') {
								html.push("</br><span style='color:red;'>异常</span>");
							}
							return html.join("");
						}},
			            { display: '订单号', width: 160,name:'subOrderCode'},
			            { display: '投诉类型',name:'appealTypeDesc'},
		                { display: '状态', name:'statusDesc'},
		                { display : '客服状态', render : function(rowdata, rowindex) {
							if (rowdata.serviceStatus == "0") {
								return "<span id='serviceStatus-"+rowdata.id+"' style='color:red;'>待介入</span>";
							} else if (rowdata.serviceStatus == "1") {
								return "<span id='serviceStatus-"+rowdata.id+"' style='color:green;'>处理中</span>";
							} else if (rowdata.serviceStatus == "2") {
								return "<span id='serviceStatus-"+rowdata.id+"'>已结束</span>";
							}else {
								return "<span id='serviceStatus-"+rowdata.id+"'></span>";
							}
						}},
		                { display: '商家',render: function (rowdata, rowindex) {
		                	return rowdata.companyName+"<br>"+rowdata.mchtCode;			                		
		                }},
		                { display: '所属活动[活的Id]', render: function (rowdata, rowindex) {
		                	if(rowdata.activityId){
			                	return rowdata.activityName+"<br>"+"["+rowdata.activityId+"]";	
		                	}
		                }},
		                { display: '投诉时间', width: 150, render: function (rowdata, rowindex) {
							var createDate=new Date(rowdata.createDate);
							return createDate.format("yyyy-MM-dd hh:mm:ss");		                		
		                }},
		                { display: '最后更新时间',width: 150, render: function (rowdata, rowindex) {
		                	if(rowdata.updateDate){
								var updateDate=new Date(rowdata.updateDate);
								return updateDate.format("yyyy-MM-dd hh:mm:ss");		                		
		                	}else{
		                		return "";
		                	}
		                }}, 
		                { display: '责任方', name:'liabilityDesc'},
		                { display: '投诉人',name:'userName'},
		                { display: '处理人',render: function (rowdata, rowindex) {
		                	if(rowdata.staffName){
		                		return rowdata.staffName;
		                	}else{
			                	return "<input type='button' style='cursor: pointer;width: 60px;height: 35px;-moz-border-radius: 2px;-webkit-border-radius: 2px;border-radius:6px;border:1px solid #ddd;background-color:#269abc;border-color: #269abc;' onclick='savePlatformProcessor("+rowdata.id+",this)' value='领取'></input>";
		                	}
		                }},
		                { display: '操作',render: function (rowdata, rowindex) {
		                	return "<a href='javascript:;' onclick='viewAppealOrder("+rowdata.id+");'>查看</a>";
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
<style type="text/css">
	.l-box-select .l-box-select-table td {
		font-size: 12px;
		line-height: 18px;
	}
</style>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		
		<div class="search-tr"  > 
			
			<div class="search-td">
			<div class="search-td-label" >投诉编号：</div>
			<div class="search-td-condition">
				<input type="text" id="orderCode" name="orderCode" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >订单号：</div>
			<div class="search-td-condition">
				<input type="text" id="subOrderCode" name="subOrderCode" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">投诉类型：</div>
			<div class="l-panel-search-item" >
				<select id="appealType" name="appealType" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="appealOrderAppealType" items="${appealOrderAppealTypeList}">
						<option value="${appealOrderAppealType.statusValue}">${appealOrderAppealType.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			
			<div class="search-td">
			<div class="search-td-label" >商家序号：</div>
			<div class="search-td-condition" >
				<input type="text" id="mchtCode" name="mchtCode" >
			</div>
			</div>
		</div>	
		<div class="search-tr"  >	
			<div class="search-td">
			<div class="search-td-label" style="float:left;">状态：</div>
			<div class="l-panel-search-item" >
				<select id="status" name="status" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="appealOrderStatus" items="${appealOrderStatusList}">
						<option value="${appealOrderStatus.statusValue}">${appealOrderStatus.statusDesc}</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">客服状态：</div>
			<div class="l-panel-search-item" >
				<select id="serviceStatus" name="serviceStatus" style="width: 150px;">
					<option value="">请选择</option>
					<option value="0">待介入</option>
					<option value="1">处理中</option>
				</select>
		 	 </div>
			 </div>
			
			<%--<div class="search-td">
			<div class="search-td-label" style="float:left;">投诉时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="create_date_begin" name="create_date_begin" />
				<script type="text/javascript">
					$(function() {
						$("#create_date_begin").ligerDateEditor( {
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
				<input type="text" id="create_date_end" name="create_date_end" />
				<script type="text/javascript">
					$(function() {
						$("#create_date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>--%>

			<div class="search-td" style="width: 30%;margin-bottom:-6px;">
				<div class="search-td-label" style="float: left;width: 20%;margin-top:2px;">投诉日期</div>
				<div class="l-panel-search-item" >
					<input type="text" id="create_date_begin" name="create_date_begin" class="dateEditor" placeholder="请选择" style="width: 135px;" />
				</div>
				<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
				<div class="l-panel-search-item">
					<input type="text" id="create_date_end" name="create_date_end" class="dateEditor" placeholder="请选择" style="width: 135px;" />
				</div>
			</div>
		</div>
			<div class="search-tr">
				<%--<div class="search-td">
					<div class="search-td-label" style="float:left;">最后更新时间：</div>
					<div class="l-panel-search-item">
						<input type="text" id="update_date_begin" name="update_date_begin" />
						<script type="text/javascript">
							$(function() {
								$("#update_date_begin").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
					<div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
				</div>

				<div class="search-td">
					<div class="l-panel-search-item">
						<input type="text" id="update_date_end" name="update_date_end" />
						<script type="text/javascript">
							$(function() {
								$("#update_date_end").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
				</div>--%>
					<div class="search-td" style="width: 30%;margin-bottom:-6px;">
						<div class="search-td-label" style="float: left;width: 20%;margin-top:2px;">最后更新日期</div>
						<div class="l-panel-search-item" >
							<input type="text" id="update_date_begin" name="update_date_begin" class="dateEditor" placeholder="请选择" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
						<div class="l-panel-search-item">
							<input type="text" id="update_date_end" name="update_date_end" class="dateEditor" placeholder="请选择" style="width: 135px;" />
						</div>
					</div>
				<div class="search-td">
					<div class="search-td-label" style="color: red">投诉异常：</div>
					<div class="search-td-condition" >
						<select id="memberStatus" name="memberStatus" >
							<option value="">请选择...</option>
							<option value="NP">正常</option>
							<option value="P">异常</option>
						</select>
					</div>
				</div> 
				<div class="search-td">
					<div class="search-td-label">品类：</div>
					<div style="display: inline-block;">
						<input type="text" id="productTypeId" name="productTypeId" />
					</div>
				</div>
				<div class="search-td-search-btn">
					<div id="searchbtn">搜索</div>
				</div>

			</div>

		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>