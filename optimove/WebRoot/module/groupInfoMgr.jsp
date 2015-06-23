<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>角色管理</title>
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
	        //title:'列表',  
	        //iconCls:'icon-edit',//图标  
	        width: 800,  
	        height: 'auto',  
	        nowrap: false,  
	        striped: true,  
	        border: false,  
	        collapsible:false,//是否可折叠的  
	        fit: true,//自动大小  
	        url:'#',  
	        remoteSort:false,   
	        singleSelect:false,//是否单选  
	        pagination:true,//分页控件  
	        rownumbers:true,//行号  
	        frozenColumns:[[  
	            {field:'ck',checkbox:true}  
	        ]],  
	        url:searchUrl, 
	        toolbar:'#tb',
	        columns:[[   
                   		{field:'grpId',title:'角色ID',width:180,align:'center'},
                   		{field:'grpCname',title:'角色中文名',width:200,align:'center'},
                   		{field:'grpEname',title:'角色英文名',width:180,align:'center'},
                   		{field:'grpLevel',title:'角色级别',width:170,align:'center',formatter: function(value,row,index){ 
                   		    return getConstantDisplay('Grade',value); 
                   		}},
                   		//{field:'cretaedBy',title:'创建人',width:100,align:'center'},
                   		//{field:'creationDate',title:'创建日期',width:100,align:'center'},
                   		//{field:'lastUpdatedBy',title:'最后修改人',width:100,align:'center'},
                   		//{field:'lastUpdateDate',title:'最后修改日期',width:100,align:'center'},
                   		{field:'grpRemark',title:'备注',width:200,align:'center'}
	        ]],
	        onLoadSuccess:function(data){
	    		data = convertJson(data);
	        	if(data.result!='ok'){
	        		showBox("提示信息",data.errorMsg,'warning');
	        	}
	        },
	        onDblClickRow:function(rowIndex, rowData){
	    		window.location.href='<%=request.getContextPath()%>/module/permissionTree.jsp?grpId='+rowData.grpId;
	        }
	        
	    });  
	
	    //设置分页控件  
	    var p = $('#dataList').datagrid('getPager');  
	    $(p).pagination({  
	        pageSize: 10,//每页显示的记录条数，默认为10  
	        pageList: [10,20,30],//可以设置每页记录条数的列表  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddwindow({title:'新增'})">新增</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'修改',readonlyFields:['grpId']});">修改</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'grpId'});">删除</a>|<a class="collapse-link" id="togglebtn">
                                        <i class="fa fa-chevron-up" id="up"></i>
                         </a>
		</div>
		<div id="searchdiv">
			<form  id='searchForm' action="" method="post">
				角色ID:
				<input type="text" id="sgrpId" name="grpId" class="form-text">
				角色中文名:
				<input type="text" id="sgrpCname" name="grpCname" class="form-text">
				角色英文名:
				<input type="text" id="sgrpEname" name="grpEname" class="form-text">
				<input type="button" onclick="loadList(1);" value="查询" class="commbtn">
			</form>
		</div>
	</div>
		</div>
	</div>
	
	<div style="visibility:hidden" >
		<div id="addwindow"  title="添加" style="width:600px;height:350px;padding:10px">
			<form id='addForm' action="" method="post">
				<table>
						<tr>
							<td style="font-size: 13px">角色ID:</td>
							<td><input type="text" id="grpId" name="grpId" style="width:180px" class="form-text input-margin"></td>
							<td style="font-size: 13px">角色中文名:</td>
							<td><input type="text" id="grpCname" name="grpCname" style="width:180px" class="form-text input-margin"></td>
						</tr>
						<tr>
							<td style="font-size: 13px">角色英文名:</td>
							<td><input type="text" id="grpEname" name="grpEname" style="width:180px" class="form-text input-margin"></td>
							<td style="font-size: 13px">角色级别:</td>
							<td>
							<select id="grpLevel" name="grpLevel" constantId="Grade" style="width:180px" class="clientInput"></select>
							</td>
						</tr>
						<tr style="display: none">
							<td style="font-size: 13px">创建人:</td>
							<td><input type="text" id="cretaedBy" name="cretaedBy" style="width:180px" class="form-text input-margin"></td>
						</tr>
						<tr style="display: none">
							<td>创建日期:</td>
							<td><input type="text" class="easyui-datebox"  id="creationDate" name="creationDate" style="width:180px"></td>
						</tr>
						<tr style="display: none">
							<td>最后修改人:</td>
							<td><input type="text" id="lastUpdatedBy" name="lastUpdatedBy" style="width:180px" class="form-text input-margin"></td>
						</tr>
						<tr style="display: none">
							<td>最后修改日期:</td>
							<td><input type="text" id="lastUpdateDate" name="lastUpdateDate" style="width:180px" class="easyui-datebox"></td>
						</tr>
						<tr style="font-size: 13px">
							<td>备注:</td>
							<td><input type="text" id="grpRemark" name="grpRemark" style="width:180px" class="form-text input-margin"></td>
						</tr>
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
