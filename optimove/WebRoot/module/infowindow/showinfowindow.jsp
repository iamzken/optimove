<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<title>信息窗口库</title>
		<jsp:include page="/module/common/comm.jsp"></jsp:include>
		<script src="<%=request.getContextPath()%>/js/plugins/treeview/bootstrap-treeview.js"></script>
		 <link href="<%=request.getContextPath()%>/css/plugins/treeview/bootstrap-treeview.css" rel="stylesheet">
		  <style type="text/css">
		 	.container1 {
    					padding-right: 0px;
						}
		 </style>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js?v=1.7"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <!-- Custom and plugin javascript -->
    <script src="<%=request.getContextPath()%>/js/hplus.js?v=1.7"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/pace/pace.min.js"></script>
		<script type="text/javascript">
ajaxConstants("tblMapModelMag|mapModelId|mapModelName|attributedes|orderBy=mapModelId");
</script>
		<script type="text/javascript">
var searchUrl = "<%=request.getContextPath()%>/mapmodelmags/getMapModelJson.do?modeltype=1";
var getMapModelTreeUrl = "<%=request.getContextPath()%>/mapmodelmags/getMapModelTree.do?modeltype=1";
var getMapModelUrl = "<%=request.getContextPath()%>/mapmodelmags/getMapmodelmagMap.do?modeltype=1";
var treeData = "";
var bdTreeData = "";
var replacestr="";
$(function() {
	//动态菜单数据
	$.ajax( {
		url : getMapModelTreeUrl,
		dataType : "json",
		success : function(data) {
			$.each(data, function(key, value) {
				$('#baiduTree').treeview({
			        color: "#428bca",
			        expandIcon: 'glyphicon glyphicon-chevron-right',
			        collapseIcon: 'glyphicon glyphicon-chevron-down',
			        nodeIcon: 'glyphicon glyphicon-bookmark',
			        onhoverColor: "orange",
			        data:eval(value),
			        onNodeSelected: function (event, node) {
			        	$("#bdIntroduction").empty();
						$("#bdIntroduction").text(node.text);
						var obj = eval(node.attributes);
						$("#bdArgDesc").val(obj.des);
						replacestr="";
						//$("#bdDataDesc").val(obj.des);
						showhtml(obj.modelId);
			        }
			    });
			})

		}
	});
	function showhtml(modelId) {
		$.ajax( {
			url : getMapModelUrl,
			data : {
				mapmodelid : modelId
			},
			dataType : "json",
			success : function(data) {
				$.each(data, function(key, value) {
					if (key == 'model') {
						$("#bdDataDesc").val("");
						$("#bdJs").text("");
						//$("#bdJs").val("");
						$("#bdHtmlMap").text(value);
						$("#bdHtml").html($("#bdHtmlMap").val());
					}
				})
			}
		});

	}
	function Open(text, url) {
		if ($("#tabs").tabs('exists', text)) {
			$('#tabs').tabs('select', text);
		} else {
			$('#tabs').tabs('add', {
				title : text,
				closable : true,
				content : text
			});
		}
	}
});

