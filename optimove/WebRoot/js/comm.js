/**
 * @author tanxf
 */
var constantDatas = {};
var constantKeys;

//加载常量
$(function() {
	/**
	ajaxConstants();
	$('[constantId]').each(function(index,row){
		loadConstant2($(row).attr('constantId'));
	});
	 **/

	$('[constantId]').each(function(index, row) {
		$(row).combobox( {
			valueField : 'value',
			textField : 'display'
		});

		loadConstant($(row));
	});
})

// 初始化添加dlg
function initDlg(dlgId) {
	$(dlgId).dialog( {
		title : "",
		closable : true,
		closed : true,
		modal : true,
		buttons : [ {
			text : '确定',
			iconCls : 'icon-ok',
			handler : function() {
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
			}
		} ]
	});

	return $(dlgId);
}

/**
 * 显示新增界面
 * @param preHandler,afHandler,insertHandler,clearHandler,title,url
 * @return
 */
function showAddwindow(jsonParam) {
	jsonParam = jsonParam || {};
	$('#addForm')[0].reset();
	$('#addwindow').removeAttr("disabled", "disabled");
	$('#addwindow input').removeAttr("readonly");
	delError();
	jsonParam.title = isEmpty(jsonParam.title) ? $('#addwindow').attr('title')
			: jsonParam.title;
	//新增前处理
	if (typeof jsonParam.preHandler === "function")
		jsonParam.preHandler();

	initDlg('#addwindow').dialog( {
		title : jsonParam.title,
		buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			handler : function() {
				//确定按钮点击后的具体处理函数
			if (isFunction(jsonParam.insertHandler)) {
				jsonParam.insertHandler(jsonParam);
			} else {
				_insertHandler(jsonParam.url);
			}

		}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				//取消前处理
			if (typeof jsonParam.clearHandler === "function")
				jsonParam.clearHandler();
			else
				$('#addwindow').dialog('close');
		}
		} ]
	});
	//新增后处理
	if (isFunction(jsonParam.afHandler))
		jsonParam.afHandler(jsonParam);
	$('#addwindow').dialog('open');
}

/**
 * 显示子项新增界面
 * @param preHandler,afHandler,insertHandler,clearHandler,title,url
 * @return
 */
function subShowAddwindow(jsonParam) {
	jsonParam = jsonParam || {};
	$('#subAddForm')[0].reset();
	$('#subAddwindow').removeAttr("disabled", "disabled");
	$('#subAddwindow input').removeAttr("readonly");
	$('#modelattributetype').hide();
	$('#attributetype').show();
	delError();
	jsonParam.title = isEmpty(jsonParam.title) ? $('#subAddwindow').attr('title') : jsonParam.title;
	//新增前处理
	if (typeof jsonParam.preHandler === "function")
		jsonParam.preHandler();

	initDlg('#subAddwindow').dialog( {
		title : jsonParam.title,
		buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			handler : function() {
				//确定按钮点击后的具体处理函数
				if (isFunction(jsonParam.insertHandler)) {
					jsonParam.insertHandler(jsonParam);
				} else {
					_subInsertHandler(jsonParam.url);
				}
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				//取消前处理
				if (typeof jsonParam.clearHandler === "function")
					jsonParam.clearHandler();
				else
					$('#subAddwindow').dialog('close');
			}
		} ]
	});
	//新增后处理
	if (isFunction(jsonParam.afHandler))
		jsonParam.afHandler(jsonParam);
	$('#subAddwindow').dialog('open');
}

var _insertHandler = function(url) {
	$('#addForm').form.url = url || insertUrl; //表单提交路径
	submitForm("addForm", $('#addForm').form.url, function(data) {
		data = convertJson(data);
		if (data.result == "ok") {
			$('#addForm').form('clear'); // 清空form
			$('#dataList').datagrid('clearSelections');//清空选择
			$('#addwindow').dialog('close');
			showBox("提示信息", "保存成功", 'info');
			var pageNumber = $('#dataList').datagrid('getPager').data(
					"pagination").options.pageNumber;
			loadList(pageNumber);
			$('#addwindow').dialog('close');
		} else {
			showError(data);
			//showBox("提示信息",data.result,'warning');
		}
	});
}

var _subInsertHandler = function(url) {
	$('#subAddForm').form.url = url || subInsertUrl; //表单提交路径
	submitForm("subAddForm", $('#subAddForm').form.url, function(data) {
		data = convertJson(data);
		if (data.result == "ok") {
			$('#subAddForm').form('clear'); // 清空form
			$('#subDataList').datagrid('clearSelections');//清空选择
			$('#subAddwindow').dialog('close');
			showBox("提示信息", "保存成功", 'info');
			reloadList();
			$('#subAddwindow').dialog('close');
		} else {
			showError(data);
		}
	});
}

/**
 * jsonParam 中的参数有 updateUrl,updateHandler,preHandler,afHandler,readonlyFields
 * @param jsonParam
 * @return
 */
function showUpdate(jsonParam) {
	jsonParam = jsonParam || {};
	jsonParam.updateUrl = isEmpty(jsonParam.updateUrl) ? updateUrl
			: jsonParam.updateUrl;
	jsonParam.title = isEmpty(jsonParam.title) ? $('#addwindow').attr('title')
			: jsonParam.title;
	delError();
	var rows = $('#dataList').datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert('提示框', '请选择更新数据', 'warning');
		return;
	}
	var data = rows[0];
	$(jsonParam).attr('data', data);
	//更新前处理
	if (isFunction(jsonParam.preHandler))
		jsonParam.preHandler(jsonParam);
	else
		setFormValue("addForm", data);
	setReadonly(jsonParam.readonlyFields);

	initDlg('#addwindow').dialog( {
		title : jsonParam.title,
		buttons : [ {
			text : '提交',
			iconCls : 'icon-ok',
			handler : function() {
				//确定按钮点击后的具体处理函数
			if (isFunction(jsonParam.updateHandler)) {
				jsonParam.updateHandler(jsonParam);
			} else
				doEdit(jsonParam.updateUrl);

		}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$("#addForm")[0].reset();
				$('#addwindow').dialog('close');
			}
		} ]
	});
	//新增后处理
	if (isFunction(jsonParam.afHandler))
		jsonParam.afHandler(jsonParam);
	$('#addwindow').dialog('open');

	/**$.post(url, {workId:rows[0].workId}, function(data) {
		data = convertJson(data);
		var user = data.data;
		showData(user);
		initDlg('#addwindow').dialog({title:"修改用户",buttons:[{
			text:'修改',
			iconCls:'icon-ok',
			handler:function(){
	            //确定按钮点击后的具体处理函数
				doEdit();
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$("#addForm")[0].reset();
				$('#addwindow').dialog('close');
			}
		}]});
		
	$('#addwindow').dialog('open');
	});
	 **/
}

/**
 * 子项更新界面
 * jsonParam 中的参数有 updateUrl,updateHandler,preHandler,afHandler,readonlyFields
 * @param jsonParam
 * @return
 */
function subShowUpdate(jsonParam) {
	jsonParam = jsonParam || {};
	jsonParam.updateUrl = isEmpty(jsonParam.updateUrl) ? subUpdateUrl : jsonParam.updateUrl;
	jsonParam.title = isEmpty(jsonParam.title) ? $('#subAddwindow').attr('title') : jsonParam.title;
	delError();
	var rows = $('#subDataList').datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert('提示框', '请选择更新数据', 'warning');
		return;
	}
	var data = rows[0];
	$(jsonParam).attr('data', data);
	//更新前处理
	if (isFunction(jsonParam.preHandler))
		jsonParam.preHandler(jsonParam);
	else
		setFormValue("subAddForm", data);
	setReadonly(jsonParam.readonlyFields);
	
	initDlg('#subAddwindow').dialog( {
		title : jsonParam.title,
		buttons : [ {
			text : '提交',
			iconCls : 'icon-ok',
			handler : function() {
				//确定按钮点击后的具体处理函数
				if (isFunction(jsonParam.updateHandler)) {
					jsonParam.updateHandler(jsonParam);
				} else
					subDoEdit(jsonParam.updateUrl);
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$("#subAddForm")[0].reset();
				$('#subAddwindow').dialog('close');
			}
		} ]
	});
	//新增后处理
	if (isFunction(jsonParam.afHandler))
		jsonParam.afHandler(jsonParam);
	$('#subAddwindow').dialog('open');
}

