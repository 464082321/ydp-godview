<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache,no-store, must-revalidate" />
<meta http-equiv="expires" content="0" />
<title></title>
<link href="../static/css/base.css" rel="stylesheet" type="text/css" />
<link href="../static/css/user.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	#foldCbTree{float:left;width:100%;}
	#foldCbTree ul li span{cursor:pointer;font-size:18px;}
	ul li{list-style-type:none;}
	ul ul li{display:none;}
</style>
<script type="text/javascript" src="../static/js/jquery.min.js"></script>
<script type="text/javascript" src="../static/js/setting/operation.tree.js"></script>
</head>
<body>
	<div class="yg_body">
		<!-- 分类路径 end-->
		<div class="u_wrap">
			<#include "/common/memu.ftl">
			<!-- 配置栏开始-->
 			<div class="u_main" id="statementSearch">
 				<form id="saveForm" method="post" class="partner">
	                <div id="foldCbTree">
						<#if settingList??>
							<#list settingList as setting>
								<ul>
									<li><span>+</span><input name="myckeckbox" class="" type="checkbox" <#if setting.isOpen=='1'>checked=checked</#if> />${setting.interfaceName!""}</li>
									<ul>
										<#list setting.methods as method>
											<li><input name="methodName" class="" type="checkbox"<#if method.isOpen=='1'>checked=checked</#if> value="${setting.interfaceName!""}.${method.methodName}"/>${method.methodName}</li>
										</#list>
									</ul>
								</ul>
							</#list>
						</#if>
						<div style="float:right">
							<input type="button" id="saveBtn" value="确认保存" class="subBtn"/>
	                		<input type="button" id="udRMdBtn" value="更新API方法" class="subBtn"/>
	                	</div>
					</div>
                </form>
			</div>
 			<!-- 配置栏结束-->
		</div>
	</div>
</body>
<script>
	$(function() {
		$("#saveBtn").click(function(){
			// 获取被选中的checkbox
			var tdata = new Array(); 
			$('input:checkbox[name=methodName]:checked').each(function(i){
       			tdata.push($(this).val()); 
      		});
      		tdata.join(','); 

			$.ajax({
				type : "POST",
				data : "methodName=" + tdata,
				url : "/dynamicLog/udMethods.sc",
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					alert('保存失败:' + errorThrown);
				},
				success : function(data) {
					alert("save success!");
				}
			});
		});
		
		//更新远程API的方法列表
		$("#udRMdBtn").click(function(){
			$.ajax({
				type : "POST",
				url : "/dynamicLog/udSettings.sc",
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					alert('保存失败:' + errorThrown);
				},
				success : function(data) {
					alert("save success!");
				}
			});
		});
	});
</script>
</html>
