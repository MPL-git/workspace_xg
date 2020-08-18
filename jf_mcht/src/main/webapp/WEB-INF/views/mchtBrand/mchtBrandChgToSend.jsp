<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>立即寄件</title>
</head>

<body>
<!--查看品牌 -->
  <div class="modal-dialog" role="document">
	  <input  type="hidden" id="isManageSelf" value="${mchtInfo.isManageSelf}">
    <div class="modal-content">
    <input type="hidden" id="mchtBrandChgId" value="${id}">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">立即寄件</span>
      </div>
     <div class="modal-body" style="height: 350px;">
     	 <div>
     	 	请按《寄件内容》的要求，请将合同原件及所有资质（已盖贵公司公章）寄往：<br>
			收  件  人：招商<br>
			联系电话：4008088227<br>
			收件地址：厦门市思明区塔埔东路171号1104-A单元<br>
			寄件之后，请及时提交快递信息<br>
     	 </div>
     	 <div class="form-group" style="margin-top: 15px;">
			<label for="productBrand" class="col-md-2 control-label search-lable" style="width: 55px;margin-top:-8px;">快递：</label>
			<div class="col-md-2 search-condition" >
				 <select class="form-control" name="expressId" id="expressId">
					<option value="">请选择</option>
					<c:forEach var="express" items="${expressList}">
						<option value="${express.id}">${express.name}</option>
					</c:forEach>
					</select>
			</div>
			<label for="productBrand" class="col-md-2 control-label search-lable" style="width: 75px;margin-top:-8px;">快递单号：</label>
			<div class="col-md-2 search-condition" >
				 <input class="form-control" type="text"  id="expressNo" name="expressNo" style="width: 150px;">
			</div>
		<!-- 	<div class="col-md-3 text-center search-btn">
				<button type="button" class="btn btn-info" id="send">提交</button>
			</div> -->
			 <div>
            <br>
			<br>
			<br>
             <p style="line-height: 30px;" id="change">您的收件地址<br>
				收 &nbsp;件&nbsp;人：<span id ="contactName">${mchtContact.contactName}</span><br>
                                               联系电话：<span id ="mobile" >${mchtContact.mobile}</span><br>
                                               收件地址：<span id="address">${address}</span><br>
            </p>
            </div>
            <br>
            <div >
            	 <button style=" margin-left: 210px;"  class="btn btn-info" data-dismiss="modal">取消</button>
				 <button type="button" class="btn btn-info" id="send">提交</button>
                
			</div>
		 </div>
    </div>
    
    </div>
  </div>
  
<script type="text/javascript">
$(function(){
	var submitting;
	$("#send").on('click',function(){
		if(submitting){
			return false;
		}
		var mchtBrandChgId = $("#mchtBrandChgId").val();
		var expressId = $("#expressId").val();
		var expressNo = $.trim($("#expressNo").val());
		if(!expressId){
			swal("请选择快递");
			return false;
		}
		if(!expressNo){
			swal("快递单号不能为空");
			return false;
		}
		
		var address = $("#contactName").text();
		var mobile = $("#mobile").text();
		var contactName = $("#contactName").text();
		var isManageSelf = $("#isManageSelf").val();
		if(isManageSelf != 1) {
			if (address == null || address == '' || mobile == null || mobile == '' || contactName == null || contactName == '') {
				swal("请完善您的收件信息");
				return false;
			}
		}
		submitting = true;
		$.ajax({
		        method: 'POST',
		        url: '${ctx}/mcht/mchtBrandChg/send',
		        data: {"id":mchtBrandChgId,"expressId":expressId,"expressNo":expressNo},
		        dataType: 'json'
		    }).done(function (result) {
		        if (result.returnCode =='0000') {
		        	submitting = false;
		           	swal("操作成功");
		           	$("#viewModal").modal('hide');
		           	table.ajax.reload(null, false);
		        }else{
		        	swal(result.returnMsg);
		        	//swal("操作失败，请稍后重试");
		        	submitting = false;
		        }
		    });
	});
});
</script>
</body>
</html>
