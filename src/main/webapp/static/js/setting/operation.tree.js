$(document).ready(function(){
	//tree flod
	$("#foldCbTree").find("ul").each(function(){
		$(this).find("span").click(function(){
			if($(this).html()=="+"){
				$(this).html("-");
			} else if($(this).html()=="-"){
				$(this).html("+");
			}
			$(this).parent().parent().find("ul").children().each(function(){
				//alert($(this).html());
				if($(this).css("display")=="none"){
					$(this).css("display","block");
				} else if($(this).css("display")=="block") {
					$(this).css("display","none");
				}
			});
		});
	});

	//checkbox relative
	$("input[type=checkbox][name=myckeckbox]").click(function(){
		//alert($(this).parent().parent().html());
		var checked;
		if($(this).is(":checked")){
			checked = "checked";
		}

		if($(this).parent().parent().find("ul").children().length > 0) {
			//check root
			$(this).parent().parent().find("ul").children().each(function(){
				//alert($(this).html());
				if(checked) {
					$(this).find("input[type=checkbox]").attr("checked",true);
				} else {
					$(this).find("input[type=checkbox]").attr("checked",false);
				}
			});
		} else {
			//check children
			//recheck all the children status
			var tmpStatus = true;
			$(this).parent().parent().find("input[type=checkbox]").each(function(){
				//alert($(this).parent().html());
				if(checked && !$(this).is(":checked")) {
					tmpStatus = false;
				} else if(!checked && $(this).is(":checked")) {
					tmpStatus = false;
				}
			});
			if(tmpStatus==true) {
				//alert($(this).parent().parent().parent().find("li").html());
				if(checked) {
					$(this).parent().parent().parent().find("li").find("input[type=checkbox]").attr("checked",true);
				} else {
					$(this).parent().parent().parent().find("li").find("input[type=checkbox]").attr("checked",false);
				}
			}
		}
	});
});