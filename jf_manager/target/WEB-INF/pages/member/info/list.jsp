<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
<script type="text/javascript">
$(function(){
    <%--$("#partStatus").change(function() {--%>
    <%--    if($("#partStatus").is(':checked')){--%>
    <%--        window.location.href='${pageContext.request.contextPath}/member/info/list.shtml?type=5&partStatus=1';--%>
	<%--	}else {--%>
    <%--        window.location.href='${pageContext.request.contextPath}/member/info/list.shtml?type=5&partStatus=2';--%>
	<%--	}--%>

    <%--});--%>

	$(".dateEditor").ligerDateEditor({
		showTime : true,
		format : "yyyy-MM-dd",
		labelAlign : 'left',
		width : 135
	});
	
	$("#search").bind('click',function(){
		<c:if test="${type eq 2 }">
			var payOrderCountMin = $.trim($("#payOrderCountMin").val());
			var payOrderCountMax = $.trim($("#payOrderCountMax").val());
			if(payOrderCountMin){
				if(!testNumber(payOrderCountMin)){
					commUtil.alertError("消费订单数只能是整数");
					return;
				}
			}
			if(payOrderCountMax){
				if(!testNumber(payOrderCountMax)){
					commUtil.alertError("消费订单数只能是整数");
					return;
				}
			}
			var payOrderAmountMin = $.trim($("#payOrderAmountMin").val());
			var payOrderAmountMax = $.trim($("#payOrderAmountMax").val());
			var reg=/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
			if(payOrderAmountMin){
				if(!reg.test(payOrderAmountMin)){
					commUtil.alertError("消费金额格式不正确");
					return;
				}
			}
			if(payOrderAmountMax){
				if(!reg.test(payOrderAmountMax)){
					commUtil.alertError("消费金额格式不正确");
					return;
				}
			}
			$("#searchbtn").click();
		</c:if>
		<c:if test="${ type eq 5}">

			var payOrderCountMin = $.trim($("#payOrderCountMin").val());
			var payOrderCountMax = $.trim($("#payOrderCountMax").val());
			var partPayOrderCountMin = $.trim($("#partPayOrderCountMin").val());
			var partPayOrderCountMax = $.trim($("#partPayOrderCountMax").val());
			if(payOrderCountMin){
				if(!testNumber(payOrderCountMin) && payOrderCountMin!=0){
					commUtil.alertError("消费订单数只能是整数");
					return;
				}
			}
			if(payOrderCountMax){
				if(!testNumber(payOrderCountMax)){
					commUtil.alertError("消费订单数只能是整数");
					return;
				}
			}
			if(partPayOrderCountMin && payOrderCountMin!=0){
				if(!testNumber(partPayOrderCountMin)){
					commUtil.alertError("本期消费订单数只能是整数");
					return;
				}
			}
			if(partPayOrderCountMax){
				if(!testNumber(partPayOrderCountMax)){
					commUtil.alertError("本期消费订单数只能是整数");
					return;
				}
			}
			var payOrderAmountMin = $.trim($("#payOrderAmountMin").val());
			var payOrderAmountMax = $.trim($("#payOrderAmountMax").val());
			var partPayOrderAmountMin = $.trim($("#partPayOrderAmountMin").val());
			var partPayOrderAmountMax = $.trim($("#partPayOrderAmountMax").val());
			var reg=/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
			if(payOrderAmountMin && payOrderAmountMin!=0){
				if(!reg.test(payOrderAmountMin)){
					commUtil.alertError("消费金额格式不正确");
					return;
				}
			}
			if(payOrderAmountMax){
				if(!reg.test(payOrderAmountMax)){
					commUtil.alertError("消费金额格式不正确");
					return;
				}
			}
			if(partPayOrderAmountMin && payOrderAmountMin!=0){
				if(!reg.test(partPayOrderAmountMin)){
					commUtil.alertError("本期消费金额格式不正确");
					return;
				}
			}
			if(partPayOrderAmountMax){
				if(!reg.test(partPayOrderAmountMax)){
					commUtil.alertError("本期消费金额格式不正确");
					return;
				}
			}

			var comPayDateBegin = $("#comPayDateBegin").val();
			var comPayDateEnd = $("#comPayDateEnd").val();
			if(!isEmpty(partPayOrderAmountMin) || !isEmpty(partPayOrderAmountMax) || !isEmpty(partPayOrderCountMin) || !isEmpty(partPayOrderCountMax)){
				if(isEmpty(comPayDateBegin) || isEmpty(comPayDateEnd)){
					commUtil.alertError("消费时间不能为空！");
					return;
				}
			}
			if(!isEmpty(comPayDateBegin) && !isEmpty(comPayDateEnd)){
				var days = new Date(comPayDateEnd).getTime() - new Date(comPayDateBegin).getTime();
				var day = parseInt(days / (1000 * 60 * 60 * 24));
				if(day>100){
					commUtil.alertError("消费时间查询跨度不能大于100天！");
					return;
				}
			}
			if(!isEmpty(comPayDateBegin) && isEmpty(comPayDateEnd)){
				commUtil.alertError("消费时间需输入范围！");
				return;
			}
			if(isEmpty(comPayDateBegin) && !isEmpty(comPayDateEnd)){
				commUtil.alertError("消费时间需输入范围！");
				return;
			}
			$("#seeFunctionId").click();
		</c:if>
		<c:if test="${type ne 2 && type ne 5}">
			$("#searchbtn").click();	
		</c:if>
	});
	
	$("#exportExcel").bind('click',function(){
        <c:if test="${type eq 5}">
			$("#dataForm").attr("action", "${pageContext.request.contextPath}/member/info/getPartExportExcel.shtml?");
			$("#dataForm").submit();
        </c:if>
        <c:if test="${type ne 5}">
			$.ajax({
				url : "${pageContext.request.contextPath}/member/info/getExportExcelTotal.shtml?type="+$("#type").val()+"&status="+$("#status").val()+"&id="+$("#id").val()+"&nick="+$("#nick").val()+"&mobile="+$("#mobile").val()+"&groupId="+$("#groupId").val()+"&createDateBegin="+$("#createDateBegin").val()+"&createDateEnd="+$("#createDateEnd").val()+"&comPayDateBegin="+$("#comPayDateBegin").val()+"&comPayDateEnd="+$("#comPayDateEnd").val()+"&loginDateBegin="+$("#loginDateBegin").val()+"&loginDateEnd="+$("#loginDateEnd").val()+"&firstPayDateBegin="+$("#firstPayDateBegin").val()+"&firstPayDateEnd="+$("#firstPayDateEnd").val()+"&lastPayDateBegin="+$("#lastPayDateBegin").val()+"&lastPayDateEnd="+$("#lastPayDateEnd").val()+"&payOrderAmountMin="+$("#payOrderAmountMin").val()+"&payOrderAmountMax="+$("#payOrderAmountMax").val()+"&payOrderCountMin="+$("#payOrderCountMin").val()+"&payOrderCountMax="+$("#payOrderCountMax").val(),
				type : 'Get',
				dataType : 'json',
				cache : false,
				async : false,
				timeout : 30000,
				success : function(data) {
					if("4004" == data.returnCode){
						$.ligerDialog.error(data.returnMsg);
					}else{
						$("#dataForm").attr("action", "${pageContext.request.contextPath}/member/info/exportExcel.shtml");
						$("#dataForm").submit();
					}
				},
				error : function(data) {
					$.ligerDialog.error(data.returnMsg);
				}
			});
        </c:if>
	});

	$("#partExportExcel").bind('click',function(){
<c:if test="${ type eq 5}">
		var total=0;
		var itemForm = $('#dataForm').serializeArray();
		$.ajax({
			url : "${pageContext.request.contextPath}/member/info/partDataList.shtml",
			method: 'POST',
			dataType : 'json',
			data: itemForm,
			cache : false,
			async : false,
			timeout : 30000,
			success : function(data) {
				total = data.Total;
			},
			error : function(data) {
				$.ligerDialog.error(data.returnMsg);
			}
		});
        $.ligerDialog.open({
            height: $(window).height() - 600,
            width: $(window).width() - 1200,
            title: "导出",
            name: "INSERT_WINDOW",
            url: "${pageContext.request.contextPath}/member/info/partExportExcel.shtml?Total="+total,
            showMax: true,
            showToggle: false,
            showMin: false,
            isResize: true,
            slide: false,
            data: null
        });
		</c:if>
	});
	
// 	var data = [{id : "province", pid : null, data : "${pageContext.request.contextPath}/webcommon/prodata.shtml"},
// 	              {id : "city", pid : "province", data : "${pageContext.request.contextPath}/webcommon/areadata.shtml"},
// 	    	      {id : "county", pid : "city", data : "${pageContext.request.contextPath}/webcommon/areadata.shtml"}];
// 	LinkageComboBox.init(data);
// 	$("#province").ligerComboBox({selectBoxWidth: 200, selectBoxHeight: 200});
// 	$("#city").ligerComboBox({selectBoxWidth: 200, selectBoxHeight: 200});
// 	$("#county").ligerComboBox({selectBoxWidth:200, selectBoxHeight: 200});
	
/* 	<c:if test="${type eq 2}">
	$(".search-pannel div").eq(0).hide();
</c:if> */
});


