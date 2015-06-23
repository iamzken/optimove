<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>����</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
	<script type="text/javascript" charset="GBK">
	   var searchUrl = "<%=request.getContextPath()%>/modelattributes/searchModelattribute.do";
	   var updateUrl = "<%=request.getContextPath()%>/modelattributes/update.do";
	   var insertUrl = "<%=request.getContextPath()%>/modelattributes/insert.do";
	   var deleteUrl = "<%=request.getContextPath()%>/modelattributes/delete.do";
		$(function() {
			$('#dataList').datagrid({
				title : '�б�',
				iconCls : 'icon-edit',//ͼ��  
				//width: 700,  
				height : 'auto',
				nowrap : false,
				striped : true,
				border : true,
				collapsible : false,//�Ƿ���۵���  
				fit : true,//�Զ���С  
				remoteSort : false,
				singleSelect : false,//�Ƿ�ѡ  
				pagination : true,//��ҳ�ؼ�  
				rownumbers : true,//�к�  
				frozenColumns : [ [ {
					field : 'ck',
					checkbox : true
				} ] ],
				url : searchUrl,
				idField : 'id',
				toolbar : '#tb',
				columns : [ [
				{
					field : 'tablename',
					title : 'ģ�ͱ����',
					width : 100,
					align : 'center'
				}, {
					field : 'modelattribute',
					title : 'ģ������',
					width : 100,
					align : 'center'
				}, {
					field : 'modelattributename',
					title : 'ģ����������',
					width : 100,
					align : 'center'
				}, {
					field : 'modelattributetype',
					title : 'ģ���������',
					width : 100,
					align : 'center'
				}, {
					field : 'modelattributeflag',
					title : 'ģ�����Ա�ʶ',
					width : 100,
					align : 'center'
				} ] ],
				onLoadSuccess : function(data) {
					data = convertJson(data);
					if (data.result != 'ok') {
						showBox("��ʾ��Ϣ", data.errorMsg, 'warning');
					}
				}
	
			});
	
			//���÷�ҳ�ؼ�  
			var p = $('#dataList').datagrid('getPager');
			$(p).pagination({
				pageSize : 10,//ÿҳ��ʾ�ļ�¼������Ĭ��Ϊ10  
				pageList : [ 10, 20, 30 ],//��������ÿҳ��¼�������б�  
				beforePageText : '��',//ҳ���ı���ǰ��ʾ�ĺ���  
				afterPageText : 'ҳ    �� {pages} ҳ',
				displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
			})
		});
	</script>
</head>
  
  <body class="easyui-layout" >
	<div  region="center" >
		<div id='dataList'>
			<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddwindow({title:'����'})">&nbsp;����&nbsp;</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸�',readonlyFields:['id']});">&nbsp;�޸�&nbsp;</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'id'});">&nbsp;ɾ��&nbsp;</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				ģ�ͱ����:
				<input type="text" id="tablename" name="tablename"/>
				ģ����������:
				<input type="text" id="modelattributename" name="modelattributename"/>
				ģ���������:
				<input type="text" id="modelattributetype" name="modelattributetype"/>
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
					<tr style="display: none;">
						<td>ID:</td>
						<td><input type="text" id="id" name="id" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr style="display: none;">
						<td>ģ�ͱ����:</td>
						<td><input type="text" id="tablename" name="tablename" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr>
						<td>ģ������:</td>
						<td><input type="text" id="modelattribute" name="modelattribute" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr>
						<td>ģ����������:</td>
						<td><input type="text" id="modelattributename" name="modelattributename" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr>
						<td>ģ���������:</td>
						<td><input type="text" id="modelattributetype" name="modelattributetype" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr style="display: none;">
						<td>ģ�����Ա�ʶ:</td>
						<td><input type="text" id="modelattributeflag" name="modelattributeflag" class="form-text input-margin" style="width:180px"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
