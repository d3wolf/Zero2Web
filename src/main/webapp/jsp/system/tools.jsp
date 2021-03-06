<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<head>
<title>Tools</title>

<meta charset="utf-8">

<%@include file="/jsp/common.jsp" %> 

<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:bundle basename="com.z2w.action.resource.ActionRB">
	<fmt:message key="action.reload.title" var="reloadAction" />
	<fmt:message key="action.reload.description" var="reloadActionDesc" />

</fmt:bundle>

<script type="text/javascript">
	function reloadAction() {
		$.ajax({
			cache: false,//必须不要缓存，否则只执行一次
			url : "${pageContext.request.contextPath}/z2w/action/reload",
			success : function(result) {
			//	$.messager.alert('Hello',result);  
				alert("action reload completely");
			}
		});
	}
</script>

</head>
<body>
	<table>
		<tr>
			<td><a href="javascript:reloadAction()">${reloadAction}</a></td>
			<td>${ reloadActionDesc}</td>
		</tr>
	</table>
</body>
