<#import "common.ftlh" as c>

<@c.page>
    <@c.post post/>
    <div class="writeComment">
        <div class="body">
            <form method="post" action="/makeComment">
                <div class="field">
                    <input type="hidden" id="postNum" name="postNum" value=${post.id}>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text">Comment</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text"></textarea>
                    </div>
                </div>
                <div class="button-field">
                    <input type="submit" value="Publish">
                </div>
                <div class="error">
                </div>
            </form>
        </div>
    </div>
    <#list post.comments as comment>
        <@c.comment comment/>
    </#list>
</@c.page>
