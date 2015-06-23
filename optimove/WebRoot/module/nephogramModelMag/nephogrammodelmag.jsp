<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>云图模型管理</title>
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
		title : '云图模板列表',
		iconCls : 'icon-edit',//图标  
		height : 'auto',
		nowrap : false,
		striped : true,
		border : true,
		idField : 'nephogrammodelid',
		collapsible : false,//是否可折叠的  
		fit : false,//自动大小  
		url : '#',
		remoteSort : false,
		singleSelect : true,//是否单选  
		pagination : true,//分页控件  
		rownumbers : true,//行号  
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		} ] ],
		url : searchUrl,
		columns : [ [ {
			field : 'nephogrammodelid',
			title : '模板编号',
			width : 100,
			align : 'center'
		}, {
			field : 'nephogrammodelname',
			title : '模板名称',
			width : 100,
			align : 'center'
		}, {
			field : 'tablemodelname',
			title : '备注',
			width : 100,
			align : 'center'
		} ] ],
		toolbar : [ {
			id : 'btnAddT',
			text : '新增',
			iconCls : 'icon-add',
			handler : function() {
				showAddwindow( {
					title : '新增'
				});
			}
		}, '-', {
			id : 'btneditT',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function() {
				showUpdateType( {
					title : '修改',
					readonlyFields : [ 'nephogrammodelid' ],
					updateUrl : updateUrl
				});
			}
		}, '-', {
			id : 'btndelT',
			text : '删除',
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
				showBox("提示信息", data.msg, 'warning');
			}
		},
		onSelect : function(index, data) {//显示子表
			$("#tb_lookupvalueList").removeAttr("style");
			loadValueList(1);//加载子表
		//$('#dg_lookupvalueList').datagrid("unselectAll");//取消dg_lookupvalueList的选择//加载子表。
	},
	fitColumns : true,
	pagination : true

	});
	//设置分页控件  
	var p = $('#dataList').datagrid('getPager');
	$(p).pagination( {
		pageSize : 10,//每页显示的记录条数，默认为10  
		pageList : [ 10, 20, 30 ],//可以设置每页记录条数的列表  
		beforePageText : '第',//页数文本框前显示的汉字  
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	})

	$('#dg_lookupvalueList').datagrid( {
		title : '属性列表',
		iconCls : 'icon-edit',//图标  
		height : 'auto',
		nowrap : false,
		striped : true,
		border : true,
		collapsible : false,//是否可折叠的  
		fit : false,//自动大小  
		remoteSort : false,
		singleSelect : true,//是否单选
		pageSize : constant.pageSize,
		pageList : constant.pageList,
		pagination : true,//分页控件
		rownumbers : true,//行号  
		fitColumns : true,
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		} ] ],
		columns : [ [ {
			field : 'nephogrammodelid',
			title : '模板编号',
			width : 100,
			align : 'center'
		}, {
			field : 'nephogramattrid',
			title : '属性编号',
			width : 100,
			align : 'center'
		}, {
			field : 'attributecode',
			title : '属性代码',
			width : 100,
			align : 'center'
		}, {
			field : 'attributename',
			title : '属性名称',
			width : 100,
			align : 'center'
		}, {
			field : 'attrtype',
			title : '属性类型',
			width : 100,
			align : 'center'
		}, {
			field : 'remarks',
			title : '备注',
			width : 100,
			align : 'center'
		} ] ],
		toolbar : [ {
			id : 'btnAddAdd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function() {
				showAddCodeDlg();
			}
		}, {
			id : 'btnedit2',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function() {
				showUpdateValues( {
					title : '修改',
					readonlyFields : [ 'nephogramattrid' ],
					updateUrl : updateNephogramAttrUrl
				}, 'updatevalueswindow', 'updatevaluesForm');
			}
		}, '-', {
			id : 'btndel',
			text : '删除',
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
				showBox("提示信息", data.msg, 'warning');
			}
		}

	});

	gridPager = $('#dg_lookupvalueList').datagrid('getPager');

	$(gridPager).pagination( {
		pageSize : 10,//每页显示的记录条数，默认为10  
		pageList : [ 10, 20, 30 ],//可以设置每页记录条数的列表  
		beforePageText : '第',//页数文本框前显示的汉字  
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	})

	/**
	 * 得到datagrid 的分页对象
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
			showBox('温馨提示', '请选要查询的类型!', 'warning');
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
						showBox("提示信息", response.result, 'warning');
						return;
					}
					refreshDatagrid("dg_lookupvalueList", pageNumber);
					$('#dg_lookupvalueList').datagrid('loadData', response);

				});

	}
	//子表分页
	PaginationConfig('dg_lookupvalueList', 'loadValueList');

	// 增加lookupCode
	function doAddCode() {
		$('#addvaluesForm').form.url = insertNephogramAttrUrl; //表单提交路径
		submitForm("addvaluesForm", $('#addvaluesForm').form.url,
				function(data) {
					eval('data=' + data);
					if (data.result == "ok") {
						$('#dg_lookupvalueList').datagrid('clearSelections');//清空选择
				$('#addvalueswindow').dialog('close');
				showBox("提示信息", "保存成功", 'info');
				var pageNumber = $('#dataList').datagrid('getPager').data(
						"pagination").options.pageNumber;
				loadValueList(pageNumber);
			} else {
				showBox("提示信息", data.result, 'warning');
			}
		});
	}

	//show 添加参数codeDlg 
	function showAddCodeDlg() {

		var selectType = $('#dataList').datagrid('getSelected');
		if (!selectType) {
			showBox('温馨提示', '请选要添加的类型!', 'warning');
			return false;
		}

		$('#addvaluesForm')[0].reset();
		$('#lookupType1').val(selectType.nephogrammodelid);
		$('#a_v_enabledFlag').val("Y");
		initDlg('#addvalueswindow').dialog( {
			title : "添加参数",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					doAddCode();
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#addvalueswindow').dialog('close');
				}
			} ]
		});
		$('#addvalueswindow').dialog('open');
	}
	//修改字典类型
	function showUpdateType(jsonParam) {
		jsonParam = jsonParam || {};
		jsonParam.updateUrl = isEmpty(jsonParam.updateUrl) ? updateUrl
				: jsonParam.updateUrl;
		jsonParam.title = isEmpty(jsonParam.title) ? $('#updatewindow').attr(
				'title') : jsonParam.title;
		delError();
		var rows = $('#dataList').datagrid("getSelections");
		if (rows.length == 0) {
			$.messager.alert('提示框', '请选择更新数据', 'warning');
			return;
		}
		var data = rows[0];
		//更新前处理
		if (isFunction(jsonParam.preHandler))
			jsonParam.preHandler(jsonParam);
		else
			setFormValue("updateForm", data);
		setReadonly(jsonParam.readonlyFields);

		initDlg('#updatewindow').dialog( {
			title : jsonParam.title,
			buttons : [ {
				text : '修改',
				iconCls : 'icon-ok',
				handler : function() {
					//确定按钮点击后的具体处理函数
				if (isFunction(jsonParam.updateHandler)) {
					jsonParam.updateHandler(jsonParam);
				} else
					doEditType(jsonParam.updateUrl);

			}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$("#updateForm")[0].reset();
					$('#updatewindow').dialog('close');
				}
			} ]
		});
		//新增后处理
		if (isFunction(jsonParam.afHandler))
			jsonParam.afHandler(jsonParam);
		$('#updatewindow').dialog('open');

	}
	//修改字典类型
	function doEditType(url) {
		if (url)
			$('#updateForm').form.url = url; //表单提交路径
		submitForm("updateForm", $('#updateForm').form.url, function(data) {
			//eval('data='+data); 
				data = convertJson(data);
				if (data.result == "ok") {
					$('#addDlg').dialog('close');
					showBox("提示信息", "修改成功", 'info');
					var pageNumber = $('#dataList').datagrid('getPager').data(
							"pagination").options.pageNumber;
					loadList(pageNumber);
					$('#updatewindow').dialog('close');
				} else {
					//showBox("提示信息",data.result,'warning');
				showError(data);
			}
		});
	}

	//修改字典值列表
	function showUpdateValues(jsonParam, windowName, formName) {
		jsonParam = jsonParam || {};
		jsonParam.updateUrl = isEmpty(jsonParam.updateUrl) ? updateUrl
				: jsonParam.updateUrl;
		jsonParam.title = isEmpty(jsonParam.title) ? $('#' + windowName).attr(
				'title') : jsonParam.title;
		delError();
		var rows = $('#dg_lookupvalueList').datagrid("getSelections");
		if (rows.length == 0) {
			$.messager.alert('提示框', '请选择更新数据', 'warning');
			return;
		}
		var data = rows[0];
		//更新前处理
		if (isFunction(jsonParam.preHandler))
			jsonParam.preHandler(jsonParam);
		else
			setFormValue(formName, data);
		setReadonly(jsonParam.readonlyFields);

		initDlg('#' + windowName).dialog( {
			title : jsonParam.title,
			buttons : [ {
				text : '修改',
				iconCls : 'icon-ok',
				handler : function() {
					//确定按钮点击后的具体处理函数
				if (isFunction(jsonParam.updateHandler)) {
					jsonParam.updateHandler(jsonParam);
				} else
					doEditValues(jsonParam.updateUrl, windowName, formName);

			}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$("#" + formName)[0].reset();
					$('#' + windowName).dialog('close');
				}
			} ]
		});
		//新增后处理
		if (isFunction(jsonParam.afHandler))
			jsonParam.afHandler(jsonParam);
		$('#' + windowName).dialog('open');

	}

	function doEditValues(url, windowName, formName) {
		if (url)
			$('#' + formName).form.url = url; //表单提交路径
		submitForm(formName, $('#' + formName).form.url, function(data) {
			//eval('data='+data); 
				data = convertJson(data);
				if (data.result == "ok") {
					$('#addDlg').dialog('close');
					showBox("提示信息", "修改成功", 'info');
					var pageNumber = $('#dg_lookupvalueList').datagrid(
							'getPager').data("pagination").options.pageNumber;
					loadValueList(pageNumber);
					$('#' + windowName).dialog('close');
				} else {
					//showBox("提示信息",data.result,'warning');
				showError(data);
			}
		});
	}

	/**
	 * 
	 * @param url 删除字典的url
	 * @param id  删除的条件
	 */
	function delLookupData(jsonParam) {
		jsonParam = jsonParam || {};
		jsonParam.url = isEmpty(jsonParam.url) ? deleteNephogramAttr : jsonParam.url;
		var rows = $('#dg_lookupvalueList').datagrid("getSelections");

		if (rows.length == 0) {
			$.messager.alert('提示框', '请选择要删除的数据', 'warning');
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
				$.messager.alert('提示框','删除成功','info');
				$('#dataList').datagrid("reload");
				$('#dataList').datagrid("clearChecked");
			});**/
			Confirm('是否删' + rows.length + '条数据！', function() {

				var _param = convertJson('{\"' + jsonParam.id + 's\":\"' + ids
						+ '\"}');

				_param = convertJson('{\"' + jsonParam.id + 's\":\"' + ids
						+ '\",\"' + jsonParam.nephogramattrid + '\":\"'
						+ nephogramattrid + '\"}');

				$.post(jsonParam.url, _param, function(data) {
					$.messager.alert('提示框', '删除成功', 'info');
					var pageNumber = $('#dg_lookupvalueList').datagrid(
							'getPager').data("pagination").options.pageNumber;
					loadValueList(pageNumber);
				});

			});
		}
	}

	//添加自定义级别的下拉框
	ajaxConstants('customizationLevel');
});
</script>
	</head>
	<body class="easyui-layout">
		<div class="wrapper1 wrapper-content1 gray-bg panel-fit">
			<div style="background-color: white;">
			<form id='searchForm' action="" method="post">
						云图模板编号:
						<input type="text" id="snephogrammodelid" name="nephogrammodelid" class="form-text">
						<input type="button" onclick="loadList(1);" value="查询" class="commbtn">
			</form>
			<table id="dataList"></table>
			</div>
			<div style="visibility: hidden" id="tb_lookupvalueList">
				<div title="属性列表" style="padding: 0px">
					<table id="dg_lookupvalueList"></table>
				</div>
			</div>
		</div>
		<%--   添加字典类型 --%>
		<div style="visibility: hidden">
			<div id="addwindow" title="添加云图模板"
				style="width: 600px; height: 350px; padding: 10px">
				<form id='addForm' action="" method="post">
					<table>
						<tr>
							<td style="font-size: 13px">
								模板编号:
							</td>
							<td>
								<input type="text" id="a_t_nephogrammodelid"
									name="nephogrammodelid" class="form-text input-margin" style="width: 180px">
							</td>
							<td style="font-size: 13px">
								模板名称:
							</td>
							<td>
								<input type="text" id="a_t_nephogrammodelname"
									name="nephogrammodelname" style="width: 180px" class="form-text input-margin">
							</td>
						</tr>

						<tr>
							<td style="font-size: 13px"> 
								备注 :
							</td>
							<td>
								<input id="a_t_tablemodelname" name="tablemodelname" style="width: 180px" class="form-text input-margin"></input>
							</td>
						</tr>

					</table>
				</form>
			</div>
		</div>
		<%--   修改字典类型 --%>

		<div style="visibility: hidden">
			<div id="updatewindow" title="修改云图模板"
				style="width: 600px; height: 350px; padding: 10px">
				<form id='updateForm' action="" method="post">
					<table>
						<tr>
							<td style="font-size: 13px">
								模板编号:
							</td>
							<td>
								<input type="text" id="u_t_nephogrammodelid" name="nephogrammodelid"
									readonly="readonly" style="width: 180px" class="form-text input-margin">
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								模板名称:
							</td>
							<td>
								<input type="text" id="u_t_nephogrammodelname"
									name="nephogrammodelname" style="width: 180px" class="form-text input-margin">

							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								备注:
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

		<%--   添加字典值列表 --%>
		<div style="visibility: hidden">
			<div id="addvalueswindow" title="添加"
				style="width: 600px; height: 350px; padding: 10px">
				<form id='addvaluesForm' action="" method="post">
					<input id="lookupType1" type="hidden" name="nephogrammodelid" />
					<table>
						<tr>
							<td style="font-size: 13px">
								属性编码:
							</td>
							<td>
								<input type="text" id="a_v_nephogramattrid" name="nephogramattrid"
									style="width: 180px" class="form-text input-margin">
							</td>
							<td style="font-size: 13px">
								属性代码:
							</td>
							<td>
								<input type="text" id="a_v_attributecode" name="attributecode"
									style="width: 180px" class="form-text input-margin">
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								属性名称:
							</td>
							<td>
								<input type="text" id="a_v_attributename" name="attributename"
									style="width: 180px" class="form-text input-margin">
							</td>
							<td style="font-size: 13px">
								属性类型:
							</td>
							<td>
								<input type="text" id="a_v_attrtype" name="attrtype"
									style="width: 180px" class="form-text input-margin">
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								备注:
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


		<%--   修改字典值列表 --%>
		<div style="visibility: hidden">
			<div id="updatevalueswindow" title="修改属性"
				style="width: 600px; height: 350px; padding: 10px">
				<form id='updatevaluesForm' action="" method="post">
					<input id="lookupType_u_v" type="hidden" name="nephogrammodelid" />
					<table>
						<tr>
							<td style="font-size: 13px">
								属性编码:
							</td>
							<td>
								<input type="text" id="u_v_nephogramattrid" name="nephogramattrid"
									readonly="readonly" style="width: 180px" class="form-text input-margin">
							</td>
							<td style="font-size: 13px">
								属性代码:
							</td>
							<td>
								<input type="text" id="u_v_attributecode" name="attributecode"
									style="width: 180px" class="form-text input-margin">
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								属性名称:
							</td>
							<td>
								<input type="text" id="u_v_attributename" name="attributename"
									style="width: 180px" class="form-text input-margin">
							</td>
							<td style="font-size: 13px">
								属性类型:
							</td>
							<td>
								<input type="text" id="u_v_attrtype" name="attrtype"
									 style="width: 180px" class="form-text input-margin">
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								备注:
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