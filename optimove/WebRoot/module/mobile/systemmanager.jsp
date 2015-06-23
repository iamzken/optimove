<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>����</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/systemmanagers/searchSystemmanager.do";
   var updateUrl = "<%=request.getContextPath()%>/systemmanagers/update.do";
   var insertUrl = "<%=request.getContextPath()%>/systemmanagers/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/systemmanagers/delete.do";
   ajaxConstants("tblLookupValues|LookupCode|Meaning|LookupType='accessType';orderBy=LookupCode");
	
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
                   		{field:'syscode',title:'ϵͳ����',width:100,align:'center'},
                   		{field:'sysname',title:'ϵͳ����',width:100,align:'center'},
                   		{field:'sysdescription',title:'ϵͳ˵��',width:100,align:'center'},
                   		{field:'accesstype',title:'���뷽ʽ',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblLookupValues',value); 
           				}},
                   		{field:'sysip',title:'IP��ַ',width:100,align:'center'},
                   		{field:'sysport',title:'�˿�',width:100,align:'center'},
                   		{field:'sysuser',title:'ʹ���û�',width:100,align:'center'},
                   		{field:'syspwd',title:'ʹ������',width:100,align:'center'},
                   		{field:'operator',title:'������Ա',width:100,align:'center'},
                   		{field:'operatororg',title:'��������',width:100,align:'center'},
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸�',readonlyFields:['syscode']});">�޸�</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'syscode'});">ɾ��</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				ϵͳ����:
				<input type="text" id="syscode" name="syscode"/>
				ϵͳ����:
				<input type="text" id="sysname" name="sysname"/>
				
				���뷽ʽ:
				<select id="accesstype" name="accesstype" constantId="tblLookupValues" style="width:120px"></select>
				
				
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
							<td>ϵͳ����:</td>
							<td><input type="text" id="syscode" name="syscode" style="width:120px"/></td>
						</tr>
						<tr>
							<td>ϵͳ����:</td>
							<td><input type="text" id="sysname" name="sysname" style="width:120px"/></td>
						</tr>
						<tr>
							<td>ϵͳ˵��:</td>
							<td><input type="text" id="sysdescription" name="sysdescription" style="width:120px"/></td>
						</tr>
						<tr>
							<td>���뷽ʽ:</td>
							<td><select id="accesstype" name="accesstype" constantId="tblLookupValues" style="width:120px"></select></td>
					
						</tr>
						<tr>
							<td>IP��ַ:</td>
							<td><input type="text" id="sysip" name="sysip" style="width:120px"/></td>
						</tr>
						<tr>
							<td>�˿�:</td>
							<td><input type="text" id="sysport" name="sysport" style="width:120px"/></td>
						</tr>
						<tr>
							<td>ʹ���û�:</td>
							<td><input type="text" id="sysuser" name="sysuser" style="width:120px"/></td>
						</tr>
						<tr>
							<td>ʹ������:</td>
							<td><input type="text" id="syspwd" name="syspwd" style="width:120px"/></td>
						</tr>
						
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
