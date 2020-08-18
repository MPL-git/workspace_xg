<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>联系人管理</title>
<style type="text/css">


</style>
</head>

<body>
	<div class="x_panel container-fluid">
		<div class="row content-title">
			<div class="t-title">
				联系人管理
				<a href='javascript:void(0);' onclick="addMchtContact(1);">添加对接人</a>
			</div>
		</div>
		<div class="pt">

  <!-- Nav tabs -->
  <ul class="nav nav-tabs q" role="tablist">
    <li role="presentation" class="active"><a href="#contact_1" aria-controls="contact_1" role="tab" data-toggle="tab">店铺总负责人</a></li>
    <li role="presentation"><a href="#contact_2" aria-controls="contact_2" role="tab" data-toggle="tab">商家运营</a></li>
    <li role="presentation"><a href="#contact_3" aria-controls="contact_3" role="tab" data-toggle="tab">订单对接人</a></li>
    <li role="presentation"><a href="#contact_4" aria-controls="contact_4" role="tab" data-toggle="tab">客服对接人</a></li>
    <li role="presentation"><a href="#contact_5" aria-controls="contact_5" role="tab" data-toggle="tab">售后对接人</a></li>
    <li role="presentation"><a href="#contact_7" aria-controls="contact_7" role="tab" data-toggle="tab">平台联系人</a></li>
  </ul>

<!--   Tab panes -->
  <div class="tab-content at-table">
    <div role="tabpanel" class="tab-pane active" id="contact_1">
    				<div class="datatable-container">
						<table id="contact_table_1" class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row">
									<th>姓名</th>
									<th>手机号</th>
									<th>QQ号</th>
									<th>微信号</th>
									<th>邮箱</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
	</div>
    <div role="tabpanel" class="tab-pane" id="contact_2">
    				<div class="datatable-container">
						<table id="contact_table_2" class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row">
									<th>姓名</th>
									<th>手机号</th>
									<th>QQ号</th>
									<th>微信号</th>
									<th>邮箱</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
	</div>
    <div role="tabpanel" class="tab-pane" id="contact_3">
           <div class="datatable-container">
						<table id="contact_table_3" class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row">
									<th>姓名</th>
									<th>手机号</th>
									<th>QQ号</th>
									<th>微信号</th>
									<th>邮箱</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
    </div>
    <div role="tabpanel" class="tab-pane" id="contact_4">
         <div class="datatable-container">
						<table id="contact_table_4" class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row">
									<th>姓名</th>
									<th>手机号</th>
									<th>QQ号</th>
									<th>微信号</th>
									<th>邮箱</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
			</div>
    </div>
    <div role="tabpanel" class="tab-pane" id="contact_5">
        				<div class="datatable-container">
						<table id="contact_table_5" class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row">
									<th>姓名</th>
									<th>手机号</th>
									<th>QQ号</th>
									<th>微信号</th>
									<th>邮箱</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
    </div>
    <div role="tabpanel" class="tab-pane" id="contact_7" >
        				<div class="datatable-container">
						<table id="contact_table_7" class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row">
									<th>类型</th>
									<th>姓名</th>
									<th>手机号</th>
									<th>QQ号</th>
									<th>微信号</th>
									<th>邮箱</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
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


	<script>
	
var contact_table_1;
var contact_table_2;
var contact_table_3;
var contact_table_4;
var contact_table_5;
var contact_table_7;

$(document).ready(function () {
	
	contact_table_1 = initMchtContacTable("contact_table_1",1);
	contact_table_2 = initMchtContacTable("contact_table_2",2);
	contact_table_3 = initMchtContacTable("contact_table_3",3);
	contact_table_4 = initMchtContacTable("contact_table_4",6);
	contact_table_5 = initMchtContacTable("contact_table_5",4);
	contact_table_7 = initPlatformContacTable("contact_table_7");
    
});


