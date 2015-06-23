<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>应用管理</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/appmanagers/searchAppmanager.do";
   var updateUrl = "<%=request.getContextPath()%>/appmanagers/update.do";
   var insertUrl = "<%=request.getContextPath()%>/appmanagers/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/appmanagers/delete.do";
   ajaxConstants("tblLookupValues|LookupCode|Meaning|LookupType='VerType';orderBy=LookupCode");
	
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
                   		{field:'appcode',title:'应用代码',width:100,align:'center'},
                   		{field:'appname',title:'应用名称',width:100,align:'center'},
                   		{field:'appdescription',title:'应用说明',width:100,align:'center'},
                   		{field:'verno',title:'版本号',width:100,align:'center'},
                   		{field:'vertype',title:'版本类型',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblLookupValues',value); 
           				}},
                   		{field:'releaseoperate',title:'发布人',width:100,align:'center'},
                   		{field:'releasedate',title:'发布日期',width:100,align:'center'},
                   		{field:'updatedescription',title:'更新说明',width:100,align:'center'},
                   		{field:'operator',title:'操作员',width:100,align:'center'},
                   		{field:'operatororg',title:'操作机构',width:100,align:'center'},
                   		{field:'updatedate',title:'操作日期',width:100,align:'center'},
                   		{field:'updatetime',title:'操作时间',width:100,align:'center'}
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'修改',readonlyFields:['appcode']});">修改</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'appcode'});">删除</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				应用代码:
				<input type="text" id="sappcode" name="appcode"/>
				应用名称:
				<input type="text" id="sappname" name="appname"/>
				版本号:
				<input type="text" id="sverno" name="verno"/>
				版本类型:
				<select id="vertype" name="vertype" constantId="tblLookupValues" style="width:180px"></select>
				
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
							<td>应用代码:</td>
							<td><input type="text" id="appcode" name="appcode" style="width:180px"/></td>
						</tr>
						<tr>
							<td>应用名称:</td>
							<td><input type="text" id="appname" name="appname" style="width:180px"/></td>
						</tr>
						<tr>
							<td>应用说明:</td>
							<td><input type="text" id="appdescription" name="appdescription" style="width:180px"/></td>
						</tr>
						<tr>
							<td>版本号:</td>
							<td><input type="text" id="verno" name="verno" style="width:180px"/></td>
						</tr>
						<tr>
							<td>版本类型:</td>
							<td><select id="vertype" name="vertype" constantId="tblLookupValues" style="width:180px"></select></td>
					
						</tr>
						<tr>
							<td>发布人:</td>
							<td><input type="text" id="releaseoperate" name="releaseoperate" style="width:180px"/></td>
						</tr>
						<tr>
							<td>发布日期:</td>
							<td><input type="text" id="releasedate" name="releasedate" style="width:180px"/></td>
						</tr>
						<tr>
							<td>更新说明:</td>
							<td><input type="text" id="updatedescription" name="updatedescription" style="width:180px"/></td>
						</tr>
						
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
