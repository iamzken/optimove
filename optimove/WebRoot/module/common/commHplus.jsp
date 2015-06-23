<link href="<%=request.getContextPath() %>/css/bootstrap.min.css?v=1.7" rel="stylesheet">
<link href="font-awesome/css/font-awesome.css?v=1.7" rel="stylesheet">

<!-- Morris -->
<link href="<%=request.getContextPath() %>/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

<!-- Gritter -->
<link href="js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

<link href="<%=request.getContextPath() %>/css/plugins/summernote/summernote.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">

<link href="<%=request.getContextPath() %>/css/animate.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/style.css?v=1.7" rel="stylesheet">

<!-- Mainly scripts -->
<script src="<%=request.getContextPath() %>/js/jquery-2.1.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js?v=1.7"></script>
<script src="<%=request.getContextPath() %>/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="<%=request.getContextPath() %>/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Flot -->
<script src="<%=request.getContextPath() %>/js/plugins/flot/jquery.flot.js"></script>
<script src="<%=request.getContextPath() %>/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="<%=request.getContextPath() %>/js/plugins/flot/jquery.flot.spline.js"></script>
<script src="<%=request.getContextPath() %>/js/plugins/flot/jquery.flot.resize.js"></script>
<script src="<%=request.getContextPath() %>/js/plugins/flot/jquery.flot.pie.js"></script>

<!-- GITTER -->
<script src="js/plugins/gritter/jquery.gritter.min.js"></script>

<!-- EayPIE -->
<script src="js/plugins/easypiechart/jquery.easypiechart.js"></script>

<!-- Custom and plugin javascript -->
<script src="<%=request.getContextPath() %>/js/hplus.js?v=1.7"></script>
<script src="<%=request.getContextPath() %>/js/plugins/pace/pace.min.js"></script>

<!-- jQuery UI -->
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.min.js"></script>


<link href="<%=request.getContextPath() %>/css/commStyle.css" rel="stylesheet" type="text/css" charset="GBK"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/jquery-easyui-1.3.6/themes/bootstrap/easyui.css"  charset="GBK"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/jquery-easyui-1.3.6/themes/icon.css" charset="GBK"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/json2.js"></script>
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
