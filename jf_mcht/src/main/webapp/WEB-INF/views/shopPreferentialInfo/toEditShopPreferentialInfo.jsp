<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>添加/编辑店铺优惠</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
	<!-- Bootstrap -->
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
<style type="text/css">
.vm {
	margin: 0 3px 0 0;
}
</style>
</head>

<body>

  <div class="modal-dialog" role="document" style="width:800px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">优惠信息</span>
      </div>
		<div class="modal-body">
		<form id="dataFrom">
		<input type="hidden" id="shopPreferentialInfoId" name="id" value="${id}">
		<input type="hidden"  value="<fmt:formatDate value='${shopPreferentialInfo.beginDate}' pattern='yyyy-MM-dd HH:mm:ss'/>" id="oldBeginDate" name="oldBeginDate" data-date-format="yyyy-mm-dd HH:mm:ss">
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">店铺优惠名称<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<input type="text" id="name" name="name" style="width: 300px;"  placeholder="该名称将不对买家展示，仅内部可见"  value="${shopPreferentialInfo.name}" validate="{required:true,maxlength:15}">
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">活动开始时间<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<input type="text"  validate="{required:true}"  style="width: 180px;"  value="<fmt:formatDate value='${shopPreferentialInfo.beginDate}' pattern='yyyy-MM-dd HH:mm:ss'/>" id="beginDate" name="beginDate" data-date-format="yyyy-mm-dd HH:mm:ss" autocomplete="off" readonly="readonly">
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">活动结束时间<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<input type="text"  validate="{required:true}"   style="width: 180px;"  value="<fmt:formatDate value='${shopPreferentialInfo.endDate}' pattern='yyyy-MM-dd HH:mm:ss'/>" id="endDate" name="endDate" data-date-format="yyyy-mm-dd HH:mm:ss" autocomplete="off" readonly="readonly">
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">满减类型<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<input type="radio" class="vm" name="sumFlag" value="0" <c:if test="${sumFlag == '0'}">checked="checked"</c:if>>不累加&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" class="vm" name="sumFlag" value="1" <c:if test="${sumFlag == '1'}">checked="checked"</c:if>>累加（上不封顶）
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">设置规则<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<div class="row fullCutRule" style="margin-top: 5px;" id="fullCutRuleDiv">
								<c:if test="${empty shopPreferentialInfo}">
									<div name="eachRule">
										订单满<input type="text"  style="width: 160px;"  size="6" name="fullMoney" value=""  data-type="money" placeholder="请输入1-10000的整数数字"  >元   &nbsp;&nbsp;
										减<input type="text"  style="width: 160px;"  size="6" name="cutMoney" value=""  data-type="money"  placeholder="请输入1-150的整数数字"  >元
		                                <button type="button" <c:if test="${sumFlag == '1'}">style="display: none;"</c:if> class="btn btn-primary btn-xs" id="addFullCutRule">&nbsp;+&nbsp;</button>
	                                </div>
								</c:if>
                            </div>
						</td>
					</tr>
					
				</tbody>
			</table>
		</div>
		</form>
		
	  <div class="modal-footer">
			<button class="btn btn-info" id="confirm" onclick="commitSave();">提交</button>
			<button class="btn btn-info" data-dismiss="modal">取消</button>
      </div>
	</div>
</div>
</div>

	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/utils.js"></script>
