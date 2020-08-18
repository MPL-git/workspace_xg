<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
</style>

<script type="text/javascript">

$(function () {
	$("#startTime").ligerDateEditor({
		showTime : false,
		width: 158,
		format: "yyyy-MM-dd"
	});
	
	$("#startDate").ligerDateEditor({
		showTime : true,
		width: 158,
		format: "hh:mm"
	});
	
	$("#endDate").ligerDateEditor({
		showTime : true,
		width: 158,
		format: "hh:mm"
	});
	
});



//图片格式验证
/* function ajaxPicFileUploadImg(statusImg) {
	var file = document.getElementById(statusImg).files[0]; 
	if(!/image\/(GIF|gif)$/.test(file.type)){  
    	commUtil.alertError("图片格式不正确！");
        return;
    }
    var size = file.size;
    if(size > 1024*1024 ) {
    	commUtil.alertError("图片不能大于1024Kb，请重新上传！");
        return;
    }
    var reader = new FileReader();  
    reader.onload = function(e) { 
    	var image = new Image();
    	image.onload = function() {
    		if(this.width != '660' && this.height != '660') {
    			commUtil.alertError("图片像素不是660x660像素！");
        	}else{
        		ajaxFileUploadLogo(statusImg);
        	}
        };
        image.src = e.target.result;
    };
    reader.readAsDataURL(file);  
} 

function ajaxFileUploadLogo() {
	$.ajaxFileUpload({
		url: contextPath + '/service/common/ajax_upload.shtml',
		secureuri: false,
		fileElementId: "logoPicFile",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				$("#logoPic").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
				$("#pic").val(result.FILE_PATH);
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});
	
} */


function commitSave(){
	/* var pic = document.getElementById("pic"); */
	var couponRainSetupId=document.getElementById("couponRainSetupId");
	var gameSeconds=document.getElementById("gameSeconds");
	var bombPercent=document.getElementById("bombPercent");
	var startTime=document.getElementById("startTime");
	var startDate=document.getElementById("startDate");
	var endDate=document.getElementById("endDate");
	
     var startDate1=document.getElementById("startDate1");
	var endDate1=document.getElementById("endDate1"); 
	
	if ($.trim(couponRainSetupId.value)=="") {
		commUtil.alertError("红包雨名称空！");
		return;
	}
	
	if ($.trim(startTime.value)=="") {
		commUtil.alertError("日期不能为空！");
		return;
	}
	
	if ($.trim(startDate.value)=="") {
		commUtil.alertError("开始时间不能为空！");
		return;
	}
	
	if ($.trim(endDate.value)=="") {
		commUtil.alertError("结束时间不能为空！");
		return;
	}
	
	if ($.trim(startDate.value)>$.trim(endDate.value)) {
		commUtil.alertError("开始时间必须小于结束时间！");
		return;
	}
	
	if ($.trim(startDate.value)==$.trim(endDate.value) && $.trim(startDate1.value)>$.trim(endDate1.value)) {
		commUtil.alertError("开始时间必须小于结束时间！");
		return;
	}
	
	/* if ($.trim(pic.value)=="") {
		commUtil.alertError("图片不能为空！");
		return;
	} */
	
	if ($.trim(gameSeconds.value)=="") {
		commUtil.alertError("游戏持续时间不能为空！");
		return;
	}
	if ($.trim(gameSeconds.value)!="" && $.trim(gameSeconds.value)<0.01) {
		commUtil.alertError("游戏持续时间不能为0！");
		return;
	}

	if ($.trim(bombPercent.value)=="") {
		commUtil.alertError("炸弹比例不能为空！");
		return;
	}
	
	if ($.trim(bombPercent.value)!="" && $.trim(bombPercent.value)<0.01) {
		commUtil.alertError("炸弹比例不能为0！");
		return;
	}
	
	if ($.trim(bombPercent.value)>=100) {
		commUtil.alertError("炸弹比例必须小于100%！");
		return;
	}
	

		var dataJson = $("#form1").serializeArray();
		
		$.ajax({
			method: 'POST',
			url: '${pageContext.request.contextPath}/couponRain/editcouponRain.shtml',
			data: dataJson,
			dataType: 'json',
			cache : false,
			async : false,
			timeout : 30000,
			success:function(data){
				if(data.returnCode == '0000'){
					commUtil.alertSuccess("保存成功");
					setTimeout(function(){
						parent.location.reload();
						frameElement.dialog.close();
					},1000);
				}else{
					commUtil.alertError(data.returnMsg); 
				}
			},
			error:function(){
				commUtil.alertError("请求失败"); 
			}
		});	

};

