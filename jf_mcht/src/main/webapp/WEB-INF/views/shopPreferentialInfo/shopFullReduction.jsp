<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<title>创建店铺券</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
	<!-- Bootstrap -->
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
<style type="text/css">
.vm {
	margin: 0 3px 0 0;
}

	.line-limit-length {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap; 
	}
	.rows {
		position: relative;
	    width: 300px;
	    margin: 5px;
    	display: inline-block;
 		
	}
	.tap-title {
		text-align:center;
		position: absolute;
		top:0;
		right:0;
		width: 60px;
		height:20px;
		color: #fff;
		background: red;
	}
	.tap-title2 {
		text-align:center;
		position: absolute;
		top:0;
		right:0;
		width: 60px;
		height:20px;
		color: #fff;
		background: DeepSkyBlue ;
	}


</style>
</head>

<body>

  <div class="modal-dialog" role="document" style="width:800px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<c:if test="${preferentialType==1 }">
					 <span class="modal-title" id="exampleModalLabel">创建店铺满减券</span>
				</c:if>
				<c:if test="${preferentialType==2 }">
					 <span class="modal-title" id="exampleModalLabel">创建折扣券</span>
				</c:if>
       
      </div>
		<div class="modal-body">
		<form id="dataFrom">
		<input type="hidden" id="id" name="id" value="${id}">
		<input type="hidden" id="coupon" name="coupon" value="${coupon}">
		<input type="hidden" value="<fmt:formatDate value='${coupon.recBeginDate}' pattern='yyyy-MM-dd HH:mm:ss'/>"    id="oldBeginDate" name="oldBeginDate" data-date-format="yyyy-mm-dd HH:mm:ss">
		<input type="hidden" id="preferentialType" name="preferentialType" value="${preferentialType}">
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
				
					<tr>
						<td class="editfield-label-td">优惠类型</td>
						<td colspan="2" class="text-left">
						<c:if test="${preferentialType==1 }">
							<div class="rows">
							<div class="col-md-12">
								  <div><span>  </span></div>
							</div>
							<div><p style="color:black;font-size:16px;">店铺满减券</p></div>
							<div class="col-md-9">
								<p style="color: gray;">
									消费者店铺内消费达到一定金额，即可使用（活动商品除外）
								</p>
							</div>
							<div class="tap-title">店铺优惠</div>
						</div>
						</c:if>
						<c:if test="${preferentialType==2 }">
								<div class="rows">
							<div class="col-md-12">
								  <div><span>  </span></div>
							</div>
							<div><p style="color:black;font-size:16px;">店铺折扣券</p></div>
							<div class="col-md-9">
								<p style="color: gray;">
									消费者店铺内购买商品达到一定数量，即可使用（活动商品除外）
								</p>
							</div>
							<div class="tap-title">店铺优惠</div>
						</div>
						</c:if>
							
						</td>
					</tr>
				
				
					<tr>
						<td class="editfield-label-td">优惠名称<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<input type="text" id="name" name="name"  onkeypress="showNameLength(this)"	value="${coupon.name }"  style="width: 300px;"  placeholder="该名称将不对买家展示，仅内部可见"  validate="{required:true,maxlength:15}"><span id="errorMessage"></span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">使用时间<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						
							<input type="text" placeholder="开始时间" value="<fmt:formatDate value='${coupon.recBeginDate}' pattern='yyyy-MM-dd HH:mm:ss'/>"  validate="{required:true,date:true}"  id="recBeginDate" name="recBeginDate" data-date-format="yyyy-mm-dd HH:mm:ss" autocomplete="off" readonly="readonly"><span id="errorMessage"></span>
							到
					<!-- 	</td>
						<td colspan="2" class="text-left"> -->
							<input type="text" placeholder="结束时间" value="<fmt:formatDate value='${coupon.recEndDate}' pattern='yyyy-MM-dd HH:mm:ss'/>"   validate="{required:true}"  id="recEndDate" name="recEndDate" data-date-format="yyyy-mm-dd HH:mm:ss" autocomplete="off" readonly="readonly">
									领取时间与使用时间一致 <span id="errorMessage"></span>
						</td>
				
					</tr>
					
					
					<c:if test="${preferentialType==1}">
					<tr>			
						<td class="editfield-label-td">面额<span class="required">*</span></td>
						<td colspan="2" class="text-left">	
							<input type="text" value=""  placeholder="请输入1-150的整数数字" id="money"   name="money" style="width: 300px;" validate="{required:true,digits:true,min:1,max:150}" >元<span id="errorMessage"></span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">使用条件<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						订单满<input type="text"  id="minimum" name="minimum" style="width: 300px;"   placeholder="请输入1-10000的整数数字"  value="${coupon.minimum }"  validate="{required:true,digits:true,min:1,max:10000}"   >元 可用  &nbsp;&nbsp;<span id="errorMessage"></span>
						</td>
					</tr>
					</c:if>
					
					<c:if test="${preferentialType==2}">
					<tr>
						<td class="editfield-label-td">折扣<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<div class="row fullCutRule" style="margin-top: 5px;" id="fullCutRuleDiv">
								<c:if test="${empty shopPreferentialInfo}">
									<div name="eachRule">
										满<input type="text" size="6"  id="minicount"  name="minicount"  value="${coupon.minicount}"  validate="{required:true,digits:true,min:1,max:99}" >件 <span id="errorMessage"></span>  &nbsp;&nbsp;
										<input type="text" size="6"  id="discount"   name="discount"  value=""   >折<span id="errorMessage"></span>
	                                </div>
								</c:if>
                            </div>
						</td>
					</tr>
					
					</c:if>
					
					<tr>			
						<td class="editfield-label-td">发行张数<span class="required">*</span></td>
						<td colspan="2" class="text-left">	
							<input type="text" id="grantQuantity" value="${ coupon.grantQuantity}"  validate="{required:true,number:true,min:1,max:999999999}"   name="grantQuantity"  style="width: 200px;" >张<span id="errorMessage"></span>
						</td>
					</tr>
					
					<tr>			
						<td class="editfield-label-td">每人限领</td>
						<td colspan="2" class="text-left">	
							<select id="recEach" name="recEach" style="width: 150px;" >
							<option value="1" <c:if test="${coupon.recEach==1 }" >selected</c:if>>1张</option>
							<option value="2" <c:if test="${coupon.recEach==2 }" >selected</c:if>>2张</option>
							<option value="3" <c:if test="${coupon.recEach==3 }" >selected</c:if>>3张</option>
							</select>
						</td>
					</tr>
					
					<tr>			
						<td class="editfield-label-td">收藏店铺领取</td>
						<td colspan="2" class="text-left">	
						 <input type="checkbox" class="vm" id="careShopCanRec" name="careShopCanRec" value="1" <c:if test="${coupon.careShopCanRec == '1'}">checked="checked"</c:if>>是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<p>(若勾选，用户需收藏店铺后才可以领取此优惠券)<p>
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
	//给面额赋值
	var moneys = "${coupon.money}";
	if(moneys!=""){
	var moneyNum = moneys.substring(0, moneys.indexOf("."))
	$("#money").val(moneyNum)
	}
	//给使用条件赋值
	var minimums = "${coupon.minimum}";
	if(minimums!=""){
	var minimumsNum = minimums.substring(0, minimums.indexOf("."))
	$("#minimum").val(minimumsNum)
	}
	//给折扣赋值
	var tempDiscount = "${coupon.discount}";
	if(tempDiscount!=""){
	var discount = tempDiscount*10;
	$("#discount").val(discount.toFixed(1));
	}
	
	
	 $.metadata.setType("attr", "validate");
	 
	 dataFromValidate =$("#dataFrom").validate({
		 rules: {
			 discount: {
				 required:true,
				 number:true,
				 min:0.1,
				 max:9.9,
				 maxlength:3
		      },
		      
		 },
		 messages: {
			 discount: {
			        required: "不可为空",
			        number:"请输入正确数字",
			        min:"折扣最小值为0.1",
			        max:"折扣最大值为9.9",
			        maxlength:"最多输入小数点后一位"
			      }
		 },
	
	        highlight : function(element) {  
	        	$(element).next().next().addClass('error');
	            $(element).closest('tr').find("td").first().addClass('has-error');  
	        },
	        success : function(label) {  
	            label.closest('tr').find("td").first().removeClass('has-error');  
	        },
	        errorPlacement:function(error,element){
	        	error.appendTo(element.next('span'));
	        }
		});


    $('#recBeginDate').datetimepicker(
    		{	
    			format: 'yyyy-mm-dd hh:ii:ss',
    		    minView: 0,
    		    minuteStep:1,
    		    autoclose: true,
    		    startDate:new Date(),
    		    language: 'zh-CN' //汉化
    	    }
    );
    $('#recEndDate').datetimepicker(
    		{
    			format: 'yyyy-mm-dd hh:ii:ss',
    		    minView: 0,
    		    minuteStep:1,
    		    autoclose: true,
    		    startDate:new Date(),
    		    language: 'zh-CN' //汉化
    	    }
    );
    
    
});




