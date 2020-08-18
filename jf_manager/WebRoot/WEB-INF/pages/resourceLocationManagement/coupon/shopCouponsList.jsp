<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript">
 function gridRefresh() {
	    if (listModel.gridManager) {
	        var gridparms = [];
	        gridparms.push({ name: "staff", value: "self" });
	        listModel.gridManager.loadServerData(gridparms);
	    }
	}
 
  var listConfig={
	  
      url:"/resourceLocationManagement/shopCouponsListData.shtml",
    
      listGrid:{ columns: [  
 		                { display: '店铺名称/商家序号',width: 200, render: function (rowdata, rowindex) {
 		                	var html = [];
 		                	html.push(rowdata.mchtName);
 		                	html.push("<br>");
 		                	html.push(rowdata.mchtCode);
 		               		html.push("<br>");
		                	html.push(rowdata.id);
 		              	 	return html.join("");
		                }},
		                { display: '类型', width: 150, render: function (rowdata, rowindex) {
		                	var html = [];
		                    if (rowdata.preferentialType==1){
		                    	html.push("满减");
		                    }
		                    if (rowdata.preferentialType==2){
		                    	html.push("折扣");
		                    }
							return html.join("");
		                }},
		                { display: '优惠信息', width: 150, render: function (rowdata, rowindex) {
                          	 var html = [];
                         	if(rowdata.preferentialType==1){
                         		html.push("满"+rowdata.minimum+"减"+rowdata.money+"<br>");
                         	}else if(rowdata.preferentialType==2){
                         		var discount = rowdata.discount*10 
                         		html.push("满"+rowdata.minicount+"件打"+discount.toFixed(1) +"折<br>");
                         	}
                         	
                         	 return html.join("");
		                }},

		                { display: '活动时间', width: 150, render: function (rowdata, rowindex) {
		                	var html = [];
		                    if (rowdata.recBeginDate!=null){
		                    	var beginDate=new Date(rowdata.recBeginDate);
		                    	html.push("起 "+ beginDate.format("yyyy-MM-dd hh:mm"));
		                    }
		                    if (rowdata.recEndDate!=null){
		                    	var endDate=new Date(rowdata.recEndDate);
		                    	html.push("止 "+endDate.format("yyyy-MM-dd hh:mm"));
		                    }
							return html.join("<br>");
		                }},
		                { display: '发行量',  name: 'grantQuantity'},
		                { display: '领取量',  name: 'recQuantity'},
		                { display: '使用量',  name: 'useQuantity'},
		                { display: '状态', render: function(rowdata, rowindex) {
		                	var html = [];
		                	var now = new Date();
		                	var beginTime = rowdata.recBeginDate;
		                	var endTime = rowdata.recEndDate;
	                  		if(now>endTime){
	                  			 html.push("已结束");
	                  		}
	                  		if(now>=beginTime && now<=endTime){
	                  			 html.push("活动中");
	                  		}
	                  		if(now<beginTime){
	                  			 html.push("未开始")
	                  		}
	                  		return html.join("")
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
}
</script>

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<c:if test="${roleType!=1}">
	<div id="toptoolbar"></div>
	</c:if>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		
		<div class="search-tr"  > 
		
			<div class="search-td">
			<div class="search-td-label" style="float:left;">日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="recBeginDate" name="recBeginDate" />
				<script type="text/javascript">
					$(function() {
						$("#recBeginDate").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			</div>
			
		
			
			<div class="search-td">
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
			<div class="l-panel-search-item">
				<input type="text" id="recEndDate" name="recEndDate" />
				<script type="text/javascript">
					$(function() {
						$("#recEndDate").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			 
			 
			 <div class="search-td">
			 <div class="search-td-label" >优惠类型：</div>
			 <div class="search-td-condition" >
				<select id="preferentialType" name="preferentialType">
					<option value="">请选择...</option>
					<option value="1">店铺满减券</option>
					<option value="2">店铺折扣券</option>
				</select>
		 	 </div>
			 </div>
			 
			 
			 <div class="search-td">
			 <div class="search-td-label" >状态：</div>
			 <div class="search-td-condition" >
				<select id="seachStatus" name="seachStatus">
					<option value="">请选择...</option>
					<option value="1">未开始</option>
					<option value="2">活动中</option>
					<option value="3">已结束</option>
				</select>
		 	 </div>
			 </div>
			 
			 
			
			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
			
		</div>
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>