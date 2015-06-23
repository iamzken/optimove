<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>��֧����Ϣ����</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript">
   ajaxConstants("Grade");
   var searchUrl = "<%=request.getContextPath()%>/newBranchInfos/searchNewBranchInfo.do";
   var updateUrl = "<%=request.getContextPath()%>/newBranchInfos/update.do";
   var insertUrl = "<%=request.getContextPath()%>/newBranchInfos/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/newBranchInfos/delete.do";
	$(function() {
	    $('#dataList').datagrid({  
	        //title:'�б�',  
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
                   		{field:'branchCode',title:'��֧�д���',width:100,align:'center'},
                   		{field:'grade',title:'��������',width:100,align:'center',formatter: function(value,row,index){ 
                   		    return getConstantDisplay('Grade',value); 
                   		}},
                   		{field:'upCode',title:'�ϼ��д���',width:100,align:'center'},
                   		{field:'name',title:'����',width:150,align:'center'},
                   		{field:'address',title:'��ַ',width:120,align:'center'},
                   		{field:'zipcode',title:'�ʱ�',width:100,align:'center'},
                   		{field:'telephone',title:'�绰����',width:100,align:'center'},
                   		{field:'fax',title:'����',width:100,align:'center'},
                   		//{field:'status',title:'��Ӫ״̬',width:100,align:'center'},
                   		//{field:'account',title:'���������������˺�',width:100,align:'center'},
                   		//{field:'linkManName',title:'��ϵ������',width:100,align:'center'},
                   		//{field:'linkManDept',title:'����',width:100,align:'center'},
                   		//{field:'linkManPos',title:'ְ��',width:100,align:'center'},
                   		//{field:'linkManTel',title:'��ϵ�˵绰',width:100,align:'center'},
                   		//{field:'linkManFax',title:'��ϵ�˴���',width:100,align:'center'},
                   		//{field:'linkManEmail',title:'��ϵ��Email',width:100,align:'center'},
                   		{field:'createDate',title:'��������',width:100,align:'center'},
                   		{field:'operatorCode',title:'����Ա��',width:100,align:'center'}
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
	    paginationConfig('dataList');
	});
	</script>
  </head>
  
  <body class="easyui-layout" >
	<div  region="center" >
		<div id='dataList'>
			<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddwindow({title:'����'})">����</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸�',readonlyFields:['branchCode']});">�޸�</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'branchCode'});">ɾ��</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
			<table>
				<tr>
					<td>��֧�д���:</td>
					<td><input type="text" id="sbranchCode" name="branchCode"></td>
					<td>��������:</td>
					<td><select id="sgrade" name="grade" constantId="Grade" style="width: 80px;"></select></td>
					<!--  
					<td>�ϼ��д���:</td>
					<td><input type="text" id="supCode" name="upCode"></td>
					-->
					<td>����:</td>
					<td><input type="text" id="sname" name="name"></td>
					<td><a href="#" class="easyui-linkbutton" onclick="javascript:loadList(1);" iconCls="icon-search">��&nbsp;&nbsp;&nbsp;ѯ</a></td>
				</tr>
			</table>
				<%-- 
				��ַ:
				<input type="text" id="saddress" name="address">
				�ʱ�:
				<input type="text" id="szipcode" name="zipcode">
				�绰����:
				<input type="text" id="stelephone" name="telephone">
				����:
				<input type="text" id="sfax" name="fax">
				��Ӫ״̬:
				<input type="text" id="sstatus" name="status">
				���������������˺�:
				<input type="text" id="saccount" name="account">
				��ϵ������:
				<input type="text" id="slinkManName" name="linkManName">
				����:
				<input type="text" id="slinkManDept" name="linkManDept">
				ְ��:
				<input type="text" id="slinkManPos" name="linkManPos">
				��ϵ�˵绰:
				<input type="text" id="slinkManTel" name="linkManTel">
				��ϵ�˴���:
				<input type="text" id="slinkManFax" name="linkManFax">
				��ϵ��Email:
				<input type="text" id="slinkManEmail" name="linkManEmail">
				��������:
				<input type="text" id="screateDate" name="createDate">
				����Ա��:
				<input type="text" id="soperatorCode" name="operatorCode">
				--%>
				
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
							<td>��֧�д���:</td>
							<td><input type="text" id="branchCode" name="branchCode" style="width:120px"></td>
							<td>��������:</td>
							<td><select id="grade" name="grade" constantId="Grade" style="width:120px"></select></td>
						</tr>
						<tr>
							<td>�ϼ��д���:</td>
							<td><input type="text" id="upCode" name="upCode" style="width:120px"></td>
							<td>����:</td>
							<td><input type="text" id="name" name="name" style="width:120px"></td>
						</tr>
						<tr>
							<td>��ַ:</td>
							<td><input type="text" id="address" name="address" style="width:120px"></td>
							<td>�ʱ�:</td>
							<td><input type="text" id="zipcode" name="zipcode" style="width:120px"></td>
						</tr>
						<tr>
							<td>�绰����:</td>
							<td><input type="text" id="telephone" name="telephone" style="width:120px"></td>
							<td>����:</td>
							<td><input type="text" id="fax" name="fax" style="width:120px"></td>
						</tr>
						<tr>
							<td>��Ӫ״̬:</td>
							<td><input type="text" id="status" name="status" style="width:120px"></td>
							<td>���������������˺�:</td>
							<td><input type="text" id="account" name="account" style="width:120px"></td>
						</tr>
						<tr>
							<td>ְ��:</td>
							<td><input type="text" id="linkManPos" name="linkManPos" style="width:120px"></td>
							<td>����:</td>
							<td><input type="text" id="linkManDept" name="linkManDept" style="width:120px"></td>
						</tr>
						<tr>
							
							<td>��ϵ������:</td>
							<td><input type="text" id="linkManName" name="linkManName" style="width:120px"></td>
							<td>��ϵ�˵绰:</td>
							<td><input type="text" id="linkManTel" name="linkManTel" style="width:120px"></td>
						</tr>
						<tr>
							<td>��ϵ�˴���:</td>
							<td><input type="text" id="linkManFax" name="linkManFax" style="width:120px"></td>
							<td>��ϵ��Email:</td>
							<td><input type="text" id="linkManEmail" name="linkManEmail" style="width:120px"></td>
						</tr>
						<tr style="display: none">
							<td>��������:</td>
							<td><input type="text" id="createDate" name="createDate" style="width:120px"></td>
							<td>����Ա��:</td>
							<td><input type="text" id="operatorCode" name="operatorCode" style="width:120px"></td>
						</tr>
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
