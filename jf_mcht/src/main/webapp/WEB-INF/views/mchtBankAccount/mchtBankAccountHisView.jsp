<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>



<title>查看信息</title>


<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>	



<style type="text/css">
td img{
width: 60px;
height: 40px;
}
</style>

<script type="text/javascript">
var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures1'), {hide:function(){
		$("#viewer-pictures1").hide();
	}});
});

function viewerPic(imgPath){
	
	$("#viewer-pictures1").empty();
	viewerPictures.destroy();
		$("#viewer-pictures1").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
	viewerPictures=new Viewer(document.getElementById('viewer-pictures1'), {hide:function(){
		$("#viewer-pictures1").hide();
	}});
	viewerPictures.show();
	
}
</script>

</head>

<body>

  <div class="modal-dialog" role="document" style="width:700px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">申请更新税票信息</span>
      </div>
			<div class="modal-body">
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">账户类型</td>
						<td colspan="2" class="text-left">
						<span>${mchtBankAccountHis.accTypeDesc }</span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">账户名称</td>
						<td colspan="2" class="text-left">
						<span>${mchtBankAccountHis.accName }</span>
						</td>
					</tr>
										<tr>
						<td class="editfield-label-td">开户银行</td>
						<td colspan="2" class="text-left">
						<span>${mchtBankAccountHis.bankName }</span>
						</td>
					</tr>
										<tr>
						<td class="editfield-label-td">账户账号</td>
						<td colspan="2" class="text-left">
						<span>${mchtBankAccountHis.accNumber }</span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">开户支行名称</td>
						<td colspan="2" class="text-left">
						<span>${mchtBankAccountHis.depositBank }</span>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
			</div>
	</div>
  </div>
  	<ul  class="clearfix td-ul" id="viewer-pictures1" style="display: none;">
	
	</ul>
</body>
</html>
