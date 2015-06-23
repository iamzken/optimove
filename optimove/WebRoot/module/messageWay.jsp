<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<html>
	<head>
		<title>��Ϣ����</title>
		<jsp:include page="/module/common/comm.jsp"></jsp:include>
		<script type="text/javascript" charset="GBK">
var searchUrl = "<%=request.getContextPath()%>/budgetScheme/searchBudgetScheme.do";
var updateUrl = "<%=request.getContextPath()%>/budgetScheme/update.do";
var insertUrl = "<%=request.getContextPath()%>/budgetScheme/insert.do";
var deleteUrl = "<%=request.getContextPath()%>/budgetScheme/delete.do";
var setPowerUrl = "<%=request.getContextPath()%>/subjectpowers/update.do";
ajaxConstants("tbluserinfo|GRPID|GRPCNAME");
ajaxConstants("tblnewbranchinfo|BRANCHCODE|NAME");
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
			field : 'subjectId',
			title : '�������',
			width : 150,
			align : 'center'
		}, {
			field : 'subjectName',
			title : '��������',
			width : 200,
			align : 'center'
		}, {
			field : 'madePerson',
			title : '������',
			width : 180,
			align : 'center'
		}, {
			field : 'status',
			title : '����״̬',
			width : 200,
			align : 'center'
		}, {
			field : 'powerStatus',
			title : '�Ƿ�����Ȩ��',
			width : 200,
			align : 'center',
			formatter : function(powerStatus) {
				if (powerStatus == null) {
					return "��";
				} else {
					return "��";
				}
			}
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
							plain="true" onclick="showAddMesswway({title:'��Ϣ����'})">��Ϣ����</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="delRowData({id:'subjectId'});">ɾ��</a>|
					</div>
					<div>
						<form id='searchForm' action="" method="post">
							�������:
							<input type="text" id="subjectId" name="subjectId">
							��������:
							<input type="text" id="subjectName" name="subjectName">
							Ȩ������:
							<input type="text" id="powerStatus" name="powerStatus">
							<input type="button" onclick="loadList(1);" value="��ѯ">
						</form>
					</div>
				</div>
			</div>
		</div>

		<div style="visibility: hidden">
			<div id="addmessageway" title="��Ϣ��Ϣ"
				style="width: 580px; height: 170px; padding: 10px;overflow: hidden;">
				<form id='addmesswayForm' action="" method="post">
					<table>
						<tr>
							<td>
								��&nbsp;��&nbsp;��&nbsp;:
							</td>
							<td>
								<input type="text" id="subjectId" name="subjectId"
									style="width: 180px">
							</td>
							<td>
								��������:
							</td>
							<td>
								<input type="text" id="subjectName" class="easyui-datebox" name="subjectName"
									style="width: 180px">
							</td>
						</tr>
						<tr>
							<td>
								��Ϣ����:
							</td>
							<td>
								<select name="subjectId" style="width: 180px">
									<option>��ѡ��...</option>
									<option>��������</option>
									<option>Ԥ����</option>
									<option>������Ϣ</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								֪ͨ��ʽ:
							</td>
							<td colspan="3">
								<input type="checkbox"/>���칫��<input type="checkbox"/>E-mail<input type="checkbox"/>����<input type="checkbox"/>΢��<input type="checkbox"/>�칫ƽ̨
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>
</html>
