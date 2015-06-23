<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<html>
	<head>
		<title>管理</title>
		<jsp:include page="/module/common/comm.jsp"></jsp:include>
		<script type="text/javascript" charset="GBK">
var searchUrl = "<%=request.getContextPath()%>/applicationmags/searchApplicationmag.do";
var updateUrl = "<%=request.getContextPath()%>/applicationmags/update.do";
var insertUrl = "<%=request.getContextPath()%>/applicationmags/insert.do";
var deleteUrl = "<%=request.getContextPath()%>/applicationmags/delete.do";
$(function() {
	$("#togglebtn").click(function(){
		  $("#searchdiv").slideToggle();
		  var cl = $("#up").attr("class");
		  if(cl=="fa fa-chevron-down"){
			  $("#up").removeClass().addClass("fa fa-chevron-up");
		  }else{
		  	$("#up").removeClass().addClass("fa fa-chevron-down"); 
		  }
		  });
	$('#dataList').datagrid( {
		//title : '列表',
		//iconCls : 'icon-edit',//图标  
		width: '600',  
		height : 'auto',
		nowrap : false,
		striped : true,
		border : false,
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
			field : 'applicationid',
			title : '应用编号',
			width : 200,
			align : 'center'
		}, {
			field : 'applicationname',
			title : '应用名称',
			width : 300,
			align : 'center'
		}, {
			field : 'applicationdes',
			title : '应用描述',
			width : 302,
			align : 'center'
		}, {
			field : 'status',
			title : '认证状态',
			width : 215,
			align : 'center',
			formatter : function(status) {
				if (status == 0) {
					return "<button class='btn btn-success btn-sm'><i class='fa fa-check'>已认证</i></button>";
				} else {
					return "<button class='btn btn-warning btn-sm'><i class='fa fa-warning'>未认证</i></button>";
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
});
</script>
	</head>

	<body class="easyui-layout">
		<div class="wrapper1 wrapper-content1 gray-bg panel-fit" region="center">
			<div id='dataList'>
				<div id="tb" style="padding: 5px;background-color: white;">
					<div style="margin-bottom: 5px;text-align: right;">
						<a href="#" class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="showAddwindow({title:'新增'})">&nbsp;新增&nbsp;</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
							plain="true"
							onclick="showUpdate({title:'修改',readonlyFields:['applicationid']});">&nbsp;修改&nbsp;</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="delRowData({id:'applicationid'});">&nbsp;删除&nbsp;</a>|<a class="collapse-link" id="togglebtn">
                                        <i class="fa fa-chevron-up" id="up"></i>
                         </a>
					</div>
					<div id="searchdiv">
						<form id='searchForm' action="" method="post">
							应用名称:
							<input type="text" id="applicationname" name="applicationname" class="form-text" style="width:180px"/>
							认证状态:
							<input type="text" id="status" name="status" class="form-text" style="width:180px"/>
							<input type="button" onclick="loadList(1);" value="查询" class="commbtn"/>
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
							<td style="font-size: 13px">
								应用编号:
							</td>
							<td>
								<input type="text" id="applicationid" name="applicationid"
									style="width: 180px" class="form-text input-margin"/>
							</td>
							<td style="font-size: 13px">
								应用名称:
							</td>
							<td>
								<input type="text" id="applicationname" name="applicationname"
									style="width: 180px" class="form-text input-margin"/>
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								应用描述:
							</td>
							<td>
								<input type="text" id="applicationdes" name="applicationdes"
									style="width: 180px" class="form-text input-margin"/>
							</td>
							<td style="font-size: 13px">
								认证状态:
							</td>
							<td>
								<input type="text" id="status" name="status"
									style="width: 180px" class="form-text input-margin"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>
</html>
