<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>添加动态</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
<!-- Bootstrap -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.td-pictures li{
display: inline-block;
}
.td-pictures li img{
width: 100px;
height: 100px;
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

.form-group img{
	width: 100px;
	height: 100px;
}
</style>


</head>

<body>

      <div class="x_panel container-fluid py-tm">
        <div class="row content-title">
      <div class="modal-header">
       <c:if test="${shopDynamic == null}">
        <span class="modal-title" id="exampleModalLabel">添加动态</span>
        </c:if>
        <c:if test="${shopDynamic != null}">
        <span class="modal-title" id="exampleModalLabel">编辑动态</span>
        </c:if>
      </div>
		<div class="modal-body">

		<form id="dataFrom">
		<input type="hidden" value="${shopDynamic.id}" id="shopDynamicId">
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
				
				<tr>
						<td class="editfield-label-td">动态顶部封面<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<div style="color: #999999;">
				    		图片像素宽度750PX,高度不超过1500PX,支持格式：.jpg .png
				    		</div>
							<c:if test='${shopDynamic.topCover !=null && shopDynamic.topCover !=""}'>
								<div class="single_pic_picker">
									<input id="logoFile" onchange="loadImageFile(this)" type="file">
									<img src="${ctx}/file_servelt${shopDynamic.topCover}" style="width:60px;height:60px;">
									<input type="hidden"  id="logo" name="logo" value="${shopDynamic.topCover}">
								</div>
							</c:if>
							<c:if test='${shopDynamic.topCover == null||shopDynamic.topCover == ""}'>
								<div class="single_pic_picker">
									<input id="logoFile" onchange="loadImageFile(this)" type="file">
									<div>+</div>
									<input type="hidden" id="logo" name="logo" value="">
								</div>
				    		</c:if>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">动态内容<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<c:if test='${shopDynamic.content!=null && shopDynamic.content!=""}'>
						    	<textarea rows="5" class="txt-area" id="dynamicContent">${shopDynamic.content}</textarea>
						    </c:if>
							<c:if test='${shopDynamic.content==null||shopDynamic.content==""}'>
						    	<textarea rows="5" class="txt-area" id="dynamicContent" placeholder="请输入1~500个字的动态内容"></textarea>
						    </c:if>
						    
						</td>
						
					</tr>
						
					<tr>
						<td class="editfield-label-td">绑定商品</td>
						<td colspan="2" class="text-left">
							<div style="display: inline-block;padding-bottom: 10px;">
							<a href="javascript:;" class="btn btn-info" id="addBrandProductType" onclick="addProduct();">管理商品</a>
							</div>

							<table style="display:none" id="checkedList" class="checkedList" border="1" width="700px">
								<thead id="tableHead">
									<tr>
									<td width="400px">商品信息</td>
									<td width="60px">商城价</td>
									<td width="60px">活动价</td>
									<td width="60px">库存</td>
									<td width="60px">状态</td>
									<td width="60px">操作</td>
									</tr>
								</thead>
								<tbody id="tableBody">
									<c:if test="${productList!= null}">
										<c:forEach var="row" items="${productList}">
											<tr>
												<td>
													<div class="is-check">
														<div class="width-60"><img src="${ctx}/file_servelt${row.pic}"></div>
														<div class="width-226">
														<p class="h34">${row.name}</p>
														<div>
														<span style="float: left; margin: 0;">ID：${row.code}
														</span>
														<a style="float: right;" href="https://m.xgbuy.cc/share_buy.html?id=${row.id}" target="_blank">预览</a>
														</div>
														</div>
													</div>
												</td>
												<td class=" hiddenCol">${row.mallPriceMax}</td>
												<td>${row.salePriceMin}</td>
												<td>${row.stock}</td>
												<td>
													<c:if test="${row.saleType =='1'}">
														<!-- 品牌款<br> -->
														
														<c:if test="${row.status =='0'}">
														 	下架<br>
														</c:if>
														<c:if test="${row.status =='1'}">
														 	上架<br>
														</c:if>
													</c:if>
													<%-- <c:if test="${row.saleType =='2'}">
														单品款<br>
														
														<c:if test="${row.status =='0'}">
														 	下架<br>
														</c:if>
														<c:if test="${row.status =='1'}">
														 	上架<br>
														</c:if>
													</c:if> --%>
												</td>
												<td>
													<a href="javascript:;" id="checkMain${row.id}" name="opName" class="check${row.id}" onclick="delId(${row.id})">删除</a>
													
												</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>

						</td>
					</tr>	
					
				</tbody>
			</table>
		</div>
		</form>
		
	  <div class="modal-footer">
		<c:if test="${empty mchtProductBrand.id || mchtProductBrand.zsAuditStatus == 0 || mchtProductBrand.zsAuditStatus == 4 || mchtProductBrand.auditStatus == 4}">
			<button class="btn btn-info" onclick="commitAudit();">提交</button>
		</c:if>	
      </div>
		
			</div>
	</div>
  </div>

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>
	<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/utils.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/imageUpload.js" type="text/javascript"></script>		
<script type="text/javascript">
$("#goback").on('click',function(){
	getContent("${ctx}/shopDynamic/shopDynamicInfoIndex");
})

var arrayId = new Array(); //创建一个ID数组

$(document).ready(function () {
	
	var productIds="${productIds}"
	//可以用字符或字符串分割
	 if(productIds!="" && productIds!=null){
	arr=productIds.split(',');
	for(var i=0;i<arr.length;i++){
		arrayId.push([arr[i]]);	
	}
	$("#checkedList").attr("style","display:block;");//显示div
	 };	
});

function contains(arr, obj) {
    //while
    var i = arr.length;
    while(i--) {
        if(arr[i] == obj) {
            return i;
        }
    }
    return -1;
}

function delId(id){
	var ids="checkMain"+id;
	var state = $("#"+ids).text();
	var length = arrayId.length;
	var arrIndex = contains(arrayId,id);
	$("#idsum").html(arrayId.length);
	if(state == "删除" && arrIndex != -1){
		$($("#"+ids).parent().parent()).empty();
		$("."+ids).html("添加");
		arrayId.splice(arrIndex,1);
		$("#idsum").html(arrayId.length);
		if(arrayId.length == 0){
			$("#checkedList").attr("style","display:none;");//隐藏div
		}
	}
	
}

function saveId(id){
	var ids="check"+id;
	var state = $("#"+ids).text();
	var length = arrayId.length;
	var arrIndex = contains(arrayId,id);
	$("#idsum").html(arrayId.length);
	
	if(state == "添加" && length < 9){	
		if(arrIndex != -1){
			swal("不能重复添加!");
			return;
		}
		$("."+ids).html("已添加");
		var $tr = $("#"+ids).parent().parent().clone();
		$tr.find("a[name='opName']").parent().each(function(){
			$(this).html("<a href='javascript:;' id='checkMain"+id+"' name='opName' class='check"+id+"' onclick='delId("+id+")'>删除</a>");
		});		
		$("#checkedList").append($tr);
		arrayId.push([id]);			
		$("#idsum").html(arrayId.length);
		$("#check"+id).parent().html("已添加");
		$("#checkedList").attr("style","display:block;");//显示div
		return;
	}
	if(state == "添加" && length >= 9){		
		swal("最多只能绑定9个商品！");
		return;
	}

	if(state == "已添加" && arrIndex != -1){
//		$("#checkedList").remove($("#"+ids).parent().parent());
		/* $($("#"+ids).parent().parent()).empty();
		$("."+ids).html("添加");
		arrayId.splice(arrIndex,1); */
		return;
	}
	
}

function addProduct(){
	
	var ids;
	var trt = $("#tableBody tr").val();
	if(trt != null){
	ids =  arrayId.toString();
	}
	$.ajax({
	    url: "${ctx}/shopDynamic/shopDynamicChoose", 
	    data : {ids:ids},
	    type: "GET", 
	    success: function(data){
//	    	alert(data);
	        $("#viewModal").html(data);
	        $("#viewModal").modal();
	    },
	    error:function(){
	    	swal('页面不存在');
	    }
	}); 
}


var table1;



var dataFromValidate;
var imageUploader;
$(function(){
	
	$.metadata.setType("attr", "validate");

	dataFromValidate =$("#dataFrom").validate({
        highlight : function(element) {  
        	$(element).addClass('error');
            $(element).closest('tr').find("td").first().addClass('has-error');  
        },
        success : function(label) {  
            label.closest('tr').find("td").first().removeClass('has-error');  
        }
	});

   
  
    	
    });
    
    
    
    $(".video_close").on('click',function(){
    	$("#brandProductTypeDiv").hide();
    	$("#viewAptitudeDiv").hide();
    	$("#blackBox").hide();
    });
    
   
 
    
    

function delMchtBrandProductType(_this){
	$(_this).closest("tr").remove();
}

function delMchtBrandAptitude(_this){
	$(_this).closest("table").remove();
}

function getProductTypeList(selectId,parentId){
	var sel = $("#"+selectId);
	if(parentId==""){
		sel.empty();
		return;
	}
	$.ajax({
		url : "${ctx}/mcht/getProductTypes",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {parentId:parentId},
		timeout : 30000,
		success : function(data) {
			if (data.returnCode=="0000") {
				sel.empty();
				$.each(data.returnData, function(index, item) {
					sel.append("<option value=\"" + item.id + "\">" + item.name + "</option>");
				});
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
				  title: "请求失败！",
				  type: "error",
				  confirmButtonText: "确定"
				});
		}
	});
	
}

