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
	<div class="">
		<!-- 主面板开始-->
		<div class="u_main" id="statementSearch">
			<#if refLogList?? && (refLogList?size > 0)>
                <div class="uc_search_result cl">
					<table class="tab_tb" width="80%">
						<thead>
							<th width="130">请求的接口名称</th>
							<th width="120">请求的方法</th>
							<th width="180">请求的参数</th>
							<th width="95">请求的时长 <span class="Gray">(mm)</th>
							<th width="220">返回的结果</th>
						</thead>
						<tbody>
							<#list refLogList as vo>
								<tr>
									<td>${vo.interfaceName!""}</td>
									<td>${vo.reqMethod!""}</td>
									<td>${vo.reqParams!""}</td>
									<td>${vo.accessPeriod!""}</td>
									<td>${vo.respData!""}</td>
								</tr>
							</#list>
						</tbody>
					</table>				
				</div>
			<#else>
				<div class="u_noresult">暂无交易记录~</div>
			</#if>
			<div class="blank20"></div>
        </div>
		<!-- 主面板结束-->
</body>
</html>