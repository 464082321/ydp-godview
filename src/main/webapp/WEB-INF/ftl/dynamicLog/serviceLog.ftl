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
</head>
<body>
	<div class="yg_body">
		<!-- 分类路径 end-->
		<div class="u_wrap" id="u_wrap_statements">
			<!-- 主面板开始-->
			<div class="u_main" id="statementSearch">
				<#if pageFinder?? && pageFinder.data?? && (pageFinder.data?size > 0)>
	                <div class="uc_search_result cl">
						<table class="tab_tb" >
							<colgroup>
								<col width="50">
								<col width="50">
								<col width="50">
								<col width="50">
								<col width="70">
								<col width="80">
							</colgroup>
							<thead>
								<th>接口名称</th>
								<th>请求方法</th>
								<th>请求参数</th>
								<th>请求IP</th>
								<th>请求时长 <span class="Gray">(mm)</th>
								<th>返回结果</th>
							</thead>
							<tbody>
								<#list pageFinder.data as vo>
									<tr>
										<td>${vo.interfaceName!""}</td>
										<td>${vo.reqMethod!""}</td>
										<td>${vo.reqParams!""}</td>
										<td>${vo.reqIp!""}</td>
										<td>${vo.accessPeriod!""}</td>
										<td>${vo.respData!""}</td>
									</tr>
								</#list>
							</tbody>
						</table>				
					</div>
					<!--<div class="paginator">
						<form method="get" id="searchForm" action="/dynamicLog/serviceLog">
						</form>
						<#import "/common/multi_page.ftl" as page>
						<@page.queryForm formId="searchForm" />
					</div>-->
				<#else>
					<div class="u_noresult">暂无交易记录~</div>
				</#if>
				<div class="blank20"></div>
            </div>
			<!-- 主面板结束-->
		</div>
	</div>
</body>
</html>