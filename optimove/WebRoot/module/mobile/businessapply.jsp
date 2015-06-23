<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>����</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/businessapplys/searchBusinessapply.do";
   var updateUrl = "<%=request.getContextPath()%>/businessapplys/update.do";
   var insertUrl = "<%=request.getContextPath()%>/businessapplys/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/businessapplys/delete.do";
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
                   		{field:'businessid',title:'',width:100,align:'center'},
                   		{field:'businesstype',title:'',width:100,align:'center'},
                   		{field:'productcode',title:'',width:100,align:'center'},
                   		{field:'tableid',title:'',width:100,align:'center'},
                   		{field:'fieldname',title:'',width:100,align:'center'},
                   		{field:'fieldcnname',title:'',width:100,align:'center'},
                   		{field:'fieldvalue',title:'',width:100,align:'center'},
                   		{field:'transchannel',title:'',width:100,align:'center'},
                   		{field:'operator',title:'',width:100,align:'center'},
                   		{field:'operatororg',title:'',width:100,align:'center'},
                   		{field:'updatedate',title:'',width:100,align:'center'},
                   		{field:'updatetime',title:'',width:100,align:'center'}
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸�',readonlyFields:['businessid']});">�޸�</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'businessid'});">ɾ��</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				:
				<input type="text" id="businessid" name="businessid"/>
				:
				<input type="text" id="businesstype" name="businesstype"/>
				:
				<input type="text" id="productcode" name="productcode"/>
				:
				<input type="text" id="tableid" name="tableid"/>
				:
				<input type="text" id="fieldname" name="fieldname"/>
				:
				<input type="text" id="fieldcnname" name="fieldcnname"/>
				:
				<input type="text" id="fieldvalue" name="fieldvalue"/>
				:
				<input type="text" id="transchannel" name="transchannel"/>
				:
				<input type="text" id="operator" name="operator"/>
				:
				<input type="text" id="operatororg" name="operatororg"/>
				:
				<input type="text" id="updatedate" name="updatedate"/>
				:
				<input type="text" id="updatetime" name="updatetime"/>
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
							<td>:</td>
							<td><input type="text" id="businessid" name="businessid" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="businesstype" name="businesstype" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="productcode" name="productcode" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="tableid" name="tableid" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="fieldname" name="fieldname" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="fieldcnname" name="fieldcnname" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="fieldvalue" name="fieldvalue" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="transchannel" name="transchannel" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="operator" name="operator" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="operatororg" name="operatororg" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="updatedate" name="updatedate" style="width:180px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="updatetime" name="updatetime" style="width:180px"/></td>
						</tr>
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
