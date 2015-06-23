<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>�����ֵ�ά��</title>
		<jsp:include page="/module/common/comm.jsp"></jsp:include>
		<script type="text/javascript" charset="GBK">
			var searchUrl="<%=request.getContextPath()%>/lookuptypes/searchLookuptype.do";
			var updateUrl="<%=request.getContextPath()%>/lookuptypes/update.do";
			var insertUrl="<%=request.getContextPath()%>/lookuptypes/insert.do";
			var deleteUrl ="<%=request.getContextPath()%>/lookuptypes/delete.do";
			var searchLookupvaluesUrl="<%=request.getContextPath()%>/lookupvalues/searchLookupvalues.do";		
			var updateLookupvaluesUrl="<%=request.getContextPath()%>/lookupvalues/update.do";
			var insertLookupvaluesUrl="<%=request.getContextPath()%>/lookupvalues/insert.do";
			var deleteLookupvalues="<%=request.getContextPath()%>/lookupvalues/delete.do";
			var getLookvaluesBylookupUrl="<%=request.getContextPath()%>/lookupvalues/getLookvaluesBylookup.do";
		
			var constant ={pageList:[10,20,30],pageSize:10};
			
			$(function(){
				$('#dataList').datagrid({
				    title:'�ֵ������б�',  
	      			iconCls:'icon-edit',//ͼ��  
	      			fitColumns:true,
			        striped: true,  
			        border: false,  
			        idField : 'lookupType',
			        remoteSort:false,   
			        singleSelect:true,//�Ƿ�ѡ  
			        pagination:true,//��ҳ�ؼ�  
			        rownumbers:true,//�к�  
			        frozenColumns:[[  
			            {field:'ck',checkbox:true}  
			        ]],  
			        url:searchUrl, 
			        columns:[[
			        			{field:'lookupType',title:'�ֵ�����',width:100,align:'center'},
			        			{field:'customizationLevel',title:'�Զ��弶��',width:100,align:'center'},
			        			{field:'description',title:'����',width:100,align:'center'},
			        			{field:'createdBy',title:'������',width:100,align:'center'},
			        			{field:'creationDate',title:'��������',width:100,align:'center'},
			        			{field:'lastUpdateBy',title:'����޸���',width:100,align:'center'},
			        			{field:'lastUpdateDate',title:'����޸�����',width:100,align:'center'}
			        ]],
			        toolbar:[
					{
						id:'btnAddT',
						text:'����',
						iconCls:'icon-add',
						handler:function(){
							showAddwindow({title:'����'});
						}
					},'-',{
						id:'btneditT',
						text:'�޸�',
						iconCls:'icon-edit',
						handler:function(){
								showUpdateType({title:'�޸�',readonlyFields:['lookupType'],updateUrl:updateUrl});
						}
					},'-',{
						id:'btndelT',
						text:'ɾ��',
						iconCls:'icon-remove',
						handler:function(){
							delRowData({id:'lookupType',url:deleteUrl});
							
						}
					}],
			        
			        onLoadSuccess:function(data){	
			        $('#dataList').datagrid("unselectAll");		        
			    		data = convertJson(data);	 
			        	if(data.result!='ok'){
			        		showBox("��ʾ��Ϣ",data.msg,'warning');
			        	}
			        },
			        onSelect:function(index,data){
				        //��ʾ�ӱ�
				        loadValueList(1);
			        },
			        
				});
			
		   
		   
		   $('#dg_lookupvalueList').datagrid({
		      		title:'�ֵ��б�',  
	      			iconCls:'icon-edit',//ͼ��  
			        height: 'auto',  
			        nowrap: false,  
			        striped: true,  
			        border: true,  
			        collapsible:false,//�Ƿ���۵���  
			        fit: false,//�Զ���С  
			        remoteSort:false,  			       
			        singleSelect:true,//�Ƿ�ѡ
			        pagination:true,//��ҳ�ؼ�
			        rownumbers:true,//�к�  
			        fitColumns:true,
			        frozenColumns:[[  
			            {field:'ck',checkbox:true}  
			        ]],  			         			        
			        columns:[[
			        			{field:'lookupType',title:'�ֵ�����',width:100,align:'center'},
			        			{field:'lookupCode',title:'����',width:100,align:'center'},
			        			{field:'meaning',title:'����',width:100,align:'center'},
			        			{field:'remark',title:'��ע',width:100,align:'center'},
			        			{field:'enabledFlag',title:'���ñ�־',width:100,align:'center',
			        				formatter: function(value,row,index){ return value=='Y'?'����':'����'}},
			        			{field:'selectFlag',title:'ѡ�б�־',width:100,align:'center',
			        			formatter: function(value,row,index){ return value=='Y'?'����':'����'}},
			        			{field:'createdBy',title:'������',width:100,align:'center'},
			        			{field:'creationDate',title:'��������',width:100,align:'center'},
			        			{field:'lastUpdateBy',title:'����޸���',width:100,align:'center'},
			        			{field:'lastUpdateDate',title:'����޸�����',width:100,align:'center'}
			        ]],
			        toolbar:[
					{
						id:'btnAddAdd',
						text:'����',
						iconCls:'icon-add',
						handler:function(){
							showAddCodeDlg();
						}
					},{
					id:'btnedit2',
						text:'�޸�',
						iconCls:'icon-edit',
						handler:function(){
								showUpdateValues({title:'�޸�',readonlyFields:['lookupCode'],updateUrl:updateLookupvaluesUrl},'updatevalueswindow','updatevaluesForm');				
						}
					},'-',{
					id:'btndel',
						text:'ɾ��',
						iconCls:'icon-remove',
						handler:function(){
							delLookupData({id:'lookupCode',url:deleteLookupvalues,lookupType:'lookupType'});
							
						}
					}],
			        onLoadSuccess:function(data){			        			      			   		         
			    		data = convertJson(data);	 
			       		if(data.result!='ok'){
			       			showBox("��ʾ��Ϣ",data.msg,'warning');
			       		}
			        }
			        
			      
		   
		   });	
		   
		 /**
		 * �õ�datagrid �ķ�ҳ����
		 */
		 function PaginationConfig(datagridId,executeFunction){
			gridPager = $('#'+datagridId).datagrid('getPager');
			
				if (gridPager) {
					$(gridPager)
							.pagination(
									{
										onBeforeRefresh : function(pageNumber, pageSize) {
										
											eval(executeFunction+'('+pageNumber+')');
											return false;
										},
										onSelectPage : function(pageNumber, pageSize) {									   
											$('#'+datagridId).datagrid('options').pageNumber = pageNumber;
											$('#'+datagridId).datagrid('options').pageSize = pageSize;
											eval(executeFunction+'('+pageNumber+')');
										}
									});
				}
		 }
		  
		 function loadValueList(pageNumber){
			var selectType=$('#dataList').datagrid('getSelected');		
			if (!selectType){
				showBox('��ܰ��ʾ','��ѡҪ��ѯ������!','warning');
				return false;
			}
			$.post(getLookvaluesBylookupUrl,
			{
				"lookupType":selectType.lookupType,
				"page":pageNumber,
				"pageSize":$('#dataList').datagrid('getPager').data("pagination").pageSize		 	
				
			},function(response){	
				response = convertJson(response);		
				if(response.result != 'ok'){
					showBox("��ʾ��Ϣ",response.result,'warning');
					return;
				}	
				refreshDatagrid("dg_lookupvalueList",pageNumber);
				$('#dg_lookupvalueList').datagrid('loadData',response);	
				
			});
			
		}
		//�ӱ��ҳ
		PaginationConfig('dg_lookupvalueList','loadValueList');	 
		
		  // ����lookupCode
	function doAddCode(){
		  $('#addvaluesForm').form.url = insertLookupvaluesUrl; //���ύ·��
		  submitForm("addvaluesForm",$('#addvaluesForm').form.url,function(data){
		  	       eval('data='+data); 
					if(data.result == "ok"){					
						$('#dg_lookupvalueList').datagrid('clearSelections');//���ѡ��
						$('#addvalueswindow').dialog('close');
						showBox("��ʾ��Ϣ","����ɹ�",'info');
						var pageNumber = $('#dataList').datagrid('getPager').data("pagination").options.pageNumber;
						loadValueList(pageNumber); 
					}else{
						showBox("��ʾ��Ϣ",data.result,'warning');
				}
		  });
	}
		  
		  
    //show ��Ӳ���codeDlg 
	function showAddCodeDlg(){

		var selectType=$('#dataList').datagrid('getSelected');	
		if (!selectType){
			showBox('��ܰ��ʾ','��ѡҪ��ӵ�����!','warning');
			return false;
		}
		
		$('#addvaluesForm')[0].reset();
		$('#lookupType1').val(selectType.lookupType);
		$('#a_v_enabledFlag').val("Y");
		initDlg('#addvalueswindow').dialog({title:"��Ӳ���",buttons:[{
				text:'ȷ��',
				iconCls:'icon-ok',
				handler:function(){
					doAddCode();
				}
			},{
				text:'ȡ��',
				iconCls:'icon-cancel',
				handler:function(){
					$('#addvalueswindow').dialog('close');
				}
			}]});
		$('#addvalueswindow').dialog('open');
	}
	//�޸��ֵ�����
	function showUpdateType(jsonParam) {		
		jsonParam=jsonParam||{};
		jsonParam.updateUrl = isEmpty(jsonParam.updateUrl)?updateUrl:jsonParam.updateUrl;
		jsonParam.title = isEmpty(jsonParam.title)?$('#updatewindow').attr('title'):jsonParam.title;
		delError();
		var rows = $('#dataList').datagrid("getSelections");
		if(rows.length==0){
			$.messager.alert('��ʾ��','��ѡ���������','warning');  
			return;
		}
		var data = rows[0];
		//����ǰ����
		if(isFunction(jsonParam.preHandler)) 
			jsonParam.preHandler(jsonParam);
		else
			setFormValue("updateForm",data);
		setReadonly(jsonParam.readonlyFields);
		
		initDlg('#updatewindow').dialog({title:jsonParam.title,buttons:[{
			text:'�޸�',
			iconCls:'icon-ok',
			handler:function(){
	            //ȷ����ť�����ľ��崦����
				if( isFunction(jsonParam.updateHandler)){
					jsonParam.updateHandler(jsonParam);
				}else
					doEditType(jsonParam.updateUrl);
				
				
			}
		},{
			text:'ȡ��',
			iconCls:'icon-cancel',
			handler:function(){
				$("#updateForm")[0].reset();
				$('#updatewindow').dialog('close');
			}
		}]});
		//��������
		if(isFunction(jsonParam.afHandler)) jsonParam.afHandler(jsonParam);
		$('#updatewindow').dialog('open');
	
	}	
	//�޸��ֵ�����
	function doEditType(url){
			if(url)
			$('#updateForm').form.url =url; //���ύ·��
			submitForm("updateForm",$('#updateForm').form.url,function(data){
	  	       //eval('data='+data); 
	  	     	data = convertJson(data);
				if(data.result == "ok"){					
					$('#addDlg').dialog('close');
					showBox("��ʾ��Ϣ","�޸ĳɹ�",'info');
					var pageNumber = $('#dataList').datagrid('getPager').data("pagination").options.pageNumber;
					loadList(pageNumber);
					$('#updatewindow').dialog('close');
				}else{
					//showBox("��ʾ��Ϣ",data.result,'warning');
					showError(data);
			}
	  });
	}
	
	//�޸��ֵ�ֵ�б�
	function showUpdateValues(jsonParam,windowName,formName) {		
		jsonParam=jsonParam||{};
		jsonParam.updateUrl = isEmpty(jsonParam.updateUrl)?updateUrl:jsonParam.updateUrl;
		jsonParam.title = isEmpty(jsonParam.title)?$('#'+windowName).attr('title'):jsonParam.title;
		delError();
		var rows = $('#dg_lookupvalueList').datagrid("getSelections");
		if(rows.length==0){
			$.messager.alert('��ʾ��','��ѡ���������','warning');  
			return;
		}
		var data = rows[0];
		//����ǰ����
		if(isFunction(jsonParam.preHandler)) 
			jsonParam.preHandler(jsonParam);
		else
			setFormValue(formName,data);
		setReadonly(jsonParam.readonlyFields);
		
		initDlg('#'+windowName).dialog({title:jsonParam.title,buttons:[{
			text:'�޸�',
			iconCls:'icon-ok',
			handler:function(){
	            //ȷ����ť�����ľ��崦����
				if( isFunction(jsonParam.updateHandler)){
					jsonParam.updateHandler(jsonParam);
				}else
					doEditValues(jsonParam.updateUrl,windowName,formName);
				
				
			}
		},{
			text:'ȡ��',
			iconCls:'icon-cancel',
			handler:function(){
				$("#"+formName)[0].reset();
				$('#'+windowName).dialog('close');
			}
		}]});
		//��������
		if(isFunction(jsonParam.afHandler)) jsonParam.afHandler(jsonParam);
		$('#'+windowName).dialog('open');	
	
}
	
function doEditValues(url,windowName,formName){
	if(url)
		$('#'+formName).form.url =url; //���ύ·��
	submitForm(formName,$('#'+formName).form.url,function(data){
	  	       //eval('data='+data); 
	  	     	data = convertJson(data);
				if(data.result == "ok"){					
					$('#addDlg').dialog('close');
					showBox("��ʾ��Ϣ","�޸ĳɹ�",'info');
					var pageNumber = $('#dg_lookupvalueList').datagrid('getPager').data("pagination").options.pageNumber;
					loadValueList(pageNumber);
					$('#'+windowName).dialog('close');
				}else{
					//showBox("��ʾ��Ϣ",data.result,'warning');
					showError(data);
			}
	  });
}
	
	
	
/**
 * 
 * @param url ɾ���ֵ��url
 * @param id  ɾ��������
 */
function delLookupData(jsonParam)
{
	jsonParam=jsonParam||{};
	jsonParam.url =isEmpty(jsonParam.url)?deleteUrl:jsonParam.url;
	var rows = $('#dg_lookupvalueList').datagrid("getSelections");
	
	if(rows.length == 0){
		$.messager.alert('��ʾ��','��ѡ��Ҫɾ��������','warning');  
	}else{
		var ids = "";		
		var lookupType="";
		if(rows.length>1){
			for(var i = 0;i<rows.length;i++){
			ids += $(rows[i]).attr(jsonParam.id)+",";
			lookupType += $(rows[i]).attr(jsonParam.lookupType)+",";
		}
		}else{	
			ids=$(rows[0]).attr(jsonParam.id);
			lookupType=$(rows[0]).attr(jsonParam.lookupType);
		}
		var _param = convertJson('{\"'+jsonParam.id+'s\":\"'+ids+'\"}');
		Confirm('�Ƿ�ɾ'+rows.length+'�����ݣ�',function(){
		
			var _param = convertJson('{\"'+jsonParam.id+'s\":\"'+ids+'\"}');
			
			_param = convertJson('{\"'+jsonParam.id+'s\":\"'+ids + '\",\"' +jsonParam.lookupType+'\":\"'+lookupType+'\"}');
		
		
			$.post(jsonParam.url, _param, function(data) {
				$.messager.alert('��ʾ��','ɾ���ɹ�','info');
				var pageNumber = $('#dg_lookupvalueList').datagrid('getPager').data("pagination").options.pageNumber;
				loadValueList(pageNumber);				
			});
			
		});
	}
}
	
	
		   //����Զ��弶���������
		   ajaxConstants('customizationLevel');
	});
			
		</script>
	</head>
	<body class="easyui-layout">
		<div region="center" class="wrapper1 wrapper-content1 gray-bg panel-fit">
			<div style="background-color: white;">
			<form id='searchForm' action="" method="post">
						�ֵ�����:
						<input type="text" id="slookupType" name="lookupType" class="form-text" width="12px">
						�Զ��弶��:
						<input type="text" id="scustomizationLevel"
							name="customizationLevel" class="form-text">
						������:
						<input type="text" id="screatedBy"  name="createdBy" class="form-text">
						<input type="button" onclick="loadList(1);" value="��ѯ" class="commbtn">
			</form>
			<div id="dataList" style="height:300px;overflow:auto;"></div>
			</div>
			<div title="�ֵ��б�" style="height: 300px;overflow:auto;"">
				<div id="dg_lookupvalueList"></div>
			</div>

		</div>
		<%--   ����ֵ����� --%>
		<div style="visibility:hidden" >
		<div id="addwindow"  title="����ֵ�����" style="width:600px;height:350px;padding:10px">
			<form id='addForm' action="" method="post">
				<table>
					<tr>
						<td> �ֵ�����:</td>
						<td><input type="text" id="a_t_lookupType" name="lookupType" style="width:120px"></td>
						</tr>
						<tr>
						<td>�Զ��弶��:</td>
						<td>						
						<select id='a_t_customizationLevel' name='customizationLevel' style="width:120px" >
								<option value='1' selected="selected">1</option>
								<option value='2'>2</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>����:</td>										
						<td><input type="text" id="a_t_description" name="description" style="width:120px"></td>
					</tr>
						<tr  style="display: none">
						<td>������:</td>
						<td><input type="text" id="a_t_createdBy" name="createdBy" readonly="readonly" value="${LOGIN_USER.workId}"></td>
						</tr>
						<tr  style="display: none">
						<td>�������� :</td>
						<td>
							<input class="easyui-datebox" id="a_t_creationDate" name="creationDate"></input>
						</td>
						</tr>						
						
						<tr  style="display: none">
						<td>����޸��� :</td>
						<td><input type="text" id="a_t_lastUpdateBy" name="lastUpdateBy" style="width:120px"></td>
						</tr>
						
						<tr  style="display: none">
						<td>����޸����� :</td>
						<td>
							<input class="easyui-datebox" id="a_t_lastUpdateDate" name="lastUpdateDate"></input>
						</td>
						</tr>
					
				</table>
			</form>
		</div>
	</div>
	<%--   �޸��ֵ����� --%>
	
	<div style="visibility:hidden" >
		<div id="updatewindow"  title="�޸��ֵ�����" style="width:600px;height:350px;padding:10px">
			<form id='updateForm' action="" method="post">
				<table>
					<tr>
						<td> �ֵ�����:</td>
						<td><input type="text" id="u_t_lookupType" name="lookupType" readonly="readonly" style="width:120px"></td>
						</tr>
						<tr>
						<td>�Զ��弶��:</td>
						<td>
						<input type="text" id="u_t_customizationLevel" name="customizationLevel" style="width:120px">
						
						</td>
					</tr>
					<tr>
						<td>����:</td>										
						<td><input type="text" id="u_t_description" name="description" style="width:120px"></td>
					</tr>
						<tr style="display: none">
						<td>������:</td>
						<td><input type="text" id="u_t_createdBy" name="createdBy"  readonly="readonly" style="width:120px"></td>
						</tr>
						<tr  style="display: none">
						<td>�������� :</td>
						<td>
							<input class="easyui-datebox" id="u_t_creationDate" name="creationDate"></input>
						</td>
						</tr>	
						<tr  style="display: none">
						<td>����޸��� :</td>
						<td><input type="text" id="u_t_lastUpdateBy" name="lastUpdateBy" style="width:120px"></td>
						</tr>
						
						<tr  style="display: none">
						<td>����޸����� :</td>
						<td>
							<input class="easyui-datebox" id="u_t_lastUpdateDate" name="lastUpdateDate"></input>
						</td>
						</tr>
					
				</table>
			</form>
		</div>
	</div>
	
