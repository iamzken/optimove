<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>����</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/transmanagers/searchTransmanager.do";
   var updateUrl = "<%=request.getContextPath()%>/transmanagers/update.do";
   var insertUrl = "<%=request.getContextPath()%>/transmanagers/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/transmanagers/delete.do";
   ajaxConstants("tysfbz,tblTransTemplateManager|templateCode|templateName|;orderBy=templateName");
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
                   		{field:'transcode',title:'���״���',width:100,align:'center'},
                   		{field:'transname',title:'��������',width:100,align:'center'},
                   		{field:'templatecode',title:'����ģ��',width:300,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblTransTemplateManager',value); 
           				}},
                   		{field:'iscamera',title:'�Ƿ�����',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tysfbz',value); 
           				}},
                   		{field:'isrecognition',title:'�Ƿ�ʶ��',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tysfbz',value); 
           				}},
                   		{field:'islocation',title:'��¼����λ��',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tysfbz',value); 
           				}},
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸�',readonlyFields:['transcode']});">�޸�</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'transcode'});">ɾ��</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				���״���:
				<input type="text" id="transcode" name="transcode"/>
				��������:
				<input type="text" id="transname" name="transname"/>
				����ģ��:
				<select id="templatecode" name="templatecode" constantId="tblTransTemplateManager" style="width:120px"></select>
				
				
				<input type="button" onclick="loadList(1);" value="��ѯ"/>
			</form>
		</div>
	</div>
		</div>
	</div>
	
	<div style="visibility:hidden" >
		<div id="addwindow"  title="����" style="width:700px;height:350px;padding:10px">
			<form id='addForm' action="" method="post">
				<table>
						<tr>
							<td>���״���:</td>
							<td><input type="text" id="transcode" name="transcode" style="width:180px"/></td>
						</tr>
						<tr>
							<td>��������:</td>
							<td><input type="text" id="transname" name="transname" style="width:180px"/></td>
						</tr>
						<tr>
							<td>����ģ��:</td>
							<td><select id="templatecode" name="templatecode" constantId="tblTransTemplateManager" style="width:180px"></select></td>
						</tr>
						<tr>
							<td>�Ƿ�����:</td>
							<td><select id="iscamera" name="iscamera" constantId="tysfbz" style="width:180px"></select></td>
					
						</tr>
						<tr>
							<td>�Ƿ�ʶ��:</td>
							<td><select id="isrecognition" name="isrecognition" constantId="tysfbz" style="width:180px"></select></td>
					
						</tr>
						<tr>
							<td>��¼����λ��:</td>
							<td><select id="islocation" name="islocation" constantId="tysfbz" style="width:180px"></select></td>
					
						</tr>
						
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>