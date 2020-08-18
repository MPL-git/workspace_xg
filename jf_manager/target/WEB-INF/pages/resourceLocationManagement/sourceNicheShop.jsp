<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<html>
<head>
<script type="text/javascript">
	
$(function(){
	
	$(".l-dialog-close").live("click", function(){
		$("#searchbtn").click();
	});
	
});

	//查看
	function watchProduct(id) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "查看店铺信息",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/resourceLocationManagement/watchProduct.shtml?sourceId=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
	
	//添加
	function admchtinfolist() {
		$.ligerDialog.open({
			height: $(window).height() - 50,
			width: $(window).width() - 50,
			title : "添加",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/resourceLocationManagement/admchtinfolist.shtml",
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	
	//删除
	function deleteviewMchtinfo(id) {
	    var title="删除";
	    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
	    if (yes) {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/resourceLocationManagement/deleteviewProduct.shtml',
			data: {id : id},
			dataType: 'json',
			success: function(data) {
				if(data.code != null && data.code == 200) {
					$("#searchbtn").click();
				}else {
					commUtil.alertError(data.msg);
				}
			},
			error: function(e) {
				commUtil.alertError("系统异常请稍后再试");
			}
		});
	   }
	  });
	 }

	 //修改排序值
	function updateArtNo(id) {
		$("#seqNo" + id).parent().find("span").remove();
		var seqNo = $("#seqNo" + id).val();
		var flag = seqNo.match(/^[1-9]\d*$/);
		if(seqNo != '' && flag != null) {
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/resourceLocationManagement/updateSourcenicheseNo.shtml",
				 data : {id : id, seqNo : seqNo},
				 dataType : 'json',
				 success : function(data){
					 if(data == null || data.statusCode != 200){
						 
						 commUtil.alertError(json.message);
					 }
					 else{
						 $("#seqNo" + id).parent().append("<span style='color:#009999;'>OK</span>");
						 $("#searchbtn").click();
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
	 
	 //清空排序值
	function delseqNo(id) {
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/resourceLocationManagement/delseqNo.shtml",
				 data : {id : id},
				 dataType : 'json',
				 success : function(data){
					 if(data.code != null && data.code == 200){
						 
						 commUtil.alertError(json.message);
					 }else{
						 $("#searchbtn").click();
					 }
					
				 },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
	}
	
	//查看店铺
	function showMchtShop(mchtId, mchtType) {
		$.ligerDialog.open({
		 	height: $(window).height() - 50,
			width: $(window).width() - 100,
			title: "商品列表",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/showMchtShopManager.shtml?mchtId="+mchtId+"&mchtType="+mchtType,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	} 
	 
 var listConfig={
      url:"/resourceLocationManagement/sourcenicheshopdata.shtml?pagetype=${pagetype}",
/* 
      btnItems:[{text: '添加',icon: 'add',click: function() {
    	         admchtinfolist();
      }},
	  ], */
      listGrid:{ columns: [
						{display:'排序', name:'seqNo', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
								var html = [];
								var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
								html.push("<input type='text' style='width:70px;margin-top:5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateArtNo(" + rowdata.id + ")' value='" + seqNo + "' >");
								if (seqNo!=null && seqNo!='') {
									html.push("<br>");
									html.push("<a href=\"javascript:delseqNo(" + rowdata.id + ");\">清空</a>");
								}
	                        	return html.join("");
	                    }},
	                    { display: '权重值', name: 'weight', width: 100},

							 
			            { display: '商家序号', name: 'mchtCode', width: 100},
			            { display: '开通日期', name: 'cooperate_begin_date', width: 120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.cooperateBeginDate != null && rowdata.cooperateBeginDate != ""){
   	   		                   var cooperateBeginDate = new Date(rowdata.cooperateBeginDate);
   	   		          	       return cooperateBeginDate.format("yyyy-MM-dd");	
   		                	}
		                }},
				         { display: '类目/商家名称', width: 200, render: function(rowdata, rowindex){
			                	var html = [];
						    	html.push(rowdata.productTypeName);
							    html.push("<br/>"+rowdata.companyName);
							    if(rowdata.shopStatus == '1') {
							    	html.push('<br/><a href="${previewUrl}'+rowdata.linkId+'&preview=1" target="_blank">'+rowdata.shopName+'</a>');
							    
 		                	}else {
			                		html.push("<br/><a href=\"javascript:showMchtShop("+ rowdata.linkId +", "+ rowdata.mchtType +");\">"+ rowdata.shopName +"</a>");
			                	}
			                    return html.join("");
			                }},
			                { display: '品牌', name: '', width: 180 ,render: function(rowdata, rowindex) {
			                	var mchtProductBrandName = rowdata.mchtProductbrandName;
			                	var html = [];
								html.push(mchtProductBrandName);
								return html.join("");
			                }},
			                { display: '上架商品数', name: 'countProduct', width: 100},
	      					{ display: '近30天销售额', name: 'pay_amount_sum_30_days', width: 100},
			                { display: '近30天订单量', name: 'sub_order_count_30_days', width: 120 },
			                { display: '近30天退货率', name: 'return_rate_30_days', width: 100,render:function(rowdata, rowindex){
						     	if(rowdata.return_rate_30_days==null){
						     		return "0.0%";
						     	}else{
						     		return rowdata.return_rate_30_days;
						     	}
						     }},       
      			/* 		{ display: '合作状态', name: 'mchtStatusDesc', width: 100},
      					{ display: '商家商城状态', name: 'shopStatusDesc', width: 100},
		                { display: '合同到期日期', name: 'status_date', width: 120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.agreementEndDate != null && rowdata.agreementEndDate != ""){
   	   		                   var agreementEndDate = new Date(rowdata.agreementEndDate);
   	  
   	   		                   var agreementEndDateStr = agreementEndDate.format("yyyy-MM-dd");
   	
   	   		                	return agreementEndDateStr;
   		                	}
 
 		                }},
				         { display: '商家等级', name: 'gradeDesc', width: 100},            
				         { display: '配合度', name: 'degreeAdaptabilityDesc', width: 100},
				         { display: '法务风险等级', name: 'degreeAuditriskDesc', width: 100},   */
			         { display: "操作", name: "OPER", width: 100, align: "center", render: function(rowdata, rowindex) {
							var html = [];
							html.push("<a href=\"javascript:watchProduct(" + rowdata.id + ");\">查看</a>"); 
							return html.join("");
						 }},
	                { display: '上线时间', name: "upDate", width: 100, align: "center", render: function(rowdata, rowindex) {
						var html = []; 
					 	var upDate;
            	 	    if (rowdata.upDate!=null){
            	 	    upDate=new Date(rowdata.upDate);
						html.push("<span>"+upDate.format("yyyy-MM-dd")+"</span>"); 
						
            	 	    }
            	 	   return html.join("");
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
       
  };
   
    </script>  
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div id="topmenu"></div>
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-condition">
						<input name="mchtCode" >
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">商家名称：</div>
					<div class="search-td-condition">
						<input name="mchtName">
					</div>
				</div>
				
			<div class="search-td" id="period" style="width: 40%;">
				<div class="search-td-label" style="float: left;width: 20%;">上线日期：</div>
				<div class="l-panel-search-item" >
					<input type="text" id="sign_begin" name="sign_begin" value="" />
					<script type="text/javascript">
						$(function() {
							$("#sign_begin").ligerDateEditor({
								showTime : false,
								labelWidth : 150,
								width : 150,
								labelAlign : 'left'
							});
						});
					</script>
				</div>
				<div class="l-panel-search-item" style="margin-left: 17px;margin-right: 15px;" >~</div>
				<div class="l-panel-search-item">
					<input type="text" id="sign_end" name="sign_end" value=""  />
					<script type="text/javascript">
						$(function() {
							$("#sign_end").ligerDateEditor({
								showTime : false,
								labelWidth : 150,
								width : 150,
								labelAlign : 'left'
							});
						});
					</script>
				</div>
			</div>
				
			</div>
			
			<div class="search-tr">

			
				<div class="search-td">
					<div class="search-td-label">品牌：</div>
					<div class="search-td-condition">
						<input id="productBrandName" name="productBrandName">
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label"  >类目：</div>
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
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label"></div>
					<div class="search-td-condition">
						
					</div> 
				</div>
				 <div class="search-td-search-btn">
					<div id="searchbtn">搜索</div>
				</div>
		</div>
	</div>
	</form>
	   <div id="maingrid" style="margin: 0; padding: 0"></div>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
