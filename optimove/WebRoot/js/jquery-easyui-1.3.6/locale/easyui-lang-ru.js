if ($.fn.pagination){
	$.fn.pagination.defaults.beforePageText = '§³§ä§â§Ñ§ß§Ú§è§Ñ';
	$.fn.pagination.defaults.afterPageText = '§Ú§Ù {pages}';
	$.fn.pagination.defaults.displayMsg = '§±§â§à§ã§Þ§à§ä§â {from} §Õ§à {to} §Ú§Ù {total} §Ù§Ñ§á§Ú§ã§Ö§Û';
}
if ($.fn.datagrid){
	$.fn.datagrid.defaults.loadMsg = '§°§Ò§â§Ñ§Ò§Ñ§ä§í§Ó§Ñ§Ö§ä§ã§ñ, §á§à§Ø§Ñ§Ý§å§Û§ã§ä§Ñ §Ø§Õ§Ú§ä§Ö ...';
}
if ($.fn.treegrid && $.fn.datagrid){
	$.fn.treegrid.defaults.loadMsg = $.fn.datagrid.defaults.loadMsg;
}
if ($.messager){
	$.messager.defaults.ok = '§°§Ü';
	$.messager.defaults.cancel = '§©§Ñ§Ü§â§í§ä§î';
}
if ($.fn.validatebox){
	$.fn.validatebox.defaults.missingMessage = '§¿§ä§à §á§à§Ý§Ö §ß§Ö§à§Ò§ç§à§Õ§Ú§Þ§à.';
	$.fn.validatebox.defaults.rules.email.message = '§±§à§Ø§Ñ§Ý§å§Û§ã§ä§Ñ §Ó§Ó§Ö§Õ§Ú§ä§Ö §Ü§à§â§â§Ö§Ü§ä§ß§í§Û e-mail §Ñ§Õ§â§Ö§ã.';
	$.fn.validatebox.defaults.rules.url.message = '§±§à§Ø§Ñ§Ý§å§Û§ã§ä§Ñ §Ó§Ó§Ö§Õ§Ú§ä§Ö §Ü§à§â§â§Ö§Ü§ä§ß§í§Û URL.';
	$.fn.validatebox.defaults.rules.length.message = '§±§à§Ø§Ñ§Ý§å§Û§ã§ä§Ñ §Ó§Ó§Ö§Õ§Ú§ä§Ö §Ù§Ñ§é§Ö§ß§Ú§Ö §Þ§Ö§Ø§Õ§å {0} §Ú {1}.';
	$.fn.validatebox.defaults.rules.remote.message = '§±§à§Ø§Ñ§Ý§å§Û§ã§ä§Ñ §Ú§ã§á§â§Ñ§Ó§ä§Ö §ï§ä§à §á§à§Ý§Ö.';
}
if ($.fn.numberbox){
	$.fn.numberbox.defaults.missingMessage = '§¿§ä§à §á§à§Ý§Ö §ß§Ö§à§Ò§ç§à§Õ§Ú§Þ§à.';
}
if ($.fn.combobox){
	$.fn.combobox.defaults.missingMessage = '§¿§ä§à §á§à§Ý§Ö §ß§Ö§à§Ò§ç§à§Õ§Ú§Þ§à.';
}
if ($.fn.combotree){
	$.fn.combotree.defaults.missingMessage = '§¿§ä§à §á§à§Ý§Ö §ß§Ö§à§Ò§ç§à§Õ§Ú§Þ§à.';
}
if ($.fn.combogrid){
	$.fn.combogrid.defaults.missingMessage = '§¿§ä§à §á§à§Ý§Ö §ß§Ö§à§Ò§ç§à§Õ§Ú§Þ§à.';
}
if ($.fn.calendar){
	$.fn.calendar.defaults.firstDay = 1;
	$.fn.calendar.defaults.weeks  = ['§£','§±','§£','§³','§¹','§±','§³'];
	$.fn.calendar.defaults.months = ['§Á§ß§Ó', '§¶§Ö§Ó', '§®§Ñ§â', '§¡§á§â', '§®§Ñ§Û', '§ª§ð§ß', '§ª§ð§Ý', '§¡§Ó§Ô', '§³§Ö§ß', '§°§Ü§ä', '§¯§à§ñ', '§¥§Ö§Ü'];
}
if ($.fn.datebox){
	$.fn.datebox.defaults.currentText = '§³§Ö§Ô§à§Õ§ß§ñ';
	$.fn.datebox.defaults.closeText = '§©§Ñ§Ü§â§í§ä§î';
	$.fn.datebox.defaults.okText = '§°§Ü';
	$.fn.datebox.defaults.missingMessage = '§¿§ä§à §á§à§Ý§Ö §ß§Ö§à§Ò§ç§à§Õ§Ú§Þ§à.';
}
if ($.fn.datetimebox && $.fn.datebox){
	$.extend($.fn.datetimebox.defaults,{
		currentText: $.fn.datebox.defaults.currentText,
		closeText: $.fn.datebox.defaults.closeText,
		okText: $.fn.datebox.defaults.okText,
		missingMessage: $.fn.datebox.defaults.missingMessage
	});
}
