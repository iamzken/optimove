<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<html>
	<head>
		<title>����</title>
		<jsp:include page="/module/common/comm.jsp"></jsp:include>
		<script type="text/javascript" charset="GBK">
var searchUrl = "<%=request.getContextPath()%>/macrostatisticss/searchMacrostatistics.do";
var updateUrl = "<%=request.getContextPath()%>/macrostatisticss/update.do";
var insertUrl = "<%=request.getContextPath()%>/macrostatisticss/insert.do";
var deleteUrl = "<%=request.getContextPath()%>/macrostatisticss/delete.do";
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
			field : 'macrostatisticsid',
			title : '���ͳ�����ݱ��',
			width : 150,
			align : 'center'
		}, {
			field : 'macrostatisticsname',
			title : '���ͳ����������',
			width : 150,
			align : 'center'
		}, {
			field : 'macrodatasource',
			title : '���ͳ��������Դ',
			width : 150,
			align : 'center'
		}, {
			field : 'datatype',
			title : '���ͳ����������',
			width : 150,
			align : 'center'
		}, {
			field : 'remarks',
			title : '��ע��Ϣ',
			width : 100,
			align : 'center'
		}, {
			field : 'macrodatafile',
			title : '���ͳ������',
			width : 163,
			align : 'center'
		}, {
			field : 'operate',
			title : '����',
			width : 150,
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
		<div class="wrapper1 wrapper-content1 panel-fit gray-bg" region="center">
			<div id='dataList'>
				<div id="tb" style="padding: 5px; height: auto;background-color: white;">
					<div style="margin-bottom: 5px;text-align: right;">
						<a href="#" class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="showAddwindow({title:'����'})">����</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
							plain="true"
							onclick="showUpdate({title:'�޸�',readonlyFields:['macrostatisticsid']});">�޸�</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="delRowData({id:'macrostatisticsid'});">ɾ��</a>|<a class="collapse-link" id="togglebtn">
                                        <i class="fa fa-chevron-up" id="up"></i>
                         </a>
					</div>
					<div id="searchdiv">
						<form id='searchForm' action="" method="post">
							���ͳ����������:
							<input type="text" id="macrostatisticsname"
								name="macrostatisticsname" class="form-text"/>
							���ͳ��������Դ:
							<input type="text" id="macrodatasource" name="macrodatasource" class="form-text"/>
							��������:
							<input type="text" id="datatype" name="datatype" class="form-text"/>
							<input type="button" onclick="loadList(1);" value="��ѯ" class="commbtn"/>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div style="visibility: hidden">
			<div id="chartwindow" title="�鿴Ԥ��"
				style="width: 600px; height: 450px; padding: 1px">
				<%@ include file="macrodataMap.html"%>
			</div>
		</div>
		<div style="visibility: hidden">
			<div id="addwindow" title="���"
				style="width: 700px; height: 350px; padding: 10px">
				<form id='addForm' action="" method="post">
					<table>
						<tr>
							<td style="font-size: 13px;">
								������ݱ��:
							</td>
							<td style="font-size: 13px;">
								<input type="text" id="macrostatisticsid"
									name="macrostatisticsid" style="width: 180px" class="form-text input-margin"/>
							</td>
							<td style="font-size: 13px;">
								���ͳ����������:
							</td>
							<td>
								<input type="text" id="macrostatisticsname"
									name="macrostatisticsname" style="width: 180px" class="form-text input-margin"/>
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px;">
								���ͳ��������Դ:
							</td>
							<td>
								<input type="text" id="macrodatasource" name="macrodatasource"
									style="width: 180px" class="form-text input-margin"/>
							</td>
							<td style="font-size: 13px;">
								��������:
							</td>
							<td>
								<input type="text" id="datatype" name="datatype"
									style="width: 180px" class="form-text input-margin"/>
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px;">
								��ע:
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
