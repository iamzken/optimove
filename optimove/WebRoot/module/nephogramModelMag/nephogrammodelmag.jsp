<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>��ͼģ�͹���</title>
		<jsp:include page="/module/common/comm.jsp"></jsp:include>
		<script type="text/javascript" charset="GBK">
var searchUrl = "<%=request.getContextPath()%>/nephogrammodelmags/searchNephogrammodelmag.do";
var updateUrl = "<%=request.getContextPath()%>/nephogrammodelmags/update.do";
var insertUrl = "<%=request.getContextPath()%>/nephogrammodelmags/insert.do";
var deleteUrl = "<%=request.getContextPath()%>/nephogrammodelmags/delete.do";
var searchNephogramAttrUrl = "<%=request.getContextPath()%>/nephogramatrributes/searchNephogramatrribute.do";
var updateNephogramAttrUrl = "<%=request.getContextPath()%>/nephogramatrributes/update.do";
var insertNephogramAttrUrl = "<%=request.getContextPath()%>/nephogramatrributes/insert.do";
var deleteNephogramAttr = "<%=request.getContextPath()%>/nephogramatrributes/delete.do";
var getNephogramAttrById = "<%=request.getContextPath()%>/nephogramatrributes/getNephogramAttrById.do";

var constant = {
	pageList : [ 10, 20, 30 ],
	pageSize : 10
};

