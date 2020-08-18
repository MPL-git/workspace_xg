<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 150
		});
		
	});
	
	function formatMoney(s, n) {
	    n = n > 0 && n <= 20 ? n : 2;
	    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	    var l = s.split(".")[0].split("").reverse(),
	    r = s.split(".")[1];
	    t = "";
	    for(i = 0; i < l.length; i ++ )
	    {
	       t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "" : "");
	    }
	    return t.split("").reverse().join("") + "." + r;
	 }

	//添加
	function addSeckillBrandGroupProduct(singleProductActivityIds) {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/seckillBrandGroup/addSeckillBrandGroupProduct.shtml',
			data: {singleProductActivityIds : singleProductActivityIds, seckillBrandGroupId : ${seckillBrandGroupId}},
			dataType: 'json',
			success: function(data) {
				if(data.code != null && data.code == 200) {
					$("#searchbtn").click();
				}else {
					commUtil.alertError(data.msg);
				}
			},
			error: function(e) {
				commUtil.alertError("系统异常请稍后再试");
			}
		});
	}
	
	var listConfig = {
		url : "/seckillBrandGroup/singleProductActivityList.shtml",
		btnItems:[{text: '批量添加',icon: 'add',click: function() {
				var data = listModel.gridManager.getSelectedRows();
	           	if (data.length == 0) {
	           		commUtil.alertError('请选择行！');
	           	}else {
	              		var str = "";                         
	               	$(data).each(function() {    
	             	  		if(str=='') {
	             		  		str = this.id ;
	             	  		}else {
	             		  		str += ","+ this.id ;
	             	  		}
	               	});
	               	addSeckillBrandGroupProduct(str); 
	           	}
            }}
			],
		listGrid: {
			columns : [{display:'主图',name:'productPic', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
	            	return "<div style='padding:3px;'><img style='width:50px;height:50px;' src='${pageContext.request.contextPath}/file_servelt"+rowdata.productPic+"'></div>";
	            }},
			    {display:'品牌 / 货号/ 商品ID',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	            	var html = []; 
					if(rowdata.brandName != '') {
						html.push(rowdata.brandName+"<br/>");
					} 
					if(rowdata.artNo != '') {
						html.push(rowdata.artNo+"<br/>");
					}
					if(rowdata.code != '') {
						html.push(rowdata.code);
					}
					return html.join("");
	            }},
	            {display:'店铺名 / 商品名',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	            	var html = []; 
					if(rowdata.shopName != '') {
						html.push(rowdata.shopName+"<br/>");
					} 
					if(rowdata.productName != '') {
						html.push(rowdata.productName);
					}
					return html.join("");
	            }},
            	{display:'活动价格',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
					if(rowdata.activityPrice){
						return formatMoney(rowdata.activityPrice, 2);	                		
                	}else{
                		return "0.00";
                	}
				}},
            	{display:'折扣',name:'discount', align:'center', isSort:false, width:80},
            	{display:'最新库存数',name:'stockSum', align:'center', isSort:false, width:80},
				{display:'操作', name:'', align:'center', isSort:false, width:220, render:function(rowdata, rowindex) {
					var html = []; 
					html.push("<a href=\"javascript:addSeckillBrandGroupProduct(" + rowdata.id + ");\">【添加】</a>");
					return html.join("");
				}}
				],
			showCheckbox : true, //不设置默认为 true
			showRownumber : true
		//不设置默认为 true
		},
		container : {
			toolBarName : "toptoolbar",
			searchBtnName : "searchbtn",
			fromName : "dataForm",
			listGridName : "maingrid"
		}
	};
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" name="beginTime" value="${beginTime }" >
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >商品ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="code" name="code" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >品牌：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="brandName" name="brandName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >货号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="artNo" name="artNo" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >店铺名：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="shopName" name="shopName" >
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
									<option value="${productType.id }">
										${productType.name }
									</option>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${isCwOrgStatus == 0 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" >
								<option value="">请选择...</option>
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">
										${productType.name }
									</option>
								</c:forEach>
							</select>
						</c:if>
				 	 </div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">价格区间：</div>
					<div class="l-panel-search-item">
						<input type="text" id="startActivityPrice" name="startActivityPrice" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endActivityPrice" name="endActivityPrice" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>

	<div id="maingrid" style="margin: 0;"></div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
