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
	 $(function() {
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left'
		});

         viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {
             hide: function () {
                 $("#viewer-pictures").hide();
             }
         });

	 });

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
	
	 //商品信息
	 function viewProduct(id) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: 1200,
			title: "商品信息",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/product/viewProduct.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

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

	function updateArtNo(id) {
		$("#seqNo" + id).parent().find("span").remove();
		var seqNo = $("#seqNo" + id).val();
		var flag = seqNo.match(/^[1-9]\d*$/);
		if(seqNo != '' && flag != null) {
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/singleProductActivity/updateSingleProductActivity.shtml",
				 data : {id : id, seqNo : seqNo},
				 dataType : 'json',
				 async: false,
				 success : function(data){
					 if(data == null || data.statusCode != 200)
						 commUtil.alertError(json.message);
					 else{
						 $("#seqNo" + id).parent().append("<span style='color:#009999;'>更改成功</span>");
					 }
				 },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
		}else{
			$("#seqNo" + id).val("");
			$("#seqNo" + id).parent().append("<span style='color:red;'>请输入正整数</span>");
		}
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
	      url:"/singleProductActivity/singleProductActivityTrafficStatisticsData.shtml?type=${type }",
	      btnItems:[],
	      listGrid:{ columns: [
	                        {display:'排序值',name:'seqNo', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
	                        	var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
	                        	return "<input type='text' id='seqNo" + rowdata.id + "' name='seqNo' onChange='updateArtNo(" + rowdata.id + ")' value='" + seqNo + "' >";
	                        }},
	                        {display:'单品活动ID',name:'id', align:'center', isSort:false, width:80},
	                        {display:'主图',name:'productPic', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
	                        	return "<div style='padding:3px;'><img style='width:50px;height:50px;' src='${pageContext.request.contextPath}/file_servelt"+rowdata.productPic+"' onclick='viewerPic("+rowdata.entryPic+")'></div>";
	                        }},
	                        {display:'品牌 / 货号 / 商品ID',name:'brandName', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	return (rowdata.brandName==null?'':rowdata.brandName) 
	                        		+ "<br/>" + (rowdata.artNo==null?'':rowdata.artNo) 
	                        		+ "<br/>" + (rowdata.code==null?'':rowdata.code) ;
	                        }},
	                        {display:'店铺名 / 商品名',name:'shopName', align:'center', isSort:false, width:260, render:function(rowdata, rowindex) {
	                        	var html = [];
	                        	if(rowdata.mchtGradeDesc) {
				                    html.push("<span style='color:red;margin-right:5px;'>"+rowdata.mchtGradeDesc+"</span>"); 
 	                        	}
	                        	html.push((rowdata.shopName==null?'':rowdata.shopName) 
	                        			+ "<br/><a href=\"javascript:viewProduct(" + rowdata.productId + ");\">"
	                        			+ (rowdata.productName==null?'':rowdata.productName) + "</a>");
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
	                 showCheckbox : true,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      } , 							
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
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td" style="margin-right:-20px;">
					<div class="search-td-label" >商品ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productId" name="productId" >
					</div>
				</div>
				<div class="search-td" style="margin-right:-20px;">
					<div class="search-td-label" >商品名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productName" name="productName" >
					</div>
				</div>
				<div class="search-td" style="margin-right:-20px;">
					<div class="search-td-label" >商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
				<div class="search-td" style="margin-right:-20px;">
					<div class="search-td-label" >店铺名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtName" name="mchtName" >
					</div>
				</div>
				<div class="search-td" style="margin-right:-20px;">
					<div class="search-td-label"  >一级类目：</div>
					<div class="search-td-combobox-condition" >
						<select id="productTypeId" name="productTypeId" style="width: 135px;" >
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
			<span class="search-tr"  >

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
