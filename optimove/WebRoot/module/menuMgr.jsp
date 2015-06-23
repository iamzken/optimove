<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>�˵�����</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/menu/searchMenu.do";
   var updateUrl = "<%=request.getContextPath()%>/menu/update.do";
   var insertUrl = "<%=request.getContextPath()%>/menu/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/menu/delete.do";
    ajaxConstants("cdbz,cdzt");
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
	        //title:'�˵��б�',  
	        iconCls:'icon-edit',//ͼ��  
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
	            {field:'menuCode',title:'�˵�����',width:100,align:'center'},   
	            {field:'menuParent',title:'���˵����',width:100,align:'center'},   
	            {field:'menuName',title:'�˵�����',width:100,align:'center'},
	            {field:'icon',title:'ͼ��',width:100,align:'center'},   
	            {field:'menuUrl',title:'��ָURL',width:250,align:'center',dataalign:'left'},   
	            {field:'menuModel',title:'����ģ��',width:100,align:'center'},  
	            {field:'menuFlag',title:'�˵�״̬',width:100,align:'center',formatter: function(value,row,index){ 
           		    	return getConstantDisplay('cdzt',value); 
           		}},   
	             {field:'isMenu',title:'�˵���־',width:100,align:'center',formatter: function(value,row,index){ 
           		    	return getConstantDisplay('cdbz',value); 
           		}},
	            {field:'menuRemark',title:'��ע',align:'center'}
	        ]]

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
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddwindow({title:'�����˵�'})">����</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸Ĳ˵�',readonlyFields:['menuCode']});">�޸�</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'menuCode'});">ɾ��</a>|<a class="collapse-link" id="togglebtn">
                                        <i class="fa fa-chevron-up" id="up"></i>
                         </a>
		</div>
		<div id="searchdiv">
			<form  id='searchForm' action="" method="post">
				�˵���ţ�
				<input type="text" id="smenuCode" name="menuCode" class="form-text">
				�˵����ƣ�
				<input type="text" id="smenuName" name="menuName" class="form-text">
				<input type="button" onclick="loadList(1);" value="��ѯ" class="commbtn">
			</form>
		</div>
	</div>
		</div>
	</div>
	
	<div style="visibility:hidden" >
		<div id="addwindow"  title="��Ӳ˵�" style="width:600px;height:350px;padding:10px">
			<form id='addForm' action="" method="post">
				<table>
					<tr>
						<td style="font-size: 13px">�˵�����:</td>
						<td><input type="text" id="menuCode" name="menuCode" style="width:180px" class="form-text input-margin"></td>
						<td style="font-size: 13px">���˵���� :</td>
						<td><input type="text" id="menuParent" name="menuParent" style="width:180px" class="form-text input-margin"></td>
					</tr>
					<tr>
						<td style="font-size: 13px">�˵�����:</td>
						<td><input type="text" id="menuName" name="menuName" style="width:180px" class="form-text input-margin"></td>
						<td style="font-size: 13px">ͼ�� :</td>
						<td><input type="text" id="icon" name="icon" style="width:180px" class="form-text input-margin"></td>
					</tr>
					<tr>
						<td style="font-size: 13px">��ָURL:</td>
						<td><input type="text" id="menuUrl" name="menuUrl" style="width:180px" class="form-text input-margin"></td>
						<td style="font-size: 13px">����ģ��:</td>
						<td><input type="text" id="menuModel" name="menuModel" style="width:180px" class="form-text input-margin"></td>
					</tr>
					<tr>
						<td style="font-size: 13px">�˵�״̬:</td>
						<td><select id="menuFlag" name="menuFlag" constantId="cdzt" style="width:180px"></select></td>
					
						<td style="font-size: 13px">�˵���־ :</td>
						<td><select id="isMenu" name="isMenu" constantId="cdbz" style="width:180px"></select></td>
					
					</tr>
					<tr>
						<td style="font-size: 13px">��ע:</td>
						<td><input type="text" id="menuRemark" name="menuRemark" style="width:180px" class="form-text input-margin"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
  </body>
</html>
