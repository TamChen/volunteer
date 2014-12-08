<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function() {
	$('#user_login_form').form({
		url: '${pageContext.request.contextPath}/userAction!login.action',
		data: $('#user_login_form').serialize(),
		success: function(data) {
			var obj = jQuery.parseJSON(data);
			if(obj.success) {
				$('#user_login_dialog').dialog('close');
				$.messager.show({
					title:'提示',
					msg: obj.msg
				});
			} else {
				$.messager.alert('提示', obj.msg,'warning');
			}
		}
	});
	$('#user_login_form input').on('keyup', function(event) {
		if(event.keyCode == '13') {
			$('#user_login_form').submit();
		}
	});
	window.setTimeout(function() {
		$('#user_login_form input[name=username]').focus();
	}, 0);
});
</script>
<!-- 登录窗口 -->
<div id="user_login_dialog" class="easyui-dialog"
	data-options="title:'用户登录', closable:false, modal:true, 
    	draggable:false, buttons:[
    	{
			text:'注册',
			iconCls:'icon-add',
			handler:function(){
				$('#user_reg_form input').val('');
				var regDialog = $('#user_reg_dialog');
				regDialog.dialog('open');
				$('#user_reg_form input[name=username]').focus();
			}
		},{
			text:'登录',
			iconCls:'icon-ok',
			handler:function(){
				$('#user_login_form').submit();
			}
		}]">
	<form id="user_login_form" method="post">
		<table class="submit_tab">
			<tr>
				<th>账号：</th>
				<td><input type="text" name="username"
					class="easyui-validatebox"
					data-options="required:true, missingMessage:'账号必须输入'" /></td>
			</tr>
			<tr>
				<th>密码：</th>
				<td><input type="password" name="password"
					class="easyui-validatebox"
					data-options="required:true, missingMessage:'密码必须输入'" /></td>
			</tr>
		</table>
	</form>
</div>