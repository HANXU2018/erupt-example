<div>
    <#-- title 为 bindTplData 返回的绑定数据 -->
    <#list .data_model?keys as key>
        ${key}
    </#list>

    <h1>${title}</h1>

<#--    <#list keys.request as key>-->
<#--        ${key}-->
<#--    </#list>-->
<#--    <#list keys.response as key>-->
<#--        ${key}-->
<#--    </#list>-->
    <#list rows as key>
        ${key}
    </#list>

    <table border="1" cellpadding="0" style="width: 100%">
        <tr>
            <th>id</th>
            <th>name</th>
            <th>number</th>
        </tr>

        <#list rows as row>
            <tr>
                <td>${row.id}</td>
                <td style="background: #bef3d1;color: #040404">${row.name}</td>
                <td>${row.number}</td>
            </tr>
        </#list>
    </table>
</div>