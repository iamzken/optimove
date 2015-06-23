<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>����</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/transtemplatemanagers/searchTranstemplatemanager.do";
   var updateUrl = "<%=request.getContextPath()%>/transtemplatemanagers/update.do";
   var insertUrl = "<%=request.getContextPath()%>/transtemplatemanagers/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/transtemplatemanagers/delete.do";
   ajaxConstants("tblLookupValues|LookupCode|Meaning|LookupType='useChannel';orderBy=LookupCode");
	
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
                   		{field:'templatecode',title:'ģ�����',width:100,align:'center'},
                   		{field:'templatename',title:'ģ������',width:100,align:'center'},
                   		{field:'templateobject',title:'ģ�����',width:100,align:'center'},
                   		{field:'linkurl',title:'���õ�ַ',width:100,align:'center'},
                   		{field:'usechannel',title:'ʹ������',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblLookupValues',value); 
           				}},
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸�',readonlyFields:['templatecode']});">�޸�</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'templatecode'});">ɾ��</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				ģ�����:
				<input type="text" id="templatecode" name="templatecode"/>
				ģ������:
				<input type="text" id="templatename" name="templatename"/>
				ʹ������:
				<select id="usechannel" name="usechannel" constantId="tblLookupValues" style="width:120px"></select>
				
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
							<td>ģ�����:</td>
							<td><input type="text" id="templatecode" name="templatecode" style="width:120px"/></td>
						</tr>
						<tr>
							<td>ģ������:</td>
							<td><input type="text" id="templatename" name="templatename" style="width:120px"/></td>
						</tr>
						<tr>
							<td>ģ�����:</td>
							<td><input type="text" id="templateobject" name="templateobject" style="width:120px"/></td>
						</tr>
						<tr>
							<td>���õ�ַ:</td>
							<td><input type="text" id="linkurl" name="linkurl" style="width:120px"/></td>
						</tr>
						<tr>
							<td>ʹ������:</td>
							<td><select id="usechannel" name="usechannel" constantId="tblLookupValues" style="width:120px"></select></td>
					
						</tr>
						
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