function commitAudit(){
	if(dataFromValidate.form()){
		var $logo=$("#logo").parent().children("img");
		if($logo.length <= 0){
				swal({
					  title: '请上传动态封面图',
					  type: "error",
					  confirmButtonText: "确定"
					});
			return;
		}
		if($logo.length > 0 && $logo.attr("src") != "" && $logo.attr("src").indexOf("data:image") == 0){//有修改
			uploadImage("logoFile",$("#logo"));
		}
		var text = $("#dynamicContent").val().trim();
		if(text == "" || text == "请输入1~500个字的动态内容"){
			swal('动态内容不能为空');
			return;
		}
		if(text.length > 500){
			swal('动态内容限制为1~500个字');
			return;
		}
		show_waitMe();
		var dynamicContent = $("#dynamicContent").val();
		var pic =  $("#logo").val();
		var ids;
		if(arrayId.length != 0){
		ids =  arrayId.join(',');
		}
		var shopDynamicId = $("#shopDynamicId").val(); 
			$.ajax({
				url : "${ctx}/shopDynamic/shopDynamicCommit",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {pic:pic,dynamicContent:dynamicContent,ids:ids,id:shopDynamicId},
				timeout : 30000,
				success : function(data) {
					hide_waitMe();
					if (data.returnCode=="0000") {
						$("#viewModal").modal('hide');
						swal({
							  title: "提交成功!",
							  type: "success",
							  confirmButtonText: "确定"
							  
							});
//						table.ajax.reload();
						getContent("${ctx}/shopDynamic/shopDynamicInfoIndex");
					} else {
						swal({
							  title: data.returnMsg,
							  type: "error",
							  confirmButtonText: "确定"
							});
					}
				},
				error : function() {
					hide_waitMe();
					swal({
						  title: "提交失败！",
						  type: "error",
						  timer: 1500,
						  confirmButtonText: "确定"
						});
				}
			});


	}
}


