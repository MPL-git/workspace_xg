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
 .l-grid-header1,.l-grid-header2{height:35px!important;}
 .l-grid-body2{height:655px!important;}
 </style>
 <script type="text/javascript">
//  var activityAreaId="${activityAreaId}";
var type="${type}";
/* 查看活动 */
 function seeOperateSingleProductInfo(activityProductId,productId) {
		$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "单品活动商家报名审核",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activity/allSeeSingleActivityProductInfo.shtml?activityProductId=" + activityProductId+"&productId="+productId+"&type=${type}",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}

 /* 权限控制*/
 function operateSingleProductAudit(activityId) {
 		$.ligerDialog.open({
 		height: 800,
 		width: 600,
 		title: "单品活动商家权限管理",
 		name: "INSERT_WINDOW",
 		url: "${pageContext.request.contextPath}/activity/activityAuthInfo.shtml?activityId=" + activityId+"&type="+type,
 		showMax: true,
 		showToggle: false,
 		showMin: false,
 		isResize: true,
 		slide: false,
 		data: null
 	});
 	}
 
 var listConfig={
		      url:"/activity/singleActivityProductGoodListData.shtml?activityAreaId=${activityAreaId}&type=${type}",
		      btnItems:[],
		      listGrid:{ columns: [
								{display:'活动名称',name:'activityAreaName',width: 120},
								{display:'活动类型',name:'activityAreaTypeDesc',width: 80},
								{display:'商品ID',name:'productId',width: 80},
								{ display: '商品图片', width:120, render: function (rowdata, rowindex)
								    {
									 var h = "";
								       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.productPic+"' width='60' height='60' onclick='viewerPic(this.src)'>";
								    	return h;
								    
								    }
								},
		                        {display:'商品标题',name:'productName',width: 200,render:function(rowdata, rowindex){
		                        	return "<samp style='color:#0099FF'>"+rowdata.productName+"</samp>";
		                        }},
/* 		                        {display:'类目/品牌',name:'typeBrandName',width: 200,render:function(rowdata,rowindex){
		                        	if (rowdata.productTypeName!=null){
		                        		return rowdata.productTypeName+"/"+rowdata.productBrandName;
		                        	}
		                        }}, */
		                        {display:'秒杀价<br>历史最低价',name:'activitySalePrice',width: 120,render:function(rowdata,rowindex){
		                        	var activitySalePrice=0;
		                        	var minimumPrice=0;
		                        	if(rowdata.activitySalePrice!=null){
		                        		activitySalePrice=rowdata.activitySalePrice;
		                        	}
		                        	if(rowdata.minimumPrice!=null){
		                        		minimumPrice=rowdata.minimumPrice;
		                        	}
		                        	
		                        	return "<samp>"+activitySalePrice+"</samp><br/><samp>"+minimumPrice+"</samp>";
		                        }},
		                        {display:'折扣',name:'"discountMin"',width: 100,render:function(rowdata,rowindex){
		                        	if(rowdata.discountMin==rowdata.discountMax){
		                        		return rowdata.discountMin;
		                        	}else{
		                        		return rowdata.discountMin+"~"+rowdata.discountMax;
		                        	}		                        	
		                        }},
		                        {display:'运营专员审核',name:'operateAuditStatusDesc',width:100,render:function(rowdata, rowindex){
		                        	if(rowdata.operateAuditStatus==null){
		                        		return "未审核";
		                        	}else if(rowdata.operateAuditStatus==1){
		                        		
		                        		return "<samp style='color: #FF6600;'>"+rowdata.operateAuditStatusDesc+"</samp>";
		                        	}else if(rowdata.operateAuditStatus==2){
		                        		
		                        		return "<samp style='color: #0000FF;'>"+rowdata.operateAuditStatusDesc+"</samp>";
		                        	}else if(rowdata.operateAuditStatus==3){
		                        		
		                        		return "<samp style='color: red;'>"+rowdata.operateAuditStatusDesc+"</samp>";
		                        	}
		                        }},
		                        {display:'设计审核',name:'designAuditStatusDesc',width: 100,render:function(rowdata, rowindex){
		                        	if(rowdata.designAuditStatus==null){
		                        		return "未审核";
		                        	}else if(rowdata.designAuditStatus==1){
		                        		return "<samp style='color: #FF6600;'>"+rowdata.designAuditStatusDesc+"</samp>";
		                        	}else if(rowdata.designAuditStatus==2){
		                        		return "<samp style='color: #0000FF;'>"+rowdata.designAuditStatusDesc+"</samp>";
		                        	}else if(rowdata.designAuditStatus==3){
		                        		return "<samp style='color: red;'>"+rowdata.designAuditStatusDesc+"</samp>";
		                        	}
		                        }},
		                        {display:'法务审核',name:'lawAuditStatusDesc',width: 100,render:function(rowdata, rowindex){
		                        	if(rowdata.lawAuditStatus==null){
		                        		return "未审核";
		                        	}else if(rowdata.lawAuditStatus==1){
		                        		return "<samp style='color: #FF6600;'>"+rowdata.lawAuditStatusDesc+"</samp>";
		                        	}else if(rowdata.lawAuditStatus==2){
		                        		return "<samp style='color: #0000FF;'>"+rowdata.lawAuditStatusDesc+"</samp>";
		                        	}else if(rowdata.lawAuditStatus==3){
		                        		return "<samp style='color: red;'>"+rowdata.lawAuditStatusDesc+"</samp>";
		                        	}
		                        }},
		                        {display:'运营总监审核',name:'cooAuditStatusDesc',width: 100,render:function(rowdata, rowindex){
		                        	if(rowdata.cooAuditStatus==null){
		                        		return "未审核";
		                        	}else if(rowdata.cooAuditStatus==1){
		                        		return "<samp style='color: #FF6600;'>"+rowdata.cooAuditStatusDesc+"</samp>";
		                        	}else if(rowdata.cooAuditStatus==2){
		                        		return "<samp style='color: #0000FF;'>"+rowdata.cooAuditStatusDesc+"</samp>";
		                        	}else if(rowdata.cooAuditStatus==3){
		                        		return "<samp style='color: red;'>"+rowdata.cooAuditStatusDesc+"</samp>";
		                        	}
		                        }},
		                        {display:'报名时间',name:'submitTime',width: 150,render:function(rowdata, rowindex){
		                        	if(rowdata.submitTime==null||rowdata.submitTime==""||rowdata.submitTime==undefined){
										return "";
									}else{
										var submitTime=new Date(rowdata.submitTime);
										return submitTime.format("yyyy-MM-dd hh:mm:ss");
									}
		                        }},
		                        {display:'最后审核时间',name:'activityUpdateDate',width: 150,render:function(rowdata, rowindex){
		                        	if(rowdata.updateDate==null||rowdata.updateDate==""||rowdata.updateDate==undefined){
										return "";
									}else{
										var updateDate=new Date(rowdata.updateDate);
										return updateDate.format("yyyy-MM-dd hh:mm:ss");
									}
		                        }},
		                        { display: "操作", name: "OPER",align: "center",width: 150, render: function(rowdata, rowindex) {
										var html = [];
									    html.push("<a href=\"javascript:seeOperateSingleProductInfo(" + rowdata.id + ","+rowdata.productId+");\">查看</a>&nbsp;&nbsp;"); 
									    html.push("<a href=\"javascript:operateSingleProductAudit(" + rowdata.activityId + ");\">权限管理</a>&nbsp;&nbsp;"); 
									    return html.join("");
							 		}
			             		},
		                        {display:'所属商家',name:'shortNameAndCode',width: 200,render:function(rowdata,rowindex){
		                        	return rowdata.shortName+"/"+rowdata.mchtCode;
		                        }},
		                        {display:'对接运营',name:'contactName',width: 100},
		                        {display:'历史总销量',name:'totalSalesNum',width: 80},
		                        {display:'库存',name:'productStock',width: 80},
		                        {display:'商品参加单品次数<br>商家参加单品次数',name:'twoNum',width: 120,render:function(rowdata,rowindex){
		                        	return "<samp>"+rowdata.signleTypeTwoNum+"</samp><br/><samp>"+rowdata.mchtSignNum+"</samp>";
		                        }}
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
 
function rejectOnclick(){
	$("#batchName").show();
}

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

function butAdopt(){
	$("#batchName").hide();
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
        $.ligerDialog.confirm('确定通过审核吗？',  function (yes)  
        {
	       	 if(yes==true){
		        	 adopt(str);
	       	 }
        }); 
    }
}

function adopt(str){
	var type="${type}";
	$.ajax({ //ajax提交
		type:'POST',
		url:'${pageContext.request.contextPath}' +"/activity/singleAuditAdopt.shtml",
		data:{activityProductId:str,type:type},
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

function operateSingleTypeNameOnblur(){
	 var activityType=$("#activityType").val();
	 
	 $.ajax({ //ajax提交
			type:'POST',
			url:"${pageContext.request.contextPath}/activityArea/singleProductActivityAreaTypeName.shtml",
			data:{activityType:activityType},
			dataType:'json',
			cache: false,
			success: function(json){
			   if(json==null || json.statusCode!=200){
			     commUtil.alertError(json.message);
			  }else{
				  if(json.activity!=null){
	              		var obj=json.activity;
	              		if(obj.length>0){
		              		for(var i=0;i<obj.length;i++){
	// 	              			console.log(obj[i]);
		              			var html='<option value="'+obj[i].activityAreaId+'">'+obj[i].activityAreaName+'</option>';
		              			$("#typeName").append(html);
		              		}
	              		}else{
	              			$("#typeName").empty();
	              			var html='<option value="">不限</option>';
	              			$("#typeName").append(html);
	              		}
	              	}
			  }
			},
			error: function(e){
			 commUtil.alertError("系统异常请稍后再试");
			}
	});
}


</script>
<style type="text/css">
.l-panel-body .l-panel{
	height: 650px;
}
</style>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr">	
				<div class="search-td">
				<div class="search-td-label" >商品ID：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id = "productId" name="productId" >
				</div>
				</div>
				
				<div class="search-td">
				<div class="search-td-label" >商家：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id = "shortName" name="shortName" >
				</div>
				</div>
				
				<div class="search-td">
				<div class="search-td-label" >商家序号：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id = "mchtCode" name="mchtCode" >
				</div>
				</div>
				 
				<div class="search-td">
				 	<div class="search-td-label" >活动类型：</div>
					<div class="search-td-combobox-condition">
						<select id="activityType" name="activityType" onchange="operateSingleTypeNameOnblur();" >
							<option value="-1">不限</option>
							<option value="6">爆款单品</option>
							<option value="7">新用户专享</option>
						</select>
					</div>
				</div>
			</div>
			<div class="search-tr">	
<!-- 				<div class="search-td">
				 	<div class="search-td-label" >报名活动：</div>
					<div class="search-td-combobox-condition" >
						<select id="typeName" name="typeName">
							<option value="-1">不限</option>
						</select>
					</div>
				</div> -->
				
				<div class="search-td">
				<div class="search-td-label"  >运营专员审核：</div>
				<div class="search-td-combobox-condition" >
					<select id="operateAuditStatus" name="operateAuditStatus">
						<option value="">不限</option>
						<c:forEach items="${operateAuditStatus}" var="list">
							<option value="${list.statusValue}">${list.statusDesc}
						</c:forEach>
					</select>
				</div>
				</div>
				
				<div class="search-td">
				<div class="search-td-label"  >设计专员审核：</div>
				<div class="search-td-combobox-condition" >
					<select id="designAuditStatus" name="designAuditStatus">
						<option value="">不限</option>
						<c:forEach items="${designAuditStatus}" var="list">
							<option value="${list.statusValue}">${list.statusDesc}
						</c:forEach>
					</select>
				</div>
				</div>
				
				<div class="search-td">
				<div class="search-td-label"  >法务部审核：</div>
				<div class="search-td-combobox-condition" >
					<select id="lawAuditStatus" name="lawAuditStatus">
						<option value="">不限</option>
						<c:forEach items="${lawAuditStatus}" var="list">
							<option value="${list.statusValue}">${list.statusDesc}
						</c:forEach>
					</select>
				</div>
				</div>
			</div>
			
			<div class="search-tr">	
			
				<div class="search-td">
				<div class="search-td-label"  >运营总监审核：</div>
				<div class="search-td-combobox-condition" >
					<select id="cooAuditStatus" name="cooAuditStatus">
						<option value="">不限</option>
						<c:forEach items="${cooAuditStatus}" var="list">
							<option value="${list.statusValue}">${list.statusDesc}
						</c:forEach>
					</select>
				</div>
				</div>			 	
			 	
				<div class="search-td-search-btn">
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
			
		</div>
		<div style="height: 40px;width: 100%;background-color: rgba(242, 242, 242, 1);">
			<input type="button" value="通过" onclick="butAdopt();" style="cursor:pointer;width: 80px;height: 30px;margin-top: 5px;margin-left: 10px;"/>
			<input type="button" value="驳回" style="cursor:pointer;width: 80px;height: 30px;margin-top: 5px;margin-left: 10px;" onclick="rejectOnclick();"/>
			<input type="text" id="batchName" value="" style="width: 300px;height: 30px;margin-top: 5px;margin-left: 10px;display: none;"/>
			<input type="button" value="提交"  onclick="batchReject();" style="cursor:pointer;width: 80px;height: 30px;margin-top: 5px;margin-left: 10px;background-color: rgba(22, 155, 213, 1);border: none;color: #F5F5F5;"/>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0;"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
 