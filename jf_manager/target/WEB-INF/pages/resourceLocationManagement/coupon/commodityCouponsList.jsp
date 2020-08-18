<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<style>
</style>
<script type="text/javascript">
	var viewerPictures;

	$(function(){
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
				$("#viewer-pictures").hide();
			}});
	});

	function viewerPic(productId){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$.ajax({
			url : "${pageContext.request.contextPath}/product/getPoductPic.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {productId:productId},
			timeout : 30000,
			success : function(data) {

				if(data&&data.length>0){
					for (var i=0;i<data.length;i++)
					{	if(data[i].pic.indexOf("http") >= 0){
						$("#viewer-pictures").append('<li><img data-original="'+data[i].pic+'" src="'+data[i].pic+'" alt=""></li>');
					}else{
						$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" alt=""></li>');
					}
					}
					viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
							$("#viewer-pictures").hide();
						}});
					$("#viewer-pictures").show();
					viewerPictures.show();
				}


			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});

	}

	function gridRefresh() {
			if (listModel.gridManager) {
				var gridparms = [];
				gridparms.push({ name: "staff", value: "self" });
				listModel.gridManager.loadServerData(gridparms);
			}
		}

	//抽中明细
	function memberLotteryView(id) {
		 $.ligerDialog.open({
			 height: $(window).height() - 40,
			 width: 1200,
			 title: "优惠券抽中明细",
			 name: "INSERT_WINDOW",
			 url: "${pageContext.request.contextPath}/resourceLocationManagement/memberLotteryView.shtml?type=2&couponId="+id,
			 showMax: true,
			 showToggle: false,
			 showMin: false,
			 isResize: true,
			 slide: false,
			 data: null
		 });
	 }
 
  var listConfig={
	  
      url:"/resourceLocationManagement/commodityCouponsListData.shtml",
    
      listGrid:{ columns: [  
 		                { display: '店铺名称/商家序号',width: 200, height:126,render: function (rowdata, rowindex) {
 		                	var html = [];
 		                	html.push(rowdata.mchtName);
 		                	html.push("<br>");
 		                	html.push(rowdata.mchtCode);
 		              	 	return html.join("");
		                }},
		                { display: '商品', width: 280, align: 'center',render: function (rowdata, rowindex) {
		            		  var tempBrand = rowdata.productBrandName
  		            		  var tempName = rowdata.productName;

								var html=[];
								var h = "";
								if(rowdata.pic!=null&&(rowdata.pic.indexOf("http") >= 0)){
									h += "<div class='no-check' style='display:  inline-flex; margin:  20px;'><img src='"+rowdata.pic+"' width='100' height='100' onclick='viewerPic("+rowdata.typeIds+")'>";
								}else{
									h += "<div class='no-check' style='display:  inline-flex; margin:  20px;'><img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='100' height='100' onclick='viewerPic("+rowdata.typeIds+")'>";
								}
								html.push(h);
								html.push("<div class='width-226'><p class='ellipsis'>"+tempName+"</p><p>品牌:"+tempBrand+"</p><p>ID:"+rowdata.productCode+"</p></div>")
								html.push("<div>")
								
								return html.join(""); 

		                }},
		                { display: '面额',  name: 'money'},
		                { display: '活动时间', width: 150, render: function (rowdata, rowindex) {
		                	var html = [];
		                    if (rowdata.recBeginDate!=null){
		                    	var beginDate=new Date(rowdata.recBeginDate);
		                    	html.push("起 "+ beginDate.format("yyyy-MM-dd hh:mm"));
		                    }
		                    if (rowdata.recEndDate!=null){
		                    	var endDate=new Date(rowdata.recEndDate);
								if(endDate <= new Date) {
									html.push("止 <span style='color: red;'>"+endDate.format("yyyy-MM-dd hh:mm")+"</span></br>");
								}else {
									html.push("止 "+endDate.format("yyyy-MM-dd hh:mm"));
								}
		                    }
							return html.join("<br>");
		                }},
		                { display: '发行量',  name: 'grantQuantity'},
		                { display: '领取量',  name: 'recQuantity'},
		                { display: '使用量',  name: 'useQuantity'},
		                { display: '状态', render: function(rowdata, rowindex) {
		                	var html = [];
		                	var now = new Date();
		                	var beginTime = rowdata.recBeginDate;
		                	var endTime = rowdata.recEndDate;
	                  		if(now>endTime){
	                  			 html.push("已结束");
	                  		}
	                  		if(now>=beginTime && now<=endTime){
	                  			 html.push("活动中");
	                  		}
	                  		if(now<beginTime){
	                  			 html.push("未开始")
	                  		}
	                  		return html.join("")
		                }},
		                { display: '操作', hide: ${pagetype ne 10005}, render: function(rowdata, rowindex) {
	                  		return '<a href="javascript:memberLotteryView('+ rowdata.id +');">抽中明细</a>'
		                }},
		                ],
                 showCheckbox : false,  //不设置默认为 true
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

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<c:if test="${roleType!=1}">
	<div id="toptoolbar"></div>
	</c:if>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		
		<div class="search-tr"  >

			<c:if test="${pagetype eq 1092}">
			<input type="hidden" id="isIntegralTurntable" name="isIntegralTurntable" value="0"/>
			<div class="search-td">
			<div class="search-td-label" style="float:left;">日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="recBeginDate" name="recBeginDate" />
				<script type="text/javascript">
					$(function() {
						$("#recBeginDate").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			</div>

			<div class="search-td">
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
			<div class="l-panel-search-item">
				<input type="text" id="recEndDate" name="recEndDate" />
				<script type="text/javascript">
					$(function() {
						$("#recEndDate").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>

			</div>
				<div class="search-td">
					<div class="search-td-label" >状态：</div>
					<div class="search-td-condition" >
						<select id="seachStatus" name="seachStatus">
							<option value="">请选择...</option>
							<option value="1">未开始</option>
							<option value="2">活动中</option>
							<option value="3">已结束</option>
						</select>
					</div>
				</div>
			</c:if>
			<c:if test="${pagetype eq 10002}">
				<input type="hidden" id="isIntegralTurntable" name="isIntegralTurntable" value="1"/>
				<div class="search-td">
					<div class="search-td-label" style="float:left;">日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="recDate" name="recDate" />
						<script type="text/javascript">
							$(function() {
								$("#recDate").ligerDateEditor( {
									showTime : false,
									labelWidth : 150,
									width:150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label">面额：</div>
					<div class="search-td-condition" style="display: inline-flex;">
						<div style="float: left;"><input type="text" id="moneyMin" name="moneyMin"></div>
						<div style="width: 25px;">--</div>
						<div style="float: left;"><input type="text" id="moneyMax" name="moneyMax"></div>
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label" >状态：</div>
					<div class="search-td-condition" >
						<select id="seachStatus" name="seachStatus">
							<option value="">请选择...</option>
							<option value="1">未开始</option>
							<option value="2">活动中</option>
							<option value="3">已结束</option>
						</select>
					</div>
				</div>
			</c:if>
			<c:if test="${pagetype eq 10005}">
				<input type="hidden" id="isIntegralTurntable" name="isIntegralTurntable" value="1"/>
				<input type="hidden" id="seachStatus" name="seachStatus" value="2"/>
				<input type="hidden" id="moneyMin" name="moneyMin" value="${moneyMin}"/>
				<input type="hidden" id="moneyLessThan" name="moneyLessThan" value="${moneyMax}"/>
				<div class="search-td">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-condition">
						<input type="text" id="mchtCode" name="mchtCode">
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label">店铺名：</div>
					<div class="search-td-condition">
						<input type="text" id="mchtName" name="mchtName">
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label">品牌：</div>
					<div class="search-td-condition">
						<input type="text" id="productBrandName" name="productBrandName">
					</div>
				</div>
			</c:if>

			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
			
		</div>
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>