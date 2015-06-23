<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<title>test</title>
		 
	    <script src="http://192.169.10.81:8088/optimove/js/jquery-2.1.1.min.js"></script>
	    <script src="http://192.169.10.81:8088/optimove/api/baidu_map_api_js_1.0.js"></script>
		<script type="text/javascript">
	//var xhrurl = 'http://192.169.10.81:8088/optimove/test/testAPI.do';
	  var xhrurl = 'http://192.169.10.81:8088/optimove/modelattributes/searchModelAttributeAndDataList4Test.do';
	  $.ajax({
	         type : "get",
	         async : false,
	         url :xhrurl, 
	         data : {
			 	tablename : 'TB_201502030137420248',
			 	condition : 'name=李四'
			 },
	         dataType : "jsonp",
	         jsonp: "callbackparam",
	         jsonpCallback:"jsonpCallback",
	         success : function(json){
	             alert(11);
	         },
	    error : function(err){
	        alert("error");
	    }
	     });
	     function jsonpCallback(dat){
	     	alert(22);
	     }
		</script>
	</head>

	<body>
	</body>
</html>
