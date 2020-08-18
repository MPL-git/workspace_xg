<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
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
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>		

<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title-link{color: #1B17EE;font-size: 14px;text-decoration: none;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:25px;}
.marR20{margin-right:20px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
.font13{font-size: 13px;}
.font12{font-size: 12px;}
.amt-red{font-size: 25px;color: #FF0000;font-weight: 600;}
</style>
<script type="text/javascript">
function viewerPic(imgPath){
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
	imgPath=imgPath.replace('_S', '');
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	viewerPictures.show();
}

function auditMchtBankAccount(mchtBankAccountId) {
	 
		$.ligerDialog.open({
			height: 600,
			width: 800,
    		title: "商家银行帐号审核",
    		name: "INSERT_WINDOW",
    		url: "${pageContext.request.contextPath}/mcht/mchtBankAccountAudit.shtml?id=" + mchtBankAccountId,
    		showMax: true,
    		showToggle: false,
    		showMin: false,
    		isResize: true,
    		slide: false,
    		data: null,
    		id:"audit_dialog"
		});
	}


function addMchtDepositDtl(depositId) {
	$.ligerDialog.open({
	height: 500,
	width: 800,
	title: "添加保证金记录",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/addMchtDepositDtl.shtml?depositId=" + depositId,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}

function mchtDepositDtl(mchtCode) {
	$.ligerDialog.open({
	height: $(window).height()-150,
	width: $(window).width()-100,
	title: "保证金明细",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mchtDeposit/mchtDepositDtlList.shtml?mchtCode=" + mchtCode,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}
/* function mchtDepositDtl(depositId) {
	$.ligerDialog.open({
	height: $(window).height()-150,
	width: $(window).width()-400,
	title: "保证金明细",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/mchtDepositDtl.shtml?depositId=" + depositId,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
} */

function changeDepositCont(mchtId) {
	$.ligerDialog.open({
	height: 450,
	width: 700,
	title: "修改保证内容",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/changeDepositCont.shtml?mchtId=" + mchtId,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}

function mchtTaxInvoiceInfoEdit(id) {
	$.ligerDialog.open({
	height: $(window).height()-200,
	width: $(window).width()-500,
	title: "税务开票信息修改与审核",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/mchtTaxInvoiceInfoEdit.shtml?id=" +id,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}

function mchtTaxInvoiceInfoAudit(id) {
	$.ligerDialog.open({
	height: 350,
	width: 600,
	title: "税务开票信息审核",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/mchtTaxInvoiceInfoAudit.shtml?id=" +id,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}

function toEditContractDeposit(mchtId) {
	$.ligerDialog.open({
	height: 250,
	width: 400,
	title: "操作",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/toEditContractDeposit.shtml?mchtId=" +mchtId,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}

//财务确认
function financeConfirm(id){
    $.ligerDialog.confirm("确定确认吗？", function (yes)
    {
    	if(yes){
   		   $.ajax({ //ajax提交
   				type:'POST',
   				url:'${pageContext.request.contextPath}' +"/mcht/financeConfirm.shtml?mchtId="+id,
   				data:"",
   				dataType:"json",
   				cache: false,
   				success: function(data){
   					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("确认成功");
						setTimeout(function(){
							location.reload();
							frameElement.dialog.close();
						},1000);
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
   				},
   				error: function(e){
   				 commUtil.alertError("系统异常请稍后再试");
   				}
    	    });
   		} 
   });  
}

function mchtBankAccountHisList(mchtId){
	$.ligerDialog.open({
		height: $(window).height()*0.85,
		width: $(window).width()*0.85,
		title: "银行账号更新查询",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/mchtBankAccountHisList.shtml?mchtId=" +mchtId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
});
;
</script>

<html>
<body>
		<div class="title-top">
			<div class="table-title">
				<span class="marR10">商家保证金信息
				（<p style="color: red;display: inline;">
					<c:if test="${mchtInfo.depositType == '1'}">可货款抵缴</c:if>
					<c:if test="${mchtInfo.depositType == '2'}"> 现金缴纳（不可货款抵扣）</c:if>
				</p>）
				<c:if test="${sessionScope.USER_SESSION.staffID == 1}">
					<a href="javascript:;" onclick="toEditContractDeposit(${mchtInfo.id});" >【修改】</a>
				</c:if>
				<c:if test="${sessionScope.USER_SESSION.staffID==cwPlatformContact.staffId}">
					<a href="javascript:mchtDepositDtl('${mchtInfo.mchtCode}')" class="marR10 table-title-link">查看明细 </a>
					<a href="javascript:addMchtDepositDtl(${mchtDeposit.id})" class="table-title-link">添加记录 </a>
				</c:if>
				<c:if test="${sessionScope.USER_SESSION.staffID==fwMchtPlatformContact.staffId}">
					<a href="javascript:changeDepositCont(${mchtInfo.id})" class="table-title-link font13">【修改】</a>
				</c:if>
				<c:if test="${mchtInfo.depositType == 1}">
					<c:if test="${contractDeposit == mchtDeposit.totalAmt && mchtInfo.financeConfirmStatus==0}">
						<c:if test="${canConfirm}">
							<a href="javascript:financeConfirm(${mchtInfo.id})" class="table-title-link font13">【财务确认】</a>
						</c:if>
					</c:if>
				</c:if>
				<c:if test="${mchtInfo.depositType == 2}">
					<c:if test="${contractDeposit == mchtDeposit.totalAmt && mchtDeposit.totalAmt <= mchtDeposit.payAmt && mchtInfo.financeConfirmStatus==0}">
						<c:if test="${canConfirm}">
							<a href="javascript:financeConfirm(${mchtInfo.id})" class="table-title-link font13">【财务确认】</a>
						</c:if>
					</c:if>
				</c:if>
				</span>
			</div>
			<table class="gridtable marT10" style="width:80%;">
	           	<tr>
	               <td class="title" style="text-align: center;">店铺保证金</td>  
	               <td class="title" style="text-align: center;">应缴保证金</td> 
	               <td class="title" style="text-align: center;">已缴保证金</td> 
	               <td class="title" style="text-align: center;">还需补缴</td> 
				</tr>
	           	<tr>
	               <td style="text-align: center;">${contractDeposit}</td>  
	               <td style="text-align: center;">${mchtDeposit.totalAmt}</td> 
	               <td style="text-align: center;">${mchtDeposit.payAmt}</td> 
	               <td style="text-align: center;">${mchtDeposit.unpayAmt}</td> 
				</tr>
			</table>
			
			<!-- 
			<div class="marT10 font13 marB05">保证金用于商家保证的内容：</div>
			<textarea name="depositCont" id="depositCont" disabled="disabled" rows="6" style=" width: 40%;" >${mchtInfo.depositContent}</textarea>
			 -->
		</div>
<!--  
		<div class="title-top">
			<div class="table-title">
				<span class="marR10">税务开票信息</span>
				<c:if test="${sessionScope.USER_SESSION.staffID==fwMchtPlatformContact.staffId}">
 				<a href="javascript:mchtTaxInvoiceInfoEdit(${mchtTaxInvoiceInfo.id})" class="table-title-link">【修改】</a> 
				</c:if>
			</div>
			<table class="gridtable marT10" style="width:80%;">
           	<tr>
               <td class="title" style="text-align: center;">项目</td>  
               <td class="title" style="text-align: center;" colspan="3">内容</td>     
			</tr> 
	        <tr>
	           	<td>纳税类型：</td> 
	           	<td colspan="3">
 				<c:forEach var="typeItem" items="${taxType}">
					<c:if test="${mchtTaxInvoiceInfo.taxType==typeItem.statusValue}">${typeItem.statusDesc}</c:if> 
				</c:forEach>
				</td> 	       
	        </tr>
	        <tr>
	           	<td>公司名称：</td> 
	           	<td colspan="3">${mchtInfo.companyName}</td> 	         
	        </tr>
	        <tr>
	           	<td>纳税人识别号：</td> 
	           	<td colspan="3">${mchtTaxInvoiceInfo.taxNumber}</td>          	
	        </tr>
	        <tr>
	           	<td>地址：</td> 
	           	<td colspan="3">${mchtTaxInvoiceInfo.address}</td>	         	
	        </tr>
	        <tr>
	           	<td>电话：</td> 
	           	<td colspan="3">${mchtTaxInvoiceInfo.tel}</td> 	  
	        </tr>
	        <tr>
	           	<td>开户行：</td> 
	           	<td colspan="3">${mchtTaxInvoiceInfo.depositBank}</td> 	         
	        </tr>
	        <tr>
	           	<td>税务开票确认函：</td> 
	         	<td colspan="3">
	         	<ul  class="docs-pictures clearfix td-pictures" id="Pic_viewer" >
	           	<li>
	           	<img style="width:60px;height:40px;" src="${pageContext.request.contextPath}/file_servelt${mchtTaxInvoiceInfo.confirmFile}">
	           	</li>
	           	</ul>	
	         	</td>	     
	        </tr>    
	        <tr>
	           	<td>账号：</td> 
	           	<td colspan="3">${mchtTaxInvoiceInfo.accountNumber}</td>	        
	        </tr>
	        <tr>
	           	<td>状态：</td> 
	           	<td colspan="3">
	           		<c:forEach var="statusItem" items="${auditStatus}">
						<c:if test="${mchtTaxInvoiceInfo.auditStatus==statusItem.statusValue}">${statusItem.statusDesc}</c:if>
					</c:forEach>
					<c:if test="${(mchtTaxInvoiceInfo.auditStatus=='1' || mchtTaxInvoiceInfo.auditStatus=='2') and sessionScope.USER_SESSION.staffID == cwMchtPlatformContact.staffId }">
						<a href="javascript:mchtTaxInvoiceInfoAudit(${mchtTaxInvoiceInfo.id})" class="table-title-link font12">【审核】</a>
					</c:if>
					<c:if test="${mchtTaxInvoiceInfo.auditStatus=='4' }">					
					 <span style="color: red;padding-left: 5px;">(驳回原因: ${mchtTaxInvoiceInfo.auditRemarks};)</span>					    					
					</c:if>
				</td> 	         	
	        </tr>
	        </table>
		</div>
-->		
		<div class="title-top">
			<div class="table-title">
				<span class="marR10">结算银行信息</span>
			</div>
			<table class="gridtable marT10" style="width:80%;">
           	<tr>
               <td class="title" style="text-align: center;">项目</td>  
               <td class="title" style="text-align: center;" colspan="3">内容</td> 
			</tr> 
	        <tr>
	           	<td>入驻类型</td> 
	           	<td colspan="3">
 					<c:if test="${mchtInfo.settledType eq 1}">公司企业</c:if>
 					<c:if test="${mchtInfo.settledType eq 2}">个体商户</c:if>
				</td> 
	        </tr>
	        <tr>
	           	<td>账户名称：</td> 
	           	<td colspan="3">
 				${mchtBankAccount.accName}
				</td> 
	        </tr>
	        <tr>
	           	<td>账号类型</td> 
	           	<td colspan="3">
	           		<c:if test="${mchtBankAccount.accType eq 1}">
	           			对私账号
	           		</c:if>
	           		<c:if test="${mchtBankAccount.accType eq 2}">
	           			对公账号
	           		</c:if>
				</td> 
	        </tr>
	        <tr>
	           	<td>银行名称：</td> 
	           	<td colspan="3">
					<c:forEach var="bank" items="${banks}">
	         		<c:if test="${mchtBankAccount.bankCode==bank.id}">${bank.name}</c:if>
	         		</c:forEach>
	         	</td> 
	        </tr>
	        <tr>
	           	<td>开户支行名称：</td> 
	           	<td colspan="3">${mchtBankAccount.depositBank}</td> 
	        </tr>
	        <tr>
	           	<td>银行账号：</td> 
	           	<td colspan="3">${mchtBankAccount.accNumber}</td>
	        </tr>
	        
	          <%--  <tr>
	           	<td>状态：</td> 
	           	<td colspan="3">
	           <c:choose>
			   <c:when test="${(mchtBankAccount.status=='0'||mchtBankAccount.status=='1') && sessionScope.USER_SESSION.staffID == cwMchtPlatformContact.staffId}"> 			       			          
				<a class="table-title-link" href="javascript:auditMchtBankAccount('${mchtBankAccount.id}')" >【${mchtBankAccount.statusDesc}】</a>					 			 
			   </c:when>
			   <c:otherwise>
			  	${mchtBankAccount.statusDesc}
			   </c:otherwise> 
			</c:choose>	
	           	</td>
	        </tr> --%>   
	        
	        
	        <tr>
	           	<td>状态：</td> 
	           	<td colspan="3">
	           		<c:forEach var="Starstds" items="${Starstd}">
						<c:if test="${mchtBankAccount.status==Starstds.statusValue}">${Starstds.statusDesc}</c:if>
					</c:forEach>
					<c:if test="${(mchtBankAccount.status=='0' || mchtBankAccount.status=='1') && isCw}">
					   <a class="table-title-link" href="javascript:auditMchtBankAccount(${mchtBankAccount.id})" >【${mchtBankAccount.statusDesc}】</a>   
					</c:if>	
					<c:if test="${mchtBankAccount.status=='3' }">
					   <span style="color: red;padding-left: 5px;">(驳回原因: ${mchtBankAccount.auditRemarks};)</span>
					</c:if>
					<a href="javascript:mchtBankAccountHisList(${mchtInfo.id})">[查看更新记录]</a>
				</td> 	         	
	        </tr>   
	             
	        </table>
		</div>		
		
			
		<div class="title-top" <c:if test="${mchtInfo.mchtType =='2'}">style="display: none"</c:if>>
			<div class="table-title">
				<span class="marR10">SPOP信息补充（供应商对我们的要求）</span>
			</div>
		 <table class="gridtable marT10" style="width:80%;">
           	<tr>
               <td class="title" style="text-align: center;">项目</td>  
               <td class="title" style="text-align: center;" colspan="3">内容</td> 
			</tr> 
	        <tr>
	           	<td>我们总支付保证金：</td> 
	           	<td colspan="3">
 				${mchtBankAccount.accName}
				</td> 
	        </tr>
	        <tr>
	           	<td>银行名称：</td> 
	           	<td colspan="3">
					<c:forEach var="bank" items="${banks}">
	         		<c:if test="${mchtBankAccount.bankCode==bank.id}">${bank.name}</c:if>
	         		</c:forEach>
	         	</td> 
	        </tr>
	        <tr>
	           	<td>开户支行名称：</td> 
	           	<td colspan="3">${mchtBankAccount.depositBank}</td> 
	        </tr>
	        <tr>
	           	<td>银行账号：</td> 
	           	<td colspan="3">${mchtBankAccount.accNumber}</td>
	        </tr>
	      </table>
		</div>
		<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
