<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>管理</title>
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
	        //title:'列表',  
	        //iconCls:'icon-edit',//图标  
	        width: 700,  
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
                   		{field:'mapmodelid',title:'模板编号',width:150,align:'center'},
                   		{field:'mapmodeltype',title:'模板类型',width:150,align:'center',formatter: function(value,row,index){ 
           		         return getConstantDisplay('mblx',value); 
           				}},   
                   		{field:'mapmodelname',title:'模板名称',width:200,align:'center'},
                   		{field:'mapmodeldes',title:'模板描述',width:200,align:'center'},
                   		{field:'attributedes',title:'参数描述',width:150,align:'center'},
                   		{field:'datades',title:'数据描述',width:164,align:'center'}
                   		//{field:'htmlmodel',title:'HTML模板',hidden:true,width:120,align:'center'}
	        ]],
	        onLoadSuccess:function(data){
	    		data = convertJson(data);
	        	if(data.result!='ok'){
	        		showBox("提示信息",data.errorMsg,'warning');
	        	}
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
	});
	</script>
  </head>
  
  <body class="easyui-layout" >
	<div  class="wrapper1 wrapper-content1 gray-bg panel-fit" region="center">
		<div id='dataList'>
			<div id="tb" style="padding:5px;height:auto;background-color: white;">
		<div style="margin-bottom:5px;text-align: right;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddwindow({title:'新增'})">&nbsp;新增&nbsp;</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'修改',readonlyFields:['mapmodelid']});">&nbsp;修改&nbsp;</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'mapmodelid'});">&nbsp;删除&nbsp;</a>|<a class="collapse-link" id="togglebtn">
                                        <i class="fa fa-chevron-up" id="up"></i>
                         </a>
		</div>
		<div id="searchdiv">
			<form  id='searchForm' action="" method="post">
				模板类型:
				<select id="mapmodeltype" name="mapmodeltype" constantId="mblx" style="width:180px"></select>
				模板名称:
				<input type="text" id="mapmodelname" name="mapmodelname" class="form-text"/>
				<input type="button" onclick="loadList(1);" value="查询" class="commbtn"/>
			</form>
		</div>
	</div>
		</div>
	</div>
	
	<div style="visibility:hidden" >
		<div id="addwindow"  title="添加" style="width:600px;height:450px;padding:10px">
			<form id='addForm' action="" method="post">
				<table>
						<tr>
							<td style="font-size: 13px">模板编号:</td>
							<td><input type="text" id="mapmodelid" name="mapmodelid" style="width:180px" class="form-text input-margin"/></td>
							<td style="font-size: 13px">模板类型:</td>
							<td><select id="mapmodeltype" name="mapmodeltype" constantId="mblx" style="width:180px"></select></td>
					
						</tr>
						<tr>
							<td style="font-size: 13px">模板名称:</td>
							<td><input type="text" id="mapmodelname" name="mapmodelname" style="width:180px" class="form-text input-margin"/></td>
							<td style="font-size: 13px">模板描述:</td>
							<td><input type="text" id="mapmodeldes" name="mapmodeldes" style="width:180px" class="form-text input-margin"/></td>
						</tr>
						<tr>
							<td style="font-size: 13px">参数描述:</td>
							<td><input type="text" id="attributedes" name="attributedes" style="width:180px" class="form-text input-margin"/></td>
							<td style="font-size: 13px">数据描述:</td>
							<td><input type="text" id="datades" name="datades" style="width:180px" class="form-text input-margin"/></td>
						</tr>
						<tr>
							<td style="font-size: 13px">HTML模板:</td>
							<td colspan="3"><textarea type="text" id="htmlmodel" name="htmlmodel" style="width:450px" value=" "></textarea></td>
						</tr>
				</table>
			</form>
		</div>
	</div>
  </body>
</html>
