<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:10px;padding-bottom:10px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
</style>
<script type="text/javascript">
var viewerPictures;
$(function(){
		$("#save").bind('click',function(){
			var violateType = $("#violateType").val();
			var violateAction = $("#violateAction").val();
			var content = $("#content").val();
			var punishStandard = $("#punishStandard").val();
			var punishOther = $("#punishOther").val();
			var complainFlag = $("input[name='complainFlag']:checked").val();
			var enableHours = $("#enableHours").val();
			var jifenIntegralDesc = $("#jifenIntegralDesc").val();
			var jifenIntegral = $("#jifenIntegral").val();
			var seqNo = $("#seqNo").val();
			if(!violateType){
				commUtil.alertError("请选择类型");
				return false;
			}
			if(!violateAction){
				commUtil.alertError("请选择违规行为");
				return false;
			}
			if(!content){
				commUtil.alertError("请输入违规内容");
				return false;
			}
			if(!punishStandard){
				commUtil.alertError("请输入扣款标准");
				return false;
			}
			if(!punishOther){
				commUtil.alertError("请输入其他处罚");
				return false;
			}
			if(!complainFlag){
				commUtil.alertError("请选择可否申诉");
				return false;
			}
			if(complainFlag == "1"){
				if(!enableHours){
					commUtil.alertError("请选择可申诉时长");
					return false;
				}
			}
			if(!seqNo){
				commUtil.alertError("请输入排序值");
				return false;
			}
			/* if(jifenIntegral){
				if(jifenIntegral>0){
					commUtil.alertError("积分请输入正数");
					return false;
				}
			} */
			if(seqNo){
				if(!testNumber(seqNo)){
					commUtil.alertError("排序请输入正整数");
					return false;
				}
			}
			
			if(!jifenIntegralDesc){
				commUtil.alertError("补偿用户积分说明不能为空");
				return false;
			}
			var paymentBreachModel = $("input[name='paymentBreachModel']:checked").val();
			if(paymentBreachModel==""||paymentBreachModel==null){
				commUtil.alertError("支付违约金模式必填");
				return false;
			}
			var integralCompensateModel = $("input[name='integralCompensateModel']:checked").val();
			if(integralCompensateModel==""||integralCompensateModel==null){
				commUtil.alertError("积分补偿模式必填");
				return false;
			}
			var jifenIntegral=$("#jifenIntegral").val();
			var integralCompensateModel=$("input[name='integralCompensateModel']:checked").val();	
			if(integralCompensateModel=="0" && !testNumber(jifenIntegral)){
				commUtil.alertError("积分补偿额度请输入正整数");
				return false;
			}
			if(integralCompensateModel=="1" && parseInt(jifenIntegral)!=0){
				commUtil.alertError("积分补偿额度输入有误");
				return false;
			}
			if(integralCompensateModel=="1" && jifenIntegral==0){
				commUtil.alertError("积分补偿额度输入有误");
				return false;
			}
			
			if(integralCompensateModel=="2" && jifenIntegral!=0){
				commUtil.alertError("积分补偿额度输入有误");
				return false;
			}
		/* 	alert(jifenIntegral==0);
			if(jifenIntegral==0){s
				commUtil.alertError("积分补偿额度输入有误");
				return false;
			} */
			
			if($("#content").val().length>512){
				commUtil.alertError("违规内容文字过多");
				return false;
			}
			if($("#punishStandard").val().length>128){
				commUtil.alertError("支付违约金标准文字过多");
				return false;
			}
			if($("#punishOther").val().length>256){
				commUtil.alertError("其它处理方式文字过多");
				return false;
			}
			if($("#jifenIntegralDesc").val().length>256){
				commUtil.alertError("补偿用户积分说明文字过多");
				return false;
			}
			if($(".integralCompensateModel").val()=="2"){
				$("#jifenIntegral").val()==0;
			}
			if($('input:radio[name="paymentBreachModel"]').val()=="0"){			
				$("#minDeductionQuota").val()==0;
			}
		
			if($("#paymentBreachModel0").is(":checked")){
				var breachDeductionQuota = Number($("#breachDeductionQuota").val());
				if(!(Math.floor(breachDeductionQuota) === breachDeductionQuota) || (breachDeductionQuota < 0)){
					commUtil.alertError("违约金扣除额度不是非负整数");
					return false;
				}
			}
			if($("#paymentBreachModel1").is(":checked")){
				if(parseInt($("#breachDeductionQuota").val())!=0){
					commUtil.alertError("违约金扣除额度大于零小于壹");
					return false;
				}
				if($("#breachDeductionQuota").val()==0){
					commUtil.alertError("违约金扣除额度不能为零");
					return false;
				}
			}
	
			$.ajax({
				url : "${pageContext.request.contextPath}/violateOrder/saveViolatePunishStandard.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : $("#form").serialize(),
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("提交成功");
						setTimeout(function(){
							parent.location.reload();
							frameElement.dialog.close();
						},1000);
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		});
		
		$("input[name='complainFlag']").bind('click',function(){
			var complainFlag = $(this).val();
			if(complainFlag == "2"){
				$("#enableHours").val("");
			}
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
		
		 $('input:radio[name="paymentBreachModel"]').change(function () {
	            if($("#paymentBreachModel0").is(":checked")){
	            	$("#minDeductionQuotaTr").hide();
	            }
	            if($("#paymentBreachModel1").is(":checked")){
	            	$("#minDeductionQuotaTr").show();
	            }
	        });
		 
		if(${violatePunishStandard.paymentBreachModel eq '0'}){
			$("#minDeductionQuotaTr").hide();
		}
});


</script>
<html>
<body>
<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/violateOrder/saveViolatePunishStandard.shtml">
			<input type="hidden" id="id" name="id" value="${violatePunishStandard.id}">
			<table class="gridtable">
           	<tr>
               <td class="title">类型<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
	               <select id="violateType" name="violateType">
               			<option value="">请选择</option>
						<c:forEach var="violateType" items="${violateTypeList}">
							<option value="${violateType.statusValue}" <c:if test="${violatePunishStandard.violateType eq violateType.statusValue}">selected="selected"</c:if>>${violateType.statusDesc}</option>
						</c:forEach>
               	   </select>
               </td>
			</tr> 
	        <tr>
               <td class="title">违规行为<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<select id="violateAction" name="violateAction">
               			<option value="">请选择</option>
               			<c:forEach var="violateAction" items="${violateActions}">
               				<option value="${violateAction.statusValue}" <c:if test="${violatePunishStandard.violateAction eq violateAction.statusValue}">selected="selected"</c:if>>${violateAction.statusDesc}</option>
               			</c:forEach>
               		</select>
               </td>
	        </tr>
	        
	        <tr>
               <td class="title">违规内容<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<textarea rows="6" cols="50" id="content" name="content">${violatePunishStandard.content}</textarea>
               </td>
	        </tr>
	        
	        <tr>
               <td class="title">支付违约金标准<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<%-- <input type="text" id="punishStandard" name="punishStandard" style="width: 500px;" value="${violatePunishStandard.punishStandard}"> --%>
               		<textarea rows="6" cols="50" id="punishStandard" name="punishStandard">${violatePunishStandard.punishStandard}</textarea>
               </td>
	        </tr>
	        
	        <tr>
               <td class="title">支付违约金模式<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="radio" id="paymentBreachModel0" name="paymentBreachModel" value="0" <c:if test="${violatePunishStandard.paymentBreachModel eq '0'}">checked="checked"</c:if>>N元起
               		<input type="radio" id="paymentBreachModel1" name="paymentBreachModel" value="1" <c:if test="${violatePunishStandard.paymentBreachModel eq '1'}">checked="checked"</c:if>>按比例
               </td>
	        </tr>
	        
	        <tr >
               <td class="title">违约金扣除额度<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="breachDeductionQuota" name="breachDeductionQuota" value="${violatePunishStandard.breachDeductionQuota}">
               		按照比例的请填写小于1的数值  例0.15
               </td>
	        </tr>
	        
	       
	        <tr id="minDeductionQuotaTr">
               <td class="title">最低扣除额度</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="minDeductionQuota" name="minDeductionQuota" value="${violatePunishStandard.minDeductionQuota}">
               </td>

	        </tr>
	  
	        
	        <tr>
               <td class="title">其他处理方式<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<textarea rows="6" cols="50" id="punishOther" name="punishOther">${violatePunishStandard.punishOther}</textarea>
               </td>
	        </tr>
	        
	        <tr>
               <td class="title">补偿用户积分说明<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<textarea rows="6" cols="50" id="jifenIntegralDesc" name="jifenIntegralDesc">${violatePunishStandard.jifenIntegralDesc}</textarea>
               </td>
	        </tr>
	        
	        <tr>
               <td class="title">积分补偿模式<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="radio" class="integralCompensateModel" name="integralCompensateModel" value="0" <c:if test="${violatePunishStandard.integralCompensateModel eq '0'}">checked="checked"</c:if>>固定积分
               		<input type="radio" class="integralCompensateModel" name="integralCompensateModel" value="1" <c:if test="${violatePunishStandard.integralCompensateModel eq '1'}">checked="checked"</c:if>>按比例
               		<input type="radio" class="integralCompensateModel" name="integralCompensateModel" value="2" <c:if test="${violatePunishStandard.integralCompensateModel eq '2'}">checked="checked"</c:if>>不发放
               </td>
	        </tr>
	        
	        
	        <tr>
               <td class="title">积分补偿额度</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<c:if test="${violatePunishStandard.integralCompensateModel eq '1'}">
               			<input type="text" id="jifenIntegral" name="jifenIntegral" value="${violatePunishStandard.integralCompensateRate}">
					</c:if>
               		<c:if test="${violatePunishStandard.integralCompensateModel eq '0'}">
               			<input type="text" id="jifenIntegral" name="jifenIntegral" value="${violatePunishStandard.jifenIntegral}">
					</c:if>
               		<c:if test="${violatePunishStandard.integralCompensateModel ne '1' && violatePunishStandard.integralCompensateModel ne '0'}">
               			<input type="text" id="jifenIntegral" name="jifenIntegral" value="">
					</c:if>
               </td>
	        </tr>
	        
	        <tr>
               <td class="title">可否申诉<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="radio" name="complainFlag" value="2" <c:if test="${violatePunishStandard.complainFlag eq '2'}">checked="checked"</c:if>>不可申诉
               		<input type="radio" name="complainFlag" value="1" <c:if test="${violatePunishStandard.complainFlag eq '1'}">checked="checked"</c:if>>可申诉
               		可申诉时长：
               		<select id="enableHours" name="enableHours">
               			<option value="">请选择</option>
               			<option value="12小时" <c:if test="${violatePunishStandard.enableHours eq '12小时'}">selected="selected"</c:if>>12小时</option>
               			<option value="24小时" <c:if test="${violatePunishStandard.enableHours eq '24小时'}">selected="selected"</c:if>>24小时</option>
               			<option value="48小时" <c:if test="${violatePunishStandard.enableHours eq '48小时'}">selected="selected"</c:if>>48小时</option>
               			<option value="72小时" <c:if test="${violatePunishStandard.enableHours eq '72小时'}">selected="selected"</c:if>>72小时</option>
               		</select>
               </td>
	        </tr>
	        
	        <tr>
               <td class="title">排序<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="seqNo" name="seqNo" value="${violatePunishStandard.seqNo}">
               </td>
	        </tr>
	        
	        <tr>
               <td class="title">操作</td>
               <td colspan="2" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="save" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
					</div>
				</td>
	        </tr>
	        </table>
</form>	        
</body>
</html>
