<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<title>地图模板库</title>
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
var searchUrl = "<%=request.getContextPath()%>/mapmodelmags/getMapModelJson.do?modeltype=0";
var getMapModelTreeUrl = "<%=request.getContextPath()%>/mapmodelmags/getMapModelTree.do?modeltype=0";
var getMapModelUrl = "<%=request.getContextPath()%>/mapmodelmags/getMapmodelmagMap.do?modeltype=0";
var treeData = "";
var bdTreeData = "";
var html="";
$(function() {
	//动态菜单数据
	$.ajax( {
		url : getMapModelTreeUrl,
		dataType : "json",
		success : function(data) {
			$.each(data, function(key, value) {
				//$("#baiduTree").tree( {
				//	data : eval(value),
				//	lines : true,
				//	onClick : function(node) {
				//		$("#bdIntroduction").empty();
				//		$("#bdIntroduction").text(node.text);
				//		var obj = eval(node.attributes);
				//		html="";
				//		showhtml(obj.modelId,node.text);
				//	}
				//});
				$('#baiduTree').treeview({
			        color: "#428bca",
			        //expandIcon: 'glyphicon glyphicon-chevron-right',
			        //collapseIcon: 'glyphicon glyphicon-chevron-down',
			       // nodeIcon: 'glyphicon glyphicon-bookmark',
			      	  onhoverColor: "orange",
			        data:eval(value),
			        onNodeSelected: function (event, node) {
			        	var obj = eval(node.attributes);
			           	//alert(node.text);
			            //alert(obj.modelId);
			            $("#bdIntroduction").empty();
						$("#bdIntroduction").text(obj.des);
						html="";
						showhtml(obj.modelId,node.text);
			        }
			    });
			});

		}
	});
	
	function showhtml(modelId,text) {
		$("#dataDiv").empty();
		if(text=='轨迹模板'){
			$("#dataDiv").append("起始坐标:<input id='startPoint' class='form-text input-margin' style='width:180px'/><font style='font-size:8px;color:red'>以逗号隔开</font><br/>");
			$("#dataDiv").append("终点坐标:<input id='endPoint' class='form-text input-margin' style='width:180px'/><font style='font-size:8px;color:red'>以逗号隔开</font>");
		}else if(text=='自定义地图模板'){
			var selections = "请选择地图风格:<select id='styleSelect' onchange=\"bdCreateHtmlJs('"+text+"','"+modelId+"')\"><option value='normal'>默认</option><option value='dark'>黑夜</option><option value='light'>清新蓝</option><option value='redalert'>红色警戒</option><option value='pink'>浪漫粉</option><option value='darkgreen'>青春绿</option></select>";
			$("#dataDiv").append(selections);
		}else if(text=='信贷影像信息窗口'){
			$("#dataDiv").append("业务编号:<input id='buzid' class='form-text input-margin' style='width:180px'/><br/>");
			$("#dataDiv").append("树形编号:<input id='treecode' class='form-text input-margin' style='width:180px'/><br/>");
			$("#dataDiv").append("客户类型:<input id='tag' class='form-text input-margin' style='width:180px'/><br/>");
			$("#dataDiv").append("坐标信息:<input id='position' class='form-text input-margin' style='width:180px'/><font style='font-size:8px;color:red'>以逗号隔开</font>");
		}else if(text=='信贷地图'){
			$("#dataDiv").append("坐标信息:<input id='creditposition' class='form-text input-margin' style='width:180px' /><font style='font-size:8px;color:red'>以逗号隔开</font>");
		}else if(text=='区域周边搜索'){
			$("#dataDiv").append("关键字段:<input id='key' class='form-text input-margin' style='width:180px'/><br/>");
			$("#dataDiv").append("坐标信息:<input id='searchposition' class='form-text input-margin' style='width:180px' /><font style='font-size:8px;color:red'>以逗号隔开</font>");
		}else if(text=='全景和普通地图'){
			$("#dataDiv").append("坐标信息:<input id='allposition' class='form-text input-margin' style='width:180px'/><font style='font-size:8px;color:red'>以逗号隔开</font>");
		}else if(text=='热力图'){
			$("#dataDiv").append("图半径:<input id='radius' class='form-text input-margin' style='width:180px'/><br/>");
			$("#dataDiv").append("透明度:<input id='max' class='form-text input-margin' style='width:180px'/>");
		}
		if(text!='自定义地图模板'){
			$("#dataDiv").append("<br/><input type='button' class='commbtn' value='完成' onclick=\"bdCreateHtmlJs('"+text+"','"+modelId+"')\">");
		}
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
						$("#bdHtml").html(value);
						html=value;
					}
				});
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
function bdCreateHtmlJs(text,modelId) {
	$.ajax( {
		url : getMapModelUrl,
		data : {
			mapmodelid : modelId
		},
		dataType : "json",
		success : function(data) {
			$.each(data, function(key, value) {
				if (key == 'model') {
					html = value;
					if(text=='轨迹模板'){
						var replacestr = "function start(){return new BMap.Point(116.380967,39.913285);}function end(){return new BMap.Point(116.424374,39.914668);}";
						//获取轨迹模板的html模板
						var start = $("#startPoint").val();
						var end = $("#endPoint").val();
						var js = "function start(){return new BMap.Point("+start+");}function end(){return new BMap.Point("+end+");}";
						$("#bdJs").text(js);
						//$("#bdJs").val(js);
						//alert(html);
						var result = html.replace(replacestr,js);
						$("#bdHtmlMap").text(result);
						$("#bdHtml").html(result);
					}else if(text=='自定义地图模板'){
						var replacestr="normal";
						var style= $("#styleSelect").find("option:selected").val();
						var js = "mapStyle ={features: ['road', 'building','water','land'],style : '"+style+"'}";
						$("#bdJs").text(js);
						var result = html.replace(replacestr,style);
						$("#bdHtmlMap").text(result);
						//alert($("#bdHtmlMap").text());
						$("#bdHtml").html(result);
					}else if(text=='信贷影像信息窗口'){
						var replacestr="function mappoint(){return new BMap.Point(121.548392,29.816413);}";
						var position = $("#position").val();
						var js = "function mappoint(){return new BMap.Point("+position+");}";
						$("#bdJs").text(js);
						var result = html.replace(replacestr,js);
						$("#bdHtmlMap").text(result);
						$("#bdHtml").html(result);
					}else if(text=='信贷地图'){
						var replacestr="function mappoint(){return new BMap.Point(116.404,39.915);}";
						var creditposition = $("#creditposition").val();
						var js = "function mappoint(){return new BMap.Point("+creditposition+");}";
						$("#bdJs").text(js);
						var result = html.replace(replacestr,js);
						$("#bdHtmlMap").text(result);
						$("#bdHtml").html(result);
					}else if(text=='区域周边搜索'){
						var replacestr = "new BMap.Point(116.404, 39.915)";
						var keystr = "娱乐";
						var key = $("#key").val();
						var searchposition = $("#searchposition").val();
						var js = "new BMap.Point("+searchposition+")";
						$("#bdJs").text(js+"local.search("+key+")");
						var result = html.replace(replacestr,js);
						var searchhtml = result.replace(keystr,key);
						$("#bdHtmlMap").text(searchhtml);
						$("#bdHtml").html(result);
					}else if(text=='全景和普通地图'){
						var replacestr = "function mapposition(){return new BMap.Point(121.548392,29.816413);}";
						var allviewposition = $("#allposition").val();
						var js = "function mapposition(){return new BMap.Point("+allviewposition+");}";
						$("#bdJs").text(js);
						var result = html.replace(replacestr,js);
						$("#bdHtmlMap").text(result);
						$("#bdHtml").html(result);
					}else if(text=='热力图'){
						var radiusrep = "{\"radius\":40}";
						var maxrep = "max:50";
						var radius = $("#radius").val();
						var radiusresult = "{\"radius\":"+radius+"}";
						var max = $("#max").val(); 
						var maxresult = "max:"+max+"";
						var js = "new BMapLib.HeatmapOverlay({'radius':"+radius+"})"+"heatmapOverlay.setDataSet({data:points,max:"+max+"})";
						$("#bdJs").text(js);
						var result = html.replace(radiusrep,radiusresult);
						var resulthtml = result.replace(maxrep,maxresult);
						//alert(resulthtml);
						$("#bdHtmlMap").text(resulthtml);
						$("#bdHtml").html(resulthtml);
					}
				}
			});
		}
	});
}
</script>
	</head>

	<body class="easyui-layout">
		<div class="row wrapper1 wrapper-content1 animated fadeInRight gray-bg">
                <div class="col-xs-3 container1">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>模板列表</h5>
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
				<div title="" id='baiduMap'
					style="padding: 1px; overflow: hidden;background-color: white;" align="center">
					<div style="width: 20%; height: 100%; float: left;" align="left">
						<ul id="baiduTree" class="easyui-tree"
							style="width: 100%; height: 100%;"></ul>
					</div>
					<div
					style="width: 30%; height: 100%; float: left; margin-left: 1px;border: 1px">
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
								id="bdIntroduction" aria-labelledBy="bdIntroduction-tab" style="margin-top: 10px">
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
					<div id="bdHtml" style="width: 49.7%; height: 100%; float: right;">
						<%@ include file="../maps/baiduMap.html"%>
					</div>
				</div>
			</div>
		</div> --%>
	</body>
</html>
