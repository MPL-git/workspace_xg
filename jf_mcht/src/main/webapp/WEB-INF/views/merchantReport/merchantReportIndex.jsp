<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>商家举报</title>
<style type="text/css">


</style>
</head>

<body>
	<div class="x_panel container-fluid py-tm">

		<div class="row content-title">
			<div class="t-title">
			商家举报
			<a href='javascript:void(0);' onclick="addTpl();" >举报</a>
			</div>
		</div>
		<div class="search-container container-fluid">
				<form class="form-horizontal" id="searchForm">
				<input type="hidden" name="search_remarksColor" id="search_remarksColor" >
				<input type="hidden" value="${toBeRevised}" id="toBeRevised" >
				<div class="form-group">
				
					<label for="productBrand" class="col-md-1 control-label search-lable">举报时间：</label>
					<div class="col-md-5 search-condition">
						 <input class="form-control datePicker"  id="createTimeBegin" name="createTimeBegin" data-date-format="yyyy-mm-dd" >
						 <i class="picker-split">-</i>
						 <input class="form-control datePicker"  id="createTimeEnd" name="createTimeEnd" data-date-format="yyyy-mm-dd">
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">举报编号：</label>
					<div class="col-md-5 search-condition" >
						 <input class="form-control" type="text"  id="search_reportCode" name="search_reportCode"  style="width:180px;" >
					</div>
					
					
					<label for="productBrand" class="col-md-1 control-label search-lable">举报状态：</label>
					<div class="col-md-5 search-condition">
						   <select class="form-control" name="search_status" id="search_status">
						   <option value="">--请选择--</option>
						   <option value="1">待处理</option>
						   <option value="2">处理中</option>
						   <option value="3" >驳回</option>
						   <option value="4">通过</option>
						   
						  <%-- <c:if test="${status == '3'}">selected</c:if>  
						  
						  <c:forEach var="appealOrderAppealType" items="${appealOrderAppealTypeList}">
						   		<option value="${appealOrderAppealType.statusValue}">${appealOrderAppealType.statusDesc}</option>
						   </c:forEach> --%>
                          </select>
					</div>
					
				<!--用于选择全部举报和待修改    -->
			 	 <select class="form-control" name="toBeRevised" id="toBeRevised" name="toBeRevised"  style="display:none" >
				 <option <c:if test="${toBeRevised == '-1'}">selected</c:if>  value="-1" >-1</option>
				 <option <c:if test="${toBeRevised == '2'}">selected</c:if>  value="2" >2</option>
				 </select> 
			
				</div>
				
				
				<div class="form-group">
				
					<label for="productBrand" class="col-md-1 control-label search-lable">子订单号：</label>
					<div class="col-md-5 search-condition" >
						 <input class="form-control" style="width:250px;"  type="text"  id="search_subOrderCode" name="search_subOrderCode" >
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable mwa">举报类型：</label>
					<div class="col-md-2 search-condition">
						  <select class="form-control" name="search_appealType" id="search_appealType" style="width:180px;" >
						   <option value="">--请选择--</option>
						<c:forEach var="reportingType" items="${reportingTypeList}">
						   		<option value="${reportingType.statusValue}">${reportingType.statusDesc}</option>
						   </c:forEach>
						  <%--  <option value="-1" <c:if test="${status == '-1'}">selected="selected"</c:if>>不限状态</option>
						   <c:forEach var="appealOrderStatus" items="${appealOrderStatusList}">
						   		<option value="${appealOrderStatus.statusValue}">${appealOrderStatus.statusDesc}</option>
						   </c:forEach> --%>
                          </select>
					</div>
					
				
					<div class="col-md-3 text-center search-btn">
						             <button type="button"  class="btn btn-info" id="btn-query">搜索</button>
					</div>
				</div>
			</form>
		</div>
  <!-- Nav tabs -->
  <ul class="nav nav-tabs q" role="tablist">
    <li role="presentation" <c:if test="${toBeRevised=='-1'}">class="active"</c:if>><a href="#"  role="tab" data-toggle="tab" onclick="list('-1');">全部举报</a></li>
    <li role="presentation" <c:if test="${toBeRevised=='2'}">class="active"</c:if>><a href="#" role="tab" data-toggle="tab" onclick="list('2');">待修改</a></li>
  </ul>

<!--   Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane row active" id="contact_1">
    				<div class="col-md-12 datatable-container">
						<table id="dataTableId" class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							
						</table>
					</div>
	</div>
  </div>

</div>
		
	</div>
	
	
<!-- 	查看信息框 -->

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">

</div>
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
	<!-- Bootstrap -->
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>

	<script>
	
