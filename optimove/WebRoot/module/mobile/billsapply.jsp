<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<html>
  <head>
    <title>管理</title>
   <jsp:include page="/module/common/comm.jsp"></jsp:include>
   <script type="text/javascript"  charset="GBK">
   var searchUrl = "<%=request.getContextPath()%>/billsapplys/searchBillsapply.do";
   var updateUrl = "<%=request.getContextPath()%>/billsapplys/update.do";
   var insertUrl = "<%=request.getContextPath()%>/billsapplys/insert.do";
   var deleteUrl = "<%=request.getContextPath()%>/billsapplys/delete.do";
	$(function() {
	    $('#dataList').datagrid({  
	        title:'列表',  
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
                   		{field:'custno',title:'客户号',width:100,align:'center'},
                   		{field:'businessid',title:'业务流水号',width:100,align:'center'},
                   		{field:'businesstype',title:'业务类型',width:100,align:'center'},
                   		{field:'productcode',title:'产品代码',width:100,align:'center'},
                   		{field:'tableid',title:'表单ID',width:100,align:'center'},
                   		{field:'transchannel',title:'交易渠道',width:100,align:'center'},
                   		{field:'fkrqc',title:'付款人全称',width:100,align:'center'},
                   		{field:'fkrzh',title:'付款人账号',width:100,align:'center'},
                   		{field:'fkryh',title:'付款人开户银行',width:100,align:'center'},
                   		{field:'skrqc',title:'收款人全称',width:100,align:'center'},
                   		{field:'skrzh',title:'收款人账号',width:100,align:'center'},
                   		{field:'skryh',title:'收款人开户银行',width:100,align:'center'},
                   		{field:'cpje',title:'出票金额',width:100,align:'center'},
                   		{field:'dqr',title:'汇票到期日',width:100,align:'center'},
                   		{field:'jyhthm',title:'交易合同号码',width:100,align:'center'},
                   		{field:'fkrkhhhh',title:'付款人开户行行号',width:100,align:'center'},
                   		{field:'fkrkhhdz',title:'付款人开户行地址',width:100,align:'center'},
                   		{field:'operator',title:'操作员',width:100,align:'center'},
                   		{field:'operatororg',title:'操作机构',width:100,align:'center'},
                   		{field:'updatedate',title:'操作日期',width:100,align:'center'},
                   		{field:'updatetime',title:'操作时间',width:100,align:'center'}
 
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
	<div  region="center" >
		<div id='dataList'>
			<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
				</div>
		<div>
			<form  id='searchForm' action="" method="post">
				客户号:
				<input type="text" id="custno" name="custno"/>
				
				付款人:
				<input type="text" id="fkrqc" name="fkrqc"/>
			
				收款人:
				<input type="text" id="skrqc" name="skrqc"/>
				
				<input type="button" onclick="loadList(1);" value="查询"/>
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
							<td>:</td>
							<td><input type="text" id="custno" name="custno" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="businessid" name="businessid" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="businesstype" name="businesstype" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="productcode" name="productcode" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="tableid" name="tableid" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="transchannel" name="transchannel" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="fkrqc" name="fkrqc" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="fkrzh" name="fkrzh" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="fkryh" name="fkryh" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="skrqc" name="skrqc" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="skrzh" name="skrzh" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="skryh" name="skryh" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="cpje" name="cpje" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="dqr" name="dqr" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="jyhthm" name="jyhthm" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="fkrkhhhh" name="fkrkhhhh" style="width:120px"/></td>
						</tr>
						<tr>
							<td>:</td>
							<td><input type="text" id="fkrkhhdz" name="fkrkhhdz" style="width:120px"/></td>
						</tr>
						
				</table>
			</form>
		</div>
	</div>
	
  </body>
</html>
