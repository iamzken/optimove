<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>部门管理</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/departments/searchDepartment.do";
   var updateUrl = "<%=request.getContextPath()%>/departments/update.do";
   var insertUrl = "<%=request.getContextPath()%>/departments/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/departments/delete.do";
	$(function() {
	    $('#dataList').datagrid({  
	        title:'列表',  
	        iconCls:'icon-edit',//图标  
	        //width: 700,  
	        height: 'auto',  
	        nowrap: false,  
	        striped: true,  
	        border: true,  
	        collapsible:false,//是否可折叠的  
	        fit: true,//自动大小  
	        url:'#',  
	        remoteSort:false,   
	        singleSelect:false,//是否单选  
	        pagination:true,//分页控件  
	        rownumbers:true,//行号  
	        frozenColumns:[[  
	            {field:'ck',checkbox:true}  
	        ]],  
	        url:searchUrl, 
	        toolbar:'#tb',
	        columns:[[   
                   		{field:'departmentcode',title:'部门编号',width:100,align:'center'},
                   		{field:'departmentname',title:'部门名称',width:100,align:'center'},
                   		{field:'deptdescription',title:'部门描述',width:100,align:'center'},
                   		{field:'remarks',title:'备注',width:100,align:'center'},
                   		{field:'operatorbankcode',title:'操作机构',width:100,align:'center'},
                   		{field:'operatorcode',title:'操作人',width:100,align:'center'},
                   		{field:'updatedate',title:'更新日期',width:100,align:'center'},
                   		{field:'updatetime',title:'更新时间',width:100,align:'center'}
	        ]],
	        onLoadSuccess:function(data){
	    		data = convertJson(data);
	        	if(data.result!='ok'){
	        		showBox("提示信息",data.errorMsg,'warning');
	        	}
	        }
	        
	    });  
	
	    //设置分页控件  
	    var p = $('#dataList').datagrid('getPager');  
	    $(p).pagination({  
	        pageSize: 10,//每页显示的记录条数，默认为10  
	        pageList: [10,20,30],//可以设置每页记录条数的列表  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	    })
	});
	</script>
  </head>
  
  <body class="easyui-layout" >
	<div  region="center" >
		<div id='dataList'>
			<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddwindow({title:'新增'})">新增</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'修改',readonlyFields:['departmentcode']});">修改</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'departmentcode'});">删除</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				部门编号:
				<input type="text" id="sdepartmentcode" name="departmentcode"/>
				部门名称:
				<input type="text" id="sdepartmentname" name="departmentname"/>
				<input type="button" onclick="loadList(1);" value="查询"/>
			</form>
		</div>
	</div>
		</div>
	</div>
	
	<div style="visibility:hidden" >
		<div id="addwindow"  title="添加" style="width:600px;height:350px;padding:10px">
			<form id='addForm' action="" method="post">
				<table>
						<tr>
							<td>部门编号:</td>
							<td><input type="text" id="departmentcode" name="departmentcode" style="width:120px"/></td>
							<td>部门名称:</td>
							<td><input type="text" id="departmentname" name="departmentname" style="width:120px"/></td>
						</tr>
						<tr>
							<td>部门描述:</td>
							<td><input type="text" id="deptdescription" name="deptdescription" style="width:120px"/></td>
							<td>备注:</td>
							<td><input type="text" id="remarks" name="remarks" style="width:120px"/></td>
						</tr>
						<tr>
							<td>操作机构:</td>
							<td><input type="text" id="operatorbankcode" name="operatorbankcode" style="width:120px"/></td>
							<td>操作人:</td>
							<td><input type="text" id="operatorcode" name="operatorcode" style="width:120px"/></td>
						</tr>
						<tr>
							<td>更新日期:</td>
							<td><input type="text" id="updatedate" class="easyui-datebox" name="updatedate" style="width:120px"/></td>
							<td>更新时间:</td>
							<td><input type="text" id="updatetime" class="easyui-datebox" name="updatetime" style="width:120px"/></td>
						</tr>
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