function showAddUpdate(jsonParam) {
	jsonParam = jsonParam || {};
	jsonParam.updateUrl = isEmpty(jsonParam.updateUrl) ? updateUrl
			: jsonParam.updateUrl;
	jsonParam.title = isEmpty(jsonParam.title) ? $('#addwindow').attr('title')
			: jsonParam.title;
	delError();
	var rows = $('#dataList').datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert('提示框', '请选择更新数据', 'warning');
		return;
	}
	var data = rows[0];
	$(jsonParam).attr('data', data);
	//更新前处理
	if (isFunction(jsonParam.preHandler))
		jsonParam.preHandler(jsonParam);
	else
		setFormValue("adddm", data);
	setReadonly(jsonParam.readonlyFields);

	initDlg('#addDemo').dialog( {
		title : jsonParam.title,
		buttons : [ {
			text : '提交',
			iconCls : 'icon-ok',
			handler : function() {
				$.ajax( {
					url : insertUrl,
					data : formToString($("#adddm").get(0)),
					success : function(result) {
						if(result.result=='fail'){
							$.messager.alert("提示", "已经发起了审批!", "info");
							$('#addDemo').dialog('close');
						}else{
							$.messager.alert("提示", "发起审批成功！", "info")
							$('#addDemo').dialog('close');
						}
						
					}
				});

			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$("#adddm")[0].reset();
				$('#addDemo').dialog('close');
			}
		} ]
	});
	//新增后处理
	if (isFunction(jsonParam.afHandler))
		jsonParam.afHandler(jsonParam);
	$('#addDemo').dialog('open');
}
function showTaskUpdate(jsonParam, index) {
	$('#taskDataList').datagrid("selectRow", index);
	var test = $('#taskDataList').datagrid("getSelections", {
		index : index,
		field : jsonParam.title
	});
	if (test[0].taskstatus == '1') {
		$.messager.alert('提示框', '该任务已完成!', 'info');
		return;
	}
	if (test[0].tasktype == '0') {
		jsonParam.title = '方案审批';
		jsonParam = jsonParam || {};
		delError();
		var rows = $('#taskDataList').datagrid("getSelections");
		if (rows.length == 0) {
			$.messager.alert('提示框', '请选择更新数据', 'warning');
			return;
		}
		var data = rows[0];
		$(jsonParam).attr('data', data);
		//更新前处理
		if (isFunction(jsonParam.preHandler))
			jsonParam.preHandler(jsonParam);
		else
			setFormValue("addsubjectForm", data);
		setReadonly(jsonParam.readonlyFields);
		initDlg('#addsubjectwindow')
				.dialog(
						{
							title : jsonParam.title,
							buttons : [
									{
										text : '提交',
										iconCls : 'icon-ok',
										handler : function() {
											//确定按钮点击后的具体处理函数
										$
												.ajax( {
													url : submitApproveUrl,
													data : formToString($(
															"#addsubjectForm")
															.get(0)),
													success : function(data) {
														data = convertJson(data);
														if (data.result == "ok") {
															$(
																	'#addsubjectwindow')
																	.dialog(
																			'close');
															showBox("提示信息",
																	"操作成功",
																	'info');
															var pageNumber = $(
																	'#dataList')
																	.datagrid(
																			'getPager')
																	.data(
																			"pagination").options.pageNumber;
															loadList(pageNumber);
															$(
																	'#addsubjectwindow')
																	.dialog(
																			'close');
														} else {
															//showBox("提示信息",data.result,'warning');
															showError(data);
														}
													}
												});
									}
									},
									{
										text : '取消',
										iconCls : 'icon-cancel',
										handler : function() {
											$("#addsubjectForm")[0].reset();
											$('#addsubjectwindow').dialog(
													'close');
										}
									} ],
							onOpen : function() {
								$.ajax( {
									url : getSubjectDataUrl,
									data : {
										businessid : test[0].businessid
									},
									dataType : "json",
									success : function(data) {
										if (data.data.approveFlow == null) {
											$("#approveFlow").val("");
										} else {
											$("#approveFlow").val(
													data.data.approveFlow);
										}
										if (data.data.beginDate == null) {
											$("#beginDate").val("");
										} else {
											$("#beginDate").val(
													data.data.beginDate);
										}
										if (data.data.budgetRound == null) {
											$("#budgetRound").val("");
										} else {
											$("#budgetRound").val(
													data.data.budgetRound);
										}
										if (data.data.endDate == null) {
											$("#endDate").val("");
										} else {
											$("#endDate")
													.val(data.data.endDate);
										}
										if (data.data.madeAspect == null) {
											$("#madeAspect").val("");
										} else {
											$("#madeAspect").val(
													data.data.madeAspect);
										}
										if (data.data.madeGroup == null) {
											$("#madeGroup").val("");
										} else {
											$("#madeGroup").val(
													data.data.madeGroup);
										}
										if (data.data.madePerson == null) {
											$("#madePerson").val("");
										} else {
											$("#madePerson").val(
													data.data.madePerson);
										}
										if (data.data.madeScope == null) {
											$("#madeScope").val("");
										} else {
											$("#madeScope").val(
													data.data.madeScope);
										}
										if (data.data.madeStyle == null) {
											$("#madeStyle").val("");
										} else {
											$("#madeStyle").val(
													data.data.madeStyle);
										}
										if (data.data.status == null) {
											$("#status").val("");
										} else {
											$("#status").val(data.data.status);
										}
										if (data.data.subjectName == null) {
											$("#subjectName1").val("");
										} else {
											$("#subjectName1").val(
													data.data.subjectName);
										}
										if (data.data.operateTime == null) {
											$("#operateTime1").val("20140929");
										} else {
											$("#operateTime1").val(
													data.data.operateTime);
										}
										if (data.data.remark == null) {
											$("#remarks1").val("1231");
										} else {
											$("#remarks1")
													.val(data.data.remark);
										}
										$("#subjectName1").attr("disabled",
												"disabled");
										$("#operateTime1").attr("disabled",
												"disabled");
										$("#remarks1").attr("disabled",
												"disabled");
										$("#approveFlow").attr("disabled",
												"disabled");
										$("#beginDate").attr("disabled",
												"disabled");
										$("#budgetRound").attr("disabled",
												"disabled");
										$("#endDate").attr("disabled",
												"disabled");
										$("#madeAspect").attr("disabled",
												"disabled");
										$("#madeGroup").attr("disabled",
												"disabled");
										$("#madePerson").attr("disabled",
												"disabled");
										$("#madeScope").attr("disabled",
												"disabled");
										$("#madeStyle").attr("disabled",
												"disabled");
										$("#status").attr("disabled",
												"disabled");
									}
								});
								$('#demoTbl')
										.datagrid(
												{
													title : '指标列表',
													width : 600,
													height : 200,
													pagination : true,//分页控件  
													rownumbers : true,//行号  
													frozenColumns : [ [ {
														field : 'ck',
														checkbox : true
													} ] ],
													url : getApproveRecordUrl,
													columns : [ [
															{
																field : 'approverecordid',
																title : '指标编号',
																width : 150,
																align : 'center'
															},
															{
																field : 'tasktype',
																title : '指标类型',
																width : 100,
																align : 'center',
																formatter : function(
																		taskstatus) {
																	if (taskstatus == '0')
																		return "金额";
																	return "数量";
																}
															},
															{
																field : 'approveremark',
																title : '指标名称',
																width : 100,
																align : 'center',
																formatter : function(
																		taskstatus) {
																	if (taskstatus == '同意')
																		return "指标1";
																	return "指标2";
																}
															},
															{
																field : 'approvestatus',
																title : '指标级别',
																width : 120,
																align : 'center',
																formatter : function(
																		taskstatus) {
																	if (taskstatus == '0')
																		return "一级";
																	return "二级";
																}
															} ] ],
													onLoadSuccess : function(
															data) {
														data = convertJson(data);
														if (data.result != 'ok') {
															showBox(
																	"提示信息",
																	data.errorMsg,
																	'warning');
														}
													}

												});
								//设置分页控件  
								var p = $('#demoTbl').datagrid('getPager');
								$(p)
										.pagination(
												{
													pageSize : 10,//每页显示的记录条数，默认为10  
													pageList : [ 10, 20, 30 ],//可以设置每页记录条数的列表  
													beforePageText : '第',//页数文本框前显示的汉字  
													afterPageText : '页    共 {pages} 页',
													displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
												})
								paginationConfig('demoTbl');
							}
						});
		//新增后处理
		if (isFunction(jsonParam.afHandler))
			jsonParam.afHandler(jsonParam);
		$('#addsubjectwindow').dialog('open');
	} else {
		jsonParam.title = '预算审批';
		jsonParam = jsonParam || {};
		jsonParam.updateUrl = isEmpty(jsonParam.updateUrl) ? updateUrl
				: jsonParam.updateUrl;
		delError();
		var rows = $('#taskDataList').datagrid("getSelections");
		if (rows.length == 0) {
			$.messager.alert('提示框', '请选择更新数据', 'warning');
			return;
		}
		var data = rows[0];
		$(jsonParam).attr('data', data);
		//更新前处理
		if (isFunction(jsonParam.preHandler))
			jsonParam.preHandler(jsonParam);
		else
			setFormValue("addbudgetForm", data);
		setReadonly(jsonParam.readonlyFields);
		initDlg('#addbudgetwindow').dialog( {
			title : jsonParam.title,
			buttons : [ {
				text : '提交',
				iconCls : 'icon-ok',
				handler : function() {
					//确定按钮点击后的具体处理函数
				if (isFunction(jsonParam.updateHandler)) {
					jsonParam.updateHandler(jsonParam);
				} else
					doEdit(jsonParam.updateUrl);
			}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$("#addbudgetForm")[0].reset();
					$('#addbudgetwindow').dialog('close');
				}
			} ],
			onOpen : function() {
				$.ajax( {
					url : getSubjectDataUrl,
					data : {
						businessid : test[0].businessid
					},
					dataType : "json",
					success : function(data) {
						if (data.data.subjectName == null) {
							$("#subjectName1").val("");
						} else {
							$("#subjectName1").val(data.data.subjectName);
						}
						if (data.data.operateTime == null) {
							$("#operateTime1").val("");
						} else {
							$("#operateTime1").val(data.data.operateTime);
						}
						if (data.data.remark == null) {
							$("#remarks1").val("");
						} else {
							$("#remarks1").val(data.data.remark);
						}
						$("#subjectName1").attr("disabled", "disabled");
						$("#operateTime1").attr("disabled", "disabled");
						$("#remarks1").attr("disabled", "disabled");
					}
				});
				var users = {
					total : 1,
					rows : [ {
						goalcode : "Goo1",
						goalname : "指标1",
						goaltype : "金额",
						goalfrom : "系统"
					} ]
				};
				$('#demoTbl').datagrid( {
					striped : true,
					iconCls : 'icon-edit',
					width : 700,
					height : 100,
					view : detailview,
					singleSelect : true,
					columns : [ [ {
						field : 'goalcode',
						title : '指标代码',
						width : 160
					}, {
						field : 'goalname',
						title : '指标名',
						width : 160
					}, {
						field : 'goaltype',
						title : '指标类型',
						width : 160
					}, {
						field : 'goalfrom',
						title : '指标来源',
						width : 160
					} ] ]
				}).datagrid('loadData', users).datagrid('acceptChanges');

				//设置分页控件  
			var p = $('#demoTbl').datagrid('getPager');
			$(p).pagination( {
				pageSize : 10,//每页显示的记录条数，默认为10  
				pageList : [ 10, 20, 30 ],//可以设置每页记录条数的列表  
				beforePageText : '第',//页数文本框前显示的汉字  
				afterPageText : '页    共 {pages} 页',
				displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
			})
			paginationConfig('demoTbl');
		}
		});
		//新增后处理
		if (isFunction(jsonParam.afHandler))
			jsonParam.afHandler(jsonParam);
		$('#addbudgetwindow').dialog('open');
	}

}

/**
 * jsonParam 中的参数有 setPowerUrl,updateHandler,preHandler,afHandler,readonlyFields
 * @param jsonParam
 * @return
 */
function showPowerSetting(jsonParam) {
	jsonParam = jsonParam || {};
	jsonParam.updateUrl = isEmpty(jsonParam.getSettingDataUrl) ? getSettingDataUrl
			: jsonParam.getSettingDataUrl;
	jsonParam.title = isEmpty(jsonParam.title) ? $('#addpowerwindow').attr(
			'title') : jsonParam.title;
	delError();
	var rows = $('#dataList').datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert('提示框', '请选择更新数据', 'warning');
		return;
	}
	var data = rows[0];
	$(jsonParam).attr('data', data);

	//更新前处理
	if (isFunction(jsonParam.preHandler))
		jsonParam.preHandler(jsonParam);
	else
		setFormValue("addpowerForm", data);
	setReadonly(jsonParam.readonlyFields);

	initDlg('#addpowerwindow').dialog( {
		title : jsonParam.title,
		buttons : [ {
			text : '提交',
			iconCls : 'icon-ok',
			handler : function() {
				//确定按钮点击后的具体处理函数
			if (isFunction(jsonParam.updateHandler)) {
				jsonParam.updateHandler(jsonParam);
			} else
				doEdit(jsonParam.setPowerUrl);

		}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$("#addpowerForm")[0].reset();
				$('#addpowerwindow').dialog('close');
			}
		} ],
		onOpen : function() {
			//树插件配置属性定义 ,详细API参照http://www.ztree.me/v3/api.php
		var setting = {
			edit : {
				/*设置 zTree 是否开启异步加载模式
				 * true 表示 开启 异步加载模式
				 * false 表示 关闭 异步加载模式
				 * */
				enable : true,
				/*设置是否显示删除按钮。[setting.edit.enable = true 时生效]
				 * true / false 分别表示 显示 / 隐藏 删除按钮
				 */
				showRemoveBtn : false,
				/*设置是否显示编辑名称按钮。[setting.edit.enable = true 时生效]
				 * true / false 分别表示 显示 / 隐藏 编辑名称按钮
				 * */
				showRenameBtn : false
			},
			data : {
				simpleData : {
					/*节点数据中保存唯一标识的属性名称。[setting.data.simpleData.enable = true 时生效]*/
					idKey : "id",
					/*节点数据中保存其父节点唯一标识的属性名称。[setting.data.simpleData.enable = true 时生效]*/
					pIdKey : "pId",
					/*确定 zTree 初始化时的节点数据、异步加载时的节点数据、或 addNodes 方法中输入的 newNodes 数据是否采用简单数据模式 (Array)
					 * 不需要用户再把数据库中取出的 List 强行转换为复杂的 JSON 嵌套格式
					 * 默认值：false
					 * true / false 分别表示 使用 / 不使用 简单数据模式
					 * */
					enable : true
				}
			},
			callback : {
			/*用于捕获节点被点击的事件回调函数
			 * 如果设置了 setting.callback.beforeClick 方法，且返回 false，将无法触发 onClick 事件回调函数。
			 * */
			// onClick: zTreeOnClickBudget
			},
			view : {
				addDiyDom : addDiyDomPower
			}
		};
		var branchNodes = [];
		var groupNodes = [];
		var pmmNodes = [];
		var deptNodes = [];
		$.ajax( {
			url : getTreeDataUrl,
			dataType : "json",
			success : function(data) {
				for ( var i = 0; i < data.data.length; i++) {
					var obj = {
						"id" : data.data[i].grpId,
						"pId" : 0,
						"name" : data.data[i].grpCname
					};
					groupNodes.push(obj);
				}
				for ( var j = 0; j < data.branchData.length; j++) {
					var branchObj = {
						"id" : data.branchData[j].branchCode,
						"pId" : data.branchData[j].upCode,
						"name" : data.branchData[j].name
					};
					branchNodes.push(branchObj);
				}
				for ( var k = 0; k < data.productMainManage.length; k++) {
					var pmmObj = {
						"id" : data.productMainManage[k].code,
						"pId" : 0,
						"name" : data.productMainManage[k].name
					};
					pmmNodes.push(pmmObj);
				}
				for ( var m = 0; m < data.deptData.length; m++) {
					var deptObj = {
						"id" : data.deptData[m].departmentcode,
						"pId" : 0,
						"name" : data.deptData[m].departmentname
					};
					deptNodes.push(deptObj);
				}
				$.fn.zTree.init($("#group"), setting, groupNodes);
				$.fn.zTree.init($("#branch"), setting, branchNodes);
				$.fn.zTree.init($("#productMM"), setting, pmmNodes);
				$.fn.zTree.init($("#dept"), setting, deptNodes);
			}
		});
		$("#powerTab").tabs( {
			width : 570,
			height : 200
		});

	}
	});
	//新增后处理
	if (isFunction(jsonParam.afHandler))
		jsonParam.afHandler(jsonParam);
	$('#addpowerwindow').dialog('open');
}

function showWindow(windowId, datagridId) {
	var data = getDatagridOneRow(datagridId);
	if (isEmpty(data))
		return;
	$('#' + windowId + ' form').each(function(index, row) {
		setFormValue(row.id, data);
	});
	$('#' + windowId).window("open");
}

function getDatagridOneRow(datagridId) {
	var rows = $('#' + datagridId).datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert('提示框', '请选择一行数据', 'warning');
		return;
	} else if (rows.length > 1) {
		$.messager.alert('提示框', '只能选择一行数据', 'warning');
		return;
	}
	return rows[0];
}

function setFormValue(_data) {
	$.each(_data, function(key, value) {
		$('#' + key).val(value);
		$('[name=' + key + ']').val(value);
	});
}

function setFormValue(formId, _data) {
	$.each(_data, function(key, value) {
		//$('#'+formId).children('#'+key).val(value);
			//$('#'+formId).find('[name='+key+']').val(value);
			//ar _temp = $('#'+formId).find("#"+key);
			_setValue($('#' + formId).find("#" + key), value)
			//_setValue($('#'+formId).children('#'+key),value)
			_setValue($('#' + formId).find('[name=' + key + ']'), value)
		});
}
function _setValue(_temp, value) {
	if (!isEmpty(_temp)) {
		_temp.val(value);
		if (!isEmpty(_temp.attr('constantId'))) {
			_temp.combobox('setValue', value);
		} else if (_temp.hasClass('easyui-datebox')) {
			_temp.datebox('setValue', value);
		}
	}
}

function setFormValueByName(formId, _data) {
	$.each(_data, function(key, value) {
		$('#' + formId).children('' + key).val(value);
		$('#' + formId).find('[name=' + key + ']').val(value);
		if ('groupId' == key)
			$('#groupId').combobox('setValue', value);
	});
}

function setReadonly(readonlyFields) {
	if (!isEmpty(readonlyFields)) {
		$.each(readonlyFields, function(key, value) {
			if($('#' + value).is('select')) {
				$('#' + value).attr('disabled', 'disabled');
			} else {
				$('#' + value).attr('readonly', 'readonly');
			}
		});
	}

}

//修改系统配置文件设置
function doEdit(url) {
	if (url)
		$('#addForm').form.url = url; //表单提交路径
	submitForm("addForm", $('#addForm').form.url, function(data) {
		//eval('data='+data); 
			data = convertJson(data);
			if (data.result == "ok") {
				$('#addDlg').dialog('close');
				showBox("提示信息", "修改成功", 'info');
				var pageNumber = $('#dataList').datagrid('getPager').data(
						"pagination").options.pageNumber;
				loadList(pageNumber);
				$('#addwindow').dialog('close');
			} else {
				//showBox("提示信息",data.result,'warning');
			showError(data);
		}
	});
}

