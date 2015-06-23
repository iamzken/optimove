<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>����</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/nephogramdatas/searchNephogramdata.do";
   var updateUrl = "<%=request.getContextPath()%>/nephogramdatas/update.do";
   var insertUrl = "<%=request.getContextPath()%>/nephogramdatas/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/nephogramdatas/delete.do";
	$(function() {
	    $('#dataList').datagrid({  
	        title:'�б�',  
	        iconCls:'icon-edit',//ͼ��  
	        //width: 700,  
	        height: 'auto',  
	        nowrap: false,  
	        striped: true,  
	        border: true,  
	        collapsible:false,//�Ƿ���۵���  
	        fit: true,//�Զ���С  
	        url:'#',  
	        remoteSort:false,   
	        singleSelect:false,//�Ƿ�ѡ  
	        pagination:true,//��ҳ�ؼ�  
	        rownumbers:true,//�к�  
	        frozenColumns:[[  
	            {field:'ck',checkbox:true}  
	        ]],  
	        url:searchUrl, 
	        toolbar:'#tb',
	        columns:[[   
                   		{field:'nephogramdataid',title:'',width:100,align:'center'},
                   		{field:'nephogrammodelid',title:'',width:100,align:'center'},
                   		{field:'nephogramattrid',title:'',width:100,align:'center'},
                   		{field:'attrvalue',title:'',width:100,align:'center'},
                   		{field:'dataremarks',title:'',width:100,align:'center'}
	        ]],
	        onLoadSuccess:function(data){
	    		data = convertJson(data);
	        	if(data.result!='ok'){
	        		showBox("��ʾ��Ϣ",data.errorMsg,'warning');
	        	}
	        }
	        
	    });  
	
	    //���÷�ҳ�ؼ�  
	    var p = $('#dataList').datagrid('getPager');  
	    $(p).pagination({  
	        pageSize: 10,//ÿҳ��ʾ�ļ�¼������Ĭ��Ϊ10  
	        pageList: [10,20,30],//��������ÿҳ��¼�������б�  
	        beforePageText: '��',//ҳ���ı���ǰ��ʾ�ĺ���  
	        afterPageText: 'ҳ    �� {pages} ҳ',  
	        displayMsg: '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	    })
	});
	</script>
  </head>
  
  <body class="easyui-layout" >
	<div  region="center" >
		<div id='dataList'>
			<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddwindow({title:'����'})">����</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸�',readonlyFields:['nephogramdataid']});">�޸�</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'nephogramdataid'});">ɾ��</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				:
				<input type="text" id="nephogramdataid" name="nephogramdataid"/>
				:
				<input type="text" id="nephogrammodelid" name="nephogrammodelid"/>
				:
				<input type="text" id="nephogramattrid" name="nephogramattrid"/>
				:
				<input type="text" id="attrvalue" name="attrvalue"/>
				:
				<input type="text" id="dataremarks" name="dataremarks"/>
				<input type="button" onclick="loadList(1);" value="��ѯ"/>
			</form>
		</div>
	</div>
		</div>
	</div>
	
	<div style="visibility:hidden" >
		<div id="addwindow"  title="���" style="width:600px;height:350px;padding:10px">
			<form id='addForm' action="" method="post">
				<table>
						<tr>
							<td>:</td>
							<td><input type="text" id="nephogramdataid" name="nephogramdataid" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="nephogrammodelid" name="nephogrammodelid" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="nephogramattrid" name="nephogramattrid" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="attrvalue" name="attrvalue" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="dataremarks" name="dataremarks" style="width:180px"/></td>
						</tr>
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
