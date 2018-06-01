package com.liyihan;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class getProfileSrc extends HttpServlet {
 User newUser = new User();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("getProfileSrc doPost");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                int userId = LoginedManager.getInstance().getIdByCookie(cookie.getValue());
                System.out.println(userId);
                if (0 != userId) {
                    String avatar = newUser.getAvartarById(userId);
                    System.out.println(avatar);
                    response.getWriter().write(avatar);
                }
            }
        }
    }

}
