<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>�û�����</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/users/searchUser.do";
   var updateUrl = "<%=request.getContextPath()%>/users/update.do";
   var insertUrl = "<%=request.getContextPath()%>/users/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/users/delete.do";
   ajaxConstants("groupOption,tbldepartment|departmentcode|departmentname|orderBy=departmentname,tblgroupinfo|grpid|grpcname|orderBy=grpcname,tblnewBranchinfo|BranchCode|name|orderBy=name");
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
		checkLength(['workId'],8);
	    $('#dataList').datagrid({  
	        //title:'�û���Ϣ�б�',  
	        //iconCls:'icon-edit',//ͼ��  
	        //width: 700,  
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
	            {field:'workId',title:'�û�Ա����',width:100,align:'center'},   
	            {field:'userName',title:'�û���',width:100,align:'center'},   
	            {field:'userLoginName',title:'��¼��',width:100,align:'center'},
	            {field:'userBankCode',title:'����������',width:100,align:'center',formatter: function(value,row,index){ 
           		    return getConstantDisplay('tblnewBranchinfo',value); 
           		}},     
	            {field:'userDept',title:'��������',width:100,align:'center',formatter: function(value,row,index){ 
           		    return getConstantDisplay('tbldepartment',value); 
           		}},  
	            {field:'userLevel',title:'ְλ',width:100,align:'center'},   
	            {field:'groupId',title:'��ɫ',width:100,align:'center',formatter: function(value,row,index){ 
           		    return getConstantDisplay('tblgroupinfo',value); 
           		}},
	            {field:'userStatus',title:'״̬',width:100,align:'center',width:80},   
	            {field:'operatorCode',title:'����Ա��',width:100,align:'center'},   
	            {field:'telephone',title:'��ϵ�绰',width:100,align:'center'},
	            {field:'userRemark',title:'��ע',width:100,align:'center'}
	        ]],
	        onLoadSuccess:function(data){
	    		data = convertJson(data);
	        	if(data.result!='ok'){
	        		showBox("��ʾ��Ϣ",data.errorMsg,'warning');
	        	}
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

	function initLoginData(){
		$("#operatorCode").val("${sessionScope.WorkId}");
		//$("#subbranchCode").combobox('setValue',"${sessionScope.SubbranchCode}");
		//$("#branchCode").val("${sessionScope.BranchCode}");
	}
	
	</script>
  </head>
  
  <body class="easyui-layout" >
	<div  region="center" class="wrapper1 wrapper-content1 panel-fit gray-bg">
		<div id='dataList'>
			<div id="tb" style="padding:5px;height:auto;background-color: white;">
		<div style="margin-bottom:5px;text-align: right;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddwindow({title:'�����û�',preHandler:initLoginData})">����</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸��û�',readonlyFields:['workId']});">�޸�</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'workId'});">ɾ��</a>|<a class="collapse-link" id="togglebtn">
                                        <i class="fa fa-chevron-up" id="up"></i>
                         </a>
		</div>
		<div id="searchdiv">
			<form  id='searchForm' action="" method="post">
				�û�ID��
				<input type="text" id="sworkId" name="workId" class="form-text">
				�û�����
				<input type="text" id="suserName" name="userName" class="form-text">
				<input type="button" onclick="loadList(1);" value="��ѯ" class="commbtn">
			</form>
		</div>
	</div>
		</div>
	</div>
	
	<div style="visibility:hidden" >
		<div id="addwindow"  title="����û�" style="width:600px;height:360px;padding:10px">
			<form id='addForm' action="" method="post">
				<table>
					<tr>
						<td style="width: 100;font-size: 13px">Ա����:</td>
						<td><input type="text" id="workId" name="workId" style="width:180px" class="form-text input-margin"></td>
						<td style="width: 100;font-size: 13px">���� :</td>
						<td><input type="text" id="userName" name="userName" style="width:180px" class="form-text input-margin"></td>
					</tr>
					<tr>
						<td style="width: 100;font-size: 13px">��¼��:</td>
						<td><input type="text" id="userLoginName" name="userLoginName" style="width:180px" class="form-text input-margin"></td>
						<td style="width: 100;font-size: 13px">���������� :</td>
						<td><select id="userBankCode" name="userBankCode" constantId="tblnewBranchinfo" style="width:180px"></select></td>
					
					</tr>
					<tr>
						<td style="width: 100;font-size: 13px">��������:</td>
						<td><select id="userDept" name="userDept" constantId="tbldepartment" style="width:180px"></select></td>
					
						<td style="width: 100;font-size: 13px">ְλ :</td>
						<td><input type="text" id="userLevel" name="userLevel" style="width:180px" class="form-text input-margin"></td>
					</tr>
					<tr>
						<td style="width: 100;font-size: 13px">��ɫ��</td>
						<td><select id="groupId" name="groupId" constantId="tblgroupinfo" style="width:180px"></select></td>
					
					</tr>
					<tr>
						<td style="width: 100;font-size: 13px">״̬:</td>
						<td><input type="text" id="userStatus" name="userStatus" style="width:180px" class="form-text input-margin"></td>
						<td style="width: 100;font-size: 13px">����Ա�� :</td>
						<td><input type="text" id="operatorCode" name="operatorCode" style="width:180px" class="form-text input-margin"></td>
					</tr>
					<tr>
						<td style="width: 100;font-size: 13px">��ϵ�绰:</td>
						<td><input type="text" id="telephone" name="telephone" style="width:180px" class="form-text input-margin"></td>
						<td style="width: 100;font-size: 13px">��ע:</td>
						<td><input type="text" id="userRemark" name="userRemark" style="width:180px" class="form-text input-margin"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
  </body>
</html>
