//resetlayout
function resetLayout(){
	if($(window).width()<1190){
		$('body').removeClass('selfadaptat');
	}else{
		$('body').addClass('selfadaptat');
	}		
}
$(function(){
	//自适应
	resetLayout();
	$(window).resize(resetLayout);
	
	//更多、收起链接布局
	$(".moreLink").toggle(function(){
		var $self=$(this);
		var dl=$self.closest("dl");
		dl.find("dd").css({"height":"100%"});
		dl.find("dd").find("ul").find("li").show();
		$self.removeClass("down").addClass("up");
		$self.html("收起<i class=\"list_bg\"></i>");
		return;
	},function(){
		var $self = $(this);
		var dl=$self.closest("dl");
		if(dl.find("dd").find("ul").attr("name")=="seo_en_brand_name"){
			dl.find("dd").find("ul").find("li:lt(12)").show();
			dl.find("dd").height(100);
		}else{
			dl.find("dd").find("ul").find("li:lt(6)").show();
			dl.find("dd").height(100);
		}
		$self.removeClass("up").addClass("down");
		$self.html("更多<i class=\"list_bg\"></i>");
	});
});