//修改子项数据
function subDoEdit(url) {
	if (url)
		$('#subAddForm').form.url = url; //表单提交路径
	submitForm("subAddForm", $('#subAddForm').form.url, function(data) {
		data = convertJson(data);
		if (data.result == "ok") {
			$('#subAddwindow').dialog('close');
			showBox("提示信息", "修改成功", 'info');
			reloadList();
			$('#subAddwindow').dialog('close');
		} else {
			showError(data);
		}
	});
}

/**
 * 
 * @param url 删除的url
 * @param id  删除的条件
 */
function delRowData(jsonParam) {
	jsonParam = jsonParam || {};
	jsonParam.url = isEmpty(jsonParam.url) ? deleteUrl : jsonParam.url;
	var rows = $('#dataList').datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert('提示框', '请选择要删除的数据', 'warning');
	} else {
		var ids = "";
		for ( var i = 0; i < rows.length; i++) {
			ids += $(rows[i]).attr(jsonParam.id) + ",";
		}
		var _param = convertJson('{\"' + jsonParam.id + 's\":\"' + ids + '\"}');
		/**$.post(jsonParam.url, _param, function(data) {
			$.messager.alert('提示框','删除成功','info');
			$('#dataList').datagrid("reload");
			$('#dataList').datagrid("clearChecked");
		});**/
		Confirm('是否删' + rows.length + '条数据！', function() {
			var _param = convertJson('{\"' + jsonParam.id + 's\":\"' + ids
					+ '\"}');
			$.post(jsonParam.url, _param, function(data) {
				data = convertJson(data);
				if (data.result == 'ok') {
					$.messager.alert('提示框', '删除成功', 'info');
					$('#dataList').datagrid("reload");
					$('#dataList').datagrid("clearChecked");
				} else {
					showError(data);
				}

			});
		});
	}
}

/**
 * 子项删除界面
 * @param url 删除的url
 * @param id  删除的条件
 */
function subDelRowData(jsonParam) {
	jsonParam = jsonParam || {};
	jsonParam.url = isEmpty(jsonParam.url) ? subDeleteUrl : jsonParam.url;
	var rows = $('#subDataList').datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert('提示框', '请选择要删除的数据', 'warning');
	} else {
		var ids = "";
		for ( var i = 0; i < rows.length; i++) {
			ids += $(rows[i]).attr(jsonParam.id) + ",";
		}
		var _param = convertJson('{\"' + jsonParam.id + 's\":\"' + ids + '\"}');
		Confirm('是否删' + rows.length + '条数据！', function() {
			var _param = convertJson('{\"' + jsonParam.id + 's\":\"' + ids + '\"}');
			$.post(jsonParam.url, _param, function(data) {
				data = convertJson(data);
				if (data.result == 'ok') {
					$.messager.alert('提示框', '删除成功', 'info');
					reloadList();
					$('#subDataList').datagrid("clearChecked");
				} else {
					showError(data);
				}
			});
		});
	}
}

//重新加载子列表
function reloadList() {
	var selectType = $('#dataList').datagrid('getSelected');
	var pageNumber = $('#subDataList').datagrid('getPager').data("pagination").options.pageNumber;
	var pageSize = $('#subDataList').datagrid('getPager').data("pagination").options.pageSize;
	$.post(subSearchUrl, {
		"tablename" : selectType.modeldatatable,
		"page" : pageNumber,
		"rows" : pageSize
	}, function(response) {
		response = convertJson(response);
		if (response.result == 'ok') {
			refreshDatagrid('subDataList', pageNumber, pageSize);
			$('#subDataList').datagrid('loadData', response);
		} else {
			showBox("提示信息", response.result, 'warning');
		}
	});
}

//加载系统配置文件设置列表
function loadList(pageNumber, searchForm, datagridId, pageSize) {
	if (isEmpty(searchForm)) {
		searchForm = 'searchForm';
	}
	if (isEmpty(datagridId)) {
		datagridId = 'dataList';
	}
	var pager = $('#' + datagridId).datagrid('getPager');
	var rows = pager.pagination('options').pageSize;
	if (!isEmpty(pageSize)) {
		rows = pageSize;
		/*if(!isEmpty(pager)){
			pager.pagination('options').pageSize=pageSize;
		}*/
	}
	var searchP = getFormJson(searchForm);

	$(searchP).attr('page', pageNumber);
	$(searchP).attr('rows', rows);
	var url = $('#' + datagridId).datagrid("options").url;
	if (isEmpty(url)) {
		url = searchUrl;
	}
	
	$.post(url, searchP, function(response) {
		response = convertJson(response);
		if (response.result == 'ok') {
			refreshDatagrid(datagridId, pageNumber, pageSize);
			$('#' + datagridId).datagrid('loadData', response);
		} else {
			showBox("提示信息", response.result, 'warning');
		}
	});
}

/**
 * 表单提交
 * 
 */
function submitForm(formId, url, handler) {
	// 判断当前操作表单提交action
	var t_handler = handler || function() {
	};
	var cmitUrl = url;

	$('#' + formId).form('submit', {
		url : cmitUrl,
		onSubmit : function() { // 提交前的验证				
			//valiDateCss(true);//初始化验证框
		return $(this).form('validate');
	},
	success : function(data) {
		if (typeof t_handler === "function") {
			t_handler(data);
		}
	}
	});
}

function showError(data) {
	var bl = false;
	var errors = data.error;
	if (!isEmpty(errors)) {
		var i = 0;
		$(errors).each(
				function(index, error) {
					$('#' + error.field + 'Error').remove();
					var field = $('#' + error.field);
					if (isEmpty(field.lenght) || field.lenght == 0) {
						if (i == 0) {
							showBox("提示信息", error.msg, 'warning');
						}
						i++;
					} else
						field.after("<span id='" + error.field + 'Error'
								+ "' style='color:red'>" + error.msg
								+ "</span>");
				});
		$('#' + errors[0].field + 'Error').focus();
		bl = true;
	}
	if (!isEmpty(data.errorMsg)) {
		showBox("提示信息", data.errorMsg, 'warning');
		bl = true;
	}
	return bl;
}

function delError() {
	$('span[id$="Error"]').remove();
}
/**
 * 合并数组
 * @param result
 * @param src
 * @returns
 */
function extend(result, src) {
	/* if(src instanceof Array){
	     for(var i = 0, len = src.length; i < len; i++)
	  	   $(result).attr(src[i].name,src[i].value);
	  	   //result = jQuery.extend(result, src[i]);
	 }*/
	$(src).each(function(index, row) {
		$(result).attr(row.name, row.value);
	})
	return result;
}

function getFormJson(formId) {
	var data = $("#" + formId).serializeArray();
	var result = {};
	extend(result, data);
	return result;
}

//确认框   
function Confirm(msg, control) {
	$.messager.confirm("确认", msg, function(r) {
		if (r) {
			control();
		}
	});
}

/**
 * 刷新DataGrid
 */
function refreshDatagrid(dgid, pageNumber, pageSize) {
	$('#' + dgid).datagrid('options').pageNumber = pageNumber;
	if (isEmpty(pageSize)) {
		$('#' + dgid).datagrid('getPager').pagination( {
			pageNumber : pageNumber
		});
	} else {
		$('#' + dgid).datagrid('options').pageSize = pageSize;
		$('#' + dgid).datagrid('getPager').pagination( {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
	}

}

function loadConstant(select) {
	var key = select.attr('constantId');
	var constantValue = select.attr('constantValue');
	var constantDisplay = select.attr('constantDisplay');
	var condition = select.attr('condition');
	if (key.indexOf("|") < 0) {
		if (!isEmpty(constantValue) && !isEmpty(constantDisplay)) {
			key = key + "|" + constantValue + "|" + constantDisplay;
			if (!isEmpty(condition))
				key = key + "|" + condition;
		} else if (!isEmpty(condition)) {
			key = key + "||" + condition;
		}
	}

	if (isEmpty($(constantDatas).attr(key))) {
		$.post(path + "/comm/searchConstant.do", {
			key : key
		}, function(data) {
			data = convertJson(data);
			if (data.result == 'ok') {
				var constant = data.data;
				$(constantDatas).attr(select.attr('constantId'), constant);
				var tempKey = key.split("|")[0];
				
				if(key.indexOf("&")<0){
					tempKey = key.split("&")[0];
				}else{
					tempKey = key.split("&")[1];
				}
				$(constantDatas).attr(tempKey, constant);
				addOption(constant, select);
			} else {
				showBox("提示信息", data.errorMsg, 'warning');
			}
		});
	} else {
		if(key.indexOf("&")<0){
			tempKey = key.split("&")[0];
		}else{
			tempKey = key.split("&")[1];
		}
		var constant = constantDatas.attr(key);
		addOption(constant, select);
	}
}

/**
 * <select id="sdJStatus" name="dJStatus" constantId="aaa" default="1" showNull='false'></select>
 * constantId 常量type
 * showNull 如果为false 则不显示空选项，否则显示
 * default 是默认选中项
 * 
 */
function addOption(constant, select) {
	var jsonData = new Array();
	;

	if (isEmpty(select.attr('showNull')) || select.attr('showNull') != 'false'
			|| select.attr('showNull') == false) {
		if (isEmpty(select.attr('showNull'))) {
			//select.append("<option value=''>请选择</option>");
			jsonData.push( {
				value : '',
				display : '请选择'
			});
		} else {
			//select.append("<option value=''>"+select.attr('showNull')+"</option>");
			jsonData.push( {
				value : '',
				display : select.attr('showNull')
			});
		}
	}

	$(constant).each(
			function(index, row) {
				if (!isEmpty(select.attr('default'))
						&& select.attr('default') == row.value) {
					jsonData.push( {
						value : row.value,
						display : row.display,
						"selected" : true
					});
					//select.append("<option value='"+row.value+"' selected='selected'>"+row.display+"</option>");
				} else {
					//select.append("<option value='"+row.value+"'>"+row.display+"</option>");
					jsonData.push( {
						value : row.value,
						display : row.display
					});
				}
			});
	select.combobox('loadData', jsonData);
}

function loadConstant2(key) {
	if (isEmpty(constantDatas)) {
		$.post(path + "/comm/searchConstant.do", {
			key : key
		}, function(data) {
			data = convertJson(data);
			if (data.result == 'ok') {
				var constant = data.data;
				addOption(constant, key);
				/**$('[constantId="'+key+'"]').append("<option value=''>请选择</option>");
				$(constant).each(function(index,row){
					$('[constantId]').append("<option value='"+row.value+"'>"+row.display+"</option>");
				});**/
			} else {
				showBox("提示信息", data.errorMsg, 'warning');
			}
		});
	} else {
		var constant = constantDatas.attr(key);
		addOption(constant, key);
		/**$('[constantId="'+key+'"]').append("<option value=''>请选择</option>");
		$(constant).each(function(index,row){
			$('[constantId]').append("<option value='"+row.value+"'>"+row.display+"</option>");
		});**/
	}
	/**$.post(path+"/comm/searchConstant.do",{key:key},function(data){
		data = convertJson(data);
		if(data.result == 'ok'){
			$('[constantId]').append("<option value=''>请选择</option>");
			$(data.data).each(function(index,row){
				$('[constantId]').append("<option value='"+row.value+"'>"+row.display+"</option>");
			});
		}else{
			showBox("提示信息",data.errorMsg,'warning');
		}
	});
	 **/
}

/**
 * <select id="sdJStatus" name="dJStatus" constantId="aaa" default="1" showNull='false'></select>
 * constantId 常量type
 * showNull 如果为false 则不显示空选项，否则显示
 * default 是默认选中项
 * 

 function addOption2(constant,key){
 var selects = $('[constantId="'+key+'"]');
 selects.each(function(index,select){
 select = $(select);
 if(isEmpty(select.attr('showNull'))||select.attr('showNull')!='false'||select.attr('showNull')==false){
 if(isEmpty(select.attr('showNull')))
 select.append("<option value=''>请选择</option>");
 else
 select.append("<option value=''>"+select.attr('showNull')+"</option>");
 }

 $(constant).each(function(index,row){
 if(!isEmpty(select.attr('default'))&&select.attr('default')==row.value){
 select.append("<option value='"+row.value+"' selected='selected'>"+row.display+"</option>");
 }else 
 select.append("<option value='"+row.value+"'>"+row.display+"</option>");
 });
 });

 }
 */

//加载全部的常量放入  constantDatas 中
function ajaxConstants() {
	$.ajax( {
		type : "POST",
		url : path + "/comm/loadConstants.do",
		data : {
			constantKeys : constantKeys
		},
		async : false,
		success : function(data) {
			data = convertJson(data);
			if (data.result == 'ok') {
				constantDatas = data.data;
			} else {
				showBox("提示信息", data.errorMsg, 'warning');
			}
		}
	});
}

function ajaxConstants(_constantKeys) {
	constantKeys = _constantKeys;
	$.ajax( {
		type : "POST",
		url : path + "/comm/loadConstants.do",
		data : {
			constantKeys : constantKeys
		},
		async : false,
		success : function(data) {
			data = convertJson(data);
			if (data.result == 'ok') {
				constantDatas = $(data.data);
				for ( var cons in constantDatas[0]) {
					if (cons.indexOf('|') > 0) {
						var tempKey = cons.split("|")[0];
						$(constantDatas)
								.attr(tempKey, constantDatas.attr(cons));
					}

				}
			} else {
				showBox("提示信息", data.errorMsg, 'warning');
			}
		}
	});
}

function getConstantDisplay(key, value) {
	var result = value;
	if (!isEmpty(constantDatas)) {
		$(constantDatas.attr(key)).each(function(index, row) {
			if (row.value == value) {
				result = row.display;
				return;
			}
		});
	}
	return result;
}

function getConstantValue(key, display) {
	var result = display;
	if (!isEmpty(constantDatas)) {
		$(constantDatas.attr(key)).each(function(index, row) {
			if (row.display == display) {
				result = row.value;
				return;
			}
		});
	}
	return result;
}
/**
 * 
 * @param data
 * @param jsonData {showText:"",windowId:}  
 * @return
 */
function sendTrans(data, jsonData) {
	if (isEmpty(jsonData))
		jsonData = {};
	data = convertJson(data);
	if (isEmpty(jsonData.showText))
		jsonData.showText = "请重新向监管中心发送报文?";
	if (isEmpty(jsonData.refresh))
		jsonData.refresh = 1;
	if (!isEmpty(data.sendInfo)) {
		Confirm(jsonData.showText, function() {
			wait("正在重发中....");
			$.post(path + "/comm/sendTrans.do", data.sendInfo, function(data) {
				data = convertJson(data);
				closeWait()
				if (!isEmpty(data.sendInfo)) {
					jsonData.showText = "重发失败，是否再次重发报文？";
					sendTrans(data, jsonData);
				} else {
					if (data.result == "ok") {
						showBox("提示信息", "发送成功！", 'info');
					} else {
						showError(data);
					}
					if (!isEmpty(jsonData.windowId))
						closeWindow(jsonData.windowId);
					//if(!isEmpty(jsonData.refresh))
					//	loadList(jsonData.refresh);
				}
				loadList(jsonData.refresh);
			});
		})
	}
}

function addCheckInput(ids) {
	$(ids).each(function(index, id) {
		//		if($("#"+id)[0].tagName=='SELECT'){
			//			$("#"+id)..after("<span style='color:red'>*</span>");
			//		}else
			$("#" + id).next('span[name="_checkInput"]').remove();
			$("#" + id).after(
					"<span name='_checkInput' style='color:red'>*</span>");
		});
}
//为空返回false
function checkInput(ids, msg) {
	var bl = true;
	$(ids).each(function(index, id) {
		var value = $("#" + id).val();
		//var value = getVal(id);
			if (isEmpty(value)) {
				showBox("提示信息", msg[index], 'info', id);
				bl = false;
				return bl;
			}
		});
	return bl;
}

function getVal(id) {
	var obj = $("#" + id);
	var val;
	if (!isEmpty(obj.css('datebox'))) {
		val = obj.datebox("getValue");
	} else if (!isEmpty(obj.css('combobox'))) {
		val = obj.combobox("getValue");
	} else {
		val = $("#" + id).val();
	}

	return val;
}

/**
 * 提示框
 * @returns
 */
var showBox = function(title, content, m_type, id) {
	$.messager.alert(title, content, m_type, function() {
		if (!isEmpty(id)) {
			$("#" + id).focus();
		}
	});
};

function closeWindow(id) {
	$("#" + id).window("close");
}

/**
 * 判断是否为空
 * @param exp
 * @returns {Boolean}
 */
function isEmpty(exp) {
	var bl = false;
	if (typeof exp === "undefined") {
		bl = true;
	} else if (typeof exp === "string" && !exp) {
		bl = true;
	}

	return bl;
}

/**
 * 判断是否为方法
 * @param exp
 * @returns {Boolean}
 */
function isFunction(exp) {
	var bl = false;
	if (typeof (exp) == "function") {
		bl = true;
	}

	return bl;
}

/**
 * 转换为json对象
 * 
 * @param data
 * @returns
 */
function convertJson(data) {
	if (typeof data === 'string') {
		if (data.indexOf('<HTML>') > 0 && data.indexOf('<BODY>') > 0) {
			parent.window.location.href = path + '/login.jsp';
			return;
		}
		data = jQuery.parseJSON(data);
	}
	return data;
}
/**
 * 等待效果
 * title
 */
function wait(title) {
	if (isEmpty(title)) {
		title = "正在处理，请稍候。。。";
	}
	$("<div class=\"datagrid-mask\" style=\"z-index: 990000;\"></div>").css( {
		display : "block",
		width : "100%",
		height : "100%"
	}).appendTo("body");
	$("<div class=\"datagrid-mask-msg\" style=\"z-index: 999000;\"></div>")
			.html(title).appendTo("body").css( {
				display : "block",
				left : (document.body.scrollWidth - 190) / 2,
				top : (document.body.clientHeight - 45) / 2
			});
}
//删除等待
function closeWait() {
	$('.datagrid-mask').remove();
	$('.datagrid-mask-msg').remove();
}

function paginationConfig(datagridId, searchFormId) {
	var datagrid = $('#' + datagridId);
	gridPager = datagrid.datagrid('getPager');
	if (gridPager) {
		$(gridPager).pagination( {
			onBeforeRefresh : function(pageNumber, pageSize) {
				loadList(pageNumber, searchFormId, datagridId, pageSize);
				return false;
			},
			onSelectPage : function(pageNumber, pageSize) {
				loadList(pageNumber, searchFormId, datagridId, pageSize);
			}
		});
	}
}

function clearNoNum(obj) {
	obj.value = obj.value.replace(/[^\d.]/g, ""); // 清除“数字”和“.”以外的字符
	obj.value = obj.value.replace(/^\./g, ""); // 验证第一个字符是数字而不是.
	obj.value = obj.value.replace(/\.{2,}/g, "."); // 只保留第一个. 清除多余的.
	obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$",
			".");

}
//length 小数点位数
function inputNums(ids, length) {
	if (isEmpty(length)) {
		length = 2;
	}
	$(ids).each(function(index, id) {
		/*JQuery 限制文本框只能输入数字和小数点*/
		$("#" + id).keyup(function() {
			var val = $(this).val();
			if (!checkMoneyFormat(val)) {
				val = (val + "").replace(/[^0-9|.]*/g, '');
				//val = (val+"").replace(/\.$/g,'');
				//val = (val+"").replace(/\.{2,}/g,'');
				var strs = val.split('.');
				var str = '';
				if (strs.length > 1) {
					str = strs[strs.length - 1];
					if (str.length > length) {
						str = str.substring(0, length);
					}
					$(this).val(strs[0] + '.' + str);
				} else {
					$(this).val(strs[0]);
				}
				//eval("/^\\d+" + v + "$/gim")

			}
			//$(this).val($(this).val().replace(/[^0-9.]/g,''));    
		}).bind("paste", function() { //CTR+V事件处理    
					var val = $(this).val();
					if (!checkMoneyFormat(val)) {
						val = (val + "").replace(/[^0-9|.]*/g, '');
						//val = (val+"").replace(/\.$/g,'');
				//val = (val+"").replace(/\.{2,}/g,'');
				var strs = val.split('.');
				var str = '';
				if (strs.length > 1) {
					str = strs[strs.length - 1];
					if (str.length > length) {
						str = str.substring(0, length);
					}
					$(this).val(strs[0] + '.' + str);
				} else {
					$(this).val(strs[0]);
				}
			}
		}).css("ime-mode", "disabled"); //CSS设置输入法不可用   
		});
}

function inputNums2(ids) {
	$(ids).each(function(index, id) {
		inputNum2(id);
	});
}
//可以以0开头
function inputNum2(id) {
	$("#" + id).keyup(function() {
		$(this).val($(this).val().replace(/\D/g, ''));
	}).bind("paste", function() { //CTR+V事件处理    
				$(this).val($(this).val().replace(/\D/g, ''));
			}).css("ime-mode", "disabled"); //CSS设置输入法不可用 
}

//JS替换
String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
}

