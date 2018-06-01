<%@ page import="com.liyihan.Admin" %>
<%@ page import="com.liyihan.Feedback" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <style type="text/css">
        .detail-lable {
            display: inline-block;
            font-size: 20px;
            width: 100px;
            text-align: right;
            margin: 5px;
            flex-shrink: 0;
        }
        .detail-content-label {
            display: inline-block;
            font-size: 20px;
            margin: 5px 0;
        }
        .div-container {
            display: flex;
            flex-direction: row;
        }
        .administrate-navigation {
            width: 200px;
            box-sizing: border-box;
            margin: 0;
            position: relative;
            border-right: 1px solid black;
        }
        .navigation-container {
            margin: 8px;
            width: 180px;
            box-sizing: border-box;
            height: 120px;
            position: relative;
            background-color: rgba(255, 127, 0, 0.5);
            cursor: pointer;
        }
        .navigation-class {
            position: absolute;
            top: 15px;
            left: 10px;
        }
        .navigation-name {
            position: absolute;
            top: 15px;
            right: 10px;
        }
        .navigation-content {
            position: absolute;
            bottom: 0;
            left: 10px;
            width: 160px;
            text-overflow: ellipsis;
            overflow: hidden;
            display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 1;
        }
        #detail-reply {
            flex-grow: 1;
            height: 400px;
            width:1000px;
        }
        #reply-submit {
            position: absolute; 
            right: 0;
            margin: 20px;
            width: calc(100% - 345px);
            height: 50px; 
            background-color: rgba(0, 127, 255, 0.5);
        }
    </style>
    <title>客服处理</title>
</head>
<body style="padding: 0; margin: 0;">
    <script src="./js/JQuery.js"></script>
    <script type="text/javascript">
        targetId = 0;
        $(document).ready(function() {
            $.post("getAdminId", {} , function (data) {
                if (data == "fail") {
                    alert("请先登录");
                    location.href="backend.html";
                } else {
                    $("#administrator-id").text(data);
                    $.post("getFeedback", {}, function (data) {
                        $(".administrate-navigation").text(data);
                    });
                }
            });
            $(".navigation-container").click(function(e) {
                targetId = e.target.id;
                $("#detail-class").text($("#" + e.target.id).find(".navigation-class").text());
                $("#detail-name").text($("#" + e.target.id).find(".navigation-name").text());
                $("#detail-content").text($("#" + e.target.id).find(".navigation-content").text());
            });
            $("#reply-submit").click(function (e) {
               $.post("dealFeedback", {
                   "id": targetId,
                   "reply-content": $("#detail-reply").val()
               }, function (data) {
                   if (data == "success") {
                       alert("success");
                   } else {
                       alert("fail");
                   }
               });
            });
        });
    </script>

    <%Admin admin = (Admin)request.getAttribute("admin");%>

    <div class="div-container">
        <label class="detail-lable">工号&nbsp;:&nbsp;</label>
        <label class="detail-content-label" id="administrator-id">
            <c:out value="${admin.adminid}" />
        </label>
    </div>
    <%List<Feedback> feedback = (List<Feedback>)request.getAttribute("feedback");%>
    <div style="display: flex;flex-direction: row; padding: 10px;">
        <div class="administrate-navigation">
            <c:forEach items="${requestScope.feedback}" var="fe">

                <div class="navigation-container" id="${fe.feedbackid}">
                <label class="navigation-class"><c:out value="${fe.tagname}" /></label>
                <label class="navigation-name"><c:out value="${fe.title}" /></label>
                <p class="navigation-content" >
                    <c:out value="${fe.content}" />
                </p>
            </div>
            </c:forEach>
        </div>
        <%Feedback firstf = (Feedback) request.getAttribute("firstf");%>
        <div style="margin: 0;">
            <div class="div-container">
                <label class="detail-lable">类别&nbsp;:&nbsp;</label>
                <label class="detail-content-label" id="detail-class">
                    <c:out value="${firstf.tagname}" />
                </label>
            </div>
            <div class="div-container">
                <label class="detail-lable">名称&nbsp;:&nbsp;</label>
                <label class="detail-content-label" id="detail-name">
                    <c:out value="${firstf.title}" />
                </label>
            </div>
            <div class="div-container">
                <label class="detail-lable">问题内容&nbsp;:&nbsp;</label>
                <label class="detail-content-label" id="detail-content">
                    <c:out value="${firstf.content}" />
                </label>
            </div>
            <div class="div-container">
                <label class="detail-lable">回复&nbsp;:&nbsp;</label>
                <textarea class="detail-content-label" id="detail-reply"></textarea>
            </div>
            <button id="reply-submit">提交</button>
        </div>
    </div>
</body>
</html>
