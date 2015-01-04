<!-- 自定义翻页标签宏 (带多个页码的分页器)-->
<#macro queryForm formId>
<script>
	var formId = "${formId}";
	var totalRows = '${pageFinder.rowCount}';
	var pageSize = ${pageFinder.pageSize};

	/**
	*检查是否含有财务千分位分隔符
	*当totalRows>1000时默认格式会加财务千分位逗号
	*例如11,628，在js当做字符串处理
	*/
	function ck(txt){
	 if(txt.indexOf(',')>-1){
	  return true;
	 }
	 return false;
	}

	/**
	*以逗号进行字符串分割
	*返回去掉逗号的字符串
	*例如11,628->11628
	*/
	function split(datastr){
	  var arr= new Array();
	  var str = "";
	  arr=datastr.split(",");
	    for (i=0;i<arr.length ;i++ )
	    {
	        str+=arr[i];
	    }
	    return str;
	}
	
	/**
	*如果totalRows>=1000,则去除财务分隔符逗号
	*否则转换为数字类型
	*/
	if(ck(totalRows)){
		totalRows = split(totalRows);
	}else{
		totalRows = Number(totalRows);
	}

	function queryPage(pageNo){
		if(isNaN(pageSize)){
			alert('每页条数只能为数字');
			return;
		}
		var totalPage=Math.ceil(totalRows/pageSize);
		var toPage=pageNo;
		if(pageNo==0){
			toPage=document.getElementById("query.page").value;
			if(isNaN(toPage) || toPage<=0){
				alert("请输入大于0的整数.");
				return;
			}
			if(toPage>totalPage){
				alert("没有当前页数");
				return;
			}
		}
		//校验是跳转页是否在记录有效范围内
		//取大于等于总页数的值
		if(toPage>totalPage){
			alert("已经到最后一页");
			return;
		}
		var pageForm = (formId&&formId!="")?document.getElementById(formId):document.forms[0];
		var arr = pageForm.elements;
		var flag = false;
		for(var i=0,j=arr.length;i<j;i++){
			if(arr[i].getAttribute("name")=="query.page"){
				flag = true;
				break;
			}
		}
		var pageEle = document.getElementById("pageNo");
		if(!flag && pageEle == null){
			var actionUrl = pageForm.getAttribute("action");
 			if(actionUrl.indexOf("?")>0){
				actionUrl = actionUrl + "&pageNo="+toPage;
 			}else{
				actionUrl = actionUrl + "?pageNo="+toPage;
			}
			actionUrl = YouGou.Util.setUrlStamp(actionUrl);
			pageForm.setAttribute("action",actionUrl);
			createInput(actionUrl,pageForm);
		}
		pageForm.submit();
	}
	
	function createInput(actionUrl,pageForm){
		var array = getQueryString(actionUrl);
		if(array==null) return;
		for(var i = 0; i < array.length; i++){
          	var temp = array[i].split('=');
          	if(temp.length<2) continue;
          	var temp1=temp[0];
          	var temp2=temp[1];
          	if(temp1.length==0 || temp2.length==0) continue;
          	var pageInput =	document.createElement("input");
			pageInput.setAttribute("type", "hidden");
			pageInput.setAttribute("name", temp1);
			pageInput.setAttribute("id", temp1);
			pageInput.setAttribute("value", temp2);
			pageForm.appendChild(pageInput);
     	}
	}
	
	function getQueryString(url){
     var result = url.match(new RegExp("[\?\&][^\?\&]+=[^\?\&]+","g")); 
     if(result == null){
         return null;
     }
     for(var i = 0; i < result.length; i++){
         result[i] = result[i].substring(1);
     }
     return result;
	}
</script>
<#setting number_format="0">
<#if pageFinder?? && pageFinder.data?? && (pageFinder.rowCount > 0)>
	<#if pageFinder ?? && pageFinder.rowCount ??>
		<div style="float:left;">总记录数：<font color ="red">${pageFinder.rowCount}</font></div>
	</#if>
	<div class="page">
		<#if (pageFinder.pageCount > 1) >
		
			<#if (pageFinder.hasPrevious == false) >
				<span class="page-pre-no"></span>
			<#else>
				<a href="javascript:queryPage(${pageFinder.pageNo-1})" class="page-pre"  title="上一页">上一页</a>
			</#if>
			
			
			<#if (pageFinder.pageCount < 10) >
				<#list 1..(pageFinder.pageCount) as row>
					<#if pageFinder.pageNo == row >
						<!-- 选中时的样式 -->
						<a href="javascript:queryPage(${row})" class="current" >${row}</a>
					<#else>
						<a href="javascript:queryPage(${row})" >${row}</a>
					</#if>
				</#list>
			<#elseif ((pageFinder.pageCount - pageFinder.pageNo) < 5) >
				<#list 1..9 as row>
					<#if ((pageFinder.pageCount - pageFinder.pageNo) == (9- row)) >
						<!-- 选中时的样式 -->
						<a href="javascript:queryPage(${pageFinder.pageCount - 9 + row})" class="current"> ${pageFinder.pageCount  - 9 + row}</a>
					<#else>
						<a href="javascript:queryPage(${pageFinder.pageCount - 9 + row})"> ${pageFinder.pageCount  - 9 + row}</a>
						<!-- 默认的样式 -->
					</#if>
				</#list>
			<#else>
				<#list 1..10 as row>
					<#if (row == 8) >
					<a>	...</a>
					<#elseif (row == 9) >
						<a href="javascript:queryPage(${pageFinder.pageCount -1 })"  > ${pageFinder.pageCount -1}</a>
					<#elseif (row == 10) >
						<a href="javascript:queryPage(${pageFinder.pageCount})" > ${pageFinder.pageCount}</a>
					<#else>
						<#if (pageFinder.pageNo < 5) >
							<#if (pageFinder.pageNo == row) >
								<!-- 选中时的样式 -->
								<a href="javascript:queryPage(${row});" class="current" > ${row}</a>
							<#else>
								<a href="javascript:queryPage(${row});" > ${row}</a>
								<!-- 默认的样式 -->
							</#if>
						<#else>
							<#if (row == 4) >
								<!-- 选中时的样式 -->
								<a href="javascript:queryPage(${pageFinder.pageNo-4+row})" class="current" > ${pageFinder.pageNo-4+row}</a>
							<#else>
								<!-- 默认的样式 -->
								<a href="javascript:queryPage(${pageFinder.pageNo-4+row})"> ${pageFinder.pageNo-4+row}</a>
							</#if>
						</#if>
					</#if>
				</#list>
			</#if>
			
			
			<#if (pageFinder.pageNo == pageFinder.pageCount) >
				<!-- 选中时的样式 -->
			<#else>
				<!-- 默认的样式 -->
			</#if>
			
			<#if (pageFinder.hasNext == false) >
				<span class="page-next-no"></span>
			<#else>
				<a href="javascript:queryPage(${pageFinder.pageNo+1})" class="page-next"  title="下一页"  >下一页</a>
			</#if>
			
			<span class="jum-page">
				到第
				<input id="query.page" name="query.page" type="text" maxlength="5" value="${pageFinder.pageNo}"  class="pagenum" onkeyup="value=value.replace(/[^\d]/g,'')"/>
				页
			</span>
			<a class="submit-page ft-sz-12" href="#"  onClick="queryPage(0);"style="color:red">确定</a>
		</#if>
	</div>
</#if>
</#macro>
