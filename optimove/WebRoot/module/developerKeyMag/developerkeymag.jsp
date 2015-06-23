<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<html>
<head>
<title>����</title>
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
			//title : '�б�',
			//iconCls : 'icon-edit',//ͼ��  
			//width: 700,  
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
				field : 'keyid',
				title : 'key���',
				width : 100,
				align : 'center'
			}, {
				field : 'keyno',
				title : '������key',
				width : 240,
				align : 'center'
			}, {
				field : 'servicetype',
				title : '��������',
				width : 180,
				align : 'center'
			}, {
				field : 'providerid',
				title : '������',
				width : 120,
				align : 'center',
				formatter : function(value, row, index) {
					return getConstantDisplay('tblProviderMag', value);
				}
			}, {
				field : 'keystatus',
				title : 'key״̬',
				width : 120,
				align : 'center',
				formatter : function(keystatus) {
					if (keystatus == 0) {
						return "����";
					} else {
						return "ͣ��";
					}
				}
			}, {
				field : 'applicationid',
				title : 'Ӧ��',
				width : 120,
				align : 'center',
				formatter : function(value, row, index) {
					return getConstantDisplay('tblApplicationMag', value);
				}
			}, {
				field : 'status',
				title : '���״̬',
				width : 140,
				align : 'center',
				formatter : function(status) {
					if (status == 0) {
						return "<button class='btn btn-danger btn-sm disabled'>&nbsp;�ѷ���</button>";
					} else {
						return "<button class='btn btn-primary btn-sm disabled'>&nbsp;δ����</button>";
					}
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
	<div class="wrapper1 wrapper-content1 panel-fit gray-bg" region="center" style="border: 0">
		<div id='dataList'>
			<div id="tb"
				style="padding: 5px; height: auto;background-color: white;">
				<div style="margin-bottom: 5px;text-align: right;">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add"
						plain="true" onclick="showAddwindow({title:'����'})">&nbsp;����&nbsp;</a>| <a
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
						key״̬: <input type="text" id="keystatus" name="keystatus" class="form-text"/>
						���״̬: <input type="text" id="status" name="status" class="form-text"/> <input
							type="button" class="commbtn"
							onclick="loadList(1);" value="��ѯ" />
					</form>
				</div>
			</div>
		</div>
	</div>

	<div style="visibility: hidden">
		<div id="addwindow" title="���"
			style="width: 600px; height: 350px; padding: 10px">
			<form id='addForm' action="" method="post">
				<table>
					<tr>
						<td style="text-align: right;font-size: 13px">key���:</td>
						<td><input type="text" id="keyid" name="keyid"
							style="width: 180px" class="form-text input-margin"/></td>
						<td style="text-align: right;font-size: 13px">�����߱��:</td>
						<td><input type="text" id="keyno" name="keyno"
							style="width: 180px" class="form-text input-margin"/></td>
					</tr>
					<tr>
						<td style="text-align: right;font-size: 13px">��������:</td>
						<td><input type="text" id="servicetype" name="servicetype"
							style="width: 180px" class="form-text input-margin"/></td>
						<td style="text-align: right;font-size: 13px">������:</td>
						<td><select id="providerid" name="providerid"
							style="width: 180px" constantId="tblProviderMag" ></select></td>
					</tr>
					<tr>
						<td style="text-align: right;font-size: 13px">key״̬:</td>
						<td><input type="text" id="keystatus" name="keystatus"
							style="width: 180px" class="form-text input-margin"/></td>
						<td style="text-align: right;font-size: 13px">Ӧ��:</td>
						<td><select id="applicationcode" name="applicationcode"
							style="width: 180px" constantId="tblApplicationMag"></select></td>
					</tr>
					<tr>
						<td style="text-align: right;font-size: 13px">���״̬:</td>
						<td><input type="text" id="status" name="status"
							style="width: 180px" class="form-text input-margin"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>
