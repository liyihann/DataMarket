<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.liyihan.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            我的信息
        </title>
        <link href="css/head.css" type="text/css" rel="stylesheet">
		<link href="css/tablestyle.css" type="text/css" rel="stylesheet" media="all">
		<link href="css/bottom.css" type="text/css" rel="stylesheet">
  <style>
	 body {
                display: flex;
                flex-direction: column;
                margin: 0;
                padding: 0;
                position: relative;
				min-width: 600px;
            }
        
            p {
                padding: 0;
                margin: 0;
            }
			.requirement-container {
                box-sizing: border-box;
                border: 1px solid grey;
                padding: 5px 8px;
                position: relative;
                width: 100%;
            }
			.requirement-class {
                background-color: rgb(192, 255, 62);
                width: 80px;
                height: 40px;
                font-size: 20px;
                display: block;
                text-align: center;
                line-height: 40px;
				background-color: rgba(0,0,0,0.30);
            }
            .requirement-name {
				color: black;
                height: 40px;
                line-height: 40px;
                font-size: 20px;
                margin-left: 20px
            }
            .requirement-class-and-name {
                display: flex;
                flex-direction: row;
            }
			.requirement-status{
				text-align:center;
				position: absolute;
                right: 8px;
                top: 5px;
                height: 40px;
                width: 80px;
                font-size: 20px;
                background: aquamarine;
			}
   </style>
        <script type="text/javascript" src="./js/JQuery.js">
        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $.post("getProfileSrc", {} , function (data) {
                    if (data != "") {
                        $("#profile").attr("src", data);
                    } else {
                        location.href="index.html";
                    }
                });
                content = $("#head-select-item-search-content");
                $("#head-select-item-search-content").bind('keypress', function(e) {
                    if (e.keyCode == 13) {
                        alert("搜索" + content.val());
                    }
                });
				$("#head-select-item-search-icon").click(function(e) {
                    if ("none" == content.css("display")) {
                        content.css("display", "block");
                        content.animate({
                            width: "200px"
                        }, "slow");
                    } else {
                        content.val("");
                        content.animate({
                            width: "0"
                        }, "slow", function() {
                            content.css("display", "none");
                        });
                    }
                });
                $(".head-select-item").mouseover(function(e) {
                    if (e.target.id != "head-select-item-search-content") {
                        $(e.target).css("color", "rgb(255, 127, 0)");
                    }
                });
                $(".head-select-item").mouseout(function(e) {
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
                $("#head-select-item-index").click(function() {
                    location.href="index.html"; 
                });                
                $("#head-select-item-requirement").click(function() {
                    //location.href="requirement.jsp";
                    location.href="RequestServlet";
                });
                $("#head-select-item-service").click(function() {
                    location.href="feedback.jsp";
                });
                $("#profile").click(function() {
                    location.href="UserServlet";
                });
                $("#requirement-submit").click(function () {
					$.post("postRequirement",
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
                    })
                });
            });
        </script>
       <script type="text/javascript">
		function tab(pid){
		  var tabs=["myinformation","requires-made","requires-accept"];
		  for(var i=0;i<3;i++){
			if(tabs[i]==pid){
				document.getElementById(tabs[i]).style.display="block";
				if(i==0){
					document.getElementById("title1_information").style= "width:100%;background-color:#66FFFF";
					document.getElementById("title2_information").style= "width:100%;background-color:#CCFFFF";
					document.getElementById("title3_information").style= "width:100%;background-color:#CCFFFF";
				}else if (i==1){
					document.getElementById("title1_information").style= "width:100%;background-color:#CCFFFF";
					document.getElementById("title2_information").style= "width:100%;background-color:#66FFFF";
					document.getElementById("title3_information").style= "width:100%;background-color:#CCFFFF";
				}else{
					document.getElementById("title1_information").style= "width:100%;background-color:#CCFFFF";
					document.getElementById("title2_information").style= "width:100%;background-color:#CCFFFF";
					document.getElementById("title3_information").style= "width:100%;background-color:#66FFFF";
				}
			}else{
				document.getElementById(tabs[i]).style.display="none";
				}
			}
		}
