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
	//排序下拉菜单
	$(".list_select").hover(function(){
		$(this).addClass("lcurr").children(".list_select_show").show();	
	},function(){
		$(this).removeClass("lcurr").children(".list_select_show").hide();			
	});
	//图片懒加载
	$(".proList img").lazyload();
	//价格区间
    $(".fprice input").focus(function(){
        $(".fprice").addClass("fprice-hover");
        $(this).parents(".fpb-item").css("border","1px solid #ff5000");
    });
    $(".fprice input").blur(function(){
        $(this).parents(".fpb-item").css("border","1px solid #ccc");
    });
    $(".fprice").hover(function(){
        $("body").unbind("click");
    },function(){
        $("body").click(function(){
            $(".fprice").removeClass("fprice-hover");
        });
    });
    
    // gotoTop
    if (!$('#fixedRight')[0]) {
        $('<div class="fixed_right" id="fixedRight"><a rel="nofollow" href="#" class="gotop_lnk">返回顶部</a></div>').appendTo('body');
        $('#fixedRight').jqScrollTop({
            startPos: 0,
            show: true,
            bottom: 10
        });
    }
     
    //-------------筛选器相关
    /*筛选条件很多时出现左右移动按钮*/
	var menu_width=$("#filterSelCon").width();
	var menucont_width=$("#filterTtCon").width();
	var fw=menu_width-menucont_width;
	var move=50;//每次移动步长
	var left=0;
	if(fw>0){
		//$("#filterTtlBtn,#filterTtrBtn").show();
		$("#filterTtrBtn").show();
		$("#filterTtlBtn").click(function(){
			left = parseInt($("#filterSelCon").position().left);
			if(left < 0){
				left = left + move;
				$("#filterSelCon").animate({left:left},100);
				if(left == 0){
					$("#filterTtlBtn").hide();
				}
				$("#filterTtrBtn").show();
			}else if(left ==0){
				return;
			}
		});
		$("#filterTtrBtn").click(function(){
			left=parseInt($("#filterSelCon").position().left);
			if(Math.abs(left) < fw){
				//菜单左坐标在当前值基础上减去预设的步长值
				left = left - move;
				$("#filterSelCon").animate({left:left},100);
				if(Math.abs(left) >= fw){
					$("#filterTtrBtn").hide();
				}
				$("#filterTtlBtn").show();
			}else if(Math.abs(left) >= fw){
				return;
			}
		});
	}else{
		$("#filterTtlBtn,#filterTtrBtn").hide();
	}
	
	/*点击多选*/
	$(".ygFilterCon .moreSelete").click(function(){
		multiSelect = true;
		var $dl=$(this).closest("dl");
		$(".ygFilterCon dl").addClass("defaultCat").removeClass("chooseList");
		//li元素显示
		$(this).prev("ul").find("li").show();
		setHeight();
		$dl.addClass("chooseList").removeClass("defaultCat").find("dd").height("100%");
		$(this).siblings(".moreLink").hide();
	});
	
	/*取消多选*/
	$(".ygFilter_cz .cancel").click(function(){
		multiSelect = false;
		$(this).parent().parent().find("input[type='hidden']").val($("#paramsStr").val());
		var dl=$(this).closest("dl");
		var li=dl.find(".catList li");
		var smt=dl.find(".ygFilter_cz .ygFilterSmt");
		//点击取消后复选框返回非选中状态、提交按钮返回不可用状态
		dl.removeClass("chooseList").addClass("defaultCat");
		li.removeClass("choosed");
		smt.removeClass("smt").addClass("noSmt");
		smt.attr("disabled","disabled");
		//设置取消多选后dd的默认高度
		setHeight();
		brand_en_name = [];
	});
	
	$("#bread .property a").live("click",function(){
		var prop = $(this).attr('prop');
		var paramsStr = $("#paramsStr").val();
		var parr = prop.split("|");
		$.each(parr,function(k,v){
			var reg=new RegExp("(&"+v+")","g"); 
			paramsStr = paramsStr.replace(reg,"");
		});
		paramsStr = trimPageNo(paramsStr);
		window.location.href = paramsStr;
	});
	
	$("#moreSeleteBtn").live("click",function(){
		var paramsStr = $(this).parent().parent().find("input").val();
		paramsStr = trimPageNo(paramsStr);
		window.location.href = paramsStr;
	});
				
	/*复选框 、使多选中的提交按钮可用*/
	$(".chooseList .catList li a").live("click",function(){
		var emnu = $(this).attr("tag");
    	var evalue = $(this).attr("name");
    	var paramsStr = $(this).parent().parent().find("input[type='hidden']").val();
    	var paramarr = paramsStr.split("?");
    	var pl = paramarr[1].split("&");
		var has=$(this).parent().is(".choosed");
		if(has){
			// 取消选中
			if(emnu == "品牌"){
				if(paramsStr.indexOf("brandNo")== -1){
					paramsStr = paramsStr + "&brandNo="+evalue;
				}else{
					for(var i=0 ; i<pl.length ;i++){
			    		var v = pl[i]; 
			    		if(v.indexOf("brandNo")!= -1){
			    			var gen = v.split("=");
			    			var genArr = gen[1].split("_");
			    			if(genArr.length == 1){
			    				var reg=new RegExp("(&"+v+")","g"); 
			    				paramsStr = paramsStr.replace(reg,"");
			    			}else{
			    				var temp="";
			    				for(var j=0 ;j<genArr.length; j++){
			    					if(genArr[j] == evalue){
			    						continue;
			    					}
			    					temp+=genArr[j];
			    					if(j<genArr.length-2){
			    						temp+="_";
			    					}
			    				}
			    				var reg=new RegExp("("+v+")","g"); 
								paramsStr = paramsStr.replace(reg,("brandNo=" + temp));	
			    			}
			    			break;
			    		}
					}
				}
	    	}else if(emnu == "分类"){
	    		if(paramsStr.indexOf("category")== -1){
	    			paramsStr = paramsStr + "&category="+evalue;
				}else{
					for(var i=0 ; i<pl.length ;i++){
			    		var v = pl[i]; 
			    		if(v.indexOf("category")!= -1){
			    			var gen = v.split("=");
			    			var genArr = gen[1].split("_");
			    			if(genArr.length == 1){
			    				var reg=new RegExp("(&"+v+")","g"); 
			    				paramsStr = paramsStr.replace(reg,"");
			    			}else{
			    				var temp="";
			    				for(var j=0 ;j<genArr.length; j++){
			    					if(genArr[j] == evalue){
			    						continue;
			    					}
			    					temp+=genArr[j];
			    					if(j<genArr.length-2){
			    						temp+="_";
			    					}
			    				}
			    				var reg=new RegExp("("+v+")","g"); 
								paramsStr = paramsStr.replace(reg,("category=" + temp));	
			    			}
			    			break;
			    		}
					}
				}
	    	}else if(emnu == "性别"){
	    		if(paramsStr.indexOf("genderNo")== -1){
	    			paramsStr = paramsStr + "&genderNo="+evalue;
				}else{
					for(var i=0 ; i<pl.length ;i++){
			    		var v = pl[i]; 
			    		if(v.indexOf("genderNo")!= -1){
			    			var gen = v.split("=");
			    			var genArr = gen[1].split("_");
			    			if(genArr.length == 1){
			    				var reg=new RegExp("(&"+v+")","g"); 
			    				paramsStr = paramsStr.replace(reg,"");
			    			}else{
			    				var temp="";
			    				for(var j=0 ;j<genArr.length; j++){
			    					if(genArr[j] == evalue){
			    						continue;
			    					}
			    					temp+=genArr[j];
			    					if(j<genArr.length-2){
			    						temp+="_";
			    					}
			    				}
			    				var reg=new RegExp("("+v+")","g"); 
								paramsStr = paramsStr.replace(reg,("genderNo=" + temp));	
			    			}
			    			break;
			    		}
					}
				}
	    	}else if(emnu == "季节"){
	    		if(paramsStr.indexOf("seasonNo")== -1){
	    			paramsStr = paramsStr + "&seasonNo="+evalue;
				}else{
					for(var i=0 ; i<pl.length ;i++){
			    		var v = pl[i]; 
			    		if(v.indexOf("seasonNo")!= -1){
			    			var gen = v.split("=");
			    			var genArr = gen[1].split("_");
			    			if(genArr.length == 1){
			    				var reg=new RegExp("(&"+v+")","g"); 
			    				paramsStr = paramsStr.replace(reg,"");
			    			}else{
			    				var temp="";
			    				for(var j=0 ;j<genArr.length; j++){
			    					if(genArr[j] == evalue){
			    						continue;
			    					}
			    					temp+=genArr[j];
			    					if(j<genArr.length-2){
			    						temp+="_";
			    					}
			    				}
			    				var reg=new RegExp("("+v+")","g"); 
								paramsStr = paramsStr.replace(reg,("seasonNo=" + temp));	
			    			}
			    			break;
			    		}
					}
				}	
	    	}
			$(this).parent().removeClass("choosed");
		}else{
			// 选中
			if(emnu == "品牌"){
				if(paramsStr.indexOf("brandNo")== -1){
					paramsStr = paramsStr + "&brandNo="+evalue;
				}else{
					for(var i=0 ; i<pl.length ;i++){
						var v = pl[i]; 
			    		if(v.indexOf("brandNo")!= -1){
			    			var gen = v.split("=");
			    			var genArr = gen[1].split("_");
		    				var temp="";
		    				for(var j=0 ;j<genArr.length; j++){
		    					if(genArr[j] == evalue){
		    						continue;
		    					}
		    					temp+=(genArr[j]+"_");
		    				}
		    				var reg=new RegExp("("+v+")","g"); 
							paramsStr = paramsStr.replace(reg,("brandNo="+ temp + evalue));	
			    			break;
			    		}
					}
				}
	    	}else if(emnu == "分类"){
	    		if(paramsStr.indexOf("category")== -1){
	    			paramsStr = paramsStr + "&category="+evalue;
				}else{
					for(var i=0 ; i<pl.length ;i++){
						var v = pl[i]; 
			    		if(v.indexOf("category")!= -1){
			    			var gen = v.split("=");
			    			var genArr = gen[1].split("_");
		    				var temp="";
		    				for(var j=0 ;j<genArr.length; j++){
		    					if(genArr[j] == evalue){
		    						continue;
		    					}
		    					temp+=(genArr[j]+"_");
		    				}
		    				var reg=new RegExp("("+v+")","g"); 
							paramsStr = paramsStr.replace(reg,("category="+ temp + evalue));	
			    			break;
			    		}
					}
				}
	    	}else if(emnu == "性别"){
	    		if(paramsStr.indexOf("genderNo")== -1){
	    			paramsStr = paramsStr + "&genderNo="+evalue;
				}else{
					for(var i=0 ; i<pl.length ;i++){
						var v = pl[i]; 
			    		if(v.indexOf("genderNo")!= -1){
			    			var gen = v.split("=");
			    			var genArr = gen[1].split("_");
		    				var temp="";
		    				for(var j=0 ;j<genArr.length; j++){
		    					if(genArr[j] == evalue){
		    						continue;
		    					}
		    					temp+=(genArr[j]+"_");
		    				}
		    				var reg=new RegExp("("+v+")","g"); 
							paramsStr = paramsStr.replace(reg,("genderNo="+ temp + evalue));	
			    			break;
			    		}
					}
				}
	    	}else if(emnu == "季节"){
	    		if(paramsStr.indexOf("seasonNo")== -1){
	    			paramsStr = paramsStr + "&seasonNo="+evalue;
				}else{
					for(var i=0 ; i<pl.length ;i++){
			    		var v = pl[i]; 
			    		if(v.indexOf("seasonNo")!= -1){
			    			var gen = v.split("=");
			    			var genArr = gen[1].split("_");
		    				var temp="";
		    				for(var j=0 ;j<genArr.length; j++){
		    					if(genArr[j] == evalue){
		    						continue;
		    					}
		    					temp+=(genArr[j]+"_");
		    				}
		    				var reg=new RegExp("("+v+")","g"); 
							paramsStr = paramsStr.replace(reg,("seasonNo="+ temp + evalue));	
			    			break;
			    		}
					}
				}	
	    	}
			$(this).parent().addClass("choosed");	
		}
		$(this).parent().parent().find("input").val(paramsStr);
		showSmt($(this));
		return false;
	});
	function showSmt(e){
		var checkLength=e.closest(".chooseList").find(".catList .choosed").length;
		var ygFilterSmt=e.closest(".chooseList").find(".ygFilter_cz .ygFilterSmt");
		if(checkLength<=0){
			ygFilterSmt.removeClass("smt").addClass("noSmt");
			ygFilterSmt.attr("disabled","disabled");
		}else{
			ygFilterSmt.removeClass("noSmt").addClass("smt");
			ygFilterSmt.removeAttr("disabled");
		}
	}
	
	// 现货0 期货1
	$("input[name='spotGoods']").click(function(){
		var paramsStr = $("#paramsStr").val();
    	var paramarr = paramsStr.split("?");
    	var pl = paramarr[1].split("&");
		if(this.checked){
			var spotGoods = $(this).val();
			if(paramsStr.indexOf("spotGoods")== -1){
				paramsStr = paramsStr + "&spotGoods="+spotGoods;
			}
		}else{
			for(var i=0 ; i<pl.length ;i++){
	    		var v = pl[i]; 
	    		if(v.indexOf("spotGoods")!= -1){
	    			var reg=new RegExp("(&"+v+")","g"); 
	    			paramsStr = paramsStr.replace(reg,"");
	    			break;
	    		}
			}
		}
		$("#paramsStr").val(paramsStr);
		paramsStr = trimPageNo(paramsStr);
		window.location.href = paramsStr;
	});
	$("input[name='futureGoods']").click(function(){
		var paramsStr = $("#paramsStr").val();
    	var paramarr = paramsStr.split("?");
    	var pl = paramarr[1].split("&");
		if(this.checked){
			var futureGoods = $(this).val();
			if(paramsStr.indexOf("futureGoods")== -1){
				paramsStr = paramsStr + "&futureGoods="+futureGoods;
			}
		}else{
			for(var i=0 ; i<pl.length ;i++){
	    		var v = pl[i]; 
	    		if(v.indexOf("futureGoods")!= -1){
	    			var reg=new RegExp("(&"+v+")","g"); 
	    			paramsStr = paramsStr.replace(reg,(""));
	    			break;
	    		}
			}
		}
		$("#paramsStr").val(paramsStr);
		paramsStr = trimPageNo(paramsStr);
		window.location.href = paramsStr;
	});
	
	/*自动计算高度，为了加快渲染速度，请不要提到底部或者其他位置*/
	setHeight();
	function setHeight(){
		$(".ygFilterCon dl").each(function(){
			var $self=$(this);
			var $dd=$self.find("dd");
			var height=$dd.height();
			var moreLink=$self.find(".moreLink");
			var default_height = 50;
			if($dd.find("ul").attr("name")=="seo_en_brand_name"){
				default_height = 50;
			}else{
				default_height = 25;
			}
			if(height>default_height){
				moreLink.show();
				if(moreLink.hasClass("up"))
				{
					$dd.height("100%");
				}
				else
				{
					$dd.height(default_height);
				}
			}
		});
	}
	//更多、收起链接布局
	$(".ygFilterCon .moreLink").toggle(function(){
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
			dl.find("dd").height(50);
		}else{
			dl.find("dd").find("ul").find("li:lt(6)").show();
			dl.find("dd").height(25);
		}
		$self.removeClass("up").addClass("down");
		$self.html("更多<i class=\"list_bg\"></i>");
	});
	
	//筛选器  更多筛选内容
	$("#moreFilter").toggle(function(){
		var $self = $(this);
		$("#filterCon").find("dl:gt(8)").each(function(){
			$(this).show();
		});
		$self.html("收起<i>&nbsp;</i>").addClass("collapse").removeClass("expand");
		$("#filterCon").find("dl:last").css({"margin-bottom":"0"});
		$("#filterCon").css({"border-bottom-width":"0"});
		return;
	},function(){
		var $self = $(this);
		$("#filterCon").find("dl:gt(8)").each(function(){
			$(this).hide();
		});
		$self.html("更多<i>&nbsp;</i>").addClass("expand").removeClass("collapse");
		$("#filterCon").find("dl:last").css({"margin-bottom":"-1px"});
		$("#filterCon").css({"border-bottom-width":"1px"});
		return;
	});
		
	$(".ygFilterCon dl").hover(function(){
		$(this).css({"border-bottom-style":"solid"});
	},function(){
		$(this).css({"border-bottom-style":"dotted"});
	});
	//---------------排序相关
	$(".ui-btn-s").click(function(){
        $(".fprice input").val("");
    });
	
	$("#tt0_0").click(function(){
		var paramsStr = $("#paramsStr").val();
    	var paramarr = paramsStr.split("?");
    	var pl = paramarr[1].split("&");
		if(paramsStr.indexOf("orderBy")== -1){
			paramsStr = paramsStr + "&orderBy=normal_0";
		}else{
			for(var i=0 ; i<pl.length ;i++){
	    		var v = pl[i]; 
	    		if(v.indexOf("orderBy")!= -1){
	    			var reg=new RegExp("("+v+")","g"); 
	    			paramsStr = paramsStr.replace(reg,("orderBy=normal_0"));
	    			break;
	    		}
			}
		}
		$("#paramsStr").val(paramsStr);
		paramsStr = trimPageNo(paramsStr);
		window.location.href = paramsStr;
	});
	
	$("#tt0_3").click(function(){
		var paramsStr = $("#paramsStr").val();
    	var paramarr = paramsStr.split("?");
    	var pl = paramarr[1].split("&");
		if(paramsStr.indexOf("orderBy")== -1){
			paramsStr = paramsStr + "&orderBy=new_1";
		}else{
			for(var i=0 ; i<pl.length ;i++){
	    		var v = pl[i]; 
	    		if(v.indexOf("orderBy")!= -1){
	    			var reg=new RegExp("("+v+")","g"); 
	    			paramsStr = paramsStr.replace(reg,("orderBy=new_1"));
	    			break;
	    		}
			}
		}
		$("#paramsStr").val(paramsStr);
		paramsStr = trimPageNo(paramsStr);
		window.location.href = paramsStr;
	});
	
	$("#tt0_2").click(function(){
    	var paramsStr = $("#paramsStr").val();
    	var paramarr = paramsStr.split("?");
    	var pl = paramarr[1].split("&");
		if(paramsStr.indexOf("orderBy")== -1){
			paramsStr = paramsStr + "&orderBy=price_1";
		}else{
			for(var i=0 ; i<pl.length ;i++){
	    		var v = pl[i]; 
	    		if(v.indexOf("orderBy")!= -1){
	    			var reg=new RegExp("("+v+")","g"); 
	    			paramsStr = paramsStr.replace(reg,("orderBy=price_1"));
	    			break;
	    		}
			}
		}
		$("#paramsStr").val(paramsStr);
		paramsStr = trimPageNo(paramsStr);
		window.location.href = paramsStr;
	});
	
	
    $(".ui-btn-s-primary").click(function(){
    	var minPrice = $("#minPrice").val();
    	var maxPrice = $("#maxPrice").val();
    	var paramsStr = $("#paramsStr").val();
    	var paramarr = paramsStr.split("?");
    	var pl = paramarr[1].split("&");
		if(paramsStr.indexOf("minPrice")== -1){
			paramsStr = paramsStr + "&minPrice="+minPrice;
		}else{
			for(var i=0 ; i<pl.length ;i++){
	    		var v = pl[i]; 
	    		if(v.indexOf("minPrice")!= -1){
	    			var reg=new RegExp("("+v+")","g"); 
	    			paramsStr = paramsStr.replace(reg,("minPrice=" + minPrice));
	    			break;
	    		}
			}
		}
		if(paramsStr.indexOf("maxPrice")== -1){
			paramsStr = paramsStr + "&maxPrice="+maxPrice;
		}else{
			for(var i=0 ; i<pl.length ;i++){
	    		var v = pl[i]; 
	    		if(v.indexOf("maxPrice")!= -1){
	    			var reg=new RegExp("("+v+")","g"); 
					paramsStr = paramsStr.replace(reg,("maxPrice=" + maxPrice));
	    			break;
	    		}
			}
		}
		$("#paramsStr").val(paramsStr);
		paramsStr = trimPageNo(paramsStr);
		window.location.href = paramsStr;
    });
    
    var multiSelect = false;
    //  category=hr&genderNo=sex3&orderBy=normal_0&pageNo=1&seasonNo=sn03
    $(".ygFilterCon li a").click(function(){
    	if(multiSelect){return;}
    	var emnu = $(this).attr("tag");
    	var evalue = $(this).attr("name");
    	var paramsStr = $("#paramsStr").val();
    	var paramarr = paramsStr.split("?");
    	var pl = paramarr[1].split("&");
		if(emnu == "品牌"){
			if(paramsStr.indexOf("brandNo")== -1){
				paramsStr = paramsStr + "&brandNo="+evalue;
			}else{
				for(var i=0 ; i<pl.length ;i++){
		    		var v = pl[i]; 
		    		if(v.indexOf("brandNo")!= -1){
		    			var reg=new RegExp("("+v+")","g"); 
						paramsStr = paramsStr.replace(reg,("brandNo=" + evalue));
		    			break;
		    		}
				}
			}
    	}else if(emnu == "分类"){
    		if(paramsStr.indexOf("category")== -1){
    			paramsStr = paramsStr + "&category="+evalue;
			}else{
				for(var i=0 ; i<pl.length ;i++){
		    		var v = pl[i]; 
		    		if(v.indexOf("category")!= -1){
		    			var reg=new RegExp("("+v+")","g"); 
						paramsStr = paramsStr.replace(reg,("category=" + evalue));
		    			break;
		    		}
				}
			}
    	}else if(emnu == "性别"){
    		if(paramsStr.indexOf("genderNo")== -1){
    			paramsStr = paramsStr + "&genderNo="+evalue;
			}else{
				for(var i=0 ; i<pl.length ;i++){
		    		var v = pl[i]; 
		    		if(v.indexOf("genderNo")!= -1){
		    			var reg=new RegExp("("+v+")","g"); 
						paramsStr = paramsStr.replace(reg,("genderNo=" + evalue));
		    			break;
		    		}
				}
			}
    	}else if(emnu == "季节"){
    		if(paramsStr.indexOf("seasonNo")== -1){
    			paramsStr = paramsStr + "&seasonNo="+evalue;
			}else{
				for(var i=0 ; i<pl.length ;i++){
		    		var v = pl[i]; 
		    		if(v.indexOf("seasonNo")!= -1){
		    			var reg=new RegExp("("+v+")","g"); 
						paramsStr = paramsStr.replace(reg,("seasonNo=" + evalue));
		    			break;
		    		}
				}
			}	
    	}
    	$("#paramsStr").val(paramsStr);
    	paramsStr = trimPageNo(paramsStr);
		window.location.href = paramsStr;
    });
    
    // 剔除分页
    function trimPageNo(paramer){
    	var paramarr = paramer.split("?");
    	var pl = paramarr[1].split("&");
    	for(var i=0 ; i<pl.length ;i++){
    		var v = pl[i]; 
    		if(v.indexOf("pageNo")!= -1){
    			var reg=new RegExp("(&"+v+")","g"); 
    			paramer = paramer.replace(reg,"");
    			break;
    		}
		}
    	return paramer;
    }
    
    $(".newPage a[rel='nofollow']").click(function(){
    	var pageNo = 1;
    	if($(this).attr("cuPage")){
    		pageNo = $(this).attr("cuPage");
    	}else if($(this).attr("upPage")){
    		pageNo = $(this).attr("upPage");
    	}else if($(this).attr("downPage")){
    		pageNo = $(this).attr("downPage");
    	}
    	var paramsStr = $("#paramsStr").val();
    	var paramarr = paramsStr.split("?");
    	var pl = paramarr[1].split("&");
		if(paramsStr.indexOf("pageNo")== -1){
			paramsStr = paramsStr + "&pageNo=1";
		}else{
			for(var i=0 ; i<pl.length ;i++){
	    		var v = pl[i]; 
	    		if(v.indexOf("pageNo")!= -1){
	    			var reg=new RegExp("("+v+")","g"); 
	    			paramsStr = paramsStr.replace(reg,("pageNo="+pageNo));
	    			break;
	    		}
			}
		}
		$("#paramsStr").val(paramsStr);
		window.location.href = paramsStr;
    });
    
    $(".paginator .page a[rel='nofollow']").click(function(){
    	var pageNo = 1;
    	if($(this).attr("downPage")==undefined || $(this).attr("downPage") == null){
    		pageNo = $(this).attr("upPage");
    	}else if($(this).attr("upPage")==undefined || $(this).attr("upPage") == null){
    		pageNo = $(this).attr("downPage");
    	}
    	var pageCount = $("#pageCount").val();
    	if(pageNo>pageCount){return ;}
    	var paramsStr = $("#paramsStr").val();
    	var paramarr = paramsStr.split("?");
    	var pl = paramarr[1].split("&");
		if(paramsStr.indexOf("pageNo")== -1){
			paramsStr = paramsStr + "&pageNo=1";
		}else{
			for(var i=0 ; i<pl.length ;i++){
	    		var v = pl[i]; 
	    		if(v.indexOf("pageNo")!= -1){
	    			var reg=new RegExp("("+v+")","g"); 
	    			paramsStr = paramsStr.replace(reg,("pageNo="+pageNo));
	    			break;
	    		}
			}
		}
		$("#paramsStr").val(paramsStr);
		window.location.href = paramsStr;
    });
    
    
  //--------------搜索结果相关
  //跳转到某页
	function jumpPage(){
		var toPage = $("#jumpToPage").val();
		if(!toPage){
			$("#jumpToPage").focus();
			return;
		}
		if(!/^[1-9]\d*$/.test(toPage)){
			$("#jumpToPage").val("");
			$("#jumpToPage").focus();
			return;
		}
		if(toPage<1){
			toPage = 1;
		}
		toPage = Number( toPage ) ;
		var pageCount = Number( $("#pageCount").val() ) ;
		if(toPage > pageCount ){
			toPage = pageCount ;
		}
		url = getPageUrl( toPage ) ;
		window.location.href=url;
	}
	var v = $("#jumpToPage").val();
	document.onclick = function(e) {
		e = window.event || e; // 兼容IE7
		obj = $(e.srcElement || e.target);
		if (obj.attr('id') == 'jumpToPage'){
			$("#jumpToPage").val("");
		}else if(obj.attr('class') == 'gbtn'){
			jumpPage();
		}else{
			$("#jumpToPage").val(v);
		}
	}
});