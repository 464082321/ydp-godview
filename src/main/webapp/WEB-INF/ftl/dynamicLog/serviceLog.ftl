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
<!--<link href="../static/css/channel.css" rel="stylesheet" type="text/css" />-->
</head>
<body>
	<div class="yg_body">
		<!-- 分类路径 end-->
		<div class="u_wrap">
			<#include "/common/memu.ftl">
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
								<th width="8%">请求时长</th>
								<th width="5%">操作</th>
							</thead>
							<tbody>
								<#list pageFinder.data as vo>
									<tr>
										<td>${vo.interfaceName!""}</td>
										<td>${vo.reqMethod!""}</td>
										<td>${vo.reqParams!""}
											<!--<dl>
									            <dd style="height:30px">
									                <li>
									                	${vo.reqParams!""}
										                <#if vo.reqParams?? && vo.reqParams?length &gt; 8>
										                	<a href="javascript:;" class="moreLink down" style="display:inline">更多<i class="list_bg"></i></a>
										                </#if>
									                </li>
									            </dd>
								       	 	</dl>-->
										</td>
										<td>${vo.reqIp!""}</td>
										<td>${vo.respData!""}</td>
										<td>${vo.errMsg!""}</td>
										<td>${vo.accessPeriod!""}mm</td>
										<td><a href="javascript:toRefLog('${vo.id!""}');" class="f_blue">查看</a></td>
									</tr>
								</#list>
							</tbody>
						</table>				
					</div>
					<div class="paginator">
						<form method="get" id="searchForm" action="/dynamicLog/serviceLog.sc"></form>
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
	<script type="text/javascript" src="../static/js/jquery.min.js"></script>
	<script type="text/javascript" src="../static/js/ydp.common.js"></script>
	<!--<script type="text/javascript" src="../static/js/ydp.search.sr.js"></script>-->
	<script type="text/javascript" src="../static/js/setting/operation.tree.js"></script>
</body>
<script>
	function toRefLog(serviceId){
		location.href="/dynamicLog/refLog.sc?serviceId=" + serviceId;
	}
</script>
</html>