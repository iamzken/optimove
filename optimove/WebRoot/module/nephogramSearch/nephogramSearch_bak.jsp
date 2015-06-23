<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<title>云图检索</title>

		<jsp:include page="/module/common/comm.jsp"></jsp:include>

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
			function showtool() {
				var id = $("input[name=typeSelect]:checked").attr("id");
				if (javastr == "") {
					javastr = "function mapfun(){map.centerAndZoom(new BMap.Point(113.2359818,23.16937253), 4);}";
				}
				var htmlstatic = $("#mapTexthidden2").text();
				//var text = $("#mapText").text();
				if (id == "selectedview") {
					//$("#mapDiv").load("searchonselectview.html");
					$("#mapDiv").html($("#mapTexthidden").text());
					$("#mapText").val($("#mapTexthidden").text());
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
			var oldHtml = "var points = [[121.548392,29.816413]];";
			var newHtml = "var points = [";
			function show(flg,i) {
			
				var a = $("#mapModelId").find("option:selected").val();
				if (a == "") {
					$("#dataDiv").empty();
					$("#modelchoose").empty();
					$("#mapText").hide();
					return;
				}
				$("#dataDiv").empty();
				$("#modelchoose").empty();
				if(flg){
					subSearchUrlForDataList = "<%=request.getContextPath()%>/modelattributes/searchMapInfo.do?"+$("#searchForm"+i).serialize();
				}
				var len = 0;
				$.ajax( {
					url : subSearchUrlForDataList,
					data : {
						nephogrammodelid : a
					},
					dataType : "json",
					success : function(data) {
						var tab = "<table>";
						$.each(data, function(key, value) {
							if(key == 'rows'){
								tab += "<tr>";
								$.each(value, function(idx, obj){
									len++;
									if(obj.modelattribute !="picture" && obj.modelattribute != "updatetime" && obj.modelattribute != "createtime"){
										tab += "<td>" + obj.modelattributename+"&nbsp;&nbsp;&nbsp;&nbsp;" + "</td>";
									}
								})
								tab += "</tr>";
							}
							
						});
						$("#tableName").val(data["tableName"]);
						$.each(data, function(key, value) {
							if(key == 'dataList'){
								$.each(value, function(idx, obj){
									tab += "<tr onclick='showPosition(";
									for(var i=0;i<len;i++){
										var colName = data["rows"][i].modelattribute.toLocaleUpperCase();
										if(colName == "LONGITUDE"){
											tab += obj[colName]+",";
											newHtml += "[" + obj[colName] + ",";
										}
										if(colName == "LATITUDE"){
											tab += obj[colName]+")'>";
											newHtml += obj[colName] + "],";
										}
									}
									
									for(var i=0;i<len;i++){
									    var attr = data["rows"][i].modelattribute;
										if(attr !="picture" && attr != "updatetime" && attr != "createtime"){
											var colName = attr.toLocaleUpperCase();
											var val = obj[colName];
											if(val == undefined){
												val = "";
											}else if(colName == "SALARY"){
												val += "万元";
											}
											tab += "<td>" + val +"&nbsp;&nbsp;" + "</td>";
										}
									}
									tab += "</tr>";
								})
							}
						});
						
						//tab += "<tr><td align='center'><input type='button' value='检索' onclick='ok()' class='commbtn'></td></tr>";
						tab += "</table>";
						$("#dataDiv").html(tab);
						//$("#mapText").show();
						
						newHtml = newHtml.substring(0, newHtml.length-1);
						newHtml += "];";
						$("#mapHtml").val($("#mapHtml").val().replace(oldHtml, newHtml));
						oldHtml = newHtml;
						$("#bdHtmlMap").html($("#mapHtml").val());
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
				//	alert($("#allviewMap").text());
					$("#mapDiv").html($("#allviewMap").text());
				} else if (id == "currentview") {
					$("#mapDiv").html($("#currentviewMap").text());
				} else if (id == "selectedview") {
					
				}
				//var model = $("#mapText").text();
				//$("#mapText").text(model);
				//$("#mapDiv").html(model);
			}

</script>
	</head>

	<body class="easyui-layout">
		
		<div id="mainPanle" region="center" style="background: #eee; overflow-y: hidden">
			<div id="tabs" class="" fit="true" border="false" cache="false">
				<div title="" id='baiduMap' style="padding: 5px; overflow: hidden;background-color: white;" align="center">
					<div style="width: 40%; height: 100%; float: left;">
						<input type="hidden" value="" id="tableName">
						<div title="介绍" style="overflow: hidden;" align="left">
							云图模板选择:
							<select onchange="show()" id="mapModelId" name="mapModelId">
								<option value="">
									请选择
								</option>
							</select>
						</div>
						<div id="dataDiv" align="left">
						</div>
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
					<textarea id="mapHtml" rows="21" style="display: none;">
						<%@ include file="../maps/baiduMap2.html"%>
					</textarea>
					<div style="width: 59%; height: 100%; float: right;" id="bdHtmlMap">
						<%@ include file="../maps/baiduMap2.html"%>
					</div>
					
				</div>
			</div>
			</div>
			<script type="text/javascript">
				var content = $("#bdHtmlMap").html();
				function showPosition(longitude,latitude){
					//$("#bdHtmlMap").html(content.replaceAll(121.548392,longitude).replaceAll(29.816413,latitude));
					
					newHtml = "var points = [[" + longitude + "," + latitude +"]];";
					$("#mapHtml").val($("#mapHtml").val().replace(oldHtml, newHtml));
					oldHtml = newHtml;
					$("#bdHtmlMap").html($("#mapHtml").val());
				}
				function changeInfo(vPoints){
					/* var start = $("#mapHtml").val().indexOf("<h4");
					var end = $("#mapHtml").val().indexOf("</h4>")+5;
					var h4 = $("#mapHtml").val().substring(start,end);
					$("#bdHtmlMap").html(content.replace(s,"Hello"));
					ss += point;
					$("#bdHtmlMap").html(content.replace(mark,ss)); */
					
					newHtml = vPoints;
					$("#mapHtml").val($("#mapHtml").val().replace(oldHtml, newHtml));
					$("#mapHtml").val($("#mapHtml").val().replace("var infodiv = \"display: none;\";", "var infodiv = \"display: block;\";"));
					$("#mapHtml").val($("#mapHtml").val().replace("var formdiv = \"display: block;\";", "var formdiv = \"display: none;\";"));
					oldHtml = newHtml;
					$("#bdHtmlMap").html($("#mapHtml").val());
				}
			</script>
	</body>
</html>
