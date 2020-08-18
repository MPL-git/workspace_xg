<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>确认结算单</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/validate.jf.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/viewer.min.css" />

</head>

<body>

<div class="modal-dialog" role="document" style="width:600px;">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <span class="modal-title" id="exampleModalLabel">确认结算单</span>
        </div>
        <div class="modal-body">
            <div>在确认前请您检查商家银行账号是否有误。</div>
            <div style="color:#f00;">您的保证金缴纳不足时，在您确认之后，平台会将部分金额抵缴到保证金。</div>
            <form id="dataFrom">
                <input type="hidden" value="${model.id}" name="id">
                <input type="hidden" value="3" name="mchtCollectType">
                <div class="table-responsive">
                    <%-- <table class="table table-bordered ">
                        <tbody>
                        <!-- 
                        <tr>
                            <td class="editfield-label-td">开票类型</td>
                            <td colspan="2" class="text-left">
                                <c:forEach var="invoiceType" items="${invoiceTypeList}">
                                    <input type="radio" name="mchtCollectType" value="${invoiceType.value}" validate="{required:true}"
                                           <c:if test="${model.mchtCollectType==invoiceType.value}">checked="checked"</c:if>>${invoiceType.name}
                                </c:forEach>
                            </td>
                        </tr>
						-->
                        <tr name="contact">
                            <td class="editfield-label-td">财务收票人</td>
                            <td colspan="2" class="text-left">
                                <input type="text" name="mchtReceiverName" value="${contactName}" validate="{required:true}" >

                            </td>
                        </tr>

                        <tr name="contact">
                            <td class="editfield-label-td">联系电话</td>
                            <td colspan="2" class="text-left">
                                <input type="text" name="mchtReceiverPhone" value="${mobile}" validate="{required:true,mobile:true}" >

                            </td>
                        </tr>

                        <tr name="contact">
                            <td class="editfield-label-td">联系地址</td>
                            <td colspan="2" class="text-left">
                                <select style="width:100px;" class="province-select" onchange="onchangeProvince();" name="province" id="province" ></select>
                                <select style="width:100px;" class="city-select" onchange="onchangeCity();" name="city" id="city" ></select>
                                <select style="width:100px;" class="county-select"  name="county" id="county" validate="{required:true}"></select>
                                <br>
                                <br>
                                <input style="width:200px;" type="text" id="mchtReceiverAddress" name="mchtReceiverAddress" value="${address}" validate="{required:true}">
                            </td>
                        </tr>


                        </tbody>
                    </table> --%>
                    <table class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
						<tbody>
							<tr>
								<td>账户类型</td>
								<td style="text-align: left;">${mchtBankAccount.accTypeDesc}</td>
							</tr>
							<tr>
								<td>账户名称</td>
								<td style="text-align: left;">${mchtBankAccount.accName}</td>
							</tr>
							<tr>
								<td>开户银行</td>
								<td style="text-align: left;">${mchtBankAccount.bankName}</td>
							</tr>
							<tr>
								<td>账户账号</td>
								<td style="text-align: left;">${mchtBankAccount.accNumber}</td>
							</tr>
							<tr>
								<td>开户支行名称</td>
								<td style="text-align: left;">${mchtBankAccount.depositBank}</td>
							</tr>
							<tr>
								<td>状态</td>
								<td style="text-align: left;">${mchtBankAccount.statusDesc}</td>
							</tr>
							<tr>
								<td>操作</td>
								<td style="text-align: left;">
							
									<c:if test="${empty mchtBankAccount.id||mchtBankAccount.status=='0'||mchtBankAccount.status=='2'||mchtBankAccount.status=='3'}">
									<a href="javascript:mchtBankAccountEdit()">【修改】</a>
									</c:if>
									<a href="javascript:viewChgLog()">【查看更新记录】</a>
								</td>
							</tr>
						</tbody>
					</table>
                </div>
            </form>

            <div class="modal-footer">
                <button class="btn btn-info mr17" onclick="commitSave();">确认</button>
                <button class="btn btn-info" data-dismiss="modal">取消</button>
            </div>

        </div>
    </div>
</div>


<script src="${ctx}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script src="${ctx}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery.validate.jf.js" type="text/javascript"></script>

