if ($.fn.pagination) {
    $.fn.pagination.defaults.beforePageText = 'Sida';
    $.fn.pagination.defaults.afterPageText = 'av {pages}';
    $.fn.pagination.defaults.displayMsg = 'Visar {from} till {to} av {total} poster';
}
if ($.fn.datagrid) {
    $.fn.datagrid.defaults.loadMsg = 'Bearbetar, v?nligen v?nta ...';
}
if ($.fn.treegrid && $.fn.datagrid) {
    $.fn.treegrid.defaults.loadMsg = $.fn.datagrid.defaults.loadMsg;
}
if ($.messager) {
    $.messager.defaults.ok = 'Ok';
    $.messager.defaults.cancel = 'Avbryt';
}
if ($.fn.validatebox) {
    $.fn.validatebox.defaults.missingMessage = 'Detta f?lt ?r obligatoriskt.';
    $.fn.validatebox.defaults.rules.email.message = 'V?nligen ange en korrekt e-post adress.';
    $.fn.validatebox.defaults.rules.url.message = 'V?nligen ange en korrekt URL.';
    $.fn.validatebox.defaults.rules.length.message = 'V?nligen ange ett nummer mellan {0} och {1}.';
    $.fn.validatebox.defaults.rules.remote.message = 'V?nligen ?tg?rda detta f?lt.';
}
if ($.fn.numberbox) {
    $.fn.numberbox.defaults.missingMessage = 'Detta f?lt ?r obligatoriskt.';
}
if ($.fn.combobox) {
    $.fn.combobox.defaults.missingMessage = 'Detta f?lt ?r obligatoriskt.';
}
if ($.fn.combotree) {
    $.fn.combotree.defaults.missingMessage = 'Detta f?lt ?r obligatoriskt.';
}
if ($.fn.combogrid) {
    $.fn.combogrid.defaults.missingMessage = 'Detta f?lt ?r obligatoriskt.';
}
if ($.fn.calendar) {
    $.fn.calendar.defaults.weeks = ['S?n', 'M?n', 'Tis', 'Ons', 'Tors', 'Fre', 'L?r'];
    $.fn.calendar.defaults.months = ['Jan', 'Feb', 'Mar', 'Apr', 'Maj', 'Jun', 'Jul', 'Aug', 'Sep', 'Okt', 'Nov', 'Dec'];
}
if ($.fn.datebox) {
    $.fn.datebox.defaults.currentText = 'I dag';
    $.fn.datebox.defaults.closeText = 'St?ng';
    $.fn.datebox.defaults.okText = 'Ok';
    $.fn.datebox.defaults.missingMessage = 'Detta f?lt ?r obligatoriskt.';
}
if ($.fn.datetimebox && $.fn.datebox) {
    $.extend($.fn.datetimebox.defaults, {
        currentText: $.fn.datebox.defaults.currentText,
        closeText: $.fn.datebox.defaults.closeText,
        okText: $.fn.datebox.defaults.okText,
        missingMessage: $.fn.datebox.defaults.missingMessage
    });
}
