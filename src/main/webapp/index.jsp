<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<head>
<title>Zero2Web</title>

<meta charset="utf-8">
<link rel="stylesheet" href="js/themes/default/easyui.css" type="text/css" media="all">
<link rel="stylesheet" type="text/css" href="js/themes/icon.css">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>


<script type="text/javascript">
	$(function() {
		var location = (window.location+'').split('/');  
		var basePath = location[0]+'//'+location[2]+'/'+location[3]; 
		
        $('#menu001').tree({   
            checkbox: false,   
            url: '${pageContext.request.contextPath}/z2w/action/modelActions?id=show_navigator_actions' ,

			onClick : function(node) {
				if(node.url){
				window.location.href=basePath  + '/#z2w/action/' + node.url;
			//	$('#maincontent').panel('setTitle',"菜单1>>"+node.text);
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
		<div  style="cursor:pointer" onclick="gohome()"><h1>Zero To Web</h1></div>
	</div>
	<div data-options="region:'west',title:'导航栏',split:true" id="aa" class="easyui-accordion" style="width: 200px">
		<div title="菜单1" data-options="iconCls:'icon-save'">
		 <ul id="menu001"></ul>
		</div>
		<div title="菜单2" data-options="iconCls:'icon-reload'">content2</div>
		<div title="菜单3" data-options="iconCls:'icon-print'">content3</div>
	</div>
	
	<div id="maincontent" data-options="region:'center',title:'center'">
		<iframe id="maincontentframe" style="width:100%;height:100%;border:0px solid #ccc;"></iframe>
	</div>
	
</body>