function checknumeric2(str) {
	var checks = new RegExp("^\\d{0,2}$");
	if (checks.test(str)) {
		//return true;
	} else {
		showBox("提示信息", "请输入两位小数！", 'info');
	}
}

function checkMoneyFormat(val, length) {
	if (isEmpty(length)) {
		length = 2;
	}
	var reg = new RegExp('^\-?\\d+\\.?\\d{0,' + length + '}$', 'g');
	var isMoneyFormatRight = reg.test(val);

	return isMoneyFormatRight;
}

//

function checkLength(ids, length) {
	if (isEmpty(length)) {
		length = 8;
	}
	$(ids).each(function(index, id) {
		$("#" + id).keyup(function() {
			var val = $(this).val().toString();
			;
			if (val.length > length) {
				$(this).val((val + "").substring(0, length));
			}
		}).bind("paste", function() { //CTR+V事件处理    
					var val = $(this).val().toString();
					;
					if (val.length > length) {
						$(this).val((val + "").substring(0, length));
					}
				}).css("ime-mode", "disabled"); //CSS设置输入法不可用 
		})
}

/**
 * 传递json对象
 * @param downLoadUrl  				下载链接
 * @param parameters 				参数 json对象
 * @param searchFormId 				查询条件
 * @param datagrid 					列表id
 * @param downTitle 					标题
 * @param downfileName 					文件名称
 * @param serviceObjId 				spring Dao id
 * @param methodName 				方法名称
 * @param constantKeys 				常量转换
 *  @param titleHeight 标题高度
 *  @param isShowTitle 是否显示'false'不显示 默认显示
 *  @param titleFontSize 标题大小
 *  
 * @return
 */
function downLoad(jsonData) {
	if (isEmpty(jsonData))
		jsonData = {};
	var downLoadUrl = $(jsonData).attr('downLoadUrl');
	var searchFormId = $(jsonData).attr('searchFormId');
	var datagrid = $(jsonData).attr('datagrid');
	var downTitle = $(jsonData).attr('downTitle');
	var downfileName = $(jsonData).attr('downfileName');
	var serviceObjId = $(jsonData).attr('serviceObjId');
	var methodName = $(jsonData).attr('methodName');
	var _constantKeys = $(jsonData).attr('constantKeys');
	var isShowTitle = $(jsonData).attr('isShowTitle');
	var titleHeight = $(jsonData).attr('titleHeight');
	var titleFontSize = $(jsonData).attr('titleFontSize');
	if (isEmpty(_constantKeys))
		_constantKeys = constantKeys;
	//var tabs = parent.document.getElementById('tabs');
	if (isEmpty(downTitle) && isEmpty(downfileName)) {
		var tabs = $(window.parent.document).find("#tabs").find(
				"li[class='tabs-selected']").find("span")[0];
		downTitle = $(tabs).html();
		downfileName = downTitle;
	}

	//var tab = $(tabs).tabs('options');

	if (isEmpty(downLoadUrl))
		downLoadUrl = path + "/comm/exportExcel.do";
	if (isEmpty(searchFormId))
		searchFormId = 'searchForm';
	if (isEmpty(datagrid))
		datagrid = 'dataList';
	if (isEmpty(methodName) && !isEmpty(searchUrl)) {
		var strs = searchUrl.split('/');
		methodName = strs[strs.length - 1];
		//带参数url
		methodName = methodName.split('?');
		if (methodName.length > 1) {
			var pa = methodName[1];
			var pas = pa.split('&');
			for ( var i = 0; i < pas.length; i++) {
				var p = pas[i].split('=');
				$(jsonData).attr("s_" + p[0], p[1]);
			}
		}
		methodName = methodName[0];
		//methodName = methodName.replace(/.do/,'');

		methodName = methodName.split('.')[0];
		//methodName=searchUrl;
	}
	if (isEmpty(serviceObjId) && !isEmpty(methodName)) {
		serviceObjId = methodName.replace(/search/, '');
		serviceObjId = serviceObjId.substr(0, 1).toLowerCase()
				+ serviceObjId.substr(1) + "Mapper";
	}
	if (isEmpty(downTitle)) {
		downTitle = downfileName;
	}
	if (isEmpty(downfileName)) {
		downfileName = downTitle;
	}

	//查询条件
	var searchForm = getFormJson(searchFormId);
	for ( var item in searchForm) {
		$(jsonData).attr('s_' + item, searchForm[item]);
	}

	var columnInfos = $('#' + datagrid).datagrid('options').columns;
	columnInfos = JSON.stringify(columnInfos);
	//var searchP = getFormJson(searchForm);

	$(jsonData).attr('downLoadUrl', downLoadUrl);
	$(jsonData).attr('datagrid', datagrid);
	$(jsonData).attr('serviceObjId', serviceObjId);
	$(jsonData).attr('methodName', methodName);
	$(jsonData).attr('downTitle', downTitle);
	$(jsonData).attr('downfileName', downfileName);
	$(jsonData).attr('columnInfos', columnInfos);
	$(jsonData).attr('constantKeys', _constantKeys);

	//	$.post(path+"/comm/checkExportExcel.do",jsonData,function(data){
	//		
	//		data = convertJson(data);
	//		if(data.result=="ok"){
	var form = $("<form>"); //定义一个form表单
	form.attr('style', 'display:none'); //在form表单中添加查询参数
	form.attr('target', '');
	form.attr('method', 'post');
	form.attr('action', downLoadUrl);
	for ( var item in jsonData) {
		addHide(item, jsonData[item], form);
	}
	$('body').append(form); //将表单放置在web中
	form.submit();
	//		}else{
	//			showError(data);
	//		}
	//	});

}

function addHide(name, value, form) {
	var input1 = $('<input>');
	input1.attr('type', 'hidden');
	input1.attr('name', name);
	input1.attr('value', value);
	if (!isEmpty(form))
		form.append(input1);
	return input1;
}

function printHtml(printId, iframeId) {
	pagesetup_null();
	if (isEmpty(iframeId)) {
		document.body.innerHTML = document.getElementById(printId).innerHTML;
		window.print();
	} else {
		var myWindow = document.getElementById(iframeId).contentWindow;
		if (!isEmpty(printId))
			myWindow.document.body.innerHTML = myWindow.document
					.getElementById(printId).innerHTML;
		myWindow.print();
	}

}

function printView() {
	//wb.execwb(1,1)//打开
	//wb.ExecWB(2,1);//关闭现在所有的IE窗口，并打开一个新窗口
	//wb.ExecWB(4,1)//;保存网页
	//wb.ExecWB(6,1)//打印
	//wb.ExecWB(7,1)//打印预览
	//wb.ExecWB(8,1)//打印页面设置
	//wb.ExecWB(10,1)//查看页面属性
	//wb.ExecWB(15,1)//好像是撤销，有待确认
	//wb.ExecWB(17,1)//全选
	//wb.ExecWB(22,1)//刷新
	//wb.ExecWB(45,1)//关闭窗体无提示
}

var hkey_root, hkey_path, hkey_key;
hkey_root = "HKEY_CURRENT_USER";
hkey_path = "\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
//配置网页打印的页眉页脚为空
function pagesetup_null() {
	try {
		var RegWsh = new ActiveXObject("WScript.Shell");
		hkey_key = "header";
		RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "");
		hkey_key = "footer";
		RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "");
		//&b 第&p页/共&P页 &b
	} catch (e) {
	}
}

