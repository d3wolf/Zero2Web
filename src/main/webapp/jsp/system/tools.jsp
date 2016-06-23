<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<head>
<title>Tools</title>

<meta charset="utf-8">


<link rel="stylesheet" href="${pageContext.request.contextPath}/js/themes/default/easyui.css" type="text/css" media="all">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/themes/icon.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}js/easyui-lang-zh_CN.js"></script>


<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:bundle basename="com.z2w.action.resource.ActionRB">
	<fmt:message key="action.reload.title" var="reloadAction" />
	<fmt:message key="action.reload.description" var="reloadActionDesc" />

</fmt:bundle>

<script type="text/javascript">
	function reloadAction() {
		$.ajax({
			url : "${pageContext.request.contextPath}/z2w/action/reload",
			success : function() {
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
