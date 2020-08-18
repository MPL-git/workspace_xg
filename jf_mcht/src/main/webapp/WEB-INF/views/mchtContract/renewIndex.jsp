<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>合同续签</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/viewer.min.css" />
</head>

<body>
<div>
	<div class="modal-content">
		<div class="modal-header">
		<c:if test="${(contract.renewProStatus eq 0 || contract.renewProStatus eq 6 || (empty contract.renewProStatus && contract.renewStatus eq '0')) && timeTag}">
	        <span style="float: right;margin-right:15px;"><a id="infoChgApply" href='javascript:void(0);' onclick="renewView();" >续签确认</a></span>
		</c:if>
	        <span class="modal-title" id="exampleModalLabel">合同续签</span>
      	</div>
      	<div class="modal-body">
      	<c:if test="${contract.renewProStatus eq 4}">
      	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">续签申请驳回：${contract.zsNotRenewRemarks}</font>
      	<br>
      	<br>
      	</c:if>   	
      		<div class="container-fluid">
				<div class="search-container container-fluid">
		
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
<!-- 	订单详情 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
     data-backdrop="static">
</div>	
<!-- 	查看信息框 -->
	<div class="modal fade" id="myViewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
	 data-backdrop="static">
	</div>
	
	<!-- 	合同续签框 -->
	<div class="modal fade" id="unexpiredModal" tabindex="-1" role="dialog" aria-labelledby="unexpiredModalLabel" aria-hidden="true"  data-backdrop="static"> 
	<div class="modal-dialog">
		<div class="modal-content"  style="text-align:center;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<font size="3px;">合同续签提醒</font>
				</h4>
			</div>
			<div class="modal-body">
				您的合同将于${endDate}到期，到期后将会影响店铺的运营，请您尽快的完成续签。
				<br>
				<br>
				<button type="button" class="btn btn-info" id="renew" onclick="renew(${contract.id},2);">续签申请</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-info" id="notRenew" onclick="notRenew();">不再续签</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
		</div>
	</div>
	</div>
	
	<!--合同续签提交提醒框 -->
	<div class="modal fade" id="remind" tabindex="-1" role="dialog" aria-labelledby="remindLabel" aria-hidden="true"  data-backdrop="static"> 
	<div class="modal-dialog">
		<div class="modal-content"  style="text-align:center;">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<font size="3px;">提醒</font>
				</h4>
			</div>
			<div class="modal-body">
				您已提交申请，后续可在【商家管理-合同续签】栏目查看申请进度
				<br>
				<br>
				<button type="button" class="btn btn-info" onclick="remindClose();">关闭</button>
			</div>
		</div>
	</div>
	</div>
	
	<!--不续签弹框 -->
	<div class="modal fade" id="notRenewModal" tabindex="-1" role="dialog" aria-labelledby="notRenewModalLabel" aria-hidden="true"  data-backdrop="static"> 
	<div class="modal-dialog">
		<div class="modal-content"  style="text-align:center;">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<font size="3px;">提交原因</font>
				</h4>
			</div>
			<div class="modal-body">
				非常遗憾！不能再与贵司进行合作，请您能提交不续签原因，才能确认不续签。
				<br>
				<br>
				<div><textarea rows="5" id="mchtNotRenewRemarks" style="resize:none;outline:none;width:80%" placeholder="填写不续签原因" maxlength="64"></textarea></div>
				<br>
				<button type="button" class="btn btn-info" onclick="renew(${contract.id},1);">确认不续签</button>
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
	<script type="text/javascript" src="${ctx}/static/js/viewer.min.js"></script>
	<script type="text/javascript">
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
            var param = [];
            param.push({"name":"pagesize","value":data.length});
            if (data.start > 0) {
                param.push({"name":"page","value":data.start/data.length+1});
            } else {
                param.push({"name":"page","value":1});
            }
            
            
            $.ajax({
                method: 'POST',
                url: '${ctx}/mcht/contract/getRenewList',
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
            {"data": "id","title":"合同编号","render": function (data, type, row, meta) {
            	return row.contractCode;
            }},
            {"data": "id","title":"合同开始日期","render": function (data, type, row, meta) {
			    return new Date(row.beginDate).format("yyyy-MM-dd");
            }},
            {"data": "id","title":"合同结束日期","render": function (data, type, row, meta) {
			    return new Date(row.endDate).format("yyyy-MM-dd");
            }},
            {"data": "id","title":"合同详情","render": function (data, type, row, meta) {
			    return '<a href="javascript:;" onclick="showInfo('+row.id+');">预览打印</a>';
            }},
            {"data": "id","title":"线上审核状态","render": function (data, type, row, meta) {
			    if(row.auditStatus == "1"){
			    	return '<a href="javascript:renewContractPic('+row.id+')">未上传【上传合同】</a>';
			    }else if(row.auditStatus == "2"){
			    	return "已上传待审";
			    }else if(row.auditStatus == "3"){
			    	return "通过";
			    }else if(row.auditStatus == "4"){
			    	return '<a href="javascript:;" onclick="renewContractPic('+row.id+')">驳回【查看原因并上传】</a>';
			    }
            }},
            {"data": "id","title":"商家寄件状态","render": function (data, type, row, meta) {
            	if(row.isMchtSend == "1"){
            		return "已寄出";
            	}else if(row.auditStatus=="3"){
            		return '<a href="javascript:;" onclick="toSend('+row.id+')">立即寄件</a><br><a href="javascript:;" onclick="sendContent('+row.id+')">寄件内容</a>';
            	}
            	return "";
            }},
            {"data": "id","title":"合同归档状态","render": function (data, type, row, meta) {
				if(row.archiveStatus == "1"){//通过已归档 
			    	return "已归档";
			    }else if(row.archiveStatus == "2"){//不通过驳回
			    	return '驳回<a href="javascript:;" onclick="remarks('+row.id+')">【驳回原因】</a>';
			    }else if(row.archiveStatus == "4"){//不签约
			    	return "异常，请与招商联系";
			    }
				return "";
            }}
        ]
    }).api();

});
function showInfo(id){
	$.ajax({
		url: "${ctx}/mcht/contract/contractPreview?id="+id, 
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

function renewContractPic(id){
	$.ajax({
		url: "${ctx}/mcht/contract/renewContractPic?id="+id,
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

function remarks(mchtContractId){
	$.ajax({
		url: "${ctx}/mcht/contract/getRemarks?id="+mchtContractId, 
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

function toSend(mchtContractId){
	$.ajax({
		url: "${ctx}/mcht/contract/toSend?id="+mchtContractId, 
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

function sendContent(id){
	$.ajax({
		url: "${ctx}/mcht/contract/sendContent?id="+id, 
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

//确认续签页面
function renewView(){
	$('#unexpiredModal').modal();
}

//确认续签操作
function renew(mchtContractId,status){
	var url = "${ctx}/mcht/contract/renew?mchtContractId="+mchtContractId+"&status="+status;
	var mchtNotRenewRemarks = $('#mchtNotRenewRemarks').val();
	//如果是不再续签，把不续签原因带上
	if(status == '1' && mchtNotRenewRemarks == ""){
		swal('不续签原因不可为空');
		return;
	}else if(status == '1' && mchtNotRenewRemarks != ""){
		url = "${ctx}/mcht/contract/renew?mchtContractId="+mchtContractId+"&status="+status+"&mchtNotRenewRemarks="+mchtNotRenewRemarks;	
	}	
	$.ajax({
        url: url,
        type: "GET", 
        async: false, 
        success: function(data){
        	if(data.returnCode == '0000' && status == '2'){
        		$("#unexpiredModal").hide();
        		$('.modal-backdrop').remove();//去掉遮罩层
        		getContent('${ctx}/mcht/contract/renewIndex');
        	}else if(data.returnCode == '0000' && status == '1'){  
        		$("#notRenewModal").hide();
        		$('.modal-backdrop').remove();//去掉遮罩层
        		getContent('${ctx}/mcht/contract/renewIndex');
        	}
        },
	    error:function(){
	    	swal('页面不存在');
	    }
	});
};

//关闭
function remindClose(){
	$('#remind').hide();
	$('.modal-backdrop').remove();//去掉遮罩层
}

//不续签操作
function notRenew(){
	$("#unexpiredModal").hide();
	$('.modal-backdrop').remove();//去掉遮罩层
	$("#notRenewModal").modal();
}
</script>
</body>
</html>
