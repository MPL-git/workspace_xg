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
	
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 
 <script type="text/javascript">
 	 var viewerPictures;
	 $(function() {
         $(".dateEditor").ligerDateEditor({
             showTime: false,
             labelAlign: 'left'
         });

         viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {
             hide: function () {
                 $("#viewer-pictures").hide();
             }
         });
     });


     //查询类型改变
     function selectTypeChange() {
         var selectType = $("#selectType").val();
         if(selectType == 1){
             $("#timeOldDiv").css("display","none");
             $("#timeNowDiv").removeAttr("style");
         }else{
             var day = new Date();
             day.setTime(day.getTime()-24*60*60*1000);
             var strTime = day.getFullYear()+"-" + (day.getMonth()+1) + "-" + day.getDate();
             $("#beginTime").val(strTime);
             $("#endTime").val(strTime);
             $("#timeNowDiv").css("display","none");
             $("#timeOldDiv").removeAttr("style");
         }
     }
	 
	 //图片查看
	 function viewerPic(entryPic){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+entryPic+'" src="${pageContext.request.contextPath}/file_servelt'+entryPic+'" alt=""></li>');
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		$("#viewer-pictures").show();
		viewerPictures.show();
	 }
	 
	 //排序值
	 function updateSeqNo(activityId) {
		$("#ok" + activityId).hide();
		var seqNoStr = $("#seqNo" + activityId).attr("seqNoStr");
		var seqNo = $("#seqNo" + activityId).val();
		var flag = seqNo.match(/^[1-9]\d*$/);
		if(seqNo != '' && flag != null) {
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/activityNew/updateSeqNo.shtml",
				 data : {activityId : activityId, seqNo : seqNo},
				 dataType : 'json',
				 success : function(data){
					 if(data == null || data.returnCode != 200)
						 commUtil.alertError(data.returnMsg);
					 else{
						 $("#ok" + activityId).show();
						 $("#seqNo" + activityId).attr("seqNoStr", seqNo);
					 }
				 },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
		}else{
			commUtil.alertError("请输入正整数");
			$("#seqNo" + activityId).val(seqNoStr);
		}
	 }
	 
	 //推荐到首页
	 function addAdInfo(activityId) {
		var seqNo = $("#ad" + activityId).val();
		$.ajax({
			 type : 'POST',
			 url : "${pageContext.request.contextPath}/activityNew/addAdInfo.shtml",
			 data : {activityId : activityId},
			 dataType : 'json',
			 success : function(data){
				 if(data == null || data.returnCode != 200)
					 commUtil.alertError(data.returnMsg);
				 else{
					 $("#ad" + activityId).html("已推荐首页");
				 }
			 },
			 error : function(e) {
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
	 }

     function checkFunction() {
        if($("#selectType").val() == 2){
			var beginTime = $("#beginTime").val();
			var endTime = $("#endTime").val();
			if(!beginTime){
                commUtil.alertError("开始时间为必填项！");
                return;
			}
			if(!endTime){
                commUtil.alertError("结束时间为必填项！");
                return;
			}
			if(new Date(beginTime).getTime()>new Date(endTime).getTime()){
                commUtil.alertError("时间格式错误！");
                return;
			}
			var dateNow = $("#beginTimeNow").val();
            if(new Date(endTime).getTime()>=new Date(dateNow).getTime()){
                commUtil.alertError("结束时间必须小于今天！");
                return;
            }
		}
         $("#searchbtn").click();
     }
	 
 	 var listConfig={
	      url:"/activityNew/activityTrafficStatisticsData.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'特卖ID',name:'id', align:'center', isSort:false, width:80},
							{display:'分类排序值',name:'seqNo', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
								var html = [];
								var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
								html.push("<input type='text' style='width: 80px;' seqNoStr='" + seqNo + "' id='seqNo" + rowdata.id + "' name='seqNo' onChange='updateSeqNo(" + rowdata.id + ")' value='" + seqNo + "' >");
								html.push("<span id='ok" + rowdata.id + "' style='color: #009999; display: none;'>OK</span><br/>");
	                        	return html.join("");
	                        }},
	                        {display:'入口图',name:'entryPic', align:'center', isSort:false, width:220, render:function(rowdata, rowindex) {
	                        	return "<img style='width:200px; height:100px;' src='${pageContext.request.contextPath}/file_servelt"+rowdata.entryPic+"' onclick='viewerPic(\""+rowdata.entryPic+"\")' >";
	                        }},
							{display:'活动名称',name:'name', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.name != '') {
									html.push("<span>"+rowdata.NAME+"</span><br/>");
								}
								html.push("<span>销售额："+rowdata.payAmountSum+"元</span><br/>");
								if('${isCwOrgStatus}' == '0') {
									if(rowdata.adInfoId == null) {
										html.push("<span id='ad" + rowdata.id + "' ><a href=\"javascript:addAdInfo(" + rowdata.id + ");\">推荐到首页</a></span>");
									}else {
										html.push("<span id='ad" + rowdata.id + "' >已推荐首页</span>");
									}
								}else {
									if(rowdata.adInfoId != null) {
										html.push("<span id='ad" + rowdata.id + "' >已推荐首页</span>");
									}
								}
	                        	return html.join("");
	                        }},
							{ display: '访客数（会员）',name:'totalVisitorCount'},
							{ display: "访客数（非会员）", name:'totalVisitorCountTourist'},
							{ display: '浏览量（会员）', name:'totalPvCount'},
							{ display: '浏览量（非会员）', name:'totalPvCountTourist'},
							{ display: '加购件数', name:'shoppingCartCount'},
							{ display: '加购转化', name:'addProductRate'},
							{ display: '提交订单件数',name:'subProductCount'},
							{ display: '下单转化', name:'submitOrderRate'},
							{ display: '付款件数', name:'payProductCount'},
							{ display: '付款转化', name:'paymentRate'}
			         ], 
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      } , 							
	      container:{
	        toolBarName:"toptoolbar",
	        searchBtnName:"searchbtn",
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }        
	  }
 
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
<!-- 	<div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td" style="margin-right:-20px;">
					<div class="search-td-label" >特卖ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="activityId" name="activityId" >
					</div>
				</div>
				<div class="search-td" style="margin-right:-20px;">
					<div class="search-td-label" >活动名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="name" name="name" >
					</div>
				</div>
				<div class="search-td" style="margin-right:-20px;">
					<div class="search-td-label" >商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
				<div class="search-td" style="margin-right:-20px;">
					<div class="search-td-label" >商家名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtName" name="mchtName" >
					</div>
				</div>
				<div class="search-td" style="margin-right:-20px;">
					<div class="search-td-label"  >一级类目：</div>
					<div class="search-td-combobox-condition" >
						<select id="productTypeId" name="productTypeId" style="width: 135px;" onchange="productTypelist();" >
							<option value="">请选择...</option>
							<c:forEach var="productType" items="${productTypeList }">
								<option value="${productType.id }">${productType.name }</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td" style="margin-right:-20px;">
					<div class="search-td-label"  >统计类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="selectType" name="selectType" style="width: 135px;" onchange="selectTypeChange();" >
							<option value="1">实时统计</option>
							<option value="2" selected>历史数据</option>
						</select>
					</div>
				</div>
				<span id="timeOldDiv">
					<div class="search-td" style="margin-right:-20px;">
						<div class="search-td-label" style="float:left;" >付款日期：</div>
						<div class="l-panel-search-item" >
							<input type="text" class="dateEditor" id="beginTime" name="beginTime" value="${dateBegin}">
						</div>
						<div class="l-panel-search-item">&nbsp;&nbsp;&nbsp;&nbsp;至</div>
					</div>
					<div class="search-td" style="margin-right:-20px;">
						<div class="l-panel-search-item" >
							<input type="text" class="dateEditor" id="endTime" name="endTime" value="${dateEnd}">
						</div>
					</div>
				</span>
				<span id="timeNowDiv" style="display: none">
					<div class="search-td" style="margin-right:-20px;">
						<div class="search-td-label" style="float:left;" >付款日期：</div>
						<div class="l-panel-search-item">
							<input type="text" id="beginTimeNow" name="beginTimeNow" value="${dateNow}" readonly>
						</div>
						<div class="l-panel-search-item">&nbsp;&nbsp;&nbsp;&nbsp;至</div>
					</div>
					<div class="search-td" style="margin-right:-20px;">
						<div class="l-panel-search-item">
							<input type="text" id="endTimeNow" name="endTimeNow" value="${dateNow}" readonly>
						</div>
					</div>
				</span>
				<div class="search-td-search-btn" >
					<input type="button" class="l-button" onclick="checkFunction();" value="搜索">
					<div id="searchbtn" style="display: none;"></div>
				</div>
			</div>
		</div>

	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
