<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" ></script>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
 
 <script type="text/javascript">
	 
	 $(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
		});
		
	 });
 
	 function formatMoney(s, n) {
	    n = n > 0 && n <= 20 ? n : 2;
	    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	    var l = s.split(".")[0].split("").reverse(),
	    r = s.split(".")[1];
	    t = "";
	    for(i = 0; i < l.length; i ++ )
	    {
	       t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "" : "");
	    }
	    return t.split("").reverse().join("") + "." + r;
	 }
	 
	 //渠道集合优惠率
	 function updateDiscountRate(id) {
		$("#discountRate" + id).parent().find("span").remove();
		var discountRate = $("#discountRate" + id).val();
		var flag = discountRate.match(/^(([1-9]{1}\d{0,6})|(0{1}))(\.\d{1,4})?$/);
		if(discountRate != '' && flag != null) {
			if(Number(discountRate) == Number(0) ) {
				$("#discountRate" + id).val($("#discountRate" + id).attr("discountRate"));
				$("#discountRate" + id).parent().append("<span style='color:red;'>请输入正数</span>");
				return;
			}
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/spreadActivityGroupDiscountRate/updateDiscountRate.shtml",
				 data : {id : id, discountRate : discountRate},
				 dataType : 'json',
				 success : function(data){
					 if(data == null || data.code != 200) {
						 commUtil.alertError(data.msg);
					 }else{
						 $("#discountRate" + id).parent().append("<span style='color:#009999;'>OK</span>");
						 $("#discountRate" + id).attr("discountRate", discountRate);
					 }
				 },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
		}else{
			if(discountRate.match(/^(([1-9]{1}\d*)|(0{1}))(\.\d+)?$/) != null ) {
				if(Number(999999.9999) < Number(discountRate) ) {
					$("#discountRate" + id).val($("#discountRate" + id).attr("discountRate"));
					$("#discountRate" + id).parent().append("<span style='color:red;'>数值过大</span>");
					return;
				}else {
					$("#discountRate" + id).val($("#discountRate" + id).attr("discountRate"));
					$("#discountRate" + id).parent().append("<span style='color:red;'>最多四位小数</span>");
					return;
				}
			}
			$("#discountRate" + id).val($("#discountRate" + id).attr("discountRate"));
			$("#discountRate" + id).parent().append("<span style='color:red;'>请输入正数</span>");
		}
	 }
	 
	 //批量修改优惠率
	 function updateDiscountRateBatch() {
		 $.ligerDialog.open({
			height: 600,
			width: 900,
			title: "批量修改优惠率",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/spreadActivityGroupDiscountRate/updateDiscountRateBatchManager.shtml?deviceType=2",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }

	 function addSpreadActivityGroupDiscountRate() {
		 $.ligerDialog.open({
			 height: 400,
			 width: 600,
			 title: "新增渠道优惠率",
			 name: "INSERT_WINDOW",
			 url: "${pageContext.request.contextPath}/spreadActivityGroupDiscountRate/addSpreadActivityGroupDiscountRateManager.shtml?deviceType=2",
			 showMax: true,
			 showToggle: false,
			 showMin: false,
			 isResize: true,
			 slide: false,
			 data: null
		 });
	 }

 	 var listConfig={
	      url:"/androidGroupDiscountRate/data.shtml",
		  btnItems:[{text:'添加渠道优惠率', icon:'add', click: function() {
				  addSpreadActivityGroupDiscountRate();
			 }}
		  ],
		  pageSize: 10,
	      listGrid:{ columns: [
						{display:'日期',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
							  if(rowdata.date == null || rowdata.date == '' ) {
								  return "";
							  }else{
								  var date = new Date(rowdata.date);
								  return date.format("yyyy-MM-dd");
							  }
						}},
						{display:'活动组ID',name:'spreadActivityGroupId', align:'center', isSort:false, width:180},
						{display:'活动组名称',name:'activityGroupName', align:'center', isSort:false, width:180},
						{display:'活动组优惠率',name:'discountRate', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
							var html = [];
							var discountRate = rowdata.discountRate==null?'':rowdata.discountRate;
							html.push("<input type='text' style='width:80px; margin-top: 5px;' id='discountRate" + rowdata.id + "' name='discountRate' discountRate='"+discountRate+"' onChange='updateDiscountRate(" + rowdata.id + ")' value='" + discountRate + "' >");
							return html.join("");
						}},
						{display:'所属推广渠道',name:'activityGroupChannelName', align:'center', isSort:false, width:180},
						{display:'更新时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
							if(rowdata.updateDate == null || rowdata.updateDate == '' ) {
								return "";
							}else{
								var updateDate = new Date(rowdata.updateDate);
								return updateDate.format("yyyy-MM-dd hh:mm:ss");
							}
						}}
				 ],
				 showCheckbox :false,  //不设置默认为 true
				 showRownumber:true //不设置默认为 true
	      }, 							
	      container:{
	        toolBarName:"toptoolbar",
	        searchBtnName:"searchbtn",
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }        
	  };
 
	  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server" >
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="discountRateDateBegin" name="discountRateDateBegin" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="discountRateDateEnd" name="discountRateDateEnd" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >活动组ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="spreadActivityGroupId" name="spreadActivityGroupId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >活动组名称：</div>
					<div class="search-td-combobox-condition" >
						<select id="groupNameId" name="groupNameId" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="spreadActivityGroup" items="${spreadActivityGroupList }">
								<option value="${spreadActivityGroup.id }">${spreadActivityGroup.activityGroup }</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn">搜索</div>
					<div style="padding-left: 10px;">
						<input type="button" class="l-button" style="width: 100px;height: 25px;cursor: pointer;" value="批量修改优惠率" onclick="updateDiscountRateBatch();">
					</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">

	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
