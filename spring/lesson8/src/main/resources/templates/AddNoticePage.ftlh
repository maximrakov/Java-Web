<#import "common.ftlh" as c>

<@c.page>
    <form action="/AddNotice" method="post">
        <div>
            <textarea id="content" name="content" value="${noticeForm.content!}" rows="5" cols="33">
            </textarea>
        </div>
        <div>
            <button>Submit</button>
        </div>
    </form>
</@c.page>