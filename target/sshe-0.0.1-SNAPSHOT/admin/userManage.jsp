<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#admin_usermanage_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/userAction!datagrid.action',
			frozenColumns: [[{
				field : 'id',
				title : '编号',
				width : 150,
				align: 'center',
				checkbox: true
			},{
				field : 'username',
				title : '登陆名',
				width : 150,
				align: 'center',
				sortable: true
			}]],
			columns : [ [{
				field : 'createDate',
				title : '创建时间',
				width : 150,
				align: 'center'
			}, {
				field : 'modifyDate',
				title : '修改时间',
				width : 150,
				align: 'center'
			} ] ],
			fit: true,
			border: false,
			pagination:true,
			idField: 'id',
			fitColumns: true,
			checkOnSelect: false,
			selectOnCheck: false,
			singleSelect: true,
			toolbar: [{
				text: '添加',
				iconCls: 'icon-add',
				handler: function(){
					addUser();
				}
			},'-',{
				text: '删除',
				iconCls: 'icon-remove',
				handler: function(){}
			}]
		});
	});
	
	function addUser() {
		$('#admin_usermanage_adddialog').dialog('open');
		$('#admin_usermanage_addform input').val('');
	}
	
	function deleteUser() {
		var crows = $('#admin_usermanage_datagrid').datagrid('getChecked');
	}
	
	function searchUser() {
		$('#admin_usermanage_datagrid').datagrid('load', {
			 username: $('#admin_usermanage_layout input[name=username]').val()
		});
	}
	
	function clearParam() {
		$('#admin_usermanage_datagrid').datagrid('load', {});
		$('#admin_usermanage_layout input[name=username]').val('');
	}
</script>

<div id="admin_usermanage_layout" class="easyui-layout" data-options="fit:true, border:false" >
	<div data-options="region:'north', title:'用户查询', border:false" style="height:100px;">
		<input type="text" name="username" />
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search', plain:true" onclick="searchUser()">搜索</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel', plain:true" onclick="clearParam()">清空</a>
	</div>
	<div data-options="region:'center', border:false">
		<table id="admin_usermanage_datagrid"></table>
	</div>
</div>

<div id="admin_usermanage_adddialog" data-options="closed:true, modal:true, title:'添加用户', buttons:[{
					text:'关闭',
					iconCls: 'icon-cancel',
					handler:function(){
						$('#admin_usermanage_adddialog').dialog('close');
					}
				},{
				text:'添加',
				iconCls: 'icon-save',
				handler:function(){
					$('#admin_usermanage_addform').form('submit', {
						url:'${pageContext.request.contextPath}/userAction!add.action',
						success:function(data){
							var obj = jQuery.parseJSON(data);
						 	$('#admin_usermanage_adddialog').dialog('close');
						 	$('#admin_usermanage_datagrid').datagrid('appendRow', obj.object);
							$.messager.show({
								title:'提示',
								msg:obj.msg
							});
						}
					});
				}
			},]" class="easyui-dialog" style="width:550px;height:200px;">
	<form id="admin_usermanage_addform" method="post">
		<table class="submit_tab">
			<tr>
				<th>编号</th>
				<td><input type="text" readonly="readonly" name="id" /></td>
				<th>登录名</th>
				<td><input type="text" name="username" class="easyui-validatebox" data-options="required:true, missingMessage:'登录不能为空！'" /></td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input type="password" name="password" class="easyui-validatebox" data-options="required:true, missingMessage:'密码不能为空！'" /></td>
				<th>创建时间</th>
				<td><input type="text" readonly="readonly" class="easyui-datetimebox" name="createDate" /></td>
			</tr>
			<tr>
				<th>最后修改时间</th>
				<td><input type="text" readonly="readonly" class="easyui-datetimebox" name="modifyDate" /></td>
				<th></th>
				<td></td>
			</tr>
		</table>
	</form>
</div>