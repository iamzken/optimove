function NBCBMap(container) {
	this.map = new BMap.Map(container);
	this.nbcbCenterAndZoom = function (nbcbPoint, zoom) {
		this.map.centerAndZoom(nbcbPoint, zoom);
	};
	this.nbcbAddMapTypeControl = function () {
		this.map.addControl(new BMap.MapTypeControl());
	};
	this.nbcbSetCurrentCity = function (city) {
		this.map.setCurrentCity(city);
	};
	this.nbcbEnableScrollWheelZoom = function (flag) {
		this.map.enableScrollWheelZoom(flag);
	};
	this.nbcbAddOverViewControl = function () {
		var overView = new BMap.OverviewMapControl();
		this.map.addControl(new BMap.OverviewMapControl());
	};
	this.nbcbAddNavigationControl = function () {
		var top_left_navigation = new BMap.NavigationControl();
		this.map.addControl(top_left_navigation);
	};
	this.nbcbAddScaleControl = function () {
		var bottom_left_control = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
		this.map.addControl(bottom_left_control);
	};
}
NBCBMap.Map = function (container) {
	return new NBCBMap(container);
};
NBCBMap.NBCBPoint = function (lng, lat) {
	return new BMap.Point(lng, lat);
};

