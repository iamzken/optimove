var dataTree ;
$(function() {
	$.post(path + "/menu/menuTree.do", {
		grpId : grpId
	}, function(data) {
		dataTree = convertJson(data);
		InitLeftMenu(dataTree);
	});

})

function convertJson(data){
	if(typeof data === 'string'){
		data = jQuery.parseJSON(data);
	}
	return data;
}

//初始化左侧
function InitLeftMenu(dataTree) {
	var menuIcon = new Array("fa-stack-exchange","fa-toggle-left","fa-codepen","fa-cube","fa-cubes","fa-database","fa-delicious","fa-file-audio-o","fa-area-chart","fa-newspaper-o","fa-pie-chart","fa-toggle-on","fa-wifi");
	var subMenuIcon = new Array("fa-adjust","fa-arrows","fa-asterisk","fa-ban","fa-bar-chart-o","fa-bookmark","fa-bookmark-o","fa-briefcase","fa-building-o","fa-bullseye","fa-calendar","fa-calendar-o","fa-check-circle","fa-check-circle-o","fa-check-square","fa-cloud","fa-cloud-download","fa-cloud-upload","fa-cog","fa-comments","fa-comments-o","fa-compass","fa-credit-card","fa-crop","fa-crosshairs");
	var menulist = '<li class="active"><a href="index.jsp"><i class="fa fa-home"></i><span class="nav-label">首页</span><span class="fa arrow"></span></a>';
	$.each(dataTree, function(i, n) {
		menulist += '<li><a href="#"><i class="fa ' + menuIcon[i] + '"></i><span class="nav-label">' + n.menuname + '</span><span class="fa arrow"></span></a>';
		menulist += '<ul class="nav nav-second-level" style="display: none;">';
		$.each(n.menus, function(j, o) {
			menulist += '<li><a ref="'+o.menuid+'" href="#" rel="' + o.url + '"><i class="fa ' + subMenuIcon[j] + '"></i>' + o.menuname + '</a></li>';
		})
		menulist += '</ul>';
		menulist += '</li>';
    });
	$("#side-menu").append(menulist);
	
	$('#side-menu').children("li").slice(1).click(function(event){
		$(this).siblings("li").slice(1).removeClass().children("ul").hide("slow","swing");
		$(this).addClass("active").children("ul").slideToggle("slow","swing");
		event.stopPropagation();
	});
	
	$('#side-menu li ul li').click(function(event){//当单击菜单某个选项时，在右边出现对用的内容
		var tabTitle = $(this).find("a").text();
		var url = $(this).find("a").attr("rel");
		var menuid = $(this).find("a").attr("ref");//获取超链接属性中ref中的内容
		var icon = getIcon(menuid,icon);
		$("#menuName").show().html("").append("<div style=\"margin-top:12px;\"><font size=\"5px\">" + tabTitle + "</font></div>");
		addTab(tabTitle,url,icon);//增加tab
		event.stopPropagation();
	});
}
//获取左侧导航的图标
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
	$("#mainTabs").html("").append(createFrame(url));
	
//	$("#mainIframe").load(function(){
//		$(this).height($("#mainTabs").height());
//	});
}

function createFrame(url)
{
	var s = '<iframe id="mainIframe" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
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
//绑定右键菜单事件
function tabCloseEven()
{
	//刷新
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
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			$('#tabs').tabs('close',t);
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
