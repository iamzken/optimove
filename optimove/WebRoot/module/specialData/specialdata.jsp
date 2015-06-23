<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<html>
	<head>
		<title>����</title>
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
					alert("��ѡ���ļ����ټ���...");
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
								var newHtml = "�������ͣ�"
										+ $("#dataType").find("option:selected").text()
										+ "<br>������Դ��"
										+ $("#resouceType").find("option:selected").text()
										+ "<br>����������" + value + "��";
								$("#fileDetail").html("").append(newHtml);
								$("#fileDetails").show();
							})
						}
					});
				}
			}
			
			function uploadFile() {
				alert("�ϴ��ɹ�...");
				$("#fileDetails").hide();
			}
			
			function changeResouceType() {
				var dataType = $("#dataType").val();
				if (dataType == "1") {
					$("#resouceType").empty();
					$("#resouceType").append(
							"<option value=\"1\" selected=\"selected\">�Ϻ�������</option>");
					$("#resouceType").append("<option value=\"2\">�Ϻ��滮��</option>");
					$("#resouceType").append("<option value=\"3\">�Ϻ�������</option>");
				} else if (dataType == "2") {
					$("#resouceType").empty();
					$("#resouceType").append(
							"<option value=\"1\" selected=\"selected\">���������</option>");
					$("#resouceType").append("<option value=\"2\">����GDP</option>");
				} else if (dataType == "3") {
					$("#resouceType").empty();
					$("#resouceType").append(
							"<option value=\"1\" selected=\"selected\">��ҳ</option>");
					$("#resouceType").append("<option value=\"2\">����</option>");
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
									��������:
								</label>
							</td>
							<td>
								<select id="dataType" onchange="changeResouceType()"
									class="form-control">
									<option value="1" selected="selected">
										���ͳ������
									</option>
									<option value="2">
										��������
									</option>
									<option value="3">
										ץȡ����
									</option>
								</select>
							</td>
							<td>
								<label class="control-label">
									&nbsp;&nbsp;������Դ:
								</label>
							</td>
							<td>
								<select id="resouceType" class="form-control">
									<option value="1" selected="selected">
										�Ϻ�������
									</option>
									<option value="2">
										�Ϻ��滮��
									</option>
									<option value="3">
										�Ϻ�������
									</option>
								</select>
							</td>
							<td>
								<label class="control-label">
									&nbsp;&nbsp;�ļ���
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
								<input type="button" onclick="previewFile()" value="�����ļ�"
									class="commbtn pull-right" />
							</td>
						</tr>
					</table>
				</div>
		
				<div id="fileDetails" style="display: none; margin-left: 5px">
					<hr>
					<div id="fileDetail">
						�������ͣ����ͳ������
						<br>
						������Դ���Ϻ������
						<br>
						����������100��
					</div>
					<br>
					<input type="button" onclick="uploadFile()" style="float: none;"
						value="�� ��" class="btn btn-success pull-left" />
				</div>
			</div>
		</div>
	</body>
</html>