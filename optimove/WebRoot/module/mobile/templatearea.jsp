<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>����</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/templateareas/searchTemplatearea.do";
   var updateUrl = "<%=request.getContextPath()%>/templateareas/update.do";
   var insertUrl = "<%=request.getContextPath()%>/templateareas/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/templateareas/delete.do";
   ajaxConstants("tblTransTemplateManager|templateCode|templateName|;orderBy=templateName");
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
                   		{field:'id',title:'id',width:100,align:'center'},
                   		{field:'templatecode',title:'ģ������',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblTransTemplateManager',value); 
           				}},
                   		{field:'areacode',title:'�������',width:100,align:'center'},
                   		{field:'areaname',title:'��������',width:100,align:'center'},
                   		{field:'areaorder',title:'��ʾ˳��',width:100,align:'center'},
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸�',readonlyFields:['id']});">�޸�</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'id'});">ɾ��</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				
				ģ������:
				<select id="templatecode" name="templatecode" constantId="tblTransTemplateManager" style="width:120px"></select>
				
				�������:
				<input type="text" id="areacode" name="areacode"/>
				��������:
				<input type="text" id="areaname" name="areaname"/>
				
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
							<td></td>
							<td><input type="hidden" id="id" name="id" style="width:120px"/></td>
						</tr>
						<tr>
							<td>ģ������:</td>
							<td><select id="templatecode" name="templatecode" constantId="tblTransTemplateManager" style="width:120px"></select></td>
						</tr>
						<tr>
							<td>�������:</td>
							<td><input type="text" id="areacode" name="areacode" style="width:120px"/></td>
						</tr>
						<tr>
							<td>��������:</td>
							<td><input type="text" id="areaname" name="areaname" style="width:120px"/></td>
						</tr>
						<tr>
							<td>��ʾ˳��:</td>
							<td><input type="text" id="areaorder" name="areaorder" style="width:120px"/></td>
						</tr>
						
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
