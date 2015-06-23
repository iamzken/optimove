if ($.fn.pagination){
	$.fn.pagination.defaults.beforePageText = 'Strana';
	$.fn.pagination.defaults.afterPageText = 'z {pages}';
	$.fn.pagination.defaults.displayMsg = 'Zobrazuji z��znam {from} a? {to} z {total}.';
}
if ($.fn.datagrid){
	$.fn.datagrid.defaults.loadMsg = 'Pracuji, ?ekejte pros��m��';
}
if ($.fn.treegrid && $.fn.datagrid){
	$.fn.treegrid.defaults.loadMsg = $.fn.datagrid.defaults.loadMsg;
}
if ($.messager){
	$.messager.defaults.ok = 'Ok';
	$.messager.defaults.cancel = 'Zru?it';
}
if ($.fn.validatebox){
	$.fn.validatebox.defaults.missingMessage = 'Toto pole je vy?adov��no.';
	$.fn.validatebox.defaults.rules.email.message = 'Zadejte, pros��m, platnou e-mailovou adresu.';
	$.fn.validatebox.defaults.rules.url.message = 'Zadejte, pros��m, platnou adresu URL.';
	$.fn.validatebox.defaults.rules.length.message = 'Zadejte, pros��m, hodnotu mezi {0} a {1}.';
}
if ($.fn.numberbox){
	$.fn.numberbox.defaults.missingMessage = 'Toto pole je vy?adov��no.';
}
if ($.fn.combobox){
	$.fn.combobox.defaults.missingMessage = 'Toto pole je vy?adov��no.';
}
if ($.fn.combotree){
	$.fn.combotree.defaults.missingMessage = 'Toto pole je vy?adov��no.';
}
if ($.fn.combogrid){
	$.fn.combogrid.defaults.missingMessage = 'Toto pole je vy?adov��no.';
}
if ($.fn.calendar){
	$.fn.calendar.defaults.weeks = ['N','P','?','S','?','P','S']; //ned��le pond��l�� ��ter? st?eda ?tvrtek p��tek sobota
	$.fn.calendar.defaults.months = ['led', '��nr', 'b?e', 'dub', 'kv��', '?vn', '?vc', 'srp', 'z��?', '?��j', 'lis', 'pro']; //leden ��nor b?ezen duben kv��ten ?erven ?ervenec srpen z��?�� ?��jen listopad prosinec
}
if ($.fn.datebox){
	$.fn.datebox.defaults.currentText = 'Dnes';
	$.fn.datebox.defaults.closeText = 'Zav?��t';
	$.fn.datebox.defaults.okText = 'Ok';
	$.fn.datebox.defaults.missingMessage = 'Toto pole je vy?adov��no.';
}
if ($.fn.datetimebox && $.fn.datebox){
	$.extend($.fn.datetimebox.defaults,{
		currentText: $.fn.datebox.defaults.currentText,
		closeText: $.fn.datebox.defaults.closeText,
		okText: $.fn.datebox.defaults.okText,
		missingMessage: $.fn.datebox.defaults.missingMessage
	});
}
