<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>商品管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />

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

.remarks_column_class{
text-align: left;
}

.sweetAlert {
        margin: 0 auto;
        left: 0;
        right: 0;
        font-size: 14px;
        line-height: 24px;
    }
.sweetAlert p {
        font-size: 14px;
        line-height: 24px;
    }
.sweet-alert h2 {
    margin: 10px 0;
    line-height: 10px; 
}

.video_box {
	z-index: 2222;
	display: block;
}

.black_box {
	background: #000;
	opacity: 0.6;
	left: 0;
	top: 0;
	z-index: 1111;
	position: fixed;
	height: 100%;
	width: 100%;
}
.video_close {
	position: absolute;
	top: -14px;
	right: -12px;
}    
</style>
</head>

<body>
	<div class="x_panel container-fluid">

		<div class="row content-title">
			<div class="t-title">
				商品管理
				<a href='javascript:void(0);' onclick="addProduct(1);" >添加</a>
			</div>
		</div>
		<div class="search-container container-fluid">
				<input type="hidden" name="pageNumber" id="pageNumber" value="${pageNumber}">
				<form class="form-horizontal" id="searchForm">
				<div class="form-group">
					<label for="productBrand" class="col-md-1 control-label search-lable">品牌名称：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="search_brandId" id="search_brandId">
						   <option value="">--请选择--</option>
						   <c:forEach var="productBrand" items="${productBrandList}">
						   <option value="${productBrand.productBrandId}">${productBrand.productBrandName}</option>
						   </c:forEach>
                          </select>
					</div>
					<label for="productType" class="col-md-1 control-label search-lable">分类：</label>
					<div class="col-md-2 search-condition">
						   <select onchange="onchangeProductType1();" class="form-control productType1-select" name="productType1" id="productType1-select">
                          </select>
					</div>
					<div class="col-md-2 search-condition">
						   <select onchange="onchangeProductType2();" class="form-control productType2-select" name="productType2" id="productType2-select">
                          </select>
					</div>
					<div class="col-md-2 search-condition">
						   <select class="form-control productType3-select" name="productType3" id="productType3-select">
                          </select>
					</div>
				
					
				</div>
				
				<div class="form-group">
				   <label for="activityStatus" class="col-md-1 control-label search-lable">商品搜索：</label>
<!-- 				   <div class="col-md-2 search-condition"> -->
<!--                           <select  class="form-control" name="search_isShow" id="search_isShow"> -->
<!-- 						   <option value="">--是否隐藏--</option> -->
<!-- 						   <option value="1">显示</option> -->
<!-- 						   <option value="0">隐藏</option> -->
<!--                           </select> -->
<!-- 				   </div> -->
					
				   <div class="col-md-2 search-condition">
                          <select  class="form-control" name="search_status" id="search_status">
						   <option value="">--上架状态--</option>
						   <option value="1">上架</option>
						   <option value="0">下架</option>
                          </select>
					</div>
					
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="search_shopProductCustomTypeId" id="search_shopProductCustomTypeId">
						   <option value="">--所属分类--</option>
						   <c:forEach items="${shopProductCustomTypeList}" var="shopProductCustomType">
						   		<option value="${shopProductCustomType.id}">${shopProductCustomType.name}</option>
						   </c:forEach>
						   <option value="-1">未分类</option>
                          </select>
					</div>
					
				   <div class="col-md-2 search-condition" >
						   <select class="form-control" name="searchKeywrodType" id="searchKeywrodType">
						   <option value="1">商品名称</option>
						   <option value="2">商品货号</option>
						   <option value="3">商品ID</option>
						   <option value="4">商家备注</option>
						   <option value="5">活动ID</option>
                          </select>
					</div>
					<div class="col-md-2 search-condition" >
						 <input class="form-control nameWidth200" type="text"  id="searchKeywrod" name="searchKeywrod" >
					</div>

					<div class="col-md-3 text-center search-btn">
						             <button type="button"  class="btn btn-info" id="btn-query">搜索</button>
                                    <!-- <i class="glyphicon glyphicon-search"></i>查询</button> -->
					</div>
					
				</div>
				
				
				
				
				
			</form>
			
			
			
			
			
			

		</div>
		<div class="clearfix"></div>
		<div class="x_content container-fluid">
			<div class="row">
				<div class="col-md-12 datatable-container">
					<div class="datatable-caption">
						<span class="mr">
							<input type='checkbox' class='checkAll' onclick='selectAll();' value='ccc'>全选
						</span>
						<button class="btn btn-all" onclick="appendCustomType();">追加分类</button>
						<button class="btn btn-all" onclick="updateCustomType();">更新分类</button>
						<button class="btn btn-all" onclick="removeCustomType();">清除分类</button>
						<c:if test="${sessionScope.mchtInfo.mchtType != '1'}">
							<button class="btn btn-all" onclick="batchSetSvipDiscount();">批量SVIP折扣</button>
						</c:if>
						<a href="javascript:;" onclick="describe();">说明</a>
					</div>
					<table id="datatable"
						class="table table-striped table-bordered dataTable no-footer"
						role="grid" aria-describedby="datatable_info">
					</table>
				</div>
			</div>
		</div>
	</div>
	
