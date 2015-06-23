<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>菜单管理</title>
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
	        //title:'菜单列表',  
	        iconCls:'icon-edit',//图标  
	        //width: 700,  
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
	            {field:'menuCode',title:'菜单项编号',width:100,align:'center'},   
	            {field:'menuParent',title:'父菜单编号',width:100,align:'center'},   
	            {field:'menuName',title:'菜单名称',width:100,align:'center'},
	            {field:'icon',title:'图标',width:100,align:'center'},   
	            {field:'menuUrl',title:'所指URL',width:250,align:'center',dataalign:'left'},   
	            {field:'menuModel',title:'所属模块',width:100,align:'center'},  
	            {field:'menuFlag',title:'菜单状态',width:100,align:'center',formatter: function(value,row,index){ 
           		    	return getConstantDisplay('cdzt',value); 
           		}},   
	             {field:'isMenu',title:'菜单标志',width:100,align:'center',formatter: function(value,row,index){ 
           		    	return getConstantDisplay('cdbz',value); 
           		}},
	            {field:'menuRemark',title:'备注',align:'center'}
	        ]]

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
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddwindow({title:'新增菜单'})">新增</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'修改菜单',readonlyFields:['menuCode']});">修改</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'menuCode'});">删除</a>|<a class="collapse-link" id="togglebtn">
                                        <i class="fa fa-chevron-up" id="up"></i>
                         </a>
		</div>
		<div id="searchdiv">
			<form  id='searchForm' action="" method="post">
				菜单编号：
				<input type="text" id="smenuCode" name="menuCode" class="form-text">
				菜单名称：
				<input type="text" id="smenuName" name="menuName" class="form-text">
				<input type="button" onclick="loadList(1);" value="查询" class="commbtn">
			</form>
		</div>
	</div>
		</div>
	</div>
	
	<div style="visibility:hidden" >
		<div id="addwindow"  title="添加菜单" style="width:600px;height:350px;padding:10px">
			<form id='addForm' action="" method="post">
				<table>
					<tr>
						<td style="font-size: 13px">菜单项编号:</td>
						<td><input type="text" id="menuCode" name="menuCode" style="width:180px" class="form-text input-margin"></td>
						<td style="font-size: 13px">父菜单编号 :</td>
						<td><input type="text" id="menuParent" name="menuParent" style="width:180px" class="form-text input-margin"></td>
					</tr>
					<tr>
						<td style="font-size: 13px">菜单名称:</td>
						<td><input type="text" id="menuName" name="menuName" style="width:180px" class="form-text input-margin"></td>
						<td style="font-size: 13px">图标 :</td>
						<td><input type="text" id="icon" name="icon" style="width:180px" class="form-text input-margin"></td>
					</tr>
					<tr>
						<td style="font-size: 13px">所指URL:</td>
						<td><input type="text" id="menuUrl" name="menuUrl" style="width:180px" class="form-text input-margin"></td>
						<td style="font-size: 13px">所属模块:</td>
						<td><input type="text" id="menuModel" name="menuModel" style="width:180px" class="form-text input-margin"></td>
					</tr>
					<tr>
						<td style="font-size: 13px">菜单状态:</td>
						<td><select id="menuFlag" name="menuFlag" constantId="cdzt" style="width:180px"></select></td>
					
						<td style="font-size: 13px">菜单标志 :</td>
						<td><select id="isMenu" name="isMenu" constantId="cdbz" style="width:180px"></select></td>
					
					</tr>
					<tr>
						<td style="font-size: 13px">备注:</td>
						<td><input type="text" id="menuRemark" name="menuRemark" style="width:180px" class="form-text input-margin"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
  </body>
</html>
