<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<head>
<title>View organization</title>

<%@include file="/jsp/common.jsp" %> 

<script type="text/javascript">
	$(function() {
		$('#mm').menu('show', {    
			  left: 200,    
			  top: 100    
			}); 
	});
	
</script>
</head>


<body class="easyui-layout">
<div id="mm" class="easyui-menu" style="width:120px;">   
    <div>New</div>   
    <div>   
        <span>Open</span>   
        <div style="width:150px;">   
            <div><b>Word</b></div>   
            <div>Excel</div>   
            <div>PowerPoint</div>   
        </div>   
    </div>   
    <div data-options="iconCls:'icon-save'">Save</div>   
    <div class="menu-sep"></div>   
    <div>Exit</div>   
</div> 
	 
</body>
