<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>单品报名</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
</head>

<body>
<!--查看品牌 -->
<div class="modal-dialog wg-xx" role="document" style="width:800px;">
<div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">单品报名</span>
      </div>
	<div class="modal-body">
     
     	<div class="search-container container-fluid">
        <form class="form-horizontal" id="searchForm">
            <div class="form-group">
                <div class="col-md-2 search-condition" >
                    <select class="form-control" name="searchType" id="searchType">
                        <option value="1">商品ID</option>
                        <option value="2">商品货号</option>
                        <option value="3">商品名称</option>
                    </select>
                </div>
                <div class="col-md-3 search-condition" >
                    <input class="form-control" type="text" id="searchKeywrod" name="searchKeywrod" placeholder="请输入搜索关键词" style="width: 350px;">
                </div>

                <div class="col-md-5 text-center search-btn">
                    <button type="button"  class="btn btn-info" id="btn-query">搜索</button>
                </div>
            </div>
        </form>
    	</div>
		
		<div class="x_content container-fluid" style="display: none;" id="tableDiv">
				<div class="row">
					<div class="col-md-12 datatable-container at-table">
						<table id="datatable"
							class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">


						</table>
					</div>
				</div>
		</div>
		
		<div id="">
			<form class="form-horizontal" role="form" id="saveForm">
				<input type="hidden" id="type" name="type" value="${type}">
				<input type="hidden" id="productId" name="productId">
				<input type="hidden" id="originalPrice" name="originalPrice">
				<input type="hidden" id="activityPrice" name="activityPrice">
				<input type="hidden" id="isSpecial" name="isSpecial" value="${isSpecial}">
				<table class="panel-table">
					<tr>
		    			<td>商品名称</td>
		    			<td id="productName"></td>
		    		</tr>
					<c:if test="${sessionScope.mchtInfo.mchtType != '1'}">
						<tr>
							<td>竞品价格</td>
							<td><input type="text" name="comparePrice" id="comparePrice" placeholder="请输入竞品价格" data-type="money">&nbsp;元</td>
						</tr>
					</c:if>
		    		<tr>
		    			<td>活动价格</td>
		    			<td id="activityPriceDesc"></td>
		    		</tr>
					<c:if test="${sessionScope.mchtInfo.mchtType != '1'}">
						<tr>
							<td>SVIP折扣</td>
							<td>
								<select class="ad-select" name="svipDiscount" id="svipDiscount">
									<option value="">请选择</option>
									<option value="0.95">9.5</option>
									<option value="0.9">9.0</option>
									<option value="0.85">8.5</option>
									<option value="0.80">8.0</option>
									<option value="0.75">7.5</option>
									<option value="0.70">7.0</option>
									<option value="0.65">6.5</option>
									<option value="0.60">6.0</option>
									<option value="0.55">5.5</option>
									<option value="0.50">5.0</option>
									<option value="0.45">4.5</option>
									<option value="0.40">4.0</option>
								</select>
							</td>
						</tr>
					</c:if>
		    		<tr>
		    			<td>活动库存</td>
		    			<td id="productStock"></td>
		    		</tr>
		    		<c:if test="${type eq 10}">
		    		<tr>
		    			<td>助力设置</td>
		    			<td >邀请一人减<input type="text" id="eachMoney" name="eachMoney" data-type="negativeNumber" style="width: 60px;">元&nbsp;&nbsp;&nbsp;&nbsp;最多可邀请<input type="text" id="maxCount" name="maxCount" data-type="negativeNumber" style="width: 60px;">人  &nbsp;&nbsp;&nbsp;&nbsp;最低成交价<span id="minTransactionPrice"></span>元</td>
		    		</tr>
		    		</c:if>
		    		<tr>
		    			<td>期望日期</td>
		    			<td>
		    				<input style="float: left;" class="form-control datePicker" type="text" id="expectedDate" name="expectedDate" data-date-format="yyyy-mm-dd">
		    				<c:if test="${type eq 3}">
		    				<select class="ad-select" name="seckillTimeId" id="seckillTimeId" style="margin-left: 10px;width: 60px;">
		    				<c:forEach items="${seckillTimeList}" var="seckillTime">
								<option value="${seckillTime.id}">${seckillTime.startHours}:${seckillTime.startMin}</option>
		    				</c:forEach>
                            </select>	
		    				</c:if>
		    				<span id="expectedDateSpan"></span>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>优势说明</td>
		    			<td><textarea class="form-control" rows="3" placeholder="" style="height: 57px;" id="remarks" name="remarks"></textarea></td>
		    		</tr>
		    		<tr id="comfirmDiv">
		    			<td colspan="2">
		    				<div>
		    					<a href="javascript:;" class="btn btn-default" id="save">提交</a>
		    				</div>
		    			</td>
		    		</tr>
				</table>
			</form>
		</div>

	</div>