</script>
    </head>
    <body>
        <div class="head-container">
            <div class="head-logo" >
                <img src="img/logo.png" >
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
                
                <input class="head-select-item" id="head-select-item-search-content" type="text" placeholder="搜索">
                
                <img class="head-select-item" id="head-select-item-search-icon" src="img/search.png">

            </div>
            <div class="head-profile">
                <img id="profile" src="img/profile.png">
            </div>
        </div>
		<div style="margin-top: 5px; height: 550px" class="container">
       <div class="all-my-information">
	   	<div class="myinformation-all">
			
				<div class="tabs">
				<ul class="myul">
				<li style= "width:100%;background-color:#66FFFF" onclick="tab('myinformation')" id="title1_information" class="mytitle-information">我的信息
				</li>
				<li style= "width:100%;background-color:#CCFFFF" onclick="tab('requires-made')" id="title2_information" class="mytitle-information">我接收的需求
				</li>
				<li style= "width:100%;background-color:#CCFFFF" onclick="tab('requires-accept')" id="title3_information" class="mytitle-information">我发布的需求
				</li>
				</li>
				</li>
				</ul>
				</div>
			
		</div>

		<div class="twoChoice">
            <%User user = (User)request.getAttribute("user");%>
        <div id="myinformation">
			<form id="test" action="UpdateServlet" method="post">
			<fieldset>
				<legend>我的信息</legend>
				<div class="form-row">
					<div class="field-label"><label>昵称:</label></div>
					<div class="field-widget">
                        <input name="nick-name" id="name" class="required" title="Enter your name" value="<c:out value="${user.username}" />"/>

                    </div>
				</div>
<div class="form-row">
			  <div class="field-label"><label>个性签名:</label></div>
					<div class="field-widget"><textarea class="required" name="message"><c:out value="${user.message}" /></textarea></div>
				</div>
        
        
			</fieldset>
    

			<input type="submit" class="submit" value="保存" /> 
			</form>
		</div>
		
		<div id="requires-made" style="display:none">
            <%//List<Request> accepted = (List<Request>)request.getAttribute("accepted");%>
			<fieldset>
				<div class="form-row">
					<div class="field-label"><label>我接受的需求</label>:</div>
				</div>
			</fieldset>
            <c:forEach items="${requestScope.accepted}" var="ac">
			    <div class="requirement-container">

                    <div class="requirement-class-and-name">
                        <label class="requirement-class" ><c:out value="${ac.tag}" /></label>
                        <label class="requirement-name" ><c:out value="${ac.title}" /></label>
                    </div>

					<label class="requirement-status"><c:out value="${ac.statename}" /></label>
                      <p style="margin-top:20px; width: 100%;word-wrap: break-word; word-break: break-all;">
                       <c:out value="${ac.content}" />
                      </p>
                </div>
            </c:forEach>
		</div>

		<div id="requires-accept" style="display:none">	
			<fieldset>
				<div class="form-row">
					<div class="field-label"><label>我发布的需求</label>:</div>
				</div>
			</fieldset>
            <c:forEach items="${requestScope.released}" var="re">
			    <div class="requirement-container">

                    <div class="requirement-class-and-name">
                        <label class="requirement-class" ><c:out value="${re.tag}" /></label>
                        <label class="requirement-name" ><c:out value="${re.title}" /></label>
                    </div>
					<label class="requirement-status"><c:out value="${re.statename}" /></label>
                      <p style="margin-top:20px; width: 100%;word-wrap: break-word; word-break: break-all;">
                          <c:out value="${re.content}" />
                      </p>
                </div>
            </c:forEach>
		</div>
		
		</div>
	   </div>
	   </div>

    </body>
</html>