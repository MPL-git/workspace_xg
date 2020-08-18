<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
	 $(function() {
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelWidth : 150,
			width : 150,
			labelAlign : 'left'
		});
	 });
	 
 	 var listConfig={
	      url:"/mcht/getMchtList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
	                        {display:'创建与法务',name:'createDate', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
	                        	var html = [];
	                        	if(rowdata.mchtInfoCustom.createDate != null && rowdata.mchtInfoCustom.createDate != "" && rowdata.mchtInfoCustom.createDate != undefined) {
									var createDate = new Date(rowdata.mchtInfoCustom.createDate);
									html.push("<span style='color: #00998F;'>"+createDate.format("yyyy-MM-dd")+"</span></br>");
								}
	                        	if(rowdata.mchtInfoCustom.totalAuditDate != null && rowdata.mchtInfoCustom.totalAuditDate != "" && rowdata.mchtInfoCustom.totalAuditDate != undefined) {
	                        		var totalAuditDate = new Date(rowdata.mchtInfoCustom.totalAuditDate);
									html.push("法务审核："+totalAuditDate.format("yyyy-MM-dd"));
	                        	}
	                        	return html.join("");
	                        }},
	                        {display:'招商对接人',name:'mchtInfoCustom.zsContactName', align:'center', isSort:false, width:100},
	                        {display:'模式',name:'mchtInfoCustom.mchtTypeDesc', align:'center', isSort:false, width:80},
	                        {display:'商家序号',name:'mchtInfoCustom.mchtCode', align:'center', isSort:false, width:100},
	                        {display:'公司名称',name:'company_name', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
	                        	var html = [];
	                        	if(rowdata.mchtInfoCustom.isCompanyInfPerfectDesc != '') {
	                        		html.push("<span style='color: red;'>[" + rowdata.mchtInfoCustom.isCompanyInfPerfectDesc + "]</span>");
	                        	}
	                        	html.push(rowdata.mchtInfoCustom.companyName);
	                        	return html.join("");
	                        }},
	                        {display:'店铺名/合作状态',name:'shop_name', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	var html = [];
	                        	if(rowdata.mchtInfoCustom.shopNameAuditStatusDesc != '') {
	                        		html.push("<span style='color: red;'>[" + rowdata.mchtInfoCustom.shopNameAuditStatusDesc + "]</span>");
	                        	}
	                        	if(rowdata.mchtInfoCustom.statusDesc != '') {
	                        		html.push("<span style='color: #00998F;'>[" + rowdata.mchtInfoCustom.statusDesc + "]</span>");
	                        	}
	                        	html.push(rowdata.mchtInfoCustom.shopName);
	                        	return html.join("");
	                        }},
	                        {display:'类目',name:'mchtProductTypeCustomList', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
	                        	var mchtProductTypeCustomList = rowdata.mchtProductTypeCustomList;
			                	var html = [];
			                	if(mchtProductTypeCustomList.length > 0) {
									for (var i = 0; i < mchtProductTypeCustomList.length; i++) {
										if(i != 0) {
											html.push("</br>");
										}
										if(mchtProductTypeCustomList[i].statusDesc != '') {
											html.push("<span style='color: red;'>["+ mchtProductTypeCustomList[i].statusDesc +"]</span>");
										}
										html.push(mchtProductTypeCustomList[i].productTypeName);
									}
			                	}
			                	return html.join("");
	                        }},
	                        {display:'品牌/资质/开通',name:'mchtProductBrandList', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
	                        	var mchtProductBrandList = rowdata.mchtProductBrandList;
			                	var html = [];
		                		for (var i = 0; i < mchtProductBrandList.length; i++) {
		                			if(i != 0) {
		                				html.push("<br>");
		                			}
									if(mchtProductBrandList[i].auditStatusDesc != '') {
										html.push("<span style='color:red;'>[" + mchtProductBrandList[i].auditStatusDesc + "]</span>");
									}
									if(mchtProductBrandList[i].statusDesc != '') {
										html.push("<span style='color:#00998F;'>[" + mchtProductBrandList[i].statusDesc + "]</span>");
									}
									if(mchtProductBrandList[i].brandName!=null){
										html.push("<a href=\"javascript:void(0);\">"+mchtProductBrandList[i].brandName+"</a>");
									}else{
										html.push("<a href=\"javascript:void(0);\">"+mchtProductBrandList[i].productBrandName+"</a>");
									}
									if(mchtProductBrandList[i].popCommissionRate != '' && mchtProductBrandList[i].popCommissionRate != null) {
										html.push("<span style='color: #FFCC00;'>("+ mchtProductBrandList[i].popCommissionRate +")</span>");
									}
								 }
			                	return html.join("");
	                        }},
	                        {display:'资质总审核状态',name:'mchtInfoCustom.totalAuditStatusdesc', align:'center', isSort:false, width:100},
	                        {display:'审核日志',name:'mchtInfoChangeLogList', align:'center', isSort:false, width:300, render:function(rowdata, rowindex) {
	                        	var mchtInfoChangeLogList = rowdata.mchtInfoChangeLogList;
			                	var html = [];
			                	if(mchtInfoChangeLogList.length > 0) {
									for (var i = 0; i < mchtInfoChangeLogList.length; i++) {
										if(i != 0) {
											html.push("</br>");
										}
										if(mchtInfoChangeLogList[i].createDate != null || mchtInfoChangeLogList[i].createDate != "" || mchtInfoChangeLogList[i].createDate != undefined) {
											var createDate = new Date(mchtInfoChangeLogList[i].createDate);
											html.push("<span>["+ createDate.format("yyyy-MM-dd") +"]</span>");
										}
										if(mchtInfoChangeLogList[i].logType != '') {
											html.push("<span style='color: red;'>["+ mchtInfoChangeLogList[i].logType +"]</span>");
										}
										if(mchtInfoChangeLogList[i].logName != '') {
											html.push("<span>["+ mchtInfoChangeLogList[i].logName +"]</span>");
										}
										if(mchtInfoChangeLogList[i].afterChange != '') {
											html.push("<span style='color: #00998F;'>["+ mchtInfoChangeLogList[i].afterChange +"]</span>");
										}
									}
			                	}
			                	return html.join("");
	                        }},
	                        {display:'开通及合同到期日期',name:'cooperate_begin_date', align:'center', isSort:false, width:150, render:function(rowdata, rowindex) {
	                        	var html = [];
	                        	if(rowdata.mchtInfoCustom.cooperateBeginDate != null && rowdata.mchtInfoCustom.cooperateBeginDate != "" && rowdata.mchtInfoCustom.cooperateBeginDate != undefined) {
		                        	html.push("<span>[开通]</span>");
		                        	var cooperateBeginDate = new Date(rowdata.mchtInfoCustom.cooperateBeginDate);
									html.push(cooperateBeginDate.format("yyyy-MM-dd")+ "</br>");
	                        	}
	                        	if(html.length > 0) {
		                        	if(rowdata.mchtInfoCustom.agreementEndDate != null && rowdata.mchtInfoCustom.agreementEndDate != "" && rowdata.mchtInfoCustom.agreementEndDate != undefined) {
		                        		html.push("<span>~</span></br>");
			                        	var agreementEndDate = new Date(rowdata.mchtInfoCustom.agreementEndDate);
										html.push("<span style='color: red;'>[到期]"+ agreementEndDate.format("yyyy-MM-dd")+ "</span>");
		                        	}
	                        	}else {
	                        		if(rowdata.mchtInfoCustom.agreementEndDate != null && rowdata.mchtInfoCustom.agreementEndDate != "" && rowdata.mchtInfoCustom.agreementEndDate != undefined) {
			                        	var agreementEndDate = new Date(rowdata.mchtInfoCustom.agreementEndDate);
										html.push("<span style='color: red;'>[到期]"+ agreementEndDate.format("yyyy-MM-dd")+ "</span>");
		                        	}
	                        	}
							    return html.join("");
	                        }},
	                         {display:'商家对接人',name:'mchtContactList', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
			                	var html = [];			          			              
						              var staffMchtcontactPermissionlist=rowdata.staffMchtcontactPermissionlist;
						              if(staffMchtcontactPermissionlist){
							              for (var i=0; i < staffMchtcontactPermissionlist.length; i++) {
							                   if (staffMchtcontactPermissionlist[i].mchtContactType=='1') {
							                      if(rowdata.mchtInfoCustom.dsMchtContactName != '' && rowdata.mchtInfoCustom.dsMchtContactName != null) {
							                         html.push("<span style='color: #00998F;'>[电商总负责人]</span>");
							                         html.push( rowdata.mchtInfoCustom.dsMchtContactName +"</br>");
									            }			                	 	
									          }
									          
									          if (staffMchtcontactPermissionlist[i].mchtContactType=='2') {
									            if(rowdata.mchtInfoCustom.yyMchtContactName != '' && rowdata.mchtInfoCustom.yyMchtContactName != null) {
									             html.push("<span style='color: #00998F;'>[商管运营对接人]</span>");
									             html.push( rowdata.mchtInfoCustom.yyMchtContactName +"</br>");
									            }
								              }
								              
								            if (staffMchtcontactPermissionlist[i].mchtContactType=='3') {
								              if(rowdata.mchtInfoCustom.ddMchtContactName != '' && rowdata.mchtInfoCustom.ddMchtContactName != null) {
									             html.push("<span style='color: #00998F;'>[订单对接人]</span>");
									             html.push( rowdata.mchtInfoCustom.ddMchtContactName +"</br>");
									           }
								             }
								             
								           if (staffMchtcontactPermissionlist[i].mchtContactType=='4') {
								             if(rowdata.mchtInfoCustom.shMchtContactName != '' && rowdata.mchtInfoCustom.shMchtContactName != null) {
									            html.push("<span style='color: #00998F;'>[售后对接人]</span>");
									            html.push( rowdata.mchtInfoCustom.shMchtContactName +"</br>");
								             }
								            }
								             
								          if (staffMchtcontactPermissionlist[i].mchtContactType=='5') {
								             if(rowdata.mchtInfoCustom.cwMchtContactName != '' && rowdata.mchtInfoCustom.cwMchtContactName != null) {
									            html.push("<span style='color: #00998F;'>[财务对接人]</span>");
									            html.push( rowdata.mchtInfoCustom.cwMchtContactName +"</br>");
									         }
								            }
								            
								         if (staffMchtcontactPermissionlist[i].mchtContactType=='6') {
								             if(rowdata.mchtInfoCustom.kfMchtContactName != '' && rowdata.mchtInfoCustom.kfMchtContactName != null) {
									            html.push("<span style='color: #00998F;'>[客服对接人 ]</span>");
									            html.push( rowdata.mchtInfoCustom.kfMchtContactName +"</br>");
									          }
							                 }
										
									       } 														                      					               
						              }
							       return html.join("");
							 }},
	                        /* {display:'商家对接人',name:'mchtContactList', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
			                	var html = [];
			                	if (rowdata.mchtInfoCustom.staffId==${sessionScope.USER_SESSION.staffID}) {
			                	  if (rowdata.mchtInfoCustom.mchtContactType!=null && rowdata.mchtInfoCustom.mchtContactType!='') {
						              var mchtcontacttype=rowdata.mchtInfoCustom.mchtContactType.split(",");
						              for (var i=0; i < mchtcontacttype.length; i++) {
						                   if (mchtcontacttype[i]=='1') {
						                      if(rowdata.mchtInfoCustom.dsMchtContactName != '' && rowdata.mchtInfoCustom.dsMchtContactName != null) {
						                         html.push("<span style='color: #00998F;'>[电商总负责人]</span>");
						                         html.push( rowdata.mchtInfoCustom.dsMchtContactName +"</br>");
								            }			                	 	
								          }
								          
								          if (mchtcontacttype[i]=='2') {
								            if(rowdata.mchtInfoCustom.yyMchtContactName != '' && rowdata.mchtInfoCustom.yyMchtContactName != null) {
								             html.push("<span style='color: #00998F;'>[运营对接人]</span>");
								             html.push( rowdata.mchtInfoCustom.yyMchtContactName +"</br>");
								            }
							              }
							              
							            if (mchtcontacttype[i]=='3') {
							              if(rowdata.mchtInfoCustom.ddMchtContactName != '' && rowdata.mchtInfoCustom.ddMchtContactName != null) {
								             html.push("<span style='color: #00998F;'>[订单对接人]</span>");
								             html.push( rowdata.mchtInfoCustom.ddMchtContactName +"</br>");
								           }
							             }
							             
							           if (mchtcontacttype[i]=='4') {
							             if(rowdata.mchtInfoCustom.shMchtContactName != '' && rowdata.mchtInfoCustom.shMchtContactName != null) {
								            html.push("<span style='color: #00998F;'>[售后对接人]</span>");
								            html.push( rowdata.mchtInfoCustom.shMchtContactName +"</br>");
							             }
							            }
							             
							          if (mchtcontacttype[i]=='5') {
							             if(rowdata.mchtInfoCustom.cwMchtContactName != '' && rowdata.mchtInfoCustom.cwMchtContactName != null) {
								            html.push("<span style='color: #00998F;'>[财务对接人]</span>");
								            html.push( rowdata.mchtInfoCustom.cwMchtContactName +"</br>");
								         }
							            }
							            
							         if (mchtcontacttype[i]=='6') {
							             if(rowdata.mchtInfoCustom.kfMchtContactName != '' && rowdata.mchtInfoCustom.kfMchtContactName != null) {
								            html.push("<span style='color: #00998F;'>[客服对接人 ]</span>");
								            html.push( rowdata.mchtInfoCustom.kfMchtContactName +"</br>");
								          }
						                }
						              }  														                      					               
							        }
							       }
							       return html.join(""); */		           
			                	   /* if(rowdata.mchtInfoCustom.dsMchtContactName != '' && rowdata.mchtInfoCustom.dsMchtContactName != null) {
			                		     html.push("<span style='color: #00998F;'>[电商总负责人]</span>");
			                		     html.push( rowdata.mchtInfoCustom.dsMchtContactName +"</br>");
						           } 		                	
								 
			                	 if(rowdata.mchtInfoCustom.yyMchtContactName != '' && rowdata.mchtInfoCustom.yyMchtContactName != null) {
			                		html.push("<span style='color: #00998F;'>[运营对接人]</span>");
			                		html.push( rowdata.mchtInfoCustom.yyMchtContactName +"</br>");
			                	
			                	 }
			                	
			                	if(rowdata.mchtInfoCustom.ddMchtContactName != '' && rowdata.mchtInfoCustom.ddMchtContactName != null) {
			                		html.push("<span style='color: #00998F;'>[订单对接人]</span>");
			                		html.push( rowdata.mchtInfoCustom.ddMchtContactName +"</br>");
			                	
			                	 }
			                	
			                	if(rowdata.mchtInfoCustom.shMchtContactName != '' && rowdata.mchtInfoCustom.shMchtContactName != null) {
			                		html.push("<span style='color: #00998F;'>[售后对接人]</span>");
			                		html.push( rowdata.mchtInfoCustom.shMchtContactName +"</br>");
			                	
			                	 }
			                	
			                	if(rowdata.mchtInfoCustom.cwMchtContactName != '' && rowdata.mchtInfoCustom.cwMchtContactName != null) {
			                		html.push("<span style='color: #00998F;'>[财务对接人]</span>");
			                		html.push( rowdata.mchtInfoCustom.cwMchtContactName +"</br>");
			                	
			                	}
			       
			                	if(rowdata.mchtInfoCustom.kfMchtContactName != '' && rowdata.mchtInfoCustom.kfMchtContactName != null) {
			                		html.push("<span style='color: #00998F;'>[客服对接人 ]</span>");
			                		html.push( rowdata.mchtInfoCustom.kfMchtContactName +"</br>");
			                	  
                               } */
			                	/* return html.join(""); */
	                         /* }}, */ 	                   
	                        {display:'平台对接人',name:'mchtPlatformContactList', align:'center', isSort:false, width:120, render:function(rowdata, rowindex) {
	                        	var html = [];
			                	if(rowdata.mchtInfoCustom.zsContactName != '' && rowdata.mchtInfoCustom.zsContactName != null) {
			                		html.push("<span style='color: #00998F;'>[招商]</span>");
			                		html.push( rowdata.mchtInfoCustom.zsContactName +"</br>");
			                	}
			                	if(rowdata.mchtInfoCustom.yyContactName != '' && rowdata.mchtInfoCustom.yyContactName != null) {
			                		html.push("<span style='color: #00998F;'>[商管运营]</span>");
			                		html.push( rowdata.mchtInfoCustom.yyContactName +"</br>");
			                	}
			                	if(rowdata.mchtInfoCustom.ddContactName != '' && rowdata.mchtInfoCustom.ddContactName != null) {
			                		html.push("<span style='color: #00998F;'>[订单]</span>");
			                		html.push( rowdata.mchtInfoCustom.ddContactName +"</br>");
			                	}
			                	if(rowdata.mchtInfoCustom.shContactName != '' && rowdata.mchtInfoCustom.shContactName != null) {
			                		html.push("<span style='color: #00998F;'>[售后]</span>");
			                		html.push( rowdata.mchtInfoCustom.shContactName +"</br>");
			                	}
			                	if(rowdata.mchtInfoCustom.cwContactName != '' && rowdata.mchtInfoCustom.cwContactName != null) {
			                		html.push("<span style='color: #00998F;'>[财务]</span>");
			                		html.push( rowdata.mchtInfoCustom.cwContactName +"</br>");
			                	}
			                	if(rowdata.mchtInfoCustom.kfContactName != '' && rowdata.mchtInfoCustom.kfContactName != null) {
			                		html.push("<span style='color: #00998F;'>[客服]</span>");
			                		html.push( rowdata.mchtInfoCustom.kfContactName +"</br>");
			                	}
			                	if(rowdata.mchtInfoCustom.fwContactName != '' && rowdata.mchtInfoCustom.fwContactName != null) {
			                		html.push("<span style='color: #00998F;'>[法务]</span>");
			                		html.push( rowdata.mchtInfoCustom.fwContactName +"</br>");
			                	}
			                	if(rowdata.mchtInfoCustom.sjContactName != '' && rowdata.mchtInfoCustom.sjContactName != null) {
			                		html.push("<span style='color: #00998F;'>[商家服务专员]</span>");
			                		html.push( rowdata.mchtInfoCustom.sjContactName +"</br>");
			                	}
			                	return html.join("");
	                        }} /* */
			         ], 
	                 showCheckbox : false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      } , 							
	      container:{
	        toolBarName:"toptoolbar",
	        searchBtnName:"searchbtn",
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }        
	  };
 	
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >模式：</div>
					<div class="search-td-combobox-condition" >
						<select id="mchtType" name="mchtType" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="mchtType" items="${mchtTypeList }">
								<option value="${mchtType.statusValue }">
									${mchtType.statusDesc }
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtName" name="mchtName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >合作状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="status" items="${statusList }">
								<option value="${status.statusValue }">
									${status.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >到期年份：</div>
					<div class="search-td-combobox-condition" >
						<select id="endYear" name="endYear" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="endYear" items="${endYearList }">
								<option value="${endYear }">
									${endYear }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >到期月份：</div>
					<div class="search-td-combobox-condition" >
						<select id="endMonth" name="endMonth" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="endMonth" items="${endMonthList }">
								<option value="${endMonth }">
									${endMonth }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >本人对接：</div>
					<div class="search-td-combobox-condition" >
						<select id="contactType" name="contactType" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="contactType" items="${contactTypeList }">
								<option value="${contactType.statusValue }">
									${contactType.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >品牌：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="brandName" name="brandName" >
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >类目：</div>
					<div class="search-td-combobox-condition" >
						<c:if test="${isCwOrgStatus == 1 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" disabled="disabled" >
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${isCwOrgStatus == 0 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" >
								<option value="">请选择...</option>
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
						</c:if>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >招商对接：</div>
					<div class="search-td-combobox-condition" >
						<select id="zsPlatformContact" name="zsPlatformContact" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="zsPlatformContact" items="${zsPlatformContactList }">
								<option value="${zsPlatformContact.id }">
									${zsPlatformContact.contactName }
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >运营对接：</div>
					<div class="search-td-combobox-condition" >
						<select id="zyPlatformContact" name="zyPlatformContact" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="zyPlatformContact" items="${zyPlatformContactList }">
								<option value="${zyPlatformContact.id }">
									${zyPlatformContact.contactName }
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >法务对接：</div>
					<div class="search-td-combobox-condition" >
						<select id="fwPlatformContact" name="fwPlatformContact" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="fwPlatformContact" items="${fwPlatformContactList }">
								<option value="${fwPlatformContact.id }">
									${fwPlatformContact.contactName }
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label" style="float:left;"  >创建日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" class="dateEditor" id="startCreateDate" name="startCreateDate" >
				 	</div>
				 	<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
				</div>
				<div class="search-td">
					<div class="l-panel-search-item" >
						<input type="text" class="dateEditor" id="endCreateDate" name="endCreateDate" >
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="float:left;" >法务审核日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" class="dateEditor" id="startTotalAuditDate" name="startTotalAuditDate" >
				 	</div>
				 	<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
				</div>
				<div class="search-td">
					<div class="l-panel-search-item" >
						<input type="text" class="dateEditor" id="endTotalAuditDate" name="endTotalAuditDate" >
				 	</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label" style="float:left;" >开通日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" class="dateEditor" id="startCooperateBeginDate" name="startCooperateBeginDate" >
				 	</div>
				 	<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
				</div>
				<div class="search-td">
					<div class="l-panel-search-item" >
						<input type="text" class="dateEditor" id="endCooperateBeginDate" name="endCooperateBeginDate" >
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >资质总审核状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="totalauditstatus" name="totalauditstatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="0">待审</option>
							<option value="1">审核中</option>
							<option value="2">通过</option>
							<option value="3">驳回</option>
							<option value="4">未提交</option>
							<option value="5">已提交</option>
						</select>
					</div>
				</div>
				
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0; padding: 0"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
