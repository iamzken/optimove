<link href="<%=request.getContextPath() %>/css/default.css" rel="stylesheet" type="text/css" charset="GBK"/>
<link href="<%=request.getContextPath() %>/css/commStyle.css" rel="stylesheet" type="text/css" charset="GBK"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/jquery-easyui-1.3.6/themes/gray/easyui.css"  charset="GBK"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/jquery-easyui-1.3.6/themes/icon.css" charset="GBK"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/zTreeStyle/zTreeStyle.css" type="text/css">

<link href="<%=request.getContextPath()%>/css/bootstrap.min.css?v=1.7" type="text/css"  rel="stylesheet" charset="GBK">
<link href="<%=request.getContextPath()%>/font-awesome/css/font-awesome.css?v=1.7" type="text/css"  rel="stylesheet" charset="GBK">
<link href="<%=request.getContextPath()%>/css/plugins/iCheck/custom.css" type="text/css"  rel="stylesheet" charset="GBK">
<link href="<%=request.getContextPath()%>/css/animate.css" type="text/css" rel="stylesheet" charset="GBK">
<link href="<%=request.getContextPath()%>/css/hplus_style.css?v=1.7" type="text/css"  rel="stylesheet" charset="GBK">
<link href="<%=request.getContextPath()%>/css/commonbtn.css" type="text/css"  rel="stylesheet" charset="GBK">

<script type="text/javascript" src="<%=request.getContextPath() %>/js/json2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/zTree/jquery-1.8.3.min.js" charset="GBK"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-easyui-1.3.6/jquery.easyui.min.js" charset="GBK"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js" charset="GBK"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-easyui-1.3.6/datagrid-detailview.js" charset="GBK"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/zTree/jquery.ztree.all-3.5.js" charset="GBK"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/zTree/jquery.ztree.core-3.5.js" charset="GBK"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/zTree/jquery.ztree.exedit-3.5.min.js" charset="GBK"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/zTree/jquery.ztree.exhide-3.5.js" charset="GBK"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/zTree/jquery.ztree.excheck-3.5.min.js" charset="GBK"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/comm.js" charset="GBK"></script>

<script type="text/javascript" charset="GBK">
	var path = "<%=request.getContextPath()%>";
	function initLoginData(){
		$("#operatorCode").val("${sessionScope.WorkId}");
		$("#subbranchCode").val("${sessionScope.SubbranchCode}");
		$("#branchCode").val("${sessionScope.BranchCode}");
		$("#operatorCode").attr("readonly",true);
		$("#subbranchCode").attr("readonly",true);
		$("#branchCode").attr("readonly",true);
	}
</script>
