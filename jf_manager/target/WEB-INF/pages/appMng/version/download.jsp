<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script type="text/javascript">
        // 获取终端的相关信息
      
        
var browser = {
    versions: function () {
        var u = navigator.userAgent, app = navigator.appVersion;
        return {         //移动终端浏览器版本信息
            trident: u.indexOf('Trident') > -1, //IE内核
            presto: u.indexOf('Presto') > -1, //opera内核
            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
            mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
            iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
            iPad: u.indexOf('iPad') > -1, //是否iPad
            webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
        };
    }(),
    language: (navigator.browserLanguage || navigator.language).toLowerCase()
}
        
        
        var Terminal = {
            // 辨别移动终端类型
            platform : function(){
                var u = navigator.userAgent, app = navigator.appVersion;
                return {
                    // android终端或者uc浏览器
                    android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1,
                    // 是否为iPhone或者QQHD浏览器
                    iPhone: u.indexOf('iPhone') > -1 ,
                    // 是否iPad
                    iPad: u.indexOf('iPad') > -1
                };
            }(),
            // 辨别移动终端的语言：zh-cn、en-us、ko-kr、ja-jp...
            language : (navigator.browserLanguage || navigator.language).toLowerCase()
        }
 $(function() {
 		var theUrl =contextPath+'/appMng/version/download.shtml?appType=';
        // 根据不同的终端，跳转到不同的地址
          
         // theUrl = theUrl+'1';
          var isweixin=check();
          if(isweixin){
         	open2(theUrl);
          }else{
           //$("#welcome2Div").show();
             open(theUrl);
            
          }
    
        
     });
     
        
     
     function open(theUrl){
     
     	 if(Terminal.platform.android){
            $("#welcomeDiv").hide();
            $("#welcome2Div").show();
            theUrl = theUrl+'2';
            location.href = theUrl;
          	
         }else if(Terminal.platform.iPhone){
        	 theUrl='https://itunes.apple.com/us/app/lin-li-yi-zhan-shang-hu-ban/id1091900123?l=zh&ls=1&mt=8';
        	 location.href = theUrl;
         }else{
         
           $("#welcomeDiv").hide();
           $("#welcome2Div").show();
           theUrl = theUrl+'2';
           location.href = theUrl;
         }  
     
     
     }
     
     
        
     
     function open2(theUrl){
     
     	 if(Terminal.platform.android){
            $("#welcomeDiv").show();
            $("#welcome2Div").hide();
            theUrl = theUrl+'2';
            location.href = theUrl;
          	
         }else if(Terminal.platform.iPhone){
        	 
        	 theUrl='https://itunes.apple.com/us/app/lin-li-yi-zhan-shang-hu-ban/id1091900123?l=zh&ls=1&mt=8';
        	 $("#welcomeDiv").show();
             $("#welcome2Div").hide();
             location.href = theUrl;
         }else{
         
           $("#welcomeDiv").hide();
           $("#welcome2Div").show();
         }  
     
     
     }
     
     
     
     
     function check(){
     if (browser.versions.mobile) {//判断是否是移动设备打开。browser代码在下面
        var ua = navigator.userAgent.toLowerCase();//获取判断用的对象
        if (ua.match(/MicroMessenger/i) == "micromessenger") {
               return true;
        }
        if (ua.match(/WeiBo/i) == "weibo") {
                //在新浪微博客户端打开
        }
        if (ua.match(/QQ/i) == "qq") {
                //在QQ空间打开
        }
        if (browser.versions.ios) {
                //是否在IOS浏览器打开
        } 
        if(browser.versions.android){
                //是否在安卓浏览器打开
        }
} else {
        //否则就是PC浏览器打开
}
     return false;
     
     } 
    </script>    
    
<body>
<div   style="display: none;" id="welcomeDiv">
<img src="${pageContext.request.contextPath}/images/webApp/welcome.png"  height="100%" width="100%" ></img>
</div>

<div   style="display: none;" id="welcome2Div">
<img src="${pageContext.request.contextPath}/images/webApp/welcome2.png"  height="100%" width="100%" ></img>
</div>

<!-- <div class="vintage">请点击右上方在浏览器中打开</div> -->

</body>
