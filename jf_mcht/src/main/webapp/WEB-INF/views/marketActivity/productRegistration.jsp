<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>活动商品管理</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
    <style type="text/css">
        .color-1{
            color: #9D999D;
        }
        .color-2{
            color: #FC0000;
        }
        .color-3{
            color: #EFD104;
        }
        .color-4{
            color: #00FC28;
        }
        .color-5{
            color: #0351F7;
        }
        .color-6{
            color: #DF00FB;
        }
        .hidden{
            display:none;
        }
        #popDiv {
            width:446px;
            height:294px;
            position:absolute;
            left:238px;
            top:90px;
            z-index:1;
            border:4px solid #7A7A7A;
            background-color: #f2f2f2;
        }
         .change {
            background: white;
            color: #131313;
            border-top: 3px solid red;
        }   
         .lines{
    		height: 4px;
   		 	background: rgba(221,221,221,1);
    		width: 100%;
    		margin-bottom: 40px;
	}   
    </style>
</head>

<body>

<div class="x_panel container-fluid">
	<input id="count" type="hidden"/>
	<input id="counted" type="hidden"/>
	<input id="chooseUpDate" type="hidden"/>
    <div class="row content-title">
        <div class="t-title">
       		 	商品报名
         <a href="JavaScript:returnSignUpImmediately();">返回</a>
         </div>
    </div>
    <div class="clearfix"></div>
    <div class="search-container container-fluid">
        <form class="form-horizontal" id="searchForm">
				<label for="productBrand" class="col-md-1 control-label search-lable">期望上线日期：</label>
				<div class="col-md-5 search-condition">
						<input class="form-control datePicker" type="text" id="upDate" name="upDate" data-date-format="yyyy-mm-dd" value="${upDate}" autocomplete="off" readonly="readonly">
				</div>                           
           	 	<div class="col-md-5 text-center search-btn">
                	<button type="button" style="height:27px;" id="productChoose">商品选择(${counted}/${count})</button>
           	    </div>   
        </form>
    </div>
    <div class="lines"></div>
    <div class="x_content container-fluid" id="content" style="display:none;">
        <div class="row">
            <div class="col-md-12 datatable-container">
                <table border="1" bordercolor=#ddd style="width:90%;margin-left: 5%;" id="trendsTable">
                    <thead>
                    <tr role="row">
                        <th width="78">商品</th>
                        <c:if test="${activityType ne '11'}">
                            <th width="88">推荐文案</th>
                        </c:if>
                        <th width="68">活动价</th>
                        <th width="68">SVP折扣</th>
                        <th width="68">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <div>
                	<button type="button"  class="btn btn-info" id="btn-query" style="margin-top: 40px;margin-left: 45%;">提交报名</button>
                </div>            
            </div>
        </div>
    </div>
    <img alt="" src="${pageContext.request.contextPath}/static/images/addProduct.png" style="margin-top:60px;margin-left:27%" id="defaultImg">
