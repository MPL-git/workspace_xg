<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${pageContext.request.contextPath}/css/glyphicon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

.radioClass{
	margin-right: 20px;
}

.l-table-edit-td {
	padding: 4px;
}

.table-title{
font-size: 17px;font-weight: 600;
}
.center-align{
	text-align: center;
}
</style>

<script type="text/javascript">

	$(function(){
		var logGrid = $("#maingridAddress").ligerGrid({
		    columns: [
				{ display: '收货人',  name: 'recipient'},
				{ display: '收货电话',  name: 'phone'},
				{ display: '收货地址',  name: 'fullAddress'},
				{ display: '邮编', name: 'zipCode'},
				{ display: '是否默认',  name: 'isPrimary', render: function(rowdata, rowindex) {
						if (rowdata.isPrimary=="Y"){
							return "是";
						}else{
							return "否";
						}
				}}
		    ],  pageSize:5,pageSizeOptions:[5,10,20,50,100],
		    url:'${pageContext.request.contextPath}/member/address/dataList.shtml?memberId=${memberInfoCustom.id}',
		    width: '100%'
		});
		var logGrid = $("#subOrderList").ligerGrid({
		    columns: [
				{ display: '子订单号', name: 'subOrderCode', render: function(rowdata, rowindex) {
					var html = [];
					html.push("<a href=\"javascript:viewDetail(" + rowdata.id + ");\">"+rowdata.subOrderCode+"</a>"); 
					return html.join("");
				}},
				{ display: '订单类型', name: 'orderTypeDesc'},
				{ display: '下单时间', name: '', render: function(rowdata, rowindex) {
					if (rowdata.orderCreateDate != null){
						var orderCreateDate = new Date(rowdata.orderCreateDate);
						return orderCreateDate.format("yyyy-MM-dd hh:mm:ss");		                		
                	}
				}},
				{ display: '付款时间', name: '', render: function(rowdata, rowindex) {
					if (rowdata.orderPayDate != null){
						var orderPayDate = new Date(rowdata.orderPayDate);
						return orderPayDate.format("yyyy-MM-dd hh:mm:ss");
                	}
				}},
				{ display: '实付金额', name: 'payAmount'},
				{ display: '用户ID', name: 'memberId', render: function(rowdata, rowindex) {
					var html = [];
					if (rowdata.memberId == ${memberInfoCustom.id }){
						html.push("<span style='color: red;'>"+ rowdata.memberId +"</span>");
                	}else {
                		html.push(rowdata.memberId);
                		if(${sessionScope.USER_SESSION.staffID } == 1 || ${canOperate} == 1 ) {
	                		if(rowdata.memberInfoStatus != 'P') {
	                			html.push("<a href=\"javascript:limitPermission("+ rowdata.memberId +", '1');\">【加入黑名单】</a>");
	                		}else {
	                			html.push("<a href=\"javascript:limitPermission("+ rowdata.memberId +", '2');\">【查看详情】</a>");
	                			html.push("<a href=\"javascript:limitPermission("+ rowdata.memberId +", '3');\">【移出黑名单】</a>");
	                		}
                		}
                	}
					return html.join("");
				}},
				{ display: '收货人', name: 'receiverName'},
				{ display: '收货地址', name: 'receiverAddress'},
				{ display: '收货电话', name: 'receiverPhone', render: function(rowdata, rowindex) {
					var html = [];
					if (rowdata.receiverPhone == '${memberInfoCustom.mobile }'){
						html.push("<span style='color: red;'>"+ rowdata.receiverPhone +"</span>");
                	}else {
                		html.push(rowdata.receiverPhone);
                	}
					return html.join("");
				}},
				{ display: 'IMEI', name: 'reqImei', render: function(rowdata, rowindex) {
					var html = [];
					if (rowdata.reqImei == '${memberInfoCustom.reqImei }'){
						html.push("<span style='color: red;'>"+ rowdata.reqImei +"</span>");
                	}else {
                		html.push(rowdata.reqImei);
                	}
					return html.join("");
				}} 
		    ],  pageSize:5,pageSizeOptions:[5,10,20,50,100],
		    url:'${pageContext.request.contextPath}/member/integralGive/subOrderList.shtml?memberId=${memberInfoCustom.id}',
		    width: '100%'
		});
		
		var logGrid = $("#impeachMemberList").ligerGrid({
		    columns: [
				{display:'举报时间',name:'', align:'center',render:function(rowdata, rowindex){
						var html = [];
						if(rowdata.createDate != null && rowdata.createDate != '' ) {
						var createDate = new Date(rowdata.createDate);
							html.push(createDate.format("yyyy-MM-dd hh:mm"));
					   }
						return html.join("");
				}},
				{display:'举报编号',name:'code', align:'center',render:function(rowdata, rowindex){
					var html = [];
					html.push("<a href=\"javascript:imPeachSee(" + rowdata.id + ");\">"+rowdata.code+"</a>"); 
					return html.join("");
					
				}},
				{display:'创建者',name:'', align:'center',render:function(rowdata, rowindex) {
	                 if (rowdata.source=='0') {
	                	  return "商家";
					 }else if (rowdata.source=='1') {
						 return "平台";
					}
	            }},
	            {display:'创建者信息',name:'', align:'center',render:function(rowdata, rowindex){
	        		var html = [];
	        	   if (rowdata.mchtId!=null && rowdata.mchtId != '') {
                        html.push(rowdata.mchtCode+"<br>");
                        html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.mchtId + ");\">"+rowdata.companyName+"</a>");
                        
				   }else if (rowdata.staffName!=null && rowdata.staffName != '') {
					   html.push(rowdata.staffName);
				   }
	        	   return html.join("");
	           }},
	            {display:'举报类型',name:'typeDesc', align:'center'},
	            {display:'举报场景',name:'', align:'center', isSort:false, width:200,render:function(rowdata, rowindex){
		        	   if (rowdata.type=='1') {
		        		   return rowdata.scenedesc1;
					  }else if (rowdata.type=='2') {
						  return rowdata.scenedesc2;
					  }else if (rowdata.type=='3') {
						  return rowdata.scenedesc3;
					}
		          }},
		       {display:'限制行为',name:'', align:'center',render:function(rowdata, rowindex){
					   var limitMemberAction=String(rowdata.limitMemberAction);
					   var limitFunctionStatus="";
					   if (limitMemberAction!=null) {
						   if (limitMemberAction.charAt(0)=="1"){
		 						limitFunctionStatus+="限制评价"+"<br>";
							}
		                    if (limitMemberAction.charAt(2)=="2"){
		                    	limitFunctionStatus+="限制购买"+"<br>";
							}
		                    if (limitMemberAction.charAt(4)=="3"){
		                    	limitFunctionStatus+="限制登入"+"<br>";
							}
		                    return limitFunctionStatus;
					}
				}}, 
		    ],pageSize:5,pageSizeOptions:[5,10,20,50,100],
		    url:'${pageContext.request.contextPath}/member/impeachMemberList.shtml?memberId=${memberInfoCustom.id}',
		    width: '100%'
		});

		var logGrid = $("#memberLabelList").ligerGrid({
		    columns: [
				{ display: '标签类型',  name: 'memberlabelTypeName'},
				{ display: '标签',  name: 'memberlabelName'},
				{ display: '打标人',  name: 'staffName'},
				{ display: '打标时间', name: '', render: function(rowdata, rowindex) {
					if (rowdata.createDate != null){
						var createDate = new Date(rowdata.createDate);
						return createDate.format("yyyy-MM-dd hh:mm:ss");
                	}
				}},
		    ],  pageSize:5,pageSizeOptions:[5,10,20,50,100],
		    url:'${pageContext.request.contextPath}/member/MemberLabelRelationList.shtml?memberId=${memberInfoCustom.id}',
		    width: '100%'
		});
	});

	// 加入或取消黑名单
	function updateMemberStatus(memberId, memberStatus) {
		var str = '确认将该用户加入黑名单吗？';
		if(memberStatus != 'P') {
			str = '确认将该用户移出黑名单吗？';
		}
		$.ligerDialog.confirm(str,  function (yes){
	       	 if(yes == true){
	       		$.ajax({
					url : "${pageContext.request.contextPath}/member/integralGive/updateMemberStatus.shtml",
					type : 'POST',
					dataType : 'json',
					data : {memberId : memberId, memberStatus : memberStatus},
					success : function(data) {
						if (data.statusCode == '200') {
							location.reload();
						}else{
							commUtil.alertError(data.message);
						}
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
	       	 }
	     }); 
	}

	// 子订单详情
	function viewDetail(id) {
		$.ligerDialog.open({
	 		height: $(window).height(),
			width: $(window).width()-50,
			title: "子订单详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/order/sub/detail.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	// 加入黑名单/查看详情
	function limitPermission(memberId, operateType) {
		var dialogTitle = "黑名单详情";
		if (operateType === '1') {
			dialogTitle = "加入黑名单";
		} else if (operateType === '3') {
			dialogTitle = "移出黑名单";
		}
		$.ligerDialog.open({
	 		height: $(window).height(),
			width: $(window).width()-50,
			title: dialogTitle,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/member/limitPermission.shtml?memberId=" + memberId + "&operateType=" + operateType,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	//查看会员登陆日志详情

	function loginLogInfo(memberId) {
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width()-50,
			title: "登陆日志详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/member/info/logList.shtml?memberId=" + memberId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	function imPeachSee(id) {//查看会员举报详情
		$.ligerDialog.open({
	 		height: $(window).height(),
			width: $(window).width()-50,
			title: "查看详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/imPeach/impeachExamine.shtml?id=" + id+"&imPeachType=6",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
</script>

<html>
<body>
		<table class="gridtable">
			<tr>
				<td class="title">会员ID</td>
				<td align="left" class="l-table-edit-td">
					${memberInfoCustom.id}
						<c:if test="${memberInfoCustom.status == 'P' }">
							<span style="color: red;">已加入黑名单</span>
						</c:if>
						<c:if test="${memberInfoCustom.status == 'P' }">
							<a href="javascript:limitPermission(${memberInfoCustom.id}, '2');">【查看详情】</a>
						</c:if>
					<c:if test="${sessionScope.USER_SESSION.staffID == 1 or canOperate == 1 }">
						<c:if test="${memberInfoCustom.status == 'P' }">
							<a href="javascript:limitPermission(${memberInfoCustom.id}, '3');">【移出黑名单】</a>
						</c:if>
						<c:if test="${memberInfoCustom.status != 'P' }">
							<a href="javascript:limitPermission(${memberInfoCustom.id}, '1');">【加入黑名单】</a>
						</c:if>
					</c:if>
				</td>
				<td class="title">邮箱</td>
				<td align="left" class="l-table-edit-td"><input disabled="disabled" value="${memberInfoCustom.email}"/></td>
			</tr>
			<tr>
				<td class="title">会员昵称</td>
				<td align="left" class="l-table-edit-td">
					<c:if test="${memberInfoCustom.nick == null}">醒购会员</c:if>
				    <c:if test="${memberInfoCustom.nick != null}">${memberInfoCustom.nick}</c:if>
					<c:if test="${not empty memberInfoCustom.columnTypeDesc and memberInfoCustom.columnTypeDesc != '' }">
						<span style="color: red;">(栏目来源：${memberInfoCustom.columnTypeDesc })</span>
					</c:if>
				</td>
				<td class="title">性别</td>
				<td align="left" class="l-table-edit-td"><select disabled="disabled"><option >${memberInfoCustom.sexDesc }</option></select></td>
			</tr>
			<tr>
				<td class="title">用户名</td>
				<td align="left" class="l-table-edit-td">${memberInfoCustom.loginCode }</td>
				<td class="title">出生年月</td>
				<td align="left" class="l-table-edit-td"><input disabled="disabled" value="<fmt:formatDate value="${memberInfoCustom.birthday }" pattern="yyyy年MM月dd日"/>" /></td>
			</tr>
			<tr>
				<td class="title">所属会员组</td>
				<td align="left" class="l-table-edit-td"><select disabled="disabled"><option >${memberInfoCustom.groupName }</option></select></td>
				<td class="title">所在地</td>
				<td align="left" class="l-table-edit-td"><select disabled="disabled"><option >${memberInfoCustom.provinceName }</option></select>&nbsp;&nbsp;&nbsp;&nbsp;<select disabled="disabled"><option >${memberInfoCustom.cityName }</option></select></td>
			</tr>
			<tr>
				<td class="title">手机号码</td>
				<td align="left" class="l-table-edit-td"><input disabled="disabled" value="${memberInfoCustom.mobile }"/></td>
				<td class="title">头像</td>
				<td align="left" class="l-table-edit-td">
					<img src='${pageContext.request.contextPath}/file_servelt${memberInfoCustom.pic }' width='60' height='60'>
				</td>
			</tr>
		</table>

		<div><span class="table-title" >&nbsp;</span></div>
		
		<table class="gridtable">
			<tr>
				<td class="title">金币</td>
				<td align="left" class="l-table-edit-td">${memberInfoCustom.integral}</td>
				<td class="title">成长值</td>
				<td align="left" class="l-table-edit-td">${memberInfoCustom.gValue}</td>
			</tr>
			<tr>
				<td class="title">注册时间</td>
				<td align="left" class="l-table-edit-td"><fmt:formatDate value="${memberInfoCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td class="title">注册IP</td>
				<td align="left" class="l-table-edit-td">${memberInfoCustom.regIp}<!-- <span style="color:#CC0000">（福建省厦门市湖里区 电信）</span> --></td>
			</tr>
			<tr>
				<td class="title">最后登陆时间</td>
				<td align="left" class="l-table-edit-td"><fmt:formatDate value="${memberInfoCustom.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td class="title">登陆次数</td>
				<td align="left" class="l-table-edit-td">
					${memberInfoCustom.loginCount}
					<a href="javascript:loginLogInfo(${memberInfoCustom.id});">【查看登陆日志】</a>
				</td>
			</tr>
		</table>
	
		<div><span class="table-title" >&nbsp;</span></div>
		<table class="gridtable">
			<tr>
				<td class="title">订单数量</td>
				<td class="title">商品数量</td>
				<td class="title">退货率</td>
				<td class="title">退款率</td>
				<td class="title">活跃天数</td>
				<td class="title">消费天数</td>
				<td class="title">消费金额</td>
				<td class="title">客单价</td>
				<td class="title">首次消费时间</td>
				<td class="title">最后消费时间</td>
				<td class="title">渠道</td>
				<td class="title">注册端</td>
				<td class="title">联合登录类型和ID</td>
			</tr>
			
			<tr>
				<td class="l-table-edit-td center-align">${memberInfoCustom.payOrderCount}</td>
				<td class="l-table-edit-td center-align">${memberInfoCustom.payProductCount}</td>
				<c:if test="${memberInfoCustom.payOrderCount !=0}">
				<td class="l-table-edit-td center-align">
				<fmt:formatNumber type="number" value="${memberInfoCustom.returnOrderCount/memberInfoCustom.payOrderCount*100.00}" pattern="0.00" maxFractionDigits="2"/>%
				</td>
				</c:if>
				<c:if test="${memberInfoCustom.payOrderCount ==0}"><td class="l-table-edit-td center-align">0.00%</td></c:if>
				<c:if test="${memberInfoCustom.payOrderCount !=0}">
				<td class="l-table-edit-td center-align">
				<fmt:formatNumber type="number" value="${memberInfoCustom.refundOrderCount/memberInfoCustom.payOrderCount*100.00}" pattern="0.00" maxFractionDigits="2"/>%
				</td>
				</c:if>
				<c:if test="${memberInfoCustom.payOrderCount ==0}"><td class="l-table-edit-td center-align">0.00%</td></c:if>
				<td class="l-table-edit-td center-align">${memberInfoCustom.loginDays}</td>
				<td class="l-table-edit-td center-align">${memberInfoCustom.payDays}</td>
				<td class="l-table-edit-td center-align">${memberInfoCustom.payOrderAmount}</td>
				<c:if test="${memberInfoCustom.payOrderCount==0}">
				<td class="l-table-edit-td center-align">0</td>
				</c:if>
				<c:if test="${memberInfoCustom.payOrderCount!=0}">
				<td class="l-table-edit-td center-align"><fmt:formatNumber type="number" value="${memberInfoCustom.payOrderAmount/memberInfoCustom.payOrderCount}" maxFractionDigits="2"/></td>
				</c:if>
				<td class="l-table-edit-td center-align"><fmt:formatDate value="${memberInfoCustom.firstPayTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td class="l-table-edit-td center-align"><fmt:formatDate value="${memberInfoCustom.lastPayTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td class="l-table-edit-td center-align">${memberInfoCustom.sprChnlDesc}</td>
				<td class="l-table-edit-td center-align">${memberInfoCustom.regClientDesc}</td>
				<td class="l-table-edit-td center-align">
				<c:if test="${memberInfoCustom.weixinId!=null}">微信：${memberInfoCustom.weixinId}&nbsp</c:if>
				<c:if test="${memberInfoCustom.memberExtend.qqId!=null}">qq：${memberInfoCustom.memberExtend.qqId}&nbsp</c:if>
				<c:if test="${memberInfoCustom.memberExtend.weiboId!=null}">新浪微博：${memberInfoCustom.memberExtend.weiboId}&nbsp</c:if>
				</td>
			</tr>			
		</table>
		<br>
		<br>
		<div><span class="table-title" >用户标签列表</span></div>
		<br>
		<form id="memberLabel" runat="server"> 
			<div id="memberLabelList" style="margin: 0; padding: 0"></div>
		</form>
		<br>
		<br>
		<div><span class="table-title" >相关订单</span></div>
		<br>
		<form id="order" runat="server"> 
			<div id="subOrderList" style="margin: 0; padding: 0"></div>
		</form>
		<br>
		<br>
		<div><span class="table-title" >收货地址信息</span></div>
		<br>
		<form id="dataFormAddress" runat="server"> 
			<div id="maingridAddress" style="margin: 0; padding: 0"></div>
		</form>
		<br>
		<br>
		<div><span class="table-title" >相关举报单</span></div>
		<br>
		<form id="impeachMember" runat="server"> 
			<div id="impeachMemberList" style="margin: 0; padding: 0"></div>
		</form>
</body>
</html>
