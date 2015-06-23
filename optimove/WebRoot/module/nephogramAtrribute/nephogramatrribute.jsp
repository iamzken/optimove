<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<html>
	<head>
		<title>管理</title>
		<jsp:include page="/module/common/comm.jsp"></jsp:include>
		<script type="text/javascript" charset="GBK">
var searchUrl = "<%=request.getContextPath()%>/nephogramatrributes/searchNephogramatrribute.do";
var updateUrl = "<%=request.getContextPath()%>/nephogramatrributes/update.do";
var insertUrl = "<%=request.getContextPath()%>/nephogramatrributes/insert.do";
var deleteUrl = "<%=request.getContextPath()%>/nephogramatrributes/delete.do";
ajaxConstants("tblNephogramModelMag|nephogramModelId|nephogramModelName|orderBy=nephogramModelId");
$(function() {
	$('#dataList').datagrid( {
		title : '列表',
		iconCls : 'icon-edit',//图标  
		//width: 700,  
		height : 'auto',
		nowrap : false,
		striped : true,
		border : true,
		collapsible : false,//是否可折叠的  
		fit : true,//自动大小  
		url : '#',
		remoteSort : false,
		singleSelect : false,//是否单选  
		pagination : true,//分页控件  
		rownumbers : true,//行号  
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		} ] ],
		url : searchUrl,
		toolbar : '#tb',
		columns : [ [ {
			field : 'nephogramattrid',
			title : '云图属性编号',
			width : 100,
			align : 'center'
		}, {
			field : 'nephogrammodelid',
			title : '云图模型名称',
			width : 100,
			align : 'center',
			formatter : function(value, row, index) {
				return getConstantDisplay('tblNephogramModelMag', value);
			}
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
		onLoadSuccess : function(data) {
			data = convertJson(data);
			if (data.result != 'ok') {
				showBox("提示信息", data.errorMsg, 'warning');
			}
		}

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
});
</script>
	</head>

	<body class="easyui-layout">
		<div region="center">
			<div id='dataList'>
				<div id="tb" style="padding: 5px; height: auto">
					<div style="margin-bottom: 5px">
						<a href="#" class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="showAddwindow({title:'新增'})">新增</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
							plain="true"
							onclick="showUpdate({title:'修改',readonlyFields:['nephogramattrid']});">修改</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="delRowData({id:'nephogramattrid'});">删除</a>
					</div>
					<div>
						<form id='searchForm' action="" method="post">
							云图属性编号:
							<input type="text" id="nephogramattrid" name="nephogramattrid" />
							属性代码:
							<input type="text" id="attributecode" name="attributecode" />
							<input type="button" onclick="loadList(1);" value="查询" />
						</form>
					</div>
				</div>
			</div>
		</div>

		<div style="visibility: hidden">
			<div id="addwindow" title="添加"
				style="width: 600px; height: 350px; padding: 10px">
				<form id='addForm' action="" method="post">
					<table>
						<tr>
							<td>
								属性编号:
							</td>
							<td>
								<input type="text" id="nephogramattrid" name="nephogramattrid"
									style="width: 180px" />
							</td>
							<td>
								云图模板:
							</td>
							<td>
									<select id="nephogrammodelid" name="nephogrammodelid"
									style="width: 180px" constantId="tblNephogramModelMag"></select>
							</td>
						</tr>
						<tr>
							<td>
								属性代码:
							</td>
							<td>
								<input type="text" id="attributecode" name="attributecode"
									style="width: 180px" />
							</td>
							<td>
								属性名称:
							</td>
							<td>
								<input type="text" id="attributename" name="attributename"
									style="width: 180px" />
							</td>
						</tr>
						<tr>
							<td>
								属性类型:
							</td>
							<td>
								<select id="attrtype" name="attrtype" style="width: 180px">
									<option value="">请选择</option>
									<option value="电子文档">电子文档</option>
									<option value="地址文件">地址文件</option>
									<option value="影像系统">影像系统</option>
									<option value="接口">接口</option>
								</select>
							</td>
							<td>
								备注:
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
