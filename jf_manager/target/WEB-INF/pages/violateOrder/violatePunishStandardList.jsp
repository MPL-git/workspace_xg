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
}); 
 
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

function edit(id) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "编辑违规处罚标准",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/violateOrder/editViolatePunishStandard.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function deleteViolatePunishStandard(id) {
	if(confirm("确定删除吗？")){
		$.ajax({
			url : "${pageContext.request.contextPath}/violateOrder/deleteViolatePunishStandard.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess(data.returnMsg);
				}else{
					commUtil.alertError("删除失败，请稍后重试");
				}
				setTimeout(function(){
					location.reload();
					frameElement.dialog.close();
				},1000);
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
}

 var listConfig={
	  
     url:"/violateOrder/violatePunishStandardData.shtml",
     btnItems:[{ text: '添加', icon: 'add', dtype:'win',  click: itemclick, url: "/violateOrder/editViolatePunishStandard.shtml", seqId:"", width: 1200, height: 800}],
     listGrid:{ columns: [
						{ display: '排序', name:'seqNo'},
			            { display: '类型', name:'violateTypeDesc'},
			            { display: '违规行为',name:'violateActionDesc'},
			            { display: '违规内容',name:'content'},
			            
			            { display: '支付违约金标准说明',name:'punishStandard'},
			            { display: '违约金支付模式', render: function (rowdata, rowindex) {
							var html = [];
							if(rowdata.paymentBreachModel=="0"){
								html.push("N元起");
							};
							if(rowdata.paymentBreachModel=="1"){
								html.push("按比例");
							};
						    return html.join("");                		
						}},
			            { display: '违约金扣款额度',name:'breachDeductionQuota'},
			            { display: '最低扣款',name:'minDeductionQuota'},
			            { display: '其他处理方式',name:'punishOther'},
		                { display: '补偿积分说明',name:'jifenIntegralDesc', width: 300},
		                { display: '补偿模式', render: function (rowdata, rowindex) {
							var html = [];
							if(rowdata.integralCompensateModel=="0"){
								html.push("固定积分");
							};
							if(rowdata.integralCompensateModel=="1"){
								html.push("按比例");
							};
							if(rowdata.integralCompensateModel=="2"){
								html.push("不发放");
							};
						    return html.join("");                		
						}},
						{ display: '补偿积分额度', render: function (rowdata, rowindex) {
						    var html = [];
							if(rowdata.integralCompensateModel == '0'){
								html.push(rowdata.jifenIntegral);
							}
							if(rowdata.integralCompensateModel == '1'){
								html.push(rowdata.integralCompensateRate);
							}
							return html.join(""); 
						}},
		                { display: '可否申诉',name:'complainFlagDesc'},
		                { display: '申诉期限',name:'enableHours'},
		                { display: '操作', render: function (rowdata, rowindex) {
							return '<a href="javascript:;" onclick="edit('+rowdata.id+')">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="deleteViolatePunishStandard('+rowdata.id+')">删除</a>';		                		
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
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">类型：</div>
			<div class="l-panel-search-item" >
				<select id="violateType" name="violateType" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="violateType" items="${violateTypeList}">
						<option value="${violateType.statusValue}">${violateType.statusDesc}</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			 <div class="search-td">
			<div class="search-td-label" style="float:left;">违规行为：</div>
			<div class="l-panel-search-item" >
				<select id="violateAction" name="violateAction" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="violateAction" items="${violateActionList}">
						<option value="${violateAction.statusValue}">${violateAction.statusDesc}</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			
			<div class="search-td">
			<div class="search-td-label" >违规内容：</div>
			<div class="search-td-condition">
				<input type="text" id="content" name="content" style="width: 500px;">
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