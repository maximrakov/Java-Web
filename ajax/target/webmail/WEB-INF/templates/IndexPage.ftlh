<#-- @ftlvariable name="message" type="java.lang.String" -->

<#import "common.ftl" as c>

<@c.page>
    <#if message??>
        <p>${message}</p>
    </#if>
    <h1>Welcome to the index page</h1>
    <div class="articles">
        <div class="loading">
            <p1>Loading...</p1>
        </div>
        <div class="article">
            <template>
                <div class="header">
                    <p1 class="title"></p1>
                </div>
                <div class="textOfMessage">
                    <p2 class="text"></p2>
                </div>
                <br><br>
            </template>

        </div>
    </div>
</@c.page>
<script>
    $(function() {
        const $articles = $(".articles");
        const $tbody = $articles.find(".article");
        $.ajax({
            method: "POST",
            data: {
                action: "findAllArticles",
            },
            dataType: "json",
            success: function(response) {
                // alert("kk?");
                $articles.find(".loading").remove();
                response["articles"].forEach(function(article){
                    // alert(article.text);
                    const $tpl = $($articles.find("template").prop("content")).clone();
                    $tpl.find(".title").text(article.title);
                    $tpl.find(".text").text(article.text);
                    $tbody.append($tpl);
                });

                // $tbody.find("tr").click(function () {
                //     const userId = $(this).attr("data-userId");
                //     $.ajax({
                //         method: "POST",
                //         data: {
                //             action: "find",
                //             userId
                //         },
                //         dataType: "json",
                //         success: function (response){
                //             alert(response["user"].creationTime)
                //         }
                //     })
                //     return false;
                // });
            }
        });
        return false;
    });
</script>