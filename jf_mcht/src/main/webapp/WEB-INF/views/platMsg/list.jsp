<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>站内信列表</title>
	<style type="text/css">
		.hidden{
			display:none;
		}
		.noread{
			font-weight:bold;
		}
		.xxx{
			color:#f00;
		}
		.readCol{
			color:#8D8E90;
		}
	</style>
</head>

<body>
<div class="x_panel container-fluid">
	<div class="row content-title">
		<div class="col-md-12">站内信</div>
	</div>
	<div class="search-container container-fluid">
		<form class="form-horizontal" id="searchForm">
			<input type="hidden" name="status" value="${status}"/>
			<div class="form-group">
				<label for="title" class="col-md-1 control-label search-lable">标题：</label>
				<div class="col-md-2 search-condition">
					<input class="form-control" type="text"  id="title" name="title" >
				</div>
				<label for="" class="col-md-1 control-label search-lable">站内信类型&nbsp;&nbsp; </label>
				<div class="col-md-2 search-condition">
					<select class="form-control" name="msgType" id="msgType">
						<option value="">--请选择--</option>
						<c:forEach var="msgType" items="${msgTypes}">
							<option value="${msgType.msg_type}">${msgType.msg_type_desc}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-3 text-center search-btn">
					<button type="button"  class="btn btn-info" id="btn-query">查询</button>
				</div>
				<div class="col-md-6"></div>
			</div>
		</form>

	</div>
	<div class="clearfix"></div>
	<ul class="nav nav-tabs q" role="tablist">
		<li role="presentation" <c:if test="${empty status}">class="active"</c:if>> <a href="#" role="tab" data-toggle="tab" onclick="list();">全部</a></li>
		<li role="presentation" <c:if test="${status==0}">class="active"</c:if>> <a href="#" role="tab" data-toggle="tab" onclick="list(0);">未读</a></li>
		<li role="presentation" <c:if test="${status==1}">class="active"</c:if>> <a href="#" role="tab" data-toggle="tab" onclick="list(1);">已读</a></li>
	</ul>
	<div class="x_content container-fluid">
		<div class="row">
			<div class="col-md-12 datatable-container">
				<div class="datatable-caption">
					<span class="mr">
						<input type="checkbox" class="selectAll">全选
					</span>

					<c:if test="${status!=1}">
						<button type="button" class="btn btn-all" id="btn-batch-read" >标记已读</button>
					</c:if>
				</div>
				
				<table id="datatable"
					   class="table table-striped table-bordered dataTable no-footer"
					   role="grid" aria-describedby="datatable_info">
					<thead>
					<tr role="row">
						<th width="28"></th>
						<th width="99">站内信类型</th>
						<th>标题</th>
						<th width="178">时间</th>
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

		$(".selectAll").change(function(){
			if($(this).is(':checked')) {
				$("input[name='id']").attr("checked", "true");
			}else{
				$("input[name='id']").removeAttr("checked");
			}
		});

		$("#btn-batch-read").click(function(){
			var ids=[];
			$("input[name='id']:checked").each(function(){
				ids.push($(this).val());
			});
			if(ids.length == 0){
				swal("请选择");
				return false;
			}
			readMsgs(ids, false);
		});


		table = $('#datatable').dataTable({
			"ajax": function (data, callback, settings) {
				var param = $("#searchForm").serializeArray();
				param.push({"name": "pageSize", "value": data.length});
				if (data.start > 0) {
					param.push({"name": "pageNumber", "value": data.start / data.length + 1});
				} else {
					param.push({"name": "pageNumber", "value": 1});
				}

				$.ajax({
					method: 'POST',
					url: '${ctx}/platformMsg/list',
					data: param,
					dataType: 'json'
				}).done(function (result) {
					if (result.success) {
						var returnData = {};
						returnData.recordsTotal = result.data.page.totalRow;
						returnData.recordsFiltered = result.data.page.totalRow; // 后台不实现过滤功能，每次查询均视作全部结果
						returnData.data = result.data.page.list;
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
				{
					"data": "id", "render": function (data, type, row, meta) {
					var html = [];
					html.push('<input type="checkbox" name="id" value="', data ,'">');
					return html.join("");
				}},
				{"data": "msgType", render:function(data, type, row, meta){
					if(data == "A1"){
						return "退款"
					}else if(data == "A2"){
						return "退货";
					}else if(data == "A3"){
						return "换货";
					}else if(data == "A4"){
						return "投诉";
					}else if(data == "A5"){
						return "违规";
					}else if(data == "A6"){
						return "活动";
					}else if(data == "TZ"){
						return "通知";
					}else if(data == "CQ"){
                        return "产权投诉";
                    }else {
						return "通知";
					}
				}},
				{ "data": "title", className: "text-left", "render": function (data, type, row, meta) {
					var html = [];
					if(row.status == "0"){
						html.push('<a class="table-opr-btn noread" href="javascript:void(0);" onclick="readMsg(', "'" + row.id + "'", ',this);" >' + row.title + '</a>');
					}else{
						html.push('<a class="table-opr-btn readCol" href="javascript:void(0);" onclick="viewContent(', "'" + row.id + "'", ');" >' + row.title + '</a>');
					}
					html.push('<div class="hidden" id="' + row.id + '_content" style="margin-top: 5px;">' + row.content );
					if(row.attachmentPath){
						html.push('<br><a href="javascript:;" onclick="downloadAttachment('+"'"+row.attachmentName+"'"+','+"'"+row.attachmentPath+"'"+');">【附件下载】</a></div>');
					}else{
						html.push('</div>');
					}
					return html.join("");
				}},
				{"data": "createDate", "render": function (data, type, row, meta) {
					return data;
				}}
			]
		}).api();

		$('#btn-query').on('click', function (event) {
			table.ajax.reload();
		});

	});


	function list(status){
		if(typeof(status) == "undefined"){
			$("input[name='status']").attr("value", "");
		}else{
			$("input[name='status']").attr("value", status);
		}
		table.ajax.reload();
	}

	function readMsg(id, obj) {
		var ids=[];
		ids.push(id);
		readMsgs(ids, true);
		$(obj).removeClass("noread");
		$(obj).addClass("readCol");
		viewContent(id);
	}
	
	function showDetail(url){
		$.ajax({
			url: url, 
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
	
	function readMsgs(ids, single) {
		var postData= {};
		postData.ids = JSON.stringify(ids);
		var url = "${ctx}/platformMsg/read";
		$.ajax({
			url: url,
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : postData,
			timeout : 30000,
			success: function (data) {
				if (data.success) {
					if(!single){
						table.ajax.reload();
						swal({
							title: "标记已读!",
							type: "success",
							confirmButtonText: "确定"
						});
					}
				} else {
					swal({
						title: data.returnMsg,
						type: "error",
						confirmButtonText: "确定"
					});
				}

			},
			error: function () {
				swal({
					title: "提交失败！",
					type: "error",
					confirmButtonText: "确定"
				});
			}
		});
	}

	function viewContent(id) {
		$("#" + id + "_content").toggleClass("hidden");
	}

	function downloadAttachment(attachmentName,attachmentPath){
		if(!attachmentPath){
			swal("附件不存在或已被删除");
			return false;
		}
		$.ajax({
	        method: 'POST',
	        url: '${ctx}/platformMsg/checkFileExists',
	        data: {"attachmentPath":attachmentPath},
	        dataType: 'json'
	    }).done(function (result) {
	        if (result.returnCode =='0000') {
	        	location.href="${ctx}/platformMsg/downLoadAttachment?fileName="+attachmentName+"&filePath="+attachmentPath;
	        }else{
	        	swal(result.returnMsg);
	        }
	    });
		
	}
</script>
</body>
</html>
