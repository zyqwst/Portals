function FormUtil() {
};

FormUtil.setFirstFocus =  function(form){
		for(var n=0;n < form.elements.length;n++){
			if(form.elements[n].type == "text"){
				form.elements[n].focus();
				break;
			}
		}
};

/**
 * 回车切换光标
 * 可在form添加事件onkeypress="FormUtil.nextFocus(this);"
 * 注意配置DOM的tabindex
 */
FormUtil.nextFocus = function(form) {
    var event = window.event;
    var srcObj = event.target? event.target:event.srcElement;
    var key = event.which != null ? event.which : event.keyCode;
    if (form == null) {
        form = document.forms[0];
    }
    if ((key == 10 || key == 13)&& event.ctrlKey) { // 对于textarea改用ctrl+enter实现内部的回车
        if (srcObj.type == "textarea") {
            if(event.which != null){
                event.preventDefault();
                srcObj.value = srcObj.value+"\r";
                obj.selectionStart = obj.selectionEnd = srcObj.value.length;
            }else{
                event.keyCode = 13;
            }
            return;
        }
    }

    if (key == 0xD) {// 判断是否按下回车键
        //var CurTabIndex =srcObj.tabIndex + 1;// 将当前tabindex的值加1
        var flag = 0;
        //alert(srcObj.name);
        for (var n = 0; n < form.elements.length; n++) {
        	  /*
            if (form.elements[n].type == 'hidden' || form.elements[n].disabled) {// 对于隐藏或者只读的拒绝置焦
                if (form.elements[n].tabIndex == CurTabIndex) {
                    CurTabIndex = CurTabIndex + 1;
                }
                continue;
            }
            */
        	  if(form.elements[n].name==srcObj.name && flag==0){
        	  	flag=1;
        	  	continue;
        	  }else{
        	  	if(flag==0){continue;}
        	  }
            if (form.elements[n].name!=srcObj.name &&form.elements[n].type!='hidden'&&!form.elements[n].disabled&&form.elements[n].type!='checkbox') { // 找到下一个表单元素(form.elements[n].tabIndex == CurTabIndex)
                if (event.which != null) {
                    event.preventDefault();
                } else {
                   event.keyCode = 0;
                }
                if (form.elements[n].tagName == "BUTTON") {
                    form.elements[n].focus();
                    form.elements[n].click();
                    return;
                }
                if (form.elements[n].type == "select-one"
                        || form.elements[n].type == "text"
                        || form.elements[n].type == "file"
                        || form.elements[n].type == "checkbox"
                        || form.elements[n].type == "password"
                        || form.elements[n].type == "textarea") {
                	
                    form.elements[n].focus();
                    if (form.elements[n].type == "textarea") {
                        FormUtil.moveCursorToEnd(form.elements[n]); // 保证光标定位到最后面
                    } else if (form.elements[n].type == "text") {
                        form.elements[n].select();
                    }
                } else if (form.elements[n].type == "button") {
                    form.elements[n].focus();
                    form.elements[n].click();
                    return;
                }
                return;
            }
        }
    }
};
FormUtil.setElementEvent = function(id, event, functionClause) {
    eval("document.getElementById('" + id + "')." + event.toLowerCase()
            + "=function(){" + functionClause + "}");
};
/**
 * 将光标移动到末尾
 */
FormUtil.moveCursorToEnd = function (obj){
    obj.focus();
    var len = obj.value.length;
    if (document.selection) {
        var sel = obj.createTextRange();
        sel.moveStart('character',len);
        sel.collapse();
        sel.select();
    } else if (typeof obj.selectionStart == 'number' && typeof obj.selectionEnd == 'number') {
        obj.selectionStart = obj.selectionEnd = len;
    }
};