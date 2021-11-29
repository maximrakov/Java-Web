<#-- @ftlvariable name="users" type="ru.itmo.webmail.model.domain.User[]" -->
<#import "common.ftl" as c>

<@c.page>
<div class="users datatable">
    <div class="caption">Users</div>
    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>Login</th>
            <th>Registation</th>
        </tr>
        </thead>
        <tbody>
            <tr class="noData">
                <td colspan="3">No data</td>
            </tr>
            <template>
                <tr>
                    <td class="user-id"></td>
                    <td class="user-login"></td>
                    <td class="user-creationTime"></td>
                </tr>
            </template>
        </tbody>
    </table>
</div>

    <script>
        $(function() {
            const $users = $(".users");
            $users.find(".noData td").text("Loading...")
            const $tbody = $users.find("tbody");
            $.ajax({
                method: "POST",
                data: {
                    action: "findAll",
                },
                dataType: "json",
                success: function(response){
                    $users.find(".noData").remove();
                    console.log(response["users"]);
                    response["users"].forEach(function(user) {
                        const $tpl = $($users.find("template").prop("content")).clone();
                        $tpl.find("tr").attr("data-userId", user.id);
                        $tpl.find(".user-id").text(user.id);
                        $tpl.find(".user-login").text(user.login);
                        $tpl.find(".user-creationTime").text(user.creationTime);
                        $tbody.append($tpl);
                    });

                    $tbody.find("tr").click(function () {
                        const userId = $(this).attr("data-userId");
                        $.ajax({
                            method: "POST",
                            data: {
                                action: "find",
                                userId
                            },
                            dataType: "json",
                            success: function (response){
                                alert(response["user"].creationTime)
                            }
                        })
                        return false;
                    });
                }
            });
            return false;
        });
    </script>
</@c.page>
