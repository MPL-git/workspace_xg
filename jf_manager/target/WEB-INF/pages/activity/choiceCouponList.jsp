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
 
 function couponId(){
	 var couId = "";
	 var couName="";
	 var data = listModel.gridManager.getSelectedRows();
     if (data.length == 0){
   	  	$.ligerDialog.alert('请选择行');
     }
     else
     {
         $(data).each(function ()
         {    
       	  if(couId==''){
       		couId = this.id ;
       	  }else{
       		couId += ","+ this.id ;
       	  }
       	if(couName==''){
       		couName = this.name ;
     	  }else{
     		 couName += ","+ this.name ;
     	  }
         });
     }
     parent.couponListId(couId,couName);
	frameElement.dialog.close();
 }
 
 var listConfig={
	  
      url:"/activityArea/choiceCouponListData.shtml",
   
      btnItems:[],
      listGrid:{ columns: [  /* { display: 'ID', name: 'id'}, */ 
                        {display:'优惠券名称',name:'name'},
                        {display:'面额/使用条件',name:'moneyMinimum',render:function(rowdata,rowindex){
                        	return rowdata.money+"/"+rowdata.minimum;
                        }},
                        {display:'发行量',name:'grantQuantity'},
						{display:'创建时间',name:'createDate',width: 150,render:function(rowdata,rowindex){
							if(rowdata.createDate==null||rowdata.createDate==""||rowdata.createDate==undefined){
								return "";
							}else{
								var createDate=new Date(rowdata.createDate);
								return createDate.format("yyyy-MM-dd hh:mm:ss");								
							}
						}},
						{display:'状态',name:'status',render:function(rowdata,rowindex){
							if(rowdata.status==0){
								return "未启用";
							}else if(rowdata.status==1){
								return "启用";
							}else{
								return "停用";
							}
						}}
		                ],   
                 showCheckbox : true,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
     container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"couponListIdForm",
        listGridName:"maingridCoupon"
      }        
  };
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
<!-- 	<div id="toptoolbar"></div> -->
	<form id="couponListIdForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label"  >优惠券名称：</div>
			<div class="search-td-combobox-condition" >
				<input type="text" id = "couponName" name="couponName" >
			</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label"  >创建时间：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id= "couponCreateDate" name="couponCreateDate" >
					<script type="text/javascript">
					$(function() {
						$("#couponCreateDate").ligerDateEditor( {
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
				<div class="search-td-label"></div>
				<div class="search-td-combobox-condition" >
					<input type="button" style="cursor:pointer;width: 80px;background-color:rgba(255, 153, 102, 1);height: 30px;" onclick="couponId();" value="提交" >
				</div>
			</div>
			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
		</div>
		</div>
		
		<div id="maingridCoupon" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
