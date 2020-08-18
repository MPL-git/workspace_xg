 var editModel={
         initEditPage:function(){
            $.metadata.setType("attr", "validate");
            var v = $("form").validate({
                debug: true,
                errorPlacement: function (lable, element)
                {
                   /* if (element.hasClass("l-textarea"))
                    {
                        element.ligerTip({ content: lable.html(), target: element[0] }); 
                    }
                    else if (element.hasClass("l-text-field"))
                    {
                        element.parent().ligerTip({ content: lable.html(), target: element[0] });
                    }
                    else
                    {
                        lable.appendTo(element.parents("td:first").next("td"));
                    }*/
                     element.ligerTip({ content: lable.html(), target: element[0] });
                },
                success: function (lable)
                {
                    lable.ligerHideTip();
                    lable.remove();
                },
                submitHandler: function ()
                {
                    $("form .l-text,.l-textarea").ligerHideTip();
                   // var dform=$("#postForm");
                    commUtil.toDoAjax( $("form").attr("action"), $("form").serializeArray());
                }
            });
            //$("form").ligerForm();
         
         }
     
     
     }
      $(function (){  
         editModel.initEditPage(); 
      });  