</div>
<!-- 	查看信息框 -->
<div class="modal fade" id="myViewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static"></div>
<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<script>
	var addId = [];
	var notAdd = [];
    $(document).ready(function () {
    	$('.datePicker').datetimepicker(
    			{
    				minView: "month", //选择日期后，不会再跳转去选择时分秒
    				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
    				language: 'zh-CN', //汉化
    				autoclose:true, //选择日期后自动关闭
    				startDate: '+1d',  // 窗口可选开始时间
    		        endDate: '+15d',  // 窗口可选截止时间
    		        initialDate:'+1d'
    			}
    	).on('changeDate',function(ev){
    		//如果切换当前日期，不做处理
    		if($('#upDate').val() == $('#chooseUpDate').val()){
    			return;
    		}else if($('#chooseUpDate').val() == ""){//如果日期为空，则不弹窗
    			$('#chooseUpDate').val($('#upDate').val());
    			$.ajax({
        	        url: "${ctx}/market/productAmount?upDate="+$('#upDate').val()+"&activityType="+${activityType}, 
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
    		}else {//如果日期改变
    			//如果当前列表没有商品，则不用弹窗
    			if($('#content tr').length == 1){
    				$.ajax({
            	        url: "${ctx}/market/productAmount?upDate="+$('#upDate').val()+"&activityType="+${activityType}, 
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
    				addId = [];
					notAdd = [];
					$('#productChoose').text("商品选择("+$('#counted').val()+"/"+$('#count').val()+")");
					$('#chooseUpDate').val($('#upDate').val());
    			}else{
    				swal({
      				  title: "选择新的日期后，需重新选择商品",
      				  type: "warning",
      				  showCancelButton: true,
      				  confirmButtonText: "确认选择",
      				  cancelButtonText: "取消",
      				  closeOnConfirm: true
      				},
      				function(isConfirm){
      					if(isConfirm){
      						$.ajax({
      	            	        url: "${ctx}/market/productAmount?upDate="+$('#upDate').val()+"&activityType="+${activityType}, 
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
      						addId = [];
          					notAdd = [];
          					$('#productChoose').text("商品选择("+$('#counted').val()+"/"+$('#count').val()+")");
          					$('#chooseUpDate').val($('#upDate').val());
          					//清空选择的商品
          					$('#content tr').each(function(index){
          						if(index != 0){
          							$(this).remove();
          						}
          					});				
          					$('#content').hide();
          					$('#defaultImg').show();
      					}else{
      						$('#upDate').val($('#chooseUpDate').val());
      					}
      				});
    			}
    		}
    	}); 	
    });
    
	function editProductSku(id){
		$.ajax({
	        url: "${ctx}/product/productSkuEdit?id="+id, 
			type : 'GET',
	        success: function(data){
	            $("#viewModal").html(data);
	            $("#viewModal").modal();
	        },
		    error:function(){
		    	swal('页面不存在');
		    }
		});

	}
	
	function quit(id){
		swal({
			  title: "是否将商品从报名活动中移除",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonText: "确定",
			  cancelButtonText: "取消",
			  closeOnConfirm: true
			},
			function(){
				$.ajax({
					url : "${ctx}/market/quitActivity?id="+id,
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					timeout : 30000,
					success : function(data) {
						if (data.returnCode=="0000") {
							table.ajax.reload( null, false );
						} else {
							swal({
								  title: data.returnMsg,
								  type: "error",
								  confirmButtonText: "确定"
								});
						}
						
					},
					error : function() {
						swal({
							  title: "处理失败！",
							  type: "error",
							  timer: 1500,
							  confirmButtonText: "确定"
							});
					}
				});
			});
	}
	
	//弹出商品选择框
    $('#productChoose').on('click',function(){  	
		if($('#upDate').val() == ""){
			swal({
				  title: "期望日期不可为空",
				  type: "error",
				  timer: 1500,
				  confirmButtonText: "确定"
				});
			return;
		}else{
			$.ajax({
	    	    url: "${ctx}/market/productChoose?activityType="+${activityType}+"&upDate="+$('#upDate').val(),
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
	
    $('#trendsTable').on('click','#del',function(){
    	var productId = $(this).attr('data_value');
		var index = contain(productId);
		addId.splice(index,1);
		//删除表格
		$(this).parent().parent().parent().remove();
		if($('#content').find('tr').length == 1){
			$('#content').hide();
			$('#defaultImg').show();
		}
		$('#counted').val(parseInt($('#counted').val())-1);
		$('#productChoose').text("商品选择("+(parseInt($('#counted').val()))+"/"+$('#count').val()+")");
    });

	function contain(productId){
		var length = addId.length;
		while(length--){
			if(addId[length] == productId){
				return length;
			}
		}
		return -1;
	}
	
	$('#btn-query').click(function(){
		var tag = true;
		//推荐文案不可为空
		if(${activityType eq 1}){
			$('[id="remarks"]').each(function(){
	    		if($(this).val().match(/^[ ]*$/)){
	    			swal({
						  title: "商品推荐文案不可为空",
						  type: "error",
						  timer: 3000,
						  confirmButtonText: "好的"
						});
	    			tag = false;
	    			return;
	    		}
	    	});
		}   	
		if(!tag){
			return;
		}	
		var list="";
		var paramList = [];
		$('[id=del]').each(function(){
			var productId = $(this).attr('data_value');
			var item = {productId:productId,remarks:$(this).parent().parent().prev().prev().prev().children('textarea').val()};
			paramList.push(item);
		});
		list=JSON.stringify(paramList);
		$.ajax({
			method: 'POST',
			url: '${ctx}/market/signUp',
			data: {paramList:list,activityType:${activityType},upDate:$('#upDate').val()},
			dataType: 'json',
			cache : false,
			async : false,
			timeout : 30000,
			success:function(data){
				if(data.returnCode == '0000'){
					swal({
						  title: "恭喜您，报名信息提交成功！",
						  type: "success",
						  confirmButtonText: "查看审核进度"
						},function(){
							getContent("${ctx}/market/enrolledProduct");
						});
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
	});
	
	function returnSignUpImmediately(){
		getContent("${ctx}/market/signUpImmediately?activityType="+${activityType});
	}
</script>
</body>
</html>
