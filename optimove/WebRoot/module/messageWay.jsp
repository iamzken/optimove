<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<html>
	<head>
		<title>消息管理</title>
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
			field : 'subjectId',
			title : '方案编号',
			width : 150,
			align : 'center'
		}, {
			field : 'subjectName',
			title : '方案名称',
			width : 200,
			align : 'center'
		}, {
			field : 'madePerson',
			title : '编制人',
			width : 180,
			align : 'center'
		}, {
			field : 'status',
			title : '方案状态',
			width : 200,
			align : 'center'
		}, {
			field : 'powerStatus',
			title : '是否设置权限',
			width : 200,
			align : 'center',
			formatter : function(powerStatus) {
				if (powerStatus == null) {
					return "否";
				} else {
					return "是";
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
							plain="true" onclick="showAddMesswway({title:'消息设置'})">消息设置</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="delRowData({id:'subjectId'});">删除</a>|
					</div>
					<div>
						<form id='searchForm' action="" method="post">
							方案编号:
							<input type="text" id="subjectId" name="subjectId">
							方案名称:
							<input type="text" id="subjectName" name="subjectName">
							权限设置:
							<input type="text" id="powerStatus" name="powerStatus">
							<input type="button" onclick="loadList(1);" value="查询">
						</form>
					</div>
				</div>
			</div>
		</div>

		<div style="visibility: hidden">
			<div id="addmessageway" title="消息信息"
				style="width: 580px; height: 170px; padding: 10px;overflow: hidden;">
				<form id='addmesswayForm' action="" method="post">
					<table>
						<tr>
							<td>
								操&nbsp;作&nbsp;人&nbsp;:
							</td>
							<td>
								<input type="text" id="subjectId" name="subjectId"
									style="width: 180px">
							</td>
							<td>
								操作日期:
							</td>
							<td>
								<input type="text" id="subjectName" class="easyui-datebox" name="subjectName"
									style="width: 180px">
							</td>
						</tr>
						<tr>
							<td>
								消息类型:
							</td>
							<td>
								<select name="subjectId" style="width: 180px">
									<option>请选择...</option>
									<option>审批流程</option>
									<option>预算监测</option>
									<option>公告消息</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								通知方式:
							</td>
							<td colspan="3">
								<input type="checkbox"/>代办公告<input type="checkbox"/>E-mail<input type="checkbox"/>短信<input type="checkbox"/>微信<input type="checkbox"/>办公平台
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>
</html>
