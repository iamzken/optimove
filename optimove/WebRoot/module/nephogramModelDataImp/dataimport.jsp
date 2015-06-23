<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.io.File"%>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>云图数据导入</title>
<jsp:include page="/module/common/comm.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/js/plugins/treeview/bootstrap-treeview.js"></script>
<link href="<%=request.getContextPath()%>/css/plugins/treeview/bootstrap-treeview.css" rel="stylesheet">
<script type="text/javascript">
	ajaxConstants("tblMapModelMag|mapModelId|mapModelName|attributedes|orderBy=mapModelId");
</script>
<script type="text/javascript">
var searchtblUrl = "<%=request.getContextPath()%>/mapmodelmags/searchMapmodelmag.do";
var searchModelUrl = "<%=request.getContextPath()%>/nephogrammodelmags/searchNephogrammodelmag.do";
var getNephogramatrributeByModelId = "<%=request.getContextPath()%>/nephogramatrributes/getNephogramatrributeByModelId.do";

var searchUrl = "<%=request.getContextPath()%>/mapmodelinfos/searchMapmodelinfo.do";
var subSearchUrl = "<%=request.getContextPath()%>/modelattributes/searchModelattribute.do";
var getAttributesUrl = "<%=request.getContextPath()%>/modelattributes/getAttributes.do";
var importDataUrl = "<%=request.getContextPath()%>/mapmodelinfos/importModelData.do";
var downloadFileUrl = "<%=request.getContextPath()%>/mapmodelinfos/downloadFile.do";

