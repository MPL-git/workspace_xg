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
function savePlatformProcessor(id,input){
		$.ajax({
			url : "${pageContext.request.contextPath}/violateOrder/savePlatformProcessor.shtml",
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
 
function viewViolateOrder(id) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "商家违规详情页",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/violateOrder/manuallyViolateOrderView.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
	
 var listConfig={
	  
     url:"/violateOrder/manuallyViewData.shtml",
     btnItems:[{ text: '人工发起违规', icon: 'add', dtype:'win',  click: itemclick, url: "/violateOrder/add.shtml", seqId:"", width: 1200, height: 800}],
     listGrid:{ columns: [
						{ display: '违规编号', name:'orderCode'},
			            { display: '违规行为',name:'violateTypeDesc'},
		                { display: '商家', render: function (rowdata, rowindex) {
							return rowdata.companyName+"<br>"+rowdata.mchtCode;		                		
		                }},
		                { display: '支付违约金', name: 'money'},
		                { display: '状态', render: function (rowdata, rowindex) {
		                	if(rowdata.auditStatus == "0"){
								return "<span style='color:#ec971f;'>"+rowdata.auditStatusDesc+"</span>";		                	
		                	}else if(rowdata.auditStatus == "1"){
		                		return rowdata.auditStatusDesc;
		                	}else if(rowdata.auditStatus == "2"){
		                		return "<span style='color:#d43f3a;'>"+rowdata.auditStatusDesc+"</span>";
		                	}
		                }},  
		                { display: '提交时间', width: 150, render: function (rowdata, rowindex) {
		                	if(rowdata.createDate){
								var createDate=new Date(rowdata.createDate);
								return createDate.format("yyyy-MM-dd hh:mm:ss");		                		
		                	}
		                }},
		                { display: '提交人', render: function (rowdata, rowindex) {
	                		return rowdata.createName;
		                }},
		                { display: '操作',render: function (rowdata, rowindex) {
			               	return "<a href='javascript:;' onclick='viewViolateOrder("+rowdata.id+");'>查看</a>";
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
	<input type="hidden" id="staffId" value="${staffId}">
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		
		<div class="search-tr"  > 
			
			<div class="search-td">
			<div class="search-td-label" >违规编号：</div>
			<div class="search-td-condition">
				<input type="text" id="orderCode" name="orderCode" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">违规行为：</div>
			<div class="l-panel-search-item" >
				<select id="violateType" name="violateType" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="violateType" items="${violateTypeList}">
						<option value="${violateType.statusValue}">${violateType.statusDesc}
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
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">状态：</div>
			<div class="l-panel-search-item" >
				<select id="auditStatus" name="auditStatus" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="auditStatus" items="${auditStatusList}">
						<option value="${auditStatus.statusValue}">${auditStatus.statusDesc}</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">提交时间：</div>
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
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >提交人：</div>
			<div class="search-td-condition">
				<input type="text" id="platformProcessor" name="platformProcessor" >
			</div>
			</div>
			
			<div class="search-td-search-btn">
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