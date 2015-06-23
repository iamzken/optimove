<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<html>
	<head>
		<title>公告管理</title>
		<jsp:include page="/module/common/comm.jsp"></jsp:include>
		<script type="text/javascript" charset="GBK">
var searchUrl = "<%=request.getContextPath()%>/notices/searchNotice.do";
var updateUrl = "<%=request.getContextPath()%>/notices/update.do";
var insertUrl = "<%=request.getContextPath()%>/notices/insert.do";
var deleteUrl = "<%=request.getContextPath()%>/notices/delete.do";
var getTreeDataUrl = "<%=request.getContextPath()%>/subjectpowers/getTreeData.do";
$(function() {
	$('#dataList').datagrid( {
		//title:'列表',  
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
			field : 'noticecode',
			title : '公告编号',
			width : 107,
			align : 'center'
		}, {
			field : 'noticetitle',
			title : '公告标题',
			width : 120,
			align : 'center'
		}, {
			field : 'noticecontent',
			title : '公告内容',
			width : 150,
			align : 'center'
		}, {
			field : 'operatetime',
			title : '发布时间',
			width : 150,
			align : 'center'
		}, {
			field : 'startdate',
			title : '开始时间',
			width : 150,
			align : 'center'
		},{
			field : 'enddate',
			title : '结束时间',
			width : 150,
			align : 'center'
		},{
			field : 'importantlevel',
			title : '紧急程度',
			width : 120,
			align : 'center'
		},{
			field : 'createrid',
			title : '发布人',
			width : 150,
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
							plain="true" onclick="showAddMesswindow({title:'新增'})">公告新增</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="delRowData({id:'noticecode'});">删除</a>|
					</div>
					<div>
						<form id='searchForm' action="" method="post">
							公告编号:
							<input type="text" id="noticecode" name="noticecode">
							公告标题:
							<input type="text" id="noticetitle" name="noticetitle">
							发布时间:
							<input type="text" id="operatetime" name="operatetime" class="easyui-datebox">
							<input type="button" onclick="loadList(1);" value="查询">
						</form>
					</div>
				</div>
			</div>
		</div>

		<div style="visibility: hidden">
			<div id="addmessagewindow" title="公告信息"
				style="width: 600px; height: 350px; padding: 10px;overflow-x: hidden;">
				<form id='addmessageForm' action="" method="post">
					<table>
						<tr>
							<td>
								公告编号:
							</td>
							<td>
								<input type="text" id="noticecode" name="noticecode"
									style="width: 180px">
							</td>
							<td>
								公告名称:
							</td>
							<td>
								<input type="text" id="noticetitle" name="noticetitle"
									style="width: 180px">
							</td>
						</tr>
						<tr>
							<td>
								开始日期:
							</td>
							<td>
								<input type="text" id="startdate" class="easyui-datebox" name="startdate"
									style="width: 180px">
							</td>
							<td>
								结束日期:
							</td>
							<td>
								<input type="text" id="enddate" class="easyui-datebox" name="enddate"
									style="width: 180px">
							</td>
						</tr>
						<tr>
							<td>
								重要级别:
							</td>
							<td>
								<input type="text" id="importantlevel" name="importantlevel"
									style="width: 180px">
							</td>
							<td>
								当前状态:
							</td>
							<td>
								<input type="text" id="noticestatus" name="noticestatus"
									style="width: 180px"><input name="noticebranch" id="noticebranch" type="hidden"/><input name="noticedept" id="noticedept" type="hidden"/><input name="noticegroup" id="noticegroup" type="hidden"/>
							</td>
						</tr>
						<tr>
							<td valign="top">发布内容：</td>
							<td colspan="3"><textarea rows="2" id="noticecontent" name="noticecontent" style="width:425px;"></textarea></td>
						</tr>
					</table>
					<div id="messageTab">
						<div id="branch" class="ztree" title="机构"></div>
						<div id="dept" class="ztree" title="部门"></div>
						<div id="group" class="ztree" title="角色"></div>
					</div>
				</form>
			</div>
		</div>

	</body>
</html>
