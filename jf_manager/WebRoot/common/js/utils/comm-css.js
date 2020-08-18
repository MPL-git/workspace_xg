/**
 * 清单查询模块-数据表格 公共样式
 * （隔行样式与选中 滑过样式） 
 * @author huangcp
 */
$(document).ready(function(){
	var dataTab=$(".querytab");
	if(dataTab!=null && dataTab!=undefined){
     $(".querytab tr:even").addClass("orangebg");
     initQueryTabCss();
     }
  });
      
  function initQueryTabCss(){
     $(".querytab tr").mouseover(function(){
         $(this).addClass("keyMoveoverBg").siblings().removeClass("keyMoveoverBg");
     });
     $(".querytab tr").mouseout(function(){
         $(this).removeClass("keyMoveoverBg");
     });
     $(".querytab tr").click(function(){
         $(this).addClass("keyDownBg").siblings().removeClass("keyDownBg");
     });
  }