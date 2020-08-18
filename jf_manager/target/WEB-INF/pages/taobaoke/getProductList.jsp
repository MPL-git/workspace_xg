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
 var delArray = [];
 $(function(){
	// 禁止分页
	$("#maingrid2").ligerGrid({
        usePager:false
    }); 
	$("#save").bind('click',function(){
		var data = listModel.gridManager.getSelectedRows();
        if (data.length == 0){
      	  $.ligerDialog.alert('请选择行');
        }else{
        	var productData = JSON.stringify(data); 
        	var productTypeId = $("#productTypeId").val();
        	var delData = delArray.join(",");
        	var wetaoChannel=$("#wetaoChannel").val();
        	alert("保存中，过程有点漫长，请耐心等待");
        	$.ajax({
    			url : "${pageContext.request.contextPath}/taobaoke/saveTaobaoProductList.shtml",
    			type : 'POST',
    			dataType : 'json',
    			cache : false,
    			async : false,
    			data : {"productData":productData,"productTypeId":productTypeId,"delData":delData,"wetaoChannel":wetaoChannel},
    			timeout : 30000,
    			success : function(data) {
    				if ("0000" == data.returnCode) {
    					commUtil.alertSuccess("保存成功");
    					setTimeout(function(){
    						frameElement.dialog.close();
    					},1000);
    				}else{
    					$.ligerDialog.error(data.returnMsg);
    				}
    			},
    			error : function() {
    				$.ligerDialog.error('操作超时，请稍后再试！');
    			}
    		});
        }
        return;
	});
 }); 

function toView(id){
	$.ligerDialog.open({
		height: $(window).height()*0.8,
		width: $(window).width()*0.8,
		title: "查看商品",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/taobaoke/toView.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function del(_this,num_iid){
	$.ligerDialog.confirm('确定删除吗?', function (yes){
		if(yes==true){
			var $tr = $(_this).closest("tr");
			var $table = $(_this).closest("table");
			var idx = $.inArray($tr,$table.find("tr"));
			var $tr1 = $("#maingrid2").find("table").eq(0).find("tr").eq(idx);
			$tr1.remove();
			$tr.remove();
			delArray.push(num_iid);
		}
    }); 
}
 var listConfig={
     url:"/taobaoke/getProductData.shtml",
     listGrid:{ columns: [
						{ display: '主图', render: function (rowdata, rowindex) {
							return '<img src="'+rowdata.pict_url+'" style="width:60px;height:60px;" >';	                	
		                }},
		                { display: '淘宝/天猫商品ID', width:100,render: function (rowdata, rowindex) {
							return rowdata.num_iid;		                	
		                }},
		                { display: '名称',width:'180' ,name:'title'},
		                { display: '分类',render: function (rowdata, rowindex) {
							return "${productType.name}";		                	
		                }},
		                { display: '销量', name: 'volume'},
		                { display: '原价', name: 'reserve_price'}, 
		                { display: '劵后价', name: 'zk_final_price'},
		                { display: '优惠券金额', name: 'coupon_info'},
		                { display: '优惠券剩余', render: function (rowdata, rowindex) {
		                	if(rowdata.coupon_remain_count && rowdata.coupon_total_count){
								return rowdata.coupon_remain_count+"/"+rowdata.coupon_total_count;		                		
		                	}
						}},
		                { display: '优惠券有效期', render: function (rowdata, rowindex) {
		                	var html = [];
		                	if(rowdata.coupon_start_time){
		                		html.push(rowdata.coupon_start_time);
		                	}
		                	if(rowdata.coupon_end_time){
		                		html.push("至");
		                		html.push(rowdata.coupon_end_time);
		                	}
	                		return html.join("");
		                }},
		                { display: '优惠券地址', render: function (rowdata, rowindex) {
	                		return rowdata.coupon_click_url;
		                }},
		                { display: '佣金比例', render: function (rowdata, rowindex) {
	                		return Number(rowdata.commission_rate)/100+"%";
		                }},
		                { display: '类型', render: function (rowdata, rowindex) {
		                	if(rowdata.user_type == 0){
		                		return "淘宝";
		                	}else if(rowdata.user_type == 1){
		                		return "天猫";
		                	}
		                }},
		                { display: '操作', render: function (rowdata, rowindex) {
		                	var html = [];
		                	/* html.push('<a href="javascript:;" onclick="toView('+rowdata.id+');">查看</a>');
		                	html.push("|"); */
		                	html.push('<a href="javascript:;" rowindex="'+rowindex+'" onclick="del(this,'+rowdata.num_iid+');">删除</a>');
	                		return html.join("");
		                }}
		                ],
                 showCheckbox : true,  //不设置默认为 true
                 showRownumber: true //不设置默认为 true
      } ,  							
     container:{
        searchBtnName:"search",
        fromName:"dataForm2",
        listGridName:"maingrid2"
      }        
  };
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
	<form id="dataForm2" runat="server" method="post">
		<input type="hidden" id="productTypeId" name="productTypeId" value="${productTypeId}">
		<input type="hidden" id="num" name="num" value="${num}">
		<input type="hidden" id="isCoupon" name="isCoupon" value="${isCoupon}">
		<input type="hidden" id="source" name="source" value="${source}">
		<input type="hidden" id="keyword" name="keyword" value="${keyword}">
		<input type="hidden" id="start_price" name="start_price" value="${start_price}">
		<input type="hidden" id="end_price" name="end_price" value="${end_price}">
		<input type="hidden" id="start_tk_rate" name="start_tk_rate" value="${start_tk_rate}">
		<input type="hidden" id="end_tk_rate" name="end_tk_rate" value="${end_tk_rate}">
		<input type="hidden" id="need_free_shipment" name="need_free_shipment" value="${need_free_shipment}">
		<input type="hidden" id="wetaoChannel" name="wetaoChannel" value="${wetaoChannel}">
		<div class="search-pannel">
		<div class="search-tr">
			<div>
				<div class="search-td-condition">
					<input type="button" style="width: 80px;height: 25px;cursor: pointer;" value="提交保存" id="save">
				</div>
			</div>
		</div>
		</div>
		<div id="maingrid2" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>