<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
	 <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<style type="text/css">
 	.radioClass{margin: 0 10px 0 10px;}
 	.table-edit-activity-time div,
    .table-edit-activity-time span {
		display: inline-block;
		vertical-align: middle;
    }
</style>
<script type="text/javascript">
	$(function(){
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format: "yyyy-MM-dd hh:mm:ss",
			labelAlign : 'left',
			width : 160
		});
		
		var preferentialType = '${activityArea.preferentialType }';
		var status = '${activityArea.status }';
		var couponList = '';
		<c:if test="${not empty couponList}">
			couponList = ${couponList }
		</c:if>
		preferentialCoupon(couponList, preferentialType);
		if(preferentialType == '1') { //优惠券
			$(".couponClass").show();
		}
		
		if(status == '1' && preferentialType != '0') {
			$("[name='preferentialType']").attr("disabled", "disabled");
			$(".couponClass").find("input").attr("disabled", "disabled");
		}
		
	});
	
	//优惠卷
	function preferentialCoupon(couponList, preferentialType) {
		if(couponList != '' && preferentialType == 1) {
			for(var i=0; i<couponList.length; i++) {
				var html = '<div class="couponDiv'+i+' coupon_list"><input type="hidden" id="couponId" name="couponId" value="'+couponList[i].id+'"/>'
						+ '面额&nbsp;<input type="text" id="money" name="money" style="width: 50px;" value="'+couponList[i].money+'">元&nbsp; &nbsp;'
						+ '使用条件订单满&nbsp;<input type="text" id="minimum" name="minimum" style="width: 50px;" value="'+couponList[i].minimum+'"/>元&nbsp; &nbsp;'
						+ '发行量&nbsp;<input type="text" id="grantQuantity" name="grantQuantity" style="width: 50px;" value="'+couponList[i].grantQuantity+'"/>张&nbsp; &nbsp;'
						+ '&nbsp;<input type="button" id="del'+i+'" style="color: #FFFFFF;background-color: #D9534F;border: none;width: 25px;height: 20px;border-radius: 3px;" onclick="couponDel('+i+');"  value="-">'
						+ '</div><br class="couponBr'+i+'" >';
				$(".couponList").append(html);
				if(i != 0) {
					var k = i-1;
					$("#del"+k).hide();
				}else {
					$("#del"+i).hide();
				}
				if(i == couponList.length-1 && couponList.length < 3) {
					$(".couponList").append('<input type="button" id="addCoupon" style="background-color: #dddddd;padding: 3px;" onclick="couponAdd('+i+');"  value="再加1条">');
				}
			}
		}else {	
			var i = 0;
			var html = '<div class="couponDiv'+i+' coupon_list"><input type="hidden" id="couponId" name="couponId" value=""/>'
					+ '面额&nbsp;<input type="text" id="money" name="money" style="width: 50px;" value="">元&nbsp; &nbsp;'
					+ '使用条件订单满&nbsp;<input type="text" id="minimum" name="minimum" style="width: 50px;" value=""/>元&nbsp; &nbsp;'
					+ '发行量&nbsp;<input type="text" id="grantQuantity" name="grantQuantity" style="width: 50px;" value=""/>张&nbsp;'
					+ '</div><br class="couponBr'+i+'" >'
					+ '<input type="button" id="addCoupon" style="background-color: #dddddd;padding: 3px;" onclick="couponAdd('+i+');"  value="再加1条">';
			$(".couponList").append(html);
			$("#recEach").hide();
		}
	}
	
	//添加一个div
	function couponAdd(i){
		i = i+1;
		var addCouponHtml = '<input type="button" id="addCoupon" style="background-color: #dddddd;padding: 3px;" onclick="couponAdd('+i+');"  value="再加1条">';
		if($(".couponList").find("div").length >= 2){
			addCouponHtml = '';
		}
		var html = '<div class="couponDiv'+i+' coupon_list"><input type="hidden" id="couponId" name="couponId" value=""/>'
				+ '面额&nbsp;<input type="text" id="money" name="money" style="width: 50px;" value="">元&nbsp; &nbsp;'
				+ '使用条件订单满&nbsp;<input type="text" id="minimum" name="minimum" style="width: 50px;" value=""/>元&nbsp; &nbsp;'
				+ '发行量&nbsp;<input type="text" id="grantQuantity" name="grantQuantity" style="width: 50px;" value=""/>张&nbsp;'
				+ '&nbsp;<input type="button" id="del'+i+'" style="color: #FFFFFF;background-color: #D9534F;border: none;width: 25px;height: 20px;border-radius: 3px;" onclick="couponDel('+i+');"  value="-">'
				+ '</div><br class="couponBr'+i+'" >';
		$(".couponList").find("#addCoupon").remove();
		$(".couponList").append(html+addCouponHtml);
		var k = i-1;
		$("#del"+k).hide();
	}
	
	//删除一个div
	function couponDel(y){
		$(".couponDiv"+y).remove();
		$(".couponBr"+y).remove();
		var j = y-1;
		if(j != 0){
			$("#del"+j).show();
		}
		var addCouponHtml = '<input type="button" id="addCoupon" style="background-color: #dddddd;padding: 3px;" onclick="couponAdd('+j+');"  value="再加1条">';
		$(".couponList").find("#addCoupon").remove();
		$(".couponList").append(addCouponHtml);
	}
	
	//优惠方式
	function promotion(p){
		if(p==0){ //无
			$(".couponClass").hide();
		}else if(p==1){ //优惠券
			$(".couponClass").show();
		}
	}
	
	//提交
	function createActivityArea(){
		if($("#activityBeginTime").val() == ''){
			commUtil.alertError("活动开始时间不能为空！");
			return;
		}
		if($("#activityEndTime").val() == ''){
			commUtil.alertError("活动结束时间不能为空！");
			return;
		}
		if(new Date($("#activityBeginTime").val()) >= new Date($("#activityEndTime").val())){
			commUtil.alertError("活动开始时间必须小于活动结束时间！");
			return;
		}
		if('${activityArea.enrollEndTime }' != '' && new Date($("#activityEndTime").val()) <= new Date('${enrollEndTime }')){
			commUtil.alertError("活动结束时间必须大于报名结束时间！");
			return;
		}
		if(new Date($("#activityEndTime").val()).getTime() <= new Date().getTime()){
			commUtil.alertError("活动结束时间必须大于现在时间！");
			return;
		}
		//专场特惠
		var preferentialType = $('input:radio[name=preferentialType]:checked').val();
		if(preferentialType==null||preferentialType==""||preferentialType==undefined){
			commUtil.alertError("请选择优惠方式！");
			return;
		}
		//优惠券
		if(preferentialType==1) {
			var couponList=[];
			if($(".couponList").html().indexOf("div")>-1){
				var flag = true;
				$(".coupon_list").each(function(){
					var couponId = $(this).find('input').eq(0).val(); 
					var money = $(this).find('input').eq(1).val(); 
					var minimum = $(this).find('input').eq(2).val(); 
					var grantQuantity = $(this).find('input').eq(3).val(); 
					if(money == ''){
						flag=false;
						commUtil.alertError("面额不能为空！");
						return;
					}				
					if(minimum == ''){
						flag=false;
						commUtil.alertError("使用条件不能为空！");
						return;
					}
					if(eval(money)>=eval(minimum)){
						flag=false;
						commUtil.alertError("面额必须小于使用条件！");
						return;
					}
					if(grantQuantity == ''){
						flag=false;
						commUtil.alertError("发行量不能为空！");
						return;
					}	
					var object = new Object();
					object.recType = 3; //3:游离码兑换
					if(couponId!=null){
		        		object.couponId = couponId;
					}else{
						object.couponId = 0;
					}
	        		object.money = money;
	        		object.minimum = minimum;
	        		object.grantQuantity = grantQuantity;
	        		object.needIntegral = 0;
	        		couponList.push(object);
				});
				if(!flag){
	    			return false;
	    		}
				var jsonCoupon = JSON.stringify(couponList);
				$("#jsonCoupon").val(jsonCoupon);
			}	
		}
		$("#activityAreaForm").submit();
	}
	
