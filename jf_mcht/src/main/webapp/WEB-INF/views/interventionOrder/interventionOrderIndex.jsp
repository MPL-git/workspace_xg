<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>介入单管理</title>
</head>

<body>
<div class="x_panel container-fluid gf-hd">
    <div class="row content-title">
        <div class="t-title">
           	 介入单管理
        </div>
    </div>
    <div class="search-container container-fluid">
        <form class="form-horizontal" id="searchForm">
            <input type="hidden" name="searchOrderType" value="${searchOrderType}"/>
            <div class="form-group">
					<label for="productBrand" class="col-md-1 control-label search-lable">介入单编号：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_interventionCode" name="search_interventionCode" >
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">售后单号：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_customerServiceOrderCode" name="search_customerServiceOrderCode" >
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">介入原因：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="search_reason" id="search_reason">
						   <option value="">--请选择--</option>
						   <c:forEach var="reason" items="${reasonList}">
						   		<option value="${reason.statusValue}">${reason.statusDesc}</option>
						   </c:forEach>
                          </select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="productBrand" class="col-md-1 control-label search-lable">联系人：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_contacts" name="search_contacts" >
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable mwa">申诉状态：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="search_status" id="search_status">
						   		<option value="">不限状态</option>
						   		<option value="4">待申诉</option>
						   		<option value="82">申诉成功</option>
						   		<option value="81">申诉失败</option>
						   		<option value="6">待商家上传凭证</option>
						   		<option value="1">平台未受理</option>
                           </select>
					</div>
					<div class="col-md-3 text-center search-btn">
						<button type="button"  class="btn btn-info" id="btn-query">搜索</button>
						<button type="button"  class="btn btn-info" id="btn-out">导出</button>
					</div>
				</div>
        </form>
    </div>
    <div class="clearfix"></div>
    <ul class="nav nav-tabs q" role="tablist">
        <li role="presentation" <c:if test="${searchOrderType==0}">class="active"</c:if>> <a href="#" role="tab" data-toggle="tab" onclick="list(0);">全部介入单</a></li>
        <li role="presentation" <c:if test="${searchOrderType==1}">class="active"</c:if>> <a href="#" role="tab" data-toggle="tab" onclick="list(1);">待申诉</a></li>
        <li role="presentation" <c:if test="${searchOrderType==2}">class="active"</c:if>> <a href="#" role="tab" data-toggle="tab" onclick="list(2);">申诉中</a></li>
        <li role="presentation" <c:if test="${searchOrderType==3}">class="active"</c:if>> <a href="#" role="tab" data-toggle="tab" onclick="list(3);">待上传处理凭证</a></li>
    </ul>
    <div class="x_content container-fluid">
        <div class="row">
            <div class="col-md-12 datatable-container">
                <table id="datatable"
                       class="table table-striped table-bordered dataTable no-footer"
                       role="grid" aria-describedby="datatable_info">
                    <thead>
                    <tr role="row">
                        <th>介入单号</th>
						<th>售后单号</th>
						<th>介入原因</th>
						<th>联系人</th>
						<th>剩余处理时间</th>
						<th>申诉状态</th>
						<th>胜诉方</th>
						<th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
     data-backdrop="static">
</div>

