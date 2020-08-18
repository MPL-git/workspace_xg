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

	.shijian {
		width:55px;
		height:13px;
		font-size:14px;
		font-family:SourceHanSansCN-Regular;
		font-weight:400;
		color:rgba(153,153,153,1);
	}
	.content-titles {
	font-size: 14px;
    font-weight: bold;
	padding: 0 20px;
    margin: 0 -20px;
    border-bottom: 0px solid #ddd;
}
    </style>
</head>

<body>

	<div class="panel panel-default home-content-panner company-info-container" style="height: auto;" >
   			<div class="row content-title">
        		<div class="t-title">
       		 		店铺信息
         		<a href="JavaScript:returnSignUpImmediately();">返回</a>
         		</div>
    		</div>
    		
    		<div class="row content-titles" style="margin-top:8px">
        		<span class="t-title" >店铺名称 : ${mchtName }</span> 
    		
    		
    		<c:if test="${activityType==9}">
    		
    		<br><br><span class="t-title">店铺故事</span>
    		
    					<div style="padding: 0 20px 20px 82px;">
        				<span>故事简介 : </span><span id="storyIntroduction" >asdfas</span><a style="float:right" href="javascript:addShopStory();" style="width:30%;">去管理</a>
        				</div>
        				
        				<div style="padding: 0 82px;" >
        				<span >故事详情 : </span><span id="storyDetail" ></span>
    					</div>
    		</c:if>
    		</div>
	</div>
	

	<div class="x_panel container-fluid">
	<input id="count" value="${count }" type="hidden"/>
	<input id="counted" value="${counted }" type="hidden"/>
	<input id="auditStatus" value="${auditStatus }" type="hidden"/>
	<input id="productIdList" value="${productIdList }" type="hidden"/>
	<input id="chooseUpDate" type="hidden"/>
	
    
    <div class="clearfix"></div>
    <div class="search-container container-fluid">
        <form class="form-horizontal" id="searchForm">
        <input type="hidden" id="activityType" name="activityType" value="${activityType}"/>
                 
         	<div class="col-md-5 text-center search-btn">
				<span class="required">*</span><span style="color:#000000;font-size:18px" >店铺推荐商品</span> 
              	<button type="button" style="height:27px;" id="productChoose">商品选择(${counted}/${count})</button>
            <c:if test="${activityType==8 && auditStatus!=0 && auditStatus!=1 }">
				<span class="shijian">展示在每日好店店铺栏目入口处</span> 
			</c:if> 
			<c:if test="${activityType==9 && auditStatus!=0 && auditStatus!=1}">
				<span class="shijian">展示在大学生创业店铺栏目入口处</span> 
			</c:if> 	
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
                        <th width="88">商城价</th>
                        <th width="68">活动价</th>
                        <th width="68">库存</th>
                        <th width="68">状态</th>
                        <th width="68">操作</th>   
                    </tr>
                    </thead>
                    <tbody   id="tableBody"><!-- 店铺管理中  已添加的商品  -->
                    
      					<c:forEach var="row" items="${sourceNicheProductList}">
							<tr>
								<td>
									<div class="is-check">
										<div class="width-60"><img src="${ctx}/file_servelt${row.pic}"></div>
										<div class="width-226">
										<p class="h34">${row.pName}</p>
										<div>
										<span style="color: #999;margin: 5px 0 0;">货号：${row.pArtNo }&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<span style="float: left; margin: 0;">ID：${row.pCode}
										</span>
										<a style="float: right;" href="https://m.xgbuy.cc/share_buy.html?id=${row.productId}" target="_blank">预览</a>
										</div>
										</div>
									</div>
								</td>
								
								<td class=" hiddenCol">
								${row.mallPriceMin}
								<c:if test="${row.mallPriceMin!=row.mallPriceMax }">
								 - ${row.mallPriceMax}
								</c:if>
								</td>
								
								<td>${row.salePriceMin}
								<c:if test="${row.salePriceMin!=row.salePriceMax }">
								 - ${row.salePriceMax}
								</c:if>
								</td>
								
								<td>${row.pStock}</td>
								
								<td>
									<c:if test="${row.pStatus =='0'}">
									 	下架<br>
									</c:if>
									<c:if test="${row.pStatus =='1'}">
									 	上架<br>
									</c:if>
								</td>
								
								<td>
								<div><a id="del" class="table-opr-btn" href="javascript:void(0);" data_value="${row.productId }">删除</a></div>
								</td>
							</tr>
						</c:forEach>          	
                    
                    </tbody>
                </table>
                <div>
                	<c:if test="${auditStatus!=0 && auditStatus!=1}">
                	<button type="button"  class="btn btn-info" id="btn-query" style="margin-top: 40px;margin-left: 45%;">提交报名</button>
                	</c:if>
                	<c:if test="${auditStatus==0||auditStatus==1}">
                	<button type="button"  class="btn btn-info" id="btn-query" style="margin-top: 40px;margin-left: 45%;">保存</button>
                	</c:if>
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
     	var shopStoryDetails = "${shopStoryDetail}";//故事详情.的第一段文字
     	var shopStoryDetail = shopStoryDetails.replace(new RegExp("<br>","gm"),"");
    	var tempShopStoryDetail ="";
    	var storyIntroductions = "${storyIntroduction}";//故事简介
    	var storyIntroduction = storyIntroductions.replace(new RegExp("<br>","gm"),"");
    	var tempStoryIntroduction ="";
    	var shopStoryDetailList = "${shopStoryDetailList}";//故事详情
    	

    	
   	     if(storyIntroduction&&storyIntroduction.length >30){
   	    	tempStoryIntroduction = storyIntroduction.substring(0,30)+"..."
   	    	$("#storyIntroduction").html(tempStoryIntroduction)
  		  }else{
  			$("#storyIntroduction").html(storyIntroduction)
  		  }
    	
   	 	 if(shopStoryDetail&&shopStoryDetail.length >30){
   	 		tempShopStoryDetail = shopStoryDetail.substring(0,30)+"..."
 	    	$("#storyDetail").html(tempShopStoryDetail)
		  }else{
			$("#storyDetail").html(shopStoryDetail)
		  } 
    	
    	var auditStatus = $("#auditStatus").val();
    	
     	if(auditStatus=="0"||auditStatus=="1"){
    		$('#content').show();
    		$('#defaultImg').hide();
    	} 
    	var list=$("#productIdList").val();
    	if(list!=null &&list.length>0){
   			var idList = list.split(",")
    		for(var i = 0;i<idList.length;i++){
    			addId.push(idList[i]*1);
    		}
    	}  
	
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

		$.ajax({
    	    url: "${ctx}/market/shopActiviChoose?activityType="+${activityType},
    	    success: function(data){
    	          $("#myViewModal").html(data);
    	          $("#myViewModal").modal();
    	    },
    		error:function(){
    		   	  swal('页面不存在');
    		}
    	});
    });
	
	//跳转店铺故事
    function addShopStory(){
    	getContent("${ctx}/shopStory/shopStoryIndex");
	}
	
	
	
	
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
	
	//提交
	$('#btn-query').click(function(){
		var activityType= "${activityType}";
		if(activityType=='9'){
		//店铺故事不能为空
		var storyIn = "${storyIntroduction}";//故事简介
    	var shopStoryDe = "${shopStoryDetailList}";//故事详情
		if(storyIn==""||shopStoryDe==""||shopStoryDe==null||storyIn==null){
			swal("店铺故事未完善");
			return;
			}
		}
		
		var list="";
		var paramList = [];
		var productNumber=[]
		$('[id=del]').each(function(){
			var productId = $(this).attr('data_value');
			productNumber.push(productId)
			var item = {productId:productId};
			paramList.push(item);
		});
		//商品数量不能小于三个
		if(productNumber.length<3){
			swal({
				  title: "至少选择三款推荐商品",
				  type: "error",
				  timer: 3000,
				  confirmButtonText: "好的"
				});
			return;
		}
		
		list=JSON.stringify(paramList);
		$.ajax({
			method: 'POST',
			url: '${ctx}/market/shopSignUp',
			data: {paramList:list,activityType:${activityType}},
			dataType: 'json',
			cache : false,
			async : false,
			timeout : 30000,
			success:function(data){
				if(data.returnCode == '0000'){
					swal({
						  title: "恭喜您，报名信息提交成功！",
						  type: "success",
						  confirmButtonText: "保存成功"
						},function(){
							getContent("${ctx}/market/signUpImmediately?activityType=${activityType}");
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
