<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>商品分类</title>
</head>

<body>
	<div class="x_panel container-fluid pp-gl">
		<div class="row content-title">
			<div class="t-title">自定义分类管理
			<a href='javascript:void(0);' onclick="addShopProductCustomType();">添加</a>
			</div>
		</div>
		<div class="x_content container-fluid">
				<div class="row">
					<div class="col-md-12 datatable-container">
						<table id="datatable"
							class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row">
									<th>排序</th>
									<th>分类名称</th>
									<th>商品数量</th>
									<th>状态</th>
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

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<script type="text/javascript">
function addShopProductCustomType(){
	$.ajax({
        url: "${ctx}/shopProductCustomType/addShopProductCustomType", 
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
                url: '${ctx}/shopProductCustomType/getShopProductCustomTypeList',
                data: param,
                dataType: 'json'
            }).done(function (result) {
                if (result.returnCode =='0000') {
                    var returnData = {};
                    returnData.recordsTotal = result.returnData.Total;
                    returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                    returnData.data = result.returnData.Rows;
                    callback(returnData);
                    
                    var seqNoBlur=false;
                    var editNameBlur=false;
                    $("input[name='saveSeqNo']").on('blur',function(){
                    	if(seqNoBlur){
                    		return false;
                    	}
                    	var id = $(this).data("id");
                    	var seqNo = $(this).val();
                    	var reg = /^[1-9]\d*$/;
	               		if(reg.test(seqNo)){
	               			seqNoBlur = true;
	                    	$.ajax({
	                    		url : "${ctx}/shopProductCustomType/saveSeqNo",
	                    		type : 'POST',
	                    		dataType : 'json',
	                    		cache : false,
	                    		async : false,
	                    		data : {id:id,seqNo:seqNo},
	                    		timeout : 30000,
	                    		success : function(data) {
	                    			if (data.returnCode=="0000") {
	                    				seqNoBlur = false;
	                    			} else {
	                    				swal({
	                    					  title: data.returnMsg,
	                    					  type: "error",
	                    					  confirmButtonText: "确定"
	                    					});
	                    				seqNoBlur = false;
	                    			}
	                    			
	                    		},
	                    		error : function() {
	                    			swal({
	                    				  title: "处理失败！",
	                    				  type: "error",
	                    				  timer: 1500,
	                    				  confirmButtonText: "确定"
	                    				});
	                    			seqNoBlur = false;
	                    		}
	                    	});
	               		}else{
	               			swal("请输入正整数");
	               			seqNoBlur = false;
	               		}
                    });
                    $("input[name='editName']").on('blur',function(){
                    	if(editNameBlur){
                    		return false;
                    	}
                    	var id = $(this).data("id");
                    	var name = $(this).val();
                    	if(name){
                    		
                    	    var hasViolateWord=false;
                    	    //校验违禁词
                    		$.ajax({
                    		url : "${ctx}/common/checkViolateWord",
                    		type : 'POST',
                    		dataType : 'json',
                    		cache : false,
                    		async : false,
                    		data : {word:name},
                    		timeout : 30000,
                    		success : function(data) {
                    			if (data.returnCode=="4004") {
                    				hasViolateWord=true;
                    				swal(data.returnMsg);
                    			}
                    		},
                    		error : function() {
                    			swal({
                    				  title: "提交失败！",
                    				  type: "error",
                    				  confirmButtonText: "确定"
                    				});
                    		}
                    	    });
                    	    
                    	    if(hasViolateWord){
                    	    	return;
                    	    }
                    		
                    		editNameBlur = true;
	                    	$.ajax({
	                    		url : "${ctx}/shopProductCustomType/editName",
	                    		type : 'POST',
	                    		dataType : 'json',
	                    		cache : false,
	                    		async : false,
	                    		data : {id:id,name:name},
	                    		timeout : 30000,
	                    		success : function(data) {
	                    			if (data.returnCode=="0000") {
	                    				editNameBlur = false;
	                    			} else {
	                    				swal({
	                    					  title: data.returnMsg,
	                    					  type: "error",
	                    					  confirmButtonText: "确定"
	                    					});
	                    				editNameBlur = false;
	                    			}
	                    			
	                    		},
	                    		error : function() {
	                    			swal({
	                    				  title: "处理失败！",
	                    				  type: "error",
	                    				  timer: 1500,
	                    				  confirmButtonText: "确定"
	                    				});
	                    			editNameBlur = false;
	                    		}
	                    	});
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
            
            {"data": "seqNo","render": function (data, type, row, meta) {
               return '<input value="'+row.seqNo+'" data-id="'+row.id+'" name="saveSeqNo" >';	
            }},
            {"data": "name","render": function (data, type, row, meta) {
               return '<input value="'+row.name+'" data-id="'+row.id+'" name="editName" >';	
            }},
            {"data": "productCount","render": function (data, type, row, meta) {
          	   return row.productCount;
            }},
            {"data": "statusDesc"},
            {"data": "op","render": function (data, type, row, meta) {
            	var html = [];
            	if(row.status == "0"){
            		html.push('<a href="javascript:;" onclick="changeStatus('+row.id+','+row.status+');">显示</a>&nbsp;&nbsp;');
            	}else{
            		html.push('<a href="javascript:;" onclick="changeStatus('+row.id+','+row.status+');">隐藏</a>&nbsp;&nbsp;');
            	}
            	html.push('<a href="javascript:;" onclick="del('+row.id+');">删除</a>');
                return html.join("");
            }}
        ]
    }).api();

    $('#btn-query').on('click', function (event) {
        table.ajax.reload();
    });
    
    $("#searchKeyWord").keydown(function(e){
    	if(e.keyCode==13){
    		table.ajax.reload();
            return false;
    	}
    });
});
var submited;
function changeStatus(id,status){
	var title;
	if(status == "0"){
		title = "确定显示该分类吗？";
	}else{
		title = "确定隐藏该分类吗？";
	}
	swal({ 
		  title: title,
		  showCancelButton: true, 
		  confirmButtonColor: "#DD6B55",
		  confirmButtonText: "确定", 
		  cancelButtonText: "取消",
		  closeOnConfirm: false
		},
		function(isConfirm){ 
		  if(isConfirm) {
			  	if(submited){
			  		return false;
			  	}
			  	submited = true;
				$.ajax({
			        method: 'POST',
			        url: '${ctx}/shopProductCustomType/changeStatus',
			        data: {"id":id},
			        dataType: 'json'
			    }).done(function (result) {
			    	submited = false;
			        if (result.returnCode =='0000') {
			           	swal("操作成功");
			           	table.ajax.reload();
			        }else{
			        	swal("操作失败，请稍后重试");
			        }
			    });
		  }
		});
}

function del(id){
	swal({
		  title: "确定要删除此分类?",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonText: "确定",
		  cancelButtonText: "取消",
		  closeOnConfirm: false
		},
		function(){
			delConfirm(id);
		});
}

function delConfirm(id){
	$.ajax({
		url : "${ctx}/shopProductCustomType/delete",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {id:id},
		timeout : 30000,
		success : function(data) {
			if (data.returnCode=="0000") {
				swal.close();
				table.ajax.reload();
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
				  title: "处理失败！",
				  type: "error",
				  timer: 1500,
				  confirmButtonText: "确定"
				});
		}
	});

}
</script>
</body>
</html>
