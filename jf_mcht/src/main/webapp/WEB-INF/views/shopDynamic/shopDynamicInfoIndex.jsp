<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>动态管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.lineFeed {
  width: 395px;
  word-wrap: break-word;
}
.td-ul {
margin: 0;
padding: 0;
}
.td-ul li{
display: inline-block;
}
.td-ul li img{
width: 60px;
height: 60px;
}
.td-pictures li{
display: inline-block;
}
.td-pictures li img{
width: 100px;
height: 100px;
}

.video_box {
	z-index: 2222;
	display: block;
}

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
.video_close {
	position: absolute;
	top: -14px;
	right: -12px;
}

.form-group img{
	width: 100px;
	height: 100px;
}
</style>

</head>

<body>
		<div class="x_panel container-fluid">
		<div class="row content-title">
			<div class="t-title">动态管理
			<a href='javascript:void(0);' onclick="addDynamic();" >添加</a>			
			</div>
		</div>
		<div class="search-container container-fluid">
				<form class="form-horizontal" id="searchForm">
				<div class="form-group">
				  	
				  	<label for="productBrand" class="col-md-1 control-label search-lable">动态内容：</label>
				  	<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text" id="search_content" name="search_content" >
					</div>
				  	
				  	<label for="productBrand" class="col-md-1 control-label search-lable">状态：</label>
					<div class="col-md-2 search-condition">
						 <select class="form-control" name="search_status" id="search_status">
						   <option value="">--请选择--</option>
						   <option value="0">--待审核--</option>
						   <option value="1">--审核通过--</option>
						   <option value="2">--审核失败--</option>
						  <!--  <option value="3">--已删除--</option> -->
                         </select>
					</div>
				  	
				  	<label for="productBrand" class="col-md-1 control-label search-lable">创建时间：</label>
					<div class="col-md-5 search-condition">
						 <input class="form-control datePicker" placeholder="" type="text"  id="dynamicDateBegin" name="dynamicDateBegin" data-date-format="yyyy-mm-dd">
						 <i class="picker-split">-</i>
						 <input class="form-control datePicker" placeholder="" type="text"  id="dynamicDateEnd" name="dynamicDateEnd" data-date-format="yyyy-mm-dd">
					</div>
					
					<div class="col-md-3 text-center search-btn">
						             <button type="button"  class="btn btn-info" id="btn-query">搜索</button>
					</div>
					
				</div>
				
			</form>

		</div>
		<div class="clearfix"></div>
		<div class="x_content container-fluid">
				<div class="row">
					<div class="col-md-12 datatable-container at-table">
						<table id="datatable"
							class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							
						</table>
					</div>
				</div>
		</div>
	</div>
	
<!-- 	查看图片 -->
<div class="modal fade" id="preViewModal"  tabindex="-1" role="dialog" aria-labelledby="preViewModal">
  <div class="modal-dialog" role="document" style="width: 782px;">
    <div class="modal-content">
      <div class="modal-header">
      	<span class="bold">&nbsp;&nbsp;&nbsp;&nbsp;动态顶部封面</span>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body container-fluid">
      	<div id="dynamicPic">
      	</div>   	
    </div>
      <div class="modal-footer">
			<button class="btn btn-info" data-dismiss="modal">关闭</button>
      </div>
     </div>
  </div>
</div>
<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static"></div>
	<ul  class="docs-pictures clearfix td-ul" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/producttype-select.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>	
	<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/utils.js" type="text/javascript"></script>
	
	<script>	
	   
	function preViewProduct(topCover){
		
		$("#dynamicPic").html('<img src="${ctx}/file_servelt'+topCover.type+'">');
		
		$("#preViewModal").modal();
	}
var table;

