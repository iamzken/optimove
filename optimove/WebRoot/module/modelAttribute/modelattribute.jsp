<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>管理</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
	<script type="text/javascript" charset="GBK">
	   var searchUrl = "<%=request.getContextPath()%>/modelattributes/searchModelattribute.do";
	   var updateUrl = "<%=request.getContextPath()%>/modelattributes/update.do";
	   var insertUrl = "<%=request.getContextPath()%>/modelattributes/insert.do";
	   var deleteUrl = "<%=request.getContextPath()%>/modelattributes/delete.do";
		$(function() {
			$('#dataList').datagrid({
				title : '列表',
				iconCls : 'icon-edit',//图标  
				//width: 700,  
				height : 'auto',
				nowrap : false,
				striped : true,
				border : true,
				collapsible : false,//是否可折叠的  
				fit : true,//自动大小  
				remoteSort : false,
				singleSelect : false,//是否单选  
				pagination : true,//分页控件  
				rownumbers : true,//行号  
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
					title : '模型表表名',
					width : 100,
					align : 'center'
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
			$(p).pagination({
				pageSize : 10,//每页显示的记录条数，默认为10  
				pageList : [ 10, 20, 30 ],//可以设置每页记录条数的列表  
				beforePageText : '第',//页数文本框前显示的汉字  
				afterPageText : '页    共 {pages} 页',
				displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
			})
		});
	</script>
</head>
  
  <body class="easyui-layout" >
	<div  region="center" >
		<div id='dataList'>
			<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddwindow({title:'新增'})">&nbsp;新增&nbsp;</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'修改',readonlyFields:['id']});">&nbsp;修改&nbsp;</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'id'});">&nbsp;删除&nbsp;</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
				模型表表名:
				<input type="text" id="tablename" name="tablename"/>
				模型属性名称:
				<input type="text" id="modelattributename" name="modelattributename"/>
				模型属性类别:
				<input type="text" id="modelattributetype" name="modelattributetype"/>
				<input type="button" onclick="loadList(1);" value="查询"/>
			</form>
		</div>
	</div>
		</div>
	</div>
	
	<div style="visibility:hidden" >
		<div id="addwindow"  title="添加" style="width:600px;height:350px;padding:10px">
			<form id='addForm' action="" method="post">
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
						<td><input type="text" id="modelattributetype" name="modelattributetype" class="form-text input-margin" style="width:180px"/></td>
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
