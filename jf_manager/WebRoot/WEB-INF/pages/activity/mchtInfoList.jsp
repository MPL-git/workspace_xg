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
 
 function mchtInfoId(){
	 var str = "";
	 var strName="";
	 var data = listModel.gridManager.getSelectedRows();
     if (data.length == 0){
   	  	$.ligerDialog.alert('请选择行');
     }
     else
     {
         $(data).each(function ()
         {    
       	  if(str==''){
       		  str = this.id ;
       	  }else{
       		  str += ","+ this.id ;
       	  }
       	if(strName==''){
     		  strName = this.shortName ;
     	  }else{
     		  strName += ","+ this.shortName ;
     	  }
         });
     }
     parent.mchtInfoListId(str,strName);
	frameElement.dialog.close();
 }
 
 var listConfig={
	  
      url:"/activityArea/mchtInfoListData.shtml",
   
      btnItems:[],
      listGrid:{ columns: [  /* { display: 'ID', name: 'id'}, */ 
                        {display:'商家序号',name:'mchtCode'},
                        {display:'商家名称',name:'shortName'},
                        {display:'入驻类型',name:'mchtTypeDesc'},
		                {display:'30天销量', name:''},
		                {display:'30天客单价',name:''},
		                {display:'总销量',name:''},
		                {display:'总客单价',name:''},
		                {display:'违规次数',name:''},
						{display:'入驻时间',name:'createDate',width: 150,render:function(rowdata,rowindex){
							if(rowdata.createDate==null||rowdata.createDate==""||rowdata.createDate==undefined){
								return "";
							}else{
								var createDate=new Date(rowdata.createDate);
								return createDate.format("yyyy-MM-dd hh:mm:ss");								
							}
						}}
		                ],   
                 showCheckbox : true,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
     container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"operateAuditForm",
        listGridName:"maingridOperate"
      }        
  };
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
<!-- 	<div id="toptoolbar"></div> -->
	<form id="operateAuditForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label"  >商家：</div>
			<div class="search-td-combobox-condition" >
				<input type="text" id = "shortName" name="shortName" >
			</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label"  >商家序号：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id= "mchtCode" name="mchtCode" >
				</div>
			</div>
			<div class="search-td">
<!-- 				<div class="search-td-label">商家序号：</div> -->
				<div class="search-td-combobox-condition" >
					<input type="button" style="cursor:pointer;width: 80px;background-color:rgba(255, 153, 102, 1);height: 30px;" onclick="mchtInfoId();" value="提交" >
				</div>
			</div>
			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
		</div>
		</div>
		
		<div id="maingridOperate" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