</div>
</div>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<!-- Bootstrap -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/utils.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
  <script type="text/javascript">
  function editProductSku(id){
		$.ajax({
	        url: "${ctx}/product/productSkuEdit?id="+id, 
			type : 'GET',
	        success: function(data){
	            $("#skuModal").html(data);
	            $("#skuModal").modal();
	        },
		    error:function(){
		    	swal('页面不存在');
		    }
		});

	}
  
	$(function(){
		$("#expectedDate").on('change',function(){
			var isSpecial = $("#isSpecial").val();
			if(isSpecial == "1"){
				return;
			}
			var type = $("#type").val();
			var date = $(this).val()+" 10:00:00";
			var limitDate = '${limitDate}';
			var nowDate = '${nowDate}';
			if(date>limitDate){
				swal('期望日期不能大于'+limitDate);
				$(this).val("");
				return;
			}
			if(date < nowDate){
				swal('期望日期不能小于'+nowDate);
				$(this).val("");
				return;
			}
			if(date == (nowDate+" 10:00:00")){
				swal('期望日期不能等于'+nowDate);
				$(this).val("");
				return;
			}
			var productId = $("#productId").val();
			var seckillTimeId = $("#seckillTimeId").val();
			if(productId){
				$.ajax({
			        method: 'POST',
			        url: '${ctx}/singleProductActivity/getEnrollCount',
			        data: {productId:productId,expectedDate:date,type:type,isSpecial:isSpecial,seckillTimeId:seckillTimeId},
			        cache : false,
					async : false,
			        dataType: 'json'
			    }).done(function (result) {
			        if (result.returnCode =='0000') {
			        	var count = result.returnData.count;
			        	var limitBrandCount = result.returnData.limitBrandCount;
			        	if(count>=0 && limitBrandCount>=0){
			        		$("#expectedDateSpan").text("");
			        		var allowCount = limitBrandCount-count;
			        		if(allowCount<=0){
			        			allowCount = 0;
			        		}
			        		$("#expectedDateSpan").text("当前期望日期内剩余可报名数为"+allowCount);
				        	if(count>=limitBrandCount){
				        		swal('已超过提报的数量。');
				        	}
			        	}
			        }	
			    });
			}
			
		});		
		
		$("#eachMoney").on('keyup',function(){
			var eachMoney = $(this).val();
			var maxCount = $("#maxCount").val();
			if(maxCount && maxCount>0){
				var disCount = parseFloat(eachMoney)*parseFloat(maxCount);
				var originalPrice = $("#originalPrice").val();
				var originalPriceArray = originalPrice.split("-");
				if(originalPriceArray.length==1){
					var minTransactionPrice = parseFloat(originalPriceArray[0])-parseFloat(disCount);
					if(minTransactionPrice>0){
						$("#minTransactionPrice").text(Math.round(minTransactionPrice*100)/100);
					}else{
						swal('最低成交价需大于0');
						return;
					}
				}else if(originalPriceArray.length>1){
					var num1 = parseFloat(originalPriceArray[0])-parseFloat(disCount);
					if(num1<=0){
						swal('最低成交价需大于0');
						return;
					}else{
						var num2 = parseFloat(originalPriceArray[1])-parseFloat(disCount);
						$("#minTransactionPrice").text(Math.round(num1*100)/100+"-"+Math.round(num2*100)/100);
					}
				}
			}
		});
		
		$("#maxCount").on('keyup',function(){
			var eachMoney = $("#eachMoney").val();
			var maxCount = $(this).val();
			if(maxCount && maxCount>0){
				var disCount = parseFloat(eachMoney)*parseFloat(maxCount);
				var originalPrice = $("#originalPrice").val();
				var originalPriceArray = originalPrice.split("-");
				if(originalPriceArray.length==1){
					var minTransactionPrice = parseFloat(originalPriceArray[0])-parseFloat(disCount);
					if(minTransactionPrice>0){
						$("#minTransactionPrice").text(Math.round(minTransactionPrice*100)/100);
					}else{
						swal('最低成交价需大于0');
						return;
					}
				}else if(originalPriceArray.length>1){
					var num1 = parseFloat(originalPriceArray[0])-parseFloat(disCount);
					if(num1<=0){
						swal('最低成交价需大于0');
						return;
					}else{
						var num2 = parseFloat(originalPriceArray[1])-parseFloat(disCount);
						$("#minTransactionPrice").text(Math.round(num1*100)/100+"-"+Math.round(num2*100)/100);
					}
				}
			}
		});
		
		var table;
		$("#btn-query").on('click',function(){
			var searchKeywrod = $.trim($("#searchKeywrod").val());
			if(searchKeywrod == ""){
				swal("请输入搜索关键词");
				return false;
			}
			$("#tableDiv").show();
			if(!table){
				table = $('#datatable').dataTable({
			        "ajax": function (data, callback, settings) {
			            var param = $("#searchForm").serializeArray();
			            
			            param.push({"name":"pagesize","value":data.length});
			            if (data.start > 0) {
			                param.push({"name":"page","value":data.start/data.length+1});
			            } else {
			                param.push({"name":"page","value":1});
			            }
			            
			            $.ajax({
			                method: 'POST',
			                url: '${ctx}/singleProductActivity/getProductList',
			                data: param,
			                dataType: 'json'
			            }).done(function (result) {
			                if (result.returnCode =='0000') {
			                    var returnData = {};
			                    returnData.recordsTotal = result.returnData.Total;
			                    returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
			                    returnData.data = result.returnData.Rows;
			                    callback(returnData);
			                    $("input[name='product']").on('click',function(){
			                    	var $this = $(this);
			                    	var productId = $this.data("productid");
			                    	$("#productId").val(productId);
			            			var productName = $this.data("productname");
			            			$("#productName").text(productName);
			            			var originalPrice = $this.parent().parent().find("td").eq(3).text();
			            			$("#originalPrice").val(originalPrice);
			            			var productStock = $this.data("stock");
			            			$("#productStock").text("商品当前库存："+productStock);
			            			var discountMin = $this.data("discountmin");
			            			var discountMax = $this.data("discountmax");
			            			var discountDesc;
			            			if(discountMin == discountMax){
			            				if(discountMin){
				            				discountDesc=Math.ceil(discountMin*10)/10;
			            				}else{
			            					discountDesc=0;
			            				}
			            			}else{
			            				if(!discountMin){
			            					discountMin=0;
			            				}
			            				if(!discountMax){
			            					discountMax=0;
			            				}
			            				discountDesc=Math.ceil(discountMin*10)/10+"-"+Math.ceil(discountMax*10)/10;
			            			}
			            			$("#activityPriceDesc").html(originalPrice+"元("+discountDesc+"折)&nbsp;&nbsp;<a class='table-opr-btn' href='javascript:void(0);' onclick='editProductSku("+productId+")'>[修改SKU价格]</a><div id='activityPriceDiv' style='display:none;'>"+discountDesc+"</div>");
			            		});
			                }
			            });
			        },
			        "language": {"url": '${ctx}/static/cn.json'},
			        "autoWidth": true,   // 禁用自动调整列宽
			        "stripeClasses": ['odd', 'even'], // 为奇偶行加上样式，兼容不支持CSS伪类的场合
			        "order": [[1, 'asc']],          // 取消默认排序查询,否则复选框一列会出现小箭头
			        "processing": true,  // 隐藏加载提示,自行处理
			        "serverSide": true,   // 启用服务器端分页
			        "searching": false,   // 禁用原生搜索
			        "bSort": false,
			        "bServerSide": true,
			        "columns": [
			            {"data": "id","title":"选","render": function (data, type, row, meta) {
			            	return '<input type="radio" data-discountmin="'+row.discountMinTruncate+'" data-discountmax="'+row.discountMaxTruncate+'" data-productid="'+row.id+'" data-productname="'+row.name+'" data-stock="'+row.stock+'" name="product">';
			            }},
			            {"data": "id","title":"商品主图","render": function (data, type, row, meta) {
			            	if(row.pic){
				            	var pic=row.pic.substring(0, row.pic.lastIndexOf("."))+"_M"+row.pic.substring(row.pic.lastIndexOf("."));
				                return '<div class="width-60"><img src="${ctx}/file_servelt'+pic+'"></div>';
			            	}else{
			            		return '<div class="width-60"><img src="${ctx}/file_servelt"></div>';
			            	}
			            }},
			            {"data": "id","title":"商品名称","render": function (data, type, row, meta) {
						    return row.name;
			            }},
			            {"data": "id","title":"活动价格","render": function (data, type, row, meta) {
			            	if(row.salePriceMin==row.salePriceMax){
			            		if(row.salePriceMin){
					            	return row.salePriceMin;
			            		}else{
				            		return "0";
			            		}
			            	}else{
			            		return row.salePriceMin+"-"+row.salePriceMax;
			            	}
			            }},
			            {"data": "id","title":"可用库存","render": function (data, type, row, meta) {
			            	return row.stock;
			            }},
			            {"data": "id","title":"吊牌价","render": function (data, type, row, meta) {
			            	var html = [];
							html.push(row.tagPriceMin);
							if(row.tagPriceMin!=row.tagPriceMax){
								html.push("-");
								html.push(row.tagPriceMax);
							};
						    return html.join("");
			            }},
			            {"data": "id","title":"商品好评率","render": function (data, type, row, meta) {
			            	return row.goodCommentRate;
			            }},
			            {"data": "id","title":"商品的总销量","render": function (data, type, row, meta) {
			            	return row.totalSalesVolume;
			            }}
			        ]
			    }).api();
			}else{
				table.ajax.reload();
			}
		});
		
		$('.datePicker').datetimepicker(
				{
					minView: "month", //选择日期后，不会再跳转去选择时分秒
					format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
					language: 'zh-CN', //汉化
					autoclose:true //选择日期后自动关闭
				}
		);

		$("#save").on('click',function(){
			var content = $("#remarks").val().length;
			if(content > 256){
				swal("最多只能输入256字");
				return false;
			}
			/*  var expectedDate = $("#expectedDate").val();
			if(expectedDate<=new date().format("yyyy-MM-dd")){
				swal("期望日期必须大于当天");
				return false;
			}  */

			if($("input[name='product']:checked").length == 0){
				swal("请选择出要参与当前活动的商品");
				return false;
			}
			var discountDesc = $("#activityPriceDiv").text();
			if(discountDesc<1){
				swal("折扣不可低于一折");
				return false;

			}
			var productId = $("#productId").val();
			if(!table){
				swal("请先搜索选择出要参与当前活动的商品");
				return false;
			}else{
				if(!productId){
					swal("请选择出要参与当前活动的商品");
					return false;
				}
			}
			<c:if test="${sessionScope.mchtInfo.mchtType != '1'}">
				var comparePrice = $.trim($("#comparePrice").val());
				if(comparePrice == ""){
					swal("竞品价格必填");
					return false;
				}
				if(parseFloat(comparePrice) <= 0){
					swal("竞品价格必须大于0");
					return false;
				}
			</c:if>

			var originalPrice = $("input[name='product']:checked").parent().parent().find("td").eq(3).text();
			var originalPriceArray = originalPrice.split("-");
			var minPrice = originalPriceArray[0];
			<c:if test="${not empty limitBrandCount && isSpecial == 0}">

			</c:if>
			$("#activityPrice").val(minPrice);//STORY #877
			var expectedDate = $("#expectedDate").val();
			if(!expectedDate){
				swal("期望日期不能为空");
				return false;
			}
			var now = new Date();
			if(expectedDate<=now.format("yyyy-MM-dd")){
				swal("期望日期必须大于等于今天日期");
				return false;
			}
			var type = $("#type").val();
			if(type==3 && ($('#seckillTimeId').val() == '' || $('#seckillTimeId').val() == null)){
				swal('平台端未启用限时抢购时间点设置');
				return;
			}

			if(type!=3){
				expectedDate = expectedDate+" 10:00:00";
				$("#expectedDate").val(expectedDate);
			}else{
				var seckillTime = $("#seckillTimeId").find("option:selected").text();
				expectedDate = expectedDate+" "+seckillTime+":00";
				$("#expectedDate").val(expectedDate);
			}
			var remarks = $("#remarks").val();
			<c:if test="${sessionScope.mchtInfo.mchtType != '1'}">
				if(!remarks){
					swal("优势说明必填");
					return false;
				}
			</c:if>
			<c:if test="${type eq 10}">
				var eachMoney = $("#eachMoney").val();
				var maxCount = $("#maxCount").val();
				if(!eachMoney){
					swal("邀请一人可减免的金额不能为空");
					return false;
				}
				if(!maxCount){
					swal("最多邀请人数不能为空");
					return false;
				}
				if(eachMoney<=0){
					swal("邀请一人可减免的金额必须大于0");
					return false;
				}else{
					if(eachMoney>=10000){
						swal("邀请一人可减免的金额必须小于10000");
						return false;
					}
				}
				if(maxCount<=0){
					swal("最多邀请人数必须大于0");
					return false;
				}
				var disCount = parseFloat(eachMoney)*parseFloat(maxCount);
				var minTransactionPrice = parseFloat(originalPriceArray[0])-parseFloat(disCount);
				if(minTransactionPrice<=0){
					swal('最低成交价需大于0');
					return false;
				}
			</c:if>
			if(type == 2 || type == 6){
				<c:if test="${sessionScope.mchtInfo.mchtType != '1'}">
					var svipDiscount = $("#svipDiscount").val();
					if(!svipDiscount){
						swal('请选择SVIP折扣');
						return false;
					}
				</c:if>
			}
			/* var allowCount = $("#allowCount").text();
			if(allowCount<=0){
				swal('已超过提报的数量。');
				return false;
			} */
			$.ajax({
		        method: 'POST',
		        url: '${ctx}/singleProductActivity/save',
		        data: $("#saveForm").serialize(),
		        cache : false,
				async : false,
		        dataType: 'json'
		    }).done(function (result) {
		        if (result.returnCode =='0000') {
		        	$("#toAddModal").modal('hide');
		        	swal("提交成功");
					/* if(type==1){
						var newCount = $("#newCount").text();
						--newCount;
						if(newCount<0){
							$("#newCount").text(0);
						}else{
							$("#newCount").text(newCount);
						}
					}else if(type==2){
						var hotCount = $("#hotCount").text();
						--hotCount;
						if(hotCount<0){
							$("#hotCount").text(0);
						}else{
							$("#hotCount").text(hotCount);
						}
					}else if(type==3){
						var killCount = $("#killCount").text();
						--killCount;
						if(killCount<0){
							$("#killCount").text(0);
						}else{
							$("#killCount").text(killCount);
						}
					}else if(type==4){
						var newUserKillCount = $("#newUserKillCount").text();
						--newUserKillCount;
						if(newUserKillCount<0){
							$("#newUserKillCount").text(0);
						}else{
							$("#newUserKillCount").text(newUserKillCount);
						}
					}else if(type==5){
						var integralCount = $("#integralCount").text();
						--integralCount;
						if(integralCount<0){
							$("#integralCount").text(0);
						}else{
							$("#integralCount").text(integralCount);
						}
					}else if(type==6){
						var brokenCodeCount = $("#brokenCodeCount").text();
						--brokenCodeCount;
						if(brokenCodeCount<0){
							$("#brokenCodeCount").text(0);
						}else{
							$("#brokenCodeCount").text(brokenCodeCount);
						}
					}else if(type==7){
						var cutFreeAllCount = $("#cutFreeAllCount").text();
						--cutFreeAllCount;
						if(cutFreeAllCount<0){
							$("#cutFreeAllCount").text(0);
						}else{
							$("#cutFreeAllCount").text(cutFreeAllCount);
						}
					}else if(type==8){
						var invitationFreeCount = $("#invitationFreeCount").text();
						--invitationFreeCount;
						if(invitationFreeCount<0){
							$("#invitationFreeCount").text(0);
						}else{
							$("#invitationFreeCount").text(invitationFreeCount);
						}
					}else if(type==10){
						var assistanceCount = $("#assistanceCount").text();
						--assistanceCount;
						if(assistanceCount<0){
							$("#assistanceCount").text(0);
						}else{
							$("#assistanceCount").text(assistanceCount);
						}
					} */
		        	return false;
		        }else{
		        	if(result.returnMsg){
		        		swal(result.returnMsg);
		        	}else{
			        	swal("提交失败，请稍后重试");
		        	}
		        	return false;
		        }
		    });
		});

		$("#searchKeywrod").keydown(function(e){
			if(e.keyCode==13){
	    		$("#btn-query").click();
	            return false;
	    	}
	    });
	});
  </script>
</body>
</html>