<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<script>
    var table;
    $(document).ready(function () {
        table = $('#datatable').dataTable({
            "ajax": function (data, callback, settings) {
                var param = $("#searchForm").serializeArray();
    			param.push({"name":"pagesize","value":data.length});
    			if (data.start > 0) {
    				param.push({"name":"page","value":data.start/data.length+1});
    			} else {
    				param.push({"name":"page","value":1});
    			}
                $.ajax({
                    method: 'POST',
                    url: '${ctx}/interventionOrder/getInterventionOrderList',
                    data: param,
                    dataType: 'json'
                }).done(function (result) {
                    if (result.returnCode =='0000') {
                    	var returnData = {};
                        returnData.recordsTotal = result.returnData.Total;
                        returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.returnData.Rows;
                        callback(returnData);
                        $("#datatable").find("tbody").find("tr").each(function(){
                        	var $this = $(this);
                        	if($this.find("td").eq(1).find("a").eq(0).attr("statusDesc")=="待商家申诉" || $this.find("td").eq(1).find("a").eq(0).attr("statusDesc")=="待商家上传凭证"){
                        		var updateDate = $this.find("td").eq(1).find("a").eq(0).attr("updateDate");
                        		var endTime = Number(updateDate)+Number(2*24*3600*1000);
                        		leftTimer($this.find("td").eq(4),endTime);
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
               		{"data": "interventionCode","render": function (data, type, row, meta) {
               			
               			var blackMemberRemark="";
            			//黑名单订单
            			if(row.memberStatus=='P'){
            				blackMemberRemark = '<span style="color:red;">（用户异常）</span>';
            			}
            			if(blackMemberRemark!=""){
            				return row.interventionCode+"<br>"+blackMemberRemark;
            			}else{
            				return row.interventionCode;
            			}
                    }},
                    {"data": "id","render": function (data, type, row, meta) {
                    	return "<a class='table-opr-btn' href='javascript:void(0);' statusDesc='"+row.statusDesc+"' updateDate='"+row.updateDate+"' onclick='viewCustomerServiceOrder("+row.serviceOrderId+")'>"+row.customerServiceOrderCode+"</a>";
                    }},
                    {"data": "reasonDesc"},
                    {"data": "contacts"},
                    {"data": "id", "render": function (data, type, row, meta) {
	                    return "";
                    }},
                    {"data": "id","render": function (data, type, row, meta) {
                    	if(row.status == "1"){
                    		return "平台未受理";
                    	}else if(row.status == "4"){
                    		return "待申诉";
                    	}else if(row.status == "5"){
                    		return "申诉中";
                    	}else if(row.status == "6"){
                    		return "待上传凭证";
                    	}else if(row.status == "8"){
                    		if(row.winType == "1"){//买家胜诉
                    			return "申诉失败";
                    		}else if(row.winType == "2"){//商家胜诉
                    			return "申诉成功";
                    		}else{
                    			return "";
                    		}
                    	}else{
                    		return "";
                    	}
                    }},
                    {"data": "id", "render": function (data, type, row, meta) {
                    	if(row.winType == 1){//1.买家
		                    return "买家";
                    	}else if(row.winType == 2){//2.商家
		                    return "商家";
                    	}else{
		                    return "";
                    	}
                    }},
                    {"data": "id","render": function (data, type, row, meta) {
                    	if(row.status == "6"){
	                    	return "<a class='table-opr-btn' href='javascript:void(0);' onclick='viewInterventionOrder("+row.id+")'>【查看】</a><a class='table-opr-btn' href='javascript:void(0);' onclick='viewCustomerServiceOrder("+row.serviceOrderId+")'>【去处理售后】</a>";
                    	}else{
	                    	return "<a class='table-opr-btn' href='javascript:void(0);' onclick='viewInterventionOrder("+row.id+")'>查看</a>";
                    	}
                    }}
            ]
        }).api();

        $('#btn-query').on('click', function (event) {
            table.ajax.reload();
        });
		
        $("#btn-out").on('click',function(){
        	var search_interventionCode = $("#search_interventionCode").val();
        	var search_customerServiceOrderCode = $("#search_customerServiceOrderCode").val();
        	var search_reason = $("#search_reason").val();
        	var search_contacts = $("#search_contacts").val();
        	var search_status = $("#search_status").val();
        	location.href="${ctx}/interventionOrder/export?search_interventionCode="+search_interventionCode+"&search_customerServiceOrderCode="+search_customerServiceOrderCode+"&search_reason="+search_reason+"&search_contacts="+search_contacts+"&search_status="+search_status;
        });
    });
    
    function list(searchOrderType) {
        var url = "${ctx}/interventionOrder/interventionOrderIndex?searchOrderType=" + searchOrderType;
        getContent(url);
    }
    
    function viewCustomerServiceOrder(id){
		$.ajax({
	        url: "${ctx}/customerServiceOrder/customerServiceOrderView?id="+id, 
	        type: "GET", 
	        success: function(data){
	            $("#viewModal").html(data);
	            $("#viewModal").modal();
	        },
		    error:function(){
		    	swal('页面不存在');
		    }
		});

	}
    
    function viewInterventionOrder(id){
		$.ajax({
	        url: "${ctx}/interventionOrder/interventionOrderView?id="+id, 
	        type: "GET", 
	        success: function(data){
	            $("#viewModal").html(data);
	            $("#viewModal").modal();
	        },
		    error:function(){
		    	swal('页面不存在');
		    }
		});

	}
    function leftTimer(object,endTime){
   	 var nowDate = new Date();
   	 var nowTime = nowDate.getTime();
   	 if(endTime > nowTime){
   		var leftTime = endTime - nowTime;//计算剩余的毫秒数 
   		var days = parseInt(leftTime / 1000 / 60 / 60 / 24 , 10); //计算剩余的天数 
   		var hours = parseInt(leftTime / 1000 / 60 / 60 % 24 , 10); //计算剩余的小时 
   		var minutes = parseInt(leftTime / 1000 / 60 % 60, 10);//计算剩余的分钟 
   		days = checkTime(days); 
   		hours = checkTime(hours); 
   		minutes = checkTime(minutes);
   		setTimeout(function() { 
   			leftTimer(object,endTime); 
   		},1000);
   	    $(object).html('<span style="color: red;">'+days+'</span> 天 <span style="color: red;">'+hours+'</span> 时<span style="color: red;">'+minutes+'</span>分钟<br>');
   	 }else{
   		$(object).html('<span style="color: red;">已超时</span>');
   	 }
   }	
	function checkTime(i){ //将0-9的数字前面加上0，例1变为01 
		 if(i<10){ 
		  	i = "0" + i;
		  } 
		 	return i; 
	}
</script>
</body>
</html>