<!--弹出Div-->
<div class="video_box" style="position:fixed; width:355px; height:170px; top:35%; left:42%; display: none;" id="isShowDiv">
	<a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					提醒
				</h3>
			</div>
			
			<form class="form-horizontal" role="form" style="margin-top: 10px;">
				<input type="hidden" id="showSetting" value="${showSetting}">
				<input type="hidden" id="hideSetting" value="${hideSetting}">
				<input type="hidden" id="productId" >
				<input type="hidden" id="isShow" >
				  <div class="form-group" style="padding-left: 10px;word-break:break-all;" id="remindTitle">
				  </div>
				  <div class="form-group" style="padding-left: 2px;">
					<div class="col-md-7">
					  <input type="checkbox" id="setting" name="setting"><span id="settingTitle"></span>
					</div>
				  </div>
 
				  <div class="form-group">
					<div class="col-sm-offset-4 col-md-7">
					  <button type="button" class="btn btn-default" id="cancle">取消</button>
					  <a href="javascript:;" class="btn btn-default" id="commit">确定</a>
					</div>
				  </div>
			 </form>
		 </div>
</div>

<!--弹出div End-->
<div class="black_box" style="display: none;"></div>
	
<!-- 	查看信息框 -->

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static"></div>
<div class="modal fade" id="setSvipDiscountModal" tabindex="-1" role="dialog" aria-labelledby="setSvipDiscountModal" data-backdrop="static" >
  <div id="test" class="modal-dialog" role="document" style="width: 400px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">批量SVIP折扣</h4>
      </div>
      <div class="modal-body">
     	<div>
	     	<form id="dataFrom">
			    <label for="name" class="money">SVIP折扣：</label>
			    <select class="ad-select" name="indexSvipDiscount" id="indexSvipDiscount">
					<option value="">请选择</option>
					<option value="0.95" >9.5</option>
					<option value="0.90" >9.0</option>
					<option value="0.85" >8.5</option>
					<option value="0.80" >8.0</option>
					<option value="0.75" >7.5</option>
					<option value="0.70" >7.0</option>
					<option value="0.65" >6.5</option>
					<option value="0.60" >6.0</option>
					<option value="0.55" >5.5</option>
					<option value="0.50" >5.0</option>
					<option value="0.45" >4.5</option>
					<option value="0.40" >4.0</option>
                </select>
			    <br>
			    <div class="modal-footer" style="padding: 10px 0 0;">
				<div style="display: inline-block;text-align: center;">
				    <input type="hidden" id="productIds" value="" name="productIds">
					<button type="button" class="btn btn-default" style="margin-right: 17px;" onclick="saveSvipDiscount();">提交</button>
					<button type="button" class="btn btn-info" data-dismiss="modal">取消</button> 
				</div>
				</div>
			</form> 
	    </div>
      </div>
    </div>
  </div>