<script type="text/javascript">
function mchtBankAccountEdit(){
	
	$.ajax({
        url: "${ctx}/mchtBankAccount/mchtBankAccountEdit?id=${mchtBankAccount.id}", 
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

function viewChgLog(){
	getContent("${ctx}/mchtBankAccount/mchtBankAccountHisIndex");
	$(".modal-backdrop").hide();
	/* $.ajax({
	       url: "${ctx}/mchtBankAccount/mchtBankAccountHisIndex", 
	       type: "GET", 
	       success: function(data){
	       	 $("#viewModal").html(data);
	         $("#viewModal").modal();
	       },
	    error:function(){
	    	swal('页面不存在');
	    }
	}); */
}

    var dataFromValidate;
    $(function () {
		
    	$("input[name='mchtCollectType']").on('click',function(){
    		var mchtCollectType = $(this).val();
    		if(mchtCollectType == 2){
    			$("tr[name='contact']").show();
    		}else if(mchtCollectType == 3){//不需要发票
    			$("tr[name='contact']").hide();
    		}
    	});
    	
        getProvinceList();
        getCityList(${provinceId});
        getCountyList(${cityId});
        $(".province-select").find("option[value='${provinceId}']").prop("selected",true);
        $(".city-select").find("option[value='${cityId}']").prop("selected",true);
        $(".county-select").find("option[value='${countyId}']").prop("selected",true);

        $.metadata.setType("attr", "validate");
        dataFromValidate = $("#dataFrom").validate({
            highlight: function (element) {
                $(element).addClass('error');
                $(element).closest('tr').find("td").first().addClass('has-error');
            },
            success: function (label) {
                label.closest('tr').find("td").first().removeClass('has-error');
            },
            errorPlacement: function (error, element) {
                error.appendTo($(element).closest('td'));
            }
        });


    });


    function commitSave() {
    	var checked;
    	var mchtCollectType = "3";
//    	var mchtCollectType = $("input[name='mchtCollectType']:checked").val();
//    	if(!mchtCollectType){
//    		swal("请选择开票类型!");
//    		checked = false;
//    	}
    	if(mchtCollectType == 2){
    		checked = dataFromValidate.form();
    	}else if(mchtCollectType == 3){
    		checked = true;
    	}
        if (checked) {
            var dataJson = $("#dataFrom").serializeArray();
            $.ajax({
                url: "${ctx}/mchtSettleOrder/confirmCommit",
                type: 'POST',
                dataType: 'json',
                cache: false,
                async: false,
                data: dataJson,
                timeout: 30000,
                success: function (result) {
                    if (result.success) {
                        swal({
                            title: "提交成功!",
                            type: "success",
                            confirmButtonText: "确定"
                        });

                        $('#viewModal').on('hidden.bs.modal', function (e) {
                            getContent("${ctx}/mchtSettleOrder/detail?id=${model.id}")
                        })

                        $("#viewModal").modal('hide');
                    } else {
                        swal({
                            title: result.returnMsg,
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

    }


    function getProvinceList(){
        var path = '${pageContext.request.contextPath}/area/getAreaList.shtml';
        jQuery.ajax( {
            url : path,
            type : 'POST',
            dataType : 'json',
            cache : false,
            async : false,
            data : { parentAreaId:0},
            timeout : 30000,
            success : function(json)
            {  var sel = $(".province-select");
                sel.empty();
                sel.append("<option value=\"" + "\">--省份--</option>");

                $.each(json, function(index, item) {
                    sel.append("<option value=\"" + item.areaId + "\">" + item.areaName + "</option>");
                });

            },
            error : function()
            {
                swal('操作超时，请稍后再试！');
            }
        });
    }
    function getCityList(parentAreaId){
        if(typeof(parentAreaId)!="undefined"){
            var path = '${pageContext.request.contextPath}/area/getAreaList.shtml';
            jQuery.ajax( {
                url : path,
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : { parentAreaId : parentAreaId},
                timeout : 30000,
                success : function(json)
                {  var sel = $(".city-select");
                    sel.empty();
                    sel.append("<option value=\"" + "\">--地级市--</option>");

                    $.each(json, function(index, item) {
                        sel.append("<option value=\"" + item.areaId + "\">" + item.areaName + "</option>");
                    });

                },
                error : function()
                {
                    swal('操作超时，请稍后再试！');
                }
            });
        }
    }

    function getCountyList(parentAreaId){
        if(typeof(parentAreaId)!="undefined"){
            var path = '${pageContext.request.contextPath}/area/getAreaList.shtml';
            jQuery.ajax( {
                url : path,
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : { parentAreaId : parentAreaId},
                timeout : 30000,
                success : function(json)
                {  var sel = $(".county-select");
                    sel.empty();
                    sel.append("<option value=\"" + "\">--县级--</option>");

                    $.each(json, function(index, item) {
                        sel.append("<option value=\"" + item.areaId + "\">" + item.areaName + "</option>");
                    });

                },
                error : function()
                {
                    swal('操作超时，请稍后再试！');
                }
            });
        }
    }
    function  onchangeProvince(){
        $(".city-select").empty();
        $(".county-select").empty();
        getCityList($(".province-select").val());
    }
    function  onchangeCity(){
        $(".county-select").empty();
        getCountyList($(".city-select").val());
    }

</script>
</body>
</html>