function run(flag) {
	if (flag == "baidu") {
		var attributedes = $("#bdArgDesc").val();
		var htmlval = $("#bdHtmlMap").val();
		$("#bdHtml").html(htmlval);
	}
}
function bdCreateHtmlJs() {
	$("#bdJs").empty();
	var bddata = $("#bdDataDesc").val().trim();
	if(replacestr==""){
		replacestr = "function newMapPoint(){return new BMap.Point(116.328852,40.057031);}";
	}
	$("#bdJs").text("function newMapPoint(){return new BMap.Point(" + bddata+ ");}");
	var htmltext = $("#bdHtmlMap").val();
	var result = htmltext.replace(replacestr,$("#bdJs").text());
	replacestr=$("#bdJs").val();
	$("#bdHtmlMap").text(result);
	$("#bdHtml").html(result);
}
</script>
	</head>

	<body class="easyui-layout">
			<div class="row wrapper1 wrapper-content1 animated fadeInRight gray-bg">
                <div class="col-xs-3 container1">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>信息窗口列表</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                <a class="dropdown-toggle" data-toggle="dropdown" href="buttons.html#">
                                    <i class="fa fa-wrench"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-user">
                                    <li><a href="buttons.html#">选项1</a>
                                    </li>
                                    <li><a href="buttons.html#">选项2</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div id="treeview1" class="test"><ul id="baiduTree" class="easyui-tree"
							style="width: 100%; height: 100%;"></ul></div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-4 container1">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>源代码</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                <a class="dropdown-toggle" data-toggle="dropdown" href="buttons.html#">
                                    <i class="fa fa-wrench"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-user">
                                    <li><a href="buttons.html#">选项1</a>
                                    </li>
                                    <li><a href="buttons.html#">选项2</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="ibox-content">
                            	<div class="bs-example bs-example-tabs" role="tabpanel"
						data-example-id="togglable-tabs">
						<ul id="myTab" class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a
								href="#bdIntroduction" id="bdIntroduction-tab" role="tab"
								data-toggle="tab" aria-controls="bdIntroduction"
								aria-expanded="true">介绍</a></li>
							<li role="presentation"><a href="#bdInvoke" role="tab"
								id="bdInvoke-tab" data-toggle="tab" aria-controls="bdInvoke">调用</a></li>
							<li role="presentation"><a href="#dropdown1" role="tab"
								id="dropdown1-tab" data-toggle="tab" aria-controls="dropdown1">源代码</a></li>
						</ul>
						<div id="myTabContent" class="tab-content">
							<div role="tabpanel" class="tab-pane fade in active"
								id="bdIntroduction" aria-labelledBy="bdIntroduction-tab" style="margin-top: 10px;">
								点击左侧菜单显示介绍</div>
							<div role="tabpanel" class="tab-pane fade" id="bdInvoke"
								aria-labelledBy="bdInvoke-tab">
								<div style="border: 1px gray; margin-top: 2px;display: none">
									<div style="margin: 1px;">
										参数描述 :&nbsp;<input id="bdArgDesc" type="text" value="" class="form-text"/>
									</div>
								</div>
								<div style="border: 1px gray; margin-top: 40px;">
									<div style="margin: 2px;" id="dataDiv" align="left">
									</div>
								</div>
								<div style="border: 1px gray; margin-top: 22px;">
									<div style="margin: 1px;">
										Javascript代码
										<pre id="bdJs" style="overflow: auto; width: 100%;height: 200px;"
											 onchange="bdChangeJs()">
										</pre>
									</div>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane fade" id="dropdown1"
								aria-labelledBy="dropdown1-tab">
								<textarea id="bdHtmlMap" rows="21"
									style="width: 300px; height: 400px;background-color:#F5F5F5;border-radius:4px;"><%@ include
										file="../maps/baiduMap.html"%></textarea>
								<div
									style="width: 100%; text-align: right; border-bottom: 1px gray;">
									<input type="button" value="运行" style="margin-top: 10px;margin-right: 10px"
										onclick="run('baidu')" class="btn btn-primary" />
								</div>

							</div>
						</div>
					</div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-5 container1">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>地图</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                <a class="dropdown-toggle" data-toggle="dropdown" href="buttons.html#">
                                    <i class="fa fa-wrench"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-user">
                                    <li><a href="buttons.html#">选项1</a>
                                    </li>
                                    <li><a href="buttons.html#">选项2</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="ibox-content">
                        <div id="treeview2" class="test">
                            <div id="bdHtml" style="width: 100%; height: 100%; float: right;">
								<%@ include file="../maps/baiduMap.html"%>
							</div>
							<div>
							&nbsp;
							</div>
						</div>
                        </div>
                    </div>
                </div>
	</div>
		<%-- <div id="mainPanle" region="center"
			style="background: #eee; overflow-y: hidden">
			<div id="tabs" class="" fit="true" border="false"
				cache="false">
				<div title="信息窗口库" id='baiduMap'
					style="padding: 1px; overflow: hidden;background-color: white;" align="center">
					<div style="width: 20%; height: 100%; float: left;" align="left">
						<ul id="baiduTree" class="easyui-tree"
							style="width: 100%; height: 100%;"></ul>
					</div>
				<div
					style="width: 30%; height: 100%; float: left; margin-left: 1px;">
					<div class="bs-example bs-example-tabs" role="tabpanel"
						data-example-id="togglable-tabs" style="margin-left: 10px;margin-right: 10px">
						<ul id="myTab" class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a
								href="#bdIntroduction" id="bdIntroduction-tab" role="tab"
								data-toggle="tab" aria-controls="bdIntroduction"
								aria-expanded="true">介绍</a></li>
							<li role="presentation"><a href="#bdInvoke" role="tab"
								id="bdInvoke-tab" data-toggle="tab" aria-controls="bdInvoke">调用</a></li>
							<li role="presentation"><a href="#dropdown1" role="tab"
								id="dropdown1-tab" data-toggle="tab" aria-controls="dropdown1">源代码</a></li>
						</ul>
						<div id="myTabContent" class="tab-content">
							<div role="tabpanel" class="tab-pane fade in active"
								id="bdIntroduction" aria-labelledBy="bdIntroduction-tab">
								点击左侧菜单显示介绍</div>
							<div role="tabpanel" class="tab-pane fade" id="bdInvoke"
								aria-labelledBy="bdInvoke-tab">
								<div style="border: 1px  gray; margin-top: 2px;display: none">
									<div style="margin: 1px;">
										参数描述 :&nbsp; <input id="bdArgDesc" type="text" value="" class="form-text"/>
									</div>
								</div>
								<div style="border: 1px gray; margin-top: 2px;">
									<div style="margin: 2px;">
									</div>
								</div>
								<div style="border: 1px gray; margin-top: 2px;">
									<div style="margin: 1px;">
										Javascript代码
										<pre id="bdJs" style="overflow: auto; width: 100%;height: 100px"
											 onchange="bdChangeJs()">
									</pre>
									</div>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane fade" id="dropdown1"
								aria-labelledBy="dropdown1-tab">
								<textarea id="bdHtmlMap" rows="21"
									style="width: 300px; height: 400px;background-color: #F5F5F5;border-radius:4px"><%@ include
										file="../maps/baiduMap.html"%></textarea>
								<div
									style="width: 100%; text-align: right; border-bottom: 1px gray;">
									<input type="button" value="运行" style="margin-top: 10px;margin-right:10px"
										onclick="run('baidu')" class="btn btn-primary" />
								</div>
							</div>
						</div>
					</div>
				</div>
					<div id="bdHtml" style="width: 49.7%; height: 100%; float: right;">
						<%@ include file="../maps/baiduMap.html"%>
					</div>
				</div>
			</div>
		</div> --%>
	</body>
</html>
