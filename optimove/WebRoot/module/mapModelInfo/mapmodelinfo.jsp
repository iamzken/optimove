<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>模型管理</title>
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
			//title:'列表',  
			//iconCls:'icon-edit',//图标  
			width: 'auto',  
			height : 'auto',
			nowrap : false,
			striped : true,
			border : false,
			collapsible : false,//是否可折叠的  
			fit : false,//自动大小  
			fitColumns : true,
			remoteSort : false,
			singleSelect : true,//是否单选  
			pagination : true,//分页控件  
			rownumbers : true,//行号  
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
				title : '模型代码',
				width : 100,
				align : 'center'
			}, {
				field : 'modelname',
				title : '模型名称',
				width : 100,
				align : 'center'
			}, {
				field : 'modeltype',
				title : '模型类型',
				width : 100,
				align : 'center'
			}, {
				field : 'modeldatatable',
				title : '模型数据表表名',
				width : 150,
				align : 'center'
			}, {
				field : 'modelcreatetime',
				title : '模型创建时间',
				width : 150,
				align : 'center'
			}, {
				field : 'modelupdatetime',
				title : '模型修改时间',
				width : 250,
				align : 'center'
			} ] ],
			onLoadSuccess : function(data) {
				data = convertJson(data);
				if (data.result != 'ok') {
					showBox("提示信息", data.errorMsg, 'warning');
				}
			},
			onSelect : function(index, data) {
				$('#subDataList').show();
				loadValueList(1);
			}

		});

		//设置分页控件  
		var p = $('#dataList').datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10  
			pageList : [ 10, 20, 30 ],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
	});

	//子表分页
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
			showBox('温馨提示', '请选要查询的类型!', 'warning');
			return false;
		}
		
		$.post(subSearchUrl, {
			"tablename" : selectType.modeldatatable,
			"page" : pageNumber
		}, function(response) {
			response = convertJson(response);
			if (response.result != 'ok') {
				showBox("提示信息", response.result, 'warning');
				return;
			}

			//模型属性
			$('#subDataList').datagrid({
				//title : '列表',
				//iconCls : 'icon-edit',//图标  
				//width: 700,  
				height : 'auto',
				nowrap : false,
				striped : true,
				border : false,
				collapsible : false,//是否可折叠的  
				fit : false,//自动大小  
				fitColumns : true,
				remoteSort : false,
				singleSelect : true,//是否单选  
				pagination : true,//分页控件  
				rownumbers : true,//行号  
				frozenColumns : [ [ {
					field : 'ck',
					checkbox : true
				} ] ],
				idField : 'id',
				toolbar : '#subTb',
				columns : [ [ {
					field : 'tablename',
					title : '模型表表名',
					width : 100,
					align : 'center',
					formatter : function(tablename) {
						if (tablename == 'default') {
							return "默认字段";
						} else {
							return tablename;
						}
					}
				}, {
					field : 'modelattribute',
					title : '模型属性',
					width : 100,
					align : 'center'
				}, {
					field : 'modelattributename',
					title : '模型属性名称',
					width : 100,
					align : 'center'
				}, {
					field : 'modelattributetype',
					title : '模型属性类别',
					width : 100,
					align : 'center'
				}, {
					field : 'modelattributeflag',
					title : '模型属性标识',
					width : 200,
					align : 'center',
					formatter : function(modelattributeflag) {
						if (modelattributeflag == '0') {
							return "默认字段";
						} else {
							return "自定义字段";
						}
					}
				} ] ],
				onLoadSuccess : function(data) {
					data = convertJson(data);
					if (data.result != 'ok') {
						showBox("提示信息", data.errorMsg, 'warning');
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
			showBox('温馨提示', '请选要查询的类型!', 'warning');
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
						showBox('温馨提示', '系统默认模型属性不能更新，请重新选择!', 'warning');
					}
					if (flag == 1) {
						showBox('温馨提示', '系统默认模型属性不能删除，请重新选择!', 'warning');
					}
					return false;
				}
			}
		}
		if (flag == 0) {
			subShowUpdate({ title : '修改', readonlyFields : [ 'id', 'modelattribute', 'modelattributetype' ] });
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
					<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddwindow({title:'新增'});">新增</a>|
					<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'修改',readonlyFields:['modelid']});">修改</a>|
					<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'modelid'});">删除</a>
				</div>
				<div>
					<form  id='searchForm' action="" method="post">
						模型代码:
						<input type="text" id="modelcode" name="modelcode" class="form-text"/>
						模型名称:
						<input type="text" id="modelname" name="modelname" class="form-text"/>
						模型类型:
						<input type="text" id="modeltype" name="modeltype" class="form-text"/>
						<input type="button" class="commbtn" onclick="loadList(1);" value="查询"/>
					</form>
				</div>
			</div>
		</div>
		<div id="subDataList" style="display: none;">
			<hr>
			<div id="subTb" style="padding:5px;height:auto">
				<div style="margin-bottom:5px">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="subShowAddwindow({title:'新增'});setAttrVal();">新增</a>|
					<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="checkAttr(0);">修改</a>|
					<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="checkAttr(1);">删除</a>
				</div>
			</div>
		</div>
	</div>
	
	<div style="visibility:hidden" >
		<div id="addwindow" title="添加" style="width:600px;height:350px;padding:10px">
			<form id='addForm' action="" method="post">
				<table>
					<tr style="display: none;">
						<td>模型ID:</td>
						<td><input type="text" id="modelid" name="modelid" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr>
						<td>模型代码:</td>
						<td><input type="text" id="modelcode" name="modelcode" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr>
						<td>模型名称:</td>
						<td><input type="text" id="modelname" name="modelname" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr>
						<td>模型类型:</td>
						<td><input type="text" id="modeltype" name="modeltype" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr style="display: none;">
						<td>模型数据表表名:</td>
						<td><input type="text" id="modeldatatable" name="modeldatatable" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr style="display: none;">
						<td>模型创建时间:</td>
						<td><input type="text" id="modelcreatetime" name="modelcreatetime" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr style="display: none;">
						<td>模型修改时间:</td>
						<td><input type="text" id="modelupdatetime" name="modelupdatetime" class="form-text input-margin" style="width:180px"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<div style="visibility:hidden" >
		<div id="subAddwindow" title="添加" style="width:600px;height:350px;padding:10px">
			<form id='subAddForm' action="" method="post">
				<table>
					<tr style="display: none;">
						<td>ID:</td>
						<td><input type="text" id="id" name="id" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr style="display: none;">
						<td>模型表表名:</td>
						<td><input type="text" id="tablename" name="tablename" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr>
						<td>模型属性:</td>
						<td><input type="text" id="modelattribute" name="modelattribute" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr>
						<td>模型属性名称:</td>
						<td><input type="text" id="modelattributename" name="modelattributename" class="form-text input-margin" style="width:180px"/></td>
					</tr>
					<tr>
						<td>模型属性类别:</td>
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
						<td>是否能被查询:</td>
						<td>
							<select id="canSearch" name="canSearch" class="form-text input-margin" style="width:180px" onchange="setAttrType()">
								<option value="1" selected="selected">
									能被查询
								</option>
								<option value="0">
									不能被查询
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>能否被显示:</td>
						<td>
							<select id="canDisplay" name="canDisplay" class="form-text input-margin" style="width:180px" onchange="setAttrType()">
								<option value="1" selected="selected">
									能被显示
								</option>
								<option value="0">
									不能被显示
								</option>
							</select>
						</td>
					</tr>
					<tr style="display: none;">
						<td>模型属性标识:</td>
						<td><input type="text" id="modelattributeflag" name="modelattributeflag" class="form-text input-margin" style="width:180px"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
