<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript">
	$(function() {
		$('#user_reg_form').form({
			url: '${pageContext.request.contextPath}/userAction!regist.action',
			data: $('#user_reg_form').serialize(),
			success: function(data) {
				var obj = jQuery.parseJSON(data);
				if(obj.success) {
					$('#user_reg_dialog').dialog('close');
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
		$('#user_reg_form input').on('keyup', function(event) {
			if(event.keyCode == '13') {
				$('#user_reg_form').submit();
			}
		});
	});
</script>

<!-- 注册窗口,默认隐藏 -->
<div id="user_reg_dialog" style="width: 350px;" class="easyui-dialog"
	data-options="title:'用户注册',
		closable:false, 
		modal:true, 
    	draggable:false, 
    	closed: true,
    	buttons:[
    	{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$('#user_reg_dialog').dialog('close');
			}
		},{
			text:'提交',
			iconCls:'icon-save',
			handler:function(){
				$('#user_reg_form').form('submit');
			}
		}]">
	<form id="user_reg_form" method="post">
		<table class="submit_tab";>
			<tr>
				<th>账号：</th>
				<td><input type="text" class="easyui-validatebox"
					data-options="required:true, missingMessage:'账号必须输入'"
					name="username" /></td>
			</tr>
			<tr>
				<th>密码：</th>
				<td><input type="password" id="password"
					class="easyui-validatebox"
					data-options="required:true, missingMessage:'密码必须输入'"
					name="password" /></td>
			</tr>
			<tr>
				<th>确认密码：</th>
				<td><input type="password" class="easyui-validatebox"
					data-options="required:true,validType: 'equals[\'#password\']', missingMessage:'确认密码必须输入'" /></td>
			</tr>
		</table>
	</form>
</div>