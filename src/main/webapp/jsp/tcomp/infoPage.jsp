<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<head>
<title>View organization</title>

<%@include file="/jsp/common.jsp"%>

<script type="text/javascript">
	$(function() {
		$.ajax({
			cache: false,//必须不要缓存，否则只执行一次
			url : "${pageContext.request.contextPath}/z2w/action/modelActions?id=show_navigator_actions",
			success : function(result) {
				var ja = jQuery.parseJSON(result);
				
				var currentParent;
		        $.each(ja, function (n, value) {//遍历json数组
		        	currentParent = $('#mm').menu('appendItem', {
						text: value.text,
						iconCls: 'icon-ok'
					});
		        	console.log(currentParent);
		        
			        if(value.children){
			          	$.each(value.children, function(index, json) {
			          		var p0 = currentParent[0];
			          		var item = $('#mm').menu('findItem', value.text);  
			          		console.log(item);

			          		$('#mm').menu('appendItem', {
			          			parent: item.target,
								text: json.text,
								iconCls: 'icon-ok'
							});
			          		
			/*            		if(json.children){
			          			$.each(value.children, function(index, json1) {
					          		var item1 = $('#mm').menu('findItem', json.text);  
					          		$('#mm').menu('appendItem', {
					          			parent: item1.target,
										text: json1.text,
										iconCls: 'icon-ok'
									});
					          		
					          		$.each(value.children, function(index, json2) {
						          		var item2 = $('#mm').menu('findItem', json1.text);  
						          		$('#mm').menu('appendItem', {
						          			parent: item2.target,
											text: json2.text,
											iconCls: 'icon-ok'
										});
						          	}); 
					          	}); 
			          		}  */
			          	}); 
			        } 
		        }); 
			}
		});
		
		$('#mb').linkbutton('resize', {
			height: '20',
		});
		
		$('#mm').menu('appendItem', {
			text: '新菜单项',
			iconCls: 'icon-ok'
		});
		
		$('#mm').menu('appendItem', {
			text: '新菜单项',
			iconCls: 'icon-ok',
		});
		
		var item = $('#mm').menu('findItem', '新菜单项');  // 查找“打开”项
		$('#mm').menu('appendItem', {
			parent: item.target,  // 设置父菜单元素
			text: '打开Excel文档',
			iconCls: 'icon-excel',
			onclick: function(){alert('提示：打开Excel文档！')}
		});


	});
</script>
</head>


<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height: 30px">
		<a href="javascript:void(0)" id="mb" class="easyui-menubutton" data-options="menu:'#mm',plain:false">actions</a>
		<div id="mm" style="width: 150px;">
			<div data-options="iconCls:'icon-undo'">Undo</div>
			<div data-options="iconCls:'icon-redo'">Redo</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-remove'">Delete</div>
			<div>Select All</div>
			<div>New</div>
			<div>
				<span>Open</span>
				<div>
					<div>
						<b>Word</b>
					</div>
					<div>Excel</div>
					<div>PowerPoint</div>
				</div>
			</div>
			<div data-options="iconCls:'icon-save'">Save</div>
			<div class="menu-sep"></div>
			<div>Exit</div>
		</div>
	</div>


	<div data-options="region:'center',border:false"></div>

</body>
