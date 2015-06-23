<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>����</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript">
   var searchUrl = "<%=request.getContextPath()%>/currencys/searchCurrency.do";
   var updateUrl = "<%=request.getContextPath()%>/currencys/update.do";
   var insertUrl = "<%=request.getContextPath()%>/currencys/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/currencys/delete.do";
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
                   		{field:'code',title:'���ִ���',width:100,align:'center'},
                   		{field:'name',title:'��������',width:100,align:'center'},
                   		{field:'brief',title:'��д',width:100,align:'center'}
                   		
	        ]],
	        onLoadSuccess:function(data){
	    		data = convertJson(data);
	        	if(data.result!='ok'){
	        		showBox("��ʾ��Ϣ",data.msg,'warning');
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸�',readonlyFields:['code']});">�޸�</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'code'});">ɾ��</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				���ִ���:
				<input type="text" id="scode" name="code"/>
				��������:
				<input type="text" id="sname" name="name"/>
				��д:
				<input type="text" id="sbrief" name="brief"/>
				
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
							<td>���ִ���:</td>
							<td><input type="text" id="code" name="code" style="width:120px"/></td>
						</tr>
						<tr>
							<td>��������:</td>
							<td><input type="text" id="name" name="name" style="width:120px"/></td>
						</tr>
						<tr>
							<td>��д:</td>
							<td><input type="text" id="brief" name="brief" style="width:120px"/></td>
						</tr>
						
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
