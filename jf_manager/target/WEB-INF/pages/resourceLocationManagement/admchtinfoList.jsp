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
	
	function editMchtinfo(id) {
	$("input[type=button]").attr('disabled',true);
	$.ajax({
		type: 'post',
		url: '${pageContext.request.contextPath}/resourceLocationManagement/editMchtinfoList.shtml',
		data: {id : id},
		dataType: 'json',
		success: function(data) {
			if(data.code != null && data.code == 200) {
				$("table tr").attr("class","l-grid-hd-row");
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
	 
 var listConfig={
      url:"/resourceLocationManagement/admchtinfoListdata.shtml",
      btnItems:[{text: '批量添加',icon: 'add',click: function() {
			var data = listModel.gridManager.getSelectedRows();
       	if (data.length == 0) {
       		commUtil.alertError('请选择行！');
       	}else {
          		var str = "";                         
           	$(data).each(function() {    
         	  		if(str=='') {
         		  		str = this.id;
         	  		}else {
         		  		str += ","+ this.id;
         	  		}
           	});
           	editMchtinfo(str); 
       	}
    }}
      ],
      listGrid:{ columns: [
						
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
						    	html.push('<br/><a href="${previewUrl}'+rowdata.id+'&preview=1" target="_blank">'+rowdata.shopName+'</a>');
		                	}
		                    return html.join("");
		                 }},
			                { display: '品牌', name: '', width: 180 ,render: function(rowdata, rowindex) {
			                	var mchtProductBrandName = rowdata.productbrandName;
			                	var html = [];
								html.push(mchtProductBrandName);
								return html.join("");
			                }},
      					{ display: '合作状态', name: 'statusDesc', width: 200},
		                { display: '合同到期日期', name: '', width: 120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.agreementEndDate != null && rowdata.agreementEndDate != ""){
   	   		                   var agreementEndDate = new Date(rowdata.agreementEndDate);
   	  
   	   		                   var agreementEndDateStr = agreementEndDate.format("yyyy-MM-dd");
   	
   	   		                	return agreementEndDateStr;
   		                	}
 
 		                }},
				         { display: '商家等级', name: 'gradeDesc', width: 100},            
				         { display: '配合度', name: 'degreeadaptabilitydDesc', width: 100},
				         { display: '法务风险等级', name: 'degreeAuditriskDesc', width: 100},  
			         { display: "操作", name: "OPER", width: 100, align: "center", render: function(rowdata, rowindex) {
							var html = [];
							html.push("<input type='button' style='width:70px;margin-top:5px;' name='' onclick='editMchtinfo(" + rowdata.id + ")' value='添加'>");
							return html.join("");
						 }},
		         
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
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div id="topmenu"></div>
		<div class="search-pannel">
			<div class="search-tr">
			<div class="search-td">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-condition">
						<input name="mchtCode" value="">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商家名称：</div>
					<div class="search-td-condition">
						<input name="mchtName">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商家等级：</div>
					<div class="search-td-condition">
						<select id="gradeDesc" name="gradeDesc">
							<option value="">请选择</option>
							<option value="1">KA</option>
							<option value="2">中</option>
							<option value="3">差</option>
							<option value="4">屏</option>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">配合度：</div>
					<div class="search-td-condition">
						<select id="degreeAdaptabilityDesc" name="degreeAdaptabilityDesc">
							<option value="">请选择</option>
							<option value="1">积极</option>
							<option value="2">一般</option>
							<option value="3">差</option>
						</select>
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
						<c:if test="${isCwOrgStatus == 1 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" disabled="disabled" >
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${isCwOrgStatus == 0 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" >
								<option value="">请选择...</option>
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name}</option>
								</c:forEach>
							</select>
						</c:if>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label">法务风险：</div>
					<div class="search-td-condition">
						<select id="auditriskDesc" name="auditriskDesc">
							<option value="">请选择</option>
							<option value="1">高</option>
							<option value="2">中</option>
							<option value="3">低</option>
						</select>
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
