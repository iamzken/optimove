<%@ page language="java" contentType="text/html; charset=GBK"%>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>������ͼƽ̨</title>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK" />

    <jsp:include page="/module/common/comm_main.jsp"></jsp:include>
    <jsp:include page="/module/common/commHplus.jsp"></jsp:include>
    
    <script type="text/javascript" src="js/menutree.js" charset="GBK"></script>
	
	<script type="text/javascript">
		var grpId = '<%=request.getSession().getAttribute("grpId")%>';
		var path = '<%=request.getContextPath()%>';
		
		$(function(){
			$("#i_search").click(function(event){
				$(this).siblings("input").toggle(function(){
					$("#i_search").animate({left: '+110px'}, "slow");
					$(this).animate({left: '+100px'}, "slow");
				},function(){
					$("#i_search").animate({left: '-110px'}, "slow");
					$(this).animate({left: '-100px'}, "slow");
				});
			});
			
			$('#loginOut').click(function() {
				$.messager.confirm('ϵͳ��ʾ', '��ȷ��Ҫ�˳����ε�¼��?', function(isOkPressed) {
					if (isOkPressed) {
						location.href = 'logout.do';
					}
				});
			})
			
			WinMove();
	        setTimeout(function () {
	            $.gritter.add({
	                title: '����2��δ����Ϣ',
	                text: '��ǰ��<a href="#" class="text-warning">�ռ���</a>�鿴��������',
	                time: 10000
	            });
	        }, 2000);
	        $('.chart').easyPieChart({
	            barColor: '#FF4500',
	            trackColor: '#bfbfbf',
	            //scaleColor: false,
	            scaleLength: 5,
	            lineWidth: 8,
	            size: 120
	        });
	        $('.chart2').easyPieChart({
	            barColor: '#DA7096',
	            trackColor: '#bfbfbf',
	            //scaleColor: false,
	            scaleLength: 5,
	            lineWidth: 8,
	            size: 120
	        });
	        $('.chart3').easyPieChart({
	            barColor: '#4488BC',
	            trackColor: '#bfbfbf',
	            //scaleColor: false,
	            scaleLength: 5,
	            lineWidth: 8,
	            size: 120
	        });
	        
	        var data1 = [
                [0, 4], [1, 8], [2, 5], [3, 10], [4, 4], [5, 34], [6, 5], [7, 11], [8, 6], [9, 11], [10, 30], [11, 10], [12, 13], [13, 4], [14, 3], [15, 3], [16, 6]
            ];
            var data2 = [
                [0, 1], [1, 0], [2, 2], [3, 0], [4, 1], [5, 3], [6, 1], [7, 5], [8, 2], [9, 3], [10, 21], [11, 1], [12, 0], [13, 14], [14, 8], [15, 0], [16, 0]
            ];
            var data3 = [
                [0, 1], [1, 6], [2, 2], [3, 6], [4, 1], [5, 6], [6, 1], [7, 5], [8, 2], [9, 3], [10, 2], [11, 1], [12, 10], [13, 2], [14, 8], [15, 0], [16, 0]
            ];
            $("#flot-dashboard-chart").length && $.plot($("#flot-dashboard-chart"), [
                data1, data2, data3
            ], {
                series: {
                    lines: {
                        show: false,
                        fill: true
                    },
                    splines: {
                        show: true,
                        tension: 0.4,
                        lineWidth: 1,
                        fill: 0.4
                    },
                    points: {
                        radius: 0,
                        show: true
                    },
                    shadowSize: 2
                },
                grid: {
                    hoverable: true,
                    clickable: true,
                    tickColor: "#d5d5d5",
                    borderWidth: 1,
                    color: '#d5d5d5'
                },
                colors: ["#FF4500", "#DA7096", "#4488BC"],
                xaxis: {},
                yaxis: {
                    ticks: 4
                },
                tooltip: false
            });
		});
	</script>
