<link href="<%=request.getContextPath() %>/css/default.css" rel="stylesheet" type="text/css" charset="GBK"/>
<link href="<%=request.getContextPath() %>/css/commStyle.css" rel="stylesheet" type="text/css" charset="GBK"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/jquery-easyui-1.3.6/themes/bootstrap/easyui.css"  charset="GBK"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/jquery-easyui-1.3.6/themes/icon.css" charset="GBK"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/json2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-easyui-1.3.6/jquery-1.8.0.min.js" charset="GBK"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-easyui-1.3.6/jquery.easyui.min.js" charset="GBK"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js" charset="GBK"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/comm.js" charset="GBK"></script>
<script type="text/javascript" charset="GBK">
	var path = "<%=request.getContextPath()%>";
	function initLoginData(){
		$("#operatorCode").val("${sessionScope.WorkId}");
		$("#subbranchCode").val("${sessionScope.SubbranchCode}");
		$("#branchCode").val("${sessionScope.BranchCode}");
	}
</script>
