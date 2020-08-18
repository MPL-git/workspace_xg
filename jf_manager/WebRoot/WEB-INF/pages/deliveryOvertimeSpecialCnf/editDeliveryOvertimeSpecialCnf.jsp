<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
	
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<style type="text/css">
 	.radioClass {
		margin-right: 20px;
	}
 	.table-edit-activity-time div,
    .table-edit-activity-time span {
		display: inline-block;
		vertical-align: middle;
    }
    .activity-time {
    	margin-left: 50px;
    }
    .activity-hint {
    	color: #6B6B6B;
    }
</style>
<script type="text/javascript">
	var submitFlag = true;
	$(function(){
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format: "yyyy-MM-dd hh:mm:ss",
			labelAlign : 'left',
			width : 183
		});

		<c:if test="${empty deliveryOvertimeSpecialCnf}" >
			updateTimeType('0');
		</c:if>
		<c:if test="${not empty deliveryOvertimeSpecialCnf}">
			updateTimeType('${deliveryOvertimeSpecialCnf.timeType}');
		</c:if>

		<c:if test="${empty deliveryOvertimeSpecialCnfAreaList}" >
			editArea('', '', '', '');
		</c:if>
		<c:if test="${not empty deliveryOvertimeSpecialCnfAreaList}">
			var obj = ${deliveryOvertimeSpecialCnfAreaList};
			for(var i=0;i<obj.length;i++){
				editArea(obj[i].id, obj[i].province, obj[i].city, obj[i].county);
			}
			$(".area-list").not(".area-list:last").find(".province").attr("disabled", "disabled");
			$(".area-list").not(".area-list:last").find(".city").attr("disabled", "disabled");
			$(".area-list").not(".area-list:last").find(".county").attr("disabled", "disabled");
		</c:if>

		areaEditBut();

		$.metadata.setType("attr", "validate");
		$("#form1").validate({
	        errorPlacement: function (lable, element) {
	        	var elementType=$(element).attr("type");
	        	if('radio'==elementType) {
	        		var radioName=$(element).attr("name");
	        		$("input[type=radio][name="+radioName+"]:last").parent("span").ligerTip({
						content : lable.html(),width: 100
					});
	        	}else {
	        		$(element).ligerTip({
						content : lable.html(),width: 100
					});
	        	}
	        	$(".l-verify-tip-corner").css("z-index", "1001");
	        	$(".l-verify-tip-content").css("z-index", "1000");
	        },
	        success: function (lable, element) {
	            lable.ligerHideTip();
	            lable.remove();
	        },
	        submitHandler: function(form) {
				var beginPayDate = $("#beginPayDate").val();
				var endPayDate = $("#endPayDate").val();
				var timeType = $("#timeType").val();
				var overtime = $("#overtime").val();
				var deliveryDate = $("#deliveryDate").val();
				if(!beginPayDate){
					commUtil.alertError("请选择开始时间！");
					return false;
				}
				if(!endPayDate){
					commUtil.alertError("请选择结束时间！");
					return false;
				}
				if(new Date(beginPayDate) >= new Date(endPayDate)) {
					commUtil.alertError("开始时间必须小于结束时间！");
					return false;
				}
				if(timeType == '0') {
					if(!overtime){
						commUtil.alertError("请输入发货承诺时间！");
						return false;
					}else if(!testNumber(overtime)) {
						commUtil.alertError("发货承诺时间只能是正整数！");
						return false;
					}
				}else if(timeType == '1') {
					if(!deliveryDate){
						commUtil.alertError("请选择发货承诺时间！");
						return false;
					}else if(new Date(endPayDate) >= new Date(deliveryDate)) {
						commUtil.alertError("发货承诺时间必须大于结束时间！");
						return false;
					}
				}
				var remarks = "";
				var areaId = $(".area-list:last").find(".province").val();
				var areaFlag = "1";
				if($(".area-list:last").find(".city").val() != "" && $(".area-list:last").find(".county").val() == "") {
					areaId = $(".area-list:last").find(".city").val();
					areaFlag = "2";
				}else if($(".area-list:last").find(".county").val() != "") {
					areaId = $(".area-list:last").find(".county").val()
					areaFlag = "3";
				}
				$(".area-list").not(".area-list:last").each(function(index, element){
					var deliveryOvertimeSpecialCnfAreaId = $(element).find(".deliveryOvertimeSpecialCnfAreaId").val();
					var province = $(element).find(".province").val();
					var provinceText = $(element).find(".province").find("option:selected").text();
					var city = $(element).find(".city").val();
					var cityText = $(element).find(".city").find("option:selected").text();
					var county = $(element).find(".county").val();
					var countyText = $(element).find(".county").find("option:selected").text();
					if((areaFlag == "1" && province == areaId)
						|| (areaFlag == "2" && city == areaId)
						|| (areaFlag == "3" && county == areaId)
						|| (province == "" && city == "" && county == "")) {
						remarks = "remarks";
						commUtil.alertError("请选择地区添加！");
						return false;
					}
					if(remarks != "") {
						remarks += "-";
					}
					if(deliveryOvertimeSpecialCnfAreaId == '') {
						deliveryOvertimeSpecialCnfAreaId = "id";
					}
					if(province == "") {
						province = "province";
					}
					if(city == "") {
						city = "city";
					}
					if(county == "") {
						county = "county";
					}
					remarks += deliveryOvertimeSpecialCnfAreaId+",";
					remarks += province+"."+city+"."+county+",";
					if(province != "province") {
						remarks += provinceText;
					}
					if(city != "city") {
						remarks += cityText;
					}
					if(county != "county") {
						remarks += countyText;
					}
				});
				if(remarks == "remarks") {
					return false;
				}
				var deliveryOvertimeSpecialCnfAreaId = $(".area-list:last").find(".deliveryOvertimeSpecialCnfAreaId").val();
				var province = $(".area-list:last").find(".province").val();
				var provinceText = $(".area-list:last").find(".province").find("option:selected").text();
				var city = $(".area-list:last").find(".city").val();
				var cityText = $(".area-list:last").find(".city").find("option:selected").text();
				var county = $(".area-list:last").find(".county").val();
				var countyText = $(".area-list:last").find(".county").find("option:selected").text();
				if(province == "" && city == "" && county == "") {
					commUtil.alertError("请选择地区添加！");
					return false;
				}
				if(remarks != "") {
					remarks += "-";
				}
				if(deliveryOvertimeSpecialCnfAreaId == '') {
					deliveryOvertimeSpecialCnfAreaId = "id";
				}
				if(province == "") {
					province = "province";
				}
				if(city == "") {
					city = "city";
				}
				if(county == "") {
					county = "county";
				}
				remarks += deliveryOvertimeSpecialCnfAreaId+",";
				remarks += province+"."+city+"."+county+",";
				if(province != "province") {
					remarks += provinceText;
				}
				if(city != "city") {
					remarks += cityText;
				}
				if(county != "county") {
					remarks += countyText;
				}
				$("#remarks").val(remarks);
				$.ajax({
					url : "${pageContext.request.contextPath}/deliveryOvertimeSpecialCnf/validatePayDate.shtml",
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {id : $("#id").val(), beginPayDate : beginPayDate, endPayDate : endPayDate},
					timeout : 30000,
					success : function(data) {
						if (data.code == "200") {
							if(submitFlag){
								submitFlag = false;
								form.submit();
							}
						}else{
							commUtil.alertError(data.msg);
						}
					},
					error : function() {
						commUtil.alertError('操作超时，请稍后再试！');
					}
				});
				return false;
	        }
	    });
		
	});

	function updateTimeType(timeType) {
		if(timeType == '0') {
			$("#overtime-tr").show();
			$("#deliveryDate-tr").hide();
		}else if(timeType == '1') {
			$("#overtime-tr").hide();
			$("#deliveryDate-tr").show();
		}
	}

	function editArea(deliveryOvertimeSpecialCnfAreaId, province, city, county) {
		var html = [];
		html.push('<div class="area-list" style="margin-top: 5px;margin-bottom: 5px;" >');
		html.push('<input type="hidden" class="deliveryOvertimeSpecialCnfAreaId" name="deliveryOvertimeSpecialCnfAreaId" value="' + deliveryOvertimeSpecialCnfAreaId + '" />');
		html.push('<span class="area-province" style="margin-right: 10px;">');
		html.push('<select class="province" name="province" style="width: 80px;" onchange="updateArea(this, this.value, \'2\');" >');
		html.push('<option value="">请选择...</option>');
		<c:if test="${not empty areaList}">
			var obj = ${areaList};
			for(var i=0;i<obj.length;i++){
				var provinceFlag = true;
				$(".area-list").each(function(index, element){
					if($(element).find(".province").val() == obj[i].areaId && $(element).find(".city").val() == "") {
						provinceFlag = false;
					}
				});
				if(provinceFlag) {
					if(obj[i].areaId == province) {
						html.push('<option value="'+ obj[i].areaId +'" selected >' + obj[i].areaName + '</option>');
					}else {
						html.push('<option value="'+ obj[i].areaId +'" >' + obj[i].areaName + '</option>');
					}
				}
			}
		</c:if>
		html.push('</select>');
		html.push('</span>');

		html.push('<span class="area-city" style="margin-right: 10px;">');
		html.push('<select class="city" name="city" style="width: 80px;" onchange="updateArea(this, this.value, \'3\');" >');
		html.push('<option value="">请选择...</option>');
		if(province != '') {
			var obj = selectArea(province, '2');
			if(obj != "") {
				for(var i=0;i<obj.length;i++){
					var cityFlag = true;
					$(".area-list").each(function(index, element){
						if($(element).find(".city").val() == obj[i].areaId && $(element).find(".county").val() == "") {
							cityFlag = false;
						}
					});
					if(cityFlag) {
						if(obj[i].areaId == city) {
							html.push('<option value="'+ obj[i].areaId +'" selected >' + obj[i].areaName + '</option>');
						}else {
							html.push('<option value="'+ obj[i].areaId +'" >' + obj[i].areaName + '</option>');
						}
					}
				}
			}
		}
		html.push('</select>');
		html.push('</span>');

		html.push('<span class="area-county" style="margin-right: 20px;">');
		html.push('<select class="county" name="county" style="width: 80px;" >');
		html.push('<option value="">请选择...</option>');
		if(city != '') {
			var obj = selectArea(city, '3');
			if(obj != "") {
				for(var i=0;i<obj.length;i++){
					var countyFlag = true;
					$(".area-list").each(function(index, element){
						if($(element).find(".county").val() == obj[i].areaId) {
							countyFlag = false;
						}
					});
					if(countyFlag) {
						if (obj[i].areaId == county) {
							html.push('<option value="' + obj[i].areaId + '" selected >' + obj[i].areaName + '</option>');
						} else {
							html.push('<option value="' + obj[i].areaId + '" >' + obj[i].areaName + '</option>');
						}
					}
				}
			}
		}
		html.push('</select>');
		html.push('</span>');

		html.push('<span class="area-edit">');
		html.push('<input type="button" class="area-add" style="color: #FFFFFF;background-color: #D9534F;border: none;width: 25px;height: 20px;border-radius: 3px;margin-right: 10px;" onclick="areaAdd();"  value="+">');
		html.push('<input type="button" class="area-del" style="color: #FFFFFF;background-color: #D9534F;border: none;width: 25px;height: 20px;border-radius: 3px;margin-right: 10px;" onclick="areaDel(this);"  value="-">');
		html.push('</span>');

		html.push('</div>');

		$("#area-div").append(html.join(""));
	}

	function selectArea(areaId, areaType) {
		var areaList = "";
		$.ajax({
			url : "${pageContext.request.contextPath}/deliveryOvertimeSpecialCnf/selectAreaList.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {parentId : areaId, areaType : areaType},
			timeout : 30000,
			success : function(data) {
				if (data.code == "200") {
					areaList = data.areaList;
				}else{
					commUtil.alertError(data.msg);
				}
			},
			error : function() {
				commUtil.alertError('操作超时，请稍后再试！');
			}
		});
		return areaList;
	}

	function areaEditBut() {
		$(".area-del").show();
		if($(".area-edit").length == 1) {
			$(".area-del").hide();
		}
		$(".area-add").hide();
		$(".area-add:last").show();
	}

	function areaAdd() {
		if($(".area-list:last").find(".province").val() == ""
			&& $(".area-list:last").find(".city").val() == ""
			&& $(".area-list:last").find(".county").val() == "" ) {
			commUtil.alertError("请选择地区添加！");
		}else {
			var areaAddFlag = true;
			var province = $(".area-list:last").find(".province").val();
			var city = $(".area-list:last").find(".city").val();
			var county = $(".area-list:last").find(".county").val();
			$(".area-list").not(".area-list:last").each(function(index, element){
				if($(element).find(".province").val() == province
					&& ($(element).find(".city").val() == "" || city == "")) {
					areaAddFlag = false;
				}else if($(element).find(".province").val() == province
					&& $(element).find(".city").val() == city
					&& ($(element).find(".county").val() == "" || county == "")) {
					areaAddFlag = false;
				}
			});
			if(areaAddFlag) {
				$(".area-list:last").find(".province").attr("disabled", "disabled");
				$(".area-list:last").find(".city").attr("disabled", "disabled");
				$(".area-list:last").find(".county").attr("disabled", "disabled");
				editArea('', '', '', '');
				areaEditBut();
			}else {
				commUtil.alertError("请选择地区添加！");
			}
		}
	}

	function areaDel(obj) {
		$(obj).parent().parent().remove();
		var deliveryOvertimeSpecialCnfAreaId = $(".area-list:last").find(".deliveryOvertimeSpecialCnfAreaId").val();
		var province = $(".area-list:last").find(".province").val();
		var city = $(".area-list:last").find(".city").val();
		var county = $(".area-list:last").find(".county").val();
		$(".area-list:last").remove();
		editArea(deliveryOvertimeSpecialCnfAreaId, province, city, county);
		areaEditBut();
	}

	function updateArea(obj, areaId, areaType) {
		var areaList = "";
		$.ajax({
			url : "${pageContext.request.contextPath}/deliveryOvertimeSpecialCnf/selectAreaList.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {parentId : areaId, areaType : areaType},
			timeout : 30000,
			success : function(data) {
				if (data.code == "200") {
					areaList = data.areaList;
				}else{
					commUtil.alertError(data.msg);
				}
			},
			error : function() {
				commUtil.alertError('操作超时，请稍后再试！');
			}
		});
		var html = [];
		html.push('<option value="">请选择...</option>');
		if(areaList != '') {
			if(areaList != null) {
				for(var i=0;i<areaList.length;i++){
					var areaFlag = true;
					$(".area-list").not(".area-list:last").each(function(index, element){
						if(areaType == '2' && $(element).find(".province").val() == areaId) {
							if($(element).find(".city").val() == "") {
								areaFlag = false;
							}else if($(element).find(".city").val() == areaList[i].areaId && $(element).find(".county").val() == "") {
								areaFlag = false;
							}
						}else if(areaType == '3' && $(element).find(".city").val() == areaId) {
							if($(element).find(".county").val() == "") {
								areaFlag = false;
							}else if($(element).find(".county").val() == areaList[i].areaId) {
								areaFlag = false;
							}
						}
					});
					if(areaFlag) {
						html.push('<option value="'+ areaList[i].areaId +'" >' + areaList[i].areaName + '</option>');
					}
				}
			}
		}
		if(areaType == '2') {
			$(obj).parent().parent(".area-list").find(".area-city").find(".city").html(html.join(""));
			$(obj).parent().parent(".area-list").find(".area-county").find(".county").html('<option value="">请选择...</option>');
		}else {
			$(obj).parent().parent(".area-list").find(".area-county").find(".county").html(html.join(""));
		}
	}

