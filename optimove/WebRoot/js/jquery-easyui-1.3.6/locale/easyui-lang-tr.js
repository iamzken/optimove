if ($.fn.pagination){
    $.fn.pagination.defaults.beforePageText = 'Sayfa';
    $.fn.pagination.defaults.afterPageText = ' / {pages}';
    $.fn.pagination.defaults.displayMsg = '{from} ile {to} aras? g?steriliyor, toplam {total} kay?t';
}
if ($.fn.datagrid){
    $.fn.panel.defaults.loadingMessage = "Y¨¹kleniyor...";
}

if ($.fn.datagrid){
    $.fn.datagrid.defaults.loadingMessage = "Y¨¹kleniyor...";
    $.fn.datagrid.defaults.loadMsg = '??leminiz Yap?l?yor, l¨¹tfen bekleyin ...';
}
if ($.fn.treegrid && $.fn.datagrid){
    $.fn.treegrid.defaults.loadMsg = $.fn.datagrid.defaults.loadMsg;
}
if ($.messager){
    $.messager.defaults.ok = 'Tamam';
    $.messager.defaults.cancel = '?ptal';
}
if ($.fn.validatebox){
    $.fn.validatebox.defaults.missingMessage = 'Bu alan zorunludur.';
    $.fn.validatebox.defaults.rules.email.message = 'L¨¹tfen ge?erli bir email adresi giriniz.';
    $.fn.validatebox.defaults.rules.url.message = 'L¨¹tfen ge?erli bir URL giriniz.';
    $.fn.validatebox.defaults.rules.length.message = 'L¨¹tfen {0} ile {1} aras?nda bir de?er giriniz.';
    $.fn.validatebox.defaults.rules.remote.message = 'L¨¹tfen bu alan? d¨¹zeltiniz.';
}
if ($.fn.numberbox){
    $.fn.numberbox.defaults.missingMessage = 'Bu alan zorunludur.';
}
if ($.fn.combobox){
    $.fn.combobox.defaults.missingMessage = 'Bu alan zorunludur.';
}
if ($.fn.combotree){
    $.fn.combotree.defaults.missingMessage = 'Bu alan zorunludur.';
}
if ($.fn.combogrid){
    $.fn.combogrid.defaults.missingMessage = 'Bu alan zorunludur.';
}
if ($.fn.calendar){
    $.fn.calendar.defaults.weeks = ['Pz','Pt','Sa','?a','Pe','Cu','Ct'];
    $.fn.calendar.defaults.months = ['Oca', '?ub', 'Mar', 'Nis', 'May', 'Haz', 'Tem', 'A?u', 'Eyl', 'Eki', 'Kas', 'Ara'];
}
if ($.fn.datebox){
    $.fn.datebox.defaults.currentText = 'Bug¨¹n';
    $.fn.datebox.defaults.closeText = 'Kapat';
    $.fn.datebox.defaults.okText = 'Tamam';
    $.fn.datebox.defaults.missingMessage = 'Bu alan zorunludur.';
}
if ($.fn.datetimebox && $.fn.datebox){
    $.extend($.fn.datetimebox.defaults,{
        currentText: $.fn.datebox.defaults.currentText,
        closeText: $.fn.datebox.defaults.closeText,
        okText: $.fn.datebox.defaults.okText,
        missingMessage: $.fn.datebox.defaults.missingMessage
    });
    
    $.fn.datebox.defaults.formatter=function(date){
        var y=date.getFullYear();
        var m=date.getMonth()+1;
        var d=date.getDate();
        if(m<10){m="0"+m;}
        if(d<10){d="0"+d;}
        return d+"."+m+"."+y;
    };
}
