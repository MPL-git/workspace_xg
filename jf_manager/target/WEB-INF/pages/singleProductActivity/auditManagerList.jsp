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
	function auditOrRetrial(id, str) {
		$.ligerDialog.open({
			height: 500,
			width: 600,
			title: str,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/singleProductActivity/auditOrRetrial.shtml?id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	 
 	var listConfig={
	      url:"/singleProductActivity/getSingleProductActivityList.shtml?type=${type }&flag=1",
	      btnItems:[
			{text: '批量关闭', icon: 'busy', click: function() {
				var data = listModel.gridManager.getSelectedRows();
			    if (data.length == 0)
			  	  $.ligerDialog.alert('请选择行！');
			    else{
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
	      ],
	      listGrid:{ columns: [
							{display:'主图',name:'productPic', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
								return "<div style='padding:3px;'><img style='width:50px;height:50px;' src='${pageContext.request.contextPath}/file_servelt"+rowdata.productPic+"'></div>";
							}},
							{display:'单品活动ID',name:'id', align:'center', isSort:false, width:80},
							{display:'品牌 / 货号/ 商品ID',name:'brandName', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	return (rowdata.brandName==null?'':rowdata.brandName)
	                        		   + "<br/>" + 
	                        		   (rowdata.artNo==null?'':rowdata.artNo)
	                        		   + "<br/>" + 
	                        		   (rowdata.code==null?'':rowdata.code);
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
	                        {display:'秒杀类型',name:'seckillType', hide:${type!=3},align:'center', isSort:false, width:80,render:function(rowdata, rowindex) {
	                        	if(rowdata.seckillType == "1"){
		                        	return "限时秒杀";
	                        	}else{
		                        	return "会场秒杀";
	                        	}
                    		}},
	                        {display:'报名价格',name:'originalPrice', align:'center', isSort:false, width:80},
	                        {display:'吊牌价',name:'tagPriceMin', align:'center', isSort:false, width:80},
	                        {display:'折扣',name:'discount', align:'center', isSort:false, width:80},
	                        {display:'竞品价格',name:'comparePrice', align:'center', isSort:false, width:80},
	                        {display:'商家最低活动价',name:'activityPriceMin', align:'center', isSort:false, width:120},
	                        {display:'近三个月最低活动价',name:'productLogMinSalePrice', align:'center', isSort:false, width:120},
	                        {display:'最新库存数',name:'stockSum', align:'center', isSort:false, width:80},
	                        {display:'往期销量',name:'totalQuantitySum', align:'center', isSort:false, width:80, hide:${role60 } },
	                        {display:'好评率',name:'goodCommentRate', align:'center', isSort:false, width:80 },
	                        {display:'操作',name:'', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href=\"javascript:viewSingleProductActivity(" + rowdata.id + ");\">【查看】</a></br>");
								if(rowdata.isClose == '0') {
									if (rowdata.auditStatus == '0' ){
										html.push("<a href=\"javascript:auditOrRetrial(" + rowdata.id + ", '审核');\">【审核】</a>");	
									}else{
										html.push("<a href=\"javascript:auditOrRetrial(" + rowdata.id + ", '重审');\">【重审】</a>");	
									}
								}
							    return html.join("");
							}},
							{display:'审核状态',name:'auditStatus', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
	                        	if(rowdata.auditStatus == null || rowdata.auditStatus == "" || rowdata.auditStatus == undefined) {
									return "";
								}else{
									if(rowdata.auditStatus == '0') 
										return "待审";
									if(rowdata.auditStatus == '2') 
										return "<span style='color:red;'>初审驳回</span>";
									if(rowdata.auditStatus == '4') 
										return "<span style='color:red;'>排期驳回</span>";
									if(rowdata.auditStatus == '5') 
										return "<span style='color:red;'>复审驳回</span>";
								}
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
	                        {display:'初审专员',name:'firstAuditName', align:'center', isSort:false, width:80, render: function(rowdata, rowindex) {
	                        	if(rowdata.firstAuditName != null && rowdata.firstAuditName != '') 
	                        		return rowdata.firstAuditName;
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
			<div class="search-tr"  > 
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
				<div class="search-td">
					<div class="search-td-label"  >审核状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="auditStatus" name="auditStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="auditStatus" items="${auditStatusList }">
								<c:if test="${auditStatus.statusValue != '1' and auditStatus.statusValue != '3' }">
									<option value="${auditStatus.statusValue }">
										${auditStatus.statusDesc }
									</option>
								</c:if>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<c:if test="${type == '3'}">
					<div class="search-td">
						<div class="search-td-label"  >秒杀类型：</div>
						<div class="search-td-combobox-condition" >
							<select id="seckillType" name="seckillType" style="width: 135px;" >
								<option value="">请选择...</option>
								<option value="1">限时秒杀</option>
								<option value="2">会场秒杀</option>
							</select>
					 	 </div>
					</div>
				</c:if>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
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
				<div class="search-td">
					<div class="search-td-label"  >选择：</div>
					<div class="search-td-combobox-condition" >
						<select id="updateDateFlag" name="updateDateFlag" style="width: 135px;" >
							<option value="between" selected >7天内</option>
							<option value="before">7天后</option>
						</select>
				 	 </div>
				</div>
				
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
	        </div>    
			<div class="search-tr"  >
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
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
