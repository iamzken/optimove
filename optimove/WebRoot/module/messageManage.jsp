<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<html>
	<head>
		<title>�������</title>
		<jsp:include page="/module/common/comm.jsp"></jsp:include>
		<script type="text/javascript" charset="GBK">
var searchUrl = "<%=request.getContextPath()%>/notices/searchNotice.do";
var updateUrl = "<%=request.getContextPath()%>/notices/update.do";
var insertUrl = "<%=request.getContextPath()%>/notices/insert.do";
var deleteUrl = "<%=request.getContextPath()%>/notices/delete.do";
var getTreeDataUrl = "<%=request.getContextPath()%>/subjectpowers/getTreeData.do";
$(function() {
	$('#dataList').datagrid( {
		//title:'�б�',  
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
			field : 'noticecode',
			title : '������',
			width : 107,
			align : 'center'
		}, {
			field : 'noticetitle',
			title : '�������',
			width : 120,
			align : 'center'
		}, {
			field : 'noticecontent',
			title : '��������',
			width : 150,
			align : 'center'
		}, {
			field : 'operatetime',
			title : '����ʱ��',
			width : 150,
			align : 'center'
		}, {
			field : 'startdate',
			title : '��ʼʱ��',
			width : 150,
			align : 'center'
		},{
			field : 'enddate',
			title : '����ʱ��',
			width : 150,
			align : 'center'
		},{
			field : 'importantlevel',
			title : '�����̶�',
			width : 120,
			align : 'center'
		},{
			field : 'createrid',
			title : '������',
			width : 150,
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
	paginationConfig('dataList');
});
</script>
	</head>

	<body class="easyui-layout">
		<div region="center">
			<div id='dataList'>
				<div id="tb" style="padding: 5px; height: auto">
					<div style="margin-bottom: 5px">
						<a href="#" class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="showAddMesswindow({title:'����'})">��������</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="delRowData({id:'noticecode'});">ɾ��</a>|
					</div>
					<div>
						<form id='searchForm' action="" method="post">
							������:
							<input type="text" id="noticecode" name="noticecode">
							�������:
							<input type="text" id="noticetitle" name="noticetitle">
							����ʱ��:
							<input type="text" id="operatetime" name="operatetime" class="easyui-datebox">
							<input type="button" onclick="loadList(1);" value="��ѯ">
						</form>
					</div>
				</div>
			</div>
		</div>

		<div style="visibility: hidden">
			<div id="addmessagewindow" title="������Ϣ"
				style="width: 600px; height: 350px; padding: 10px;overflow-x: hidden;">
				<form id='addmessageForm' action="" method="post">
					<table>
						<tr>
							<td>
								������:
							</td>
							<td>
								<input type="text" id="noticecode" name="noticecode"
									style="width: 180px">
							</td>
							<td>
								��������:
							</td>
							<td>
								<input type="text" id="noticetitle" name="noticetitle"
									style="width: 180px">
							</td>
						</tr>
						<tr>
							<td>
								��ʼ����:
							</td>
							<td>
								<input type="text" id="startdate" class="easyui-datebox" name="startdate"
									style="width: 180px">
							</td>
							<td>
								��������:
							</td>
							<td>
								<input type="text" id="enddate" class="easyui-datebox" name="enddate"
									style="width: 180px">
							</td>
						</tr>
						<tr>
							<td>
								��Ҫ����:
							</td>
							<td>
								<input type="text" id="importantlevel" name="importantlevel"
									style="width: 180px">
							</td>
							<td>
								��ǰ״̬:
							</td>
							<td>
								<input type="text" id="noticestatus" name="noticestatus"
									style="width: 180px"><input name="noticebranch" id="noticebranch" type="hidden"/><input name="noticedept" id="noticedept" type="hidden"/><input name="noticegroup" id="noticegroup" type="hidden"/>
							</td>
						</tr>
						<tr>
							<td valign="top">�������ݣ�</td>
							<td colspan="3"><textarea rows="2" id="noticecontent" name="noticecontent" style="width:425px;"></textarea></td>
						</tr>
					</table>
					<div id="messageTab">
						<div id="branch" class="ztree" title="����"></div>
						<div id="dept" class="ztree" title="����"></div>
						<div id="group" class="ztree" title="��ɫ"></div>
					</div>
				</form>
			</div>
		</div>

	</body>
</html>
