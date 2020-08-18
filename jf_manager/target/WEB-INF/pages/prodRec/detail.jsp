<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/common-tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>首页产品推荐</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript">
		 var _obj; 
          $(function ()
        {    _obj = $("#_producttype").ligerComboBox({
                 width: 180,
                selectBoxWidth: 200,
                selectBoxHeight: 200, valueField: 'text',treeLeafOnly:false,
                valueField : 'id',
                isScroll:true,
                //initValue : '1',
                tree: { url: '${pageContext.request.contextPath}/prodRec/productTypedata.shtml?time' + new Date(), checkbox: false, ajaxType: 'get' ,isExpand: 2}
            });
        });  
         
         
      
       
       function change() {
        $("#producttype").val(_obj.getValue());
       }
        

 var orgcomm={
     gridManager:null,
     ligerGrid:{ pageSize:20,
	             pageSizeOptions:[20, 50, 100, 200, 300],
	             url:"${pageContext.request.contextPath}/prodRec/search.shtml"
	        
               },
     btnItems: [
                { text: '推荐', icon: 'config',  dtype:'ajax', checkType:'mult',   click: itemclick, url:"/prodRec/saveRecommend.shtml?CATALOG_ID=${CATALOG_ID}&ids=",seqId:"PRODUCT_ID" }
               ],          
     initdataPage:function(){
            //工具条
            $("#toptoolbar").ligerToolBar({ items:orgcomm.btnItems  });
            
            //搜索
            $("#searchbtn").ligerButton({ click: function ()
            {
                if (!orgcomm.gridManager) return;
                var jsonParams=commUtil.formToJson("dataForm");
                  orgcomm.gridManager.setOptions({
                   parms:jsonParams 
                   }); 
                orgcomm.gridManager.loadServerData(jsonParams,null);
              
             }
            // $("#searchbtn").click();
          });
          
          //表格
          $("#maingrid").ligerGrid({
                columns: [  { display: '产品标识', name: 'PRODUCT_ID', align: 'left', width: 120 },
                { display: '产品名称', name: 'PRODUCT_NAME', minWidth: 60 },
                { display: '产品类型', name: 'PRODUCT_TYPE_NAME',minWidth: 60}, 
                { display: '产品品牌', name: 'BRAND_NAME', minWidth: 140 }, 
                { display: '企业名称', name: 'ENTERPRISE_NAME', minWidth: 140 }
		                ], 
                method :'post',
                dataAction: 'server',
                data: "",
                url:orgcomm.ligerGrid.url ,
                sortName:'ROLE_NAME',
                width: '100%', height: '100%', pageSize: orgcomm.ligerGrid.pageSize,pageSizeOptions:orgcomm.ligerGrid.pageSizeOptions ,rownumbers:false,
                checkbox : true,
                //应用灰色表头
                cssClass: 'l-grid-gray', 
                heightDiff: -6
            });
            orgcomm.gridManager = $("#maingrid").ligerGetGridManager();
            $("#pageloading").hide();
     
     }
   
   }
 var commUtil={
     ajaxSuccessCode:200,
     ajaxSuccessMsg:"操作成功",
     ajaxErrorMsg:"系统异常请稍后再试",
	 toDoAjaxForm: function(formName){
			       var dform=$("#"+formName);
				   $.ajax({ //ajax提交
							type:'POST',
							url: dform.attr("action"),
							data:dform.serializeArray(),
							dataType:"json",
							cache: false,
							success: function(json){
							  if(json==null || json.statusCode!=commUtil.ajaxStatusCode){
							    commUtil.alertError(json.message);
							  }else{
							    commUtil.alertSuccess(commUtil.ajaxSuccessMsg);
							  }
							},
							error: function(e){
							 commUtil.alertError(commUtil.ajaxErrorMsg);
							}
				    });
	     },
	     toDoAjax: function(url,jsonParms){
				   $.ajax({ //ajax提交
							type:'POST',
							url:'${pageContext.request.contextPath}' +url,
							data:jsonParms,
							dataType:"json",
							cache: false,
							success: function(json){
							  if(json==null || json.statusCode!=200){
							    commUtil.alertError(json.message);
							  }else{
							    commUtil.alertSuccess(commUtil.ajaxSuccessMsg);
							  }
							},
							error: function(e){
							 commUtil.alertError(commUtil.ajaxErrorMsg);
							}
				    });
	     },
	     openUrlWin:function(winTile,url,params)  {  
		            $.ligerDialog.open({
		                height:600,
		                width: 850,
		                title : winTile,
		                url: "${pageContext.request.contextPath}"+url, 
		                showMax: false,
		                showToggle: true,
		                showMin: false,
		                isResize: true,
		                slide: false,
		                data:params ,
		                //自定义参数
		                myDataName: $("#txtValue").val()
		            });
           },
           alert:function(content){
             $.ligerDialog.alert(content);
           },
           alertSuccess:function(content){
            $.ligerDialog.success(content,function() {
            	$("#searchbtn").click();
				 parent.$("#searchbtn").click();
						 });
           },
           alertError:function(content){
            $.ligerDialog.warn(content);
           }, 
		   formToJson:function (form){
			 	var paramsArray = $("#"+form).serializeArray();
			 	return paramsArray;
		   }
    
    
    }     
            
   
 function itemclick(item){ 
   if(item.dtype){ 
       switch (item.dtype) { 
         case "add": //新增类：无列表选中行， 打开新增窗口
              commUtil.openUrlWin(item.text ,item.url,{});
              return ;
         //打开新链接窗口 多用于如 新增或修改功能等   ,单选列表选中行为参
         case "win": 
             //无参-打开新链接窗口
              if(item.seqId==null|| item.seqId==''||item.seqId==undefined){
	              commUtil.openUrlWin(item.text ,item.url,{});
	              return ;
              }
              //有参（行选中值）-打开新链接窗口
             var rowsdata = orgcomm.gridManager.getCheckedRows();
             var str = "";
             var params={};
             $(rowsdata).each(function ()
             {
                 str = this[item.seqId] ;
             });
             if (!rowsdata.length) commUtil.alert('请选择行');
             else{
              commUtil.openUrlWin(item.text ,item.url+str,params);
              return ;
              }
         case "ajax"://ajax 可多选或单选中行,多用于删除行记录
             var data = orgcomm.gridManager.getCheckedRows();
             if (data.length == 0)
                 commUtil.alert('请选择行');
             else
             {  var params={};
                var checkedIds = [];
                 $(data).each(function ()
                 {    
                     checkedIds.push(this[item.seqId]);
                 });
                 $.ligerDialog.confirm('确定'+item.text+'?', function (operType)
                 {  
                  if(operType==true){
                     commUtil.toDoAjax(item.url+checkedIds.join(','),params);
                     }
                 }); 
             }
             return;
            }   
        }
    }
    $(function () { 
      orgcomm.initdataPage();
    });

    </script>
 
</head>

<body style="padding:0px; overflow:hidden;">

	<div class="l-loading" style="display: block" id="pageloading"></div>
		<form id="dataForm" runat="server" >
			<div id="topmenu"></div>
			<div id="toptoolbar"></div>
			<div class="l-panel-search">
				<div class="l-panel-search-item">
					产品类型：
				</div>
				<div class="l-panel-search-item">
					<input type="text" id="_producttype" name="_producttype" onchange="change();"/>
				
				</div>
				<div class="l-panel-search-item">
					产品名称：
				</div>
				<div class="l-panel-search-item"> 
					<input type="text" id="productname" name="productname" value="${map.productname }"/>
				</div>
			
				<div class="l-panel-search-item">
					<div id="searchbtn" style="width: 80px;">
						搜索
					</div>
				</div>
			</div>
			<div id="maingrid" style="margin: 0; padding: 0"></div>
					<input type="hidden" id="producttype" name="producttype" />
		</form>
		<div style="display: none;">
		</div>
	</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</body>
</html>
