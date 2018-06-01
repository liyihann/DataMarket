
         $(document).ready(function() {
                content = $("#head-select-item-search-content");
                $("#head-select-item-search-content").bind('keypress', function(e) {
                    if (e.keyCode == 13) {
                        alert("搜索" + content.val());
                    }
                });
                $(".head-select-item").mouseover(function(e) {
                    $(e.target).css("color", "red");
                });
                $(".head-select-item").mouseout(function(e) {
                    $(e.target).css("color", "white");
                });
                $("#head-select-item-search-icon").click(function(e) {
                    if ("none" == content.css("display")) {
                        content.css("display", "block");
                        content.animate({
                            width: "27%"
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
                sign_up_exchange=$("#sign-up");
                sign_up_exchange.hover(function(){
                    sign_up_exchange.css("color","rgba(46, 130, 255, 0.5)");
                }, function() {
                    sign_up_exchange.css("color","#2e82ff");
                });
                sign_up_exchange.click(function(e) {
                    if (sign_up_exchange.text()=="登录") {
                        sign_up_exchange.text("注册");
                        $("#make-sure").val("登录");
                    } else {
                        sign_up_exchange.text("登录");
                        $("#make-sure").val("注册");
                    }
                });
                $("#sign-in-or-up").click(function(e) {
                    $(".sign-container").css("display", "block");
                    $("#background-window").show();
                });
                $("#flow-close").click(function(e) {
                    $(".sign-container").css("display", "none");
                    $("#background-window").hide();
                });
                flow_close=$("#flow-close");
                flow_close.hover(function(){
                    flow_close.attr("src", "img/close-hover.png");
                }, function() {
                    flow_close.attr("src", "img/close.png");
                });
            });
        