var table;
$(document).ready(function () {
	
/* 	$("select['toBeRevised']").hide(); */
	
	$('.datePicker').datetimepicker(
    		{
    			minView: "month", //选择日期后，不会再跳转去选择时分秒
				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                autoclose:true//选择日期后自动关闭       
                
    	    }
    ); 
    
    
   

	
	table =	$('#dataTableId').dataTable({
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
                url: '${ctx}/merchantReport/getMerchantReportList',
                data: param,
                dataType: 'json'
            }).done(function (result) {
                if (result.returnCode =='0000') {
                    var returnData = {};
                    returnData.recordsTotal = result.returnData.Total;
                    returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                    returnData.data = result.returnData.Rows;
                    callback(returnData);
                    
                    $("span[name='dealTime']").each(function(){
						var lasteditdate = $(this).data("lasteditdate");
						var endTime = lasteditdate;
						var title="剩余处理时间：";
						var desc = "";
						var status = $(this).data("status");
						if(status == "3"||status == "8"){
							leftTimer(this,endTime,desc);
                    	}
					});
                }
            });
        },
        "language": {"url": '${ctx}/static/cn.json'},
        "bAutoWidth":false,
        "stripeClasses": ['odd', 'even'], // 为奇偶行加上样式，兼容不支持CSS伪类的场合
        "order": [[1, 'asc']],          // 取消默认排序查询,否则复选框一列会出现小箭头
        "processing": true,  // 隐藏加载提示,自行处理
        "serverSide": true,   // 启用服务器端分页
        "searching": false,   // 禁用原生搜索
        "bSort": false,
        "bServerSide": true,
        "columns": [
            {"data": "createDate","title":"举报时间","width":"15%","render": function (data, type, row, meta) {
            	return new Date(row.createDate).format("yyyy-MM-dd hh:mm:ss");
            }},
            {"data": "code","title":"举报编号","width":"15%","render": function (data, type, row, meta) {
            	 return row.code; 
            }},
            {"data": "status","title":"状态","width":"11%","render": function (data, type, row, meta) {
            	var html = [];
            	if(row.status == "0"){
            		html.push("待处理")
            	}
            	if(row.status == "1"||row.status == "2"||row.status == "4"||row.status == "5"||row.status == "6"){
            		html.push("处理中")
            	}
            	if(row.status == "3"){
            		html.push("驳回")
            	}
            	if(row.status == "7"){
            		html.push("通过")
            	}
            	if(row.status == "8"){
            		html.push("超时关闭")
            	}
            	return html.join("");
            }},
            {"data": "typeDesc","title":"举报类型","width":"10%","render": function (data, type, row, meta) {
            	return row.typeDesc;
            }},
            {"data": "subOrderCodes","title":"相关单号","width":"6%","render": function (data, type, row, meta) {
            	var html=[]
           	      var str =	row.subOrderCodes;
           	      var arr = str.split(",")
           	      for(var i = 0 ;i<arr.length;i++){
           	    	  html.push(arr[i]+"</br>")
           	      }
        		 return html.join("");
            }},

            {"data": "lastEditDate","title":"剩余修改时间","width":"11%","render": function (data, type, row, meta) {
            	var html=[];
            	html.push('<span width="129" class="br" name="dealTime" data-status="'+row.status+'" data-lasteditdate="'+row.lastEditDate+'"></span>')
            	var date = new Date();
            /* 	var date2 = row.lastEditDate;
            	dateTime = date.getTime();
            	dateTime2 = date2.getTime(); */
             	if(date>row.lastEditDate&&row.status==3&&row.lastEditDate!=null){
            		overtimeClosure(row.id);
            	} 
            return html.join("");
            }},
            {"data": "id","title":"操作","width":"6%","render": function (data, type, row, meta) {
            	var beginDate = new Date();
            	var beginTime = beginDate.getTime()
            	var lastTime = row.lastEditDate
            	if(row.status == "3" && beginTime<lastTime){
            		return "<a class='table-opr-btn' href='javascript:void(0);' onclick='viewUpdatelOrder("+row.id+")'>修改</a>";
            	}else{
            		return "<a class='table-opr-btn' href='javascript:void(0);' onclick='viewAppealOrder("+row.id+")'>查看</a>";
            	}
            	
            }}
        ]
    }).api();	
	
	$('#btn-query').on('click', function (event) {
	    table.ajax.reload();
	});
});

function overtimeClosure(id){
	var url = "${ctx}/merchantReport/overtimeClosure?id=" + id;
	getContent(url); 
}


function leftTimer(object,endTime,desc,title){
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
			leftTimer(object,endTime,desc,title); 
		},1000)
		if(title){
		    $(object).html(title+'<span style="color: red;">'+days+'</span> 天 <span style="color: red;">'+hours+'</span> 时<span style="color: red;">'+minutes+'</span>分<br>'+desc);
		}else{
		    $(object).html('<span style="color: red;">'+days+'</span> 天 <span style="color: red;">'+hours+'</span> 时<span style="color: red;">'+minutes+'</span>分<br>'+desc);
		}
	 }else{
		$(object).html('<span style="color: red;" >超时关闭</span>'); 
	 }
}	
function checkTime(i){ //将0-9的数字前面加上0，例1变为01 
	 if(i<10){ 
	  	i = "0" + i;
	  } 
	 	return i; 
}

function list(toBeRevised) {
	var url = "${ctx}/merchantReport/merchantReportIndex?toBeRevised=" + toBeRevised;
	getContent(url);
}

function addTpl(){
	$.ajax({
        url: "${ctx}/merchantReport/merchantReportAdd", 
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

function viewAppealOrder(id){
	$.ajax({
        url: "${ctx}/merchantReport/merchantReportView?id="+id, 
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


function viewUpdatelOrder(id){
	$.ajax({
        url: "${ctx}/merchantReport/merchantReportEdit?id="+id, 
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
</script>
</body>
</html>
