<%--
  Created by IntelliJ IDEA.
  User: 小米
  Date: 2019/6/21
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css" />
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript" src="js/user.js"></script>

</head>
<body>

<!--展示数据-->
<table id="dg"></table>

<!--定义工具栏-->
<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:Add()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a>
        <a
                href="javascript:upType()" class="easyui-linkbutton"
                iconCls="icon-edit" plain="true">修改</a>
        <a
                href="javascript:deleteMoreType()" class="easyui-linkbutton"
                iconCls="icon-remove" plain="true">删除多个</a>
    </div>



    <div>
        姓名:<input type="text" name="name" id="nameName"/>
        电话:<input type="text" name="telephone" id="tel1"/>
        <a id="btn" href="javascript:searchUser();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>

    </div>
</div>

<!--添加窗口-->
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="addDialogForm" method="post">
        <table>
            <tr>
                <td>类型:</td>
                <td><input type="text" name="name"  /></td>
                <td><input type="text" name="telephone"  /></td>
                <td><input type="text" name="password"  /></td>
                <td><input type="text" name="isadimin"  /></td>
            </tr>


        </table>
    </form>
</div>

<!--给添加对话框添加按钮-->
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog()"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>



<div id="upDialogButtons">
    <a href="javascript:upDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog()"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>


<%--修改窗口--%>
<div id="upDialog" class="easyui-dialog" buttons="#upDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="upDialogForm" method="post">
        <table>
            <tr>
                <td>用户:</td>
                <input type="hidden" name="id" id="id">
                <td><input type="text" name="name" id="name" /></td>
                <td><input type="text" name="telephone" id="tel" /></td>
            </tr>


        </table>
    </form>
</div>


</body>
</html>