<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=gb2312"%>
<HTML>
	<HEAD>
		<TITLE>上海天正-地图平台-登录</TITLE>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<META http-equiv="Content-Type" content="text/html; charset=gb2312">
		<LINK href="css/reset.css" rel="stylesheet" type="text/css">
		<LINK href="css/style_login.css" rel="stylesheet" type="text/css">
		<style type="text/css">
		.form-login{
			width:180px;
			height:45px;
			padding:0px 1px;
			margin:6px;
			font-size:13px;
			font-family: 微软雅黑;
			line-height:1.42857143;
			color:#000;
			background-color:#fff;
			background-image:none;
			border:1px solid #e5e6e7;
			border-radius:4px;
			-webkit-box-shadow:inset 0 1px 1px rgba(0,0,0,.075);
			box-shadow:inset 0 1px 1px rgba(0,0,0,.075);
			-webkit-transition:border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
			-o-transition:border-color ease-in-out .15s,box-shadow ease-in-out .15s;
			transition:border-color ease-in-out .15s,box-shadow ease-in-out .15s;
		}
		:-ms-input-placeholder {
			color: #DBDBDB !important;
		}
		input {
			letter-spacing:0.25em;
		}
		#UserPwd {
			font-size:12px;
			letter-spacing:0.25em;
		}
		</style>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-easyui-1.3.6/jquery-1.8.0.min.js" charset="GBK"></script>
		<jsp:include page="/module/common/commSe7en.jsp"></jsp:include>
		<SCRIPT language="javascript">
	/**
	 * 判断是否为空
	 * @param exp
	 * @returns {Boolean}
	 */
	function isEmpty(exp){
		var bl = false;
		if (typeof exp === "undefined" )
		{
			bl = true;
		}else if(typeof exp === "string" && !exp){
			bl = true;
		}
		
		return bl;
	}
	$(function(){
		
		var val = parent.document.URL;
        if(!isEmpty(val)&&val.indexOf('login.jsp')<0){
        	parent.window.location.href='<%=request.getContextPath()%>/login.jsp';
         }
	})
	function checkform() {
		//if (document.all.BranchCode.value.length < 5) {
		//	alert('机构号必须输入5位');
		//	document.all.BranchCode.select();
		//	return false;
		//}
		//if(!checkNumberAndChar('form10092','BranchCode','只能输入字母或数字！')){
		//	return false;
		//}
		if (document.all.WorkId.value.length < 8) {
			alert('操作号必须输入8位');
			document.all.WorkId.select();
			return false;
		}
		
		if (document.all.UserPwd.value.length < 8) {
			alert('密码必须输入8位');
			document.all.UserPwd.select();
			return false;
		}
		document.form10092.B1.disabled = true;
	}

	function trans() {
		var LoginId = document.all.j_username.value;
		var PassWord = document.all.j_password.value;
		//得到转换的值 
		var CoreUserPwd = document.loginForm.myApplet.invokeDES("0000123980012399",PassWord);
		if (CoreUserPwd == "error_1") {
			document.loginForm.j_password.select();
			alert("您输入的卡号位数不正确，请重试。");
			return false;
		}
		if (CoreUserPwd == "error_0") {
			alert("运行环境有问题,请下载运行插件");
			return false;
		}
		document.all.CoreUserPwd.value = CoreUserPwd;
		//alert("document.all.CoreUserPwd.value=" + document.all.CoreUserPwd.value);
	}
	</SCRIPT>
	</HEAD>
	<BODY class="login1">
		<div class="login-wrapper">
			<div class="login-container">
				<img width="271" height="79" src="img/login-logo.png" />
				<form action="<%=request.getContextPath()%>/login.do" method="post"
					name="form10092" onSubmit="return checkform();">
					<div style="font-size: 12px; float: left;">
						<font color="red">&nbsp;&nbsp; <c:if
								test="${passwordError != null}">
								<%=request.getAttribute("passwordError")%>
							</c:if> <c:if test="${userError != null}">
								<%=request.getAttribute("userError")%>
							</c:if>
						</font>
					</div>
					<div class="form-group">
						<br>
					</div>
					<div class="form-group" style="display: none;">
						<P>机构号：</P> <INPUT name="BranchCode" class="sinp" id="BranchCode"
						type="text" maxlength="5" value="00000">
					</div>
					<div class="form-group">
							<!-- 用户名: -->
							<input class="form-login" name="WorkId" id="WorkId" placeholder="用户名" type="text" size="20" maxlength="8" style="width: 270px;">
					</div>
					<div class="form-group">
							<!-- 密&nbsp;码: -->
							<input class="form-login" name="UserPwd" id="UserPwd" placeholder="密码" type="password" size="8" maxlength="8" style="width: 270px;">
						<INPUT name="CoreUserPwd" type="hidden">
					</div>
					<div class="form-group" style="text-align: center; margin-top: 7px;">
						<input class="btn btn-lg btn-primary form-login" type="submit" value="登 录" style="width: 270px; margin-left: 11px;">
					</div>
					<div class="form-group" style="text-align: center; margin-top: 20px;">
						<span style="font-family: 微软雅黑; font-size: 11px;">
							<font color="#A0A0A0">版权所有&#169;上海天正软件有限公司 2003-2015</font>
						</span>
					</div>
				</form>
			</div>
		</div>
	</BODY>
</HTML>