<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<html>
	<head>
		<title>����</title>
		<jsp:include page="/module/common/comm.jsp"></jsp:include>
		<script type="text/javascript" charset="GBK">
var searchUrl = "<%=request.getContextPath()%>/applicationmags/searchApplicationmag.do";
var updateUrl = "<%=request.getContextPath()%>/applicationmags/update.do";
var insertUrl = "<%=request.getContextPath()%>/applicationmags/insert.do";
var deleteUrl = "<%=request.getContextPath()%>/applicationmags/delete.do";
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
		width: '600',  
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
			field : 'applicationid',
			title : 'Ӧ�ñ��',
			width : 200,
			align : 'center'
		}, {
			field : 'applicationname',
			title : 'Ӧ������',
			width : 300,
			align : 'center'
		}, {
			field : 'applicationdes',
			title : 'Ӧ������',
			width : 302,
			align : 'center'
		}, {
			field : 'status',
			title : '��֤״̬',
			width : 215,
			align : 'center',
			formatter : function(status) {
				if (status == 0) {
					return "<button class='btn btn-success btn-sm'><i class='fa fa-check'>����֤</i></button>";
				} else {
					return "<button class='btn btn-warning btn-sm'><i class='fa fa-warning'>δ��֤</i></button>";
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
		<div class="wrapper1 wrapper-content1 gray-bg panel-fit" region="center">
			<div id='dataList'>
				<div id="tb" style="padding: 5px;background-color: white;">
					<div style="margin-bottom: 5px;text-align: right;">
						<a href="#" class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="showAddwindow({title:'����'})">&nbsp;����&nbsp;</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
							plain="true"
							onclick="showUpdate({title:'�޸�',readonlyFields:['applicationid']});">&nbsp;�޸�&nbsp;</a>|
						<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="delRowData({id:'applicationid'});">&nbsp;ɾ��&nbsp;</a>|<a class="collapse-link" id="togglebtn">
                                        <i class="fa fa-chevron-up" id="up"></i>
                         </a>
					</div>
					<div id="searchdiv">
						<form id='searchForm' action="" method="post">
							Ӧ������:
							<input type="text" id="applicationname" name="applicationname" class="form-text" style="width:180px"/>
							��֤״̬:
							<input type="text" id="status" name="status" class="form-text" style="width:180px"/>
							<input type="button" onclick="loadList(1);" value="��ѯ" class="commbtn"/>
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
							<td style="font-size: 13px">
								Ӧ�ñ��:
							</td>
							<td>
								<input type="text" id="applicationid" name="applicationid"
									style="width: 180px" class="form-text input-margin"/>
							</td>
							<td style="font-size: 13px">
								Ӧ������:
							</td>
							<td>
								<input type="text" id="applicationname" name="applicationname"
									style="width: 180px" class="form-text input-margin"/>
							</td>
						</tr>
						<tr>
							<td style="font-size: 13px">
								Ӧ������:
							</td>
							<td>
								<input type="text" id="applicationdes" name="applicationdes"
									style="width: 180px" class="form-text input-margin"/>
							</td>
							<td style="font-size: 13px">
								��֤״̬:
							</td>
							<td>
								<input type="text" id="status" name="status"
									style="width: 180px" class="form-text input-margin"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>
</html>
