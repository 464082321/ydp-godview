<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache,no-store, must-revalidate"/>
<meta http-equiv="expires" content="0"/>
<style>
	table {border:1px solid #ccc;text-align:center;}
	table th {background-color:#aaa;}
</style>
</head>
	<body>
		<strong>user-list</strong>
		&nbsp;&nbsp;<a href="/user/addUI.sc">add user</a>
		<hr />
		<table>
			<tr>
				<th>user name</th>
				<th>password</th>
			</tr>
			<#if userList?? && userList?size gt 0>
			<#list userList as user>
			<tr>
				<td>${user.userName!""}</td>
				<td>${user.password!""}</td>
			</tr>
			</#list>
			</#if>
		</table>
	</body>
</html>