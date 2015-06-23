<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>����</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/billsapplys/searchBillsapply.do";
   var updateUrl = "<%=request.getContextPath()%>/billsapplys/update.do";
   var insertUrl = "<%=request.getContextPath()%>/billsapplys/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/billsapplys/delete.do";
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
                   		{field:'custno',title:'�ͻ���',width:100,align:'center'},
                   		{field:'businessid',title:'ҵ����ˮ��',width:100,align:'center'},
                   		{field:'businesstype',title:'ҵ������',width:100,align:'center'},
                   		{field:'productcode',title:'��Ʒ����',width:100,align:'center'},
                   		{field:'tableid',title:'��ID',width:100,align:'center'},
                   		{field:'transchannel',title:'��������',width:100,align:'center'},
                   		{field:'fkrqc',title:'������ȫ��',width:100,align:'center'},
                   		{field:'fkrzh',title:'�������˺�',width:100,align:'center'},
                   		{field:'fkryh',title:'�����˿�������',width:100,align:'center'},
                   		{field:'skrqc',title:'�տ���ȫ��',width:100,align:'center'},
                   		{field:'skrzh',title:'�տ����˺�',width:100,align:'center'},
                   		{field:'skryh',title:'�տ��˿�������',width:100,align:'center'},
                   		{field:'cpje',title:'��Ʊ���',width:100,align:'center'},
                   		{field:'dqr',title:'��Ʊ������',width:100,align:'center'},
                   		{field:'jyhthm',title:'���׺�ͬ����',width:100,align:'center'},
                   		{field:'fkrkhhhh',title:'�����˿������к�',width:100,align:'center'},
                   		{field:'fkrkhhdz',title:'�����˿����е�ַ',width:100,align:'center'},
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
				</div>
		<div>
			<form  id='searchForm' action="" method="post">
				�ͻ���:
				<input type="text" id="custno" name="custno"/>
				
				������:
				<input type="text" id="fkrqc" name="fkrqc"/>
			
				�տ���:
				<input type="text" id="skrqc" name="skrqc"/>
				
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
							<td><input type="text" id="custno" name="custno" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="businessid" name="businessid" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="businesstype" name="businesstype" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="productcode" name="productcode" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="tableid" name="tableid" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="transchannel" name="transchannel" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="fkrqc" name="fkrqc" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="fkrzh" name="fkrzh" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="fkryh" name="fkryh" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="skrqc" name="skrqc" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="skrzh" name="skrzh" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="skryh" name="skryh" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="cpje" name="cpje" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="dqr" name="dqr" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="jyhthm" name="jyhthm" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="fkrkhhhh" name="fkrkhhhh" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="fkrkhhdz" name="fkrkhhdz" style="width:120px"/></td>
						</tr>
						
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
