<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>数据字典维护</title>
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
				    title:'字典类型列表',  
	      			iconCls:'icon-edit',//图标  
	      			fitColumns:true,
			        striped: true,  
			        border: false,  
			        idField : 'lookupType',
			        remoteSort:false,   
			        singleSelect:true,//是否单选  
			        pagination:true,//分页控件  
			        rownumbers:true,//行号  
			        frozenColumns:[[  
			            {field:'ck',checkbox:true}  
			        ]],  
			        url:searchUrl, 
			        columns:[[
			        			{field:'lookupType',title:'字典类型',width:100,align:'center'},
			        			{field:'customizationLevel',title:'自定义级别',width:100,align:'center'},
			        			{field:'description',title:'描述',width:100,align:'center'},
			        			{field:'createdBy',title:'创建人',width:100,align:'center'},
			        			{field:'creationDate',title:'创建日期',width:100,align:'center'},
			        			{field:'lastUpdateBy',title:'最后修改人',width:100,align:'center'},
			        			{field:'lastUpdateDate',title:'最后修改日期',width:100,align:'center'}
			        ]],
			        toolbar:[
					{
						id:'btnAddT',
						text:'新增',
						iconCls:'icon-add',
						handler:function(){
							showAddwindow({title:'新增'});
						}
					},'-',{
						id:'btneditT',
						text:'修改',
						iconCls:'icon-edit',
						handler:function(){
								showUpdateType({title:'修改',readonlyFields:['lookupType'],updateUrl:updateUrl});
						}
					},'-',{
						id:'btndelT',
						text:'删除',
						iconCls:'icon-remove',
						handler:function(){
							delRowData({id:'lookupType',url:deleteUrl});
							
						}
					}],
			        
			        onLoadSuccess:function(data){	
			        $('#dataList').datagrid("unselectAll");		        
			    		data = convertJson(data);	 
			        	if(data.result!='ok'){
			        		showBox("提示信息",data.msg,'warning');
			        	}
			        },
			        onSelect:function(index,data){
				        //显示子表
				        loadValueList(1);
			        },
			        
				});
			
		   
		   
		   $('#dg_lookupvalueList').datagrid({
		      		title:'字典列表',  
	      			iconCls:'icon-edit',//图标  
			        height: 'auto',  
			        nowrap: false,  
			        striped: true,  
			        border: true,  
			        collapsible:false,//是否可折叠的  
			        fit: false,//自动大小  
			        remoteSort:false,  			       
			        singleSelect:true,//是否单选
			        pagination:true,//分页控件
			        rownumbers:true,//行号  
			        fitColumns:true,
			        frozenColumns:[[  
			            {field:'ck',checkbox:true}  
			        ]],  			         			        
			        columns:[[
			        			{field:'lookupType',title:'字典类型',width:100,align:'center'},
			        			{field:'lookupCode',title:'编码',width:100,align:'center'},
			        			{field:'meaning',title:'含义',width:100,align:'center'},
			        			{field:'remark',title:'备注',width:100,align:'center'},
			        			{field:'enabledFlag',title:'启用标志',width:100,align:'center',
			        				formatter: function(value,row,index){ return value=='Y'?'启用':'禁用'}},
			        			{field:'selectFlag',title:'选中标志',width:100,align:'center',
			        			formatter: function(value,row,index){ return value=='Y'?'启用':'禁用'}},
			        			{field:'createdBy',title:'创建人',width:100,align:'center'},
			        			{field:'creationDate',title:'创建日期',width:100,align:'center'},
			        			{field:'lastUpdateBy',title:'最后修改人',width:100,align:'center'},
			        			{field:'lastUpdateDate',title:'最后修改日期',width:100,align:'center'}
			        ]],
			        toolbar:[
					{
						id:'btnAddAdd',
						text:'新增',
						iconCls:'icon-add',
						handler:function(){
							showAddCodeDlg();
						}
					},{
					id:'btnedit2',
						text:'修改',
						iconCls:'icon-edit',
						handler:function(){
								showUpdateValues({title:'修改',readonlyFields:['lookupCode'],updateUrl:updateLookupvaluesUrl},'updatevalueswindow','updatevaluesForm');				
						}
					},'-',{
					id:'btndel',
						text:'删除',
						iconCls:'icon-remove',
						handler:function(){
							delLookupData({id:'lookupCode',url:deleteLookupvalues,lookupType:'lookupType'});
							
						}
					}],
			        onLoadSuccess:function(data){			        			      			   		         
			    		data = convertJson(data);	 
			       		if(data.result!='ok'){
			       			showBox("提示信息",data.msg,'warning');
			       		}
			        }
			        
			      
		   
		   });	
		   
		 /**
		 * 得到datagrid 的分页对象
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
				showBox('温馨提示','请选要查询的类型!','warning');
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
					showBox("提示信息",response.result,'warning');
					return;
				}	
				refreshDatagrid("dg_lookupvalueList",pageNumber);
				$('#dg_lookupvalueList').datagrid('loadData',response);	
				
			});
			
		}
		//子表分页
		PaginationConfig('dg_lookupvalueList','loadValueList');	 
		
		  // 增加lookupCode
	function doAddCode(){
		  $('#addvaluesForm').form.url = insertLookupvaluesUrl; //表单提交路径
		  submitForm("addvaluesForm",$('#addvaluesForm').form.url,function(data){
		  	       eval('data='+data); 
					if(data.result == "ok"){					
						$('#dg_lookupvalueList').datagrid('clearSelections');//清空选择
						$('#addvalueswindow').dialog('close');
						showBox("提示信息","保存成功",'info');
						var pageNumber = $('#dataList').datagrid('getPager').data("pagination").options.pageNumber;
						loadValueList(pageNumber); 
					}else{
						showBox("提示信息",data.result,'warning');
				}
		  });
	}
		  
		  
    //show 添加参数codeDlg 
	function showAddCodeDlg(){

		var selectType=$('#dataList').datagrid('getSelected');	
		if (!selectType){
			showBox('温馨提示','请选要添加的类型!','warning');
			return false;
		}
		
		$('#addvaluesForm')[0].reset();
		$('#lookupType1').val(selectType.lookupType);
		$('#a_v_enabledFlag').val("Y");
		initDlg('#addvalueswindow').dialog({title:"添加参数",buttons:[{
				text:'确定',
				iconCls:'icon-ok',
				handler:function(){
					doAddCode();
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){
					$('#addvalueswindow').dialog('close');
				}
			}]});
		$('#addvalueswindow').dialog('open');
	}
	//修改字典类型
	function showUpdateType(jsonParam) {		
		jsonParam=jsonParam||{};
		jsonParam.updateUrl = isEmpty(jsonParam.updateUrl)?updateUrl:jsonParam.updateUrl;
		jsonParam.title = isEmpty(jsonParam.title)?$('#updatewindow').attr('title'):jsonParam.title;
		delError();
		var rows = $('#dataList').datagrid("getSelections");
		if(rows.length==0){
			$.messager.alert('提示框','请选择更新数据','warning');  
			return;
		}
		var data = rows[0];
		//更新前处理
		if(isFunction(jsonParam.preHandler)) 
			jsonParam.preHandler(jsonParam);
		else
			setFormValue("updateForm",data);
		setReadonly(jsonParam.readonlyFields);
		
		initDlg('#updatewindow').dialog({title:jsonParam.title,buttons:[{
			text:'修改',
			iconCls:'icon-ok',
			handler:function(){
	            //确定按钮点击后的具体处理函数
				if( isFunction(jsonParam.updateHandler)){
					jsonParam.updateHandler(jsonParam);
				}else
					doEditType(jsonParam.updateUrl);
				
				
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$("#updateForm")[0].reset();
				$('#updatewindow').dialog('close');
			}
		}]});
		//新增后处理
		if(isFunction(jsonParam.afHandler)) jsonParam.afHandler(jsonParam);
		$('#updatewindow').dialog('open');
	
	}	
	//修改字典类型
	function doEditType(url){
			if(url)
			$('#updateForm').form.url =url; //表单提交路径
			submitForm("updateForm",$('#updateForm').form.url,function(data){
	  	       //eval('data='+data); 
	  	     	data = convertJson(data);
				if(data.result == "ok"){					
					$('#addDlg').dialog('close');
					showBox("提示信息","修改成功",'info');
					var pageNumber = $('#dataList').datagrid('getPager').data("pagination").options.pageNumber;
					loadList(pageNumber);
					$('#updatewindow').dialog('close');
				}else{
					//showBox("提示信息",data.result,'warning');
					showError(data);
			}
	  });
	}
	
	//修改字典值列表
	function showUpdateValues(jsonParam,windowName,formName) {		
		jsonParam=jsonParam||{};
		jsonParam.updateUrl = isEmpty(jsonParam.updateUrl)?updateUrl:jsonParam.updateUrl;
		jsonParam.title = isEmpty(jsonParam.title)?$('#'+windowName).attr('title'):jsonParam.title;
		delError();
		var rows = $('#dg_lookupvalueList').datagrid("getSelections");
		if(rows.length==0){
			$.messager.alert('提示框','请选择更新数据','warning');  
			return;
		}
		var data = rows[0];
		//更新前处理
		if(isFunction(jsonParam.preHandler)) 
			jsonParam.preHandler(jsonParam);
		else
			setFormValue(formName,data);
		setReadonly(jsonParam.readonlyFields);
		
		initDlg('#'+windowName).dialog({title:jsonParam.title,buttons:[{
			text:'修改',
			iconCls:'icon-ok',
			handler:function(){
	            //确定按钮点击后的具体处理函数
				if( isFunction(jsonParam.updateHandler)){
					jsonParam.updateHandler(jsonParam);
				}else
					doEditValues(jsonParam.updateUrl,windowName,formName);
				
				
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$("#"+formName)[0].reset();
				$('#'+windowName).dialog('close');
			}
		}]});
		//新增后处理
		if(isFunction(jsonParam.afHandler)) jsonParam.afHandler(jsonParam);
		$('#'+windowName).dialog('open');	
	
}
	
function doEditValues(url,windowName,formName){
	if(url)
		$('#'+formName).form.url =url; //表单提交路径
	submitForm(formName,$('#'+formName).form.url,function(data){
	  	       //eval('data='+data); 
	  	     	data = convertJson(data);
				if(data.result == "ok"){					
					$('#addDlg').dialog('close');
					showBox("提示信息","修改成功",'info');
					var pageNumber = $('#dg_lookupvalueList').datagrid('getPager').data("pagination").options.pageNumber;
					loadValueList(pageNumber);
					$('#'+windowName).dialog('close');
				}else{
					//showBox("提示信息",data.result,'warning');
					showError(data);
			}
	  });
}
	
	
	
/**
 * 
 * @param url 删除字典的url
 * @param id  删除的条件
 */
