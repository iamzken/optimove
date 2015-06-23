<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>ģ�͹���</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
<script type="text/javascript" charset="GBK">
    var searchUrl = "<%=request.getContextPath()%>/mapmodelinfos/searchMapmodelinfo.do";
    var updateUrl = "<%=request.getContextPath()%>/mapmodelinfos/update.do";
    var insertUrl = "<%=request.getContextPath()%>/mapmodelinfos/insert.do";
    var deleteUrl = "<%=request.getContextPath()%>/mapmodelinfos/delete.do";
    
    var subSearchUrl = "<%=request.getContextPath()%>/modelattributes/searchModelattribute.do";
    var subUpdateUrl = "<%=request.getContextPath()%>/modelattributes/update.do";
    var subInsertUrl = "<%=request.getContextPath()%>/modelattributes/insert.do";
    var subDeleteUrl = "<%=request.getContextPath()%>/modelattributes/delete.do";
    
	$(function() {
		$('#dataList').datagrid({
			//title:'�б�',  
			//iconCls:'icon-edit',//ͼ��  
			width: 'auto',  
			height : 'auto',
			nowrap : false,
			striped : true,
			border : false,
			collapsible : false,//�Ƿ���۵���  
			fit : false,//�Զ���С  
			fitColumns : true,
			remoteSort : false,
			singleSelect : true,//�Ƿ�ѡ  
			pagination : true,//��ҳ�ؼ�  
			rownumbers : true,//�к�  
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			} ] ],
			url : searchUrl,
			toolbar : '#tb',
			idField : 'modelid',
			columns : [ [
			//{field:'modelid',title:'',width:100,align:'center'},
			{
				field : 'modelcode',
				title : 'ģ�ʹ���',
				width : 100,
				align : 'center'
			}, {
				field : 'modelname',
				title : 'ģ������',
				width : 100,
				align : 'center'
			}, {
				field : 'modeltype',
				title : 'ģ������',
				width : 100,
				align : 'center'
			}, {
				field : 'modeldatatable',
				title : 'ģ�����ݱ����',
				width : 150,
				align : 'center'
			}, {
				field : 'modelcreatetime',
				title : 'ģ�ʹ���ʱ��',
				width : 150,
				align : 'center'
			}, {
				field : 'modelupdatetime',
				title : 'ģ���޸�ʱ��',
				width : 250,
				align : 'center'
			} ] ],
			onLoadSuccess : function(data) {
				data = convertJson(data);
				if (data.result != 'ok') {
					showBox("��ʾ��Ϣ", data.errorMsg, 'warning');
				}
			},
			onSelect : function(index, data) {
				$('#subDataList').show();
				loadValueList(1);
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
		});
	});

	//�ӱ��ҳ
	function PaginationConfig(datagridId, executeFunction) {
		gridPager = $('#' + datagridId).datagrid('getPager');
		if (gridPager) {
			$(gridPager).pagination({
				onBeforeRefresh : function(pageNumber, pageSize) {
					eval(executeFunction + '(' + pageNumber + ')');
					return false;
				}, onSelectPage : function(pageNumber, pageSize) {
					$('#' + datagridId).datagrid('options').pageNumber = pageNumber;
					$('#' + datagridId).datagrid('options').pageSize = pageSize;
					eval(executeFunction + '(' + pageNumber + ')');
				}
			});
		}
	}

	function loadValueList(pageNumber) {
		var selectType = $('#dataList').datagrid('getSelected');
		if (!selectType) {
			showBox('��ܰ��ʾ', '��ѡҪ��ѯ������!', 'warning');
			return false;
		}
		
		$.post(subSearchUrl, {
			"tablename" : selectType.modeldatatable,
			"page" : pageNumber
		}, function(response) {
			response = convertJson(response);
			if (response.result != 'ok') {
				showBox("��ʾ��Ϣ", response.result, 'warning');
				return;
			}

			//ģ������
			$('#subDataList').datagrid({
				//title : '�б�',
				//iconCls : 'icon-edit',//ͼ��  
				//width: 700,  
				height : 'auto',
				nowrap : false,
				striped : true,
				border : false,
				collapsible : false,//�Ƿ���۵���  
				fit : false,//�Զ���С  
				fitColumns : true,
				remoteSort : false,
				singleSelect : true,//�Ƿ�ѡ  
				pagination : true,//��ҳ�ؼ�  
				rownumbers : true,//�к�  
				frozenColumns : [ [ {
					field : 'ck',
					checkbox : true
				} ] ],
				idField : 'id',
				toolbar : '#subTb',
				columns : [ [ {
					field : 'tablename',
					title : 'ģ�ͱ����',
					width : 100,
					align : 'center',
					formatter : function(tablename) {
						if (tablename == 'default') {
							return "Ĭ���ֶ�";
						} else {
							return tablename;
						}
					}
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
					width : 200,
					align : 'center',
					formatter : function(modelattributeflag) {
						if (modelattributeflag == '0') {
							return "Ĭ���ֶ�";
						} else {
							return "�Զ����ֶ�";
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

			refreshDatagrid("subDataList", pageNumber);
			$('#subDataList').datagrid('loadData', response);
			PaginationConfig('subDataList', 'loadValueList');
		});
	}

	function setAttrVal() {
		var selectType = $('#dataList').datagrid('getSelected');
		if (!selectType) {
			showBox('��ܰ��ʾ', '��ѡҪ��ѯ������!', 'warning');
			return false;
		}
		$('#tablename').val(selectType.modeldatatable)
	}

	function checkAttr(flag) {
		var rows = $('#subDataList').datagrid("getSelections");
		if (rows.length > 0) {
			for ( var i = 0; i < rows.length; i++) {
				if (rows[i].modelattributeflag == '0') {
					if (flag == 0) {
						showBox('��ܰ��ʾ', 'ϵͳĬ��ģ�����Բ��ܸ��£�������ѡ��!', 'warning');
					}
					if (flag == 1) {
						showBox('��ܰ��ʾ', 'ϵͳĬ��ģ�����Բ���ɾ����������ѡ��!', 'warning');
					}
					return false;
				}
			}
		}
		if (flag == 0) {
			subShowUpdate({ title : '�޸�', readonlyFields : [ 'id', 'modelattribute', 'modelattributetype' ] });
			$('#modelattributetype').show();
			$('#attributetype').hide();
		}
		if (flag == 1) {
			subDelRowData({ id : 'id' });
		}
	}
	
	function setAttrType() {
		$('#modelattributetype').val($('#attributetype').val());
	}
</script>
</head>
  
  <body class="easyui-layout" >
	<div  region="center" wrapper1 wrapper-content1 panel-fit gray-bg>
		<div id='dataList'>
			<div id="tb" style="padding:5px;height:auto">
				<div style="margin-bottom:5px">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddwindow({title:'����'});">����</a>|
					<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸�',readonlyFields:['modelid']});">�޸�</a>|
					<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'modelid'});">ɾ��</a>
				</div>
				<div>
					<form  id='searchForm' action="" method="post">
						ģ�ʹ���:
						<input type="text" id="modelcode" name="modelcode" class="form-text"/>
						ģ������:
						<input type="text" id="modelname" name="modelname" class="form-text"/>
						ģ������:
						<input type="text" id="modeltype" name="modeltype" class="form-text"/>
						<input type="button" class="commbtn" onclick="loadList(1);" value="��ѯ"/>
					</form>
				</div>
			</div>
		</div>
		<div id="subDataList" style="display: none;">
			<hr>
			<div id="subTb" style="padding:5px;height:auto">
				<div style="margin-bottom:5px">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="subShowAddwindow({title:'����'});setAttrVal();">����</a>|
					<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="checkAttr(0);">�޸�</a>|
					<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="checkAttr(1);">ɾ��</a>
				</div>
			</div>
		</div>
	</div>
	
	<div style="visibility:hidden" >
		<div id="addwindow" title="���" style="width:600px;height:350px;padding:10px">
			<form id='addForm' action="" method="post">
				<table>
					<tr style="display: none;">
						<td>ģ��ID:</td>
						<td><input type="text" id="modelid" name="modelid" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr>
						<td>ģ�ʹ���:</td>
						<td><input type="text" id="modelcode" name="modelcode" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr>
						<td>ģ������:</td>
						<td><input type="text" id="modelname" name="modelname" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr>
						<td>ģ������:</td>
						<td><input type="text" id="modeltype" name="modeltype" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr style="display: none;">
						<td>ģ�����ݱ����:</td>
						<td><input type="text" id="modeldatatable" name="modeldatatable" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr style="display: none;">
						<td>ģ�ʹ���ʱ��:</td>
						<td><input type="text" id="modelcreatetime" name="modelcreatetime" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr style="display: none;">
						<td>ģ���޸�ʱ��:</td>
						<td><input type="text" id="modelupdatetime" name="modelupdatetime" class="form-text input-margin" style="width:180px"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<div style="visibility:hidden" >
		<div id="subAddwindow" title="���" style="width:600px;height:350px;padding:10px">
			<form id='subAddForm' action="" method="post">
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
						<td>
							<select id="attributetype" name="attributetype" class="form-text input-margin" style="width:180px" onchange="setAttrType()">
								<option value="string" selected="selected">
									string
								</option>
								<option value="int">
									int
								</option>
							</select>
							<input type="text" id="modelattributetype" name="modelattributetype" value="string" class="form-text input-margin" style="width:180px; display: none;"/>
						</td>
					</tr>
					<tr>
						<td>�Ƿ��ܱ���ѯ:</td>
						<td>
							<select id="canSearch" name="canSearch" class="form-text input-margin" style="width:180px" onchange="setAttrType()">
								<option value="1" selected="selected">
									�ܱ���ѯ
								</option>
								<option value="0">
									���ܱ���ѯ
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>�ܷ���ʾ:</td>
						<td>
							<select id="canDisplay" name="canDisplay" class="form-text input-margin" style="width:180px" onchange="setAttrType()">
								<option value="1" selected="selected">
									�ܱ���ʾ
								</option>
								<option value="0">
									���ܱ���ʾ
								</option>
							</select>
						</td>
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
