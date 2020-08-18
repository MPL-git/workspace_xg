<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
 $(function(){

	 $(".dateEditor").ligerDateEditor( {
		 showTime : false,
		 labelAlign : 'left',
		 labelwidth: 120,
		 width:120
	 });
	 
	// 倒计时
     setInterval(function () {
         $(".endDateDiv").each(function () {
             var limitDate = parseInt($(this).attr("data-value"));
             var result = countdown(limitDate);
             $(this).html(result);
         });
     }, 60000);
	 
		$("#violateType").bind('change',function(){
			var violateType = $(this).val();
			$.ajax({
				url : "${pageContext.request.contextPath}/violateOrder/getViolateActions.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"violateType":violateType},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						var violateActions = data.violateActions;
						var optionArray = [];
						optionArray.push('<option value="">请选择</option>');
						for(var i=0;i<violateActions.length;i++){
							var statusValue = violateActions[i].statusValue;
							var statusDesc = violateActions[i].statusDesc;
							optionArray.push('<option value="'+statusValue+'">'+statusDesc+'</option>');
						}
						$("#violateAction").html(optionArray.join(""));
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
					
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		});
		
		var dateArray = [];
		var defaultProductTypeIds = [];
		<c:if test="${not empty sysStaffProductTypeList }" >
			var sysStaffProductTypeList = ${sysStaffProductTypeList };
			for(var i=0;i<sysStaffProductTypeList.length;i++) {
				dateArray.push(dataBox(sysStaffProductTypeList[i].staffName, sysStaffProductTypeList[i].productTypeId));
				if(defaultProductTypeIds.length != 0) {
					defaultProductTypeIds.push(";");
				}
				defaultProductTypeIds.push(sysStaffProductTypeList[i].productTypeId);
			}
		</c:if>
		var productType2IdsComboBox = $("#productTypeId").ligerComboBox({ 
			isShowCheckBox: true, 
			isMultiSelect: true, 
			emptyText: false,
	        data: dateArray, 
	        valueFieldID: 'productTypeIds',
	        selectBoxWidth: 165.5,
	        width: 165.5
	    }); 
		<c:if test="${isCwOrgStatus == 1 }" >
			productType2IdsComboBox.selectValue(defaultProductTypeIds.join());
			productType2IdsComboBox.updateStyle();
		</c:if>
		
 }); 
 
function dataBox(text, id){ 
	 var obj = new Object();
	 obj.text = text; 
	 obj.id = id; 
	 return obj;
}
 
function savePlatformProcessor(id,input){
		$.ajax({
			url : "${pageContext.request.contextPath}/violateOrder/savePlatformProcessor.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					var $div = $(input).parent();
					$div.empty();
					$div.text(data.staffName);
					commUtil.alertSuccess("领取成功");
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
				
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
 
function viewViolateOrder(id) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "商家违规详情页",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/violateOrder/view.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toAudit(id) {
	$.ligerDialog.open({
		height: 350,
		width: 400,
		title: "审核",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/violateOrder/toAudit.shtml?violateOrderId=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toAuditSingle(id) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "审核",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/violateOrder/toCheckManuallyViolateOrder.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toAuditRemarks(id) {
	$.ligerDialog.open({
		height: 300,
		width: 400,
		title: "驳回原因",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/violateOrder/toAuditRemarks.shtml?violateOrderId=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

//倒计时
function countdown(limitDate) {
    var nowDate = new Date().getTime();
    if (limitDate <= nowDate) {
        return "已超时，系统已自动处理。";
    }
    var times = (limitDate - nowDate) / 1000;
    var day = 0,
        hour = 0,
        minute = 0,
        second = 0;//时间默认值
    if (times > 0) {
        day = Math.floor(times / (60 * 60 * 24));
        hour = Math.floor(times / (60 * 60)) - (day * 24);
        minute = Math.floor(times / 60) - (day * 24 * 60) - (hour * 60);
        second = Math.floor(times) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
    }
    if (day <= 9) day = '0' + day;
    if (hour <= 9) hour = '0' + hour;
    if (minute <= 9) minute = '0' + minute;
    if (second <= 9) second = '0' + second;
    return day + "天" + hour + "小时" + minute + "分";
}

 var listConfig={
 	btnItems:[
              {text: '批量审核', icon: 'edit', click: function(yes) {
      			  var data = listModel.gridManager.getSelectedRows();
                          if (data.length == 0)
                        	  $.ligerDialog.alert('请选择行');
                          else
                          {
                             var str = "";
                             
                              $(data).each(function ()
                              {    
                            	  if(str==''){
                            		  str = this.id ;
                            	  }else{
                            		  str += ","+ this.id ;
                            	  }
                              });
                              $.ligerDialog.confirm('确定批量审核吗?', function ()
                              {
                            	  toAudit(str);
                              }); 
                          }
                          return;
      			  }}
    			],
     url:"/violateOrder/shamDeliveryOrderData.shtml",
     listGrid:{ columns: [
						{ display: '创建时间', width: 150, render: function (rowdata, rowindex) {
							if(rowdata.createDate){
								var createDate=new Date(rowdata.createDate);
								return createDate.format("yyyy-MM-dd hh:mm:ss");		                		
							}
						}},
						{ display: '来源/违规单编号', render: function (rowdata, rowindex) {
							return rowdata.orderSourceDesc+'<br><a href="javascript:;" onclick="viewViolateOrder('+rowdata.id+');">'+rowdata.orderCode+'</a>';	                	
		                }},
		                { display: '商家', render: function (rowdata, rowindex) {
							return rowdata.mchtCode+"<br>"+rowdata.shopName+"<br>"+rowdata.companyName;		                	
		                }},
		                { display: '子订单号',width:'180' ,name:'subOrderCode'},
		                { display: '违规行为',render: function (rowdata, rowindex) {
							return "【"+rowdata.violateTypeDesc+"】"+rowdata.violateActionDesc;		                	
		                }},
		                { display: '处罚金额（元）', name: 'money'},
			            { display: '是否挂起', render:function (rowdata, rowindex) {
			                if(rowdata.suspendedStatus == "1"){
								return "否";
							}else if (rowdata.suspendedStatus == "2"){
								return "是";
							}
			            }},
		                { display: '审核状态', render: function (rowdata, rowindex) {
		                	if(rowdata.auditStatus == "0"){
								return "<span style='color:#ec971f;'>"+rowdata.auditStatusDesc+"<a href='javascript:;' onclick='toAuditSingle("+rowdata.id+");'>【审核】</a></span>";		                	
		                	}else if(rowdata.auditStatus == "1"){
		                		return rowdata.auditStatusDesc;
		                	}else if(rowdata.auditStatus == "2"){
		                		return "<span style='color:#d43f3a;'>"+rowdata.auditStatusDesc+"<a href='javascript:;' onclick='toAuditRemarks("+rowdata.id+");'>【查看原因】</a></span>";
		                	}
		                }}, 
		                { display: '剩余自动审核时间', render: function (rowdata, rowindex) {
		                	var resultHtml = [];
			            	if (rowdata.createDate != null){
			            		var d = new Date(rowdata.createDate);
			            		d.setDate(d.getDate() + 4);//获取Day天后的日期 
		                        var desc = countdown(d.getTime());
		                        resultHtml.push('<div class="endDateDiv" data-value="' + d.getTime() + '">' + desc + '</div>');
			            	}
			            	return resultHtml.join("");
						}},
		                { display: '申诉状态', name: 'statusDesc'},
		                { display: '商家申诉时间', render: function (rowdata, rowindex) {
							if(rowdata.complainTime){
								var complainTime=new Date(rowdata.complainTime);
								return complainTime.format("yyyy-MM-dd hh:mm:ss");		                		
							}
						}},
		                { display: '审核人', render: function (rowdata, rowindex) {
	                		return rowdata.staffName;
		                }}
		                ],
                 showCheckbox : true,  //不设置默认为 true
                 showRownumber: true //不设置默认为 true
      } ,  							
     container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
  };
 
 function excel(){  
	$("#dataForm").attr("action","${pageContext.request.contextPath}/violateOrder/exportViolateOrder.shtml?pageStatus=1");
	$("#dataForm").submit();
 }
  
</script>
<style type="text/css">
	.l-box-select .l-box-select-table td {
		font-size: 12px;
		line-height: 18px;
	}
</style>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<input type="hidden" id="staffId" value="${staffId}">
	<form id="dataForm" runat="server" method="post">
		<div class="search-pannel">
		<div class="search-tr">
			<div class="search-td">
			<div class="search-td-label" style="float:left;">审核状态：</div>
			<div class="l-panel-search-item" >
				<select id="auditStatus" name="auditStatus" style="width: 150px;" >
					<option value="">请选择</option>
					<c:forEach var="auditStatus" items="${auditStatusList}">
						<option value="${auditStatus.statusValue}">${auditStatus.statusDesc}</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			<div class="search-td">
			<div class="search-td-label" >违规编号：</div>
			<div class="search-td-condition">
				<input type="text" id="orderCode" name="orderCode" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >商家序号：</div>
			<div class="search-td-condition">
				<input type="text" id="mchtCode" name="mchtCode" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >商家名称：</div>
			<div class="search-td-condition">
				<input type="text" id="name" name="name" >
			</div>
			</div>
		</div>
		<div class="search-tr"  >
			<div class="search-td">
			<div class="search-td-label" >子订单号：</div>
			<div class="search-td-condition">
				<input type="text" id="subOrderCode" name="subOrderCode" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">申诉状态：</div>
			<div class="l-panel-search-item" >
				<select id="status" name="status" style="width: 150px;" >
					<option value="">请选择</option>
					<c:forEach var="status" items="${statusList}">
						<option value="${status.statusValue}">${status.statusDesc}</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			<%--<div class="search-td">
			<div class="search-td-label" style="float:left;">创建日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="create_date_begin" name="create_date_begin" />
				<script type="text/javascript">
					$(function() {
						$("#create_date_begin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="create_date_end" name="create_date_end" />
				<script type="text/javascript">
					$(function() {
						$("#create_date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>--%>
			<div class="search-td" style="width: 30%;margin-bottom:-6px;">
				<div class="search-td-label" style="float: left;width: 20%;margin-top:2px;">创建日期</div>
				<div class="l-panel-search-item" >
					<input type="text" id="create_date_begin" name="create_date_begin" class="dateEditor" placeholder="请选择" style="width: 135px;" />
				</div>
				<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
				<div class="l-panel-search-item">
					<input type="text" id="create_date_end" name="create_date_end" class="dateEditor" placeholder="请选择" style="width: 135px;" />
				</div>
			</div>
			
		</div> 
		<div class="search-tr">
			<div class="search-td">
				<div class="search-td-label">品类：</div>
				<div style="display: inline-block;">
					<input type="text" id="productTypeId" name="productTypeId" />
				</div>
			</div>

			<div class="search-td">
				<div class="search-td-label" style="float:left;">是否挂起：</div>
				<div class="l-panel-search-item" >
					<select id="suspendedStatus" name="suspendedStatus" style="width:70px; ">
						<option value="">请选择</option>
						<c:forEach var="suspendedStatus" items="${suspendedStatusList}">
							<option value="${suspendedStatus.statusValue}">${suspendedStatus.statusDesc}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="search-td-search-btn">
				<div id="searchbtn">搜索</div>
			</div>
		</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>