function delLookupData(jsonParam)
{
	jsonParam=jsonParam||{};
	jsonParam.url =isEmpty(jsonParam.url)?deleteUrl:jsonParam.url;
	var rows = $('#dg_lookupvalueList').datagrid("getSelections");
	
	if(rows.length == 0){
		$.messager.alert('提示框','请选择要删除的数据','warning');  
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
		Confirm('是否删'+rows.length+'条数据！',function(){
		
			var _param = convertJson('{\"'+jsonParam.id+'s\":\"'+ids+'\"}');
			
			_param = convertJson('{\"'+jsonParam.id+'s\":\"'+ids + '\",\"' +jsonParam.lookupType+'\":\"'+lookupType+'\"}');
		
		
			$.post(jsonParam.url, _param, function(data) {
				$.messager.alert('提示框','删除成功','info');
				var pageNumber = $('#dg_lookupvalueList').datagrid('getPager').data("pagination").options.pageNumber;
				loadValueList(pageNumber);				
			});
			
		});
	}
}
	
	
		   //添加自定义级别的下拉框
		   ajaxConstants('customizationLevel');
	});
			
		</script>
	</head>
	<body class="easyui-layout">
		<div region="center" class="wrapper1 wrapper-content1 gray-bg panel-fit">
			<div style="background-color: white;">
			<form id='searchForm' action="" method="post">
						字典类型:
						<input type="text" id="slookupType" name="lookupType" class="form-text" width="12px">
						自定义级别:
						<input type="text" id="scustomizationLevel"
							name="customizationLevel" class="form-text">
						创建人:
						<input type="text" id="screatedBy"  name="createdBy" class="form-text">
						<input type="button" onclick="loadList(1);" value="查询" class="commbtn">
			</form>
			<div id="dataList" style="height:300px;overflow:auto;"></div>
			</div>
			<div title="字典列表" style="height: 300px;overflow:auto;"">
				<div id="dg_lookupvalueList"></div>
			</div>

		</div>
		<%--   添加字典类型 --%>
		<div style="visibility:hidden" >
		<div id="addwindow"  title="添加字典类型" style="width:600px;height:350px;padding:10px">
			<form id='addForm' action="" method="post">
				<table>
					<tr>
						<td> 字典类型:</td>
						<td><input type="text" id="a_t_lookupType" name="lookupType" style="width:120px"></td>
						</tr>
						<tr>
						<td>自定义级别:</td>
						<td>						
						<select id='a_t_customizationLevel' name='customizationLevel' style="width:120px" >
								<option value='1' selected="selected">1</option>
								<option value='2'>2</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>描述:</td>										
						<td><input type="text" id="a_t_description" name="description" style="width:120px"></td>
					</tr>
						<tr  style="display: none">
						<td>创建人:</td>
						<td><input type="text" id="a_t_createdBy" name="createdBy" readonly="readonly" value="${LOGIN_USER.workId}"></td>
						</tr>
						<tr  style="display: none">
						<td>创建日期 :</td>
						<td>
							<input class="easyui-datebox" id="a_t_creationDate" name="creationDate"></input>
						</td>
						</tr>						
						
						<tr  style="display: none">
						<td>最后修改人 :</td>
						<td><input type="text" id="a_t_lastUpdateBy" name="lastUpdateBy" style="width:120px"></td>
						</tr>
						
						<tr  style="display: none">
						<td>最后修改日期 :</td>
						<td>
							<input class="easyui-datebox" id="a_t_lastUpdateDate" name="lastUpdateDate"></input>
						</td>
						</tr>
					
				</table>
			</form>
		</div>
	</div>
	<%--   修改字典类型 --%>
	
	<div style="visibility:hidden" >
		<div id="updatewindow"  title="修改字典类型" style="width:600px;height:350px;padding:10px">
			<form id='updateForm' action="" method="post">
				<table>
					<tr>
						<td> 字典类型:</td>
						<td><input type="text" id="u_t_lookupType" name="lookupType" readonly="readonly" style="width:120px"></td>
						</tr>
						<tr>
						<td>自定义级别:</td>
						<td>
						<input type="text" id="u_t_customizationLevel" name="customizationLevel" style="width:120px">
						
						</td>
					</tr>
					<tr>
						<td>描述:</td>										
						<td><input type="text" id="u_t_description" name="description" style="width:120px"></td>
					</tr>
						<tr style="display: none">
						<td>创建人:</td>
						<td><input type="text" id="u_t_createdBy" name="createdBy"  readonly="readonly" style="width:120px"></td>
						</tr>
						<tr  style="display: none">
						<td>创建日期 :</td>
						<td>
							<input class="easyui-datebox" id="u_t_creationDate" name="creationDate"></input>
						</td>
						</tr>	
						<tr  style="display: none">
						<td>最后修改人 :</td>
						<td><input type="text" id="u_t_lastUpdateBy" name="lastUpdateBy" style="width:120px"></td>
						</tr>
						
						<tr  style="display: none">
						<td>最后修改日期 :</td>
						<td>
							<input class="easyui-datebox" id="u_t_lastUpdateDate" name="lastUpdateDate"></input>
						</td>
						</tr>
					
				</table>
			</form>
		</div>
	</div>
	
