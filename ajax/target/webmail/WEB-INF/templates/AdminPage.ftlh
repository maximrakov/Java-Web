<#import "common.ftl" as c>
<@c.page>
    <div class="users">
        <table>
            <thead>
            <tr>
                <th>User</th>
                <th>Admin</th>
            </tr>
            </thead>
            <tbody>
            <tr class="noData">
                <td colspan="2">Loading...</td>
            </tr>
            <template>
                <tr>
                    <td class="user-login"></td>
                    <td class="admin"> <input type="submit" value=""> </td>
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
                url: "http://localhost:8080/users",
                method: "POST",
                data: {
                    action: "findAll",
                },
                dataType: "json",
                success: function(response){
                    $users.find(".noData").remove();
                    response["users"].forEach(function(user) {
                        const $tpl = $($users.find("template").prop("content")).clone();
                        $tpl.find("tr").attr("data-userId", user.id);
                        $tpl.find(".user-login").text(user.login);
                        if(user.admin){
                            $tpl.find(".admin input")[0].setAttribute("value","Not Admin");
                        }else{
                            $tpl.find(".admin input")[0].setAttribute("value","Admin");
                        }
                        $tbody.append($tpl);
                    });

                    $tbody.find("tr input").click(function () {
                        const userId = $(this).attr("data-userId");
                        $.ajax({
                            method: "POST",
                            data: {
                                action: "changeAdminRoot",
                                userid: userId
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