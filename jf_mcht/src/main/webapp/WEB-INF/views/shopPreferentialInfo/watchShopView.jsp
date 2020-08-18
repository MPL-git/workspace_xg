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
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">店铺优惠名称<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<span>${shopPreferentialInfo.name}</span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">活动开始时间<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<fmt:formatDate value='${shopPreferentialInfo.beginDate}' pattern='yyyy-MM-dd HH:mm:ss'/> 
						
							
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">活动结束时间<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<fmt:formatDate value='${shopPreferentialInfo.endDate}' pattern='yyyy-MM-dd HH:mm:ss'/>
						
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">满减类型<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<c:if test="${sumFlag == '0'}">
							<span>不累加</span>
							</c:if>
							<c:if test="${sumFlag == '1'}">
							<span>累加</span>
							</c:if>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">设置规则<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<div class="row fullCutRule" style="margin-top: 5px;" id="fullCutRuleDiv">
								<c:if test="${empty shopPreferentialInfo}">
									<div name="eachRule">
										<%-- 订单满<input type="text" size="6" name="fullMoney" value="" validate="{number:true,min:0}" data-type="money">元   &nbsp;&nbsp;
										减<input type="text" size="6" name="cutMoney" value="" validate="{number:true,min:0}" data-type="money">元
		                                <button type="button" <c:if test="${sumFlag == '1'}">style="display: none;"</c:if> class="btn btn-primary btn-xs" id="addFullCutRule">&nbsp;+&nbsp;</button> --%>
	                                </div>
								</c:if>
                            </div>
						</td>
					</tr>
					
				</tbody>
			</table>
		</div>
		</form>
		

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
$(function(){
    $('#beginDate').datetimepicker(
    		{	
    			format: 'yyyy-mm-dd hh:ii:ss',
    		    minView: 0,
    		    minuteStep:1,
    		    autoclose: true,
    		    language: 'zh-CN' //汉化
    	    }
    );
    $('#endDate').datetimepicker(
    		{
    			format: 'yyyy-mm-dd hh:ii:ss',
    		    minView: 0,
    		    minuteStep:1,
    		    autoclose: true,
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
										'订单满<span  size="6" name="fullMoney"  data-type="money">'+fullMoney+'</span>元   &nbsp;&nbsp;&nbsp;'+
										'减<span  size="6" name="cutMoney"   data-type="money">'+cutMoney+'</span>元&nbsp;'+
            							'</div>');
		}else{
			$("#fullCutRuleDiv").append('<div name="eachRule">'+
										'订单满<span  size="6" name="fullMoney"  data-type="money">'+fullMoney+'</span>元   &nbsp;&nbsp;&nbsp;'+
										'减<span  size="6" name="cutMoney"   data-type="money">'+cutMoney+'</span>元&nbsp;'+
				 						'</div>');
		}
	}

</c:if>

    

    
});

</script>
</body>
</html>