function loadImageFile(obj) {
	if (obj.files.length === 0) {
		return;
	}
	var oFile = obj.files[0];
// 	var rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
	var rFilter = /^(?:image\/jpeg|image\/png)$/i;
	if (!rFilter.test(oFile.type)) {
		swal("图片格式不正确！");
		return;
	}
	var reader = new FileReader();  
    reader.onload = function(e) { 
    	var image = new Image();
    	image.onload = function() {
	    	if(this.width != 750 && this.height <= 1500) {
	    		swal("图片像素宽度750PX,高度不超过1500PX");
	    		return;
	    	}else {
	    		if($(obj).parent().children("img").length<=0){
	    	    	$('<img>').appendTo( $(obj).parent() );;
	    	    }
    			$(obj).parent().children("img").attr("src",e.target.result);
    			$(obj).parent().children("div").remove();
	    	}
        };
        image.src = e.target.result;
    }
    reader.readAsDataURL(oFile); 
}

//上传图片
function uploadImage(fileElementId,$valueFiled) {
	var oFile = document.getElementById(fileElementId).files[0];
	var formData = new FormData();
	formData.append("file",oFile);
	$.ajax({ 
		url : "${ctx}/common/fileUpload", 
		type : 'POST', 
		data : formData, 
		async: false,
		// 告诉jQuery不要去处理发送的数据
		processData : false, 
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,
		beforeSend:function(){
			console.log("图片片上传中，请稍候");
		},
		success : function(data) {
            if (data.returnCode=="0000") {
                $valueFiled.val(data.returnData);
            } else {
                swal({
                    title: "图片上传失败！",
                    type: "error",
                    confirmButtonText: "确定"
                });
            }
		}, 
		error : function(responseStr) { 
			swal("图片上传失败");
		} 
		});
}
function toViewAptitude(){
	$("#viewAptitudeDiv").show();
	$("#blackBox").show();
}
</script>
</body>
</html>
