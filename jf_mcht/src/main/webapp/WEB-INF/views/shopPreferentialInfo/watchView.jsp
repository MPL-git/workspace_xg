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

	.width-180 {
    float: left;
    width: 180px;
    padding-left: 10px;
    text-align: left;
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
		<input type="hidden" id="id" name="id" value="${id}">
		<input type="hidden" id="coupon" name="coupon" value="${coupon}">
		<input type="hidden" id="preferentialType" name="preferentialType" value="${preferentialType}">
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">优惠类型<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<c:if test="${coupon.couponType==1&&coupon.preferentialType==1 }"><span>店铺满减券</span></c:if>
							<c:if test="${coupon.couponType==1&&coupon.preferentialType==2 }"><span>店铺折扣券</span></c:if>
							<c:if test="${coupon.couponType==4 }"><span>商品券</span></c:if>
						</td>
					</tr>
				
				
					<tr>
						<td class="editfield-label-td">优惠名称<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<span>${coupon.name }</span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">使用时间<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<fmt:formatDate value='${coupon.recBeginDate}' pattern='yyyy-MM-dd'/>
							
							到
						<span></span>
						<fmt:formatDate value='${coupon.recEndDate }' pattern='yyyy-MM-dd'/>	
									领取时间与使用时间一致
						</td>
				
					</tr>
					
					
					<c:if test="${coupon.preferentialType==1}">
					<tr>			
						<td class="editfield-label-td">面额<span class="required">*</span></td>
						<td colspan="2" class="text-left">	
							<span>${coupon.money }</span>元
						</td>
					</tr>
					<c:if test="${coupon.couponType==1}">
					<tr>
						<td class="editfield-label-td">使用条件<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						订单满<span>${ coupon.minimum}</span>元 可用  
						</td>
					</tr>
					</c:if>
					</c:if>
					
					<c:if test="${coupon.preferentialType==2}">
					<tr>
						<td class="editfield-label-td">折扣<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<div class="row fullCutRule" style="margin-top: 5px;" id="fullCutRuleDiv">
								<c:if test="${empty shopPreferentialInfo}">
									<div name="eachRule">
										满<span>${coupon.minicount}</span>件   
										<span>${coupon.discount*10}</span>折
	                                </div>
								</c:if>
                            </div>
						</td>
					</tr>
					
					</c:if>
					
					<c:if test="${coupon.couponType==4}">
					
							<tr>
						<td class="editfield-label-td">绑定商品</td>
						<td colspan="2" class="text-left">							
						 

			   				 <div class="lines"></div>
    								
       								 <div class="row">
           						 	<div class="col-md-12 datatable-container">
              						  <table border="1" bordercolor=#ddd style="width:90%;margin-left: 5%;">
                 						   <thead>
                  						  <tr role="row">
				                        <th width="78">商品</th>
				                        
				                        <th width="68">活动价</th>
				                        <th width="68">SVP折扣</th>
				                       
                 					   </tr>
		                    				</thead>
		                   			 <tbody>
		                   			
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
                 					   	
	   	
                 					   	</tr>
                 					   </c:forEach>
                   			 		</c:if>
		                    	
		                    	
		                    	</tbody>
		                	</table>
						                      
				            </div>
				        </div>
				    

						</td>
					</tr> 
					
					
					
					</c:if>
					
						<tr>			
						<td class="editfield-label-td">发行张数<span class="required">*</span></td>
						<td colspan="2" class="text-left">	
							<span>${ coupon.grantQuantity}</span>张
						</td>
					</tr>

					<tr>
						<td class="editfield-label-td">是否参与积分转盘字段<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<span>
							<c:if test="${coupon.isIntegralTurntable eq '0'}">
								否
							</c:if>
							<c:if test="${coupon.isIntegralTurntable eq '1'}">
								是
							</c:if>
							</span>
						</td>
					</tr>

					<tr>			
						<td class="editfield-label-td">每人限领<span class="required">*</span></td>
						<td colspan="2" class="text-left">	
							<span>${coupon.recEach }</span>张
						</td>
					</tr>
					
					<tr>			
						<td class="editfield-label-td">收藏店铺领取<span class="required">*</span></td>
						<td colspan="2" class="text-left">	
						<c:if test="${coupon.careShopCanRec == '1'}">
						<span>是</span>
						</c:if>
						<c:if test="${coupon.careShopCanRec != '1'}">
						<span>否</span>
						</c:if>
						</td>
					</tr>

					
					
				</tbody>
			</table>
		</div>
		</form>
		
	  <div class="modal-footer">
			<button class="btn btn-info" id="confirm">提交</button>
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
$(function(){

    $('#recBeginDate').datetimepicker(
    		{	
    			format: 'yyyy-mm-dd hh:ii:ss',
    		    minView: 0,
    		    minuteStep:1,
    		    autoclose: true,
    		    language: 'zh-CN' //汉化
    	    }
    );
    $('#recEndDate').datetimepicker(
    		{
    			format: 'yyyy-mm-dd hh:ii:ss',
    		    minView: 0,
    		    minuteStep:1,
    		    autoclose: true,
    		    language: 'zh-CN' //汉化
    	    }
    );
});  

</script>
</body>
</html>
