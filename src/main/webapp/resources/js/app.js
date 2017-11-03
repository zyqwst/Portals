var closableTab = {
		frameLoad:function (frame){
			var clientHeight = document.body.clientHeight;
			var id = $(frame).attr('id');
			  var ifm = document.getElementById(id);
			    var subWeb = document.frames ? document.frames["iframe"].document : ifm.contentDocument;
			    if (ifm != null && subWeb != null) {
			         ifm.height = subWeb.body.scrollHeight;
			         $(frame).height(clientHeight-80);
			    }
			
		},
		
	addTab : function(tabItem) { // tabItem = {id,name,url,closable}
		var container ="tab_container_" + tabItem.id;

		$("div[id^=tab_container_]").addClass("disactive");
		if($('#'+container)[0] && tabItem.closable){
			$("#"+container).remove();
		}
		if(!$('#'+container)[0]){
		 	PF('statusDialog2').show();
		 	var tabpanel = '<div role="tabpanel" class="tab-pane" id="'+container+'" style="width: 100%;height:100%">'+
			  '<iframe src="'+tabItem.url+'" id="tab_frame_'+container+'" frameborder="0" style="overflow-x: hidden; overflow-y: hidden;width:100%;height:100%" onload="PF(\'statusDialog2\').hide()" ></iframe>'+
		   '</div>';
		 	
			$('#tab-content').append(tabpanel);
		}
		$("#"+container).removeClass("disactive");
		if(!$("[class='fa fa-thumb-tack']")[0]){
			$('.menu-button.material-icons').trigger('click');
		}
	},
	add : function(hre){
		var item = {
				'id' : hre,
				'name' : hre,
				'url' : $("#"+hre).attr('data-href'),
				'closable' : false
		};
		closableTab.addTab(item);
	},
	addTabByUrl : function(url){
		var linkid=url.substr(0,url.indexOf('?')==-1? url.length:url.indexOf('?')).replace(/\//g,'').replace('.','');
		var item = {
				'id' : linkid,
				'name' : linkid,
				'url' : url,
				'closable' : true
		};
		closableTab.addTab(item);
	}
}


var datatable = {
	tableAlignBug  : function(){
		$('.ui-datatable-scrollable-body').each(function(){
			var item = $(this);
			if(item[0].scrollHeight>item.height()){
				item.parent().children('.ui-datatable-scrollable-header').css('margin-right','15px');
			}
		});
	}
}

$(function(){
	$('#menu-form a').each(function(i,obj){
		var item = $(obj);
		var hre = item.attr('href');
		if(hre && hre != '#'){
			item.attr('data-href',hre);
			item.attr('id',hre.substr(0,hre.indexOf('?')==-1?hre.length:hre.indexOf('?')).replace(/\//g,'').replace('.',''));
			item.attr('href','javascript:void(0);');
			item.attr('onclick','closableTab.add("'+hre.substr(0,hre.indexOf('?')==-1?hre.length:hre.indexOf('?')).replace(/\//g,'').replace('.','')+'")');
		}
	});
	$('a[id$=admindashboardxhtml]').trigger('click');
	setInterval("datatable.tableAlignBug()",50)
})


