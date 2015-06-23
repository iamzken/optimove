<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<html>
<head>
<title>����</title>
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
			//title : '�����б�',
			//iconCls : 'icon-edit',//ͼ��  
			width: 600,  
			height : 'auto',
			nowrap : false,
			striped : true,
			border : false,
			collapsible : false,//�Ƿ���۵���  
			fit : true,//�Զ���С  
			url : '#',
			remoteSort : false,
			singleSelect : false,//�Ƿ�ѡ  
			pagination : true,//��ҳ�ؼ�  
			rownumbers : true,//�к�  
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			} ] ],
			url : searchUrl,
			toolbar : '#tb',
			columns : [ [ {
				field : 'apicode',
				title : 'api���',
				width : 120,
				align : 'center'
			}, {
				field : 'apiname',
				title : 'api����',
				width : 150,
				align : 'center'
			}, {
				field : 'apitype',
				title : '����',
				width : 130,
				align : 'center'
			}, {
				field : 'apiversion',
				title : '�汾��',
				width : 120,
				align : 'center'
			}, {
				field : 'operatetime',
				title : '����ʱ��',
				width : 150,
				align : 'center'
			}, {
				field : 'status',
				title : '����״̬',
				width : 150,
				align : 'center',
				formatter : function(status) {
					if (status == 0) {
						return "<button class='btn btn-primary btn-sm'><i class='fa fa-check'>&nbsp;����</i></button>";
					} else {
						return "<button class='btn btn-warning btn-sm'><i class='fa fa-warning'>&nbsp;ͣ��</i></button>";
					}
				}
			}, {
				field : 'providerid',
				title : '������',
				width : 200,
				align : 'center',
				formatter : function(value, row, index) {
					return getConstantDisplay('tblProviderMag', value);
				}
			} ] ],
			onLoadSuccess : function(data) {
				data = convertJson(data);
				if (data.result != 'ok') {
					showBox("��ʾ��Ϣ", data.errorMsg, 'warning');
				}
			}

		});

		//���÷�ҳ�ؼ�  
		var p = $('#dataList').datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//ÿҳ��ʾ�ļ�¼������Ĭ��Ϊ10  
			pageList : [ 10, 20, 30 ],//��������ÿҳ��¼�������б�  
			beforePageText : '��',//ҳ���ı���ǰ��ʾ�ĺ���  
			afterPageText : 'ҳ    �� {pages} ҳ',
			displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
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
						plain="true" onclick="showAddwindow({title:'����'})">&nbsp;����&nbsp;</a> | <a
						href="#" class="easyui-linkbutton" iconCls="icon-edit"
						plain="true"
						onclick="showUpdate({title:'�޸�',readonlyFields:['keyid']});">&nbsp;�޸�&nbsp;</a>|
					<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
						plain="true" onclick="delRowData({id:'keyid'});">&nbsp;ɾ��&nbsp;</a>|<a class="collapse-link" id="togglebtn">
                                        <i class="fa fa-chevron-up" id="up"></i>
                         </a>
				</div>
				<div id="searchdiv">
					<form id='searchForm' action="" method="post">
						����: <input type="text" id="apiname" name="apiname" class="form-text" style="width:180px"/>
						�汾��: <input type="text" id="apiversion" name="apiversion" class="form-text" style="width: 180px"/> <input
							type="button" class="commbtn"
							onclick="loadList(1);" value="��ѯ" />
					</form>
				</div>
			</div>
		</div>
	</div>
	<div style="visibility: hidden;">
		<div id="addwindow" title="���"
			style="width: 600px; height: 350px; padding: 20px">
			<form id='addForm' action="" method="post">
				<table>
					<tr>
						<td style="text-align: right;font-size: 13px">���:</td>
						<td><input type="text" id="apicode" name="apicode"
							style="width: 180px" class="form-text input-margin"/></td>
						<td style="text-align: right;font-size: 13px">����:</td>
						<td><input type="text" id="apiname" name="apiname"
							style="width: 180px" class="form-text input-margin"/></td>
					</tr>
					<tr>
						<td style="text-align: right;font-size: 13px">����:</td>
						<td><input type="text" id="apitype" name="apitype"
							style="width: 180px" class="form-text input-margin"/></td>
						<td style="text-align: right;font-size: 13px">�汾��:</td>
						<td><input type="text" id="apiversion" name="apiversion"
							style="width: 180px" class="form-text input-margin"/></td>
					</tr>
					<tr>
						<td style="text-align: right;font-size: 13px">������:</td>
						<td><select id="providerid" name="providerid"
							style="width: 180px" constantId="tblProviderMag" class="easyui-combobox"></select></td>
						<td style="text-align: right;font-size: 13px">״̬:</td>
						<td><input type="text" id="status" name="status"
							style="width: 180px" class="form-text input-margin"/></td>
					</tr>
					<tr>
						<td style="text-align: right;font-size: 13px">����ʱ��:</td>
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
