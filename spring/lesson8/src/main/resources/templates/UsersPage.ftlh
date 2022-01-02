<#import "common.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">Users</div>
        <table>
            <thead>
            <tr>
                <th>#</th>
                <th>Login</th>
                <th>Registation</th>
                <th>Disable user</th>
            </tr>
            </thead>
            <tbody>
            <#list persons as u>
                <tr>
                    <td>${u.id}</td>
                    <td>${u.login}</td>
                    <td>${u.creationTime}</td>
                    <td>
                        <form action="/changeAccess" method="post">
                            <div>
                                <input type="hidden" id="personId" name="personId" value="${u.id}">
                            </div>
                            <div>
                                <button>${u.access}</button>
                            </div>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>