<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:10px;padding-bottom:10px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
</style>
<script type="text/javascript">
var viewerPictures;
$(function(){
		$("#register").bind('click',function(){
			var refundOrderId = $.trim($("#refundOrderId").val());
			var status = "3";//已登记
			var refundCode = $("#refundCode").val();
			if(!refundCode){
				commUtil.alertError("退款编号不能为空");
				return false;
			}
			var refundRegisterDate = $("#refundRegisterDate").val();
			if(!refundRegisterDate){
				commUtil.alertError("退款登记日期不能为空");
				return false;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/refundOrder/register.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"id":refundOrderId,status:status,refundRegisterDate:refundRegisterDate,refundCode:refundCode},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("登记成功");
						setTimeout(function(){
							parent.location.reload();
							frameElement.dialog.close();
						},1000);
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		});
		
		/* $("#refundAgain").bind('click',function(){
			var id = $("#refundOrderId").val();
			parent.$.ligerDialog.open({
		 		height: $(window).height()*1.50,
				width: $(window).width()*1.50,
				title: "重新退款操作",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/refundOrder/toRefund.shtml?id=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}); */
});


</script>
<html>
<body>
<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/refundOrder/register.shtml">
			<input type="hidden" id="refundOrderId" value="${id}">
			<table class="gridtable">
           	<tr>
               <td class="title">当前状态</td>
               <td colspan="2" align="left" class="l-table-edit-td">
	               <span>已退</span>
	               <!-- <span style="padding-left: 20px;"><input id="refundAgain" type="button" style="width: 80px;" class="l-button l-button-submit" value="失败，重退"/></span> -->
               </td>
			</tr> 
			<c:if test="${batch eq 0}">
	        <tr>
               <td class="title">退款编号</td>
               <td colspan="2" align="left" class="l-table-edit-td"><input type="text" id="refundCode" name="refundCode" value="${refundOrder.refundCode}"></td>
	        </tr>
	        </c:if>
	        <tr>
               <td class="title">退款登记日期</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="refundRegisterDate" name="refundRegisterDate"/>
	               	<script type="text/javascript">
						$(function(){
							$("#refundRegisterDate").ligerDateEditor({
								showTime : false,
								labelWidth : 150,
								width:150,
								labelAlign : 'left'
							});
						});
					</script>
				</td>
	        </tr>
	        <tr>
               <td class="title">操作</td>
               <td colspan="2" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="register" type="button" style="float:left;" class="l-button l-button-submit" value="登记"/>
					</div>
				</td>
	        </tr>
	        </table>
</form>	        
</body>
</html>
