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
	 $(function() {
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left'
		});
	 });
	 
	 //批量关闭
	 function receive(str, strProductId) {		
		 $.ajax({
			 type : 'POST',
			 url : "${pageContext.request.contextPath}/singleProductActivity/updateSingleProductActivityList.shtml",
			 data : {str : str, strProductId : strProductId},
			 dataType : 'json',
			 success : function(data){
				 if(data == null || data.statusCode != 200)
					 commUtil.alertError(data.message);
				 else{
					 $.ligerDialog.success("操作成功",function() {
						 listModel.gridManager.loadData();
					 });
				 }
			 },
			 error : function(e) {
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
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
	
	//查看
	function viewSingleProductActivity(id) {
		$.ligerDialog.open({
			height: 800,
			width: 900,
			title: "审核信息",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/singleProductActivity/showSingleProductActivity.shtml?id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	 
	//审核、重审
	function auditOrRetrial(id, type, str) {
		var flag = "3";
		if(type == '3')
			flag = "1";
		if(type == '1' || type == '2' || type == '4' || type == '5' || type == '6' )
			flag = "2";
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width()*0.75,
			title: str,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/singleProductActivity/scheduleAuditOrRetrial.shtml?id="+id+"&flag="+flag,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//批量审核
	function batchAuditOrRetrial(id, type, title,ids) {
		var flag = "3";
		if(type == '3')
			flag = "1";
		if(type == '1' || type == '2' || type == '4' || type == '5' || type == '6' )
			flag = "2";
		$.ligerDialog.open({
			height: 500,
			width: 600,
			title: title,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/singleProductActivity/batchAuditOrRetrial.shtml?id="+id+"&flag="+flag+"&ids="+ids,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	 //批量调价
	 function batchChangePrice(id,title,ids) {
		 $.ligerDialog.open({
			 height: 300,
			 width: 600,
			 title: title,
			 name: "INSERT_WINDOW",
			 url: "${pageContext.request.contextPath}/singleProductActivity/batchChangePrice.shtml?id="+id+"&ids="+ids,
			 showMax: true,
			 showToggle: false,
			 showMin: false,
			 isResize: true,
			 slide: false,
			 data: null
		 });
	 }
	
	//自定义排期
	function auditOrRetrialCustom(id, str) {
		$.ligerDialog.open({
			height: 500,
			width: 600,
			title: "审核",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/singleProductActivity/scheduleAuditOrRetrialCustom.shtml?id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
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
				 timeout : 30000,
				success : function(data){
					 if(data && data.statusCode == 200){
						 $("#seqNo" + id).parent().append("<span style='color:#009999;'>更改成功</span>");
						 // commUtil.alertError(json.message);
					 }else{
						 // $("#seqNo" + id).parent().append("<span style='color:#009999;'>更改成功</span>");
						 commUtil.alertError(json.message);
					 }
				 },
				 error : function() {
					 //commUtil.alertError("系统异常请稍后再试");
					 $.ligerDialog.error('操作超时，请稍后再试！');
				 }
			 });
		}else{
			$("#seqNo" + id).val("");
			$("#seqNo" + id).parent().append("<span style='color:red;'>请输入正整数</span>");
		}
	}
	function updateUnrealityNum(id) {
		$("#unrealityNum" + id).parent().find("span").remove();
		var unrealityNum = $("#unrealityNum" + id).val();
		var flag = unrealityNum.match(/^[0-9]\d*$/);
		if(unrealityNum != '' && flag != null) {
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/singleProductActivity/updateUnrealityNum.shtml",
				 data : {id : id, unrealityNum : unrealityNum},
				 dataType : 'json',
				 success : function(data){
					 if(data == null || data.statusCode != 200)
						 commUtil.alertError(json.message);
					 else{
						 $("#unrealityNum" + id).parent().append("<span style='color:#009999;'>更改成功</span>");
					 }
				 },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
		}else{
			$("#unrealityNum" + id).val("");
			$("#unrealityNum" + id).parent().append("<span style='color:red;'>请输入非负整数</span>");
		}
	}
	
	
	function schedulingStatus(auditStatus,id){
	      if (auditStatus=='3') {
	    	  $("#"+id+"-label").parent("div").html("<label id='"+ id +"-label'>排期通过</label>");
		  }else if (auditStatus=='1') {
			  $("#"+id+"-label").parent("div").html("<label id='"+ id +"-label'>初审通过</label>");
		  }else {
			  $("#"+id+"-label").parent("div").html("<label id='"+ id +"-label' style='color:red;'>排期驳回</label>"); 
		}
    }
	
	
 	var listConfig={
	      url:"/singleProductActivity/getSingleProductActivityList.shtml?type=${type }&flag=2",
	      btnItems:[
   			{text: '批量关闭', icon: 'busy', click: function() {
   				var data = listModel.gridManager.getSelectedRows();
   			    if (data.length == 0){
   			  	  $.ligerDialog.alert('请选择行！');
   			    }else{
   			       	var str = "";
   			    	var strProductId = "";
   			        $(data).each(function (){  
   			          if(this.isClose == '0') {
   			        	  if(str==''){
   				      		  str = this.id ;
   				      	  }else{
   				      		  str += ","+ this.id ;
   				      	  }  
   			          } 
	   			      if(strProductId == '') {
		        		  strProductId = this.productId ;
			      	  }else{
			      		strProductId += ","+ this.productId ;
			      	  }
   			        });
   			     	receive(str, strProductId);   
   			    }
   			    return;
   			  }
   			}
   			<c:if test="${type != 4}">
   			,
   			{text: '批量审核', icon: 'add', click: function() {
   				var data = listModel.gridManager.getSelectedRows();
   			    if (data.length == 0){
   			  	  $.ligerDialog.alert('请选择行！');
   			    }else{
   			       	var str = ""; 
   			        $(data).each(function (){  
 			          if(str==''){
			      		  str = this.id ;
			      	  }else{
			      		  str += ","+ this.id ;
			      	  } 
   			        });
   			     	batchAuditOrRetrial(0,${type},"批量审核",str);
   			    }
   			    return;
   			  }
   			}
   			</c:if>
              ,
              {text: 'SPOP批量调价', icon: 'add', click: function() {
                      var data = listModel.gridManager.getSelectedRows();
                      if (data.length == 0){
                          $.ligerDialog.alert('请选择行！');
                      }else{
                          var str = "";
                          $(data).each(function (){
                          	console.log(this)
                              if(str==''){
                                  str = this.productId ;
                              }else{
                                  str += ","+ this.productId ;
                              }

                          });
                          batchChangePrice(0,"批量调价",str);
                      }
                      return;
                  }
              }
   	      ],
	      listGrid:{ columns: [
	                        {display:'排序值',name:'seqNo', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
	                        	var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
	                        	return "<input type='text' id='seqNo" + rowdata.id + "' name='seqNo' onChange='updateArtNo(" + rowdata.id + ")' value='" + seqNo + "' >";
	                        }},
	                        {display:'单品活动ID',name:'id', align:'center', isSort:false, width:80},
	                        {display:'主图',name:'productPic', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
	                        	return "<div style='padding:3px;'><img style='width:50px;height:50px;' src='${pageContext.request.contextPath}/file_servelt"+rowdata.productPic+"'></div>";
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
	                        	// debugger;
	                        	html.push((rowdata.shopName==null?'':rowdata.shopName)+" " +(rowdata.isManageSelf==1 && rowdata.mchtType ==1?"<span style='color:red;margin-right:5px;'>自营</span>":'')
	                        			+ "<br/><a href=\"javascript:viewProduct(" + rowdata.productId + ");\">"
	                        			+ (rowdata.productName==null?'':rowdata.productName) + "</a>");
	                        	return html.join("");
	                        }},
	                        {display:'秒杀类型',name:'seckillType', hide:${type!=3},align:'center', isSort:false, width:80,render:function(rowdata, rowindex) {
	                        	if(rowdata.seckillType == "1"){
		                        	return "限时秒杀";
	                        	}else{
		                        	return "会场秒杀";
	                        	}
                    		}},
						    {display:'结算价格',name:'', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
							    if(rowdata.mchtType == '1' ) {
							  		if(rowdata.costPriceMin == rowdata.costPriceMax ) {
								  		return rowdata.costPriceMin;
								  	}
							  		return rowdata.costPriceMin+"-"+rowdata.costPriceMax;
								}
							  	return "--";
						  	}},
						    {display:'最新价格',name:'', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
							  	if(rowdata.salePriceMin == rowdata.salePriceMax ) {
								  	return rowdata.salePriceMin;
							  	}
							  	return rowdata.salePriceMin+"-"+rowdata.salePriceMax;
						    }},
	                        {display:'吊牌价',name:'tagPriceMin', align:'center', isSort:false, width:80},
						    {display:'商城价',name:'', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
						 	   if(rowdata.mallPriceMin == rowdata.mallPriceMax ) {
							    return rowdata.mallPriceMin;
						 		}
							    return rowdata.mallPriceMin+"-"+rowdata.mallPriceMax;
						    }},
						    {display:'毛利',name:'', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
						 	   if(rowdata.grossProfitMin == rowdata.grossProfitMax ) {
							 	 return String(rowdata.grossProfitMin);
						 	   }
						    	 return rowdata.grossProfitMin+"-"+rowdata.grossProfitMax;
						    }},
						    {display:'毛利率',name:'', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
								var html = [];
								html.push(rowdata.grossProfitRateMin+"%");
								if(rowdata.grossProfitRateMin != rowdata.grossProfitRateMax){
									html.push("-");
									html.push(rowdata.grossProfitRateMax+"%");
								};
								return html.join("");
						    }},
					        {display:'svip折扣',name:'svipDiscount', align:'center', isSort:false, width:80},
	                        {display:'折扣',name:'discount', align:'center', isSort:false, width:80},
	                        {display:'竞品价格',name:'comparePrice', align:'center', isSort:false, width:80},
	                        {display:'商家最低活动价',name:'activityPriceMin', align:'center', isSort:false, width:120},
	                        {display:'近三个月最低活动价',name:'productLogMinSalePrice', align:'center', isSort:false, width:120},
	                        {display:'往期销量',name:'totalQuantitySum', align:'center', isSort:false, width:80, hide:${role60 } },
	                        {display:'当前活动销量',name:'quantitySum', align:'center', isSort:false, width:80, hide:${role60 } },
				            {display:'当前销售额',name:'totalPayAmount', align:'center', isSort:false, width:80, hide:${role60 } },
				            {display:'最新库存数',name:'stockSum', align:'center', isSort:false, width:80},
	                        {display:'虚拟数',name:'unrealityNum', align:'center', hide:${type != 3 }, isSort:false, width:100, render:function(rowdata, rowindex) {
	                        	var unrealityNum = rowdata.unrealityNum==null?'':rowdata.unrealityNum;
	                        	return "<input type='text' id='unrealityNum" + rowdata.id + "' name='unrealityNum' onChange='updateUnrealityNum(" + rowdata.id + ")' value='" + unrealityNum + "' >";
	                        }},
	                        {display:'操作',name:'auditOrRetrial', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								var html = [];
								if (rowdata.auditStatus == '1' ){
									html.push("<a href=\"javascript:auditOrRetrial(" + rowdata.id + "," + rowdata.type +", '审核');\">【审核】</a>");	
								}else{
									html.push("<a href=\"javascript:auditOrRetrial(" + rowdata.id + "," + rowdata.type + ", '重审');\">【重排】</a>");	
								}
								if(rowdata.customFlag == 'pass' ) {
									html.push("</br><a href=\"javascript:auditOrRetrialCustom(" + rowdata.id + ");\">【自定义排期】</a>");	
								}
							    return html.join("");
							}},
	                        {display:'商品历史销量',name:'productQuantitySumsDesc', align:'left', isSort:false, width:200, render: function(rowdata, rowindex) {
								var html = [];
			               		if(rowdata.productQuantitySums!=null){
				               		for(var i=0;i<rowdata.productQuantitySums.length;i++){
				               			var beginTime= new Date(rowdata.productQuantitySums[i].beginTime);
				               			if(html.length == 0){
				               				html.push("&nbsp;&nbsp;"+beginTime.format("yyyy-MM-dd hh:mm")+"&nbsp;&nbsp;&nbsp;"+rowdata.productQuantitySums[i].quantitySum);
				               			}else{
				               				html.push("<br/>&nbsp;&nbsp;"+beginTime.format("yyyy-MM-dd hh:mm")+"&nbsp;&nbsp;&nbsp;"+rowdata.productQuantitySums[i].quantitySum);
				               			}
									}
			               		}
								html.push("<a href=\"javascript:viewSingleProductActivity(" + rowdata.id + ");\">【更多】</a>");
							    return html.join("");
							}},
	                        {display:'审核状态',name:'auditStatus', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
	                        	var html=[];
	                        	if(rowdata.auditStatus == null || rowdata.auditStatus == "" || rowdata.auditStatus == undefined) {
	                        		html.push("");
								}else{
									if(rowdata.auditStatus == '1') 
										/* return "初审通过"; */
									   html.push("<label id='"+ rowdata.id+"-label'>初审通过</label>");
									if(rowdata.auditStatus == '3') 
										/* return "排期通过"; */
									html.push("<label id='"+ rowdata.id+"-label'>排期通过</label>");
									if(rowdata.auditStatus == '4') 
										/* return "<span style='color:red;'>排期驳回</span>"; */
									html.push("<label id='"+ rowdata.id+"-label' style='color:red;'>排期驳回</label>");
								}
	                        	 return html.join("");
	                        }},
	                        {display:'是否关闭',name:'isClose', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
	                        	if(rowdata.isClose == '0') {
									return "否";
								}else{
									return "是";
								}
	                        }},
	                        {display:'活动开始时间',name:'beginTime', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	if(rowdata.beginTime == null || rowdata.beginTime == "" || rowdata.beginTime == undefined) {
									return "";
								}else{
									var beginTime = new Date(rowdata.beginTime);
									return beginTime.format("yyyy-MM-dd hh:mm");
								}
	                        }},
	                        {display:'活动结束时间',name:'endTime', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	if(rowdata.endTime == null || rowdata.endTime == "" || rowdata.endTime == undefined) {
									return "";
								}else{
									var endTime = new Date(rowdata.endTime);
									return endTime.format("yyyy-MM-dd hh:mm");
								}
	                        }},
	                        {display:'排期专员',name:'scheduleAuditName', align:'center', isSort:false, width:80, render: function(rowdata, rowindex) {
	                        	if(rowdata.scheduleAuditName != null && rowdata.scheduleAuditName != '') 
	                        		return rowdata.scheduleAuditName;
							}},
							{display:'期望日期',name:'expectedDate', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	if(rowdata.expectedDate == null || rowdata.expectedDate == "" || rowdata.expectedDate == undefined) {
									return "";
								}else{
									var expectedDate = new Date(rowdata.expectedDate);
									return expectedDate.format("yyyy-MM-dd");
								}
	                        }},
							{display:'待审时间',name:'updateDate', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	if(rowdata.updateDate == null || rowdata.updateDate == "" || rowdata.updateDate == undefined) {
									return "";
								}else{
									var updateDate = new Date(rowdata.updateDate);
									return updateDate.format("yyyy-MM-dd hh:mm");
								}
	                        }}
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
				<div class="search-td">
					<div class="search-td-label"  >商品ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="code" name="code" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >品牌：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="brandName" name="brandName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >货号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="artNo" name="artNo" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >店铺名：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="shopName" name="shopName" >
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  style="display: flex;justify-content: space-between">
				<div class="search-td">
					<div class="search-td-label"  >期望日期：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" class="dateEditor" id="expectedDate" name="expectedDate" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >活动开始日期：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" class="dateEditor" id="beginTime" name="beginTime" >
					</div>
				</div>
				<div class="search-td" style="text-align: center">
					<div class="search-td-combobox-condition" style="">
						<select id="startTime" name="startTime" style="height: 23px; width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="seckillTime" items="${seckillTimeList }">
								<option value="${seckillTime.startHours }:${seckillTime.startMin }">
									${seckillTime.startHours }:${seckillTime.startMin }
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td" style="color: red">
					<div class="search-td-label"  >合作类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="mchtType" name="mchtType" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="1">SPOP</option>
							<option value="2">POP</option>
						</select>
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label" style="float:left;">是否自营：</div>
					<div class="search-td-condition" >
						<select id="isManageSelf" name="isManageSelf" style="width: 135px;">
							<option value="">请选择</option>
							<option value="0">非自营</option>
							<option value="1">自营</option>
						</select>
					</div>
				</div>

			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >审核状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="auditStatus" name="auditStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="auditStatus" items="${auditStatusList }">
								<c:if test="${auditStatus.statusValue != '0' and auditStatus.statusValue != '2' and auditStatus.statusValue != '5' }">
									<option value="${auditStatus.statusValue }">
										${auditStatus.statusDesc }
									</option>
								</c:if>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >类目：</div>
					<div class="search-td-combobox-condition" >
						<c:if test="${isCwOrgStatus == 1 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" disabled="disabled" >
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">
										${productType.name }
									</option>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${isCwOrgStatus == 0 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" >
								<option value="">请选择...</option>
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">
										${productType.name }
									</option>
								</c:forEach>
							</select>
						</c:if>
				 	 </div>
				</div>
				<c:if test="${type == '3' }">
					<div class="search-td">
						<div class="search-td-label"  >品牌团：</div>
						<div class="search-td-combobox-condition" >
							<select id="seckillBrandGroupId" name="seckillBrandGroupId" style="width: 135px;" >
								<option value="">请选择...</option>
								<option value="000">全部品牌团</option>
								<option value="999">未加入</option>
								<c:forEach var="seckillBrandGroup" items="${seckillBrandGroupList }">
									<option value="${seckillBrandGroup.id }">
										${seckillBrandGroup.name }
									</option>
								</c:forEach>
							</select>
					 	 </div>
					</div>
				</c:if>
				<div class="search-td" style="position:relative;">
			 		<div class="search-td-label" style="text-align: right: ;" >
			 			<span style="margin-right: 10px;">
				 			<input type="checkbox" value="1" name="seqNo" >
			 			</span>
			 		</div>
			 		<div class="search-td-condition" style="position:absolute; top:-2px;">按序号排序</div>
				</div>
				<c:if test="${type != '3'}">
					<div class="search-td-search-btn" >
						<div id="searchbtn" >搜索</div>
					</div>
				</c:if>
			</div>
			
				<div class="search-tr"  > 
					<c:if test="${type == '3'}">
						<div class="search-td">
							<div class="search-td-label"  >秒杀类型：</div>
							<div class="search-td-combobox-condition" >
								<select id="seckillType" name="seckillType" style="width: 135px;" >
									<option value="1">限时秒杀</option>
									<option value="2">会场秒杀</option>
								</select>
						 	 </div>
						</div>
					</c:if>
					<div class="search-td">
		                <div class="search-td-label" style="float:left;">报名价格：</div>
		                <div class="l-panel-search-item">
		                    <input type="text" id="originalPriceBegin" name="originalPriceBegin" value=""/>
		                </div>
		                <div class="l-panel-search-item">&nbsp;&nbsp;&nbsp;&nbsp;至</div>
		            </div>
		
		            <div class="search-td">
		                <div class="l-panel-search-item">
		                    <input type="text" id="originalPriceEnd" name="originalPriceEnd" value=""/>
		                </div>
		            </div>
		            <div class="search-td">
						<div class="search-td-label">对接人：</div>
						<div class="search-td-condition">
							<select id="platContactStaffId" name="platContactStaffId">
								<c:if test="${isManagement == 1}">
									<option value="" selected="selected">全部商家</option>
								</c:if>
								<option value="${staffID}" <c:if test="${isManagement == 0}">selected="selected"</c:if> >我自己的商家</option>
								<c:forEach items="${sysStaffInfoCustomList }" var="sysStaffInfoCustom">
									<option value="${sysStaffInfoCustom.staffId }">${sysStaffInfoCustom.staffName }</option>
								</c:forEach>
							</select>
						</div>
					</div>
		            <c:if test="${type == '3'}">
						<div class="search-td-search-btn" >
							<div id="searchbtn" >搜索</div>
						</div>
					</c:if>
				</div>
		
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
