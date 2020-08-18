<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<style type="text/css">
.color-s0,.color-fs1{color: #0000FF;}
.color-s1,.color-fs2{color: #008000;}
.color-s4{color: #333333;}
.color-fs0{color: #000000;}
</style>
<script type="text/javascript">
function formatMoney(s, n)
{
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

$(function(){
	$("#batch").live('click',function(){
		if($(this).attr("checked")){
			$("input[name='checkbox']").each(function(){
				$(this).attr("checked",true);
			});
		}else{
			$("input[name='checkbox']").each(function(){
				$(this).attr("checked",false);
			});
		}
	});
	$("input[name='checkbox']").live('click',function(){
		var totalLength = $("input[name='checkbox']").length;
		if($(this).attr("checked")){
			var selectedLength = $("input[name='checkbox']:checked").length;
			if(selectedLength == totalLength){
				$("#batch").attr("checked",true);
			}else{
				$("#batch").attr("checked",false);
			}
		}else{
			$("#batch").attr("checked",false);
		}
	});
	
	$("#export").bind('click',function(){
		var type = $("#type").val();
		var paymentType = $("#paymentType").val();
		var received_date_begin = $("#received_date_begin").val();
		var received_date_end = $("#received_date_end").val();
		var create_date_begin = $("#create_date_begin").val();
		var create_date_end = $("#create_date_end").val();
		location.href="${pageContext.request.contextPath}/receivablePaymentPlatform/exportPaymentPlatform.shtml?type="+type+"&paymentType="+paymentType+"&received_date_begin="+received_date_begin+"&received_date_end="+received_date_end+"&create_date_begin="+create_date_begin+"&create_date_end="+create_date_end;
	});
	
});
	
//批量删除
function batchDelete(ids){
	   $.ajax({ //ajax提交
				type:'POST',
				url:'${pageContext.request.contextPath}' +"/receivablePaymentPlatform/batchDelete.shtml?ids="+ids,
				data:"",
				dataType:"json",
				cache: false,
				success: function(result){
				  if(result.returnCode == "0000"){
	                 $.ligerDialog.success("批量删除成功",function() {
	                            javascript:location.reload();
						});
				  }else{
				     commUtil.alertError(result.returnMsg);
				  }
				},
				error: function(e){
				 commUtil.alertError("系统异常请稍后再试");
				}
	    });
}
	
  var listConfig={
		  
	  url:"/receivablePaymentPlatform/paymentPlatformData.shtml",
	  btnItems:[{ text: '导入', icon: 'add', dtype:'win',  click: itemclick, url: "/receivablePaymentPlatform/toImportData.shtml", seqId:"", width: 600, height: 400},
	            {text: '批量删除', icon: 'delete', click: function(yes) {
	      			  var data = listModel.gridManager.getSelectedRows();
	                          if (data.length == 0)
	                        	  $.ligerDialog.alert('请选择行');
	                          else
	                          {
	                             var str = "";
	                             var params={};
	                             
	                              $(data).each(function ()
	                              {    
	                            	  if(str==''){
	                            		  str = this.id ;
	                            	  }else{
	                            		  str += ","+ this.id ;
	                            	  }
	                              });
	                              $.ligerDialog.confirm('确定删除?', function (yes)
	                              {
	                            	  if (yes){
	                            		  batchDelete(str);  
	                            	  }
	                              }); 
	                          }
	                          return;
	      			  }}
	            ],
      listGrid:{ columns: [
                        { display: "回款日期", render: function(rowdata, rowindex) {
                        	var receivedDate = new Date(rowdata.receivedDate);
							return receivedDate.format("yyyy-MM-dd");
    					}},
 		                { display: '交易号', name: 'paymentNo'},
		                { display: '金额', render: function (rowdata, rowindex) {
		                	if(rowdata.amount){
	 		                	return formatMoney(rowdata.amount,2);
 		                	}else{
 		                		return "0.00";
 		                	}
		                }},
		                { display: '类型', render: function(rowdata, rowindex) {
                        	if(rowdata.combineOrderCode && rowdata.receivedDate == rowdata.customerPayDate){
                        		return "实收当日";
                        	}else if(rowdata.combineOrderCode && rowdata.receivedDate != rowdata.customerPayDate){
                        		return "实收非当日";
                        	}else if(!rowdata.combineOrderCode){
                        		return "未在应收中";
                        	}
    					}},
		                { display: '支付渠道', render: function(rowdata, rowindex) {
		                	if(rowdata.paymentType == "1"){
		                		return "微信(APP、H5)";	
		                	}else if(rowdata.paymentType == "2"){
								return "微信(公众号)";		                		
		                	}else if(rowdata.paymentType == "3"){
			                	return "支付宝(APP、H5)";
		                	}
    					}},
		                { display: '客户付款日期', render: function (rowdata, rowindex) {
		                	if (rowdata.customerPayDate){
								var customerPayDate = new Date(rowdata.customerPayDate);
								return customerPayDate.format("yyyy-MM-dd");
		                	}
		                }},
		                { display: '母订单号', name:'combineOrderCode'},
		                { display: '导入时间', render: function (rowdata, rowindex) {
		                	if (rowdata.createDate!=null){
								var createDate = new Date(rowdata.createDate);
								return createDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }}
		                ],
                 showCheckbox : true,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
     container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
}
</script>

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">回款日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="received_date_begin" name="received_date_begin" value="${received_date_begin}"/>
				<script type="text/javascript">
					$(function() {
						$("#received_date_begin").ligerDateEditor({
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="received_date_end" name="received_date_end" value="${received_date_end}"/>
				<script type="text/javascript">
					$(function() {
						$("#received_date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			 
			 <div class="search-td">
			 <div class="search-td-label">类型：</div>
			 <div class="search-td-condition" >
				<select id="type" name="type">
					<option value="">请选择</option>
						<option value="1">实收当日</option>
						<option value="2">实收非当日</option>
						<option value="3">未在应收中</option>
				</select>
		 	 </div>
			 </div>
			 
			 <div class="search-td">
			 <div class="search-td-label" >支付渠道：</div>
			 <div class="search-td-condition" >
				<select id="paymentType" name="paymentType">
					<option value="">请选择</option>
					<option value="1">微信（APP、H5）</option>
					<option value="2">微信(公众号)</option>
					<option value="3">支付宝(APP、H5)</option>
				</select>
		 	 </div>
			 </div>
		</div>
		
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">导入时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="create_date_begin" name="create_date_begin" />
				<script type="text/javascript">
					$(function() {
						$("#create_date_begin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
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
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			
			<div style="display: inline-flex;">
				<div id="searchbtn" style="height: 28px;line-height: 28px;">
					查看
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="导出" id="export">
				</div>
			</div>
		</div>
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>