</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/deliveryOvertimeSpecialCnf/editDeliveryOvertimeSpecialCnf.shtml" >
		<input type="hidden" id="id" name="id" value="${deliveryOvertimeSpecialCnf.id }" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">备注名称</td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:180px;" type="text" id="name" name="name" value="${deliveryOvertimeSpecialCnf.name }" />
				</td>
           	</tr>
			<tr>
				<td class="title">开始时间<span style="color: red;">*</span></td>
				<td colspan="2" align="left" class="l-table-edit-td">
					<input type="text" class="dateEditor" id="beginPayDate" name="beginPayDate" style="width: 183px;" value="<fmt:formatDate value="${deliveryOvertimeSpecialCnf.beginPayDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</td>
			</tr>
			<tr>
				<td class="title">结束时间<span style="color: red;">*</span></td>
				<td colspan="2" align="left" class="l-table-edit-td">
					<input type="text" class="dateEditor" id="endPayDate" name="endPayDate" style="width: 183px;" value="<fmt:formatDate value="${deliveryOvertimeSpecialCnf.endPayDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</td>
			</tr>
			<tr>
				<td class="title">时间类型<span style="color: red;">*</span></td>
				<td colspan="2" align="left" class="l-table-edit-td">
					<select id="timeType" name="timeType" style="width: 184px;" onchange="updateTimeType(this.value);" >
						<option value="0" <c:if test="${deliveryOvertimeSpecialCnf.timeType == 0}">selected</c:if> >相对值</option>
						<option value="1" <c:if test="${deliveryOvertimeSpecialCnf.timeType == 1}">selected</c:if> >绝对值</option>
					</select>
				</td>
			</tr>
			<tr id="overtime-tr" style="display: none;">
				<td class="title">发货承诺时间<span style="color: red;">*</span></td>
				<td colspan="2" align="left" class="l-table-edit-td">
					<input type="text" id="overtime" name="overtime" style="width: 183px;" value="${deliveryOvertimeSpecialCnf.overtime}" />
					<span>小时</span>
				</td>
			</tr>
			<tr id="deliveryDate-tr" style="display: none;">
				<td class="title">发货承诺时间<span style="color: red;">*</span></td>
				<td colspan="2" align="left" class="l-table-edit-td table-edit-activity-time">
					<div><input type="text" class="dateEditor" id="deliveryDate" name="deliveryDate" style="width: 180px;" value="<fmt:formatDate value="${deliveryOvertimeSpecialCnf.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" /></div>
					<span>前发货</span>
				</td>
			</tr>
			<tr>
				<td class="title" width="20%">地区添加<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<div id="area-div">
						<input type="hidden" id="remarks" name="remarks" value="" />
					</div>
				</td>
			</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>