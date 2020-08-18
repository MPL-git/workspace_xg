<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/common/common-tag.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
      rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
        type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
        type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
      rel="stylesheet" type="text/css"/>

<link href="${pageContext.request.contextPath}/css/viewer.min.css"
      rel="stylesheet" type="text/css"/>

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<style type="text/css">
    body {
        font-size: 12px;
        padding: 10px;
    }

    td input, td select {
        border: 1px solid #AECAF0;
    }

    .radioClass {
        margin-right: 20px;
    }

    .l-table-edit {

    }

    .l-table-edit-td {
        padding: 4px;
    }

    .l-button-submit, .l-button-test {
        width: 80px;
        float: left;
        margin-left: 10px;
        padding-bottom: 2px;
    }

    .l-verify-tip {
        left: 230px;
        top: 120px;
    }

    .table-title-link {
        color: #1B17EE;
        font-size: 15px;
        text-decoration: none;
    }

    .table-title {
        font-size: 17px;
        font-weight: 600;
    }
</style>
<style type="text/css">
    .middle input {
        display: block;
        width: 30px;
        margin: 2px;
    }

    table.l-checkboxlist-table td {
        border-style: none;
    }

    table.l-listbox-table td {
        border-style: none;
    }

    .td-pictures li {
        display: inline-block;
    }

    td img {
        width: 60px;
        height: 40px;
    }
    /*显示大图*/
	.show-img {
		position: fixed;
		top: 0;
		left: 0;
		z-index: 11;
		width: 100%;
		height: 100%;
		background: rgba(0, 0, 0, .8);
	}
	.show-img img {
		width: auto;
		margin: 50vh auto 0;
		-webkit-transform: translateY(-50%);
		transform: translateY(-50%);
		display:block;
	}
	.blue {
		color: #2476ff;
	}
