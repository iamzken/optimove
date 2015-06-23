<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>����</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/businessproductmanagers/searchBusinessproductmanager.do";
   var updateUrl = "<%=request.getContextPath()%>/businessproductmanagers/update.do";
   var insertUrl = "<%=request.getContextPath()%>/businessproductmanagers/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/businessproductmanagers/delete.do";
   ajaxConstants("tblBusinessTypeManager|typeCode|typeName|;orderBy=typeName,tblTransTemplateManager|templateCode|templateName|;orderBy=templateName,tblFlowManager|flowCode|flowName|;orderBy=flowName");
	
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
	        toolbar:'#tb',//��Ʒ���롢��Ʒ���ơ�ҵ�����ࣨ����ҵ�����ࣩ������ģ�壨���ý���ģ�壩���������̣���������ģ�壩
	        columns:[[   
                   		{field:'productcode',title:'��Ʒ����',width:100,align:'center'},
                   		{field:'productname',title:'��Ʒ����',width:100,align:'center'},
                   		{field:'typecode',title:'ҵ������',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblBusinessTypeManager',value); 
           				}},
                   		{field:'templatecode',title:'����ģ��',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblTransTemplateManager',value); 
           				}},
                   		{field:'flowcode',title:'��������',width:100,align:'center',formatter: function(value,row,index){ 
           		    		return getConstantDisplay('tblFlowManager',value); 
           				}},
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸�',readonlyFields:['productcode']});">�޸�</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'productcode'});">ɾ��</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				��Ʒ����:
				<input type="text" id="productcode" name="productcode"/>
				��Ʒ����:
				<input type="text" id="productname" name="productname"/>
				ҵ������:
				<select id="typecode" name="typecode" constantId="tblBusinessTypeManager" style="width:180px"></select>
				
				
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
							<td>��Ʒ����:</td>
							<td><input type="text" id="productcode" name="productcode" style="width:180px"/></td>
						</tr>
						<tr>
							<td>��Ʒ����:</td>
							<td><input type="text" id="productname" name="productname" style="width:180px"/></td>
						</tr>
						<tr>
							<td>ҵ������:</td>
							<td><select id="typecode" name="typecode" constantId="tblBusinessTypeManager" style="width:180px"></select></td>
					
						</tr>
						<tr>
							<td>����ģ��:</td>
							<td><select id="templatecode" name="templatecode" constantId="tblTransTemplateManager" style="width:180px"></select></td>
					
						</tr>
						<tr>
							<td>��������:</td>
							<td><select id="flowcode" name="flowcode" constantId="tblFlowManager" style="width:180px"></select></td>
					
						</tr>
						<tr>
							<td>ͼƬ��ַ:</td>
							<td><input type="text" id="logourl" name="logourl" style="width:180px"/></td>
						
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
