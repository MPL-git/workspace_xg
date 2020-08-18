<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>管理平台</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
    <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script> 
    <script src="${pageContext.request.contextPath}/liger/lib/json2.js"></script>
    <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/glyphicon.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
    <!-- 主页独有样式 -->
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
     <style type="text/css">
      a{text-decoration:none;}
      table tr td{border-top: 1px solid #e9e9e9;border-left: 1px solid #e9e9e9;}
      table tr td{border-right: 1px solid #e9e9e9;}
      table tr td{border-bottom: 1px solid #e9e9e9;}
      table tr td{border-top-left-radius: 5px;}
      table tr td{border-top-right-radius: 5px;}
      table tr td{border-bottom-left-radius: 5px;}
      table tr td{border-bottom-right-radius: 5px;}
     </style>
    <script type="text/javascript">
    function showMchtMenu(staffID) {
    	$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/orgMng/menuJurisdiction.shtml',
			data: {staffID : staffID},
			dataType: 'json',
			success: function(data) {
				if(data == null || data.code != 200){
			    	commUtil.alertError(data.msg);
			  	}else {
	          		   var html=[];		
	          		   html.push("<table style='font-size: 12px;width:230px;margin-left:20px;text-align:center;vertical-align:middle;'>");   
					   html.push("<tr>");
					   html.push("<td>入驻申请</td>");
					   html.push("<td style='padding-top:5px;padding-bottom:5px;line-height:27px;'>");
					      if (!data.Menu_307 && !data.Menu_422 && !data.Menu_423 && !data.Menu_308) {
					    	  html.push("<div style='color:red'>暂无菜单列表</div>");
						  }else{
							  
						   if (data.Menu_307) {
							   html.push("<a href='javascript:;' onclick='';>主管分配专员"+"("+data.Menu307_count+")"+"</a>");
							   html.push("<br>");
						    }
						   if (data.Menu_422) {
							   html.push("<a href='javascript:;' onclick=''>招商评估商家"+"("+data.Menu422_conut+")"+"</a>");
							   html.push("<br>");
						    }
						   if (data.Menu_423) {
							   html.push("<a href='javascript:;' onclick=''>主管确认是否合作"+"("+data.Menu423_conut+")"+"</a>");
							   html.push("<br>");
							}
							if (data.Menu_308) {
								html.push("<a href='javascript:;' onclick=''>入驻待提交"+"("+data.Menu308_count+")"+"</a>");
							}
							  
						  }
						   
					  html.push("</td>");				
					  html.push("</tr>");
					 
					  html.push("<tr>");
					  html.push("<td>提交入驻</td>");			
					  html.push("<td style='padding-top:5px;padding-bottom:5px;line-height:27px;'>");
					  if (!data.Menu_434 && !data.Menu_311 && !data.Menu_261) {
						  html.push("<div style='color:red'>暂无菜单列表</div>");
					  }else{
						  
					   if (data.Menu_434) {
					       html.push("<a href='javascript:;' onclick=''>招商确认合作信息"+"("+data.Menu434_count+")"+"</a>");
					       html.push("<br>");
					   }
					   if (data.Menu_311) {
						   html.push("<a href='javascript:;' onclick=''>入驻资质审核"+"("+data.Menu311_count+")"+"</a>");
						   html.push("<br>");
					   }
					   if (data.Menu_261) {
						   html.push("<a href='javascript:;' onclick=''>银行账号审核"+"("+data.Menu261_count+")"+"</a>");
					   }
						  
					  }
					   
					  html.push("</td>");
					  html.push("</tr>");
					 
					 html.push("<tr>");
					 html.push("<td>合同审核</td>");			
					 html.push("<td style='padding-top:5px;padding-bottom:5px;line-height:27px;'>");
					  if (!data.Menu_276 && !data.Menu_314) {
						  html.push("<div style='color:red'>暂无菜单列表</div>");
					  }else{
						  
					   if (data.Menu_312) {
					      html.push("<a href='javascript:;' onclick=''>线上合同审核"+"("+data.Menu312_count+")"+"</a>");
					      html.push("<br>");
					   }   
					   if (data.Menu_314) {
					       html.push("<a href='javascript:;' onclick=''>商家资料归档审核"+"("+data.Menu314_count+")"+"</a>");
					   }	  
						  
					  }
						  
					  html.push("</td>");
					  html.push("</tr>");
					 
					 html.push("<tr>");
					 html.push("<td>商家缴费</td>");			
					 html.push("<td style='padding-top:5px;padding-bottom:5px;line-height:27px;'>");	
					 if (!data.Menu_435) {
						 html.push("<div style='color:red'>暂无菜单列表</div>");
					 }else{
						 
					   if (data.Menu_435) {
					      html.push("<a href='javascript:;' onclick=''>入驻缴费"+"("+data.Menu435_count+")"+"</a>");
					   }	  
						 
					 }
					 html.push("</td>");
					 html.push("</tr>");
					 
					 html.push("<tr>");
					 html.push("<td>店铺开通</td>");			
					 html.push("<td style='padding-top:5px;padding-bottom:5px;line-height:27px;'>");
					 if (!data.Menu_313) {
						 html.push("<div style='color:red'>暂无菜单列表</div>");
					 }else{
						 
					   if (data.Menu_313) {
					      html.push("<a href='javascript:;' onclick=''>店铺待开通"+"("+data.Menu313_count+")"+"</a>");
					   }	 
						 
					 }
					 html.push("</td>");
					 html.push("</tr>");
					 html.push("</table>");
					 
					 $(".roleMng-mchtmenu-div").html(html.join(""));
			  	}
			},
			error: function(e){
			 	commUtil.alertError("系统异常请稍后再试！");
			}
		});
    	
    }
    
    
    function showSystemMenu(staffID) {
    	$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/orgMng/menuJurisdiction.shtml',
			data: {staffID : staffID},
			dataType: 'json',
			success: function(data) {
				if(data == null || data.code != 200){
			    	commUtil.alertError(data.msg);
			  	}else {
	          		   var html=[];		
	          		   html.push("<table style='font-size: 12px;width:230px;text-align:center;vertical-align:middle;'>");		
					   html.push("<tr>");		
					   html.push("<td>工单</td>"); 
					   html.push("<td style='padding-top:5px;padding-bottom:5px;line-height:27px;'>");
					   if (!data.Menu_551 && !data.Menu_552) {
						   html.push("<div style='color:red'>暂无菜单列表</div>");
					  }else{
						  
					   if (data.Menu_551) {
					      html.push("<a href='javascript:;' onclick=''>我创建的工单"+"("+data.Menu551_count+")"+"</a>");
					      html.push("<br>");
					   }
					   if (data.Menu_552) {
					       html.push("<a href='javascript:;' onclick=''>我处理的工单"+"("+data.Menu552_count+")"+"</a>");
					   }
						  
					  }
						   
					  html.push("</td>");				
					  html.push("</tr>");
					 
					  html.push("<tr>");
					  html.push("<td>意见反馈</td>");			
					  html.push("<td style='padding-top:5px;padding-bottom:5px;line-height:27px;'>");
					  if (!data.Menu_534 && !data.Menu_535) {
						  html.push("<div style='color:red'>暂无菜单列表</div>");
					  }else{
						  
					   if (data.Menu_534) {
					      html.push("<a href='javascript:;' onclick=''>我的意见反馈"+"("+data.Menu534_count+")"+"</a>");
					      html.push("<br>");
					   }		   
					   if (data.Menu_535) {
					       html.push("<a href='javascript:;' onclick=''>员工意见反馈"+"("+data.Menu535_count+")"+"</a>");
					   }
						  
					  }
						  
					  html.push("</td>");
					  html.push("</tr>");
					  html.push("</table>");					 
					 $(".roleMng-systemmenu-div").html(html.join(""));
			  	}
			},
			error: function(e){
			 	commUtil.alertError("系统异常请稍后再试！");
			}
		});
    	
    }
    
    
    function showInterventionMenu(staffID) {
    	$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/orgMng/menuJurisdictionStatistics.shtml',
			data: {staffID : staffID},
			dataType: 'json',
			success: function(data) {
				if(data == null || data.code != 200){
			    	commUtil.alertError(data.msg);
			  	}else {
	          		   var html=[];		
	          		   html.push("<table style='font-size: 12px;width:317px;text-align:center;vertical-align:middle;'>");		
					   html.push("<tr>");		
					   html.push("<td>平台介入管理</td>"); 
					   html.push("<td style='padding-top:5px;padding-bottom:5px;line-height:27px;'>");
					   if (!data.Menu_426 && !data.Menu_443 && !data.Menu_430) {
						   html.push("<div style='color:red'>暂无菜单列表</div>");
					  }else{
						  
					   if (data.Menu_426) {
					      html.push("<a href='javascript:;' onclick=''>待领取的介入"+"("+data.Menu426_conut+")"+"</a>");
					      html.push("<br>");
					   }
					   if (data.Menu_443) {
					       html.push("<a href='javascript:;' onclick=''>我的介入单"+"("+data.Menu443_conut+")"+"</a>");
					       html.push("<br>");
					   }
					   if (data.Menu_430) {
					       html.push("<a href='javascript:;' onclick=''>待复审核"+"("+data.Menu430_conut+")"+"</a>");
					   }
						  
					  }
						   
					  html.push("</td>");				
					  html.push("</tr>");
					 
					  html.push("<tr>");
					  html.push("<td>投诉管理</td>");			
					  html.push("<td style='padding-top:5px;padding-bottom:5px;line-height:27px;'>");
					  if (!data.Menu_212) {
						  html.push("<div style='color:red'>暂无菜单列表</div>");
					  }else{
						  
					   if (data.Menu_212) {
					      html.push("<a href='javascript:;' onclick=''>待处理的投诉"+"("+data.Menu212_conut+")"+"</a>");
					   }		     
						  
					  }
					  html.push("</td>");
					  html.push("</tr>");
					  html.push("</table>");					 
					 $(".roleMng-Interventionmenu-div").html(html.join(""));
			  	}
			},
			error: function(e){
			 	commUtil.alertError("系统异常请稍后再试！");
			}
		});
    	
    }
    
    
    function showShopMenu(staffID) {
    	$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/orgMng/menuShopJurisdiction.shtml',
			data: {staffID : staffID},
			dataType: 'json',
			success: function(data) {
				if(data == null || data.code != 200){
			    	commUtil.alertError(data.msg);
			  	}else {
	          		   var html=[];		
	          		   html.push("<table style='font-size: 12px;width:258px;text-align:center;vertical-align:middle;'>");   
					   html.push("<tr>");
					   html.push("<td>变更资质审核</td>");
					   html.push("<td style='padding-top:5px;padding-bottom:5px;line-height:27px;'>");
					   if (!data.Menu_325 && !data.Menu_328) {
						   html.push("<div style='color:red'>暂无菜单列表</div>");
					   }else{
						   
					   if (data.Menu_325) {
					      html.push("<a href='javascript:;' onclick=''>公司信息审核"+"("+data.Menu325_conut+")"+"</a>");
					      html.push("<br>");
					   }
					   if (data.Menu_328) {
					       html.push("<a href='javascript:;' onclick=''>银行账号审核"+"("+data.Menu328_conut+")"+"</a>");
					   }
						   
					   }

					  html.push("</td>");				
					  html.push("</tr>");
					 
					  html.push("<tr>");
					  html.push("<td>品牌资质变更审核</td>");			
					  html.push("<td style='padding-top:5px;padding-bottom:5px;line-height:27px;'>");
					  if (!data.Menu_326 && !data.Menu_520) {
						  html.push("<div style='color:red'>暂无菜单列表</div>");
					  }else{
						  
					   if (data.Menu_326) {
					       html.push("<a href='javascript:;' onclick=''>法务品牌资质审核"+"("+data.Menu326_conut+")"+"</a>");
					       html.push("<br>");
					   }
					   if (data.Menu_520) {
						   html.push("<a href='javascript:;' onclick=''>品牌资质归档"+"("+data.Menu520_conut+")"+"</a>");
					   }
						  
					  }

					  html.push("</td>");
					  html.push("</tr>");
					 
					 html.push("<tr>");
					 html.push("<td>新增品牌</td>");			
					 html.push("<td style='padding-top:5px;padding-bottom:5px;line-height:27px;'>");
					 if (!data.Menu_522 && !data.Menu_324 && !data.Menu_523 && !data.Menu_524) {
						 html.push("<div style='color:red'>暂无菜单列表</div>");
					  }else{
						  
					   if (data.Menu_522) {
					      html.push("<a href='javascript:;' onclick=''>招商品牌资质审核"+"("+data.Menu522_conut+")"+"</a>");
					      html.push("<br>");
					   }   
					   if (data.Menu_324) {
					       html.push("<a href='javascript:;' onclick=''>法务品牌信息审核"+"("+data.Menu324_conut+")"+"</a>");
					       html.push("<br>");
					   }
					   if (data.Menu_523) {
					       html.push("<a href='javascript:;' onclick=''>品牌资料归档"+"("+data.Menu523_conut+")"+"</a>");
					       html.push("<br>");
					   }
					   if (data.Menu_524) {
					       html.push("<a href='javascript:;' onclick=''>新增品牌待开通"+"("+data.Menu524_conut+")"+"</a>");
					   }					  
						  
					  }

					  html.push("</td>");
					  html.push("</tr>");
					 
					 html.push("<tr>");
					 html.push("<td>关店管理</td>");			
					 html.push("<td style='padding-top:5px;padding-bottom:5px;line-height:27px;'>");
					 if (!data.Menu_537 && !data.Menu_538 && !data.Menu_539 && !data.Menu_540 && !data.Menu_541 && !data.Menu_542 && !data.Menu_543 && !data.Menu_544 && !data.Menu_545 && !data.Menu_546) {
						 html.push("<div style='color:red'>暂无菜单列表</div>");
					 }else{
						 
					   if (data.Menu_537) {
					      html.push("<a href='javascript:;' onclick=''>招商部确认"+"("+data.Menu537_conut+")"+"</a>");
					      html.push("<br>");
					   }
					   if (data.Menu_538) {
						  html.push("<a href='javascript:;' onclick=''>商品部确认"+"("+data.Menu538_conut+")"+"</a>");
						  html.push("<br>");
					   }
					   if (data.Menu_539) {
					     html.push("<a href='javascript:;' onclick=''>商家资料确认"+"("+data.Menu539_conut+")"+"</a>");
					     html.push("<br>");
					   }
					   if (data.Menu_540) { 
						 html.push("<a href='javascript:;' onclick=''>客服部确认"+"("+data.Menu540_conut+")"+"</a>");
						 html.push("<br>");
					   }
					   if (data.Menu_541) {  
					     html.push("<a href='javascript:;' onclick=''>财务部货款结清确认"+"("+data.Menu541_conut+")"+"</a>");
					     html.push("<br>");
					   }
					   if (data.Menu_542) {
						 html.push("<a href='javascript:;' onclick=''>法务部确认"+"("+data.Menu542_conut+")"+"</a>");
						 html.push("<br>");
					   }
					   if (data.Menu_543) {
					     html.push("<a href='javascript:;' onclick=''>分管确认关店"+"("+data.Menu543_conut+")"+"</a>");
					     html.push("<br>");
					   }
					   if (data.Menu_544) {
						 html.push("<a href='javascript:;' onclick=''>操作关店"+"("+data.Menu544_conut+")"+"</a>");
						 html.push("<br>");
					   }
					   if (data.Menu_545) {
						 html.push("<a href='javascript:;' onclick=''>结算审核"+"("+data.Menu545_conut+")"+"</a>");
						 html.push("<br>");
					   }
					   if (data.Menu_546) {
						 html.push("<a href='javascript:;' onclick=''>操作结算"+"("+data.Menu546_conut+")"+"</a>");
					   }
						 
					 }

					 html.push("</td>");
					 html.push("</tr>");
					 html.push("</table>");
					 
					 $(".roleMng-shopmenu-div").html(html.join(""));
			  	}
			},
			error: function(e){
			 	commUtil.alertError("系统异常请稍后再试！");
			}
		});
    	
    }
    </script>
  </head>
  <body style="padding: 10px; background: #FFFFFF;">
    <h2>我的待办事项</h2>
    <br>
    <div style="float:left;">
         <div style=" margin-left:20px;font-size: 13px;border:1px solid #e9e9e9;width: 228px;padding-top:2px;padding-bottom:2px;">&nbsp&nbsp商家入驻管理 &nbsp<span style="margin-left:96px;"><a href="javascript:showMchtMenu(${sessionScope.USER_SESSION.staffID});">查看</a></span></div>    
         <div class="roleMng-mchtmenu-div"></div>
    </div> 
    <div style="float:left; margin-left:50px;">
       <div style="font-size: 13px;border:1px solid #e9e9e9;width: 256px;padding-top:2px;padding-bottom:2px;">&nbsp&nbsp店铺管理 &nbsp<span style="margin-left:151px;"><a href="javascript:showShopMenu(${sessionScope.USER_SESSION.staffID});">查看</a></span></div>
       <div class="roleMng-shopmenu-div"></div>    
    </div>
    <div style="float:left; margin-left:56px;">
       <div style="font-size: 13px;border:1px solid #e9e9e9;width: 228px;padding-top:2px;padding-bottom:2px;">&nbsp&nbsp系统管理 &nbsp<span style="margin-left:123px;"><a href="javascript:showSystemMenu(${sessionScope.USER_SESSION.staffID});">查看</a></span></div>
       <div class="roleMng-systemmenu-div"></div>    
    </div>
    <%-- <div style="float:left; margin-left:62px;">
       <div style="font-size: 13px;border:1px solid #000;width: 250px;font-weight: bold;">&nbsp&nbsp违规投诉管理 &nbsp<span style="margin-left:120px;"><a href="javascript:showViolationMenu(${sessionScope.USER_SESSION.staffID});">查看</a></span></div>
       <div class="roleMng-violationmenu-div"></div>    
    </div> --%>
    <div style="float:left; margin-left:68px;">
       <div style="font-size: 13px;border:1px solid #e9e9e9;width: 315px;padding-top:2px;padding-bottom:2px;">&nbsp&nbsp平台介入管理、投诉管理 &nbsp<span style="margin-left:119px;"><a href="javascript:showInterventionMenu(${sessionScope.USER_SESSION.staffID});">查看</a></span></div>
       <div class="roleMng-Interventionmenu-div"></div>    
    </div> 
  </body>
</html>