function commitSave(){
	
	var beginDate = $("#recBeginDate").val();
	var endDate = $("#recEndDate").val();
	
	var oldBeginDate = $("#oldBeginDate").val();
	var nowTime = new Date();
	var fixOldBeginDate = new Date(oldBeginDate);//已填写的上线时间
	var fixBeginDate = new Date(beginDate);//现在修改的上线时间

/* 	console.log("beginDate"+beginDate);
	console.log("oldBeginDate"+oldBeginDate);
	console.log("nowTime"+nowTime);
	console.log("fixOldBeginDate"+fixOldBeginDate);
	console.log("fixBeginDate"+fixBeginDate); */
	
	if(fixOldBeginDate<=nowTime){
		swal("优惠券已发布领取，无法修改")
		$("#viewModal").modal('hide');
       	table2.ajax.reload();
       	return false;
	}
	
	if(fixBeginDate <= nowTime){
		swal("开始时间需大于当前时间");
		return false;
	}
	
	
	if(beginDate>=endDate){
		swal("活动开始时间需小于活动结束时间");
		return false;
	}
	
	var minimum = $("#minimum").val();
	var money = $("#money").val();
	if(minimum*1<=money*1){
		swal("使用条件应大于优惠面额");
		return;
	}
	
 	var count = $("#discount").val()
	if(parseFloat(count)<parseFloat(0.1) || parseFloat(count)>parseFloat(9.9)){
		swal("请输入0.1~9.9之间的折扣")
		return;
	} 
	
	if(dataFromValidate.form()){
		var dataJson = $("#dataFrom").serializeArray();
		
		$.ajax({
			method: 'POST',
			url: '${ctx}/shopPreferentialInfo/shopFullReductionSave',
			data: dataJson,
			dataType: 'json',
			cache : false,
			async : false,
			timeout : 30000,
			success:function(data){
				if(data.returnCode == '0000'){
					swal("提交成功");
					$("#viewModal").modal('hide');
		           	table2.ajax.reload();
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
   
   function showNameLength(name){
	   document.getElementById("name").innerHTML=name.value.length+1;
	 
   }



</script>
</body>
</html>
