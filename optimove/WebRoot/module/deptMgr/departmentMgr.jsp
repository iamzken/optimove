<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>���Ź���</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/departments/searchDepartment.do";
   var updateUrl = "<%=request.getContextPath()%>/departments/update.do";
   var insertUrl = "<%=request.getContextPath()%>/departments/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/departments/delete.do";
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
                   		{field:'departmentcode',title:'���ű��',width:100,align:'center'},
                   		{field:'departmentname',title:'��������',width:100,align:'center'},
                   		{field:'deptdescription',title:'��������',width:100,align:'center'},
                   		{field:'remarks',title:'��ע',width:100,align:'center'},
                   		{field:'operatorbankcode',title:'��������',width:100,align:'center'},
                   		{field:'operatorcode',title:'������',width:100,align:'center'},
                   		{field:'updatedate',title:'��������',width:100,align:'center'},
                   		{field:'updatetime',title:'����ʱ��',width:100,align:'center'}
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸�',readonlyFields:['departmentcode']});">�޸�</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'departmentcode'});">ɾ��</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				���ű��:
				<input type="text" id="sdepartmentcode" name="departmentcode"/>
				��������:
				<input type="text" id="sdepartmentname" name="departmentname"/>
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
							<td>���ű��:</td>
							<td><input type="text" id="departmentcode" name="departmentcode" style="width:120px"/></td>
							<td>��������:</td>
							<td><input type="text" id="departmentname" name="departmentname" style="width:120px"/></td>
						</tr>
						<tr>
							<td>��������:</td>
							<td><input type="text" id="deptdescription" name="deptdescription" style="width:120px"/></td>
							<td>��ע:</td>
							<td><input type="text" id="remarks" name="remarks" style="width:120px"/></td>
						</tr>
						<tr>
							<td>��������:</td>
							<td><input type="text" id="operatorbankcode" name="operatorbankcode" style="width:120px"/></td>
							<td>������:</td>
							<td><input type="text" id="operatorcode" name="operatorcode" style="width:120px"/></td>
						</tr>
						<tr>
							<td>��������:</td>
							<td><input type="text" id="updatedate" class="easyui-datebox" name="updatedate" style="width:120px"/></td>
							<td>����ʱ��:</td>
							<td><input type="text" id="updatetime" class="easyui-datebox" name="updatetime" style="width:120px"/></td>
						</tr>
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
