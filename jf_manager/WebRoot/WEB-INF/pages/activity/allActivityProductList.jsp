<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
  <style type="text/css">
 .l-grid-row{height: 64px;}
 </style>
 <script type="text/javascript">
 var activityId="${activityId}";
 var refuseFlag="${refuseFlag}";
 var type="${type}";
 var listConfig={
		      url:"/activity/allActivityProductListData.shtml?activityId="+activityId+"&refuseFlag="+refuseFlag+"&type="+type,
		      btnItems:[],
		      listGrid:{ columns: [
		                        {display:'商品信息',name:"productInfo",width:450,render:function(rowdata,roeindex){
									var operHtml="";		                        	
		                        	if(rowdata.refuseFlag==1){
		                        		if (rowdata.remarks==null){
		                        			operHtml="<br/><samp style='color:#FF0000'>驳回理由：</samp>";
		                        		}else{
		                        			operHtml="<br/><samp style='color:#FF0000'>驳回理由："+rowdata.remarks+"</samp>";
		                        		}	
		                        	}
		                           	return 	"<div style='float: left;'><img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.productPic+"' width='60' height='60'/></div>"
		                        			+"&nbsp;<div align='left' style='float: left;font-size: 15px;width:350px'><samp style='color:#0099FF'>"+rowdata.productName+"</samp><br/><samp style='color:#949494'>货号："+rowdata.productArtNo+"&nbsp;&nbsp;商品ID："+rowdata.productCode+"（"+rowdata.productId+"）</samp>"
		                        			+operHtml+"</div>";
		                        }},
		                        {display:'活动价',width:100,name:'activitySalePrice'},
		                        {display:'折扣',name:'"discountMin"',width: 100,render:function(rowdata,rowindex){
		                        	if(rowdata.discountMin==rowdata.discountMax){
		                        		return rowdata.discountMin;
		                        	}else{
		                        		return rowdata.discountMin+"~"+rowdata.discountMax;
		                        	}		                        	
		                        }},
		                        {display:'历史最低价',width:100,name:'minimumPrice'},
		                        {display:'库存',width:100,name:'productStock'},
		                        {display:'参加活动次数',width:100,name:'joinNum'},
		                        {display:'历史总销量',width:100,name:'totalSalesNum'},
		                        {display:'本次活动销量',width:100,name:'thisProductQuantityNum'},
		                        {display:'审核状态',width:100,render:function(rowdata,rowindex){
		                        	if(type==5){
		                        		if(rowdata.refuseFlag==1){
											return "<samp style='color: red;'>已驳回</samp>";	                        			
		                        		}else{
			                        		if(rowdata.cooAuditStatus==2){
			                            		return "<samp style='color: #0000FF;'>"+rowdata.cooAuditStatusDesc+"</samp>";
			                            	}
		                        		}
		                        	}else if(type==0 || type==1 || type==2){
		                        		if(rowdata.refuseFlag==1){
		                        			return "<samp style='color: red;'>已驳回</samp>";
		                        		}else{
			                        		if(rowdata.operateAuditStatus==2){
			                            		return "<samp style='color: #0000FF;'>"+rowdata.operateAuditStatusDesc+"</samp>";
			                            	}
		                        		}
		                        	}
		                        }},
		                        {display:'商品提交时间',width:150,name:'updateDate',render:function(rowdata,rowindex){
		                        	if(rowdata.updateDate!=null){
			                        	var updateDate=new Date(rowdata.updateDate);
						            	return updateDate.format("yyyy-MM-dd hh:mm:ss");
		                        	}else if (rowdata.createDate!=null){
			                        	var createDate=new Date(rowdata.createDate);
						            	return createDate.format("yyyy-MM-dd hh:mm:ss");
		                        	}
		                        }},
		                        
		                        { display: "操作", name: "OPER",align: "center",width: 150, render: function(rowdata, rowindex) {
										var html = [];
									    /* html.push("<a href=\"javascript:seeActivityss(" + rowdata.id + ");\">修改商品</a>&nbsp;&nbsp;"); */
									    if(type==1){
										    if(rowdata.refuseFlag==1){
										    	html.push("<samp style='color: red;'>已驳回</samp");									    	
										    }else{
										    	if(rowdata.operateAuditStatus==2){
										    		html.push("<samp style='color:#949494;'>驳回商品</samp>&nbsp;&nbsp;");
										    	}else if(rowdata.operateAuditStatus==1 || rowdata.operateAuditStatus==null){
												    html.push("<a href=\"javascript:rejectProduct(" + rowdata.id + ");\">驳回商品</a>&nbsp;&nbsp;");
										    	}
										    }
									    }
									    if(type==5){
									    	if(rowdata.refuseFlag==1){
										    	html.push("<samp style='color: red;'>已驳回</samp");									    	
										    }else{
										    	if(rowdata.cooAuditStatus==2){
										    		html.push("<samp style='color:#949494;'>驳回商品</samp>&nbsp;&nbsp;");	    		
										    	}else if(rowdata.cooAuditStatus==1){
												    html.push("<a href=\"javascript:rejectProduct(" + rowdata.id + ");\">驳回商品</a>&nbsp;&nbsp;"); 
										    	}
										    }
									    }
									    return html.join("");
							 		}
			             		}
				                ],
		                 showCheckbox : true,  //不设置默认为 true
		                 showRownumber:true //不设置默认为 true
		      } , 							
		     container:{
		        searchBtnName:"searchbtn",
		        fromName:"dataForm",
		        listGridName:"maingrid"
		      }        
		  };
 
 //批量驳回按钮
 function batchReject(){
	 var data = listModel.gridManager.getSelectedRows();
     if (data.length == 0){
   	   $.ligerDialog.alert("请选择行");
     }else{
        var str = "";
         $(data).each(function ()
         {    
       	  if(str==''){
       		  str = this.id ;
       	  }else{
       		  str += ","+ this.id ;
       	  }
         });
         $.ligerDialog.confirm('确定驳回吗？',  function (yes)  
         {
        	 if(yes==true){
	        	 Reject(str);
        	 }
         }); 
     }
 }
