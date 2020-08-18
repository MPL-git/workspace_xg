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
 
 <script type="text/javascript">
 function itemclickX() {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 500,
		title: "发布信息",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/infomation/editinfo.shtml?inforId="+"&seeInfoInfo=${seeInfoInfo}",
		showMax:  true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	}).max();
 }
 function editInfo(id) {
		$.ligerDialog.open({
		height: $(window).height() - 40,
		width: $(window).width() - 40,
		title: "编辑信息",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/infomation/editinfo.shtml?inforId=" + id+"&seeInfoInfo=${seeInfoInfo}",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	}).max();
	}
 
	function gridRefresh() {
	    if (listModel.gridManager) {
	        var gridparms = [];
	        gridparms.push({ name: "staff", value: "self" });
	        listModel.gridManager.loadServerData(gridparms);
	    }
	}

	//批量删除
	function deletInfo(url){
		   $.ajax({ //ajax提交
					type:'POST',
					url:'${pageContext.request.contextPath}' +"/infomation/cancleInfo.shtml?Ids="+url+"&status=1",
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

	 //已读商家列表展示
	 function showReadMcht(id) {
		 $.ligerDialog.open({
			 height: $(window).height() - 100,
			 width: $(window).width() - 740,
			 title: "已读商家",
			 name: "INSERT_WINDOW",
			 url: "${pageContext.request.contextPath}/infomation/showReadMcht.shtml?id="+id,
			 showMax: true,
			 showToggle: false,
			 showMin: false,
			 isResize: true,
			 slide: false,
			 data: null
		 });
	 }


		   
		   
	  function changeInfostatu(ids){
		  $.ligerDialog.open({
				height: 260,
				width: 400,
				title: "修改状态",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/infomation/changeStatupage.shtml?inforIds=" + ids,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
	  }
		   
	function changecatalog(ids){
		 $.ligerDialog.open({
				height: 360,
				width: 400,
				title: "转移栏目",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/infomation/changecatalogpage.shtml?inforIds=" + ids+"&seeInfoInfo=${seeInfoInfo}",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
	}
	  
 //添加和修改品牌
 var listConfig={
	  
      url:"/infomation/data.shtml?seeInfoInfo=${seeInfoInfo}",
   
      btnItems:[{ text: '发布信息', icon: 'add', dtype:'win',  click:itemclickX},
                {text: '批量删除', icon: 'delete', click: function(yes) {
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
                              $.ligerDialog.confirm('确定删除?', function (yes)
                              {
                            	  if (yes){
                            		  deletInfo(str);  
                            	  }
                              }); 
                          }
                          return;
      			  }},
      			{text: '批量修改状态', icon: 'modify', click: function(yes) {
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
                               changeInfostatu(str);
                            }
                            return;
        			  }},
        			  {text: '批量转移栏目', icon: 'modify', click: function(yes) {
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
                                    $.ligerDialog.confirm('确定修改?', function (yes)
                                    {
                                    	if (yes){
                                    		changecatalog(str);
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
						    html.push("<a href=\"javascript:editInfo(" + rowdata.id + ");\">编辑</a>&nbsp;&nbsp;");
						    html.push("<a href=\"javascript:deletInfo(" + rowdata.id + ");\">删除</a>&nbsp;&nbsp;");
						    console.log(rowdata.catalogId ==3);
							if (rowdata.catalogId ==3){
							html.push("<a href=\"javascript:showReadMcht(" + rowdata.id + ");\">已读商家</a>&nbsp;&nbsp;");
							}
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
  };
  
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
              tree: { url: '${pageContext.request.contextPath}/infomation/getCatalogTree.shtml?seeInfoInfo=${seeInfoInfo}', checkbox: false, ajaxType: 'get', idFieldName: 'id',textFieldName:'frontName',parentIDFieldName:'parentId',isExpand:2}
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
			 <div class="search-td-label" >信息类别:</div>
			 <div class="search-td-condition" >
				<select id="status" name="status" class="status">
					<option value="1">正常信息</option>
					<option value="2">未使用信息</option>
				</select>
		 	 </div>
			 </div>
			 
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
