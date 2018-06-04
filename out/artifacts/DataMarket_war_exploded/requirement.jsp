<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.liyihan.Request" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/head.css" type="text/css" rel="stylesheet">
    <style type="text/css">
        body {
            display: flex;
            flex-direction: column;
            margin: 0;
            padding: 0;
            position: relative;
        }

        p {
            padding: 0;
            margin: 0;
        }

        .body-requirement {
            display: flex;
            flex-direction: row;
            position: relative;
            width: 100%;
            flex-grow: 1;
            min-height: 600px;
            min-width: 1200px;
        }

        .body-requirement-navigation {
            box-sizing: border-box;
            width: 18%;
            min-width: 200px;
            padding: 20px;
        }

        .body-requirement-publish {
            flex-grow: 1;
            position: relative;
            box-sizing: border-box;
            padding: 20px;
            display: none;
        }

        .body-requirement-receive {
            flex-grow: 1;
            position: relative;
            box-sizing: border-box;
            padding: 20px;
        }

        .body-requirement-navigation-select-item {
            box-sizing: border-box;
            margin-top: 20px;
            width: 100%;
            font-size: 23px;
            text-align: center;
            padding: 8px;
            background: url("img/body-requirement-navigation-select-item-bg.png");
            background-size: cover;
            font-family: 'KaiTi';
            color: white;
            cursor: pointer;
        }

        .body-requirement-publish-fieldset {
            width: calc(100% - 40px);
            height: calc(80% - 40px);
            padding: 16px;
        }

        .body-requirement-publish-fieldset-content-container {
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            width: 100%;
            height: 400px;/*calc(100% - 30px);*/
            font-size: 20px;
        }

        .body-requirement-publish-fieldset-name-container {
            display: flex;
            flex-direction: row;
        }

        .body-requirement-publish-class-container {
            position: absolute;
            bottom: 15px;
            left: 25px;
        }

        .body-requirement-receive-container {
            box-sizing: border-box;
            border: 1px solid grey;
            padding: 5px 8px;
            position: relative;
            width: 100%;
            max-width: 100%;
            overflow: hidden;
            margin-top: 8px;
        }

        .body-requirement-receive-class {
            background: rgb(192, 255, 62);
            width: 80px;
            height: 40px;
            font-size: 20px;
            display: block;
            text-align: center;
            line-height: 40px;
        }

        .body-requirement-receive-name {
            height: 40px;
            line-height: 40px;
            font-size: 20px;
            margin-left: 20px
        }

        .body-requirement-receive-class-and-name {
            display: flex;
            flex-direction: row;
        }

        .body-requirement-receive-receive {
            position: absolute;
            right: 8px;
            top: 5px;
            height: 40px;
            width: 80px;
            font-size: 20px;
            background: aquamarine;
        }

        #body-requirement-publish-fieldset-name {
            padding: 2px 5px 2px 5px;
            font-size: 18px;
            margin-left: 16px;
        }

        #body-requirement-publish-fieldset-content {
            flex-grow: 1;
            margin-top: 20px;
            font-size: 26px;
        }

        #requirement-submit {
            position: absolute;
            bottom: 12px;
            right: 25px;
            width: 80px;
            height: 35px;
        }

    </style>

</head>


<body>
<script type="text/javascript" src="./js/JQuery.js">
</script>
<script type="text/javascript">
    $(document).ready(function () {
        $.post("getProfileSrc", {}, function (data) {
            if (data != "") {
                $("#profile").attr("src", data);
            } else {
                location.href = "index.html";
            }
        });
        content = $("#head-select-item-search-content");
        $("#head-select-item-search-content").bind('keypress', function (e) {
            if (e.keyCode == 13) {
                var form = document.getElementById('search-form');
                form.submit();
            }
        });
        $("#head-select-item-search-icon").click(function (e) {
            if ("none" == content.css("display")) {
                content.css("display", "block");
                content.animate({
                    width: "200px"
                }, "slow");
            } else {
                content.val("");
                content.animate({
                    width: "0"
                }, "slow", function () {
                    content.css("display", "none");
                });
            }
        });
        $(".head-select-item").mouseover(function (e) {
            if (e.target.id != "head-select-item-search-content") {
                $(e.target).css("color", "rgb(255, 127, 0)");
            }
        });
        $(".head-select-item").mouseout(function (e) {
            if (e.target.id != "head-select-item-search-content") {
                $(e.target).css("color", "white");
            }
        });
        $("#publish-requirement").click(function () {
            $(".body-requirement-receive").css("display", "none");
            $(".body-requirement-publish").css("display", "block");
        });
        $("#receive-requirement").click(function () {
            $(".body-requirement-publish").css("display", "none");
            $(".body-requirement-receive").css("display", "block");
        });
        $("#head-select-item-index").click(function () {
            location.href = "index.html";
        });
        $("#head-select-item-requirement").click(function () {
            //location.href="requirement.jsp";
            location.href = "RequestServlet";
        });
        $("#head-select-item-service").click(function () {
            location.href = "feedback.jsp";
        });
        $("#profile").click(function() {
            location.href="UserServlet";
        });
        $("#requirement-submit").click(function () {
            location.href="ReleaseServlet";
           /*$.post("postRequirement",
                {
                    "requirementName": $("#body-requirement-publish-fieldset-name").val(),
                    "requirementContent": $("#body-requirement-publish-fieldset-content").val()
                },
                function (data) {
                    if (data == "success") {
                        alert("success");
                        location.reload();
                    } else {
                        alert("fail");
                    }
                })*/
        });

        $(".body-requirement-receive-receive").click(function (e) {
            // alert(e.target.id);
            $.post(
                "AcceptServlet",
                    {
                        "requirement-receive-id": e.target.id
                    }, function (data) {
                    // alert(data);
                        if (data == "success") {
                            location.reload();
                        }
                }
            );
        });
    });
