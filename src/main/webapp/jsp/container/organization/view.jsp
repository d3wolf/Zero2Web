<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<head>
<title>View organization</title>

<%@include file="/jsp/common.jsp" %> 

<script type="text/javascript">
	$(function() {
		$('#viewOrganizations').datagrid({    
		    url:'${pageContext.request.contextPath}/z2w/organization/getAll',    
		    columns:[[    
		        {field:'name',title:'名称',width:100},    
		        {field:'createTimestamp',title:'创建时间',width:120,formatter:formatFun},    
		        {field:'modefyTimestamp',title:'修改时间',width:120,formatter:formatFun}    
		    ]]    
		}); 
	});
	
	function formatFun(value){
		var unixTimestamp = new Date(value);  
        return unixTimestamp.Format("YYYY-MM-DD HH:mm:SS");  
	}
</script>
</head>


<body class="easyui-layout">
	<table id="viewOrganizations"></table>  
</body>
