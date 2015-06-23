<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>用户管理</title>
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
	        //title:'用户信息列表',  
	        //iconCls:'icon-edit',//图标  
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
	            {field:'workId',title:'用户员工号',width:100,align:'center'},   
	            {field:'userName',title:'用户名',width:100,align:'center'},   
	            {field:'userLoginName',title:'登录名',width:100,align:'center'},
	            {field:'userBankCode',title:'所属机构号',width:100,align:'center',formatter: function(value,row,index){ 
           		    return getConstantDisplay('tblnewBranchinfo',value); 
           		}},     
	            {field:'userDept',title:'所属部门',width:100,align:'center',formatter: function(value,row,index){ 
           		    return getConstantDisplay('tbldepartment',value); 
           		}},  
	            {field:'userLevel',title:'职位',width:100,align:'center'},   
	            {field:'groupId',title:'角色',width:100,align:'center',formatter: function(value,row,index){ 
           		    return getConstantDisplay('tblgroupinfo',value); 
           		}},
	            {field:'userStatus',title:'状态',width:100,align:'center',width:80},   
	            {field:'operatorCode',title:'操作员工',width:100,align:'center'},   
	            {field:'telephone',title:'联系电话',width:100,align:'center'},
	            {field:'userRemark',title:'备注',width:100,align:'center'}
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showAddwindow({title:'新增用户',preHandler:initLoginData})">新增</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showUpdate({title:'修改用户',readonlyFields:['workId']});">修改</a>|
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRowData({id:'workId'});">删除</a>|<a class="collapse-link" id="togglebtn">
                                        <i class="fa fa-chevron-up" id="up"></i>
                         </a>
		</div>
		<div id="searchdiv">
			<form  id='searchForm' action="" method="post">
				用户ID：
				<input type="text" id="sworkId" name="workId" class="form-text">
				用户名：
				<input type="text" id="suserName" name="userName" class="form-text">
				<input type="button" onclick="loadList(1);" value="查询" class="commbtn">
			</form>
		</div>
	</div>
		</div>
	</div>
	
	<div style="visibility:hidden" >
		<div id="addwindow"  title="添加用户" style="width:600px;height:360px;padding:10px">
			<form id='addForm' action="" method="post">
				<table>
					<tr>
						<td style="width: 100;font-size: 13px">员工号:</td>
						<td><input type="text" id="workId" name="workId" style="width:180px" class="form-text input-margin"></td>
						<td style="width: 100;font-size: 13px">姓名 :</td>
						<td><input type="text" id="userName" name="userName" style="width:180px" class="form-text input-margin"></td>
					</tr>
					<tr>
						<td style="width: 100;font-size: 13px">登录名:</td>
						<td><input type="text" id="userLoginName" name="userLoginName" style="width:180px" class="form-text input-margin"></td>
						<td style="width: 100;font-size: 13px">所属机构号 :</td>
						<td><select id="userBankCode" name="userBankCode" constantId="tblnewBranchinfo" style="width:180px"></select></td>
					
					</tr>
					<tr>
						<td style="width: 100;font-size: 13px">所属部门:</td>
						<td><select id="userDept" name="userDept" constantId="tbldepartment" style="width:180px"></select></td>
					
						<td style="width: 100;font-size: 13px">职位 :</td>
						<td><input type="text" id="userLevel" name="userLevel" style="width:180px" class="form-text input-margin"></td>
					</tr>
					<tr>
						<td style="width: 100;font-size: 13px">角色：</td>
						<td><select id="groupId" name="groupId" constantId="tblgroupinfo" style="width:180px"></select></td>
					
					</tr>
					<tr>
						<td style="width: 100;font-size: 13px">状态:</td>
						<td><input type="text" id="userStatus" name="userStatus" style="width:180px" class="form-text input-margin"></td>
						<td style="width: 100;font-size: 13px">操作员工 :</td>
						<td><input type="text" id="operatorCode" name="operatorCode" style="width:180px" class="form-text input-margin"></td>
					</tr>
					<tr>
						<td style="width: 100;font-size: 13px">联系电话:</td>
						<td><input type="text" id="telephone" name="telephone" style="width:180px" class="form-text input-margin"></td>
						<td style="width: 100;font-size: 13px">备注:</td>
						<td><input type="text" id="userRemark" name="userRemark" style="width:180px" class="form-text input-margin"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
  </body>
</html>
