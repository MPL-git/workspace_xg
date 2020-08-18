<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>经营许可证更新记录</title>
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
			<div class="t-title">经营许可证更新记录
			<c:if test="${applyCount<=0}">
			<a id="infoChgApply" href='javascript:void(0);' onclick="editMchtLicenseChg(${mchtId});" >申请更新</a>
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
									<th>创建日期</th>
									<th>名称</th>
									<th>资料审核状态</th>
									<th>资质归档状态</th>
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
<!--弹出行业经营许可管理-->
<div class="video_box" style="position:fixed; width:400px; height:265px; top:35%; left:0;right:0;margin:0 auto; display: none;border-radius: 2px;" id="businessLicensePicModal">
    <a href="javascript:;" class="video_close" style="right: -215px;"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default" style="height: 265px;width:600px">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		        	行业经营许可管理
				</h3>
		    </div>
		    <table class="panel-table">
		    	<tr>
		    		<td>经营许可证</td>
		    		<td><img src="${ctx}/file_servelt" id="businessLicensePicShow" style="height: 200px;width:400px"></td>
		    	</tr>
		    </table>
			  <!-- <div style="padding-left: 20px;" id="businessLicensePicText">
			    	
			  </div> -->
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
	
function editMchtLicenseChg(id){
	$.ajax({
        url: "${ctx}/mchtUser/toMchtLicenseChg?id="+id, 
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

function toSend(id){
	$.ajax({
	       url: "${ctx}/mchtUser/mchtLicenseChg/toSend?id="+id, 
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

function dismissTheReason(id){
	$("#dismissTheReasonText").text($("#archiveDealRemarks"+id).text());
    $("#dismissTheReasonModal").show();
    $("#deliveryOrderBlackBox").show();
}

function toMchtLicenseChg(id){
	path=$("#businessLicensePicText"+id).text();
	$("#businessLicensePicShow").attr("src","${ctx}/file_servelt"+path);
    $("#businessLicensePicModal").show();
    $("#deliveryOrderBlackBox").show();
}

$(".video_close").on('click',function(){
	$("#dismissTheReasonModal").hide();
	$("#businessLicensePicModal").hide();
	$("#deliveryOrderBlackBox").hide();
});

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
            param.push({"name":"mchtId","value":"${mchtId}"});
            
            $.ajax({
                method: 'POST',
                url: '${ctx}/mchtUser/getMchtLicenseChgList',
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
                     {"data": "createDate","render": function (data, type, row, meta) {
                    	 var date = new Date(row.createDate);
                    	 return date.format("yyyy-MM-dd");
                     }},
                     {"data": "nameDesc","render": function (data, type, row, meta) {
                    	 return "经营许可证";
                      }},
                     {"data": "auditStatus","render": function (data, type, row, meta) {
                     	   if(row.auditStatus == 0){
                     		   return "未审核";
                     	   }else if(row.auditStatus == 1){
                     		  	return "通过";
                     	   }else if(row.auditStatus == 2){
                     		  	return "驳回";
                     	   }else{
                     		  return "";
                     	   }
                     }},
                     {"data": "archiveDealStatus","render": function (data, type, row, meta) {
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
		                 		  html="<a href='javascript:;' onclick='dismissTheReason("+row.id+")'>驳回【查看驳回原因】</a><div id='archiveDealRemarks"+row.id+"' style = 'display:none;'>"+row.archiveDealRemarks+"</div>";;
		                 	   }
		               		return html;
                 	 }},
                     {"data": "operation","width": "120","render": function (data, type, row, meta) {
                    	 var returnHtml = [];
                      	if(row.auditStatus!=2){
 	                     	returnHtml.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='toMchtLicenseChg("+row.id+")'>【查看】</a><br><div id='businessLicensePicText"+row.id+"' style = 'display:none;'>"+row.businessLicensePic+"</div>");
 	                     	if(!row.businessLicensePic){
 	                     		
 	                     	}
                      	}
                      	if(row.auditStatus==2){
 	                     	returnHtml.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='editMchtLicenseChg("+row.id+")'>【修改】</a><br>");
                      	}
                      	if(row.auditStatus == 1 && row.archiveDealStatus != 1){//行业许可证法务审核通过 且 归档状态不为通过
                      		if(!row.expressId || !row.expressNo || row.archiveDealStatus == 2){
 		                     	returnHtml.push("<a href='javascript:;' onclick='toSend("+row.id+")'>立即寄件</a><br>"); 
 		                     	returnHtml.push("<a href='javascript:;' onclick='toMchtLicenseChg("+row.id+")'>查看寄件内容</a>");
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


</script>
</body>
</html>
