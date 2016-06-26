<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<head>
<title>Zero2Web</title>

<meta charset="utf-8">


<link rel="stylesheet" href="js/themes/default/easyui.css" type="text/css" media="all">
<link rel="stylesheet" type="text/css" href="js/themes/icon.css">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>


<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:bundle basename="com.z2w.common.resource.NavigatorRB">
	<fmt:message key="object.navigator.title" var="navigatorTitle" />
	<fmt:message key="object.menu1.title" var="menu1Title" />
	<fmt:message key="object.system_config_actions.title" var="menu2Title" />
	<fmt:message key="object.orgnazition.title" var="menu3Title" />
</fmt:bundle>

<script type="text/javascript">
	$(function() {
		var location = (window.location+'').split('/');  
		var basePath = location[0]+'//'+location[2]+'/'+location[3]; 
		
		$('#organization').panel({
			  tools: [{
			    iconCls:'icon-view-list',
			    handler:function(){
			    	$('#navigation-panel').accordion('select','${menu3Title}');
			    	window.location.href=basePath  + '/#z2w/organization/view';
			   // 	$('#maincontent').panel('setTitle',"${menu3Title}");
				}    
			  }]    
		});  
		
	   $('#list_orgnazition').tree({
            checkbox: false,   
            url: '${pageContext.request.contextPath}/z2w/organization/listOrgNavOpt' ,

			onClick : function(node) {
				if(node.url){
					window.location.href=basePath  + '/#z2w/' + node.url;
				//	$('#maincontent').panel('setTitle',"${menu1Title}>>"+node.text);
				}
			}
		});
		
        $('#menu001').tree({
            checkbox: false,   
            url: '${pageContext.request.contextPath}/z2w/action/modelActions?id=show_navigator_actions' ,

			onClick : function(node) {
				if(node.url){
					window.location.href=basePath  + '/#z2w/' + node.url;
				//	$('#maincontent').panel('setTitle',"${menu1Title}>>"+node.text);
				}
			}
		});
        
        $('#system_config').tree({
            checkbox: false,   
            url: '${pageContext.request.contextPath}/z2w/action/modelActions?id=system_config_actions' ,

			onClick : function(node) {
				if(node.url){
					window.location.href=basePath  + '/#z2w/' + node.url;
				//	$('#maincontent').panel('setTitle',"${menu2Title}>>"+node.text);
				}
			}
		});
        
        handleHashChange();
	});
	
	window.onhashchange=function(){
		handleHashChange();
	}

	var handleHashChange = function(){
		var hash = window.location.hash;
		var rightUrl = hash.replace("#", "");
		resetIframe(rightUrl);
	}

	function resetIframe(rightUrl){
		var iframe =$("#maincontentframe")[0];
		document.getElementById("maincontent").removeChild(iframe);//先将旧的移除
		
		var newFrame = document.createElement("iframe");//创建新的iframe，id不变
		newFrame.setAttribute("id","maincontentframe");
		newFrame.setAttribute("src",rightUrl);
		newFrame.setAttribute("style","width:100%;height:100%;border:0px solid #ccc;");
	    document.getElementById("maincontent").appendChild(newFrame);
	    
	    newFrame.onload = function() { //将iframe的title赋值到父窗口
	    	var title = newFrame.contentWindow.document.title;
	    	document.title = title;
	    };
	}
	
	function gohome(){
		var location = (window.location+'').split('/');  
		var basePath = location[0]+'//'+location[2]+'/'+location[3]; 
		window.location.href=basePath;
	}
</script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height: 60px">
		<div style="cursor: pointer" onclick="gohome()">
			<h1>Zero To Web</h1>
		</div>
	</div>
	<div data-options="region:'west',title:'${navigatorTitle}',split:true" id="navigation-panel" class="easyui-accordion" style="width: 200px">
		<div id="organization" title="${menu3Title}" data-options="iconCls:'icon-organization'">
			<ul id="list_orgnazition"></ul>
		</div>

		<div title="${menu2Title}" data-options="iconCls:'icon-reload'">
			<ul id="system_config"></ul>
		</div>
		<div title="${menu1Title}" data-options="iconCls:'icon-save'">
			<ul id="menu001"></ul>
		</div>
	</div>

	<div id="maincontent" data-options="region:'center',title:'center'">
		<iframe id="maincontentframe" style="width: 100%; height: 100%; border: 0px solid #ccc;"></iframe>
	</div>

</body>