<script type="text/javascript">
var dataFromValidate
$(function(){
	
	$.metadata.setType("attr", "validate");
	 
	 dataFromValidate =$("#dataFrom").validate({
	        highlight : function(element) {  
	        	
	        	$(element).next().addClass('error');
	            $(element).closest('tr').find("td").first().addClass('has-error');  
	        },
	        success : function(label) {  
	            label.closest('tr').find("td").first().removeClass('has-error');  
	        }
		});
	
    $('#beginDate').datetimepicker(
    		{	
    			format: 'yyyy-mm-dd hh:ii:ss',
    		    minView: 0,
    		    minuteStep:1,
    		    autoclose: true,
    		    startDate:new Date(),
    		    language: 'zh-CN' //汉化
    	    }
    );
    $('#endDate').datetimepicker(
    		{
    			format: 'yyyy-mm-dd hh:ii:ss',
    		    minView: 0,
    		    minuteStep:1,
    		    autoclose: true,
    		    startDate:new Date(),
    		    language: 'zh-CN' //汉化
    	    }
    );
    
    <c:if test="${not empty shopPreferentialInfo}">
    	var rules = "${rules}";
    	var rulesArray = rules.split("|");
    	for(var i=0;i<rulesArray.length;i++){
    		var ruleArray = rulesArray[i].split(",");
    		var fullMoney = ruleArray[0];
    		var cutMoney = ruleArray[1];
    		if(i==0){
    			$("#fullCutRuleDiv").append('<div name="eachRule">'+
												'订单满<input type="text"   style="width: 160px;"  size="6" name="fullMoney" value="'+fullMoney+'"  data-type="money">元   &nbsp;&nbsp;&nbsp;'+
												'减<input type="text" validate="{required:true}"  style="width: 160px;" size="6" name="cutMoney" value="'+cutMoney+'"  data-type="money">元&nbsp;'+
		                						'<button type="button" <c:if test="${sumFlag == '1'}">style="display: none;"</c:if> class="btn btn-primary btn-xs" id="addFullCutRule">&nbsp;+&nbsp;</button>'+
	            							'</div>');
    		}else{
    			$("#fullCutRuleDiv").append('<div name="eachRule">'+
    											'订单满<input type="text" style="width: 160px;" size="6" name="fullMoney" value="'+fullMoney+'" data-type="money">元   &nbsp;&nbsp;&nbsp;'+
    				 							'减<input type="text" validate="{required:true}" style="width: 160px;" size="6" name="cutMoney" value="'+cutMoney+'"  data-type="money">元&nbsp;'+
    				 							'<button type="button" class="btn btn-danger btn-xs" name="delFullCutRule">&nbsp;-&nbsp;</button>'+
    				 						'</div>');
    		}
    	}
    	$("button[name='delFullCutRule']").on('click',function(){
	    	$(this).closest("div").remove();
	    });
    </c:if>
    
    $("input[name='sumFlag']").on('click',function(){
    	var sumFlag = $(this).val();
    	if(sumFlag == 0){
    		$("#addFullCutRule").show();
    	}else{
    		$("#addFullCutRule").hide();
    		$("button[name='delFullCutRule']").closest("div").remove();
    	}
    });
    
    $("#addFullCutRule").on('click',function(){
    	if($("div[name='eachRule']").length>=3){
    		return false;
    	}
    	var tmpStr = '<div name="eachRule">订单满<input type="text"   style="width: 160px;"  size="6" name="fullMoney" value=""   data-type="money">元   &nbsp;&nbsp;&nbsp;'+
    				 '减<input type="text" validate="{required:true}"  style="width: 160px;"   size="6" name="cutMoney" value="" validate="{number:true,min:1,max:150,digits:true}" data-type="money">元&nbsp;'+
    				 '<button type="button" class="btn btn-danger btn-xs" name="delFullCutRule">&nbsp;-&nbsp;</button></div>';
    	$("#fullCutRuleDiv").append(tmpStr);
    	
	    $("button[name='delFullCutRule']").on('click',function(){
	    	$(this).closest("div").remove();
	    });
	    
    });
    
    
/*     var submitting;
    $("#confirm").on('click',function(){
    	if(submitting){
  			return false;	
  		}
    	var id = $("#shopPreferentialInfoId").val();
    	var name = $("#name").val();
    	var beginDate = $("#beginDate").val();
    	var endDate = $("#endDate").val();
    	var sumFlag = $("input[name='sumFlag']:checked").val();
    	if(!name){
    		swal("店铺优惠名称不能为空");
    		return false;
    	}
    	if(!beginDate){
    		swal("活动开始时间不能为空");
    		return false;
    	}
    	if(!endDate){
    		swal("活动结束时间不能为空");
    		return false;
    	}
    	if(beginDate>endDate){
    		swal("活动开始时间不能大于活动结束时间");
    		return false;
    	}
    	if(!sumFlag){
    		swal("请选择满减类型");
    		return false;
    	}
    	var rule="";
    	var ruleMsg="";
    	var ruleError=false;
    	$("input[name='fullMoney']").each(function(index){
    		var fullMoney = $(this).val();
    		if(!fullMoney){
    			ruleMsg = "请完善设置规则";
    			ruleError = true;
        		return;
    		}
    		var cutMoney = $(this).next().val();
    		if(!cutMoney){
    			ruleMsg = "请完善设置规则";
    			ruleError = true;
        		return;
    		}
    		if(parseFloat(fullMoney)<=parseFloat(cutMoney)){
    			ruleMsg = "满M元减N元，M必须大于N";
    			ruleError = true;
        		return;
    		}
    		rule += fullMoney+","+cutMoney;
    		if(index!=$("input[name='fullMoney']").length-1){
    			rule+="|";
    		}
    	}); 
    	if(ruleError){
    		swal(ruleMsg);
    		return false;
    	}
  		submitting = true;
  		$.ajax({
            method: 'POST',
            url: '${ctx}/shopPreferentialInfo/saveShopPreferentialInfo',
            data: {"id":id,"name":name,"beginDate":beginDate,"endDate":endDate,"sumFlag":sumFlag,"rule":rule},
            dataType: 'json'
        }).done(function (result) {
            if (result.returnCode =='0000') {
            	swal("提交成功");
            	$("#viewModal").modal('hide');
	           	table.ajax.reload();
            }else{
            	if(result.returnMsg){
            		swal(result.returnMsg);
            	}else{
	            	swal("操作失败，请重试");
            	}
            }
            submitting = false;
        }); 
    }); */
    
});


