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
 <script type="text/javascript">
//  var activityAreaId="${activityAreaId}";

/* 查看活动 */
 function seeSingleProductGood(activityProductId,productId) {
		$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "单品活动商家报名审核",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activity/allSeeSingleActivityProductInfo.shtml?activityProductId=" + activityProductId+"&productId="+productId+"&type=-1",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}


 var listConfig={
		      url:"/activity/singleActivityProductGoodListData.shtml?activityAreaId=${activityAreaId}",
		      btnItems:[],
		      listGrid:{ columns: [
								{display:'活动名称',name:'activityAreaName'},
								{display:'活动类型',name:'activityAreaTypeDesc'},
								{display:'商品ID',name:'productId'},
								{ display: '商品图片', width:120, render: function (rowdata, rowindex)
								    {
									 var h = "";
								       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.productPic+"' width='60' height='60' onclick='viewerPic(this.src)'>";
								    	return h;
								    
								    }
								},
		                        {display:'商品标题',name:'productName',width: 200},
		                        {display:'类目/品牌',name:'typeBrandName',render:function(rowdata,rowindex){
		                        	if (rowdata.productTypeName!=null){
		                        		return rowdata.productTypeName+"/"+rowdata.productBrandName;
		                        	}
		                        }},
		                        {display:'所属商家',name:'shortNameAndCode',render:function(rowdata,rowindex){
		                        	return rowdata.shortName+"/"+rowdata.mchtCode;
		                        }},
		                        {display:'活动价/原价',name:'activitySalePrice'},
		                        {display:'销量',name:""},
		                        {display:'库存',name:'productStock'},
		                        {display:'销售额',name:""},
		                        { display: "操作", name: "OPER",align: "center",width: 150, render: function(rowdata, rowindex) {
										var html = [];
									    html.push("<a href=\"javascript:seeSingleProductGood(" + rowdata.id + ","+rowdata.productId+");\">查看</a>&nbsp;&nbsp;"); 
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
 
 
 function allSingletypeNameOnblur(){
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
/* .l-panel-bwarp{
	height: 450px;
} */
</style>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel" style="height: 50px;">
			<div class="search-tr">	
				<div class="search-td" style="float: left;">
				<div class="search-td-label" >商品ID：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id = "productId" name="productId" >
				</div>
				</div>
				
				<div class="search-td" style="float: left;">
				<div class="search-td-label" >商家：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id = "shortName" name="shortName" >
				</div>
				</div>
				
				<div class="search-td" style="float: left;">
				<div class="search-td-label" >商家序号：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id = "mchtCode" name="mchtCode" >
				</div>
				</div>
				 
				 <div class="search-td" style="float:left;width: 180px;">
				 	<div class="search-td-label" >活动类型：</div>
					<div class="search-td-combobox-condition">
						<select id="activityType" name="activityType" onchange="allSingletypeNameOnblur();" >
							<option value="-1">不限</option>
							<option value="6">爆款单品</option>
							<option value="7">新用户专享</option>
						</select>
					</div>
				</div>
				
<!-- 				<div class="search-td" style="float:left;width: 180px;">
				 	<div class="search-td-label" >报名活动：</div>
					<div class="search-td-combobox-condition" >
						<select id="typeName" name="typeName">
							<option value="-1">不限</option>
						</select>
					</div>
				</div> -->
				 
				<div class="search-td-search-btn" style="float: left;width: 110px;">
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
 