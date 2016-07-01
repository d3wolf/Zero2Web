<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<head>
<title>View organization</title>

<%@include file="/jsp/common.jsp" %> 

<script type="text/javascript">
	$(function() {
		$('#pg').propertygrid({    
		    url: '${pageContext.request.contextPath}/z2w/organization/getInfoJsonByOid?oid=${param.oid}',    
		    scrollbarSize: 0    
		}); 
	});
	
</script>
</head>


<body class="easyui-layout">
<table id="pg" style="width:300px"></table>  

	 
</body>
