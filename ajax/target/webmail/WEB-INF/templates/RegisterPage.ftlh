<#import "common.ftl" as c>

<@c.page>
<div class="form-box">
    <div class="header">Register</div>
    <div class="body">
        <form method="post" action="/register">
            <input type="hidden" name="action" value="register">
            <div class="field">
                <div class="name">
                    <label for="login">Login</label>
                </div>
                <div class="value">
                    <input id="login" name="login" value="${login!}"/>
                </div>
            </div>

            <div class="field">
                <div class="name">
                    <label for="email">Email</label>
                </div>
                <div class="value">
                    <input id="email" type="text" name="email" value="${email!}"/>
                </div>
            </div>

            <div class="field">
                <div class="name">
                    <label for="password">Password</label>
                </div>
                <div class="value">
                    <input id="password" type="password" name="password" value="${password!}"/>
                </div>
            </div>
            <div class="button-field">
                <input type="submit" value="Register">
            </div>
            <div class="error">
            </div>
        </form>
    </div>
</div>
<script>
    $(function() {
        $(".form-box form").submit(function () {
            const $form = $(this);
            const login = $form.find("input[name='login']").val();
            const email = $form.find("input[name='email']").val();
            const password = $form.find("input[name='password']").val();
            $.ajax({
                method: "POST",
                data: {
                    action: "register",
                    login,
                    email,
                    password
                },
                dataType: "json",
                success: function(response){
                    if(response["error"]){
                        $form.find(".error").text(response["error"]);
                    }else{
                        if(response["redirect"]){
                            alert(response["redirect"]);
                            location.href = response["redirect"];
                        }
                    }

                }
            });
            // alert(login + " " + password);
            return false;
        });
    });
</script>
</@c.page>