</head>
<body>
	<div id="wrapper">
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
					<li class="nav-header-new">
                        <div class="dropdown profile-element">
                        	<span>
                            	<img alt="image" src="img/logo.png" />
                             </span>
                        </div>
                        <div class="logo-element">
                        	�� +
                        </div>

                    </li>
					<!-- �˵� -->
                </ul>
            </div>
        </nav>
		
        <div id="page-wrapper" class="gray-bg dashbard-1">
        	<div id="header" class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
					<div class="navbar-header" style="margin-top: 4px;">
						<a class="navbar-minimalize minimalize-styl-2-new btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
					</div>
					<ul class="nav navbar-top-links navbar-right">
						<li class="dropdown">
							<i id="i_search" class="fa fa-search"></i>&nbsp;
							<input type="text" placeholder="����������Ҫ���ҵ����� ��" id="top-search" class="navbar-search-input" style="display: none;"/>
						</li>
						<li class="dropdown">
							<span class="m-r-sm text-muted welcome-message">
								<a href="index.jsp" title="������ҳ"><i class="fa fa-home"></i></a>
							</span>
						</li>
						<li class="dropdown">
							<a class="dropdown-toggle count-info" data-toggle="dropdown" href="index.jsp#">
								<i class="fa fa-envelope"></i><span class="label label-warning">16</span>
							</a>
							<ul class="dropdown-menu dropdown-messages">
								<li>
									<div class="dropdown-messages-box">
										<a href="#" class="pull-left"> <img alt="image"
											class="img-circle" src="img/a7.jpg">
										</a>
										<div class="media-body">
											<small class="pull-right">46Сʱǰ</small> <strong>С��</strong>
											��Ŀ�Ѵ������ <br> <small class="text-muted">3��ǰ
												2014.11.8</small>
										</div>
									</div>
								</li>
								<li class="divider"></li>
								<li>
									<div class="dropdown-messages-box">
										<a href="#" class="pull-left"> <img alt="image"
											class="img-circle" src="img/a4.jpg">
										</a>
										<div class="media-body ">
											<small class="pull-right text-navy">25Сʱǰ</small> <strong>��������</strong>
											����һ��������Ϣ <br> <small class="text-muted">����</small>
										</div>
									</div>
								</li>
								<li class="divider"></li>
								<li>
									<div class="text-center link-block">
										<a href="#"> <i class="fa fa-envelope"></i> <strong>
												�鿴������Ϣ</strong>
										</a>
									</div>
								</li>
							</ul></li>
						<li class="dropdown">
							<a class="dropdown-toggle count-info" data-toggle="dropdown" href="index.jsp#">
								<i class="fa fa-bell"></i><span class="label label-primary">8</span>
							</a>
							<ul class="dropdown-menu dropdown-alerts">
								<li>
									<a href="#">
										<div>
											<i class="fa fa-envelope fa-fw"></i> ����16��δ����Ϣ <span
												class="pull-right text-muted small">4����ǰ</span>
										</div>
									</a>
								</li>
								<li class="divider"></li>
								<li>
									<a href="#">
										<div>
											<i class="fa fa-qq fa-fw"></i> 3���»ظ� <span
												class="pull-right text-muted small">12����Ǯ</span>
										</div>
									</a>
								</li>
								<li class="divider"></li>
								<li>
									<div class="text-center link-block">
										<a href="#"> <strong>�鿴���� </strong> <i
											class="fa fa-angle-right"></i>
										</a>
									</div>
								</li>
							</ul>
						</li>
						<li class="dropdown">
							<a data-toggle="dropdown" class="dropdown-toggle">
								<span class="text-muted text-xs block">
									<img alt="image" class="img-circle" src="img/profile_small.jpg" />&nbsp;��������Ա<b class="caret"></b>
								</span>
							</a>
							<ul class="dropdown-menu animated fadeInRight m-t-xs">
								<!-- <li><a href="#">�޸�ͷ��</a></li> -->
								<li><a href="#"><i class="fa fa-gear"></i>&nbsp;��������</a></li>
								<li><a href="#"><i class="fa fa-envelope"></i>&nbsp;��ϵ����</a></li>
								<!-- <li><a href="#"><i class="fa fa-envelope"></i>&nbsp;����</a></li> -->
								<li class="divider"></li>
								<li><a href="#" id="loginOut"><i class="fa fa-sign-out"></i>&nbsp;��ȫ�˳�</a></li>
							</ul>
						</li>
					</ul>
				</nav>
			</div>
			
			<div id="menuName" class="row wrapper border-bottom white-bg dashboard-header" style="display: none; padding: 0 10px 10px 10px;">
                <!-- �˵����� -->
            </div>
            
            <div id="mainTabs" region="center" class="row  border-bottom gray-bg dashboard-header" fit="true" border="false" cache="false">
                <div style="padding-left: 20px; padding-right: 20px; padding-top: 10px;">
	                <div class="row">
	                    <div class="col-lg-12">
	                        <div class="ibox float-e-margins">
	                            <div class="ibox-title" style="font-size: 15px; padding: 13px 15px 14px;">
	                            	�Ϻ�����
	                            </div>
	                            <div class="ibox-content">
	                            	<div class="row">
								        <div class="col-lg-3-new">
						                    <div class="widget style1 navy-bg">
						                        <div class="row">
						                            <div class="col-xs-4">
						                                <i class="fa fa-sun-o fa-5x"></i>
						                            </div>
						                            <div class="col-xs-8 text-right">
						                                <span> ����һ�¶� </span>
						                                <h2 class="font-bold">6'C</h2>
						                            </div>
						                        </div>
						                    </div>
						                </div>
						                <div class="col-lg-3-new">
						                    <div class="widget style1 navy-bg">
						                        <div class="row">
						                            <div class="col-xs-4">
						                                <i class="fa fa-cloud fa-5x"></i>
						                            </div>
						                            <div class="col-xs-8 text-right">
						                                <span> ���ڶ��¶� </span>
						                                <h2 class="font-bold">10'C</h2>
						                            </div>
						                        </div>
						                    </div>
						                </div>
						                <div class="col-lg-3-new">
						                    <div class="widget style1 navy-bg">
						                        <div class="row">
						                            <div class="col-xs-4">
						                                <i class="fa fa-moon-o fa-5x"></i>
						                            </div>
						                            <div class="col-xs-8 text-right">
						                                <span> �������¶� </span>
						                                <h2 class="font-bold">11'C</h2>
						                            </div>
						                        </div>
						                    </div>
						                </div>
						                <div class="col-lg-3-new">
						                    <div class="widget style1 navy-bg">
						                        <div class="row">
						                            <div class="col-xs-4">
						                                <i class="fa fa-cloud fa-5x"></i>
						                            </div>
						                            <div class="col-xs-8 text-right">
						                                <span> �������¶� </span>
						                                <h2 class="font-bold">14'C</h2>
						                            </div>
						                        </div>
						                    </div>
						                </div>
						                <div class="col-lg-3-new">
						                    <div class="widget style1 navy-bg">
						                        <div class="row">
						                            <div class="col-xs-4">
						                                <i class="fa fa-asterisk fa-5x"></i>
						                            </div>
						                            <div class="col-xs-8 text-right">
						                                <span> �������¶� </span>
						                                <h2 class="font-bold">3'C</h2>
						                            </div>
						                        </div>
						                    </div>
						                </div>
					                </div>
				                </div>
	                        </div>
	                    </div>
	                </div>
                </div>
                <div style="padding-left: 20px; padding-right: 20px;">
	                <div class="row">
	                    <div class="col-lg-6">
	                        <div class="ibox float-e-margins">
	                        	<div class="ibox-title" style="font-size: 15px; padding: 13px 15px 14px;">
	                            	��ǰ����������ͼ
	                            </div>
	                            <div class="ibox-content">
	                            	<div class="row" style="padding-left: 35px; padding-right: 35px;">
	                            		<div class="flot-chart dashboard-chart">
					                        <div class="flot-chart-content" id="flot-dashboard-chart"></div>
					                    </div>
					                    <div class="row text-center">
					                        <div class="col-xs-4">
					                            <div class=" m-l-md">
					                                <span class="h4 font-bold m-t block"><font style="color: #FF4500"> 3400</font></span>
					                                <small class="text-muted m-b block">�Ŵ�ϵͳ</small>
					                            </div>
					                        </div>
					                        <div class="col-xs-4">
					                            <span class="h4 font-bold m-t block"><font style="color: #DA7096"> 2100</font></span>
					                            <small class="text-muted m-b block">����ϵͳ</small>
					                        </div>
					                        <div class="col-xs-4">
					                            <span class="h4 font-bold m-t block"><font style="color: #4488BC"> 1000</font></span>
					                            <small class="text-muted m-b block">���ÿ�ϵͳ</small>
					                        </div>
					                    </div>
	                            	</div>
                            	</div>
	                        </div>
                        </div>
                        <div class="col-lg-6">
	                        <div class="ibox float-e-margins">
	                        	<div class="ibox-title" style="font-size: 15px; padding: 13px 15px 14px;">
	                            	��ʷ�������Ǳ���
	                            </div>
	                            <div class="ibox-content">
	                            	<div class="row">
	                            		<div class="row text-center">
				                            <div class="col-lg-4">
				                            	<br><br><br>
				                            	<div class="row text-center">
					                				<font style="color: #FF4500">ϵͳ��������</font>
					                			</div>
					                			<br>
				                                <div class="chart easypiechart inline" data-percent="34">
				                                	<span class="easypie-text"><font style="color: #FF4500">3400</font></span>
				                                </div>
				                                <h5>�Ŵ�ϵͳ</h5>
				                                <br><br><br>
				                            </div>
				                            <div class="col-lg-4">
				                            	<br><br><br>
				                            	<div class="row text-center">
					                				<font style="color: #DA7096">ϵͳ��������</font>
					                			</div>
					                			<br>
				                                <div class="chart2 easypiechart inline" data-percent="21">
				                                	<span class="easypie-text"><font style="color: #DA7096">2100</font></span>
				                                </div>
				                                <h5>����ϵͳ</h5>
				                                <br><br><br>
				                            </div>
				                            <div class="col-lg-4">
				                            	<br><br><br>
				                            	<div class="row text-center">
					                				<font style="color: #4488BC">ϵͳ��������</font>
					                			</div>
					                			<br>
				                                <div class="chart3 easypiechart inline" data-percent="10">
				                                	<span class="easypie-text"><font style="color: #4488BC">1000</font></span>
				                                </div>
				                                <h5>���ÿ�ϵͳ</h5>
				                                <br><br><br>
				                            </div>
			                            </div>
	                            	</div>
                            	</div>
	                        </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
    </div>
</body>
</html>
