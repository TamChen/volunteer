<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.3.6/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.3.6/themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/common.css">

<script type="text/javascript" src="js/jquery-easyui-1.3.6/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.3.6/jquery.validatebox.fixed.js"></script>
<script type="text/javascript" src="js/common/my-util.js"></script>

<script type="text/javascript">

	function addTab(opts) {
		var t = $('#layout_center_tab');
		if(t.tabs('exists', opts.title)) {	//如果存在，就选中
			t.tabs('select', opts.title);
		} else {
			t.tabs('add', opts);
		}
	}
</script>

</head>
<body>
<body class="easyui-layout">
	<div data-options="region:'north', split:false" style="height: 100px;"></div>
	<div data-options="region:'south', split:false" style="height: 20px;">dsafdsf</div>
	<div data-options="region:'east',title:'East',split:true"
		style="width: 100px;"></div>
	<jsp:include page="layout/center.jsp"></jsp:include>

	<!--继续测试-->
	<!-- 登录页面 -->
	<jsp:include page="user/login.jsp"></jsp:include>
	<jsp:include page="user/regist.jsp"></jsp:include>

</body>
</body>
</html>
