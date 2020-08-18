<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>保证金缴款记录</title>
</head>

<body>
<div class="modal-dialog" role="document" style="width:1000px;">
	<div class="modal-content">
		<div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <span class="modal-title" id="exampleModalLabel">保证金缴款记录</span>
      	</div>
      	<div class="modal-body">
      		<div class="x_panel container-fluid" style="width: 960px;">
				<div class="search-container container-fluid"
					style="padding:10px 10px;">
						<form class="form-horizontal" id="depositOrderSearchForm">
						<div class="form-group">
							<label class="col-md-1 control-label search-lable" style="width: 38px;">时间:</label>
							<div class="col-md-2 search-condition" style="width:13.5833210%">
								 <input class="form-control datePicker" type="text"  id="createTimeBegin" name="createTimeBegin" data-date-format="yyyy-mm-dd">
							</div>
							<div class="col-md-1 search-condition text-center"  style="width:2%;padding:5px 0;" >
								<span>--</span> 
							</div>
							<div class="col-md-2 search-condition" style="width:13.5833210%">
								 <input class="form-control datePicker" type="text"  id="createTimeEnd" name="createTimeEnd" data-date-format="yyyy-mm-dd">
							</div>
							<label for="" class="col-md-1 control-label search-lable">支付方式:</label>
							<div class="col-md-2 search-condition">
								   <select class="form-control" name="search_paymentType" id="search_paymentType">
								   		<option value="">不限</option>
								   		<c:forEach items="${paymentTypeList}" var="paymentType">
								   			<option value="${paymentType.statusValue}">${paymentType.statusDesc}</option>
								   		</c:forEach>
		                           </select>
							</div>
							<div class="col-md-3 text-center search-btn">
								             <button type="button"  class="btn btn-info" id="btn-search">
		                                    <i class="glyphicon glyphicon-search"></i>搜索</button>
							</div>
						</div>
						</form>
		
				</div>
				<div class="clearfix"></div>
				<div class="x_content container-fluid">
						<div class="row">
							<div class="col-md-12 datatable-container">
								<table id="depositOrderDatatable"
									class="table table-striped table-bordered dataTable no-footer"
									role="grid" aria-describedby="datatable_info">
								</table>
							</div>
						</div>
				</div>
			</div>
      	</div>
	</div>
</div>
	
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<script>
var table;

