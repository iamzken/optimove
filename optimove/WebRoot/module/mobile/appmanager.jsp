<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>Ӧ�ù���</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/appmanagers/searchAppmanager.do";
   var updateUrl = "<%=request.getContextPath()%>/appmanagers/update.do";
   var insertUrl = "<%=request.getContextPath()%>/appmanagers/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/appmanagers/delete.do";
   ajaxConstants("tblLookupValues|LookupCode|Meaning|LookupType='VerType';orderBy=LookupCode");
	
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
                   		{field:'appcode',title:'Ӧ�ô���',width:100,align:'center'},
                   		{field:'appname',title:'Ӧ������',width:100,align:'center'},
                   		{field:'appdescription',title:'Ӧ��˵��',width:100,align:'center'},
                   		{field:'verno',title:'�汾��',width:100,align:'center'},
                   		{field:'vertype',title:'�汾����',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblLookupValues',value); 
           				}},
                   		{field:'releaseoperate',title:'������',width:100,align:'center'},
                   		{field:'releasedate',title:'��������',width:100,align:'center'},
                   		{field:'updatedescription',title:'����˵��',width:100,align:'center'},
                   		{field:'operator',title:'����Ա',width:100,align:'center'},
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸�',readonlyFields:['appcode']});">�޸�</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'appcode'});">ɾ��</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				Ӧ�ô���:
				<input type="text" id="sappcode" name="appcode"/>
				Ӧ������:
				<input type="text" id="sappname" name="appname"/>
				�汾��:
				<input type="text" id="sverno" name="verno"/>
				�汾����:
				<select id="vertype" name="vertype" constantId="tblLookupValues" style="width:180px"></select>
				
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
							<td>Ӧ�ô���:</td>
							<td><input type="text" id="appcode" name="appcode" style="width:180px"/></td>
						</tr>
						<tr>
							<td>Ӧ������:</td>
							<td><input type="text" id="appname" name="appname" style="width:180px"/></td>
						</tr>
						<tr>
							<td>Ӧ��˵��:</td>
							<td><input type="text" id="appdescription" name="appdescription" style="width:180px"/></td>
						</tr>
						<tr>
							<td>�汾��:</td>
							<td><input type="text" id="verno" name="verno" style="width:180px"/></td>
						</tr>
						<tr>
							<td>�汾����:</td>
							<td><select id="vertype" name="vertype" constantId="tblLookupValues" style="width:180px"></select></td>
					
						</tr>
						<tr>
							<td>������:</td>
							<td><input type="text" id="releaseoperate" name="releaseoperate" style="width:180px"/></td>
						</tr>
						<tr>
							<td>��������:</td>
							<td><input type="text" id="releasedate" name="releasedate" style="width:180px"/></td>
						</tr>
						<tr>
							<td>����˵��:</td>
							<td><input type="text" id="updatedescription" name="updatedescription" style="width:180px"/></td>
						</tr>
						
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