<%--   ����ֵ�ֵ�б� --%>
		<div style="visibility: hidden">
			<div id="addvalueswindow" title="���"
				style="width: 600px; height: 350px; padding: 10px">
				<form id='addvaluesForm' action="" method="post">
					<input id="lookupType1" type="hidden" name="lookupType" />
					<table>
						<tr>
							<td>
								�ֵ����:
							</td>
							<td>
								<input type="text" id="a_v_lookupCode" name="lookupCode"
									style="width: 120px">
							</td>
						</tr>
						<tr>
							<td>
								����:
							</td>
							<td>
								<input type="text" id="a_v_meaning" name="meaning"
									style="width: 120px">
							</td>
						</tr>
						<tr>
							<td>
								��ע:
							</td>
							<td>
								<input type="text" id="a_v_remark" name="remark"
									style="width: 120px">
							</td>
						</tr>
						<tr>
							<td>
								���ñ�־:
							</td>
							<td>
								<select id="a_v_enabledFlag" name="enabledFlag">
									<option value="Y" selected="selected">
										����
									</option>
									<option value="N">
										����
									</option>
									
								</select>
							</td>
						</tr>
						<tr>
							<td>
								ѡ�б�־:
							</td>
							<td>
								<select id="a_v_selectFlag" name="selectFlag">
									<option value="Y">
										ѡ��
									</option>
									<option value="N" selected="selected">
										δѡ
									</option>
								</select>
							</td>
						</tr>
						<tr  style="display: none">
							<td>
								������:
							</td>
							<td>
								<input type="text" id="a_v_createdBy" name="createdBy"
									style="width: 120px">
							</td>
						</tr>
						<tr style="display: none">
							<td>
								��������:
							</td>
							<td>
								<input type="text" id="a_v_creationDate" name="creationDate"
									style="width: 120px">
							</td>
						</tr>
					
					</table>
				</form>
			</div>
		</div>
		
		
		<%--   �޸��ֵ�ֵ�б� --%>
		<div style="visibility: hidden">
			<div id="updatevalueswindow" title="�޸��ֵ�ֵ"
				style="width: 600px; height: 350px; padding: 10px">
				<form id='updatevaluesForm' action="" method="post">
					<input id="lookupType_u_v" type="hidden" name="lookupType" />
					<table>
						<tr>
							<td>
								�ֵ����:
							</td>
							<td>
								<input type="text" id="u_v_lookupCode" name="lookupCode" readonly="readonly" style="width: 120px">
							</td>
						</tr>
						<tr>
							<td>
								����:
							</td>
							<td>
								<input type="text" id="u_v_meaning" name="meaning"
									style="width: 120px">
							</td>
						</tr>
						<tr>
							<td>
								��ע:
							</td>
							<td>
								<input type="text" id="u_v_remark" name="remark"
									style="width: 120px">
							</td>
						</tr>
						<tr>
							<td>
								���ñ�־:
							</td>
							<td>
								<select id="u_v_enabledFlag" name="enabledFlag">
									<option value="N">
										����
									</option>
									<option value="Y" selected="selected">
										����
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								ѡ�б�־:
							</td>
							<td>
								<select id="u_v_selectFlag" name="selectFlag">
									<option value="Y" selected="selected">
										ѡ��
									</option>
									<option value="N">
										δѡ
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								������:
							</td>
							<td>
								<input type="text" id="u_v_createdBy" name="createdBy" readonly="readonly"
									style="width: 120px">
							</td>
						</tr>
						<tr>
							<td>
								����޸���:
							</td>
							<td>
								<input type="text" id="u_v_lastUpdateBy" name="lastUpdateBy"
									style="width: 120px">
							</td>
						</tr>
						<tr>
							<td>
								����޸�����:
							</td>
							<td>
								<input type="text" id="u_v_lastUpdateDate" name="lastUpdateDate"
									style="width: 120px">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>
</html>