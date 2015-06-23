<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>专题数据管理</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/specialdatas/searchSpecialdata.do";
   var updateUrl = "<%=request.getContextPath()%>/specialdatas/update.do";
   var insertUrl = "<%=request.getContextPath()%>/specialdatas/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/specialdatas/delete.do";
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
                   		{field:'specialdataid',title:'专题数据编号',width:100,align:'center'},
                   		{field:'specialdataname',title:'专题数据名称',width:100,align:'center'},
                   		{field:'specialdatasource',title:'专题数据来源',width:100,align:'center'},
                   		{field:'specialdatatype',title:'专题数据类型',width:100,align:'center'},
                   		{field:'remarks',title:'备注',width:100,align:'center'},
                   		{field:'specialdatafile',title:'专题数据',width:100,align:'center'}
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'修改',readonlyFields:['specialdataid']});">修改</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'specialdataid'});">删除</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				专题数据名称:
				<input type="text" id="specialdataname" name="specialdataname"/>
				专题数据来源:
				<input type="text" id="specialdatasource" name="specialdatasource"/>
				专题数据类型:
				<input type="text" id="specialdatatype" name="specialdatatype"/>
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
							<td>专题数据编号:</td>
							<td><input type="text" id="specialdataid" name="specialdataid" style="width:180px"/></td>
						</tr>
						<tr>
							<td>专题数据名称:</td>
							<td><input type="text" id="specialdataname" name="specialdataname" style="width:180px"/></td>
						</tr>
						<tr>
							<td>专题数据来源:</td>
							<td><input type="text" id="specialdatasource" name="specialdatasource" style="width:180px"/></td>
						</tr>
						<tr>
							<td>专题数据类型:</td>
							<td><input type="text" id="specialdatatype" name="specialdatatype" style="width:180px"/></td>
						</tr>
						<tr>
							<td>备注:</td>
							<td><input type="text" id="remarks" name="remarks" style="width:180px"/></td>
						</tr>
						<tr>
							<td>专题数据:</td>
							<td><input type="text" id="specialdatafile" name="specialdatafile" style="width:180px"/></td>
						</tr>
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