function addDiyDomPower(treeId, treeNode) {
	var aObj = $("#" + treeNode.tId + "_a");
	var editStr = "<input type='checkbox' id='diyBtn_" + treeNode.id
			+ "_edit'>编辑<input type='checkbox' id='diyBtn_" + treeNode.id
			+ "_check'>查看";
	aObj.after(editStr);
}

function showAddMesswindow(jsonParam) {
	jsonParam = jsonParam || {};
	$('#addmessageForm')[0].reset();
	$('#addmessagewindow').removeAttr("disabled", "disabled");
	$('#addmessagewindow input').removeAttr("readonly");
	delError();
	jsonParam.title = isEmpty(jsonParam.title) ? $('#addmessagewindow').attr(
			'title') : jsonParam.title;
	//新增前处理
	if (typeof jsonParam.preHandler === "function")
		jsonParam.preHandler();

	$('#addmessagewindow').dialog( {
		title : jsonParam.title,
		buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			handler : function() {
				//确定按钮点击后的具体处理函数
			if (isFunction(jsonParam.insertHandler)) {
				jsonParam.insertHandler(jsonParam);
			} else {
				_insertHandlerMess(jsonParam.url);
			}

		}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				//取消前处理
			if (typeof jsonParam.clearHandler === "function")
				jsonParam.clearHandler();
			else
				$('#addmessagewindow').dialog('close');
		}
		} ],
		onOpen : function() {
			//树插件配置属性定义 ,详细API参照http://www.ztree.me/v3/api.php
		var setting = {
			edit : {
				/*设置 zTree 是否开启异步加载模式
				 * true 表示 开启 异步加载模式
				 * false 表示 关闭 异步加载模式
				 * */
				enable : true,
				/*设置是否显示删除按钮。[setting.edit.enable = true 时生效]
				 * true / false 分别表示 显示 / 隐藏 删除按钮
				 */
				showRemoveBtn : false,
				/*设置是否显示编辑名称按钮。[setting.edit.enable = true 时生效]
				 * true / false 分别表示 显示 / 隐藏 编辑名称按钮
				 * */
				showRenameBtn : false
			},
			data : {
				simpleData : {
					/*节点数据中保存唯一标识的属性名称。[setting.data.simpleData.enable = true 时生效]*/
					idKey : "id",
					/*节点数据中保存其父节点唯一标识的属性名称。[setting.data.simpleData.enable = true 时生效]*/
					pIdKey : "pId",
					/*确定 zTree 初始化时的节点数据、异步加载时的节点数据、或 addNodes 方法中输入的 newNodes 数据是否采用简单数据模式 (Array)
					 * 不需要用户再把数据库中取出的 List 强行转换为复杂的 JSON 嵌套格式
					 * 默认值：false
					 * true / false 分别表示 使用 / 不使用 简单数据模式
					 * */
					enable : true
				}
			},
			callback : {
			/*用于捕获节点被点击的事件回调函数
			 * 如果设置了 setting.callback.beforeClick 方法，且返回 false，将无法触发 onClick 事件回调函数。
			 * */
			// onClick: zTreeOnClickBudget
			},
			check : {
				enable : true,
				chkStyle : "checkbox",
				chkboxType : {
					"Y" : "ps",
					"N" : "ps"
				}
			}
		};
		var branchNodes = [];
		var groupNodes = [];
		var pmmNodes = [];
		var deptNodes = [];
		$.ajax( {
			url : getTreeDataUrl,
			dataType : "json",
			success : function(data) {
				for ( var i = 0; i < data.data.length; i++) {
					var obj = {
						"id" : data.data[i].grpId,
						"pId" : 0,
						"name" : data.data[i].grpCname
					};
					groupNodes.push(obj);
				}
				for ( var j = 0; j < data.branchData.length; j++) {
					var branchObj = {
						"id" : data.branchData[j].branchCode,
						"pId" : data.branchData[j].upCode,
						"name" : data.branchData[j].name
					};
					branchNodes.push(branchObj);
				}
				for ( var k = 0; k < data.productMainManage.length; k++) {
					var pmmObj = {
						"id" : data.productMainManage[k].code,
						"pId" : 0,
						"name" : data.productMainManage[k].name
					};
					pmmNodes.push(pmmObj);
				}
				for ( var m = 0; m < data.deptData.length; m++) {
					var deptObj = {
						"id" : data.deptData[m].departmentcode,
						"pId" : 0,
						"name" : data.deptData[m].departmentname
					};
					deptNodes.push(deptObj);
				}
				$.fn.zTree.init($("#group"), setting, groupNodes);
				$.fn.zTree.init($("#branch"), setting, branchNodes);
				$.fn.zTree.init($("#dept"), setting, deptNodes);
			}
		});
		$("#messageTab").tabs( {
			width : 550,
			height : 200
		});

	}
	});
	//新增后处理
	if (isFunction(jsonParam.afHandler))
		jsonParam.afHandler(jsonParam);
	$('#addpowerwindow').dialog('open');
}

var _insertHandlerMess = function(url) {
	var groupTree = $.fn.zTree.getZTreeObj("group");
	var branchTree = $.fn.zTree.getZTreeObj("branch");
	var deptTree = $.fn.zTree.getZTreeObj("dept");
	var groupChecked = groupTree.getCheckedNodes(true);
	var branchChecked = branchTree.getCheckedNodes(true);
	var deptChecked = deptTree.getCheckedNodes(true);
	var groupArr = "";
	var branchArr = "";
	var deptArr = ""
	for ( var i = 0; i < groupChecked.length; i++) {
		if (i != groupChecked.length - 1) {
			groupArr += groupChecked[i].id + ",";
		} else {
			groupArr += groupChecked[i].id;
		}
	}
	$("#noticegroup").val(groupArr);
	for ( var i = 0; i < branchChecked.length; i++) {
		if (i != branchChecked.length - 1) {
			branchArr += branchChecked[i].id + ",";
		} else {
			branchArr += branchChecked[i].id;
		}
	}
	$("#noticebranch").val(branchArr);
	for ( var i = 0; i < deptChecked.length; i++) {
		if (i != deptChecked.length - 1) {
			deptArr += deptChecked[i].id + ",";
		} else {
			deptArr += deptChecked[i].id;
		}
	}
	$("#noticedept").val(deptArr);
	$('#addmessageForm').form.url = url || insertUrl; //表单提交路径
	submitForm("addmessageForm", $('#addmessageForm').form.url, function(data) {
		data = convertJson(data);
		if (data.result == "ok") {
			$('#addmessageForm').form('clear'); // 清空form
			$('#dataList').datagrid('clearSelections');//清空选择
			$('#addmessagewindow').dialog('close');
			showBox("提示信息", "保存成功", 'info');
			var pageNumber = $('#dataList').datagrid('getPager').data(
					"pagination").options.pageNumber;
			loadList(pageNumber);
			$('#addmessagewindow').dialog('close');
		} else {
			showError(data);
			//showBox("提示信息",data.result,'warning');
		}
	});
}

/**
 * jsonParam 中的参数有 updateUrl,updateHandler,preHandler,afHandler,readonlyFields
 * @param jsonParam
 * @return
 */
function showProcessor(jsonParam) {
	//	$("#searchForm").hide();
	initDlg('#processorwindow').dialog( {
		title : jsonParam.title,
		buttons : [ {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$("#processorForm")[0].reset();
				$('#processorwindow').dialog('close');
			}
		} ],
		onOpen : function() {
			//			alert(jsonParam.readonlyFields);
		//			console.info($("#" + jsonParam.readonlyFields));
		//		var businessid = $("#" + jsonParam.readonlyFields).val();
		//			alert(businessid);
		$('#approveList').datagrid( {
			title : '列表',
			iconCls : 'icon-edit',//图标 
			width : 650,
			height : 250,
			pagination : true,//分页控件  
			rownumbers : true,//行号  
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			} ] ],
			url : getApproveRecordUrl,
			columns : [ [ {
				field : 'approverecordid',
				title : '审批过程编号',
				width : 150,
				align : 'center'
			}, {
				field : 'tasktype',
				title : '审批类型',
				width : 100,
				align : 'center',
				formatter : function(taskstatus) {
					if (taskstatus == '0')
						return "方案审批";
					return "预算审批";
				}
			}, {
				field : 'approveremark',
				title : '审批意见',
				width : 100,
				align : 'center'
			}, {
				field : 'approvestatus',
				title : '审批操作',
				width : 120,
				align : 'center',
				formatter : function(taskstatus) {
					if (taskstatus == '0')
						return "同意";
					return "拒绝";
				}
			} ] ],
			onLoadSuccess : function(data) {
				data = convertJson(data);
				if (data.result != 'ok') {
					showBox("提示信息", data.errorMsg, 'warning');
				}
			}

		});
		//设置分页控件  
		var p = $('#approveList').datagrid('getPager');
		$(p).pagination( {
			pageSize : 10,//每页显示的记录条数，默认为10  
			pageList : [ 10, 20, 30 ],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		})
	}
	});
	//新增后处理
	if (isFunction(jsonParam.afHandler))
		jsonParam.afHandler(jsonParam);
	$('#processorwindow').dialog('open');
}

function showAddMesswway(jsonParam) {
	jsonParam = jsonParam || {};
	jsonParam.updateUrl = isEmpty(jsonParam.setPowerUrl) ? setPowerUrl
			: jsonParam.setPowerUrl;
	jsonParam.title = isEmpty(jsonParam.title) ? $('#addpowerwindow').attr(
			'title') : jsonParam.title;
	$('#addmessageway').dialog( {
		title : jsonParam.title,
		buttons : [ {
			text : '提交',
			iconCls : 'icon-ok',
			handler : function() {
				//确定按钮点击后的具体处理函数
			if (isFunction(jsonParam.updateHandler)) {
				jsonParam.updateHandler(jsonParam);
			} else
				doEdit(jsonParam.setPowerUrl);

		}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$("#addmesswayForm")[0].reset();
				$('#addmessageway').dialog('close');
			}
		} ]
	});
	//新增后处理
	if (isFunction(jsonParam.afHandler))
		jsonParam.afHandler(jsonParam);
	$('#addmessageway').dialog('open');
}

//reportType  formId ,sql ,tableName,where
//orientation 方向  1 横向
function viewReport(jsonp) {
	reportType = $(jsonp).attr('reportType');
	if (isEmpty(reportType)) {
		reportType = 'Bir';
		$(jsonp).attr('reportType', reportType);
	}
	formId = $(jsonp).attr('formId');
	where = $(jsonp).attr('where');
	if (!isEmpty(formId)) {
		searchP = getFormJson(formId);
		for ( var p in searchP) {
			_val = $(searchP).attr(p);
			if (!isEmpty(_val)) {
				where = where + ';' + p + '=' + _val;
			} else {
				where = p + '=' + _val;
			}
		}
	}

	$('#report').remove();
	report = $('<div id="report"  title="图表" style="text-align: center"></div>');
	src = path + "/comm/report.do";
	new Date().valueOf();
	src = src + "?timestamp1=" + new Date().valueOf();

	//单表图表
	tableName = $(jsonp).attr('tableName');
	if (!isEmpty(tableName)) {
		src = src + "&tableName=" + tableName;
	}
	parameter = $(jsonp).attr('parameter');
	if (!isEmpty(parameter)) {
		src = src + "&parameter=" + parameter;
	}
	where = $(jsonp).attr('where');
	if (!isEmpty(where)) {
		src = src + '&where=' + where;
	}
	orderBy = $(jsonp).attr('orderBy');
	if (!isEmpty(orderBy)) {
		src = src + '&orderBy=' + orderBy;
	}

	//调用后台方法
	serviceObjId = $(jsonp).attr('serviceObjId');
	if (!isEmpty(serviceObjId)) {
		src = src + '&serviceObjId=' + serviceObjId;
	}
	methodName = $(jsonp).attr('methodName');
	if (!isEmpty(methodName)) {
		src = src + '&methodName=' + methodName;
	}
	//直接调用sql
	sql = $(jsonp).attr('sql');
	if (!isEmpty(sql)) {
		src = src + "&sql=" + sql;
	}

	//report 设置
	reportTitle = $(jsonp).attr('reportTitle');
	if (!isEmpty(reportTitle)) {
		src = src + '&reportTitle=' + reportTitle;
	}
	reportTitleX = $(jsonp).attr('reportTitleX');
	if (!isEmpty(reportTitleX)) {
		src = src + '&reportTitleX=' + reportTitleX;
	}
	reportTitleY = $(jsonp).attr('reportTitleY');
	if (!isEmpty(reportTitleY)) {
		src = src + '&reportTitleY=' + reportTitleY;
	}
	reportType = $(jsonp).attr('reportType');
	if (!isEmpty(reportType)) {
		src = src + '&reportType=' + reportType;
	}
	orientation = $(jsonp).attr('orientation');
	if (!isEmpty(orientation)) {
		src = src + '&orientation=' + orientation;
	}
	width = $(jsonp).attr('width');
	if (!isEmpty(width)) {
		src = src + '&width=' + width;
	}
	height = $(jsonp).attr('height');
	if (!isEmpty(height)) {
		src = src + '&height=' + height;
	}

	//src = src +"&reportType="+reportType+"&reportTitle=饼图&sql=select t.menuname type,t.menucode value1,t.menuparent value2 from TBLMENULIST t";
	$(
			'<img alt="" id="reportUrl" src="" style="text-align: center;margin-top: 5px;margin-bottom: 5px" >')
			.attr('src', src).appendTo(report);
	report.appendTo('body');
	report.window( {
		//width:700,   
		//height:450,
		maximized : true,
		title : '图表'
	});

	report.window('open');
}