</script>
<div class="head-container">
    <div class="head-logo">
        <img src="img/logo.png">
    </div>

    <div class="head-select">
        <p class="head-select-item" id="head-select-item-index">
            首页
        </p>
        <p class="head-select-item" id="head-select-item-requirement">
            需求
        </p>
        <p class="head-select-item" id="head-select-item-service">
            客服
        </p>
        <form action="SearchServlet" method="post" id="search-form">
        <input class="head-select-item" id="head-select-item-search-content" name="search-keyword" type="text" placeholder="搜索">
        </form>
        <img class="head-select-item" id="head-select-item-search-icon" src="img/search.png">

    </div>
    <div class="head-profile">
        <img id="profile" src="img/profile.png">
    </div>
</div>
<div class="body-requirement">
    <div class="body-requirement-navigation">
        <p class="body-requirement-navigation-select-item" id="receive-requirement">接受需求</p>
        <p class="body-requirement-navigation-select-item" id="publish-requirement">发布需求</p>
    </div>

    <div class="body-requirement-publish">
        <form name="requestForm" method="post" action="ReleaseServlet">
        <fieldset class="body-requirement-publish-fieldset">
            <legend>委托内容</legend>
            <div class="body-requirement-publish-fieldset-content-container">
                <div class="body-requirement-publish-fieldset-name-container">
                    <p>名称&nbsp;:&nbsp;</p>
                    <input id="body-requirement-publish-fieldset-name" name="requirement-title">
                </div>
                <textarea id="body-requirement-publish-fieldset-content" name="requirement-content" placeholder="请输入详细内容"></textarea>
            </div>
        </fieldset>
        <div class="body-requirement-publish-class-container">
            <label>分类&nbsp;:&nbsp;</label>
            <select class="body-requirement-publish-class" id="body-requirement-publish-class" name="requirement-tag"
                    style="width: 60px; height: 30px;">
                <option value="科学">科学</option>
                <option value="人文">人文</option>
                <option value="艺术">艺术</option>
                <option value="经济">经济</option>
                <option value="娱乐">娱乐</option>
                <option value="学术">学术</option>
                <option value="其他">其他</option>
            </select>
        </div>
        <button id="requirement-submit">
            提交
        </button>
        </form>
    </div>

    <%List<Request> requests = (List<Request>)request.getAttribute("requests");%>
            <div class="body-requirement-receive">
                <c:forEach items="${requestScope.requests}" var="r">
                    <div class="body-requirement-receive-container">

                        <div class="body-requirement-receive-class-and-name">
                            <label class="body-requirement-receive-class" ><c:out value="${r.tag}" /></label><!--tag-->
                            <label class="body-requirement-receive-name" ><c:out value="${r.title}" /></label><!--title-->
                        </div>

                        <%--<form name="acceptForm" method="post" action="AcceptServlet">--%>

                            <%--<textarea rows="0" cols="0" hidden="hidden" name="requirement-id"></textarea>--%>
                            <button  class="body-requirement-receive-receive" id="${r.requestid}">领取</button><!--添加需求至数据库-->
                            <%--</form>--%>


                        <p style="margin-top:20px; width: 100%;word-wrap: break-word; word-break: break-all;">
                            <c:out value="${r.content}" />
                        </p>
                    </div>
                </c:forEach>
            </div>            

        </div>
    
    </body>
</html>