function viewDetail(id) {
	$.ligerDialog.open({
		height: $(window).height() - 50,
		width: $(window).width() - 100,
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
//判断字符是否为空
function isEmpty(obj){
    return (typeof obj === 'undefined' || obj === null || obj === "");
}

function seeFunction() {
	<c:if test="${type eq 5}">
        listModel.ligerGrid.url = '${pageContext.request.contextPath}/member/info/partDataList.shtml';
	    listModel.initdataPage();
    </c:if>
	<c:if test="${type ne 5}">
        listModel.ligerGrid.url = '${pageContext.request.contextPath}/member/info/dataList.shtml';
        listModel.initdataPage();
	</c:if>
}
 

function memberRecovery(id,status) {
	var title="确定恢复账户";
	$.ligerDialog.confirm("是否"+title+"？", function (yes) { 
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/member/memberRecovery.shtml?id=" + id+ "&status=" + status,
				secureuri : false,
				dataType : 'json',
				cache : false,
				async : false,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("恢复账户成功~");
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


 var listConfig={
      btnItems:[{ text: '账户拉黑', icon: 'delete',  dtype:'ajax', checkType:'mult',   click: itemclick, url:"/service/memberMng/del.shtml?type=1&MEMBER_ID=",seqId:"MEMBER_ID" },
                { text: '账户恢复', icon: 'modify',  dtype:'ajax', checkType:'mult',   click: itemclick, url:"/service/memberMng/del.shtml?type=2&MEMBER_ID=",seqId:"MEMBER_ID" }],
      listGrid:{columns: [{ display: '会员ID', name: 'id', render: function(rowdata, rowindex) {
					      	var html = [];
					      	html.push(rowdata.id);
					      	if(rowdata.status == 'P') {
					      		html.push("</br><span style='color: red;'>黑名单</span>");
					      	}
					      	return html.join("");
					    } },
			         // { display: '会员ID', name: 'id', render: function(count, rowindex) {
					 //  var html = [];
					 //  html.push(count);
					 //  return html.join("");
				     //  } },
    	                { display: '会员类型', name: 'type',hide:${type==3}, render: function(rowdata, rowindex) {
    	                	if(rowdata.type == '1') {
    	                		return "手机注册";
    	                	}else if(rowdata.type == '2') {
    	                		return "联合登录";
    	                	}else if(rowdata.type == '3') {
    	                		return "老游客";
    	                	}else if(rowdata.type == '4') {
    	                		return "新游客";
    	                	}
    	                } },
    	                { display: '来源渠道', name: 'sprChnlDesc',hide:${type==4 || type == 2 || type == 5}},
    	                { display: '昵称', name: 'nick', render: function(rowdata, rowindex) {
    	                	if(rowdata.nick==null){
    	                		return "醒购会员";
    	                	}else{
    	                		if(rowdata.nick.length > 8) {
    	                			return rowdata.nick.substring(0, 6)+"**"+rowdata.nick.substring(rowdata.nick.length-1);
    	                		}else {
	    	                		return rowdata.nick;
    	                		}
    	                	}
    	                }},
		                {display: '会员状态', name: 'statusDesc'},
		                { display: '用户组', name: 'groupName'},
		                { display: '注销原因', name: 'cancelReason',hide:${type==3}},
		                { display: '注销前状态', name: '',hide:${type==2 || type==3 || type == 5},render: function(rowdata, rowindex) {
		                	var html=[];
		                	var limitFunctionStatus="";
		                	var limitFunction=String(rowdata.limitFunction);
		                	if (limitFunction.charAt(0)==null || limitFunction.charAt(0)=='') {
		                		limitFunctionStatus+="无/";
							}else{
								if (limitFunction.charAt(0)=="1"){
			 						limitFunctionStatus+="限制评价/";
								}
			                    if (limitFunction.charAt(2)=="2"){
			                    	limitFunctionStatus+="限制购买/";
								}
			                    if (limitFunction.charAt(4)=="3"){
			                    	limitFunctionStatus+="限制登入/";
								}
							}
		                       html.push(limitFunctionStatus.replace(/[/]$/,""));
		 					
					      	if (rowdata.memberStatuslog==null) {
					      		return "已激活";
							}else{
								if(rowdata.memberStatuslog == 'A') {
						      		return "已激活";  		
						      	}
						      	if (rowdata.memberStatuslog == 'B') {
									return "待激活"+"("+ html.join("")+")";
								}
						      	if (rowdata.memberStatuslog == 'P') {
									return "黑名单"+"("+ html.join("")+")";
								}
						      	if (rowdata.memberStatuslog == 'C') {
									return "注销"+"("+ html.join("")+")";
								}
							}
					    }},
		                { display: '注册时间', render: function (rowdata, rowindex) {
		                	if (rowdata.createDate!=null){
								var createDate=new Date(rowdata.createDate);
								return createDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '最后登录时间', render: function (rowdata, rowindex) {
		                	if (rowdata.msLastLoginTime!=null){
								var msLastLoginTime=new Date(rowdata.msLastLoginTime);
								return msLastLoginTime.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '首次消费时间', render: function (rowdata, rowindex) {
		                	if (rowdata.msFirstBuyTime!=null){
								var msFirstBuyTime=new Date(rowdata.msFirstBuyTime);
								return msFirstBuyTime.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '最后消费时间', render: function (rowdata, rowindex) {
		                	if (rowdata.msLastBuyTime!=null){
								var msLastBuyTime=new Date(rowdata.msLastBuyTime);
								return msLastBuyTime.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '历史消费订单数', name: 'totalBuyCount'},
		                { display: '历史消费金额', name: 'totalBuyAmount' },
						{ display: '本期消费订单数', name: 'partBuyCount', hide:${type!=5}},
		                { display: '本期消费金额', name: 'partBuyAmount', hide:${type!=5}},
		                { display: '积分', width: 80, name: 'integral' },
		                { display: '成长值', width: 80, name: 'gValue' },
		                
		                { display: '推广渠道', name: 'channel',hide:${type==4|| type == 2 || type == 5}},
		                { display: '活动名称', name: 'spreadname',hide:${type==4|| type == 2 || type == 5}},
		                { display: '活动组', name: 'activityname',hide:${type==4|| type == 2 || type == 5}},
		                { display: '设备序列号', name: 'reqImei',hide:${type!=1}},
			            { display: '操作',  name: 'OPER', render: function(rowdata, rowindex) {
							var html = []; 
							html.push("<a href=\"javascript:viewDetail(" + rowdata.id + ");\">查看&nbsp&nbsp&nbsp</a>");
							if (${type==4}) {
								html.push("<a href=\"javascript:memberRecovery(" + rowdata.id+",'C');\">恢复</a>");
							}
							return html.join("");
						  }}
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
       
  };
 
</script>

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server" action="" method="post" >
		<input type="hidden" id="type" name="type" value="${type}">
		<c:if test="${type eq 1 || type eq 2 || type eq 5}">
			<input type="hidden" id="status" name="status" value="A">
		</c:if>
		<c:if test="${type eq 4}">
			<input type="hidden" id="status" name="status" value="C">
		</c:if>
		<c:if test="${type eq 5}">
			<input type="hidden" id="section" name="section">
<%--			<input type="hidden" id="Total" value="${count}">--%>
		</c:if>
		<div class="search-pannel">
			<c:if test="${type ne 5}">
			<div class="search-tr">
<!-- 				<div class="search-td"> -->
<!-- 					<div class="search-td-label" style="float:left;">省：</div> -->
<!-- 					<div class="l-panel-search-item"> -->
<!-- 						<input id="province" name="province" type="text" style="float:left;width: 100%;" /> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="search-td"> -->
<!-- 					<div class="search-td-label" style="float:left;">市：</div> -->
<!-- 					<div class="l-panel-search-item"> -->
<!-- 						<input id="city" name="city" type="text" style="float:left;width: 100%;" /> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="search-td"> -->
<!-- 					<div class="search-td-label" style="float:left;">区：</div> -->
<!-- 					<div class="l-panel-search-item"> -->
<!-- 						<input id="county" name="county" type="text" style="float:left;width: 100%;" /> -->
<!-- 					</div> -->
<!-- 				</div> -->
				<div class="search-td">
					<div class="search-td-label">会员ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="id" name="id">
					</div>
				</div>
			</div>
			</c:if>
			<c:if test="${type eq 1}">
				<div class="search-tr">
					<div class="search-td">
						<div class="search-td-label">会员昵称：</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="nick" name="nick">
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">手机号码：</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="mobile" name="mobile">
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">会员组：</div>
						<div class="search-td-combobox-condition" >
							<select id="groupId" name="groupId" style="width: 135px;">
								<option value="">请选择...</option>
								<c:forEach var="list" items="${memberGroups}">
									<option value="${list.id}">${list.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">活动名称：</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="spreadname" name="spreadname">
						</div>
					</div>
				</div>
				<div class="search-tr">
					<div class="search-td">
						<div class="search-td-label">来源渠道：</div>
						<div class="search-td-combobox-condition" >
							<select id="sprChnl" name="sprChnl" style="width: 135px;">
								<option value="">请选择...</option>
								<option value="20000">微信小程序</option>
								<option value="30000">抖音</option>
								<c:forEach var="list" items="${sprChnls}">
									<option value="${list.statusValue}">${list.statusDesc}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">推广渠道：</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="channel" name="channel">
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">活动组：</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="activityname" name="activityname">
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">推广设备类型：</div>
						<div class="search-td-combobox-condition" >
							<select id="regClient" name="regClient" style="width: 135px;">
								<option value="1">IOS</option>
								<option value="2" selected>Android</option>
							</select>
						</div>
					</div>
				</div>
				<div class="search-tr">
					<div class="search-td" style="width: 40%;">
						<div class="search-td-label" style="float: left;width: 20%;">
							<select id="timeType" name="timeType">
								<option value="1">注册时间</option>
								<option value="2">最后登录时间</option>
								<option value="3">首次消费时间</option>
								<option value="4">最后消费时间</option>
							</select>：
						</div>
						<div class="l-panel-search-item" >
							<input type="text" id="date_begin" name="date_begin" class="dateEditor" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
						<div class="l-panel-search-item">
							<input type="text" id="date_end" name="date_end" class="dateEditor" style="width: 135px;" />
						</div>
					</div>
					<div class="search-td-search-btn" style="display: inline-flex;">
						<div id="search">
							<input type="button" class="l-button" onclick="seeFunction();" value="搜索">
						</div>
						<div id="searchbtn" style="display: none;"></div>
					</div>
				</div>
			</c:if>
			<c:if test="${type eq 2}">
				<div class="search-tr">
					<div class="search-td">
						<div class="search-td-label">会员昵称：</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="nick" name="nick">
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">手机号码：</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="mobile" name="mobile">
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">会员组：</div>
						<div class="search-td-combobox-condition" >
							<select id="groupId" name="groupId" style="width: 135px;">
								<option value="">请选择...</option>
								<c:forEach var="list" items="${memberGroups}">
									<option value="${list.id}">${list.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="search-tr">
					<div class="search-td" style="width: 40%;">
						<div class="search-td-label" style="float: left;width: 20%;">注册时间：</div>
						<div class="l-panel-search-item" >
							<input type="text" id="createDateBegin" name="createDateBegin" class="dateEditor" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
						<div class="l-panel-search-item">
							<input type="text" id="createDateEnd" name="createDateEnd" class="dateEditor" style="width: 135px;" />
						</div>
					</div>
					
					<div class="search-td" style="width: 40%;">
						<div class="search-td-label" style="float: left;width: 20%;">消费时间：</div>
						<div class="l-panel-search-item" >
							<input type="text" id="comPayDateBegin" name="comPayDateBegin" class="dateEditor" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
						<div class="l-panel-search-item">
							<input type="text" id="comPayDateEnd" name="comPayDateEnd" class="dateEditor" style="width: 135px;" />
						</div>
					</div>
					<div class="search-td" style="width: 40%;">
						<div class="search-td-label" style="float: left;width: 20%;">最后登录时间：</div>
						<div class="l-panel-search-item" >
							<input type="text" id="loginDateBegin" name="loginDateBegin" class="dateEditor" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
						<div class="l-panel-search-item">
							<input type="text" id="loginDateEnd" name="loginDateEnd" class="dateEditor" style="width: 135px;" />
						</div>
					</div>
				</div>
				<div class="search-tr">
					<div class="search-td" style="width: 40%;">
						<div class="search-td-label" style="float: left;width: 20%;">首次消费时间：</div>
						<div class="l-panel-search-item" >
							<input type="text" id="firstPayDateBegin" name="firstPayDateBegin" class="dateEditor" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
						<div class="l-panel-search-item">
							<input type="text" id="firstPayDateEnd" name="firstPayDateEnd" class="dateEditor" style="width: 135px;" />
						</div>
					</div>
					<div class="search-td" style="width: 40%;">
						<div class="search-td-label" style="float: left;width: 20%;">最后消费时间：</div>
						<div class="l-panel-search-item" >
							<input type="text" id="lastPayDateBegin" name="lastPayDateBegin" class="dateEditor" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
						<div class="l-panel-search-item">
							<input type="text" id="lastPayDateEnd" name="lastPayDateEnd" class="dateEditor" style="width: 135px;" />
						</div>
					</div>
				</div>
				<div class="search-tr">
					<div class="search-td">
						<div class="search-td-label">累积消费金额：</div>
						<div class="search-td-condition" style="display: inline-flex;">
							<div style="float: left;"><input type="text" id="payOrderAmountMin" name="payOrderAmountMin"></div>
							<div style="width: 25px;">--</div>
							<div style="float: left;"><input type="text" id="payOrderAmountMax" name="payOrderAmountMax"></div>
						</div>
					</div>
					<div class="search-td">
					<div class="search-td-label">累积消费订单数：</div>
						<div class="search-td-condition" style="display: inline-flex;">
							<div style="float: left;"><input type="text" id="payOrderCountMin" name="payOrderCountMin"></div>
							<div style="width: 25px;">--</div>
							<div style="float: left;"><input type="text" id="payOrderCountMax" name="payOrderCountMax"></div>
						</div>
					</div>				
					<div class="search-td-search-btn" style="display: inline-flex;">
						<div id="search">
							<input type="button" class="l-button" onclick="seeFunction();" value="搜索">
						</div>
						<div id="searchbtn" style="display: none;"></div>
						<div style="padding-left: 10px;">
							<input type="button" class="l-button" value="导出Excel" id="exportExcel">
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${type eq 3}">
				<div class="search-tr">
					<div class="search-td">
						<div class="search-td-label">会员昵称：</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="nick" name="nick">
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">手机号码：</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="mobile" name="mobile">
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">会员组：</div>
						<div class="search-td-combobox-condition" >
							<select id="groupId" name="groupId" style="width: 135px;">
								<option value="">请选择...</option>
								<c:forEach var="list" items="${memberGroups}">
									<option value="${list.id}">${list.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">会员状态：</div>
						<div class="search-td-combobox-condition" >
							<select id="status" name="status" style="width: 135px;">
								<option value="">请选择</option>
								<option value="A">已激活</option>
								<option value="P">黑名单</option>
							</select>
						</div>
					</div>
					<div class="search-td-search-btn" style="display: inline-flex;">
						<div id="search">
							<input type="button" class="l-button" onclick="seeFunction();" value="搜索">
						</div>
						<div id="searchbtn" style="display: none;"></div>
					</div>
				</div>
			</c:if>
			<c:if test="${type eq 4}">
				<div class="search-tr">
					<div class="search-td">
						<div class="search-td-label">会员昵称：</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="nick" name="nick">
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">手机号码：</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="mobile" name="mobile">
						</div>
					</div>
					<div class="search-td" style="width: 40%;">
						<div class="search-td-label" style="float: left;width: 20%;">
							<select id="timeType" name="timeType">
								<option value="1">注册时间</option>
								<option value="2">最后登录时间</option>
								<option value="3">首次消费时间</option>
								<option value="4">最后消费时间</option>
							</select>：
						</div>
						<div class="l-panel-search-item" >
							<input type="text" id="date_begin" name="date_begin" class="dateEditor" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
						<div class="l-panel-search-item">
							<input type="text" id="date_end" name="date_end" class="dateEditor" style="width: 135px;" />
						</div>
					</div>
					<div class="search-td-search-btn" style="display: inline-flex;">
						<div id="search">
							<input type="button" class="l-button" onclick="seeFunction();" value="搜索">
						</div>
						<div id="searchbtn" style="display: none;"></div>
					</div>
				</div>
			</c:if>
			<c:if test="${type eq 5}">
				<div class="search-tr">
					<div class="search-td">
						<div class="search-td-label">会员ID：</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="id" name="id">
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">会员昵称：</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="nick" name="nick">
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">手机号码：</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="mobile" name="mobile">
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">会员组：</div>
						<div class="search-td-combobox-condition" >
							<select id="groupId" name="groupId" style="width: 135px;">
								<option value="">请选择...</option>
								<c:forEach var="list" items="${memberGroups}">
									<option value="${list.id}">${list.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="search-tr">
					<div class="search-td" style="width: 40%;">
						<div class="search-td-label" style="float: left;width: 20%;">注册时间：</div>
						<div class="l-panel-search-item" >
							<input type="text" id="createDateBegin" name="createDateBegin" class="dateEditor" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
						<div class="l-panel-search-item">
							<input type="text" id="createDateEnd" name="createDateEnd" class="dateEditor" style="width: 135px;" />
						</div>
					</div>

					<div class="search-td" style="width: 40%;">
						<div class="search-td-label" style="float: left;width: 20%;">最后登录时间：</div>
						<div class="l-panel-search-item" >
							<input type="text" id="loginDateBegin" name="loginDateBegin" class="dateEditor" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
						<div class="l-panel-search-item">
							<input type="text" id="loginDateEnd" name="loginDateEnd" class="dateEditor" style="width: 135px;" />
						</div>
					</div>
				</div>
				<div class="search-tr">
					<div class="search-td" style="width: 40%;">
						<div class="search-td-label" style="float: left;width: 20%;">首次消费时间：</div>
						<div class="l-panel-search-item" >
							<input type="text" id="firstPayDateBegin" name="firstPayDateBegin" class="dateEditor" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
						<div class="l-panel-search-item">
							<input type="text" id="firstPayDateEnd" name="firstPayDateEnd" class="dateEditor" style="width: 135px;" />
						</div>
					</div>
					<div class="search-td" style="width: 40%;">
						<div class="search-td-label" style="float: left;width: 20%;">最后消费时间：</div>
						<div class="l-panel-search-item" >
							<input type="text" id="lastPayDateBegin" name="lastPayDateBegin" class="dateEditor" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
						<div class="l-panel-search-item">
							<input type="text" id="lastPayDateEnd" name="lastPayDateEnd" class="dateEditor" style="width: 135px;" />
						</div>
					</div>
				</div>
				<div class="search-tr">
					<div class="search-td" style="width: 40%;">
						<div class="search-td-label" style="float: left;width: 20%;">消费时间：</div>
						<div class="l-panel-search-item" >
							<input type="text" id="comPayDateBegin" name="comPayDateBegin" class="dateEditor" style="width: 135px;"/>
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
						<div class="l-panel-search-item">
							<input type="text" id="comPayDateEnd" name="comPayDateEnd" class="dateEditor" style="width: 135px;"/>
						</div>
                        <span style="color:red;display:none;" class="comPayDateSpan">请输入取值范围！</span>
					</div>
					<div class="search-td">
						<div class="search-td-label">本期消费金额：</div>
						<div class="search-td-condition" style="display: inline-flex;">
							<div style="float: left;"><input type="text" id="partPayOrderAmountMin" name="partPayOrderAmountMin"></div>
							<div style="width: 25px;">--</div>
							<div style="float: left;"><input type="text" id="partPayOrderAmountMax" name="partPayOrderAmountMax"></div>
						</div>
                        <span style="color:red;display:none;" class="partPayOrderAmount">请输入消费时间范围！</span>
					</div>
					<div class="search-td">
						<div class="search-td-label">本期消费订单数：</div>
						<div class="search-td-condition" style="display: inline-flex;">
							<div style="float: left;"><input type="text" id="partPayOrderCountMin" name="partPayOrderCountMin"></div>
							<div style="width: 25px;">--</div>
							<div style="float: left;"><input type="text" id="partPayOrderCountMax" name="partPayOrderCountMax"></div>
						</div>
					</div>
				</div>
				<div class="search-tr">
					<div class="search-td">
						<div class="search-td-label">累积消费金额：</div>
						<div class="search-td-condition" style="display: inline-flex;">
							<div style="float: left;"><input type="text" id="payOrderAmountMin" name="payOrderAmountMin"></div>
							<div style="width: 25px;">--</div>
							<div style="float: left;"><input type="text" id="payOrderAmountMax" name="payOrderAmountMax"></div>
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">累积消费订单数：</div>
						<div class="search-td-condition" style="display: inline-flex;">
							<div style="float: left;"><input type="text" id="payOrderCountMin" name="payOrderCountMin"></div>
							<div style="width: 25px;">--</div>
							<div style="float: left;"><input type="text" id="payOrderCountMax" name="payOrderCountMax"></div>
						</div>
					</div>
					<div class="search-td">
<%--					<div class="search-td-label" >--%>
<%--					<c:if test="${partStatus eq 1}"><input type="checkbox" checked="checked" id = "partStatus" name="partStatus"></div></c:if>--%>
<%--					<c:if test="${partStatus eq 2}"><input type="checkbox" id = "partStatus" name="partStatus"></div></c:if>--%>
<%--						<div class="search-td-condition" >--%>
<%--							显示本期消费金额/订单数--%>
<%--						</div>--%>
<%--					</div>--%>
					<div class="search-td-search-btn" style="display: inline-flex;">
						<div >
							<input id="search" type="button" class="l-button" value="搜索">
                            <input id="seeFunctionId" type="hidden" class="l-button" onclick="seeFunction();" value="搜索">
						</div>
						<div id="searchbtn" style="display: none;"></div>
						<div style="padding-left: 10px;">
							<input type="button" class="l-button" value="导出Excel" id="partExportExcel">
							<input type="hidden" class="l-button" value="导出Excel" id="exportExcel">
						</div>
					</div>
				</div>
			</c:if>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js">
</script>