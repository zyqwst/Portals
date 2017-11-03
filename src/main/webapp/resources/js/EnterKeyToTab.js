//�����ݼ�
var hotKeyPress=false;
function hotKey(buttonName){
	if(!hotKeyPress){
		hotKeyPress=true;
		try{
			document.getElementById(buttonName).click();
			
		}catch(e){
		}
	}
	hotKeyPress=false;
}
function enterKey(stsl,e,nextId){
	var e=e?e:window.event;
	var code=e.keyCode;
	if(code==13){
		document.getElementById(nextId).focus();
		return false;
	}
}
function enterKeyToTab(e){
	var e=e?e:window.event;
	var code = e.keyCode;
	if(code==13){
		e.keyCode = 9;
	}
}
$(function(){
	$("#frmMain\\:txtKh").focus().select();
});	


function selectText(elem1){
	if(elem1){
	 if (elem1.setSelectionRange)
    {
        elem1.setSelectionRange(0, elem1.value.length);  
    }   
    else if (elem1.createTextRange)
    {
        var range = elem1.createTextRange();  
        range.collapse(true);
        
        range.moveStart('character',0);  
        range.moveEnd('character', elem1.value.length);  
        range.select();
    }   }
}
//��ID��ת����
function nextFocus(id){
	  var elem=document.getElementById(id);
	  if(elem){
	  	elem.focus(); //���ý���
	  	selectText(document.getElementById(id)); //ѡ���ı�
	  }
	}
//��ʱ��ID��ת����
function nextFocusWithTimeOut(id){
	setTimeout('nextFocus('+id+')', 10);	
}

function setNextFocus(args){
	if(args.nextFocus){
		setTimeout("setFocus('"+args.nextFocus+"')", 10);
	}
}
		
function setFocus(id){
	 //ѡ���ı�
	 	selectText(document.getElementById(id));
	 	document.getElementById(id).focus();
	 }
