<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>官方活动管理</title>
</head>

<body>
<div class="x_panel container-fluid gf-hd">
	<input type="hidden" id="mchtInfoStatus" value="${mchtInfoStatus}"/>
    <div class="row content-title">
        <div class="t-title">
            	官方活动报名
        </div>
    </div>
    <div class="search-container container-fluid">
    <input type="hidden" name="pageNumber" id="pageNumber" value="${pageNumber}">
        <form class="form-horizontal" id="searchForm">
            <input type="hidden" name="pageStatus" value="${pageStatus}"/>
            <div class="form-group">
                <label for="name" class="col-md-1 control-label search-lable">活动名称：</label>
                <div class="col-md-2 search-condition">
                    <input class="form-control nameWidth200" type="text"  id="name" name="name" value="${name}">
                </div>
                <div class="col-md-3 text-center search-btn">
                    <button type="button"  class="btn btn-info" id="btn-query">搜索</button>
                </div>
            </div>
        </form>
    </div>
    <div class="clearfix"></div>
    <ul class="nav nav-tabs q" role="tablist">
        <li role="presentation" <c:if test="${pageStatus==0}">class="active"</c:if>> <a href="#" role="tab" data-toggle="tab" onclick="list(0);">全部活动（${allCount}）</a></li>
        <li role="presentation" <c:if test="${pageStatus==1}">class="active"</c:if>> <a href="#" role="tab" data-toggle="tab" onclick="list(1);">未报名活动（${noActivityCount}）</a></li>
        <li role="presentation" <c:if test="${pageStatus==2}">class="active"</c:if>> <a href="#" role="tab" data-toggle="tab" onclick="list(2);">已报名活动（${activityCount}）</a></li>
        <li role="presentation" <c:if test="${pageStatus==3}">class="active"</c:if>> <a href="#" role="tab" data-toggle="tab" onclick="list(3);">已驳回活动（${rejectCount}）</a></li>
    </ul>
    <div class="x_content container-fluid">
        <div class="row">
            <div class="col-md-12 datatable-container">
                <table id="datatable"
                       class="table table-striped table-bordered dataTable no-footer"
                       role="grid" aria-describedby="datatable_info">
                    <thead>
                    <tr role="row">
                        <th>活动基本信息</th>
                        <th width="118">状态</th>
                        <th width="158">操作</th>
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
    function view(id) {
        var url = "${ctx}/plat/activity/view?id=" + id;
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                $("#viewModal").html(data);
                $("#viewModal").modal();
            },
            error: function () {
                swal('页面不存在');
            }
        });
    }

    function edit(id) {
    	var mchtInfoStatus = $("#mchtInfoStatus").val();
		if(mchtInfoStatus == "2"){
			swal("对不起，合作状态暂停，不能提报活动");
			return false;
		}
        var url = "${ctx}/plat/activity/edit?id=" + id;
        getContent(url);
//        $.ajax({
//            url: url,
//            type: "GET",
//            success: function (data) {
//                $("#viewModal").html(data);
//                $("#viewModal").modal();
//            },
//            error: function () {
//                swal('页面不存在');
//            }
//       });
    }
    
    function manage(id,type,activityId) {
//        var url = "${ctx}/plat/activity/manage?id=" + id;
//        $.ajax({
//            url: url,
//            type: "GET",
//            success: function (data) {
//                $("#viewModal").html(data);
//                $("#viewModal").modal();
//            },
//            error: function () {
//                swal('页面不存在');
//            }
//        });
		var name = $("#name").val();
		var pageNumber = $("li[class='paginate_button active']").find("a").first().text();
		if(type == 1){
	    	var url = "${ctx}/plat/activity/manage?id=" + id;
	    	if(name){
	    		url+="&name="+name;
	    	}
	    	if(pageNumber){
	    		url+="&pageNumber="+pageNumber;
	    	}
	        getContent(url);
		}else if(type == 2 || type == 3){
			var url = "${ctx}/plat/activity/listProductPage?activityId=" + activityId+"&status=2";
			if(name){
	    		url+="&name="+name;
	    	}
	    	if(pageNumber){
	    		url+="&pageNumber="+pageNumber;
	    	}
	        getContent(url);
		}
    }

    var table;
    $(document).ready(function () {
    	var isInit = true;
		var pageNumber;
        table = $('#datatable').dataTable({
            "ajax": function (data, callback, settings) {
            	pageNumber = $("#pageNumber").val();
                var param = $("#searchForm").serializeArray();
                param.push({"name": "pageSize", "value": data.length});
                if (data.start > 0) {
                    param.push({"name": "pageNumber", "value": data.start / data.length + 1});
                } else if(isInit && pageNumber && parseInt(pageNumber)>0){
					param.push({"name": "pageNumber", "value": pageNumber});
				}else {
                    param.push({"name": "pageNumber", "value": 1});
                }

                $.ajax({
                    method: 'POST',
                    url: '${ctx}/plat/activity/list',
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
                {"data": "id", "render": function (data, type, row, meta) {
                    var html = [];
                    html.push('<div class="no-check">');
                    html.push("<div class='width-img'>"+"<img src='${ctx}/file_servelt"+row.pic+"'height='100' width='100'>"+"</div>");
                    html.push('<div class="width-txt">');
                    html.push('<h3><strong style="cursor: default;">【' + row.typeStr + '】&nbsp;' + row.name + '</strong><a href="javascript:;" onclick="view('+row.id+');">【详情】</a></h3>');
                    html.push('<p>报名时间：' + row.enrollBeginTime + '&nbsp;&nbsp;-&nbsp;&nbsp;' + row.enrollEndTime + '</p>');
                    html.push('<p>活动时间：' + row.activityBeginTime + '&nbsp;&nbsp;-&nbsp;&nbsp;' + row.activityEndTime + '</p>');
                    html.push("<div>");
                    html.push("<div>");
                    return html.join("");
                }},
                {"data": "statusStr"},
                {"data": "id", "class": "pl", "render": function (data, type, row, meta) {
                        var html = [];
                    if(row.activity){
                        html.push('<a class="table-opr-btn gl" href="javascript:void(0);" onclick="manage('+row.id+','+row.type+','+row.activity.id+');" >管理报名</a>');
//                        if(row.type == "1"){
//                        	if(row.statusStr == "报名已结束" || row.statusStr == "活动已结束"){
//                        		html.push('<br><a class="table-opr-btn" href="javascript:void(0);">立即报名</a>');
//                       	}else{
//                        		var now = new Date().getTime();
//                        		if( now >= new Date(row.enrollBeginTime).getTime() && now <= new Date(row.enrollEndTime).getTime() ){
//			                        html.push('<br><a class="table-opr-btn lj" href="javascript:void(0);" onclick="edit(', "'"+row.id+"'", ');" >立即报名</a>');
//                       		}else{
//	                        		html.push('<br><a class="table-opr-btn" href="javascript:void(0);">立即报名</a>');
//                        		}
//                        	}
//                        }
                    }else{
                        if(row.statusStr == "未报名"){
                            html.push('<a class="table-opr-btn lj" href="javascript:void(0);" onclick="edit(', "'"+row.id+"'", ');" >立即报名</a>');
                        }else{
                            html.push('<a class="table-opr-btn" href="javascript:void(0);">立即报名</a>');
                        }
                    }
                        return html.join("");
                }}
            ],
            "initComplete": function(settings, json) {
		           //这里在初始化完成后修改一下显示的页数
		           if(isInit&& parseInt(pageNumber)!=0){
		    		   isInit = false;
		    		   table.page(parseInt(pageNumber)-1).draw(false);
		    	   }
		       }
        }).api();

        $('#btn-query').on('click', function (event) {
        	$("#pageNumber").val(1);
            table.ajax.reload();
        });
		
        $("#name").keydown(function(e){
	    	if(e.keyCode==13){
	    		table.ajax.reload();
	            return false;
	    	}
	    });
    });

    function list(pageStatus) {
        var url = "${ctx}/plat/activity?pageStatus=" + pageStatus;
        getContent(url);
    }
</script>
</body>
</html>
