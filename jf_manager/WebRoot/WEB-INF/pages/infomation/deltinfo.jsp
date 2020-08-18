<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>
 
 <script type="text/javascript">
	
    
	function gridRefresh() {
	    if (listModel.gridManager) {
	        var gridparms = [];
	        gridparms.push({ name: "staff", value: "self" });
	        listModel.gridManager.loadServerData(gridparms);
	    }
	}
	
	//恢复信息
	function deletInfo(id){
		   $.ajax({ //ajax提交
					type:'POST',
					url:'${pageContext.request.contextPath}' +"/infomation/cancleInfo.shtml?Ids="+id+"&status=0",
					data:"",
					dataType:"json",
					cache: false,
					success: function(json){
						$.ligerDialog.alert(json);
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

 var listConfig={
	  
      url:"/infomation/deldata.shtml",
      
      btnItems:[
                {text: '批量恢复信息', icon: 'modify', click: function(yes) {
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
                                $.ligerDialog.confirm('确定恢复?', function (yes)
                                {
                                	if (yes){
                                		deletInfo(str);
                                	}
                                }); 
                            }
                            return;
        			  }}
      			],
      
      listGrid:{ columns: [  { display: 'ID', name: 'id'}, 
		                { display: ' 标题', name: 'title'},
		                { display: '发布人<a href=\"javascript:gridRefresh();\">我发布的信息</a>',  name: 'creatName'}, 
		                { display: '发布时间', name: 'creatDataDsc'},
		                { display: '推送渠道', name: 'infoTypeDsc'},
		                { display: "操作", name: "OPER",align: "center", render: function(rowdata, rowindex) {
							var html = [];
							var id = rowdata.id;
						    html.push("<a href=\"javascript: deletInfo("+id+");\">恢复信息</a>&nbsp;&nbsp;"); 
						    return html.join("");
					 }
	             }
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
       
  }
  
</script>


<script type="text/javascript">
    var productTypeCombo;
    $(function(){
    	productTypeCombo= $("#catalogName").ligerComboBox({
              selectBoxWidth: 200,
              selectBoxHeight: 200,  
              valueField: 'id',
              textField: 'frontName',
              valueFieldID:'catalogId',
              treeLeafOnly:false,
              valueField : 'id',
              tree: { url: '${pageContext.request.contextPath}/infomation/getCatalogTree.shtml', checkbox: false, ajaxType: 'get', idFieldName: 'id',textFieldName:'frontName',parentIDFieldName:'parentId',isExpand:2}
          });
    });
    
    </script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
		
			 <div class="search-td">
			<div class="search-td-label">栏目:</div>
			<div  class="search-td-combobox-condition">
				<input id="catalogName" type="text" >
			</div>
			</div>
			 	
			<div class="search-td">
			<div class="search-td-label"  >标题：</div>
			<div class="search-td-combobox-condition" >
				<input type="text" id = "title" name="title" >
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
