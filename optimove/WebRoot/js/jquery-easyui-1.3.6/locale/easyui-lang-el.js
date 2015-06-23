if ($.fn.pagination){
	$.fn.pagination.defaults.beforePageText = '¦²¦Å¦Ë?¦Ä¦Á';
	$.fn.pagination.defaults.afterPageText = '¦Á¦Ð? {pages}';
	$.fn.pagination.defaults.displayMsg = '¦¥¦Ì¦Õ?¦Í¦É¦Ò¦Ç {from} ¦Å?? {to} ¦Á¦Ð? {total} ¦Á¦Í¦Ó¦É¦Ê¦Å?¦Ì¦Å¦Í¦Á';
}
if ($.fn.datagrid){
	$.fn.datagrid.defaults.loadMsg = '¦£?¦Í¦Å¦Ó¦Á¦É ¦¥¦Ð¦Å¦Î¦Å¦Ñ¦Ã¦Á¦Ò?¦Á, ¦°¦Á¦Ñ¦Á¦Ê¦Á¦Ë? ¦°¦Å¦Ñ¦É¦Ì?¦Í¦Å¦Ó¦Å ...';
}
if ($.fn.treegrid && $.fn.datagrid){
	$.fn.treegrid.defaults.loadMsg = $.fn.datagrid.defaults.loadMsg;
}
if ($.messager){
	$.messager.defaults.ok = '¦¥¦Í¦Ó?¦Î¦Å¦É';
	$.messager.defaults.cancel = '?¦Ê¦Ô¦Ñ¦Ï';
}
if ($.fn.validatebox){
	$.fn.validatebox.defaults.missingMessage = '¦³¦Ï ¦Ð¦Å¦Ä?¦Ï ¦Å?¦Í¦Á¦É ¦Ô¦Ð¦Ï¦Ö¦Ñ¦Å¦Ø¦Ó¦É¦Ê?.';
	$.fn.validatebox.defaults.rules.email.message = '¦°¦Á¦Ñ¦Á¦Ê¦Á¦Ë? ¦Å¦É¦Ò?¦Ã¦Å¦Ó¦Å ¦Ò¦Ø¦Ò¦Ó? ¦§¦Ë.¦¤¦É¦Å?¦È¦Ô¦Í¦Ò¦Ç.';
	$.fn.validatebox.defaults.rules.url.message = '¦°¦Á¦Ñ¦Á¦Ê¦Á¦Ë? ¦Å¦É¦Ò?¦Ã¦Å¦Ó¦Å ¦Ò¦Ø¦Ò¦Ó? ¦Ò?¦Í¦Ä¦Å¦Ò¦Ì¦Ï.';
	$.fn.validatebox.defaults.rules.length.message = '¦°¦Á¦Ñ¦Á¦Ê¦Á¦Ë? ¦Å¦É¦Ò?¦Ã¦Å¦Ó¦Å ¦Ó¦É¦Ì? ¦Ì¦Å¦Ó¦Á¦Î? {0} ¦Ê¦Á¦É {1}.';
	$.fn.validatebox.defaults.rules.remote.message = '¦°¦Á¦Ñ¦Á¦Ê¦Á¦Ë? ¦Ä¦É¦Ï¦Ñ¦È?¦Ò¦Ó¦Å ¦Á¦Ô¦Ó? ¦Ó¦Ï ¦Ð¦Å¦Ä?¦Ï.';
}
if ($.fn.numberbox){
	$.fn.numberbox.defaults.missingMessage = '¦³¦Ï ¦Ð¦Å¦Ä?¦Ï ¦Å?¦Í¦Á¦É ¦Ô¦Ð¦Ï¦Ö¦Ñ¦Å¦Ø¦Ó¦É¦Ê?.';
}
if ($.fn.combobox){
	$.fn.combobox.defaults.missingMessage = '¦³¦Ï ¦Ð¦Å¦Ä?¦Ï ¦Å?¦Í¦Á¦É ¦Ô¦Ð¦Ï¦Ö¦Ñ¦Å¦Ø¦Ó¦É¦Ê?.';
}
if ($.fn.combotree){
	$.fn.combotree.defaults.missingMessage = '¦³¦Ï ¦Ð¦Å¦Ä?¦Ï ¦Å?¦Í¦Á¦É ¦Ô¦Ð¦Ï¦Ö¦Ñ¦Å¦Ø¦Ó¦É¦Ê?.';
}
if ($.fn.combogrid){
	$.fn.combogrid.defaults.missingMessage = '¦³¦Ï ¦Ð¦Å¦Ä?¦Ï ¦Å?¦Í¦Á¦É ¦Ô¦Ð¦Ï¦Ö¦Ñ¦Å¦Ø¦Ó¦É¦Ê?.';
}
if ($.fn.calendar){
	$.fn.calendar.defaults.weeks = ['¦ª¦Ô¦Ñ','¦¤¦Å¦Ô','¦³¦Ñ¦É','¦³¦Å¦Ó','¦°¦Å¦Ì','¦°¦Á¦Ñ','¦²¦Á¦Â'];
	$.fn.calendar.defaults.months = ['¦©¦Á¦Í', '¦µ¦Å¦Â', '¦¬¦Á¦Ñ', '¦¡¦Ð¦Ñ', '¦¬¦Á?', '¦©¦Ï¦Ô', '¦©¦Ï¦Ô', '¦¡¦Ô¦Ã', '¦²¦Å¦Ð', '¦¯¦Ê¦Ó', '¦­¦Ï¦Å', '¦¤¦Å¦Ê'];
}
if ($.fn.datebox){
	$.fn.datebox.defaults.currentText = '¦²?¦Ì¦Å¦Ñ¦Á';
	$.fn.datebox.defaults.closeText = '¦ª¦Ë¦Å?¦Ò¦É¦Ì¦Ï';
	$.fn.datebox.defaults.okText = '¦¥¦Í¦Ó?¦Î¦Å¦É';
	$.fn.datebox.defaults.missingMessage = '¦³¦Ï ¦Ð¦Å¦Ä?¦Ï ¦Å?¦Í¦Á¦É ¦Ô¦Ð¦Ï¦Ö¦Ñ¦Å¦Ø¦Ó¦É¦Ê?.';
}
if ($.fn.datetimebox && $.fn.datebox){
	$.extend($.fn.datetimebox.defaults,{
		currentText: $.fn.datebox.defaults.currentText,
		closeText: $.fn.datebox.defaults.closeText,
		okText: $.fn.datebox.defaults.okText,
		missingMessage: $.fn.datebox.defaults.missingMessage
	});
}
