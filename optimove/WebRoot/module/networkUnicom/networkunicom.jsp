<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<html>
	<head>
		<title>����</title>
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
		//title : '�б�',
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
			field : 'networkunicomid',
			title : '������',
			width : 210,
			align : 'center'
		}, {
			field : 'ipaddress',
			title : '����IP��ַ',
			width : 210,
			align : 'center'
		}, {
			field : 'port',
			title : '����˿ں�',
			width : 210,
			align : 'center'
		},  {
			field : 'applicationcode',
			title : 'Ӧ������',
			width : 210,
			align : 'center',
			formatter : function(value, row, index) {
				return getConstantDisplay('tblApplicationMag', value);
			}
		},{
			field : 'status',
			title : '��ͨ״̬',
			width : 180,
			align : 'center',
			formatter : function(status) {
				if (status == 0) {
					return "<button class='btn btn-primary btn-sm disabled'><i class='fa fa-check'>&nbsp;����ͨ</i></button>";
				} else {
					return "<button class='btn btn-danger btn-sm disabled'><i class='fa fa-times'>&nbsp;δ��ͨ</i></button>";
				}
			}
		}] ],
		onLoadSuccess : function(data) {
			data = convertJson(data);
			if (data.result != 'ok') {
				showBox("��ʾ��Ϣ", data.errorMsg, 'warning');
			}
		}

	});

	//���÷�ҳ�ؼ�  
	var p = $('#dataList').datagrid('getPager');
	$(p).pagination( {
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
				<div id="tb" style="padding: 5px; height: auto">
					<div style="margin-bottom: 5px; text-align: right;">
						<a href="#" class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="showAddwindow({title:'����'})">&nbsp;����&nbsp;</a>&nbsp;|
						<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
							plain="true"
							onclick="showUpdate({title:'�޸�',readonlyFields:['networkunicomid']});">&nbsp;�޸�&nbsp;</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="delRowData({id:'networkunicomid'});">&nbsp;ɾ��&nbsp;</a>|<a class="collapse-link" id="togglebtn">
                                        <i class="fa fa-chevron-up" id="up"></i>
                         </a>
					</div>
					<div id="searchdiv">
						<form id='searchForm' action="" method="post">
							������:
							<input type="text" id="networkunicomid" name="networkunicomid" class="form-text" />
							����IP��ַ:
							<input type="text" id="ipaddress" name="ipaddress" class="form-text" />
							����˿ں�:
							<input type="text" id="port" name="port" class="form-text" />
							״̬:
							<input type="text" id="status" name="status" class="form-text" />
							<input type="button" class="commbtn" onclick="loadList(1);" value="��ѯ" />
						</form>
					</div>
				</div>
			</div>
		</div>

		<div style="visibility: hidden">
			<div id="addwindow" title="���"
				style="width: 650px; height: 350px; padding: 10px">
				<form id='addForm' action="" method="post">
					<table>
						<tr>
							<td style="font-size: 13px">
								������:
							</td>
							<td>
								<input type="text" id="networkunicomid" name="networkunicomid"
									style="width: 180px" class="form-text input-margin"/>
							</td>
							<td style="font-size: 13px">
								����IP��ַ:
							</td>
							<td>
								<input type="text" id="ipaddress" name="ipaddress"
									style="width: 180px" class="form-text input-margin"/>
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								����˿ں�:
							</td>
							<td>
								<input type="text" id="port" name="port" style="width: 180px" class="form-text input-margin"/>
							</td>
							<td style="font-size: 13px">
								״̬:
							</td>
							<td>
								<select id="status" name="status"
									style="width: 180px" >
									<option value="0">��ͨ</option>
									<option value="1">δ��ͨ</option>
								</select>
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								Ӧ��ϵͳ:
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
