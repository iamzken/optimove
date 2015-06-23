<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<html>
<head>
<title>管理</title>
<jsp:include page="/module/common/comm.jsp"></jsp:include>
<script type="text/javascript" charset="GBK">
var searchUrl = "<%=request.getContextPath()%>/developerkeymags/searchDeveloperkeymag.do";
var updateUrl = "<%=request.getContextPath()%>/developerkeymags/update.do";
var insertUrl = "<%=request.getContextPath()%>/developerkeymags/insert.do";
var deleteUrl = "<%=request.getContextPath()%>/developerkeymags/delete.do";
	ajaxConstants("tblProviderMag|providerId|providerName|orderBy=providerId,tblApplicationMag|applicationId|applicationName|orderBy=applicationId");
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
			//title : '列表',
			//iconCls : 'icon-edit',//图标  
			//width: 700,  
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
				field : 'keyid',
				title : 'key编号',
				width : 100,
				align : 'center'
			}, {
				field : 'keyno',
				title : '开发者key',
				width : 240,
				align : 'center'
			}, {
				field : 'servicetype',
				title : '服务类型',
				width : 180,
				align : 'center'
			}, {
				field : 'providerid',
				title : '服务商',
				width : 120,
				align : 'center',
				formatter : function(value, row, index) {
					return getConstantDisplay('tblProviderMag', value);
				}
			}, {
				field : 'keystatus',
				title : 'key状态',
				width : 120,
				align : 'center',
				formatter : function(keystatus) {
					if (keystatus == 0) {
						return "启用";
					} else {
						return "停用";
					}
				}
			}, {
				field : 'applicationid',
				title : '应用',
				width : 120,
				align : 'center',
				formatter : function(value, row, index) {
					return getConstantDisplay('tblApplicationMag', value);
				}
			}, {
				field : 'status',
				title : '配额状态',
				width : 140,
				align : 'center',
				formatter : function(status) {
					if (status == 0) {
						return "<button class='btn btn-danger btn-sm disabled'>&nbsp;已分配</button>";
					} else {
						return "<button class='btn btn-primary btn-sm disabled'>&nbsp;未分配</button>";
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
	<div class="wrapper1 wrapper-content1 panel-fit gray-bg" region="center" style="border: 0">
		<div id='dataList'>
			<div id="tb"
				style="padding: 5px; height: auto;background-color: white;">
				<div style="margin-bottom: 5px;text-align: right;">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add"
						plain="true" onclick="showAddwindow({title:'新增'})">&nbsp;新增&nbsp;</a>| <a
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
						key状态: <input type="text" id="keystatus" name="keystatus" class="form-text"/>
						配额状态: <input type="text" id="status" name="status" class="form-text"/> <input
							type="button" class="commbtn"
							onclick="loadList(1);" value="查询" />
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
						<td style="text-align: right;font-size: 13px">key编号:</td>
						<td><input type="text" id="keyid" name="keyid"
							style="width: 180px" class="form-text input-margin"/></td>
						<td style="text-align: right;font-size: 13px">开发者编号:</td>
						<td><input type="text" id="keyno" name="keyno"
							style="width: 180px" class="form-text input-margin"/></td>
					</tr>
					<tr>
						<td style="text-align: right;font-size: 13px">服务类型:</td>
						<td><input type="text" id="servicetype" name="servicetype"
							style="width: 180px" class="form-text input-margin"/></td>
						<td style="text-align: right;font-size: 13px">服务商:</td>
						<td><select id="providerid" name="providerid"
							style="width: 180px" constantId="tblProviderMag" ></select></td>
					</tr>
					<tr>
						<td style="text-align: right;font-size: 13px">key状态:</td>
						<td><input type="text" id="keystatus" name="keystatus"
							style="width: 180px" class="form-text input-margin"/></td>
						<td style="text-align: right;font-size: 13px">应用:</td>
						<td><select id="applicationcode" name="applicationcode"
							style="width: 180px" constantId="tblApplicationMag"></select></td>
					</tr>
					<tr>
						<td style="text-align: right;font-size: 13px">配额状态:</td>
						<td><input type="text" id="status" name="status"
							style="width: 180px" class="form-text input-margin"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>
