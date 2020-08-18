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


		//修改上线时间
		function updateupdate(id) {
			 $.ligerDialog.open({
					height: 400,
					width: 500,
					title: "修改",
					name: "INSERT_WINDOW",
					url: "${pageContext.request.contextPath}/resourceLocationManagement/toEditDate.shtml?id="+id,
					showMax: true,
					showToggle: false,
					showMin: false,
					isResize: true,
					slide: false,
					data: null
			});
		}
		
	  //驳回
	 function shopBatchRejection(ids) {
		 $.ligerDialog.open({
				height: 400,
				width: 500,
				title: "驳回",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/resourceLocationManagement/shopBatchRejection.shtml?ids="+ids,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	//批量通过
	function batchPass(ids){
		   var title="通过";
		    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
		    if (yes) {
			$.ajax({
				type: 'post',
				url: "${pageContext.request.contextPath}/resourceLocationManagement/toBatchPass.shtml?ids=" + ids,
				data: {ids : ids},
				dataType: 'json',
				success: function(data) {
					if(data.returnCode != null && data.returnCode == 0000) {
						$("#searchbtn").click();
					}else {
						commUtil.alertError(data.returnMsg);
					}
				},
				error: function(e) {
					commUtil.alertError("系统异常请稍后再试");
				}
			});
		   }
		  });
	}	
	
	//批量驳回
/* 	function batchRejection(ids){
		   var title="驳回";
		    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
		    if (yes) {
			$.ajax({
				type: 'post',
				url: "${pageContext.request.contextPath}/resourceLocationManagement/toBatchRejection.shtml?ids=" + ids,
				data: {ids : ids},
				dataType: 'json',
				success: function(data) {
					if(data.returnCode != null && data.returnCode == 0000) {
						$("#searchbtn").click();
					}else {
						commUtil.alertError(data.returnMsg);
					}
				},
				error: function(e) {
					commUtil.alertError("系统异常请稍后再试");
				}
			});
		   }
		  });

	} */
	
	
	 
 var listConfig={
      url:"/resourceLocationManagement/shopExamineDate.shtml?pagetype=${pagetype}",
      btnItems:[{text: '批量通过', icon: 'add', click: function(yes) {
		  var data = listModel.gridManager.getSelectedRows();
          if (data.length == 0){
        	  $.ligerDialog.alert('请选择行');
          }else{
             var str = "";
             var params={};
             var auditStatus="";
             
              $(data).each(function ()
              {    
            	  if(str==''){
            		  str = this.id ;
            	  }else{
            		  str += ","+ this.id ;
            	  }
            	  
            	  if(this.auditStatus=='2'){
            		  auditStatus+=this.auditStatus;
            	  }
              });
              
              if(auditStatus.length>0){
            	  $.ligerDialog.alert('已驳回商品不能再通过');
            	  return;
              }else{
            	  batchPass(str);
              }
              
          }
          return;
	  }},
	  {text: '批量驳回', icon: 'delete', click: function(yes) {
		  var data = listModel.gridManager.getSelectedRows();
          if (data.length == 0){
        	  $.ligerDialog.alert('请选择行');
          }else{
             var str = "";
             var params={};
             var auditStatus="";
             
              $(data).each(function ()
              {    
            	  if(str==''){
            		  str = this.id ;
            	  }else{
            		  str += ","+ this.id ;
            	  }
            	  
            	  if(this.auditStatus=='2'){
            		  auditStatus+=this.auditStatus;
            	  }
              });
              if(auditStatus.length>0){
            	  $.ligerDialog.alert('已驳回商品不能再驳回');
            	  return;
              }else{
            	  shopBatchRejection(str);
              }
              
          }
          return;
	  }},
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
						    html.push("<br/>"+rowdata.shopName);
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
				        { display: '店铺评分', name: '', width: 150,render:function(rowdata, rowindex){
						     	var html=[];
						    	html.push("商品描述:<span>"+rowdata.avgProductScore+"</span><br>")
						    	html.push("卖家服务:<span>"+rowdata.avgMchtScore+"</span><br>")
						    	html.push("物流服务:<span>"+rowdata.avgWuliuScore+"</span>")
						    	return html.join(""); 
						     }},
			             { display: '操作', width: 150, render: function(rowdata, rowindex) {
			            	  var html = []; 
			            	  if(rowdata.auditStatus=='0'){
			            		  html.push("<a href=\"javascript:batchPass(" + rowdata.id + ");\">通过</a> | <a href=\"javascript:shopBatchRejection(" + rowdata.id + ");\">驳回</a> | <a href=\"javascript:watchProduct(" + rowdata.id + ");\">查看</a>");
			            	  }else if(rowdata.auditStatus=='1'){ 
			            		  html.push("<a href=\"javascript:shopBatchRejection(" + rowdata.id + ");\">驳回</a> | <a href=\"javascript:watchProduct(" + rowdata.id + ");\">查看</a>");
			            	  }else{
			            		  html.push("<a href=\"javascript:watchProduct(" + rowdata.id + ");\">查看</a>");  
			            	  }
								return html.join(""); 
				         }},
				        { display: '审核状态', name: "auditStatus", width: 100, align: "center", render: function(rowdata, rowindex) {
				        		var name = "无"	
				        	 	var auditDate;
		            	 	    if (rowdata.createDate!=null){
		            	 	    	auditDate=new Date(rowdata.auditDate); 
		            	 	    }
								if(rowdata.auditStatus=='0'){
									return "待审核"
								}
								if(rowdata.auditStatus=='1'){
									if(rowdata.auditName!=null&&rowdata.auditName!=""){
										return "<span >审核通过</span><br><span>"+rowdata.auditName+"</span><br><span>"+auditDate.format("yyyy-MM-dd")+"</span>";
									}else{
										return "<span >审核通过</span><br><span>"+name+"</span><br><span>"+auditDate.format("yyyy-MM-dd")+"</span>";
									}
									
								}
								if(rowdata.auditStatus=='2'){
									if(rowdata.auditName!=null&&rowdata.auditName!=""){
										return "<span >驳回</span><br><span>"+rowdata.auditName+"</span><br><span>"+auditDate.format("yyyy-MM-dd")+"</span>";
									}else{
										return "<span >驳回</span><br><span>"+name+"</span><br><span>"+auditDate.format("yyyy-MM-dd")+"</span>";
									}
								}
							 }},  
			 			 { display: '上线时间', name: "upDate", width: 100, align: "center", render: function(rowdata, rowindex) {
								var html = []; 
							 	var upDate;
		            	 	    if (rowdata.upDate!=null){
		            	 	    upDate=new Date(rowdata.upDate);
								html.push("<span>"+upDate.format("yyyy-MM-dd")+"</span><br><a href=\"javascript:updateupdate(" + rowdata.id + ");\">修改</a>"); 
								
		            	 	    }
		            	 	   return html.join("");
							 }},
						 { display: '报名时间', name: "createDate", width: 100, align: "center", render: function(rowdata, rowindex) {
							 var createDate;
		            	 	    if (rowdata.createDate!=null){
		            	 	    	createDate=new Date(rowdata.createDate);
			            	        return createDate.format("yyyy-MM-dd");
		            	 	    }
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
					<div class="search-td-label" style="float: left;width: 20%;">报名日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="create_begin" name="create_begin" value="" />
						<script type="text/javascript">
							$(function() {
								$("#create_begin").ligerDateEditor({
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
						<input type="text" id="create_end" name="create_end"  value=""  />
						<script type="text/javascript">
							$(function() {
								$("#create_end").ligerDateEditor({
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
					<div class="search-td-label"  >类目：</div>
					<div class="search-td-combobox-condition" >
							<select id="productTypeId" name="productTypeId" style="width: 135px;" >
								<option value="">请选择...</option>
								<c:forEach var="productType" items="${productTypes }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
				 	 </div>
				</div>
			
				<div class="search-td">
					<div class="search-td-label">品牌：</div>
					<div class="search-td-condition">
						<input id="productBrandName" name="productBrandName">
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
					<div class="search-td-label">状态：</div>
					<div class="search-td-condition">
						<select id="auditStatus" name="auditStatus">
							<option value="">请选择</option>
							<option value="0">待审核</option>
							<option value="1">审核通过</option>
							<option value="2">驳回</option>
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
