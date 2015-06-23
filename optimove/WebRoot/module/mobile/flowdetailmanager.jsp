<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>管理</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/flowdetailmanagers/searchFlowdetailmanager.do";
   var updateUrl = "<%=request.getContextPath()%>/flowdetailmanagers/update.do";
   var insertUrl = "<%=request.getContextPath()%>/flowdetailmanagers/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/flowdetailmanagers/delete.do";
   ajaxConstants("tblLookupValues|LookupCode|Meaning|LookupType='processFlag';orderBy=LookupCode,tblServiceManager|serviceCode|serviceCnName|;orderBy=serviceCnName,tblFlowManager|flowCode|flowName|;orderBy=flowName");
	
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
                   		{field:'flowcode',title:'流程名称',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblFlowManager',value); 
           				}},
                   		{field:'servicecode',title:'服务名',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblServiceManager',value); 
           				}},
                   		{field:'floworder',title:'流程顺序',width:100,align:'center'},
                   		{field:'processflag',title:'过程标识',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblLookupValues',value); 
           				}},
                   		{field:'operator',title:'操作人员',width:100,align:'center'},
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'修改',readonlyFields:['flowcode']});">修改</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'flowcode'});">删除</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				流程名称:
				<select id="flowcode" name="flowcode" constantId="tblFlowManager" style="width:120px"></select>
				
				服务名:
				<input type="text" id="servicecode" name="servicecode"/>
				
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
							<td>流程名称:</td>
							<td><select id="flowcode" name="flowcode" constantId="tblFlowManager" style="width:120px"></select></td>
					
						</tr>
						<tr>
							<td>服务名:</td>
							<td><select id="servicecode" name="servicecode" constantId="tblServiceManager" style="width:120px"></select></td>
					
						</tr>
						<tr>
							<td>流程顺序:</td>
							<td><input type="text" id="floworder" name="floworder" style="width:120px"/></td>
						</tr>
						<tr>
							<td>过程标识:</td>
							<td><select id="processflag" name="processflag" constantId="tblLookupValues" style="width:120px"></select></td>
					
						</tr>
						
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
