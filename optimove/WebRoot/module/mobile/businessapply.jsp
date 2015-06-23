<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>管理</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/businessapplys/searchBusinessapply.do";
   var updateUrl = "<%=request.getContextPath()%>/businessapplys/update.do";
   var insertUrl = "<%=request.getContextPath()%>/businessapplys/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/businessapplys/delete.do";
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
                   		{field:'businessid',title:'',width:100,align:'center'},
                   		{field:'businesstype',title:'',width:100,align:'center'},
                   		{field:'productcode',title:'',width:100,align:'center'},
                   		{field:'tableid',title:'',width:100,align:'center'},
                   		{field:'fieldname',title:'',width:100,align:'center'},
                   		{field:'fieldcnname',title:'',width:100,align:'center'},
                   		{field:'fieldvalue',title:'',width:100,align:'center'},
                   		{field:'transchannel',title:'',width:100,align:'center'},
                   		{field:'operator',title:'',width:100,align:'center'},
                   		{field:'operatororg',title:'',width:100,align:'center'},
                   		{field:'updatedate',title:'',width:100,align:'center'},
                   		{field:'updatetime',title:'',width:100,align:'center'}
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'修改',readonlyFields:['businessid']});">修改</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'businessid'});">删除</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				:
				<input type="text" id="businessid" name="businessid"/>
				:
				<input type="text" id="businesstype" name="businesstype"/>
				:
				<input type="text" id="productcode" name="productcode"/>
				:
				<input type="text" id="tableid" name="tableid"/>
				:
				<input type="text" id="fieldname" name="fieldname"/>
				:
				<input type="text" id="fieldcnname" name="fieldcnname"/>
				:
				<input type="text" id="fieldvalue" name="fieldvalue"/>
				:
				<input type="text" id="transchannel" name="transchannel"/>
				:
				<input type="text" id="operator" name="operator"/>
				:
				<input type="text" id="operatororg" name="operatororg"/>
				:
				<input type="text" id="updatedate" name="updatedate"/>
				:
				<input type="text" id="updatetime" name="updatetime"/>
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
							<td>:</td>
							<td><input type="text" id="businessid" name="businessid" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="businesstype" name="businesstype" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="productcode" name="productcode" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="tableid" name="tableid" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="fieldname" name="fieldname" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="fieldcnname" name="fieldcnname" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="fieldvalue" name="fieldvalue" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="transchannel" name="transchannel" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="operator" name="operator" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="operatororg" name="operatororg" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="updatedate" name="updatedate" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="updatetime" name="updatetime" style="width:180px"/></td>
						</tr>
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
