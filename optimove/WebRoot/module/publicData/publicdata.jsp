<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<html>
	<head>
		<title>管理</title>
		<jsp:include page="/module/common/comm.jsp"></jsp:include>
		<script type="text/javascript" charset="GBK">
var searchUrl = "<%=request.getContextPath()%>/publicdatas/searchPublicdata.do";
var updateUrl = "<%=request.getContextPath()%>/publicdatas/update.do";
var insertUrl = "<%=request.getContextPath()%>/publicdatas/insert.do";
var deleteUrl = "<%=request.getContextPath()%>/publicdatas/delete.do";
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
		width: 800,  
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
			field : 'publicdataid',
			title : '开放数据编号',
			width : 120,
			align : 'center'
		}, {
			field : 'publicdataname',
			title : '开放数据',
			width : 120,
			align : 'center'
		}, {
			field : 'publicdatasource',
			title : '开放数据来源',
			width : 150,
			align : 'center'
		}, {
			field : 'publicdatatype',
			title : '开放数据类型',
			width : 150,
			align : 'center'
		}, {
			field : 'remarks',
			title : '备注',
			width : 140,
			align : 'center'
		}, {
			field : 'publicdatafile',
			title : '开放数据',
			width : 120,
			align : 'center'
		}, {
			field : 'operate',
			title : '操作',
			width : 213,
			align : 'center',
			formatter : function(operate) {
				return "<button class='commbtn' onclick='showchart()'>预览</button>";
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

function showchart(){
	initDlg('#chartwindow').dialog( {
			title : "查看预览",
			buttons : [
				{
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#chartwindow').dialog('close');
				}
			} ]
		});
	$('#chartwindow').dialog('open');
}

</script>
	</head>

	<body class="easyui-layout">
		<div region="center" class="wrapper1 wrapper-content1 gray-bg panel-fit">
			<div id='dataList'>
				<div id="tb" style="padding: 5px; height: auto;background-color: white;">
					<div style="margin-bottom: 5px;text-align: right;">
						<a href="#" class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="showAddwindow({title:'新增'})">新增</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
							plain="true"
							onclick="showUpdate({title:'修改',readonlyFields:['publicdataid']});">修改</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="delRowData({id:'publicdataid'});">删除</a>|<a class="collapse-link" id="togglebtn">
                                        <i class="fa fa-chevron-up" id="up"></i>
                         </a>
					</div>
					<div id="searchdiv">
						<form id='searchForm' action="" method="post">
							开放数据名称:
							<input type="text" id="publicdataname" name="publicdataname" class="form-text"/>
							开放数据来源:
							<input type="text" id="publicdatasource" name="publicdatasource" class="form-text"/>
							开放数据类型:
							<input type="text" id="publicdatatype" name="publicdatatype" class="form-text"/>
							<input type="button" onclick="loadList(1);" value="查询" class="commbtn"/>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div style="visibility: hidden">
			<div id="chartwindow" title="查看预览"
				style="width: 600px; height: 450px; padding: 1px">
				<%@ include file="publicdataMap.html"%>
			</div>
		</div>
		<div style="visibility: hidden">
			<div id="addwindow" title="添加"
				style="width: 600px; height: 350px; padding: 10px">
				<form id='addForm' action="" method="post">
					<table>
						<tr>
							<td style="font-size: 13px;">
								开放数据编号:
							</td>
							<td>
								<input type="text" id="publicdataid" name="publicdataid"
									style="width: 180px" class="form-text input-margin"/>
							</td>
							<td style="font-size: 13px;">
								开放数据名称:
							</td>
							<td>
								<input type="text" id="publicdataname" name="publicdataname"
									style="width: 180px" class="form-text input-margin"/>
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px;">
								开放数据来源:
							</td>
							<td>
								<input type="text" id="publicdatasource" name="publicdatasource"
									style="width: 180px" class="form-text input-margin"/>
							</td>
							<td style="font-size: 13px;">
								开放数据类型:
							</td>
							<td>
								<input type="text" id="publicdatatype" name="publicdatatype"
									style="width: 180px" class="form-text input-margin"/>
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px;">
								备注:
							</td>
							<td>
								<input type="text" id="remarks" name="remarks"
									style="width: 180px" class="form-text input-margin"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>
</html>
