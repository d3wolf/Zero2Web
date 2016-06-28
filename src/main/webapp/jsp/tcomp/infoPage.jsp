<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<head>
<title>View organization</title>

<%@include file="/jsp/common.jsp"%>

<script type="text/javascript">
	$(function() {
		

		loadMenuFromServer({
			actionJsonUrl : "${pageContext.request.contextPath}/z2w/action/modelActionsMenu?id=show_navigator_actions",
			menuId : 'mm',
			cache : true
		});

		$('#mb').linkbutton('resize', {
			height : '20',
		});

	 	$('#mm').menu({ 
		    onClick:function(item){  
		    	if(item.name){
		    		$.messager.alert('警告',item.name);    
		    	}
		    }    
		});  
	});


</script>
</head>


<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height: 30px">
		<a href="javascript:void(0)" id="mb" class="easyui-menubutton" data-options="menu:'#mm',plain:false">actions</a>
		<div id="mm" style="width: 150px;">
			<div data-options="iconCls:'icon-save'">Save</div>
			<div class="menu-sep"></div>
			<div>Exit</div>
		</div>
	</div>


	<div data-options="region:'center',border:false"></div>

</body>
