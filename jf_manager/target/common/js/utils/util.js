//iframe自适应高度
function setWinHeight(obj){ 
    var win=obj; 
	if(document.getElementById){ 
	if(win&&!window.opera){ 
		if(win.contentDocument && win.contentDocument.body.offsetHeight){
		  win.height = win.contentDocument.body.offsetHeight;
		}else if(win.Document && win.Document.body.scrollHeight){
		win.height = win.Document.body.scrollHeight;
		}
	}
   }
}

/** 
 * IFRAME自适应高度-[研发调用]
 * @author:huangcp
 */
function autoHeight(obj) {
	var d_height=window.frames[obj.id].document.body.scrollHeight;
	//obj.height = window.frames[obj.id].document.body.scrollHeight;	
	var w = window.frames[obj.id].document.body.scrollWidth;
	var ifw = obj.scrollWidth;
	if (w > ifw) {
		d_height=obj.height * 1 + 15;
	}
	$("#"+obj.id).attr("style","height:"+d_height+"px;"+"width:"+(w+15)+"px;");
 
}
//设置cookie
function setCookie(name,value){
    var Days = 360; 
    var exp  = new Date();    //new Date("December 31, 9998");
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

//获取cookie
function getCookie(name){
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
     if(arr != null) return unescape(arr[2]); return null;
}

//删除cookie
function delCookie(name){
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}


/**
 *check All  checkBox
 */
function dcheckAll(obj,dName, chkname) {
	$("input["+dName+"='" + chkname + "']").attr("checked",obj.checked);
}

/**
 * set checkboxs value 
 */
function dcheckValue(dName,vName){  
      var values="";
      var firstFlag=0;
      var blackName = document.getElementsByName(dName);   
      for(var i = 0; i < blackName.length; i++){   
        if(blackName[i].checked == true){   
    	 var a = blackName[i].value;
    	   if(firstFlag==0){
    	    values=a;
    	    firstFlag++;
    	   }else{
     	    values+=","+a; }
        }   
       } 
	     $("#"+vName).val(values);
 }

function testNumber(num){ 
	  var reg = /^[1-9]\d*$/;
	  return reg.test(num);
}


