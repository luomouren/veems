<#macro layout title="" active="">
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>GEM-View - ${title!}</title>
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<!-- Bootstrap 3.3.7 -->
	<link rel="stylesheet" href="/webjars/bootstrap/dist/css/bootstrap.css"/>
	<!-- Font Awesome -->
	<link rel="stylesheet" href="/webjars/font-awesome/css/font-awesome.css">
	<!-- Ionicons -->
	<link rel="stylesheet" href="/webjars/ionicons/dist/css/ionicons.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="/webjars/AdminLTE/dist/css/AdminLTE.css">
	<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
	<link rel="stylesheet" href="/webjars/AdminLTE/dist/css/skins/_all-skins.css">
	<!-- layer -->
	<link rel="stylesheet" href="/webjars/layer/dist/theme/default/layer.css">
	<!--表单校验-->
	<link rel="stylesheet" href="/webjars/layer/dist/theme/default/layer.css">
	<link rel="stylesheet" href="/webjars/bootstrapValidator/dist/css/bootstrapValidator.css"/>
	${css!}<#--具体ftl中用到的css-->

	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->

	<!-- Google Font -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>

<#--其中class为skin-blue表示用AdminLTE中蓝肤色-->
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

	<header class="main-header">
		<!-- Logo -->
		<a href="../../index.html" class="logo">
			<!-- mini logo for sidebar mini 50x50 pixels -->
			<span class="logo-mini"><b>G</b></span>
			<!-- logo for regular state and mobile devices -->
			<span class="logo-lg"><b>GEM-View</b></span>
		</a>
		<!-- Header Navbar: style can be found in header.less -->
		<nav class="navbar navbar-static-top">
			<!-- Sidebar toggle button-->
			<a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</a>

			<div class="navbar-custom-menu">
				<ul class="nav navbar-nav">

					<!-- User Account: style can be found in dropdown.less -->
					<li class="dropdown user user-menu">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<img src="/assets/img/user/avatar.png" class="user-image" alt="User Image">
							<span class="hidden-xs"><@shiro.principal property="realName"/></span>
						</a>
						<ul class="dropdown-menu">
							<!-- User image -->
							<li class="user-header">
								<img src="/assets/img/user/avatar.png" class="img-circle" alt="User Image">
								<p>
                              <@shiro.principal property="realName"/>
									<small><@shiro.principal property="userName"/></small>
								</p>
							</li>
							<!-- Menu Footer-->
							<li class="user-footer">
								<div class="pull-left">
									<a href="/user/updatePwd" class="btn btn-default btn-flat">修改密码</a>
								</div>
								<div class="pull-right">
									<a href="/logout" class="btn btn-default btn-flat">退出</a>
								</div>
							</li>

						</ul>
					</li>
				</ul>
			</div>
		</nav>
	</header>

	<!-- Left side column. contains the sidebar -->
	<aside class="main-sidebar">
		<!-- sidebar: style can be found in sidebar.less -->
		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					<img src="/assets/img/user/avatar.png" class="img-circle" alt="User Image">
				</div>
				<div class="pull-left info">
					<p><@shiro.principal property="realName"/></p>
					<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
				</div>
			</div>
		<#--用户的导航权限-->
			<input type="hidden" id="menuJson" value='<@shiro.principal property="menuJson"/>'/>
		<#--模块页面详情active值是多少，用于展开显示具体地模块导航-->
			<input type="hidden" id="activeInput" value='${active}'/>


			<!-- sidebar menu: : style can be found in sidebar.less -->
			<ul class="sidebar-menu" data-widget="tree">
				<li <#if active = "home">class="active" </#if>><a href="/index"><i class="fa fa-home"></i> <span>主页</span></a></li>
			</ul>





		</section>
		<!-- /.sidebar -->
	</aside>

	<!-- =============================================== -->

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
      <#nested >
	</div>
	<!-- /.content-wrapper -->

	<footer class="main-footer">
		<div class="pull-right hidden-xs">
			<b>Version</b> 1.0.0
		</div>
		<strong>Copyright &copy; 2018 <a href="http://www.guangyuanbj.com">Guangyuan</a>.</strong> All rights
		reserved.
	</footer>
</div>
<!-- ./wrapper -->


<!-- jQuery 3 -->
<script src="/webjars/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/webjars/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- SlimScroll ,菜单和页面中的滚动条样式-->
<script src="/webjars/jQuery-slimScroll/jquery.slimscroll.js"></script>
<!-- FastClick 触摸设备快速点击体验，不想兼容手机可以去掉 -->
<script src="/webjars/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="/webjars/AdminLTE/dist/js/adminlte.js"></script>
<!-- layer -->
<script src="/webjars/layer/dist/layer.js"></script>
<!--表单校验-->
<script src="/webjars/bootstrapValidator/dist/js/bootstrapValidator.js"></script>
<script src="/webjars/bootstrapValidator/dist/js/language/zh_CN.js"></script>

<script>
	$(document).ready(function () {
		$('.sidebar-menu').tree();
		$(".btn-back").click(function () {
			window.history.back();
		});
	})

	// 从数据库中获取用户的导航权限  start*********
	var  menuJson = JSON.parse($("#menuJson").val());
	function menuInit() {
		var menu = null;
		var html = null;
		var chidLen = null;
		var child = null;
		for (var i = 0; i < menuJson.length; i++) {
			menu = menuJson[i];
			html = $(' <li menu-id="' + i + '" class="treeview "><li>');
			$(".sidebar .sidebar-menu").append(html);

			html = $(' <a href="'+menu.url+'"> <i class="fa '+menu.icon+'"></i> <span>'+menu.permissionName
					+'</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i> </span> </a> <ul menuUl-id="'+i+'" class="treeview-menu"> </ul>');
			$('[menu-id="'+i+'"]').append(html);

			chidLen = menu.childList.length;
			for (var j=0;j<chidLen;j++){
				child = menu.childList[j];
				var childLiId = child.percode.split(":")[1];
				html = $('<li id="'+childLiId+'"><a href="'+child.url+'"><i class="fa  '+child.icon+'"></i> '+child.permissionName+'</a></li>');
				$('[menuUl-id="'+i+'"]').append(html);
			}
		}
	}
	menuInit();

	// 展开具体的导航
	var activeInput = $("#activeInput").val();
	$("#"+activeInput).attr("class","active");
	$("#"+activeInput).parent().parent().attr("class","active");
	// 从数据库中获取用户的导航权限  end*********
</script>



<#--具体ftl中用到的js-->
	${js!}
</body>
</html>
</#macro>