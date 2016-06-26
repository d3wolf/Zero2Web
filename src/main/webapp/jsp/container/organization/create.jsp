<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<head>
<title>Create Organization</title>

<%@include file="/jsp/common.jsp" %> 

<script type="text/javascript">
	$(function() {
		$('#system-loggers').combobox({    
		    url:'${pageContext.request.contextPath}/z2w/log4j/getTarget?type=system',    
		    valueField:'id',    
		    textField:'text'   
		});  
		$('#customer-loggers').combobox({    
		    url:'${pageContext.request.contextPath}/z2w/log4j/getTarget?type=customer',    
		    valueField:'id',    
		    textField:'text'   
		}); 
	});
	


</script>

</head>



<body class="easyui-layout">



	<div data-options="region:'center',title:'center'">
		<form id="ff" method="post">
			<div>
				<label for="name">Name:</label> <input class="easyui-validatebox" type="text" name="name" data-options="required:true" />
			</div>
		</form>
	</div>

</body>
