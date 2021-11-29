<#import "common.ftl" as c>
<@c.page>
    <div class="articles">
        <table>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Message</th>
                    <th>Visibility</th>
                </tr>
            </thead>
            <tbody>
                <tr class="noData">
                    <td colspan="3">Loading...</td>
                </tr>
                <template>
                    <tr>
                        <td class="title"></td>
                        <td class="message"></td>
                        <td class="visibility"> <input type="submit" value="wefj"> </td>
                    </tr>
                </template>
            </tbody>
        </table>
    </div>
    <script>
        $(function() {
            const $articles = $(".articles");
            $articles.find(".noData").remove();
            const $tbody = $articles.find("tbody");
            $.ajax({
                method: "POST",
                data: {
                    action: "findAllArticles",
                },
                dataType: "json",
                success: function(response){
                    artcls = response["articless"];
                    artcls.forEach(function(article){
                        const $tpl = $($articles.find("template").prop("content")).clone();
                         $tpl.find(".visibility input").attr("articleId", article.id);
                        $tpl.find(".title").text(article.title);
                        $tpl.find(".message").text(article.text);
                        if(article.hide) {
                            $tpl.find(".visibility input")[0].setAttribute("value", "show")
                        }else{
                            $tpl.find(".visibility input")[0].setAttribute("value", "hide");
                        }
                        $tbody.append($tpl);
                    });

                    $tbody.find("tr input").click(function () {
                        const articleId = $(this).attr("articleId");
                        const currentButton = $(this)[0];
                        /*alert(articleId);*/
                        $.ajax({
                            method: "POST",
                            data: {
                                action: "changeVisibility",
                                articleId
                            },
                            dataType: "json",
                            success: function (response){
                                console.log(currentButton);
                                if(currentButton.getAttribute("value") == "show") {
                                    currentButton.setAttribute("value", "hide")
                                }else {
                                    currentButton.setAttribute("value", "show");
                                }
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