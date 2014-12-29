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
</div>