</div>
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/producttype-select.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
		<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
		<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>


	<script type="text/javascript">
	function batchSetSvipDiscount(){
		if($(".checkRow:checked").length<=0){
			swal('请选择行');
			return;
		}
		 var productIds="";
	     $(".checkRow:checked").each(function(){
	    	 if(!productIds){
	    		 productIds = $(this).val();
	    	 }else{
		    	 productIds = productIds+","+$(this).val();
	    	 }
	    		 
	     });
	     setSvipDiscount(productIds);
	}	
	
	function setSvipDiscount(productIds){
		 $("#indexSvipDiscount").val("");
		 $("#productIds").val(productIds);
		 $("#setSvipDiscountModal").modal();
	}
	
	function saveSvipDiscount(){
		var indexSvipDiscount = $("#indexSvipDiscount").val();
		if(!indexSvipDiscount){
			swal("请选择SVIP折扣");
			return;
		}
		$.ajax({
			url : "${ctx}/product/setSvipDiscount",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"productIds":$("#productIds").val(),"svipDiscount":indexSvipDiscount},
			timeout : 30000,
			success : function(data) {
				if (data.returnCode == "0000") {
					table.ajax.reload( null, false );
					 $("#setSvipDiscountModal").modal('hide');
				} else {
					swal({
						  title: '备注失败',
						  type: "error",
						  confirmButtonText: "确定"
						});
					return;
				}

			},
			error : function() {
				swal({
					  title: '备注失败',
					  type: "error",
					  confirmButtonText: "确定"
					});
				return;
			}
		});
	}
	
	function addProduct(saleType){
// 		$.ajax({
// 	        url: "${ctx}/product/productAdd", 
// 	        type: "GET", 
// 	        success: function(data){
// 	            $("#viewModal").html(data);
// 	            $("#viewModal").modal();
// 	        },
// 		    error:function(){
// 		    	swal('页面不存在');
// 		    }
// 		});
		getContent("${ctx}/product/productAdd?shopProduct=1&saleType="+saleType);

	}
	
	function describe(){
		swal({ 
			  title:"说明",
			  text: "追加分类： 选中商品原来的分类不变，再给它追加几个分类 \n 更新分类：选中商品原来的分类都清除掉，更新为新的分类 \n 清除分类：选中商品原来的分类都清除掉，商品的分类为空\n\n",
			  customClass: "sweetAlert sweetAlert2"    
			});
	}
	
	
	var dataFromValidate;
	
	function selectSaleType(){
		var selectType=$("#search_saleType").val();
		var selectHtml;
		if(selectType==""||selectType=="1"){
			selectHtml='<option value="">--上架状态--</option><option value="1">上架</option><option value="0">下架</option>';
		}
		if(selectType=="2"){
			selectHtml='<option value="">--活动状态--</option><option value="0">可报名</option><option value="1">报名中</option><option value="2">待开始</option><option value="3">预热中</option><option value="4">活动中</option><option value="5">活动暂停</option><option value="6">未提审</option><option value="7">未通过</option>';
		}
		
		$("#search_status").html(selectHtml);
		
		
	}
	
	function toEditCustomType(id){
		$.ajax({
	        url: "${ctx}/shopProduct/toEditCustomType?id="+id+"&actionType=3", 
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
	function editProduct(id){
		var pageNumber = $("li[class='paginate_button active']").find("a").first().text();
		getContent("${ctx}/product/productEdit?shopProduct=1&id="+id+"&pageNumber="+pageNumber);
	}
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
	
	
	function changeProductStatus(id,status){
		var title;
		if(status==0){
			title="确定要下架此商品？";
		}else{
			title="确定要上架此商品？";
		}
		swal({
			  title: title,
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonText: "确定",
			  cancelButtonText: "取消",
			  closeOnConfirm: false
			},
			function(){
				$.ajax({
					url : "${ctx}/product/changeProductStatus?id="+id,
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {id:id},
					timeout : 30000,
					success : function(data) {
						if (data.returnCode=="0000") {
							swal.close();
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
	
	function updateShowOrHide(id,isShow){
		$("#productId").val(id);
		$("#isShow").val(isShow);
		if(isShow == 1){
			$("#remindTitle").text('设置“隐藏”后，商品将不在APP端商城店铺中展示，停止销售');
			var hideSetting = $("#hideSetting").val();
			if(!hideSetting){
				$("#settingTitle").text('以后设置“隐藏”不再弹窗');
				$("#isShowDiv").show();
				$(".black_box").show();
			}else{
				$.ajax({
					url : "${ctx}/shopProduct/updateIsShow",
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {productId:id},
					timeout : 30000,
					success : function(data) {
						if (data.returnCode=="0000") {
							table.ajax.reload(null, false);
						} else {
							swal("操作失败，请稍后重试");
						}
					}
				});
			}
		}else{
			$("#remindTitle").text('设置“显示”后，商品将在APP端商城店铺中展示，进行销售');
			var showSetting = $("#showSetting").val();
			if(!showSetting){
				$("#settingTitle").text('以后设置“显示”不再弹窗');
				$("#isShowDiv").show();
				$(".black_box").show();
			}else{
				$.ajax({
					url : "${ctx}/shopProduct/updateIsShow",
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {productId:id},
					timeout : 30000,
					success : function(data) {
						if (data.returnCode=="0000") {
							table.ajax.reload(null, false);
						} else {
							swal("操作失败，请稍后重试");
						}
					}
				});
			}
		}
	}
	
var table;


function selectAll(){
    var check = $(".checkAll").prop("checked");
    $(".checkRow").prop("checked", check);
}

function appendCustomType(){
	if($(".checkRow:checked").length<=0){
		swal('请先选中需要追加分类的商品');
		return;
	}
	 var productIds="";
     $(".checkRow:checked").each(function(index){
    	 if(index!=$(".checkRow:checked").length-1){
	    	 productIds+=$(this).val()+","; 
    	 }else{
    		 productIds+=$(this).val();
    	 }
     });
     $.ajax({
	        url: "${ctx}/shopProduct/toEditCustomType?ids="+productIds+"&actionType=1", 
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

function updateCustomType(){
	if($(".checkRow:checked").length<=0){
		swal('请先选中需要更新分类的商品');
		return;
	}
	 var productIds="";
	 $(".checkRow:checked").each(function(index){
    	 if(index!=$(".checkRow:checked").length-1){
	    	 productIds+=$(this).val()+","; 
    	 }else{
    		 productIds+=$(this).val();
    	 }
     });
     $.ajax({
	        url: "${ctx}/shopProduct/toEditCustomType?ids="+productIds+"&actionType=2", 
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

function removeCustomType(){
	if($(".checkRow:checked").length<=0){
		swal('请先选中需要更新分类的商品');
		return;
	}
	 var productIds="";
	 $(".checkRow:checked").each(function(index){
    	 if(index!=$(".checkRow:checked").length-1){
	    	 productIds+=$(this).val()+","; 
    	 }else{
    		 productIds+=$(this).val();
    	 }
     });
     
     swal({
   	  title: "确定清除选中商品的所有分类吗",
   	  type: "warning",
   	  showCancelButton: true,
   	  confirmButtonText: "确定",
   	  cancelButtonText: "取消",
   	  closeOnConfirm: false
   	},
   	function(){
   		$.ajax({
   			url : "${ctx}/shopProduct/removeCustomType",
   			type : 'POST',
   			dataType : 'json',
   			cache : false,
   			async : false,
   			data : {ids:productIds},
   			timeout : 30000,
   			success : function(data) {
   				if (data.returnCode=="0000") {
   					swal.close();
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

$(document).ready(function () {
	$.metadata.setType("attr", "validate");
	dataFromValidate =$("#dataFrom").validate({});
	//解决select2模态框搜索问题
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	
	$("#brandId").select2({
		  language: "zh-CN",
		  placeholder: "--请选择--",
		  allowClear: true
		});
	
	
	
	$("#productType1-select").select2({
		  language: "zh-CN",
		  placeholder: "--请选择--",
		  allowClear: true
		});
	$("#productType2-select").select2({
		  language: "zh-CN",
		  placeholder: "--请选择--",
		  allowClear: true
		});
	$("#productType3-select").select2({
		  language: "zh-CN",
		  placeholder: "--请选择--",
		  allowClear: true
		});
	
	getProductType1List(1);
	
	
    $('.datePicker').datetimepicker(
    		{
    			minView: "month", //选择日期后，不会再跳转去选择时分秒
				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                autoclose:true //选择日期后自动关闭
    	    }
    );
    var isInit = true;
	var pageNumber;
    table = $('#datatable').dataTable({
        "ajax": function (data, callback, settings) {
        	pageNumber = $("#pageNumber").val();
            var param = $("#searchForm").serializeArray();
            
            param.push({"name":"pagesize","value":data.length});
            if (data.start > 0) {
                param.push({"name":"page","value":data.start/data.length+1});
            } else if(isInit && pageNumber && parseInt(pageNumber)>0){
            	data.start = (parseInt(pageNumber)-1)*data.length;
				param.push({"name": "pageNumber", "value": pageNumber});
			} else {
                param.push({"name":"page","value":1});
            }
            
            $.ajax({
                method: 'POST',
                url: '${ctx}/shopProduct/getProductList',
                data: param,
                dataType: 'json'
            }).done(function (result) {
                if (result.returnCode =='0000') {
                    var returnData = {};
                    returnData.recordsTotal = result.returnData.Total;
                    returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                    returnData.data = result.returnData.Rows;
                    callback(returnData);
                    $(".checkAll").prop("checked", false);

                    var seqNoBlur = false;
                    $("input[name='saveSort']").on('blur', function () {
                        if (seqNoBlur) {
                            return false;
                        }
                        var id = $(this).data("id");
                        var seqNo = $(this).val();
                        var reg = /^[1-9]\d*$/;

                        if (!reg.test(seqNo)) {
                            swal("请输入正整数");
                            seqNoBlur = false;
                            return false;
                        }

                        seqNoBlur = true;
                        $.ajax({
                            url: "${ctx}/shopProduct/saveSort",
                            type: 'POST',
                            dataType: 'json',
                            cache: false,
                            async: false,
                            data: {id: id, sort: seqNo},
                            timeout: 30000,
                            success: function (data) {
                                if (data.returnCode == "0000") {
                                    seqNoBlur = false;
                                } else {
                                    swal({
                                        title: data.returnMsg,
                                        type: "error",
                                        confirmButtonText: "确定"
                                    });
                                    seqNoBlur = false;
                                }

                            },
                            error: function () {
                                swal({
                                    title: "处理失败！",
                                    type: "error",
                                    timer: 1500,
                                    confirmButtonText: "确定"
                                });
                                seqNoBlur = false;
                            }
                        });
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
            {"data": "id","title":"图片/名称/商品ID","render": function (data, type, row, meta) {
            	var html = [];
            	html.push('<div class="is-check">');
            	html.push('<div class="width-33"><input type="checkbox" class="checkRow"  value="' + data + '" /></div>');
            	if(row.pic && row.pic.indexOf("http") >= 0){//网络图片
            		html.push('<div class="width-60"><img src='+row.pic+'></div>');
            	}else{
            		html.push('<div class="width-60"><img src="${ctx}/file_servelt'+row.pic+'"></div>');
            	}
            	html.push('<div class="width-226"><p class="h34">'+row.name+'</p><div><span style="float: left; margin: 0;">ID：'+row.code+'</span><a style="float: right;" href="https://m.xgbuy.cc/share_buy.html?id='+row.code+'" target="_blank">预览</a></div></div>');
            	html.push("<div>");
            	return html.join("");
            }},
            {"data": "id",width:"98","title":"品牌/货号","render": function (data, type, row, meta) {
            	var returnStr= "<div style='width:90px;word-wrap:break-word;'>"+row.productBrandName+"<br>"+row.artNo+"</div>";
                return returnStr;
            }},
			<c:if test="${sessionScope.mchtInfo.mchtType == '1'}">
				{"data": "id",width:"88","title":"结算价","render": function (data, type, row, meta) {
					var html = [];
					html.push(row.costPriceMin);
					if(row.costPriceMin!=row.costPriceMax){
						html.push("-");
						html.push(row.costPriceMax);
					};
					return html.join("");
				}},
			</c:if>
			<c:if test="${sessionScope.mchtInfo.mchtType == '2'}">
				{"data": "id",width:"88","title":"商城价","render": function (data, type, row, meta) {
					var html = [];
					html.push(row.mallPriceMin);
					if(row.mallPriceMin!=row.mallPriceMax){
						html.push("-");
						html.push(row.mallPriceMax);
					};
					return html.join("");
				}},
			</c:if>
            {"data": "id",width:"78","title":"SVIP折扣","render": function (data, type, row, meta) {
            	if(!row.svipDiscount){
            		return "/";
            	}else{
            		return row.svipDiscount*10;
            	}
            }},
            {"data": "id", "width":"134", "title":"所属分类","render": function (data, type, row, meta) {
            	return row.customTypes;
            }},
            {"data": "id",width:"108","title":"状态","render": function (data, type, row, meta) {
            	var html = [];
            	if(row.status == "0"){
            		html.push("下架<br>");
            	}else{
            		html.push("上架");
            	}
//             	if(row.isShow == "0"){
//             		html.push("<br>隐藏");
//             	}else{
//             		html.push("<br>显示");
//             	}
            	return html.join("");
            }},
            {"data": "sort","width":"50", "title":"商城排序", "render": function (data, type, row, meta) {
                    var sort = row.sort ? row.sort : "";
                    return '<input style="width:60px;" value="' + sort + '" data-id="' + row.id + '" name="saveSort" >';
                }
            },
            {"data": "id","width":"69","title":"操作","render": function (data, type, row, meta) {
            	var html = [];
            	if(row.status=='0'){
            		html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='editProduct("+row.id+")'>编辑</a>"+"<br>");	
            	}
            	
            	html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='editProductSku("+row.id+")'>修改SKU</a><br>");
            	html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='toEditCustomType("+row.id+")'>修改分类</a><br>");	
            	if(row.status=='1'&&row.activityStatusDesc=='0'){
           	   		html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='changeProductStatus("+row.id+",0)'>下架</a><br>");
            	}
            	if(row.canDelete){
            		html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='delProduct("+row.id+")' >删除</a>");	
            	}
            	
//             	else{
//            	   		if(row.auditStatus=='2'&&row.offReason=='3'){
//             	   		html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='changeProductStatus("+row.id+",1)'>上架</a>");
//             	   	}
//            	}
//             	if(row.isShow == "0"){
//             		html.push("<br><a class='table-opr-btn' href='javascript:void(0);' onclick='updateShowOrHide("+row.id+","+row.isShow+")'>显示</a>");
//             	}else{
//             		html.push("<br><a class='table-opr-btn' href='javascript:void(0);' onclick='updateShowOrHide("+row.id+","+row.isShow+")'>隐藏</a>");
//             	}
            	return  html.join("");
            }}
        ],
	      
	       "initComplete": function(settings, json) {
	           //这里在初始化完成后修改一下显示的页数
	           if(isInit&& parseInt(pageNumber)!=0){
	    		   isInit = false;
	    		   table.page(parseInt(pageNumber)-1).draw(false);
	    	   }
	       }
    }).api();

    $('#btn-query').on('click', function (event) {
        table.ajax.reload();
    });
    
    $("#searchKeywrod").keydown(function(e){
    	if(e.keyCode==13){
    		table.ajax.reload();
            return false;
    	}
    });
    
    $(".video_close").on('click',function(){
    	$("#isShowDiv").hide();
    	$(".black_box").hide();
    });
    
    $("#cancle").on('click',function(){
    	$("#isShowDiv").hide();
    	$(".black_box").hide();
    });
    var submitting;
    $("#commit").on('click',function(){
    	if(submitting){
    		return;
    	}
    	var checked = $("#setting").prop("checked");
    	var isShow = $("#isShow").val();
    	var productId = $("#productId").val();
    	var showSetting="";
    	var hideSetting="";
    	if(isShow == 1){//进行隐藏操作
    		if(checked){
	    		hideSetting = "1";
	    		$("#hideSetting").val(hideSetting);
    		}
    	}else if(isShow == 0){//进行显示操作
    		if(checked){
	    		showSetting = "1";
	    		$("#showSetting").val(showSetting);
    		}
    	}
    	submitting=true;
    	$.ajax({
			url : "${ctx}/shopProduct/updateIsShow",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {productId:productId,showSetting:showSetting,hideSetting:hideSetting},
			timeout : 30000,
			success : function(data) {
				if (data.returnCode=="0000") {
					submitting = false;
					$("#isShowDiv").hide();
			    	$(".black_box").hide();
					table.ajax.reload(null, false);
				} else {
					submitting = false;
					swal("操作失败，请稍后重试");
				}
			}
		});
    });
});


function delProduct(id){
	swal({
		  title: "确定要删除此商品?",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonText: "确定",
		  cancelButtonText: "取消",
		  closeOnConfirm: true
		},
		function(){
			delConfirm(id);
		});
}

function delConfirm(id){
	$.ajax({
		url : "${ctx}/product/delProduct?id="+id,
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {id:id},
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

}
</script>
</body>
</html>
