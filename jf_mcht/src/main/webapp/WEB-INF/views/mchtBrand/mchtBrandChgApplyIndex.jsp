<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>品牌管理</title>
<style type="text/css">
.black_box {
    background: #000;
    opacity: 0.6;
    left: 0;
    top: 0;
    z-index: 1111;
    position: fixed;
    height: 100%;
    width: 100%;
}
</style>
</head>

<body>
	<div class="x_panel container-fluid pp-gl">
		<div class="row content-title">
			<div class="t-title">品牌资质更新记录
			<c:if test="${applyCount<=0}">
			<a id="infoChgApply" href='javascript:void(0);' onclick="mchtBrandChgApply('${mchtBrandId}');" >申请更新</a>
			</c:if>
			</div>
		</div>
		<br>
		<br>
		<div class="clearfix"></div>
		<div class="x_content container-fluid">
				<div class="row">
					<div class="col-md-12 datatable-container">
						<table id="datatable"
							class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row">
									<th>品牌logo</th>
									<th>品牌</th>
									<th>佣金比例</th>
									<th>授权期限</th>
									<th>资料审核状态</th>
									<th>资质归档状态</th>
									<!-- <th>通过日期</th> -->
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
	
<!--弹出驳回原因-->
<div class="video_box" style="position:fixed; width:400px; height:265px; top:35%; left:0;right:0;margin:0 auto; display: none;border-radius: 2px;" id="dismissTheReasonModal">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default" style="height: 265px;">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		        	驳回原因
				</h3>
		    </div>
			  <div style="padding-left: 20px;" id="dismissTheReasonText">
			    	
			  </div>
		 </div>
</div>
<!--弹出div End-->
<div class="black_box" style="display: none;" id="deliveryOrderBlackBox"></div>
	
<!-- 	查看信息框 -->

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<!-- Bootstrap -->


	<script>
	
