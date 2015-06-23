<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<html>
	<head>
		<title>����</title>
		<jsp:include page="/module/common/comm.jsp"></jsp:include>
		<script type="text/javascript" charset="GBK">
var searchUrl = "<%=request.getContextPath()%>/grabdatas/searchGrabdata.do";
var updateUrl = "<%=request.getContextPath()%>/grabdatas/update.do";
var insertUrl = "<%=request.getContextPath()%>/grabdatas/insert.do";
var deleteUrl = "<%=request.getContextPath()%>/grabdatas/delete.do";
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
		width: 800,  
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
			field : 'grabdataid',
			title : '���ݱ��',
			width : 120,
			align : 'center'
		}, {
			field : 'grabdataname',
			title : '��������',
			width : 150,
			align : 'center'
		}, {
			field : 'grabdatasource',
			title : '������Դ',
			width : 120,
			align : 'center'
		}, {
			field : 'grabdatatype',
			title : '��������',
			width : 120,
			align : 'center'
		}, {
			field : 'remarks',
			title : '��ע��Ϣ',
			width : 150,
			align : 'center'
		}, {
			field : 'grabdatafile',
			title : '����',
			width : 150,
			align : 'center'
		}, {
			field : 'operate',
			title : '����',
			width : 220,
			align : 'center',
			formatter : function(operate) {
				return "<button class='commbtn' onclick='showchart()'>Ԥ��</button>";
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
	$(p).pagination( {
		pageSize : 10,//ÿҳ��ʾ�ļ�¼������Ĭ��Ϊ10  
		pageList : [ 10, 20, 30 ],//��������ÿҳ��¼�������б�  
		beforePageText : '��',//ҳ���ı���ǰ��ʾ�ĺ���  
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	})
});

function showchart(){
	initDlg('#chartwindow').dialog( {
			title : "�鿴Ԥ��",
			buttons : [
				{
				text : '�ر�',
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
							plain="true" onclick="showAddwindow({title:'����'})">&nbsp;����&nbsp;</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
							plain="true"
							onclick="showUpdate({title:'�޸�',readonlyFields:['grabdataid']});">&nbsp;�޸�&nbsp;</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="delRowData({id:'grabdataid'});">&nbsp;ɾ��&nbsp;</a>|<a class="collapse-link" id="togglebtn">
                                        <i class="fa fa-chevron-up" id="up"></i>
                         </a>
					</div>
					<div id="searchdiv">
						<form id='searchForm' action="" method="post">
							��������:
							<input type="text" id="grabdataname" name="grabdataname" class="form-text"/>
							������Դ:
							<input type="text" id="grabdatasource" name="grabdatasource" class="form-text"/>
							��������:
							<input type="text" id="grabdatatype" name="grabdatatype" class="form-text"/>
							<input type="button" onclick="loadList(1);" value="��ѯ" class="commbtn"/>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div style="visibility: hidden">
			<div id="chartwindow" title="�鿴Ԥ��"
				style="width: 600px; height: 450px; padding: 1px">
				<%@ include file="grabdataMap.html"%>
			</div>
		</div>
		<div style="visibility: hidden">
			<div id="addwindow" title="���"
				style="width: 600px; height: 350px; padding: 10px">
				<form id='addForm' action="" method="post">
					<table>
						<tr>
							<td style="font-size: 13px">
								���ݱ��:
							</td>
							<td>
								<input type="text" id="grabdataid" name="grabdataid"
									style="width: 180px" class="form-text input-margin"/>
							</td>
							<td style="font-size: 13px">
								��������:
							</td>
							<td>
								<input type="text" id="grabdataname" name="grabdataname"
									style="width: 180px" class="form-text input-margin"/>
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								������Դ:
							</td>
							<td>
								<input type="text" id="grabdatasource" name="grabdatasource"
									style="width: 180px" class="form-text input-margin"/>
							</td>
							<td style="font-size: 13px">
								��������:
							</td>
							<td>
								<input type="text" id="grabdatatype" name="grabdatatype"
									style="width: 180px" class="form-text input-margin"/>
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								��ע��Ϣ:
							</td>
							<td>
								<input type="text" id="remarks" name="remarks"
									style="width: 180px" class="form-text input-margin"/>
							</td>
							<td style="font-size: 13px">
								����:
							</td>
							<td>
								<input type="text" id="grabdatafile" name="grabdatafile"
									style="width: 180px" class="form-text input-margin"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>
</html>
