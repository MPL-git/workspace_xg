<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>创建店铺满减券</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
	<!-- Bootstrap -->
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
<style type="text/css">
.vm {
	margin: 0 3px 0 0;
}

.isIntegralTurntable{
	vertical-align:middle;
	margin-bottom:2px;
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
		background: #FFFFE0;
 		
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

  <div class="modal-dialog" role="document" style="width:1100px;height:900px">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        
        <span class="modal-title" id="exampleModalLabel">创建商品券</span>
      </div>
		<div class="modal-body">
		<form id="dataFrom">
		<input type="hidden" id="id" name="id" value="${id}">
		<input type="hidden" id="coupon" name="coupon" value="${coupon}">
		<input type="hidden" value="<fmt:formatDate value='${coupon.recBeginDate}' pattern='yyyy-MM-dd HH:mm:ss'/>"    id="oldBeginDate" name="oldBeginDate" data-date-format="yyyy-mm-dd HH:mm:ss">
		<input type="hidden" id="preferentialType" name="preferentialType" value="${preferentialType}">
		<input type="hidden" id="isHide" name=""isHide"" value="${isHide}">
		<div class="table-responsive" style="overflow-x: hidden">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">优惠类型</td>
						<td colspan="2" class="text-left">
							<div class="rows" >
							<div class="col-md-12">
								  <div><span>  </span></div>
							</div>
							<div><p style="color:black;font-size:16px;">商品券</p></div>
							<div class="col-md-9">
								<p style="color: gray;">
									针对指定商品使用的无门槛优惠券（可与其他优惠活动叠加使用）
								</p>
							</div>
							<div class="tap-title2">商品券</div>
						</div>
						
						</td>
					</tr>
				
				
					<tr>
						<td class="editfield-label-td">优惠名称<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<input type="text" id="name" name="name"	value="${coupon.name }"  style="width: 300px;"  placeholder="该名称将不对买家展示，仅内部可见"  ><span id="errorMessage"></span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">使用时间<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<input type="text" placeholder="开始时间" value="<fmt:formatDate value='${coupon.recBeginDate}' pattern='yyyy-MM-dd HH:mm:ss'/>"  validate="{required:true}"  id="recBeginDate" name="recBeginDate" data-date-format="yyyy-mm-dd HH:mm:ss" autocomplete="off" readonly="readonly"><span id="errorMessage"></span>
							到
					<!-- 	</td>
						<td colspan="2" class="text-left"> -->
							<input type="text" placeholder="结束时间" value="<fmt:formatDate value='${coupon.recEndDate}' pattern='yyyy-MM-dd HH:mm:ss'/>"   validate="{required:true}"  id="recEndDate" name="recEndDate" data-date-format="yyyy-mm-dd HH:mm:ss" autocomplete="off" readonly="readonly">
									领取时间与使用时间一致<span id="errorMessage"></span>
						</td>
				
					</tr>
						
					<tr>			
						<td class="editfield-label-td">面额<span class="required">*</span></td>
						<td colspan="2" class="text-left">	
							<input type="text" id="money" name="money" value=""  style="width: 300px;"  placeholder="请输入1-150的整数数字" onchange="changeMoney()"  validate="{required:true,digits:true,min:1,max:150}" >元<span id="errorMessage"></span>
						</td>
					</tr>
					
					
					
					<tr>
						<td class="editfield-label-td">绑定商品</td>
						<td colspan="2" class="text-left">							
							<div class="col-md-5 text-center search-btn">
                				<button type="button" style="height:27px;" id="productChoose">添加商品(可多选)</button>
           	    			</div>  

			   				 <div class="lines"></div>
    								<div class="x_content container-fluid" id="content" >
       								 <div class="row" style="max-height: 300px;overflow-x: hidden;">
           						 	<div class="col-md-12 datatable-container">
              						  <table  border="1" bordercolor=#ddd style="width:90%;margin-left: 5%;" id="tableForm">
                 						   <thead >
                  						<tr role="row" >
				                        <th width="78">商品</th>
				                        
				                        <th width="68">活动价</th>
				                        <th width="68">SVIP折扣</th>
				                        <th width="68">操作</th>
                 					   </tr>
		                    				</thead>
		                   			 <tbody id="attachmentTbody">
		                   			
		                   			 <c:if test="${productList!= null}">
                   			 			               					    
                 					   <c:forEach var="productCustom"  items="${productList }">
                 					   	<tr>
                 					   		<td>
                 					   		<div class="no-check">
                 					   		<div class="width-60"><img src="${ctx}/file_servelt${productCustom.pic}"></div>
                 					   		<div class="width-226"><p>${productCustom.name}</p><br><p style="color: #999;margin: 5px 0 0;">货号：${ productCustom.artNo}&nbsp;&nbsp;&nbsp;&nbsp;</p><p style="color: #999;margin: 0;">ID：${productCustom.code}</p></div>
                 					   		</div>
                 					   		</td>
                 					   		
                 					   		<td>
                 					   		${productCustom.salePriceMax}
                 					   		<c:if test="${productCustom.salePriceMin != productCustom.salePriceMax}">
                 					   		 -${ productCustom.salePriceMax}
        
                 					   		</c:if>
                 					   		</td>
                 					   	
                 					   		<td>
                 					   		${productCustom.svipDiscount }
                 					   		</td>
                 					   	
                 					   		<td>
                 					   		<div><a id="del" class="table-opr-btn" href="javascript:void(0);" data_value="'${ productCustom.id}" >删除</a></div>
                 					   		</td>
                 					   	
                 					   	
                 					   	</tr>
                 					   </c:forEach>
                   			 		</c:if> 
		                    	
		                    	
		                    	</tbody>
		                	</table>
						                      
				            </div>
				        </div>
				    </div>

						</td>
					</tr>

					<tr>
						<td class="editfield-label-td">是否参加积分抽奖</td>
						<td colspan="2" class="text-left">
							<input type="radio" class="isIntegralTurntable" name = "isIntegralTurntable" value="0" <c:if test="${coupon.isIntegralTurntable eq '0'}">checked</c:if>>否&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" class="isIntegralTurntable" name = "isIntegralTurntable" value="1" <c:if test="${coupon.isIntegralTurntable eq '1'}">checked</c:if>>是,参加后,不展示在商品详情内
						</td>
					</tr>

						<tr>			
						<td class="editfield-label-td">每个商品发行张数<span class="required">*</span></td>
						<td colspan="2" class="text-left">	
							<input type="text" id="grantQuantity" value="${ coupon.grantQuantity}"     name="grantQuantity"  validate="{required:true,digits:true,min:1,max:999999999}" style="width: 200px;">张<span id="errorMessage"></span>
						</td>
					</tr>
					
					<tr id="limitedCollection">
						<td class="editfield-label-td">每人限领</td>
						<td colspan="2" class="text-left">	
							<select id="recEach" name="recEach" style="width: 150px;" >
							<option value="1" <c:if test="${coupon.recEach==1 }" >selected</c:if>>1张</option>
							<option value="2" <c:if test="${coupon.recEach==2 }" >selected</c:if>>2张</option>
							<option value="3" <c:if test="${coupon.recEach==3 }" >selected</c:if>>3张</option>
							</select>
						</td>
					</tr>
					
					
				</tbody>
			</table>
		</div>
		</form>
		
	  <div class="modal-footer">
			<button class="btn btn-info" onclick="commitSave();" >提交</button>
			<button class="btn btn-info" data-dismiss="modal">取消</button>
      </div>
	</div>
</div>
	
<!-- 	查看信息框 -->
<div class="modal fade" id="myViewModal" tabindex="-1" role="dialogA" aria-labelledby="viewModalLabelB" data-backdrop="static">
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

var addId = [];
var added = [];
var notAdd = [];
var price =[];

var dataFromValidate;
$(document).ready(function () {

	$("input:radio[name='isIntegralTurntable']").change(function () {
		if($("input:radio[name='isIntegralTurntable']:checked").val() == '1'){
			$('#limitedCollection').hide();
		}else{
			$('#limitedCollection').show();
		}
	});

		var moneys = "${coupon.money}";
		if(moneys!=""){
		var moneyNum = moneys.substring(0, moneys.indexOf("."))
		$("#money").val(moneyNum)
		}

    //规则使用及提示
	$.metadata.setType("attr", "validate");

	dataFromValidate =$("#dataFrom").validate({
		rules: {
			name: {
				required:true,
				maxlength:15
			},
		},
		messages: {
			name: {
				required: "不可为空",
				maxlength:"不能超过15个字"
			}
		}
	});



	var productIds="${productIds}"
	
		//可以用字符或字符串分割
		if(productIds!="" && productIds!=null){
		arr=productIds.split(',');
		for(var i=0;i<arr.length;i++){
			addId.push([arr[i]]);	
			}
		}
		

	if($('#content').find('tr').length == 1){
		$('#content').hide();
	}
	
	if($('#content').find('tr').length >= 2&&${isHide==1}){
		$('#productChoose').hide();
	}
	 
	 $.metadata.setType("attr", "validate");
	 
	 dataFromValidate =$("#dataFrom").validate({
	        highlight : function(element) {  
	        	$(element).addClass('error');
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

	
	
	
	$('.datePicker').datetimepicker(
			{
				minView: "month", //选择日期后，不会再跳转去选择时分秒
				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
				language: 'zh-CN', //汉化
				autoclose:true, //选择日期后自动关闭
				startDate: '+1d',  // 窗口可选开始时间
		        endDate: '+15d'   // 窗口可选截止时间
			}
	)/* .on('changeDate',function(ev){
		$.ajax({
	        url: "${ctx}/market/productAmount?upDate="+$('#upDate').val(), 
			type : 'GET',
	        success: function(data){
	           if(data.returnCode="0000"){
	        	   $('#productChoose').text("商品选择("+data.returnData.counted+"/"+data.returnData.count+")");
	        	   $('#count').val(data.returnData.count);
	        	   $('#counted').val(data.returnData.counted);
	           }else{
	        	   swal(data.returnMsg);
	           } 	          
	        },
		    error:function(){
		    	swal('请求失败');
		    }
		});
	});  */	
});

$('#tableForm').on('click','#del',function(){
	
/* 	var productId = $(this).attr('data_value');
	var index = contain(addId,productId);
	addId.splice(index,1);
	var index1 = contain(added,productId);
	added.splice(index1,1); */
	var index = $("#tableForm").find("tr").index($(this).closest('tr'));

	addId.splice(index-1,1);
	price.splice(index-1,1)
	//删除表格
	/* $(this).parent().parent().parent().remove(); */
	  $(this).closest('tr').remove(); 
	if($('#content').find('tr').length == 1){
		$('#content').hide();
		$('#productChoose').show();
	}
	
	
});


function contain(ids,productId){
	var length = ids.length;
	while(length--){
		if(ids[length] == productId){
			return length;
		}
	}
	return -1;
}

//弹出商品选择框
$('#productChoose').click(function(){  
	var money = $("#money").val();
	if(!money){
		swal('请先填写面额');
	}else{
	
		$.ajax({
    	    url: "${ctx}/shopPreferentialInfo/shopDynamicChoose?money="+money+"&isHide=${isHide}",
    	    success: function(data){
    	          $("#myViewModal").html(data);
    	          $("#myViewModal").modal();
    	    },
    		error:function(){
    		   	  swal('页面不存在');
    		}
    	});
	}
});

//面额改变清空不能添加集合
function changeMoney(){
	notAdd = []
}



function commitSave(){
	var money = $("#money").val();
	for(var i = 0 ; i < price.length; i++){
		var one = money*1;
		var two = price[i]*1
		if(one>two){
			swal("商品最低价（含SVIP价）不得低于商品券面额哦~")
			return;
		}
	}
	

	var beginDate = $("#recBeginDate").val();
	var endDate = $("#recEndDate").val();

	var oldBeginDate = $("#oldBeginDate").val();
	var nowTime = new Date();
	var fixOldBeginDate = new Date(oldBeginDate);//已填写的上线时间
	var fixBeginDate = new Date(beginDate);//现在修改的上线时间

	
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
	
	if(addId.length<=0){
		swal("请选择商品");
		return ;
	}
	
	if(dataFromValidate.form()){
		var list="";
		var paramList = [];
		$('[id=del]').each(function(){
			var productId = $(this).attr('data_value');
			/* var upDate = $(this).attr('upDate');
			var item = {productId: ,upDate:upDate,remarks:$(this).parent().parent().prev().prev().prev().children('textarea').val()}; */
			paramList.push(productId);
		});
		
		
		var dataJson = $("#dataFrom").serializeArray();
		dataJson.push({"name":"paramList","value":JSON.stringify(paramList)});
		dataJson.push({"name":"isIntegralTurntable","value":$("input:radio[name='isIntegralTurntable']:checked").val()});

		$.ajax({
			method: 'POST',
			url: '${ctx}/shopPreferentialInfo/saveCertificates',
			data: dataJson,
			dataType: 'json',
			cache : false,
			async : false,
			timeout : 30000,
			success:function(data){
				if(data.returnCode == '0000'){
					swal("提交成功");
					$("#viewModal").modal('hide');
		           	table3.ajax.reload();
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