//reportType  formId ,sql ,tableName,where
//orientation 方向  1 横向
function viewReport2(jsonp) {
	var searchP = '';
	var where = $(jsonp).attr('where');
	if (isEmpty(where)) {
		where = '';
	}
	for ( var p in jsonp) {
		val = $(jsonp).attr(p);
		if (p == 'formId') {
			searchForm = getFormJson(val);
			for ( var a in searchForm) {
				_val = $(searchForm).attr(a);
				if (!isEmpty(_val)) {
					where = where + ';' + a + '=' + _val;
				}
			}
			if (where.indexOf(';') == 0) {
				where = where.substring(1);
			}
			searchP = searchP + "&where=" + where;
		} else if (p == 'where' && isEmpty($(jsonp).attr('formId'))) {
			if (!isEmpty(val)) {
				searchP = searchP + "&" + p + "=" + val;
			}
		} else {
			if (p != 'where')
				searchP = searchP + "&" + p + "=" + val;
		}
	}

	$('#report').remove();
	report = $('<div id="report"  title="图表" style="text-align: center"></div>');
	src = path + "/comm/report.do";
	src = src + "?timestamp1=" + new Date().valueOf();
	src = src + "&" + searchP;
	$(
			'<img alt="" id="reportUrl" src="" style="text-align: center;margin-top: 5px;margin-bottom: 5px" >')
			.attr('src', src).appendTo(report);
	report.appendTo('body');
	report.window( {
		//width:700,   
		//height:450,
		maximized : true,
		title : '图表'
	});

	report.window('open');
}

function onbudgetcourseTreeClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("budgetcourseTree"), nodes = zTree
			.getSelectedNodes(), v = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name + ",";
	}
	if (v.length > 0)
		v = v.substring(0, v.length - 1);
	var cityObj = $("#budgetcourse");
	cityObj.attr("value", v);
}

function zTreeOnClickBudget(event, treeId, treeNode) {
	$("#budgetLevel").val(treeNode.level + 1);
	$.ajax( {
		url : searchGoalByCodeUrl,
		data : {
			'goalcode' : treeNode.id
		},
		dataType : "json",
		success : function(json) {
			json = convertJson(json);
			$("#goalcode").val(json.goalData.goalcode);
			$("#goalname").val(json.goalData.goalname);
			$("#goaltype").val(json.goalData.goaltype);
			$("#operatebranch").val(json.goalData.operatebranch);
			$("#budgetLevel").val(json.goalData.goallevel);
		},
		error : function() {
		}
	});
}

function delVal(self) {
	$(self).val("");
}

function addTreep() {
	var treeObj = $.fn.zTree.getZTreeObj("budgetTree");
	var newNode = {
		name : $("#addnodeName").val()
	};
	newNode = treeObj.addNodes(null, newNode);

}

function delTreep() {
	var treeObj = $.fn.zTree.getZTreeObj("budgetTree");
	var selectNode = treeObj.getSelectedNodes()[0];
	treeObj.removeNode(selectNode);

}

function showMenu() {
	$("#menuContent").fadeIn("fast");
	$("body").bind("mousedown", onBodyDown);
}

function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
			event.target).parents("#menuContent").length > 0)) {
		$("#menuContent").fadeOut("fast");
		;
	}
}

//打开预算模板界面
function showAddDemo(jsonParam) {
	$("#demoTbl").empty();
	$('#addDemo').removeAttr("disabled", "disabled");
	$('#addDemo input').removeAttr("readonly");
	$('#addDemo')
			.dialog(
					{
						title : jsonParam.title,
						buttons : [
								{
									text : '新增',
									iconCls : 'icon-ok',
									handler : function() {
										$.ajax( {
											url : insertUrl,
											data : formToString($("#adddm")
													.get(0)),
											success : function() {
												alert("已提交");
											}
										});
									}
								},
								{
									text : '新增指标',
									iconCls : 'icon-ok',
									handler : function() {
										$('#demoTbl').datagrid('appendRow', {
											zbselect : "请选择...",
											name : ""
										});
									}
								},
								{
									text : '发起审批',
									iconCls : 'icon-ok',
									handler : function() {
										$.ajax( {
											url : insertUrl,
											data : formToString($("#adddm")
													.get(0)),
											success : function() {
												$.messager.alert("提示",
														"发起审批成功！", "info")
												$('#addDemo').dialog('close');
											}
										});
									}
								},
								{
									text : '取消',
									iconCls : 'icon-cancel',
									handler : function() {
										//取消前处理
									if (typeof jsonParam.clearHandler === "function")
										jsonParam.clearHandler();
									else
										$('#addDemo').dialog('close');
								}
								} ],
						modal : true,
						onOpen : function() {
							var users = {
								total : 6,
								rows : [ {
									zbselect : "请选择...",
									name : ""
								} ]
							};
							$('#demoTbl')
									.datagrid(
											{
												striped : true,
												iconCls : 'icon-edit',
												width : 560,
												height : 300,
												view : detailview,
												detailFormatter : function(
														index, row) {
													return '<div id="ddv-' + index + '" style="padding:5px 0"></div>';
												},
												onExpandRow : function(index,
														row) {
													$('#ddv-' + index)
															.panel(
																	{
																		onLoad : function() {
																			$(
																					'#dg')
																					.datagrid(
																							'fixDetailRowHeight',
																							index);
																		}
																	});
													$('#dg')
															.datagrid(
																	'fixDetailRowHeight',
																	index);
												},
												singleSelect : true,
												columns : [ [ {
													field : 'goalid',
													title : '指标Id',
													width : 265
												}, {
													field : 'name',
													title : '指标名',
													width : 265
												} ] ],
												onDblClickCell : function(
														index, field, value) {
													var fieldOp = $('#demoTbl')
															.datagrid('options');
													var col = fieldOp.columns[0];
													for ( var i = 0; i < col.length; i++) {
														if (field == 'name'
																&& field == col[i].field) {
															col[i].editor = "numberbox";
														} else if (field == 'zbselect'
																&& field == col[i].field) {
															col[i].editor = {
																type : "combobox",
																options : {
																	valueField : "name",
																	textField : "name",
																	data : [
																			{
																				productid : "1",
																				name : "11111"
																			},
																			{
																				productid : "2",
																				name : "1221111"
																			},
																			{
																				productid : "3",
																				name : "1331111"
																			} ]
																}
															};
														}
													}
													$(this).datagrid(
															'beginEdit', index);
													var ed = $('#demoTbl')
															.datagrid(
																	'getEditor',
																	{
																		index : index,
																		field : field
																	});
													$(ed.target).focus();
												},
												onClickCell : function(index,
														field, value) {
													var rows = $('#demoTbl')
															.datagrid('getRows').length;
													for ( var i = 0; i < rows; i++) {
														$(this).datagrid(
																'endEdit', i);
													}
													var fieldOp = $('#demoTbl')
															.datagrid('options');
													var col = fieldOp.columns[0];
													for ( var i = 0; i < col.length; i++) {
														col[i].editor = "";
													}
												},
												onAfterEdit : function(
														rowIndex, rowData,
														changes) {
													var d = [];
													if (changes.zdselect != undefined) {

													}
												}
											}).datagrid('loadData', users)
									.datagrid('acceptChanges');
						}
					});
}

//打开设置公式
function showMath(title) {
	$("#mathDiv").css("display", "block")
	$("#mathDiv").dialog( {
		title : title,
		buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			handler : function() {

			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				//取消前处理
			if (typeof jsonParam.clearHandler === "function")
				jsonParam.clearHandler();
			else
				$('#addProcess').dialog('close');
		}
		} ],
		onOpen : function() {
			$("#val").empty();
			var allVal = zTreeBudgetObj.getNodes();
			addVal(allVal);
		}

	});
}

//增加指标按钮
function addVal(valList) {
	//树插件配置属性定义 ,详细API参照http://www.ztree.me/v3/api.php
	var setting = {
		edit : {
			/*设置 zTree 是否开启异步加载模式
			 * true 表示 开启 异步加载模式
			 * false 表示 关闭 异步加载模式
			 * */
			enable : true,
			/*设置是否显示删除按钮。[setting.edit.enable = true 时生效]
			 * true / false 分别表示 显示 / 隐藏 删除按钮
			 */
			showRemoveBtn : false,
			/*设置是否显示编辑名称按钮。[setting.edit.enable = true 时生效]
			 * true / false 分别表示 显示 / 隐藏 编辑名称按钮
			 * */
			showRenameBtn : false
		},
		data : {
			simpleData : {
				/*节点数据中保存唯一标识的属性名称。[setting.data.simpleData.enable = true 时生效]*/
				idKey : "id",
				/*节点数据中保存其父节点唯一标识的属性名称。[setting.data.simpleData.enable = true 时生效]*/
				pIdKey : "pId",
				/*确定 zTree 初始化时的节点数据、异步加载时的节点数据、或 addNodes 方法中输入的 newNodes 数据是否采用简单数据模式 (Array)
				 * 不需要用户再把数据库中取出的 List 强行转换为复杂的 JSON 嵌套格式
				 * 默认值：false
				 * true / false 分别表示 使用 / 不使用 简单数据模式
				 * */
				enable : true
			}
		},
		callback : {
			/*用于捕获节点被点击的事件回调函数
			 * 如果设置了 setting.callback.beforeClick 方法，且返回 false，将无法触发 onClick 事件回调函数。
			 * */
			onClick : zTreeOnClick
		}
	};
	zTreeObj = $.fn.zTree.init($("#val"), setting, valList);
	/*for ( var i = 0; i < valList.length; i++) {
		var inputHtml="<input value='"+valList[i].name+"' type='button' width='80' style='margin-left: 5px;' onclick='valClick(\""+valList[i].name+"\",\""+valList[i].id+"\")'/>";
		$("#val").append(inputHtml);
		if (valList[i].children!=undefined) {
			addVal(valList[i].children);
		}
	}*/
}

function mathClick(self) {
	var oldrule = $("#rules").val();
	var oldrulenext = $("#rules").next().val();
	$("#rules").val(oldrule + $(self).val());
	$("#rules").next().val(oldrule + $(self).val());
}

function zTreeOnClick(event, treeId, treeNode) {
	var oldrule = $("#rules").val();
	var oldrulenext = $("#rules").next().val();
	var express = treeNode.name;
	$("#rules").val(oldrule + express);
	$("#rules").next().val(oldrulenext + treeNode.id);
}

