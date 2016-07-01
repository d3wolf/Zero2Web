<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<head>
<title>View organization</title>

<%@include file="/jsp/common.jsp"%>

<script type="text/javascript">
	$(function() {
		
		$.ajax({//获取标识栏位信息
			cache: false,//是否缓存
			url : "${pageContext.request.contextPath}/z2w/tcomp/getObject?oid=${param.oid}",
			success : function(result) {
				console.log(result.displayIdentity);
				$('#displayIdentifier').html(result.displayIdentity);
			}
		});
		
		//加载action菜单
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
		

	 	$.ajax({//后台读取标签页信息
			cache: false,//是否缓存
			url : "${pageContext.request.contextPath}/z2w/action/modelActions?id=organization_infoPage_tabs",
			success : function(result) {
				 var ja = jQuery.parseJSON(result);
				 $.each(ja, function (n, value) {//遍历json数组
					$('#tt').tabs('add',{
						title:value.text,
						href:"${pageContext.request.contextPath}/z2w/"+value.url,
					}); 
				  });
			}
		});
				$('#tt').tabs({   
				    selected:0
				}); 
	});

</script>
</head>


<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height: 30px">
		<a href="javascript:void(0)" id="mb" class="easyui-menubutton" data-options="menu:'#mm',plain:false,duration:999999" style="float: left">actions</a>
		<div id="mm" style="width: 150px;"></div>
		<div id="displayIdentifier" style="float: left;padding-top:5px;padding-left:5px"></div>
	</div>


	<div data-options="region:'center',border:false">
		<div id="tt"></div>
	</div>

</body>
