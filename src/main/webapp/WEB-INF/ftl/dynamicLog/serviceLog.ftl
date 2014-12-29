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
	#foldCbTree{float:left;width:20%;}
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
			<!-- 配置栏开始-->
 			<div class="u_balance_tt clearfix">
 				<form id="saveForm" method="post" class="partner">
                <!--<ul>
                    <li><em>接口配置面板</em></li>
                    <li><em></em></li>
                    <li><em>待完善...</em></li>
                </ul>-->
                <#include "/settingOperation/setting_list.ftl">
	                <div class="venter">
	                	<input type="button" id="saveBtn" value="确认保存" class="cButton"/>
	                	<input type="button" id="udRMdBtn" value="更新API方法" class="cButton"/>
	                </div>
                </form>
			</div>
 			<!-- 配置栏结束-->
			<!-- 主面板开始-->
			<div class="u_main" id="statementSearch">
				<#if pageFinder?? && pageFinder.data?? && (pageFinder.data?size > 0)>
	                <div class="uc_search_result cl">
						<table class="tab_tb">
							<thead>
								<th width="20%">接口名称</th>
								<th width="12%">请求方法</th>
								<th width="15%">请求参数</th>
								<th width="8%">请求IP</th>
								<th width="25%">返回结果</th>
								<th width="10%">异常信息</th>
								<th width="5%">请求时长</th>
								<th width="5%">操作</th>
							</thead>
							<tbody>
								<#list pageFinder.data as vo>
									<tr>
										<td>${vo.interfaceName!""}</td>
										<td>${vo.reqMethod!""}</td>
										<td>${vo.reqParams!""}</td>
										<td>${vo.reqIp!""}</td>
										<td>${vo.respData!""}</td>
										<td>${vo.errMsg!""}</td>
										<td>${vo.accessPeriod!""}</td>
										<td><a href="javascript:toRefLog('${vo.id!""}');" class="f_blue">查看</a></td>
									</tr>
								</#list>
							</tbody>
						</table>				
					</div>
					<div class="paginator">
						<form method="get" id="searchForm" action="/dynamicLog/serviceLog.sc">
						</form>
						<#import "/common/multi_page.ftl" as page>
						<@page.queryForm formId="searchForm" />
					</div>
				<#else>
					<div class="u_noresult">暂无交易记录~</div>
				</#if>
				<div class="blank20"></div>
            </div>
			<!-- 主面板结束-->
		</div>
	</div>
</body>
<script>
	function toRefLog(serviceId){
		location.href="/dynamicLog/refLog.sc?serviceId=" + serviceId;
	}
	
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