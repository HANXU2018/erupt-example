<style>
    * {
        padding: 0;
        margin: 0;
    }
</style>
<div>
    <#-- title 为 bindTplData 返回的绑定数据 -->
    <h1 align="center">${title}</h1>
    <table border="1" cellpadding="0" style="width: 100%">
        <#list rows as row>
            <tr>
                <td>${row.id}</td>
                <td style="background: #09f;color: #fff">${row.name}</td>
                <td>${row.number}</td>
            </tr>
        </#list>
    </table>
</div>