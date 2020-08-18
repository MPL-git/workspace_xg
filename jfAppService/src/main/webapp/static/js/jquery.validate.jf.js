/**
 * jquery.validate 自定义规则
 *
 */

	//手机号
   $.validator.addMethod("mobile", function(value, element) {   
	   var reg=/^1\d{10}/;
	   return reg.test(value);
   }, "手机号格式错误");
   
   
   //单选框必选
   $.validator.addMethod("radioRequired", function(value, element) {
	   var radioName=$(element).attr("name");
	   var hasSelect=false;
	   $($("input[type=radio][name="+radioName+"]")).each(function(){
		    if($(this).attr("checked")){
		    	hasSelect=true;
		    }
		  });
	   return hasSelect;
   }, "请选择");
