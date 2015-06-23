<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>管理</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/businessproductmanagers/searchBusinessproductmanager.do";
   var updateUrl = "<%=request.getContextPath()%>/businessproductmanagers/update.do";
   var insertUrl = "<%=request.getContextPath()%>/businessproductmanagers/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/businessproductmanagers/delete.do";
   ajaxConstants("tblBusinessTypeManager|typeCode|typeName|;orderBy=typeName,tblTransTemplateManager|templateCode|templateName|;orderBy=templateName,tblFlowManager|flowCode|flowName|;orderBy=flowName");
	
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
	        toolbar:'#tb',//产品代码、产品名称、业务种类（引用业务种类）、交易模板（引用交易模板）、交易流程（引用流程模板）
	        columns:[[   
                   		{field:'productcode',title:'产品代码',width:100,align:'center'},
                   		{field:'productname',title:'产品名称',width:100,align:'center'},
                   		{field:'typecode',title:'业务种类',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblBusinessTypeManager',value); 
           				}},
                   		{field:'templatecode',title:'交易模板',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblTransTemplateManager',value); 
           				}},
                   		{field:'flowcode',title:'交易流程',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblFlowManager',value); 
           				}},
           				{field:'logourl',title:'图片地址',width:150,align:'center'},
                   		{field:'remark',title:'说明',width:300,align:'center'},
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'修改',readonlyFields:['productcode']});">修改</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'productcode'});">删除</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				产品代码:
				<input type="text" id="productcode" name="productcode"/>
				产品名称:
				<input type="text" id="productname" name="productname"/>
				业务种类:
				<select id="typecode" name="typecode" constantId="tblBusinessTypeManager" style="width:180px"></select>
				
				
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
							<td>产品代码:</td>
							<td><input type="text" id="productcode" name="productcode" style="width:180px"/></td>
						</tr>
						<tr>
							<td>产品名称:</td>
							<td><input type="text" id="productname" name="productname" style="width:180px"/></td>
						</tr>
						<tr>
							<td>业务种类:</td>
							<td><select id="typecode" name="typecode" constantId="tblBusinessTypeManager" style="width:180px"></select></td>
					
						</tr>
						<tr>
							<td>交易模板:</td>
							<td><select id="templatecode" name="templatecode" constantId="tblTransTemplateManager" style="width:180px"></select></td>
					
						</tr>
						<tr>
							<td>交易流程:</td>
							<td><select id="flowcode" name="flowcode" constantId="tblFlowManager" style="width:180px"></select></td>
					
						</tr>
						<tr>
							<td>图片地址:</td>
							<td><input type="text" id="logourl" name="logourl" style="width:180px"/></td>
						
						</tr>
						<tr>
							<td>说明:</td>
							<td>
							<textarea name="remark" rows="5" cols="20" style="width:180px"></textarea>
							</td>
						</tr>
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
