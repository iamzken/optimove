<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>����</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/servicemanagers/searchServicemanager.do";
   var updateUrl = "<%=request.getContextPath()%>/servicemanagers/update.do";
   var insertUrl = "<%=request.getContextPath()%>/servicemanagers/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/servicemanagers/delete.do";
   ajaxConstants("tblSystemManager|sysCode|sysName|;orderBy=sysName");
	
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
                   		{field:'servicecode',title:'������',width:100,align:'center'},
                   		{field:'servicecnname',title:'����������',width:100,align:'center'},
                   		{field:'sourcesystem',title:'Դϵͳ',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblSystemManager',value); 
           				}},
                   		{field:'linkurl',title:'���õ�ַ',width:100,align:'center'},
                   		{field:'remark',title:'��ע',width:100,align:'center'},
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸�',readonlyFields:['servicecode']});">�޸�</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'servicecode'});">ɾ��</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				������:
				<input type="text" id="servicecode" name="servicecode"/>
				����������:
				<input type="text" id="servicecnname" name="servicecnname"/>
				Դϵͳ:
				<select id="sourcesystem" name="sourcesystem" constantId="tblSystemManager" style="width:120px"></select>
				
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
							<td>������:</td>
							<td><input type="text" id="servicecode" name="servicecode" style="width:120px"/></td>
						</tr>
						<tr>
							<td>����������:</td>
							<td><input type="text" id="servicecnname" name="servicecnname" style="width:120px"/></td>
						</tr>
						<tr>
							<td>Դϵͳ:</td>
							<td><select id="sourcesystem" name="sourcesystem" constantId="tblSystemManager" style="width:120px"></select></td>
					
						</tr>
						<tr>
							<td>���õ�ַ:</td>
							<td><input type="text" id="linkurl" name="linkurl" style="width:120px"/></td>
						</tr>
						<tr>
							<td>��ע:</td>
							<td><input type="text" id="remark" name="remark" style="width:120px"/></td>
						</tr>
						
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