$(function() {
	$('#dataList').datagrid( {
		title : '��ͼģ���б�',
		iconCls : 'icon-edit',//ͼ��  
		height : 'auto',
		nowrap : false,
		striped : true,
		border : true,
		idField : 'nephogrammodelid',
		collapsible : false,//�Ƿ���۵���  
		fit : false,//�Զ���С  
		url : '#',
		remoteSort : false,
		singleSelect : true,//�Ƿ�ѡ  
		pagination : true,//��ҳ�ؼ�  
		rownumbers : true,//�к�  
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		} ] ],
		url : searchUrl,
		columns : [ [ {
			field : 'nephogrammodelid',
			title : 'ģ����',
			width : 100,
			align : 'center'
		}, {
			field : 'nephogrammodelname',
			title : 'ģ������',
			width : 100,
			align : 'center'
		}, {
			field : 'tablemodelname',
			title : '��ע',
			width : 100,
			align : 'center'
		} ] ],
		toolbar : [ {
			id : 'btnAddT',
			text : '����',
			iconCls : 'icon-add',
			handler : function() {
				showAddwindow( {
					title : '����'
				});
			}
		}, '-', {
			id : 'btneditT',
			text : '�޸�',
			iconCls : 'icon-edit',
			handler : function() {
				showUpdateType( {
					title : '�޸�',
					readonlyFields : [ 'nephogrammodelid' ],
					updateUrl : updateUrl
				});
			}
		}, '-', {
			id : 'btndelT',
			text : 'ɾ��',
			iconCls : 'icon-remove',
			handler : function() {
				delRowData( {
					id : 'nephogrammodelid',
					url : deleteUrl
				});

			}
		} ],

		onLoadSuccess : function(data) {
			$('#dataList').datagrid("unselectAll");
			data = convertJson(data);
			if (data.result != 'ok') {
				showBox("��ʾ��Ϣ", data.msg, 'warning');
			}
		},
		onSelect : function(index, data) {//��ʾ�ӱ�
			$("#tb_lookupvalueList").removeAttr("style");
			loadValueList(1);//�����ӱ�
		//$('#dg_lookupvalueList').datagrid("unselectAll");//ȡ��dg_lookupvalueList��ѡ��//�����ӱ�
	},
	fitColumns : true,
	pagination : true

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

	$('#dg_lookupvalueList').datagrid( {
		title : '�����б�',
		iconCls : 'icon-edit',//ͼ��  
		height : 'auto',
		nowrap : false,
		striped : true,
		border : true,
		collapsible : false,//�Ƿ���۵���  
		fit : false,//�Զ���С  
		remoteSort : false,
		singleSelect : true,//�Ƿ�ѡ
		pageSize : constant.pageSize,
		pageList : constant.pageList,
		pagination : true,//��ҳ�ؼ�
		rownumbers : true,//�к�  
		fitColumns : true,
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		} ] ],
		columns : [ [ {
			field : 'nephogrammodelid',
			title : 'ģ����',
			width : 100,
			align : 'center'
		}, {
			field : 'nephogramattrid',
			title : '���Ա��',
			width : 100,
			align : 'center'
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
		toolbar : [ {
			id : 'btnAddAdd',
			text : '����',
			iconCls : 'icon-add',
			handler : function() {
				showAddCodeDlg();
			}
		}, {
			id : 'btnedit2',
			text : '�޸�',
			iconCls : 'icon-edit',
			handler : function() {
				showUpdateValues( {
					title : '�޸�',
					readonlyFields : [ 'nephogramattrid' ],
					updateUrl : updateNephogramAttrUrl
				}, 'updatevalueswindow', 'updatevaluesForm');
			}
		}, '-', {
			id : 'btndel',
			text : 'ɾ��',
			iconCls : 'icon-remove',
			handler : function() {
				delLookupData( {
					id : 'nephogramattrid',
					url : deleteNephogramAttr,
					nephogramattrid : 'nephogramattrid'
				});

			}
		} ],
		onLoadSuccess : function(data) {
			data = convertJson(data);
			if (data.result != 'ok') {
				showBox("��ʾ��Ϣ", data.msg, 'warning');
			}
		}

	});

	gridPager = $('#dg_lookupvalueList').datagrid('getPager');

	$(gridPager).pagination( {
		pageSize : 10,//ÿҳ��ʾ�ļ�¼������Ĭ��Ϊ10  
		pageList : [ 10, 20, 30 ],//��������ÿҳ��¼�������б�  
		beforePageText : '��',//ҳ���ı���ǰ��ʾ�ĺ���  
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	})

	/**
	 * �õ�datagrid �ķ�ҳ����
	 */
	function PaginationConfig(datagridId, executeFunction) {
		gridPager = $('#' + datagridId).datagrid('getPager');

		if (gridPager) {
			$(gridPager)
					.pagination(
							{
								onBeforeRefresh : function(pageNumber, pageSize) {

									eval(executeFunction + '(' + pageNumber
											+ ')');
									return false;
								},
								onSelectPage : function(pageNumber, pageSize) {
									$('#' + datagridId).datagrid('options').pageNumber = pageNumber;
									$('#' + datagridId).datagrid('options').pageSize = pageSize;
									eval(executeFunction + '(' + pageNumber
											+ ')');
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
		$.post(getNephogramAttrById,
				{
					"nephogrammodelid" : selectType.nephogrammodelid,
					"page" : pageNumber,
					"pageSize" : $('#dataList').datagrid('getPager').data(
							"pagination").pageSize

				}, function(response) {
					response = convertJson(response);
					if (response.result != 'ok') {
						showBox("��ʾ��Ϣ", response.result, 'warning');
						return;
					}
					refreshDatagrid("dg_lookupvalueList", pageNumber);
					$('#dg_lookupvalueList').datagrid('loadData', response);

				});

	}
	//�ӱ��ҳ
	PaginationConfig('dg_lookupvalueList', 'loadValueList');

	// ����lookupCode
	function doAddCode() {
		$('#addvaluesForm').form.url = insertNephogramAttrUrl; //���ύ·��
		submitForm("addvaluesForm", $('#addvaluesForm').form.url,
				function(data) {
					eval('data=' + data);
					if (data.result == "ok") {
						$('#dg_lookupvalueList').datagrid('clearSelections');//���ѡ��
				$('#addvalueswindow').dialog('close');
				showBox("��ʾ��Ϣ", "����ɹ�", 'info');
				var pageNumber = $('#dataList').datagrid('getPager').data(
						"pagination").options.pageNumber;
				loadValueList(pageNumber);
			} else {
				showBox("��ʾ��Ϣ", data.result, 'warning');
			}
		});
	}

	//show ��Ӳ���codeDlg 
	function showAddCodeDlg() {

		var selectType = $('#dataList').datagrid('getSelected');
		if (!selectType) {
			showBox('��ܰ��ʾ', '��ѡҪ��ӵ�����!', 'warning');
			return false;
		}

		$('#addvaluesForm')[0].reset();
		$('#lookupType1').val(selectType.nephogrammodelid);
		$('#a_v_enabledFlag').val("Y");
		initDlg('#addvalueswindow').dialog( {
			title : "��Ӳ���",
			buttons : [ {
				text : 'ȷ��',
				iconCls : 'icon-ok',
				handler : function() {
					doAddCode();
				}
			}, {
				text : 'ȡ��',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#addvalueswindow').dialog('close');
				}
			} ]
		});
		$('#addvalueswindow').dialog('open');
	}
	//�޸��ֵ�����
	function showUpdateType(jsonParam) {
		jsonParam = jsonParam || {};
		jsonParam.updateUrl = isEmpty(jsonParam.updateUrl) ? updateUrl
				: jsonParam.updateUrl;
		jsonParam.title = isEmpty(jsonParam.title) ? $('#updatewindow').attr(
				'title') : jsonParam.title;
		delError();
		var rows = $('#dataList').datagrid("getSelections");
		if (rows.length == 0) {
			$.messager.alert('��ʾ��', '��ѡ���������', 'warning');
			return;
		}
		var data = rows[0];
		//����ǰ����
		if (isFunction(jsonParam.preHandler))
			jsonParam.preHandler(jsonParam);
		else
			setFormValue("updateForm", data);
		setReadonly(jsonParam.readonlyFields);

		initDlg('#updatewindow').dialog( {
			title : jsonParam.title,
			buttons : [ {
				text : '�޸�',
				iconCls : 'icon-ok',
				handler : function() {
					//ȷ����ť�����ľ��崦����
				if (isFunction(jsonParam.updateHandler)) {
					jsonParam.updateHandler(jsonParam);
				} else
					doEditType(jsonParam.updateUrl);

			}
			}, {
				text : 'ȡ��',
				iconCls : 'icon-cancel',
				handler : function() {
					$("#updateForm")[0].reset();
					$('#updatewindow').dialog('close');
				}
			} ]
		});
		//��������
		if (isFunction(jsonParam.afHandler))
			jsonParam.afHandler(jsonParam);
		$('#updatewindow').dialog('open');

	}
	//�޸��ֵ�����
	function doEditType(url) {
		if (url)
			$('#updateForm').form.url = url; //���ύ·��
		submitForm("updateForm", $('#updateForm').form.url, function(data) {
			//eval('data='+data); 
				data = convertJson(data);
				if (data.result == "ok") {
					$('#addDlg').dialog('close');
					showBox("��ʾ��Ϣ", "�޸ĳɹ�", 'info');
					var pageNumber = $('#dataList').datagrid('getPager').data(
							"pagination").options.pageNumber;
					loadList(pageNumber);
					$('#updatewindow').dialog('close');
				} else {
					//showBox("��ʾ��Ϣ",data.result,'warning');
				showError(data);
			}
		});
	}

	//�޸��ֵ�ֵ�б�
	function showUpdateValues(jsonParam, windowName, formName) {
		jsonParam = jsonParam || {};
		jsonParam.updateUrl = isEmpty(jsonParam.updateUrl) ? updateUrl
				: jsonParam.updateUrl;
		jsonParam.title = isEmpty(jsonParam.title) ? $('#' + windowName).attr(
				'title') : jsonParam.title;
		delError();
		var rows = $('#dg_lookupvalueList').datagrid("getSelections");
		if (rows.length == 0) {
			$.messager.alert('��ʾ��', '��ѡ���������', 'warning');
			return;
		}
		var data = rows[0];
		//����ǰ����
		if (isFunction(jsonParam.preHandler))
			jsonParam.preHandler(jsonParam);
		else
			setFormValue(formName, data);
		setReadonly(jsonParam.readonlyFields);

		initDlg('#' + windowName).dialog( {
			title : jsonParam.title,
			buttons : [ {
				text : '�޸�',
				iconCls : 'icon-ok',
				handler : function() {
					//ȷ����ť�����ľ��崦����
				if (isFunction(jsonParam.updateHandler)) {
					jsonParam.updateHandler(jsonParam);
				} else
					doEditValues(jsonParam.updateUrl, windowName, formName);

			}
			}, {
				text : 'ȡ��',
				iconCls : 'icon-cancel',
				handler : function() {
					$("#" + formName)[0].reset();
					$('#' + windowName).dialog('close');
				}
			} ]
		});
		//��������
		if (isFunction(jsonParam.afHandler))
			jsonParam.afHandler(jsonParam);
		$('#' + windowName).dialog('open');

	}

	function doEditValues(url, windowName, formName) {
		if (url)
			$('#' + formName).form.url = url; //���ύ·��
		submitForm(formName, $('#' + formName).form.url, function(data) {
			//eval('data='+data); 
				data = convertJson(data);
				if (data.result == "ok") {
					$('#addDlg').dialog('close');
					showBox("��ʾ��Ϣ", "�޸ĳɹ�", 'info');
					var pageNumber = $('#dg_lookupvalueList').datagrid(
							'getPager').data("pagination").options.pageNumber;
					loadValueList(pageNumber);
					$('#' + windowName).dialog('close');
				} else {
					//showBox("��ʾ��Ϣ",data.result,'warning');
				showError(data);
			}
		});
	}

	/**
	 * 
	 * @param url ɾ���ֵ��url
	 * @param id  ɾ��������
	 */
	function delLookupData(jsonParam) {
		jsonParam = jsonParam || {};
		jsonParam.url = isEmpty(jsonParam.url) ? deleteNephogramAttr : jsonParam.url;
		var rows = $('#dg_lookupvalueList').datagrid("getSelections");

		if (rows.length == 0) {
			$.messager.alert('��ʾ��', '��ѡ��Ҫɾ��������', 'warning');
		} else {
			var ids = "";
			var nephogramattrid = "";
			if (rows.length > 1) {
				for ( var i = 0; i < rows.length; i++) {
					ids += $(rows[i]).attr(jsonParam.id) + ",";
					nephogramattrid += $(rows[i]).attr(
							jsonParam.nephogramattrid)
							+ ",";
				}
			} else {
				ids = $(rows[0]).attr(jsonParam.id);
				nephogramattrid = $(rows[0]).attr(jsonParam.nephogramattrid);
			}
			//alert("ids="+ids);
			//alert("lookupType="+lookupType);
			var _param = convertJson('{\"' + jsonParam.id + 's\":\"' + ids
					+ '\"}');
			/**$.post(jsonParam.url, _param, function(data) {
				$.messager.alert('��ʾ��','ɾ���ɹ�','info');
				$('#dataList').datagrid("reload");
				$('#dataList').datagrid("clearChecked");
			});**/
			Confirm('�Ƿ�ɾ' + rows.length + '�����ݣ�', function() {

				var _param = convertJson('{\"' + jsonParam.id + 's\":\"' + ids
						+ '\"}');

				_param = convertJson('{\"' + jsonParam.id + 's\":\"' + ids
						+ '\",\"' + jsonParam.nephogramattrid + '\":\"'
						+ nephogramattrid + '\"}');

				$.post(jsonParam.url, _param, function(data) {
					$.messager.alert('��ʾ��', 'ɾ���ɹ�', 'info');
					var pageNumber = $('#dg_lookupvalueList').datagrid(
							'getPager').data("pagination").options.pageNumber;
					loadValueList(pageNumber);
				});

			});
		}
	}

	//����Զ��弶���������
	ajaxConstants('customizationLevel');
});
</script>
	</head>
	<body class="easyui-layout">
		<div class="wrapper1 wrapper-content1 gray-bg panel-fit">
			<div style="background-color: white;">
			<form id='searchForm' action="" method="post">
						��ͼģ����:
						<input type="text" id="snephogrammodelid" name="nephogrammodelid" class="form-text">
						<input type="button" onclick="loadList(1);" value="��ѯ" class="commbtn">
			</form>
			<table id="dataList"></table>
			</div>
			<div style="visibility: hidden" id="tb_lookupvalueList">
				<div title="�����б�" style="padding: 0px">
					<table id="dg_lookupvalueList"></table>
				</div>
			</div>
		</div>
		<%--   ����ֵ����� --%>
		<div style="visibility: hidden">
			<div id="addwindow" title="�����ͼģ��"
				style="width: 600px; height: 350px; padding: 10px">
				<form id='addForm' action="" method="post">
					<table>
						<tr>
							<td style="font-size: 13px">
								ģ����:
							</td>
							<td>
								<input type="text" id="a_t_nephogrammodelid"
									name="nephogrammodelid" class="form-text input-margin" style="width: 180px">
							</td>
							<td style="font-size: 13px">
								ģ������:
							</td>
							<td>
								<input type="text" id="a_t_nephogrammodelname"
									name="nephogrammodelname" style="width: 180px" class="form-text input-margin">
							</td>
						</tr>

						<tr>
							<td style="font-size: 13px"> 
								��ע :
							</td>
							<td>
								<input id="a_t_tablemodelname" name="tablemodelname" style="width: 180px" class="form-text input-margin"></input>
							</td>
						</tr>

					</table>
				</form>
			</div>
		</div>
		<%--   �޸��ֵ����� --%>

		<div style="visibility: hidden">
			<div id="updatewindow" title="�޸���ͼģ��"
				style="width: 600px; height: 350px; padding: 10px">
				<form id='updateForm' action="" method="post">
					<table>
						<tr>
							<td style="font-size: 13px">
								ģ����:
							</td>
							<td>
								<input type="text" id="u_t_nephogrammodelid" name="nephogrammodelid"
									readonly="readonly" style="width: 180px" class="form-text input-margin">
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								ģ������:
							</td>
							<td>
								<input type="text" id="u_t_nephogrammodelname"
									name="nephogrammodelname" style="width: 180px" class="form-text input-margin">

							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								��ע:
							</td>
							<td>
								<input type="text" id="u_t_tablemodelname" name="tablemodelname"
									style="width: 180px" class="form-text input-margin">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

		<%--   ����ֵ�ֵ�б� --%>
		<div style="visibility: hidden">
			<div id="addvalueswindow" title="���"
				style="width: 600px; height: 350px; padding: 10px">
				<form id='addvaluesForm' action="" method="post">
					<input id="lookupType1" type="hidden" name="nephogrammodelid" />
					<table>
						<tr>
							<td style="font-size: 13px">
								���Ա���:
							</td>
							<td>
								<input type="text" id="a_v_nephogramattrid" name="nephogramattrid"
									style="width: 180px" class="form-text input-margin">
							</td>
							<td style="font-size: 13px">
								���Դ���:
							</td>
							<td>
								<input type="text" id="a_v_attributecode" name="attributecode"
									style="width: 180px" class="form-text input-margin">
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								��������:
							</td>
							<td>
								<input type="text" id="a_v_attributename" name="attributename"
									style="width: 180px" class="form-text input-margin">
							</td>
							<td style="font-size: 13px">
								��������:
							</td>
							<td>
								<input type="text" id="a_v_attrtype" name="attrtype"
									style="width: 180px" class="form-text input-margin">
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								��ע:
							</td>
							<td>
								<input type="text" id="a_v_remarks" name="remarks"
									style="width: 180px" class="form-text input-margin">
							</td>
						</tr>

					</table>
				</form>
			</div>
		</div>


		<%--   �޸��ֵ�ֵ�б� --%>
		<div style="visibility: hidden">
			<div id="updatevalueswindow" title="�޸�����"
				style="width: 600px; height: 350px; padding: 10px">
				<form id='updatevaluesForm' action="" method="post">
					<input id="lookupType_u_v" type="hidden" name="nephogrammodelid" />
					<table>
						<tr>
							<td style="font-size: 13px">
								���Ա���:
							</td>
							<td>
								<input type="text" id="u_v_nephogramattrid" name="nephogramattrid"
									readonly="readonly" style="width: 180px" class="form-text input-margin">
							</td>
							<td style="font-size: 13px">
								���Դ���:
							</td>
							<td>
								<input type="text" id="u_v_attributecode" name="attributecode"
									style="width: 180px" class="form-text input-margin">
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								��������:
							</td>
							<td>
								<input type="text" id="u_v_attributename" name="attributename"
									style="width: 180px" class="form-text input-margin">
							</td>
							<td style="font-size: 13px">
								��������:
							</td>
							<td>
								<input type="text" id="u_v_attrtype" name="attrtype"
									 style="width: 180px" class="form-text input-margin">
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								��ע:
							</td>
							<td>
								<input type="text" id="u_v_remarks" name="remarks"
									style="width: 180px" class="form-text input-margin">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>
</html>