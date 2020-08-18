
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
    <title>复选框</title>     
    <meta name="keywords" content="免费控件,免费UI控件,免费开源UI,免费开源UI控件,免费开源UI框架,复选框">  
    <link href="../../lib/ligerUI/skins/Aqua/css/ligerui-all.css?332" rel="stylesheet" type="text/css">
    <style type="text/css">
         
 
         
    </style>
    <script src="../../lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="../../lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
      
 
    <script type="text/javascript">
        $(function () {
            $("#chk1").change(function () { alert(this.checked); });
 
            $('input:checkbox').ligerCheckBox();
 
            $("#btnMul").click(function () {
                var str = "";
                $("#table2 input:chekcbox").each(function () {
                    str += this.checked + ",";
                });
                alert(str);
            });
        });
          
  
    </script>
 
 
<h3>示例一：单选项(触发事件)</h3>
<table id="table1">
    <tbody><tr><td><div class="l-checkbox-wrapper"><a class="l-checkbox"></a><input type="checkbox" name="chbox" id="chk1" class="l-hidden" ligeruiid="chk1"></div></td><td> 选项</td></tr>
</tbody></table>
<br>
<h3>示例二：多选项</h3>
 <table id="table2">
    <tbody><tr><td> <div class="l-checkbox-wrapper"><a class="l-checkbox"></a><input type="checkbox" name="chbox" class="l-hidden" ligeruiid="CheckBox1000"></div></td><td>选项一</td></tr>
    <tr><td><div class="l-checkbox-wrapper"><a class="l-checkbox"></a><input type="checkbox" name="chbox" class="l-hidden" ligeruiid="CheckBox1001"></div></td><td>选项二</td></tr>
    <tr><td><div class="l-checkbox-wrapper"><a class="l-checkbox"></a><input type="checkbox" name="chbox" class="l-hidden" ligeruiid="CheckBox1002"></div></td><td>选项三</td></tr>
 </tbody></table>
 <input type="button" id="btnMul" value="获取值">
  <div style="display:none;">
   
</div>