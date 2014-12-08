<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div data-options="region:'west',split:false" style="width: 200px;">
	<div class="easyui-panel"
	data-options="title:'功能导航', fit:true, border:false">
	<div id="aa" class="easyui-accordion" data-options="fit:true, border:false">
		<div title="系统菜单" data-options="iconCls:'icon-save'" >
			<ul id="layou_west_tree" class="easyui-tree" 
			data-options="
			url:'${pageContext.request.contextPath }/menuAction!treeAll.action',
			parentField:'pid', 
			lines:true, 
			onLoadSuccess: function(node, data) {
				$(this).tree('collapseAll');
			},
			onClick: function(node){
				if(node.attributes.url) {
					var url = '${pageContext.request.contextPath }/' + node.attributes.url;
					addTab({
						title: node.text,
						href: url,
						loadingMessage: '正在加载，请稍后...',
						closable: true
					});
				}
			}"></ul>
			</div>
			<div title="Title2" data-options="iconCls:'icon-reload'">
				content2
			</div>
			<div title="Title3">content3</div>
		</div>
	</div>
</div>