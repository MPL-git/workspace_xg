<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <script type="text/javascript">
var viewerPictures;
 function formatMoney(s, n)
 {
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

	 $(function() {
	  viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
	  }});
	  $(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
	  });
	  
	$("#searchbtn").bind('click',function(){
		var adType = $("#adType").val();
		if(adType == '2' || adType == '3' || adType == '4' || adType == '5' || adType == '6'
			  || adType == '7' || adType == '8' || adType == '9' || adType == '10' ) {
			if($("#adSourceType").val() == '' ) {
				commUtil.alertError("请选择广告类型");
				return;
			}
		}
		if($('#createDateBegin').val() == '' || $('#createDateEnd').val() == ''){
			commUtil.alertError("日期不能为空");
			return;
		}
		$("#search").click();
	});
	   
	 });
	 
	 function viewerPic(imgPath) {
			$("#viewer-pictures").empty();
			viewerPictures.destroy();
				$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
			viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
				$("#viewer-pictures").hide();
			}});
			$("#viewer-pictures").show();
			viewerPictures.show();
	 }

	function showAdSourceType(adType) {
		if(adType == '2' || adType == '3' || adType == '4' || adType == '5' || adType == '6'
			|| adType == '7' || adType == '8' || adType == '9' || adType == '10' ) {
			$(".ad-source-type").attr("style","display:inline-block;");
		}else {
			$(".ad-source-type").attr("style","display:none;");
		}
	}
	 
 	var listConfig={
	      url:"/trafficData/adsenseList.shtml",
	      listGrid:{ columns: [                    
	                        {display:'日期', name:'statisticalDate', align:'center', isSort:false},
	                        {display:'广告类目', name:'adTypeDesc', align:'center', isSort:false, render: function (rowdata, rowindex){
								var h = rowdata.adTypeDesc;
								if(rowdata.adType == '2' || rowdata.adType == '3' || rowdata.adType == '4' || rowdata.adType == '5' || rowdata.adType == '6'
										|| rowdata.adType == '7' || rowdata.adType == '8' || rowdata.adType == '9' || rowdata.adType == '10' ) {
									if(rowdata.adSourceType == '1' ) {
										h += "--品牌团";
									}else if(rowdata.adSourceType == '2' ) {
										h += "--淘宝优选";
									}else if(rowdata.adSourceType == '3' ) {
										h += "--其它";
									}
								}
								return h;
							}},
				  			{display:'广告状态', name:'adStatusDesc', align:'center', isSort:false},
	                        {display:'广告ID', name:'adInfoId', align:'center', isSort:false},
	                        { display: '图片', width: 120, render: function (rowdata, rowindex){
	                        	var h = "";
	        	                    h += "<img src='${pageContext.request.contextPath}/file_servelt"+rowdata.adPic+"' width='100' height='50' onclick='viewerPic(this.src)'>";
	        	                return h;
	                        }}, 
	                        {display:'曝光（次）', name:'exposureCount', align:'center', isSort:false},
	                        {display:'点击人数(会员)', name:'clickMemberCount', align:'center', isSort:false},
	                        {display:'点击人数(非会员)', name:'clickMemberCountTourist', align:'center', isSort:false},
	                        {display:'点击次数(会员)', name:'clickCount', align:'center', isSort:false},
	                        {display:'点击次数(非会员)', name:'clickCountTourist', align:'center', isSort:false}
			         ],
	                 showRownumber: true //不设置默认为 true
	      } , 							
	      container:{
	        searchBtnName:"search",
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }   
	  };
 
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<input type="hidden" id="pageSize" value="${pageSize}">
	<input type="hidden" id="pageNumber" value="${pageNumber}">
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label">广告类目：</div>
					<div class="search-td-combobox-condition" >
						<select id="adType" name="adType" style="width:135px;" onchange="showAdSourceType(this.value);">
							<option value="">请选择</option>
								<c:forEach var="adType" items="${adTypeList}">
									<option value="${adType.statusValue}">${adType.statusDesc}</option>
								</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td ad-source-type" style="display: none;">
					<div class="search-td-label">广告类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="adSourceType" name="adSourceType" style="width:135px;">
							<option value="">请选择</option>
							<c:forEach var="adSourceType" items="${adSourceTypeList}">
								<option value="${adSourceType.statusValue}">${adSourceType.statusDesc}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">广告状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="adStatus" name="adStatus" style="width:135px;">
							<option value="">请选择</option>
							<option value="1">上架</option>
							<option value="0">下架</option>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">广告ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="adInfoId" name="adInfoId">
					</div>
			 	</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">统计日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="createDateBegin" name="createDateBegin" class="dateEditor"  value="${beginDate}" placeholder="请选择" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="createDateEnd" name="createDateEnd" class="dateEditor" value="${endDate}" placeholder="请选择" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" class="l-button">搜索</div>
					<div id="search" style="display: none;">搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