var gettablename = "<%=request.getContextPath()%>/mapmodelinfos/gettablename.do";
var getModelDataUrl = "<%=request.getContextPath()%>/mapmodelinfos/getModelData.do";
var modelAttributeInsertUrl = "<%=request.getContextPath()%>/mapmodelinfos/insertModelData.do";
var uploadimgUrl = "<%=request.getContextPath()%>/mapmodelinfos/uploadimgData.do";
var getSuperModelDataUrl = "<%=request.getContextPath()%>/mapmodelinfos/getSuperModelData.do";
var imgPath = "<%=request.getContextPath()%>/mobileimages/";
	var treeData = "";
	$(function() {
		$('#dataList')
				.datagrid(
						{
							title : '云图模型列表',
							iconCls : 'icon-edit',//图标  
							width : 600,
							height : 630,
							nowrap : false,
							striped : true,
							border : true,
							collapsible : false,//是否可折叠的  
							fit : true,//自动大小
							fitColumns : true,
							remoteSort : false,
							singleSelect : false,//是否单选  
							pagination : true,//分页控件  
							rownumbers : true,//行号  
							frozenColumns : [ [ {
								field : 'ck',
								checkbox : true
							} ] ],
							url : searchUrl,
							idField : 'modelid',
							//toolbar:'#tb',
							columns : [ [
									{
										field : 'modelid',
										title : '模型id',
										width : 100,
										align : 'center',
										hidden : true
									},
									{
										field : 'modelcode',
										title : '模型代码',
										width : 100,
										align : 'center'
									},
									{
										field : 'modelname',
										title : '模型名称',
										width : 100,
										align : 'center'
									},
									{
										field : 'modeltype',
										title : '模型类型',
										width : 100,
										align : 'center'
									},
									{
										field : 'modeldatatable',
										title : '模型数据表表名',
										width : 100,
										align : 'center'
									},
									{
										field : 'opt',
										title : '操作',
										width : 200,
										align : 'center',
										formatter : function(value, row, index) {
											return "<button class='btn btn-info btn-sm' onclick=\"show('"
													+ row.modeldatatable
													+ "')\">查看</button>&nbsp;&nbsp;"+
													"<button class='btn btn-info btn-sm' onclick=\"downloadFile('"+row.modeldatatable+"')\">模板下载</button>";
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
			pageList : [ 10, 20, 30 ]
		});
		var defaultdata = "[{text: '数据来源',nodes: [{text: '电子文档',id:'1'},{text: '影像系统',id:'2'},{text: '地址文件',id:'3'},{text: '接口',id:'4'}]}]";
		$('#baiduTree').treeview({
			color : "#428bca",
			expandIcon: 'glyphicon glyphicon-chevron-right',
			collapseIcon: 'glyphicon glyphicon-chevron-down',
			nodeIcon: 'glyphicon glyphicon-bookmark',
			onhoverColor : "orange",
			data : eval(defaultdata),
			onNodeSelected : function(event, node) {
				showdataList();
			}
		});
	});
	function changePlugin(text) {
		$("#dataList").empty();
		if (text == "电子文档") {
			showdataList();
		} else if (text == "地址文件") {
			var item = $('#dataList').datagrid('getRows');
			if (item) {
				for ( var i = item.length - 1; i >= 0; i--) {
					var index = $('#dataList').datagrid('getRowIndex', item[i]);
					$('#dataList').datagrid('deleteRow', index);
				}
			}
			$("#uploadfileDiv").hide();
		} else if (text == "影像系统") {
			var item = $('#dataList').datagrid('getRows');
			if (item) {
				for ( var i = item.length - 1; i >= 0; i--) {
					var index = $('#dataList').datagrid('getRowIndex', item[i]);
					$('#dataList').datagrid('deleteRow', index);
				}
			}
			$("#uploadfileDiv").hide();
		} else if (text == "接口") {
			var item = $('#dataList').datagrid('getRows');
			if (item) {
				for ( var i = item.length - 1; i >= 0; i--) {
					var index = $('#dataList').datagrid('getRowIndex', item[i]);
					$('#dataList').datagrid('deleteRow', index);
				}
			}
			$("#uploadfileDiv").hide();
		}

	}
	function selectfile() {
		var selectfile = $("#fileId").val();
		var row = $('#dataList').datagrid('getSelected');
	}

	function showdataList() {
		$('#dataList')
				.datagrid(
						{
							title : '云图模型列表',
							iconCls : 'icon-edit',//图标  
							width : 600,
							height : 600,
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
							idField : 'modelid',
							//toolbar:'#tb',
							columns : [ [
									{
										field : 'modelid',
										title : '模型id',
										width : 100,
										align : 'center',
										hidden : true
									},
									{
										field : 'modelcode',
										title : '模型代码',
										width : 100,
										align : 'center'
									},
									{
										field : 'modelname',
										title : '模型名称',
										width : 100,
										align : 'center'
									},
									{
										field : 'modeltype',
										title : '模型类型',
										width : 100,
										align : 'center'
									},
									{
										field : 'modeldatatable',
										title : '模型数据表表名',
										width : 100,
										align : 'center'
									},
									{
										field : 'opt',
										title : '操作',
										width : 200,
										align : 'center',
										formatter : function(value, row, index) {
											return "<button class='btn btn-info btn-sm' onclick=\"show('"
													+ row.modeldatatable
													+ "')\">查看</button>&nbsp;&nbsp;"+
													"<button class='btn btn-info btn-sm' onclick=\"downloadFile('"+row.modeldatatable+"')\">模板下载</button>";
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
			pageList : [ 10, 20, 30 ]
		})
		$("#uploadfileDiv").show();
	}
	function show(str) {
		$.ajax({
			url : getAttributesUrl,
			data : {
				tablename : str
			},
			dataType : "json",
			success : function(data) {
				$.each(data, function(key, value) {
					$('#valuelist').empty();
					$('#valuelist').datagrid({
						width : 820,
						height : 480,
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
						//toolbar:'#detailtb',
						url : getSuperModelDataUrl,
						queryParams : {
							tablename : str
						},
						columns : eval(value),
						onLoadSuccess : function(data) {
							data = convertJson(data);
							if (data.result != 'ok') {
								showBox("提示信息", data.errorMsg, 'warning');
							}
						}
					});
					//设置分页控件  
					var p = $('#valuelist').datagrid('getPager');
					$(p).pagination({
						pageSize : 10,//每页显示的记录条数，默认为10  
						pageList : [ 10, 20, 30 ]
					});

					initDlg('#showDetail').dialog({
						title : "数据列表",
						buttons : [ {
							text : '关闭',
							iconCls : 'icon-cancel',
							handler : function() {
								$('#showDetail').dialog('close');
							}
						} ]
					});
					$('#showDetail').dialog('open');

				});
			}
		});
	}

	function gettablename(str) {
		$.post({
			url : gettablename,
			data : {
				modelid : str
			},
			dataType : "json",
			success : function(data) {
				$.each(data, function(key, value) {
					alert(value);
					return value;
				});
			}
		});
	}
	function showProcess() {
		
		var selectType = $('#dataList').datagrid('getSelected');
		var selectfile = $("#fileId").val();
		var rows = $('#dataList').datagrid("getSelections");
		if (!selectType || selectfile == "") {
			showBox('温馨提示', '请选要添加的云图模型或者文件!', 'warning');
			return false;
		}
		if (rows.length > 1) {
			showBox('温馨提示', '请选择一条模型!', 'warning');
			return false;
		}
		$('#addForm')[0].reset();
		$("#attributelist").empty();
		$("#connectfile").empty();
		var input = document.getElementById("fileId");
		var fReader = new FileReader();
		fReader.readAsDataURL(input.files[0]);
		fReader.onloadend = function(event) {
			var img = document.getElementById("imgsrc");
			img.src = event.target.result;
		};
		$("#attributelist").append("<input type='text' id='tablename' name='tablename' value='"+selectType.modeldatatable+"' style='display:none'/>");
		$("#connectfile").append("<div class='checkbox'><input type='text' class='form-text' name='filepath' value='" + selectfile + "' readonly/></div>");
		$.ajax({
				url : subSearchUrl,
				data : {
					tablename : selectType.modeldatatable
				},
				dataType : "json",
				success : function(data){
					$.each(data,function(key, value){
						if (key == 'rows'){
							$.each(value,function(idx,obj){
								var inpt = "<input id='" + obj.modelattribute + "' name='"+obj.modelattribute+"'"+(obj.modelattribute=="picture"?"readonly='readonly'":"")+" class='form-text input-margin' placeholder='"+obj.modelattributename+"'>";
								$("#attributelist").append(inpt);
							});
						}
					});
					var name = selectfile.split("\\");
					if($("#picture")){
						$("#picture").val(name[name.length-1]);
					}
			   }
		});

		initDlg('#addwindow').dialog({
			title : "加工",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					doAddCode();
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$("#fileId").val("");
					$("#dataList").datagrid('clearSelections');
					$('#addwindow').dialog('close');
				}
			} ]
		});
		
		$('#addwindow').dialog('open');
	}

	function doAddCode(){
	  $("#uploadForm").attr("action", uploadimgUrl);
	  submitForm("uploadForm", uploadimgUrl, function(data){
			data = convertJson(data);
			if(data.result == "ok"){
				$('#addForm').form.url = modelAttributeInsertUrl; //表单提交路径
				$('#picture').val(data.fileName);
				submitForm("addForm",$('#addForm').form.url,function(data){
			  	        eval('data='+data); 
						if(data.result == "ok"){
							$('#addwindow').dialog('close');
							$("#fileId").empty();
							$("#dataList").datagrid('clearSelections');
							showBox("提示信息","保存成功",'info');
						}else{
							showBox("提示信息",data.result,'warning');
						}
				}); 
			}else{
				 showBox("提示信息",data.result,'warning');
			}
  	  });
	  $('#addwindow').dialog('close');
	  $("#fileId").empty();
	  $("#dataList").datagrid('clearSelections');
	  showBox("提示信息","保存成功",'info');
	}
	function impsuccess() {
		var selectType = $('#dataList').datagrid('getSelected');
		var selectfile = $("#fileId").val();
		if (!selectType || selectfile == "") {
			showBox('温馨提示', '请选要添加的云图模型或者文件!', 'warning');
			return false;
		}
		$("#uploadTablename").val(selectType.modeldatatable);
		$("#uploadForm").attr("action", importDataUrl);
		submitForm("uploadForm", importDataUrl, function(data){
			data = convertJson(data);
			if(data.result == "ok"){
				showBox("提示信息", "导入成功", 'info');
			}else{
				showBox("提示信息",data.result,'warning');
			}
	  	});
		
	}
	
	function downloadFile(tbname) {
		location.href =  downloadFileUrl + "?tablename=" + tbname;
	}
</script>
</head>
<body>
	<div class="easyui-layout" style="width:100%px;height:600px;" region="center">
		<div data-options="region:'west'" style="width:200px;height: 100%">
			<ul id="baiduTree" class="easyui-tree" style="width: 100%; height: 100%;">
			</ul>
		</div>
		<div data-options="region:'center'" style="padding: 0px;width:70%;height: 100%;border: 0">
			<div class="panel-fit" region="center">
				<div id='dataList'></div>
			</div>
		</div>
		<div data-options="region:'south'" style="height:200px;">
			<form id="uploadForm" action="" method="post" enctype="multipart/form-data">
				<div id="uploadfileDiv" align="center">
					<table style="text-align: center;">
						<tr>
							<td><label class="control-label" style="font-size: 13px">选择文件:&nbsp; </label></td>
							<td>
								<div>
									<input class="form-control" type="file" id="fileId" name="fileId" value="" onchange="selectfile()">
									<input type="text" id="uploadTablename" name="uploadTablename" value="" style="display: none;">
								</div>
							</td>
							<td align="right">&nbsp; 
								<input class="btn" type="button" value="导入" onclick="impsuccess()" /> &nbsp;&nbsp;
							</td>
							<td align="left">
								<input type="button" id="reprocessbtn" class="btn" value="加工" onclick="javascript:showProcess()">
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
	<div style="visibility: hidden">
		<div id="addwindow" title="加工" style="width: 1000px; height: 450px; padding: 10px">
			<form id='addForm' action="" method="post">
				<div style="width: 220; height: 100%; float: left; margin-left: 1px;">
					<div class="easyui-tabs" border="false" cache="false">
						<div id="connectfile" title="关联文件" style="width: 33%; overflow: hidden;" align="left"></div>
					</div>
				</div>
				<div style="width: 220; height: 100%; float: left; margin-left: 1px;">
					<div class="easyui-tabs" border="false" cache="false">
						<div id="fileinfo" title="文件信息" style="width: 33%; overflow: hidden;" align="left">
							<img id="imgsrc" width="300px" height="400px" />
						</div>
					</div>
				</div>
				<div style="width: 220; height: 100%; float: left; margin-left: 1px;">
					<div class="easyui-tabs" border="false" cache="false">
						<div id="attributelist" title="属性列表" style="width: 33%; border: 1px; border-color: blue">
						</div>
					</div>
				</div>
				<div style="width: 240; height: 100%; float: left; margin-left: 1px;">
					<div class="easyui-tabs" border="false" cache="false">
						<div id="maplist" title="地图" style="width: 80%; border: 1px; border-color: blue" align="left">
							<div>
								<%@ include file="dataimp.html"%>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div style="visibility: hidden">
		<div id="showDetail" title="信息详情" style="width: 820px; height: 480px;padding: 0px">
				<div id="valuelist" class="panel-fit" style="padding: 0px">
					<div id="detailtb" style="padding: 0px">
						<div style="text-align: right;">
							<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="">新增</a>| 
							<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'修改',readonlyFields:['mapmodelid']});">修改</a>|
							<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'mapmodelid'});">删除</a>
						</div>
					</div>
				</div>
		</div>
	</div>
</body>
</html>