</script>

<html>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/couponRain/editcouponRain.shtml">
		<input type="hidden" id="id" name="id" value="${couponRain.id}"/>
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">红包雨<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
			      <select id="couponRainSetupId" name="couponRainSetupId" style="width: 150px;">
					<option value="">请选择...</option>
					  <c:forEach var="couponRainSetups" items="${couponRainSetups}">
					    <option value="${couponRainSetups.id}" <c:if test="${couponRain.couponRainSetupId==couponRainSetups.id}">selected</c:if>>${couponRainSetups.title}</option>
					</c:forEach>  
			      </select>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">日期<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="startTime" name="startTime" value="<fmt:formatDate value="${couponRain.startTime}" pattern="yyyy-MM-dd"/>"/>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">开始时间<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<%-- <input type="text" id="startDate" name="startDate" value="<fmt:formatDate value="${couponRain.startTime}" pattern="HH:mm"/>"/>&nbsp;&nbsp;至
					<input type="text" id="endDate" name="endDate" value="<fmt:formatDate value="${couponRain.endTime}" pattern="HH:mm"/>"/> --%>
				<select id="startDate" name="startDate" style="width: 60px;">
					    <option value="00" <c:if test="${startTime1==00}">selected</c:if>>00</option>
					    <option value="01" <c:if test="${startTime1==01}">selected</c:if>>01</option>
					    <option value="02" <c:if test="${startTime1==02}">selected</c:if>>02</option>
					    <option value="03" <c:if test="${startTime1==03}">selected</c:if>>03</option>
					    <option value="04" <c:if test="${startTime1==04}">selected</c:if>>04</option>
					    <option value="05" <c:if test="${startTime1==05}">selected</c:if>>05</option>
					    <option value="06" <c:if test="${startTime1==06}">selected</c:if>>06</option>
					    <option value="07" <c:if test="${startTime1==07}">selected</c:if>>07</option>
					    <option value="08" <c:if test="${startTime1==08}">selected</c:if>>08</option>
					    <option value="09" <c:if test="${startTime1==09}">selected</c:if>>09</option>
					    <option value="10" <c:if test="${startTime1==10}">selected</c:if>>10</option>
					    <option value="11" <c:if test="${startTime1==11}">selected</c:if>>11</option>
					    <option value="12" <c:if test="${startTime1==12}">selected</c:if>>12</option>
					    <option value="13" <c:if test="${startTime1==13}">selected</c:if>>13</option>
					    <option value="14" <c:if test="${startTime1==14}">selected</c:if>>14</option>
					    <option value="15" <c:if test="${startTime1==15}">selected</c:if>>15</option>
					    <option value="16" <c:if test="${startTime1==16}">selected</c:if>>16</option>
					    <option value="17" <c:if test="${startTime1==17}">selected</c:if>>17</option>
					    <option value="18" <c:if test="${startTime1==18}">selected</c:if>>18</option>
					    <option value="19" <c:if test="${startTime1==19}">selected</c:if>>19</option>
					    <option value="20" <c:if test="${startTime1==20}">selected</c:if>>20</option>
					    <option value="21" <c:if test="${startTime1==21}">selected</c:if>>21</option>
					    <option value="22" <c:if test="${startTime1==22}">selected</c:if>>22</option>
					    <option value="23" <c:if test="${startTime1==23}">selected</c:if>>23</option>
			      </select>
			      
			      <select id="startDate1" name="startDate1" style="width: 60px;">
					    <option value="00" <c:if test="${startTime2==00}">selected</c:if>>00</option>
					    <option value="01" <c:if test="${startTime2==01}">selected</c:if>>01</option>
					    <option value="02" <c:if test="${startTime2==02}">selected</c:if>>02</option>
					    <option value="03" <c:if test="${startTime2==03}">selected</c:if>>03</option>
					    <option value="04" <c:if test="${startTime2==04}">selected</c:if>>04</option>
					    <option value="05" <c:if test="${startTime2==05}">selected</c:if>>05</option>
					    <option value="06" <c:if test="${startTime2==06}">selected</c:if>>06</option>
					    <option value="07" <c:if test="${startTime2==07}">selected</c:if>>07</option>
					    <option value="08" <c:if test="${startTime2==08}">selected</c:if>>08</option>
					    <option value="09" <c:if test="${startTime2==09}">selected</c:if>>09</option>
					    <option value="10" <c:if test="${startTime2==10}">selected</c:if>>10</option>
					    <option value="11" <c:if test="${startTime2==11}">selected</c:if>>11</option>
					    <option value="12" <c:if test="${startTime2==12}">selected</c:if>>12</option>
					    <option value="13" <c:if test="${startTime2==13}">selected</c:if>>13</option>
					    <option value="14" <c:if test="${startTime2==14}">selected</c:if>>14</option>
					    <option value="15" <c:if test="${startTime2==15}">selected</c:if>>15</option>
					    <option value="16" <c:if test="${startTime2==16}">selected</c:if>>16</option>
					    <option value="17" <c:if test="${startTime2==17}">selected</c:if>>17</option>
					    <option value="18" <c:if test="${startTime2==18}">selected</c:if>>18</option>
					    <option value="19" <c:if test="${startTime2==19}">selected</c:if>>19</option>
					    <option value="20" <c:if test="${startTime2==20}">selected</c:if>>20</option>
					    <option value="21" <c:if test="${startTime2==21}">selected</c:if>>21</option>
					    <option value="22" <c:if test="${startTime2==22}">selected</c:if>>22</option>
					    <option value="23" <c:if test="${startTime2==23}">selected</c:if>>23</option>
					    <option value="24" <c:if test="${startTime2==24}">selected</c:if>>24</option>
					    <option value="25" <c:if test="${startTime2==25}">selected</c:if>>25</option>
					    <option value="26" <c:if test="${startTime2==26}">selected</c:if>>26</option>
					    <option value="27" <c:if test="${startTime2==27}">selected</c:if>>27</option>
					    <option value="28" <c:if test="${startTime2==28}">selected</c:if>>28</option>
					    <option value="29" <c:if test="${startTime2==29}">selected</c:if>>29</option>
					    <option value="30" <c:if test="${startTime2==30}">selected</c:if>>30</option>
					    <option value="31" <c:if test="${startTime2==31}">selected</c:if>>31</option>
					    <option value="32" <c:if test="${startTime2==32}">selected</c:if>>32</option>
					    <option value="33" <c:if test="${startTime2==33}">selected</c:if>>33</option>
					    <option value="34" <c:if test="${startTime2==34}">selected</c:if>>34</option>
					    <option value="35" <c:if test="${startTime2==35}">selected</c:if>>35</option>
					    <option value="36" <c:if test="${startTime2==36}">selected</c:if>>36</option>
					    <option value="37" <c:if test="${startTime2==37}">selected</c:if>>37</option>
					    <option value="38" <c:if test="${startTime2==38}">selected</c:if>>38</option>
					    <option value="39" <c:if test="${startTime2==39}">selected</c:if>>39</option>
					    <option value="40" <c:if test="${startTime2==40}">selected</c:if>>40</option>
					    <option value="41" <c:if test="${startTime2==41}">selected</c:if>>41</option>
					    <option value="42" <c:if test="${startTime2==42}">selected</c:if>>42</option>
					    <option value="43" <c:if test="${startTime2==43}">selected</c:if>>43</option>
					    <option value="44" <c:if test="${startTime2==44}">selected</c:if>>44</option>
					    <option value="45" <c:if test="${startTime2==45}">selected</c:if>>45</option>
					    <option value="46" <c:if test="${startTime2==46}">selected</c:if>>46</option>
					    <option value="47" <c:if test="${startTime2==47}">selected</c:if>>47</option>
					    <option value="48" <c:if test="${startTime2==48}">selected</c:if>>48</option>
					    <option value="49" <c:if test="${startTime2==49}">selected</c:if>>49</option>
					    <option value="50" <c:if test="${startTime2==50}">selected</c:if>>50</option>
					    <option value="51" <c:if test="${startTime2==51}">selected</c:if>>51</option>
					    <option value="52" <c:if test="${startTime2==52}">selected</c:if>>52</option>
					    <option value="53" <c:if test="${startTime2==53}">selected</c:if>>53</option>
					    <option value="54" <c:if test="${startTime2==54}">selected</c:if>>54</option>
					    <option value="55" <c:if test="${startTime2==55}">selected</c:if>>55</option>
					    <option value="56" <c:if test="${startTime2==56}">selected</c:if>>56</option>
					    <option value="57" <c:if test="${startTime2==57}">selected</c:if>>57</option>
					    <option value="58" <c:if test="${startTime2==58}">selected</c:if>>58</option>
					    <option value="59" <c:if test="${startTime2==59}">selected</c:if>>59</option>
			      </select>
			                     至&nbsp;&nbsp;
			         <select id="endDate" name="endDate" style="width: 60px;">
					    <option value="00" <c:if test="${endTime1==00}">selected</c:if>>00</option>
					    <option value="01" <c:if test="${endTime1==01}">selected</c:if>>01</option>
					    <option value="02" <c:if test="${endTime1==02}">selected</c:if>>02</option>
					    <option value="03" <c:if test="${endTime1==03}">selected</c:if>>03</option>
					    <option value="04" <c:if test="${endTime1==04}">selected</c:if>>04</option>
					    <option value="05" <c:if test="${endTime1==05}">selected</c:if>>05</option>
					    <option value="06" <c:if test="${endTime1==06}">selected</c:if>>06</option>
					    <option value="07" <c:if test="${endTime1==07}">selected</c:if>>07</option>
					    <option value="08" <c:if test="${endTime1==08}">selected</c:if>>08</option>
					    <option value="09" <c:if test="${endTime1==09}">selected</c:if>>09</option>
					    <option value="10" <c:if test="${endTime1==10}">selected</c:if>>10</option>
					    <option value="11" <c:if test="${endTime1==11}">selected</c:if>>11</option>
					    <option value="12" <c:if test="${endTime1==12}">selected</c:if>>12</option>
					    <option value="13" <c:if test="${endTime1==13}">selected</c:if>>13</option>
					    <option value="14" <c:if test="${endTime1==14}">selected</c:if>>14</option>
					    <option value="15" <c:if test="${endTime1==15}">selected</c:if>>15</option>
					    <option value="16" <c:if test="${endTime1==16}">selected</c:if>>16</option>
					    <option value="17" <c:if test="${endTime1==17}">selected</c:if>>17</option>
					    <option value="18" <c:if test="${endTime1==18}">selected</c:if>>18</option>
					    <option value="19" <c:if test="${endTime1==19}">selected</c:if>>19</option>
					    <option value="20" <c:if test="${endTime1==20}">selected</c:if>>20</option>
					    <option value="21" <c:if test="${endTime1==21}">selected</c:if>>21</option>
					    <option value="22" <c:if test="${endTime1==22}">selected</c:if>>22</option>
					    <option value="23" <c:if test="${endTime1==23}">selected</c:if>>23</option>
			      </select>
			      
			      <select id="endDate1" name="endDate1" style="width: 60px;">
					    <option value="00" <c:if test="${endTime2==00}">selected</c:if>>00</option>
					    <option value="01" <c:if test="${endTime2==01}">selected</c:if>>01</option>
					    <option value="02" <c:if test="${endTime2==02}">selected</c:if>>02</option>
					    <option value="03" <c:if test="${endTime2==03}">selected</c:if>>03</option>
					    <option value="04" <c:if test="${endTime2==04}">selected</c:if>>04</option>
					    <option value="05" <c:if test="${endTime2==05}">selected</c:if>>05</option>
					    <option value="06" <c:if test="${endTime2==06}">selected</c:if>>06</option>
					    <option value="07" <c:if test="${endTime2==07}">selected</c:if>>07</option>
					    <option value="08" <c:if test="${endTime2==08}">selected</c:if>>08</option>
					    <option value="09" <c:if test="${endTime2==09}">selected</c:if>>09</option>
					    <option value="10" <c:if test="${endTime2==10}">selected</c:if>>10</option>
					    <option value="11" <c:if test="${endTime2==11}">selected</c:if>>11</option>
					    <option value="12" <c:if test="${endTime2==12}">selected</c:if>>12</option>
					    <option value="13" <c:if test="${endTime2==13}">selected</c:if>>13</option>
					    <option value="14" <c:if test="${endTime2==14}">selected</c:if>>14</option>
					    <option value="15" <c:if test="${endTime2==15}">selected</c:if>>15</option>
					    <option value="16" <c:if test="${endTime2==16}">selected</c:if>>16</option>
					    <option value="17" <c:if test="${endTime2==17}">selected</c:if>>17</option>
					    <option value="18" <c:if test="${endTime2==18}">selected</c:if>>18</option>
					    <option value="19" <c:if test="${endTime2==19}">selected</c:if>>19</option>
					    <option value="20" <c:if test="${endTime2==20}">selected</c:if>>20</option>
					    <option value="21" <c:if test="${endTime2==21}">selected</c:if>>21</option>
					    <option value="22" <c:if test="${endTime2==22}">selected</c:if>>22</option>
					    <option value="23" <c:if test="${endTime2==23}">selected</c:if>>23</option>
					    <option value="24" <c:if test="${endTime2==24}">selected</c:if>>24</option>
					    <option value="25" <c:if test="${endTime2==25}">selected</c:if>>25</option>
					    <option value="26" <c:if test="${endTime2==26}">selected</c:if>>26</option>
					    <option value="27" <c:if test="${endTime2==27}">selected</c:if>>27</option>
					    <option value="28" <c:if test="${endTime2==28}">selected</c:if>>28</option>
					    <option value="29" <c:if test="${endTime2==29}">selected</c:if>>29</option>
					    <option value="30" <c:if test="${endTime2==30}">selected</c:if>>30</option>
					    <option value="31" <c:if test="${endTime2==31}">selected</c:if>>31</option>
					    <option value="32" <c:if test="${endTime2==32}">selected</c:if>>32</option>
					    <option value="33" <c:if test="${endTime2==33}">selected</c:if>>33</option>
					    <option value="34" <c:if test="${endTime2==34}">selected</c:if>>34</option>
					    <option value="35" <c:if test="${endTime2==35}">selected</c:if>>35</option>
					    <option value="36" <c:if test="${endTime2==36}">selected</c:if>>36</option>
					    <option value="37" <c:if test="${endTime2==37}">selected</c:if>>37</option>
					    <option value="38" <c:if test="${endTime2==38}">selected</c:if>>38</option>
					    <option value="39" <c:if test="${endTime2==39}">selected</c:if>>39</option>
					    <option value="40" <c:if test="${endTime2==40}">selected</c:if>>40</option>
					    <option value="41" <c:if test="${endTime2==41}">selected</c:if>>41</option>
					    <option value="42" <c:if test="${endTime2==42}">selected</c:if>>42</option>
					    <option value="43" <c:if test="${endTime2==43}">selected</c:if>>43</option>
					    <option value="44" <c:if test="${endTime2==44}">selected</c:if>>44</option>
					    <option value="45" <c:if test="${endTime2==45}">selected</c:if>>45</option>
					    <option value="46" <c:if test="${endTime2==46}">selected</c:if>>46</option>
					    <option value="47" <c:if test="${endTime2==47}">selected</c:if>>47</option>
					    <option value="48" <c:if test="${endTime2==48}">selected</c:if>>48</option>
					    <option value="49" <c:if test="${endTime2==49}">selected</c:if>>49</option>
					    <option value="50" <c:if test="${endTime2==50}">selected</c:if>>50</option>
					    <option value="51" <c:if test="${endTime2==51}">selected</c:if>>51</option>
					    <option value="52" <c:if test="${endTime2==52}">selected</c:if>>52</option>
					    <option value="53" <c:if test="${endTime2==53}">selected</c:if>>53</option>
					    <option value="54" <c:if test="${endTime2==54}">selected</c:if>>54</option>
					    <option value="55" <c:if test="${endTime2==55}">selected</c:if>>55</option>
					    <option value="56" <c:if test="${endTime2==56}">selected</c:if>>56</option>
					    <option value="57" <c:if test="${endTime2==57}">selected</c:if>>57</option>
					    <option value="58" <c:if test="${endTime2==58}">selected</c:if>>58</option>
					    <option value="59" <c:if test="${endTime2==59}">selected</c:if>>59</option>
			      </select>      
				</td>
			</tr>
			<%-- <tr>
               <td  class="title" width="20%">弹窗图片<span class="required">*</span></td>
               <td align="left" class="l-table-edit-td" >
               		<div><img id="logoPic" style="width: 300px;height: 150px" alt="" src="${pageContext.request.contextPath}/file_servelt${couponRain.pic}"></div>
	    			<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file" onchange="ajaxPicFileUploadImg('logoPicFile');" />
	    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    				<span style="color: gray;">(图片尺寸不超过660*660px，大小不超过1024kb)</span>
	    			</div>
	    			<input id="pic" name="pic" type="hidden" value="${couponRain.pic}">
               </td>
           </tr> --%>
           <tr>
				<td colspan="1" class="title">游戏持续时间<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="gameSeconds" name="gameSeconds" value="${couponRain.gameSeconds}" />&nbsp;秒
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">炸弹比例<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text"  id="bombPercent" name="bombPercent" value="${bombPercent}"  maxlength="5"/>&nbsp;%
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit"  id="Button1" style="float:left;" class="l-button l-button-submit" value="提交" onclick="commitSave();"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
