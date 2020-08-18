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
 
//添加活动
function addAvtivity(id){
	   $.ajax({ //ajax提交
				type:'POST',
				url:'${pageContext.request.contextPath}' +"/activityArea/previewActivityAdd.shtml?ids="+id,
				data:"",
				dataType:"json",
				cache: false,
				success: function(json){
				   if(json==null || json.statusCode!=200){
				     commUtil.alertError(json.message);
				  }else{
	                 $.ligerDialog.success("操作成功",function() {
	                	 parent.$("#searchbtn").click();
	                	 frameElement.dialog.close();
					 });
				  }
				},
				error: function(e){
				 commUtil.alertError("系统异常请稍后再试");
				}
	    });
}
 
 var listConfig={
		  
    url:"/activityArea/previewSortListData.shtml",
    
    btnItems:[
              {text: '批量添加', icon: 'add', click: function(yes) {
      			  var data = listModel.gridManager.getSelectedRows();
                          if (data.length == 0)
                        	  $.ligerDialog.alert('请选择行');
                          else
                          {
                             var str = "";
                             
                              $(data).each(function ()
                              {    
                            	  if(str==''){
                            		  str = this.id ;
                            	  }else{
                            		  str += ","+ this.id ;
                            	  }
                              });
                              $.ligerDialog.confirm('确定添加?', function ()
                              {
                            	  addAvtivity(str);
                              }); 
                          }
                          return;
      			  }}
    			],
 
    listGrid:{ columns: [
                    {display:'类型', width:100, name:'sourceDesc'},
                    {display:'特卖ID/会场ID', width:120,render:function(rowdata,rowindex){
                    	if (rowdata.source==2){
                    		return rowdata.activityId;
                    	}else{
                    		return rowdata.id;
                    	}
                    }},
                    {display:'特卖/会场名称', width:230,name:'name'},
	                {display:'商家名称', width:230, render:function(rowdata,rowindex){
						if(rowdata.source==2){
							return rowdata.shortName;						
						}
					}},
	                {display:'商家序号', width:150,render:function(rowdata,rowindex){
	                	if(rowdata.source==2){
	                		return rowdata.mchtCode;							
						}
					}},
	                {display:'品牌', width:150,render:function(rowdata,rowindex){
	                	if(rowdata.source==2){
	                		return rowdata.brandName;							
						}
					}},
					{display:'预热开始时间',width: 150,render:function(rowdata,rowindex){
						if(rowdata.preheatTime!=null){
							var preheatTime=new Date(rowdata.preheatTime);
							return preheatTime.format("yyyy-MM-dd hh:mm:ss");								
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
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" id ="listType" name="listType" value="add">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td" >
				<div class="search-td-label" style="float:left;">
					<select id="source" name="source">
						<option value="2">特卖ID</option>
						<option value="1">会场ID</option>
					</select>
				</div>
				<div class="l-panel-search-item" >
					<input type="text" id = "activityOrAreaId" name="activityOrAreaId" >
				</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label"  >活动名称：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id = "name" name="name" >
				</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label"  >商家：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id = "shortName" name="shortName" >
				</div>
			</div>
			<div class="search-td">
			<div class="search-td-label"  >商家序号：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id = "mchtCode" name="mchtCode" >
				</div>
			</div>
			
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
