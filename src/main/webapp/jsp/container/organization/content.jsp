<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<head>
<title>View organization</title>

<%@include file="/jsp/common.jsp" %> 

<script type="text/javascript">
	$(function() {
		var tbVal;
		$.ajax({//获取标识栏位信息
			cache: false,//是否缓存
			url : "${pageContext.request.contextPath}/z2w/action/modelActions?id=show_navigator_actions",
			success : function(result) {
				tbVal = eval(result);//jQuery.parseJSON(result);
				console.log(tbVal);
				$('#showDocuments').datagrid({
				    url:'${pageContext.request.contextPath}/z2w/doc/getContainerDoc?containerOid=${param.orgnizationOid}',    
				    columns:[[
				        {field:'id',checkbox:true,},
				        {field:'name',title:'名称',width:100,sortable : true},  
				        {field:'number',title:'编号',width:100,sortable : true}, 
				        {field:'createTimestamp',title:'创建时间',width:120,formatter:formatFun},    
				        {field:'modefyTimestamp',title:'修改时间',width:120,formatter:formatFun}    
				    ]],
				    toolbar : tbVal,
				  /*   toolbar: [
				                { text: '增加', iconCls: 'icon-add', handler: function () {
				                	window.open('${pageContext.request.contextPath}/z2w/doc/create?containerOid=${param.orgnizationOid}');
				                } },
				                { text: '修改', iconCls: 'icon-edit', handler: function () {  } }], */
				}); 
			}
		});
		
	
	});
	
	function formatFun(value){
		var unixTimestamp = new Date(value);  
        return unixTimestamp.Format("YYYY-MM-DD HH:mm:SS");  
	}
</script>
</head>


<body class="easyui-layout">
	<table id="showDocuments"></table>  
</body>
