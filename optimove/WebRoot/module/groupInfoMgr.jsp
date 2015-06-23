<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>��ɫ����</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/groupInfos/searchGroupInfo.do";
   var updateUrl = "<%=request.getContextPath()%>/groupInfos/update.do";
   var insertUrl = "<%=request.getContextPath()%>/groupInfos/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/groupInfos/delete.do";
   ajaxConstants("groupOption");
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
	        //title:'�б�',  
	        //iconCls:'icon-edit',//ͼ��  
	        width: 800,  
	        height: 'auto',  
	        nowrap: false,  
	        striped: true,  
	        border: false,  
	        collapsible:false,//�Ƿ���۵���  
	        fit: true,//�Զ���С  
	        url:'#',  
	        remoteSort:false,   
	        singleSelect:false,//�Ƿ�ѡ  
	        pagination:true,//��ҳ�ؼ�  
	        rownumbers:true,//�к�  
	        frozenColumns:[[  
	            {field:'ck',checkbox:true}  
	        ]],  
	        url:searchUrl, 
	        toolbar:'#tb',
	        columns:[[   
                   		{field:'grpId',title:'��ɫID',width:180,align:'center'},
                   		{field:'grpCname',title:'��ɫ������',width:200,align:'center'},
                   		{field:'grpEname',title:'��ɫӢ����',width:180,align:'center'},
                   		{field:'grpLevel',title:'��ɫ����',width:170,align:'center',formatter: function(value,row,index){ 
                   		    return getConstantDisplay('Grade',value); 
                   		}},
                   		//{field:'cretaedBy',title:'������',width:100,align:'center'},
                   		//{field:'creationDate',title:'��������',width:100,align:'center'},
                   		//{field:'lastUpdatedBy',title:'����޸���',width:100,align:'center'},
                   		//{field:'lastUpdateDate',title:'����޸�����',width:100,align:'center'},
                   		{field:'grpRemark',title:'��ע',width:200,align:'center'}
	        ]],
	        onLoadSuccess:function(data){
	    		data = convertJson(data);
	        	if(data.result!='ok'){
	        		showBox("��ʾ��Ϣ",data.errorMsg,'warning');
	        	}
	        },
	        onDblClickRow:function(rowIndex, rowData){
	    		window.location.href='<%=request.getContextPath()%>/module/permissionTree.jsp?grpId='+rowData.grpId;
	        }
	        
	    });  
	
	    //���÷�ҳ�ؼ�  
	    var p = $('#dataList').datagrid('getPager');  
	    $(p).pagination({  
	        pageSize: 10,//ÿҳ��ʾ�ļ�¼������Ĭ��Ϊ10  
	        pageList: [10,20,30],//��������ÿҳ��¼�������б�  
	        beforePageText: '��',//ҳ���ı���ǰ��ʾ�ĺ���  
	        afterPageText: 'ҳ    �� {pages} ҳ',  
	        displayMsg: '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	    })
	    paginationConfig('dataList');
	});
	</script>
  </head>
  
  <body class="easyui-layout" >
	<div  region="center" class="wrapper1 wrapper-content1 gray-bg panel-fit">
		<div id='dataList'>
			<div id="tb" style="padding:5px;height:auto;background-color: white;">
		<div style="margin-bottom:5px;text-align: right;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddwindow({title:'����'})">����</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸�',readonlyFields:['grpId']});">�޸�</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'grpId'});">ɾ��</a>|<a class="collapse-link" id="togglebtn">
                                        <i class="fa fa-chevron-up" id="up"></i>
                         </a>
		</div>
		<div id="searchdiv">
			<form  id='searchForm' action="" method="post">
				��ɫID:
				<input type="text" id="sgrpId" name="grpId" class="form-text">
				��ɫ������:
				<input type="text" id="sgrpCname" name="grpCname" class="form-text">
				��ɫӢ����:
				<input type="text" id="sgrpEname" name="grpEname" class="form-text">
				<input type="button" onclick="loadList(1);" value="��ѯ" class="commbtn">
			</form>
		</div>
	</div>
		</div>
	</div>
	
	<div style="visibility:hidden" >
		<div id="addwindow"  title="���" style="width:600px;height:350px;padding:10px">
			<form id='addForm' action="" method="post">
				<table>
						<tr>
							<td style="font-size: 13px">��ɫID:</td>
							<td><input type="text" id="grpId" name="grpId" style="width:180px" class="form-text input-margin"></td>
							<td style="font-size: 13px">��ɫ������:</td>
							<td><input type="text" id="grpCname" name="grpCname" style="width:180px" class="form-text input-margin"></td>
						</tr>
						<tr>
							<td style="font-size: 13px">��ɫӢ����:</td>
							<td><input type="text" id="grpEname" name="grpEname" style="width:180px" class="form-text input-margin"></td>
							<td style="font-size: 13px">��ɫ����:</td>
							<td>
							<select id="grpLevel" name="grpLevel" constantId="Grade" style="width:180px" class="clientInput"></select>
							</td>
						</tr>
						<tr style="display: none">
							<td style="font-size: 13px">������:</td>
							<td><input type="text" id="cretaedBy" name="cretaedBy" style="width:180px" class="form-text input-margin"></td>
						</tr>
						<tr style="display: none">
							<td>��������:</td>
							<td><input type="text" class="easyui-datebox"  id="creationDate" name="creationDate" style="width:180px"></td>
						</tr>
						<tr style="display: none">
							<td>����޸���:</td>
							<td><input type="text" id="lastUpdatedBy" name="lastUpdatedBy" style="width:180px" class="form-text input-margin"></td>
						</tr>
						<tr style="display: none">
							<td>����޸�����:</td>
							<td><input type="text" id="lastUpdateDate" name="lastUpdateDate" style="width:180px" class="easyui-datebox"></td>
						</tr>
						<tr style="font-size: 13px">
							<td>��ע:</td>
							<td><input type="text" id="grpRemark" name="grpRemark" style="width:180px" class="form-text input-margin"></td>
						</tr>
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
