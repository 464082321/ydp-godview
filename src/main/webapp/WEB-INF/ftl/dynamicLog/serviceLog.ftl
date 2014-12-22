<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
						<table class="tab_tb">
							<colgroup>
								<col width="20%">
								<col width="20%">
								<col width="20%">
								<col width="15%">
								<col width="10%">
							</colgroup>
							<thead>
								<th>请求的接口名称</th>
								<th>请求的方法</th>
								<th>请求的参数</th>
								<th>请求的IP</th>
								<th>请求的时长</th>
							</thead>
							<tbody>
								<#list pageFinder.data as vo>
									<tr class="nobdr">
										<td>${vo.interfaceName!""}</td>
										<td>${vo.reqMethod!""}</td>
										<td>${vo.reqParams!""}</td>
										<td>${vo.reqIp!""}</td>
										<td>${vo.accessPeriod!""}</td>
									</tr>
								</#list>
							</tbody>
						</table>				
					</div>
					<div class="paginator">
						<form method="get" id="searchForm" action="/dynamicLog/serviceLog">
						</form>
						<#import "/common/muilt_page.ftl" as page>
						<@page.queryForm formId="searchForm" />
					</div>
				<#else>
					<div class="u_noresult">暂无交易记录~</div>
				</#if>
				<div class="blank20"></div>
               	<div class="tripDiv">
					<p>温馨提示：</p>
					<p class="blank10"></p>
					<p>1、每个月已结算的佣金收益将自动转入您的账户余额；</p>
					<p>2、结算单金额=货款金额*（1-17%）*收益率+贵司开票可抵扣税金；</p>
					<p>3、贵司开票可抵扣税金会根据实际开票情况在结算单调整项中体现。</p>
				</div>
            </div>
			<!-- 主面板结束-->
		</div>
	</div>
</body>
</html>