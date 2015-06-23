<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<html>
<head>
<title>管理</title>
<jsp:include page="/module/common/comm.jsp"></jsp:include>
<script type="text/javascript" charset="GBK">
var searchUrl = "<%=request.getContextPath()%>/apiserviceproviders/searchApiserviceprovider.do";
var updateUrl = "<%=request.getContextPath()%>/apiserviceproviders/update.do";
var insertUrl = "<%=request.getContextPath()%>/apiserviceproviders/insert.do";
var deleteUrl = "<%=request.getContextPath()%>/apiserviceproviders/delete.do";
	ajaxConstants("tblProviderMag|providerId|providerName|orderBy=providerId");
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
		$('#dataList').datagrid({
			//title : '数据列表',
			//iconCls : 'icon-edit',//图标  
			width: 600,  
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
				field : 'apicode',
				title : 'api编号',
				width : 120,
				align : 'center'
			}, {
				field : 'apiname',
				title : 'api名称',
				width : 150,
				align : 'center'
			}, {
				field : 'apitype',
				title : '类型',
				width : 130,
				align : 'center'
			}, {
				field : 'apiversion',
				title : '版本号',
				width : 120,
				align : 'center'
			}, {
				field : 'operatetime',
				title : '操作时间',
				width : 150,
				align : 'center'
			}, {
				field : 'status',
				title : '分配状态',
				width : 150,
				align : 'center',
				formatter : function(status) {
					if (status == 0) {
						return "<button class='btn btn-primary btn-sm'><i class='fa fa-check'>&nbsp;启用</i></button>";
					} else {
						return "<button class='btn btn-warning btn-sm'><i class='fa fa-warning'>&nbsp;停用</i></button>";
					}
				}
			}, {
				field : 'providerid',
				title : '服务商',
				width : 200,
				align : 'center',
				formatter : function(value, row, index) {
					return getConstantDisplay('tblProviderMag', value);
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

<body class="easyui-layout">
	<div class="wrapper1 wrapper-content1 panel-fit gray-bg" region="center">
		<div id='dataList'>
			<div id="tb"
				style="padding: 5px; height: auto;background-color: white;">
				<div style="margin-bottom: 5px;text-align: right;">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add"
						plain="true" onclick="showAddwindow({title:'新增'})">&nbsp;新增&nbsp;</a> | <a
						href="#" class="easyui-linkbutton" iconCls="icon-edit"
						plain="true"
						onclick="showUpdate({title:'修改',readonlyFields:['keyid']});">&nbsp;修改&nbsp;</a>|
					<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
						plain="true" onclick="delRowData({id:'keyid'});">&nbsp;删除&nbsp;</a>|<a class="collapse-link" id="togglebtn">
                                        <i class="fa fa-chevron-up" id="up"></i>
                         </a>
				</div>
				<div id="searchdiv">
					<form id='searchForm' action="" method="post">
						名称: <input type="text" id="apiname" name="apiname" class="form-text" style="width:180px"/>
						版本号: <input type="text" id="apiversion" name="apiversion" class="form-text" style="width: 180px"/> <input
							type="button" class="commbtn"
							onclick="loadList(1);" value="查询" />
					</form>
				</div>
			</div>
		</div>
	</div>
	<div style="visibility: hidden;">
		<div id="addwindow" title="添加"
			style="width: 600px; height: 350px; padding: 20px">
			<form id='addForm' action="" method="post">
				<table>
					<tr>
						<td style="text-align: right;font-size: 13px">编号:</td>
						<td><input type="text" id="apicode" name="apicode"
							style="width: 180px" class="form-text input-margin"/></td>
						<td style="text-align: right;font-size: 13px">名称:</td>
						<td><input type="text" id="apiname" name="apiname"
							style="width: 180px" class="form-text input-margin"/></td>
					</tr>
					<tr>
						<td style="text-align: right;font-size: 13px">类型:</td>
						<td><input type="text" id="apitype" name="apitype"
							style="width: 180px" class="form-text input-margin"/></td>
						<td style="text-align: right;font-size: 13px">版本号:</td>
						<td><input type="text" id="apiversion" name="apiversion"
							style="width: 180px" class="form-text input-margin"/></td>
					</tr>
					<tr>
						<td style="text-align: right;font-size: 13px">服务商:</td>
						<td><select id="providerid" name="providerid"
							style="width: 180px" constantId="tblProviderMag" class="easyui-combobox"></select></td>
						<td style="text-align: right;font-size: 13px">状态:</td>
						<td><input type="text" id="status" name="status"
							style="width: 180px" class="form-text input-margin"/></td>
					</tr>
					<tr>
						<td style="text-align: right;font-size: 13px">操作时间:</td>
						<td>
							<input type="text" value=""  id="operatetime" name="operatetime" class="easyui-datebox" style="width: 180px">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>