//打开预算详细页面
function showPoint(jsonParam) {
	$('#addPoint').removeAttr("disabled", "disabled");
	$('#addPoint input').removeAttr("readonly");
	$('#addPoint')
			.dialog(
					{
						title : jsonParam.title,
						buttons : [
								{
									text : '调整',
									iconCls : 'icon-ok',
									handler : function() {
										var selected = $('#pointdg').datagrid(
												'getSelected');
										var rowIndex = $('#pointdg').datagrid(
												'getRowIndex', selected);
										var fieldOp = $('#pointdg').datagrid(
												'options');
										var col = fieldOp.columns[0];
										for ( var i = 0; i < col.length; i++) {
											col[i].editor = "numberbox";
										}
										$('#pointdg').datagrid('beginEdit',
												rowIndex);
										$('#changeNum')
												.dialog(
														{
															title : "调整",
															buttons : [
																	{
																		text : '调整',
																		iconCls : 'icon-ok',
																		handler : function() {
																			var way = $("select[id=way]");
																			var cent = $("select[id=cent]");
																			var val = $("input[id^=val]");
																			for ( var i = 0; i < val.length; i++) {
																				if (way
																						.val() == "0") {
																					if (cent
																							.val() == "0") {
																						if (i == 0) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'January'
																											});
																							selected.January += selected.January
																									* (parseInt(val[0].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.January);
																						} else if (i == 1) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'February'
																											});
																							selected.February += selected.February
																									* (parseInt(val[1].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.February);
																						} else if (i == 2) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'March'
																											});
																							selected.March += selected.March
																									* (parseInt(val[2].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.March);
																						} else if (i == 3) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'quarter1'
																											});
																							selected.quarter1 += selected.quarter1
																									* (parseInt(val[3].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.quarter1);
																						} else if (i == 4) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'April'
																											});
																							selected.April += selected.April
																									* (parseInt(val[4].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.April);
																						} else if (i == 5) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'May'
																											});
																							selected.May += selected.May
																									* (parseInt(val[5].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.May);
																						} else if (i == 6) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'June'
																											});
																							selected.June += selected.June
																									* (parseInt(val[6].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.June);
																						} else if (i == 7) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'quarter2'
																											});
																							selected.quarter2 += selected.quarter2
																									* (parseInt(val[7].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.quarter2);
																						} else if (i == 8) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'July'
																											});
																							selected.July += selected.July
																									* (parseInt(val[8].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.July);
																						} else if (i == 9) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'August'
																											});
																							selected.August += selected.August
																									* (parseInt(val[9].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.August);
																						} else if (i == 10) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'September'
																											});
																							selected.September += selected.September
																									* (parseInt(val[10].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.September);
																						} else if (i == 11) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'quarter3'
																											});
																							selected.quarter3 += selected.quarter3
																									* (parseInt(val[11].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.quarter3);
																						} else if (i == 12) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'October'
																											});
																							selected.October += selected.October
																									* (parseInt(val[12].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.October);
																						} else if (i == 13) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'November'
																											});
																							selected.November += selected.November
																									* (parseInt(val[13].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.November);
																						} else if (i == 14) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'December'
																											});
																							selected.December += selected.December
																									* (parseInt(val[14].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.December);
																						} else if (i == 15) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'quarter4'
																											});
																							selected.quarter4 += selected.quarter4
																									* (parseInt(val[15].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.quarter4);
																						}
																					} else {
																						if (i == 0) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'January'
																											});
																							selected.January += parseInt(val[0].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.January);
																						} else if (i == 1) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'February'
																											});
																							selected.February += parseInt(val[1].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.February);
																						} else if (i == 2) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'March'
																											});
																							selected.March += parseInt(val[2].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.March);
																						} else if (i == 3) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'quarter1'
																											});
																							selected.quarter1 += parseInt(val[3].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.quarter1);
																						} else if (i == 4) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'April'
																											});
																							selected.April += parseInt(val[4].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.April);
																						} else if (i == 5) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'May'
																											});
																							selected.May += parseInt(val[5].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.May);
																						} else if (i == 6) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'June'
																											});
																							selected.June += parseInt(val[6].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.June);
																						} else if (i == 7) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'quarter2'
																											});
																							selected.quarter2 += parseInt(val[7].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.quarter2);
																						} else if (i == 8) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'July'
																											});
																							selected.July += parseInt(val[8].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.July);
																						} else if (i == 9) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'August'
																											});
																							selected.August += parseInt(val[9].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.August);
																						} else if (i == 10) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'September'
																											});
																							selected.September += parseInt(val[10].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.September);
																						} else if (i == 11) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'quarter3'
																											});
																							selected.quarter3 += parseInt(val[11].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.quarter3);
																						} else if (i == 12) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'quarter3'
																											});
																							selected.October += parseInt(val[12].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.quarter3);
																						} else if (i == 13) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'November'
																											});
																							selected.November += parseInt(val[13].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.November);
																						} else if (i == 14) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'December'
																											});
																							selected.December += parseInt(val[14].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.December);
																						} else if (i == 15) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'quarter4'
																											});
																							selected.quarter4 += parseInt(val[15].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.quarter4);
																						}
																					}
																				} else {
																					if (cent
																							.val() == "0") {
																						if (i == 0) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'January'
																											});
																							selected.January -= selected.January
																									* (parseInt(val[0].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.January);
																						} else if (i == 1) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'February'
																											});
																							selected.February -= selected.February
																									* (parseInt(val[1].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.February);
																						} else if (i == 2) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'March'
																											});
																							selected.March -= selected.March
																									* (parseInt(val[2].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.March);
																						} else if (i == 3) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'quarter1'
																											});
																							selected.quarter1 -= selected.quarter1
																									* (parseInt(val[3].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.quarter1);
																						} else if (i == 4) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'April'
																											});
																							selected.April -= selected.April
																									* (parseInt(val[4].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.April);
																						} else if (i == 5) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'May'
																											});
																							selected.May -= selected.May
																									* (parseInt(val[5].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.May);
																						} else if (i == 6) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'June'
																											});
																							selected.June -= selected.June
																									* (parseInt(val[6].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.June);
																						} else if (i == 7) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'quarter2'
																											});
																							selected.quarter2 -= selected.quarter2
																									* (parseInt(val[7].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.quarter2);
																						} else if (i == 8) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'July'
																											});
																							selected.July -= selected.July
																									* (parseInt(val[8].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.July);
																						} else if (i == 9) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'August'
																											});
																							selected.August -= selected.August
																									* (parseInt(val[9].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.August);
																						} else if (i == 10) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'September'
																											});
																							selected.September -= selected.September
																									* (parseInt(val[10].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.September);
																						} else if (i == 11) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'quarter3'
																											});
																							selected.quarter3 -= selected.quarter3
																									* (parseInt(val[11].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.quarter3);
																						} else if (i == 12) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'October'
																											});
																							selected.October -= selected.October
																									* (parseInt(val[12].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.October);
																						} else if (i == 13) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'November'
																											});
																							selected.November -= selected.November
																									* (parseInt(val[13].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.November);
																						} else if (i == 14) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'December'
																											});
																							selected.December -= selected.December
																									* (parseInt(val[14].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.December);
																						} else if (i == 15) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'quarter4'
																											});
																							selected.quarter4 -= selected.quarter4
																									* (parseInt(val[15].value) * 0.01);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.quarter4);
																						}
																					} else {
																						if (i == 0) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'January'
																											});
																							selected.January -= parseInt(val[0].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.January);
																						} else if (i == 1) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'February'
																											});
																							selected.February -= parseInt(val[1].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.February);
																						} else if (i == 2) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'March'
																											});
																							selected.March -= parseInt(val[2].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.March);
																						} else if (i == 3) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'quarter1'
																											});
																							selected.quarter1 -= parseInt(val[3].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.quarter1);
																						} else if (i == 4) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'April'
																											});
																							selected.April -= parseInt(val[4].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.April);
																						} else if (i == 5) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'May'
																											});
																							selected.May -= parseInt(val[5].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.May);
																						} else if (i == 6) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'June'
																											});
																							selected.June -= parseInt(val[6].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.June);
																						} else if (i == 7) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'quarter2'
																											});
																							selected.quarter2 -= parseInt(val[7].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.quarter2);
																						} else if (i == 8) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'July'
																											});
																							selected.July -= parseInt(val[8].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.July);
																						} else if (i == 9) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'August'
																											});
																							selected.August -= parseInt(val[9].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.August);
																						} else if (i == 10) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'September'
																											});
																							selected.September -= parseInt(val[10].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.September);
																						} else if (i == 11) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'quarter3'
																											});
																							selected.quarter3 -= parseInt(val[11].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.quarter3);
																						} else if (i == 12) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'quarter3'
																											});
																							selected.October -= parseInt(val[12].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.quarter3);
																						} else if (i == 13) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'November'
																											});
																							selected.November -= parseInt(val[13].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.November);
																						} else if (i == 14) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'December'
																											});
																							selected.December -= parseInt(val[14].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.December);
																						} else if (i == 15) {
																							var ed = $(
																									'#pointdg')
																									.datagrid(
																											'getEditor',
																											{
																												index : rowIndex,
																												field : 'quarter4'
																											});
																							selected.quarter4 -= parseInt(val[15].value);
																							$(
																									ed.target)
																									.attr(
																											'value',
																											selected.quarter4);
																						}
																					}
																				}
																			}
																			$(
																					'#pointdg')
																					.datagrid(
																							'endEdit',
																							rowIndex);
																			$(
																					'#changeNum')
																					.dialog(
																							'close');
																		}
																	},
																	{
																		text : '取消',
																		iconCls : 'icon-cancel'
																	} ]

														});
										var fieldOp = $(this).datagrid(
												'options');
										var col = fieldOp.columns[0];
										for ( var j = 0; j < col.length; j++) {
											col[j].editor = "";
										}
									}
								},
								{
									text : '确定',
									iconCls : 'icon-ok',
									handler : function() {
										//确定按钮点击后的具体处理函数
									/*if (isFunction(jsonParam.insertHandler))
									{	
										jsonParam.insertHandler(jsonParam);
									}else{
										_insertHandler(jsonParam.url);
									}*/

								}
								},
								{
									text : '保存',
									iconCls : 'icon-ok',
									handler : function() {
										//确定按钮点击后的具体处理函数
									/*if (isFunction(jsonParam.insertHandler))
									{	
										jsonParam.insertHandler(jsonParam);
									}else{
										_insertHandler(jsonParam.url);
									}*/

								}
								},
								{
									text : '刷新',
									iconCls : 'icon-ok',
									handler : function() {
										//确定按钮点击后的具体处理函数
									/*if (isFunction(jsonParam.insertHandler))
									{	
										jsonParam.insertHandler(jsonParam);
									}else{
										_insertHandler(jsonParam.url);
									}*/

								}
								},
								{
									text : '启动规则',
									iconCls : 'icon-ok',
									handler : function() {
										//确定按钮点击后的具体处理函数
									/*if (isFunction(jsonParam.insertHandler))
									{	
										jsonParam.insertHandler(jsonParam);
									}else{
										_insertHandler(jsonParam.url);
									}*/

								}
								},
								{
									text : '数据导出',
									iconCls : 'icon-ok',
									handler : function() {
										//确定按钮点击后的具体处理函数
									/*if (isFunction(jsonParam.insertHandler))
									{	
										jsonParam.insertHandler(jsonParam);
									}else{
										_insertHandler(jsonParam.url);
									}*/

								}
								},
								{
									text : '数据导入',
									iconCls : 'icon-ok',
									handler : function() {
										//确定按钮点击后的具体处理函数
									/*if (isFunction(jsonParam.insertHandler))
									{	
										jsonParam.insertHandler(jsonParam);
									}else{
										_insertHandler(jsonParam.url);
									}*/

								}
								},
								{
									text : '注释',
									iconCls : 'icon-ok',
									handler : function() {
										//确定按钮点击后的具体处理函数
									/*if (isFunction(jsonParam.insertHandler))
									{	
										jsonParam.insertHandler(jsonParam);
									}else{
										_insertHandler(jsonParam.url);
									}*/

								}
								},
								{
									text : '辅助信息',
									iconCls : 'icon-ok',
									handler : function() {
										//确定按钮点击后的具体处理函数
									/*if (isFunction(jsonParam.insertHandler))
									{	
										jsonParam.insertHandler(jsonParam);
									}else{
										_insertHandler(jsonParam.url);
									}*/

								}
								},
								{
									text : '发起审批',
									iconCls : 'icon-ok',
									handler : function() {
										//取消前处理
									if (typeof jsonParam.clearHandler === "function")
										jsonParam.clearHandler();
									else
										$('#addPoint').dialog('close');
								}
								} ],
						modal : true,
						onOpen : function() {
							var users = {
								total : 6,
								rows : [ {
									name : "指标1",
									January : 0,
									February : 0,
									March : 0,
									quarter1 : 0,
									April : 0,
									May : 0,
									June : 0,
									quarter2 : 0,
									July : 0,
									August : 0,
									September : 0,
									quarter3 : 0,
									October : 0,
									November : 0,
									December : 0,
									quarter4 : 0
								}, {
									name : "指标2",
									January : 0,
									February : 0,
									March : 0,
									quarter1 : 0,
									April : 0,
									May : 0,
									June : 0,
									quarter2 : 0,
									July : 0,
									August : 0,
									September : 0,
									quarter3 : 0,
									October : 0,
									November : 0,
									December : 0,
									quarter4 : 0
								}, {
									name : "指标3",
									January : 0,
									February : 0,
									March : 0,
									quarter1 : 0,
									April : 0,
									May : 0,
									June : 0,
									quarter2 : 0,
									July : 0,
									August : 0,
									September : 0,
									quarter3 : 0,
									October : 0,
									November : 0,
									December : 0,
									quarter4 : 0
								}, {
									name : "指标4",
									January : 0,
									February : 0,
									March : 0,
									quarter1 : 0,
									April : 0,
									May : 0,
									June : 0,
									quarter2 : 0,
									July : 0,
									August : 0,
									September : 0,
									quarter3 : 0,
									October : 0,
									November : 0,
									December : 0,
									quarter4 : 0
								}, {
									name : "指标5",
									January : 0,
									February : 0,
									March : 0,
									quarter1 : 0,
									April : 0,
									May : 0,
									June : 0,
									quarter2 : 0,
									July : 0,
									August : 0,
									September : 0,
									quarter3 : 0,
									October : 0,
									November : 0,
									December : 0,
									quarter4 : 0
								}, {
									name : "指标6",
									January : 0,
									February : 0,
									March : 0,
									quarter1 : 0,
									April : 0,
									May : 0,
									June : 0,
									quarter2 : 0,
									July : 0,
									August : 0,
									September : 0,
									quarter3 : 0,
									October : 0,
									November : 0,
									December : 0,
									quarter4 : 0
								}

								]
							};
							$('#pointdg')
									.datagrid(
											{
												striped : true,
												iconCls : 'icon-edit',
												width : 860,
												height : 250,
												singleSelect : true,
												view : detailview,
												detailFormatter : function(
														index, row) {
													return '<div id="ddv-' + index + '" style="height:70px;"></div>';
												},
												onExpandRow : function(index,
														row) {
													$('#ddv-' + index)
															.datagrid(
																	{
																		striped : true,
																		width : 760,
																		height : 70,
																		singleSelect : true,
																		frozenColumns : [ [ {
																			field : 'name',
																			title : '指标',
																			width : 60
																		} ] ],
																		columns : [ [
																				{
																					field : 'January',
																					title : '1月',
																					width : 60
																				},
																				{
																					field : 'February',
																					title : '2月',
																					width : 60
																				},
																				{
																					field : 'March',
																					title : '3月',
																					width : 60
																				},
																				{
																					field : 'quarter1',
																					title : '1季度',
																					width : 60
																				},
																				{
																					field : 'April',
																					title : '4月',
																					width : 60
																				},
																				{
																					field : 'May',
																					title : '5月',
																					width : 60
																				},
																				{
																					field : 'June',
																					title : '6月',
																					width : 60
																				},
																				{
																					field : 'quarter2',
																					title : '2季度',
																					width : 60
																				},
																				{
																					field : 'July',
																					title : '7月',
																					width : 60
																				},
																				{
																					field : 'August',
																					title : '8月',
																					width : 60
																				},
																				{
																					field : 'September',
																					title : '9月',
																					width : 60
																				},
																				{
																					field : 'quarter3',
																					title : '3季度',
																					width : 60
																				},
																				{
																					field : 'October',
																					title : '10月',
																					width : 60
																				},
																				{
																					field : 'November',
																					title : '11月',
																					width : 60
																				},
																				{
																					field : 'December',
																					title : '12月',
																					width : 60
																				},
																				{
																					field : 'quarter4',
																					title : '4季度',
																					width : 60
																				} ] ],
																		onDblClickCell : function(
																				index,
																				field,
																				value) {
																			var fieldOp = $(
																					this)
																					.datagrid(
																							'options');
																			var col = fieldOp.columns[0];
																			for ( var i = 0; i < col.length; i++) {
																				if (col[i].field == field) {
																					col[i].editor = "numberbox";
																				}
																			}
																			$(
																					this)
																					.datagrid(
																							'beginEdit',
																							index);
																			var ed = $(
																					this)
																					.datagrid(
																							'getEditor',
																							{
																								index : index,
																								field : field
																							});
																			$(
																					ed.target)
																					.focus();
																		},
																		onClickCell : function(
																				index,
																				field,
																				value) {
																			var rows = $(
																					this)
																					.datagrid(
																							'getRows').length;
																			for ( var i = 0; i < rows; i++) {
																				$(
																						this)
																						.datagrid(
																								'endEdit',
																								i);
																			}
																			var fieldOp = $(
																					this)
																					.datagrid(
																							'options');
																			var col = fieldOp.columns[0];
																			for ( var i = 0; i < col.length; i++) {
																				col[i].editor = "";
																			}
																		}
																	})
															.datagrid(
																	'loadData',
																	users)
															.datagrid(
																	'acceptChanges');

												},
												frozenColumns : [ [ {
													field : 'name',
													title : '指标',
													width : 60
												} ] ],
												columns : [ [ {
													field : 'January',
													title : '1月',
													width : 60
												}, {
													field : 'February',
													title : '2月',
													width : 60
												}, {
													field : 'March',
													title : '3月',
													width : 60
												}, {
													field : 'quarter1',
													title : '1季度',
													width : 60
												}, {
													field : 'April',
													title : '4月',
													width : 60
												}, {
													field : 'May',
													title : '5月',
													width : 60
												}, {
													field : 'June',
													title : '6月',
													width : 60
												}, {
													field : 'quarter2',
													title : '2季度',
													width : 60
												}, {
													field : 'July',
													title : '7月',
													width : 60
												}, {
													field : 'August',
													title : '8月',
													width : 60
												}, {
													field : 'September',
													title : '9月',
													width : 60
												}, {
													field : 'quarter3',
													title : '3季度',
													width : 60
												}, {
													field : 'October',
													title : '10月',
													width : 60
												}, {
													field : 'November',
													title : '11月',
													width : 60
												}, {
													field : 'December',
													title : '12月',
													width : 60
												}, {
													field : 'quarter4',
													title : '4季度',
													width : 60
												} ] ],
												onDblClickCell : function(
														index, field, value) {
													var fieldOp = $(this)
															.datagrid('options');
													var col = fieldOp.columns[0];
													for ( var i = 0; i < col.length; i++) {
														if (col[i].field == field) {
															col[i].editor = "numberbox";
														}
													}
													$(this).datagrid(
															'beginEdit', index);
													var ed = $(this).datagrid(
															'getEditor', {
																index : index,
																field : field
															});
													$(ed.target).focus();
												},
												onClickCell : function(index,
														field, value) {
													var rows = $(this)
															.datagrid('getRows').length;
													for ( var i = 0; i < rows; i++) {
														$(this).datagrid(
																'endEdit', i);
													}
													var fieldOp = $(this)
															.datagrid('options');
													var col = fieldOp.columns[0];
													for ( var i = 0; i < col.length; i++) {
														col[i].editor = "";
													}
												}
											}).datagrid('loadData', users)
									.datagrid('acceptChanges');
						}
					});
	//新增后处理
	//if(isFunction(jsonParam.afHandler)) jsonParam.afHandler(jsonParam);
	$('#addPoint').dialog('open');
}

