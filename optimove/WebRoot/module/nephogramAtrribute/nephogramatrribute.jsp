<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<html>
	<head>
		<title>����</title>
		<jsp:include page="/module/common/comm.jsp"></jsp:include>
		<script type="text/javascript" charset="GBK">
var searchUrl = "<%=request.getContextPath()%>/nephogramatrributes/searchNephogramatrribute.do";
var updateUrl = "<%=request.getContextPath()%>/nephogramatrributes/update.do";
var insertUrl = "<%=request.getContextPath()%>/nephogramatrributes/insert.do";
var deleteUrl = "<%=request.getContextPath()%>/nephogramatrributes/delete.do";
ajaxConstants("tblNephogramModelMag|nephogramModelId|nephogramModelName|orderBy=nephogramModelId");
$(function() {
	$('#dataList').datagrid( {
		title : '�б�',
		iconCls : 'icon-edit',//ͼ��  
		//width: 700,  
		height : 'auto',
		nowrap : false,
		striped : true,
		border : true,
		collapsible : false,//�Ƿ���۵���  
		fit : true,//�Զ���С  
		url : '#',
		remoteSort : false,
		singleSelect : false,//�Ƿ�ѡ  
		pagination : true,//��ҳ�ؼ�  
		rownumbers : true,//�к�  
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		} ] ],
		url : searchUrl,
		toolbar : '#tb',
		columns : [ [ {
			field : 'nephogramattrid',
			title : '��ͼ���Ա��',
			width : 100,
			align : 'center'
		}, {
			field : 'nephogrammodelid',
			title : '��ͼģ������',
			width : 100,
			align : 'center',
			formatter : function(value, row, index) {
				return getConstantDisplay('tblNephogramModelMag', value);
			}
		}, {
			field : 'attributecode',
			title : '���Դ���',
			width : 100,
			align : 'center'
		}, {
			field : 'attributename',
			title : '��������',
			width : 100,
			align : 'center'
		}, {
			field : 'attrtype',
			title : '��������',
			width : 100,
			align : 'center'
		}, {
			field : 'remarks',
			title : '��ע',
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
	$(p).pagination( {
		pageSize : 10,//ÿҳ��ʾ�ļ�¼������Ĭ��Ϊ10  
		pageList : [ 10, 20, 30 ],//��������ÿҳ��¼�������б�  
		beforePageText : '��',//ҳ���ı���ǰ��ʾ�ĺ���  
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	})
});
</script>
	</head>

	<body class="easyui-layout">
		<div region="center">
			<div id='dataList'>
				<div id="tb" style="padding: 5px; height: auto">
					<div style="margin-bottom: 5px">
						<a href="#" class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="showAddwindow({title:'����'})">����</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
							plain="true"
							onclick="showUpdate({title:'�޸�',readonlyFields:['nephogramattrid']});">�޸�</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="delRowData({id:'nephogramattrid'});">ɾ��</a>
					</div>
					<div>
						<form id='searchForm' action="" method="post">
							��ͼ���Ա��:
							<input type="text" id="nephogramattrid" name="nephogramattrid" />
							���Դ���:
							<input type="text" id="attributecode" name="attributecode" />
							<input type="button" onclick="loadList(1);" value="��ѯ" />
						</form>
					</div>
				</div>
			</div>
		</div>

		<div style="visibility: hidden">
			<div id="addwindow" title="���"
				style="width: 600px; height: 350px; padding: 10px">
				<form id='addForm' action="" method="post">
					<table>
						<tr>
							<td>
								���Ա��:
							</td>
							<td>
								<input type="text" id="nephogramattrid" name="nephogramattrid"
									style="width: 180px" />
							</td>
							<td>
								��ͼģ��:
							</td>
							<td>
									<select id="nephogrammodelid" name="nephogrammodelid"
									style="width: 180px" constantId="tblNephogramModelMag"></select>
							</td>
						</tr>
						<tr>
							<td>
								���Դ���:
							</td>
							<td>
								<input type="text" id="attributecode" name="attributecode"
									style="width: 180px" />
							</td>
							<td>
								��������:
							</td>
							<td>
								<input type="text" id="attributename" name="attributename"
									style="width: 180px" />
							</td>
						</tr>
						<tr>
							<td>
								��������:
							</td>
							<td>
								<select id="attrtype" name="attrtype" style="width: 180px">
									<option value="">��ѡ��</option>
									<option value="�����ĵ�">�����ĵ�</option>
									<option value="��ַ�ļ�">��ַ�ļ�</option>
									<option value="Ӱ��ϵͳ">Ӱ��ϵͳ</option>
									<option value="�ӿ�">�ӿ�</option>
								</select>
							</td>
							<td>
								��ע:
							</td>
							<td>
								<input type="text" id="remarks" name="remarks"
									style="width: 180px" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>