function Reject(str){
		var type="${type}";
		var batchName=$("#batchName").val();
		if(batchName==null||batchName==""||batchName==undefined){
			$.ligerDialog.alert("驳回内容不能为空");
			return;
		}
		   $.ajax({ //ajax提交
					type:'POST',
					url:'${pageContext.request.contextPath}' +"/activity/allActivityProductBatchReject.shtml",
					data:{activityProductId:str,batchName:batchName,type:type},
					dataType:"json",
					cache: false,
					success: function(json){
					   if(json==null || json.statusCode!=200){
					     commUtil.alertError(json.message);
					  }else{
		                 $.ligerDialog.success("操作成功",function() {
		                            javascript:location.reload();
							});
					  }
					},
					error: function(e){
					 commUtil.alertError("系统异常请稍后再试");
					}
		    });
	}
 
 function rejectProduct(activityProductId){
	 $.ligerDialog.open({
		height: 300,
		width: 800,
		title: "活动商品驳回",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activity/allActivityProductReject.shtml?activityProductId=" + activityProductId+"&type=${type}",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
 }
 
 
</script>
<style type="text/css">
.l-grid-row{height: 64px;}
</style>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel" style="height: 50px;">
			<div class="search-tr">	
				<div class="search-td" style="float: left;">
				<div class="search-td-label" >商品ID/名称/货号：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id = "productValue" name="productValue" >
				</div>
				</div>
				 
				 <div class="search-td" style="float:left;width: 150px;">
					<div class="search-td-combobox-condition" >
						<select id="refuse" name="refuse">
							<option value="0">已提名商品</option>
							<option value="1">已驳回商品</option>
						</select>
					</div>
				</div>
				 
				<div class="search-td-search-btn" style="float: left;width: 110px;">
					<div id="searchbtn" >搜索</div>
				</div>
				<c:if test="${type eq 5 or myself eq 1}">
				<div>
					<input type="button" style="height: 25px;width: 110px;" value="<<返回活动页" onclick="frameElement.dialog.close();">&nbsp;&nbsp;
					批量驳回理由：<input type="text" id="batchName" style="width: 500px;">
					<input type="button" style="height: 25px;width: 110px;" value="批量提交" onclick="batchReject();"/>
				</div>
				</c:if>
			</div>
		</div>
<!-- 		<div style="width: 100%;background-color: rgba(242, 242, 242, 1);"><input type="button" style="width: 100px;height: 30px;background-color: #A3C0E8;" value="已提名商品(${already})" onclick="productByButton(0);"/><input type="button" style="width: 100px;height: 30px;background-color: #A3C0E8;" value="已驳回商品(${reject})" onclick="productByButton(1);"/></div> -->
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>