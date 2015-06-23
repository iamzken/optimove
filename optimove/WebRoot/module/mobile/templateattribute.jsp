<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>����</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/templateattributes/searchTemplateattribute.do";
   var updateUrl = "<%=request.getContextPath()%>/templateattributes/update.do";
   var insertUrl = "<%=request.getContextPath()%>/templateattributes/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/templateattributes/delete.do";

   ajaxConstants("sjlx,tysfbz,tblTransTemplateManager&ttmp|templateCode|templateName|;orderBy=templateName,tblTemplateArea|areaCode|areaName|;orderBy=areaName,tblLookupValues&tblLookupValues-sjlx|LookupCode|Meaning|LookupType='sjlx';orderBy=LookupCode,tblLookupValues|LookupCode|Meaning|LookupType='srlx';orderBy=LookupCode");	
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
           		    		return getConstantDisplay('ttmp',value); 
           				}},
                   		{field:'areacode',title:'��������',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblTemplateArea',value); 
           				}},
                   		{field:'attributecode',title:'���Դ���',width:100,align:'center'},
                   		{field:'attributename',title:'��������',width:100,align:'center'},
                   		{field:'attributewidth',title:'��ʾ���',width:100,align:'center'},
                   		{field:'attributeorder',title:'��ʾ˳��',width:100,align:'center'},
                   		{field:'attributetype',title:'��������',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblLookupValues',value); 
           				}},
           				{field:'typevalue',title:'����ֵ',width:100,align:'center'},
                   		{field:'datatype',title:'��������',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblLookupValues-sjlx',value); 
           				}},
                   		{field:'ispk',title:'�Ƿ�Ψһ',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tysfbz',value); 
           				}},
                   		{field:'isempty',title:'�Ƿ����',width:100,align:'center',formatter: function(value,row,index){ 
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸�',readonlyFields:['id']});">�޸�</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'id'});">ɾ��</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				ģ������:
			
				<select id="templatecode" name="templatecode" constantId="ttmp" style="width:120px"></select>
				
				��������:
				<select id="areacode" name="areacode" constantId="tblTemplateArea" style="width:120px"></select>
				
				���Դ���:
				<input type="text" id="attributecode" name="attributecode"/>
				��������:
				<input type="text" id="attributename" name="attributename"/>
				
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
							<td style="width: 100">ģ������:</td>
							<td><select id="templatecode" name="templatecode" constantId="ttmp" style="width:180px"></select></td>
						
							<td style="width: 100">��������:</td>
							<td><select id="areacode" name="areacode" constantId="tblTemplateArea" style="width:180px"></select></td>
						</tr>
						<tr>
							<td>���Դ���:</td>
							<td><input type="text" id="attributecode" name="attributecode" style="width:180px"/></td>
						
							<td>��������:</td>
							<td><input type="text" id="attributename" name="attributename" style="width:180px"/></td>
						</tr>
						<tr>
							<td>��ʾ���:</td>
							<td><input type="text" id="attributewidth" name="attributewidth" style="width:180px"/></td>
						
							<td>��ʾ˳��:</td>
							<td><input type="text" id="attributeorder" name="attributeorder" style="width:180px"/></td>
						</tr>
						<tr>
							<td>��������:</td>
							<td><select id="attributetype" name="attributetype" constantId="tblLookupValues" style="width:180px"></select></td>
					
						
							<td>����ֵ:</td>
							<td><textarea name="typevalue" rows="5" cols="20" style="width:180px"></textarea></td>
						
							
						</tr>
						<tr>
							<td>��������:</td>
							<td><select id="datatype" name="datatype" constantId="sjlx" style="width:180px"></select></td>
					
							<td>�Ƿ�Ψһ:</td>
							<td><select id="ispk" name="ispk" constantId="tysfbz" style="width:180px"></select></td>
					
						
						</tr>
						<tr>
							<td>�Ƿ����:</td>
							<td><select id="isempty" name="isempty" constantId="tysfbz" style="width:180px"></select></td>
					
							<td></td>
							<td><input type="hidden" id="id" name="id" style="width:180px"/></td>
						</tr>
						
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
