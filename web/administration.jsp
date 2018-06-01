<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.liyihan.*"%>

<!doctype html>
<html class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>后台管理</title>

    <link rel="stylesheet"
          href="css/amazeui.min.css">
    <link rel="stylesheet" href="css/admin.css">

</head>
<body>
<script type="text/javascript" src="./js/JQuery.js">
</script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#user-management").click(function () {
            $(".body-user-management").css("display", "block");
            $(".body-requirement-management").css("display", "none");
            $(".body-feedback-management").css("display", "none");
        });
        $("#requirement-management").click(function () {
            $(".body-requirement-management").css("display", "block");
            $(".body-user-management").css("display", "none");
            $(".body-feedback-management").css("display", "none");
        });
        $("#feedback-management").click(function () {
            $(".body-feedback-management").css("display", "block");
            $(".body-user-management").css("display", "none");
            $(".body-requirement-management").css("display", "none");
        });
        $(".table-delete").click(function (e) {
            // alert(e.target.id);
            $.post(
                "DeleteServlet",
                {
                    "requirement-delete-id": e.target.id
                }, function (data) {
                    // alert(data);
                    if (data == "success") {
                        alert('操作成功！');
                        location.reload();
                    }
                }
            );
        });
    });

</script>
<header class="am-topbar admin-header">
    <div class="am-topbar-brand">
        <strong>后台管理</strong>
    </div>

    <button
            class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
            data-am-collapse="{target: '#topbar-collapse'}">
        <span class="am-sr-only">导航切换</span>
        <span class="am-icon-bars"></span>
    </button>

</header>

<div class="am-cf admin-main">
    <!-- sidebar start -->
    <div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
        <div class="am-offcanvas-bar admin-offcanvas-bar">
            <ul class="am-list admin-sidebar-list">
                <li id="user-management" style="cursor: pointer;">
                    <a title="用户管理"><span
                            class="am-icon-pencil-square-o"></span> 用户管理</a>
                </li>

                <li  id="requirement-management"  style="cursor: pointer;">
                    <a title="需求管理"><span
                            class="am-icon-book"></span> 需求管理</a>
                </li>
                <li  id="feedback-management" style="cursor: pointer;">
                    <a title="反馈管理"><span
                            class="am-icon-bookmark"></span> 反馈管理</a>
                </li>
            </ul>


        </div>

    </div>
    <!-- sidebar end -->

    <!-- content start -->
    <div class="body-user-management" style="display: block;">
    <div class="admin-content">

        <div class="am-cf am-padding">
            <div class="am-fl am-cf">
                <strong>后台管理</strong>/
                <strong>用户信息</strong>
            </div>
        </div>
        <%List<User> users = (List<User>)request.getAttribute("users");%>

        <div class="am-g" style="height: 300px">
            <div class="am-u-sm-12">
                <form class="am-form">
                    <table
                            class="am-table am-table-striped am-table-hover table-main">
                        <thead>
                        <tr>
                            <th class="table-id">
                                序号
                            </th>

                            <th class="table-title">
                                用户名
                            </th>
                            <th class="table-title">
                                用户密码
                            </th>
                            <th class="table-title">
                                个性签名
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.users}" var="u">
                            <tr>
                            <td class="table-id">
                                <c:out value="${u.userid}" />
                            </td>
                            <td class="table-title">
                            <c:out value="${u.username}" />
                            </td>
                            <td class="table-title">
                            <c:out value="${u.password}" />
                            </td>
                            <td class="table-title">
                            <c:out value="${u.message}" />
                            </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>
            </div>

        </div>
    </div>
    <!-- content end -->
    </div>

    <div class="body-requirement-management" style="display: none;">
        <div class="admin-content">

            <div class="am-cf am-padding">
                <div class="am-fl am-cf">
                    <strong>后台管理</strong>/
                    <strong>需求信息</strong>
                </div>
            </div>
            <%List<Request> requests = (List<Request>)request.getAttribute("requests");%>

            <div class="am-g" style="height: 300px">
                <div class="am-u-sm-12">
                    <form class="am-form">
                        <table
                                class="am-table am-table-striped am-table-hover table-main">
                            <thead>
                            <tr>
                                <th class="table-id">
                                    序号
                                </th>

                                <th class="table-title">
                                    需求分类
                                </th>
                                <th class="table-title">
                                    需求标题
                                </th>
                                <th class="table-title">
                                    需求内容
                                </th>
                                <th class="table-title">
                                    操作
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.requests}" var="r">
                                <tr>
                                    <td class="table-id">
                                        <c:out value="${r.requestid}" />
                                    </td>
                                    <td class="table-title">
                                        <c:out value="${r.tag}" />
                                    </td>
                                    <td class="table-title">
                                        <c:out value="${r.title}" />
                                    </td>
                                    <td class="table-title">
                                        <c:out value="${r.content}" />
                                    </td>
                                    <td class="table-title">
                                        <label class="table-delete">
                                        <a class="am-btn am-btn-danger am-btn-xs" id="${r.requestid}">删除</a>
                                        </label>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </div>

            </div>
        </div>
        <!-- content end -->

    </div>


    <div class="body-feedback-management" style="display: none;">
        <div class="admin-content">

            <div class="am-cf am-padding">
                <div class="am-fl am-cf">
                    <strong>后台管理</strong>/
                    <strong>反馈信息</strong>
                </div>
            </div>
            <%List<Feedback> feedbacks = (List<Feedback>)request.getAttribute("feedbacks");%>

            <div class="am-g" style="height: 300px">
                <div class="am-u-sm-12">
                    <form class="am-form">
                        <table
                                class="am-table am-table-striped am-table-hover table-main">
                            <thead>
                            <tr>
                                <th class="table-id">
                                    序号
                                </th>

                                <th class="table-title">
                                    反馈分类
                                </th>
                                <th class="table-title">
                                    反馈标题
                                </th>
                                <th class="table-title">
                                    反馈内容
                                </th>
                                <th class="table-title">
                                    联系方式
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.feedbacks}" var="f">
                                <tr>
                                    <td class="table-id">
                                        <c:out value="${f.feedbackid}" />
                                    </td>
                                    <td class="table-title">
                                        <c:out value="${f.tagname}" />
                                    </td>
                                    <td class="table-title">
                                        <c:out value="${f.title}" />
                                    </td>
                                    <td class="table-title">
                                        <c:out value="${f.content}" />
                                    </td>
                                    <td class="table-title">
                                        <c:out value="${f.contact}" />
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </div>

            </div>
        </div>
        <!-- content end -->
    </div>


    <script src="js/jquery.min.js"></script>
    <script src="js/amazeui.min.js"></script>
</div>
</body>
</html>