$(document).ready(function () {
	
	$('.datePicker').datetimepicker(
    		{
    			minView: "month", //选择日期后，不会再跳转去选择时分秒
				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                autoclose:true //选择日期后自动关闭
    	    }
    );
	
    table = $('#depositOrderDatatable').dataTable({
        "ajax": function (data, callback, settings) {
            var param = $("#depositOrderSearchForm").serializeArray();
            
            param.push({"name":"pagesize","value":data.length});
            if (data.start > 0) {
                param.push({"name":"page","value":data.start/data.length+1});
            } else {
                param.push({"name":"page","value":1});
            }
            
            
            $.ajax({
                method: 'POST',
                url: '${ctx}/mchtDeposit/getDepositOrderList',
                data: param,
                dataType: 'json'
            }).done(function (result) {
                if (result.returnCode =='0000') {
                    var returnData = {};
                    returnData.recordsTotal = result.returnData.Total;
                    returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                    returnData.data = result.returnData.Rows;
                    callback(returnData);
                    
                    $("a[name='showDetail']").on('click',function(){
                   		var tr = $(this).parent().parent();
                   		if($(tr).next().attr("name") == "detail"){
                   			if($(tr).next().attr("display") == "block"){
                   				$(tr).next().attr("display","none");
                   				$(tr).next().hide();
                   				$(this).text("查看");
                   			}else{
                   				$(tr).next().attr("display","block");
                   				$(tr).next().show();
                   				$(this).text("隐藏");
                   			}
                   			$(tr).next().attr("display")
                   		}else{
	                    	var paymentType = $(this).data("paymenttype");
	                    	var paymentNo = $(this).data("paymentno");
                    		var trClass = $(tr).prop("class");
	                    	if(paymentType == "1"){//支付宝
	                    		if(trClass == "odd"){
		                    		$(tr).after("<tr role='row' class='even' name='detail' display='block'><td colspan='7'>支付宝交易号："+paymentNo+"</td></tr>");
	                    		}else if(trClass == "even"){
		                    		$(tr).after("<tr role='row' class='odd' name='detail' display='block'><td colspan='7'>支付宝交易号："+paymentNo+"</td></tr>");
	                    		}
	                    	}else if(paymentType == "3"){//网银
	                    		var paymentname = $(this).data("paymentname");
		                    	var accountno = $(this).data("accountno");
		                    	var accountname = $(this).data("accountname");
		                    	var platformpaymentname = $(this).data("platformpaymentname");
		                    	var platformaccountno = $(this).data("platformaccountno");
		                    	var platformaccountname = $(this).data("platformaccountname");
	                    		var html=[];
	                    		if(trClass == "odd"){
	                    			html.push("<tr role='row' class='even' name='detail' display='block'>");
	                    		}else if(trClass == "even"){
		                    		html.push("<tr role='row' class='odd' name='detail' display='block'>");
	                    		}
	                    		html.push("<td colspan='7'>");
	                    		html.push("<div>");
	                    		html.push("<div class='info-row'><span class='info-title'>支付银行卡户名&nbsp;:</span><span class='info-cotent'>"+accountname+"</span><span class='info-title'>,开户银行&nbsp;:</span><span class='info-cotent'>"+paymentname+"</span><span class='info-title'>,银行账号&nbsp;:</span><span class='info-cotent'>"+accountno+"</span></div>");
	                    		html.push("<div class='info-row'> <span class='info-title'>户名&nbsp;:</span><span class='info-cotent'>"+platformaccountname+"</span></div>");
	                    		html.push("<div class='info-row'> <span class='info-title'>开户银行&nbsp;:</span><span class='info-cotent'>"+platformpaymentname+"</span></div>");
	                    		html.push("<div class='info-row'> <span class='info-title'>银行卡号&nbsp;:</span><span class='info-cotent'>"+platformaccountno+"</span></div>");
	                    		html.push("</div>");
	                    		html.push("</td>");
	                    		html.push("</tr>");
	                    		$(tr).after(html.join(""));
	                    	}
	                    	$(this).text("隐藏");
                   		}
                    });
                }
            });
        },
        

        "language": {"url": '${ctx}/static/cn.json'},
        "autoWidth": true,   // 禁用自动调整列宽
        "stripeClasses": ['odd', 'even'], // 为奇偶行加上样式，兼容不支持CSS伪类的场合
        "order": [[1, 'asc']],          // 取消默认排序查询,否则复选框一列会出现小箭头
        "processing": true,  // 隐藏加载提示,自行处理
        "serverSide": true,   // 启用服务器端分页
        "searching": false,   // 禁用原生搜索
        "bSort": false,
        "bServerSide": true,
        "columns": [
            {"data": "id","title":"支付方式","render": function (data, type, row, meta) {
            	return row.paymentTypeDesc;
            }},
            {"data": "id","title":"金额（元）","render": function (data, type, row, meta) {
			    return row.amount;
            }},
            {"data": "id","title":"缴款时间","render": function (data, type, row, meta) {
			    return new Date(row.createDate).format("yyyy-MM-dd hh:mm:ss");
            }},
            {"data": "id","title":"状态","render": function (data, type, row, meta) {
	            return  row.statusDesc;
            }},
            {"data": "id","title":"操作","render": function (data, type, row, meta) {
            	var platformPaymentName="";
            	var platformAccountNo="";
            	var platformAccountName="";
            	if(row.platformCapitalAccount){
            		platformPaymentName = row.platformCapitalAccount.paymentName;
            		platformAccountNo = row.platformCapitalAccount.accountNo;
            		platformAccountName = row.platformCapitalAccount.accountName;
            	}
	            return  "<a href='javascript:;' name='showDetail' data-paymentname='"+row.paymentName+"' data-accountno='"+row.accountNo+"' data-accountname='"+row.accountName+"' data-paymentno='"+row.paymentNo+"' data-paymenttype='"+row.paymentType+"' data-accountid='"+row.accountId+"' data-platformpaymentname='"+platformPaymentName+"' data-platformaccountno='"+platformAccountNo+"' data-platformaccountname='"+platformAccountName+"'>查看</a>";
            }}
        ]
    }).api();

    $('#btn-search').on('click', function (event) {
        table.ajax.reload();
    });
    
});
</script>
</body>
</html>
