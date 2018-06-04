<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<title>客服</title>
	 <style type="text/css">
            body {
                display: flex;
                flex-direction: column;
                margin: 0;
                padding: 0;
                position: relative;
				min-width: 600px;
				height: 100%;
            }
        
            p {
                padding: 0;
                margin: 0;
            }
        
        </style>
	<link href="css/head.css" type="text/css" rel="stylesheet">
	<link href="css/service-page.css" type="text/css" rel="stylesheet">
	<link href="css/bottom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<script src="./js/JQuery.js"></script>
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
                        var form = document.getElementById('search-form');
                        form.submit();
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
                $("#head-select-index").click(function() {
                    location.href="index.html";
                });                
                $("#head-select-requirement").click(function() {
                    //location.href="requirement.jsp";
                    location.href="RequestServlet";
                });
                $("#head-select-service").click(function() {
                    location.href="feedback.jsp";
                });
            	$("#profile").click(function() {
                	location.href="UserServlet";
            	});
            	$("#feedback-submit").click(function () {
                location.href="FeedbackServlet";
            });
		});
	</script>
	<!-- 顶栏 -->

	
        <div class="head-container">
            <div class="head-logo" >
           		<a href="index.html"><img src="img/logo.png"></a>
       		</div>
	        <div class="head-logo-text"></div>
            <div class="head-select">
                <p class="head-select-item" id="head-select-index">
                    首页
                </p>
                <p class="head-select-item" id="head-select-requirement">
                    需求
                </p>                
                <p class="head-select-item" id="head-select-service">
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
   
   	<!-- 提交给人工客服信息 -->
    <div class="service-make">
	    <div class="service-make-request">
	    	<div class="service-make-request-item-l">
	    		<fieldset style="margin-top: 13px">
	    			<legend style="font-size: 25px" >关于</legend>
		    		<div class="label">
		    			<label for="field1" style="font-size: 18px">
		    				您可以通过填写右侧的表格向我们的专业客服提出问题，留下您的联系方式（手机号或者邮箱），我们的客服将会尽快与您取得联系。
		    				<br>
		    				请确保正确选择问题类型，并且在问题简述中用简练的语言描述，这将有助于我们更快处理您的请求。
		    			</label>
		    		</div>
		    	</fieldset>	
		    	<fieldset style="margin-top: 14px">
		    		<legend style="font-size: 25px" >联系我们</legend>
		    		<div class="label">
		    			<label for="field1" style="font-size: 18px">
		    				邮箱：XXXXXXX@163.com
		    				<br>
		    				电话：152XXXXXXXX
							<br>
							公司地址：湖北省武汉市洪山区珞喻路珞珈创意体验城
						</label>
		    	</fieldset>
	    	</div>
	    	<div class="service-make-request-item-r">
	    		<form id="test" action="FeedbackServlet" method="post" onsubmit="sumbit_success()">
					<fieldset>
						<legend style="font-size: 30px" >人工客服</legend>
						<div class="form-row">
							<div class="field-label"><label for="field1">问题简述</label>:</div>
							<div class="field-widget"><input name="feedback-title" id="field1" class="required" title="Enter your name"></div>
						</div>
						<div class="form-row">
						<div class="field-label"><label for="field6">类别</label>:</div>
							<div class="field-widget">
								<select id="field6" name="feedback-tag" class="validate-selection" title="选择委托所属的学术领域">
									<option>问卷定制</option>
									<option>服务咨询</option>
									<option>问题反馈</option>
									<option>投诉</option>
									<option>其他</option>
								</select>
							</div>
						</div>
						<div class="form-row">
							<div class="field-label"><label>问题详述</label>:</div>
							<div class="field-widget"><textarea class="required" name="feedback-content"></textarea></div>
						</div>
						<div class="form-row">
							<div class="field-label"><label>联系方式</label>:</div>
							<div class="field-widget"><input name="contact" id="field4" class="required" title="Enter your name">
							</div>
						</div>
					</fieldset>
					<input type="submit" id="feedback-submit" class="submit" value="提交" style="float: left;">
					<input class="reset" type="button" value="清空" onclick="reset(); return false" >
				</form>
	    	</div>
		</div>
	</div>

</body>
</html>