function commitSave(){
	
/* 	var beginDate = $("#beginDate").val();
	var endDate = $("#endDate").val();
	
	if(beginDate>=endDate){
		swal("活动开始时间不能大于活动结束时间");
		return false;
	} */
	

	
	var beginDate = $("#beginDate").val();
	var endDate = $("#endDate").val();
	
	var oldBeginDate = $("#oldBeginDate").val();
	var nowTime = new Date();
	var fixOldBeginDate = new Date(oldBeginDate);//已填写的上线时间
	var fixBeginDate = new Date(beginDate);//现在修改的上线时间

	if(fixOldBeginDate<=nowTime){
		swal("店铺满减活动已开始，无法修改")
		$("#viewModal").modal('hide');
       	table2.ajax.reload();
       	return false;
	}
	
	if(fixBeginDate <= nowTime){
		swal("开始时间需大于当前时间");
		return false;
	}
	

	
	var reg1 = /^([1-9]\d{0,3}|10000)$/;
	var reg2 = /^((\d{1,2})|(1[0-4]\d)|(150))$/;
	var rule="";
	var ruleMsg="";
	var ruleError=false;
	$("input[name='fullMoney']").each(function(index){
		var fullMoney = $(this).val();
		if(!fullMoney){
			ruleMsg = "请完善设置规则";
			ruleError = true;
    		return;
		}
		var cutMoney = $(this).next().val();
		console.log(cutMoney)
		if(!cutMoney){
		
			ruleMsg = "请完善设置规则";
			ruleError = true;
    		return;
		}
		
		if(beginDate>=endDate){
    		swal("活动开始时间需小于活动结束时间");
    		return false;
    	}


		if(!reg1.test(fullMoney)){
			ruleMsg = "满减金额范围请在1~10000内";
			ruleError = true;
    		return;
		}
		
		if(!reg2.test(cutMoney)||cutMoney*1==0){
			ruleMsg = "优惠金额范围请在1~150内";
			ruleError = true;
    		return;
		}
	
		if(parseFloat(fullMoney)<=parseFloat(cutMoney)){
			ruleMsg = "满M元减N元，M必须大于N";
			ruleError = true;
    		return;
		}
		rule += fullMoney+","+cutMoney;
		if(index!=$("input[name='fullMoney']").length-1){
			rule+="|";
		}
	}); 
	if(ruleError){
		swal(ruleMsg);
		return false;
	}
	
	if(dataFromValidate.form()){
		var dataJson = $("#dataFrom").serializeArray();
		dataJson.push({"name":"rule","value":JSON.stringify(rule)}); 
		$.ajax({
			method: 'POST',
			url: '${ctx}/shopPreferentialInfo/saveShopPreferentialInfo',
			data: dataJson,
			dataType: 'json',
			cache : false,
			async : false,
			timeout : 30000,
			success:function(data){
				if(data.returnCode == '0000'){
					swal("提交成功");
					$("#viewModal").modal('hide');
		           	table.ajax.reload();
				}else{
					swal({
						  title: data.returnMsg,
						  type: "error",
						  confirmButtonText: "确定"
						});
				}
			},
			error:function(){
				 swal('请求失败');
			}
		});	
	
	}
}
   





</script>
</body>
</html>
