<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<html>
	<head>
		<title>管理</title>
		<jsp:include page="/module/common/comm.jsp"></jsp:include>
		<script type="text/javascript" charset="GBK">
			var searchUrl = "<%=request.getContextPath()%>/specialdatas/searchSpecialdata.do";
			var updateUrl = "<%=request.getContextPath()%>/specialdatas/update.do";
			var insertUrl = "<%=request.getContextPath()%>/specialdatas/insert.do";
			var deleteUrl = "<%=request.getContextPath()%>/specialdatas/delete.do";
			ajaxConstants("tblMacrostatistics|macrostatisticsId|macrostatisticsName|orderBy=macrostatisticsId,tblPublicData|publicdataId|publicdataName|orderBy=publicdataId,tblGrabData|grabdataId|grabdataName|orderBy=grabdataId");
			$(function() {
			
			});
			
			function previewFile() {
				if ($("#fileId").val() == "") {
					alert("请选择文件后再加载...");
				} else {
					$.ajax( {
						url : "<%=request.getContextPath()%>/specialdatas/saxParseXml.do",
						data : {
							filePath : $("#fileId").val()
						},
						type : "post",
						dataType : "json",
						success : function(data) {
							$.each(data, function(key, value) {
								var newHtml = "数据类型："
										+ $("#dataType").find("option:selected").text()
										+ "<br>数据来源："
										+ $("#resouceType").find("option:selected").text()
										+ "<br>数据总量：" + value + "条";
								$("#fileDetail").html("").append(newHtml);
								$("#fileDetails").show();
							})
						}
					});
				}
			}
			
			function uploadFile() {
				alert("上传成功...");
				$("#fileDetails").hide();
			}
			
			function changeResouceType() {
				var dataType = $("#dataType").val();
				if (dataType == "1") {
					$("#resouceType").empty();
					$("#resouceType").append(
							"<option value=\"1\" selected=\"selected\">上海民政局</option>");
					$("#resouceType").append("<option value=\"2\">上海规划局</option>");
					$("#resouceType").append("<option value=\"3\">上海公安局</option>");
				} else if (dataType == "2") {
					$("#resouceType").empty();
					$("#resouceType").append(
							"<option value=\"1\" selected=\"selected\">国家气象局</option>");
					$("#resouceType").append("<option value=\"2\">国家GDP</option>");
				} else if (dataType == "3") {
					$("#resouceType").empty();
					$("#resouceType").append(
							"<option value=\"1\" selected=\"selected\">网页</option>");
					$("#resouceType").append("<option value=\"2\">报文</option>");
				}
			}
		</script>
	</head>
	<body class="easyui-layout">
		<div class="wrapper wrapper-content panel-fit gray-bg">
			<div class="row wrapper border-bottom white-bg page-heading" style="width: 100%; height: 100%; margin-left: 0px;">
				<div style="margin-top: 50px; margin-left: 15px;">
					<table>
						<tr>
							<td>
								<label class="control-label">
									数据类型:
								</label>
							</td>
							<td>
								<select id="dataType" onchange="changeResouceType()"
									class="form-control">
									<option value="1" selected="selected">
										宏观统计数据
									</option>
									<option value="2">
										开发数据
									</option>
									<option value="3">
										抓取数据
									</option>
								</select>
							</td>
							<td>
								<label class="control-label">
									&nbsp;&nbsp;数据来源:
								</label>
							</td>
							<td>
								<select id="resouceType" class="form-control">
									<option value="1" selected="selected">
										上海民政局
									</option>
									<option value="2">
										上海规划局
									</option>
									<option value="3">
										上海公安局
									</option>
								</select>
							</td>
							<td>
								<label class="control-label">
									&nbsp;&nbsp;文件域：
								</label>
							</td>
							<td>
								<div>
									<input class="form-control" type="file" id="fileId" value="">
								</div>
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="button" onclick="previewFile()" value="加载文件"
									class="commbtn pull-right" />
							</td>
						</tr>
					</table>
				</div>
		
				<div id="fileDetails" style="display: none; margin-left: 5px">
					<hr>
					<div id="fileDetail">
						数据类型：宏观统计数据
						<br>
						数据来源：上海劳务局
						<br>
						数据总量：100条
					</div>
					<br>
					<input type="button" onclick="uploadFile()" style="float: none;"
						value="上 传" class="btn btn-success pull-left" />
				</div>
			</div>
		</div>
	</body>
</html>