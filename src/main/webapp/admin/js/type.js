
    $(function(){
        //使用datagrid绑定数据展示
        $('#dg').datagrid({
            url:'/selectTypeAll',
            fitColumns: true,
            pagination: true,
            pageList: [5, 10, 15, 20],
            toolbar:"#ToolBar",

            columns: [[
                {field:'ck',checkbox:true},  //复选框列
                { field: 'id', title: '编号', width: 50, align: "center" },
                { field: 'name', title: '名称', width: 50, align: "center" },
                { field: '操作', title: '操作', width: 50, align: "center",
                    formatter: function(value,row,index){
                        return "<a href='javascript:delSingle("+row.id+")'>删除</a>";
                    }
                }
            ]]
        });
    });

//打开添加窗口
function Add() {
    // alert("打开窗口");
    $("#AddDialog").dialog("open").dialog('setTitle',"添加类型");
}

//关闭添加窗口
function CloseDialog(){
    $("#AddDialog").dialog("close");
}

function upDistrict() {
    // alert("打开窗口");
    $("#upDialog").dialog("open").dialog('setTitle',"添加类型");
}

//关闭添加窗口
function CloseDialog(){
    $("#upDialog").dialog("close");
}



function SaveDialog() {
    $('#addDialogForm').form('submit', {
        url:'/addType',

        success:function(data){
            var obj=$.parseJSON(data);
            if(obj.result>0){
                $("#AddDialog").dialog("close");  //关闭
                $("#dg").datagrid('reload'); //刷新
                //alert("添加成功");
                $.messager.alert('提示框','恭喜添加成功!');
            }
            else
            {
                $.messager.alert('提示框','系统正在维护!');
            }
        }
    });
}

//删除单个
function delSingle(id) {
    $.messager.confirm('提示窗口','你确定要删除吗',function(result){
        if (result){

            $.post("/delType",{"id":id},function(data){
                if(data.result>0){
                    $("#dg").datagrid('reload'); //刷新
                    //alert("添加成功");
                    //$.messager.alert('提示框','恭喜删除成功!');
                }
                else
                {
                    $.messager.alert('提示框','系统正在维护!');
                }
            },"json");
        }
    });

}





//弹出修改对话框
function upType(){
    //判断有没有选择修改的记录
    //获取所有选中行，返回的数据，如果没有选中返回空
    var SelectRows = $("#dg").datagrid('getSelections');
    if(SelectRows.length!=1){
        $.messager.alert('提示框','你还没有选中行，或者选择了多行');
        return;
    }

    //选择了一行
    //还原数据
    var SelectRow = SelectRows[0];  //获取当前行的json:{"id":1000,"name":"丰台"}
    //打开编辑对话框
    $("#upDialog").dialog("open").dialog('setTitle',"修改类型");
    //获得行对象的数据加载到表单中显示
    //注意:SelectRow表示的就是当前行的Json:{"id":1000,"name":"丰台"}
    // 表单对象名称与json对象的键相同即可还原
    $("#upDialogForm").form('load',SelectRow);

}
//修改
function upDialog(){
    $('#upDialogForm').form('submit', {
        url:'/upType',

        success:function(data){
            var obj=$.parseJSON(data);
            if(obj.result>0){
                $("#upDialog").dialog("close");  //关闭
                $("#dg").datagrid('reload'); //刷新
                //alert("添加成功");
                $.messager.alert('提示框','恭喜修改成功!');
            }
            else
            {
                $.messager.alert('提示框','系统正在维护!');
            }
        }
    });
}



//弹出修改对话框
function deleteMoreType(){
    //判断有没有选择修改的记录
    //获取所有选中行，返回的数据，如果没有选中返回空
    var SelectRows = $("#dg").datagrid('getSelections');
    if(SelectRows.length==0){
        $.messager.alert('提示框','你还没有选中行');
        return;
    }

    //选择了一行
    //还原数据
    var delValue="";
    for(var i=0;i< SelectRows.length;i++){
        delValue=delValue+SelectRows[i].id+",";
    }
    delValue=delValue.substring(0,delValue.length-1);
    //alert(delValue);

    //发送异步请求到服务器
    $.post("/deleteMoreType",{"ids":delValue},function(data){
        if(data.result>0){
            $("#dg").datagrid('reload'); //刷新
            //alert("添加成功");
            $.messager.alert('提示框','恭喜你成功删除'+data.result+'行!');
        }
        else
        {
            $.messager.alert('提示框','系统正在维护!');
        }
    },"json");
}




