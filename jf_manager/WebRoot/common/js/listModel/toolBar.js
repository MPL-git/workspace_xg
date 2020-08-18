/**
* 清单模块-任务工具条事件（如 增、删、改）
*/
function  itemclick(item){ 
     if(item.dtype){ 
        switch (item.dtype) {
               //打开新链接窗口 多用于如 新增或修改功能等  
		       case "win": 
		             //无参
		             if(item.seqId==null|| item.seqId==''||item.seqId==undefined){
			              commUtil.openUrlWin(item.text ,item.url,{},item.width,item.height);
			              return ;
		              }
		              
		             //有参（单多行选中值)
		             var checkedData = listModel.gridManager.getCheckedRows();
		             if(checkedData.length ==0){
		                 commUtil.alert('请选择行');
		                 return false;
		             }
		             var checkedType=item.checkType;
		             if(checkedType==null || checkedType==''||checkedType==undefined){checkedType='single';}
		             if(checkedType=='single'){
			             if (checkedData.length >1){
			                 commUtil.alert('对不起, '+item.text+'不能选择多行');
			                 return false;
			             }
		             }
		             
		             var params={};
		             var checkedIds = [];
	                 $(checkedData).each(function (){ checkedIds.push(this[item.seqId]); });
	                 commUtil.openUrlWin(item.text ,item.url+checkedIds.join(','),params);
		             
		       return;
		       case "ajax"://ajax 可多选或单选中行,多用于删除行记录
		             //无参
		             if(item.seqId==null|| item.seqId==''||item.seqId==undefined){
			              commUtil.openUrlWin(item.text ,item.url,{});
			              return ;
		              }
		              
		             //有参（单多行选中值)
		             var checkedData = listModel.gridManager.getCheckedRows();
		             if(checkedData.length ==0){
		                 commUtil.alert('请选择行');
		                 return false;
		             }
		             var checkedType=item.checkType;
		             if(checkedType==null || checkedType==''||checkedType==undefined){checkedType='mult';}
		             if(checkedType=='single'){
			             if (checkedData.length >1){
			                 commUtil.alert('对不起, '+item.text+'不能选择多行');
			                 return false;
			             }
		             }
		             
		             var params={};
		             var checkedIds = [];
		              $(checkedData).each(function (){   checkedIds.push(this[item.seqId]);});
		              $.ligerDialog.confirm('确定'+item.text+'?', function (operType)
		              {  
		               if(operType==true){
		                  commUtil.toDelAjax(item.url+checkedIds.join(','),params);
		                  }
		              });
		       return;
        }
     }
   }