//分摊比例页面
function showCent(jsonParam) {
	$('#addCent').removeAttr("disabled", "disabled");
	$('#addCent input').removeAttr("readonly");
	$("#addCent").dialog( {
		title : jsonParam.title,
		buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			handler : function() {
				//确定按钮点击后的具体处理函数
			/*if (isFunction(jsonParam.insertHandler))
			{	
				jsonParam.insertHandler(jsonParam);
			}else{
				_insertHandler(jsonParam.url);
			}*/

		}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				//取消前处理
			if (typeof jsonParam.clearHandler === "function")
				jsonParam.clearHandler();
			else
				$('#addCent').dialog('close');
		}
		} ],
		onOpen : function() {
			$("#centdg").datagrid( {
				striped : true,
				iconCls : 'icon-edit',
				width : 570,
				height : 220,
				singleSelect : true,
				frozenColumns : [ [ {
					field : 'kind',
					title : '分摊类型',
					width : 285
				} ] ],
				columns : [ [ {
					field : 'cent',
					title : '分摊比例',
					width : 285
				} ] ],
				onDblClickCell : function(index, field, value) {
					var fieldOp = $(this).datagrid('options');
					var col = fieldOp.columns[0];
					for ( var i = 0; i < col.length; i++) {
						if (col[i].field == field) {
							col[i].editor = "numberbox";
						}
					}
					$(this).datagrid('beginEdit', index);
					var ed = $(this).datagrid('getEditor', {
						index : index,
						field : field
					});
					$(ed.target).focus();
				},
				onClickCell : function(index, field, value) {
					var rows = $(this).datagrid('getRows').length;
					for ( var i = 0; i < rows; i++) {
						$(this).datagrid('endEdit', i);
					}
					var fieldOp = $(this).datagrid('options');
					var col = fieldOp.columns[0];
					for ( var i = 0; i < col.length; i++) {
						col[i].editor = "";
					}
				}
			});
		}
	});
}

function showProcess(jsonParam) {
	var demoName = $("#processTbl").find("tr").eq(0).html();
	$("#processTbl").empty();
	$("#processTbl").append("<tr>" + demoName + "</tr>");
	$('#addProcess').removeAttr("disabled", "disabled");
	$('#addProcess input').removeAttr("readonly");

	$('#addProcess').dialog(
			{
				title : jsonParam.title,
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						var td = $(".tempNodeOperations");
						for ( var i = 0; i < td.length; i++) {
							var checkbos = $(td[i]).find(":checked");
							var hid = $(td[i]).find("input:hidden").val();
							for ( var j = 0; j < checkbos.length; j++) {
								var valElement = checkbos[j].value;
								hid += valElement;
							}
							$(td[i]).find("input:hidden").val(hid);
						}
						$.ajax( {
							url : insertUrl,
							data : formToString($("#addForm").get(0)),
							success : function() {
								$('#addProcess').dialog('close');
								alert("保存成功");

							}
						});

					}
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						//取消前处理
					if (typeof jsonParam.clearHandler === "function")
						jsonParam.clearHandler();
					else
						$('#addProcess').dialog('close');
				}
				} ],
				modal : true,
				onOpen : function() {
					$("#branchData").empty();
					$("#deptData").empty();
					$("#groupData").empty();
					$("#userData").empty();
					$.ajax( {
						url : getStySearchInfosUrl,
						datatype : 'json',
						success : function(result) {
							var result = convertJson(result);
							var branchData = result.branchData;
							var deptData = result.deptData;
							var groupData = result.groupData;
							var userData = result.userData;
							for ( var i = 0; i < branchData.length; i++) {

								var op = "<option value='"
										+ branchData[i].branchCode + "'>"
										+ branchData[i].name + "</option>";
								$("#branchData").append(op);
							}
							for ( var j = 0; j < deptData.length; j++) {
								var op = "<option value='"
										+ deptData[j].deptCode + "'>"
										+ deptData[j].deptName + "</option>";
								$("#deptData").append(op);
							}
							for ( var k = 0; k < groupData.length; k++) {
								var op = "<option value='" + groupData[k].grpId
										+ "'>" + groupData[k].grpCname
										+ "</option>";
								$("#groupData").append(op);
							}
							for ( var l = 0; l < userData.length; l++) {
								var op = "<option value='" + userData[l].workId
										+ "'>" + userData[l].userName
										+ "</option>";
								$("#userData").append(op);
							}
						}
					});

				}
			});
	//新增后处理
	//if(isFunction(jsonParam.afHandler)) jsonParam.afHandler(jsonParam);
	//$('#addProcess').dialog('open');
}

function processAddPerson() {
	var selPers = zTreeObj.getSelectedNodes();
	var processTbl = $("#processTbl");
	var oldPers = $("#processTbl").find(".oldPer");
	var oldflag = false;
	for ( var i = 0; i < selPers.length; i++) {
		//若操作人员不能重复添加，去掉下面for循环注释即可
		/*for ( var j = 0; j < oldPers.length; j++) {
			if(selPers[i].id==oldPers[j].value){
				oldflag=true;
			}
		}*/
		if (!oldflag) {
			var tr = "<tr onclick='lightFunc(this)'>"
					+ "<td style='height: 20px;font-size: 14px;line-height: -5px;text-align: center;'>"
					+ selPers[i].name
					+ "</td>"
					+ "<td style='height: 20px;font-size: 14px;line-height: -5px;text-align: center;'>"
					+ "<input name='tempNodeNos' type='text' style='width:30px;text-align: center;'></input>"
					+ "<input name='tempIds' class='oldPer' value='"
					+ selPers[i].id
					+ "' type='hidden'></input>"
					+ "<input name='tempBranchIds' class='oldPer' value='"
					+ selPers[i].branchId
					+ "' type='hidden'></input>"
					+ "<input name='tempDeptIds' class='oldPer' value='"
					+ selPers[i].deptId
					+ "' type='hidden'></input>"
					+ "</td>"
					+ "<td style='height: 20px;font-size: 14px;line-height: -5px;text-align: center;'><select type='text' id='nodeType' name='tempNodeTypes'>"
					+ "<option value='1'>审批</option>"
					+ "</select>"
					+ "</td>"
					+ "<td  class='tempNodeOperations' style='height: 20px;font-size: 14px;line-height: -5px;text-align: center;'>"
					+ "<input type='checkbox' value='1'/>同意"
					+ "<input type='checkbox' value='2'/>终止"
					+ "<input type='checkbox' value='3'/>退回"
					+ "<input type='checkbox' value='4'/>发布"
					+ "<input type='hidden' name='tempNodeOperations' value=''/>"
					+ "</td>" + "</tr>";
			processTbl.append(tr);
		}
	}
}

function processDelPerson() {
	$("#processTbl").find(".trSelected").remove();
}

function lightFunc(self) {
	$("#processTbl").find("tr").css("background", "");
	$("#processTbl").find("tr").attr("class", "");
	$(self).css("background", "#FFCC66");
	$(self).attr("class", "trSelected");
}

//查询
function submitRole() {

	$.ajax( {
		url : getLeftInfosUrl,
		data : formToString($("#addForm").get(0)),
		datatype : 'json',
		success : function(result) {
			//树插件配置属性定义 ,详细API参照http://www.ztree.me/v3/api.php
		var setting = {
			edit : {
				/*设置 zTree 是否开启异步加载模式
				 * true 表示 开启 异步加载模式
				 * false 表示 关闭 异步加载模式
				 * */
				enable : true,
				/*设置是否显示删除按钮。[setting.edit.enable = true 时生效]
				 * true / false 分别表示 显示 / 隐藏 删除按钮
				 */
				showRemoveBtn : false,
				/*设置是否显示编辑名称按钮。[setting.edit.enable = true 时生效]
				 * true / false 分别表示 显示 / 隐藏 编辑名称按钮
				 * */
				showRenameBtn : false
			},
			data : {
				simpleData : {
					/*节点数据中保存唯一标识的属性名称。[setting.data.simpleData.enable = true 时生效]*/
					idKey : "id",
					/*节点数据中保存其父节点唯一标识的属性名称。[setting.data.simpleData.enable = true 时生效]*/
					pIdKey : "pId",
					/*确定 zTree 初始化时的节点数据、异步加载时的节点数据、或 addNodes 方法中输入的 newNodes 数据是否采用简单数据模式 (Array)
					 * 不需要用户再把数据库中取出的 List 强行转换为复杂的 JSON 嵌套格式
					 * 默认值：false
					 * true / false 分别表示 使用 / 不使用 简单数据模式
					 * */
					enable : true
				}
			},
			callback : {
			/*用于捕获节点被点击的事件回调函数
			 * 如果设置了 setting.callback.beforeClick 方法，且返回 false，将无法触发 onClick 事件回调函数。
			 * */
			// onClick: zTreeOnClick
			}
		};

		var result = convertJson(result);
		var zTreeNodes = [];
		for ( var j = 0; j < result.leftGroupData.length; j++) {
			var obj = {
				id : result.leftGroupData[j].grpId,
				pId : 0,
				name : result.leftGroupData[j].grpCname,
				branchId : "",
				deptId : ""
			};
			zTreeNodes.push(obj);
		}
		for ( var i = 0; i < result.leftUserData.length; i++) {
			var obj = {
				id : result.leftUserData[i].workId,
				pId : result.leftUserData[i].groupId,
				name : result.leftUserData[i].userName,
				branchId : result.leftUserData[i].userBankCode,
				deptId : result.leftUserData[i].userDept
			};
			zTreeNodes.push(obj);
		}
		zTreeObj = $.fn.zTree.init($("#userTree"), setting, zTreeNodes);
	}

	});
}

//form数据封装   
function formToString(formObj) {
	var allStr = "";
	if (formObj) {
		var elementsObj = formObj.elements;
		var obj;
		if (elementsObj) {
			for ( var i = 0; i < elementsObj.length; i += 1) {
				obj = elementsObj[i];
				if (obj.name != undefined && obj.name != "") {
					allStr += "&" + obj.name + "="
							+ encodeURIComponent(obj.value);
				}
			}
		} else {
			alert("没有elements对象!");
			return;
		}
	} else {
		alert("form不存在!");
		return;
	}
	return allStr;
}
function showProcessFlow(jsonParam) {
	var demoName = $("#processTbl").find("tr").eq(0).html();
	$("#processTbl").empty();
	$("#processTbl").append("<tr>" + demoName + "</tr>");
	$('#addProcessFlow').removeAttr("disabled", "disabled");
	$('#addProcessFLow input').removeAttr("readonly");

	$('#addProcessFlow').dialog(
			{
				title : jsonParam.title,
				modal : true,
				onOpen : function() {
					$("#branchData").empty();
					$("#deptData").empty();
					$("#groupData").empty();
					$("#userData").empty();
					$.ajax( {
						url : getStySearchInfosUrl,
						datatype : 'json',
						success : function(result) {
							var result = convertJson(result);
							var branchData = result.branchData;
							var deptData = result.deptData;
							var groupData = result.groupData;
							var userData = result.userData;
							for ( var i = 0; i < branchData.length; i++) {

								var op = "<option value='"
										+ branchData[i].branchCode + "'>"
										+ branchData[i].name + "</option>";
								$("#branchData").append(op);
							}
							for ( var j = 0; j < deptData.length; j++) {
								var op = "<option value='"
										+ deptData[j].deptCode + "'>"
										+ deptData[j].deptName + "</option>";
								$("#deptData").append(op);
							}
							for ( var k = 0; k < groupData.length; k++) {
								var op = "<option value='" + groupData[k].grpId
										+ "'>" + groupData[k].grpCname
										+ "</option>";
								$("#groupData").append(op);
							}
							for ( var l = 0; l < userData.length; l++) {
								var op = "<option value='" + userData[l].workId
										+ "'>" + userData[l].userName
										+ "</option>";
								$("#userData").append(op);
							}
						}
					});

				}
			});
}