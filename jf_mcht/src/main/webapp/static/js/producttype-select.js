
function getProductType1List(parentId)
{   
	var path = ctx+'/productType/getMchtProductTypes';
	jQuery.ajax( {
  		url : path,
  		type : 'POST',
  		dataType : 'json',
  		cache : false,
  		async : false,
  		data : { parentId:parentId},
  		timeout : 30000,
  		success : function(data) 
  		{  var sel = $(".productType1-select");
				sel.empty();
				sel.append("<option value=\"" + "\">--请选择--</option>");
				
				$.each(data.returnData, function(index, item) {
					sel.append("<option value=\"" + item.id + "\">" + item.name + "</option>");
				});
  	      
  		},
		error : function() 
		{
			alert('操作超时，请稍后再试！');
		}
  	}); 
}
function getProductType2List(parentId)
{
	if(typeof(parentId)!="undefined"){
		var path = ctx+'/productType/getMchtProductTypes';
		jQuery.ajax( {
	  		url : path,
	  		type : 'POST',
	  		dataType : 'json',
	  		cache : false,
	  		async : false,
	  		data : { parentId : parentId},
	  		timeout : 30000,
	  		success : function(data) 
	  		{  var sel = $(".productType2-select");
					sel.empty();
					sel.append("<option value=\"" + "\">--请选择--</option>");
					
					$.each(data.returnData, function(index, item) {
						sel.append("<option value=\"" + item.id + "\">" + item.name + "</option>");
					});
	  	      
	  		},
			error : function() 
			{
				alert('操作超时，请稍后再试！');
			}
	  	});
	}
	
 
}
function getProductType3List(parentId)
{
	if(typeof(parentId)!="undefined"){
	var path = ctx+'/productType/getMchtProductTypes';
	jQuery.ajax( {
  		url : path,
  		type : 'POST',
  		dataType : 'json',
  		cache : false,
  		async : false,
  		data : { parentId : parentId},
  		timeout : 30000,
  		success : function(data) 
  		{  var sel = $(".productType3-select");
				sel.empty();
				sel.append("<option value=\"" + "\">--请选择--</option>");
				
				$.each(data.returnData, function(index, item) {
					sel.append("<option value=\"" + item.id + "\">" + item.name + "</option>");
				});
  	      
  		},
		error : function() 
		{
			alert('操作超时，请稍后再试！');
		}
  	}); 
	}
}
function  onchangeProductType1()
{
	   $(".productType2-select").empty();
	   $(".productType3-select").empty();
	   getProductType2List($(".productType1-select").val()); 
}
function  onchangeProductType2()
{ 
	   $(".productType3-select").empty();  
	   getProductType3List($(".productType2-select").val()); 
}