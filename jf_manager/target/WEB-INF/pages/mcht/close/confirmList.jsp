<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    
    <link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>	
     <script type="text/javascript">
     function delApply(id){
 	  	$.ligerDialog.confirm('确认取消关店？', function (yes) {
 			if(yes){
 				$.ajax({
 					url : "${pageContext.request.contextPath}/mcht/close/delApply.shtml",
 					type : 'POST',
 					dataType : 'json',
 					cache : false,
 					async : false,
 					data : {
 						id:id
 					},
 					timeout : 30000,
 					success : function(data) {
 						if ("0000" == data.returnCode) {
 							$.ligerDialog.success("取消关店成功!");
 							$("#searchbtn").click();
 						}else{
 							$.ligerDialog.error(data.returnMsg);
 						}
 						
 					},
 					error : function() {
 						$.ligerDialog.error('操作超时，请稍后再试！');
 					}
 				});
 				
 				
 			}
 			
 		});
   }
     
     
     var viewerPictures;
    $(function(){
     	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    		$("#viewer-pictures").hide();
    	}});
    });

     function viewApply(id) {
    		$.ligerDialog.open({
    			height: $(window).height() - 100,
    			width: $(window).width() - 100,
	    		title: "关店详情",
	    		name: "INSERT_WINDOW",
	    		url: "${pageContext.request.contextPath}/mcht/close/viewApply.shtml?id=" + id,
	    		showMax: true,
	    		showToggle: false,
	    		showMin: false,
	    		isResize: true,
	    		slide: false,
	    		data: null
    		});
    	} 
     
     function toEditApply(id) {
    	 $.ajax({
				url : "${pageContext.request.contextPath}/mcht/close/checkAuth.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {
					id:id
				},
				timeout : 30000,
				success : function(data) {
						if ("0000" == data.returnCode) {
							$.ligerDialog.open({
								height: $(window).height()- 100,
								width: $(window).width()- 100,
								title: "关店确认",
								name: "INSERT_WINDOW",
								url: "${pageContext.request.contextPath}/mcht/close/toEditApply.shtml?id=" + id,
								showMax: true,
								showToggle: false,
								showMin: false,
								isResize: true,
								slide: false,
								data: null
							});
						}else{
						$.ligerDialog.error(data.returnMsg);
					}
					
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
    } 
     
     function toEndAgreement(id) {
    		$.ligerDialog.open({
    			height: 350,
    			width: 400,
	    		title: "设置停止协议出具日期",
	    		name: "INSERT_WINDOW",
	    		url: "${pageContext.request.contextPath}/mcht/close/toEndAgreement.shtml?id=" + id,
	    		showMax: true,
	    		showToggle: false,
	    		showMin: false,
	    		isResize: true,
	    		slide: false,
	    		data: null
    		});
    	} 
     
     function editMchtBaseInfo(id) {
			$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width() - 40,
			title: "商家基础资料",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/mchtBaseInfoEdit.shtml?mchtId=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
		}
		
		function editMchtFinanceInfo(id) {
			$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width() - 200,
			title: "商家财务信息",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/mchtFinanceInfoEdit.shtml?mchtId=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
		}
     
		//查看商家联系人
		 function mchtContact(id){
			$.ligerDialog.open({
				height: $(window).height(),
				width: $(window).width(),
				title: "商家联系人",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mcht/mchtContact.shtml?mchtId=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		 }	
		
		function toAddRemarks(id){
			$.ligerDialog.open({
				height: 480,
				width: 640,
				title: "添加备注",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mcht/close/toAddRemarks.shtml?id="+id+"&progressStatus=${progressStatus}",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}
		
		function viewMoreRemarks(id,remarksType){
			$.ligerDialog.open({
				height: $(window).height()*0.6,
				width: $(window).width()*0.6,
				title: "查看备注",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mcht/close/viewMoreRemarksList.shtml?mchtCloseApplicationId=" + id+"&remarksType="+remarksType,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}
		
		//下载退保单
		function toDownLoadPdf(mchtCloseId){
			$.ajax({
				url : "${pageContext.request.contextPath}/mcht/close/toDownLoadBillsPdf.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"mchtCloseId":mchtCloseId},
				timeout : 30000,
				success : function(data) {
					if(data.returnCode == '0000'){
						var filePath = data.filePath;
						//console.log(filePath);
						location.href="${pageContext.request.contextPath}/file_servelt/"+filePath;
						$("#searchbtn").click();
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
		
		//确认已打单
		function confirmOrder(mchtCloseId){
			$.ajax({
				url : "${pageContext.request.contextPath}/mcht/close/confirmOrder.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"mchtCloseId":mchtCloseId},
				timeout : 30000,
				success : function(data) {
					if(data.returnCode == '0000'){
/* 						var filePath = data.filePath;
						console.log(filePath);
						location.href="${pageContext.request.contextPath}/file_servelt/"+filePath; */
						$("#searchbtn").click();
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
		
		
     function viewerMchtPic(mchtProductBrandId,picType){
    		
    		var url;
    		if(picType==1){
    			url="${pageContext.request.contextPath}/mcht/getMchtBrandPicChg.shtml";
    		}
    		if(picType==2){
    			url="${pageContext.request.contextPath}/mcht/getPlatfromAuthPicChg.shtml";
    		}
    		
    		$("#viewer-pictures").empty();
    		viewerPictures.destroy();
    		$.ajax({
    			url : url,
    			type : 'POST',
    			dataType : 'json',
    			cache : false,
    			async : false,
    			data : {mchtProductBrandId:mchtProductBrandId},
    			timeout : 30000,
    			success : function(data) {
    				if(data&&data.length>0){
    					for (var i=0;i<data.length;i++)
    					{
    						$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" alt=""></li>');
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
     
	    var listConfig={
	    		  
	    	      url:"/mcht/close/confirmData.shtml",
	    	      
	    	      listGrid:{ columns: [
		    			          { display: '申请时间', name: 'createDate', width:100, hide:${progressStatus!=0},render:function(rowdata,rowindex){
		    			        	  	if(rowdata.createDate){
		    			                	var createDate=new Date(rowdata.createDate);
		    			                	return createDate.format("yyyy-MM-dd");
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }}, 
		    			          { display: '招商确认时间', name: 'zsConfirmDate', width:100, hide:${progressStatus!=1},render:function(rowdata,rowindex){
		    			        	  	if(rowdata.zsConfirmDate){
		    			                	var zsConfirmDate=new Date(rowdata.zsConfirmDate);
		    			                	return zsConfirmDate.format("yyyy-MM-dd");
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }}, 
		    			          { display: '商品部确认时间', name: 'commodityConfirmDate', width:100, hide:${progressStatus!=2},render:function(rowdata,rowindex){
		    			        	  	if(rowdata.commodityConfirmDate){
		    			                	var commodityConfirmDate=new Date(rowdata.commodityConfirmDate);
		    			                	return commodityConfirmDate.format("yyyy-MM-dd");
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }}, 
		    			          { display: '商家资料确认时间', name: 'mchtArchiveConfirmDate', width:100, hide:${progressStatus!=3},render:function(rowdata,rowindex){
		    			        	  	if(rowdata.mchtArchiveConfirmDate){
		    			                	var mchtArchiveConfirmDate=new Date(rowdata.mchtArchiveConfirmDate);
		    			                	return mchtArchiveConfirmDate.format("yyyy-MM-dd");
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }}, 
		    			          { display: '客服部确认时间', name: 'kfConfirmDate', width:100, hide:${progressStatus!=4},render:function(rowdata,rowindex){
		    			        	  	if(rowdata.kfConfirmDate){
		    			                	var kfConfirmDate=new Date(rowdata.kfConfirmDate);
		    			                	return kfConfirmDate.format("yyyy-MM-dd");
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }}, 
		    			          { display: '财务部确认时间', name: 'cwConfirmDate', width:100, hide:${progressStatus!=5},render:function(rowdata,rowindex){
		    			        	  	if(rowdata.cwConfirmDate){
		    			                	var cwConfirmDate=new Date(rowdata.cwConfirmDate);
		    			                	return cwConfirmDate.format("yyyy-MM-dd");
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }}, 
		    			          { display: '法务部确认时间', name: 'fwConfirmDate', width:100, hide:${progressStatus!=6},render:function(rowdata,rowindex){
		    			        	  	if(rowdata.fwConfirmDate){
		    			                	var fwConfirmDate=new Date(rowdata.fwConfirmDate);
		    			                	return fwConfirmDate.format("yyyy-MM-dd");
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }}, 
		    			          { display: '分管确认时间', name: 'directorConfirmDate', width:100, hide:${progressStatus!=7},render:function(rowdata,rowindex){
		    			        	  	if(rowdata.directorConfirmDate){
		    			                	var directorConfirmDate=new Date(rowdata.directorConfirmDate);
		    			                	return directorConfirmDate.format("yyyy-MM-dd");
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }}, 
		    			          { display: '确认关店时间', name: 'productConfirmDate', width:100, hide:${progressStatus!=8},render:function(rowdata,rowindex){
		    			        	  	if(rowdata.productConfirmDate){
		    			                	var productConfirmDate=new Date(rowdata.productConfirmDate);
		    			                	return productConfirmDate.format("yyyy-MM-dd");
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }}, 
		    			          { display: '操作关店确认时间', name: 'settlementConfirmDate', width:100, hide:${progressStatus!=9},render:function(rowdata,rowindex){
		    			        	  	if(rowdata.settlementConfirmDate){
		    			                	var settlementConfirmDate=new Date(rowdata.settlementConfirmDate);
		    			                	return settlementConfirmDate.format("yyyy-MM-dd");
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }}, 
		    			                
		    			          { display: '申请人', name: 'applyName',width:100 },   
					                { display: '公司信息',  name: 'companyInfo', width: 150 , render: function(rowdata, rowindex){
					  					var html = [];
					  					html.push(rowdata.mchtCode+"<br>");
					  					html.push(rowdata.companyName+"<br>");
					  					html.push(rowdata.shopName);
					  					return html.join("");
				  					}}, 
	    			                { display: '经营品牌', name: 'mchtProductBrands',width:180 },
	    			                { display: '合同到期日期', name: 'agreementEndDate', width:100,render:function(rowdata,rowindex){
		    			        	  	var html=[];
	    			                	if(rowdata.agreementEndDate){
		    			                	var agreementEndDate=new Date(rowdata.agreementEndDate);
		    			                	html.push(agreementEndDate.format("yyyy-MM-dd")+"<br>");
		    			        	  	}else{
		    			        	  		html.push("");
		    			        	  	}
	    			                	if(rowdata.archiveStatus == 0){
	    			                		html.push("未处理");
	    			                	}else if(rowdata.archiveStatus == 1){
	    			                		html.push("已归档");
	    			                	}else if(rowdata.archiveStatus == 2){
	    			                		html.push("驳回");
	    			                	}else if(rowdata.archiveStatus == 4){
	    			                		html.push("不签约");
	    			                	}
	    			                	return html.join("");
		    			           }},
		    			           { display: '查看详情', name: 'detail', width: 80 ,render:function(rowdata,rowindex){
		    			        	   var html = [];
			   						   html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.mchtId + ");\">店铺主信息</a><br>"); 
			   						   html.push("<a href=\"javascript:editMchtFinanceInfo(" + rowdata.mchtId + ");\">财务信息</a><br>"); 
			   						   html.push("<a href=\"javascript:mchtContact(" + rowdata.mchtId + ");\">商家对接人</a><br>"); 
			   						   return html.join("");
	    			                }},
	    			                { display: '预计保证金退还日期', name: 'depositDate', width:140, hide:${progressStatus!='7' && progressStatus!='8'}, render:function(rowdata, rowindex){
		    			        	  	var html=[];
	    			                	if(rowdata.depositDate){
		    			                	var depositDate=new Date(rowdata.depositDate);
		    			                	html.push(depositDate.format("yyyy-MM-dd"));
		    			        	  	}
	    			                	return html.join("");
		    			           }},
		    			           { display: '关店信息', name: 'viewApply', width: 80 ,render:function(rowdata,rowindex){
	    			                	return '<a href="javascript:;" onclick="viewApply('+rowdata.id+')">查看详情</a>';
	    			                }},
	    			                { display: '招商部确认状态', name: 'zsConfirmStatus', width: 90 ,hide:${progressStatus!=0},render:function(rowdata,rowindex){
	    			                	if(rowdata.zsConfirmStatus == 0){
	    			                		return "待确认";
	    			                	}else if(rowdata.zsConfirmStatus == 1){
	    			                		return "已确认";
	    			                	}else if(rowdata.zsConfirmStatus == 2){
	    			                		return "不同意关店";
	    			                	}
	    			                }},
	    			                { display: '确认时间', name: 'zsConfirmDate', width:100, hide:${progressStatus!=0},render:function(rowdata,rowindex){
		    			        	  	if(rowdata.zsConfirmDate){
		    			                	var zsConfirmDate=new Date(rowdata.zsConfirmDate);
		    			                	return zsConfirmDate.format("yyyy-MM-dd");
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }},
	    			                { display: '招商部备注', name: 'zsRemarks', width:350, hide:${progressStatus!=0},render:function(rowdata,rowindex){
		    			        	  	if(rowdata.zsRemarks){
		    			        	  		var zsRemarksDate = new Date(rowdata.zsRemarksDate);
		    			                	return "["+zsRemarksDate.format("yyyy-MM-dd hh:mm")+"]"+rowdata.zsRemarks+'<a href="javascript:;" onclick="viewMoreRemarks('+rowdata.id+',0)">【查看更多】</a>';
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }},
		    			           { display: '商品部确认状态',   name: 'commodityConfirm' , width: 90,hide:${progressStatus!=1},render:function(rowdata,rowindex){
	    							    if(rowdata.mchtInfoStatus == 1 && rowdata.activityStatus == 1 && rowdata.commodityStatus == 1 && rowdata.marketingStatus == 1){
	    							    	return "已确认";
	    							    }else{
	    							    	return "待确认";
	    							    }
	    			                }
	    			                },
	    			                { display: '活动结束',   name: 'activityStatus' , width: 80,hide:${progressStatus!=1},render:function(rowdata,rowindex){
	    							    if(rowdata.activityStatus == 1){
	    							    	return "已结束";
	    							    }else{
	    							    	return "未结束";
	    							    }
	    			                }
	    			                },
	    			                { display: '商品全下架',   name: 'commodityStatus' , width: 80,hide:${progressStatus!=1},render:function(rowdata,rowindex){
	    							    if(rowdata.commodityStatus == 1){
	    							    	return "已下架";
	    							    }else{
	    							    	return "未下架";
	    							    }
	    			                }
	    			                },
	    			                { display: '取消营销安排',   name: 'marketingStatus' , width: 80,hide:${progressStatus!=1},render:function(rowdata,rowindex){
	    							    if(rowdata.marketingStatus == 1){
	    							    	return "已取消";
	    							    }else{
	    							    	return "未取消";
	    							    }
	    			                }
	    			                },
	    			                { display: '商品部备注',   name: 'commodityRemarks' , width: 350,hide:${progressStatus!=1},render:function(rowdata,rowindex){
	    							    if(rowdata.commodityRemarks){
	    							    	var commodityRemarksDate = new Date(rowdata.commodityRemarksDate);
		    			                	return "["+commodityRemarksDate.format("yyyy-MM-dd hh:mm")+"]"+rowdata.commodityRemarks+'<a href="javascript:;" onclick="viewMoreRemarks('+rowdata.id+',1)">【查看更多】</a>';
	    							    }else{
	    							    	return "";
	    							    }
	    			                }
	    			                },
	    			                { display: '商家资料确认',   name: 'mchtAchive' , width: 80,hide:${progressStatus!=2},render:function(rowdata,rowindex){
	    							    if(rowdata.mchtContractStatus == 1 && rowdata.mchtArchiveStatus == 1){
	    							    	return "已确认";
	    							    }else{
	    							    	return "待确认";
	    							    }
	    			                }
	    			                },
	    			                { display: '挂起原因',   name: 'fwHangUpReason' , width: 250,hide:${progressStatus!=2},render:function(rowdata,rowindex){
	    							    if(rowdata.fwHangUpReason){
	    							    	return "您寄回的资质尚未完善，请补齐。未齐全的资质事宜请联系你的招商对接人";
	    							    }else{
	    							    	return "";
	    							    }
	    			            	      }
	    			                },
	    			                { display: '商家资料备注',   name: 'mchtArchiveRemarks' , width: 250,hide:${progressStatus!=2},render:function(rowdata,rowindex){
	    							    if(rowdata.mchtArchiveRemarks){
	    							    	var mchtArchiveRemarksDate = new Date(rowdata.mchtArchiveRemarksDate);
		    			                	return "["+mchtArchiveRemarksDate.format("yyyy-MM-dd hh:mm")+"]"+rowdata.mchtArchiveRemarks+'<a href="javascript:;" onclick="viewMoreRemarks('+rowdata.id+',2)">【查看更多】</a>';
	    							    }else{
	    							    	return "";
	    							    }
	    			                }
	    			                },
						            { display: '客服部确认状态', name: "clientConfirm",width:80 , hide:${progressStatus!=3},render: function(rowdata, rowindex) {
								  if(rowdata.orderConfirmStatus == 1 && rowdata.serviceOrderConfirmStatus == 1 && rowdata.appealOrderConfirmStatus == 1 && rowdata.interventionOrderConfirmStatus == 1){
									  return "已确认";
								  }else{
									  return "待确认";
								  }
							      }},
						          { display: '订单已完结', name: "orderConfirmStatus",width:80 , hide:${progressStatus!=3},render: function(rowdata, rowindex) {
								  if(rowdata.orderConfirmStatus == 1){
									  return "已确认";
								  }else{
									  return "待确认";
								  }
							      }},
						          { display: '售后已完结', name: "serviceOrderConfirmStatus",width:80 , hide:${progressStatus!=3},render: function(rowdata, rowindex) {
								  if(rowdata.serviceOrderConfirmStatus == 1){
									  return "已确认";
								  }else{
									  return "待确认";
								  }
							      }},
						          { display: '投诉单已完结', name: "appealOrderConfirmStatus",width:80 , hide:${progressStatus!=3},render: function(rowdata, rowindex) {
								  if(rowdata.appealOrderConfirmStatus == 1){
									  return "已确认";
								  }else{
									  return "待确认";
								  }
							      }},
						          { display: '介入单已完结', name: "interventionOrderConfirmStatus",width:80 , hide:${progressStatus!=3},render: function(rowdata, rowindex) {
								  if(rowdata.interventionOrderConfirmStatus == 1){
									  return "已确认";
								  }else{
									  return "待确认";
								  }
							      }},
						          { display: '最后一单/最后一单发货时间/三包期', name: "lastOrder",width:200 , hide:${progressStatus!=3},render: function(rowdata, rowindex) {
								  var html=[];
								  if(rowdata.subOrderCode){
									  html.push(rowdata.subOrderCode+"<br>");
								  }
								  if(rowdata.deliveryDate){
									  var deliveryDate=new Date(rowdata.deliveryDate);
									  html.push(deliveryDate.format("yyyy-MM-dd")+"<br>");
								  }
								  if(rowdata.threePackagePeriod){
									  html.push(rowdata.threePackagePeriod);
								  }
								  return html.join("");
							      }},
    				              	{ display: '挂起原因', name: "kfHangUpReason",width:150 , hide:${progressStatus!=3},render: function(rowdata, rowindex) {
    		 		                	if(rowdata.kfHangUpReason == 1){
    		 		                		return "<label id='"+rowdata.id+"-label'>当前您还有订单未完成，审核暂停</label>";
    		 		                	}else if(rowdata.kfHangUpReason == 2){
											return "<label id='"+rowdata.id+"-label'>当前店铺还有介入单未完成，审核暂停</label>";
										}else if(rowdata.kfHangUpReason == 3){
    		 		                		return "<label id='"+rowdata.id+"-label'>当前存在订单三包期未过，审核暂停</label>";
											}
											return "<label id='" + rowdata.id + "-label'></label>"
    				              	}},
    				              	{ display: '客服部备注', name: "kfRemarks",width:250 , hide:${progressStatus!=3},render: function(rowdata, rowindex) {

    		 		                	if(rowdata.kfRemarks){
    		 		                		var kfRemarksDate = new Date(rowdata.kfRemarksDate);
		    			                	return "<div class='kfRemark'>["+kfRemarksDate.format("yyyy-MM-dd hh:mm")+"]<span class='kfRemarks'>"+rowdata.kfRemarks+'</span><a href="javascript:;" onclick="viewMoreRemarks('+rowdata.id+',3)">【查看更多】</a></div>';
    		 		                	}else{
    		 		                		return "<div class='kfRemark'></div>";
    		 		                	}
    				              	}},
    				              	{ display: '财务部确认状态', name: "cwConfirm",width:100 , hide:${progressStatus!=4},render: function(rowdata, rowindex) {
    		 		                	if(rowdata.paymentOfGoodsConfirm == 1){
    		 		                		return "已确认";
    		 		                	}else{
    		 		                		return "待确认";
    		 		                	}
    				              	}},
    				              	{ display: '货款已结清', name: "cwConfirm",width:80 , hide:${progressStatus!=4},render: function(rowdata, rowindex) {
    		 		                	if(rowdata.paymentOfGoodsConfirm == 1){
    		 		                		return "已确认";
    		 		                	}else{
    		 		                		return "待确认";
    		 		                	}
    				              	}},
    				              	{ display: '商家保证金余额', name: "",width:100 , hide:${progressStatus!=4},render: function(rowdata, rowindex) {
    		 		                	if(rowdata.paymentOfGoodsConfirm == 1){
    		 		                		return "已确认";
    		 		                	}else{
    		 		                		return "待确认";
    		 		                	}
    				              	}},
    				              	{ display: '财务部备注', name: "cwRemarks",width:350 , hide:${progressStatus!=4},render: function(rowdata, rowindex) {
    		 		                	if(rowdata.cwRemarks){
    		 		                		var cwRemarksDate = new Date(rowdata.cwRemarksDate);
		    			                	return "["+cwRemarksDate.format("yyyy-MM-dd hh:mm")+"]"+rowdata.cwRemarks+'<a href="javascript:;" onclick="viewMoreRemarks('+rowdata.id+',4)">【查看更多】</a>';
    		 		                	}else{
    		 		                		return "";
    		 		                	}
    				              	}},
    				              	{ display: '法务部确认状态', name: "fwConfirm",width:100 , hide:${progressStatus!=5},render: function(rowdata, rowindex) {
    		 		                	if(rowdata.endCooperationAgreement == 1){
    		 		                		return "已确认";
    		 		                	}else{
    		 		                		return "待确认";
    		 		                	}
    				              	}},
    				              	{ display: '签署终止协议', name: "fwConfirm",width:100 , hide:${progressStatus!=5},render: function(rowdata, rowindex) {
    		 		                	if(rowdata.endCooperationAgreement == 1){
    		 		                		return "已确认";
    		 		                	}else{
    		 		                		return "待确认";
    		 		                	}
    				              	}},
    				              	{ display: '协议出具日期', name: 'agreementIssueDate', width:80, hide:${progressStatus!=5},render:function(rowdata,rowindex){
		    			        	  	if(rowdata.agreementIssueDate){
		    			                	var agreementIssueDate=new Date(rowdata.agreementIssueDate);
		    			                	return agreementIssueDate.format("yyyy-MM-dd");
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }},
    				              	{ display: '预计保证金退还日期', name: 'depositDate', width:110, hide:${progressStatus!=5},render:function(rowdata,rowindex){
    				              		if(rowdata.depositDate){
		    			                	var depositDate = new Date(rowdata.depositDate);
		    			                	var now = new Date();
		    			                	var diff = (depositDate - now) / (1000 * 60 * 60 * 24);
		    			                	if(parseInt(diff)<=15){
		    			                		return '<span style="color:red;">'+depositDate.format("yyyy-MM-dd")+'</span>';
		    			                	}else{
			    			                	return depositDate.format("yyyy-MM-dd");
		    			                	}
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }},
		    			           { display: '法务部备注', name: "fwRemarks",width:350 , hide:${progressStatus!=5},render: function(rowdata, rowindex) {
   		 		                	if(rowdata.fwRemarks){
	   		 		                	var fwRemarksDate = new Date(rowdata.fwRemarksDate);
	    			                	return "["+fwRemarksDate.format("yyyy-MM-dd hh:mm")+"]"+rowdata.fwRemarks+'<a href="javascript:;" onclick="viewMoreRemarks('+rowdata.id+',5)">【查看更多】</a>';
   		 		                	}else{
   		 		                		return "";
   		 		                	}
   				              		}},
	   				              	{ display: '店铺状态', name: "mchtInfoStatus",width:80 , hide:${progressStatus!=6},render: function(rowdata, rowindex) {
			 		                	if(rowdata.mchtInfoStatus == 0){
			 		                		return "正常";
			 		                	}else if(rowdata.mchtInfoStatus == 1){
			 		                		return "暂停";
			 		                	}
					              	}},
	   				              	{ display: '协议出具日期', name: 'agreementIssueDate', width:100, hide:${progressStatus!=6},render:function(rowdata,rowindex){
		    			        	  	if(rowdata.agreementIssueDate){
		    			                	var agreementIssueDate=new Date(rowdata.agreementIssueDate);
		    			                	return agreementIssueDate.format("yyyy-MM-dd");
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }},
					              	{ display: '预计保证金退还日期', name: 'depositDate', width:110, hide:${progressStatus!=6},render:function(rowdata,rowindex){
					              		if(rowdata.depositDate){
		    			                	var depositDate = new Date(rowdata.depositDate);
		    			                	var now = new Date();
		    			                	var diff = (depositDate - now) / (1000 * 60 * 60 * 24);
		    			                	if(parseInt(diff)<=15){
		    			                		return '<span style="color:red;">'+depositDate.format("yyyy-MM-dd")+'</span>';
		    			                	}else{
			    			                	return depositDate.format("yyyy-MM-dd");
		    			                	}
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }},
	   				              	{ display: '分管确认关店', name: "directorConfirm",width:100 , hide:${progressStatus!=6},render: function(rowdata, rowindex) {
			 		                	if(rowdata.directorConfirmStatus == 1){
			 		                		return "已确认";
			 		                	}else{
			 		                		return "待确认";
			 		                	}
					              	}},
					              	{ display: '店铺状态', name: "mchtInfoStatus",width:80 , hide:${progressStatus!=7},render: function(rowdata, rowindex) {
		 		                		return rowdata.statusDesc;
					              	}},
									{ display: '结算审核状态', name: 'settlementAuditStatus', width: 100 ,hide:${progressStatus!=8},render:function(rowdata,rowindex){
										if(rowdata.settlementAuditStatus == 1){
			 		                		return "已确认";
			 		                	}else{
			 		                		return "待确认";
			 		                	}
	    			                }},
									{ display: '需退还保证金', name: 'needReturnDeposit', width: 100 ,hide:${progressStatus!=8},render:function(rowdata,rowindex){
										if(rowdata.payAmt){
											return rowdata.payAmt;
										}else{
											return "";
										}
	    			                }},
	    			             /*   { display: '结算审核状态', name: 'settlementAuditStatus', width: 100 ,hide:${progressStatus!=9},render:function(rowdata,rowindex){
										if(rowdata.settlementAuditStatus == 1){
			 		                		return "已确认";
			 		                	}else{
			 		                		return "待确认";
			 		                	}
	    			                }},*/
	    			                { display: '预计保证金退还日期', name: 'depositDate', width:110, hide:${progressStatus!=9},render:function(rowdata,rowindex){
	    			                	if(rowdata.depositDate){
		    			                	var depositDate = new Date(rowdata.depositDate);
		    			                	var now = new Date();
		    			                	var diff = (depositDate - now) / (1000 * 60 * 60 * 24);
		    			                	if(parseInt(diff)<=15){
		    			                		return '<span style="color:red;">'+depositDate.format("yyyy-MM-dd")+'</span>';
		    			                	}else{
			    			                	return depositDate.format("yyyy-MM-dd");
		    			                	}
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }},
		    			           { display: '需退还保证金', name: 'needReturnDeposit', width: 100 ,hide:${progressStatus!=9},render:function(rowdata,rowindex){
		    			        	   if(rowdata.payAmt){
											return rowdata.payAmt;
										}else{
											return "";
										}
	    			                }},
	    			                { display: '保证金结清日期', name: 'depositReturnDate', width:110, hide:${progressStatus!=9},render:function(rowdata,rowindex){
		    			        	  	if(rowdata.depositReturnDate){
		    			                	var depositReturnDate=new Date(rowdata.depositReturnDate);
		    			                	return depositReturnDate.format("yyyy-MM-dd");
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }},
		    			           { display: '是否已打单', name: 'comfirmBillsStatus', width:110, hide:${progressStatus!=9},render:function(rowdata,rowindex){
		    			        	  	if(rowdata.comfirmBillsStatus=='1' ){
		    			        	  		return "是";
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }},
		    			           
    				              	{ display: "操作", name: "op",width:100 , render: function(rowdata, rowindex) {
    				              		var html = [];
    				              		var progressStatus = ${progressStatus};
    				              		if(progressStatus==rowdata.progressStatus){
											if (rowdata.progressStatus !=10) {
												html.push("<a id='" + "operate-" + rowdata.id + "' href=\"javascript:toEditApply(" + rowdata.id + ");\">操作</a><br>");
											}
    				              		}
    				              		if(rowdata.progressStatus == 0 || rowdata.progressStatus == 1 || rowdata.progressStatus == 2 || rowdata.progressStatus == 3 || rowdata.progressStatus == 4 || rowdata.progressStatus == 5){
	    				              		html.push("<a id='"+"remark-"+rowdata.id+"' href=\"javascript:toAddRemarks(" + rowdata.id + ");\">添加备注</a><br>");
    				              		}
    				              		if(rowdata.progressStatus == 5){
    				              			
    				              			html.push("<a href=\"javascript:toEndAgreement(" + rowdata.id + ");\">设置协议出具日期</a>");
    				              	/* 		if(rowdata.agreementIssueDate){
	    				              			html.push("<a href=\"javascript:toEndAgreement(" + rowdata.id + ");\">重新生成合作终止协议</a>");
    				              			}else{
	    				              			html.push("<a href=\"javascript:toEndAgreement(" + rowdata.id + ");\">生成合作终止协议</a>");
    				              			} */
    				              		}
    				              		if(rowdata.progressStatus == 10 && rowdata.comfirmBillsStatus=='0' ){
    				              			var billsPath = rowdata.billsPath;
    				              			//html.push('<a href="${pageContext.request.contextPath}/file_servelt'+billsPath+'" target="_blank">下载退保单</a>')
    				              			html.push("<a href=\"javascript:toDownLoadPdf(" + rowdata.id + ");\">下载退保单</a>");
    				              			html.push("<br><a href=\"javascript:confirmOrder(" + rowdata.id + ");\">确认已打单</a>");
    				              		}
    				              		if(rowdata.progressStatus == 10 && rowdata.comfirmBillsStatus=='1' ){
    				              			html.push("<a href=\"javascript:toDownLoadPdf(" + rowdata.id + ");\">下载退保单</a><br>");
    				              			html.push('确认已打单');
    				              		}
    				              
    				              		return html.join("");
    				              	}}
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
     //客服部备注刷新
     function updateRemark(id,remark) {
     	     var remarkDate = new Date();
			 $("#remark-" + id).closest("tr").find(".kfRemark").parent("div").html("<div class='kfRemark'>["+remarkDate.format("yyyy-MM-dd hh:mm")+"]<span class='kfRemarks'>"+remark+'</span><a href="javascript:;" onclick="viewMoreRemarks('+id+',3)">【查看更多】</a></div>');
	 }
     //挂起原因刷新
	 function  confirmkfHangUpReason(id,hangUpReason) {
     	if(hangUpReason == 1) {
			$("#" + id + "-label").parent("div").html("<label id='" + id + "-label'>当前您还有订单未完成，审核暂停</label>");
		}else if (hangUpReason == 2){
			$("#" + id + "-label").parent("div").html("<label id='" + id + "-label'>当前店铺还有介入单未完成，审核暂停</label>");
		}else if (hangUpReason == 3){
			$("#" + id + "-label").parent("div").html("<label id='" + id + "-label'>当前存在订单三包期未过，审核暂停</label>");
		}else {
			$("#" + id + "-label").parent("div").html("<label id='" + id + "-label'></label>");
		}
     }


	 </script>
  </head>
  
  <body>
    <div class="l-loading" style="display: block" id="pageloading"></div>
	  <div id="toptoolbar"></div>
  
	  <form id="dataForm" runat="server">
	  	<input type="hidden" name="delApply" id="delApply" value="${delApply}">
	  	<input type="hidden" name="progressStatus" id="progressStatus" value="${progressStatus}">
		<div class="search-pannel">
				<div class="search-tr"  >
				<c:if test="${progressStatus == 0}">
					<div class="search-td">
						<div class="search-td-label" style="float:left;width: 20%;">申请时间：</div>
						<div class="l-panel-search-item">
							<input type="text" id="create_date_begin" name="create_date_begin" />
							<script type="text/javascript">
								$(function() {
									$("#create_date_begin").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
						<div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
					</div>
					<div class="search-td">
						<div class="l-panel-search-item">
							<input type="text" id="create_date_end" name="create_date_end" />
							<script type="text/javascript">
								$(function() {
									$("#create_date_end").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
					</div>
			 	</c:if>
				<c:if test="${progressStatus == 1}">
					<div class="search-td">
						<div class="search-td-label" style="float:left;width: 20%;">招商确认时间：</div>
						<div class="l-panel-search-item">
							<input type="text" id="zs_confirm_date_begin" name="zs_confirm_date_begin" />
							<script type="text/javascript">
								$(function() {
									$("#zs_confirm_date_begin").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
						<div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
					</div>
					<div class="search-td">
						<div class="l-panel-search-item">
							<input type="text" id="zs_confirm_date_end" name="zs_confirm_date_end" />
							<script type="text/javascript">
								$(function() {
									$("#zs_confirm_date_end").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
					</div>
			 	</c:if>
				<c:if test="${progressStatus == 2}">
					<div class="search-td">
						<div class="search-td-label" style="float:left;width: 20%;">商品部确认时间：</div>
						<div class="l-panel-search-item">
							<input type="text" id="commodity_confirm_date_begin" name="commodity_confirm_date_begin" />
							<script type="text/javascript">
								$(function() {
									$("#commodity_confirm_date_begin").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
						<div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
					</div>
					<div class="search-td">
						<div class="l-panel-search-item">
							<input type="text" id="commodity_confirm_date_end" name="commodity_confirm_date_end" />
							<script type="text/javascript">
								$(function() {
									$("#commodity_confirm_date_end").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
					</div>
			 	</c:if>
				<c:if test="${progressStatus == 3}">
					<div class="search-td">
						<div class="search-td-label" style="float:left;width: 20%;">商家资料确认时间：</div>
						<div class="l-panel-search-item">
							<input type="text" id="mcht_archive_confirm_date_begin" name="mcht_archive_confirm_date_begin" />
							<script type="text/javascript">
								$(function() {
									$("#mcht_archive_confirm_date_begin").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
						<div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
					</div>
					<div class="search-td">
						<div class="l-panel-search-item">
							<input type="text" id="mcht_archive_confirm_date_end" name="mcht_archive_confirm_date_end" />
							<script type="text/javascript">
								$(function() {
									$("#mcht_archive_confirm_date_end").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
					</div>
			 	</c:if>
				<c:if test="${progressStatus == 4}">
					<div class="search-td">
						<div class="search-td-label" style="float:left;width: 20%;">客服部确认时间：</div>
						<div class="l-panel-search-item">
							<input type="text" id="kf_confirm_date_begin" name="kf_confirm_date_begin" />
							<script type="text/javascript">
								$(function() {
									$("#kf_confirm_date_begin").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
						<div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
					</div>
					<div class="search-td">
						<div class="l-panel-search-item">
							<input type="text" id="kf_confirm_date_end" name="kf_confirm_date_end" />
							<script type="text/javascript">
								$(function() {
									$("#kf_confirm_date_end").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
					</div>
			 	</c:if>
				<c:if test="${progressStatus == 5}">
					<div class="search-td">
						<div class="search-td-label" style="float:left;width: 20%;">财务部确认时间：</div>
						<div class="l-panel-search-item">
							<input type="text" id="cw_confirm_date_begin" name="cw_confirm_date_begin" />
							<script type="text/javascript">
								$(function() {
									$("#cw_confirm_date_begin").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
						<div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
					</div>
					<div class="search-td">
						<div class="l-panel-search-item">
							<input type="text" id="cw_confirm_date_end" name="cw_confirm_date_end" />
							<script type="text/javascript">
								$(function() {
									$("#cw_confirm_date_end").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
					</div>
			 	</c:if>
				<c:if test="${progressStatus == 6}">
					<div class="search-td">
						<div class="search-td-label" style="float:left;width: 20%;">法务部确认时间：</div>
						<div class="l-panel-search-item">
							<input type="text" id="fw_confirm_date_begin" name="fw_confirm_date_begin" />
							<script type="text/javascript">
								$(function() {
									$("#fw_confirm_date_begin").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
						<div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
					</div>
					<div class="search-td">
						<div class="l-panel-search-item">
							<input type="text" id="fw_confirm_date_end" name="fw_confirm_date_end" />
							<script type="text/javascript">
								$(function() {
									$("#fw_confirm_date_end").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
					</div>
			 	</c:if>
				<c:if test="${progressStatus == 7}">
					<div class="search-td">
						<div class="search-td-label" style="float:left;width: 20%;">分管确认时间：</div>
						<div class="l-panel-search-item">
							<input type="text" id="director_confirm_date_begin" name="director_confirm_date_begin" />
							<script type="text/javascript">
								$(function() {
									$("#director_confirm_date_begin").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
						<div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
					</div>
					<div class="search-td">
						<div class="l-panel-search-item">
							<input type="text" id="director_confirm_date_end" name="director_confirm_date_end" />
							<script type="text/javascript">
								$(function() {
									$("#director_confirm_date_end").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
					</div>
			 	</c:if>
				<c:if test="${progressStatus == 8}">
					<div class="search-td">
						<div class="search-td-label" style="float:left;width: 20%;">确认关店日期：</div>
						<div class="l-panel-search-item">
							<input type="text" id="product_confirm_date_begin" name="product_confirm_date_begin" />
							<script type="text/javascript">
								$(function() {
									$("#product_confirm_date_begin").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
						<div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
					</div>
					<div class="search-td">
						<div class="l-panel-search-item">
							<input type="text" id="product_confirm_date_end" name="product_confirm_date_end" />
							<script type="text/javascript">
								$(function() {
									$("#product_confirm_date_end").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
					</div>
			 	</c:if>
				<c:if test="${progressStatus == 9}">
					<div class="search-td">
						<div class="search-td-label" style="float:left;width: 20%;">操作关店确认时间：</div>
						<div class="l-panel-search-item">
							<input type="text" id="settlement_confirm_date_begin" name="settlement_confirm_date_begin" />
							<script type="text/javascript">
								$(function() {
									$("#settlement_confirm_date_begin").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
						<div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
					</div>
					<div class="search-td">
						<div class="l-panel-search-item">
							<input type="text" id="settlement_confirm_date_end" name="settlement_confirm_date_end" />
							<script type="text/javascript">
								$(function() {
									$("#settlement_confirm_date_end").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
					</div>
			 	</c:if>
				 	<div class="search-td">
					<div class="search-td-label">申请人:</div>
					<div class="search-td-condition" >
						<input type="text" id="appleName" name="appleName" >
					</div>
					</div>
				
				 	<div class="search-td">
					<div class="search-td-label">名称:</div>
					<div class="search-td-condition" >
						<input type="text" id="mchtName" name="mchtName" >
					</div>
					</div>
				</div>
				
				<div class="search-tr"  >
					<div class="search-td">
					<div class="search-td-label">商家序号:</div>
					<div class="search-td-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
					</div>
					
					<div class="search-td">
						<div class="search-td-label">确认状态:</div>
						<div class="search-td-condition" >
							<select  id="confirmStatus" name="confirmStatus" class="querysel">
								<option value="">请选择</option>
								<option value="0" selected="selected">待确认</option>
								<option value="1">已确认</option>
								<c:if test="${progressStatus == 0}">
								<option value="2">不同意关店</option>
								</c:if>
							</select>
						</div>
					</div>
					<c:if test="${progressStatus == 0 || progressStatus == 1 || progressStatus == 2 || progressStatus == 3 || progressStatus == 4 || progressStatus == 5}">
					<div class="search-td">
						<div class="search-td-label">主营类目:</div>
						<div class="search-td-condition" >
							<select  id="productTypeId" name="productTypeId" class="querysel">
								<option value="">请选择</option>
								<c:forEach var="productType" items="${productTypeListByStaffId}">
									<option value="${productType.id}">${productType.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					</c:if>

					<c:if test="${progressStatus == 4 }">
						<div class="search-td">
							<div class="search-td-label">货款结清:</div>
							<div class="search-td-condition" >
								<select  id="paymentOfGoodsConfirm" name="paymentOfGoodsConfirm" class="querysel">
									<option value="">请选择</option>
									<option value="1" selected="selected">已结清</option>
									<option value="0">未结清</option>
								</select>
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
	
		<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
  </body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
