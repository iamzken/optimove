var dataTree ;
$(function(){
	$.post(path+"/menu/menuTree.do",{grpId:grpId}, function(data) {
		//var dataTree = jQuery.parseJSON(data);
		//var dataTree = eval('(' + data + ')'); 
		dataTree=convertJson(data);
		//dataTree = JSON.parse(data);
		InitLeftMenu(dataTree);
		tabClose();
		tabCloseEven();
	});
	
	
})

function convertJson(data){
	if(typeof data === 'string'){
		data = jQuery.parseJSON(data);
	}
	return data;
}


//��ʼ�����
function InitLeftMenu(dataTree) {
	$("#nav").accordion({animate:false});//ΪidΪnav��div�����ַ���Ч������ȥ����̬����Ч��
    $.each(dataTree, function(i, n) {//$.each ����_menu�е�Ԫ��
		var menulist ='';
		menulist +='<ul>';
        $.each(n.menus, function(j, o) {
			menulist += '<li><div><a ref="'+o.menuid+'" href="#" rel="' + o.url + '" ><span class="icon '+o.icon+'" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div></li> ';
        })
		menulist += '</ul>';

		$('#nav').accordion('add', {
            title: n.menuname,
            content: menulist,
            iconCls: 'icon ' + n.icon
        });

    });

	$('.easyui-accordion li a').click(function(){//�������˵�ĳ��ѡ��ʱ�����ұ߳��ֶ��õ�����
		var tabTitle = $(this).children('.nav').text();//��ȡ������span�е�������Ϊ�´�tab�ı���
		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");//��ȡ������������ref�е�����
		var icon = getIcon(menuid,icon);
		//var icon = $(this).children('.icon').attr('class').trim().split(" ")[1];

		addTab(tabTitle,url,icon);//����tab
		$('.easyui-accordion li div').removeClass("selected");
		$(this).parent().addClass("selected");
	}).hover(function(){
		$(this).parent().addClass("hover");
	},function(){
		$(this).parent().removeClass("hover");
	});

	//ѡ�е�һ��
	var panels = $('#nav').accordion('panels');
	var t = panels[0].panel('options').title;
    $('#nav').accordion('select', t);
}
//��ȡ��ർ����ͼ��
function getIcon(menuid){
	var icon = 'icon ';
	$.each(dataTree, function(i, n) {
		 $.each(n.menus, function(j, o) {
		 	if(o.menuid==menuid){
				icon += o.icon;
			}
		 })
	})

	return icon;
}

function addTab(subtitle,url,icon){
	if(!$('#tabs').tabs('exists',subtitle)){
		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true,
			icon:icon
		});
	}else{
		$('#tabs').tabs('select',subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();
}

function createFrame(url)
{
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose()
{
	/*˫���ر�TABѡ�*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*Ϊѡ����Ҽ�*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}
//���Ҽ��˵��¼�
function tabCloseEven()
{
	//ˢ��
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#tabs').tabs('update',{
			tab:currTab,
			options:{
				content:createFrame(url)
			}
		})
	})
	//�رյ�ǰ
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//ȫ���ر�
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			$('#tabs').tabs('close',t);
		});
	});
	//�رճ���ǰ֮���TAB
	$('#mm-tabcloseother').click(function(){
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	//�رյ�ǰ�Ҳ��TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('ϵͳ��ʾ','���û����~~','error');
			alert('���û����~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//�رյ�ǰ����TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('��ͷ�ˣ�ǰ��û����~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//�˳�
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

//������Ϣ���� title:���� msgString:��ʾ��Ϣ msgType:��Ϣ���� [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
