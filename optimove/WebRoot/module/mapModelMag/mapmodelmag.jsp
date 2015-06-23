<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>����</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/mapmodelmags/searchMapmodelmag.do";
   var updateUrl = "<%=request.getContextPath()%>/mapmodelmags/update.do";
   var insertUrl = "<%=request.getContextPath()%>/mapmodelmags/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/mapmodelmags/delete.do";
   ajaxConstants("mblx");
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
	        width: 700,  
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
                   		{field:'mapmodelid',title:'ģ����',width:150,align:'center'},
                   		{field:'mapmodeltype',title:'ģ������',width:150,align:'center',formatter: function(value,row,index){ 
           		         return getConstantDisplay('mblx',value); 
           				}},   
                   		{field:'mapmodelname',title:'ģ������',width:200,align:'center'},
                   		{field:'mapmodeldes',title:'ģ������',width:200,align:'center'},
                   		{field:'attributedes',title:'��������',width:150,align:'center'},
                   		{field:'datades',title:'��������',width:164,align:'center'}
                   		//{field:'htmlmodel',title:'HTMLģ��',hidden:true,width:120,align:'center'}
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
	});
	</script>
  </head>
  
  <body class="easyui-layout" >
	<div  class="wrapper1 wrapper-content1 gray-bg panel-fit" region="center">
		<div id='dataList'>
			<div id="tb" style="padding:5px;height:auto;background-color: white;">
		<div style="margin-bottom:5px;text-align: right;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddwindow({title:'����'})">&nbsp;����&nbsp;</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'�޸�',readonlyFields:['mapmodelid']});">&nbsp;�޸�&nbsp;</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'mapmodelid'});">&nbsp;ɾ��&nbsp;</a>|<a class="collapse-link" id="togglebtn">
                                        <i class="fa fa-chevron-up" id="up"></i>
                         </a>
		</div>
		<div id="searchdiv">
			<form  id='searchForm' action="" method="post">
				ģ������:
				<select id="mapmodeltype" name="mapmodeltype" constantId="mblx" style="width:180px"></select>
				ģ������:
				<input type="text" id="mapmodelname" name="mapmodelname" class="form-text"/>
				<input type="button" onclick="loadList(1);" value="��ѯ" class="commbtn"/>
			</form>
		</div>
	</div>
		</div>
	</div>
	
	<div style="visibility:hidden" >
		<div id="addwindow"  title="���" style="width:600px;height:450px;padding:10px">
			<form id='addForm' action="" method="post">
				<table>
						<tr>
							<td style="font-size: 13px">ģ����:</td>
							<td><input type="text" id="mapmodelid" name="mapmodelid" style="width:180px" class="form-text input-margin"/></td>
							<td style="font-size: 13px">ģ������:</td>
							<td><select id="mapmodeltype" name="mapmodeltype" constantId="mblx" style="width:180px"></select></td>
					
						</tr>
						<tr>
							<td style="font-size: 13px">ģ������:</td>
							<td><input type="text" id="mapmodelname" name="mapmodelname" style="width:180px" class="form-text input-margin"/></td>
							<td style="font-size: 13px">ģ������:</td>
							<td><input type="text" id="mapmodeldes" name="mapmodeldes" style="width:180px" class="form-text input-margin"/></td>
						</tr>
						<tr>
							<td style="font-size: 13px">��������:</td>
							<td><input type="text" id="attributedes" name="attributedes" style="width:180px" class="form-text input-margin"/></td>
							<td style="font-size: 13px">��������:</td>
							<td><input type="text" id="datades" name="datades" style="width:180px" class="form-text input-margin"/></td>
						</tr>
						<tr>
							<td style="font-size: 13px">HTMLģ��:</td>
							<td colspan="3"><textarea type="text" id="htmlmodel" name="htmlmodel" style="width:450px" value=" "></textarea></td>
						</tr>
				</table>
			</form>
		</div>
	</div>
  </body>
</html>
