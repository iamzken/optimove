var baiduMapHtml='<!DOCTYPE html>'+
'<html>'+
	'<head>'+
		'<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">'+
		'<meta http-equiv="pragma" content="no-cache">'+
		'<meta http-equiv="cache-control" content="no-cache">'+
		'<meta http-equiv="expires" content="0">'+
		'<style type="text/css">'+
			'body,html,#allmap {'+
				'width: 100%;'+
				'height: 100%;'+
				'overflow: hidden;'+
				'margin: 0;'+
				'font-family: "微软雅黑";'+
			'}'+
		'</style>'+
		'<script type="text/javascript" src="http://192.169.10.219:8088/optimove/api/baidu_map_api_core.js"></script>'+
		'<script type="text/javascript" src="http://192.169.10.219:8088/optimove/api/baidu_map_api_drawing_manager.js"></script>'+
		'<link rel="stylesheet" href="http://192.169.10.219:8088/optimove/api/DrawingManager_min.css" />'+
		'<script type="text/javascript" src="http://192.169.10.219:8088/optimove/api/SearchInfoWindow_min.js"></script>'+
		'<link rel="stylesheet" href="http://192.169.10.219:8088/optimove/api/SearchInfoWindow_min.css" />'+
		'<title></title>'+
	'</head>'+
	'<body>'+
		'<div id="allmap"></div>'+
	'</body>'+
	'<script type="text/javascript" charset="GB2312">'+
		'var uploadImgDir = "http://192.169.10.219:8088/optimove/mobileimages/";'+
		'var points = [[["LONGITUDE","经度","121.548392"],["LATITUDE","纬度","29.816413"]]];'+
		'var infodiv = "display: block;";'+
		'var formdiv = "display: none;";'+
		'var info = "";'+
		'var checkPoint = [];'+
		'var vIcon = "marker.png";'+
		'var map = new BMap.Map("allmap");'+
		'map.centerAndZoom(mappoint(), 12);'+
		'map.addControl(new BMap.MapTypeControl());'+
		'map.setCurrentCity("北京");'+
		'map.enableScrollWheelZoom(true);'+ 
		'var markers = [];'+
		'var pointArray = new Array();'+
		'for(var i=0; i<points.length; i++){'+
			'var x = "";'+
			'var y = "";'+
			'var info = "";'+
			'for(var j=0;j<points[i].length;j++){'+
				'var val = points[i][j][2];'+
				'if(points[i][j][0] == "LONGITUDE"){'+
					'x = points[i][j][2];'+
				'}else if(points[i][j][0] == "LATITUDE"){'+
					'y = points[i][j][2];'+
				'}else if(points[i][j][0] == "PICTURE"){'+
					'val = "<img src=\'"+uploadImgDir+points[i][j][2]+"\' width=\'150\' height=\'50\'>";'+
				'}'+
				'info += points[i][j][1]+":"+val+"<br>";'+
			'}'+
			'var point = new BMap.Point(x,y);'+
			'if(checkPoint.length > 0){'+
				'if(x == checkPoint[0] && y == checkPoint[1]){'+
					'vIcon = "pointMarker.png"'+
				'} else {'+
					'vIcon = "marker.png"'+
				'}'+
			'}'+
			'var icon = new BMap.Icon("http://lbsyun.baidu.com/map/resource/geodata/css/images/"+vIcon, new BMap.Size(32, 32), {'+
			    'anchor: new BMap.Size(10, 30)'+
			'});'+
			'var marker = new BMap.Marker(point, {'+
			    'icon: icon'+
			'});'+ 
			'var sContent = info;'+
			'map.addOverlay(marker);'+
			'addClickHandler(sContent, marker);'+
			'pointArray[i] = point;'+
		'}'+
		'map.setViewport(pointArray);'+
		'var opts = {'+
			'width : 300,'+
			'enableMessage:true'+
	    '};'+
		'function addClickHandler(content,marker){'+
			'marker.addEventListener("click",function(e){'+
				'openInfo(content,e)}'+
			');'+
		'}'+
		'function openInfo(content,e){'+
			'var p = e.target;'+
			'var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);'+
			'var infoWindow = new BMap.InfoWindow(content,opts);'+ 
			'map.openInfoWindow(infoWindow,point);'+
		'}'+
		'function mappoint(){return new BMap.Point(121.548392,29.816413);}'+
		'function loadDrawingManager(){'+
			'var overlaycomplete = function(e){'+
			    'var strpoint="";'+
				'for(var i = 0;i < e.overlay.W.length-1;i++){'+
					'if(i>0){'+
						'strpoint += ",";'+
					'}'+
					'strpoint += (e.overlay.W[i].lng + " , " + e.overlay.W[i].lat);'+
				'}'+
				'$("#resultpoint").val(strpoint);'+
		    '};'+
		    'var styleOptions = {'+
		    	'fillColor: "blue",'+
				'strokeWeight: 1,'+
				'fillOpacity: 0.3,'+
				'strokeOpacity: 0.3'+
		    '};'+
		    'var drawingManager = new BMapLib.DrawingManager(map, {'+
		        'isOpen: false,'+
		        'enableDrawingTool: true,'+
		        'drawingToolOptions: {'+
		            'anchor: BMAP_ANCHOR_TOP_RIGHT,'+
		            'offset: new BMap.Size(95, 5),'+
		            'scale: 0.8'+
		        '},'+
		        'circleOptions: styleOptions,'+
		        'polygonOptions: styleOptions,'+
		        'rectangleOptions: styleOptions'+
		    '});'+
		    'drawingManager.addEventListener("overlaycomplete", overlaycomplete);'+
		'}'+
	'</script>'+
