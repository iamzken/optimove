<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>ר�����ݹ���</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/specialdatas/searchSpecialdata.do";
   var updateUrl = "<%=request.getContextPath()%>/specialdatas/update.do";
   var insertUrl = "<%=request.getContextPath()%>/specialdatas/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/specialdatas/delete.do";
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
                   		{field:'specialdataid',title:'ר�����ݱ��',width:100,align:'center'},
                   		{field:'specialdataname',title:'ר����������',width:100,align:'center'},
                   		{field:'specialdatasource',title:'ר��������Դ',width:100,align:'center'},
                   		{field:'specialdatatype',title:'ר����������',width:100,align:'center'},
                   		{field:'remarks',title:'��ע',width:100,align:'center'},
                   		{field:'specialdatafile',title:'ר������',width:100,align:'center'}
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸�',readonlyFields:['specialdataid']});">�޸�</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'specialdataid'});">ɾ��</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				ר����������:
				<input type="text" id="specialdataname" name="specialdataname"/>
				ר��������Դ:
				<input type="text" id="specialdatasource" name="specialdatasource"/>
				ר����������:
				<input type="text" id="specialdatatype" name="specialdatatype"/>
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
							<td>ר�����ݱ��:</td>
							<td><input type="text" id="specialdataid" name="specialdataid" style="width:180px"/></td>
						</tr>
						<tr>
							<td>ר����������:</td>
							<td><input type="text" id="specialdataname" name="specialdataname" style="width:180px"/></td>
						</tr>
						<tr>
							<td>ר��������Դ:</td>
							<td><input type="text" id="specialdatasource" name="specialdatasource" style="width:180px"/></td>
						</tr>
						<tr>
							<td>ר����������:</td>
							<td><input type="text" id="specialdatatype" name="specialdatatype" style="width:180px"/></td>
						</tr>
						<tr>
							<td>��ע:</td>
							<td><input type="text" id="remarks" name="remarks" style="width:180px"/></td>
						</tr>
						<tr>
							<td>ר������:</td>
							<td><input type="text" id="specialdatafile" name="specialdatafile" style="width:180px"/></td>
						</tr>
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
