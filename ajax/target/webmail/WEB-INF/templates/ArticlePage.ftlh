<#import "common.ftl" as c>
<@c.page>
    <div class="fb">
        <form method="post" action="/article">
            <input type="hidden" name="action" value="send">
            <div class="field">
                <div class="name">
                    <label for="title">Title</label>
                </div>
                <div class="value">
                    <input id="title" type="text" name="title" value="${title!}"/>
                </div>
            </div>
            <textarea id="text" name="text">
            </textarea>
            <div class="button-field">
                <input type="submit" value="Send article">
            </div>
        </form>
    </div>
<script>
    $(function() {
        $("fb").submit(function () {
            const title = $form.find("input[name='title']").val();
            const text = $form.find("input[name='text']").val();
            $.ajax({
                method: "POST",
                data: {
                    action: "register",
                    title,
                    text
                },
                dataType: "json",
                success: function(response){
                    alert("Yep");
                }
            });
            return false;
        });
    });
</script>
</@c.page>