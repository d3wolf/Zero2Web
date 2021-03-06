/**扩展js使日期支持格式化*/
Date.prototype.Format = function(formatStr){   
    var str = formatStr;   
    var Week = ['日','一','二','三','四','五','六'];  
  
    str=str.replace(/yyyy|YYYY/,this.getFullYear());   
    str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));   
  
    str=str.replace(/MM/,this.getMonth()>9?this.getMonth().toString():'0' + this.getMonth());   
    str=str.replace(/M/g,this.getMonth());   
  
    str=str.replace(/w|W/g,Week[this.getDay()]);   
  
    str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());   
    str=str.replace(/d|D/g,this.getDate());   
  
    str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());   
    str=str.replace(/h|H/g,this.getHours());   
    str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());   
    str=str.replace(/m/g,this.getMinutes());   
  
    str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());   
    str=str.replace(/s|S/g,this.getSeconds());   
  
    return str;   
}

/**
 * 从后台加载menu菜单
 * @param {} options:<br>
 * actionJsonUrl-获取菜单json数据的url<br>
 * menuId-菜单id<br>
 * cache-ajax cache参数,默认false<br>
 */
function loadMenuFromServer(options){
	$.ajax({
		cache: options.cache ? options.cache : false,//是否缓存
		url : options.actionJsonUrl,
		success : function(result) {
			var ja = jQuery.parseJSON(result);
			
	        $.each(ja, function (n, value) {//遍历json数组
	        	
	        	if(value.text=='separator'){//处理分割线
					$('#'+options.menuId).menu('appendItem', {
		        		separator:true
					});
	        	}else{
		        	$('#'+options.menuId).menu('appendItem', {
		        		id : value.id,
						text: value.text,
						iconCls: value.iconCls ? value.iconCls : 'icon-blank',
						name:value.url
					});
	        	}
				
		        if(value.children){//如果有子菜单，递归生成子菜单
		        	createChildrenMenu(options.menuId, value.id, value.children);
		        }   
	        }); 
		}
	});
	
}
/**
 * 使用递归构造menu菜单
 * @param {} menuId
 * @param {} parentId
 * @param {} childrenJson
 */
function createChildrenMenu(menuId, parentId, childrenJson){
	$.each(childrenJson, function(index, json) {
		var itemEl = $('#'+parentId)[0];  // 获取菜单项
		var item = $('#mm').menu('getItem', itemEl);
  		$('#'+menuId).menu('appendItem', {
  			parent: item.target,
  			id:json.id,
			text: json.text,
			iconCls: json.iconCls ? json.iconCls : 'icon-blank',
			name:json.url
		});
		if(json.children){
			createChildrenMenu(menuId, json.id, json.children);
		}
  	}); 
}

/**
 * 根据oid获取类型名称
 * @param {} objectOid
 * @return {}
 */
function getObjectNameByOid(objectOid){
	var objectClass = objectOid.split(":")[0];
	var objectClassNames = objectClass.split(".");
	var objectClassName = objectClassNames[objectClassNames.length-1];

	return objectClassName;
}


/*function loadDetailTT(){
	var objectClassName = getObjectNameByOid('${param.oid}');
	$('#tt').tabs({}); 
 	$.ajax({//后台读取标签页信息
		cache: false,//是否缓存
		url : "${pageContext.request.contextPath}/z2w/action/modelActions?id="+objectClassName+"_infoPage_tabs",
		success : function(result) {
			 var ja = jQuery.parseJSON(result);
			 $.each(ja, function (n, value){//遍历json数组
				var selected = n==0?true:false;//设置第一个tab被选中
				$('#tt').tabs('add',{
					title:value.text,
					href:"${pageContext.request.contextPath}/z2w/"+value.url+"?oid=${param.oid}",
					selected:selected
				}); 
			  });
		}
	});
}*/