function NBCBMap(container) {
	this.map = new AMap.Map(container);
	this.nbcbCenterAndZoom = function (nbcbPoint, zoom) {
		this.map.setCenter(nbcbPoint);
		this.map.setZoom(zoom);
	};
	this.nbcbAddNavigationControl = function () {
		var that = this;
		that.map.plugin(["AMap.ToolBar"], function () {
			that.map.addControl(new AMap.ToolBar());
		});
	};
	this.nbcbAddScaleControl = function () {
		var that = this;
		that.map.plugin(["AMap.Scale"], function () {
			that.map.addControl(new AMap.Scale());
		});
	};
	this.nbcbAddMapTypeControl = function () {
		var that = this;
		that.map.plugin(["AMap.MapType"], function () {
			var mapType = new AMap.MapType({defaultType:2, showRoad:true});
			that.map.addControl(mapType);
		});
	};
	this.nbcbAddOverViewControl = function () {
		var that = this;
		that.map.plugin(["AMap.OverView"], function () {
			var overView = new AMap.OverView({visible:true});
			that.map.addControl(overView);
		});
	};
	this.nbcbSetCurrentCity = function (city) {
		this.map.setCity(city);
	};
	this.nbcbEnableScrollWheelZoom = function (flag) {
		this.map.setStatus({dragEnable:flag});
		this.map.setStatus({zoomEnable:flag});
	};
}
NBCBMap.Map = function (container) {
	return new NBCBMap(container);
};
NBCBMap.NBCBPoint = function (lng, lat) {
	return new AMap.LngLat(lng, lat);
};

