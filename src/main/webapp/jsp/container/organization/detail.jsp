

<!-- 

如果你应该用了easyui中加载外部界面的组件，例如：panel,window，dailog,tabs等。
请确保你引入的界面是一个html片段。
html片段正确的写法再次提醒不要出现<html><head><body>三个标签：
	<script type="text/javascript" src="test.js"></script>
	<link ref="css"/>
	<style>还可以写点样式</style>
	<script>alert("我是外部加载的html片段");</script>
	<div><p>我是外部加载的html片段</p></div>
 -->

<script type="text/javascript">
	$(function() {
		$('#pg').propertygrid({    
		    url: '${pageContext.request.contextPath}/z2w/organization/getInfoJsonByOid?oid=${param.oid}',    
		    scrollbarSize: 0    
		}); 
	});
	
</script>
<div style="width:100%;height: 100%">
	<table id="pg" style="width:400px"></table>
</div>  


