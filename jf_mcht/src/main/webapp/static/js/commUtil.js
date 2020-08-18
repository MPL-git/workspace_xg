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
							url:contextPath +url,
							data:jsonParms,
							dataType:"json",
							cache: false,
							success: function(json){
							  if(json==null || json.statusCode!=200){
							    commUtil.alertError(json.message);
							  }else{
								//alert($("body", window.parent.document)[0].outerHTML);
								commUtil.alertSuccess(commUtil.ajaxSuccessMsg, function() {
									var name = window.parent.listConfig.container.searchBtnName; 
									window.parent.$("#" + name).click();   
									frameElement.dialog.close();
								});
								//var name = window.parent.listConfig.container.searchBtnName;
								//window.parent.$("#" + name).click();
							  }
							},
							error: function(e){
							 commUtil.alertError(commUtil.ajaxErrorMsg);
							}
				    });
	     },
	      toDelAjax: function(url,jsonParms){
				   $.ajax({ //ajax提交
							type:'POST',
							url:contextPath +url,
							data:jsonParms,
							dataType:"json",
							cache: false,
							success: function(json){
							  if(json==null || json.statusCode!=200){
							    commUtil.alertError(json.message);
							  }else{
								//alert($("body", window.parent.document)[0].outerHTML);
								commUtil.alertSuccess(commUtil.ajaxSuccessMsg, function() {
									var name = window.listConfig.container.searchBtnName; 
									window.$("#" + name).click(); 
								});
								//var name = window.parent.listConfig.container.searchBtnName;
								//window.parent.$("#" + name).click();
							  }
							},
							error: function(e){
							 commUtil.alertError(commUtil.ajaxErrorMsg);
							}
				    });
	     },
	     openUrlWin:function(winTile,url,params,width,height)  {  
	    	 		
	                var dwidth=width;
	                var dheight=height;
	                
	                if(dwidth==null||dwidth==''||dwidth==undefined){dwidth=850;}
	                if(dheight==null||dheight==''||dheight==undefined){dheight=500;}
		            $.ligerDialog.open({
		                height:dheight,
		                width: dwidth,
		                title : winTile,
		                url: contextPath+url, 
		                showMax: true,
		                showToggle: true,
		                showMin: false,
		                isResize: true,
		                slide: true,
		                data:params ,
		                //自定义参数
		                myDataName: $("#txtValue").val()
		            });
           },
           alert:function(content){
             $.ligerDialog.alert(content);
           },
           alertSuccess:function(content, fun){
            $.ligerDialog.success(content, '提示', fun);
            //frameElement.dialog.close();
           },
           alertError:function(content){
            $.ligerDialog.warn(content);
           }, 
		   formToJson:function (form){
			 	var paramsArray = $("#"+form).serializeArray();
			 	return paramsArray;
		   }
    }   