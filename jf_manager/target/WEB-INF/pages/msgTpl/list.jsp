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
	
 var listConfig={
	  
      url:"/msgTpl/msgTpl-list-data.shtml",
   
     /*  btnItems:[{text: '批量删除', icon: 'delete', click: function(yes) {
      			  var data = listModel.gridManager.getSelectedRows();
                          if (data.length == 0)
                        	  $.ligerDialog.alert('请选择行');
                          else
                          {
                             var str = "";
                             var params={};
                             
                              $(data).each(function ()
                              {    
                            	  if(str==''){
                            		  str = this.id ;
                            	  }else{
                            		  str += ","+ this.id ;
                            	  }
                              });
                              $.ligerDialog.confirm('确定删除?', function ()
                              {
                            	  deletInfo(str);
                              }); 
                          }
                          return;
      			  }}
      			], */
      
      listGrid:{ columns: [  /* { display: 'ID', name: 'id'}, */ 
		                { display: ' 模版类型', name: 'tplTypeDesc',width:100}, 
		                { display: '消息类型',  name: 'msgTypeDesc',width:200}, 
		                { display: '创建时间', name: 'createTime',width:200, render: function(rowdata, rowindex) {
			              	   var createDate=new Date(rowdata.createDate);
			            	   return createDate.format("yyyy-MM-dd hh:mm:ss");
			            }},
		                { display: '内容',name: 'content',align:'left',width:1100,render:function(rowdata,rowindex){
		                	if(rowdata.content==null||rowdata.content==""||rowdata.content==undefined){
		                		return "-";
		                	}else{return rowdata.content;}
		                }}
		                ],   
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
     container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"msgTplForm",
        listGridName:"maingrid"
      }        
  };
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<input id="tplType" name="tplType" type="hidden" value="A"/>
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="msgTplForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
		
 			<div class="search-td">
 			<div class="search-td-label">消息类型：</div>
			<div class="search-td-combobox-condition" >
 				<select id="msgType" name="msgType" style="width:100px;">
 					<option value="">不限</option>
 					<c:forEach var="list" items="${statusList}">
					<option value="${list.statusValue}">${list.statusDesc}</option>
 					</c:forEach>
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
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
