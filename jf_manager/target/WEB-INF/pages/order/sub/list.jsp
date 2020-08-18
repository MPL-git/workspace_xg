<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="${pageContext.request.contextPath}/css/glyphicon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />

<style type="text/css">
.color-1{color: #9D999D;}
.color-2{color: #FC0000;}
.color-3{color: #EFD104;}
.color-4{color: #00FC28;}
.color-5{color: #0351F7;}
.color-6{color: #DF00FB;}
.l-box-select .l-box-select-table td {
	font-size: 12px;
	line-height: 18px;
}
</style>
<script type="text/javascript">

$(function(){

	$("#wuliu").bind('click',function(){
		if($(this).attr("checked")){
			$(this).val(1);
		}else{
			$(this).val(0);
		}
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
	 console.log(dateArray);
	 console.log(defaultProductTypeIds);
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

function viewDetail(id) {
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
		title: "子订单详情",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/order/sub/detail.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function editMchtBaseInfo(id) {
	$.ligerDialog.open({
		height: $(window).height() - 40,
		width: $(window).width() - 40,
		title: "商家基础资料",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/mchtBaseInfoEdit.shtml?mchtId=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function viewAfterServiceDetail(id, serviceTypeDesc) {
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
 		title: "售后详情（"+serviceTypeDesc+"）",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/order/afterService/detail.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

  var listConfig={
	  url:"/order/sub/data.shtml",
      listGrid:{ columns: [{ display: '子订单编号', width: 200, render: function(rowdata, rowindex) {
    						var html = [];
    						html.push("<a href=\"javascript:viewDetail(" + rowdata.id + ");\">"+rowdata.subOrderCode+"</a>");
    						if(rowdata.memberInfoStatus == 'P') {
    							html.push("</br><span style='color:red;'>异常</span>");
    						}
    						return html.join("");
    					}},
    					{ display: '类目',width: 100,render: function(rowdata, rowindex){
    						if (rowdata.productTypename!=null) {
    						   return rowdata.productTypename;
							}else{
							   return "";
							}
    					}},

    					{ display: '店铺名称', width: 200, render: function(rowdata, rowindex) {
    						var html = [];
    						if (rowdata.shopName!=null){
    							html.push(rowdata.shopName);
    						}
    						return html.join("");
    					}},
 		                { display: '品牌/货号', width: 180, render: function (rowdata, rowindex) {
 		                	var artBrandGroup=rowdata.artBrandGroup;
 		                	if (artBrandGroup!=null){
 		                		return artBrandGroup.replace(/,/g,"<br>");
 		                	}
		                }},
		                { display: '实付金额', width: 100, name: 'payAmount'},
		                { display: '付款渠道', width: 100, name: 'paymentName',render: function (rowdata, rowindex) {
								if(rowdata.orderType == 7){
									return "积分转盘";
								}else {
									return rowdata.paymentName;
								}
						}},
		                { display: '用户昵称', width: 100, render: function (rowdata, rowindex) {
 		                	var memberNick = rowdata.memberNick;
 		                	if(memberNick != null && memberNick != '' && memberNick.length > 8) {
 		                		return memberNick.substring(0, 6)+"**"+memberNick.substring(memberNick.length-1, memberNick.length);
 		                	}else {
 		                		return memberNick;
 		                	}
		                }},
		                { display: '收货人', width: 100, name: 'receiverName'},
		                { display: '订单状态', width: 100, name: 'statusDesc'},
		                { display: '下单时间', width: 150, render: function (rowdata, rowindex) {
		                	if (rowdata.orderCreateDate!=null){
								var orderCreateDate=new Date(rowdata.orderCreateDate);
								return orderCreateDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '付款时间', width: 150, render: function (rowdata, rowindex) {
		                	if (rowdata.orderPayDate!=null){
								var orderPayDate=new Date(rowdata.orderPayDate);
								return orderPayDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '发货时间', width: 150, render: function (rowdata, rowindex) {
		                	if (rowdata.deliveryDate!=null){
								var deliveryDate=new Date(rowdata.deliveryDate);
								return deliveryDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '快递', width: 100, render: function (rowdata, rowindex) {
		                	if (rowdata.expressName==null && rowdata.expressNo==null){
		                		return "-";
		                		html.push(rowdata.expressName+"<br>"+rowdata.expressNo);
		                	}else{
		                		var html = [];
		                		if (rowdata.expressName!=null){
		                			html.push(rowdata.expressName);
		                		}
		                		if (rowdata.expressNo!=null){
		                			html.push(rowdata.expressNo);
		                		}
		                		return html.join("<br>");
		                	}
		                }},
                        { display: '相关违规', width: 150, render: function (rowdata, rowindex) {
		                	var html = [];
        	                if (rowdata.violateorderid !=null && rowdata.ordercode!=null){
       	                	 	var str=rowdata.violateorderid;
       	                	 	var code=rowdata.ordercode;
	                	     	var vid=str.split(",");
	                	     	var codes=code.split("<br>");
	                		 	for(var i=0; i < vid.length; i++){
	                		    	html.push("<a href=\"javascript:viewViolateOrder(" +vid[i]+ ");\">"+codes[i]+"</a>");
	                         	}
		                		return html.join("");

		                	}else{
		                		return "";
		                	}
		                }},
		                { display: '售后单', width: 150, render: function (rowdata, rowindex) {
		                	var html = [];
		                	if(rowdata.customerServiceOrderConcat) {
		                		var customerServiceOrderArray = rowdata.customerServiceOrderConcat.split(",");
		                		for(var i=0;i<customerServiceOrderArray.length;i++) {
		                			var customerServiceOrderStr = customerServiceOrderArray[i].split("|");
		                			html.push("<a href=\"javascript:viewAfterServiceDetail(" + customerServiceOrderStr[2] + ",'" + customerServiceOrderStr[0] + "');\">【"+customerServiceOrderStr[0]+"】</a>");
		                			html.push(customerServiceOrderStr[1]);
		                			html.push("<br/>");
		                		}
		                	}
		                	return html.join("");
		                }},
		                { display: '备注', width: 150, render: function (rowdata, rowindex) {
		                	var html = [];
		                	if (rowdata.logRemarksColor!=null){
		                		html.push("<span style='padding:0 10px;font-size:20px;' class='glyphicon glyphicon-flag "+"color-"+rowdata.logRemarksColor+"' aria-hidden='true'></span>");
		                	}
		                	if (rowdata.remarks!=null){
		                		html.push(rowdata.logRemarks);
		                	}
		                	return html.join("");
		                }}
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
}

  function subFunction(statusExcel) {
	  	/* var status = $("[name='status']").val(); */
		/* var orderPayDateBegin = $("[name='orderPayDateBegin']").val();
		var orderPayDateEnd = $("[name='orderPayDateEnd']").val(); */
		var orderCreateDateBegin=$("[name='orderCreateDateBegin']").val();
		var orderCreateDateEnd=$("[name='orderCreateDateEnd']").val();
/*		if(statusExcel == '1') {
			listModel.ligerGrid.url = '${pageContext.request.contextPath}/order/sub/data.shtml';
			listModel.initdataPage();
		}else if(statusExcel == '2') {*/
			if(!orderCreateDateBegin || !orderCreateDateEnd) {
				commUtil.alertError("导出时，请选择下单时间！");
			}else {
				$("#dataForm").attr("action","${pageContext.request.contextPath}/order/sub/subExport.shtml");
				$("#dataForm").submit();
			}
  }

  function dataBox(text, id){
	 var obj = new Object();
	 obj.text = text;
	 obj.id = id;
	 return obj;
  }

</script>

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" method="post" action="" >
		<div class="search-pannel">

		<div class="search-tr"  >

			 <div class="search-td">
			 <div class="search-td-label" >订单状态：</div>
			 <div class="search-td-condition" >
				<select id="status" name="status">
					<option value="">请选择...</option>
					<c:forEach var="list" items="${statusList}">
						<option value="${list.statusValue}">${list.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>

			<div class="search-td">
			<div class="search-td-label" >商家序号：</div>
			<div class="search-td-condition" >
				<input type="text" id="mchtCode" name="mchtCode" >
			</div>
			</div>

			<div class="search-td">
			<div class="search-td-label" >商家名称：</div>
			<div class="search-td-condition" >
				<input type="text" id="mchtName" name="mchtName" >
			</div>
			</div>

			<div class="search-td">
			<div class="search-td-label" >订单金额：</div>
			<div class="search-td-condition" >
				<input type="text" id="amountMin" name="amountMin" style="width:60px;" onchange="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}">&nbsp;-&nbsp;<input type="text" id="amountMax" name="amountMax"  style="width:60px;" onchange="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}">
			</div>
			</div>

		</div>

		<div class="search-tr"  >

			<div class="search-td">
			<div class="search-td-label"   style="float: left;width: 25%;margin-top:2px;">下单时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="orderCreateDateBegin" name="orderCreateDateBegin" value="${date_begin}"/>
				<script type="text/javascript">
					$(function() {
						$("#orderCreateDateBegin").ligerDateEditor( {
							showTime : true,
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
				<input type="text" id="orderCreateDateEnd" name="orderCreateDateEnd" value="${date_end}"/>
				<script type="text/javascript">
					$(function() {
						$("#orderCreateDateEnd").ligerDateEditor( {
							showTime : true,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			</div>

			<div class="search-td">
			<div class="search-td-label"  style="float: left;width: 25%;margin-top:2px;">付款时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="orderPayDateBegin" name="orderPayDateBegin" />
				<script type="text/javascript">
					$(function() {
						$("#orderPayDateBegin").ligerDateEditor( {
							showTime : true,
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
				<input type="text" id="orderPayDateEnd" name="orderPayDateEnd" />
				<script type="text/javascript">
					$(function() {
						$("#orderPayDateEnd").ligerDateEditor( {
							showTime : true,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			</div>
		</div>
		<div class="search-tr"  >
			<div class="search-td">
			<div class="search-td-label"  style="float: left;width: 25%;margin-top:2px;">发货时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="deliveryDateBegin" name="deliveryDateBegin" />
				<script type="text/javascript">
					$(function() {
						$("#deliveryDateBegin").ligerDateEditor( {
							showTime : true,
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
				<input type="text" id="deliveryDateEnd" name="deliveryDateEnd" />
				<script type="text/javascript">
					$(function() {
						$("#deliveryDateEnd").ligerDateEditor( {
							showTime : true,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			</div>

			<div class="search-td">
			<div class="search-td-label" >订单号：</div>
			<div class="search-td-condition" >
				<input type="text" id = "subOrderCode" name="subOrderCode" >
			</div>
			</div>

			<div class="search-td">
			<div class="search-td-label" >收货电话：</div>
			<div class="search-td-condition" >
				<input type="text" id = "phone" name="phone" >
			</div>
			</div>
		</div>

		<div class="search-tr"  >
			<div class="search-td">
			<div class="search-td-label" >会员ID：</div>
			<div class="search-td-condition" >
				<input type="text" id = "memberInfoId" name="memberInfoId" >
			</div>
			</div>

			<div class="search-td">
			<div class="search-td-label" >品牌：</div>
			<div class="search-td-condition" >
				<input type="text" id = "brandName" name="brandName" >
			</div>
			</div>

			<div class="search-td">
			<div class="search-td-label" >货号：</div>
			<div class="search-td-condition" >
				<input type="text" id = "artNo" name="artNo" >
			</div>
			</div>

			<div class="search-td">
			<div class="search-td-label" >付款渠道：</div>
			<div class="search-td-condition" >
				<select id="paymentId" name="paymentId">
					<option value="">请选择...</option>
					<c:forEach items="${sysPaymentList}" var="sysPayment">
						<option value="${sysPayment.id}">${sysPayment.remarks}</option>
					</c:forEach>
				</select>
			</div>
			</div>
		</div>
		<div class="search-tr"  >
			<div class="search-td">
				<div class="search-td-label" >用户昵称：</div>
				<div class="search-td-condition" >
					<input type="text" id = "memberNick" name="memberNick" >
				</div>
			</div>

			<div class="search-td">
				<div class="search-td-label" >收货人：</div>
				<div class="search-td-condition" >
					<input type="text" id = "receiverName" name="receiverName" >
				</div>
			</div>

			<div class="search-td">
				<div class="search-td-label" >品类：</div>
				<div style="display: inline-block;" >
					<input type="text" id="productTypeId" name="productTypeId" />

					<%-- <!-- isCwOrgStatus：是否为钟表类目 -->
					<select id="productTypeId" name="productTypeId" <c:if test="${isCwOrgStatus == 1 }">disabled="disabled"</c:if> >
						<c:if test="${isCwOrgStatus == 0 }">
							<option value="">请选择...</option>
						</c:if>
						<c:forEach items="${sysStaffProductTypeList}" var="sysStaffProductType">
							<option value="${sysStaffProductType.productTypeId}">${sysStaffProductType.staffName}</option>
						</c:forEach>
					</select> --%>

				</div>
			</div>
			<div class="search-td">
				<div class="search-td-label" >物流信息：<input type="checkbox" id = "wuliu" name="wuliu" ></div>
				<div class="search-td-condition" >
					只看无物流信息
				</div>
			</div>
		</div>
		<div class="search-tr"  >
			<div class="search-td">
				<div class="search-td-label" style="color: red">订单异常：</div>
				<div class="search-td-condition" >
					<select id="memberStatus" name="memberStatus" >
						<option value="">请选择...</option>
						<option value="NP">正常</option>
						<option value="P">异常</option>
					</select>
				</div>
			</div>
			<%-- <div class="search-td">
				<div class="search-td-label" style="color: red">对接人：</div>
				<div class="search-td-condition" >
					<select id="platformContactId" name="platformContactId" >
						<c:if test="${isContact==1}">
							<c:if test="${sessionScope.USER_SESSION.isManagement=='1'}">
								<option value="">全部商家</option>
							</c:if>
							<option value="${myContactId}">我对接的商家</option>
							<c:forEach items="${platformAssistantContacts}" var="platformAssistantContactList">
								<option value="${platformAssistantContactList.id}">${platformAssistantContactList.contactName}的商家</option>
							</c:forEach>
						</c:if>

						<c:if test="${isContact==0}">
							<option value="">全部商家</option>
							<c:forEach items="${platformMchtContacts}" var="platformMchtContactList">
								<option value="${platformMchtContactList.id}">${platformMchtContactList.contactName}的商家</option>
							</c:forEach>
						</c:if>
					</select>
				</div>
			</div> --%>
			<div class="search-td">
					<div class="search-td-label" style="color: red;">对接人：</div>
					<div class="search-td-condition">
						<select id="platContactStaffId" name="platContactStaffId">
							<c:if test="${isManagement == 1}">
								<option value="" selected="selected">全部商家</option>
							</c:if>
							<option value="${staffID}" selected="selected" >我自己的商家</option>
							<c:forEach items="${sysStaffInfoCustomList}" var="sysStaffInfoCustom">
								<option value="${sysStaffInfoCustom.staffId}">${sysStaffInfoCustom.staffName}的商家</option>
							</c:forEach>
						</select>
					</div>
			</div>
			<div class="search-td">
						<div class="search-td-label"  >推广类型：</div>
						<div class="search-td-combobox-condition" >
							<select id="promotionType" name="promotionType" style="width: 135px;" >
							    <option value="">请选择</option>
								<option value="1">推广分润</option>
								<option value="0">无</option>
							</select>
						</div>
			</div>
			<div class="search-td-search-btn" style="display: inline-flex;">
				<%--<div class="l-button" onclick="subFunction('1');" >搜索</div>--%>
				<div id="searchbtn" >搜索</div>
				<c:if test="${statusPage == '2' }">
					<div style="padding-left: 10px;">
						<input type="button" style="width: 50px;height: 25px;cursor: pointer;" value="导出" onclick="subFunction()"; id="export">
					</div>
				</c:if>
			</div>

		</div>
		</div>

		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>