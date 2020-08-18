<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>编辑售后模版</title>
	<!-- Bootstrap -->
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
      <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<!--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/fileuploader-style.css" /> -->


</head>

<body>

  <div class="modal-dialog" role="document" style="width:700px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">编辑售后模版</span>
      </div>
			<div class="modal-body">
		<form id="dataFrom1">
		<input type="hidden" value="${productAfterTemplet.id}" name="id">
		<input type="hidden" value="${productAfterTemplet.mchtId}" name="mchtId">
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">售后名称</td>
						<td colspan="2" class="text-left">
						<input type="text" id="name" name="name" value="${productAfterTemplet.name}" validate="{required:true,maxlength:14}">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">负责品牌</td>
						<td colspan="2" class="text-left">
						   <select class="form-control" name="brandDef" id="brandDef">
						   <option value="">--请选择--</option>
						   <c:forEach var="productBrand" items="${productBrandList}">
						   <option value="${productBrand.productBrandId}" <c:if test="${brandId==productBrand.productBrandId}">selected="selected"</c:if> >${productBrand.productBrandName}</option>
						   </c:forEach>
                          </select>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">收货人</td>
						<td colspan="2" class="text-left">
						<input type="text" id="recipient" name="recipient" value="${productAfterTemplet.recipient }" validate="{required:true}">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">电话</td>
						<td colspan="2" class="text-left">
						<input type="text" id="phone" value="${productAfterTemplet.phone }" name="phone" validate="{required:true}">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">收货地址</td>
						<td colspan="2" class="text-left">
						<select style="width:100px;" class="province-select" onchange="onchangeProvince();" name="province" id="province" validate="{required:true}">
                         </select>
                         <select style="width:100px;" class="city-select" onchange="onchangeCity();" name="city" id="city" validate="{required:true}">
                         </select>
                         <select style="width:100px;" class="county-select"  name="county" id="county" validate="{required:true}">
                         </select>
                         <br>
                         <br>
						<input style="width:300px;" type="text" id="address" name="address" value="${productAfterTemplet.address}" validate="{required:true,maxlength:50}">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">注意事项</td>
						<td colspan="2" class="text-left">
						<textarea id="remarks" name="remarks" rows="6" cols="40">${productAfterTemplet.remarks}</textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		</form>
		
	<div class="modal-footer">
	
			<button class="btn btn-info" onclick="commitSaveTmplet();">保存</button>
			<button class="btn btn-info" onclick="coloseViewModel();">取消</button>
      </div>
		
			</div>
	</div>
  </div>




	
	<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js"
	type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	
	
<script type="text/javascript">







function getProvinceList()
{   
	var path = '${pageContext.request.contextPath}/area/getAreaList.shtml';
	jQuery.ajax( {
  		url : path,
  		type : 'POST',
  		dataType : 'json',
  		cache : false,
  		async : false,
  		data : { parentAreaId:0},
  		timeout : 30000,
  		success : function(json) 
  		{  var sel = $(".province-select");
				sel.empty();
				sel.append("<option value=\"" + "\">--省份--</option>");
				
				$.each(json, function(index, item) {
					sel.append("<option value=\"" + item.areaId + "\">" + item.areaName + "</option>");
				});
  	      
  		},
		error : function() 
		{
			swal('操作超时，请稍后再试！');
		}
  	}); 
}
function getCityList(parentAreaId)
{
	if(typeof(parentAreaId)!="undefined"){
		var path = '${pageContext.request.contextPath}/area/getAreaList.shtml';
		jQuery.ajax( {
	  		url : path,
	  		type : 'POST',
	  		dataType : 'json',
	  		cache : false,
	  		async : false,
	  		data : { parentAreaId : parentAreaId},
	  		timeout : 30000,
	  		success : function(json) 
	  		{  var sel = $(".city-select");
					sel.empty();
					sel.append("<option value=\"" + "\">--地级市--</option>");
					
					$.each(json, function(index, item) {
						sel.append("<option value=\"" + item.areaId + "\">" + item.areaName + "</option>");
					});
	  	      
	  		},
			error : function() 
			{
				swal('操作超时，请稍后再试！');
			}
	  	});
	}
	
 
}
function getCountyList(parentAreaId)
{
	if(typeof(parentAreaId)!="undefined"){
	var path = '${pageContext.request.contextPath}/area/getAreaList.shtml';
	jQuery.ajax( {
  		url : path,
  		type : 'POST',
  		dataType : 'json',
  		cache : false,
  		async : false,
  		data : { parentAreaId : parentAreaId},
  		timeout : 30000,
  		success : function(json) 
  		{  var sel = $(".county-select");
				sel.empty();
				sel.append("<option value=\"" + "\">--县级--</option>");
				
				$.each(json, function(index, item) {
					sel.append("<option value=\"" + item.areaId + "\">" + item.areaName + "</option>");
				});
  	      
  		},
		error : function() 
		{
			swal('操作超时，请稍后再试！');
		}
  	}); 
	}
}
function  onchangeProvince()
{
	  
	   $(".city-select").empty();
	   $(".county-select").empty();
	   getCityList($(".province-select").val()); 
}
function  onchangeCity()
{ 
	   $(".county-select").empty();  
	   getCountyList($(".city-select").val()); 
}











var dataFromValidate1;
$(function(){
	
	$("#brandDef").select2({
		  language: "zh-CN",
		  placeholder: "--请选择--",
		  allowClear: true
		});
	
	getProvinceList();
	getCityList(${productAfterTemplet.province});
	getCountyList(${productAfterTemplet.city});  
	
	$(".province-select").val(${productAfterTemplet.province});
	$(".city-select").val(${productAfterTemplet.city});
	$(".county-select").val(${productAfterTemplet.county});
	
	
	
	$.metadata.setType("attr", "validate");

	dataFromValidate1 =$("#dataFrom1").validate({
        highlight : function(element) {  
        	$(element).addClass('error');
            $(element).closest('tr').find("td").first().addClass('has-error');  
        },
        success : function(label) {  
            label.closest('tr').find("td").first().removeClass('has-error');  
        }
	});
});



function coloseViewModel(){
	$("#viewModal1").modal('hide');
}

function commitSaveTmplet(){

	if(dataFromValidate1.form()){
		var dataJson = $("#dataFrom1").serializeArray();
		$.ajax({
			url : "${ctx}/productAfterTemplet/productAfterTempletSave",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : dataJson,
			timeout : 30000,
			success : function(data) {
				if (data.returnCode=="0000") {
					$("#viewModal1").modal('hide');
					getProductAfterTempleList("${brandId}");
					$("#csTempletId").val(data.returnData.id);
					var selectElemt=$("#csTempletId option:selected");
					var csTempletinfo;
					if($(selectElemt).val()!=""){
							csTempletinfo="收货人："+$(selectElemt).attr("recipient")+"&nbsp&nbsp&nbsp&nbsp"+"电话："+$(selectElemt).attr("tel")+"<br>"+"地址："+$(selectElemt).attr("address");
						}else{
							csTempletinfo="";
						}
						$("#csTempletInfoContainer").html(csTempletinfo);
					 
					 
					swal({
						  title: "保存成功!",
						  type: "success",
						  confirmButtonText: "确定"
						  
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
					  title: "提交失败！",
					  type: "error",
					  timer: 1500,
					  confirmButtonText: "确定"
					});
			}
		});
	}
	
	
}

</script>
</body>
</html>
