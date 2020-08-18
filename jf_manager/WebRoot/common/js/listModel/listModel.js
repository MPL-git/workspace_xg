
 var listModel={
     gridManager:null,
     ligerGrid:{ pageSize:listConfig.pageSize || 20,
	             pageSizeOptions:[10, 15, 20, 50, 100],
	             url:contextPath +listConfig.url
               },
     btnItems: listConfig.btnItems,
     /**
     * 模块功能容器名称配置 -可以不设置
     *
     * toolBarName   按钮工具条名称  默认：toptoolbar
     * searchBtnName 搜索按钮名称    默认：searchbtn 
     * fromName      提交FORM名     默认：dataForm
     * listGridName  清单列表DIV名   默认：maingrid
     */
     container:{
		        fromName:"dataForm",
		        toolBarName:"toptoolbar",
		        searchBtnName:"searchbtn",
		        listGridName:"maingrid"
      } ,
     setContainer:function(){
			      var dcontainer=listConfig.container;
			      if(dcontainer!=null&&dcontainer!=undefined && dcontainer!=''){
                       listModel.container =listConfig.container;
			     /*       var dfrom=dcontainer.fromName+"";
				        var dtoolBar=dcontainer.toolBarName+"";
			            var dlistGrid=dcontainer.listGridName+"";
			            var dsearchBtn=dcontainer.searchBtnName+"";
			            if(dfrom!=null && dfrom!=undefined && dfrom!= '' ){listModel.container.fromName=dfrom+"";}
			            if(dtoolBar!=null && dtoolBar!=undefined && dtoolBar!= ''){listModel.container.toolBarName=dtoolBar+"";}
			            if(dlistGrid!=null && dlistGrid!=undefined && dlistGrid!= ''){listModel.container.searchBtnName=dlistGrid+"";}
			            if(dsearchBtn!=null && dsearchBtn!=undefined && dsearchBtn!= ''){listModel.container.listGridName=dsearchBtn+"";} */
			      }
     },
     initdataPage:function(){
             listModel.setContainer();
            //工具条
            $("#"+listModel.container.toolBarName+"").ligerToolBar({ items:listModel.btnItems  });
            //搜索
            $("#"+listModel.container.searchBtnName+"").ligerButton({ click: function ()
            {		   
                if (!listModel.gridManager) return;
                
                if(typeof(listConfig.listGrid.beforeSearch)!="undefined"&&!listConfig.listGrid.beforeSearch()){
                	return;
                }
                
                var jsonParams=commUtil.formToJson(listModel.container.fromName+"");
                var pageSizeSearch = $("select[name='rp']").val();
                jsonParams.push({"name":"pageSizeSearch","value":pageSizeSearch});
                listModel.gridManager.setOptions({
                   parms:jsonParams 
                   }); 
                listModel.gridManager.loadServerData(jsonParams,null);
                listModel.gridManager.changePage("first");
             }
          });
          var initParams=commUtil.formToJson(listModel.container.fromName+"");
          
          if(typeof(listConfig.listGrid.onAfterEdit)=="undefined"){
       	   listModel.ligerGrid.onAfterEdit=defaultOnAfterEdit;
          }else{
       	   listModel.ligerGrid.onAfterEdit=listConfig.listGrid.onAfterEdit;
          }
          if(typeof(listConfig.listGrid.onAfterShowData)=="undefined"){
       	   listModel.ligerGrid.onAfterShowData=defaultOnAfterShowData;
          }else{
       	   listModel.ligerGrid.onAfterShowData=listConfig.listGrid.onAfterShowData;
          }
          
          //表格
          $("#"+listModel.container.listGridName+"").ligerGrid({
                columns:listConfig.listGrid.columns, 
                method :'post',
                dataAction: 'server',
                data: "",
                parms:initParams,
                url:listModel.ligerGrid.url ,
                width: '100%', height: '100%', pageSize: listModel.ligerGrid.pageSize,pageSizeOptions:listModel.ligerGrid.pageSizeOptions ,
                rownumbers:listConfig.listGrid.showRownumber,
                checkbox : listConfig.listGrid.showCheckbox,
                enabledEdit: true,
                onAfterEdit:listModel.ligerGrid.onAfterEdit,
                groupColumnName:listConfig.listGrid.showGroupColumnName,
                groupColumnDisplay:listConfig.listGrid.showGroupColumnDisplay,
                isScroll:listConfig.listGrid.showIsScroll,
                //应用灰色表头
                cssClass: 'l-grid-gray', 
                heightDiff: -6,
                onAfterShowData: listModel.ligerGrid.onAfterShowData
            });
            listModel.gridManager = $("#"+listModel.container.listGridName+"").ligerGetGridManager();
            $("#pageloading").hide();
     
     }
   
 }  
   $(function () {
      listModel.initdataPage();
    }); 
  function defaultOnAfterEdit(){
  }
  function defaultOnAfterShowData() {
     $(".l-grid-row-cell-inner").css("height", "auto");
     var i = 0;
     $("tr",".l-grid2","#"+listModel.container.listGridName+"").each(function() {
         $($("tr",".l-grid1","#"+listModel.container.listGridName+"")[i]).css("height",$(this).height());
         i++;
     });
  }
  