'</html>';
var subSearchUrlForDataList4Test = "http://192.169.10.219:8088/optimove/modelattributes/searchModelAttributeAndDataList4Test.do";
var searchModelattribute4Display4Test = "http://192.169.10.219:8088/optimove/modelattributes/searchModelattribute4Display4Test.do";
function callback_(dd){
alert(6);
}
function _callback(dd){
alert(7);
}
function getBaiduMap(tabName,queryParams) {
	var oldPoints = 'var points = [[["LONGITUDE","经度","121.548392"],["LATITUDE","纬度","29.816413"]]];';
	var cols;
	var rows;
	$.ajax({
		type : "get",
		url : searchModelattribute4Display4Test,
		async :false,
		data : {
			tablename : tabName
		},
		cache : false,
	    dataType : "jsonp",
	    jsonp: "callbackparam",
	    jsonpCallback:"callback_",
		success : function(data_) {
		alert(1);
			$.each(data_, function(key, value) {
			   cols = eval(data_);
		   });
		   $.ajax({
		   		type : "get",
				url : subSearchUrlForDataList4Test,
				async :false,
				data : {
					tablename : tabName,
					condition : queryParams
				},
				cache : false,
		    	dataType : "jsonp",
		    	jsonp: "callbackparam",
		    	jsonpCallback:"_callback",
				success : function(_data) {
					rows = eval(_data[0]["rows"]);
					var str = "";
					var newPoints = "var points = [";
					for(var i=0;i<rows.length;i++){
						str += "[";
						for(var j=0;j<cols[0].length;j++){
							if(rows[i][cols[0][j].field] != undefined){
								str += "['"+cols[0][j].field+"','"+cols[0][j].title+"','"+(rows[i][cols[0][j].field]=="null"?"":rows[i][cols[0][j].field])+"'],";
							}
						}
						str = str.substring(0,str.length-1);
						str += "],";
					}
					newPoints += str.substring(0,str.length-1)+"];";
					baiduMapHtml=baiduMapHtml.replace(oldPoints,newPoints);
				alert(2);
			  }
		   });
		}
	});
			//alert(baiduMapHtml);	
	//setTimeout(function(){alert(999);}, 1000); 
	return baiduMapHtml;

	
}