</script>

</head>
	<body style="margin: 10px;">
	<form name="form1" class="form1" method="post" id="activityAreaForm" action="${pageContext.request.contextPath}/activityAreaNew/updateActivityAreaPreferential.shtml">
		
		<input type="hidden" id="activityAreaId" name="activityAreaId" value="${activityArea.id}"/>
		<!-- 	优惠券 -->
		<input type="hidden" id="jsonCoupon" name="jsonCoupon">
		
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">会场名称</td>
				<td align="left" class="l-table-edit-td" >${activityArea.name }</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">活动时间</td>
				<td align="left" class="l-table-edit-td table-edit-activity-time">
					<div><input type="text" class="dateEditor" id="activityBeginTime" name="activityBeginTime" value="<fmt:formatDate value="${activityArea.activityBeginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
					<span style="margin: 0px 10px;">到</span>
					<div><input type="text" class="dateEditor" id="activityEndTime" name="activityEndTime" value="<fmt:formatDate value="${activityArea.activityEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">优惠方式</td>
				<td align="left" class="l-table-edit-td" >
					<span class="radioClass">
						<input type="radio" value="0" name="preferentialType" onclick="promotion(0);" <c:if test="${activityArea.preferentialType == '0'}">checked</c:if> >无
					</span>
					<span class="radioClass">
						<input type="radio" value="1" name="preferentialType" onclick="promotion(1);" <c:if test="${activityArea.preferentialType == '1'}">checked</c:if> >优惠券
					</span>
				</td>
           	</tr>
           	<tr class="couponClass" <c:if test="${activityArea.preferentialType ne 1}">style="display: none"</c:if>>
				<td class="title">优惠劵：</td>
				<td>
					<div class="couponC">
						<table>
							<tr>
								<td style="border:none">
									<div class="couponList">
									
									</div>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="button" onclick="createActivityArea();" class="l-button" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>