<%--   添加字典值列表 --%>
		<div style="visibility: hidden">
			<div id="addvalueswindow" title="添加"
				style="width: 600px; height: 350px; padding: 10px">
				<form id='addvaluesForm' action="" method="post">
					<input id="lookupType1" type="hidden" name="lookupType" />
					<table>
						<tr>
							<td>
								字典编码:
							</td>
							<td>
								<input type="text" id="a_v_lookupCode" name="lookupCode"
									style="width: 120px">
							</td>
						</tr>
						<tr>
							<td>
								含义:
							</td>
							<td>
								<input type="text" id="a_v_meaning" name="meaning"
									style="width: 120px">
							</td>
						</tr>
						<tr>
							<td>
								备注:
							</td>
							<td>
								<input type="text" id="a_v_remark" name="remark"
									style="width: 120px">
							</td>
						</tr>
						<tr>
							<td>
								启用标志:
							</td>
							<td>
								<select id="a_v_enabledFlag" name="enabledFlag">
									<option value="Y" selected="selected">
										启用
									</option>
									<option value="N">
										禁用
									</option>
									
								</select>
							</td>
						</tr>
						<tr>
							<td>
								选中标志:
							</td>
							<td>
								<select id="a_v_selectFlag" name="selectFlag">
									<option value="Y">
										选中
									</option>
									<option value="N" selected="selected">
										未选
									</option>
								</select>
							</td>
						</tr>
						<tr  style="display: none">
							<td>
								创建人:
							</td>
							<td>
								<input type="text" id="a_v_createdBy" name="createdBy"
									style="width: 120px">
							</td>
						</tr>
						<tr style="display: none">
							<td>
								创建日期:
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
		
		
		<%--   修改字典值列表 --%>
		<div style="visibility: hidden">
			<div id="updatevalueswindow" title="修改字典值"
				style="width: 600px; height: 350px; padding: 10px">
				<form id='updatevaluesForm' action="" method="post">
					<input id="lookupType_u_v" type="hidden" name="lookupType" />
					<table>
						<tr>
							<td>
								字典编码:
							</td>
							<td>
								<input type="text" id="u_v_lookupCode" name="lookupCode" readonly="readonly" style="width: 120px">
							</td>
						</tr>
						<tr>
							<td>
								含义:
							</td>
							<td>
								<input type="text" id="u_v_meaning" name="meaning"
									style="width: 120px">
							</td>
						</tr>
						<tr>
							<td>
								备注:
							</td>
							<td>
								<input type="text" id="u_v_remark" name="remark"
									style="width: 120px">
							</td>
						</tr>
						<tr>
							<td>
								启用标志:
							</td>
							<td>
								<select id="u_v_enabledFlag" name="enabledFlag">
									<option value="N">
										禁用
									</option>
									<option value="Y" selected="selected">
										启用
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								选中标志:
							</td>
							<td>
								<select id="u_v_selectFlag" name="selectFlag">
									<option value="Y" selected="selected">
										选中
									</option>
									<option value="N">
										未选
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								创建人:
							</td>
							<td>
								<input type="text" id="u_v_createdBy" name="createdBy" readonly="readonly"
									style="width: 120px">
							</td>
						</tr>
						<tr>
							<td>
								最后修改人:
							</td>
							<td>
								<input type="text" id="u_v_lastUpdateBy" name="lastUpdateBy"
									style="width: 120px">
							</td>
						</tr>
						<tr>
							<td>
								最后修改日期:
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