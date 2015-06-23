<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>分支行信息管理</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript">
   ajaxConstants("Grade");
   var searchUrl = "<%=request.getContextPath()%>/newBranchInfos/searchNewBranchInfo.do";
   var updateUrl = "<%=request.getContextPath()%>/newBranchInfos/update.do";
   var insertUrl = "<%=request.getContextPath()%>/newBranchInfos/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/newBranchInfos/delete.do";
	$(function() {
	    $('#dataList').datagrid({  
	        //title:'列表',  
	        iconCls:'icon-edit',//图标  
	        //width: 700,  
	        height: 'auto',  
	        nowrap: false,  
	        striped: true,  
	        border: true,  
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
                   		{field:'branchCode',title:'分支行代码',width:100,align:'center'},
                   		{field:'grade',title:'机构级别',width:100,align:'center',formatter: function(value,row,index){ 
                   		    return getConstantDisplay('Grade',value); 
                   		}},
                   		{field:'upCode',title:'上级行代码',width:100,align:'center'},
                   		{field:'name',title:'名称',width:150,align:'center'},
                   		{field:'address',title:'地址',width:120,align:'center'},
                   		{field:'zipcode',title:'邮编',width:100,align:'center'},
                   		{field:'telephone',title:'电话号码',width:100,align:'center'},
                   		{field:'fax',title:'传真',width:100,align:'center'},
                   		//{field:'status',title:'经营状态',width:100,align:'center'},
                   		//{field:'account',title:'分行手续费清算账号',width:100,align:'center'},
                   		//{field:'linkManName',title:'联系人姓名',width:100,align:'center'},
                   		//{field:'linkManDept',title:'部门',width:100,align:'center'},
                   		//{field:'linkManPos',title:'职务',width:100,align:'center'},
                   		//{field:'linkManTel',title:'联系人电话',width:100,align:'center'},
                   		//{field:'linkManFax',title:'联系人传真',width:100,align:'center'},
                   		//{field:'linkManEmail',title:'联系人Email',width:100,align:'center'},
                   		{field:'createDate',title:'更新日期',width:100,align:'center'},
                   		{field:'operatorCode',title:'操作员工',width:100,align:'center'}
	        ]],
	        onLoadSuccess:function(data){
	    		data = convertJson(data);
	        	if(data.result!='ok'){
	        		showBox("提示信息",data.msg,'warning');
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
	    paginationConfig('dataList');
	});
	</script>
  </head>
  
  <body class="easyui-layout" >
	<div  region="center" >
		<div id='dataList'>
			<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddwindow({title:'新增'})">新增</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'修改',readonlyFields:['branchCode']});">修改</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'branchCode'});">删除</a>
		</div>
		<div>
			<form  id='searchForm' action="" method="post">
			<table>
				<tr>
					<td>分支行代码:</td>
					<td><input type="text" id="sbranchCode" name="branchCode"></td>
					<td>机构级别:</td>
					<td><select id="sgrade" name="grade" constantId="Grade" style="width: 80px;"></select></td>
					<!--  
					<td>上级行代码:</td>
					<td><input type="text" id="supCode" name="upCode"></td>
					-->
					<td>名称:</td>
					<td><input type="text" id="sname" name="name"></td>
					<td><a href="#" class="easyui-linkbutton" onclick="javascript:loadList(1);" iconCls="icon-search">查&nbsp;&nbsp;&nbsp;询</a></td>
				</tr>
			</table>
				<%-- 
				地址:
				<input type="text" id="saddress" name="address">
				邮编:
				<input type="text" id="szipcode" name="zipcode">
				电话号码:
				<input type="text" id="stelephone" name="telephone">
				传真:
				<input type="text" id="sfax" name="fax">
				经营状态:
				<input type="text" id="sstatus" name="status">
				分行手续费清算账号:
				<input type="text" id="saccount" name="account">
				联系人姓名:
				<input type="text" id="slinkManName" name="linkManName">
				部门:
				<input type="text" id="slinkManDept" name="linkManDept">
				职务:
				<input type="text" id="slinkManPos" name="linkManPos">
				联系人电话:
				<input type="text" id="slinkManTel" name="linkManTel">
				联系人传真:
				<input type="text" id="slinkManFax" name="linkManFax">
				联系人Email:
				<input type="text" id="slinkManEmail" name="linkManEmail">
				更新日期:
				<input type="text" id="screateDate" name="createDate">
				操作员工:
				<input type="text" id="soperatorCode" name="operatorCode">
				--%>
				
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
							<td>分支行代码:</td>
							<td><input type="text" id="branchCode" name="branchCode" style="width:120px"></td>
							<td>机构级别:</td>
							<td><select id="grade" name="grade" constantId="Grade" style="width:120px"></select></td>
						</tr>
						<tr>
							<td>上级行代码:</td>
							<td><input type="text" id="upCode" name="upCode" style="width:120px"></td>
							<td>名称:</td>
							<td><input type="text" id="name" name="name" style="width:120px"></td>
						</tr>
						<tr>
							<td>地址:</td>
							<td><input type="text" id="address" name="address" style="width:120px"></td>
							<td>邮编:</td>
							<td><input type="text" id="zipcode" name="zipcode" style="width:120px"></td>
						</tr>
						<tr>
							<td>电话号码:</td>
							<td><input type="text" id="telephone" name="telephone" style="width:120px"></td>
							<td>传真:</td>
							<td><input type="text" id="fax" name="fax" style="width:120px"></td>
						</tr>
						<tr>
							<td>经营状态:</td>
							<td><input type="text" id="status" name="status" style="width:120px"></td>
							<td>分行手续费清算账号:</td>
							<td><input type="text" id="account" name="account" style="width:120px"></td>
						</tr>
						<tr>
							<td>职务:</td>
							<td><input type="text" id="linkManPos" name="linkManPos" style="width:120px"></td>
							<td>部门:</td>
							<td><input type="text" id="linkManDept" name="linkManDept" style="width:120px"></td>
						</tr>
						<tr>
							
							<td>联系人姓名:</td>
							<td><input type="text" id="linkManName" name="linkManName" style="width:120px"></td>
							<td>联系人电话:</td>
							<td><input type="text" id="linkManTel" name="linkManTel" style="width:120px"></td>
						</tr>
						<tr>
							<td>联系人传真:</td>
							<td><input type="text" id="linkManFax" name="linkManFax" style="width:120px"></td>
							<td>联系人Email:</td>
							<td><input type="text" id="linkManEmail" name="linkManEmail" style="width:120px"></td>
						</tr>
						<tr style="display: none">
							<td>更新日期:</td>
							<td><input type="text" id="createDate" name="createDate" style="width:120px"></td>
							<td>操作员工:</td>
							<td><input type="text" id="operatorCode" name="operatorCode" style="width:120px"></td>
						</tr>
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
