<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	
<link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>	

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

td input,td select{
border:1px solid #AECAF0;
}
.radioClass{
	margin-right: 20px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}

.table-title-link{
color: #1B17EE;
font-size: 15px;
text-decoration: none;
}

.table-title{
font-size: 17px;font-weight: 600;
}

.td-width{
	width:100px;
}
</style>
<style type="text/css">
.middle input {
	display: block;
	width: 30px;
	margin: 2px;
}
table.l-checkboxlist-table td{
	border-style: none;
}
table.l-listbox-table td{
	border-style: none;
}
.td-pictures li{
display: inline-block;
}
td img{
width: 60px;
height: 40px;
}
</style>
<script type="text/javascript">
$(function(){
	Date.prototype.format = function(fmt)   
	{ //author: meizz   
	  var o = {   
	    "M+" : this.getMonth()+1,                 //月份   
	    "d+" : this.getDate(),                    //日   
	    "h+" : this.getHours(),                   //小时   
	    "m+" : this.getMinutes(),                 //分   
	    "s+" : this.getSeconds(),                 //秒   
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
	    "S"  : this.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;   
	} ;
	
	if(${mchtContract.renewProStatus ge 3}){
		$('#radio1').attr("disabled",true);
		$('#radio2').attr("disabled",true);
	}
	
	  $(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
	  });
	
	//监听是否显示店铺关店原因
	$("#shopCloseReasonTr").show();
	$("input:radio[name='renewProStatus']").change(function (){
		if($(this).val() == 5){
			$('#shopCloseReasonTr').show();
		}else{
			$('#shopCloseReasonTr').hide();
		}		
	});
	
	//如果查看情况下，是店铺暂停的话就显示关店原因
	if(${yyTAG == true} && ${mchtContract.renewProStatus eq 5}){
		$('#shopCloseReasonTr').show();
	}
	if(${yyTAG == false} && ${mchtContract.renewProStatus eq 5}){
		$('#shopCloseReasonTrText').show();
	}
	var brandGrid = $("#maingridBrand").ligerGrid({
        columns: [
			{ display: '品牌库名称', name: 'brandName'},       
        	{ display: '技术服务费率', name: 'popCommissionRate'},
        	{ display: '授权有效期', name: 'platformAuthExpDate',render: function(rowdata, rowindex) {
        		var platformAuthExpDate=new Date(rowdata.platformAuthExpDate);
            	return platformAuthExpDate.format("yyyy-MM-dd");
		 	}},
		 	{ display: '运营状态', name: 'statusDesc'},
		 { display: '招商标记', name: 'statusDesc', render: function(rowdata, rowindex) {
				var html = [];
				html.push(rowdata.isSpeciallyApprovedDesc);
				html.push("<a href=\"javascript:editOrShowMchtProductBrandApproved(" + rowdata.id + ", 2);\">【查看】</a>&nbsp;&nbsp;");
			    return html.join("");
		 }},
		 { display: '是否参与特卖', name: 'isActivity', render: function(rowdata, rowindex) {
				var html = [];
				var isActivityStr = rowdata.isActivity == 1 ? '是': '否';
				html.push(isActivityStr);
			    return html.join("");
		 }},
		 { display: '品牌GMV', name: 'brandGMV'},
        ],  pageSize:5,pageSizeOptions:[5,10,20,50,100],
        url:'${pageContext.request.contextPath}/mcht/mchtProductBrandData.shtml?mchtId=${mchtInfo.id}',
        width: '100%',
        onAfterShowData: function() { 
				$(".l-grid-row-cell-inner").css("height", "auto");
				var i = 0;
//				$("tr",".l-grid2","#"+listModel.container.listGridName+"").each(function() {
//					$($("tr",".l-grid1","#"+listModel.container.listGridName+"")[i]).css("height",$(this).height());
//					i++;
//				});
				}
    });
	
	$("#confirm").bind('click',function(){
		var renewProStatus =$("input[name='renewProStatus']:checked").val();
		var zsNotRenewRemarks = $("#zsNotRenewRemarks").val();
		var zsRenewInnerRemarks = $("#zsRenewInnerRemarks").val();
		if(!zsRenewInnerRemarks){
			commUtil.alertError("内部备注不可为空");
			return;
		}
		if(renewProStatus == ""){
			commUtil.alertError("确认状态不可为空");
			return;
		}
		//选择不续签时，不续签备注为必填
		if(renewProStatus == '4' && !zsNotRenewRemarks){
			commUtil.alertError("不续签招商备注不可为空");
			return;
		}
		var dataJson = $("#form1").serializeArray();
		dataJson.push({"name":"id",value:JSON.stringify($('#id').val())});
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtContract/auditSubmit.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : dataJson,
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					$.ligerDialog.alert('提交成功！', function (yes) { 
						window.location.reload();
					});		
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
				
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
	
	
	$("#fwConfirm").bind('click',function(){
		var renewRemarks = $("#renewRemarks").val();
		var beginDate = $("#beginDate").val();
		var endDate = $("#endDate").val();
		if(beginDate == "" || endDate == ""){
			commUtil.alertError("开始或结束日期不可为空");
			return;
		}
		if(!renewRemarks){
			commUtil.alertError("备注不可为空");
			return;
		}
		var dataJson = $("#form1").serializeArray();
		dataJson.push({"name":"id",value:JSON.stringify($('#id').val())});
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtContract/fwAuditSubmit.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data :  dataJson,
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					$.ligerDialog.alert('提交成功！', function (yes) { 
						window.location.reload();
					});		
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
				
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
		
	$("#yyConfirm").bind('click',function(){
		var renewProStatus =$("input[name='renewProStatus']:checked").val();
		if(renewProStatus != "5" && renewProStatus != "6"){
			commUtil.alertError("确认状态不可为空");
			return;
		}
		var shopCloseReason = $("#shopCloseReason").val();
		if(!shopCloseReason && renewProStatus == "5" ){
			commUtil.alertError("店铺关店原因不可为空");
			return;
		}
		var dataJson = $("#form1").serializeArray();
		dataJson.push({"name":"id",value:JSON.stringify($('#id').val())});
		dataJson.push({"name":"shopCloseReason",value:JSON.stringify(shopCloseReason)});
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtContract/yyAuditSubmit.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data :  dataJson,
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					$.ligerDialog.alert('提交成功！', function (yes) { 
						parent.location.reload();
					});		
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

//查看
function editOrShowMchtProductBrandApproved(mchtProductBrandId, statusPage) {
	$.ligerDialog.open({
		height: 400,
		width: 500,
		title: "招商标记",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/editOrShowMchtProductBrandApproved.shtml?mchtProductBrandId=" + mchtProductBrandId +"&statusPage=" + statusPage,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

// 公司资质归档状态查看
function viewCompanyInfoArchiveChgs(chgId) {
    $.ligerDialog.open({
        height: $(window).height() * 0.9,
        width: $(window).width() * 0.9,
        title: "公司资质归档",
        name: "INSERT_WINDOW",
        url: "${pageContext.request.contextPath}/mchtContract/viewCompanyInfoArchiveChgs.shtml?chgId=" + chgId,
        showMax: true,
        showToggle: false,
        showMin: false,
        isResize: true,
        slide: false,
        data: null,
        id: "closeReload"
    });
}

//经营许可证查看
function toArchive(id) {
	$.ligerDialog.open({
		height: 600,
		width: 800,
		title: "归档",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtLicenseChg/toArchive.shtml?id=" + id+ "&hideRadio=1",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

//品牌资质归档查看
function viewBrandChgPics(mchtBrandChgId){
	 $.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 200,
   		title: "品牌资质归档情况",
   		name: "INSERT_WINDOW",
   		url: "${pageContext.request.contextPath}/mcht/viewBrandChgPics.shtml?id=" + mchtBrandChgId+ "&hideRadio=1",
   		showMax: true,
   		showToggle: false,
   		showMin: false,
   		isResize: true,
   		slide: false,
   		data: null
	}); 
}
</script>

<body>
<!-- 商家合作信息 -->
<input type="hidden" id="id" name="id" value="${id}">
	<c:if test="${type eq 1}">
		<div>
			<span class="table-title" >商家销量情况</span>
		</div>
		<br>
		<table class="gridtable" style="width:60%">
			<tr>
				<td  colspan="1" class="title td-width">商家总销量 </td>
				<td  colspan="1" class="title td-width">商家GMV </td>
				<td  colspan="1" class="title td-width">商家退货率 </td>
				<td  colspan="1" class="title td-width">介入率 </td>
				<td  colspan="1" class="title td-width">投诉率 </td>
			</tr>		
			<tr>
				<td  colspan="1" align="center" class="l-table-edit-td">${mchtInfo.salesVolume}</td>
				<td  colspan="1" align="center" class="l-table-edit-td">${mchtInfo.mchtGMV}</td>
				<td  colspan="1" align="center" class="l-table-edit-td">${mchtInfo.returnGoodsRate}</td>
				<td  colspan="1" align="center" class="l-table-edit-td">${mchtInfo.interventionOrderRate}</td>
				<td  colspan="1" align="center" class="l-table-edit-td">${mchtInfo.appealOrderRate}</td>
			</tr>
		</table>	
				
		<br>
		<br>
	</c:if>
		
		<div>
			<span class="table-title" >商家财务信息</span>
		</div>
		<br>
		<table class="gridtable" style="width:36%">
			<tr>
				<td  colspan="1" class="title td-width">抵扣模式 </td>
				<td  colspan="1" class="title td-width">店铺保证金</td>
				<td  colspan="1" class="title td-width">已缴保证金</td>
			</tr>		
			<tr>
				<td  colspan="1" align="center" class="l-table-edit-td">${mchtInfo.depositTypeDesc}</td>
				<td  colspan="1" align="center" class="l-table-edit-td">${mchtInfo.contract_deposit}</td>
				<td  colspan="1" align="center" class="l-table-edit-td">${mchtInfo.payAmt}</td>
			</tr>
		</table>	
		
		<br>
		<br>
		<div>
			<span class="table-title" >商家品牌信息</span>
		</div>
		<br>
		<form id="dataFormBrand" runat="server">
			<div id="maingridBrand" style="margin: 0; padding: 0"></div>
		</form>
	
	<c:if test="${type eq 2}">
		<br>
		<br>
		<div>
			<span class="table-title" >商家资料归档情况</span>
		</div>
		<br>
		<table class="gridtable" style="width:36%">
			<tr>
				<td  colspan="1" class="title td-width">公司资质归档情况 </td>
				<td  colspan="1" class="title td-width">品牌资质归档情况</td>
				<td  colspan="1" class="title td-width">经营许可证归档情况</td>
			</tr>		
			<tr>
			
				<td  colspan="1" align="center" class="l-table-edit-td">				
				<c:forEach items="${mchtInfoChgCustoms}" var="mchtInfoChgCustom">
				<c:choose>
					<c:when test="${empty mchtInfoChgCustom.companyInfoArchiveStatus || mchtInfoChgCustom.companyInfoArchiveStatus == 0}">
						【未齐全】<a href="javascript:viewCompanyInfoArchiveChgs(${mchtInfoChgCustom.id});">【查看】</a><br>	
					</c:when>
					<c:otherwise>
						【已齐全】<a href="javascript:viewCompanyInfoArchiveChgs(${mchtInfoChgCustom.id});">【查看】</a><br>			
					</c:otherwise>
				</c:choose>
				</c:forEach>	
				</td>	
							
				<td  colspan="1" align="center" class="l-table-edit-td">
				<c:forEach items="${mchtBrandChgCustoms}" var="mchtBrandChgCustom">
				<c:choose>
					<c:when test="${empty mchtBrandChgCustom.archiveStatus || mchtBrandChgCustom.archiveStatus == 3}">
						【未齐全】${mchtBrandChgCustom.productBrandName}<a href="javascript:;" onclick="viewBrandChgPics(${mchtBrandChgCustom.id})">【查看】</a><br>	
					</c:when>
					<c:otherwise>
						【已齐全】${mchtBrandChgCustom.productBrandName}<a href="javascript:;" onclick="viewBrandChgPics(${mchtBrandChgCustom.id})">【查看】</a><br>			
					</c:otherwise>
				</c:choose>
				</c:forEach>
				</td>
					
				<td  colspan="1" align="center" class="l-table-edit-td">
				<c:forEach items="${mchtLicenseChgCustoms}" var="mchtLicenseChgCustom">
				<c:choose>
					<c:when test="${mchtLicenseChgCustom.archiveStatus ne 3}">
						【未齐全】<a href="javascript:;" onclick="toArchive(${mchtLicenseChgCustom.id});">【查看】</a><br>	
					</c:when>
					<c:otherwise>
						【已齐全】<a href="javascript:;" onclick="toArchive(${mchtLicenseChgCustom.id});">【查看】</a><br>			
					</c:otherwise>
				</c:choose>
				</c:forEach>
				</td>
			</tr>
		</table>
	</c:if>
	
	<c:if test="${type eq 2}">
		<br>
		<br>
		<div><span class="table-title" >法务确认</span></div>
		<br>
		<form method="post" id="form1" name="form1" style="width:46%;">
		<div class="title-top">
		<table class="gridtable">
          	<tr>
          		<td class="title">合同开始时间</td>
          		<c:if test="${fwTAG == false}">
          			<td><fmt:formatDate value="${mchtContract.beginDate}" pattern="yyyy-MM-dd"/></td>
          		</c:if>
          		<c:if test="${fwTAG == true}">
          			<td><input type="text" id="beginDate" name="beginDate" style="width: 135px;" value="${beginDate}"/></td>
          			<script type="text/javascript">
					$(function() {
						$("#beginDate").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
					</script>
          		</c:if>
          	</tr>
          	<tr>
          		<td class="title">合同结束时间</td>
          		<c:if test="${fwTAG == false}">
          			<td><fmt:formatDate value="${mchtContract.endDate}" pattern="yyyy-MM-dd"/></td>
          		</c:if>
          		<c:if test="${fwTAG == true}">
          			<td><input type="text" id="endDate" name="endDate" class="dateEditor" style="width: 135px;" value="${endDate}"/></td>
          				<script type="text/javascript">
						$(function() {
							$("#endDate").ligerDateEditor( {
								showTime : false,
								labelWidth : 150,
								width:150,
								labelAlign : 'left'
							});
						});
					</script>
          		</c:if>
          	</tr>
          	<tr>
          		<td class="title">店铺保证金</td>
          		<td>${mchtInfo.contract_deposit}</td>
          	</tr>
          	<tr>
          		<td class="title">备注<span style="color:red;">*</span></td>
          		<td><textarea rows="5" cols="90" id="renewRemarks" name="renewRemarks" maxlength="256">${mchtContract.renewRemarks}</textarea></td>
          	</tr>
          	<c:if test="${fwTAG == true && mchtContract.renewProStatus eq 3}">
          		<tr>
          			<td class="title">操作</td>
          			<td>
						<input id="fwConfirm" type="button" style="float:left;" class="l-button l-button-test" value="提交"/>
					</td>
          		</tr>
          	</c:if>
        </table>
		</div>
		</form>
	</c:if>
	
	<c:if test="${type eq 1}">
		<br>
		<br>
		<div><span class="table-title" >招商确认续签</span></div>
		<br>
		<form method="post" id="form1" name="form1" style="width:46%;">
		<div class="title-top">
		<table class="gridtable">
		  <c:if test="${zsTAG == true}">
          	<tr>
          		<td class="title">确认状态</td>
          		<td><input type="radio" id="radio1" name="renewProStatus" value="3" <c:if test="${mchtContract.renewProStatus ge 3  && mchtContract.renewProStatus ne 4}">checked="checked"</c:if>>
					确认续签
          		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          		<input type="radio" id="radio2" name="renewProStatus" value="4" <c:if test="${mchtContract.renewProStatus eq 4}">checked="checked"</c:if>>
          			不予续签
          		</td>
          	</tr>
          </c:if>
          <c:if test="${zsTAG == false}">
          		<tr>
          			<td class="title" style="width:200px;">确认状态</td>
          			<td><c:if test="${mchtContract.renewProStatus ge 3 && mchtContract.renewProStatus ne 4}">确认续签</c:if><c:if test="${mchtContract.renewProStatus eq 4}">不予续签</c:if></td>
          		</tr>
          </c:if>
           <c:if test="${zsTAG == true}">	
          		<tr>
          			<td class="title">不续签招商备注</td>
          			<td><textarea rows="5" cols="90" id="zsNotRenewRemarks" name="zsNotRenewRemarks" <c:if test="${mchtContract.renewProStatus ge 3}">disabled</c:if> maxlength="256">${mchtContract.zsNotRenewRemarks}</textarea></td>
          		</tr>
           </c:if>
           
          	<tr>
          		<td class="title">内部备注<span style="color:red;">*</span></td>
          		<td><textarea rows="5" cols="90" id="zsRenewInnerRemarks" name="zsRenewInnerRemarks" <c:if test="${mchtContract.renewProStatus ge 3 || zsTAG == false}">disabled</c:if> maxlength="256">${mchtContract.zsRenewInnerRemarks}</textarea></td>
          	</tr>
          	<c:if test="${zsTAG == true && mchtContract.renewProStatus eq 2}">
          		<tr>
          			<td class="title">操作</td>
          			<td>
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-test" value="提交"/>
					</td>
          		</tr>
          	</c:if>
        </table>
		</div>
		</form>
	</c:if>	
	
	<c:if test="${type eq 3}">
		<br>
		<br>
		<div><span class="table-title" >运营结果</span></div>
		<br>
		<form method="post" id="form1" name="form1" style="width:46%;">
		<div class="title-top">
		<table class="gridtable">
		  <c:if test="${yyTAG == true}">
          	<tr>
          		<td class="title">确认状态</td>
          		<td><input type="radio" name="renewProStatus" value="5" checked="checked">
					确认不续签(店铺暂停)
          		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          		<input type="radio" name="renewProStatus" value="6" <c:if test="${mchtContract.renewProStatus eq 4}">disabled</c:if>>
          			开放续签入口
          		</td>
          	</tr>
          </c:if>
          <tr>
          <c:if test="${yyTAG == false}">
          		<tr>
          			<td class="title" style="width:200px;">确认状态</td>
          			<td><c:if test="${mchtContract.renewProStatus eq 5}">店铺暂停</c:if><c:if test="${mchtContract.renewProStatus eq 6}">开放续签入口</c:if></td>
          		</tr>
          </c:if>
          <c:if test="${yyTAG == false}">
           <tr id="shopCloseReasonTrText" style="display:none;">
          		<td class="title">店铺关店原因<span style="color:red;">*</span></td>
          		<td>${mchtContract.shopCloseReasonDesc}</td>
          </tr>
          </c:if>
          <c:if test="${yyTAG == true}">
          <tr id="shopCloseReasonTr" style="display:none;">
          		<td class="title">店铺关店原因<span style="color:red;">*</span></td>
          		<td>
          			<select id="shopCloseReason" name="shopCloseReason">
						<option value="">请选择</option>
						<option value="0" <c:if test="${mchtContract.shopCloseReason eq 0}">selected="selected"</c:if>>因公司业务调整</option>
						<option value="1" <c:if test="${mchtContract.shopCloseReason eq 1}">selected="selected"</c:if>>因业务合作到期</option>
						<option value="2" <c:if test="${mchtContract.shopCloseReason eq 2}">selected="selected"</c:if>>因商家违反平台相关原因规则，根据平台相关规定</option>
					</select>
          		</td>
          </tr>
          </c:if>          	
          	<c:if test="${yyTAG == true && (mchtContract.renewProStatus eq 1 || mchtContract.renewProStatus eq 4)}">
          		<tr>
          			<td class="title">操作</td>
          			<td>
						<input id="yyConfirm" type="button" style="float:left;" class="l-button l-button-test" value="提交"/>
					</td>
          		</tr>
          	</c:if>
        </table>
		</div>
		</form>
	</c:if>	
		<br>
		<br>
		<div><span class="table-title" >合同续签记录</span></div>
		<br>
		<table class="gridtable" style="width:75%">
			<tr>
				<td  colspan="1" class="title td-width">时间</td>
				<td  colspan="1" class="title td-width">状态</td>
				<td  colspan="1" class="title" style="width:300px;">内容</td>
			</tr>
			<c:forEach items="${contractRenewLogs}" var="contractRenewLog" >	
			<tr>
				<td  colspan="1" align="center" class="l-table-edit-td"><fmt:formatDate value="${contractRenewLog.statusDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				<c:if test="${contractRenewLog.status eq 7}">
				<td  colspan="1" align="center" class="l-table-edit-td">法务确认续签</td>
				</c:if>
				<c:if test="${contractRenewLog.status ne 7}">
				<td  colspan="1" align="center" class="l-table-edit-td">${contractRenewLog.statusDesc}</td>
				</c:if>
				<td  colspan="1" align="center" class="l-table-edit-td">${contractRenewLog.content}</td>
			</tr>
			</c:forEach>	
		</table>	
		
</body>
</html>
