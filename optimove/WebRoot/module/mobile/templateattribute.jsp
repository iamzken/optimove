<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>管理</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/templateattributes/searchTemplateattribute.do";
   var updateUrl = "<%=request.getContextPath()%>/templateattributes/update.do";
   var insertUrl = "<%=request.getContextPath()%>/templateattributes/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/templateattributes/delete.do";

   ajaxConstants("sjlx,tysfbz,tblTransTemplateManager&ttmp|templateCode|templateName|;orderBy=templateName,tblTemplateArea|areaCode|areaName|;orderBy=areaName,tblLookupValues&tblLookupValues-sjlx|LookupCode|Meaning|LookupType='sjlx';orderBy=LookupCode,tblLookupValues|LookupCode|Meaning|LookupType='srlx';orderBy=LookupCode");	
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
                   		{field:'id',title:'id',width:100,align:'center'},
                   		{field:'templatecode',title:'模板名称',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('ttmp',value); 
           				}},
                   		{field:'areacode',title:'区域名称',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblTemplateArea',value); 
           				}},
                   		{field:'attributecode',title:'属性代码',width:100,align:'center'},
                   		{field:'attributename',title:'属性名称',width:100,align:'center'},
                   		{field:'attributewidth',title:'显示宽度',width:100,align:'center'},
                   		{field:'attributeorder',title:'显示顺序',width:100,align:'center'},
                   		{field:'attributetype',title:'输入类型',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblLookupValues',value); 
           				}},
           				{field:'typevalue',title:'类型值',width:100,align:'center'},
                   		{field:'datatype',title:'数据类型',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblLookupValues-sjlx',value); 
           				}},
                   		{field:'ispk',title:'是否唯一',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tysfbz',value); 
           				}},
                   		{field:'isempty',title:'是否必输',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tysfbz',value); 
           				}},
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'修改',readonlyFields:['id']});">修改</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'id'});">删除</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				模板名称:
			
				<select id="templatecode" name="templatecode" constantId="ttmp" style="width:120px"></select>
				
				区域名称:
				<select id="areacode" name="areacode" constantId="tblTemplateArea" style="width:120px"></select>
				
				属性代码:
				<input type="text" id="attributecode" name="attributecode"/>
				属性名称:
				<input type="text" id="attributename" name="attributename"/>
				
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
							<td style="width: 100">模板名称:</td>
							<td><select id="templatecode" name="templatecode" constantId="ttmp" style="width:180px"></select></td>
						
							<td style="width: 100">区域名称:</td>
							<td><select id="areacode" name="areacode" constantId="tblTemplateArea" style="width:180px"></select></td>
						</tr>
						<tr>
							<td>属性代码:</td>
							<td><input type="text" id="attributecode" name="attributecode" style="width:180px"/></td>
						
							<td>属性名称:</td>
							<td><input type="text" id="attributename" name="attributename" style="width:180px"/></td>
						</tr>
						<tr>
							<td>显示宽度:</td>
							<td><input type="text" id="attributewidth" name="attributewidth" style="width:180px"/></td>
						
							<td>显示顺序:</td>
							<td><input type="text" id="attributeorder" name="attributeorder" style="width:180px"/></td>
						</tr>
						<tr>
							<td>输入类型:</td>
							<td><select id="attributetype" name="attributetype" constantId="tblLookupValues" style="width:180px"></select></td>
					
						
							<td>类型值:</td>
							<td><textarea name="typevalue" rows="5" cols="20" style="width:180px"></textarea></td>
						
							
						</tr>
						<tr>
							<td>数据类型:</td>
							<td><select id="datatype" name="datatype" constantId="sjlx" style="width:180px"></select></td>
					
							<td>是否唯一:</td>
							<td><select id="ispk" name="ispk" constantId="tysfbz" style="width:180px"></select></td>
					
						
						</tr>
						<tr>
							<td>是否必输:</td>
							<td><select id="isempty" name="isempty" constantId="tysfbz" style="width:180px"></select></td>
					
							<td></td>
							<td><input type="hidden" id="id" name="id" style="width:180px"/></td>
						</tr>
						
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
