<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
 <script type="text/javascript">
$(function(){
	$("#productType1Id").bind('change',function(){
		var productType1Id = $(this).val();
		if(productType1Id){
			$("#productType2Id").empty();
			$("#productTypeId").empty();
			$.ajax({
				url : "${pageContext.request.contextPath}/taobaoke/getProductTypesByParentId.shtml?parentId="+productType1Id,
				secureuri : false,
				dataType : 'json',
				cache : false,
				async : false,
				success : function(data) {
					if ("0000" == data.returnCode) {
						var productTypeList = data.productTypeList;
						var html=[];
						html.push('<option value="">请选择</option>');
						for(var i=0;i<productTypeList.length;i++){
							html.push('<option value="'+productTypeList[i].id+'">'+productTypeList[i].name+'</option>');
						}
						$("#productType2Id").append(html.join(""));
						$("#productTypeId").append('<option value="">请选择</option>');
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			$("#productType2Id").empty();
			$("#productType2Id").append('<option value="">请选择</option>');
			$("#productTypeId").empty();
			$("#productTypeId").append('<option value="">请选择</option>');
		}
	});
	
	$("#productType2Id").bind('change',function(){
		var productType2Id = $(this).val();
		if(productType2Id){
			$("#productTypeId").empty();
			$.ajax({
				url : "${pageContext.request.contextPath}/taobaoke/getProductTypesByParentId.shtml?parentId="+productType2Id,
				secureuri : false,
				dataType : 'json',
				cache : false,
				async : false,
				success : function(data) {
					if ("0000" == data.returnCode) {
						var productTypeList = data.productTypeList;
						var html=[];
						html.push('<option value="">请选择</option>');
						for(var i=0;i<productTypeList.length;i++){
							html.push('<option value="'+productTypeList[i].id+'">'+productTypeList[i].name+'</option>');
						}
						$("#productTypeId").append(html.join(""));
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			$("#productTypeId").empty();
			$("#productTypeId").append('<option value="">请选择</option>');
		}
	});
	 	
}); 

function saveSeqNo(_this,id){
	var seqNo = $(_this).val().trim();
	if(!seqNo){
		seqNo =0
		$.ajax({
			url : "${pageContext.request.contextPath}/taobaoke/thirdPlatformProductInfo/saveNullSeqNo.shtml?id=" + id+"&seqNo="+seqNo,
			secureuri : false,
			dataType : 'json',
			cache : false,
			async : false,
			success : function(data) {
				if ("0000" == data.returnCode) {
					listModel.gridManager.reload();
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}else{
		if(!testNumber(seqNo)){
			commUtil.alertError("排序请输入正整数");
			return false;
		}else{
			$.ajax({
				url : "${pageContext.request.contextPath}/taobaoke/thirdPlatformProductInfo/saveSeqNo.shtml?id=" + id+"&seqNo="+seqNo,
				secureuri : false,
				dataType : 'json',
				cache : false,
				async : false,
				success : function(data) {
					if ("0000" == data.returnCode) {
						listModel.gridManager.reload();
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
	}
}

function viewViolateOrder(id) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "商家违规详情页",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/violateOrder/view.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toAudit(id) {
	$.ligerDialog.open({
		height: 350,
		width: 400,
		title: "审核",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/violateOrder/toAudit.shtml?violateOrderId=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toAuditRemarks(id) {
	$.ligerDialog.open({
		height: 300,
		width: 400,
		title: "驳回原因",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/violateOrder/toAuditRemarks.shtml?violateOrderId=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function changeStatus(id,productId,status) {
	$.ajax({
		url : "${pageContext.request.contextPath}/taobaoke/changeStatus.shtml?productId=" + productId+"&id="+id,
		secureuri : false,
		dataType : 'json',
		cache : false,
		async : false,
		success : function(data) {
			if ("0000" == data.returnCode) {
				listModel.gridManager.reload();
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

function toView(id){
	$.ligerDialog.open({
		height: $(window).height()*0.8,
		width: $(window).width()*0.8,
		title: "查看商品",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/taobaoke/toView.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toEdit(id){
	$.ligerDialog.open({
		height: $(window).height()*0.8,
		width: $(window).width()*0.8,
		title: "编辑商品",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/taobaoke/toEdit.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function batchChangeStatus(ids,productIds,status){
	$.ajax({
		url : "${pageContext.request.contextPath}/taobaoke/batchChangeStatus.shtml?ids="+ids+"&status="+status+"&productIds="+productIds,
		secureuri : false,
		dataType : 'json',
		cache : false,
		async : false,
		success : function(data) {
			if ("0000" == data.returnCode) {
				listModel.gridManager.reload();
				$(".l-grid-header-table").eq(0).find("tr").eq(0).attr("class","l-grid-hd-row");
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

function toBatchSetDate(ids){
	$.ligerDialog.open({
		height: 400,
		width: 600,
		title: "设置上下架时间",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/taobaoke/toBatchSetDate.shtml?ids=" + ids,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}


//批量修改

function batchModification(productIds){
	$.ligerDialog.open({
		height:200,
		width:400,
		title: "批量修改频道",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/taobaoke/toBatchModification.shtml?productIds=" + productIds,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

 var listConfig={
	 btnItems:[
	           { text: '单个抓取', icon: 'add', dtype:'win',  click: itemclick, url: "/taobaoke/toAdd.shtml", seqId:"", width: 1200, height: 800},
	           { text: '批量抓取', icon: 'add', dtype:'win',  click: itemclick, url: "/taobaoke/toBatchAdd.shtml", seqId:"", width: 1600, height: 800},
	           {text: '批量上架', icon: 'add', click: function(yes) {
	      			  var data = listModel.gridManager.getSelectedRows();
                      if (data.length == 0){
                    	  $.ligerDialog.alert('请选择行');
                      }else{
                    	  var str = "";
                    	  var idStr = "";
                          $(data).each(function ()
                          {    
                        	  if(str==''){
                        		  str = this.productId ;
                        	  }else{
                        		  str += ","+ this.productId ;
                        	  }
                        	  if(idStr==''){
                        		  idStr = this.id ;
                        	  }else{
                        		  idStr += ","+ this.id ;
                        	  }
                          });
                          batchChangeStatus(idStr,str,"1");
                      }
                      return;
	      	    }},
	           {text: '批量下架', icon: 'add', click: function(yes) {
	      			  var data = listModel.gridManager.getSelectedRows();
                      if (data.length == 0){
                    	  $.ligerDialog.alert('请选择行');
                      }else{
                    	  var str = "";
                    	  var idStr = "";
                          $(data).each(function ()
                          {    
                        	  if(str==''){
                        		  str = this.productId ;
                        	  }else{
                        		  str += ","+ this.productId ;
                        	  }
                        	  if(idStr==''){
                        		  idStr = this.id ;
                        	  }else{
                        		  idStr += ","+ this.id ;
                        	  }
                          });
                       	  batchChangeStatus(idStr,str,"0");
                      }
                      return;
	      	    }},
	           {text: '批量设置上下架', icon: 'add', click: function(yes) {
	      			  var data = listModel.gridManager.getSelectedRows();
                      if (data.length == 0){
                    	  $.ligerDialog.alert('请选择行');
                      }else{
                    	  var idStr = "";
                          $(data).each(function ()
                          {    
                        	  if(idStr==''){
                        		  idStr = this.id ;
                        	  }else{
                        		  idStr += ","+ this.id ;
                        	  }
                          });
                       	  toBatchSetDate(idStr);
                      }
                      return;
	      	    }},
	      	  	{ text: '选取选品库', icon: 'add', dtype:'win',  click: itemclick, url: "/taobaoke/favoritesList.shtml", seqId:"", width: 1200, height: 800},
	      	  	
	      	  	{text: '批量修改频道', icon: 'add', click: function(yes) {
      			  var data = listModel.gridManager.getSelectedRows();
                          if (data.length == 0){
                        	  $.ligerDialog.alert('请选择行');
                          }else{
                             var str = "";
                             var params={};
                             
                              $(data).each(function ()
                              {    
                            	  if(str==''){
                            		  str = this.productId ;
                            	  }else{
                            		  str += ","+ this.productId ;
                            	  }
                              });
                              
                      /*       $.ligerDialog.confirm('确定删除?', function (yes)
                              {
                            	  if(yes==true){
                                  batchModification(str);
                            	  }
                              });  */
                            batchModification(str);
                          }
                          return;
      			  }}
	      		  
	          ], 
     url:"/taobaoke/thirdPlatformProductInfoData.shtml",
     listGrid:{ columns: [
						{ display: '排序', name:'seqNo',render: function (rowdata, rowindex) {
			            	if(rowdata.seqNo){
				            	return '<input type="text" name="seqNo" value="'+rowdata.seqNo+'" onblur="saveSeqNo(this,'+rowdata.id+');">';
			            	}else{
			            		return '<input type="text" name="seqNo" value="" onblur="saveSeqNo(this,'+rowdata.id+');">';
			            	}
						}},
						{ display: '商品ID', width: 100, render: function (rowdata, rowindex) {
							return rowdata.productCode;		                		
						}},
						{ display: '主图', render: function (rowdata, rowindex) {
							var h = "";
							if(rowdata.pic!=null){
		                      if(rowdata.pic.indexOf("http") >= 0||rowdata.pic.indexOf("com") >= 0){
		                       h += "<img src='"+rowdata.pic+"' width='60' height='60' onclick='viewerPic("+rowdata.id+")'>";
		                      }else{
		                       h += "<img src='${pageContext.request.contextPath}/file_servelt"+rowdata.pic+"' width='60' height='60' onclick='viewerPic("+rowdata.id+")'>";
		                      }
							}
		                    return h;
							}},
		                { display: '淘宝/天猫商品ID', width:120,render: function (rowdata, rowindex) {
							return rowdata.refId;		                	
		                }},
		                { display: '名称',width:'180' ,name:'title'},
		                
		                { display: '频道',width:'180' ,name:'channelName'},
		                
		                { display: '分类',render: function (rowdata, rowindex) {
							return rowdata.productTypeName;		                	
		                }},
		                { display: '销量', name: 'volume'},
		                { display: '一口价', name: 'reservePrice'}, 
		                { display: '淘宝/天猫价', name: 'zkFinalPrice'},
		                { display: '优惠券金额', name: 'couponInfo'},
		                { display: '优惠券剩余', render: function (rowdata, rowindex) {
		                	if(rowdata.couponRemainCount && rowdata.couponTotalCount){
								return rowdata.couponRemainCount+"/"+rowdata.couponTotalCount;		                		
		                	}
						}},
		                { display: '优惠券有效期', render: function (rowdata, rowindex) {
		                	var html = [];
		                	if(rowdata.couponStartTime){
		                		html.push(rowdata.couponStartTime);
		                	}
		                	if(rowdata.couponEndTime){
		                		html.push("至");
		                		html.push(rowdata.couponEndTime);
		                	}
	                		return html.join("");
		                }},
		                { display: '优惠券地址', render: function (rowdata, rowindex) {
	                		return rowdata.couponClickUrl;
		                }},
		                { display: '佣金比例', render: function (rowdata, rowindex) {
		                	if(rowdata.commissionRate){
		                		return rowdata.commissionRate +"%";
		                	}else{
		                		return "";
		                	}
		                }},
		                { display: '类型', render: function (rowdata, rowindex) {
		                	if(rowdata.source == 1){
		                		return "淘宝";
		                	}else if(rowdata.source == 2){
		                		return "天猫";
		                	}
		                }},
		                { display: '状态', render: function (rowdata, rowindex) {
		                	if(rowdata.status == 0){
		                		return "下架";
		                	}else if(rowdata.status == 1){
		                		return "上架";
		                	}
		                }},
		                { display: '更新时间', render: function (rowdata, rowindex) {
		                	if(rowdata.updateDate){
		                		return new Date(rowdata.updateDate).format("yyyy-MM-dd hh:mm:ss");
		                	}else{
		                		return "";
		                	}
		                }},
		                { display: '自动上架时间', render: function (rowdata, rowindex) {
		                	if(rowdata.autoUpDate){
		                		return new Date(rowdata.autoUpDate).format("yyyy-MM-dd");
		                	}else{
		                		return "";
		                	}
		                }},
		                { display: '自动下架时间', render: function (rowdata, rowindex) {
		                	if(rowdata.autoDownDate){
		                		return new Date(rowdata.autoDownDate).format("yyyy-MM-dd");
		                	}else{
		                		return "";
		                	}
		                }},
		                { display: '上下架', render: function (rowdata, rowindex) {
		                	if(rowdata.status == 0){
		                		return '<a href="javascript:;" onclick="changeStatus('+rowdata.id+','+rowdata.productId+','+rowdata.status+');">上架</a>';
		                	}else{
		                		return '<a href="javascript:;" onclick="changeStatus('+rowdata.id+','+rowdata.productId+','+rowdata.status+');">下架</a>';
		                	}
		                }},
		                { display: '操作', render: function (rowdata, rowindex) {
		                	var html = [];
		                	html.push('<a href="javascript:;" onclick="toView('+rowdata.id+');">查看</a>');
		                	if(rowdata.status == 0){
		                		html.push("|");
			                	html.push('<a href="javascript:;" onclick="toEdit('+rowdata.id+');">编辑</a>');
		                	}
	                		return html.join("");
		                }}
		                ],
                 showCheckbox : true,  //不设置默认为 true
                 showRownumber: true //不设置默认为 true
      } ,  							
     container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
  };
 
 function excel(){  
	$("#dataForm").attr("action","${pageContext.request.contextPath}/violateOrder/exportViolateOrder.shtml?pageStatus=1");
	$("#dataForm").submit();
 }
  
</script>
<style type="text/css">
	.l-box-select .l-box-select-table td {
		font-size: 12px;
		line-height: 18px;
	}
</style>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server" method="post">
		<div class="search-pannel">
		<div class="search-tr">
			<div class="search-td">
			<div class="search-td-label" >商品名称：</div>
			<div class="search-td-condition">
				<input type="text" id="title" name="title" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >天猫/淘宝商品ID：</div>
			<div class="search-td-condition">
				<input type="text" id="refId" name="refId" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >商品ID：</div>
			<div class="search-td-condition">
				<input type="text" id="productCode" name="productCode" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">类型：</div>
			<div class="l-panel-search-item" >
				<select id="source" name="source">
					<option value="">请选择</option>
					<option value="1">淘宝</option>
					<option value="2">天猫</option>
				</select>
		 	 </div>
			 </div>
			 
		</div>
		<div class="search-tr">
			<div class="search-td">
				<div class="search-td-label" style="float:left;">一级分类：</div>
				<div class="l-panel-search-item" >
				<select id="productType1Id" name="productType1Id" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach items="${productTypes}" var="productType">
						<option value="${productType.id}">${productType.name}</option>
					</c:forEach>
				</select>
		 	 	</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label" style="float:left;">二级分类：</div>
				<div class="l-panel-search-item" >
				<select id="productType2Id" name="productType2Id" style="width: 150px;">
					<option value="">请选择</option>
				</select>
		 	 	</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label" style="float:left;">三级分类：</div>
				<div class="l-panel-search-item" >
				<select id="productTypeId" name="productTypeId" style="width: 150px;">
					<option value="">请选择</option>
				</select>
		 	 	</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">状态：</div>
			<div class="l-panel-search-item" >
				<select id="status" name="status">
					<option value="">请选择</option>
					<option value="0">下架</option>
					<option value="1">上架</option>
				</select>
		 	 </div>
			 </div>
		</div>
		<div class="search-tr">
			<div class="search-td">
				<div class="search-td-label">商品价格区间：</div>
				<div class="search-td-condition">
						<input name="zkFinalPriceMin" style="width:43%"> <span
							style="width:10%">--</span> <input name="zkFinalPriceMax"
							style="width:43%">
				</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label">优惠券价格区间：</div>
				<div class="search-td-condition">
						<input name="couponAmountMin" style="width:43%"> <span
							style="width:10%">--</span> <input name="couponAmountMax"
							style="width:43%">
				</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label">佣金比例区间：</div>
				<div class="search-td-condition">
						<input name="commissionRateMin" style="width:43%"> <span
							style="width:10%">--</span> <input name="commissionRateMax"
							style="width:43%">
				</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label">月销量：</div>
				<div class="search-td-condition">
					<input name="volume" style="width:43%">
					<span style="width:10%">以上</span>
				</div>
			</div>
		</div>
		<div class="search-tr">
			<div class="search-td">
				<div class="search-td-label"><input type="checkbox" id="seqNoAsc" name="seqNoAsc" value="1" <c:if test="${seqNoAsc == 1}">checked="checked"</c:if>>按排序值排序显示</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">频道：</div>
			<div class="l-panel-search-item" >
				<select id="WetaoChannel" name="WetaoChannel" style="width:150px;" >
					<option value="">请选择</option>
					<c:forEach var="WetaoChannel" items="${WetaoChannels}">
						<option value="${WetaoChannel.id}">${WetaoChannel.channelName}</option>
					</c:forEach> 
				</select>
		 	 </div>
			 </div>
			
			
			<div class="search-td-search-btn">
				<div id="searchbtn">搜索</div>
			</div>
		</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>