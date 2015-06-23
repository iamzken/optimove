<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>����</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/businesstypemanagers/searchBusinesstypemanager.do";
   var updateUrl = "<%=request.getContextPath()%>/businesstypemanagers/update.do";
   var insertUrl = "<%=request.getContextPath()%>/businesstypemanagers/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/businesstypemanagers/delete.do";
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
	        columns:[[   //������롢�������ƣ��磺���ÿ�����ҵƱ�ݣ�����ע
                   		{field:'typecode',title:'�������',width:100,align:'center'},
                   		{field:'typename',title:'��������',width:100,align:'center'},
                   		{field:'logourl',title:'ͼƬ��ַ',width:150,align:'center'},
                   		{field:'remark',title:'˵��',width:300,align:'center'},
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸�',readonlyFields:['typecode']});">�޸�</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'typecode'});">ɾ��</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				�������:
				<input type="text" id="typecode" name="typecode"/>
				��������:
				<input type="text" id="typename" name="typename"/>
				
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
							<td>�������:</td>
							<td><input type="text" id="typecode" name="typecode" style="width:180px"/></td>
						</tr>
						<tr>
							<td>��������:</td>
							<td><input type="text" id="typename" name="typename" style="width:180px"/></td>
						</tr>
						<tr>
							<td>ͼƬ��ַ:</td>
							<td><input type="textarea" rows="3"  id="logourl" name="logourl" style="width:180px"/></td>
						</tr>
						<tr>
							<td>˵��:</td>
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