</style>
<script type="text/javascript">
    $(function(){
    	var statusS = $("input:radio[name='status1']:checked").val();
        var needLimit=$("input:radio[name='needLimit']:checked").val();
        var status156=$("#status").val();
        if (status156!='1' && status156!='5' && status156!='6') {
        	
    	if (statusS=='3' || statusS=='2') {
    		$("input[name='status1']").attr("disabled",true);
 		}
    	if (needLimit=='1' || needLimit=='0') {
    		$("input[name='needLimit']").attr("disabled",true);
 		}
    	
    	 var caseCloseDesc = $("select[name='caseCloseDesc']").val(); 
    	 if (caseCloseDesc=='1' || caseCloseDesc=='2' || caseCloseDesc=='3') {caseCloseDescS
    		 $("select[name='caseCloseDesc']").attr("disabled",true);
		 }
    	 
    	 var caseCloseDescS = $("select[name='caseCloseDescS']").val(); 
    	 if (caseCloseDescS=='4') {
    		 $("select[name='caseCloseDescS']").attr("disabled",true);
		 }
    	 
    	 var rejectReason = $("select[name='rejectReason']").val(); 
    	 if (rejectReason=='1' || rejectReason=='2' || rejectReason=='3' || rejectReason=='4') {
    		 $("select[name='rejectReason']").attr("disabled",true);
		 }
    	 
    	 var commissionerInnerRemarks=$("#commissionerInnerRemarks").val();
    	 if (commissionerInnerRemarks!='') {
    		 $("textarea[name='commissionerInnerRemarks']").attr("disabled",true);
		 }
    	 
    	 var status = $('input[name="status"]:checked').val();
    	 if (status=='4') {
    		 $("input[name='status']").attr("disabled",true);
		 }
    	 
    	 var fwInnerRemarks=$("#fwInnerRemarks").val();
    	 if (fwInnerRemarks!='') {
    		 $("textarea[name='fwInnerRemarks']").attr("disabled",true);
		 }
		
       }
    	  
    	var status=$("#status").val();
    	if (status=='6' || status=='5') {
    		$("input[name='limitMemberAction']").each(function() {
        	    $(this).removeAttr("checked");
        	});
    		$('#commissionerInnerRemarks').val('');

		}
    	if (status=='2') {
    		$('#fwInnerRemarks').val('');
		}
    	
    	
        $("input:radio[name='status1']").change(function (){
            var status1 = $("input:radio[name='status1']:checked").val();
            if (status1 == '3') {
            	$('#needLimit').hide();
            	$('#limitMemberAction1').hide();
            	/* $('#caseCloseDesc1').hide(); */
            	document.getElementById("caseCloseDesc1").style.display="none";
            	document.getElementById("caseCloseDesc2").style.display="none";
            	$('#caseCloseDesc').val('');

            	$("input[name='needLimit']").each(function() {
            	    $(this).removeAttr("checked");
            	}); 
            	
            	$("input[name='limitMemberAction']").each(function() {
            	    $(this).removeAttr("checked");
            	}); 
            	
            	$('#rejectReason1').show();
            }else if (status1 == '2') {
            	$('#needLimit').show();
            	$('#limitMemberAction1').show();
            	$('#caseCloseDesc1').show();
            	$('#rejectReason1').hide();
            	$('#rejectReason').val('');
			}
        });
        
        
        $("input:radio[name='needLimit']").change(function (){
            var needLimit = $("input:radio[name='needLimit']:checked").val();
            if (needLimit == '0') {
            	$('#limitMemberAction1').hide();
            	$('#rejectReason1').hide();
            	$('#rejectReason').val('');
            	document.getElementById("caseCloseDesc1").style.display="none";
        		document.getElementById("caseCloseDesc2").style.display='';
            	$("input[name='limitMemberAction']").each(function() {
            	    $(this).removeAttr("checked");
            	}); 
            	
            	document.getElementById("proof").style.display="none";
            	$('#filePathList').val('');
            	
            }else {
            	$('#limitMemberAction1').show();
            	document.getElementById("caseCloseDesc2").style.display="none";
        		document.getElementById("caseCloseDesc1").style.display='';
        		document.getElementById("proof").style.display="";
			}
        });
        
        
        $("a[name='downLoadimpeachMemberProof']").bind('click',function(){//下载多个文件
   		 var attachmentPath = $(this).attr("attachmentpath");
   		 var attachmentName = $(this).attr("attachmentname");
   		 $.ajax({
   				type: 'post',
   				url: '${pageContext.request.contextPath}/imPeach/checkFileExists.shtml',
   				data: {"attachmentPath":attachmentPath},
   				dataType: 'json',
   				success: function(data){
   					if(data == null || data.code != 200){
   				    	commUtil.alertError(data.msg);
   				  	}else{
   				  		location.href="${pageContext.request.contextPath}/imPeach/downLoadAttachment.shtml?fileName="+attachmentName+"&filePath="+attachmentPath;
   				  	}
   				},
   				error: function(e){
   				 	commUtil.alertError("系统异常请稍后再试！");
   				}
   			});
   	   }); 
        
        
        if (status!='5' && status!='6' && status!='7') {
        var limitMemberAction2=$("#limitMemberAction").val(); 
    	if(limitMemberAction2!='' && limitMemberAction2!=null){
    		$("input[name='limitMemberAction']").attr("disabled",true);
    		$("input[name='endlimitMemberActionS']").attr("disabled",true);
    		
    		var str1= new Array(); //定义一数组 
    		str1=limitMemberAction2.split(","); //字符分割 
    		for (i=0;i<str1.length;i++) 
    		{
    			  var j=str1[i]-1;
    			  $("input[name='limitMemberAction']")[j].checked =true; 
    			   if (status=='4') {
    			   $("input[name='endlimitMemberActionS']")[j].checked =true; 	
					
				 }
	

    		} 
    	  }
			
		}
        
        
    });


    //法务提交审核
    function submitAudit1() {
        var status = $('input[name="status"]:checked').val();
        if (!status){
            $.ligerDialog.warn("请选择判断结果");
            return false;
        }

        // 驳回原因
        var fwRejectReason = '';
        if (status == '5') {
        	fwRejectReason = $('#fwRejectReason').val();
        	if (!fwRejectReason) {
        		$.ligerDialog.warn("请填写驳回原因");
                return false;
        	}
        }
        
        var impeachMemberId = $('#impeachMemberId').val();
        
        var fwInnerRemarks=$('#fwInnerRemarks').val();

        $.ajax({
            url : "${pageContext.request.contextPath}/imPeach/fwupdateStatus.shtml?",
            type : 'POST',
            dataType : 'json',
            data : {
            	id : impeachMemberId,
            	status: status,
            	fwRejectReason: fwRejectReason,
            	fwInnerRemarks:fwInnerRemarks
            	},
            cache : false,
            async : false,
            timeout : 30000,
            success : function(data) {
                if (data.code == '200') {
                    $.ligerDialog.success("操作成功",function() {
                		parent.$("#searchbtn").click();
    					frameElement.dialog.close();
					});
                } else {
                    $.ligerDialog.error(data.message || '系统错误！');
                }
             
            },
            error : function() {
                $.ligerDialog.error('操作超时，请稍后再试！');
            }
        });
    }
    
    
    //专员提交审核
    function submitAudit() {
    	var impeachMemberId = $('#impeachMemberId').val();
        var status1 = $('input[name="status1"]:checked').val();
        var status156=$("#status").val();
        var source=$("#source").val();
        var filePathList=$("#filePathList").val(); 
        var limitMemberAction=''; 
        var needLimit='';
        var caseCloseDesc='';
        var rejectReason='';
        var caseCloseDescS='';
        if (source=='1' && (status156=='5' || status156=='6')) {
        	 needLimit  = $('input[name="needLimit"]:checked').val();
         	if (!needLimit){
                 $.ligerDialog.warn("请选择是否需限制用户行为");
                 return false;
         	}else if (needLimit=='1') {
         	      $("[name='limitMemberAction']").each(function () {
    			    	if (this.checked){
    			    		if (''==limitMemberAction) {
    			    			limitMemberAction+=this.value;
 						}else {
    			    		   limitMemberAction +=","+this.value;
 							
 						}
    			        }
    			    });
         		 if (!limitMemberAction) { 
         			 $.ligerDialog.warn("请限制用户行为选择");
                      return false;
 				}
          
         	caseCloseDesc=$('#caseCloseDesc').val();
         	if (!caseCloseDesc) {
    			 $.ligerDialog.warn("请选择结案说明");
                 return false;
 			}	
         	
 		   }else if (needLimit=='0') {
 			   caseCloseDescS=$('#caseCloseDescS').val();
 	        	if (!caseCloseDescS) {
 	   			 $.ligerDialog.warn("请选择结案说明");
 	                return false;
 				}
 		    }
        	 
	  }else {
        if (!status1){
            $.ligerDialog.warn("请选择判断结果");
            return false;
        }
        if (status1=='2') {
            needLimit  = $('input[name="needLimit"]:checked').val();
        	if (!needLimit){
                $.ligerDialog.warn("请选择是否需限制用户行为");
                return false;
        	}else if (needLimit=='1') {
        	      $("[name='limitMemberAction']").each(function () {
   			    	if (this.checked){
   			    		if (''==limitMemberAction) {
   			    			limitMemberAction+=this.value;
						}else {
   			    		   limitMemberAction +=","+this.value;
							
						}
   			        }
   			    });
        		 if (!limitMemberAction) { 
        			 $.ligerDialog.warn("请限制用户行为选择");
                     return false;
				}
         
        	caseCloseDesc=$('#caseCloseDesc').val();
        	if (!caseCloseDesc) {
   			 $.ligerDialog.warn("请选择结案说明");
                return false;
			}	
        	
		   }else if (needLimit=='0') {
			   caseCloseDescS=$('#caseCloseDescS').val();
	        	if (!caseCloseDescS) {
	   			 $.ligerDialog.warn("请选择结案说明");
	                return false;
				}
		}
        	
		}else if (status1=='3') {
			rejectReason=$('#rejectReason').val();
			if (!rejectReason) {
	   			 $.ligerDialog.warn("请选择驳回原因");
	              return false;
			 }
		}	
	 }
        
        /* if (needLimit=='1' && filePathList=='') {
        	$.ligerDialog.warn("请上传补充举证材料");
            return false;
		} */
        
        var commissionerInnerRemarks=$('#commissionerInnerRemarks').val();
       
        $.ajax({
            url : "${pageContext.request.contextPath}/imPeach/kfupdateStatus.shtml?",
            type : 'POST',
            dataType : 'json',
            data : {
            	id : impeachMemberId,
            	needLimit: needLimit,
            	status1: status1,
            	limitMemberAction: limitMemberAction,
            	caseCloseDesc: caseCloseDesc,
            	caseCloseDescS: caseCloseDescS,
            	rejectReason: rejectReason,
            	commissionerInnerRemarks: commissionerInnerRemarks,
            	status156:status156,
            	filePathList:filePathList
            	
            	},
            cache : false,
            async : false,
            timeout : 30000,
            success : function(data) {
                if (data.code == '200') {
                    $.ligerDialog.success("操作成功",function() {
                		parent.$("#searchbtn").click();
    					frameElement.dialog.close();
					});
                } else {
                    $.ligerDialog.error(data.message || '系统错误！');
                }
            },
            error : function() {
                $.ligerDialog.error('操作超时，请稍后再试！');
            }
        });
    }
    
    
    
     //平台结案
    function submitAudit2() {
        var status2 = $('input[name="status2"]:checked').val();
        var endlimitMemberActionS='';
        var endcaseCloseDesc='';
        if (!status2){
            $.ligerDialog.warn("请选择判断结果");
            return false;
        }

        // 结案驳回原因
        var endRejectReason = '';
        if (status2 == '6') {
        	endRejectReason = $('#endRejectReason').val();
        	if (!endRejectReason) {
        		$.ligerDialog.warn("请填写驳回原因");
                return false;
        	}
        }
        
        if (status2 == '7') {
        	 $("[name='endlimitMemberActionS']").each(function () {
			    	if (this.checked){
			    		if (''==endlimitMemberActionS) {
			    			endlimitMemberActionS+=this.value;
						}else {
							endlimitMemberActionS +=","+this.value;
							
						}
			        }
			    });
     		 if (!endlimitMemberActionS) { 
     			 $.ligerDialog.warn("请限制用户行为选择");
                  return false;
				}
      
     		endcaseCloseDesc=$('#endcaseCloseDesc').val();
     	if (!endcaseCloseDesc) {
			 $.ligerDialog.warn("请选择结案说明");
             return false;
			}
        }
        
        var impeachMemberId = $('#impeachMemberId').val();
        
        var endInnerRemarks =$('#endInnerRemarks').val();

        $.ajax({
            url : "${pageContext.request.contextPath}/imPeach/endupdateStatus.shtml?",
            type : 'POST',
            dataType : 'json',
            data : {
            	id : impeachMemberId,
            	status2: status2,
            	endRejectReason: endRejectReason,
            	endInnerRemarks: endInnerRemarks,
            	endlimitMemberActionS: endlimitMemberActionS,
            	endcaseCloseDesc:endcaseCloseDesc
            	},
            cache : false,
            async : false,
            timeout : 30000,
            success : function(data) {
                if (data.code == '200') {
                    $.ligerDialog.success("操作成功",function() {
                		parent.$("#searchbtn").click();
    					frameElement.dialog.close();
					});
                } else {
                    $.ligerDialog.error(data.message || '系统错误！');
                }
             
            },
            error : function() {
                $.ligerDialog.error('操作超时，请稍后再试！');
            }
        });
    }
    
    
    /* function need(value){
    	if (value=='1') {
    		document.getElementById("caseCloseDesc2").style.display="none";
    		document.getElementById("caseCloseDesc1").style.display='';
    	}else if (value=='0') {
    		document.getElementById("caseCloseDesc1").style.display="none";
    		document.getElementById("caseCloseDesc2").style.display='';
    	}
    	
    }  */
    
    
    function fwStatus(value){
    	if (value=='5') {
    		$("#fwInnerRemarks").val('');
    		document.getElementById("fwInnerRemarks1").style.display="none";
    		document.getElementById("fwRejectReason1").style.display='';
    	}else if (value=='4') {
    		$("#fwRejectReason").val('');
    		document.getElementById("fwRejectReason1").style.display="none";
    		document.getElementById("fwInnerRemarks1").style.display='';
    	}
    	
    }
    
    
    function endStatus(value){
    	if (value=='6') {
    		document.getElementById("limitMemberActionS").style.display="none";
    		document.getElementById("endcaseCloseDesc1").style.display="none";
    		document.getElementById("endInnerRemarks1").style.display="none";
    		document.getElementById("endRejectReason1").style.display='';
    		$("#endInnerRemarks"),val("");
    	}else if (value=='7') {
    		document.getElementById("limitMemberActionS").style.display='';
    		document.getElementById("endcaseCloseDesc1").style.display='';
    		document.getElementById("endInnerRemarks1").style.display='';
    		document.getElementById("endRejectReason1").style.display="none";
    		$("#endRejectReason"),val("");
    	}
    	
    }
    
    
    function endStatus(value){
    	if (value=='6') {
    		document.getElementById("limitMemberActionS").style.display="none";
    		document.getElementById("endcaseCloseDesc1").style.display="none";
    		document.getElementById("endInnerRemarks1").style.display="none";
    		document.getElementById("endRejectReason1").style.display='';
    		$("#endInnerRemarks"),val("");
    	}else if (value=='7') {
    		document.getElementById("limitMemberActionS").style.display='';
    		document.getElementById("endcaseCloseDesc1").style.display='';
    		document.getElementById("endInnerRemarks1").style.display='';
    		document.getElementById("endRejectReason1").style.display="none";
    		$("#endRejectReason"),val("");
    	}
    	
    }
  
  
    
    function subOrderCustomscuont(id,state){
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width()-50,
			title: "查看子订单",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/imPeach/subOrderListdata.shtml?memberId="+id+"&state=" + state,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});

	}
    
    function customerServiceOrderCount(id,state){
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width()-50,
			title: "查看售后单",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/imPeach/subOrderListdata.shtml?memberId="+id+"&state=" + state,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});

	}
 
 function interventionOrderCount(id,state){
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width()-50,
			title: "查看介入单",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/imPeach/subOrderListdata.shtml?memberId="+id+"&state=" + state,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});

	}
 
 
 function appealOrderCount(id,state){
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width()-50,
			title: "查看投诉单",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/imPeach/subOrderListdata.shtml?memberId="+id+"&state=" + state,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});

	}
 
 
 function memberId(id,state){
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width()-50,
			title: "查看收货地址",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/imPeach/subOrderListdata.shtml?memberId="+id+"&state=" + state,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});

	}
 
 
 function mchtInfoid(id) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width() - 40,
			title: "商家基础资料",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/mchtBaseInfoEdit.shtml?mchtId=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
 
 
 function subOrdersId(id) {
		$.ligerDialog.open({
	 		height: $(window).height(),
			width: $(window).width()-50,
			title: "子订单详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/order/sub/detail.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
 

 
 var fileS=[];
 function ajaxFileUpload(obj) {
 	/* var oFile = obj.files[0]; */
 	if (obj.files.length === 0) {
 			return;
 	 }
     var file = obj.files[0];
     var fileName = file.name;
     var rFilter = ["jpg","bmp","png","gif","JPG","BMP","PNG","GIF","mp3","wav","wma","ogg","ape","acc","MP3","WAV","WMA","OGG","APE","ACC","avi","mp4","mov","rm","wma","mkv","rmvb","AVI","MP4","MOV","RM","WMA","MKV","RMVB","doc","docx","xls","xlsx","ppt","pptx","pdf","rar","zip","DOC","DOCX","XLS","XLSX","PPT","PPTX","PDF","RAR","ZIP"];
   /*   var filesize = file.size;
 	var fileName = file.name;
 	$("#filePathName").val(fileName);
 	$("#filePathList").text(fileName);
     if (filesize>1024*1024*300) {
     	commUtil.alertError("视频大小不超过300M");
     	return false;
 	} */
 	var suffix = file.name.substring(file.name.lastIndexOf(".")+1);
 	if($.inArray(suffix,rFilter)==-1){
 		commUtil.alertError("文件格式不正确！");
 		return false;
 	}
 	if(suffix=="MP4"||suffix=="mp4"||suffix=="avi"||suffix=="AVI"){
 		if(file.size>100*1024*1024){
 			commUtil.alertError("视频大小不能超过100M");
 	        return false;
 	    } 
 	}
 	var totalSize=0;
 	 for(var i=0;i<fileS.length;i++){
 		totalSize += fileS[i].size*1;
 	}
 	
 	if(Number(totalSize)+Number(file.size)>400*1024*1024){
 		commUtil.alertError("文件总大小不能超过400M");
 		return false;
 	}; 
 	fileS.push(file);
 	
     var reader = new FileReader();
     $("#uploading").val(1);
     reader.onload = function(e) {
     	$.ajaxFileUpload({
     		url: '${pageContext.request.contextPath}/service/common/fileUpload.shtml?fileType=8',
     		secureuri: false,
     		fileElementId: "uploadFile",
     		dataType: 'json',
     		success: function(result, status) {
     			if(result.RESULT_CODE == 0){
     			   var filePath = result.FILE_PATH;
     			   var html=[];
     			    html.push("<li>");
     				html.push("<p>");
     				html.push(fileName);
     			    html.push("&nbsp;"+"已上传"+"&nbsp;&nbsp;&nbsp;&nbsp;"+"<a href=\"javascript:void(0);\" onclick='delfilePath();' style='color: red;'>删除</a>"+"<br><br>");
     				html.push("</p>");
     				html.push("</li>");
     			   $("#filePath1").append(html.join(""));
     			   
     			   var html1=[];
     			   html1.push("<li>");
     			   html1.push(fileName);
     			   html1.push(filePath);
     			   html1.push("</li>");
     			   $("#filePath2").append(html1.join(""));
     			   
     			   var pictures = [];
     			   var lis = $("#filePath2").find("li");
     			   lis.each(function(index, item) {
     	    			pictures.push($(this).text());
     	    		});

     			   $("#filePathList").val(pictures);
     			       			   			  		   	
 	               $("#uploading").val(0);
    			  	}else{
    			  		alert(result.RESULT_MESSAGE);
    			  	}
     		},
     		error: function(e) {
     			alert("服务异常");
     		}
     	});
     };
     reader.readAsDataURL(file);  
 }
 
 function delfilePath() {
	   if ($("#filePath1 li").length>1) {
	       $("#filePath1 li:last").prev().remove();
	   }else if ($("#filePath1 li").length==1) {
		    $("#filePath1 li").remove(); 	
	  }  
}
</script>

<html>
<body>
<form method="post" id="form1" name="form1">
 <input type="hidden" id="impeachMemberId" value="${impeachMemberCustom.id}">
 <input type="hidden" id="limitMemberAction" value="${impeachMemberCustom.limitMemberAction}">
 <input type="hidden" id="status" value="${impeachMemberCustom.status}">
 <input type="hidden" id="source" value="${impeachMemberCustom.source}">
 <input type="hidden" id="filePathList" name="filePathList" value=""/>
 <input type="hidden" id="uploading" value="0"/>
 <div class="title-top">
		<div class="table-title">
			<span>创建者信息</span>
		</div>
		 <br>
	  <c:if test="${impeachMemberCustom.source eq '0'}">
		<table class="gridtable marT10">
			<tr>
				<td class="title" style="width: 80px;">商家序号</td>
				<td class="title" style="width: 80px;">商家名称</td>
				<td class="title" style="width: 80px;">退货率</td>
				<td class="title" style="width: 80px;">换货率</td>
				<td class="title" style="width: 80px;">退款率</td>
				<td class="title" style="width: 80px;">商品好评率</td>
			</tr>		
			<tr>
				<td align="center">${mchtInfoCustom.mchtCode}</td>
				<td align="center"><a href="javascript:mchtInfoid(${mchtInfoCustom.id});">${mchtInfoCustom.companyName}</a></td>
				<c:if test="${not empty mchtStatisticalInfos.returnRate90Days}">
				<td align="center">${mchtStatisticalInfos.returnRate90Days}%</td>
				</c:if>
				<c:if test="${empty mchtStatisticalInfos.returnRate90Days}">
				<td align="center">0.0%</td>
				</c:if>
				<c:if test="${not empty mchtInfoCustom.customerServiceOrderRateC}">
				<td align="center">${mchtInfoCustom.customerServiceOrderRateC}%</td>
				</c:if>
				<c:if test="${empty mchtInfoCustom.customerServiceOrderRateC}">
				<td align="center">0.0%</td>
				</c:if>
				<c:if test="${not empty mchtInfoCustom.customerServiceOrderRateA}">
				<td align="center">${mchtInfoCustom.customerServiceOrderRateA}%</td>
				</c:if>
				<c:if test="${empty mchtInfoCustom.customerServiceOrderRateA}">
				<td align="center">0.0%</td>
				</c:if>
				<c:if test="${not empty mchtStatisticalInfos.productApplauseRate}">
				<td align="center">${mchtStatisticalInfos.productApplauseRate}%</td>
				</c:if>
				<c:if test="${empty mchtStatisticalInfos.productApplauseRate}">
				<td align="center">0.0%</td>
				</c:if>
			</tr>	
		</table>
		</c:if>
	  <c:if test="${impeachMemberCustom.source eq '1'}">
		<table class="gridtable marT10">
			<tr>
				<td class="title" style="width: 80px;">工号</td>
				<td class="title" style="width: 80px;">姓名</td>
				<td class="title" style="width: 80px;">部门</td>
				<td class="title" style="width: 80px;">联系电话</td>
			</tr>
			<tr>
				<td align="center">${StaffID}</td>
				<td align="center">${Name}</td>
				<td align="center">${OrgName}</td>
				<td align="center">${MobilePhone}</td>
			</tr>		
                 
		</table>
	</c:if>
	</div>

    <br>
    <div><span class="table-title">举报信息</span></div>
    <br>
    <table class="gridtable marT10 status-table">
        <tr>
            <td class="title title-width" style="width:20%;">项目</td>
            <td colspan="7" align="center" class="l-table-edit-td">
                                                               内容
            </td>
        </tr>
        <tr>
            <td class="title title-width">举报类型</td>
            <td colspan="7" align="left" class="l-table-edit-td">
                ${impeachMemberCustom.typeDesc}
            </td>
        </tr>
        <tr>
            <td class="title title-width">举报场景</td>
            <td colspan="7" align="left" class="l-table-edit-td"> 
                              <c:if test="${impeachMemberCustom.type=='1'}">   
                              ${impeachMemberCustom.scenedesc1}
                              </c:if>
                              <c:if test="${impeachMemberCustom.type=='2'}">   
                              ${impeachMemberCustom.scenedesc2}
                              </c:if> 
                              <c:if test="${impeachMemberCustom.type=='3'}">   
                              ${impeachMemberCustom.scenedesc3}
                              </c:if>          
            </td>
        </tr>
        <tr>
            <td class="title title-width">相关订单</td>
            <td colspan="7" align="left" class="l-table-edit-td">
                <c:forEach var="subOrders" items="${subOrders}">
		                  <a href="javascript:subOrdersId(${subOrders.id});">${subOrders.subOrderCode}</a>&nbsp<span style="color: red;">(会员ID:${subOrders.memberId})</span><br>
	            </c:forEach> 
            </td>
        </tr>
        <tr>
            <td class="title title-width">联系人信息</td>
            <td colspan="7" align="left" class="l-table-edit-td">
                 <c:if test="${impeachMemberCustom.source eq '1'}">${Name}</c:if>
                 <c:if test="${impeachMemberCustom.source eq '0'}">${impeachMemberCustom.name}</c:if>
            </td>
        </tr>
        <tr>
            <td class="title title-width">联系人电话</td>
            <td colspan="7" align="left" class="l-table-edit-td">
                <c:if test="${impeachMemberCustom.source eq '1'}">${MobilePhone}</c:if>
                 <c:if test="${impeachMemberCustom.source eq '0'}">${impeachMemberCustom.mobile}</c:if>
            </td>
        </tr>
        <tr>
            <td class="title title-width">举报说明</td>
            <td colspan="7" align="left" class="l-table-edit-td">
                ${impeachMemberCustom.description}
            </td>
        </tr>
        <tr>
            <td class="title title-width">举报凭证</td>
            <td colspan="7" align="left" class="l-table-edit-td">
                <c:if test="${not empty impeachMemberProofs}">
				        <c:forEach var="impeachMemberProofs" items="${impeachMemberProofs}">
						${impeachMemberProofs.fileName}&nbsp;&nbsp;<a href="javascript:;" id="${impeachMemberProofs.id}" name="downLoadimpeachMemberProof" attachmentname="${impeachMemberProofs.fileName}" attachmentpath="${impeachMemberProofs.filePath}">【下载】<c:if test="${impeachMemberProofs.isAdd eq '1'}"><label style="color: red;">[新增]</label></c:if></a>
						</c:forEach>
				</c:if>
            </td>
        </tr>
    </table>
    
    <br>
    <div class="title-top">
		<div class="table-title">
			<span>相关会员信息</span>
		</div>
		<br>
		<table class="gridtable marT10">
			<tr>
				<td class="title" style="width: 80px;">会员ID</td>
				<td class="title" style="width: 80px;">订单</td>
				<td class="title" style="width: 80px;">售后单</td>
				<td class="title" style="width: 80px;">介入单</td>
				<td class="title" style="width: 80px;">投诉单</td>
				<td class="title" style="width: 80px;">收货地址</td>
			</tr>
			<c:forEach var="memberInfoCustoms" items="${memberInfoCustoms}">
				 <tr>
					<td align="center">${memberInfoCustoms.id}</td>
					<c:if test="${memberInfoCustoms.subOrderCustomscuont == '0'}"><td align="center">0</td></c:if>
					<c:if test="${memberInfoCustoms.subOrderCustomscuont > '0'}"><td align="center"><a href="javascript:subOrderCustomscuont(${memberInfoCustoms.id},'1');">${memberInfoCustoms.subOrderCustomscuont}&nbsp(查看)</a></td></c:if>
					<c:if test="${memberInfoCustoms.customerServiceOrderCount == '0'}"><td align="center">0</td></c:if>
					<c:if test="${memberInfoCustoms.customerServiceOrderCount > '0'}"><td align="center"><a href="javascript:customerServiceOrderCount(${memberInfoCustoms.id},'2');">${memberInfoCustoms.customerServiceOrderCount}&nbsp(查看)</a></td></c:if>
					<c:if test="${memberInfoCustoms.interventionOrderCount == '0'}"><td align="center">0</td></c:if>
					<c:if test="${memberInfoCustoms.interventionOrderCount > '0'}"><td align="center"><a href="javascript:interventionOrderCount(${memberInfoCustoms.id},'3');">${memberInfoCustoms.interventionOrderCount}&nbsp(查看)</a></td></c:if>
					<c:if test="${memberInfoCustoms.appealOrderCount == '0'}"><td align="center">0</td></c:if>
					<c:if test="${memberInfoCustoms.appealOrderCount > '0'}"><td align="center"><a href="javascript:appealOrderCount(${memberInfoCustoms.id},'4');">${memberInfoCustoms.appealOrderCount}&nbsp(查看)</a></td></c:if>
					<td align="center"><a href="javascript:memberId(${memberInfoCustoms.id},'5');">查看</a></td>
				</tr>
			</c:forEach>	   
		</table>
	</div>
        <c:if test="${impeachMemberCustom.status eq '1' || impeachMemberCustom.status eq '2' || impeachMemberCustom.status eq '3' || impeachMemberCustom.status eq '5' || impeachMemberCustom.status eq '6' || impeachMemberCustom.status eq '4'}">
        <br>
        <div><span class="table-title">专员审核</span>
        </div>
        <br>
        <table class="gridtable">
           <c:if test="${impeachMemberCustom.source eq '0'}">
            <tr>
                <td colspan="1" class="title"><span class="required">*</span>判断结果</td>
                <td colspan="3" align="left" class="l-table-edit-td">
                    <input type="radio" name="status1" value="3" <c:if test="${impeachMemberCustom.status eq '3'}">checked</c:if>>驳回（至商家）
                    &nbsp; &nbsp;
                    <input type="radio" name="status1" value="2"  <c:if test="${impeachMemberCustom.status eq '2' || impeachMemberCustom.status eq '4'}">checked</c:if>>专员通过
                </td>
            </tr>
            </c:if>
            <c:if test="${impeachMemberCustom.status ne '3'}">           
            <tr id="needLimit">
                <td colspan="1" class="title"><span class="required">*</span>是否限制用户行为</td>
                <td colspan="3" align="left" class="l-table-edit-td">
                    <input type="radio" name="needLimit" value="1" <c:if test="${(impeachMemberCustom.status eq '2' or impeachMemberCustom.status eq '4' or impeachMemberCustom.status eq '2') and impeachMemberCustom.needLimit eq '1'}">checked</c:if>>是
                    &nbsp; &nbsp;
                    <input type="radio" name="needLimit" value="0" <c:if test="${(impeachMemberCustom.status eq '2' or impeachMemberCustom.status eq '4') and impeachMemberCustom.needLimit eq '0'}">checked</c:if>>否
                </td>
            </tr>
            <tr id="limitMemberAction1">
                <td colspan="1" class="title"><span class="required">*</span>限制用户行为选择</td>
                <td colspan="3" align="left" class="l-table-edit-td">
                    <input type="checkbox" name="limitMemberAction" value="1">限制评价
                    &nbsp; &nbsp;
                    <input type="checkbox" name="limitMemberAction" value="2">限制下单
                    &nbsp; &nbsp;
                    <input type="checkbox" name="limitMemberAction" value="3">限制登入
                </td>
            </tr>
            </c:if>
           <c:if test="${impeachMemberCustom.source eq '1' and (impeachMemberCustom.status eq '1' or impeachMemberCustom.status eq '5' or impeachMemberCustom.status eq '6')}">
             <tr id="proof">
				<td colspan="1" class="title">补充举证材料</td>
				  <td colspan="7" align="left" class="l-table-edit-td">
					<input style="position:absolute; opacity:0;" type="file" id="uploadFile" name="uploadFile" onchange="ajaxFileUpload(this);" />
					<input type="button" style="width: 80px;height: 25px;" value="上传凭证"><span style="color: #C0C0C0;font-size:13px">(请上传举报凭证，可上传图片、压缩包、视频文件。视频大小不超过100M)</span>
					<br>
					<br>
					<ul id=filePath1 style="color: red;"></ul>
					<ul id=filePath2 style="display: none;"></ul>
				</td>
			</tr>
            </c:if>
            <%-- <c:if test="${impeachMemberCustom.source eq '1' and impeachMemberCustom.status ne '1'  and impeachMemberCustom.status ne '5' and impeachMemberCustom.status ne '6'}">
			<tr>
                <td colspan="1" class="title"><span class="required">*</span>补充举证材料</td>
                <td colspan="7" align="left" class="l-table-edit-td">
				    <c:if test="${not empty impeachMemberProofs}">
				        <c:forEach var="impeachMemberProofs" items="${impeachMemberProofs}">
						${impeachMemberProofs.fileName}&nbsp;&nbsp;<a href="javascript:;" id="download" attachmentname="${impeachMemberProofs.fileName}" attachmentpath="${impeachMemberProofs.filePath}">【下载】</a>
						</c:forEach>
					</c:if>
                </td>
            </tr>
			 </c:if> --%>
            <c:if test="${impeachMemberCustom.status ne '3'}">       
            <tr id="caseCloseDesc1">
                <td colspan="1" class="title"><span class="required">*</span>结案说明</td>
                <td colspan="7" align="left" class="l-table-edit-td">              
               		<select id="caseCloseDesc" name="caseCloseDesc" style="width: 610px;">
               			<option value="">请选择</option>
               			<c:forEach var="caseCloselist" items="${caseCloselist}"> 
               			    <c:if test="${caseCloselist.statusValue ne '4'}">        			     
							<option value="${caseCloselist.statusValue}" <c:if test="${(impeachMemberCustom.status eq '2' or impeachMemberCustom.status eq '4') and caseCloselist.statusValue==impeachMemberCustom.caseCloseDesc}">selected</c:if>>${caseCloselist.statusDesc}</option>
							</c:if>
						</c:forEach>
               		</select>
                </td>
            </tr>
            <tr id="caseCloseDesc2" style="display:none;">
               <td colspan="1" class="title"><span class="required">*</span>结案说明</td>
                <td colspan="7" align="left" class="l-table-edit-td">
               		<select id="caseCloseDescS" name="caseCloseDescS" style="width: 610px;">
               			<option value="">请选择</option>
               			<c:forEach var="caseCloselist" items="${caseCloselist}">
               			    <c:if test="${caseCloselist.statusValue=='4'}">
							<option value="${caseCloselist.statusValue}" <c:if test="${(impeachMemberCustom.status eq '2' or impeachMemberCustom.status eq '4') and caseCloselist.statusValue==impeachMemberCustom.caseCloseDesc}">selected</c:if>>${caseCloselist.statusDesc}</option>
							</c:if>	
						</c:forEach>
               		</select>
               </td>
	        </tr>
	         </c:if>
            <c:if test="${impeachMemberCustom.source eq '0' && (impeachMemberCustom.status eq '5' || impeachMemberCustom.status eq '6' || impeachMemberCustom.status eq '3' || impeachMemberCustom.status eq '1')}">
            <tr id="rejectReason1">
                <td colspan="1" class="title"><span class="required">*</span>驳回原因</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                   <select id="rejectReason" name="rejectReason" style="width: 300px;">
               			<option value="">请选择</option>
               			<c:forEach var="rejectReasonList" items="${rejectReasonList}">         			     
							<option value="${rejectReasonList.statusValue}" <c:if test="${rejectReasonList.statusValue==impeachMemberCustom.rejectReason}">selected</c:if>>${rejectReasonList.statusDesc}</option>
						</c:forEach>
               		</select>
                </td>
            </tr>
            </c:if>
            <tr>
                <td colspan="1" class="title">内部备注</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                <textarea rows="7" id="commissionerInnerRemarks" name="commissionerInnerRemarks" cols="50" class="text">${impeachMemberCustom.commissionerInnerRemarks}</textarea>
                </td>
            </tr>
            <c:if test="${(impeachMemberCustom.status eq '5' || impeachMemberCustom.status eq '6' || impeachMemberCustom.status eq '1') and imPeachType ne '6'}">       
            <tr>
                <td colspan="1" class="title"><span class="required">*</span>操作</td>
                <td align="left" class="l-table-edit-td">
                    <button type="button" class="l-button l-button-submit" onclick="submitAudit()">提交</button>
                    <button style="margin-left: 20px;" class="l-button" type="button" onclick="frameElement.dialog.close()">取消</button>
                </td>
            </tr>
            </c:if>
        </table>
      </c:if>
      
      <c:if test="${impeachMemberCustom.status eq '2' || impeachMemberCustom.status eq '4'}">
        <br>
        <div><span class="table-title">法务复审</span>
        </div>
        <br>
        <table class="gridtable">
            <tr>
                <td colspan="1" class="title"><span class="required">*</span>判断结果</td>
                <td colspan="3" align="left" class="l-table-edit-td">
                    <input type="radio" name="status" value="5" onchange="fwStatus(5)">复审驳回（至专员）
                    &nbsp; &nbsp;
                    <input type="radio" name="status" value="4" onchange="fwStatus(4)"  <c:if test="${impeachMemberCustom.status eq '4'}">checked</c:if>>复审通过
                </td>
            </tr>
      
            <tr id="fwInnerRemarks1">
                <td colspan="1" class="title">内部备注</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                <textarea rows="7" id="fwInnerRemarks" name="fwInnerRemarks" cols="50" class="text">${impeachMemberCustom.fwInnerRemarks}</textarea>
                </td>
            </tr>
             <c:if test="${impeachMemberCustom.status eq '6' || impeachMemberCustom.status eq '2'}">
              <tr id="fwRejectReason1">
                <td colspan="1" class="title"><span class="required">*</span>驳回原因</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                <textarea rows="7" id="fwRejectReason" name="fwRejectReason" cols="50" class="text"></textarea>
                </td>
            </tr>
            </c:if>
            <c:if test="${impeachMemberCustom.status ne '4' and imPeachType ne '6'}">
            <tr>
                <td colspan="1" class="title"><span class="required">*</span>操作</td>
                <td align="left" class="l-table-edit-td">
                    <button type="button" class="l-button l-button-submit" onclick="submitAudit1()">提交</button>
                    <button style="margin-left: 20px;" class="l-button" type="button" onclick="frameElement.dialog.close()">取消</button>
                </td>
            </tr>
            </c:if>
        </table>
        </c:if>
  
        <c:if test="${impeachMemberCustom.status eq '4'}">
        <br>
        <div><span class="table-title">平台结案</span>
        </div>
        <br>
        <table class="gridtable">
            <tr>
                <td colspan="1" class="title"><span class="required">*</span>判断结果</td>
                <td colspan="3" align="left" class="l-table-edit-td">
                    <input type="radio" name="status2" value="7" onchange="endStatus(7)">结案通过
                    &nbsp; &nbsp;
                    <input type="radio" name="status2" value="6" onchange="endStatus(6)">结案驳回
                </td>
            </tr>
            <tr id="limitMemberActionS">
                <td colspan="1" class="title"><span class="required">*</span>限制用户行为选择</td>
                <td colspan="3" align="left" class="l-table-edit-td">
                    <input type="checkbox" name="endlimitMemberActionS" value="1" disabled="true">限制评价
                    &nbsp; &nbsp;
                    <input type="checkbox" name="endlimitMemberActionS" value="2" disabled="true">限制下单
                    &nbsp; &nbsp;
                    <input type="checkbox" name="endlimitMemberActionS" value="3" disabled="true">限制登入
                </td>
            </tr>
            <tr id="endcaseCloseDesc1">
                <td colspan="1" class="title"><span class="required">*</span>结案说明</td>
                <td colspan="7" align="left" class="l-table-edit-td">              
               		<select id="endcaseCloseDesc" name="endcaseCloseDesc" style="width: 610px;" disabled="true">
               			<option value="">请选择</option>
               			<c:forEach var="caseCloselist" items="${caseCloselist}">             			        			     
							<option value="${caseCloselist.statusValue}" <c:if test="${caseCloselist.statusValue==impeachMemberCustom.caseCloseDesc}">selected</c:if>>${caseCloselist.statusDesc}</option>
						</c:forEach>
               		</select>
                </td>
            </tr>
            <tr id="endInnerRemarks1">
                <td colspan="1" class="title">内部备注</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                <textarea rows="7" id="endInnerRemarks" name="endInnerRemarks" cols="50" class="text"></textarea>
                </td>
            </tr>
              <tr id="endRejectReason1">
                <td colspan="1" class="title"><span class="required">*</span>结案驳回</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                <textarea rows="7" id="endRejectReason" name="endRejectReason" cols="50" class="text"></textarea>
                </td>
            </tr>
            <c:if test="${imPeachType ne '6'}">
            <tr>
                <td colspan="1" class="title"><span class="required">*</span>操作</td>
                <td align="left" class="l-table-edit-td">
                    <button type="button" class="l-button l-button-submit" onclick="submitAudit2()">提交</button>
                    <button style="margin-left: 20px;" class="l-button" type="button" onclick="frameElement.dialog.close()">取消</button>
                </td>
            </tr>
            </c:if>
        </table>
        </c:if>
 
        <br>
        <div class="title-top">
		<div class="table-title">
			<span>平台处理记录</span>
		</div>
		<br>
		<table class="gridtable marT10">
           	<tr>
               <td width="30%" class="title">时间</td> 
               <td width="30%" class="title">状态</td>
               <td width="40%" class="title">内容</td>
			</tr>
	        <c:forEach var="impeachHandleLogs" items="${impeachHandleLogs}">
		        <tr>
		        	<td align="center">
	               		<fmt:formatDate value="${impeachHandleLogs.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
	               	</td>
	               	<c:if test="${impeachHandleLogs.status eq '1'}">
	               	   <td align="center">创建举报</td>
	               	</c:if>
	               	<c:if test="${impeachHandleLogs.status eq '2'}">
	               	   <td align="center">待初审</td>
	               	</c:if>
	               	<c:if test="${impeachHandleLogs.status eq '3'}">
	               	   <td align="center">驳回</td>
	               	</c:if>
	               	<c:if test="${impeachHandleLogs.status eq '4'}">
	               	   <td align="center">专员通过</td>
	               	</c:if>
	               	<c:if test="${impeachHandleLogs.status eq '5'}">
	               	   <td align="center">复审通过</td>
	               	</c:if>
	               	<c:if test="${impeachHandleLogs.status eq '6'}">
	               	   <td align="center">复审驳回</td>
	               	</c:if>
	               	<c:if test="${impeachHandleLogs.status eq '7'}">
	               	   <td align="center">结案通过</td>
	               	</c:if>
	               	<c:if test="${impeachHandleLogs.status eq '8'}">
	               	   <td align="center">结案驳回</td>
	               	</c:if>
	               	<td align="center">${impeachHandleLogs.content}</td>
		        </tr>
	        </c:forEach>
        </table>
	</div>
</form>
</body>
</html>
