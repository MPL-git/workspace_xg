<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>

    <script type="text/javascript">
    	$(function(){
    		// 倒计时
            setInterval(function () {
                $(".complainEndDateDiv").each(function () {
                    var limitDate = parseInt($(this).attr("data-value"));
                    var result = countdown(limitDate);
                    $(this).html(result);
                });
            }, 60000);
    	});
    
        // 查看信息
        function view(id, complainId, prosecutionId) {
			var url = "${pageContext.request.contextPath}/propertyRightAppeal/appealManageDetail.shtml?id=" + id;
			if (complainId) {
				url = "${pageContext.request.contextPath}/propertyRightComplain/complainManageDetail.shtml?id=" + complainId;
			}
			if (prosecutionId) {
				url = "${pageContext.request.contextPath}/propertyRightProsecution/prosecutionManageDetail.shtml?id=" + prosecutionId;
			}
			$.ligerDialog.open({
			    height: 800,
			    width: 1500,
			    title: "查看投诉信息",
			    name: "INSERT_WINDOW",
			    url: url,
			    showMax: true,
			    showToggle: false,
			    showMin: false,
			    isResize: true,
			    slide: false,
			    data: null
			});
        }
        
     	// 创建工单
        function addWork(id) {
           $.ligerDialog.open({
               height: 800,
               width: 1500,
               title: "新增工单",
               name: "INSERT_WINDOW",
               url: "${pageContext.request.contextPath}/work/addwork.shtml?rightAppealId=" + id,
               showMax: true,
               showToggle: false,
               showMin: false,
               isResize: true,
               slide: false,
               data: null
           });
        }
     	
     	//查看撤销原因
     	function viewReason(reason) {
     		if (reason === 'null') {
     			reason = "无";
     		}
     		$("#reasonDiv").html(reason);
     		$.ligerDialog.open({
     	        target: $("#reasonDiv"),
     	        title: '撤销原因',
     	        width: 380,
     	        height: 150,
     	        isResize: true,
     	        modal: true
     		});
     	}
        
     	// 修改处理人
        function changeStaff(id) {
            if (!id){
                $.ligerDialog.warn("数据错误，请刷新页面");
                return false;
            }
            
            $.ligerDialog.open({
                height: 200,
                width: 300,
                title: "新增工单",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/propertyRightAppeal/changeStaffDialog.shtml?id=" + id,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }
     	
     	// 倒计时
        function countdown(limitDate) {
            var nowDate = new Date().getTime();
            if (limitDate <= nowDate) {
                return "00天00时00分";
            }
            var times = (limitDate - nowDate) / 1000;
            var day = 0,
                hour = 0,
                minute = 0,
                second = 0;//时间默认值
            if (times > 0) {
                day = Math.floor(times / (60 * 60 * 24));
                hour = Math.floor(times / (60 * 60)) - (day * 24);
                minute = Math.floor(times / 60) - (day * 24 * 60) - (hour * 60);
                second = Math.floor(times) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
            }
            if (day <= 9) day = '0' + day;
            if (hour <= 9) hour = '0' + hour;
            if (minute <= 9) minute = '0' + minute;
            if (second <= 9) second = '0' + second;
            return day + "天" + hour + "小时" + minute + "分";
        }

        var listConfig = {
            url: "/propertyRightAppeal/allAppealData.shtml",
            btnItems: [],
            listGrid: {
                columns: [
					{ display: '提审时间', width: 150, render: function (rowdata, rowindex) {
						if (rowdata.createDate!=null){
							var createDate=new Date(rowdata.createDate);
							return createDate.format("yyyy-MM-dd hh:mm:ss");
						}
					}},
					{display: '投诉ID', name: 'id', align: 'center', isSort: false, width: 140},
					{
					    display: '申请人类型',
					    name: '',
					    align: 'center',
					    isSort: false,
					    width: 180,
					    render: function (rowdata, rowindex) {
					    	var html = [];
					        var identityType = rowdata.identityType;
					        if (identityType != null && identityType != '') {
					            var identityTypeDesc = identityType == '1' ? '个人' : '企业';
					            html.push(identityTypeDesc);
					        }
					        // var propertyRightBelong = rowdata.propertyRightBelong;
					        // if (propertyRightBelong != null && propertyRightBelong != '') {
					        //     var propertyRightBelongDesc = propertyRightBelong == '1' ? '权利人' : '代理人';
					        //     html.push("/");
					        //     html.push(propertyRightBelongDesc);
					        // }
					        return html.join("");
					    }
					},
					{display: '申请人手机号码', name: 'mobile', align: 'center', isSort: false, width: 180},
					{display: '侵权类型', name: 'propertyRightTypeDesc', align: 'center', isSort: false, width: 180},
					{
					    display: '侵权信息',
					    name: '',
					    align: 'center',
					    isSort: false,
					    width: 150,
					    render: function (rowdata, rowindex) {
					        var html = [];
				        	html.push("<a href=\"javascript:view(" + rowdata.id + "," + rowdata.complainId +  "," + rowdata.prosecutionId + ");\">查看信息</a>");
					        return html.join("");
					    }
					},
					{
					    display: '处理人',
					    name: '',
					    align: 'center',
					    isSort: false,
					    width: 150,
					    render: function (rowdata, rowindex) {
					        var html = [];
					        if (rowdata.staffId) {
					        	html.push(rowdata.staffName);
					        	html.push("<a href=\"javascript:changeStaff(" + rowdata.id + ");\">【修改】</a>");
					        }
				        	
					        return html.join("");
					    }
					},
					{display: '投诉状态', name: 'statusDesc', align: 'center', isSort: false, width: 180},
					{
					    display: '平台受理状态',
					    name: '',
					    align: 'center',
					    isSort: false,
					    width: 150,
					    render: function (rowdata, rowindex) {
					        var html = [];
					        var acceptStatusDesc = rowdata.acceptStatusDesc;
				        	html.push(acceptStatusDesc + "\n");
				        	if (rowdata.acceptStatus == '1' || rowdata.acceptStatus == '2') {
				        		var commitDate = new Date(rowdata.commitDate);
					        	html.push(commitDate.format("yyyy-MM-dd hh:mm:ss"));
				        	}
					        return html.join("");
					    }
					},
					{
					    display: '商家声明',
					    name: '',
					    align: 'center',
					    isSort: false,
					    width: 150,
					    render: function (rowdata, rowindex) {
					    	if (rowdata.acceptStatus === '3') {
					    		return "";
					    	}
					        var html = [];
					        var str = "";
					        // 平台已受理投诉
					        if (rowdata.acceptStatus == '1') {
					        	// 未生成商家声明 即商家未申诉 如果超过申诉时间即 超期未申明
					        	if (rowdata.complainId == null || rowdata.complainStatus != '4') {
					        		str = new Date().getTime() > rowdata.complainEndDate? "超期未声明": "待申诉";
					        	} else {
					        		str = rowdata.complainStatusDesc;
					        	}
				        	}
					        
				        	html.push(str + "\n");
				        	// 商家已声明时展示时间
				        	if (rowdata.complainStatus == '4') {
				        		var complainDate = new Date(rowdata.complainDate);
					        	html.push(complainDate.format("yyyy-MM-dd hh:mm:ss"));
				        	}
				        	
					        return html.join("");
					    }
					},
					{
					    display: '平台转发状态（对权利人）',
					    name: '',
					    align: 'center',
					    isSort: false,
					    width: 150,
					    render: function (rowdata, rowindex) {
					    	if (rowdata.acceptStatus === '3') {
					    		return "";
					    	}
					        var html = [];
					        var str = "";
					        var complainStatus = rowdata.complainStatus;
					        if (rowdata.acceptStatus == '1') {
					        	// 未生成商家声明 即商家未申诉 如果超过申诉时间即 超期未申明
					        	if (rowdata.complainId == null) {
					        		str = new Date().getTime() > rowdata.complainEndDate? "超期未声明": "待商家声明";
					        	} else if(complainStatus != '4' && new Date().getTime() > rowdata.complainEndDate) {
					        		str = "超期未声明";
					        	} else if (complainStatus == '1' || complainStatus == '2') {// 已生成商家声明 
						        	str = "待商家声明";
						        } else if (complainStatus == '4') {
						        	str = "商家已声明";
						        } else if (complainStatus == '3') {
						        	str = "超期未声明";
						        }
				        	}
					        html.push(str);
					        
					        return html.join("");
					    }
					},
					{
					    display: '起诉状态',
					    name: '',
					    align: 'center',
					    isSort: false,
					    width: 150,
					    render: function (rowdata, rowindex) {
					    	if (rowdata.acceptStatus === '3') {
					    		return "";
					    	}
					        var html = [];
					        var str = "";
					        // 平台已受理投诉 并且商家已声明
					        if (rowdata.acceptStatus == '1' && rowdata.complainStatus == '4') {
					        	// 未生成商家声明 即商家未申诉 如果超过申诉时间即 超期未申明
					        	if (rowdata.prosecutionId == null) {
					        		str = new Date().getTime() > rowdata.prosecutionEndDate? "未起诉撤销": "待申诉";
					        	} else {
					        		str = rowdata.prosecutionStatusDesc;
					        	}
				        	}
					        html.push(str);
					        return html.join("");
					    }
					},
					{ display: '起诉截止剩余时间', width: 140, render: function(rowdata, rowindex) {
						if (rowdata.acceptStatus === '3') {
				    		return "";
				    	}
						var resultHtml = [];
		            	if (rowdata.prosecutionEndDate != null && (!rowdata.complainId || rowdata.complainStatus === '1' || rowdata.complainStatus === '2')){
	                        var desc = countdown(rowdata.prosecutionEndDate);
	                        resultHtml.push('<div class="complainEndDateDiv" data-value="' + rowdata.prosecutionEndDate + '">' + desc + '</div>');
		            	}
		            	return resultHtml.join("");
		         	}},
					{
					    display: '撤销处罚',
					    name: '',
					    align: 'center',
					    isSort: false,
					    width: 150,
					    render: function (rowdata, rowindex) {
					    	if (rowdata.acceptStatus === '3') {
					    		return "";
					    	}
					        var html = [];
					        var prosecutionStatus = rowdata.prosecutionStatus;
					        // 起诉无效撤销
					        if (prosecutionStatus == "3") {
					        	html.push("已撤销");
					        	html.push("<a href=\"javascript:viewReason(\'" + rowdata.remarkToInner + "\');\">【查看原因】</a>");
					        }
					     	// 败诉撤销
					        if (prosecutionStatus == "5") {
					        	html.push("已撤销");
					        	html.push("<a href=\"javascript:viewReason(\'" + rowdata.prosecutionInnerRemarks + "\');\">【查看原因】</a>");
					        }
					        return html.join("");
					    }
					}
                ],
                showCheckbox: false,  //不设置默认为 true
                showRownumber: true //不设置默认为 true
            },
            container: {
                toolBarName: "toptoolbar",
                searchBtnName: "searchbtn",
                fromName: "dataForm",
                listGridName: "maingrid"
            }
        };

    </script>
</head>
<body style="padding: 0px; overflow: hidden;">
<!-- <div id="toptoolbar"></div> -->
<form id="dataForm" runat="server">
    <div class="search-pannel">
        <div class="search-tr">
			<div class="search-td">
                <div class="search-td-label">申请人手机号码：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="mobile" name="mobile">
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">产权类型：</div>
                <div class="search-td-combobox-condition">
                    <select id="propertyRightType" name="propertyRightType" style="width: 135px;">
                        <option value="">请选择...</option>
                        <c:forEach items="${rightTypeList}" var="rightType">
                            <option value="${rightType.statusValue}">${rightType.statusDesc}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">平台受理：</div>
                <div class="search-td-combobox-condition">
                    <select id="acceptStatus" name="acceptStatus" style="width: 135px;">
                        <option value="">请选择...</option>
                        <c:forEach items="${acceptStatusList}" var="acceptStatus">
                            <option value="${acceptStatus.statusValue}">${acceptStatus.statusDesc}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">商家声明：</div>
                <div class="search-td-combobox-condition">
                    <select id="complainStatus" name="complainStatus" style="width: 135px;">
                        <option value="">请选择...</option>
                        <option value="-1">待申诉</option>
                        <c:forEach items="${complainStatusList}" var="complainStatus">
                            <option value="${complainStatus.statusValue}">${complainStatus.statusDesc}</option>
                        </c:forEach>
                        <option value="99">超期未声明</option>
                    </select>
                </div>
            </div>
            
            <div class="search-td-search-btn">
                <div id="searchbtn">搜索</div>
            </div>
        </div>
    </div>
    
    <div class="search-pannel">
        <div class="search-tr">
        
        <div class="search-td">
			<div class="search-td-label" style="float:left;">申诉时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="create_date_begin" name="createDateBegin" />
				<script type="text/javascript">
					$(function() {
						$("#create_date_begin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="create_date_end" name="createDateEnd" />
				<script type="text/javascript">
					$(function() {
						$("#create_date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			<div class="search-td">
				<div class="l-panel-search-item">
	                <div class="search-td-label">起诉状态：</div>
	                <div class="search-td-combobox-condition">
	                    <select id="prosecutionStatus" name="prosecutionStatus" style="width: 135px;">
	                        <option value="">请选择...</option>
	                        <option value="-1">待提交</option>
	                        <c:forEach items="${prosecutionStatusList}" var="prosecutionStatus">
	                            <option value="${prosecutionStatus.statusValue}">${prosecutionStatus.statusDesc}</option>
	                        </c:forEach>
	                        <option value="99">未起诉撤销</option>
	                    </select>
	                </div>
               	</div>
            </div>
            <div class="search-td">
            	<div class="l-panel-search-item">
	                <div class="search-td-label">平台转发状态：</div>
	                <div class="search-td-combobox-condition">
	                    <select id="transmitStatus" name="transmitStatus" style="width: 135px;">
	                        <option value="">请选择...</option>
	                        <option value="1">待商家声明</option>
	                        <option value="2">商家已声明</option>
	                        <option value="3">超期未声明</option>
	                    </select>
	                </div>
                </div>
            </div>
    </div>
    </div>
</form>

<div id="reasonDiv" style="text-align:center">
	
</div>

<div id="maingrid" style="margin: 0;"></div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
