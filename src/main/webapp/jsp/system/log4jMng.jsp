<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<head>
<title>Log4j</title>

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
	
	function getLevel(loggerid, textid){
		var logger = $('#'+loggerid).combo('getValue');
		
		$.ajax({
			cache: false,//必须不要缓存，否则只执行一次
			url : "${pageContext.request.contextPath}/z2w/log4j/getLevel?target="+logger,
			success : function(result) {
				$('#'+textid).html(jQuery.parseJSON(result).levelStr);
			}
		});
	}
	
	function setLevel(loggerid, levelid, textid){
		var logger = $('#'+loggerid).combo('getValue');
		var level = $('#'+levelid).combo('getValue');

		if(logger == null || logger == ''){
			$.messager.alert('error','a logger is needed','error');
			return;
		}
		
		$.ajax({
			cache: false,//必须不要缓存，否则只执行一次
			url : "${pageContext.request.contextPath}/z2w/log4j/setLevel?levelStr="+level+"&target="+logger,
			success : function(result) {
				$('#'+textid).html(jQuery.parseJSON(result).msg);
			}
		});
	}

</script>

</head>
<body>
	<table>
		<tr>
			<td>System Loggers:</td>
			<td><input id="system-loggers" style="width: 400px"></td>
			<td><a class="easyui-linkbutton" onclick="javascript:getLevel('system-loggers','system-loggers-level')">GET</a></td>
			<td><a class="easyui-linkbutton" onclick="javascript:setLevel('system-loggers','system-level-select','system-loggers-level')">SET</a></td>
			<td><select id="system-level-select" class="easyui-combobox" name="dept" style="width: 90px;">
					<option>OFF</option>
					<option>FATAL</option>
					<option>ERROR</option>
					<option>WARN</option>
					<option>INFO</option>
					<option>DEBUG</option>
					<option>TRACE</option>
					<option>ALL</option>
			</select></td>
			<td id="system-loggers-level"></td>
		</tr>
		<tr>
			<td>Customer Loggers:</td>
			<td><input id="customer-loggers" style="width: 400px"></td>
			<td><a class="easyui-linkbutton" onclick="javascript:getLevel('customer-loggers','customer-loggers-level')">GET</a></td>
			<td><a class="easyui-linkbutton" onclick="javascript:setLevel('customer-loggers','customer-level-select','customer-loggers-level')">SET</a></td>
			<td><select id="customer-level-select" class="easyui-combobox" name="dept" style="width: 90px;">
					<option>OFF</option>
					<option>FATAL</option>
					<option>ERROR</option>
					<option>WARN</option>
					<option>INFO</option>
					<option>DEBUG</option>
					<option>TRACE</option>
					<option>ALL</option>
			</select></td>
			<td id="customer-loggers-level"></td>
		</tr>
	</table>
</body>
