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
	 
	 /* // 删除广告组
	 function delCampaign(ids) {
		 $.ligerDialog.confirm('确定删除此广告组吗?', function (status){  
             if(status==true){
            	 $.ajax({
        			 type: 'post',
        			 url: '${pageContext.request.contextPath }/toutiaoCampaign/delCampaign.shtml',
        			 data: {ids : ids},
        			 dataType: 'json',
        			 success: function(data) {
        				 if(data == null || data.statusCode != 200){
        				     commUtil.alertError(data.message);
        				 }else {
        					 $("#searchbtn").click();
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
	      url:"/toutiaoCampaign/getToutiaoCampaignList.shtml",
	      /* btnItems:[
            {text: '批量删除', icon: 'delete', click: function() {
     			  var data = listModel.gridManager.getSelectedRows();
                  if (data.length == 0) {
                	  $.ligerDialog.alert('请选择行');
                  }else {
                     var ids = "";                         
                      $(data).each(function (){    
                    	  if(ids==''){
                    		  ids = this.id ;
                    	  }else{
                    		  ids += ","+ this.id;
                    	  }
                      });
                      delCampaign(ids);
                  }
                  return;
    		}}
  		  ], */
  		  btnItems:[],
	      listGrid:{ columns: [
							{display:'账户ID',name:'advertiserId', align:'center', isSort:false, width:180},
							{display:'账户名称',name:'advertiserName', align:'center', isSort:false, width:180},
							{display:'广告组ID',name:'campaignId', align:'center', isSort:false, width:180},
							{display:'广告组名称',name:'name', align:'center', isSort:false, width:180},
							{display:'推广目的',name:'landingTypeDesc', align:'center', isSort:false, width:180},
							{display:'预算（元）',name:'', align:'center', isSort:false, width:100, render:function(rowdata,rowindex){
								var html = [];
								html.push(rowdata.budget);
								html.push("</br>");
								html.push(rowdata.budgetModeDesc);
								return html.join("");
							}},
							{display:'状态',name:'statusDesc', align:'center', isSort:false, width:100},
							{display:'创建时间',name:'campaignCreateTime', align:'center', isSort:false, width:180}/* ,
							{display:'操作',name:'', align:'center', isSort:false, width:80, render:function(rowdata,rowindex){
								var html = [];
								html.push("<a href=\"javascript:delCampaign(" + rowdata.id + ");\">【删除】</a>");
								return html.join("");
							}} */
			         ], 
	                 showCheckbox : false,  //不设置默认为 true
	                 showRownumber: true //不设置默认为 true
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
	<!-- <div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >账户ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="advertiserId" name="advertiserId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >账户名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="advertiserName" name="advertiserName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >广告组ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="campaignId" name="campaignId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >广告组名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="campaignName" name="campaignName" >
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >推广目的：</div>
					<div class="search-td-combobox-condition" >
						<select id="landingType" name="landingType" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach items="${landingTypeList }" var="landingType">
								<option value="${landingType.statusValue }">${landingType.statusDesc }</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach items="${statusList }" var="status">
								<option value="${status.statusValue }">${status.statusDesc }</option>
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
