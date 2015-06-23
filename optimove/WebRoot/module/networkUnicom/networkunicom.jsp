<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<html>
	<head>
		<title>管理</title>
		<jsp:include page="/module/common/comm.jsp"></jsp:include>
		<script type="text/javascript" charset="GBK">
var searchUrl = "<%=request.getContextPath()%>/networkunicoms/searchNetworkunicom.do";
var updateUrl = "<%=request.getContextPath()%>/networkunicoms/update.do";
var insertUrl = "<%=request.getContextPath()%>/networkunicoms/insert.do";
var deleteUrl = "<%=request.getContextPath()%>/networkunicoms/delete.do";
ajaxConstants("tblApplicationMag|applicationId|applicationName|orderBy=applicationId");
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
			field : 'networkunicomid',
			title : '网络编号',
			width : 210,
			align : 'center'
		}, {
			field : 'ipaddress',
			title : '网络IP地址',
			width : 210,
			align : 'center'
		}, {
			field : 'port',
			title : '网络端口号',
			width : 210,
			align : 'center'
		},  {
			field : 'applicationcode',
			title : '应用名称',
			width : 210,
			align : 'center',
			formatter : function(value, row, index) {
				return getConstantDisplay('tblApplicationMag', value);
			}
		},{
			field : 'status',
			title : '联通状态',
			width : 180,
			align : 'center',
			formatter : function(status) {
				if (status == 0) {
					return "<button class='btn btn-primary btn-sm disabled'><i class='fa fa-check'>&nbsp;已联通</i></button>";
				} else {
					return "<button class='btn btn-danger btn-sm disabled'><i class='fa fa-times'>&nbsp;未联通</i></button>";
				}
			}
		}] ],
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
		<div class="wrapper1 wrapper-content1 panel-fit gray-bg" region="center">
			<div id='dataList'>
				<div id="tb" style="padding: 5px; height: auto">
					<div style="margin-bottom: 5px; text-align: right;">
						<a href="#" class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="showAddwindow({title:'新增'})">&nbsp;新增&nbsp;</a>&nbsp;|
						<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
							plain="true"
							onclick="showUpdate({title:'修改',readonlyFields:['networkunicomid']});">&nbsp;修改&nbsp;</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="delRowData({id:'networkunicomid'});">&nbsp;删除&nbsp;</a>|<a class="collapse-link" id="togglebtn">
                                        <i class="fa fa-chevron-up" id="up"></i>
                         </a>
					</div>
					<div id="searchdiv">
						<form id='searchForm' action="" method="post">
							网络编号:
							<input type="text" id="networkunicomid" name="networkunicomid" class="form-text" />
							网络IP地址:
							<input type="text" id="ipaddress" name="ipaddress" class="form-text" />
							网络端口号:
							<input type="text" id="port" name="port" class="form-text" />
							状态:
							<input type="text" id="status" name="status" class="form-text" />
							<input type="button" class="commbtn" onclick="loadList(1);" value="查询" />
						</form>
					</div>
				</div>
			</div>
		</div>

		<div style="visibility: hidden">
			<div id="addwindow" title="添加"
				style="width: 650px; height: 350px; padding: 10px">
				<form id='addForm' action="" method="post">
					<table>
						<tr>
							<td style="font-size: 13px">
								网络编号:
							</td>
							<td>
								<input type="text" id="networkunicomid" name="networkunicomid"
									style="width: 180px" class="form-text input-margin"/>
							</td>
							<td style="font-size: 13px">
								网络IP地址:
							</td>
							<td>
								<input type="text" id="ipaddress" name="ipaddress"
									style="width: 180px" class="form-text input-margin"/>
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								网络端口号:
							</td>
							<td>
								<input type="text" id="port" name="port" style="width: 180px" class="form-text input-margin"/>
							</td>
							<td style="font-size: 13px">
								状态:
							</td>
							<td>
								<select id="status" name="status"
									style="width: 180px" >
									<option value="0">联通</option>
									<option value="1">未联通</option>
								</select>
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								应用系统:
							</td>
							<td>
								<select id="applicationcode" name="applicationcode"
									style="width: 180px" constantId="tblApplicationMag"></select>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>
</html>
