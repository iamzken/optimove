<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<title>云图检索</title>
		<jsp:include page="/module/common/comm.jsp"></jsp:include>
		<style type="text/css">
		 	.container1
		 	{
    			padding-right: 0px;
			}
		 </style>
		 
	    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js?v=1.7"></script>
	    <script src="<%=request.getContextPath()%>/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	    <script src="<%=request.getContextPath()%>/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	    <script src="<%=request.getContextPath()%>/js/hplus.js?v=1.7"></script>
	    <script src="<%=request.getContextPath()%>/js/plugins/pace/pace.min.js"></script>

		<script type="text/javascript">
		
			var searchNephogrammodelmagUrl = "<%=request.getContextPath()%>/nephogrammodelmags/searchAll.do";
			var searchUrl = "<%=request.getContextPath()%>/mapmodelmags/searchAll.do";
			var getMapModelUrl = "<%=request.getContextPath()%>/mapmodelmags/getMapmodelmagMap.do";
			var getNephogramUrl = "<%=request.getContextPath()%>/nephogrammodelmags/getNephogrammodelmagAttr.do";
			ajaxConstants("tblNephogramModelMag|nephogramModelId|nephogramModelName|orderBy=nephogramModelId");
			ajaxConstants("tblMapModelMag|mapModelId|mapModelName|attributedes|orderBy=mapModelId");
			
			var searchUrl = "<%=request.getContextPath()%>/mapmodelinfos/searchMapmodelinfo.do";
			var subSearchUrl = "<%=request.getContextPath()%>/modelattributes/searchModelattribute.do";
			var subSearchUrlForDataList = "<%=request.getContextPath()%>/modelattributes/searchModelAttributeAndDataList.do";
			var searchDataList = "<%=request.getContextPath()%>/modelattributes/searchDataByCondition.do";
			var searchRegionModelDataUrl = "<%=request.getContextPath()%>/mapmodelinfos/searchRegionModelData.do";
			var getAttributesUrl = "<%=request.getContextPath()%>/modelattributes/getAttributes.do";
			var getAttributes4SearchUrl = "<%=request.getContextPath()%>/modelattributes/searchModelattribute4Search.do";
			var getAttributes4DisplayUrl = "<%=request.getContextPath()%>/modelattributes/searchModelattribute4Display.do";
			
			$(function() {
				$.ajax( {
					url : searchUrl,
					dataType : "json",
					success : function(data) {
						$.each(data, function(key, value) {
							if(key == 'rows'){
								$.each(value, function(idx, obj){
									$("#mapModelId").append("<option value='" + obj.modeldatatable + "'>" + obj.modelname + "</option>");
								})
							}
						})
					}
				});
			});
			var javastr = "";
			function searchData(){
			
				show("searchData",$("#leftForm").serialize());
				
			}
			function showtool() {
				$("#resultpoint").val("");
				$('#data').html("<div id='dataList'></div>");
				$("#mapModelId option").eq(0).attr('selected', 'true');
				var id = $("input[name=typeSelect]:checked").attr("id");
				if (javastr == "") {
					javastr = "function mapfun(){map.centerAndZoom(new BMap.Point(113.2359818,23.16937253), 4);}";
				}
				var htmlstatic = $("#mapTexthidden2").text();
				if (id == "selectedview") {
					loadDrawingManager();
					//$("#bdHtmlMap").html($("#mapTexthidden").text());
					//$("#mapText").val($("#mapTexthidden").text());
				} else if (id == "currentview") {
					var replacestr = "function mapfun(){map.centerAndZoom(new BMap.Point(113.2359818,23.16937253), 14);}";
					var result = htmlstatic.replace(javastr, replacestr);
					$("#mapText").val(result);
					$("#mapDiv").html(result);
					$("#mapTexthidden2").text(result);
					javastr = replacestr;
				} else {
					var oldstr = "function mapfun(){map.centerAndZoom(new BMap.Point(113.2359818,23.16937253), 4);}";
					var oldpage = htmlstatic.replace(javastr, oldstr);
					$("#mapText").val(oldpage);
					$("#mapDiv").html(oldpage);
					$("#mapTexthidden2").text(oldpage);
					javastr = oldstr;
				}
			}
			var oldPoints = "var points = [[['LONGITUDE','经度','121.548392'],['LATITUDE','纬度','29.816413']]];";
			var newPoints = "var points = [";
			var oldCheckPoint = "var checkPoint = [];";
			var newCheckPoint = "";
			var searchCondition = "<form id='leftForm' name='leftForm' action=''>";
			
			function show(flg,obj) {
				var tablename = $("#mapModelId").find("option:selected").val();
				if(tablename == ""){
					return;
				}
				var id = $("input[name=typeSelect]:checked").attr("id");
				var searchCondition = "<form id='leftForm' name='leftForm' action=''>";
				var resultpoint = "";
				var queryCondition = "";
				if(flg == "searchData"){
					queryCondition = obj;
					resultpoint = $("#resultpoint").val();
				}
				$.ajax({
						url : getAttributes4SearchUrl,
						async :false,
						data : {
							tablename : tablename
						},
						dataType : "json",
						success : function(data) {
							$.each(data, function(key, value) {
							if(key == "column"){
							   var count = 1;
							   for(var i=0;i<eval(value)[0].length;i++){
									if(eval(value)[0][i].field != "LONGITUDE" && eval(value)[0][i].field != "LATITUDE"){
										var br = "";
										//每一行放置三个查询框
										if(count%3 == 0){
											br = "<br>";
										}
										count++;
										var val = "";
										for(var j=0;j<queryCondition.split("&").length;j++){
											if(queryCondition.split("&")[j].split("=")[0] == eval(value)[0][i].field){
												val = queryCondition.split("&")[j].split("=")[1];
												break;
											}
										}
										searchCondition += "<input type='text' class='form-text input-margin' placeholder = '"+eval(value)[0][i].title+"' name='"+eval(value)[0][i].field+"' value='"+decodeURI(val)+"'>"+br;
									}
								}
							  }
						   });
						}
					});
					searchCondition += "<input type='button' id='searchForm' class='commbtn' value='搜索' onclick='searchData();'></form>"
					$("#searchCondition").html(searchCondition);
					$.ajax({
						url : getAttributes4DisplayUrl,
						async :false,
						data : {
							tablename : tablename
						},
						dataType : "json",
						success : function(data) {
							$.each(data, function(key, value) {
							if(key == "column"){
								$('#dataList').empty();
								$('#dataList').datagrid({
									width : 'auto',
									height : 'auto',
									striped : true,
									border : true,
									collapsible : false,//是否可折叠的  
									fit : true,//自动大小  
									remoteSort : false,
									singleSelect : true,//是否单选  
									pagination : true,//分页控件  
									rownumbers : false,//行号  
									onClickRow:showPosition,
									frozenColumns : [ [ {
										field : 'ck',
										checkbox : false
									} ] ],
									toolbar:'#detailtb',
									url : subSearchUrlForDataList,
									queryParams: {
										tablename : tablename,
										resultpoint:resultpoint,
										condition : queryCondition
									},
									columns : eval(value),
									onLoadSuccess : function(_data) {
										if (_data.result != 'ok') {
											showBox("提示信息", _data.errorMsg, 'warning');
										}
										var rows = $("#dataList").datagrid("getRows");
										var str = "";
										for(var i=0;i<rows.length;i++){
											str += "[";
											for(var j=0;j<eval(value)[0].length;j++){
												if(rows[i][eval(value)[0][j].field] != undefined){
													str += "['"+eval(value)[0][j].field+"','"+eval(value)[0][j].title+"','"+(rows[i][eval(value)[0][j].field]=="null"?"":rows[i][eval(value)[0][j].field])+"'],";
												}
											}
											str = str.substring(0,str.length-1);
											str += "],";
										}
										newPoints += str.substring(0,str.length-1)+"];";
										$("#resultpoint").val("");
										$("#mapHtml").val($("#mapHtml").val().replace(oldPoints, newPoints));
										$("#bdHtmlMap").html($("#mapHtml").val());
										if(id == "selectedview"){
											loadDrawingManager();
										}
										oldPoints = newPoints;
										newPoints = "var points = [";
									}
								});
							  }
						   });
						}
					});
			
			}
			
			var getMapModelUrl = "<%=request.getContextPath()%>/mapmodelmags/getMapmodelmagMap.do";
			var mainresult = "";
			function ok() {
				$("#mapDiv").html($("#mapTexthidden2").text());
				var a = $("#mapModelId").find("option:selected").val();
				var id = $("input[name=typeSelect]:checked").attr("id");
				if (id == "allview") {
					$("#mapDiv").html($("#allviewMap").text());
				} else if (id == "currentview") {
					$("#mapDiv").html($("#currentviewMap").text());
				} else if (id == "selectedview") {
					
				}
			}

			var content = $("#bdHtmlMap").html();
			function showPosition(rowIndex, rowData){
				newCheckPoint = "var checkPoint = [" + rowData.LONGITUDE + "," + rowData.LATITUDE +"];";
				$("#mapHtml").val($("#mapHtml").val().replace(oldCheckPoint, newCheckPoint));
				oldCheckPoint = newCheckPoint;
				$("#bdHtmlMap").html($("#mapHtml").val());
				if ($("input[name=typeSelect]:checked").attr("id") == "selectedview") {
					loadDrawingManager();
				}
				map.panTo(new BMap.Point(rowData.LONGITUDE,rowData.LATITUDE));
			}
			function changeRight(newPoints){
				
				$("#mapHtml").val($("#mapHtml").val().replace(oldPoints, newPoints));
				//$("#mapHtml").val($("#mapHtml").val().replace("var infodiv = \"display: none;\";", "var infodiv = \"display: block;\";"));
				//$("#mapHtml").val($("#mapHtml").val().replace("var formdiv = \"display: block;\";", "var formdiv = \"display: none;\";"));
				oldPoints = newPoints;
				$("#bdHtmlMap").html($("#mapHtml").val());
				
			}
			function changeLeft(obj){
			
				show("changeLeft",obj);
				
			}
		</script>
	</head>

	<body class="easyui-layout">
		<div class="row wrapper1 wrapper-content1 animated fadeInRight gray-bg">
                <div class="col-xs-6 container1">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>条件/信息</h5>
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
                            <div id="treeview1" class="test">	
						<input type="text" value="" id="tablename" style="display: none;">
						<div>
							<input type="radio" id="allview" name="typeSelect" checked="checked" onclick="showtool()"/>
							当前视野&nbsp;&nbsp;
							<input type="radio" id="currentview" name="typeSelect" onclick="showtool()"/>
							全景视野&nbsp;&nbsp;
							<input type="radio" id="selectedview" name="typeSelect" onclick="showtool()"/>
							自定义视野&nbsp;&nbsp;
						</div>
						<div title="介绍" style="margin-top: 10px">
							云图模型选择:
							<select onchange="show()" id="mapModelId" name="mapModelId">
								<option value="">
									请选择
								</option>
							</select>
						</div>
						<input id="resultpoint" name="resultpoint" style="display: none"/>
						<div id="searchCondition" style="overflow: hidden;" align="left"></div>
						<div class="panel-fit gray-bg" region="center" id="data" style="height: 300px">
							<div id='dataList'></div>
						</div>
						<div id="dataDiv" align="left" style="overflow: auto;height:50%;display:none;" class="table-responsive"></div>
						<div id="modelDiv" align="left" style="margin-top: 10px">
							<textarea rows="50" cols="10" id="mapText" style="display: none; width: 400px; height: 360px">
								<%@ include file="InfoWinMap.html"%>
							</textarea>
							<textarea rows="50" cols="10" id="mapTexthidden" style="display: none; width: 400px; height: 360px">
								<%@ include file="searchonselectview.html"%>
							</textarea>
							<textarea rows="50" cols="10" id="mapTexthidden2" style="display: none; width: 400px; height: 360px">
								<%@ include file="InfoWinMap.html"%>
							</textarea>
							<textarea rows="50" cols="10" id="allviewMap" style="display: none; width: 400px; height: 360px">
								<%@ include file="allview.html"%>
							</textarea>
							<textarea rows="50" cols="10" id="currentviewMap" style="display: none; width: 400px; height: 360px">
								<%@ include file="current.html"%>
							</textarea>
						</div>
					</div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6 container1">
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
                                    <li><a href="buttons.html#">选项1</a></li>
                                    <li><a href="buttons.html#">选项2</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="ibox-content">
	                        <div id="treeview2" class="test">
	                            <textarea id="mapHtml" rows="21" style="display: none;">
									<%@ include file="../maps/baiduMap4NephogramSearch.html"%>
								</textarea>
								<div style="width: 100%; height: 100%; float: right;" id="bdHtmlMap">
									<%@ include file="../maps/baiduMap4NephogramSearch.html"%>
								</div>
							<div>
						</div>
					</div>
                  </div>
                </div>
            </div>
		</div>
	</body>
</html>
