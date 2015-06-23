<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<title>信息窗口</title>

		<jsp:include page="/module/common/comm.jsp"></jsp:include>
		<script type="text/javascript">
var searchUrl = "<%=request.getContextPath()%>/mapmodelmags/searchAll.do";
var getMapModelUrl = "<%=request.getContextPath()%>/mapmodelmags/getMapmodelmagMap.do";
ajaxConstants("tblMapModelMag|mapModelId|mapModelName|attributedes|orderBy=mapModelId");
$(function() {
	$.ajax( {
		url : searchUrl,
		dataType : "json",
		success : function(data) {
			$.each(data, function(key, value) {
				$("#mapModelId").append(
						"<option value='" + key + "'>" + value + "</option>");
			})
		}
	});
});

function show() {
	var a = $("#mapModelId").find("option:selected").val();
	if (a == "") {
		$("#dataDiv").empty();
		$("#modelchoose").empty();
		$("#mapText").hide();
		return;
	}
	$("#dataDiv").empty();
	$("#modelchoose").empty();
	$
			.ajax( {
				url : getMapModelUrl,
				data : {
					mapmodelid : a
				},
				dataType : "json",
				success : function(data) {
					var tab = "<table>";
					//$("#dataDiv").append("<table>");
					$.each(data, function(key, value) {
						if (key == 'model') {
							//alert(value);
							$("#mapText").text(value);
							$("#mapText").show();
						} else {
							tab += "<tr><td>" + key + ":"
									+ "</td><td><input id='" + value
									+ "' type='text'></input></td></tr>";
							//$("#dataDiv").append(
							//		"<tr><td>" + key + ":" + "</td><td><input id='"
							//				+ value
							//				+ "' type='text'></input></td></tr>");
						}
					})
					tab += "<tr><td align='center'><input type='button' value='OK' onclick='ok()'></td></tr>";
					//$("#dataDiv").append("<tr><td align='center'><input type='button' value='OK' onclick='ok()'></td></tr>");
					//$("#dataDiv").append("</table>");
					tab += "</table>";
					$("#dataDiv").html(tab);
				}
			});

}
var mainresult="";
function ok() {
	var texts = $("#dataDiv").find("input[type='text']");
	var model = $("#mapText").text();
	var str = "";
	texts.each(function() {
		id = $(this).attr("id");
		val = $(this).val();
		str+=val+",";
	});
	var result = str.substring(0,str.length-1);
	if(mainresult==""){
		mainresult="?116.404,39.915?";
	}
	var htmltext = model.replace(mainresult,"?"+result+"?");
	mainresult = "?"+result+"?";
	$("#mapText").text(htmltext);
	$("#mapDiv").html(htmltext);
}
</script>
	</head>

	<body class="easyui-layout">
		<div id="mainPanle" region="center"
			style="background: #eee; overflow-y: hidden">
			<div id="tabs" class="easyui-tabs" fit="true" border="false"
				cache="false">
				<div title="信息窗口展示" id='baiduMap'
					style="padding: 5px; overflow: hidden;" align="center">
					<div style="width: 40%; height: 100%; float: left;">
						<div title="介绍" style="overflow: hidden;" align="left">
							模板选择:
							<select onchange="show()" id="mapModelId" name="mapModelId">
								<option value="">
									请选择
								</option>
							</select>
						</div>
						<div id="dataDiv" align="left">
						</div>
						<div id="modelDiv" align="left" style="margin-top: 10px">
							<textarea rows="50" cols="10" id="mapText"
								style="display: none; width: 500px; height: 380px"></textarea>
						</div>
					</div>
					<div style="width: 59%; height: 100%; float: right;" id="mapDiv">
						<%@ include file="InfoWinMap.html"%>
					</div>
				</div>
			</div>
	</body>
</html>