function viewMchtBrand(id){
	
	$.ajax({
        url: "${ctx}/mcht/mchtBrandView?mchtBrandId="+id, 
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
            param.push({"name":"mchtBrandId","value":"${mchtBrandId}"});
            
            $.ajax({
                method: 'POST',
                url: '${ctx}/mcht/getMchtBrandChgList',
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
        "columns":  [
                     {"data": "logo", "width": "178", "class":"pp-img", "render": function (data, type, row, meta) {
                         return "<img style='width:158px;height:68px;' src='${ctx}/file_servelt"+data+"'>";
                     }},
                     {"data": "productBrandName","render": function (data, type, row, meta) {
                    	 var html=[];
                    	 html.push(row.productBrandName);
                    	 if(row.auditStatus!=3 && row.status == 1){
                    		 html.push("<br><span style='color:red;'>资料更新中</span>");
                     	 }
                    	 return html.join("");
                      }},
                     {"data": "popCommissionRateDesc","render": function (data, type, row, meta) {
                    	 return ${mchtBrand.popCommissionRate};
                     }},
                     {"data": "platformAuthExpDate","render": function (data, type, row, meta) {
                    	 if(data){
                   	   		var platformAuthExpDate=new Date(data);
                 	  		return platformAuthExpDate.format("yyyy-MM-dd");
                    	 }else{
                    		return "";
                    	 }
                     }},
                     {"data": "auditStatusDesc","render": function (data, type, row, meta) {
                    	 console.log(row.auditStatus);
                     	   if(!row.auditStatus || row.auditStatus == 0){
                     		   return "未提交审核";
                     	   }else if(row.auditStatus == 1){
                     		  	return "待审核";
                     	   }else if(row.auditStatus == 3){
                     		  	return "审核通过";
                     	   }else if(row.auditStatus == 4){
                     		  	return "<a href='javascript:;' onclick='dismissTheReason("+row.id+",1)'>驳回【查看驳回原因】</a><div id='auditRemarks"+row.id+"' style = 'display:none;'>"+row.auditRemarks+"</div>";
                     	   }else if(row.auditStatus == 5 || row.auditStatus == 6){
                     		  	return "异常";
                     	   }else if(row.auditStatus == 2){
                     		  	return "待定";
                     	   }
                     }},
                     {"data": "archiveDealStatusDesc","render": function (data, type, row, meta) {
                    	   var html="";
                    	   var archiveDealStatus=row.archiveDealStatus;
	                   	   if(!row.expressId || !row.expressNo){
	                   		   html="未寄出";
	                 	   }else{
	                 		   html="已寄出";
	                 	   }
	                   	   if(archiveDealStatus == "1"){
	                 		  html="通过";
	                 	   }else if(archiveDealStatus == "2"){
	                 		  html="<a href='javascript:;' onclick='dismissTheReason("+row.id+",2)'>驳回【查看驳回原因】</a><div id='archiveDealRemarks"+row.id+"' style = 'display:none;'>"+row.archiveDealRemarks+"</div>";;
	                 	   }
	                   		return html;
                 	 }},
                     {"data": "operation","width": "120","render": function (data, type, row, meta) {
                     	var returnHtml = [];
                     	if(row.auditStatus!=4){
	                     	returnHtml.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='brandChgApplyEdit("+row.id+")'>【查看】</a><br>");
                     	}
                     	if(row.auditStatus==4){
	                     	returnHtml.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='brandChgApplyEdit("+row.id+")'>【修改】</a><br>");
                     	}
                     	if(row.auditStatus==3 && row.archiveDealStatus!=1){//品牌法务审核通过 且 品牌审核状态不为通过
                     		if(!row.expressId || !row.expressNo || row.archiveDealStatus == 2){
		                     	returnHtml.push("<a href='javascript:;' onclick='toSend("+row.id+")'>立即寄件</a><br>"); 
		                     	returnHtml.push("<a href='javascript:;' onclick='toViewContent("+row.id+")'>查看寄件内容</a><br>");
                     		}
                     	}
                         return returnHtml.join("");
                     }}
                 ]
    }).api();

    $('#btn-query').on('click', function (event) {
        table.ajax.reload();
    });
    
});

function toViewContent(id){
	$.ajax({
	       url: "${ctx}/mcht/mchtBrandChg/viewBrandChgPics.shtml?id="+id, 
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

function dismissTheReason(id,type){
	if(type == 1){
		$("#dismissTheReasonText").text($("#auditRemarks"+id).text());
	}else if(type == 2){
		$("#dismissTheReasonText").text($("#archiveDealRemarks"+id).text());
	}
    $("#dismissTheReasonModal").show();
    $("#deliveryOrderBlackBox").show();
}

$(".video_close").on('click',function(){
	$("#dismissTheReasonModal").hide();
	$("#deliveryOrderBlackBox").hide();
});

function toSend(id){
	$.ajax({
	       url: "${ctx}/mcht/mchtBrandChg/toSend?id="+id, 
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

function mchtBrandChgApply(mchtBrandId){
	$.ajax({
	    url: "${ctx}/mcht/addMchtBrandChgApply?mchtBrandId="+mchtBrandId, 
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
function brandChgApplyEdit(id){
	$.ajax({
	    url: "${ctx}/mcht/mchtBrandChgApplyEdit?id="+id, 
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


function mchtChgApplyDel(id){
	swal({
		  title: "确定要删除此信息?",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonText: "确定",
		  cancelButtonText: "取消",
		  closeOnConfirm: true
		},
		function(){
			delConfirm(id);
		});
}

function delConfirm(id){
	$.ajax({
		url : "${ctx}/mcht/delMchtBrandChg?id="+id,
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {id:id},
		timeout : 30000,
		success : function(data) {
			if (data.returnCode=="0000") {
				getContent("${ctx}/mcht/mchtBrandChgApplyIndex?mchtBrandId=${mchtBrandId}");
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
