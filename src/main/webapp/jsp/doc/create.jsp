<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<head>
<title>Create Simple Document</title>

<%@include file="/jsp/common.jsp"%>

<script type="text/javascript">
	$(function() {
	});
	function submit(){
		$('#ff').form('submit', {
		    url:"${pageContext.request.contextPath}/z2w/doc/doCreate",    
		    onSubmit: function(param){
		    	param.containerOid = '${param.containerOid}';   
		   // 	alert(param.containerOid);
		    	var valid = $('#ff').form('validate');
		    	if(!valid){
		    		return false;
		    	} 
		    },    
		    success:function(data){  
		    	window.opener.refresh();
		    }
		});
	}
</script>
</head>


<body class="easyui-panel">
	<form id="ff" method="post">
		<table>
			<tr>
				<td><label for="name">Name:</label></td>
				<td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td>
			</tr>
			<tr>
				<td><label for="number">number:</label></td>
				<td><input class="easyui-validatebox" type="text" name="number" data-options="required:true" /></td>
			</tr>
			<tr>
				<td><a id="btn" class="easyui-linkbutton"  onclick="javascript:submit()">submit</a>  </td>
			</tr>
		</table>
	</form>
</body>