$(document).ready(function () {

	//默认展示时间
    var time = new Date();
    var day = ("0" + time.getDate()).slice(-2);
    var month = ("0" + (time.getMonth() + 1)).slice(-2);
    var beginToday = time.getFullYear() + "-" + (month) + "-01";
    $('#dynamicDateBegin').val(beginToday);
    
    var lastDate = getLastDay(time.getFullYear(),month);
    var endToday = time.getFullYear() + "-" + (month) + "-" + lastDate;    
    $('#dynamicDateEnd').val(endToday);
 
    function getLastDay(year,month)        
    {        
     var new_year = year;    //取当前的年份         
     var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）         
     if(month>12)            //如果当前大于12月，则年份转到下一年         
     {        
      new_month -=12;        //月份减         
      new_year++;            //年份增         
     }     
     var new_date = new Date(new_year,new_month,1);                //取当年当月中的第一天 
     var date_count =   (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月的天数       
     var last_date =   new Date(new_date.getTime()-1000*60*60*24);//获得当月最后一天的日期
    return date_count;
    } 
    
    
	$('.datePicker').datetimepicker(
			{
				minView: "month", //选择日期后，不会再跳转去选择时分秒
				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
				language: 'zh-CN', //汉化
				autoclose:true //选择日期后自动关闭
			}
	);
	
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
                url: '${ctx}/shopDynamic/getShopDynamicInfoIndexList',
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
			{"data": "id","title":"动态顶部封面","render": function (data, type, row, meta) {
				var html = [];
            	html.push('<div class="is-check">');
            	if(row.topCover){
	            	if(row.topCover.indexOf("http") >= 0){//网络图片
//	            		html.push('<div class="width-60"><ul class="td-ul" id="idCard-list"><li><img src='+row.topCover+'></li></ul></div>');
	            		html.push('<div class="width-60" ><a href="#" onclick="preViewProduct(this);" type="'+row.topCover+'"><img src='+row.topCover+'></a></div>');
	            	}else{
//	            		html.push('<div class="width-60"><ul class="td-ul" id="idCard-list"><li><img src="${ctx}/file_servelt'+row.topCover+'"></li></ul></div>');
	            		html.push('<div class="width-60"><a href="#" onclick="preViewProduct(this);" type="'+row.topCover+'"><img src="${ctx}/file_servelt'+row.topCover+'"></a></div>');
	            		
	            	}
            	}
            	html.push("<div>");
            	return html.join("");
			
				
			}},
			{"data": "id",width:"400","title":"动态内容","render": function (data, type, row, meta) {
		
				return "<div class='lineFeed'>"+row.content+"</div>";
			}},
			{"data": "id","title":"绑定商品","render": function (data, type, row, meta) {
				if(row.productCodes){
            		var rulesArray = row.productCodes.split(",");
                	var html = [];
                	for(var i=0;i<rulesArray.length;i++){
                		html.push(rulesArray[i]+"<br>");                		          
                	}
                	return html.join("");
            	}else{
            		return "";
            	}
			}},
			{"data": "id",width:"50","title":"状态","render": function (data, type, row, meta) {
				if(row.delFlag == "0"){
					if(row.auditStatus == "0"){
				    	return "待审核";
					}else if(row.auditStatus == "1"){
						return "审核通过";
					}else if(row.auditStatus == "2"){
						return "审核失败";
					}else{
						return "异常";
					}
				}
				if(row.delFlag == "1"){
					if(row.auditStatus == "0"){
				    	return "待审核<br><font color='red'>已删除</font>";
					}else if(row.auditStatus == "1"){
						return "审核通过<br><font color='red'>已删除</font>";
					}else if(row.auditStatus == "2"){
						return "审核失败<br><font color='red'>已删除</font>";
					}else{
						return "异常<br><font color='red'>已删除</font>";
					}
				}
			}},
			{"data": "id","title":"创建人","render": function (data, type, row, meta) {
				if(row.creator == null){
					return row.creatCode;
				}else{
					return row.creator;
				}
			}},
			{"data": "id","title":"创建时间","render": function (data, type, row, meta) {
				var createDate = new Date(row.createDate);
			    return createDate.format("yyyy-MM-dd hh:mm:ss");
			}},
            {"data": "id",width:"89","title":"操作","render": function (data, type, row, meta) {
            	if(row.delFlag == "1"){
            		return "";
                	}
	            var html = [];
            	html.push("<a href='javascript:;' onclick='detail("+row.id+")'>详情</a>&nbsp;&nbsp;");
            	html.push("<a href='${url}"+row.id+"' target='_blank'>预览</a><br>");
            	if(row.auditStatus != "1"){
            	html.push("<a href='javascript:;' onclick='alter("+row.id+")'>编辑</a>&nbsp;&nbsp;");
            	}

            	html.push("<a href='javascript:;' onclick='del("+row.id+")'>删除</a>");
            	
            	return html.join("");
            }}
        ]
    }).api();

    $('#btn-query').on('click', function (event) {
        table.ajax.reload();
    });
 

});


function addDynamic(saleType){
		/* $.ajax({
	        url: "${ctx}/shopDynamic/shopDynamicAdd", 
	        type: "GET", 
	        success: function(data){
	            $("#viewModal").html(data);
	            $("#viewModal").modal();
	        },
		    error:function(){
		    	swal('页面不存在');
		    }
		}); */
	getContent("${ctx}/shopDynamic/shopDynamicAdd");

}

function alter(id){
	getContent("${ctx}/shopDynamic/shopDynamicEdit?id="+id);
}

function detail(id){
	getContent("${ctx}/shopDynamic/shopDynamicDetail?id="+id);
}

function del(id){
	swal({
		  title: "确定要删除此动态?",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonText: "确定",
		  cancelButtonText: "取消",
		  closeOnConfirm: false
		},
		function(){
			delDynamic(id);
		});
}
function delDynamic(id){
	$.ajax({
		url : "${ctx}/shopDynamic/deleteDynamic",
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

function viewViolateOrder(id){
	$.ajax({
        url: "${ctx}/violateOrder/violateOrderView?id="+id, 
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
