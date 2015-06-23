<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/module/common/comm.jsp"></jsp:include>
<html>
   
  <head>
    <base href="<%=basePath%>">
    
    <title>角色权限配置</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript"  charset="GBK">
	$(function(){
		$('#pTree').tree({
	         checkbox:true,
	         url:'<%=request.getContextPath()%>/menu/permissionTree.do?grpId=<%=request.getParameter("grpId")%>',
	         onLoadSuccess:function(node, data){
	        	 data = convertJson(data);
	        	 $("#ff").show();
		         if(!isEmpty(data.result)&&data.result!='ok')
	        	 	showBox("提示信息",data.errorMsg,'warning');
	         }
	     });
	})
	 
	function goBack(){
		window.location.href='<%=request.getContextPath()%>/module/groupInfoMgr.jsp';
	}

	function goCommit(){
		var nodes = $('#pTree').tree('getChecked','indeterminate');
		var checkNodes = $('#pTree').tree('getChecked');
		var array = nodes.concat(checkNodes);
		if(array.length==0){
			$.messager.alert("提示信息","请选择菜单!",'warning');
			return false;
		}
		$(array).attr('grpId','<%=request.getParameter("grpId")%>');
		var result = new Array();
		$(array).each(function(index,row){
			getPermissionTree(result,row);
		});
		array = JSON.stringify(result);
		//array = $.toJSON(array);
		wait('正在保存....');
		$.post("<%=request.getContextPath()%>/menu/permissionTree/save.do", {treeData:array}, function(data) {
	         data = convertJson(data);
	         closeWait();
	       	 if(data.result=='ok'){
	       		//$('#pTree').tree().reload();
	       		showBox("提示信息","保存成功",'info');
	       	 }else{
	       		 showBox("提示信息",data.errorMsg,'warning');
	         }
         });

		
	}


	function getPermissionTree(result,obj){
		result.push({grpId:obj.grpId,id:obj.id});
		/**var children = obj.children;
		if(!isEmpty(children)){
			$(children).each(function(index,row){
				getPermissionTree(result,row);
			});
		}**/
		
	}
		 
	</script>

  </head>
  
  <body class="easyui-layout" >
    <div  region="center" >
    	<div id="pTree">
    	
    	</div>
    	<form id="ff" action="" style="display: none">
    		<input type="button" value="保存" id="save" onclick='goCommit()'/>
    		<input type="button" value="返回" id="fh" onclick='goBack()'/>
    	</form>
    </div>
  </body>
</html>