function initMchtContacTable(dataTableId,contactType){
	
	
    return	$('#'+dataTableId).dataTable({
        "ajax": function (data, callback, settings) {
        	var param = [];
            
            param.push({"name":"contactType","value":contactType});
            param.push({"name":"pagesize","value":data.length});
            if (data.start > 0) {
                param.push({"name":"page","value":data.start/data.length+1});
            } else {
                param.push({"name":"page","value":1});
            }
            
            
            $.ajax({
                method: 'POST',
                url: '${ctx}/mchtContact/getMchtContactList',
                data: param,
                dataType: 'json'
            }).done(function (result) {
                if (result.returnCode =='0000') {
                    var returnData = {};
                    returnData.recordsTotal = result.returnData.Total;
                    returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                    returnData.data = result.returnData.Rows;
                    callback(returnData);
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
            {"data": "contactName"},
            {"data": "mobile"},
            {"data": "qq"},
            {"data": "wechat"},
            {"data": "email"},
            {"data": "auditStatus","render": function (data, type, row, meta) {
            	if(row.auditStatus == ""){
            		return "未提交";
            	}
               	if(row.auditStatus == "0"){
            		return "待审";
            	}
               	if(row.auditStatus == "1"){
            		return "通过";
            	}
               	if(row.auditStatus == "2"){
            		return "驳回";
            	}
               	return "";
            }},
            {"data": "id","width":200,"render": function (data, type, row, meta) {

                if(!(row.auditStatus=="0" || row.auditStatus=="1")){
                	return "<a class='table-opr-btn' href='javascript:void(0);' onclick='editMchtContact("+row.id+")' >【修改】</a><a class='table-opr-btn' href='javascript:void(0);' onclick='delMchtContact("+row.id+")' >【删除】</a>"
                }
                return "";
            }}
        ]
    }).api();
}
function initPlatformContacTable(dataTableId){
	
	
    return	$('#'+dataTableId).dataTable({
        "ajax": function (data, callback, settings) {
        	var param = [];
            
            param.push({"name":"pagesize","value":data.length});
            if (data.start > 0) {
                param.push({"name":"page","value":data.start/data.length+1});
            } else {
                param.push({"name":"page","value":1});
            }
            
            
            $.ajax({
                method: 'POST',
                url: '${ctx}/mchtContact/getMchtPlatfromContactList',
                data: param,
                dataType: 'json'
            }).done(function (result) {
                if (result.returnCode =='0000') {
                    var returnData = {};
                    returnData.recordsTotal = result.returnData.Total;
                    returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                    returnData.data = result.returnData.Rows;
                    callback(returnData);
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
            {"data": "contactTypeDesc"},
            {"data": "contactName","render": function (data, type, row, meta) {
                if(row.isPrimary=="1"){
                	return data+"<span style='color:red;'>[默认]</span>";
                }else{
                	return data;
                }
            }},
            {"data": "mobile"},
            {"data": "tel"},
            {"data": "qq"},
            {"data": "email"}
        ]
    }).api();
}


function delMchtContact(id){
	swal({
		  title: "确定要删除此联系人?",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonText: "确定",
		  cancelButtonText: "取消",
		  closeOnConfirm: false
		},
		function(){
				swal({
					  title: "该对接人不处于驳回或未提交状态,不可删除",
					  type: "error",
					  confirmButtonText: "确定"
					  
					});
			delMchtContactConfirm(id);
		});
}

function delMchtContactConfirm(id){
	$.ajax({
		url : "${ctx}/mchtContact/delMchtContact?id="+id,
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {id:id},
		timeout : 30000,
		success : function(data) {
			if (data.returnCode=="0000") {
				contact_table_1.ajax.reload();
				contact_table_2.ajax.reload();
				contact_table_3.ajax.reload();
				contact_table_4.ajax.reload();
				contact_table_5.ajax.reload();
				contact_table_7.ajax.reload();
				swal({
					  title: "删除成功!",
					  type: "success",
					  confirmButtonText: "确定"
					  
					});
			} else {
				swal({
					  title: data.returnMsg,
					  type: "error",
					  confirmButtonText: "确定"
					});
			}
			
		},
		error : function() {
			swal({
				  title: "删除失败！",
				  type: "error",
				  timer: 1500,
				  confirmButtonText: "确定"
				});
		}
	});

}

function viewMchtContact(id){
		$.ajax({
	        url: "${ctx}/mchtContact/mchtContactView?id="+id, 
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
function editMchtContact(id){
		$.ajax({
	        url: "${ctx}/mchtContact/mchtContactEdit?id="+id, 
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
function setDefaultMchtContact(id){
	$.ajax({
		url : "${ctx}/mchtContact/setDefaultMchtContact",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {id:id},
		timeout : 30000,
		success : function(data) {
			if (data.returnCode=="0000") {
				contact_table_1.ajax.reload();
				contact_table_2.ajax.reload();
				contact_table_3.ajax.reload();
				contact_table_4.ajax.reload();
				contact_table_5.ajax.reload();
				contact_table_7.ajax.reload();
				swal({
					  title: "设置成功!",
					  type: "success",
					  confirmButtonText: "确定"
					  
					});
			} else {
				swal({
					  title: data.returnMsg,
					  type: "error",
					  confirmButtonText: "确定"
					});
			}
			
		},
		error : function() {
			swal({
				  title: "提交失败！",
				  type: "error",
				  timer: 1500,
				  confirmButtonText: "确定"
				});
		}
	});

}
function addMchtContact(addType){
		$.ajax({
	        url: "${ctx}/mchtContact/mchtContactAdd?addType=1", 
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
